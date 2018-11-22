package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.TinhThanh;
import vn.homtech.xddt.repository.TinhThanhRepository;
import vn.homtech.xddt.repository.search.TinhThanhSearchRepository;
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
 * Test class for the TinhThanhResource REST controller.
 *
 * @see TinhThanhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class TinhThanhResourceIntTest {

    private static final String DEFAULT_MA_TINH = "AAAAAAAAAA";
    private static final String UPDATED_MA_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_TINH = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    @Autowired
    private TinhThanhRepository tinhThanhRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.TinhThanhSearchRepositoryMockConfiguration
     */
    @Autowired
    private TinhThanhSearchRepository mockTinhThanhSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTinhThanhMockMvc;

    private TinhThanh tinhThanh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TinhThanhResource tinhThanhResource = new TinhThanhResource(tinhThanhRepository, mockTinhThanhSearchRepository);
        this.restTinhThanhMockMvc = MockMvcBuilders.standaloneSetup(tinhThanhResource)
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
    public static TinhThanh createEntity(EntityManager em) {
        TinhThanh tinhThanh = new TinhThanh()
            .maTinh(DEFAULT_MA_TINH)
            .tenTinh(DEFAULT_TEN_TINH)
            .moTa(DEFAULT_MO_TA);
        return tinhThanh;
    }

    @Before
    public void initTest() {
        tinhThanh = createEntity(em);
    }

    @Test
    @Transactional
    public void createTinhThanh() throws Exception {
        int databaseSizeBeforeCreate = tinhThanhRepository.findAll().size();

        // Create the TinhThanh
        restTinhThanhMockMvc.perform(post("/api/tinh-thanhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhThanh)))
            .andExpect(status().isCreated());

        // Validate the TinhThanh in the database
        List<TinhThanh> tinhThanhList = tinhThanhRepository.findAll();
        assertThat(tinhThanhList).hasSize(databaseSizeBeforeCreate + 1);
        TinhThanh testTinhThanh = tinhThanhList.get(tinhThanhList.size() - 1);
        assertThat(testTinhThanh.getMaTinh()).isEqualTo(DEFAULT_MA_TINH);
        assertThat(testTinhThanh.getTenTinh()).isEqualTo(DEFAULT_TEN_TINH);
        assertThat(testTinhThanh.getMoTa()).isEqualTo(DEFAULT_MO_TA);

        // Validate the TinhThanh in Elasticsearch
        verify(mockTinhThanhSearchRepository, times(1)).save(testTinhThanh);
    }

    @Test
    @Transactional
    public void createTinhThanhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tinhThanhRepository.findAll().size();

        // Create the TinhThanh with an existing ID
        tinhThanh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTinhThanhMockMvc.perform(post("/api/tinh-thanhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhThanh)))
            .andExpect(status().isBadRequest());

        // Validate the TinhThanh in the database
        List<TinhThanh> tinhThanhList = tinhThanhRepository.findAll();
        assertThat(tinhThanhList).hasSize(databaseSizeBeforeCreate);

        // Validate the TinhThanh in Elasticsearch
        verify(mockTinhThanhSearchRepository, times(0)).save(tinhThanh);
    }

    @Test
    @Transactional
    public void getAllTinhThanhs() throws Exception {
        // Initialize the database
        tinhThanhRepository.saveAndFlush(tinhThanh);

        // Get all the tinhThanhList
        restTinhThanhMockMvc.perform(get("/api/tinh-thanhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinhThanh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH.toString())))
            .andExpect(jsonPath("$.[*].tenTinh").value(hasItem(DEFAULT_TEN_TINH.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())));
    }
    
    @Test
    @Transactional
    public void getTinhThanh() throws Exception {
        // Initialize the database
        tinhThanhRepository.saveAndFlush(tinhThanh);

        // Get the tinhThanh
        restTinhThanhMockMvc.perform(get("/api/tinh-thanhs/{id}", tinhThanh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tinhThanh.getId().intValue()))
            .andExpect(jsonPath("$.maTinh").value(DEFAULT_MA_TINH.toString()))
            .andExpect(jsonPath("$.tenTinh").value(DEFAULT_TEN_TINH.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTinhThanh() throws Exception {
        // Get the tinhThanh
        restTinhThanhMockMvc.perform(get("/api/tinh-thanhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTinhThanh() throws Exception {
        // Initialize the database
        tinhThanhRepository.saveAndFlush(tinhThanh);

        int databaseSizeBeforeUpdate = tinhThanhRepository.findAll().size();

        // Update the tinhThanh
        TinhThanh updatedTinhThanh = tinhThanhRepository.findById(tinhThanh.getId()).get();
        // Disconnect from session so that the updates on updatedTinhThanh are not directly saved in db
        em.detach(updatedTinhThanh);
        updatedTinhThanh
            .maTinh(UPDATED_MA_TINH)
            .tenTinh(UPDATED_TEN_TINH)
            .moTa(UPDATED_MO_TA);

        restTinhThanhMockMvc.perform(put("/api/tinh-thanhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTinhThanh)))
            .andExpect(status().isOk());

        // Validate the TinhThanh in the database
        List<TinhThanh> tinhThanhList = tinhThanhRepository.findAll();
        assertThat(tinhThanhList).hasSize(databaseSizeBeforeUpdate);
        TinhThanh testTinhThanh = tinhThanhList.get(tinhThanhList.size() - 1);
        assertThat(testTinhThanh.getMaTinh()).isEqualTo(UPDATED_MA_TINH);
        assertThat(testTinhThanh.getTenTinh()).isEqualTo(UPDATED_TEN_TINH);
        assertThat(testTinhThanh.getMoTa()).isEqualTo(UPDATED_MO_TA);

        // Validate the TinhThanh in Elasticsearch
        verify(mockTinhThanhSearchRepository, times(1)).save(testTinhThanh);
    }

    @Test
    @Transactional
    public void updateNonExistingTinhThanh() throws Exception {
        int databaseSizeBeforeUpdate = tinhThanhRepository.findAll().size();

        // Create the TinhThanh

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTinhThanhMockMvc.perform(put("/api/tinh-thanhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhThanh)))
            .andExpect(status().isBadRequest());

        // Validate the TinhThanh in the database
        List<TinhThanh> tinhThanhList = tinhThanhRepository.findAll();
        assertThat(tinhThanhList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TinhThanh in Elasticsearch
        verify(mockTinhThanhSearchRepository, times(0)).save(tinhThanh);
    }

    @Test
    @Transactional
    public void deleteTinhThanh() throws Exception {
        // Initialize the database
        tinhThanhRepository.saveAndFlush(tinhThanh);

        int databaseSizeBeforeDelete = tinhThanhRepository.findAll().size();

        // Get the tinhThanh
        restTinhThanhMockMvc.perform(delete("/api/tinh-thanhs/{id}", tinhThanh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TinhThanh> tinhThanhList = tinhThanhRepository.findAll();
        assertThat(tinhThanhList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TinhThanh in Elasticsearch
        verify(mockTinhThanhSearchRepository, times(1)).deleteById(tinhThanh.getId());
    }

    @Test
    @Transactional
    public void searchTinhThanh() throws Exception {
        // Initialize the database
        tinhThanhRepository.saveAndFlush(tinhThanh);
        when(mockTinhThanhSearchRepository.search(queryStringQuery("id:" + tinhThanh.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(tinhThanh), PageRequest.of(0, 1), 1));
        // Search the tinhThanh
        restTinhThanhMockMvc.perform(get("/api/_search/tinh-thanhs?query=id:" + tinhThanh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinhThanh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH)))
            .andExpect(jsonPath("$.[*].tenTinh").value(hasItem(DEFAULT_TEN_TINH)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhThanh.class);
        TinhThanh tinhThanh1 = new TinhThanh();
        tinhThanh1.setId(1L);
        TinhThanh tinhThanh2 = new TinhThanh();
        tinhThanh2.setId(tinhThanh1.getId());
        assertThat(tinhThanh1).isEqualTo(tinhThanh2);
        tinhThanh2.setId(2L);
        assertThat(tinhThanh1).isNotEqualTo(tinhThanh2);
        tinhThanh1.setId(null);
        assertThat(tinhThanh1).isNotEqualTo(tinhThanh2);
    }
}
