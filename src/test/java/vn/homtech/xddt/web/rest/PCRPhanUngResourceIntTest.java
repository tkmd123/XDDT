package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.PCRPhanUng;
import vn.homtech.xddt.repository.PCRPhanUngRepository;
import vn.homtech.xddt.repository.search.PCRPhanUngSearchRepository;
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
 * Test class for the PCRPhanUngResource REST controller.
 *
 * @see PCRPhanUngResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class PCRPhanUngResourceIntTest {

    private static final String DEFAULT_CHU_KY_PHAN_UNG = "AAAAAAAAAA";
    private static final String UPDATED_CHU_KY_PHAN_UNG = "BBBBBBBBBB";

    private static final Float DEFAULT_DUNG_TICH = 1F;
    private static final Float UPDATED_DUNG_TICH = 2F;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private PCRPhanUngRepository pCRPhanUngRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.PCRPhanUngSearchRepositoryMockConfiguration
     */
    @Autowired
    private PCRPhanUngSearchRepository mockPCRPhanUngSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPCRPhanUngMockMvc;

    private PCRPhanUng pCRPhanUng;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PCRPhanUngResource pCRPhanUngResource = new PCRPhanUngResource(pCRPhanUngRepository, mockPCRPhanUngSearchRepository);
        this.restPCRPhanUngMockMvc = MockMvcBuilders.standaloneSetup(pCRPhanUngResource)
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
    public static PCRPhanUng createEntity(EntityManager em) {
        PCRPhanUng pCRPhanUng = new PCRPhanUng()
            .chuKyPhanUng(DEFAULT_CHU_KY_PHAN_UNG)
            .dungTich(DEFAULT_DUNG_TICH)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return pCRPhanUng;
    }

    @Before
    public void initTest() {
        pCRPhanUng = createEntity(em);
    }

    @Test
    @Transactional
    public void createPCRPhanUng() throws Exception {
        int databaseSizeBeforeCreate = pCRPhanUngRepository.findAll().size();

        // Create the PCRPhanUng
        restPCRPhanUngMockMvc.perform(post("/api/pcr-phan-ungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRPhanUng)))
            .andExpect(status().isCreated());

        // Validate the PCRPhanUng in the database
        List<PCRPhanUng> pCRPhanUngList = pCRPhanUngRepository.findAll();
        assertThat(pCRPhanUngList).hasSize(databaseSizeBeforeCreate + 1);
        PCRPhanUng testPCRPhanUng = pCRPhanUngList.get(pCRPhanUngList.size() - 1);
        assertThat(testPCRPhanUng.getChuKyPhanUng()).isEqualTo(DEFAULT_CHU_KY_PHAN_UNG);
        assertThat(testPCRPhanUng.getDungTich()).isEqualTo(DEFAULT_DUNG_TICH);
        assertThat(testPCRPhanUng.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testPCRPhanUng.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testPCRPhanUng.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testPCRPhanUng.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the PCRPhanUng in Elasticsearch
        verify(mockPCRPhanUngSearchRepository, times(1)).save(testPCRPhanUng);
    }

    @Test
    @Transactional
    public void createPCRPhanUngWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pCRPhanUngRepository.findAll().size();

        // Create the PCRPhanUng with an existing ID
        pCRPhanUng.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPCRPhanUngMockMvc.perform(post("/api/pcr-phan-ungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRPhanUng)))
            .andExpect(status().isBadRequest());

        // Validate the PCRPhanUng in the database
        List<PCRPhanUng> pCRPhanUngList = pCRPhanUngRepository.findAll();
        assertThat(pCRPhanUngList).hasSize(databaseSizeBeforeCreate);

        // Validate the PCRPhanUng in Elasticsearch
        verify(mockPCRPhanUngSearchRepository, times(0)).save(pCRPhanUng);
    }

    @Test
    @Transactional
    public void getAllPCRPhanUngs() throws Exception {
        // Initialize the database
        pCRPhanUngRepository.saveAndFlush(pCRPhanUng);

        // Get all the pCRPhanUngList
        restPCRPhanUngMockMvc.perform(get("/api/pcr-phan-ungs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRPhanUng.getId().intValue())))
            .andExpect(jsonPath("$.[*].chuKyPhanUng").value(hasItem(DEFAULT_CHU_KY_PHAN_UNG.toString())))
            .andExpect(jsonPath("$.[*].dungTich").value(hasItem(DEFAULT_DUNG_TICH.doubleValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getPCRPhanUng() throws Exception {
        // Initialize the database
        pCRPhanUngRepository.saveAndFlush(pCRPhanUng);

        // Get the pCRPhanUng
        restPCRPhanUngMockMvc.perform(get("/api/pcr-phan-ungs/{id}", pCRPhanUng.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pCRPhanUng.getId().intValue()))
            .andExpect(jsonPath("$.chuKyPhanUng").value(DEFAULT_CHU_KY_PHAN_UNG.toString()))
            .andExpect(jsonPath("$.dungTich").value(DEFAULT_DUNG_TICH.doubleValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPCRPhanUng() throws Exception {
        // Get the pCRPhanUng
        restPCRPhanUngMockMvc.perform(get("/api/pcr-phan-ungs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePCRPhanUng() throws Exception {
        // Initialize the database
        pCRPhanUngRepository.saveAndFlush(pCRPhanUng);

        int databaseSizeBeforeUpdate = pCRPhanUngRepository.findAll().size();

        // Update the pCRPhanUng
        PCRPhanUng updatedPCRPhanUng = pCRPhanUngRepository.findById(pCRPhanUng.getId()).get();
        // Disconnect from session so that the updates on updatedPCRPhanUng are not directly saved in db
        em.detach(updatedPCRPhanUng);
        updatedPCRPhanUng
            .chuKyPhanUng(UPDATED_CHU_KY_PHAN_UNG)
            .dungTich(UPDATED_DUNG_TICH)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restPCRPhanUngMockMvc.perform(put("/api/pcr-phan-ungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPCRPhanUng)))
            .andExpect(status().isOk());

        // Validate the PCRPhanUng in the database
        List<PCRPhanUng> pCRPhanUngList = pCRPhanUngRepository.findAll();
        assertThat(pCRPhanUngList).hasSize(databaseSizeBeforeUpdate);
        PCRPhanUng testPCRPhanUng = pCRPhanUngList.get(pCRPhanUngList.size() - 1);
        assertThat(testPCRPhanUng.getChuKyPhanUng()).isEqualTo(UPDATED_CHU_KY_PHAN_UNG);
        assertThat(testPCRPhanUng.getDungTich()).isEqualTo(UPDATED_DUNG_TICH);
        assertThat(testPCRPhanUng.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testPCRPhanUng.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testPCRPhanUng.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testPCRPhanUng.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the PCRPhanUng in Elasticsearch
        verify(mockPCRPhanUngSearchRepository, times(1)).save(testPCRPhanUng);
    }

    @Test
    @Transactional
    public void updateNonExistingPCRPhanUng() throws Exception {
        int databaseSizeBeforeUpdate = pCRPhanUngRepository.findAll().size();

        // Create the PCRPhanUng

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPCRPhanUngMockMvc.perform(put("/api/pcr-phan-ungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRPhanUng)))
            .andExpect(status().isBadRequest());

        // Validate the PCRPhanUng in the database
        List<PCRPhanUng> pCRPhanUngList = pCRPhanUngRepository.findAll();
        assertThat(pCRPhanUngList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PCRPhanUng in Elasticsearch
        verify(mockPCRPhanUngSearchRepository, times(0)).save(pCRPhanUng);
    }

    @Test
    @Transactional
    public void deletePCRPhanUng() throws Exception {
        // Initialize the database
        pCRPhanUngRepository.saveAndFlush(pCRPhanUng);

        int databaseSizeBeforeDelete = pCRPhanUngRepository.findAll().size();

        // Get the pCRPhanUng
        restPCRPhanUngMockMvc.perform(delete("/api/pcr-phan-ungs/{id}", pCRPhanUng.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PCRPhanUng> pCRPhanUngList = pCRPhanUngRepository.findAll();
        assertThat(pCRPhanUngList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PCRPhanUng in Elasticsearch
        verify(mockPCRPhanUngSearchRepository, times(1)).deleteById(pCRPhanUng.getId());
    }

    @Test
    @Transactional
    public void searchPCRPhanUng() throws Exception {
        // Initialize the database
        pCRPhanUngRepository.saveAndFlush(pCRPhanUng);
        when(mockPCRPhanUngSearchRepository.search(queryStringQuery("id:" + pCRPhanUng.getId())))
            .thenReturn(Collections.singletonList(pCRPhanUng));
        // Search the pCRPhanUng
        restPCRPhanUngMockMvc.perform(get("/api/_search/pcr-phan-ungs?query=id:" + pCRPhanUng.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRPhanUng.getId().intValue())))
            .andExpect(jsonPath("$.[*].chuKyPhanUng").value(hasItem(DEFAULT_CHU_KY_PHAN_UNG)))
            .andExpect(jsonPath("$.[*].dungTich").value(hasItem(DEFAULT_DUNG_TICH.doubleValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PCRPhanUng.class);
        PCRPhanUng pCRPhanUng1 = new PCRPhanUng();
        pCRPhanUng1.setId(1L);
        PCRPhanUng pCRPhanUng2 = new PCRPhanUng();
        pCRPhanUng2.setId(pCRPhanUng1.getId());
        assertThat(pCRPhanUng1).isEqualTo(pCRPhanUng2);
        pCRPhanUng2.setId(2L);
        assertThat(pCRPhanUng1).isNotEqualTo(pCRPhanUng2);
        pCRPhanUng1.setId(null);
        assertThat(pCRPhanUng1).isNotEqualTo(pCRPhanUng2);
    }
}
