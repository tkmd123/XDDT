package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.NghiaTrang;
import vn.homtech.xddt.repository.NghiaTrangRepository;
import vn.homtech.xddt.repository.search.NghiaTrangSearchRepository;
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
 * Test class for the NghiaTrangResource REST controller.
 *
 * @see NghiaTrangResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class NghiaTrangResourceIntTest {

    private static final String DEFAULT_MA_NGHIA_TRANG = "AAAAAAAAAA";
    private static final String UPDATED_MA_NGHIA_TRANG = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_NGHIA_TRANG = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NGHIA_TRANG = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_LIEN_HE = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_LIEN_HE = "BBBBBBBBBB";

    private static final String DEFAULT_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

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
    private NghiaTrangRepository nghiaTrangRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.NghiaTrangSearchRepositoryMockConfiguration
     */
    @Autowired
    private NghiaTrangSearchRepository mockNghiaTrangSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNghiaTrangMockMvc;

    private NghiaTrang nghiaTrang;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NghiaTrangResource nghiaTrangResource = new NghiaTrangResource(nghiaTrangRepository, mockNghiaTrangSearchRepository);
        this.restNghiaTrangMockMvc = MockMvcBuilders.standaloneSetup(nghiaTrangResource)
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
    public static NghiaTrang createEntity(EntityManager em) {
        NghiaTrang nghiaTrang = new NghiaTrang()
            .maNghiaTrang(DEFAULT_MA_NGHIA_TRANG)
            .tenNghiaTrang(DEFAULT_TEN_NGHIA_TRANG)
            .diaChi(DEFAULT_DIA_CHI)
            .nguoiLienHe(DEFAULT_NGUOI_LIEN_HE)
            .dienThoai(DEFAULT_DIEN_THOAI)
            .email(DEFAULT_EMAIL)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return nghiaTrang;
    }

    @Before
    public void initTest() {
        nghiaTrang = createEntity(em);
    }

    @Test
    @Transactional
    public void createNghiaTrang() throws Exception {
        int databaseSizeBeforeCreate = nghiaTrangRepository.findAll().size();

        // Create the NghiaTrang
        restNghiaTrangMockMvc.perform(post("/api/nghia-trangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nghiaTrang)))
            .andExpect(status().isCreated());

        // Validate the NghiaTrang in the database
        List<NghiaTrang> nghiaTrangList = nghiaTrangRepository.findAll();
        assertThat(nghiaTrangList).hasSize(databaseSizeBeforeCreate + 1);
        NghiaTrang testNghiaTrang = nghiaTrangList.get(nghiaTrangList.size() - 1);
        assertThat(testNghiaTrang.getMaNghiaTrang()).isEqualTo(DEFAULT_MA_NGHIA_TRANG);
        assertThat(testNghiaTrang.getTenNghiaTrang()).isEqualTo(DEFAULT_TEN_NGHIA_TRANG);
        assertThat(testNghiaTrang.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
        assertThat(testNghiaTrang.getNguoiLienHe()).isEqualTo(DEFAULT_NGUOI_LIEN_HE);
        assertThat(testNghiaTrang.getDienThoai()).isEqualTo(DEFAULT_DIEN_THOAI);
        assertThat(testNghiaTrang.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testNghiaTrang.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testNghiaTrang.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testNghiaTrang.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testNghiaTrang.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testNghiaTrang.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the NghiaTrang in Elasticsearch
        verify(mockNghiaTrangSearchRepository, times(1)).save(testNghiaTrang);
    }

    @Test
    @Transactional
    public void createNghiaTrangWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nghiaTrangRepository.findAll().size();

        // Create the NghiaTrang with an existing ID
        nghiaTrang.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNghiaTrangMockMvc.perform(post("/api/nghia-trangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nghiaTrang)))
            .andExpect(status().isBadRequest());

        // Validate the NghiaTrang in the database
        List<NghiaTrang> nghiaTrangList = nghiaTrangRepository.findAll();
        assertThat(nghiaTrangList).hasSize(databaseSizeBeforeCreate);

        // Validate the NghiaTrang in Elasticsearch
        verify(mockNghiaTrangSearchRepository, times(0)).save(nghiaTrang);
    }

    @Test
    @Transactional
    public void getAllNghiaTrangs() throws Exception {
        // Initialize the database
        nghiaTrangRepository.saveAndFlush(nghiaTrang);

        // Get all the nghiaTrangList
        restNghiaTrangMockMvc.perform(get("/api/nghia-trangs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nghiaTrang.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNghiaTrang").value(hasItem(DEFAULT_MA_NGHIA_TRANG.toString())))
            .andExpect(jsonPath("$.[*].tenNghiaTrang").value(hasItem(DEFAULT_TEN_NGHIA_TRANG.toString())))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI.toString())))
            .andExpect(jsonPath("$.[*].nguoiLienHe").value(hasItem(DEFAULT_NGUOI_LIEN_HE.toString())))
            .andExpect(jsonPath("$.[*].dienThoai").value(hasItem(DEFAULT_DIEN_THOAI.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getNghiaTrang() throws Exception {
        // Initialize the database
        nghiaTrangRepository.saveAndFlush(nghiaTrang);

        // Get the nghiaTrang
        restNghiaTrangMockMvc.perform(get("/api/nghia-trangs/{id}", nghiaTrang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nghiaTrang.getId().intValue()))
            .andExpect(jsonPath("$.maNghiaTrang").value(DEFAULT_MA_NGHIA_TRANG.toString()))
            .andExpect(jsonPath("$.tenNghiaTrang").value(DEFAULT_TEN_NGHIA_TRANG.toString()))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI.toString()))
            .andExpect(jsonPath("$.nguoiLienHe").value(DEFAULT_NGUOI_LIEN_HE.toString()))
            .andExpect(jsonPath("$.dienThoai").value(DEFAULT_DIEN_THOAI.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNghiaTrang() throws Exception {
        // Get the nghiaTrang
        restNghiaTrangMockMvc.perform(get("/api/nghia-trangs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNghiaTrang() throws Exception {
        // Initialize the database
        nghiaTrangRepository.saveAndFlush(nghiaTrang);

        int databaseSizeBeforeUpdate = nghiaTrangRepository.findAll().size();

        // Update the nghiaTrang
        NghiaTrang updatedNghiaTrang = nghiaTrangRepository.findById(nghiaTrang.getId()).get();
        // Disconnect from session so that the updates on updatedNghiaTrang are not directly saved in db
        em.detach(updatedNghiaTrang);
        updatedNghiaTrang
            .maNghiaTrang(UPDATED_MA_NGHIA_TRANG)
            .tenNghiaTrang(UPDATED_TEN_NGHIA_TRANG)
            .diaChi(UPDATED_DIA_CHI)
            .nguoiLienHe(UPDATED_NGUOI_LIEN_HE)
            .dienThoai(UPDATED_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restNghiaTrangMockMvc.perform(put("/api/nghia-trangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNghiaTrang)))
            .andExpect(status().isOk());

        // Validate the NghiaTrang in the database
        List<NghiaTrang> nghiaTrangList = nghiaTrangRepository.findAll();
        assertThat(nghiaTrangList).hasSize(databaseSizeBeforeUpdate);
        NghiaTrang testNghiaTrang = nghiaTrangList.get(nghiaTrangList.size() - 1);
        assertThat(testNghiaTrang.getMaNghiaTrang()).isEqualTo(UPDATED_MA_NGHIA_TRANG);
        assertThat(testNghiaTrang.getTenNghiaTrang()).isEqualTo(UPDATED_TEN_NGHIA_TRANG);
        assertThat(testNghiaTrang.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testNghiaTrang.getNguoiLienHe()).isEqualTo(UPDATED_NGUOI_LIEN_HE);
        assertThat(testNghiaTrang.getDienThoai()).isEqualTo(UPDATED_DIEN_THOAI);
        assertThat(testNghiaTrang.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testNghiaTrang.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testNghiaTrang.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testNghiaTrang.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testNghiaTrang.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testNghiaTrang.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the NghiaTrang in Elasticsearch
        verify(mockNghiaTrangSearchRepository, times(1)).save(testNghiaTrang);
    }

    @Test
    @Transactional
    public void updateNonExistingNghiaTrang() throws Exception {
        int databaseSizeBeforeUpdate = nghiaTrangRepository.findAll().size();

        // Create the NghiaTrang

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNghiaTrangMockMvc.perform(put("/api/nghia-trangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nghiaTrang)))
            .andExpect(status().isBadRequest());

        // Validate the NghiaTrang in the database
        List<NghiaTrang> nghiaTrangList = nghiaTrangRepository.findAll();
        assertThat(nghiaTrangList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NghiaTrang in Elasticsearch
        verify(mockNghiaTrangSearchRepository, times(0)).save(nghiaTrang);
    }

    @Test
    @Transactional
    public void deleteNghiaTrang() throws Exception {
        // Initialize the database
        nghiaTrangRepository.saveAndFlush(nghiaTrang);

        int databaseSizeBeforeDelete = nghiaTrangRepository.findAll().size();

        // Get the nghiaTrang
        restNghiaTrangMockMvc.perform(delete("/api/nghia-trangs/{id}", nghiaTrang.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NghiaTrang> nghiaTrangList = nghiaTrangRepository.findAll();
        assertThat(nghiaTrangList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NghiaTrang in Elasticsearch
        verify(mockNghiaTrangSearchRepository, times(1)).deleteById(nghiaTrang.getId());
    }

    @Test
    @Transactional
    public void searchNghiaTrang() throws Exception {
        // Initialize the database
        nghiaTrangRepository.saveAndFlush(nghiaTrang);
        when(mockNghiaTrangSearchRepository.search(queryStringQuery("id:" + nghiaTrang.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(nghiaTrang), PageRequest.of(0, 1), 1));
        // Search the nghiaTrang
        restNghiaTrangMockMvc.perform(get("/api/_search/nghia-trangs?query=id:" + nghiaTrang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nghiaTrang.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNghiaTrang").value(hasItem(DEFAULT_MA_NGHIA_TRANG)))
            .andExpect(jsonPath("$.[*].tenNghiaTrang").value(hasItem(DEFAULT_TEN_NGHIA_TRANG)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].nguoiLienHe").value(hasItem(DEFAULT_NGUOI_LIEN_HE)))
            .andExpect(jsonPath("$.[*].dienThoai").value(hasItem(DEFAULT_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NghiaTrang.class);
        NghiaTrang nghiaTrang1 = new NghiaTrang();
        nghiaTrang1.setId(1L);
        NghiaTrang nghiaTrang2 = new NghiaTrang();
        nghiaTrang2.setId(nghiaTrang1.getId());
        assertThat(nghiaTrang1).isEqualTo(nghiaTrang2);
        nghiaTrang2.setId(2L);
        assertThat(nghiaTrang1).isNotEqualTo(nghiaTrang2);
        nghiaTrang1.setId(null);
        assertThat(nghiaTrang1).isNotEqualTo(nghiaTrang2);
    }
}
