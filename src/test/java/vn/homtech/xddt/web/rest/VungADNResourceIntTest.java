package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.VungADN;
import vn.homtech.xddt.repository.VungADNRepository;
import vn.homtech.xddt.repository.search.VungADNSearchRepository;
import vn.homtech.xddt.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static vn.homtech.xddt.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VungADNResource REST controller.
 *
 * @see VungADNResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class VungADNResourceIntTest {

    private static final String DEFAULT_MA_VUNG_ADN = "AAAAAAAAAA";
    private static final String UPDATED_MA_VUNG_ADN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_VUNG_ADN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_VUNG_ADN = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private VungADNRepository vungADNRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.VungADNSearchRepositoryMockConfiguration
     */
    @Autowired
    private VungADNSearchRepository mockVungADNSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVungADNMockMvc;

    private VungADN vungADN;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VungADNResource vungADNResource = new VungADNResource(vungADNRepository, mockVungADNSearchRepository);
        this.restVungADNMockMvc = MockMvcBuilders.standaloneSetup(vungADNResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VungADN createEntity(EntityManager em) {
        VungADN vungADN = new VungADN()
            .maVungADN(DEFAULT_MA_VUNG_ADN)
            .tenVungADN(DEFAULT_TEN_VUNG_ADN)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED);
        return vungADN;
    }

    @Before
    public void initTest() {
        vungADN = createEntity(em);
    }

    @Test
    @Transactional
    public void createVungADN() throws Exception {
        int databaseSizeBeforeCreate = vungADNRepository.findAll().size();

        // Create the VungADN
        restVungADNMockMvc.perform(post("/api/vung-adns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vungADN)))
            .andExpect(status().isCreated());

        // Validate the VungADN in the database
        List<VungADN> vungADNList = vungADNRepository.findAll();
        assertThat(vungADNList).hasSize(databaseSizeBeforeCreate + 1);
        VungADN testVungADN = vungADNList.get(vungADNList.size() - 1);
        assertThat(testVungADN.getMaVungADN()).isEqualTo(DEFAULT_MA_VUNG_ADN);
        assertThat(testVungADN.getTenVungADN()).isEqualTo(DEFAULT_TEN_VUNG_ADN);
        assertThat(testVungADN.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testVungADN.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the VungADN in Elasticsearch
        verify(mockVungADNSearchRepository, times(1)).save(testVungADN);
    }

    @Test
    @Transactional
    public void createVungADNWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vungADNRepository.findAll().size();

        // Create the VungADN with an existing ID
        vungADN.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVungADNMockMvc.perform(post("/api/vung-adns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vungADN)))
            .andExpect(status().isBadRequest());

        // Validate the VungADN in the database
        List<VungADN> vungADNList = vungADNRepository.findAll();
        assertThat(vungADNList).hasSize(databaseSizeBeforeCreate);

        // Validate the VungADN in Elasticsearch
        verify(mockVungADNSearchRepository, times(0)).save(vungADN);
    }

    @Test
    @Transactional
    public void getAllVungADNS() throws Exception {
        // Initialize the database
        vungADNRepository.saveAndFlush(vungADN);

        // Get all the vungADNList
        restVungADNMockMvc.perform(get("/api/vung-adns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vungADN.getId().intValue())))
            .andExpect(jsonPath("$.[*].maVungADN").value(hasItem(DEFAULT_MA_VUNG_ADN.toString())))
            .andExpect(jsonPath("$.[*].tenVungADN").value(hasItem(DEFAULT_TEN_VUNG_ADN.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getVungADN() throws Exception {
        // Initialize the database
        vungADNRepository.saveAndFlush(vungADN);

        // Get the vungADN
        restVungADNMockMvc.perform(get("/api/vung-adns/{id}", vungADN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vungADN.getId().intValue()))
            .andExpect(jsonPath("$.maVungADN").value(DEFAULT_MA_VUNG_ADN.toString()))
            .andExpect(jsonPath("$.tenVungADN").value(DEFAULT_TEN_VUNG_ADN.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVungADN() throws Exception {
        // Get the vungADN
        restVungADNMockMvc.perform(get("/api/vung-adns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVungADN() throws Exception {
        // Initialize the database
        vungADNRepository.saveAndFlush(vungADN);

        int databaseSizeBeforeUpdate = vungADNRepository.findAll().size();

        // Update the vungADN
        VungADN updatedVungADN = vungADNRepository.findById(vungADN.getId()).get();
        // Disconnect from session so that the updates on updatedVungADN are not directly saved in db
        em.detach(updatedVungADN);
        updatedVungADN
            .maVungADN(UPDATED_MA_VUNG_ADN)
            .tenVungADN(UPDATED_TEN_VUNG_ADN)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED);

        restVungADNMockMvc.perform(put("/api/vung-adns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVungADN)))
            .andExpect(status().isOk());

        // Validate the VungADN in the database
        List<VungADN> vungADNList = vungADNRepository.findAll();
        assertThat(vungADNList).hasSize(databaseSizeBeforeUpdate);
        VungADN testVungADN = vungADNList.get(vungADNList.size() - 1);
        assertThat(testVungADN.getMaVungADN()).isEqualTo(UPDATED_MA_VUNG_ADN);
        assertThat(testVungADN.getTenVungADN()).isEqualTo(UPDATED_TEN_VUNG_ADN);
        assertThat(testVungADN.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testVungADN.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the VungADN in Elasticsearch
        verify(mockVungADNSearchRepository, times(1)).save(testVungADN);
    }

    @Test
    @Transactional
    public void updateNonExistingVungADN() throws Exception {
        int databaseSizeBeforeUpdate = vungADNRepository.findAll().size();

        // Create the VungADN

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVungADNMockMvc.perform(put("/api/vung-adns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vungADN)))
            .andExpect(status().isBadRequest());

        // Validate the VungADN in the database
        List<VungADN> vungADNList = vungADNRepository.findAll();
        assertThat(vungADNList).hasSize(databaseSizeBeforeUpdate);

        // Validate the VungADN in Elasticsearch
        verify(mockVungADNSearchRepository, times(0)).save(vungADN);
    }

    @Test
    @Transactional
    public void deleteVungADN() throws Exception {
        // Initialize the database
        vungADNRepository.saveAndFlush(vungADN);

        int databaseSizeBeforeDelete = vungADNRepository.findAll().size();

        // Get the vungADN
        restVungADNMockMvc.perform(delete("/api/vung-adns/{id}", vungADN.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VungADN> vungADNList = vungADNRepository.findAll();
        assertThat(vungADNList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the VungADN in Elasticsearch
        verify(mockVungADNSearchRepository, times(1)).deleteById(vungADN.getId());
    }

    @Test
    @Transactional
    public void searchVungADN() throws Exception {
        // Initialize the database
        vungADNRepository.saveAndFlush(vungADN);
        when(mockVungADNSearchRepository.search(queryStringQuery("id:" + vungADN.getId())))
            .thenReturn(Collections.singletonList(vungADN));
        // Search the vungADN
        restVungADNMockMvc.perform(get("/api/_search/vung-adns?query=id:" + vungADN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vungADN.getId().intValue())))
            .andExpect(jsonPath("$.[*].maVungADN").value(hasItem(DEFAULT_MA_VUNG_ADN)))
            .andExpect(jsonPath("$.[*].tenVungADN").value(hasItem(DEFAULT_TEN_VUNG_ADN)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VungADN.class);
        VungADN vungADN1 = new VungADN();
        vungADN1.setId(1L);
        VungADN vungADN2 = new VungADN();
        vungADN2.setId(vungADN1.getId());
        assertThat(vungADN1).isEqualTo(vungADN2);
        vungADN2.setId(2L);
        assertThat(vungADN1).isNotEqualTo(vungADN2);
        vungADN1.setId(null);
        assertThat(vungADN1).isNotEqualTo(vungADN2);
    }
}
