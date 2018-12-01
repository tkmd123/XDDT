package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.TinhSachPhanUng;
import vn.homtech.xddt.repository.TinhSachPhanUngRepository;
import vn.homtech.xddt.repository.search.TinhSachPhanUngSearchRepository;
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
 * Test class for the TinhSachPhanUngResource REST controller.
 *
 * @see TinhSachPhanUngResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class TinhSachPhanUngResourceIntTest {

    private static final Float DEFAULT_DUNG_TICH_SU_DUNG = 1F;
    private static final Float UPDATED_DUNG_TICH_SU_DUNG = 2F;

    private static final String DEFAULT_CHU_TRINH_NHIET_DO = "AAAAAAAAAA";
    private static final String UPDATED_CHU_TRINH_NHIET_DO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private TinhSachPhanUngRepository tinhSachPhanUngRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.TinhSachPhanUngSearchRepositoryMockConfiguration
     */
    @Autowired
    private TinhSachPhanUngSearchRepository mockTinhSachPhanUngSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTinhSachPhanUngMockMvc;

    private TinhSachPhanUng tinhSachPhanUng;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TinhSachPhanUngResource tinhSachPhanUngResource = new TinhSachPhanUngResource(tinhSachPhanUngRepository, mockTinhSachPhanUngSearchRepository);
        this.restTinhSachPhanUngMockMvc = MockMvcBuilders.standaloneSetup(tinhSachPhanUngResource)
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
    public static TinhSachPhanUng createEntity(EntityManager em) {
        TinhSachPhanUng tinhSachPhanUng = new TinhSachPhanUng()
            .dungTichSuDung(DEFAULT_DUNG_TICH_SU_DUNG)
            .chuTrinhNhietDo(DEFAULT_CHU_TRINH_NHIET_DO)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return tinhSachPhanUng;
    }

    @Before
    public void initTest() {
        tinhSachPhanUng = createEntity(em);
    }

    @Test
    @Transactional
    public void createTinhSachPhanUng() throws Exception {
        int databaseSizeBeforeCreate = tinhSachPhanUngRepository.findAll().size();

        // Create the TinhSachPhanUng
        restTinhSachPhanUngMockMvc.perform(post("/api/tinh-sach-phan-ungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhSachPhanUng)))
            .andExpect(status().isCreated());

        // Validate the TinhSachPhanUng in the database
        List<TinhSachPhanUng> tinhSachPhanUngList = tinhSachPhanUngRepository.findAll();
        assertThat(tinhSachPhanUngList).hasSize(databaseSizeBeforeCreate + 1);
        TinhSachPhanUng testTinhSachPhanUng = tinhSachPhanUngList.get(tinhSachPhanUngList.size() - 1);
        assertThat(testTinhSachPhanUng.getDungTichSuDung()).isEqualTo(DEFAULT_DUNG_TICH_SU_DUNG);
        assertThat(testTinhSachPhanUng.getChuTrinhNhietDo()).isEqualTo(DEFAULT_CHU_TRINH_NHIET_DO);
        assertThat(testTinhSachPhanUng.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testTinhSachPhanUng.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testTinhSachPhanUng.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testTinhSachPhanUng.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the TinhSachPhanUng in Elasticsearch
        verify(mockTinhSachPhanUngSearchRepository, times(1)).save(testTinhSachPhanUng);
    }

    @Test
    @Transactional
    public void createTinhSachPhanUngWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tinhSachPhanUngRepository.findAll().size();

        // Create the TinhSachPhanUng with an existing ID
        tinhSachPhanUng.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTinhSachPhanUngMockMvc.perform(post("/api/tinh-sach-phan-ungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhSachPhanUng)))
            .andExpect(status().isBadRequest());

        // Validate the TinhSachPhanUng in the database
        List<TinhSachPhanUng> tinhSachPhanUngList = tinhSachPhanUngRepository.findAll();
        assertThat(tinhSachPhanUngList).hasSize(databaseSizeBeforeCreate);

        // Validate the TinhSachPhanUng in Elasticsearch
        verify(mockTinhSachPhanUngSearchRepository, times(0)).save(tinhSachPhanUng);
    }

    @Test
    @Transactional
    public void getAllTinhSachPhanUngs() throws Exception {
        // Initialize the database
        tinhSachPhanUngRepository.saveAndFlush(tinhSachPhanUng);

        // Get all the tinhSachPhanUngList
        restTinhSachPhanUngMockMvc.perform(get("/api/tinh-sach-phan-ungs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinhSachPhanUng.getId().intValue())))
            .andExpect(jsonPath("$.[*].dungTichSuDung").value(hasItem(DEFAULT_DUNG_TICH_SU_DUNG.doubleValue())))
            .andExpect(jsonPath("$.[*].chuTrinhNhietDo").value(hasItem(DEFAULT_CHU_TRINH_NHIET_DO.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getTinhSachPhanUng() throws Exception {
        // Initialize the database
        tinhSachPhanUngRepository.saveAndFlush(tinhSachPhanUng);

        // Get the tinhSachPhanUng
        restTinhSachPhanUngMockMvc.perform(get("/api/tinh-sach-phan-ungs/{id}", tinhSachPhanUng.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tinhSachPhanUng.getId().intValue()))
            .andExpect(jsonPath("$.dungTichSuDung").value(DEFAULT_DUNG_TICH_SU_DUNG.doubleValue()))
            .andExpect(jsonPath("$.chuTrinhNhietDo").value(DEFAULT_CHU_TRINH_NHIET_DO.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTinhSachPhanUng() throws Exception {
        // Get the tinhSachPhanUng
        restTinhSachPhanUngMockMvc.perform(get("/api/tinh-sach-phan-ungs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTinhSachPhanUng() throws Exception {
        // Initialize the database
        tinhSachPhanUngRepository.saveAndFlush(tinhSachPhanUng);

        int databaseSizeBeforeUpdate = tinhSachPhanUngRepository.findAll().size();

        // Update the tinhSachPhanUng
        TinhSachPhanUng updatedTinhSachPhanUng = tinhSachPhanUngRepository.findById(tinhSachPhanUng.getId()).get();
        // Disconnect from session so that the updates on updatedTinhSachPhanUng are not directly saved in db
        em.detach(updatedTinhSachPhanUng);
        updatedTinhSachPhanUng
            .dungTichSuDung(UPDATED_DUNG_TICH_SU_DUNG)
            .chuTrinhNhietDo(UPDATED_CHU_TRINH_NHIET_DO)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restTinhSachPhanUngMockMvc.perform(put("/api/tinh-sach-phan-ungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTinhSachPhanUng)))
            .andExpect(status().isOk());

        // Validate the TinhSachPhanUng in the database
        List<TinhSachPhanUng> tinhSachPhanUngList = tinhSachPhanUngRepository.findAll();
        assertThat(tinhSachPhanUngList).hasSize(databaseSizeBeforeUpdate);
        TinhSachPhanUng testTinhSachPhanUng = tinhSachPhanUngList.get(tinhSachPhanUngList.size() - 1);
        assertThat(testTinhSachPhanUng.getDungTichSuDung()).isEqualTo(UPDATED_DUNG_TICH_SU_DUNG);
        assertThat(testTinhSachPhanUng.getChuTrinhNhietDo()).isEqualTo(UPDATED_CHU_TRINH_NHIET_DO);
        assertThat(testTinhSachPhanUng.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testTinhSachPhanUng.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testTinhSachPhanUng.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testTinhSachPhanUng.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the TinhSachPhanUng in Elasticsearch
        verify(mockTinhSachPhanUngSearchRepository, times(1)).save(testTinhSachPhanUng);
    }

    @Test
    @Transactional
    public void updateNonExistingTinhSachPhanUng() throws Exception {
        int databaseSizeBeforeUpdate = tinhSachPhanUngRepository.findAll().size();

        // Create the TinhSachPhanUng

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTinhSachPhanUngMockMvc.perform(put("/api/tinh-sach-phan-ungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhSachPhanUng)))
            .andExpect(status().isBadRequest());

        // Validate the TinhSachPhanUng in the database
        List<TinhSachPhanUng> tinhSachPhanUngList = tinhSachPhanUngRepository.findAll();
        assertThat(tinhSachPhanUngList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TinhSachPhanUng in Elasticsearch
        verify(mockTinhSachPhanUngSearchRepository, times(0)).save(tinhSachPhanUng);
    }

    @Test
    @Transactional
    public void deleteTinhSachPhanUng() throws Exception {
        // Initialize the database
        tinhSachPhanUngRepository.saveAndFlush(tinhSachPhanUng);

        int databaseSizeBeforeDelete = tinhSachPhanUngRepository.findAll().size();

        // Get the tinhSachPhanUng
        restTinhSachPhanUngMockMvc.perform(delete("/api/tinh-sach-phan-ungs/{id}", tinhSachPhanUng.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TinhSachPhanUng> tinhSachPhanUngList = tinhSachPhanUngRepository.findAll();
        assertThat(tinhSachPhanUngList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TinhSachPhanUng in Elasticsearch
        verify(mockTinhSachPhanUngSearchRepository, times(1)).deleteById(tinhSachPhanUng.getId());
    }

    @Test
    @Transactional
    public void searchTinhSachPhanUng() throws Exception {
        // Initialize the database
        tinhSachPhanUngRepository.saveAndFlush(tinhSachPhanUng);
        when(mockTinhSachPhanUngSearchRepository.search(queryStringQuery("id:" + tinhSachPhanUng.getId())))
            .thenReturn(Collections.singletonList(tinhSachPhanUng));
        // Search the tinhSachPhanUng
        restTinhSachPhanUngMockMvc.perform(get("/api/_search/tinh-sach-phan-ungs?query=id:" + tinhSachPhanUng.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinhSachPhanUng.getId().intValue())))
            .andExpect(jsonPath("$.[*].dungTichSuDung").value(hasItem(DEFAULT_DUNG_TICH_SU_DUNG.doubleValue())))
            .andExpect(jsonPath("$.[*].chuTrinhNhietDo").value(hasItem(DEFAULT_CHU_TRINH_NHIET_DO)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhSachPhanUng.class);
        TinhSachPhanUng tinhSachPhanUng1 = new TinhSachPhanUng();
        tinhSachPhanUng1.setId(1L);
        TinhSachPhanUng tinhSachPhanUng2 = new TinhSachPhanUng();
        tinhSachPhanUng2.setId(tinhSachPhanUng1.getId());
        assertThat(tinhSachPhanUng1).isEqualTo(tinhSachPhanUng2);
        tinhSachPhanUng2.setId(2L);
        assertThat(tinhSachPhanUng1).isNotEqualTo(tinhSachPhanUng2);
        tinhSachPhanUng1.setId(null);
        assertThat(tinhSachPhanUng1).isNotEqualTo(tinhSachPhanUng2);
    }
}
