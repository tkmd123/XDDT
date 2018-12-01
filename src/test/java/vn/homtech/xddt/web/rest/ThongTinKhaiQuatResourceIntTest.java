package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.ThongTinKhaiQuat;
import vn.homtech.xddt.repository.ThongTinKhaiQuatRepository;
import vn.homtech.xddt.repository.search.ThongTinKhaiQuatSearchRepository;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Test class for the ThongTinKhaiQuatResource REST controller.
 *
 * @see ThongTinKhaiQuatResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class ThongTinKhaiQuatResourceIntTest {

    private static final String DEFAULT_MA_KHAI_QUAT = "AAAAAAAAAA";
    private static final String UPDATED_MA_KHAI_QUAT = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_KHAI_QUAT = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_KHAI_QUAT = "BBBBBBBBBB";

    private static final String DEFAULT_DON_VI_KHAI_QUAT = "AAAAAAAAAA";
    private static final String UPDATED_DON_VI_KHAI_QUAT = "BBBBBBBBBB";

    private static final Instant DEFAULT_THOI_GIAN_KHAI_QUAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_THOI_GIAN_KHAI_QUAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_CO_HAI_COT = false;
    private static final Boolean UPDATED_CO_HAI_COT = true;

    private static final Integer DEFAULT_SO_LUONG_HAI_COT = 1;
    private static final Integer UPDATED_SO_LUONG_HAI_COT = 2;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private ThongTinKhaiQuatRepository thongTinKhaiQuatRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.ThongTinKhaiQuatSearchRepositoryMockConfiguration
     */
    @Autowired
    private ThongTinKhaiQuatSearchRepository mockThongTinKhaiQuatSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restThongTinKhaiQuatMockMvc;

    private ThongTinKhaiQuat thongTinKhaiQuat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThongTinKhaiQuatResource thongTinKhaiQuatResource = new ThongTinKhaiQuatResource(thongTinKhaiQuatRepository, mockThongTinKhaiQuatSearchRepository);
        this.restThongTinKhaiQuatMockMvc = MockMvcBuilders.standaloneSetup(thongTinKhaiQuatResource)
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
    public static ThongTinKhaiQuat createEntity(EntityManager em) {
        ThongTinKhaiQuat thongTinKhaiQuat = new ThongTinKhaiQuat()
            .maKhaiQuat(DEFAULT_MA_KHAI_QUAT)
            .nguoiKhaiQuat(DEFAULT_NGUOI_KHAI_QUAT)
            .donViKhaiQuat(DEFAULT_DON_VI_KHAI_QUAT)
            .thoiGianKhaiQuat(DEFAULT_THOI_GIAN_KHAI_QUAT)
            .coHaiCot(DEFAULT_CO_HAI_COT)
            .soLuongHaiCot(DEFAULT_SO_LUONG_HAI_COT)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return thongTinKhaiQuat;
    }

    @Before
    public void initTest() {
        thongTinKhaiQuat = createEntity(em);
    }

    @Test
    @Transactional
    public void createThongTinKhaiQuat() throws Exception {
        int databaseSizeBeforeCreate = thongTinKhaiQuatRepository.findAll().size();

        // Create the ThongTinKhaiQuat
        restThongTinKhaiQuatMockMvc.perform(post("/api/thong-tin-khai-quats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongTinKhaiQuat)))
            .andExpect(status().isCreated());

        // Validate the ThongTinKhaiQuat in the database
        List<ThongTinKhaiQuat> thongTinKhaiQuatList = thongTinKhaiQuatRepository.findAll();
        assertThat(thongTinKhaiQuatList).hasSize(databaseSizeBeforeCreate + 1);
        ThongTinKhaiQuat testThongTinKhaiQuat = thongTinKhaiQuatList.get(thongTinKhaiQuatList.size() - 1);
        assertThat(testThongTinKhaiQuat.getMaKhaiQuat()).isEqualTo(DEFAULT_MA_KHAI_QUAT);
        assertThat(testThongTinKhaiQuat.getNguoiKhaiQuat()).isEqualTo(DEFAULT_NGUOI_KHAI_QUAT);
        assertThat(testThongTinKhaiQuat.getDonViKhaiQuat()).isEqualTo(DEFAULT_DON_VI_KHAI_QUAT);
        assertThat(testThongTinKhaiQuat.getThoiGianKhaiQuat()).isEqualTo(DEFAULT_THOI_GIAN_KHAI_QUAT);
        assertThat(testThongTinKhaiQuat.isCoHaiCot()).isEqualTo(DEFAULT_CO_HAI_COT);
        assertThat(testThongTinKhaiQuat.getSoLuongHaiCot()).isEqualTo(DEFAULT_SO_LUONG_HAI_COT);
        assertThat(testThongTinKhaiQuat.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testThongTinKhaiQuat.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testThongTinKhaiQuat.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testThongTinKhaiQuat.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the ThongTinKhaiQuat in Elasticsearch
        verify(mockThongTinKhaiQuatSearchRepository, times(1)).save(testThongTinKhaiQuat);
    }

    @Test
    @Transactional
    public void createThongTinKhaiQuatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thongTinKhaiQuatRepository.findAll().size();

        // Create the ThongTinKhaiQuat with an existing ID
        thongTinKhaiQuat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongTinKhaiQuatMockMvc.perform(post("/api/thong-tin-khai-quats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongTinKhaiQuat)))
            .andExpect(status().isBadRequest());

        // Validate the ThongTinKhaiQuat in the database
        List<ThongTinKhaiQuat> thongTinKhaiQuatList = thongTinKhaiQuatRepository.findAll();
        assertThat(thongTinKhaiQuatList).hasSize(databaseSizeBeforeCreate);

        // Validate the ThongTinKhaiQuat in Elasticsearch
        verify(mockThongTinKhaiQuatSearchRepository, times(0)).save(thongTinKhaiQuat);
    }

    @Test
    @Transactional
    public void getAllThongTinKhaiQuats() throws Exception {
        // Initialize the database
        thongTinKhaiQuatRepository.saveAndFlush(thongTinKhaiQuat);

        // Get all the thongTinKhaiQuatList
        restThongTinKhaiQuatMockMvc.perform(get("/api/thong-tin-khai-quats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongTinKhaiQuat.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKhaiQuat").value(hasItem(DEFAULT_MA_KHAI_QUAT.toString())))
            .andExpect(jsonPath("$.[*].nguoiKhaiQuat").value(hasItem(DEFAULT_NGUOI_KHAI_QUAT.toString())))
            .andExpect(jsonPath("$.[*].donViKhaiQuat").value(hasItem(DEFAULT_DON_VI_KHAI_QUAT.toString())))
            .andExpect(jsonPath("$.[*].thoiGianKhaiQuat").value(hasItem(DEFAULT_THOI_GIAN_KHAI_QUAT.toString())))
            .andExpect(jsonPath("$.[*].coHaiCot").value(hasItem(DEFAULT_CO_HAI_COT.booleanValue())))
            .andExpect(jsonPath("$.[*].soLuongHaiCot").value(hasItem(DEFAULT_SO_LUONG_HAI_COT)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getThongTinKhaiQuat() throws Exception {
        // Initialize the database
        thongTinKhaiQuatRepository.saveAndFlush(thongTinKhaiQuat);

        // Get the thongTinKhaiQuat
        restThongTinKhaiQuatMockMvc.perform(get("/api/thong-tin-khai-quats/{id}", thongTinKhaiQuat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thongTinKhaiQuat.getId().intValue()))
            .andExpect(jsonPath("$.maKhaiQuat").value(DEFAULT_MA_KHAI_QUAT.toString()))
            .andExpect(jsonPath("$.nguoiKhaiQuat").value(DEFAULT_NGUOI_KHAI_QUAT.toString()))
            .andExpect(jsonPath("$.donViKhaiQuat").value(DEFAULT_DON_VI_KHAI_QUAT.toString()))
            .andExpect(jsonPath("$.thoiGianKhaiQuat").value(DEFAULT_THOI_GIAN_KHAI_QUAT.toString()))
            .andExpect(jsonPath("$.coHaiCot").value(DEFAULT_CO_HAI_COT.booleanValue()))
            .andExpect(jsonPath("$.soLuongHaiCot").value(DEFAULT_SO_LUONG_HAI_COT))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingThongTinKhaiQuat() throws Exception {
        // Get the thongTinKhaiQuat
        restThongTinKhaiQuatMockMvc.perform(get("/api/thong-tin-khai-quats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThongTinKhaiQuat() throws Exception {
        // Initialize the database
        thongTinKhaiQuatRepository.saveAndFlush(thongTinKhaiQuat);

        int databaseSizeBeforeUpdate = thongTinKhaiQuatRepository.findAll().size();

        // Update the thongTinKhaiQuat
        ThongTinKhaiQuat updatedThongTinKhaiQuat = thongTinKhaiQuatRepository.findById(thongTinKhaiQuat.getId()).get();
        // Disconnect from session so that the updates on updatedThongTinKhaiQuat are not directly saved in db
        em.detach(updatedThongTinKhaiQuat);
        updatedThongTinKhaiQuat
            .maKhaiQuat(UPDATED_MA_KHAI_QUAT)
            .nguoiKhaiQuat(UPDATED_NGUOI_KHAI_QUAT)
            .donViKhaiQuat(UPDATED_DON_VI_KHAI_QUAT)
            .thoiGianKhaiQuat(UPDATED_THOI_GIAN_KHAI_QUAT)
            .coHaiCot(UPDATED_CO_HAI_COT)
            .soLuongHaiCot(UPDATED_SO_LUONG_HAI_COT)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restThongTinKhaiQuatMockMvc.perform(put("/api/thong-tin-khai-quats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedThongTinKhaiQuat)))
            .andExpect(status().isOk());

        // Validate the ThongTinKhaiQuat in the database
        List<ThongTinKhaiQuat> thongTinKhaiQuatList = thongTinKhaiQuatRepository.findAll();
        assertThat(thongTinKhaiQuatList).hasSize(databaseSizeBeforeUpdate);
        ThongTinKhaiQuat testThongTinKhaiQuat = thongTinKhaiQuatList.get(thongTinKhaiQuatList.size() - 1);
        assertThat(testThongTinKhaiQuat.getMaKhaiQuat()).isEqualTo(UPDATED_MA_KHAI_QUAT);
        assertThat(testThongTinKhaiQuat.getNguoiKhaiQuat()).isEqualTo(UPDATED_NGUOI_KHAI_QUAT);
        assertThat(testThongTinKhaiQuat.getDonViKhaiQuat()).isEqualTo(UPDATED_DON_VI_KHAI_QUAT);
        assertThat(testThongTinKhaiQuat.getThoiGianKhaiQuat()).isEqualTo(UPDATED_THOI_GIAN_KHAI_QUAT);
        assertThat(testThongTinKhaiQuat.isCoHaiCot()).isEqualTo(UPDATED_CO_HAI_COT);
        assertThat(testThongTinKhaiQuat.getSoLuongHaiCot()).isEqualTo(UPDATED_SO_LUONG_HAI_COT);
        assertThat(testThongTinKhaiQuat.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testThongTinKhaiQuat.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testThongTinKhaiQuat.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testThongTinKhaiQuat.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the ThongTinKhaiQuat in Elasticsearch
        verify(mockThongTinKhaiQuatSearchRepository, times(1)).save(testThongTinKhaiQuat);
    }

    @Test
    @Transactional
    public void updateNonExistingThongTinKhaiQuat() throws Exception {
        int databaseSizeBeforeUpdate = thongTinKhaiQuatRepository.findAll().size();

        // Create the ThongTinKhaiQuat

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongTinKhaiQuatMockMvc.perform(put("/api/thong-tin-khai-quats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongTinKhaiQuat)))
            .andExpect(status().isBadRequest());

        // Validate the ThongTinKhaiQuat in the database
        List<ThongTinKhaiQuat> thongTinKhaiQuatList = thongTinKhaiQuatRepository.findAll();
        assertThat(thongTinKhaiQuatList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ThongTinKhaiQuat in Elasticsearch
        verify(mockThongTinKhaiQuatSearchRepository, times(0)).save(thongTinKhaiQuat);
    }

    @Test
    @Transactional
    public void deleteThongTinKhaiQuat() throws Exception {
        // Initialize the database
        thongTinKhaiQuatRepository.saveAndFlush(thongTinKhaiQuat);

        int databaseSizeBeforeDelete = thongTinKhaiQuatRepository.findAll().size();

        // Get the thongTinKhaiQuat
        restThongTinKhaiQuatMockMvc.perform(delete("/api/thong-tin-khai-quats/{id}", thongTinKhaiQuat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ThongTinKhaiQuat> thongTinKhaiQuatList = thongTinKhaiQuatRepository.findAll();
        assertThat(thongTinKhaiQuatList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ThongTinKhaiQuat in Elasticsearch
        verify(mockThongTinKhaiQuatSearchRepository, times(1)).deleteById(thongTinKhaiQuat.getId());
    }

    @Test
    @Transactional
    public void searchThongTinKhaiQuat() throws Exception {
        // Initialize the database
        thongTinKhaiQuatRepository.saveAndFlush(thongTinKhaiQuat);
        when(mockThongTinKhaiQuatSearchRepository.search(queryStringQuery("id:" + thongTinKhaiQuat.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(thongTinKhaiQuat), PageRequest.of(0, 1), 1));
        // Search the thongTinKhaiQuat
        restThongTinKhaiQuatMockMvc.perform(get("/api/_search/thong-tin-khai-quats?query=id:" + thongTinKhaiQuat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongTinKhaiQuat.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKhaiQuat").value(hasItem(DEFAULT_MA_KHAI_QUAT)))
            .andExpect(jsonPath("$.[*].nguoiKhaiQuat").value(hasItem(DEFAULT_NGUOI_KHAI_QUAT)))
            .andExpect(jsonPath("$.[*].donViKhaiQuat").value(hasItem(DEFAULT_DON_VI_KHAI_QUAT)))
            .andExpect(jsonPath("$.[*].thoiGianKhaiQuat").value(hasItem(DEFAULT_THOI_GIAN_KHAI_QUAT.toString())))
            .andExpect(jsonPath("$.[*].coHaiCot").value(hasItem(DEFAULT_CO_HAI_COT.booleanValue())))
            .andExpect(jsonPath("$.[*].soLuongHaiCot").value(hasItem(DEFAULT_SO_LUONG_HAI_COT)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongTinKhaiQuat.class);
        ThongTinKhaiQuat thongTinKhaiQuat1 = new ThongTinKhaiQuat();
        thongTinKhaiQuat1.setId(1L);
        ThongTinKhaiQuat thongTinKhaiQuat2 = new ThongTinKhaiQuat();
        thongTinKhaiQuat2.setId(thongTinKhaiQuat1.getId());
        assertThat(thongTinKhaiQuat1).isEqualTo(thongTinKhaiQuat2);
        thongTinKhaiQuat2.setId(2L);
        assertThat(thongTinKhaiQuat1).isNotEqualTo(thongTinKhaiQuat2);
        thongTinKhaiQuat1.setId(null);
        assertThat(thongTinKhaiQuat1).isNotEqualTo(thongTinKhaiQuat2);
    }
}
