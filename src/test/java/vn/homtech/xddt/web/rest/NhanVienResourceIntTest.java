package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.NhanVien;
import vn.homtech.xddt.repository.NhanVienRepository;
import vn.homtech.xddt.repository.search.NhanVienSearchRepository;
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
 * Test class for the NhanVienResource REST controller.
 *
 * @see NhanVienResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class NhanVienResourceIntTest {

    private static final String DEFAULT_MA_NHAN_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_NHAN_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_NHAN_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NHAN_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private NhanVienRepository nhanVienRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.NhanVienSearchRepositoryMockConfiguration
     */
    @Autowired
    private NhanVienSearchRepository mockNhanVienSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNhanVienMockMvc;

    private NhanVien nhanVien;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhanVienResource nhanVienResource = new NhanVienResource(nhanVienRepository, mockNhanVienSearchRepository);
        this.restNhanVienMockMvc = MockMvcBuilders.standaloneSetup(nhanVienResource)
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
    public static NhanVien createEntity(EntityManager em) {
        NhanVien nhanVien = new NhanVien()
            .maNhanVien(DEFAULT_MA_NHAN_VIEN)
            .tenNhanVien(DEFAULT_TEN_NHAN_VIEN)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .email(DEFAULT_EMAIL)
            .moTa(DEFAULT_MO_TA)
            .ghiChu(DEFAULT_GHI_CHU)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return nhanVien;
    }

    @Before
    public void initTest() {
        nhanVien = createEntity(em);
    }

    @Test
    @Transactional
    public void createNhanVien() throws Exception {
        int databaseSizeBeforeCreate = nhanVienRepository.findAll().size();

        // Create the NhanVien
        restNhanVienMockMvc.perform(post("/api/nhan-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanVien)))
            .andExpect(status().isCreated());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeCreate + 1);
        NhanVien testNhanVien = nhanVienList.get(nhanVienList.size() - 1);
        assertThat(testNhanVien.getMaNhanVien()).isEqualTo(DEFAULT_MA_NHAN_VIEN);
        assertThat(testNhanVien.getTenNhanVien()).isEqualTo(DEFAULT_TEN_NHAN_VIEN);
        assertThat(testNhanVien.getSoDienThoai()).isEqualTo(DEFAULT_SO_DIEN_THOAI);
        assertThat(testNhanVien.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testNhanVien.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testNhanVien.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testNhanVien.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testNhanVien.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testNhanVien.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testNhanVien.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the NhanVien in Elasticsearch
        verify(mockNhanVienSearchRepository, times(1)).save(testNhanVien);
    }

    @Test
    @Transactional
    public void createNhanVienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhanVienRepository.findAll().size();

        // Create the NhanVien with an existing ID
        nhanVien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanVienMockMvc.perform(post("/api/nhan-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanVien)))
            .andExpect(status().isBadRequest());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeCreate);

        // Validate the NhanVien in Elasticsearch
        verify(mockNhanVienSearchRepository, times(0)).save(nhanVien);
    }

    @Test
    @Transactional
    public void getAllNhanViens() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get all the nhanVienList
        restNhanVienMockMvc.perform(get("/api/nhan-viens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNhanVien").value(hasItem(DEFAULT_MA_NHAN_VIEN.toString())))
            .andExpect(jsonPath("$.[*].tenNhanVien").value(hasItem(DEFAULT_TEN_NHAN_VIEN.toString())))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getNhanVien() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        // Get the nhanVien
        restNhanVienMockMvc.perform(get("/api/nhan-viens/{id}", nhanVien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhanVien.getId().intValue()))
            .andExpect(jsonPath("$.maNhanVien").value(DEFAULT_MA_NHAN_VIEN.toString()))
            .andExpect(jsonPath("$.tenNhanVien").value(DEFAULT_TEN_NHAN_VIEN.toString()))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNhanVien() throws Exception {
        // Get the nhanVien
        restNhanVienMockMvc.perform(get("/api/nhan-viens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNhanVien() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();

        // Update the nhanVien
        NhanVien updatedNhanVien = nhanVienRepository.findById(nhanVien.getId()).get();
        // Disconnect from session so that the updates on updatedNhanVien are not directly saved in db
        em.detach(updatedNhanVien);
        updatedNhanVien
            .maNhanVien(UPDATED_MA_NHAN_VIEN)
            .tenNhanVien(UPDATED_TEN_NHAN_VIEN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .moTa(UPDATED_MO_TA)
            .ghiChu(UPDATED_GHI_CHU)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restNhanVienMockMvc.perform(put("/api/nhan-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNhanVien)))
            .andExpect(status().isOk());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);
        NhanVien testNhanVien = nhanVienList.get(nhanVienList.size() - 1);
        assertThat(testNhanVien.getMaNhanVien()).isEqualTo(UPDATED_MA_NHAN_VIEN);
        assertThat(testNhanVien.getTenNhanVien()).isEqualTo(UPDATED_TEN_NHAN_VIEN);
        assertThat(testNhanVien.getSoDienThoai()).isEqualTo(UPDATED_SO_DIEN_THOAI);
        assertThat(testNhanVien.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testNhanVien.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testNhanVien.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testNhanVien.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testNhanVien.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testNhanVien.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testNhanVien.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the NhanVien in Elasticsearch
        verify(mockNhanVienSearchRepository, times(1)).save(testNhanVien);
    }

    @Test
    @Transactional
    public void updateNonExistingNhanVien() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienRepository.findAll().size();

        // Create the NhanVien

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanVienMockMvc.perform(put("/api/nhan-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanVien)))
            .andExpect(status().isBadRequest());

        // Validate the NhanVien in the database
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NhanVien in Elasticsearch
        verify(mockNhanVienSearchRepository, times(0)).save(nhanVien);
    }

    @Test
    @Transactional
    public void deleteNhanVien() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);

        int databaseSizeBeforeDelete = nhanVienRepository.findAll().size();

        // Get the nhanVien
        restNhanVienMockMvc.perform(delete("/api/nhan-viens/{id}", nhanVien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        assertThat(nhanVienList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NhanVien in Elasticsearch
        verify(mockNhanVienSearchRepository, times(1)).deleteById(nhanVien.getId());
    }

    @Test
    @Transactional
    public void searchNhanVien() throws Exception {
        // Initialize the database
        nhanVienRepository.saveAndFlush(nhanVien);
        when(mockNhanVienSearchRepository.search(queryStringQuery("id:" + nhanVien.getId())))
            .thenReturn(Collections.singletonList(nhanVien));
        // Search the nhanVien
        restNhanVienMockMvc.perform(get("/api/_search/nhan-viens?query=id:" + nhanVien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNhanVien").value(hasItem(DEFAULT_MA_NHAN_VIEN)))
            .andExpect(jsonPath("$.[*].tenNhanVien").value(hasItem(DEFAULT_TEN_NHAN_VIEN)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanVien.class);
        NhanVien nhanVien1 = new NhanVien();
        nhanVien1.setId(1L);
        NhanVien nhanVien2 = new NhanVien();
        nhanVien2.setId(nhanVien1.getId());
        assertThat(nhanVien1).isEqualTo(nhanVien2);
        nhanVien2.setId(2L);
        assertThat(nhanVien1).isNotEqualTo(nhanVien2);
        nhanVien1.setId(null);
        assertThat(nhanVien1).isNotEqualTo(nhanVien2);
    }
}
