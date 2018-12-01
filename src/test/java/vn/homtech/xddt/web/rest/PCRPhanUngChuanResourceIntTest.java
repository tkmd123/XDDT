package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.PCRPhanUngChuan;
import vn.homtech.xddt.repository.PCRPhanUngChuanRepository;
import vn.homtech.xddt.repository.search.PCRPhanUngChuanSearchRepository;
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
 * Test class for the PCRPhanUngChuanResource REST controller.
 *
 * @see PCRPhanUngChuanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class PCRPhanUngChuanResourceIntTest {

    private static final String DEFAULT_CHU_KY_PHAN_UNG = "AAAAAAAAAA";
    private static final String UPDATED_CHU_KY_PHAN_UNG = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private PCRPhanUngChuanRepository pCRPhanUngChuanRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.PCRPhanUngChuanSearchRepositoryMockConfiguration
     */
    @Autowired
    private PCRPhanUngChuanSearchRepository mockPCRPhanUngChuanSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPCRPhanUngChuanMockMvc;

    private PCRPhanUngChuan pCRPhanUngChuan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PCRPhanUngChuanResource pCRPhanUngChuanResource = new PCRPhanUngChuanResource(pCRPhanUngChuanRepository, mockPCRPhanUngChuanSearchRepository);
        this.restPCRPhanUngChuanMockMvc = MockMvcBuilders.standaloneSetup(pCRPhanUngChuanResource)
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
    public static PCRPhanUngChuan createEntity(EntityManager em) {
        PCRPhanUngChuan pCRPhanUngChuan = new PCRPhanUngChuan()
            .chuKyPhanUng(DEFAULT_CHU_KY_PHAN_UNG)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return pCRPhanUngChuan;
    }

    @Before
    public void initTest() {
        pCRPhanUngChuan = createEntity(em);
    }

    @Test
    @Transactional
    public void createPCRPhanUngChuan() throws Exception {
        int databaseSizeBeforeCreate = pCRPhanUngChuanRepository.findAll().size();

        // Create the PCRPhanUngChuan
        restPCRPhanUngChuanMockMvc.perform(post("/api/pcr-phan-ung-chuans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRPhanUngChuan)))
            .andExpect(status().isCreated());

        // Validate the PCRPhanUngChuan in the database
        List<PCRPhanUngChuan> pCRPhanUngChuanList = pCRPhanUngChuanRepository.findAll();
        assertThat(pCRPhanUngChuanList).hasSize(databaseSizeBeforeCreate + 1);
        PCRPhanUngChuan testPCRPhanUngChuan = pCRPhanUngChuanList.get(pCRPhanUngChuanList.size() - 1);
        assertThat(testPCRPhanUngChuan.getChuKyPhanUng()).isEqualTo(DEFAULT_CHU_KY_PHAN_UNG);
        assertThat(testPCRPhanUngChuan.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testPCRPhanUngChuan.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testPCRPhanUngChuan.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testPCRPhanUngChuan.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the PCRPhanUngChuan in Elasticsearch
        verify(mockPCRPhanUngChuanSearchRepository, times(1)).save(testPCRPhanUngChuan);
    }

    @Test
    @Transactional
    public void createPCRPhanUngChuanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pCRPhanUngChuanRepository.findAll().size();

        // Create the PCRPhanUngChuan with an existing ID
        pCRPhanUngChuan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPCRPhanUngChuanMockMvc.perform(post("/api/pcr-phan-ung-chuans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRPhanUngChuan)))
            .andExpect(status().isBadRequest());

        // Validate the PCRPhanUngChuan in the database
        List<PCRPhanUngChuan> pCRPhanUngChuanList = pCRPhanUngChuanRepository.findAll();
        assertThat(pCRPhanUngChuanList).hasSize(databaseSizeBeforeCreate);

        // Validate the PCRPhanUngChuan in Elasticsearch
        verify(mockPCRPhanUngChuanSearchRepository, times(0)).save(pCRPhanUngChuan);
    }

    @Test
    @Transactional
    public void getAllPCRPhanUngChuans() throws Exception {
        // Initialize the database
        pCRPhanUngChuanRepository.saveAndFlush(pCRPhanUngChuan);

        // Get all the pCRPhanUngChuanList
        restPCRPhanUngChuanMockMvc.perform(get("/api/pcr-phan-ung-chuans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRPhanUngChuan.getId().intValue())))
            .andExpect(jsonPath("$.[*].chuKyPhanUng").value(hasItem(DEFAULT_CHU_KY_PHAN_UNG.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getPCRPhanUngChuan() throws Exception {
        // Initialize the database
        pCRPhanUngChuanRepository.saveAndFlush(pCRPhanUngChuan);

        // Get the pCRPhanUngChuan
        restPCRPhanUngChuanMockMvc.perform(get("/api/pcr-phan-ung-chuans/{id}", pCRPhanUngChuan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pCRPhanUngChuan.getId().intValue()))
            .andExpect(jsonPath("$.chuKyPhanUng").value(DEFAULT_CHU_KY_PHAN_UNG.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPCRPhanUngChuan() throws Exception {
        // Get the pCRPhanUngChuan
        restPCRPhanUngChuanMockMvc.perform(get("/api/pcr-phan-ung-chuans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePCRPhanUngChuan() throws Exception {
        // Initialize the database
        pCRPhanUngChuanRepository.saveAndFlush(pCRPhanUngChuan);

        int databaseSizeBeforeUpdate = pCRPhanUngChuanRepository.findAll().size();

        // Update the pCRPhanUngChuan
        PCRPhanUngChuan updatedPCRPhanUngChuan = pCRPhanUngChuanRepository.findById(pCRPhanUngChuan.getId()).get();
        // Disconnect from session so that the updates on updatedPCRPhanUngChuan are not directly saved in db
        em.detach(updatedPCRPhanUngChuan);
        updatedPCRPhanUngChuan
            .chuKyPhanUng(UPDATED_CHU_KY_PHAN_UNG)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restPCRPhanUngChuanMockMvc.perform(put("/api/pcr-phan-ung-chuans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPCRPhanUngChuan)))
            .andExpect(status().isOk());

        // Validate the PCRPhanUngChuan in the database
        List<PCRPhanUngChuan> pCRPhanUngChuanList = pCRPhanUngChuanRepository.findAll();
        assertThat(pCRPhanUngChuanList).hasSize(databaseSizeBeforeUpdate);
        PCRPhanUngChuan testPCRPhanUngChuan = pCRPhanUngChuanList.get(pCRPhanUngChuanList.size() - 1);
        assertThat(testPCRPhanUngChuan.getChuKyPhanUng()).isEqualTo(UPDATED_CHU_KY_PHAN_UNG);
        assertThat(testPCRPhanUngChuan.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testPCRPhanUngChuan.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testPCRPhanUngChuan.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testPCRPhanUngChuan.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the PCRPhanUngChuan in Elasticsearch
        verify(mockPCRPhanUngChuanSearchRepository, times(1)).save(testPCRPhanUngChuan);
    }

    @Test
    @Transactional
    public void updateNonExistingPCRPhanUngChuan() throws Exception {
        int databaseSizeBeforeUpdate = pCRPhanUngChuanRepository.findAll().size();

        // Create the PCRPhanUngChuan

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPCRPhanUngChuanMockMvc.perform(put("/api/pcr-phan-ung-chuans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRPhanUngChuan)))
            .andExpect(status().isBadRequest());

        // Validate the PCRPhanUngChuan in the database
        List<PCRPhanUngChuan> pCRPhanUngChuanList = pCRPhanUngChuanRepository.findAll();
        assertThat(pCRPhanUngChuanList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PCRPhanUngChuan in Elasticsearch
        verify(mockPCRPhanUngChuanSearchRepository, times(0)).save(pCRPhanUngChuan);
    }

    @Test
    @Transactional
    public void deletePCRPhanUngChuan() throws Exception {
        // Initialize the database
        pCRPhanUngChuanRepository.saveAndFlush(pCRPhanUngChuan);

        int databaseSizeBeforeDelete = pCRPhanUngChuanRepository.findAll().size();

        // Get the pCRPhanUngChuan
        restPCRPhanUngChuanMockMvc.perform(delete("/api/pcr-phan-ung-chuans/{id}", pCRPhanUngChuan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PCRPhanUngChuan> pCRPhanUngChuanList = pCRPhanUngChuanRepository.findAll();
        assertThat(pCRPhanUngChuanList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PCRPhanUngChuan in Elasticsearch
        verify(mockPCRPhanUngChuanSearchRepository, times(1)).deleteById(pCRPhanUngChuan.getId());
    }

    @Test
    @Transactional
    public void searchPCRPhanUngChuan() throws Exception {
        // Initialize the database
        pCRPhanUngChuanRepository.saveAndFlush(pCRPhanUngChuan);
        when(mockPCRPhanUngChuanSearchRepository.search(queryStringQuery("id:" + pCRPhanUngChuan.getId())))
            .thenReturn(Collections.singletonList(pCRPhanUngChuan));
        // Search the pCRPhanUngChuan
        restPCRPhanUngChuanMockMvc.perform(get("/api/_search/pcr-phan-ung-chuans?query=id:" + pCRPhanUngChuan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRPhanUngChuan.getId().intValue())))
            .andExpect(jsonPath("$.[*].chuKyPhanUng").value(hasItem(DEFAULT_CHU_KY_PHAN_UNG)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PCRPhanUngChuan.class);
        PCRPhanUngChuan pCRPhanUngChuan1 = new PCRPhanUngChuan();
        pCRPhanUngChuan1.setId(1L);
        PCRPhanUngChuan pCRPhanUngChuan2 = new PCRPhanUngChuan();
        pCRPhanUngChuan2.setId(pCRPhanUngChuan1.getId());
        assertThat(pCRPhanUngChuan1).isEqualTo(pCRPhanUngChuan2);
        pCRPhanUngChuan2.setId(2L);
        assertThat(pCRPhanUngChuan1).isNotEqualTo(pCRPhanUngChuan2);
        pCRPhanUngChuan1.setId(null);
        assertThat(pCRPhanUngChuan1).isNotEqualTo(pCRPhanUngChuan2);
    }
}
