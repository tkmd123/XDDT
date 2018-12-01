package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.MauXetNghiem;
import vn.homtech.xddt.repository.MauXetNghiemRepository;
import vn.homtech.xddt.repository.search.MauXetNghiemSearchRepository;
import vn.homtech.xddt.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static vn.homtech.xddt.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import vn.homtech.xddt.domain.enumeration.TrangThaiMau;
/**
 * Test class for the MauXetNghiemResource REST controller.
 *
 * @see MauXetNghiemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class MauXetNghiemResourceIntTest {

    private static final String DEFAULT_MA_MAU_XET_NGHIEM = "AAAAAAAAAA";
    private static final String UPDATED_MA_MAU_XET_NGHIEM = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_LAY_MAU = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_LAY_MAU = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NGUOI_LAY_MAU = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_LAY_MAU = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_TIEP_NHAN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_TIEP_NHAN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SO_LUONG_MAU = 1;
    private static final Integer UPDATED_SO_LUONG_MAU = 2;

    private static final TrangThaiMau DEFAULT_TRANG_THAI_MAU = TrangThaiMau.MOI;
    private static final TrangThaiMau UPDATED_TRANG_THAI_MAU = TrangThaiMau.DATACHCHIET;

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_GOC = "AAAAAAAAAA";
    private static final String UPDATED_FILE_GOC = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_FILE_KET_QUA = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_DOT_BIEN = "AAAAAAAAAA";
    private static final String UPDATED_FILE_DOT_BIEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_4 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_4 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_5 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_5 = "BBBBBBBBBB";

    @Autowired
    private MauXetNghiemRepository mauXetNghiemRepository;

    @Mock
    private MauXetNghiemRepository mauXetNghiemRepositoryMock;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.MauXetNghiemSearchRepositoryMockConfiguration
     */
    @Autowired
    private MauXetNghiemSearchRepository mockMauXetNghiemSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMauXetNghiemMockMvc;

    private MauXetNghiem mauXetNghiem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MauXetNghiemResource mauXetNghiemResource = new MauXetNghiemResource(mauXetNghiemRepository, mockMauXetNghiemSearchRepository);
        this.restMauXetNghiemMockMvc = MockMvcBuilders.standaloneSetup(mauXetNghiemResource)
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
    public static MauXetNghiem createEntity(EntityManager em) {
        MauXetNghiem mauXetNghiem = new MauXetNghiem()
            .maMauXetNghiem(DEFAULT_MA_MAU_XET_NGHIEM)
            .ngayLayMau(DEFAULT_NGAY_LAY_MAU)
            .nguoiLayMau(DEFAULT_NGUOI_LAY_MAU)
            .ngayTiepNhan(DEFAULT_NGAY_TIEP_NHAN)
            .soLuongMau(DEFAULT_SO_LUONG_MAU)
            .trangThaiMau(DEFAULT_TRANG_THAI_MAU)
            .moTa(DEFAULT_MO_TA)
            .ghiChu(DEFAULT_GHI_CHU)
            .fileGoc(DEFAULT_FILE_GOC)
            .fileKetQua(DEFAULT_FILE_KET_QUA)
            .fileDotBien(DEFAULT_FILE_DOT_BIEN)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3)
            .udf4(DEFAULT_UDF_4)
            .udf5(DEFAULT_UDF_5);
        return mauXetNghiem;
    }

    @Before
    public void initTest() {
        mauXetNghiem = createEntity(em);
    }

    @Test
    @Transactional
    public void createMauXetNghiem() throws Exception {
        int databaseSizeBeforeCreate = mauXetNghiemRepository.findAll().size();

        // Create the MauXetNghiem
        restMauXetNghiemMockMvc.perform(post("/api/mau-xet-nghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauXetNghiem)))
            .andExpect(status().isCreated());

        // Validate the MauXetNghiem in the database
        List<MauXetNghiem> mauXetNghiemList = mauXetNghiemRepository.findAll();
        assertThat(mauXetNghiemList).hasSize(databaseSizeBeforeCreate + 1);
        MauXetNghiem testMauXetNghiem = mauXetNghiemList.get(mauXetNghiemList.size() - 1);
        assertThat(testMauXetNghiem.getMaMauXetNghiem()).isEqualTo(DEFAULT_MA_MAU_XET_NGHIEM);
        assertThat(testMauXetNghiem.getNgayLayMau()).isEqualTo(DEFAULT_NGAY_LAY_MAU);
        assertThat(testMauXetNghiem.getNguoiLayMau()).isEqualTo(DEFAULT_NGUOI_LAY_MAU);
        assertThat(testMauXetNghiem.getNgayTiepNhan()).isEqualTo(DEFAULT_NGAY_TIEP_NHAN);
        assertThat(testMauXetNghiem.getSoLuongMau()).isEqualTo(DEFAULT_SO_LUONG_MAU);
        assertThat(testMauXetNghiem.getTrangThaiMau()).isEqualTo(DEFAULT_TRANG_THAI_MAU);
        assertThat(testMauXetNghiem.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testMauXetNghiem.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testMauXetNghiem.getFileGoc()).isEqualTo(DEFAULT_FILE_GOC);
        assertThat(testMauXetNghiem.getFileKetQua()).isEqualTo(DEFAULT_FILE_KET_QUA);
        assertThat(testMauXetNghiem.getFileDotBien()).isEqualTo(DEFAULT_FILE_DOT_BIEN);
        assertThat(testMauXetNghiem.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testMauXetNghiem.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testMauXetNghiem.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testMauXetNghiem.getUdf3()).isEqualTo(DEFAULT_UDF_3);
        assertThat(testMauXetNghiem.getUdf4()).isEqualTo(DEFAULT_UDF_4);
        assertThat(testMauXetNghiem.getUdf5()).isEqualTo(DEFAULT_UDF_5);

        // Validate the MauXetNghiem in Elasticsearch
        verify(mockMauXetNghiemSearchRepository, times(1)).save(testMauXetNghiem);
    }

    @Test
    @Transactional
    public void createMauXetNghiemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mauXetNghiemRepository.findAll().size();

        // Create the MauXetNghiem with an existing ID
        mauXetNghiem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMauXetNghiemMockMvc.perform(post("/api/mau-xet-nghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauXetNghiem)))
            .andExpect(status().isBadRequest());

        // Validate the MauXetNghiem in the database
        List<MauXetNghiem> mauXetNghiemList = mauXetNghiemRepository.findAll();
        assertThat(mauXetNghiemList).hasSize(databaseSizeBeforeCreate);

        // Validate the MauXetNghiem in Elasticsearch
        verify(mockMauXetNghiemSearchRepository, times(0)).save(mauXetNghiem);
    }

    @Test
    @Transactional
    public void getAllMauXetNghiems() throws Exception {
        // Initialize the database
        mauXetNghiemRepository.saveAndFlush(mauXetNghiem);

        // Get all the mauXetNghiemList
        restMauXetNghiemMockMvc.perform(get("/api/mau-xet-nghiems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mauXetNghiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].maMauXetNghiem").value(hasItem(DEFAULT_MA_MAU_XET_NGHIEM.toString())))
            .andExpect(jsonPath("$.[*].ngayLayMau").value(hasItem(DEFAULT_NGAY_LAY_MAU.toString())))
            .andExpect(jsonPath("$.[*].nguoiLayMau").value(hasItem(DEFAULT_NGUOI_LAY_MAU.toString())))
            .andExpect(jsonPath("$.[*].ngayTiepNhan").value(hasItem(DEFAULT_NGAY_TIEP_NHAN.toString())))
            .andExpect(jsonPath("$.[*].soLuongMau").value(hasItem(DEFAULT_SO_LUONG_MAU)))
            .andExpect(jsonPath("$.[*].trangThaiMau").value(hasItem(DEFAULT_TRANG_THAI_MAU.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].fileGoc").value(hasItem(DEFAULT_FILE_GOC.toString())))
            .andExpect(jsonPath("$.[*].fileKetQua").value(hasItem(DEFAULT_FILE_KET_QUA.toString())))
            .andExpect(jsonPath("$.[*].fileDotBien").value(hasItem(DEFAULT_FILE_DOT_BIEN.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].udf4").value(hasItem(DEFAULT_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].udf5").value(hasItem(DEFAULT_UDF_5.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllMauXetNghiemsWithEagerRelationshipsIsEnabled() throws Exception {
        MauXetNghiemResource mauXetNghiemResource = new MauXetNghiemResource(mauXetNghiemRepositoryMock, mockMauXetNghiemSearchRepository);
        when(mauXetNghiemRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restMauXetNghiemMockMvc = MockMvcBuilders.standaloneSetup(mauXetNghiemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restMauXetNghiemMockMvc.perform(get("/api/mau-xet-nghiems?eagerload=true"))
        .andExpect(status().isOk());

        verify(mauXetNghiemRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllMauXetNghiemsWithEagerRelationshipsIsNotEnabled() throws Exception {
        MauXetNghiemResource mauXetNghiemResource = new MauXetNghiemResource(mauXetNghiemRepositoryMock, mockMauXetNghiemSearchRepository);
            when(mauXetNghiemRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restMauXetNghiemMockMvc = MockMvcBuilders.standaloneSetup(mauXetNghiemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restMauXetNghiemMockMvc.perform(get("/api/mau-xet-nghiems?eagerload=true"))
        .andExpect(status().isOk());

            verify(mauXetNghiemRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getMauXetNghiem() throws Exception {
        // Initialize the database
        mauXetNghiemRepository.saveAndFlush(mauXetNghiem);

        // Get the mauXetNghiem
        restMauXetNghiemMockMvc.perform(get("/api/mau-xet-nghiems/{id}", mauXetNghiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mauXetNghiem.getId().intValue()))
            .andExpect(jsonPath("$.maMauXetNghiem").value(DEFAULT_MA_MAU_XET_NGHIEM.toString()))
            .andExpect(jsonPath("$.ngayLayMau").value(DEFAULT_NGAY_LAY_MAU.toString()))
            .andExpect(jsonPath("$.nguoiLayMau").value(DEFAULT_NGUOI_LAY_MAU.toString()))
            .andExpect(jsonPath("$.ngayTiepNhan").value(DEFAULT_NGAY_TIEP_NHAN.toString()))
            .andExpect(jsonPath("$.soLuongMau").value(DEFAULT_SO_LUONG_MAU))
            .andExpect(jsonPath("$.trangThaiMau").value(DEFAULT_TRANG_THAI_MAU.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.fileGoc").value(DEFAULT_FILE_GOC.toString()))
            .andExpect(jsonPath("$.fileKetQua").value(DEFAULT_FILE_KET_QUA.toString()))
            .andExpect(jsonPath("$.fileDotBien").value(DEFAULT_FILE_DOT_BIEN.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()))
            .andExpect(jsonPath("$.udf4").value(DEFAULT_UDF_4.toString()))
            .andExpect(jsonPath("$.udf5").value(DEFAULT_UDF_5.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMauXetNghiem() throws Exception {
        // Get the mauXetNghiem
        restMauXetNghiemMockMvc.perform(get("/api/mau-xet-nghiems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMauXetNghiem() throws Exception {
        // Initialize the database
        mauXetNghiemRepository.saveAndFlush(mauXetNghiem);

        int databaseSizeBeforeUpdate = mauXetNghiemRepository.findAll().size();

        // Update the mauXetNghiem
        MauXetNghiem updatedMauXetNghiem = mauXetNghiemRepository.findById(mauXetNghiem.getId()).get();
        // Disconnect from session so that the updates on updatedMauXetNghiem are not directly saved in db
        em.detach(updatedMauXetNghiem);
        updatedMauXetNghiem
            .maMauXetNghiem(UPDATED_MA_MAU_XET_NGHIEM)
            .ngayLayMau(UPDATED_NGAY_LAY_MAU)
            .nguoiLayMau(UPDATED_NGUOI_LAY_MAU)
            .ngayTiepNhan(UPDATED_NGAY_TIEP_NHAN)
            .soLuongMau(UPDATED_SO_LUONG_MAU)
            .trangThaiMau(UPDATED_TRANG_THAI_MAU)
            .moTa(UPDATED_MO_TA)
            .ghiChu(UPDATED_GHI_CHU)
            .fileGoc(UPDATED_FILE_GOC)
            .fileKetQua(UPDATED_FILE_KET_QUA)
            .fileDotBien(UPDATED_FILE_DOT_BIEN)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3)
            .udf4(UPDATED_UDF_4)
            .udf5(UPDATED_UDF_5);

        restMauXetNghiemMockMvc.perform(put("/api/mau-xet-nghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMauXetNghiem)))
            .andExpect(status().isOk());

        // Validate the MauXetNghiem in the database
        List<MauXetNghiem> mauXetNghiemList = mauXetNghiemRepository.findAll();
        assertThat(mauXetNghiemList).hasSize(databaseSizeBeforeUpdate);
        MauXetNghiem testMauXetNghiem = mauXetNghiemList.get(mauXetNghiemList.size() - 1);
        assertThat(testMauXetNghiem.getMaMauXetNghiem()).isEqualTo(UPDATED_MA_MAU_XET_NGHIEM);
        assertThat(testMauXetNghiem.getNgayLayMau()).isEqualTo(UPDATED_NGAY_LAY_MAU);
        assertThat(testMauXetNghiem.getNguoiLayMau()).isEqualTo(UPDATED_NGUOI_LAY_MAU);
        assertThat(testMauXetNghiem.getNgayTiepNhan()).isEqualTo(UPDATED_NGAY_TIEP_NHAN);
        assertThat(testMauXetNghiem.getSoLuongMau()).isEqualTo(UPDATED_SO_LUONG_MAU);
        assertThat(testMauXetNghiem.getTrangThaiMau()).isEqualTo(UPDATED_TRANG_THAI_MAU);
        assertThat(testMauXetNghiem.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testMauXetNghiem.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testMauXetNghiem.getFileGoc()).isEqualTo(UPDATED_FILE_GOC);
        assertThat(testMauXetNghiem.getFileKetQua()).isEqualTo(UPDATED_FILE_KET_QUA);
        assertThat(testMauXetNghiem.getFileDotBien()).isEqualTo(UPDATED_FILE_DOT_BIEN);
        assertThat(testMauXetNghiem.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testMauXetNghiem.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testMauXetNghiem.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testMauXetNghiem.getUdf3()).isEqualTo(UPDATED_UDF_3);
        assertThat(testMauXetNghiem.getUdf4()).isEqualTo(UPDATED_UDF_4);
        assertThat(testMauXetNghiem.getUdf5()).isEqualTo(UPDATED_UDF_5);

        // Validate the MauXetNghiem in Elasticsearch
        verify(mockMauXetNghiemSearchRepository, times(1)).save(testMauXetNghiem);
    }

    @Test
    @Transactional
    public void updateNonExistingMauXetNghiem() throws Exception {
        int databaseSizeBeforeUpdate = mauXetNghiemRepository.findAll().size();

        // Create the MauXetNghiem

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMauXetNghiemMockMvc.perform(put("/api/mau-xet-nghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauXetNghiem)))
            .andExpect(status().isBadRequest());

        // Validate the MauXetNghiem in the database
        List<MauXetNghiem> mauXetNghiemList = mauXetNghiemRepository.findAll();
        assertThat(mauXetNghiemList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MauXetNghiem in Elasticsearch
        verify(mockMauXetNghiemSearchRepository, times(0)).save(mauXetNghiem);
    }

    @Test
    @Transactional
    public void deleteMauXetNghiem() throws Exception {
        // Initialize the database
        mauXetNghiemRepository.saveAndFlush(mauXetNghiem);

        int databaseSizeBeforeDelete = mauXetNghiemRepository.findAll().size();

        // Get the mauXetNghiem
        restMauXetNghiemMockMvc.perform(delete("/api/mau-xet-nghiems/{id}", mauXetNghiem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MauXetNghiem> mauXetNghiemList = mauXetNghiemRepository.findAll();
        assertThat(mauXetNghiemList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MauXetNghiem in Elasticsearch
        verify(mockMauXetNghiemSearchRepository, times(1)).deleteById(mauXetNghiem.getId());
    }

    @Test
    @Transactional
    public void searchMauXetNghiem() throws Exception {
        // Initialize the database
        mauXetNghiemRepository.saveAndFlush(mauXetNghiem);
        when(mockMauXetNghiemSearchRepository.search(queryStringQuery("id:" + mauXetNghiem.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(mauXetNghiem), PageRequest.of(0, 1), 1));
        // Search the mauXetNghiem
        restMauXetNghiemMockMvc.perform(get("/api/_search/mau-xet-nghiems?query=id:" + mauXetNghiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mauXetNghiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].maMauXetNghiem").value(hasItem(DEFAULT_MA_MAU_XET_NGHIEM)))
            .andExpect(jsonPath("$.[*].ngayLayMau").value(hasItem(DEFAULT_NGAY_LAY_MAU.toString())))
            .andExpect(jsonPath("$.[*].nguoiLayMau").value(hasItem(DEFAULT_NGUOI_LAY_MAU)))
            .andExpect(jsonPath("$.[*].ngayTiepNhan").value(hasItem(DEFAULT_NGAY_TIEP_NHAN.toString())))
            .andExpect(jsonPath("$.[*].soLuongMau").value(hasItem(DEFAULT_SO_LUONG_MAU)))
            .andExpect(jsonPath("$.[*].trangThaiMau").value(hasItem(DEFAULT_TRANG_THAI_MAU.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].fileGoc").value(hasItem(DEFAULT_FILE_GOC)))
            .andExpect(jsonPath("$.[*].fileKetQua").value(hasItem(DEFAULT_FILE_KET_QUA)))
            .andExpect(jsonPath("$.[*].fileDotBien").value(hasItem(DEFAULT_FILE_DOT_BIEN)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)))
            .andExpect(jsonPath("$.[*].udf4").value(hasItem(DEFAULT_UDF_4)))
            .andExpect(jsonPath("$.[*].udf5").value(hasItem(DEFAULT_UDF_5)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MauXetNghiem.class);
        MauXetNghiem mauXetNghiem1 = new MauXetNghiem();
        mauXetNghiem1.setId(1L);
        MauXetNghiem mauXetNghiem2 = new MauXetNghiem();
        mauXetNghiem2.setId(mauXetNghiem1.getId());
        assertThat(mauXetNghiem1).isEqualTo(mauXetNghiem2);
        mauXetNghiem2.setId(2L);
        assertThat(mauXetNghiem1).isNotEqualTo(mauXetNghiem2);
        mauXetNghiem1.setId(null);
        assertThat(mauXetNghiem1).isNotEqualTo(mauXetNghiem2);
    }
}
