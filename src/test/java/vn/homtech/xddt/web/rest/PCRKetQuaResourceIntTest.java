package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.PCRKetQua;
import vn.homtech.xddt.repository.PCRKetQuaRepository;
import vn.homtech.xddt.repository.search.PCRKetQuaSearchRepository;
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
 * Test class for the PCRKetQuaResource REST controller.
 *
 * @see PCRKetQuaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class PCRKetQuaResourceIntTest {

    private static final String DEFAULT_MA_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_MA_KET_QUA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KET_QUA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private PCRKetQuaRepository pCRKetQuaRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.PCRKetQuaSearchRepositoryMockConfiguration
     */
    @Autowired
    private PCRKetQuaSearchRepository mockPCRKetQuaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPCRKetQuaMockMvc;

    private PCRKetQua pCRKetQua;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PCRKetQuaResource pCRKetQuaResource = new PCRKetQuaResource(pCRKetQuaRepository, mockPCRKetQuaSearchRepository);
        this.restPCRKetQuaMockMvc = MockMvcBuilders.standaloneSetup(pCRKetQuaResource)
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
    public static PCRKetQua createEntity(EntityManager em) {
        PCRKetQua pCRKetQua = new PCRKetQua()
            .maKetQua(DEFAULT_MA_KET_QUA)
            .tenKetQua(DEFAULT_TEN_KET_QUA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return pCRKetQua;
    }

    @Before
    public void initTest() {
        pCRKetQua = createEntity(em);
    }

    @Test
    @Transactional
    public void createPCRKetQua() throws Exception {
        int databaseSizeBeforeCreate = pCRKetQuaRepository.findAll().size();

        // Create the PCRKetQua
        restPCRKetQuaMockMvc.perform(post("/api/pcr-ket-quas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRKetQua)))
            .andExpect(status().isCreated());

        // Validate the PCRKetQua in the database
        List<PCRKetQua> pCRKetQuaList = pCRKetQuaRepository.findAll();
        assertThat(pCRKetQuaList).hasSize(databaseSizeBeforeCreate + 1);
        PCRKetQua testPCRKetQua = pCRKetQuaList.get(pCRKetQuaList.size() - 1);
        assertThat(testPCRKetQua.getMaKetQua()).isEqualTo(DEFAULT_MA_KET_QUA);
        assertThat(testPCRKetQua.getTenKetQua()).isEqualTo(DEFAULT_TEN_KET_QUA);
        assertThat(testPCRKetQua.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testPCRKetQua.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testPCRKetQua.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testPCRKetQua.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the PCRKetQua in Elasticsearch
        verify(mockPCRKetQuaSearchRepository, times(1)).save(testPCRKetQua);
    }

    @Test
    @Transactional
    public void createPCRKetQuaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pCRKetQuaRepository.findAll().size();

        // Create the PCRKetQua with an existing ID
        pCRKetQua.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPCRKetQuaMockMvc.perform(post("/api/pcr-ket-quas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRKetQua)))
            .andExpect(status().isBadRequest());

        // Validate the PCRKetQua in the database
        List<PCRKetQua> pCRKetQuaList = pCRKetQuaRepository.findAll();
        assertThat(pCRKetQuaList).hasSize(databaseSizeBeforeCreate);

        // Validate the PCRKetQua in Elasticsearch
        verify(mockPCRKetQuaSearchRepository, times(0)).save(pCRKetQua);
    }

    @Test
    @Transactional
    public void getAllPCRKetQuas() throws Exception {
        // Initialize the database
        pCRKetQuaRepository.saveAndFlush(pCRKetQua);

        // Get all the pCRKetQuaList
        restPCRKetQuaMockMvc.perform(get("/api/pcr-ket-quas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRKetQua.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKetQua").value(hasItem(DEFAULT_MA_KET_QUA.toString())))
            .andExpect(jsonPath("$.[*].tenKetQua").value(hasItem(DEFAULT_TEN_KET_QUA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getPCRKetQua() throws Exception {
        // Initialize the database
        pCRKetQuaRepository.saveAndFlush(pCRKetQua);

        // Get the pCRKetQua
        restPCRKetQuaMockMvc.perform(get("/api/pcr-ket-quas/{id}", pCRKetQua.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pCRKetQua.getId().intValue()))
            .andExpect(jsonPath("$.maKetQua").value(DEFAULT_MA_KET_QUA.toString()))
            .andExpect(jsonPath("$.tenKetQua").value(DEFAULT_TEN_KET_QUA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPCRKetQua() throws Exception {
        // Get the pCRKetQua
        restPCRKetQuaMockMvc.perform(get("/api/pcr-ket-quas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePCRKetQua() throws Exception {
        // Initialize the database
        pCRKetQuaRepository.saveAndFlush(pCRKetQua);

        int databaseSizeBeforeUpdate = pCRKetQuaRepository.findAll().size();

        // Update the pCRKetQua
        PCRKetQua updatedPCRKetQua = pCRKetQuaRepository.findById(pCRKetQua.getId()).get();
        // Disconnect from session so that the updates on updatedPCRKetQua are not directly saved in db
        em.detach(updatedPCRKetQua);
        updatedPCRKetQua
            .maKetQua(UPDATED_MA_KET_QUA)
            .tenKetQua(UPDATED_TEN_KET_QUA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restPCRKetQuaMockMvc.perform(put("/api/pcr-ket-quas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPCRKetQua)))
            .andExpect(status().isOk());

        // Validate the PCRKetQua in the database
        List<PCRKetQua> pCRKetQuaList = pCRKetQuaRepository.findAll();
        assertThat(pCRKetQuaList).hasSize(databaseSizeBeforeUpdate);
        PCRKetQua testPCRKetQua = pCRKetQuaList.get(pCRKetQuaList.size() - 1);
        assertThat(testPCRKetQua.getMaKetQua()).isEqualTo(UPDATED_MA_KET_QUA);
        assertThat(testPCRKetQua.getTenKetQua()).isEqualTo(UPDATED_TEN_KET_QUA);
        assertThat(testPCRKetQua.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testPCRKetQua.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testPCRKetQua.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testPCRKetQua.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the PCRKetQua in Elasticsearch
        verify(mockPCRKetQuaSearchRepository, times(1)).save(testPCRKetQua);
    }

    @Test
    @Transactional
    public void updateNonExistingPCRKetQua() throws Exception {
        int databaseSizeBeforeUpdate = pCRKetQuaRepository.findAll().size();

        // Create the PCRKetQua

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPCRKetQuaMockMvc.perform(put("/api/pcr-ket-quas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRKetQua)))
            .andExpect(status().isBadRequest());

        // Validate the PCRKetQua in the database
        List<PCRKetQua> pCRKetQuaList = pCRKetQuaRepository.findAll();
        assertThat(pCRKetQuaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PCRKetQua in Elasticsearch
        verify(mockPCRKetQuaSearchRepository, times(0)).save(pCRKetQua);
    }

    @Test
    @Transactional
    public void deletePCRKetQua() throws Exception {
        // Initialize the database
        pCRKetQuaRepository.saveAndFlush(pCRKetQua);

        int databaseSizeBeforeDelete = pCRKetQuaRepository.findAll().size();

        // Get the pCRKetQua
        restPCRKetQuaMockMvc.perform(delete("/api/pcr-ket-quas/{id}", pCRKetQua.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PCRKetQua> pCRKetQuaList = pCRKetQuaRepository.findAll();
        assertThat(pCRKetQuaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PCRKetQua in Elasticsearch
        verify(mockPCRKetQuaSearchRepository, times(1)).deleteById(pCRKetQua.getId());
    }

    @Test
    @Transactional
    public void searchPCRKetQua() throws Exception {
        // Initialize the database
        pCRKetQuaRepository.saveAndFlush(pCRKetQua);
        when(mockPCRKetQuaSearchRepository.search(queryStringQuery("id:" + pCRKetQua.getId())))
            .thenReturn(Collections.singletonList(pCRKetQua));
        // Search the pCRKetQua
        restPCRKetQuaMockMvc.perform(get("/api/_search/pcr-ket-quas?query=id:" + pCRKetQua.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRKetQua.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKetQua").value(hasItem(DEFAULT_MA_KET_QUA)))
            .andExpect(jsonPath("$.[*].tenKetQua").value(hasItem(DEFAULT_TEN_KET_QUA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PCRKetQua.class);
        PCRKetQua pCRKetQua1 = new PCRKetQua();
        pCRKetQua1.setId(1L);
        PCRKetQua pCRKetQua2 = new PCRKetQua();
        pCRKetQua2.setId(pCRKetQua1.getId());
        assertThat(pCRKetQua1).isEqualTo(pCRKetQua2);
        pCRKetQua2.setId(2L);
        assertThat(pCRKetQua1).isNotEqualTo(pCRKetQua2);
        pCRKetQua1.setId(null);
        assertThat(pCRKetQua1).isNotEqualTo(pCRKetQua2);
    }
}
