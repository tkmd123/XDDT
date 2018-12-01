package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.HaiCotLietSi;
import vn.homtech.xddt.repository.HaiCotLietSiRepository;
import vn.homtech.xddt.repository.search.HaiCotLietSiSearchRepository;
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
 * Test class for the HaiCotLietSiResource REST controller.
 *
 * @see HaiCotLietSiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class HaiCotLietSiResourceIntTest {

    private static final String DEFAULT_MA_HAI_COT = "AAAAAAAAAA";
    private static final String UPDATED_MA_HAI_COT = "BBBBBBBBBB";

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
    private HaiCotLietSiRepository haiCotLietSiRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.HaiCotLietSiSearchRepositoryMockConfiguration
     */
    @Autowired
    private HaiCotLietSiSearchRepository mockHaiCotLietSiSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHaiCotLietSiMockMvc;

    private HaiCotLietSi haiCotLietSi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HaiCotLietSiResource haiCotLietSiResource = new HaiCotLietSiResource(haiCotLietSiRepository, mockHaiCotLietSiSearchRepository);
        this.restHaiCotLietSiMockMvc = MockMvcBuilders.standaloneSetup(haiCotLietSiResource)
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
    public static HaiCotLietSi createEntity(EntityManager em) {
        HaiCotLietSi haiCotLietSi = new HaiCotLietSi()
            .maHaiCot(DEFAULT_MA_HAI_COT)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return haiCotLietSi;
    }

    @Before
    public void initTest() {
        haiCotLietSi = createEntity(em);
    }

    @Test
    @Transactional
    public void createHaiCotLietSi() throws Exception {
        int databaseSizeBeforeCreate = haiCotLietSiRepository.findAll().size();

        // Create the HaiCotLietSi
        restHaiCotLietSiMockMvc.perform(post("/api/hai-cot-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(haiCotLietSi)))
            .andExpect(status().isCreated());

        // Validate the HaiCotLietSi in the database
        List<HaiCotLietSi> haiCotLietSiList = haiCotLietSiRepository.findAll();
        assertThat(haiCotLietSiList).hasSize(databaseSizeBeforeCreate + 1);
        HaiCotLietSi testHaiCotLietSi = haiCotLietSiList.get(haiCotLietSiList.size() - 1);
        assertThat(testHaiCotLietSi.getMaHaiCot()).isEqualTo(DEFAULT_MA_HAI_COT);
        assertThat(testHaiCotLietSi.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testHaiCotLietSi.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testHaiCotLietSi.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testHaiCotLietSi.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testHaiCotLietSi.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the HaiCotLietSi in Elasticsearch
        verify(mockHaiCotLietSiSearchRepository, times(1)).save(testHaiCotLietSi);
    }

    @Test
    @Transactional
    public void createHaiCotLietSiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = haiCotLietSiRepository.findAll().size();

        // Create the HaiCotLietSi with an existing ID
        haiCotLietSi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHaiCotLietSiMockMvc.perform(post("/api/hai-cot-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(haiCotLietSi)))
            .andExpect(status().isBadRequest());

        // Validate the HaiCotLietSi in the database
        List<HaiCotLietSi> haiCotLietSiList = haiCotLietSiRepository.findAll();
        assertThat(haiCotLietSiList).hasSize(databaseSizeBeforeCreate);

        // Validate the HaiCotLietSi in Elasticsearch
        verify(mockHaiCotLietSiSearchRepository, times(0)).save(haiCotLietSi);
    }

    @Test
    @Transactional
    public void getAllHaiCotLietSis() throws Exception {
        // Initialize the database
        haiCotLietSiRepository.saveAndFlush(haiCotLietSi);

        // Get all the haiCotLietSiList
        restHaiCotLietSiMockMvc.perform(get("/api/hai-cot-liet-sis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(haiCotLietSi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHaiCot").value(hasItem(DEFAULT_MA_HAI_COT.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getHaiCotLietSi() throws Exception {
        // Initialize the database
        haiCotLietSiRepository.saveAndFlush(haiCotLietSi);

        // Get the haiCotLietSi
        restHaiCotLietSiMockMvc.perform(get("/api/hai-cot-liet-sis/{id}", haiCotLietSi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(haiCotLietSi.getId().intValue()))
            .andExpect(jsonPath("$.maHaiCot").value(DEFAULT_MA_HAI_COT.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHaiCotLietSi() throws Exception {
        // Get the haiCotLietSi
        restHaiCotLietSiMockMvc.perform(get("/api/hai-cot-liet-sis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHaiCotLietSi() throws Exception {
        // Initialize the database
        haiCotLietSiRepository.saveAndFlush(haiCotLietSi);

        int databaseSizeBeforeUpdate = haiCotLietSiRepository.findAll().size();

        // Update the haiCotLietSi
        HaiCotLietSi updatedHaiCotLietSi = haiCotLietSiRepository.findById(haiCotLietSi.getId()).get();
        // Disconnect from session so that the updates on updatedHaiCotLietSi are not directly saved in db
        em.detach(updatedHaiCotLietSi);
        updatedHaiCotLietSi
            .maHaiCot(UPDATED_MA_HAI_COT)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restHaiCotLietSiMockMvc.perform(put("/api/hai-cot-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHaiCotLietSi)))
            .andExpect(status().isOk());

        // Validate the HaiCotLietSi in the database
        List<HaiCotLietSi> haiCotLietSiList = haiCotLietSiRepository.findAll();
        assertThat(haiCotLietSiList).hasSize(databaseSizeBeforeUpdate);
        HaiCotLietSi testHaiCotLietSi = haiCotLietSiList.get(haiCotLietSiList.size() - 1);
        assertThat(testHaiCotLietSi.getMaHaiCot()).isEqualTo(UPDATED_MA_HAI_COT);
        assertThat(testHaiCotLietSi.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testHaiCotLietSi.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testHaiCotLietSi.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testHaiCotLietSi.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testHaiCotLietSi.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the HaiCotLietSi in Elasticsearch
        verify(mockHaiCotLietSiSearchRepository, times(1)).save(testHaiCotLietSi);
    }

    @Test
    @Transactional
    public void updateNonExistingHaiCotLietSi() throws Exception {
        int databaseSizeBeforeUpdate = haiCotLietSiRepository.findAll().size();

        // Create the HaiCotLietSi

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHaiCotLietSiMockMvc.perform(put("/api/hai-cot-liet-sis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(haiCotLietSi)))
            .andExpect(status().isBadRequest());

        // Validate the HaiCotLietSi in the database
        List<HaiCotLietSi> haiCotLietSiList = haiCotLietSiRepository.findAll();
        assertThat(haiCotLietSiList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HaiCotLietSi in Elasticsearch
        verify(mockHaiCotLietSiSearchRepository, times(0)).save(haiCotLietSi);
    }

    @Test
    @Transactional
    public void deleteHaiCotLietSi() throws Exception {
        // Initialize the database
        haiCotLietSiRepository.saveAndFlush(haiCotLietSi);

        int databaseSizeBeforeDelete = haiCotLietSiRepository.findAll().size();

        // Get the haiCotLietSi
        restHaiCotLietSiMockMvc.perform(delete("/api/hai-cot-liet-sis/{id}", haiCotLietSi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HaiCotLietSi> haiCotLietSiList = haiCotLietSiRepository.findAll();
        assertThat(haiCotLietSiList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HaiCotLietSi in Elasticsearch
        verify(mockHaiCotLietSiSearchRepository, times(1)).deleteById(haiCotLietSi.getId());
    }

    @Test
    @Transactional
    public void searchHaiCotLietSi() throws Exception {
        // Initialize the database
        haiCotLietSiRepository.saveAndFlush(haiCotLietSi);
        when(mockHaiCotLietSiSearchRepository.search(queryStringQuery("id:" + haiCotLietSi.getId())))
            .thenReturn(Collections.singletonList(haiCotLietSi));
        // Search the haiCotLietSi
        restHaiCotLietSiMockMvc.perform(get("/api/_search/hai-cot-liet-sis?query=id:" + haiCotLietSi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(haiCotLietSi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHaiCot").value(hasItem(DEFAULT_MA_HAI_COT)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HaiCotLietSi.class);
        HaiCotLietSi haiCotLietSi1 = new HaiCotLietSi();
        haiCotLietSi1.setId(1L);
        HaiCotLietSi haiCotLietSi2 = new HaiCotLietSi();
        haiCotLietSi2.setId(haiCotLietSi1.getId());
        assertThat(haiCotLietSi1).isEqualTo(haiCotLietSi2);
        haiCotLietSi2.setId(2L);
        assertThat(haiCotLietSi1).isNotEqualTo(haiCotLietSi2);
        haiCotLietSi1.setId(null);
        assertThat(haiCotLietSi1).isNotEqualTo(haiCotLietSi2);
    }
}
