package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.PCRMau;
import vn.homtech.xddt.repository.PCRMauRepository;
import vn.homtech.xddt.repository.search.PCRMauSearchRepository;
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
 * Test class for the PCRMauResource REST controller.
 *
 * @see PCRMauResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class PCRMauResourceIntTest {

    private static final Float DEFAULT_NONG_DO_AGAROS = 1F;
    private static final Float UPDATED_NONG_DO_AGAROS = 2F;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private PCRMauRepository pCRMauRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.PCRMauSearchRepositoryMockConfiguration
     */
    @Autowired
    private PCRMauSearchRepository mockPCRMauSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPCRMauMockMvc;

    private PCRMau pCRMau;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PCRMauResource pCRMauResource = new PCRMauResource(pCRMauRepository, mockPCRMauSearchRepository);
        this.restPCRMauMockMvc = MockMvcBuilders.standaloneSetup(pCRMauResource)
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
    public static PCRMau createEntity(EntityManager em) {
        PCRMau pCRMau = new PCRMau()
            .nongDoAgaros(DEFAULT_NONG_DO_AGAROS)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return pCRMau;
    }

    @Before
    public void initTest() {
        pCRMau = createEntity(em);
    }

    @Test
    @Transactional
    public void createPCRMau() throws Exception {
        int databaseSizeBeforeCreate = pCRMauRepository.findAll().size();

        // Create the PCRMau
        restPCRMauMockMvc.perform(post("/api/pcr-maus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRMau)))
            .andExpect(status().isCreated());

        // Validate the PCRMau in the database
        List<PCRMau> pCRMauList = pCRMauRepository.findAll();
        assertThat(pCRMauList).hasSize(databaseSizeBeforeCreate + 1);
        PCRMau testPCRMau = pCRMauList.get(pCRMauList.size() - 1);
        assertThat(testPCRMau.getNongDoAgaros()).isEqualTo(DEFAULT_NONG_DO_AGAROS);
        assertThat(testPCRMau.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testPCRMau.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testPCRMau.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testPCRMau.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the PCRMau in Elasticsearch
        verify(mockPCRMauSearchRepository, times(1)).save(testPCRMau);
    }

    @Test
    @Transactional
    public void createPCRMauWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pCRMauRepository.findAll().size();

        // Create the PCRMau with an existing ID
        pCRMau.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPCRMauMockMvc.perform(post("/api/pcr-maus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRMau)))
            .andExpect(status().isBadRequest());

        // Validate the PCRMau in the database
        List<PCRMau> pCRMauList = pCRMauRepository.findAll();
        assertThat(pCRMauList).hasSize(databaseSizeBeforeCreate);

        // Validate the PCRMau in Elasticsearch
        verify(mockPCRMauSearchRepository, times(0)).save(pCRMau);
    }

    @Test
    @Transactional
    public void getAllPCRMaus() throws Exception {
        // Initialize the database
        pCRMauRepository.saveAndFlush(pCRMau);

        // Get all the pCRMauList
        restPCRMauMockMvc.perform(get("/api/pcr-maus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRMau.getId().intValue())))
            .andExpect(jsonPath("$.[*].nongDoAgaros").value(hasItem(DEFAULT_NONG_DO_AGAROS.doubleValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getPCRMau() throws Exception {
        // Initialize the database
        pCRMauRepository.saveAndFlush(pCRMau);

        // Get the pCRMau
        restPCRMauMockMvc.perform(get("/api/pcr-maus/{id}", pCRMau.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pCRMau.getId().intValue()))
            .andExpect(jsonPath("$.nongDoAgaros").value(DEFAULT_NONG_DO_AGAROS.doubleValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPCRMau() throws Exception {
        // Get the pCRMau
        restPCRMauMockMvc.perform(get("/api/pcr-maus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePCRMau() throws Exception {
        // Initialize the database
        pCRMauRepository.saveAndFlush(pCRMau);

        int databaseSizeBeforeUpdate = pCRMauRepository.findAll().size();

        // Update the pCRMau
        PCRMau updatedPCRMau = pCRMauRepository.findById(pCRMau.getId()).get();
        // Disconnect from session so that the updates on updatedPCRMau are not directly saved in db
        em.detach(updatedPCRMau);
        updatedPCRMau
            .nongDoAgaros(UPDATED_NONG_DO_AGAROS)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restPCRMauMockMvc.perform(put("/api/pcr-maus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPCRMau)))
            .andExpect(status().isOk());

        // Validate the PCRMau in the database
        List<PCRMau> pCRMauList = pCRMauRepository.findAll();
        assertThat(pCRMauList).hasSize(databaseSizeBeforeUpdate);
        PCRMau testPCRMau = pCRMauList.get(pCRMauList.size() - 1);
        assertThat(testPCRMau.getNongDoAgaros()).isEqualTo(UPDATED_NONG_DO_AGAROS);
        assertThat(testPCRMau.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testPCRMau.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testPCRMau.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testPCRMau.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the PCRMau in Elasticsearch
        verify(mockPCRMauSearchRepository, times(1)).save(testPCRMau);
    }

    @Test
    @Transactional
    public void updateNonExistingPCRMau() throws Exception {
        int databaseSizeBeforeUpdate = pCRMauRepository.findAll().size();

        // Create the PCRMau

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPCRMauMockMvc.perform(put("/api/pcr-maus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCRMau)))
            .andExpect(status().isBadRequest());

        // Validate the PCRMau in the database
        List<PCRMau> pCRMauList = pCRMauRepository.findAll();
        assertThat(pCRMauList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PCRMau in Elasticsearch
        verify(mockPCRMauSearchRepository, times(0)).save(pCRMau);
    }

    @Test
    @Transactional
    public void deletePCRMau() throws Exception {
        // Initialize the database
        pCRMauRepository.saveAndFlush(pCRMau);

        int databaseSizeBeforeDelete = pCRMauRepository.findAll().size();

        // Get the pCRMau
        restPCRMauMockMvc.perform(delete("/api/pcr-maus/{id}", pCRMau.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PCRMau> pCRMauList = pCRMauRepository.findAll();
        assertThat(pCRMauList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PCRMau in Elasticsearch
        verify(mockPCRMauSearchRepository, times(1)).deleteById(pCRMau.getId());
    }

    @Test
    @Transactional
    public void searchPCRMau() throws Exception {
        // Initialize the database
        pCRMauRepository.saveAndFlush(pCRMau);
        when(mockPCRMauSearchRepository.search(queryStringQuery("id:" + pCRMau.getId())))
            .thenReturn(Collections.singletonList(pCRMau));
        // Search the pCRMau
        restPCRMauMockMvc.perform(get("/api/_search/pcr-maus?query=id:" + pCRMau.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCRMau.getId().intValue())))
            .andExpect(jsonPath("$.[*].nongDoAgaros").value(hasItem(DEFAULT_NONG_DO_AGAROS.doubleValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PCRMau.class);
        PCRMau pCRMau1 = new PCRMau();
        pCRMau1.setId(1L);
        PCRMau pCRMau2 = new PCRMau();
        pCRMau2.setId(pCRMau1.getId());
        assertThat(pCRMau1).isEqualTo(pCRMau2);
        pCRMau2.setId(2L);
        assertThat(pCRMau1).isNotEqualTo(pCRMau2);
        pCRMau1.setId(null);
        assertThat(pCRMau1).isNotEqualTo(pCRMau2);
    }
}
