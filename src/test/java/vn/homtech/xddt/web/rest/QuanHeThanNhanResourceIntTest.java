package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.QuanHeThanNhan;
import vn.homtech.xddt.repository.QuanHeThanNhanRepository;
import vn.homtech.xddt.repository.search.QuanHeThanNhanSearchRepository;
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
 * Test class for the QuanHeThanNhanResource REST controller.
 *
 * @see QuanHeThanNhanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class QuanHeThanNhanResourceIntTest {

    private static final String DEFAULT_MA_QUAN_HE = "AAAAAAAAAA";
    private static final String UPDATED_MA_QUAN_HE = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_QUAN_HE = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_QUAN_HE = "BBBBBBBBBB";

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
    private QuanHeThanNhanRepository quanHeThanNhanRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.QuanHeThanNhanSearchRepositoryMockConfiguration
     */
    @Autowired
    private QuanHeThanNhanSearchRepository mockQuanHeThanNhanSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuanHeThanNhanMockMvc;

    private QuanHeThanNhan quanHeThanNhan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuanHeThanNhanResource quanHeThanNhanResource = new QuanHeThanNhanResource(quanHeThanNhanRepository, mockQuanHeThanNhanSearchRepository);
        this.restQuanHeThanNhanMockMvc = MockMvcBuilders.standaloneSetup(quanHeThanNhanResource)
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
    public static QuanHeThanNhan createEntity(EntityManager em) {
        QuanHeThanNhan quanHeThanNhan = new QuanHeThanNhan()
            .maQuanHe(DEFAULT_MA_QUAN_HE)
            .loaiQuanHe(DEFAULT_LOAI_QUAN_HE)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return quanHeThanNhan;
    }

    @Before
    public void initTest() {
        quanHeThanNhan = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuanHeThanNhan() throws Exception {
        int databaseSizeBeforeCreate = quanHeThanNhanRepository.findAll().size();

        // Create the QuanHeThanNhan
        restQuanHeThanNhanMockMvc.perform(post("/api/quan-he-than-nhans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanHeThanNhan)))
            .andExpect(status().isCreated());

        // Validate the QuanHeThanNhan in the database
        List<QuanHeThanNhan> quanHeThanNhanList = quanHeThanNhanRepository.findAll();
        assertThat(quanHeThanNhanList).hasSize(databaseSizeBeforeCreate + 1);
        QuanHeThanNhan testQuanHeThanNhan = quanHeThanNhanList.get(quanHeThanNhanList.size() - 1);
        assertThat(testQuanHeThanNhan.getMaQuanHe()).isEqualTo(DEFAULT_MA_QUAN_HE);
        assertThat(testQuanHeThanNhan.getLoaiQuanHe()).isEqualTo(DEFAULT_LOAI_QUAN_HE);
        assertThat(testQuanHeThanNhan.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testQuanHeThanNhan.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testQuanHeThanNhan.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testQuanHeThanNhan.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testQuanHeThanNhan.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the QuanHeThanNhan in Elasticsearch
        verify(mockQuanHeThanNhanSearchRepository, times(1)).save(testQuanHeThanNhan);
    }

    @Test
    @Transactional
    public void createQuanHeThanNhanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quanHeThanNhanRepository.findAll().size();

        // Create the QuanHeThanNhan with an existing ID
        quanHeThanNhan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuanHeThanNhanMockMvc.perform(post("/api/quan-he-than-nhans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanHeThanNhan)))
            .andExpect(status().isBadRequest());

        // Validate the QuanHeThanNhan in the database
        List<QuanHeThanNhan> quanHeThanNhanList = quanHeThanNhanRepository.findAll();
        assertThat(quanHeThanNhanList).hasSize(databaseSizeBeforeCreate);

        // Validate the QuanHeThanNhan in Elasticsearch
        verify(mockQuanHeThanNhanSearchRepository, times(0)).save(quanHeThanNhan);
    }

    @Test
    @Transactional
    public void getAllQuanHeThanNhans() throws Exception {
        // Initialize the database
        quanHeThanNhanRepository.saveAndFlush(quanHeThanNhan);

        // Get all the quanHeThanNhanList
        restQuanHeThanNhanMockMvc.perform(get("/api/quan-he-than-nhans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quanHeThanNhan.getId().intValue())))
            .andExpect(jsonPath("$.[*].maQuanHe").value(hasItem(DEFAULT_MA_QUAN_HE.toString())))
            .andExpect(jsonPath("$.[*].loaiQuanHe").value(hasItem(DEFAULT_LOAI_QUAN_HE.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getQuanHeThanNhan() throws Exception {
        // Initialize the database
        quanHeThanNhanRepository.saveAndFlush(quanHeThanNhan);

        // Get the quanHeThanNhan
        restQuanHeThanNhanMockMvc.perform(get("/api/quan-he-than-nhans/{id}", quanHeThanNhan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quanHeThanNhan.getId().intValue()))
            .andExpect(jsonPath("$.maQuanHe").value(DEFAULT_MA_QUAN_HE.toString()))
            .andExpect(jsonPath("$.loaiQuanHe").value(DEFAULT_LOAI_QUAN_HE.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuanHeThanNhan() throws Exception {
        // Get the quanHeThanNhan
        restQuanHeThanNhanMockMvc.perform(get("/api/quan-he-than-nhans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuanHeThanNhan() throws Exception {
        // Initialize the database
        quanHeThanNhanRepository.saveAndFlush(quanHeThanNhan);

        int databaseSizeBeforeUpdate = quanHeThanNhanRepository.findAll().size();

        // Update the quanHeThanNhan
        QuanHeThanNhan updatedQuanHeThanNhan = quanHeThanNhanRepository.findById(quanHeThanNhan.getId()).get();
        // Disconnect from session so that the updates on updatedQuanHeThanNhan are not directly saved in db
        em.detach(updatedQuanHeThanNhan);
        updatedQuanHeThanNhan
            .maQuanHe(UPDATED_MA_QUAN_HE)
            .loaiQuanHe(UPDATED_LOAI_QUAN_HE)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restQuanHeThanNhanMockMvc.perform(put("/api/quan-he-than-nhans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedQuanHeThanNhan)))
            .andExpect(status().isOk());

        // Validate the QuanHeThanNhan in the database
        List<QuanHeThanNhan> quanHeThanNhanList = quanHeThanNhanRepository.findAll();
        assertThat(quanHeThanNhanList).hasSize(databaseSizeBeforeUpdate);
        QuanHeThanNhan testQuanHeThanNhan = quanHeThanNhanList.get(quanHeThanNhanList.size() - 1);
        assertThat(testQuanHeThanNhan.getMaQuanHe()).isEqualTo(UPDATED_MA_QUAN_HE);
        assertThat(testQuanHeThanNhan.getLoaiQuanHe()).isEqualTo(UPDATED_LOAI_QUAN_HE);
        assertThat(testQuanHeThanNhan.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testQuanHeThanNhan.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testQuanHeThanNhan.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testQuanHeThanNhan.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testQuanHeThanNhan.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the QuanHeThanNhan in Elasticsearch
        verify(mockQuanHeThanNhanSearchRepository, times(1)).save(testQuanHeThanNhan);
    }

    @Test
    @Transactional
    public void updateNonExistingQuanHeThanNhan() throws Exception {
        int databaseSizeBeforeUpdate = quanHeThanNhanRepository.findAll().size();

        // Create the QuanHeThanNhan

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeThanNhanMockMvc.perform(put("/api/quan-he-than-nhans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanHeThanNhan)))
            .andExpect(status().isBadRequest());

        // Validate the QuanHeThanNhan in the database
        List<QuanHeThanNhan> quanHeThanNhanList = quanHeThanNhanRepository.findAll();
        assertThat(quanHeThanNhanList).hasSize(databaseSizeBeforeUpdate);

        // Validate the QuanHeThanNhan in Elasticsearch
        verify(mockQuanHeThanNhanSearchRepository, times(0)).save(quanHeThanNhan);
    }

    @Test
    @Transactional
    public void deleteQuanHeThanNhan() throws Exception {
        // Initialize the database
        quanHeThanNhanRepository.saveAndFlush(quanHeThanNhan);

        int databaseSizeBeforeDelete = quanHeThanNhanRepository.findAll().size();

        // Get the quanHeThanNhan
        restQuanHeThanNhanMockMvc.perform(delete("/api/quan-he-than-nhans/{id}", quanHeThanNhan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuanHeThanNhan> quanHeThanNhanList = quanHeThanNhanRepository.findAll();
        assertThat(quanHeThanNhanList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the QuanHeThanNhan in Elasticsearch
        verify(mockQuanHeThanNhanSearchRepository, times(1)).deleteById(quanHeThanNhan.getId());
    }

    @Test
    @Transactional
    public void searchQuanHeThanNhan() throws Exception {
        // Initialize the database
        quanHeThanNhanRepository.saveAndFlush(quanHeThanNhan);
        when(mockQuanHeThanNhanSearchRepository.search(queryStringQuery("id:" + quanHeThanNhan.getId())))
            .thenReturn(Collections.singletonList(quanHeThanNhan));
        // Search the quanHeThanNhan
        restQuanHeThanNhanMockMvc.perform(get("/api/_search/quan-he-than-nhans?query=id:" + quanHeThanNhan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quanHeThanNhan.getId().intValue())))
            .andExpect(jsonPath("$.[*].maQuanHe").value(hasItem(DEFAULT_MA_QUAN_HE)))
            .andExpect(jsonPath("$.[*].loaiQuanHe").value(hasItem(DEFAULT_LOAI_QUAN_HE)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanHeThanNhan.class);
        QuanHeThanNhan quanHeThanNhan1 = new QuanHeThanNhan();
        quanHeThanNhan1.setId(1L);
        QuanHeThanNhan quanHeThanNhan2 = new QuanHeThanNhan();
        quanHeThanNhan2.setId(quanHeThanNhan1.getId());
        assertThat(quanHeThanNhan1).isEqualTo(quanHeThanNhan2);
        quanHeThanNhan2.setId(2L);
        assertThat(quanHeThanNhan1).isNotEqualTo(quanHeThanNhan2);
        quanHeThanNhan1.setId(null);
        assertThat(quanHeThanNhan1).isNotEqualTo(quanHeThanNhan2);
    }
}
