package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.HoSoThanNhan;
import vn.homtech.xddt.repository.HoSoThanNhanRepository;
import vn.homtech.xddt.repository.search.HoSoThanNhanSearchRepository;
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
 * Test class for the HoSoThanNhanResource REST controller.
 *
 * @see HoSoThanNhanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class HoSoThanNhanResourceIntTest {

    private static final String DEFAULT_MA_THAN_NHAN = "AAAAAAAAAA";
    private static final String UPDATED_MA_THAN_NHAN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_LAY_MAU = false;
    private static final Boolean UPDATED_IS_LAY_MAU = true;

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final Instant DEFAULT_NAM_SINH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NAM_SINH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_GIOI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_GIOI_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CMT = "AAAAAAAAAA";
    private static final String UPDATED_SO_CMT = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_DIEN_THOAI_CHINH = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_THOAI_CHINH = "BBBBBBBBBB";

    private static final String DEFAULT_DIEN_THOAI_PHU = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_THOAI_PHU = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

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
    private HoSoThanNhanRepository hoSoThanNhanRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.HoSoThanNhanSearchRepositoryMockConfiguration
     */
    @Autowired
    private HoSoThanNhanSearchRepository mockHoSoThanNhanSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHoSoThanNhanMockMvc;

    private HoSoThanNhan hoSoThanNhan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HoSoThanNhanResource hoSoThanNhanResource = new HoSoThanNhanResource(hoSoThanNhanRepository, mockHoSoThanNhanSearchRepository);
        this.restHoSoThanNhanMockMvc = MockMvcBuilders.standaloneSetup(hoSoThanNhanResource)
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
    public static HoSoThanNhan createEntity(EntityManager em) {
        HoSoThanNhan hoSoThanNhan = new HoSoThanNhan()
            .maThanNhan(DEFAULT_MA_THAN_NHAN)
            .isLayMau(DEFAULT_IS_LAY_MAU)
            .hoTen(DEFAULT_HO_TEN)
            .namSinh(DEFAULT_NAM_SINH)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .soCMT(DEFAULT_SO_CMT)
            .diaChi(DEFAULT_DIA_CHI)
            .dienThoaiChinh(DEFAULT_DIEN_THOAI_CHINH)
            .dienThoaiPhu(DEFAULT_DIEN_THOAI_PHU)
            .email(DEFAULT_EMAIL)
            .ghiChu(DEFAULT_GHI_CHU)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return hoSoThanNhan;
    }

    @Before
    public void initTest() {
        hoSoThanNhan = createEntity(em);
    }

    @Test
    @Transactional
    public void createHoSoThanNhan() throws Exception {
        int databaseSizeBeforeCreate = hoSoThanNhanRepository.findAll().size();

        // Create the HoSoThanNhan
        restHoSoThanNhanMockMvc.perform(post("/api/ho-so-than-nhans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoThanNhan)))
            .andExpect(status().isCreated());

        // Validate the HoSoThanNhan in the database
        List<HoSoThanNhan> hoSoThanNhanList = hoSoThanNhanRepository.findAll();
        assertThat(hoSoThanNhanList).hasSize(databaseSizeBeforeCreate + 1);
        HoSoThanNhan testHoSoThanNhan = hoSoThanNhanList.get(hoSoThanNhanList.size() - 1);
        assertThat(testHoSoThanNhan.getMaThanNhan()).isEqualTo(DEFAULT_MA_THAN_NHAN);
        assertThat(testHoSoThanNhan.isIsLayMau()).isEqualTo(DEFAULT_IS_LAY_MAU);
        assertThat(testHoSoThanNhan.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testHoSoThanNhan.getNamSinh()).isEqualTo(DEFAULT_NAM_SINH);
        assertThat(testHoSoThanNhan.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testHoSoThanNhan.getSoCMT()).isEqualTo(DEFAULT_SO_CMT);
        assertThat(testHoSoThanNhan.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
        assertThat(testHoSoThanNhan.getDienThoaiChinh()).isEqualTo(DEFAULT_DIEN_THOAI_CHINH);
        assertThat(testHoSoThanNhan.getDienThoaiPhu()).isEqualTo(DEFAULT_DIEN_THOAI_PHU);
        assertThat(testHoSoThanNhan.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testHoSoThanNhan.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testHoSoThanNhan.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testHoSoThanNhan.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testHoSoThanNhan.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testHoSoThanNhan.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the HoSoThanNhan in Elasticsearch
        verify(mockHoSoThanNhanSearchRepository, times(1)).save(testHoSoThanNhan);
    }

    @Test
    @Transactional
    public void createHoSoThanNhanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hoSoThanNhanRepository.findAll().size();

        // Create the HoSoThanNhan with an existing ID
        hoSoThanNhan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoSoThanNhanMockMvc.perform(post("/api/ho-so-than-nhans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoThanNhan)))
            .andExpect(status().isBadRequest());

        // Validate the HoSoThanNhan in the database
        List<HoSoThanNhan> hoSoThanNhanList = hoSoThanNhanRepository.findAll();
        assertThat(hoSoThanNhanList).hasSize(databaseSizeBeforeCreate);

        // Validate the HoSoThanNhan in Elasticsearch
        verify(mockHoSoThanNhanSearchRepository, times(0)).save(hoSoThanNhan);
    }

    @Test
    @Transactional
    public void getAllHoSoThanNhans() throws Exception {
        // Initialize the database
        hoSoThanNhanRepository.saveAndFlush(hoSoThanNhan);

        // Get all the hoSoThanNhanList
        restHoSoThanNhanMockMvc.perform(get("/api/ho-so-than-nhans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoSoThanNhan.getId().intValue())))
            .andExpect(jsonPath("$.[*].maThanNhan").value(hasItem(DEFAULT_MA_THAN_NHAN.toString())))
            .andExpect(jsonPath("$.[*].isLayMau").value(hasItem(DEFAULT_IS_LAY_MAU.booleanValue())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN.toString())))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.toString())))
            .andExpect(jsonPath("$.[*].soCMT").value(hasItem(DEFAULT_SO_CMT.toString())))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI.toString())))
            .andExpect(jsonPath("$.[*].dienThoaiChinh").value(hasItem(DEFAULT_DIEN_THOAI_CHINH.toString())))
            .andExpect(jsonPath("$.[*].dienThoaiPhu").value(hasItem(DEFAULT_DIEN_THOAI_PHU.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getHoSoThanNhan() throws Exception {
        // Initialize the database
        hoSoThanNhanRepository.saveAndFlush(hoSoThanNhan);

        // Get the hoSoThanNhan
        restHoSoThanNhanMockMvc.perform(get("/api/ho-so-than-nhans/{id}", hoSoThanNhan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hoSoThanNhan.getId().intValue()))
            .andExpect(jsonPath("$.maThanNhan").value(DEFAULT_MA_THAN_NHAN.toString()))
            .andExpect(jsonPath("$.isLayMau").value(DEFAULT_IS_LAY_MAU.booleanValue()))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN.toString()))
            .andExpect(jsonPath("$.namSinh").value(DEFAULT_NAM_SINH.toString()))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH.toString()))
            .andExpect(jsonPath("$.soCMT").value(DEFAULT_SO_CMT.toString()))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI.toString()))
            .andExpect(jsonPath("$.dienThoaiChinh").value(DEFAULT_DIEN_THOAI_CHINH.toString()))
            .andExpect(jsonPath("$.dienThoaiPhu").value(DEFAULT_DIEN_THOAI_PHU.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHoSoThanNhan() throws Exception {
        // Get the hoSoThanNhan
        restHoSoThanNhanMockMvc.perform(get("/api/ho-so-than-nhans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHoSoThanNhan() throws Exception {
        // Initialize the database
        hoSoThanNhanRepository.saveAndFlush(hoSoThanNhan);

        int databaseSizeBeforeUpdate = hoSoThanNhanRepository.findAll().size();

        // Update the hoSoThanNhan
        HoSoThanNhan updatedHoSoThanNhan = hoSoThanNhanRepository.findById(hoSoThanNhan.getId()).get();
        // Disconnect from session so that the updates on updatedHoSoThanNhan are not directly saved in db
        em.detach(updatedHoSoThanNhan);
        updatedHoSoThanNhan
            .maThanNhan(UPDATED_MA_THAN_NHAN)
            .isLayMau(UPDATED_IS_LAY_MAU)
            .hoTen(UPDATED_HO_TEN)
            .namSinh(UPDATED_NAM_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .soCMT(UPDATED_SO_CMT)
            .diaChi(UPDATED_DIA_CHI)
            .dienThoaiChinh(UPDATED_DIEN_THOAI_CHINH)
            .dienThoaiPhu(UPDATED_DIEN_THOAI_PHU)
            .email(UPDATED_EMAIL)
            .ghiChu(UPDATED_GHI_CHU)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restHoSoThanNhanMockMvc.perform(put("/api/ho-so-than-nhans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHoSoThanNhan)))
            .andExpect(status().isOk());

        // Validate the HoSoThanNhan in the database
        List<HoSoThanNhan> hoSoThanNhanList = hoSoThanNhanRepository.findAll();
        assertThat(hoSoThanNhanList).hasSize(databaseSizeBeforeUpdate);
        HoSoThanNhan testHoSoThanNhan = hoSoThanNhanList.get(hoSoThanNhanList.size() - 1);
        assertThat(testHoSoThanNhan.getMaThanNhan()).isEqualTo(UPDATED_MA_THAN_NHAN);
        assertThat(testHoSoThanNhan.isIsLayMau()).isEqualTo(UPDATED_IS_LAY_MAU);
        assertThat(testHoSoThanNhan.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testHoSoThanNhan.getNamSinh()).isEqualTo(UPDATED_NAM_SINH);
        assertThat(testHoSoThanNhan.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testHoSoThanNhan.getSoCMT()).isEqualTo(UPDATED_SO_CMT);
        assertThat(testHoSoThanNhan.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testHoSoThanNhan.getDienThoaiChinh()).isEqualTo(UPDATED_DIEN_THOAI_CHINH);
        assertThat(testHoSoThanNhan.getDienThoaiPhu()).isEqualTo(UPDATED_DIEN_THOAI_PHU);
        assertThat(testHoSoThanNhan.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testHoSoThanNhan.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testHoSoThanNhan.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testHoSoThanNhan.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testHoSoThanNhan.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testHoSoThanNhan.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the HoSoThanNhan in Elasticsearch
        verify(mockHoSoThanNhanSearchRepository, times(1)).save(testHoSoThanNhan);
    }

    @Test
    @Transactional
    public void updateNonExistingHoSoThanNhan() throws Exception {
        int databaseSizeBeforeUpdate = hoSoThanNhanRepository.findAll().size();

        // Create the HoSoThanNhan

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoSoThanNhanMockMvc.perform(put("/api/ho-so-than-nhans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoThanNhan)))
            .andExpect(status().isBadRequest());

        // Validate the HoSoThanNhan in the database
        List<HoSoThanNhan> hoSoThanNhanList = hoSoThanNhanRepository.findAll();
        assertThat(hoSoThanNhanList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HoSoThanNhan in Elasticsearch
        verify(mockHoSoThanNhanSearchRepository, times(0)).save(hoSoThanNhan);
    }

    @Test
    @Transactional
    public void deleteHoSoThanNhan() throws Exception {
        // Initialize the database
        hoSoThanNhanRepository.saveAndFlush(hoSoThanNhan);

        int databaseSizeBeforeDelete = hoSoThanNhanRepository.findAll().size();

        // Get the hoSoThanNhan
        restHoSoThanNhanMockMvc.perform(delete("/api/ho-so-than-nhans/{id}", hoSoThanNhan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HoSoThanNhan> hoSoThanNhanList = hoSoThanNhanRepository.findAll();
        assertThat(hoSoThanNhanList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HoSoThanNhan in Elasticsearch
        verify(mockHoSoThanNhanSearchRepository, times(1)).deleteById(hoSoThanNhan.getId());
    }

    @Test
    @Transactional
    public void searchHoSoThanNhan() throws Exception {
        // Initialize the database
        hoSoThanNhanRepository.saveAndFlush(hoSoThanNhan);
        when(mockHoSoThanNhanSearchRepository.search(queryStringQuery("id:" + hoSoThanNhan.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(hoSoThanNhan), PageRequest.of(0, 1), 1));
        // Search the hoSoThanNhan
        restHoSoThanNhanMockMvc.perform(get("/api/_search/ho-so-than-nhans?query=id:" + hoSoThanNhan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoSoThanNhan.getId().intValue())))
            .andExpect(jsonPath("$.[*].maThanNhan").value(hasItem(DEFAULT_MA_THAN_NHAN)))
            .andExpect(jsonPath("$.[*].isLayMau").value(hasItem(DEFAULT_IS_LAY_MAU.booleanValue())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN)))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)))
            .andExpect(jsonPath("$.[*].soCMT").value(hasItem(DEFAULT_SO_CMT)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].dienThoaiChinh").value(hasItem(DEFAULT_DIEN_THOAI_CHINH)))
            .andExpect(jsonPath("$.[*].dienThoaiPhu").value(hasItem(DEFAULT_DIEN_THOAI_PHU)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoSoThanNhan.class);
        HoSoThanNhan hoSoThanNhan1 = new HoSoThanNhan();
        hoSoThanNhan1.setId(1L);
        HoSoThanNhan hoSoThanNhan2 = new HoSoThanNhan();
        hoSoThanNhan2.setId(hoSoThanNhan1.getId());
        assertThat(hoSoThanNhan1).isEqualTo(hoSoThanNhan2);
        hoSoThanNhan2.setId(2L);
        assertThat(hoSoThanNhan1).isNotEqualTo(hoSoThanNhan2);
        hoSoThanNhan1.setId(null);
        assertThat(hoSoThanNhan1).isNotEqualTo(hoSoThanNhan2);
    }
}
