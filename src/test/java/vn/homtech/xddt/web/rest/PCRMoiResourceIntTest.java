package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.PCRMoi;
import vn.homtech.xddt.repository.PCRMoiRepository;
import vn.homtech.xddt.repository.search.PCRMoiSearchRepository;
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
 * Test class for the PCRMoiResource REST controller.
 *
 * @see PCRMoiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class PCRMoiResourceIntTest {

    private static final String DEFAULT_MA_MOI = "AAAAAAAAAA";
    private static final String UPDATED_MA_MOI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_MOI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_MOI = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private PCRMoiRepository pCRMoiRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.PCRMoiSearchRepositoryMockConfiguration
     */
    @Autowired
    private PCRMoiSearchRepository mockPCRMoiSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPCRMoiMockMvc;

    private PCRMoi pCRMoi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PCRMoiResource pCRMoiResource = new PCRMoiResource(pCRMoiRepository, mockPCRMoiSearchRepository);
        this.restPCRMoiMockMvc = MockMvcBuilders.standaloneSetup(pCRMoiResource)
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
    public static PCRMoi createEntity(EntityManager em) {
        PCRMoi pCRMoi = new PCRMoi()
            .maMoi(DEFAULT_MA_MOI)
            .tenMoi(DEFAULT_TEN_MOI)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return pCRMoi;
    }

    @Before
    public void initTest() {
        pCRMoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createPCRMoi() throws Exception {
        int databaseSizeBeforeCreate = pCRMoiRepository.findAll().size();

        // Create the PCRMoi
        restPCRMoiMockMvc.perform(post("/api/pcr-mois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRMoi)))
            .andExpect(status().isCreated());

        // Validate the PCRMoi in the database
        List<PCRMoi> pCRMoiList = pCRMoiRepository.findAll();
        assertThat(pCRMoiList).hasSize(databaseSizeBeforeCreate + 1);
        PCRMoi testPCRMoi = pCRMoiList.get(pCRMoiList.size() - 1);
        assertThat(testPCRMoi.getMaMoi()).isEqualTo(DEFAULT_MA_MOI);
        assertThat(testPCRMoi.getTenMoi()).isEqualTo(DEFAULT_TEN_MOI);
        assertThat(testPCRMoi.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testPCRMoi.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testPCRMoi.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testPCRMoi.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testPCRMoi.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the PCRMoi in Elasticsearch
        verify(mockPCRMoiSearchRepository, times(1)).save(testPCRMoi);
    }

    @Test
    @Transactional
    public void createPCRMoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pCRMoiRepository.findAll().size();

        // Create the PCRMoi with an existing ID
        pCRMoi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPCRMoiMockMvc.perform(post("/api/pcr-mois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRMoi)))
            .andExpect(status().isBadRequest());

        // Validate the PCRMoi in the database
        List<PCRMoi> pCRMoiList = pCRMoiRepository.findAll();
        assertThat(pCRMoiList).hasSize(databaseSizeBeforeCreate);

        // Validate the PCRMoi in Elasticsearch
        verify(mockPCRMoiSearchRepository, times(0)).save(pCRMoi);
    }

    @Test
    @Transactional
    public void getAllPCRMois() throws Exception {
        // Initialize the database
        pCRMoiRepository.saveAndFlush(pCRMoi);

        // Get all the pCRMoiList
        restPCRMoiMockMvc.perform(get("/api/pcr-mois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRMoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maMoi").value(hasItem(DEFAULT_MA_MOI.toString())))
            .andExpect(jsonPath("$.[*].tenMoi").value(hasItem(DEFAULT_TEN_MOI.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getPCRMoi() throws Exception {
        // Initialize the database
        pCRMoiRepository.saveAndFlush(pCRMoi);

        // Get the pCRMoi
        restPCRMoiMockMvc.perform(get("/api/pcr-mois/{id}", pCRMoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pCRMoi.getId().intValue()))
            .andExpect(jsonPath("$.maMoi").value(DEFAULT_MA_MOI.toString()))
            .andExpect(jsonPath("$.tenMoi").value(DEFAULT_TEN_MOI.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPCRMoi() throws Exception {
        // Get the pCRMoi
        restPCRMoiMockMvc.perform(get("/api/pcr-mois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePCRMoi() throws Exception {
        // Initialize the database
        pCRMoiRepository.saveAndFlush(pCRMoi);

        int databaseSizeBeforeUpdate = pCRMoiRepository.findAll().size();

        // Update the pCRMoi
        PCRMoi updatedPCRMoi = pCRMoiRepository.findById(pCRMoi.getId()).get();
        // Disconnect from session so that the updates on updatedPCRMoi are not directly saved in db
        em.detach(updatedPCRMoi);
        updatedPCRMoi
            .maMoi(UPDATED_MA_MOI)
            .tenMoi(UPDATED_TEN_MOI)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restPCRMoiMockMvc.perform(put("/api/pcr-mois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPCRMoi)))
            .andExpect(status().isOk());

        // Validate the PCRMoi in the database
        List<PCRMoi> pCRMoiList = pCRMoiRepository.findAll();
        assertThat(pCRMoiList).hasSize(databaseSizeBeforeUpdate);
        PCRMoi testPCRMoi = pCRMoiList.get(pCRMoiList.size() - 1);
        assertThat(testPCRMoi.getMaMoi()).isEqualTo(UPDATED_MA_MOI);
        assertThat(testPCRMoi.getTenMoi()).isEqualTo(UPDATED_TEN_MOI);
        assertThat(testPCRMoi.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testPCRMoi.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testPCRMoi.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testPCRMoi.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testPCRMoi.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the PCRMoi in Elasticsearch
        verify(mockPCRMoiSearchRepository, times(1)).save(testPCRMoi);
    }

    @Test
    @Transactional
    public void updateNonExistingPCRMoi() throws Exception {
        int databaseSizeBeforeUpdate = pCRMoiRepository.findAll().size();

        // Create the PCRMoi

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPCRMoiMockMvc.perform(put("/api/pcr-mois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRMoi)))
            .andExpect(status().isBadRequest());

        // Validate the PCRMoi in the database
        List<PCRMoi> pCRMoiList = pCRMoiRepository.findAll();
        assertThat(pCRMoiList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PCRMoi in Elasticsearch
        verify(mockPCRMoiSearchRepository, times(0)).save(pCRMoi);
    }

    @Test
    @Transactional
    public void deletePCRMoi() throws Exception {
        // Initialize the database
        pCRMoiRepository.saveAndFlush(pCRMoi);

        int databaseSizeBeforeDelete = pCRMoiRepository.findAll().size();

        // Get the pCRMoi
        restPCRMoiMockMvc.perform(delete("/api/pcr-mois/{id}", pCRMoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PCRMoi> pCRMoiList = pCRMoiRepository.findAll();
        assertThat(pCRMoiList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PCRMoi in Elasticsearch
        verify(mockPCRMoiSearchRepository, times(1)).deleteById(pCRMoi.getId());
    }

    @Test
    @Transactional
    public void searchPCRMoi() throws Exception {
        // Initialize the database
        pCRMoiRepository.saveAndFlush(pCRMoi);
        when(mockPCRMoiSearchRepository.search(queryStringQuery("id:" + pCRMoi.getId())))
            .thenReturn(Collections.singletonList(pCRMoi));
        // Search the pCRMoi
        restPCRMoiMockMvc.perform(get("/api/_search/pcr-mois?query=id:" + pCRMoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRMoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maMoi").value(hasItem(DEFAULT_MA_MOI)))
            .andExpect(jsonPath("$.[*].tenMoi").value(hasItem(DEFAULT_TEN_MOI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PCRMoi.class);
        PCRMoi pCRMoi1 = new PCRMoi();
        pCRMoi1.setId(1L);
        PCRMoi pCRMoi2 = new PCRMoi();
        pCRMoi2.setId(pCRMoi1.getId());
        assertThat(pCRMoi1).isEqualTo(pCRMoi2);
        pCRMoi2.setId(2L);
        assertThat(pCRMoi1).isNotEqualTo(pCRMoi2);
        pCRMoi1.setId(null);
        assertThat(pCRMoi1).isNotEqualTo(pCRMoi2);
    }
}
