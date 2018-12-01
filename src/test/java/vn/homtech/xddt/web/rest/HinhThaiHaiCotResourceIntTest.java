package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.HinhThaiHaiCot;
import vn.homtech.xddt.repository.HinhThaiHaiCotRepository;
import vn.homtech.xddt.repository.search.HinhThaiHaiCotSearchRepository;
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
 * Test class for the HinhThaiHaiCotResource REST controller.
 *
 * @see HinhThaiHaiCotResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class HinhThaiHaiCotResourceIntTest {

    private static final String DEFAULT_GIA_TRI = "AAAAAAAAAA";
    private static final String UPDATED_GIA_TRI = "BBBBBBBBBB";

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
    private HinhThaiHaiCotRepository hinhThaiHaiCotRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.HinhThaiHaiCotSearchRepositoryMockConfiguration
     */
    @Autowired
    private HinhThaiHaiCotSearchRepository mockHinhThaiHaiCotSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHinhThaiHaiCotMockMvc;

    private HinhThaiHaiCot hinhThaiHaiCot;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HinhThaiHaiCotResource hinhThaiHaiCotResource = new HinhThaiHaiCotResource(hinhThaiHaiCotRepository, mockHinhThaiHaiCotSearchRepository);
        this.restHinhThaiHaiCotMockMvc = MockMvcBuilders.standaloneSetup(hinhThaiHaiCotResource)
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
    public static HinhThaiHaiCot createEntity(EntityManager em) {
        HinhThaiHaiCot hinhThaiHaiCot = new HinhThaiHaiCot()
            .giaTri(DEFAULT_GIA_TRI)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return hinhThaiHaiCot;
    }

    @Before
    public void initTest() {
        hinhThaiHaiCot = createEntity(em);
    }

    @Test
    @Transactional
    public void createHinhThaiHaiCot() throws Exception {
        int databaseSizeBeforeCreate = hinhThaiHaiCotRepository.findAll().size();

        // Create the HinhThaiHaiCot
        restHinhThaiHaiCotMockMvc.perform(post("/api/hinh-thai-hai-cots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hinhThaiHaiCot)))
            .andExpect(status().isCreated());

        // Validate the HinhThaiHaiCot in the database
        List<HinhThaiHaiCot> hinhThaiHaiCotList = hinhThaiHaiCotRepository.findAll();
        assertThat(hinhThaiHaiCotList).hasSize(databaseSizeBeforeCreate + 1);
        HinhThaiHaiCot testHinhThaiHaiCot = hinhThaiHaiCotList.get(hinhThaiHaiCotList.size() - 1);
        assertThat(testHinhThaiHaiCot.getGiaTri()).isEqualTo(DEFAULT_GIA_TRI);
        assertThat(testHinhThaiHaiCot.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testHinhThaiHaiCot.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testHinhThaiHaiCot.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testHinhThaiHaiCot.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testHinhThaiHaiCot.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the HinhThaiHaiCot in Elasticsearch
        verify(mockHinhThaiHaiCotSearchRepository, times(1)).save(testHinhThaiHaiCot);
    }

    @Test
    @Transactional
    public void createHinhThaiHaiCotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hinhThaiHaiCotRepository.findAll().size();

        // Create the HinhThaiHaiCot with an existing ID
        hinhThaiHaiCot.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHinhThaiHaiCotMockMvc.perform(post("/api/hinh-thai-hai-cots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hinhThaiHaiCot)))
            .andExpect(status().isBadRequest());

        // Validate the HinhThaiHaiCot in the database
        List<HinhThaiHaiCot> hinhThaiHaiCotList = hinhThaiHaiCotRepository.findAll();
        assertThat(hinhThaiHaiCotList).hasSize(databaseSizeBeforeCreate);

        // Validate the HinhThaiHaiCot in Elasticsearch
        verify(mockHinhThaiHaiCotSearchRepository, times(0)).save(hinhThaiHaiCot);
    }

    @Test
    @Transactional
    public void getAllHinhThaiHaiCots() throws Exception {
        // Initialize the database
        hinhThaiHaiCotRepository.saveAndFlush(hinhThaiHaiCot);

        // Get all the hinhThaiHaiCotList
        restHinhThaiHaiCotMockMvc.perform(get("/api/hinh-thai-hai-cots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hinhThaiHaiCot.getId().intValue())))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getHinhThaiHaiCot() throws Exception {
        // Initialize the database
        hinhThaiHaiCotRepository.saveAndFlush(hinhThaiHaiCot);

        // Get the hinhThaiHaiCot
        restHinhThaiHaiCotMockMvc.perform(get("/api/hinh-thai-hai-cots/{id}", hinhThaiHaiCot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hinhThaiHaiCot.getId().intValue()))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHinhThaiHaiCot() throws Exception {
        // Get the hinhThaiHaiCot
        restHinhThaiHaiCotMockMvc.perform(get("/api/hinh-thai-hai-cots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHinhThaiHaiCot() throws Exception {
        // Initialize the database
        hinhThaiHaiCotRepository.saveAndFlush(hinhThaiHaiCot);

        int databaseSizeBeforeUpdate = hinhThaiHaiCotRepository.findAll().size();

        // Update the hinhThaiHaiCot
        HinhThaiHaiCot updatedHinhThaiHaiCot = hinhThaiHaiCotRepository.findById(hinhThaiHaiCot.getId()).get();
        // Disconnect from session so that the updates on updatedHinhThaiHaiCot are not directly saved in db
        em.detach(updatedHinhThaiHaiCot);
        updatedHinhThaiHaiCot
            .giaTri(UPDATED_GIA_TRI)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restHinhThaiHaiCotMockMvc.perform(put("/api/hinh-thai-hai-cots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHinhThaiHaiCot)))
            .andExpect(status().isOk());

        // Validate the HinhThaiHaiCot in the database
        List<HinhThaiHaiCot> hinhThaiHaiCotList = hinhThaiHaiCotRepository.findAll();
        assertThat(hinhThaiHaiCotList).hasSize(databaseSizeBeforeUpdate);
        HinhThaiHaiCot testHinhThaiHaiCot = hinhThaiHaiCotList.get(hinhThaiHaiCotList.size() - 1);
        assertThat(testHinhThaiHaiCot.getGiaTri()).isEqualTo(UPDATED_GIA_TRI);
        assertThat(testHinhThaiHaiCot.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testHinhThaiHaiCot.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testHinhThaiHaiCot.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testHinhThaiHaiCot.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testHinhThaiHaiCot.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the HinhThaiHaiCot in Elasticsearch
        verify(mockHinhThaiHaiCotSearchRepository, times(1)).save(testHinhThaiHaiCot);
    }

    @Test
    @Transactional
    public void updateNonExistingHinhThaiHaiCot() throws Exception {
        int databaseSizeBeforeUpdate = hinhThaiHaiCotRepository.findAll().size();

        // Create the HinhThaiHaiCot

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHinhThaiHaiCotMockMvc.perform(put("/api/hinh-thai-hai-cots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hinhThaiHaiCot)))
            .andExpect(status().isBadRequest());

        // Validate the HinhThaiHaiCot in the database
        List<HinhThaiHaiCot> hinhThaiHaiCotList = hinhThaiHaiCotRepository.findAll();
        assertThat(hinhThaiHaiCotList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HinhThaiHaiCot in Elasticsearch
        verify(mockHinhThaiHaiCotSearchRepository, times(0)).save(hinhThaiHaiCot);
    }

    @Test
    @Transactional
    public void deleteHinhThaiHaiCot() throws Exception {
        // Initialize the database
        hinhThaiHaiCotRepository.saveAndFlush(hinhThaiHaiCot);

        int databaseSizeBeforeDelete = hinhThaiHaiCotRepository.findAll().size();

        // Get the hinhThaiHaiCot
        restHinhThaiHaiCotMockMvc.perform(delete("/api/hinh-thai-hai-cots/{id}", hinhThaiHaiCot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HinhThaiHaiCot> hinhThaiHaiCotList = hinhThaiHaiCotRepository.findAll();
        assertThat(hinhThaiHaiCotList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HinhThaiHaiCot in Elasticsearch
        verify(mockHinhThaiHaiCotSearchRepository, times(1)).deleteById(hinhThaiHaiCot.getId());
    }

    @Test
    @Transactional
    public void searchHinhThaiHaiCot() throws Exception {
        // Initialize the database
        hinhThaiHaiCotRepository.saveAndFlush(hinhThaiHaiCot);
        when(mockHinhThaiHaiCotSearchRepository.search(queryStringQuery("id:" + hinhThaiHaiCot.getId())))
            .thenReturn(Collections.singletonList(hinhThaiHaiCot));
        // Search the hinhThaiHaiCot
        restHinhThaiHaiCotMockMvc.perform(get("/api/_search/hinh-thai-hai-cots?query=id:" + hinhThaiHaiCot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hinhThaiHaiCot.getId().intValue())))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HinhThaiHaiCot.class);
        HinhThaiHaiCot hinhThaiHaiCot1 = new HinhThaiHaiCot();
        hinhThaiHaiCot1.setId(1L);
        HinhThaiHaiCot hinhThaiHaiCot2 = new HinhThaiHaiCot();
        hinhThaiHaiCot2.setId(hinhThaiHaiCot1.getId());
        assertThat(hinhThaiHaiCot1).isEqualTo(hinhThaiHaiCot2);
        hinhThaiHaiCot2.setId(2L);
        assertThat(hinhThaiHaiCot1).isNotEqualTo(hinhThaiHaiCot2);
        hinhThaiHaiCot1.setId(null);
        assertThat(hinhThaiHaiCot1).isNotEqualTo(hinhThaiHaiCot2);
    }
}
