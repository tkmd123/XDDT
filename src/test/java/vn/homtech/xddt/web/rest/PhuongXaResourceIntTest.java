package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.PhuongXa;
import vn.homtech.xddt.repository.PhuongXaRepository;
import vn.homtech.xddt.repository.search.PhuongXaSearchRepository;
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
 * Test class for the PhuongXaResource REST controller.
 *
 * @see PhuongXaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class PhuongXaResourceIntTest {

    private static final String DEFAULT_MA_XA = "AAAAAAAAAA";
    private static final String UPDATED_MA_XA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_XA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_XA = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    @Autowired
    private PhuongXaRepository phuongXaRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.PhuongXaSearchRepositoryMockConfiguration
     */
    @Autowired
    private PhuongXaSearchRepository mockPhuongXaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPhuongXaMockMvc;

    private PhuongXa phuongXa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhuongXaResource phuongXaResource = new PhuongXaResource(phuongXaRepository, mockPhuongXaSearchRepository);
        this.restPhuongXaMockMvc = MockMvcBuilders.standaloneSetup(phuongXaResource)
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
    public static PhuongXa createEntity(EntityManager em) {
        PhuongXa phuongXa = new PhuongXa()
            .maXa(DEFAULT_MA_XA)
            .tenXa(DEFAULT_TEN_XA)
            .moTa(DEFAULT_MO_TA);
        return phuongXa;
    }

    @Before
    public void initTest() {
        phuongXa = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhuongXa() throws Exception {
        int databaseSizeBeforeCreate = phuongXaRepository.findAll().size();

        // Create the PhuongXa
        restPhuongXaMockMvc.perform(post("/api/phuong-xas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phuongXa)))
            .andExpect(status().isCreated());

        // Validate the PhuongXa in the database
        List<PhuongXa> phuongXaList = phuongXaRepository.findAll();
        assertThat(phuongXaList).hasSize(databaseSizeBeforeCreate + 1);
        PhuongXa testPhuongXa = phuongXaList.get(phuongXaList.size() - 1);
        assertThat(testPhuongXa.getMaXa()).isEqualTo(DEFAULT_MA_XA);
        assertThat(testPhuongXa.getTenXa()).isEqualTo(DEFAULT_TEN_XA);
        assertThat(testPhuongXa.getMoTa()).isEqualTo(DEFAULT_MO_TA);

        // Validate the PhuongXa in Elasticsearch
        verify(mockPhuongXaSearchRepository, times(1)).save(testPhuongXa);
    }

    @Test
    @Transactional
    public void createPhuongXaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = phuongXaRepository.findAll().size();

        // Create the PhuongXa with an existing ID
        phuongXa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhuongXaMockMvc.perform(post("/api/phuong-xas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phuongXa)))
            .andExpect(status().isBadRequest());

        // Validate the PhuongXa in the database
        List<PhuongXa> phuongXaList = phuongXaRepository.findAll();
        assertThat(phuongXaList).hasSize(databaseSizeBeforeCreate);

        // Validate the PhuongXa in Elasticsearch
        verify(mockPhuongXaSearchRepository, times(0)).save(phuongXa);
    }

    @Test
    @Transactional
    public void getAllPhuongXas() throws Exception {
        // Initialize the database
        phuongXaRepository.saveAndFlush(phuongXa);

        // Get all the phuongXaList
        restPhuongXaMockMvc.perform(get("/api/phuong-xas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phuongXa.getId().intValue())))
            .andExpect(jsonPath("$.[*].maXa").value(hasItem(DEFAULT_MA_XA.toString())))
            .andExpect(jsonPath("$.[*].tenXa").value(hasItem(DEFAULT_TEN_XA.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())));
    }
    
    @Test
    @Transactional
    public void getPhuongXa() throws Exception {
        // Initialize the database
        phuongXaRepository.saveAndFlush(phuongXa);

        // Get the phuongXa
        restPhuongXaMockMvc.perform(get("/api/phuong-xas/{id}", phuongXa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(phuongXa.getId().intValue()))
            .andExpect(jsonPath("$.maXa").value(DEFAULT_MA_XA.toString()))
            .andExpect(jsonPath("$.tenXa").value(DEFAULT_TEN_XA.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPhuongXa() throws Exception {
        // Get the phuongXa
        restPhuongXaMockMvc.perform(get("/api/phuong-xas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhuongXa() throws Exception {
        // Initialize the database
        phuongXaRepository.saveAndFlush(phuongXa);

        int databaseSizeBeforeUpdate = phuongXaRepository.findAll().size();

        // Update the phuongXa
        PhuongXa updatedPhuongXa = phuongXaRepository.findById(phuongXa.getId()).get();
        // Disconnect from session so that the updates on updatedPhuongXa are not directly saved in db
        em.detach(updatedPhuongXa);
        updatedPhuongXa
            .maXa(UPDATED_MA_XA)
            .tenXa(UPDATED_TEN_XA)
            .moTa(UPDATED_MO_TA);

        restPhuongXaMockMvc.perform(put("/api/phuong-xas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPhuongXa)))
            .andExpect(status().isOk());

        // Validate the PhuongXa in the database
        List<PhuongXa> phuongXaList = phuongXaRepository.findAll();
        assertThat(phuongXaList).hasSize(databaseSizeBeforeUpdate);
        PhuongXa testPhuongXa = phuongXaList.get(phuongXaList.size() - 1);
        assertThat(testPhuongXa.getMaXa()).isEqualTo(UPDATED_MA_XA);
        assertThat(testPhuongXa.getTenXa()).isEqualTo(UPDATED_TEN_XA);
        assertThat(testPhuongXa.getMoTa()).isEqualTo(UPDATED_MO_TA);

        // Validate the PhuongXa in Elasticsearch
        verify(mockPhuongXaSearchRepository, times(1)).save(testPhuongXa);
    }

    @Test
    @Transactional
    public void updateNonExistingPhuongXa() throws Exception {
        int databaseSizeBeforeUpdate = phuongXaRepository.findAll().size();

        // Create the PhuongXa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhuongXaMockMvc.perform(put("/api/phuong-xas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phuongXa)))
            .andExpect(status().isBadRequest());

        // Validate the PhuongXa in the database
        List<PhuongXa> phuongXaList = phuongXaRepository.findAll();
        assertThat(phuongXaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PhuongXa in Elasticsearch
        verify(mockPhuongXaSearchRepository, times(0)).save(phuongXa);
    }

    @Test
    @Transactional
    public void deletePhuongXa() throws Exception {
        // Initialize the database
        phuongXaRepository.saveAndFlush(phuongXa);

        int databaseSizeBeforeDelete = phuongXaRepository.findAll().size();

        // Get the phuongXa
        restPhuongXaMockMvc.perform(delete("/api/phuong-xas/{id}", phuongXa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PhuongXa> phuongXaList = phuongXaRepository.findAll();
        assertThat(phuongXaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PhuongXa in Elasticsearch
        verify(mockPhuongXaSearchRepository, times(1)).deleteById(phuongXa.getId());
    }

    @Test
    @Transactional
    public void searchPhuongXa() throws Exception {
        // Initialize the database
        phuongXaRepository.saveAndFlush(phuongXa);
        when(mockPhuongXaSearchRepository.search(queryStringQuery("id:" + phuongXa.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(phuongXa), PageRequest.of(0, 1), 1));
        // Search the phuongXa
        restPhuongXaMockMvc.perform(get("/api/_search/phuong-xas?query=id:" + phuongXa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phuongXa.getId().intValue())))
            .andExpect(jsonPath("$.[*].maXa").value(hasItem(DEFAULT_MA_XA)))
            .andExpect(jsonPath("$.[*].tenXa").value(hasItem(DEFAULT_TEN_XA)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhuongXa.class);
        PhuongXa phuongXa1 = new PhuongXa();
        phuongXa1.setId(1L);
        PhuongXa phuongXa2 = new PhuongXa();
        phuongXa2.setId(phuongXa1.getId());
        assertThat(phuongXa1).isEqualTo(phuongXa2);
        phuongXa2.setId(2L);
        assertThat(phuongXa1).isNotEqualTo(phuongXa2);
        phuongXa1.setId(null);
        assertThat(phuongXa1).isNotEqualTo(phuongXa2);
    }
}
