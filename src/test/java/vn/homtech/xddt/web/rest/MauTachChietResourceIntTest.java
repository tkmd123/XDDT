package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.MauTachChiet;
import vn.homtech.xddt.repository.MauTachChietRepository;
import vn.homtech.xddt.repository.search.MauTachChietSearchRepository;
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
 * Test class for the MauTachChietResource REST controller.
 *
 * @see MauTachChietResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class MauTachChietResourceIntTest {

    private static final String DEFAULT_DAC_DIEM_MAU = "AAAAAAAAAA";
    private static final String UPDATED_DAC_DIEM_MAU = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_LUONG_SU_DUNG = 1;
    private static final Integer UPDATED_SO_LUONG_SU_DUNG = 2;

    private static final String DEFAULT_NHAN_XET = "AAAAAAAAAA";
    private static final String UPDATED_NHAN_XET = "BBBBBBBBBB";

    private static final String DEFAULT_XU_LY_BE_MAT = "AAAAAAAAAA";
    private static final String UPDATED_XU_LY_BE_MAT = "BBBBBBBBBB";

    private static final Float DEFAULT_KHOI_LUONG_NGHIEN = 1F;
    private static final Float UPDATED_KHOI_LUONG_NGHIEN = 2F;

    private static final Float DEFAULT_KHOI_LUONG_DE_TACH = 1F;
    private static final Float UPDATED_KHOI_LUONG_DE_TACH = 2F;

    private static final Float DEFAULT_KHOI_LUONG_SAU_KHU = 1F;
    private static final Float UPDATED_KHOI_LUONG_SAU_KHU = 2F;

    private static final Float DEFAULT_KHOI_LUONG_SAU_LOC = 1F;
    private static final Float UPDATED_KHOI_LUONG_SAU_LOC = 2F;

    private static final Float DEFAULT_KHOI_LUONG_ADN = 1F;
    private static final Float UPDATED_KHOI_LUONG_ADN = 2F;

    private static final Float DEFAULT_KHOI_LUONG_CHUA_NGHIEN = 1F;
    private static final Float UPDATED_KHOI_LUONG_CHUA_NGHIEN = 2F;

    private static final String DEFAULT_GHI_CHU_TACH = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU_TACH = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU_ADN = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU_ADN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    private static final Float DEFAULT_FLOAT_1 = 1F;
    private static final Float UPDATED_FLOAT_1 = 2F;

    private static final Float DEFAULT_FLOAT_2 = 1F;
    private static final Float UPDATED_FLOAT_2 = 2F;

    private static final Float DEFAULT_FLOAT_3 = 1F;
    private static final Float UPDATED_FLOAT_3 = 2F;

    @Autowired
    private MauTachChietRepository mauTachChietRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.MauTachChietSearchRepositoryMockConfiguration
     */
    @Autowired
    private MauTachChietSearchRepository mockMauTachChietSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMauTachChietMockMvc;

    private MauTachChiet mauTachChiet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MauTachChietResource mauTachChietResource = new MauTachChietResource(mauTachChietRepository, mockMauTachChietSearchRepository);
        this.restMauTachChietMockMvc = MockMvcBuilders.standaloneSetup(mauTachChietResource)
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
    public static MauTachChiet createEntity(EntityManager em) {
        MauTachChiet mauTachChiet = new MauTachChiet()
            .dacDiemMau(DEFAULT_DAC_DIEM_MAU)
            .soLuongSuDung(DEFAULT_SO_LUONG_SU_DUNG)
            .nhanXet(DEFAULT_NHAN_XET)
            .xuLyBeMat(DEFAULT_XU_LY_BE_MAT)
            .khoiLuongNghien(DEFAULT_KHOI_LUONG_NGHIEN)
            .khoiLuongDeTach(DEFAULT_KHOI_LUONG_DE_TACH)
            .khoiLuongSauKhu(DEFAULT_KHOI_LUONG_SAU_KHU)
            .khoiLuongSauLoc(DEFAULT_KHOI_LUONG_SAU_LOC)
            .khoiLuongADN(DEFAULT_KHOI_LUONG_ADN)
            .khoiLuongChuaNghien(DEFAULT_KHOI_LUONG_CHUA_NGHIEN)
            .ghiChuTach(DEFAULT_GHI_CHU_TACH)
            .ghiChuADN(DEFAULT_GHI_CHU_ADN)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3)
            .float1(DEFAULT_FLOAT_1)
            .float2(DEFAULT_FLOAT_2)
            .float3(DEFAULT_FLOAT_3);
        return mauTachChiet;
    }

    @Before
    public void initTest() {
        mauTachChiet = createEntity(em);
    }

    @Test
    @Transactional
    public void createMauTachChiet() throws Exception {
        int databaseSizeBeforeCreate = mauTachChietRepository.findAll().size();

        // Create the MauTachChiet
        restMauTachChietMockMvc.perform(post("/api/mau-tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauTachChiet)))
            .andExpect(status().isCreated());

        // Validate the MauTachChiet in the database
        List<MauTachChiet> mauTachChietList = mauTachChietRepository.findAll();
        assertThat(mauTachChietList).hasSize(databaseSizeBeforeCreate + 1);
        MauTachChiet testMauTachChiet = mauTachChietList.get(mauTachChietList.size() - 1);
        assertThat(testMauTachChiet.getDacDiemMau()).isEqualTo(DEFAULT_DAC_DIEM_MAU);
        assertThat(testMauTachChiet.getSoLuongSuDung()).isEqualTo(DEFAULT_SO_LUONG_SU_DUNG);
        assertThat(testMauTachChiet.getNhanXet()).isEqualTo(DEFAULT_NHAN_XET);
        assertThat(testMauTachChiet.getXuLyBeMat()).isEqualTo(DEFAULT_XU_LY_BE_MAT);
        assertThat(testMauTachChiet.getKhoiLuongNghien()).isEqualTo(DEFAULT_KHOI_LUONG_NGHIEN);
        assertThat(testMauTachChiet.getKhoiLuongDeTach()).isEqualTo(DEFAULT_KHOI_LUONG_DE_TACH);
        assertThat(testMauTachChiet.getKhoiLuongSauKhu()).isEqualTo(DEFAULT_KHOI_LUONG_SAU_KHU);
        assertThat(testMauTachChiet.getKhoiLuongSauLoc()).isEqualTo(DEFAULT_KHOI_LUONG_SAU_LOC);
        assertThat(testMauTachChiet.getKhoiLuongADN()).isEqualTo(DEFAULT_KHOI_LUONG_ADN);
        assertThat(testMauTachChiet.getKhoiLuongChuaNghien()).isEqualTo(DEFAULT_KHOI_LUONG_CHUA_NGHIEN);
        assertThat(testMauTachChiet.getGhiChuTach()).isEqualTo(DEFAULT_GHI_CHU_TACH);
        assertThat(testMauTachChiet.getGhiChuADN()).isEqualTo(DEFAULT_GHI_CHU_ADN);
        assertThat(testMauTachChiet.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testMauTachChiet.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testMauTachChiet.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testMauTachChiet.getUdf3()).isEqualTo(DEFAULT_UDF_3);
        assertThat(testMauTachChiet.getFloat1()).isEqualTo(DEFAULT_FLOAT_1);
        assertThat(testMauTachChiet.getFloat2()).isEqualTo(DEFAULT_FLOAT_2);
        assertThat(testMauTachChiet.getFloat3()).isEqualTo(DEFAULT_FLOAT_3);

        // Validate the MauTachChiet in Elasticsearch
        verify(mockMauTachChietSearchRepository, times(1)).save(testMauTachChiet);
    }

    @Test
    @Transactional
    public void createMauTachChietWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mauTachChietRepository.findAll().size();

        // Create the MauTachChiet with an existing ID
        mauTachChiet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMauTachChietMockMvc.perform(post("/api/mau-tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauTachChiet)))
            .andExpect(status().isBadRequest());

        // Validate the MauTachChiet in the database
        List<MauTachChiet> mauTachChietList = mauTachChietRepository.findAll();
        assertThat(mauTachChietList).hasSize(databaseSizeBeforeCreate);

        // Validate the MauTachChiet in Elasticsearch
        verify(mockMauTachChietSearchRepository, times(0)).save(mauTachChiet);
    }

    @Test
    @Transactional
    public void getAllMauTachChiets() throws Exception {
        // Initialize the database
        mauTachChietRepository.saveAndFlush(mauTachChiet);

        // Get all the mauTachChietList
        restMauTachChietMockMvc.perform(get("/api/mau-tach-chiets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mauTachChiet.getId().intValue())))
            .andExpect(jsonPath("$.[*].dacDiemMau").value(hasItem(DEFAULT_DAC_DIEM_MAU.toString())))
            .andExpect(jsonPath("$.[*].soLuongSuDung").value(hasItem(DEFAULT_SO_LUONG_SU_DUNG)))
            .andExpect(jsonPath("$.[*].nhanXet").value(hasItem(DEFAULT_NHAN_XET.toString())))
            .andExpect(jsonPath("$.[*].xuLyBeMat").value(hasItem(DEFAULT_XU_LY_BE_MAT.toString())))
            .andExpect(jsonPath("$.[*].khoiLuongNghien").value(hasItem(DEFAULT_KHOI_LUONG_NGHIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongDeTach").value(hasItem(DEFAULT_KHOI_LUONG_DE_TACH.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongSauKhu").value(hasItem(DEFAULT_KHOI_LUONG_SAU_KHU.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongSauLoc").value(hasItem(DEFAULT_KHOI_LUONG_SAU_LOC.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongADN").value(hasItem(DEFAULT_KHOI_LUONG_ADN.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongChuaNghien").value(hasItem(DEFAULT_KHOI_LUONG_CHUA_NGHIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].ghiChuTach").value(hasItem(DEFAULT_GHI_CHU_TACH.toString())))
            .andExpect(jsonPath("$.[*].ghiChuADN").value(hasItem(DEFAULT_GHI_CHU_ADN.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].float1").value(hasItem(DEFAULT_FLOAT_1.doubleValue())))
            .andExpect(jsonPath("$.[*].float2").value(hasItem(DEFAULT_FLOAT_2.doubleValue())))
            .andExpect(jsonPath("$.[*].float3").value(hasItem(DEFAULT_FLOAT_3.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getMauTachChiet() throws Exception {
        // Initialize the database
        mauTachChietRepository.saveAndFlush(mauTachChiet);

        // Get the mauTachChiet
        restMauTachChietMockMvc.perform(get("/api/mau-tach-chiets/{id}", mauTachChiet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mauTachChiet.getId().intValue()))
            .andExpect(jsonPath("$.dacDiemMau").value(DEFAULT_DAC_DIEM_MAU.toString()))
            .andExpect(jsonPath("$.soLuongSuDung").value(DEFAULT_SO_LUONG_SU_DUNG))
            .andExpect(jsonPath("$.nhanXet").value(DEFAULT_NHAN_XET.toString()))
            .andExpect(jsonPath("$.xuLyBeMat").value(DEFAULT_XU_LY_BE_MAT.toString()))
            .andExpect(jsonPath("$.khoiLuongNghien").value(DEFAULT_KHOI_LUONG_NGHIEN.doubleValue()))
            .andExpect(jsonPath("$.khoiLuongDeTach").value(DEFAULT_KHOI_LUONG_DE_TACH.doubleValue()))
            .andExpect(jsonPath("$.khoiLuongSauKhu").value(DEFAULT_KHOI_LUONG_SAU_KHU.doubleValue()))
            .andExpect(jsonPath("$.khoiLuongSauLoc").value(DEFAULT_KHOI_LUONG_SAU_LOC.doubleValue()))
            .andExpect(jsonPath("$.khoiLuongADN").value(DEFAULT_KHOI_LUONG_ADN.doubleValue()))
            .andExpect(jsonPath("$.khoiLuongChuaNghien").value(DEFAULT_KHOI_LUONG_CHUA_NGHIEN.doubleValue()))
            .andExpect(jsonPath("$.ghiChuTach").value(DEFAULT_GHI_CHU_TACH.toString()))
            .andExpect(jsonPath("$.ghiChuADN").value(DEFAULT_GHI_CHU_ADN.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()))
            .andExpect(jsonPath("$.float1").value(DEFAULT_FLOAT_1.doubleValue()))
            .andExpect(jsonPath("$.float2").value(DEFAULT_FLOAT_2.doubleValue()))
            .andExpect(jsonPath("$.float3").value(DEFAULT_FLOAT_3.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMauTachChiet() throws Exception {
        // Get the mauTachChiet
        restMauTachChietMockMvc.perform(get("/api/mau-tach-chiets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMauTachChiet() throws Exception {
        // Initialize the database
        mauTachChietRepository.saveAndFlush(mauTachChiet);

        int databaseSizeBeforeUpdate = mauTachChietRepository.findAll().size();

        // Update the mauTachChiet
        MauTachChiet updatedMauTachChiet = mauTachChietRepository.findById(mauTachChiet.getId()).get();
        // Disconnect from session so that the updates on updatedMauTachChiet are not directly saved in db
        em.detach(updatedMauTachChiet);
        updatedMauTachChiet
            .dacDiemMau(UPDATED_DAC_DIEM_MAU)
            .soLuongSuDung(UPDATED_SO_LUONG_SU_DUNG)
            .nhanXet(UPDATED_NHAN_XET)
            .xuLyBeMat(UPDATED_XU_LY_BE_MAT)
            .khoiLuongNghien(UPDATED_KHOI_LUONG_NGHIEN)
            .khoiLuongDeTach(UPDATED_KHOI_LUONG_DE_TACH)
            .khoiLuongSauKhu(UPDATED_KHOI_LUONG_SAU_KHU)
            .khoiLuongSauLoc(UPDATED_KHOI_LUONG_SAU_LOC)
            .khoiLuongADN(UPDATED_KHOI_LUONG_ADN)
            .khoiLuongChuaNghien(UPDATED_KHOI_LUONG_CHUA_NGHIEN)
            .ghiChuTach(UPDATED_GHI_CHU_TACH)
            .ghiChuADN(UPDATED_GHI_CHU_ADN)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3)
            .float1(UPDATED_FLOAT_1)
            .float2(UPDATED_FLOAT_2)
            .float3(UPDATED_FLOAT_3);

        restMauTachChietMockMvc.perform(put("/api/mau-tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMauTachChiet)))
            .andExpect(status().isOk());

        // Validate the MauTachChiet in the database
        List<MauTachChiet> mauTachChietList = mauTachChietRepository.findAll();
        assertThat(mauTachChietList).hasSize(databaseSizeBeforeUpdate);
        MauTachChiet testMauTachChiet = mauTachChietList.get(mauTachChietList.size() - 1);
        assertThat(testMauTachChiet.getDacDiemMau()).isEqualTo(UPDATED_DAC_DIEM_MAU);
        assertThat(testMauTachChiet.getSoLuongSuDung()).isEqualTo(UPDATED_SO_LUONG_SU_DUNG);
        assertThat(testMauTachChiet.getNhanXet()).isEqualTo(UPDATED_NHAN_XET);
        assertThat(testMauTachChiet.getXuLyBeMat()).isEqualTo(UPDATED_XU_LY_BE_MAT);
        assertThat(testMauTachChiet.getKhoiLuongNghien()).isEqualTo(UPDATED_KHOI_LUONG_NGHIEN);
        assertThat(testMauTachChiet.getKhoiLuongDeTach()).isEqualTo(UPDATED_KHOI_LUONG_DE_TACH);
        assertThat(testMauTachChiet.getKhoiLuongSauKhu()).isEqualTo(UPDATED_KHOI_LUONG_SAU_KHU);
        assertThat(testMauTachChiet.getKhoiLuongSauLoc()).isEqualTo(UPDATED_KHOI_LUONG_SAU_LOC);
        assertThat(testMauTachChiet.getKhoiLuongADN()).isEqualTo(UPDATED_KHOI_LUONG_ADN);
        assertThat(testMauTachChiet.getKhoiLuongChuaNghien()).isEqualTo(UPDATED_KHOI_LUONG_CHUA_NGHIEN);
        assertThat(testMauTachChiet.getGhiChuTach()).isEqualTo(UPDATED_GHI_CHU_TACH);
        assertThat(testMauTachChiet.getGhiChuADN()).isEqualTo(UPDATED_GHI_CHU_ADN);
        assertThat(testMauTachChiet.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testMauTachChiet.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testMauTachChiet.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testMauTachChiet.getUdf3()).isEqualTo(UPDATED_UDF_3);
        assertThat(testMauTachChiet.getFloat1()).isEqualTo(UPDATED_FLOAT_1);
        assertThat(testMauTachChiet.getFloat2()).isEqualTo(UPDATED_FLOAT_2);
        assertThat(testMauTachChiet.getFloat3()).isEqualTo(UPDATED_FLOAT_3);

        // Validate the MauTachChiet in Elasticsearch
        verify(mockMauTachChietSearchRepository, times(1)).save(testMauTachChiet);
    }

    @Test
    @Transactional
    public void updateNonExistingMauTachChiet() throws Exception {
        int databaseSizeBeforeUpdate = mauTachChietRepository.findAll().size();

        // Create the MauTachChiet

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMauTachChietMockMvc.perform(put("/api/mau-tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauTachChiet)))
            .andExpect(status().isBadRequest());

        // Validate the MauTachChiet in the database
        List<MauTachChiet> mauTachChietList = mauTachChietRepository.findAll();
        assertThat(mauTachChietList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MauTachChiet in Elasticsearch
        verify(mockMauTachChietSearchRepository, times(0)).save(mauTachChiet);
    }

    @Test
    @Transactional
    public void deleteMauTachChiet() throws Exception {
        // Initialize the database
        mauTachChietRepository.saveAndFlush(mauTachChiet);

        int databaseSizeBeforeDelete = mauTachChietRepository.findAll().size();

        // Get the mauTachChiet
        restMauTachChietMockMvc.perform(delete("/api/mau-tach-chiets/{id}", mauTachChiet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MauTachChiet> mauTachChietList = mauTachChietRepository.findAll();
        assertThat(mauTachChietList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MauTachChiet in Elasticsearch
        verify(mockMauTachChietSearchRepository, times(1)).deleteById(mauTachChiet.getId());
    }

    @Test
    @Transactional
    public void searchMauTachChiet() throws Exception {
        // Initialize the database
        mauTachChietRepository.saveAndFlush(mauTachChiet);
        when(mockMauTachChietSearchRepository.search(queryStringQuery("id:" + mauTachChiet.getId())))
            .thenReturn(Collections.singletonList(mauTachChiet));
        // Search the mauTachChiet
        restMauTachChietMockMvc.perform(get("/api/_search/mau-tach-chiets?query=id:" + mauTachChiet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mauTachChiet.getId().intValue())))
            .andExpect(jsonPath("$.[*].dacDiemMau").value(hasItem(DEFAULT_DAC_DIEM_MAU)))
            .andExpect(jsonPath("$.[*].soLuongSuDung").value(hasItem(DEFAULT_SO_LUONG_SU_DUNG)))
            .andExpect(jsonPath("$.[*].nhanXet").value(hasItem(DEFAULT_NHAN_XET)))
            .andExpect(jsonPath("$.[*].xuLyBeMat").value(hasItem(DEFAULT_XU_LY_BE_MAT)))
            .andExpect(jsonPath("$.[*].khoiLuongNghien").value(hasItem(DEFAULT_KHOI_LUONG_NGHIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongDeTach").value(hasItem(DEFAULT_KHOI_LUONG_DE_TACH.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongSauKhu").value(hasItem(DEFAULT_KHOI_LUONG_SAU_KHU.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongSauLoc").value(hasItem(DEFAULT_KHOI_LUONG_SAU_LOC.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongADN").value(hasItem(DEFAULT_KHOI_LUONG_ADN.doubleValue())))
            .andExpect(jsonPath("$.[*].khoiLuongChuaNghien").value(hasItem(DEFAULT_KHOI_LUONG_CHUA_NGHIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].ghiChuTach").value(hasItem(DEFAULT_GHI_CHU_TACH)))
            .andExpect(jsonPath("$.[*].ghiChuADN").value(hasItem(DEFAULT_GHI_CHU_ADN)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)))
            .andExpect(jsonPath("$.[*].float1").value(hasItem(DEFAULT_FLOAT_1.doubleValue())))
            .andExpect(jsonPath("$.[*].float2").value(hasItem(DEFAULT_FLOAT_2.doubleValue())))
            .andExpect(jsonPath("$.[*].float3").value(hasItem(DEFAULT_FLOAT_3.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MauTachChiet.class);
        MauTachChiet mauTachChiet1 = new MauTachChiet();
        mauTachChiet1.setId(1L);
        MauTachChiet mauTachChiet2 = new MauTachChiet();
        mauTachChiet2.setId(mauTachChiet1.getId());
        assertThat(mauTachChiet1).isEqualTo(mauTachChiet2);
        mauTachChiet2.setId(2L);
        assertThat(mauTachChiet1).isNotEqualTo(mauTachChiet2);
        mauTachChiet1.setId(null);
        assertThat(mauTachChiet1).isNotEqualTo(mauTachChiet2);
    }
}
