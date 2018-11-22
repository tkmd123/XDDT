package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.QuanHuyen;
import vn.homtech.xddt.repository.QuanHuyenRepository;
import vn.homtech.xddt.repository.search.QuanHuyenSearchRepository;
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
 * Test class for the QuanHuyenResource REST controller.
 *
 * @see QuanHuyenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class QuanHuyenResourceIntTest {

    private static final String DEFAULT_MA_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_HUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_HUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    @Autowired
    private QuanHuyenRepository quanHuyenRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.QuanHuyenSearchRepositoryMockConfiguration
     */
    @Autowired
    private QuanHuyenSearchRepository mockQuanHuyenSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuanHuyenMockMvc;

    private QuanHuyen quanHuyen;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuanHuyenResource quanHuyenResource = new QuanHuyenResource(quanHuyenRepository, mockQuanHuyenSearchRepository);
        this.restQuanHuyenMockMvc = MockMvcBuilders.standaloneSetup(quanHuyenResource)
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
    public static QuanHuyen createEntity(EntityManager em) {
        QuanHuyen quanHuyen = new QuanHuyen()
            .maHuyen(DEFAULT_MA_HUYEN)
            .tenHuyen(DEFAULT_TEN_HUYEN)
            .moTa(DEFAULT_MO_TA);
        return quanHuyen;
    }

    @Before
    public void initTest() {
        quanHuyen = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuanHuyen() throws Exception {
        int databaseSizeBeforeCreate = quanHuyenRepository.findAll().size();

        // Create the QuanHuyen
        restQuanHuyenMockMvc.perform(post("/api/quan-huyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanHuyen)))
            .andExpect(status().isCreated());

        // Validate the QuanHuyen in the database
        List<QuanHuyen> quanHuyenList = quanHuyenRepository.findAll();
        assertThat(quanHuyenList).hasSize(databaseSizeBeforeCreate + 1);
        QuanHuyen testQuanHuyen = quanHuyenList.get(quanHuyenList.size() - 1);
        assertThat(testQuanHuyen.getMaHuyen()).isEqualTo(DEFAULT_MA_HUYEN);
        assertThat(testQuanHuyen.getTenHuyen()).isEqualTo(DEFAULT_TEN_HUYEN);
        assertThat(testQuanHuyen.getMoTa()).isEqualTo(DEFAULT_MO_TA);

        // Validate the QuanHuyen in Elasticsearch
        verify(mockQuanHuyenSearchRepository, times(1)).save(testQuanHuyen);
    }

    @Test
    @Transactional
    public void createQuanHuyenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quanHuyenRepository.findAll().size();

        // Create the QuanHuyen with an existing ID
        quanHuyen.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuanHuyenMockMvc.perform(post("/api/quan-huyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanHuyen)))
            .andExpect(status().isBadRequest());

        // Validate the QuanHuyen in the database
        List<QuanHuyen> quanHuyenList = quanHuyenRepository.findAll();
        assertThat(quanHuyenList).hasSize(databaseSizeBeforeCreate);

        // Validate the QuanHuyen in Elasticsearch
        verify(mockQuanHuyenSearchRepository, times(0)).save(quanHuyen);
    }

    @Test
    @Transactional
    public void getAllQuanHuyens() throws Exception {
        // Initialize the database
        quanHuyenRepository.saveAndFlush(quanHuyen);

        // Get all the quanHuyenList
        restQuanHuyenMockMvc.perform(get("/api/quan-huyens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quanHuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHuyen").value(hasItem(DEFAULT_MA_HUYEN.toString())))
            .andExpect(jsonPath("$.[*].tenHuyen").value(hasItem(DEFAULT_TEN_HUYEN.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())));
    }
    
    @Test
    @Transactional
    public void getQuanHuyen() throws Exception {
        // Initialize the database
        quanHuyenRepository.saveAndFlush(quanHuyen);

        // Get the quanHuyen
        restQuanHuyenMockMvc.perform(get("/api/quan-huyens/{id}", quanHuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quanHuyen.getId().intValue()))
            .andExpect(jsonPath("$.maHuyen").value(DEFAULT_MA_HUYEN.toString()))
            .andExpect(jsonPath("$.tenHuyen").value(DEFAULT_TEN_HUYEN.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuanHuyen() throws Exception {
        // Get the quanHuyen
        restQuanHuyenMockMvc.perform(get("/api/quan-huyens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuanHuyen() throws Exception {
        // Initialize the database
        quanHuyenRepository.saveAndFlush(quanHuyen);

        int databaseSizeBeforeUpdate = quanHuyenRepository.findAll().size();

        // Update the quanHuyen
        QuanHuyen updatedQuanHuyen = quanHuyenRepository.findById(quanHuyen.getId()).get();
        // Disconnect from session so that the updates on updatedQuanHuyen are not directly saved in db
        em.detach(updatedQuanHuyen);
        updatedQuanHuyen
            .maHuyen(UPDATED_MA_HUYEN)
            .tenHuyen(UPDATED_TEN_HUYEN)
            .moTa(UPDATED_MO_TA);

        restQuanHuyenMockMvc.perform(put("/api/quan-huyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedQuanHuyen)))
            .andExpect(status().isOk());

        // Validate the QuanHuyen in the database
        List<QuanHuyen> quanHuyenList = quanHuyenRepository.findAll();
        assertThat(quanHuyenList).hasSize(databaseSizeBeforeUpdate);
        QuanHuyen testQuanHuyen = quanHuyenList.get(quanHuyenList.size() - 1);
        assertThat(testQuanHuyen.getMaHuyen()).isEqualTo(UPDATED_MA_HUYEN);
        assertThat(testQuanHuyen.getTenHuyen()).isEqualTo(UPDATED_TEN_HUYEN);
        assertThat(testQuanHuyen.getMoTa()).isEqualTo(UPDATED_MO_TA);

        // Validate the QuanHuyen in Elasticsearch
        verify(mockQuanHuyenSearchRepository, times(1)).save(testQuanHuyen);
    }

    @Test
    @Transactional
    public void updateNonExistingQuanHuyen() throws Exception {
        int databaseSizeBeforeUpdate = quanHuyenRepository.findAll().size();

        // Create the QuanHuyen

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHuyenMockMvc.perform(put("/api/quan-huyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanHuyen)))
            .andExpect(status().isBadRequest());

        // Validate the QuanHuyen in the database
        List<QuanHuyen> quanHuyenList = quanHuyenRepository.findAll();
        assertThat(quanHuyenList).hasSize(databaseSizeBeforeUpdate);

        // Validate the QuanHuyen in Elasticsearch
        verify(mockQuanHuyenSearchRepository, times(0)).save(quanHuyen);
    }

    @Test
    @Transactional
    public void deleteQuanHuyen() throws Exception {
        // Initialize the database
        quanHuyenRepository.saveAndFlush(quanHuyen);

        int databaseSizeBeforeDelete = quanHuyenRepository.findAll().size();

        // Get the quanHuyen
        restQuanHuyenMockMvc.perform(delete("/api/quan-huyens/{id}", quanHuyen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuanHuyen> quanHuyenList = quanHuyenRepository.findAll();
        assertThat(quanHuyenList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the QuanHuyen in Elasticsearch
        verify(mockQuanHuyenSearchRepository, times(1)).deleteById(quanHuyen.getId());
    }

    @Test
    @Transactional
    public void searchQuanHuyen() throws Exception {
        // Initialize the database
        quanHuyenRepository.saveAndFlush(quanHuyen);
        when(mockQuanHuyenSearchRepository.search(queryStringQuery("id:" + quanHuyen.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(quanHuyen), PageRequest.of(0, 1), 1));
        // Search the quanHuyen
        restQuanHuyenMockMvc.perform(get("/api/_search/quan-huyens?query=id:" + quanHuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quanHuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHuyen").value(hasItem(DEFAULT_MA_HUYEN)))
            .andExpect(jsonPath("$.[*].tenHuyen").value(hasItem(DEFAULT_TEN_HUYEN)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanHuyen.class);
        QuanHuyen quanHuyen1 = new QuanHuyen();
        quanHuyen1.setId(1L);
        QuanHuyen quanHuyen2 = new QuanHuyen();
        quanHuyen2.setId(quanHuyen1.getId());
        assertThat(quanHuyen1).isEqualTo(quanHuyen2);
        quanHuyen2.setId(2L);
        assertThat(quanHuyen1).isNotEqualTo(quanHuyen2);
        quanHuyen1.setId(null);
        assertThat(quanHuyen1).isNotEqualTo(quanHuyen2);
    }
}
