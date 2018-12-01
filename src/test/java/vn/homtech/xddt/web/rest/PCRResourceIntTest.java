package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.PCR;
import vn.homtech.xddt.repository.PCRRepository;
import vn.homtech.xddt.repository.search.PCRSearchRepository;
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
 * Test class for the PCRResource REST controller.
 *
 * @see PCRResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class PCRResourceIntTest {

    private static final String DEFAULT_MA_PCR = "AAAAAAAAAA";
    private static final String UPDATED_MA_PCR = "BBBBBBBBBB";

    private static final Instant DEFAULT_THOI_GIAN_THUC_HIEN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_THOI_GIAN_THUC_HIEN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NHAN_XET = "AAAAAAAAAA";
    private static final String UPDATED_NHAN_XET = "BBBBBBBBBB";

    private static final Instant DEFAULT_THOI_GIAN_BAT_DAU = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_THOI_GIAN_BAT_DAU = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_CONG_SUAT_BAT_DAU = 1F;
    private static final Float UPDATED_CONG_SUAT_BAT_DAU = 2F;

    private static final Float DEFAULT_CUONG_DO_BAT_DAU = 1F;
    private static final Float UPDATED_CUONG_DO_BAT_DAU = 2F;

    private static final Float DEFAULT_DIEN_THE_BAT_DAU = 1F;
    private static final Float UPDATED_DIEN_THE_BAT_DAU = 2F;

    private static final Instant DEFAULT_THOI_GIAN_KET_THUC = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_THOI_GIAN_KET_THUC = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_CONG_SUAT_KET_THUC = 1F;
    private static final Float UPDATED_CONG_SUAT_KET_THUC = 2F;

    private static final Float DEFAULT_CUONG_DO_KET_THUC = 1F;
    private static final Float UPDATED_CUONG_DO_KET_THUC = 2F;

    private static final Float DEFAULT_DIEN_THE_KET_THUC = 1F;
    private static final Float UPDATED_DIEN_THE_KET_THUC = 2F;

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
    private PCRRepository pCRRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.PCRSearchRepositoryMockConfiguration
     */
    @Autowired
    private PCRSearchRepository mockPCRSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPCRMockMvc;

    private PCR pCR;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PCRResource pCRResource = new PCRResource(pCRRepository, mockPCRSearchRepository);
        this.restPCRMockMvc = MockMvcBuilders.standaloneSetup(pCRResource)
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
    public static PCR createEntity(EntityManager em) {
        PCR pCR = new PCR()
            .maPCR(DEFAULT_MA_PCR)
            .thoiGianThucHien(DEFAULT_THOI_GIAN_THUC_HIEN)
            .nhanXet(DEFAULT_NHAN_XET)
            .thoiGianBatDau(DEFAULT_THOI_GIAN_BAT_DAU)
            .congSuatBatDau(DEFAULT_CONG_SUAT_BAT_DAU)
            .cuongDoBatDau(DEFAULT_CUONG_DO_BAT_DAU)
            .dienTheBatDau(DEFAULT_DIEN_THE_BAT_DAU)
            .thoiGianKetThuc(DEFAULT_THOI_GIAN_KET_THUC)
            .congSuatKetThuc(DEFAULT_CONG_SUAT_KET_THUC)
            .cuongDoKetThuc(DEFAULT_CUONG_DO_KET_THUC)
            .dienTheKetThuc(DEFAULT_DIEN_THE_KET_THUC)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3)
            .udf4(DEFAULT_UDF_4)
            .udf5(DEFAULT_UDF_5);
        return pCR;
    }

    @Before
    public void initTest() {
        pCR = createEntity(em);
    }

    @Test
    @Transactional
    public void createPCR() throws Exception {
        int databaseSizeBeforeCreate = pCRRepository.findAll().size();

        // Create the PCR
        restPCRMockMvc.perform(post("/api/pcrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCR)))
            .andExpect(status().isCreated());

        // Validate the PCR in the database
        List<PCR> pCRList = pCRRepository.findAll();
        assertThat(pCRList).hasSize(databaseSizeBeforeCreate + 1);
        PCR testPCR = pCRList.get(pCRList.size() - 1);
        assertThat(testPCR.getMaPCR()).isEqualTo(DEFAULT_MA_PCR);
        assertThat(testPCR.getThoiGianThucHien()).isEqualTo(DEFAULT_THOI_GIAN_THUC_HIEN);
        assertThat(testPCR.getNhanXet()).isEqualTo(DEFAULT_NHAN_XET);
        assertThat(testPCR.getThoiGianBatDau()).isEqualTo(DEFAULT_THOI_GIAN_BAT_DAU);
        assertThat(testPCR.getCongSuatBatDau()).isEqualTo(DEFAULT_CONG_SUAT_BAT_DAU);
        assertThat(testPCR.getCuongDoBatDau()).isEqualTo(DEFAULT_CUONG_DO_BAT_DAU);
        assertThat(testPCR.getDienTheBatDau()).isEqualTo(DEFAULT_DIEN_THE_BAT_DAU);
        assertThat(testPCR.getThoiGianKetThuc()).isEqualTo(DEFAULT_THOI_GIAN_KET_THUC);
        assertThat(testPCR.getCongSuatKetThuc()).isEqualTo(DEFAULT_CONG_SUAT_KET_THUC);
        assertThat(testPCR.getCuongDoKetThuc()).isEqualTo(DEFAULT_CUONG_DO_KET_THUC);
        assertThat(testPCR.getDienTheKetThuc()).isEqualTo(DEFAULT_DIEN_THE_KET_THUC);
        assertThat(testPCR.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testPCR.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testPCR.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testPCR.getUdf3()).isEqualTo(DEFAULT_UDF_3);
        assertThat(testPCR.getUdf4()).isEqualTo(DEFAULT_UDF_4);
        assertThat(testPCR.getUdf5()).isEqualTo(DEFAULT_UDF_5);

        // Validate the PCR in Elasticsearch
        verify(mockPCRSearchRepository, times(1)).save(testPCR);
    }

    @Test
    @Transactional
    public void createPCRWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pCRRepository.findAll().size();

        // Create the PCR with an existing ID
        pCR.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPCRMockMvc.perform(post("/api/pcrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCR)))
            .andExpect(status().isBadRequest());

        // Validate the PCR in the database
        List<PCR> pCRList = pCRRepository.findAll();
        assertThat(pCRList).hasSize(databaseSizeBeforeCreate);

        // Validate the PCR in Elasticsearch
        verify(mockPCRSearchRepository, times(0)).save(pCR);
    }

    @Test
    @Transactional
    public void getAllPCRS() throws Exception {
        // Initialize the database
        pCRRepository.saveAndFlush(pCR);

        // Get all the pCRList
        restPCRMockMvc.perform(get("/api/pcrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCR.getId().intValue())))
            .andExpect(jsonPath("$.[*].maPCR").value(hasItem(DEFAULT_MA_PCR.toString())))
            .andExpect(jsonPath("$.[*].thoiGianThucHien").value(hasItem(DEFAULT_THOI_GIAN_THUC_HIEN.toString())))
            .andExpect(jsonPath("$.[*].nhanXet").value(hasItem(DEFAULT_NHAN_XET.toString())))
            .andExpect(jsonPath("$.[*].thoiGianBatDau").value(hasItem(DEFAULT_THOI_GIAN_BAT_DAU.toString())))
            .andExpect(jsonPath("$.[*].congSuatBatDau").value(hasItem(DEFAULT_CONG_SUAT_BAT_DAU.doubleValue())))
            .andExpect(jsonPath("$.[*].cuongDoBatDau").value(hasItem(DEFAULT_CUONG_DO_BAT_DAU.doubleValue())))
            .andExpect(jsonPath("$.[*].dienTheBatDau").value(hasItem(DEFAULT_DIEN_THE_BAT_DAU.doubleValue())))
            .andExpect(jsonPath("$.[*].thoiGianKetThuc").value(hasItem(DEFAULT_THOI_GIAN_KET_THUC.toString())))
            .andExpect(jsonPath("$.[*].congSuatKetThuc").value(hasItem(DEFAULT_CONG_SUAT_KET_THUC.doubleValue())))
            .andExpect(jsonPath("$.[*].cuongDoKetThuc").value(hasItem(DEFAULT_CUONG_DO_KET_THUC.doubleValue())))
            .andExpect(jsonPath("$.[*].dienTheKetThuc").value(hasItem(DEFAULT_DIEN_THE_KET_THUC.doubleValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())))
            .andExpect(jsonPath("$.[*].udf4").value(hasItem(DEFAULT_UDF_4.toString())))
            .andExpect(jsonPath("$.[*].udf5").value(hasItem(DEFAULT_UDF_5.toString())));
    }
    
    @Test
    @Transactional
    public void getPCR() throws Exception {
        // Initialize the database
        pCRRepository.saveAndFlush(pCR);

        // Get the pCR
        restPCRMockMvc.perform(get("/api/pcrs/{id}", pCR.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pCR.getId().intValue()))
            .andExpect(jsonPath("$.maPCR").value(DEFAULT_MA_PCR.toString()))
            .andExpect(jsonPath("$.thoiGianThucHien").value(DEFAULT_THOI_GIAN_THUC_HIEN.toString()))
            .andExpect(jsonPath("$.nhanXet").value(DEFAULT_NHAN_XET.toString()))
            .andExpect(jsonPath("$.thoiGianBatDau").value(DEFAULT_THOI_GIAN_BAT_DAU.toString()))
            .andExpect(jsonPath("$.congSuatBatDau").value(DEFAULT_CONG_SUAT_BAT_DAU.doubleValue()))
            .andExpect(jsonPath("$.cuongDoBatDau").value(DEFAULT_CUONG_DO_BAT_DAU.doubleValue()))
            .andExpect(jsonPath("$.dienTheBatDau").value(DEFAULT_DIEN_THE_BAT_DAU.doubleValue()))
            .andExpect(jsonPath("$.thoiGianKetThuc").value(DEFAULT_THOI_GIAN_KET_THUC.toString()))
            .andExpect(jsonPath("$.congSuatKetThuc").value(DEFAULT_CONG_SUAT_KET_THUC.doubleValue()))
            .andExpect(jsonPath("$.cuongDoKetThuc").value(DEFAULT_CUONG_DO_KET_THUC.doubleValue()))
            .andExpect(jsonPath("$.dienTheKetThuc").value(DEFAULT_DIEN_THE_KET_THUC.doubleValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()))
            .andExpect(jsonPath("$.udf4").value(DEFAULT_UDF_4.toString()))
            .andExpect(jsonPath("$.udf5").value(DEFAULT_UDF_5.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPCR() throws Exception {
        // Get the pCR
        restPCRMockMvc.perform(get("/api/pcrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePCR() throws Exception {
        // Initialize the database
        pCRRepository.saveAndFlush(pCR);

        int databaseSizeBeforeUpdate = pCRRepository.findAll().size();

        // Update the pCR
        PCR updatedPCR = pCRRepository.findById(pCR.getId()).get();
        // Disconnect from session so that the updates on updatedPCR are not directly saved in db
        em.detach(updatedPCR);
        updatedPCR
            .maPCR(UPDATED_MA_PCR)
            .thoiGianThucHien(UPDATED_THOI_GIAN_THUC_HIEN)
            .nhanXet(UPDATED_NHAN_XET)
            .thoiGianBatDau(UPDATED_THOI_GIAN_BAT_DAU)
            .congSuatBatDau(UPDATED_CONG_SUAT_BAT_DAU)
            .cuongDoBatDau(UPDATED_CUONG_DO_BAT_DAU)
            .dienTheBatDau(UPDATED_DIEN_THE_BAT_DAU)
            .thoiGianKetThuc(UPDATED_THOI_GIAN_KET_THUC)
            .congSuatKetThuc(UPDATED_CONG_SUAT_KET_THUC)
            .cuongDoKetThuc(UPDATED_CUONG_DO_KET_THUC)
            .dienTheKetThuc(UPDATED_DIEN_THE_KET_THUC)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3)
            .udf4(UPDATED_UDF_4)
            .udf5(UPDATED_UDF_5);

        restPCRMockMvc.perform(put("/api/pcrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPCR)))
            .andExpect(status().isOk());

        // Validate the PCR in the database
        List<PCR> pCRList = pCRRepository.findAll();
        assertThat(pCRList).hasSize(databaseSizeBeforeUpdate);
        PCR testPCR = pCRList.get(pCRList.size() - 1);
        assertThat(testPCR.getMaPCR()).isEqualTo(UPDATED_MA_PCR);
        assertThat(testPCR.getThoiGianThucHien()).isEqualTo(UPDATED_THOI_GIAN_THUC_HIEN);
        assertThat(testPCR.getNhanXet()).isEqualTo(UPDATED_NHAN_XET);
        assertThat(testPCR.getThoiGianBatDau()).isEqualTo(UPDATED_THOI_GIAN_BAT_DAU);
        assertThat(testPCR.getCongSuatBatDau()).isEqualTo(UPDATED_CONG_SUAT_BAT_DAU);
        assertThat(testPCR.getCuongDoBatDau()).isEqualTo(UPDATED_CUONG_DO_BAT_DAU);
        assertThat(testPCR.getDienTheBatDau()).isEqualTo(UPDATED_DIEN_THE_BAT_DAU);
        assertThat(testPCR.getThoiGianKetThuc()).isEqualTo(UPDATED_THOI_GIAN_KET_THUC);
        assertThat(testPCR.getCongSuatKetThuc()).isEqualTo(UPDATED_CONG_SUAT_KET_THUC);
        assertThat(testPCR.getCuongDoKetThuc()).isEqualTo(UPDATED_CUONG_DO_KET_THUC);
        assertThat(testPCR.getDienTheKetThuc()).isEqualTo(UPDATED_DIEN_THE_KET_THUC);
        assertThat(testPCR.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testPCR.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testPCR.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testPCR.getUdf3()).isEqualTo(UPDATED_UDF_3);
        assertThat(testPCR.getUdf4()).isEqualTo(UPDATED_UDF_4);
        assertThat(testPCR.getUdf5()).isEqualTo(UPDATED_UDF_5);

        // Validate the PCR in Elasticsearch
        verify(mockPCRSearchRepository, times(1)).save(testPCR);
    }

    @Test
    @Transactional
    public void updateNonExistingPCR() throws Exception {
        int databaseSizeBeforeUpdate = pCRRepository.findAll().size();

        // Create the PCR

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPCRMockMvc.perform(put("/api/pcrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pCR)))
            .andExpect(status().isBadRequest());

        // Validate the PCR in the database
        List<PCR> pCRList = pCRRepository.findAll();
        assertThat(pCRList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PCR in Elasticsearch
        verify(mockPCRSearchRepository, times(0)).save(pCR);
    }

    @Test
    @Transactional
    public void deletePCR() throws Exception {
        // Initialize the database
        pCRRepository.saveAndFlush(pCR);

        int databaseSizeBeforeDelete = pCRRepository.findAll().size();

        // Get the pCR
        restPCRMockMvc.perform(delete("/api/pcrs/{id}", pCR.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PCR> pCRList = pCRRepository.findAll();
        assertThat(pCRList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PCR in Elasticsearch
        verify(mockPCRSearchRepository, times(1)).deleteById(pCR.getId());
    }

    @Test
    @Transactional
    public void searchPCR() throws Exception {
        // Initialize the database
        pCRRepository.saveAndFlush(pCR);
        when(mockPCRSearchRepository.search(queryStringQuery("id:" + pCR.getId())))
            .thenReturn(Collections.singletonList(pCR));
        // Search the pCR
        restPCRMockMvc.perform(get("/api/_search/pcrs?query=id:" + pCR.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pCR.getId().intValue())))
            .andExpect(jsonPath("$.[*].maPCR").value(hasItem(DEFAULT_MA_PCR)))
            .andExpect(jsonPath("$.[*].thoiGianThucHien").value(hasItem(DEFAULT_THOI_GIAN_THUC_HIEN.toString())))
            .andExpect(jsonPath("$.[*].nhanXet").value(hasItem(DEFAULT_NHAN_XET)))
            .andExpect(jsonPath("$.[*].thoiGianBatDau").value(hasItem(DEFAULT_THOI_GIAN_BAT_DAU.toString())))
            .andExpect(jsonPath("$.[*].congSuatBatDau").value(hasItem(DEFAULT_CONG_SUAT_BAT_DAU.doubleValue())))
            .andExpect(jsonPath("$.[*].cuongDoBatDau").value(hasItem(DEFAULT_CUONG_DO_BAT_DAU.doubleValue())))
            .andExpect(jsonPath("$.[*].dienTheBatDau").value(hasItem(DEFAULT_DIEN_THE_BAT_DAU.doubleValue())))
            .andExpect(jsonPath("$.[*].thoiGianKetThuc").value(hasItem(DEFAULT_THOI_GIAN_KET_THUC.toString())))
            .andExpect(jsonPath("$.[*].congSuatKetThuc").value(hasItem(DEFAULT_CONG_SUAT_KET_THUC.doubleValue())))
            .andExpect(jsonPath("$.[*].cuongDoKetThuc").value(hasItem(DEFAULT_CUONG_DO_KET_THUC.doubleValue())))
            .andExpect(jsonPath("$.[*].dienTheKetThuc").value(hasItem(DEFAULT_DIEN_THE_KET_THUC.doubleValue())))
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
        TestUtil.equalsVerifier(PCR.class);
        PCR pCR1 = new PCR();
        pCR1.setId(1L);
        PCR pCR2 = new PCR();
        pCR2.setId(pCR1.getId());
        assertThat(pCR1).isEqualTo(pCR2);
        pCR2.setId(2L);
        assertThat(pCR1).isNotEqualTo(pCR2);
        pCR1.setId(null);
        assertThat(pCR1).isNotEqualTo(pCR2);
    }
}
