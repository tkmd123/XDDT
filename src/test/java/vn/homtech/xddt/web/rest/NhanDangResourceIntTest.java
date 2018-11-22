package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.NhanDang;
import vn.homtech.xddt.repository.NhanDangRepository;
import vn.homtech.xddt.repository.search.NhanDangSearchRepository;
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
 * Test class for the NhanDangResource REST controller.
 *
 * @see NhanDangResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class NhanDangResourceIntTest {

    private static final String DEFAULT_MA_NHAN_DANG = "AAAAAAAAAA";
    private static final String UPDATED_MA_NHAN_DANG = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_NHAN_DANG = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NHAN_DANG = "BBBBBBBBBB";

    private static final String DEFAULT_DON_VI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_DON_VI_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private NhanDangRepository nhanDangRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.NhanDangSearchRepositoryMockConfiguration
     */
    @Autowired
    private NhanDangSearchRepository mockNhanDangSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNhanDangMockMvc;

    private NhanDang nhanDang;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhanDangResource nhanDangResource = new NhanDangResource(nhanDangRepository, mockNhanDangSearchRepository);
        this.restNhanDangMockMvc = MockMvcBuilders.standaloneSetup(nhanDangResource)
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
    public static NhanDang createEntity(EntityManager em) {
        NhanDang nhanDang = new NhanDang()
            .maNhanDang(DEFAULT_MA_NHAN_DANG)
            .tenNhanDang(DEFAULT_TEN_NHAN_DANG)
            .donViTinh(DEFAULT_DON_VI_TINH)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED);
        return nhanDang;
    }

    @Before
    public void initTest() {
        nhanDang = createEntity(em);
    }

    @Test
    @Transactional
    public void createNhanDang() throws Exception {
        int databaseSizeBeforeCreate = nhanDangRepository.findAll().size();

        // Create the NhanDang
        restNhanDangMockMvc.perform(post("/api/nhan-dangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanDang)))
            .andExpect(status().isCreated());

        // Validate the NhanDang in the database
        List<NhanDang> nhanDangList = nhanDangRepository.findAll();
        assertThat(nhanDangList).hasSize(databaseSizeBeforeCreate + 1);
        NhanDang testNhanDang = nhanDangList.get(nhanDangList.size() - 1);
        assertThat(testNhanDang.getMaNhanDang()).isEqualTo(DEFAULT_MA_NHAN_DANG);
        assertThat(testNhanDang.getTenNhanDang()).isEqualTo(DEFAULT_TEN_NHAN_DANG);
        assertThat(testNhanDang.getDonViTinh()).isEqualTo(DEFAULT_DON_VI_TINH);
        assertThat(testNhanDang.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testNhanDang.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the NhanDang in Elasticsearch
        verify(mockNhanDangSearchRepository, times(1)).save(testNhanDang);
    }

    @Test
    @Transactional
    public void createNhanDangWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhanDangRepository.findAll().size();

        // Create the NhanDang with an existing ID
        nhanDang.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanDangMockMvc.perform(post("/api/nhan-dangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanDang)))
            .andExpect(status().isBadRequest());

        // Validate the NhanDang in the database
        List<NhanDang> nhanDangList = nhanDangRepository.findAll();
        assertThat(nhanDangList).hasSize(databaseSizeBeforeCreate);

        // Validate the NhanDang in Elasticsearch
        verify(mockNhanDangSearchRepository, times(0)).save(nhanDang);
    }

    @Test
    @Transactional
    public void getAllNhanDangs() throws Exception {
        // Initialize the database
        nhanDangRepository.saveAndFlush(nhanDang);

        // Get all the nhanDangList
        restNhanDangMockMvc.perform(get("/api/nhan-dangs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanDang.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNhanDang").value(hasItem(DEFAULT_MA_NHAN_DANG.toString())))
            .andExpect(jsonPath("$.[*].tenNhanDang").value(hasItem(DEFAULT_TEN_NHAN_DANG.toString())))
            .andExpect(jsonPath("$.[*].donViTinh").value(hasItem(DEFAULT_DON_VI_TINH.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getNhanDang() throws Exception {
        // Initialize the database
        nhanDangRepository.saveAndFlush(nhanDang);

        // Get the nhanDang
        restNhanDangMockMvc.perform(get("/api/nhan-dangs/{id}", nhanDang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhanDang.getId().intValue()))
            .andExpect(jsonPath("$.maNhanDang").value(DEFAULT_MA_NHAN_DANG.toString()))
            .andExpect(jsonPath("$.tenNhanDang").value(DEFAULT_TEN_NHAN_DANG.toString()))
            .andExpect(jsonPath("$.donViTinh").value(DEFAULT_DON_VI_TINH.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNhanDang() throws Exception {
        // Get the nhanDang
        restNhanDangMockMvc.perform(get("/api/nhan-dangs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNhanDang() throws Exception {
        // Initialize the database
        nhanDangRepository.saveAndFlush(nhanDang);

        int databaseSizeBeforeUpdate = nhanDangRepository.findAll().size();

        // Update the nhanDang
        NhanDang updatedNhanDang = nhanDangRepository.findById(nhanDang.getId()).get();
        // Disconnect from session so that the updates on updatedNhanDang are not directly saved in db
        em.detach(updatedNhanDang);
        updatedNhanDang
            .maNhanDang(UPDATED_MA_NHAN_DANG)
            .tenNhanDang(UPDATED_TEN_NHAN_DANG)
            .donViTinh(UPDATED_DON_VI_TINH)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED);

        restNhanDangMockMvc.perform(put("/api/nhan-dangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNhanDang)))
            .andExpect(status().isOk());

        // Validate the NhanDang in the database
        List<NhanDang> nhanDangList = nhanDangRepository.findAll();
        assertThat(nhanDangList).hasSize(databaseSizeBeforeUpdate);
        NhanDang testNhanDang = nhanDangList.get(nhanDangList.size() - 1);
        assertThat(testNhanDang.getMaNhanDang()).isEqualTo(UPDATED_MA_NHAN_DANG);
        assertThat(testNhanDang.getTenNhanDang()).isEqualTo(UPDATED_TEN_NHAN_DANG);
        assertThat(testNhanDang.getDonViTinh()).isEqualTo(UPDATED_DON_VI_TINH);
        assertThat(testNhanDang.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testNhanDang.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the NhanDang in Elasticsearch
        verify(mockNhanDangSearchRepository, times(1)).save(testNhanDang);
    }

    @Test
    @Transactional
    public void updateNonExistingNhanDang() throws Exception {
        int databaseSizeBeforeUpdate = nhanDangRepository.findAll().size();

        // Create the NhanDang

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanDangMockMvc.perform(put("/api/nhan-dangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanDang)))
            .andExpect(status().isBadRequest());

        // Validate the NhanDang in the database
        List<NhanDang> nhanDangList = nhanDangRepository.findAll();
        assertThat(nhanDangList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NhanDang in Elasticsearch
        verify(mockNhanDangSearchRepository, times(0)).save(nhanDang);
    }

    @Test
    @Transactional
    public void deleteNhanDang() throws Exception {
        // Initialize the database
        nhanDangRepository.saveAndFlush(nhanDang);

        int databaseSizeBeforeDelete = nhanDangRepository.findAll().size();

        // Get the nhanDang
        restNhanDangMockMvc.perform(delete("/api/nhan-dangs/{id}", nhanDang.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NhanDang> nhanDangList = nhanDangRepository.findAll();
        assertThat(nhanDangList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NhanDang in Elasticsearch
        verify(mockNhanDangSearchRepository, times(1)).deleteById(nhanDang.getId());
    }

    @Test
    @Transactional
    public void searchNhanDang() throws Exception {
        // Initialize the database
        nhanDangRepository.saveAndFlush(nhanDang);
        when(mockNhanDangSearchRepository.search(queryStringQuery("id:" + nhanDang.getId())))
            .thenReturn(Collections.singletonList(nhanDang));
        // Search the nhanDang
        restNhanDangMockMvc.perform(get("/api/_search/nhan-dangs?query=id:" + nhanDang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanDang.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNhanDang").value(hasItem(DEFAULT_MA_NHAN_DANG)))
            .andExpect(jsonPath("$.[*].tenNhanDang").value(hasItem(DEFAULT_TEN_NHAN_DANG)))
            .andExpect(jsonPath("$.[*].donViTinh").value(hasItem(DEFAULT_DON_VI_TINH)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanDang.class);
        NhanDang nhanDang1 = new NhanDang();
        nhanDang1.setId(1L);
        NhanDang nhanDang2 = new NhanDang();
        nhanDang2.setId(nhanDang1.getId());
        assertThat(nhanDang1).isEqualTo(nhanDang2);
        nhanDang2.setId(2L);
        assertThat(nhanDang1).isNotEqualTo(nhanDang2);
        nhanDang1.setId(null);
        assertThat(nhanDang1).isNotEqualTo(nhanDang2);
    }
}
