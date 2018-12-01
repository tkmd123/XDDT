package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.TrungTam;
import vn.homtech.xddt.repository.TrungTamRepository;
import vn.homtech.xddt.repository.search.TrungTamSearchRepository;
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
 * Test class for the TrungTamResource REST controller.
 *
 * @see TrungTamResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class TrungTamResourceIntTest {

    private static final String DEFAULT_MA_TRUNG_TAM = "AAAAAAAAAA";
    private static final String UPDATED_MA_TRUNG_TAM = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_TRUNG_TAM = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TRUNG_TAM = "BBBBBBBBBB";

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
    private TrungTamRepository trungTamRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.TrungTamSearchRepositoryMockConfiguration
     */
    @Autowired
    private TrungTamSearchRepository mockTrungTamSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrungTamMockMvc;

    private TrungTam trungTam;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrungTamResource trungTamResource = new TrungTamResource(trungTamRepository, mockTrungTamSearchRepository);
        this.restTrungTamMockMvc = MockMvcBuilders.standaloneSetup(trungTamResource)
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
    public static TrungTam createEntity(EntityManager em) {
        TrungTam trungTam = new TrungTam()
            .maTrungTam(DEFAULT_MA_TRUNG_TAM)
            .tenTrungTam(DEFAULT_TEN_TRUNG_TAM)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return trungTam;
    }

    @Before
    public void initTest() {
        trungTam = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrungTam() throws Exception {
        int databaseSizeBeforeCreate = trungTamRepository.findAll().size();

        // Create the TrungTam
        restTrungTamMockMvc.perform(post("/api/trung-tams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trungTam)))
            .andExpect(status().isCreated());

        // Validate the TrungTam in the database
        List<TrungTam> trungTamList = trungTamRepository.findAll();
        assertThat(trungTamList).hasSize(databaseSizeBeforeCreate + 1);
        TrungTam testTrungTam = trungTamList.get(trungTamList.size() - 1);
        assertThat(testTrungTam.getMaTrungTam()).isEqualTo(DEFAULT_MA_TRUNG_TAM);
        assertThat(testTrungTam.getTenTrungTam()).isEqualTo(DEFAULT_TEN_TRUNG_TAM);
        assertThat(testTrungTam.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testTrungTam.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testTrungTam.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testTrungTam.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testTrungTam.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the TrungTam in Elasticsearch
        verify(mockTrungTamSearchRepository, times(1)).save(testTrungTam);
    }

    @Test
    @Transactional
    public void createTrungTamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trungTamRepository.findAll().size();

        // Create the TrungTam with an existing ID
        trungTam.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrungTamMockMvc.perform(post("/api/trung-tams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trungTam)))
            .andExpect(status().isBadRequest());

        // Validate the TrungTam in the database
        List<TrungTam> trungTamList = trungTamRepository.findAll();
        assertThat(trungTamList).hasSize(databaseSizeBeforeCreate);

        // Validate the TrungTam in Elasticsearch
        verify(mockTrungTamSearchRepository, times(0)).save(trungTam);
    }

    @Test
    @Transactional
    public void getAllTrungTams() throws Exception {
        // Initialize the database
        trungTamRepository.saveAndFlush(trungTam);

        // Get all the trungTamList
        restTrungTamMockMvc.perform(get("/api/trung-tams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trungTam.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTrungTam").value(hasItem(DEFAULT_MA_TRUNG_TAM.toString())))
            .andExpect(jsonPath("$.[*].tenTrungTam").value(hasItem(DEFAULT_TEN_TRUNG_TAM.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getTrungTam() throws Exception {
        // Initialize the database
        trungTamRepository.saveAndFlush(trungTam);

        // Get the trungTam
        restTrungTamMockMvc.perform(get("/api/trung-tams/{id}", trungTam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trungTam.getId().intValue()))
            .andExpect(jsonPath("$.maTrungTam").value(DEFAULT_MA_TRUNG_TAM.toString()))
            .andExpect(jsonPath("$.tenTrungTam").value(DEFAULT_TEN_TRUNG_TAM.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrungTam() throws Exception {
        // Get the trungTam
        restTrungTamMockMvc.perform(get("/api/trung-tams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrungTam() throws Exception {
        // Initialize the database
        trungTamRepository.saveAndFlush(trungTam);

        int databaseSizeBeforeUpdate = trungTamRepository.findAll().size();

        // Update the trungTam
        TrungTam updatedTrungTam = trungTamRepository.findById(trungTam.getId()).get();
        // Disconnect from session so that the updates on updatedTrungTam are not directly saved in db
        em.detach(updatedTrungTam);
        updatedTrungTam
            .maTrungTam(UPDATED_MA_TRUNG_TAM)
            .tenTrungTam(UPDATED_TEN_TRUNG_TAM)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restTrungTamMockMvc.perform(put("/api/trung-tams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTrungTam)))
            .andExpect(status().isOk());

        // Validate the TrungTam in the database
        List<TrungTam> trungTamList = trungTamRepository.findAll();
        assertThat(trungTamList).hasSize(databaseSizeBeforeUpdate);
        TrungTam testTrungTam = trungTamList.get(trungTamList.size() - 1);
        assertThat(testTrungTam.getMaTrungTam()).isEqualTo(UPDATED_MA_TRUNG_TAM);
        assertThat(testTrungTam.getTenTrungTam()).isEqualTo(UPDATED_TEN_TRUNG_TAM);
        assertThat(testTrungTam.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testTrungTam.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testTrungTam.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testTrungTam.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testTrungTam.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the TrungTam in Elasticsearch
        verify(mockTrungTamSearchRepository, times(1)).save(testTrungTam);
    }

    @Test
    @Transactional
    public void updateNonExistingTrungTam() throws Exception {
        int databaseSizeBeforeUpdate = trungTamRepository.findAll().size();

        // Create the TrungTam

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrungTamMockMvc.perform(put("/api/trung-tams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trungTam)))
            .andExpect(status().isBadRequest());

        // Validate the TrungTam in the database
        List<TrungTam> trungTamList = trungTamRepository.findAll();
        assertThat(trungTamList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TrungTam in Elasticsearch
        verify(mockTrungTamSearchRepository, times(0)).save(trungTam);
    }

    @Test
    @Transactional
    public void deleteTrungTam() throws Exception {
        // Initialize the database
        trungTamRepository.saveAndFlush(trungTam);

        int databaseSizeBeforeDelete = trungTamRepository.findAll().size();

        // Get the trungTam
        restTrungTamMockMvc.perform(delete("/api/trung-tams/{id}", trungTam.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TrungTam> trungTamList = trungTamRepository.findAll();
        assertThat(trungTamList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TrungTam in Elasticsearch
        verify(mockTrungTamSearchRepository, times(1)).deleteById(trungTam.getId());
    }

    @Test
    @Transactional
    public void searchTrungTam() throws Exception {
        // Initialize the database
        trungTamRepository.saveAndFlush(trungTam);
        when(mockTrungTamSearchRepository.search(queryStringQuery("id:" + trungTam.getId())))
            .thenReturn(Collections.singletonList(trungTam));
        // Search the trungTam
        restTrungTamMockMvc.perform(get("/api/_search/trung-tams?query=id:" + trungTam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trungTam.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTrungTam").value(hasItem(DEFAULT_MA_TRUNG_TAM)))
            .andExpect(jsonPath("$.[*].tenTrungTam").value(hasItem(DEFAULT_TEN_TRUNG_TAM)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrungTam.class);
        TrungTam trungTam1 = new TrungTam();
        trungTam1.setId(1L);
        TrungTam trungTam2 = new TrungTam();
        trungTam2.setId(trungTam1.getId());
        assertThat(trungTam1).isEqualTo(trungTam2);
        trungTam2.setId(2L);
        assertThat(trungTam1).isNotEqualTo(trungTam2);
        trungTam1.setId(null);
        assertThat(trungTam1).isNotEqualTo(trungTam2);
    }
}
