package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.TachChiet;
import vn.homtech.xddt.repository.TachChietRepository;
import vn.homtech.xddt.repository.search.TachChietSearchRepository;
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

import vn.homtech.xddt.domain.enumeration.PhuongPhapLoc;
/**
 * Test class for the TachChietResource REST controller.
 *
 * @see TachChietResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class TachChietResourceIntTest {

    private static final String DEFAULT_MA_TACH_CHIET = "AAAAAAAAAA";
    private static final String UPDATED_MA_TACH_CHIET = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Instant DEFAULT_THOI_GIAN_THUC_HIEN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_THOI_GIAN_THUC_HIEN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final PhuongPhapLoc DEFAULT_PHUONG_PHAP_LOC = PhuongPhapLoc.KIT;
    private static final PhuongPhapLoc UPDATED_PHUONG_PHAP_LOC = PhuongPhapLoc.PHENOL;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private TachChietRepository tachChietRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.TachChietSearchRepositoryMockConfiguration
     */
    @Autowired
    private TachChietSearchRepository mockTachChietSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTachChietMockMvc;

    private TachChiet tachChiet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TachChietResource tachChietResource = new TachChietResource(tachChietRepository, mockTachChietSearchRepository);
        this.restTachChietMockMvc = MockMvcBuilders.standaloneSetup(tachChietResource)
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
    public static TachChiet createEntity(EntityManager em) {
        TachChiet tachChiet = new TachChiet()
            .maTachChiet(DEFAULT_MA_TACH_CHIET)
            .moTa(DEFAULT_MO_TA)
            .ghiChu(DEFAULT_GHI_CHU)
            .thoiGianThucHien(DEFAULT_THOI_GIAN_THUC_HIEN)
            .phuongPhapLoc(DEFAULT_PHUONG_PHAP_LOC)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return tachChiet;
    }

    @Before
    public void initTest() {
        tachChiet = createEntity(em);
    }

    @Test
    @Transactional
    public void createTachChiet() throws Exception {
        int databaseSizeBeforeCreate = tachChietRepository.findAll().size();

        // Create the TachChiet
        restTachChietMockMvc.perform(post("/api/tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tachChiet)))
            .andExpect(status().isCreated());

        // Validate the TachChiet in the database
        List<TachChiet> tachChietList = tachChietRepository.findAll();
        assertThat(tachChietList).hasSize(databaseSizeBeforeCreate + 1);
        TachChiet testTachChiet = tachChietList.get(tachChietList.size() - 1);
        assertThat(testTachChiet.getMaTachChiet()).isEqualTo(DEFAULT_MA_TACH_CHIET);
        assertThat(testTachChiet.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testTachChiet.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testTachChiet.getThoiGianThucHien()).isEqualTo(DEFAULT_THOI_GIAN_THUC_HIEN);
        assertThat(testTachChiet.getPhuongPhapLoc()).isEqualTo(DEFAULT_PHUONG_PHAP_LOC);
        assertThat(testTachChiet.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testTachChiet.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testTachChiet.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testTachChiet.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the TachChiet in Elasticsearch
        verify(mockTachChietSearchRepository, times(1)).save(testTachChiet);
    }

    @Test
    @Transactional
    public void createTachChietWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tachChietRepository.findAll().size();

        // Create the TachChiet with an existing ID
        tachChiet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTachChietMockMvc.perform(post("/api/tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tachChiet)))
            .andExpect(status().isBadRequest());

        // Validate the TachChiet in the database
        List<TachChiet> tachChietList = tachChietRepository.findAll();
        assertThat(tachChietList).hasSize(databaseSizeBeforeCreate);

        // Validate the TachChiet in Elasticsearch
        verify(mockTachChietSearchRepository, times(0)).save(tachChiet);
    }

    @Test
    @Transactional
    public void getAllTachChiets() throws Exception {
        // Initialize the database
        tachChietRepository.saveAndFlush(tachChiet);

        // Get all the tachChietList
        restTachChietMockMvc.perform(get("/api/tach-chiets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tachChiet.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTachChiet").value(hasItem(DEFAULT_MA_TACH_CHIET.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].thoiGianThucHien").value(hasItem(DEFAULT_THOI_GIAN_THUC_HIEN.toString())))
            .andExpect(jsonPath("$.[*].phuongPhapLoc").value(hasItem(DEFAULT_PHUONG_PHAP_LOC.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getTachChiet() throws Exception {
        // Initialize the database
        tachChietRepository.saveAndFlush(tachChiet);

        // Get the tachChiet
        restTachChietMockMvc.perform(get("/api/tach-chiets/{id}", tachChiet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tachChiet.getId().intValue()))
            .andExpect(jsonPath("$.maTachChiet").value(DEFAULT_MA_TACH_CHIET.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.thoiGianThucHien").value(DEFAULT_THOI_GIAN_THUC_HIEN.toString()))
            .andExpect(jsonPath("$.phuongPhapLoc").value(DEFAULT_PHUONG_PHAP_LOC.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTachChiet() throws Exception {
        // Get the tachChiet
        restTachChietMockMvc.perform(get("/api/tach-chiets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTachChiet() throws Exception {
        // Initialize the database
        tachChietRepository.saveAndFlush(tachChiet);

        int databaseSizeBeforeUpdate = tachChietRepository.findAll().size();

        // Update the tachChiet
        TachChiet updatedTachChiet = tachChietRepository.findById(tachChiet.getId()).get();
        // Disconnect from session so that the updates on updatedTachChiet are not directly saved in db
        em.detach(updatedTachChiet);
        updatedTachChiet
            .maTachChiet(UPDATED_MA_TACH_CHIET)
            .moTa(UPDATED_MO_TA)
            .ghiChu(UPDATED_GHI_CHU)
            .thoiGianThucHien(UPDATED_THOI_GIAN_THUC_HIEN)
            .phuongPhapLoc(UPDATED_PHUONG_PHAP_LOC)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restTachChietMockMvc.perform(put("/api/tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTachChiet)))
            .andExpect(status().isOk());

        // Validate the TachChiet in the database
        List<TachChiet> tachChietList = tachChietRepository.findAll();
        assertThat(tachChietList).hasSize(databaseSizeBeforeUpdate);
        TachChiet testTachChiet = tachChietList.get(tachChietList.size() - 1);
        assertThat(testTachChiet.getMaTachChiet()).isEqualTo(UPDATED_MA_TACH_CHIET);
        assertThat(testTachChiet.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testTachChiet.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testTachChiet.getThoiGianThucHien()).isEqualTo(UPDATED_THOI_GIAN_THUC_HIEN);
        assertThat(testTachChiet.getPhuongPhapLoc()).isEqualTo(UPDATED_PHUONG_PHAP_LOC);
        assertThat(testTachChiet.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testTachChiet.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testTachChiet.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testTachChiet.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the TachChiet in Elasticsearch
        verify(mockTachChietSearchRepository, times(1)).save(testTachChiet);
    }

    @Test
    @Transactional
    public void updateNonExistingTachChiet() throws Exception {
        int databaseSizeBeforeUpdate = tachChietRepository.findAll().size();

        // Create the TachChiet

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTachChietMockMvc.perform(put("/api/tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tachChiet)))
            .andExpect(status().isBadRequest());

        // Validate the TachChiet in the database
        List<TachChiet> tachChietList = tachChietRepository.findAll();
        assertThat(tachChietList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TachChiet in Elasticsearch
        verify(mockTachChietSearchRepository, times(0)).save(tachChiet);
    }

    @Test
    @Transactional
    public void deleteTachChiet() throws Exception {
        // Initialize the database
        tachChietRepository.saveAndFlush(tachChiet);

        int databaseSizeBeforeDelete = tachChietRepository.findAll().size();

        // Get the tachChiet
        restTachChietMockMvc.perform(delete("/api/tach-chiets/{id}", tachChiet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TachChiet> tachChietList = tachChietRepository.findAll();
        assertThat(tachChietList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TachChiet in Elasticsearch
        verify(mockTachChietSearchRepository, times(1)).deleteById(tachChiet.getId());
    }

    @Test
    @Transactional
    public void searchTachChiet() throws Exception {
        // Initialize the database
        tachChietRepository.saveAndFlush(tachChiet);
        when(mockTachChietSearchRepository.search(queryStringQuery("id:" + tachChiet.getId())))
            .thenReturn(Collections.singletonList(tachChiet));
        // Search the tachChiet
        restTachChietMockMvc.perform(get("/api/_search/tach-chiets?query=id:" + tachChiet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tachChiet.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTachChiet").value(hasItem(DEFAULT_MA_TACH_CHIET)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].thoiGianThucHien").value(hasItem(DEFAULT_THOI_GIAN_THUC_HIEN.toString())))
            .andExpect(jsonPath("$.[*].phuongPhapLoc").value(hasItem(DEFAULT_PHUONG_PHAP_LOC.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TachChiet.class);
        TachChiet tachChiet1 = new TachChiet();
        tachChiet1.setId(1L);
        TachChiet tachChiet2 = new TachChiet();
        tachChiet2.setId(tachChiet1.getId());
        assertThat(tachChiet1).isEqualTo(tachChiet2);
        tachChiet2.setId(2L);
        assertThat(tachChiet1).isNotEqualTo(tachChiet2);
        tachChiet1.setId(null);
        assertThat(tachChiet1).isNotEqualTo(tachChiet2);
    }
}
