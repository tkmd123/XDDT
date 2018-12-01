package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.LoaiHinhThaiHaiCot;
import vn.homtech.xddt.repository.LoaiHinhThaiHaiCotRepository;
import vn.homtech.xddt.repository.search.LoaiHinhThaiHaiCotSearchRepository;
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
 * Test class for the LoaiHinhThaiHaiCotResource REST controller.
 *
 * @see LoaiHinhThaiHaiCotResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class LoaiHinhThaiHaiCotResourceIntTest {

    private static final String DEFAULT_MA_HINH_THAI = "AAAAAAAAAA";
    private static final String UPDATED_MA_HINH_THAI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_HINH_THAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_HINH_THAI = "BBBBBBBBBB";

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
    private LoaiHinhThaiHaiCotRepository loaiHinhThaiHaiCotRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.LoaiHinhThaiHaiCotSearchRepositoryMockConfiguration
     */
    @Autowired
    private LoaiHinhThaiHaiCotSearchRepository mockLoaiHinhThaiHaiCotSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoaiHinhThaiHaiCotMockMvc;

    private LoaiHinhThaiHaiCot loaiHinhThaiHaiCot;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoaiHinhThaiHaiCotResource loaiHinhThaiHaiCotResource = new LoaiHinhThaiHaiCotResource(loaiHinhThaiHaiCotRepository, mockLoaiHinhThaiHaiCotSearchRepository);
        this.restLoaiHinhThaiHaiCotMockMvc = MockMvcBuilders.standaloneSetup(loaiHinhThaiHaiCotResource)
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
    public static LoaiHinhThaiHaiCot createEntity(EntityManager em) {
        LoaiHinhThaiHaiCot loaiHinhThaiHaiCot = new LoaiHinhThaiHaiCot()
            .maHinhThai(DEFAULT_MA_HINH_THAI)
            .tenHinhThai(DEFAULT_TEN_HINH_THAI)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return loaiHinhThaiHaiCot;
    }

    @Before
    public void initTest() {
        loaiHinhThaiHaiCot = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoaiHinhThaiHaiCot() throws Exception {
        int databaseSizeBeforeCreate = loaiHinhThaiHaiCotRepository.findAll().size();

        // Create the LoaiHinhThaiHaiCot
        restLoaiHinhThaiHaiCotMockMvc.perform(post("/api/loai-hinh-thai-hai-cots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiHinhThaiHaiCot)))
            .andExpect(status().isCreated());

        // Validate the LoaiHinhThaiHaiCot in the database
        List<LoaiHinhThaiHaiCot> loaiHinhThaiHaiCotList = loaiHinhThaiHaiCotRepository.findAll();
        assertThat(loaiHinhThaiHaiCotList).hasSize(databaseSizeBeforeCreate + 1);
        LoaiHinhThaiHaiCot testLoaiHinhThaiHaiCot = loaiHinhThaiHaiCotList.get(loaiHinhThaiHaiCotList.size() - 1);
        assertThat(testLoaiHinhThaiHaiCot.getMaHinhThai()).isEqualTo(DEFAULT_MA_HINH_THAI);
        assertThat(testLoaiHinhThaiHaiCot.getTenHinhThai()).isEqualTo(DEFAULT_TEN_HINH_THAI);
        assertThat(testLoaiHinhThaiHaiCot.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testLoaiHinhThaiHaiCot.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testLoaiHinhThaiHaiCot.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testLoaiHinhThaiHaiCot.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testLoaiHinhThaiHaiCot.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the LoaiHinhThaiHaiCot in Elasticsearch
        verify(mockLoaiHinhThaiHaiCotSearchRepository, times(1)).save(testLoaiHinhThaiHaiCot);
    }

    @Test
    @Transactional
    public void createLoaiHinhThaiHaiCotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loaiHinhThaiHaiCotRepository.findAll().size();

        // Create the LoaiHinhThaiHaiCot with an existing ID
        loaiHinhThaiHaiCot.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiHinhThaiHaiCotMockMvc.perform(post("/api/loai-hinh-thai-hai-cots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiHinhThaiHaiCot)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiHinhThaiHaiCot in the database
        List<LoaiHinhThaiHaiCot> loaiHinhThaiHaiCotList = loaiHinhThaiHaiCotRepository.findAll();
        assertThat(loaiHinhThaiHaiCotList).hasSize(databaseSizeBeforeCreate);

        // Validate the LoaiHinhThaiHaiCot in Elasticsearch
        verify(mockLoaiHinhThaiHaiCotSearchRepository, times(0)).save(loaiHinhThaiHaiCot);
    }

    @Test
    @Transactional
    public void getAllLoaiHinhThaiHaiCots() throws Exception {
        // Initialize the database
        loaiHinhThaiHaiCotRepository.saveAndFlush(loaiHinhThaiHaiCot);

        // Get all the loaiHinhThaiHaiCotList
        restLoaiHinhThaiHaiCotMockMvc.perform(get("/api/loai-hinh-thai-hai-cots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiHinhThaiHaiCot.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHinhThai").value(hasItem(DEFAULT_MA_HINH_THAI.toString())))
            .andExpect(jsonPath("$.[*].tenHinhThai").value(hasItem(DEFAULT_TEN_HINH_THAI.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getLoaiHinhThaiHaiCot() throws Exception {
        // Initialize the database
        loaiHinhThaiHaiCotRepository.saveAndFlush(loaiHinhThaiHaiCot);

        // Get the loaiHinhThaiHaiCot
        restLoaiHinhThaiHaiCotMockMvc.perform(get("/api/loai-hinh-thai-hai-cots/{id}", loaiHinhThaiHaiCot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loaiHinhThaiHaiCot.getId().intValue()))
            .andExpect(jsonPath("$.maHinhThai").value(DEFAULT_MA_HINH_THAI.toString()))
            .andExpect(jsonPath("$.tenHinhThai").value(DEFAULT_TEN_HINH_THAI.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLoaiHinhThaiHaiCot() throws Exception {
        // Get the loaiHinhThaiHaiCot
        restLoaiHinhThaiHaiCotMockMvc.perform(get("/api/loai-hinh-thai-hai-cots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoaiHinhThaiHaiCot() throws Exception {
        // Initialize the database
        loaiHinhThaiHaiCotRepository.saveAndFlush(loaiHinhThaiHaiCot);

        int databaseSizeBeforeUpdate = loaiHinhThaiHaiCotRepository.findAll().size();

        // Update the loaiHinhThaiHaiCot
        LoaiHinhThaiHaiCot updatedLoaiHinhThaiHaiCot = loaiHinhThaiHaiCotRepository.findById(loaiHinhThaiHaiCot.getId()).get();
        // Disconnect from session so that the updates on updatedLoaiHinhThaiHaiCot are not directly saved in db
        em.detach(updatedLoaiHinhThaiHaiCot);
        updatedLoaiHinhThaiHaiCot
            .maHinhThai(UPDATED_MA_HINH_THAI)
            .tenHinhThai(UPDATED_TEN_HINH_THAI)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restLoaiHinhThaiHaiCotMockMvc.perform(put("/api/loai-hinh-thai-hai-cots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoaiHinhThaiHaiCot)))
            .andExpect(status().isOk());

        // Validate the LoaiHinhThaiHaiCot in the database
        List<LoaiHinhThaiHaiCot> loaiHinhThaiHaiCotList = loaiHinhThaiHaiCotRepository.findAll();
        assertThat(loaiHinhThaiHaiCotList).hasSize(databaseSizeBeforeUpdate);
        LoaiHinhThaiHaiCot testLoaiHinhThaiHaiCot = loaiHinhThaiHaiCotList.get(loaiHinhThaiHaiCotList.size() - 1);
        assertThat(testLoaiHinhThaiHaiCot.getMaHinhThai()).isEqualTo(UPDATED_MA_HINH_THAI);
        assertThat(testLoaiHinhThaiHaiCot.getTenHinhThai()).isEqualTo(UPDATED_TEN_HINH_THAI);
        assertThat(testLoaiHinhThaiHaiCot.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testLoaiHinhThaiHaiCot.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testLoaiHinhThaiHaiCot.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testLoaiHinhThaiHaiCot.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testLoaiHinhThaiHaiCot.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the LoaiHinhThaiHaiCot in Elasticsearch
        verify(mockLoaiHinhThaiHaiCotSearchRepository, times(1)).save(testLoaiHinhThaiHaiCot);
    }

    @Test
    @Transactional
    public void updateNonExistingLoaiHinhThaiHaiCot() throws Exception {
        int databaseSizeBeforeUpdate = loaiHinhThaiHaiCotRepository.findAll().size();

        // Create the LoaiHinhThaiHaiCot

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiHinhThaiHaiCotMockMvc.perform(put("/api/loai-hinh-thai-hai-cots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiHinhThaiHaiCot)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiHinhThaiHaiCot in the database
        List<LoaiHinhThaiHaiCot> loaiHinhThaiHaiCotList = loaiHinhThaiHaiCotRepository.findAll();
        assertThat(loaiHinhThaiHaiCotList).hasSize(databaseSizeBeforeUpdate);

        // Validate the LoaiHinhThaiHaiCot in Elasticsearch
        verify(mockLoaiHinhThaiHaiCotSearchRepository, times(0)).save(loaiHinhThaiHaiCot);
    }

    @Test
    @Transactional
    public void deleteLoaiHinhThaiHaiCot() throws Exception {
        // Initialize the database
        loaiHinhThaiHaiCotRepository.saveAndFlush(loaiHinhThaiHaiCot);

        int databaseSizeBeforeDelete = loaiHinhThaiHaiCotRepository.findAll().size();

        // Get the loaiHinhThaiHaiCot
        restLoaiHinhThaiHaiCotMockMvc.perform(delete("/api/loai-hinh-thai-hai-cots/{id}", loaiHinhThaiHaiCot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LoaiHinhThaiHaiCot> loaiHinhThaiHaiCotList = loaiHinhThaiHaiCotRepository.findAll();
        assertThat(loaiHinhThaiHaiCotList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the LoaiHinhThaiHaiCot in Elasticsearch
        verify(mockLoaiHinhThaiHaiCotSearchRepository, times(1)).deleteById(loaiHinhThaiHaiCot.getId());
    }

    @Test
    @Transactional
    public void searchLoaiHinhThaiHaiCot() throws Exception {
        // Initialize the database
        loaiHinhThaiHaiCotRepository.saveAndFlush(loaiHinhThaiHaiCot);
        when(mockLoaiHinhThaiHaiCotSearchRepository.search(queryStringQuery("id:" + loaiHinhThaiHaiCot.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(loaiHinhThaiHaiCot), PageRequest.of(0, 1), 1));
        // Search the loaiHinhThaiHaiCot
        restLoaiHinhThaiHaiCotMockMvc.perform(get("/api/_search/loai-hinh-thai-hai-cots?query=id:" + loaiHinhThaiHaiCot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiHinhThaiHaiCot.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHinhThai").value(hasItem(DEFAULT_MA_HINH_THAI)))
            .andExpect(jsonPath("$.[*].tenHinhThai").value(hasItem(DEFAULT_TEN_HINH_THAI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiHinhThaiHaiCot.class);
        LoaiHinhThaiHaiCot loaiHinhThaiHaiCot1 = new LoaiHinhThaiHaiCot();
        loaiHinhThaiHaiCot1.setId(1L);
        LoaiHinhThaiHaiCot loaiHinhThaiHaiCot2 = new LoaiHinhThaiHaiCot();
        loaiHinhThaiHaiCot2.setId(loaiHinhThaiHaiCot1.getId());
        assertThat(loaiHinhThaiHaiCot1).isEqualTo(loaiHinhThaiHaiCot2);
        loaiHinhThaiHaiCot2.setId(2L);
        assertThat(loaiHinhThaiHaiCot1).isNotEqualTo(loaiHinhThaiHaiCot2);
        loaiHinhThaiHaiCot1.setId(null);
        assertThat(loaiHinhThaiHaiCot1).isNotEqualTo(loaiHinhThaiHaiCot2);
    }
}
