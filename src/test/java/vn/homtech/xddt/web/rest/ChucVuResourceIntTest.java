package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.ChucVu;
import vn.homtech.xddt.repository.ChucVuRepository;
import vn.homtech.xddt.repository.search.ChucVuSearchRepository;
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
 * Test class for the ChucVuResource REST controller.
 *
 * @see ChucVuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class ChucVuResourceIntTest {

    private static final String DEFAULT_MA_CHUC_VU = "AAAAAAAAAA";
    private static final String UPDATED_MA_CHUC_VU = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_CHUC_VU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CHUC_VU = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ChucVuRepository chucVuRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.ChucVuSearchRepositoryMockConfiguration
     */
    @Autowired
    private ChucVuSearchRepository mockChucVuSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChucVuMockMvc;

    private ChucVu chucVu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChucVuResource chucVuResource = new ChucVuResource(chucVuRepository, mockChucVuSearchRepository);
        this.restChucVuMockMvc = MockMvcBuilders.standaloneSetup(chucVuResource)
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
    public static ChucVu createEntity(EntityManager em) {
        ChucVu chucVu = new ChucVu()
            .maChucVu(DEFAULT_MA_CHUC_VU)
            .tenChucVu(DEFAULT_TEN_CHUC_VU)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED);
        return chucVu;
    }

    @Before
    public void initTest() {
        chucVu = createEntity(em);
    }

    @Test
    @Transactional
    public void createChucVu() throws Exception {
        int databaseSizeBeforeCreate = chucVuRepository.findAll().size();

        // Create the ChucVu
        restChucVuMockMvc.perform(post("/api/chuc-vus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chucVu)))
            .andExpect(status().isCreated());

        // Validate the ChucVu in the database
        List<ChucVu> chucVuList = chucVuRepository.findAll();
        assertThat(chucVuList).hasSize(databaseSizeBeforeCreate + 1);
        ChucVu testChucVu = chucVuList.get(chucVuList.size() - 1);
        assertThat(testChucVu.getMaChucVu()).isEqualTo(DEFAULT_MA_CHUC_VU);
        assertThat(testChucVu.getTenChucVu()).isEqualTo(DEFAULT_TEN_CHUC_VU);
        assertThat(testChucVu.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testChucVu.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the ChucVu in Elasticsearch
        verify(mockChucVuSearchRepository, times(1)).save(testChucVu);
    }

    @Test
    @Transactional
    public void createChucVuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chucVuRepository.findAll().size();

        // Create the ChucVu with an existing ID
        chucVu.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChucVuMockMvc.perform(post("/api/chuc-vus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chucVu)))
            .andExpect(status().isBadRequest());

        // Validate the ChucVu in the database
        List<ChucVu> chucVuList = chucVuRepository.findAll();
        assertThat(chucVuList).hasSize(databaseSizeBeforeCreate);

        // Validate the ChucVu in Elasticsearch
        verify(mockChucVuSearchRepository, times(0)).save(chucVu);
    }

    @Test
    @Transactional
    public void getAllChucVus() throws Exception {
        // Initialize the database
        chucVuRepository.saveAndFlush(chucVu);

        // Get all the chucVuList
        restChucVuMockMvc.perform(get("/api/chuc-vus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chucVu.getId().intValue())))
            .andExpect(jsonPath("$.[*].maChucVu").value(hasItem(DEFAULT_MA_CHUC_VU.toString())))
            .andExpect(jsonPath("$.[*].tenChucVu").value(hasItem(DEFAULT_TEN_CHUC_VU.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getChucVu() throws Exception {
        // Initialize the database
        chucVuRepository.saveAndFlush(chucVu);

        // Get the chucVu
        restChucVuMockMvc.perform(get("/api/chuc-vus/{id}", chucVu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chucVu.getId().intValue()))
            .andExpect(jsonPath("$.maChucVu").value(DEFAULT_MA_CHUC_VU.toString()))
            .andExpect(jsonPath("$.tenChucVu").value(DEFAULT_TEN_CHUC_VU.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingChucVu() throws Exception {
        // Get the chucVu
        restChucVuMockMvc.perform(get("/api/chuc-vus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChucVu() throws Exception {
        // Initialize the database
        chucVuRepository.saveAndFlush(chucVu);

        int databaseSizeBeforeUpdate = chucVuRepository.findAll().size();

        // Update the chucVu
        ChucVu updatedChucVu = chucVuRepository.findById(chucVu.getId()).get();
        // Disconnect from session so that the updates on updatedChucVu are not directly saved in db
        em.detach(updatedChucVu);
        updatedChucVu
            .maChucVu(UPDATED_MA_CHUC_VU)
            .tenChucVu(UPDATED_TEN_CHUC_VU)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED);

        restChucVuMockMvc.perform(put("/api/chuc-vus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChucVu)))
            .andExpect(status().isOk());

        // Validate the ChucVu in the database
        List<ChucVu> chucVuList = chucVuRepository.findAll();
        assertThat(chucVuList).hasSize(databaseSizeBeforeUpdate);
        ChucVu testChucVu = chucVuList.get(chucVuList.size() - 1);
        assertThat(testChucVu.getMaChucVu()).isEqualTo(UPDATED_MA_CHUC_VU);
        assertThat(testChucVu.getTenChucVu()).isEqualTo(UPDATED_TEN_CHUC_VU);
        assertThat(testChucVu.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testChucVu.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the ChucVu in Elasticsearch
        verify(mockChucVuSearchRepository, times(1)).save(testChucVu);
    }

    @Test
    @Transactional
    public void updateNonExistingChucVu() throws Exception {
        int databaseSizeBeforeUpdate = chucVuRepository.findAll().size();

        // Create the ChucVu

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChucVuMockMvc.perform(put("/api/chuc-vus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chucVu)))
            .andExpect(status().isBadRequest());

        // Validate the ChucVu in the database
        List<ChucVu> chucVuList = chucVuRepository.findAll();
        assertThat(chucVuList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ChucVu in Elasticsearch
        verify(mockChucVuSearchRepository, times(0)).save(chucVu);
    }

    @Test
    @Transactional
    public void deleteChucVu() throws Exception {
        // Initialize the database
        chucVuRepository.saveAndFlush(chucVu);

        int databaseSizeBeforeDelete = chucVuRepository.findAll().size();

        // Get the chucVu
        restChucVuMockMvc.perform(delete("/api/chuc-vus/{id}", chucVu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ChucVu> chucVuList = chucVuRepository.findAll();
        assertThat(chucVuList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ChucVu in Elasticsearch
        verify(mockChucVuSearchRepository, times(1)).deleteById(chucVu.getId());
    }

    @Test
    @Transactional
    public void searchChucVu() throws Exception {
        // Initialize the database
        chucVuRepository.saveAndFlush(chucVu);
        when(mockChucVuSearchRepository.search(queryStringQuery("id:" + chucVu.getId())))
            .thenReturn(Collections.singletonList(chucVu));
        // Search the chucVu
        restChucVuMockMvc.perform(get("/api/_search/chuc-vus?query=id:" + chucVu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chucVu.getId().intValue())))
            .andExpect(jsonPath("$.[*].maChucVu").value(hasItem(DEFAULT_MA_CHUC_VU)))
            .andExpect(jsonPath("$.[*].tenChucVu").value(hasItem(DEFAULT_TEN_CHUC_VU)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChucVu.class);
        ChucVu chucVu1 = new ChucVu();
        chucVu1.setId(1L);
        ChucVu chucVu2 = new ChucVu();
        chucVu2.setId(chucVu1.getId());
        assertThat(chucVu1).isEqualTo(chucVu2);
        chucVu2.setId(2L);
        assertThat(chucVu1).isNotEqualTo(chucVu2);
        chucVu1.setId(null);
        assertThat(chucVu1).isNotEqualTo(chucVu2);
    }
}
