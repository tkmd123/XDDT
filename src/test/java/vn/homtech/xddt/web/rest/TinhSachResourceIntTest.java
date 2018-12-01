package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.TinhSach;
import vn.homtech.xddt.repository.TinhSachRepository;
import vn.homtech.xddt.repository.search.TinhSachSearchRepository;
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

import vn.homtech.xddt.domain.enumeration.PhuongPhapTinhSach;
/**
 * Test class for the TinhSachResource REST controller.
 *
 * @see TinhSachResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class TinhSachResourceIntTest {

    private static final String DEFAULT_MA_TINH_SACH = "AAAAAAAAAA";
    private static final String UPDATED_MA_TINH_SACH = "BBBBBBBBBB";

    private static final Instant DEFAULT_THOI_GIAN_THUC_HIEN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_THOI_GIAN_THUC_HIEN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final PhuongPhapTinhSach DEFAULT_PHUONG_PHAP_TINH_SACH = PhuongPhapTinhSach.ENZYM;
    private static final PhuongPhapTinhSach UPDATED_PHUONG_PHAP_TINH_SACH = PhuongPhapTinhSach.KIT;

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
    private TinhSachRepository tinhSachRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.TinhSachSearchRepositoryMockConfiguration
     */
    @Autowired
    private TinhSachSearchRepository mockTinhSachSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTinhSachMockMvc;

    private TinhSach tinhSach;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TinhSachResource tinhSachResource = new TinhSachResource(tinhSachRepository, mockTinhSachSearchRepository);
        this.restTinhSachMockMvc = MockMvcBuilders.standaloneSetup(tinhSachResource)
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
    public static TinhSach createEntity(EntityManager em) {
        TinhSach tinhSach = new TinhSach()
            .maTinhSach(DEFAULT_MA_TINH_SACH)
            .thoiGianThucHien(DEFAULT_THOI_GIAN_THUC_HIEN)
            .phuongPhapTinhSach(DEFAULT_PHUONG_PHAP_TINH_SACH)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return tinhSach;
    }

    @Before
    public void initTest() {
        tinhSach = createEntity(em);
    }

    @Test
    @Transactional
    public void createTinhSach() throws Exception {
        int databaseSizeBeforeCreate = tinhSachRepository.findAll().size();

        // Create the TinhSach
        restTinhSachMockMvc.perform(post("/api/tinh-saches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhSach)))
            .andExpect(status().isCreated());

        // Validate the TinhSach in the database
        List<TinhSach> tinhSachList = tinhSachRepository.findAll();
        assertThat(tinhSachList).hasSize(databaseSizeBeforeCreate + 1);
        TinhSach testTinhSach = tinhSachList.get(tinhSachList.size() - 1);
        assertThat(testTinhSach.getMaTinhSach()).isEqualTo(DEFAULT_MA_TINH_SACH);
        assertThat(testTinhSach.getThoiGianThucHien()).isEqualTo(DEFAULT_THOI_GIAN_THUC_HIEN);
        assertThat(testTinhSach.getPhuongPhapTinhSach()).isEqualTo(DEFAULT_PHUONG_PHAP_TINH_SACH);
        assertThat(testTinhSach.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testTinhSach.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testTinhSach.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testTinhSach.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testTinhSach.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the TinhSach in Elasticsearch
        verify(mockTinhSachSearchRepository, times(1)).save(testTinhSach);
    }

    @Test
    @Transactional
    public void createTinhSachWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tinhSachRepository.findAll().size();

        // Create the TinhSach with an existing ID
        tinhSach.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTinhSachMockMvc.perform(post("/api/tinh-saches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhSach)))
            .andExpect(status().isBadRequest());

        // Validate the TinhSach in the database
        List<TinhSach> tinhSachList = tinhSachRepository.findAll();
        assertThat(tinhSachList).hasSize(databaseSizeBeforeCreate);

        // Validate the TinhSach in Elasticsearch
        verify(mockTinhSachSearchRepository, times(0)).save(tinhSach);
    }

    @Test
    @Transactional
    public void getAllTinhSaches() throws Exception {
        // Initialize the database
        tinhSachRepository.saveAndFlush(tinhSach);

        // Get all the tinhSachList
        restTinhSachMockMvc.perform(get("/api/tinh-saches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinhSach.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTinhSach").value(hasItem(DEFAULT_MA_TINH_SACH.toString())))
            .andExpect(jsonPath("$.[*].thoiGianThucHien").value(hasItem(DEFAULT_THOI_GIAN_THUC_HIEN.toString())))
            .andExpect(jsonPath("$.[*].phuongPhapTinhSach").value(hasItem(DEFAULT_PHUONG_PHAP_TINH_SACH.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getTinhSach() throws Exception {
        // Initialize the database
        tinhSachRepository.saveAndFlush(tinhSach);

        // Get the tinhSach
        restTinhSachMockMvc.perform(get("/api/tinh-saches/{id}", tinhSach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tinhSach.getId().intValue()))
            .andExpect(jsonPath("$.maTinhSach").value(DEFAULT_MA_TINH_SACH.toString()))
            .andExpect(jsonPath("$.thoiGianThucHien").value(DEFAULT_THOI_GIAN_THUC_HIEN.toString()))
            .andExpect(jsonPath("$.phuongPhapTinhSach").value(DEFAULT_PHUONG_PHAP_TINH_SACH.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTinhSach() throws Exception {
        // Get the tinhSach
        restTinhSachMockMvc.perform(get("/api/tinh-saches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTinhSach() throws Exception {
        // Initialize the database
        tinhSachRepository.saveAndFlush(tinhSach);

        int databaseSizeBeforeUpdate = tinhSachRepository.findAll().size();

        // Update the tinhSach
        TinhSach updatedTinhSach = tinhSachRepository.findById(tinhSach.getId()).get();
        // Disconnect from session so that the updates on updatedTinhSach are not directly saved in db
        em.detach(updatedTinhSach);
        updatedTinhSach
            .maTinhSach(UPDATED_MA_TINH_SACH)
            .thoiGianThucHien(UPDATED_THOI_GIAN_THUC_HIEN)
            .phuongPhapTinhSach(UPDATED_PHUONG_PHAP_TINH_SACH)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restTinhSachMockMvc.perform(put("/api/tinh-saches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTinhSach)))
            .andExpect(status().isOk());

        // Validate the TinhSach in the database
        List<TinhSach> tinhSachList = tinhSachRepository.findAll();
        assertThat(tinhSachList).hasSize(databaseSizeBeforeUpdate);
        TinhSach testTinhSach = tinhSachList.get(tinhSachList.size() - 1);
        assertThat(testTinhSach.getMaTinhSach()).isEqualTo(UPDATED_MA_TINH_SACH);
        assertThat(testTinhSach.getThoiGianThucHien()).isEqualTo(UPDATED_THOI_GIAN_THUC_HIEN);
        assertThat(testTinhSach.getPhuongPhapTinhSach()).isEqualTo(UPDATED_PHUONG_PHAP_TINH_SACH);
        assertThat(testTinhSach.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testTinhSach.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testTinhSach.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testTinhSach.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testTinhSach.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the TinhSach in Elasticsearch
        verify(mockTinhSachSearchRepository, times(1)).save(testTinhSach);
    }

    @Test
    @Transactional
    public void updateNonExistingTinhSach() throws Exception {
        int databaseSizeBeforeUpdate = tinhSachRepository.findAll().size();

        // Create the TinhSach

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTinhSachMockMvc.perform(put("/api/tinh-saches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhSach)))
            .andExpect(status().isBadRequest());

        // Validate the TinhSach in the database
        List<TinhSach> tinhSachList = tinhSachRepository.findAll();
        assertThat(tinhSachList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TinhSach in Elasticsearch
        verify(mockTinhSachSearchRepository, times(0)).save(tinhSach);
    }

    @Test
    @Transactional
    public void deleteTinhSach() throws Exception {
        // Initialize the database
        tinhSachRepository.saveAndFlush(tinhSach);

        int databaseSizeBeforeDelete = tinhSachRepository.findAll().size();

        // Get the tinhSach
        restTinhSachMockMvc.perform(delete("/api/tinh-saches/{id}", tinhSach.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TinhSach> tinhSachList = tinhSachRepository.findAll();
        assertThat(tinhSachList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TinhSach in Elasticsearch
        verify(mockTinhSachSearchRepository, times(1)).deleteById(tinhSach.getId());
    }

    @Test
    @Transactional
    public void searchTinhSach() throws Exception {
        // Initialize the database
        tinhSachRepository.saveAndFlush(tinhSach);
        when(mockTinhSachSearchRepository.search(queryStringQuery("id:" + tinhSach.getId())))
            .thenReturn(Collections.singletonList(tinhSach));
        // Search the tinhSach
        restTinhSachMockMvc.perform(get("/api/_search/tinh-saches?query=id:" + tinhSach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinhSach.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTinhSach").value(hasItem(DEFAULT_MA_TINH_SACH)))
            .andExpect(jsonPath("$.[*].thoiGianThucHien").value(hasItem(DEFAULT_THOI_GIAN_THUC_HIEN.toString())))
            .andExpect(jsonPath("$.[*].phuongPhapTinhSach").value(hasItem(DEFAULT_PHUONG_PHAP_TINH_SACH.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhSach.class);
        TinhSach tinhSach1 = new TinhSach();
        tinhSach1.setId(1L);
        TinhSach tinhSach2 = new TinhSach();
        tinhSach2.setId(tinhSach1.getId());
        assertThat(tinhSach1).isEqualTo(tinhSach2);
        tinhSach2.setId(2L);
        assertThat(tinhSach1).isNotEqualTo(tinhSach2);
        tinhSach1.setId(null);
        assertThat(tinhSach1).isNotEqualTo(tinhSach2);
    }
}
