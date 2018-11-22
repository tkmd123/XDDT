package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.NhanDangLietSi;
import vn.homtech.xddt.repository.NhanDangLietSiRepository;
import vn.homtech.xddt.repository.search.NhanDangLietSiSearchRepository;
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
 * Test class for the NhanDangLietSiResource REST controller.
 *
 * @see NhanDangLietSiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class NhanDangLietSiResourceIntTest {

    private static final String DEFAULT_GIA_TRI = "AAAAAAAAAA";
    private static final String UPDATED_GIA_TRI = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private NhanDangLietSiRepository nhanDangLietSiRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.NhanDangLietSiSearchRepositoryMockConfiguration
     */
    @Autowired
    private NhanDangLietSiSearchRepository mockNhanDangLietSiSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNhanDangLietSiMockMvc;

    private NhanDangLietSi nhanDangLietSi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhanDangLietSiResource nhanDangLietSiResource = new NhanDangLietSiResource(nhanDangLietSiRepository, mockNhanDangLietSiSearchRepository);
        this.restNhanDangLietSiMockMvc = MockMvcBuilders.standaloneSetup(nhanDangLietSiResource)
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
    public static NhanDangLietSi createEntity(EntityManager em) {
        NhanDangLietSi nhanDangLietSi = new NhanDangLietSi()
            .giaTri(DEFAULT_GIA_TRI)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED);
        return nhanDangLietSi;
    }

    @Before
    public void initTest() {
        nhanDangLietSi = createEntity(em);
    }

    @Test
    @Transactional
    public void createNhanDangLietSi() throws Exception {
        int databaseSizeBeforeCreate = nhanDangLietSiRepository.findAll().size();

        // Create the NhanDangLietSi
        restNhanDangLietSiMockMvc.perform(post("/api/nhan-dang-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanDangLietSi)))
            .andExpect(status().isCreated());

        // Validate the NhanDangLietSi in the database
        List<NhanDangLietSi> nhanDangLietSiList = nhanDangLietSiRepository.findAll();
        assertThat(nhanDangLietSiList).hasSize(databaseSizeBeforeCreate + 1);
        NhanDangLietSi testNhanDangLietSi = nhanDangLietSiList.get(nhanDangLietSiList.size() - 1);
        assertThat(testNhanDangLietSi.getGiaTri()).isEqualTo(DEFAULT_GIA_TRI);
        assertThat(testNhanDangLietSi.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testNhanDangLietSi.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the NhanDangLietSi in Elasticsearch
        verify(mockNhanDangLietSiSearchRepository, times(1)).save(testNhanDangLietSi);
    }

    @Test
    @Transactional
    public void createNhanDangLietSiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhanDangLietSiRepository.findAll().size();

        // Create the NhanDangLietSi with an existing ID
        nhanDangLietSi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanDangLietSiMockMvc.perform(post("/api/nhan-dang-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanDangLietSi)))
            .andExpect(status().isBadRequest());

        // Validate the NhanDangLietSi in the database
        List<NhanDangLietSi> nhanDangLietSiList = nhanDangLietSiRepository.findAll();
        assertThat(nhanDangLietSiList).hasSize(databaseSizeBeforeCreate);

        // Validate the NhanDangLietSi in Elasticsearch
        verify(mockNhanDangLietSiSearchRepository, times(0)).save(nhanDangLietSi);
    }

    @Test
    @Transactional
    public void getAllNhanDangLietSis() throws Exception {
        // Initialize the database
        nhanDangLietSiRepository.saveAndFlush(nhanDangLietSi);

        // Get all the nhanDangLietSiList
        restNhanDangLietSiMockMvc.perform(get("/api/nhan-dang-liet-sis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanDangLietSi.getId().intValue())))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getNhanDangLietSi() throws Exception {
        // Initialize the database
        nhanDangLietSiRepository.saveAndFlush(nhanDangLietSi);

        // Get the nhanDangLietSi
        restNhanDangLietSiMockMvc.perform(get("/api/nhan-dang-liet-sis/{id}", nhanDangLietSi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhanDangLietSi.getId().intValue()))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNhanDangLietSi() throws Exception {
        // Get the nhanDangLietSi
        restNhanDangLietSiMockMvc.perform(get("/api/nhan-dang-liet-sis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNhanDangLietSi() throws Exception {
        // Initialize the database
        nhanDangLietSiRepository.saveAndFlush(nhanDangLietSi);

        int databaseSizeBeforeUpdate = nhanDangLietSiRepository.findAll().size();

        // Update the nhanDangLietSi
        NhanDangLietSi updatedNhanDangLietSi = nhanDangLietSiRepository.findById(nhanDangLietSi.getId()).get();
        // Disconnect from session so that the updates on updatedNhanDangLietSi are not directly saved in db
        em.detach(updatedNhanDangLietSi);
        updatedNhanDangLietSi
            .giaTri(UPDATED_GIA_TRI)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED);

        restNhanDangLietSiMockMvc.perform(put("/api/nhan-dang-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNhanDangLietSi)))
            .andExpect(status().isOk());

        // Validate the NhanDangLietSi in the database
        List<NhanDangLietSi> nhanDangLietSiList = nhanDangLietSiRepository.findAll();
        assertThat(nhanDangLietSiList).hasSize(databaseSizeBeforeUpdate);
        NhanDangLietSi testNhanDangLietSi = nhanDangLietSiList.get(nhanDangLietSiList.size() - 1);
        assertThat(testNhanDangLietSi.getGiaTri()).isEqualTo(UPDATED_GIA_TRI);
        assertThat(testNhanDangLietSi.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testNhanDangLietSi.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the NhanDangLietSi in Elasticsearch
        verify(mockNhanDangLietSiSearchRepository, times(1)).save(testNhanDangLietSi);
    }

    @Test
    @Transactional
    public void updateNonExistingNhanDangLietSi() throws Exception {
        int databaseSizeBeforeUpdate = nhanDangLietSiRepository.findAll().size();

        // Create the NhanDangLietSi

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanDangLietSiMockMvc.perform(put("/api/nhan-dang-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanDangLietSi)))
            .andExpect(status().isBadRequest());

        // Validate the NhanDangLietSi in the database
        List<NhanDangLietSi> nhanDangLietSiList = nhanDangLietSiRepository.findAll();
        assertThat(nhanDangLietSiList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NhanDangLietSi in Elasticsearch
        verify(mockNhanDangLietSiSearchRepository, times(0)).save(nhanDangLietSi);
    }

    @Test
    @Transactional
    public void deleteNhanDangLietSi() throws Exception {
        // Initialize the database
        nhanDangLietSiRepository.saveAndFlush(nhanDangLietSi);

        int databaseSizeBeforeDelete = nhanDangLietSiRepository.findAll().size();

        // Get the nhanDangLietSi
        restNhanDangLietSiMockMvc.perform(delete("/api/nhan-dang-liet-sis/{id}", nhanDangLietSi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NhanDangLietSi> nhanDangLietSiList = nhanDangLietSiRepository.findAll();
        assertThat(nhanDangLietSiList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NhanDangLietSi in Elasticsearch
        verify(mockNhanDangLietSiSearchRepository, times(1)).deleteById(nhanDangLietSi.getId());
    }

    @Test
    @Transactional
    public void searchNhanDangLietSi() throws Exception {
        // Initialize the database
        nhanDangLietSiRepository.saveAndFlush(nhanDangLietSi);
        when(mockNhanDangLietSiSearchRepository.search(queryStringQuery("id:" + nhanDangLietSi.getId())))
            .thenReturn(Collections.singletonList(nhanDangLietSi));
        // Search the nhanDangLietSi
        restNhanDangLietSiMockMvc.perform(get("/api/_search/nhan-dang-liet-sis?query=id:" + nhanDangLietSi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanDangLietSi.getId().intValue())))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanDangLietSi.class);
        NhanDangLietSi nhanDangLietSi1 = new NhanDangLietSi();
        nhanDangLietSi1.setId(1L);
        NhanDangLietSi nhanDangLietSi2 = new NhanDangLietSi();
        nhanDangLietSi2.setId(nhanDangLietSi1.getId());
        assertThat(nhanDangLietSi1).isEqualTo(nhanDangLietSi2);
        nhanDangLietSi2.setId(2L);
        assertThat(nhanDangLietSi1).isNotEqualTo(nhanDangLietSi2);
        nhanDangLietSi1.setId(null);
        assertThat(nhanDangLietSi1).isNotEqualTo(nhanDangLietSi2);
    }
}
