package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.ThongTinADN;
import vn.homtech.xddt.repository.ThongTinADNRepository;
import vn.homtech.xddt.repository.search.ThongTinADNSearchRepository;
import vn.homtech.xddt.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
 * Test class for the ThongTinADNResource REST controller.
 *
 * @see ThongTinADNResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class ThongTinADNResourceIntTest {

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_DU_LIEU = "AAAAAAAAAA";
    private static final String UPDATED_FILE_DU_LIEU = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ThongTinADNRepository thongTinADNRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.ThongTinADNSearchRepositoryMockConfiguration
     */
    @Autowired
    private ThongTinADNSearchRepository mockThongTinADNSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restThongTinADNMockMvc;

    private ThongTinADN thongTinADN;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThongTinADNResource thongTinADNResource = new ThongTinADNResource(thongTinADNRepository, mockThongTinADNSearchRepository);
        this.restThongTinADNMockMvc = MockMvcBuilders.standaloneSetup(thongTinADNResource)
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
    public static ThongTinADN createEntity(EntityManager em) {
        ThongTinADN thongTinADN = new ThongTinADN()
            .moTa(DEFAULT_MO_TA)
            .fileDuLieu(DEFAULT_FILE_DU_LIEU)
            .isDeleted(DEFAULT_IS_DELETED);
        return thongTinADN;
    }

    @Before
    public void initTest() {
        thongTinADN = createEntity(em);
    }

    @Test
    @Transactional
    public void createThongTinADN() throws Exception {
        int databaseSizeBeforeCreate = thongTinADNRepository.findAll().size();

        // Create the ThongTinADN
        restThongTinADNMockMvc.perform(post("/api/thong-tin-adns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongTinADN)))
            .andExpect(status().isCreated());

        // Validate the ThongTinADN in the database
        List<ThongTinADN> thongTinADNList = thongTinADNRepository.findAll();
        assertThat(thongTinADNList).hasSize(databaseSizeBeforeCreate + 1);
        ThongTinADN testThongTinADN = thongTinADNList.get(thongTinADNList.size() - 1);
        assertThat(testThongTinADN.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testThongTinADN.getFileDuLieu()).isEqualTo(DEFAULT_FILE_DU_LIEU);
        assertThat(testThongTinADN.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the ThongTinADN in Elasticsearch
        verify(mockThongTinADNSearchRepository, times(1)).save(testThongTinADN);
    }

    @Test
    @Transactional
    public void createThongTinADNWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thongTinADNRepository.findAll().size();

        // Create the ThongTinADN with an existing ID
        thongTinADN.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongTinADNMockMvc.perform(post("/api/thong-tin-adns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongTinADN)))
            .andExpect(status().isBadRequest());

        // Validate the ThongTinADN in the database
        List<ThongTinADN> thongTinADNList = thongTinADNRepository.findAll();
        assertThat(thongTinADNList).hasSize(databaseSizeBeforeCreate);

        // Validate the ThongTinADN in Elasticsearch
        verify(mockThongTinADNSearchRepository, times(0)).save(thongTinADN);
    }

    @Test
    @Transactional
    public void getAllThongTinADNS() throws Exception {
        // Initialize the database
        thongTinADNRepository.saveAndFlush(thongTinADN);

        // Get all the thongTinADNList
        restThongTinADNMockMvc.perform(get("/api/thong-tin-adns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongTinADN.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].fileDuLieu").value(hasItem(DEFAULT_FILE_DU_LIEU.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getThongTinADN() throws Exception {
        // Initialize the database
        thongTinADNRepository.saveAndFlush(thongTinADN);

        // Get the thongTinADN
        restThongTinADNMockMvc.perform(get("/api/thong-tin-adns/{id}", thongTinADN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thongTinADN.getId().intValue()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.fileDuLieu").value(DEFAULT_FILE_DU_LIEU.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingThongTinADN() throws Exception {
        // Get the thongTinADN
        restThongTinADNMockMvc.perform(get("/api/thong-tin-adns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThongTinADN() throws Exception {
        // Initialize the database
        thongTinADNRepository.saveAndFlush(thongTinADN);

        int databaseSizeBeforeUpdate = thongTinADNRepository.findAll().size();

        // Update the thongTinADN
        ThongTinADN updatedThongTinADN = thongTinADNRepository.findById(thongTinADN.getId()).get();
        // Disconnect from session so that the updates on updatedThongTinADN are not directly saved in db
        em.detach(updatedThongTinADN);
        updatedThongTinADN
            .moTa(UPDATED_MO_TA)
            .fileDuLieu(UPDATED_FILE_DU_LIEU)
            .isDeleted(UPDATED_IS_DELETED);

        restThongTinADNMockMvc.perform(put("/api/thong-tin-adns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedThongTinADN)))
            .andExpect(status().isOk());

        // Validate the ThongTinADN in the database
        List<ThongTinADN> thongTinADNList = thongTinADNRepository.findAll();
        assertThat(thongTinADNList).hasSize(databaseSizeBeforeUpdate);
        ThongTinADN testThongTinADN = thongTinADNList.get(thongTinADNList.size() - 1);
        assertThat(testThongTinADN.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testThongTinADN.getFileDuLieu()).isEqualTo(UPDATED_FILE_DU_LIEU);
        assertThat(testThongTinADN.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the ThongTinADN in Elasticsearch
        verify(mockThongTinADNSearchRepository, times(1)).save(testThongTinADN);
    }

    @Test
    @Transactional
    public void updateNonExistingThongTinADN() throws Exception {
        int databaseSizeBeforeUpdate = thongTinADNRepository.findAll().size();

        // Create the ThongTinADN

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongTinADNMockMvc.perform(put("/api/thong-tin-adns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongTinADN)))
            .andExpect(status().isBadRequest());

        // Validate the ThongTinADN in the database
        List<ThongTinADN> thongTinADNList = thongTinADNRepository.findAll();
        assertThat(thongTinADNList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ThongTinADN in Elasticsearch
        verify(mockThongTinADNSearchRepository, times(0)).save(thongTinADN);
    }

    @Test
    @Transactional
    public void deleteThongTinADN() throws Exception {
        // Initialize the database
        thongTinADNRepository.saveAndFlush(thongTinADN);

        int databaseSizeBeforeDelete = thongTinADNRepository.findAll().size();

        // Get the thongTinADN
        restThongTinADNMockMvc.perform(delete("/api/thong-tin-adns/{id}", thongTinADN.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ThongTinADN> thongTinADNList = thongTinADNRepository.findAll();
        assertThat(thongTinADNList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ThongTinADN in Elasticsearch
        verify(mockThongTinADNSearchRepository, times(1)).deleteById(thongTinADN.getId());
    }

    @Test
    @Transactional
    public void searchThongTinADN() throws Exception {
        // Initialize the database
        thongTinADNRepository.saveAndFlush(thongTinADN);
        when(mockThongTinADNSearchRepository.search(queryStringQuery("id:" + thongTinADN.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(thongTinADN), PageRequest.of(0, 1), 1));
        // Search the thongTinADN
        restThongTinADNMockMvc.perform(get("/api/_search/thong-tin-adns?query=id:" + thongTinADN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongTinADN.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].fileDuLieu").value(hasItem(DEFAULT_FILE_DU_LIEU)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongTinADN.class);
        ThongTinADN thongTinADN1 = new ThongTinADN();
        thongTinADN1.setId(1L);
        ThongTinADN thongTinADN2 = new ThongTinADN();
        thongTinADN2.setId(thongTinADN1.getId());
        assertThat(thongTinADN1).isEqualTo(thongTinADN2);
        thongTinADN2.setId(2L);
        assertThat(thongTinADN1).isNotEqualTo(thongTinADN2);
        thongTinADN1.setId(null);
        assertThat(thongTinADN1).isNotEqualTo(thongTinADN2);
    }
}
