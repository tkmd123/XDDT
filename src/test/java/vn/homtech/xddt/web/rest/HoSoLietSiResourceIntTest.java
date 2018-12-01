package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.HoSoLietSi;
import vn.homtech.xddt.repository.HoSoLietSiRepository;
import vn.homtech.xddt.repository.search.HoSoLietSiSearchRepository;
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
 * Test class for the HoSoLietSiResource REST controller.
 *
 * @see HoSoLietSiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class HoSoLietSiResourceIntTest {

    private static final String DEFAULT_MA_CCS = "AAAAAAAAAA";
    private static final String UPDATED_MA_CCS = "BBBBBBBBBB";

    private static final String DEFAULT_MA_LIET_SI = "AAAAAAAAAA";
    private static final String UPDATED_MA_LIET_SI = "BBBBBBBBBB";

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_KHAC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KHAC = "BBBBBBBBBB";

    private static final String DEFAULT_BI_DANH = "AAAAAAAAAA";
    private static final String UPDATED_BI_DANH = "BBBBBBBBBB";

    private static final String DEFAULT_GIOI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_GIOI_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_NAM_SINH = "AAAAAAAAAA";
    private static final String UPDATED_NAM_SINH = "BBBBBBBBBB";

    private static final String DEFAULT_QUE_THON = "AAAAAAAAAA";
    private static final String UPDATED_QUE_THON = "BBBBBBBBBB";

    private static final String DEFAULT_QUE_XA = "AAAAAAAAAA";
    private static final String UPDATED_QUE_XA = "BBBBBBBBBB";

    private static final String DEFAULT_QUE_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_QUE_HUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_QUE_TINH = "AAAAAAAAAA";
    private static final String UPDATED_QUE_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_DON_VI = "AAAAAAAAAA";
    private static final String UPDATED_DON_VI = "BBBBBBBBBB";

    private static final Integer DEFAULT_NAM_NHAP_NGU = 1;
    private static final Integer UPDATED_NAM_NHAP_NGU = 2;

    private static final Integer DEFAULT_NAM_XUAT_NGU = 1;
    private static final Integer UPDATED_NAM_XUAT_NGU = 2;

    private static final Integer DEFAULT_NAM_TAI_NGU = 1;
    private static final Integer UPDATED_NAM_TAI_NGU = 2;

    private static final String DEFAULT_NAM_DI_B = "AAAAAAAAAA";
    private static final String UPDATED_NAM_DI_B = "BBBBBBBBBB";

    private static final Integer DEFAULT_HY_SINH_NGAY = 1;
    private static final Integer UPDATED_HY_SINH_NGAY = 2;

    private static final Integer DEFAULT_HY_SINH_THANG = 1;
    private static final Integer UPDATED_HY_SINH_THANG = 2;

    private static final Integer DEFAULT_HY_SINH_NAM = 1;
    private static final Integer UPDATED_HY_SINH_NAM = 2;

    private static final String DEFAULT_HY_SINH_LY_DO = "AAAAAAAAAA";
    private static final String UPDATED_HY_SINH_LY_DO = "BBBBBBBBBB";

    private static final String DEFAULT_HY_SINH_DON_VI = "AAAAAAAAAA";
    private static final String UPDATED_HY_SINH_DON_VI = "BBBBBBBBBB";

    private static final String DEFAULT_HY_SINH_TRAN_DANH = "AAAAAAAAAA";
    private static final String UPDATED_HY_SINH_TRAN_DANH = "BBBBBBBBBB";

    private static final String DEFAULT_HY_SINH_DIA_DIEM = "AAAAAAAAAA";
    private static final String UPDATED_HY_SINH_DIA_DIEM = "BBBBBBBBBB";

    private static final String DEFAULT_HY_SINH_XA = "AAAAAAAAAA";
    private static final String UPDATED_HY_SINH_XA = "BBBBBBBBBB";

    private static final String DEFAULT_HY_SINH_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_HY_SINH_HUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_HY_SINH_TINH = "AAAAAAAAAA";
    private static final String UPDATED_HY_SINH_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_AN_TANG_DIA_DIEM = "AAAAAAAAAA";
    private static final String UPDATED_AN_TANG_DIA_DIEM = "BBBBBBBBBB";

    private static final String DEFAULT_AN_TANG_XA = "AAAAAAAAAA";
    private static final String UPDATED_AN_TANG_XA = "BBBBBBBBBB";

    private static final String DEFAULT_AN_TANG_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_AN_TANG_HUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_AN_TANG_TINH = "AAAAAAAAAA";
    private static final String UPDATED_AN_TANG_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_NGAY_BAO_TU = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_BAO_TU = "BBBBBBBBBB";

    private static final String DEFAULT_GIAY_BAO_TU = "AAAAAAAAAA";
    private static final String UPDATED_GIAY_BAO_TU = "BBBBBBBBBB";

    private static final String DEFAULT_VAT_DUNG_KEM_THEO = "AAAAAAAAAA";
    private static final String UPDATED_VAT_DUNG_KEM_THEO = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final String DEFAULT_TRANG_THAI_XAC_MINH = "AAAAAAAAAA";
    private static final String UPDATED_TRANG_THAI_XAC_MINH = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHIEU_CAO = 1;
    private static final Integer UPDATED_CHIEU_CAO = 2;

    private static final Integer DEFAULT_CAN_NANG = 1;
    private static final Integer UPDATED_CAN_NANG = 2;

    private static final String DEFAULT_NHOM_MAU = "AAAAAAAAAA";
    private static final String UPDATED_NHOM_MAU = "BBBBBBBBBB";

    private static final String DEFAULT_DAC_DIEM_RANG = "AAAAAAAAAA";
    private static final String UPDATED_DAC_DIEM_RANG = "BBBBBBBBBB";

    private static final String DEFAULT_TINH_HUONG_HY_SINH = "AAAAAAAAAA";
    private static final String UPDATED_TINH_HUONG_HY_SINH = "BBBBBBBBBB";

    private static final String DEFAULT_TINH_HUONG_TIM_THAY = "AAAAAAAAAA";
    private static final String UPDATED_TINH_HUONG_TIM_THAY = "BBBBBBBBBB";

    private static final String DEFAULT_DAC_DIEM_NHAN_DANG = "AAAAAAAAAA";
    private static final String UPDATED_DAC_DIEM_NHAN_DANG = "BBBBBBBBBB";

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
    private HoSoLietSiRepository hoSoLietSiRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.HoSoLietSiSearchRepositoryMockConfiguration
     */
    @Autowired
    private HoSoLietSiSearchRepository mockHoSoLietSiSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHoSoLietSiMockMvc;

    private HoSoLietSi hoSoLietSi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HoSoLietSiResource hoSoLietSiResource = new HoSoLietSiResource(hoSoLietSiRepository, mockHoSoLietSiSearchRepository);
        this.restHoSoLietSiMockMvc = MockMvcBuilders.standaloneSetup(hoSoLietSiResource)
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
    public static HoSoLietSi createEntity(EntityManager em) {
        HoSoLietSi hoSoLietSi = new HoSoLietSi()
            .maCCS(DEFAULT_MA_CCS)
            .maLietSi(DEFAULT_MA_LIET_SI)
            .hoTen(DEFAULT_HO_TEN)
            .tenKhac(DEFAULT_TEN_KHAC)
            .biDanh(DEFAULT_BI_DANH)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .namSinh(DEFAULT_NAM_SINH)
            .queThon(DEFAULT_QUE_THON)
            .queXa(DEFAULT_QUE_XA)
            .queHuyen(DEFAULT_QUE_HUYEN)
            .queTinh(DEFAULT_QUE_TINH)
            .donVi(DEFAULT_DON_VI)
            .namNhapNgu(DEFAULT_NAM_NHAP_NGU)
            .namXuatNgu(DEFAULT_NAM_XUAT_NGU)
            .namTaiNgu(DEFAULT_NAM_TAI_NGU)
            .namDiB(DEFAULT_NAM_DI_B)
            .hySinhNgay(DEFAULT_HY_SINH_NGAY)
            .hySinhThang(DEFAULT_HY_SINH_THANG)
            .hySinhNam(DEFAULT_HY_SINH_NAM)
            .hySinhLyDo(DEFAULT_HY_SINH_LY_DO)
            .hySinhDonVi(DEFAULT_HY_SINH_DON_VI)
            .hySinhTranDanh(DEFAULT_HY_SINH_TRAN_DANH)
            .hySinhDiaDiem(DEFAULT_HY_SINH_DIA_DIEM)
            .hySinhXa(DEFAULT_HY_SINH_XA)
            .hySinhHuyen(DEFAULT_HY_SINH_HUYEN)
            .hySinhTinh(DEFAULT_HY_SINH_TINH)
            .anTangDiaDiem(DEFAULT_AN_TANG_DIA_DIEM)
            .anTangXa(DEFAULT_AN_TANG_XA)
            .anTangHuyen(DEFAULT_AN_TANG_HUYEN)
            .anTangTinh(DEFAULT_AN_TANG_TINH)
            .ngayBaoTu(DEFAULT_NGAY_BAO_TU)
            .giayBaoTu(DEFAULT_GIAY_BAO_TU)
            .vatDungKemTheo(DEFAULT_VAT_DUNG_KEM_THEO)
            .ghiChu(DEFAULT_GHI_CHU)
            .trangThaiXacMinh(DEFAULT_TRANG_THAI_XAC_MINH)
            .chieuCao(DEFAULT_CHIEU_CAO)
            .canNang(DEFAULT_CAN_NANG)
            .nhomMau(DEFAULT_NHOM_MAU)
            .dacDiemRang(DEFAULT_DAC_DIEM_RANG)
            .tinhHuongHySinh(DEFAULT_TINH_HUONG_HY_SINH)
            .tinhHuongTimThay(DEFAULT_TINH_HUONG_TIM_THAY)
            .dacDiemNhanDang(DEFAULT_DAC_DIEM_NHAN_DANG)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3)
            .udf4(DEFAULT_UDF_4)
            .udf5(DEFAULT_UDF_5);
        return hoSoLietSi;
    }

    @Before
    public void initTest() {
        hoSoLietSi = createEntity(em);
    }

    @Test
    @Transactional
    public void createHoSoLietSi() throws Exception {
        int databaseSizeBeforeCreate = hoSoLietSiRepository.findAll().size();

        // Create the HoSoLietSi
        restHoSoLietSiMockMvc.perform(post("/api/ho-so-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoLietSi)))
            .andExpect(status().isCreated());

        // Validate the HoSoLietSi in the database
        List<HoSoLietSi> hoSoLietSiList = hoSoLietSiRepository.findAll();
        assertThat(hoSoLietSiList).hasSize(databaseSizeBeforeCreate + 1);
        HoSoLietSi testHoSoLietSi = hoSoLietSiList.get(hoSoLietSiList.size() - 1);
        assertThat(testHoSoLietSi.getMaCCS()).isEqualTo(DEFAULT_MA_CCS);
        assertThat(testHoSoLietSi.getMaLietSi()).isEqualTo(DEFAULT_MA_LIET_SI);
        assertThat(testHoSoLietSi.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testHoSoLietSi.getTenKhac()).isEqualTo(DEFAULT_TEN_KHAC);
        assertThat(testHoSoLietSi.getBiDanh()).isEqualTo(DEFAULT_BI_DANH);
        assertThat(testHoSoLietSi.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testHoSoLietSi.getNamSinh()).isEqualTo(DEFAULT_NAM_SINH);
        assertThat(testHoSoLietSi.getQueThon()).isEqualTo(DEFAULT_QUE_THON);
        assertThat(testHoSoLietSi.getQueXa()).isEqualTo(DEFAULT_QUE_XA);
        assertThat(testHoSoLietSi.getQueHuyen()).isEqualTo(DEFAULT_QUE_HUYEN);
        assertThat(testHoSoLietSi.getQueTinh()).isEqualTo(DEFAULT_QUE_TINH);
        assertThat(testHoSoLietSi.getDonVi()).isEqualTo(DEFAULT_DON_VI);
        assertThat(testHoSoLietSi.getNamNhapNgu()).isEqualTo(DEFAULT_NAM_NHAP_NGU);
        assertThat(testHoSoLietSi.getNamXuatNgu()).isEqualTo(DEFAULT_NAM_XUAT_NGU);
        assertThat(testHoSoLietSi.getNamTaiNgu()).isEqualTo(DEFAULT_NAM_TAI_NGU);
        assertThat(testHoSoLietSi.getNamDiB()).isEqualTo(DEFAULT_NAM_DI_B);
        assertThat(testHoSoLietSi.getHySinhNgay()).isEqualTo(DEFAULT_HY_SINH_NGAY);
        assertThat(testHoSoLietSi.getHySinhThang()).isEqualTo(DEFAULT_HY_SINH_THANG);
        assertThat(testHoSoLietSi.getHySinhNam()).isEqualTo(DEFAULT_HY_SINH_NAM);
        assertThat(testHoSoLietSi.getHySinhLyDo()).isEqualTo(DEFAULT_HY_SINH_LY_DO);
        assertThat(testHoSoLietSi.getHySinhDonVi()).isEqualTo(DEFAULT_HY_SINH_DON_VI);
        assertThat(testHoSoLietSi.getHySinhTranDanh()).isEqualTo(DEFAULT_HY_SINH_TRAN_DANH);
        assertThat(testHoSoLietSi.getHySinhDiaDiem()).isEqualTo(DEFAULT_HY_SINH_DIA_DIEM);
        assertThat(testHoSoLietSi.getHySinhXa()).isEqualTo(DEFAULT_HY_SINH_XA);
        assertThat(testHoSoLietSi.getHySinhHuyen()).isEqualTo(DEFAULT_HY_SINH_HUYEN);
        assertThat(testHoSoLietSi.getHySinhTinh()).isEqualTo(DEFAULT_HY_SINH_TINH);
        assertThat(testHoSoLietSi.getAnTangDiaDiem()).isEqualTo(DEFAULT_AN_TANG_DIA_DIEM);
        assertThat(testHoSoLietSi.getAnTangXa()).isEqualTo(DEFAULT_AN_TANG_XA);
        assertThat(testHoSoLietSi.getAnTangHuyen()).isEqualTo(DEFAULT_AN_TANG_HUYEN);
        assertThat(testHoSoLietSi.getAnTangTinh()).isEqualTo(DEFAULT_AN_TANG_TINH);
        assertThat(testHoSoLietSi.getNgayBaoTu()).isEqualTo(DEFAULT_NGAY_BAO_TU);
        assertThat(testHoSoLietSi.getGiayBaoTu()).isEqualTo(DEFAULT_GIAY_BAO_TU);
        assertThat(testHoSoLietSi.getVatDungKemTheo()).isEqualTo(DEFAULT_VAT_DUNG_KEM_THEO);
        assertThat(testHoSoLietSi.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testHoSoLietSi.getTrangThaiXacMinh()).isEqualTo(DEFAULT_TRANG_THAI_XAC_MINH);
        assertThat(testHoSoLietSi.getChieuCao()).isEqualTo(DEFAULT_CHIEU_CAO);
        assertThat(testHoSoLietSi.getCanNang()).isEqualTo(DEFAULT_CAN_NANG);
        assertThat(testHoSoLietSi.getNhomMau()).isEqualTo(DEFAULT_NHOM_MAU);
        assertThat(testHoSoLietSi.getDacDiemRang()).isEqualTo(DEFAULT_DAC_DIEM_RANG);
        assertThat(testHoSoLietSi.getTinhHuongHySinh()).isEqualTo(DEFAULT_TINH_HUONG_HY_SINH);
        assertThat(testHoSoLietSi.getTinhHuongTimThay()).isEqualTo(DEFAULT_TINH_HUONG_TIM_THAY);
        assertThat(testHoSoLietSi.getDacDiemNhanDang()).isEqualTo(DEFAULT_DAC_DIEM_NHAN_DANG);
        assertThat(testHoSoLietSi.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testHoSoLietSi.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testHoSoLietSi.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testHoSoLietSi.getUdf3()).isEqualTo(DEFAULT_UDF_3);
        assertThat(testHoSoLietSi.getUdf4()).isEqualTo(DEFAULT_UDF_4);
        assertThat(testHoSoLietSi.getUdf5()).isEqualTo(DEFAULT_UDF_5);

        // Validate the HoSoLietSi in Elasticsearch
        verify(mockHoSoLietSiSearchRepository, times(1)).save(testHoSoLietSi);
    }

    @Test
    @Transactional
    public void createHoSoLietSiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hoSoLietSiRepository.findAll().size();

        // Create the HoSoLietSi with an existing ID
        hoSoLietSi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoSoLietSiMockMvc.perform(post("/api/ho-so-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoLietSi)))
            .andExpect(status().isBadRequest());

        // Validate the HoSoLietSi in the database
        List<HoSoLietSi> hoSoLietSiList = hoSoLietSiRepository.findAll();
        assertThat(hoSoLietSiList).hasSize(databaseSizeBeforeCreate);

        // Validate the HoSoLietSi in Elasticsearch
        verify(mockHoSoLietSiSearchRepository, times(0)).save(hoSoLietSi);
    }

    @Test
    @Transactional
    public void getAllHoSoLietSis() throws Exception {
        // Initialize the database
        hoSoLietSiRepository.saveAndFlush(hoSoLietSi);

        // Get all the hoSoLietSiList
        restHoSoLietSiMockMvc.perform(get("/api/ho-so-liet-sis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoSoLietSi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCCS").value(hasItem(DEFAULT_MA_CCS.toString())))
            .andExpect(jsonPath("$.[*].maLietSi").value(hasItem(DEFAULT_MA_LIET_SI.toString())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN.toString())))
            .andExpect(jsonPath("$.[*].tenKhac").value(hasItem(DEFAULT_TEN_KHAC.toString())))
            .andExpect(jsonPath("$.[*].biDanh").value(hasItem(DEFAULT_BI_DANH.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.toString())))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH.toString())))
            .andExpect(jsonPath("$.[*].queThon").value(hasItem(DEFAULT_QUE_THON.toString())))
            .andExpect(jsonPath("$.[*].queXa").value(hasItem(DEFAULT_QUE_XA.toString())))
            .andExpect(jsonPath("$.[*].queHuyen").value(hasItem(DEFAULT_QUE_HUYEN.toString())))
            .andExpect(jsonPath("$.[*].queTinh").value(hasItem(DEFAULT_QUE_TINH.toString())))
            .andExpect(jsonPath("$.[*].donVi").value(hasItem(DEFAULT_DON_VI.toString())))
            .andExpect(jsonPath("$.[*].namNhapNgu").value(hasItem(DEFAULT_NAM_NHAP_NGU)))
            .andExpect(jsonPath("$.[*].namXuatNgu").value(hasItem(DEFAULT_NAM_XUAT_NGU)))
            .andExpect(jsonPath("$.[*].namTaiNgu").value(hasItem(DEFAULT_NAM_TAI_NGU)))
            .andExpect(jsonPath("$.[*].namDiB").value(hasItem(DEFAULT_NAM_DI_B.toString())))
            .andExpect(jsonPath("$.[*].hySinhNgay").value(hasItem(DEFAULT_HY_SINH_NGAY)))
            .andExpect(jsonPath("$.[*].hySinhThang").value(hasItem(DEFAULT_HY_SINH_THANG)))
            .andExpect(jsonPath("$.[*].hySinhNam").value(hasItem(DEFAULT_HY_SINH_NAM)))
            .andExpect(jsonPath("$.[*].hySinhLyDo").value(hasItem(DEFAULT_HY_SINH_LY_DO.toString())))
            .andExpect(jsonPath("$.[*].hySinhDonVi").value(hasItem(DEFAULT_HY_SINH_DON_VI.toString())))
            .andExpect(jsonPath("$.[*].hySinhTranDanh").value(hasItem(DEFAULT_HY_SINH_TRAN_DANH.toString())))
            .andExpect(jsonPath("$.[*].hySinhDiaDiem").value(hasItem(DEFAULT_HY_SINH_DIA_DIEM.toString())))
            .andExpect(jsonPath("$.[*].hySinhXa").value(hasItem(DEFAULT_HY_SINH_XA.toString())))
            .andExpect(jsonPath("$.[*].hySinhHuyen").value(hasItem(DEFAULT_HY_SINH_HUYEN.toString())))
            .andExpect(jsonPath("$.[*].hySinhTinh").value(hasItem(DEFAULT_HY_SINH_TINH.toString())))
            .andExpect(jsonPath("$.[*].anTangDiaDiem").value(hasItem(DEFAULT_AN_TANG_DIA_DIEM.toString())))
            .andExpect(jsonPath("$.[*].anTangXa").value(hasItem(DEFAULT_AN_TANG_XA.toString())))
            .andExpect(jsonPath("$.[*].anTangHuyen").value(hasItem(DEFAULT_AN_TANG_HUYEN.toString())))
            .andExpect(jsonPath("$.[*].anTangTinh").value(hasItem(DEFAULT_AN_TANG_TINH.toString())))
            .andExpect(jsonPath("$.[*].ngayBaoTu").value(hasItem(DEFAULT_NGAY_BAO_TU.toString())))
            .andExpect(jsonPath("$.[*].giayBaoTu").value(hasItem(DEFAULT_GIAY_BAO_TU.toString())))
            .andExpect(jsonPath("$.[*].vatDungKemTheo").value(hasItem(DEFAULT_VAT_DUNG_KEM_THEO.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].trangThaiXacMinh").value(hasItem(DEFAULT_TRANG_THAI_XAC_MINH.toString())))
            .andExpect(jsonPath("$.[*].chieuCao").value(hasItem(DEFAULT_CHIEU_CAO)))
            .andExpect(jsonPath("$.[*].canNang").value(hasItem(DEFAULT_CAN_NANG)))
            .andExpect(jsonPath("$.[*].nhomMau").value(hasItem(DEFAULT_NHOM_MAU.toString())))
            .andExpect(jsonPath("$.[*].dacDiemRang").value(hasItem(DEFAULT_DAC_DIEM_RANG.toString())))
            .andExpect(jsonPath("$.[*].tinhHuongHySinh").value(hasItem(DEFAULT_TINH_HUONG_HY_SINH.toString())))
            .andExpect(jsonPath("$.[*].tinhHuongTimThay").value(hasItem(DEFAULT_TINH_HUONG_TIM_THAY.toString())))
            .andExpect(jsonPath("$.[*].dacDiemNhanDang").value(hasItem(DEFAULT_DAC_DIEM_NHAN_DANG.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].udf4").value(hasItem(DEFAULT_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].udf5").value(hasItem(DEFAULT_UDF_5.toString())));
    }
    
    @Test
    @Transactional
    public void getHoSoLietSi() throws Exception {
        // Initialize the database
        hoSoLietSiRepository.saveAndFlush(hoSoLietSi);

        // Get the hoSoLietSi
        restHoSoLietSiMockMvc.perform(get("/api/ho-so-liet-sis/{id}", hoSoLietSi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hoSoLietSi.getId().intValue()))
            .andExpect(jsonPath("$.maCCS").value(DEFAULT_MA_CCS.toString()))
            .andExpect(jsonPath("$.maLietSi").value(DEFAULT_MA_LIET_SI.toString()))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN.toString()))
            .andExpect(jsonPath("$.tenKhac").value(DEFAULT_TEN_KHAC.toString()))
            .andExpect(jsonPath("$.biDanh").value(DEFAULT_BI_DANH.toString()))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH.toString()))
            .andExpect(jsonPath("$.namSinh").value(DEFAULT_NAM_SINH.toString()))
            .andExpect(jsonPath("$.queThon").value(DEFAULT_QUE_THON.toString()))
            .andExpect(jsonPath("$.queXa").value(DEFAULT_QUE_XA.toString()))
            .andExpect(jsonPath("$.queHuyen").value(DEFAULT_QUE_HUYEN.toString()))
            .andExpect(jsonPath("$.queTinh").value(DEFAULT_QUE_TINH.toString()))
            .andExpect(jsonPath("$.donVi").value(DEFAULT_DON_VI.toString()))
            .andExpect(jsonPath("$.namNhapNgu").value(DEFAULT_NAM_NHAP_NGU))
            .andExpect(jsonPath("$.namXuatNgu").value(DEFAULT_NAM_XUAT_NGU))
            .andExpect(jsonPath("$.namTaiNgu").value(DEFAULT_NAM_TAI_NGU))
            .andExpect(jsonPath("$.namDiB").value(DEFAULT_NAM_DI_B.toString()))
            .andExpect(jsonPath("$.hySinhNgay").value(DEFAULT_HY_SINH_NGAY))
            .andExpect(jsonPath("$.hySinhThang").value(DEFAULT_HY_SINH_THANG))
            .andExpect(jsonPath("$.hySinhNam").value(DEFAULT_HY_SINH_NAM))
            .andExpect(jsonPath("$.hySinhLyDo").value(DEFAULT_HY_SINH_LY_DO.toString()))
            .andExpect(jsonPath("$.hySinhDonVi").value(DEFAULT_HY_SINH_DON_VI.toString()))
            .andExpect(jsonPath("$.hySinhTranDanh").value(DEFAULT_HY_SINH_TRAN_DANH.toString()))
            .andExpect(jsonPath("$.hySinhDiaDiem").value(DEFAULT_HY_SINH_DIA_DIEM.toString()))
            .andExpect(jsonPath("$.hySinhXa").value(DEFAULT_HY_SINH_XA.toString()))
            .andExpect(jsonPath("$.hySinhHuyen").value(DEFAULT_HY_SINH_HUYEN.toString()))
            .andExpect(jsonPath("$.hySinhTinh").value(DEFAULT_HY_SINH_TINH.toString()))
            .andExpect(jsonPath("$.anTangDiaDiem").value(DEFAULT_AN_TANG_DIA_DIEM.toString()))
            .andExpect(jsonPath("$.anTangXa").value(DEFAULT_AN_TANG_XA.toString()))
            .andExpect(jsonPath("$.anTangHuyen").value(DEFAULT_AN_TANG_HUYEN.toString()))
            .andExpect(jsonPath("$.anTangTinh").value(DEFAULT_AN_TANG_TINH.toString()))
            .andExpect(jsonPath("$.ngayBaoTu").value(DEFAULT_NGAY_BAO_TU.toString()))
            .andExpect(jsonPath("$.giayBaoTu").value(DEFAULT_GIAY_BAO_TU.toString()))
            .andExpect(jsonPath("$.vatDungKemTheo").value(DEFAULT_VAT_DUNG_KEM_THEO.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.trangThaiXacMinh").value(DEFAULT_TRANG_THAI_XAC_MINH.toString()))
            .andExpect(jsonPath("$.chieuCao").value(DEFAULT_CHIEU_CAO))
            .andExpect(jsonPath("$.canNang").value(DEFAULT_CAN_NANG))
            .andExpect(jsonPath("$.nhomMau").value(DEFAULT_NHOM_MAU.toString()))
            .andExpect(jsonPath("$.dacDiemRang").value(DEFAULT_DAC_DIEM_RANG.toString()))
            .andExpect(jsonPath("$.tinhHuongHySinh").value(DEFAULT_TINH_HUONG_HY_SINH.toString()))
            .andExpect(jsonPath("$.tinhHuongTimThay").value(DEFAULT_TINH_HUONG_TIM_THAY.toString()))
            .andExpect(jsonPath("$.dacDiemNhanDang").value(DEFAULT_DAC_DIEM_NHAN_DANG.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()))
            .andExpect(jsonPath("$.udf4").value(DEFAULT_UDF_4.toString()))
            .andExpect(jsonPath("$.udf5").value(DEFAULT_UDF_5.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHoSoLietSi() throws Exception {
        // Get the hoSoLietSi
        restHoSoLietSiMockMvc.perform(get("/api/ho-so-liet-sis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHoSoLietSi() throws Exception {
        // Initialize the database
        hoSoLietSiRepository.saveAndFlush(hoSoLietSi);

        int databaseSizeBeforeUpdate = hoSoLietSiRepository.findAll().size();

        // Update the hoSoLietSi
        HoSoLietSi updatedHoSoLietSi = hoSoLietSiRepository.findById(hoSoLietSi.getId()).get();
        // Disconnect from session so that the updates on updatedHoSoLietSi are not directly saved in db
        em.detach(updatedHoSoLietSi);
        updatedHoSoLietSi
            .maCCS(UPDATED_MA_CCS)
            .maLietSi(UPDATED_MA_LIET_SI)
            .hoTen(UPDATED_HO_TEN)
            .tenKhac(UPDATED_TEN_KHAC)
            .biDanh(UPDATED_BI_DANH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .namSinh(UPDATED_NAM_SINH)
            .queThon(UPDATED_QUE_THON)
            .queXa(UPDATED_QUE_XA)
            .queHuyen(UPDATED_QUE_HUYEN)
            .queTinh(UPDATED_QUE_TINH)
            .donVi(UPDATED_DON_VI)
            .namNhapNgu(UPDATED_NAM_NHAP_NGU)
            .namXuatNgu(UPDATED_NAM_XUAT_NGU)
            .namTaiNgu(UPDATED_NAM_TAI_NGU)
            .namDiB(UPDATED_NAM_DI_B)
            .hySinhNgay(UPDATED_HY_SINH_NGAY)
            .hySinhThang(UPDATED_HY_SINH_THANG)
            .hySinhNam(UPDATED_HY_SINH_NAM)
            .hySinhLyDo(UPDATED_HY_SINH_LY_DO)
            .hySinhDonVi(UPDATED_HY_SINH_DON_VI)
            .hySinhTranDanh(UPDATED_HY_SINH_TRAN_DANH)
            .hySinhDiaDiem(UPDATED_HY_SINH_DIA_DIEM)
            .hySinhXa(UPDATED_HY_SINH_XA)
            .hySinhHuyen(UPDATED_HY_SINH_HUYEN)
            .hySinhTinh(UPDATED_HY_SINH_TINH)
            .anTangDiaDiem(UPDATED_AN_TANG_DIA_DIEM)
            .anTangXa(UPDATED_AN_TANG_XA)
            .anTangHuyen(UPDATED_AN_TANG_HUYEN)
            .anTangTinh(UPDATED_AN_TANG_TINH)
            .ngayBaoTu(UPDATED_NGAY_BAO_TU)
            .giayBaoTu(UPDATED_GIAY_BAO_TU)
            .vatDungKemTheo(UPDATED_VAT_DUNG_KEM_THEO)
            .ghiChu(UPDATED_GHI_CHU)
            .trangThaiXacMinh(UPDATED_TRANG_THAI_XAC_MINH)
            .chieuCao(UPDATED_CHIEU_CAO)
            .canNang(UPDATED_CAN_NANG)
            .nhomMau(UPDATED_NHOM_MAU)
            .dacDiemRang(UPDATED_DAC_DIEM_RANG)
            .tinhHuongHySinh(UPDATED_TINH_HUONG_HY_SINH)
            .tinhHuongTimThay(UPDATED_TINH_HUONG_TIM_THAY)
            .dacDiemNhanDang(UPDATED_DAC_DIEM_NHAN_DANG)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3)
            .udf4(UPDATED_UDF_4)
            .udf5(UPDATED_UDF_5);

        restHoSoLietSiMockMvc.perform(put("/api/ho-so-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHoSoLietSi)))
            .andExpect(status().isOk());

        // Validate the HoSoLietSi in the database
        List<HoSoLietSi> hoSoLietSiList = hoSoLietSiRepository.findAll();
        assertThat(hoSoLietSiList).hasSize(databaseSizeBeforeUpdate);
        HoSoLietSi testHoSoLietSi = hoSoLietSiList.get(hoSoLietSiList.size() - 1);
        assertThat(testHoSoLietSi.getMaCCS()).isEqualTo(UPDATED_MA_CCS);
        assertThat(testHoSoLietSi.getMaLietSi()).isEqualTo(UPDATED_MA_LIET_SI);
        assertThat(testHoSoLietSi.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testHoSoLietSi.getTenKhac()).isEqualTo(UPDATED_TEN_KHAC);
        assertThat(testHoSoLietSi.getBiDanh()).isEqualTo(UPDATED_BI_DANH);
        assertThat(testHoSoLietSi.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testHoSoLietSi.getNamSinh()).isEqualTo(UPDATED_NAM_SINH);
        assertThat(testHoSoLietSi.getQueThon()).isEqualTo(UPDATED_QUE_THON);
        assertThat(testHoSoLietSi.getQueXa()).isEqualTo(UPDATED_QUE_XA);
        assertThat(testHoSoLietSi.getQueHuyen()).isEqualTo(UPDATED_QUE_HUYEN);
        assertThat(testHoSoLietSi.getQueTinh()).isEqualTo(UPDATED_QUE_TINH);
        assertThat(testHoSoLietSi.getDonVi()).isEqualTo(UPDATED_DON_VI);
        assertThat(testHoSoLietSi.getNamNhapNgu()).isEqualTo(UPDATED_NAM_NHAP_NGU);
        assertThat(testHoSoLietSi.getNamXuatNgu()).isEqualTo(UPDATED_NAM_XUAT_NGU);
        assertThat(testHoSoLietSi.getNamTaiNgu()).isEqualTo(UPDATED_NAM_TAI_NGU);
        assertThat(testHoSoLietSi.getNamDiB()).isEqualTo(UPDATED_NAM_DI_B);
        assertThat(testHoSoLietSi.getHySinhNgay()).isEqualTo(UPDATED_HY_SINH_NGAY);
        assertThat(testHoSoLietSi.getHySinhThang()).isEqualTo(UPDATED_HY_SINH_THANG);
        assertThat(testHoSoLietSi.getHySinhNam()).isEqualTo(UPDATED_HY_SINH_NAM);
        assertThat(testHoSoLietSi.getHySinhLyDo()).isEqualTo(UPDATED_HY_SINH_LY_DO);
        assertThat(testHoSoLietSi.getHySinhDonVi()).isEqualTo(UPDATED_HY_SINH_DON_VI);
        assertThat(testHoSoLietSi.getHySinhTranDanh()).isEqualTo(UPDATED_HY_SINH_TRAN_DANH);
        assertThat(testHoSoLietSi.getHySinhDiaDiem()).isEqualTo(UPDATED_HY_SINH_DIA_DIEM);
        assertThat(testHoSoLietSi.getHySinhXa()).isEqualTo(UPDATED_HY_SINH_XA);
        assertThat(testHoSoLietSi.getHySinhHuyen()).isEqualTo(UPDATED_HY_SINH_HUYEN);
        assertThat(testHoSoLietSi.getHySinhTinh()).isEqualTo(UPDATED_HY_SINH_TINH);
        assertThat(testHoSoLietSi.getAnTangDiaDiem()).isEqualTo(UPDATED_AN_TANG_DIA_DIEM);
        assertThat(testHoSoLietSi.getAnTangXa()).isEqualTo(UPDATED_AN_TANG_XA);
        assertThat(testHoSoLietSi.getAnTangHuyen()).isEqualTo(UPDATED_AN_TANG_HUYEN);
        assertThat(testHoSoLietSi.getAnTangTinh()).isEqualTo(UPDATED_AN_TANG_TINH);
        assertThat(testHoSoLietSi.getNgayBaoTu()).isEqualTo(UPDATED_NGAY_BAO_TU);
        assertThat(testHoSoLietSi.getGiayBaoTu()).isEqualTo(UPDATED_GIAY_BAO_TU);
        assertThat(testHoSoLietSi.getVatDungKemTheo()).isEqualTo(UPDATED_VAT_DUNG_KEM_THEO);
        assertThat(testHoSoLietSi.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testHoSoLietSi.getTrangThaiXacMinh()).isEqualTo(UPDATED_TRANG_THAI_XAC_MINH);
        assertThat(testHoSoLietSi.getChieuCao()).isEqualTo(UPDATED_CHIEU_CAO);
        assertThat(testHoSoLietSi.getCanNang()).isEqualTo(UPDATED_CAN_NANG);
        assertThat(testHoSoLietSi.getNhomMau()).isEqualTo(UPDATED_NHOM_MAU);
        assertThat(testHoSoLietSi.getDacDiemRang()).isEqualTo(UPDATED_DAC_DIEM_RANG);
        assertThat(testHoSoLietSi.getTinhHuongHySinh()).isEqualTo(UPDATED_TINH_HUONG_HY_SINH);
        assertThat(testHoSoLietSi.getTinhHuongTimThay()).isEqualTo(UPDATED_TINH_HUONG_TIM_THAY);
        assertThat(testHoSoLietSi.getDacDiemNhanDang()).isEqualTo(UPDATED_DAC_DIEM_NHAN_DANG);
        assertThat(testHoSoLietSi.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testHoSoLietSi.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testHoSoLietSi.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testHoSoLietSi.getUdf3()).isEqualTo(UPDATED_UDF_3);
        assertThat(testHoSoLietSi.getUdf4()).isEqualTo(UPDATED_UDF_4);
        assertThat(testHoSoLietSi.getUdf5()).isEqualTo(UPDATED_UDF_5);

        // Validate the HoSoLietSi in Elasticsearch
        verify(mockHoSoLietSiSearchRepository, times(1)).save(testHoSoLietSi);
    }

    @Test
    @Transactional
    public void updateNonExistingHoSoLietSi() throws Exception {
        int databaseSizeBeforeUpdate = hoSoLietSiRepository.findAll().size();

        // Create the HoSoLietSi

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoSoLietSiMockMvc.perform(put("/api/ho-so-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoLietSi)))
            .andExpect(status().isBadRequest());

        // Validate the HoSoLietSi in the database
        List<HoSoLietSi> hoSoLietSiList = hoSoLietSiRepository.findAll();
        assertThat(hoSoLietSiList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HoSoLietSi in Elasticsearch
        verify(mockHoSoLietSiSearchRepository, times(0)).save(hoSoLietSi);
    }

    @Test
    @Transactional
    public void deleteHoSoLietSi() throws Exception {
        // Initialize the database
        hoSoLietSiRepository.saveAndFlush(hoSoLietSi);

        int databaseSizeBeforeDelete = hoSoLietSiRepository.findAll().size();

        // Get the hoSoLietSi
        restHoSoLietSiMockMvc.perform(delete("/api/ho-so-liet-sis/{id}", hoSoLietSi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HoSoLietSi> hoSoLietSiList = hoSoLietSiRepository.findAll();
        assertThat(hoSoLietSiList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HoSoLietSi in Elasticsearch
        verify(mockHoSoLietSiSearchRepository, times(1)).deleteById(hoSoLietSi.getId());
    }

    @Test
    @Transactional
    public void searchHoSoLietSi() throws Exception {
        // Initialize the database
        hoSoLietSiRepository.saveAndFlush(hoSoLietSi);
        when(mockHoSoLietSiSearchRepository.search(queryStringQuery("id:" + hoSoLietSi.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(hoSoLietSi), PageRequest.of(0, 1), 1));
        // Search the hoSoLietSi
        restHoSoLietSiMockMvc.perform(get("/api/_search/ho-so-liet-sis?query=id:" + hoSoLietSi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoSoLietSi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCCS").value(hasItem(DEFAULT_MA_CCS)))
            .andExpect(jsonPath("$.[*].maLietSi").value(hasItem(DEFAULT_MA_LIET_SI)))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN)))
            .andExpect(jsonPath("$.[*].tenKhac").value(hasItem(DEFAULT_TEN_KHAC)))
            .andExpect(jsonPath("$.[*].biDanh").value(hasItem(DEFAULT_BI_DANH)))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH)))
            .andExpect(jsonPath("$.[*].queThon").value(hasItem(DEFAULT_QUE_THON)))
            .andExpect(jsonPath("$.[*].queXa").value(hasItem(DEFAULT_QUE_XA)))
            .andExpect(jsonPath("$.[*].queHuyen").value(hasItem(DEFAULT_QUE_HUYEN)))
            .andExpect(jsonPath("$.[*].queTinh").value(hasItem(DEFAULT_QUE_TINH)))
            .andExpect(jsonPath("$.[*].donVi").value(hasItem(DEFAULT_DON_VI)))
            .andExpect(jsonPath("$.[*].namNhapNgu").value(hasItem(DEFAULT_NAM_NHAP_NGU)))
            .andExpect(jsonPath("$.[*].namXuatNgu").value(hasItem(DEFAULT_NAM_XUAT_NGU)))
            .andExpect(jsonPath("$.[*].namTaiNgu").value(hasItem(DEFAULT_NAM_TAI_NGU)))
            .andExpect(jsonPath("$.[*].namDiB").value(hasItem(DEFAULT_NAM_DI_B)))
            .andExpect(jsonPath("$.[*].hySinhNgay").value(hasItem(DEFAULT_HY_SINH_NGAY)))
            .andExpect(jsonPath("$.[*].hySinhThang").value(hasItem(DEFAULT_HY_SINH_THANG)))
            .andExpect(jsonPath("$.[*].hySinhNam").value(hasItem(DEFAULT_HY_SINH_NAM)))
            .andExpect(jsonPath("$.[*].hySinhLyDo").value(hasItem(DEFAULT_HY_SINH_LY_DO)))
            .andExpect(jsonPath("$.[*].hySinhDonVi").value(hasItem(DEFAULT_HY_SINH_DON_VI)))
            .andExpect(jsonPath("$.[*].hySinhTranDanh").value(hasItem(DEFAULT_HY_SINH_TRAN_DANH)))
            .andExpect(jsonPath("$.[*].hySinhDiaDiem").value(hasItem(DEFAULT_HY_SINH_DIA_DIEM)))
            .andExpect(jsonPath("$.[*].hySinhXa").value(hasItem(DEFAULT_HY_SINH_XA)))
            .andExpect(jsonPath("$.[*].hySinhHuyen").value(hasItem(DEFAULT_HY_SINH_HUYEN)))
            .andExpect(jsonPath("$.[*].hySinhTinh").value(hasItem(DEFAULT_HY_SINH_TINH)))
            .andExpect(jsonPath("$.[*].anTangDiaDiem").value(hasItem(DEFAULT_AN_TANG_DIA_DIEM)))
            .andExpect(jsonPath("$.[*].anTangXa").value(hasItem(DEFAULT_AN_TANG_XA)))
            .andExpect(jsonPath("$.[*].anTangHuyen").value(hasItem(DEFAULT_AN_TANG_HUYEN)))
            .andExpect(jsonPath("$.[*].anTangTinh").value(hasItem(DEFAULT_AN_TANG_TINH)))
            .andExpect(jsonPath("$.[*].ngayBaoTu").value(hasItem(DEFAULT_NGAY_BAO_TU)))
            .andExpect(jsonPath("$.[*].giayBaoTu").value(hasItem(DEFAULT_GIAY_BAO_TU)))
            .andExpect(jsonPath("$.[*].vatDungKemTheo").value(hasItem(DEFAULT_VAT_DUNG_KEM_THEO)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].trangThaiXacMinh").value(hasItem(DEFAULT_TRANG_THAI_XAC_MINH)))
            .andExpect(jsonPath("$.[*].chieuCao").value(hasItem(DEFAULT_CHIEU_CAO)))
            .andExpect(jsonPath("$.[*].canNang").value(hasItem(DEFAULT_CAN_NANG)))
            .andExpect(jsonPath("$.[*].nhomMau").value(hasItem(DEFAULT_NHOM_MAU)))
            .andExpect(jsonPath("$.[*].dacDiemRang").value(hasItem(DEFAULT_DAC_DIEM_RANG)))
            .andExpect(jsonPath("$.[*].tinhHuongHySinh").value(hasItem(DEFAULT_TINH_HUONG_HY_SINH)))
            .andExpect(jsonPath("$.[*].tinhHuongTimThay").value(hasItem(DEFAULT_TINH_HUONG_TIM_THAY)))
            .andExpect(jsonPath("$.[*].dacDiemNhanDang").value(hasItem(DEFAULT_DAC_DIEM_NHAN_DANG)))
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
        TestUtil.equalsVerifier(HoSoLietSi.class);
        HoSoLietSi hoSoLietSi1 = new HoSoLietSi();
        hoSoLietSi1.setId(1L);
        HoSoLietSi hoSoLietSi2 = new HoSoLietSi();
        hoSoLietSi2.setId(hoSoLietSi1.getId());
        assertThat(hoSoLietSi1).isEqualTo(hoSoLietSi2);
        hoSoLietSi2.setId(2L);
        assertThat(hoSoLietSi1).isNotEqualTo(hoSoLietSi2);
        hoSoLietSi1.setId(null);
        assertThat(hoSoLietSi1).isNotEqualTo(hoSoLietSi2);
    }
}
