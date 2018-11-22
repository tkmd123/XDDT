package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.ThanNhanLietSi;
import vn.homtech.xddt.repository.ThanNhanLietSiRepository;
import vn.homtech.xddt.repository.search.ThanNhanLietSiSearchRepository;
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
 * Test class for the ThanNhanLietSiResource REST controller.
 *
 * @see ThanNhanLietSiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class ThanNhanLietSiResourceIntTest {

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ThanNhanLietSiRepository thanNhanLietSiRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.ThanNhanLietSiSearchRepositoryMockConfiguration
     */
    @Autowired
    private ThanNhanLietSiSearchRepository mockThanNhanLietSiSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restThanNhanLietSiMockMvc;

    private ThanNhanLietSi thanNhanLietSi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThanNhanLietSiResource thanNhanLietSiResource = new ThanNhanLietSiResource(thanNhanLietSiRepository, mockThanNhanLietSiSearchRepository);
        this.restThanNhanLietSiMockMvc = MockMvcBuilders.standaloneSetup(thanNhanLietSiResource)
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
    public static ThanNhanLietSi createEntity(EntityManager em) {
        ThanNhanLietSi thanNhanLietSi = new ThanNhanLietSi()
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED);
        return thanNhanLietSi;
    }

    @Before
    public void initTest() {
        thanNhanLietSi = createEntity(em);
    }

    @Test
    @Transactional
    public void createThanNhanLietSi() throws Exception {
        int databaseSizeBeforeCreate = thanNhanLietSiRepository.findAll().size();

        // Create the ThanNhanLietSi
        restThanNhanLietSiMockMvc.perform(post("/api/than-nhan-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thanNhanLietSi)))
            .andExpect(status().isCreated());

        // Validate the ThanNhanLietSi in the database
        List<ThanNhanLietSi> thanNhanLietSiList = thanNhanLietSiRepository.findAll();
        assertThat(thanNhanLietSiList).hasSize(databaseSizeBeforeCreate + 1);
        ThanNhanLietSi testThanNhanLietSi = thanNhanLietSiList.get(thanNhanLietSiList.size() - 1);
        assertThat(testThanNhanLietSi.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testThanNhanLietSi.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the ThanNhanLietSi in Elasticsearch
        verify(mockThanNhanLietSiSearchRepository, times(1)).save(testThanNhanLietSi);
    }

    @Test
    @Transactional
    public void createThanNhanLietSiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thanNhanLietSiRepository.findAll().size();

        // Create the ThanNhanLietSi with an existing ID
        thanNhanLietSi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThanNhanLietSiMockMvc.perform(post("/api/than-nhan-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thanNhanLietSi)))
            .andExpect(status().isBadRequest());

        // Validate the ThanNhanLietSi in the database
        List<ThanNhanLietSi> thanNhanLietSiList = thanNhanLietSiRepository.findAll();
        assertThat(thanNhanLietSiList).hasSize(databaseSizeBeforeCreate);

        // Validate the ThanNhanLietSi in Elasticsearch
        verify(mockThanNhanLietSiSearchRepository, times(0)).save(thanNhanLietSi);
    }

    @Test
    @Transactional
    public void getAllThanNhanLietSis() throws Exception {
        // Initialize the database
        thanNhanLietSiRepository.saveAndFlush(thanNhanLietSi);

        // Get all the thanNhanLietSiList
        restThanNhanLietSiMockMvc.perform(get("/api/than-nhan-liet-sis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thanNhanLietSi.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getThanNhanLietSi() throws Exception {
        // Initialize the database
        thanNhanLietSiRepository.saveAndFlush(thanNhanLietSi);

        // Get the thanNhanLietSi
        restThanNhanLietSiMockMvc.perform(get("/api/than-nhan-liet-sis/{id}", thanNhanLietSi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thanNhanLietSi.getId().intValue()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingThanNhanLietSi() throws Exception {
        // Get the thanNhanLietSi
        restThanNhanLietSiMockMvc.perform(get("/api/than-nhan-liet-sis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThanNhanLietSi() throws Exception {
        // Initialize the database
        thanNhanLietSiRepository.saveAndFlush(thanNhanLietSi);

        int databaseSizeBeforeUpdate = thanNhanLietSiRepository.findAll().size();

        // Update the thanNhanLietSi
        ThanNhanLietSi updatedThanNhanLietSi = thanNhanLietSiRepository.findById(thanNhanLietSi.getId()).get();
        // Disconnect from session so that the updates on updatedThanNhanLietSi are not directly saved in db
        em.detach(updatedThanNhanLietSi);
        updatedThanNhanLietSi
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED);

        restThanNhanLietSiMockMvc.perform(put("/api/than-nhan-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedThanNhanLietSi)))
            .andExpect(status().isOk());

        // Validate the ThanNhanLietSi in the database
        List<ThanNhanLietSi> thanNhanLietSiList = thanNhanLietSiRepository.findAll();
        assertThat(thanNhanLietSiList).hasSize(databaseSizeBeforeUpdate);
        ThanNhanLietSi testThanNhanLietSi = thanNhanLietSiList.get(thanNhanLietSiList.size() - 1);
        assertThat(testThanNhanLietSi.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testThanNhanLietSi.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the ThanNhanLietSi in Elasticsearch
        verify(mockThanNhanLietSiSearchRepository, times(1)).save(testThanNhanLietSi);
    }

    @Test
    @Transactional
    public void updateNonExistingThanNhanLietSi() throws Exception {
        int databaseSizeBeforeUpdate = thanNhanLietSiRepository.findAll().size();

        // Create the ThanNhanLietSi

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThanNhanLietSiMockMvc.perform(put("/api/than-nhan-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thanNhanLietSi)))
            .andExpect(status().isBadRequest());

        // Validate the ThanNhanLietSi in the database
        List<ThanNhanLietSi> thanNhanLietSiList = thanNhanLietSiRepository.findAll();
        assertThat(thanNhanLietSiList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ThanNhanLietSi in Elasticsearch
        verify(mockThanNhanLietSiSearchRepository, times(0)).save(thanNhanLietSi);
    }

    @Test
    @Transactional
    public void deleteThanNhanLietSi() throws Exception {
        // Initialize the database
        thanNhanLietSiRepository.saveAndFlush(thanNhanLietSi);

        int databaseSizeBeforeDelete = thanNhanLietSiRepository.findAll().size();

        // Get the thanNhanLietSi
        restThanNhanLietSiMockMvc.perform(delete("/api/than-nhan-liet-sis/{id}", thanNhanLietSi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ThanNhanLietSi> thanNhanLietSiList = thanNhanLietSiRepository.findAll();
        assertThat(thanNhanLietSiList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ThanNhanLietSi in Elasticsearch
        verify(mockThanNhanLietSiSearchRepository, times(1)).deleteById(thanNhanLietSi.getId());
    }

    @Test
    @Transactional
    public void searchThanNhanLietSi() throws Exception {
        // Initialize the database
        thanNhanLietSiRepository.saveAndFlush(thanNhanLietSi);
        when(mockThanNhanLietSiSearchRepository.search(queryStringQuery("id:" + thanNhanLietSi.getId())))
            .thenReturn(Collections.singletonList(thanNhanLietSi));
        // Search the thanNhanLietSi
        restThanNhanLietSiMockMvc.perform(get("/api/_search/than-nhan-liet-sis?query=id:" + thanNhanLietSi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thanNhanLietSi.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThanNhanLietSi.class);
        ThanNhanLietSi thanNhanLietSi1 = new ThanNhanLietSi();
        thanNhanLietSi1.setId(1L);
        ThanNhanLietSi thanNhanLietSi2 = new ThanNhanLietSi();
        thanNhanLietSi2.setId(thanNhanLietSi1.getId());
        assertThat(thanNhanLietSi1).isEqualTo(thanNhanLietSi2);
        thanNhanLietSi2.setId(2L);
        assertThat(thanNhanLietSi1).isNotEqualTo(thanNhanLietSi2);
        thanNhanLietSi1.setId(null);
        assertThat(thanNhanLietSi1).isNotEqualTo(thanNhanLietSi2);
    }
}
