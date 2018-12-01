package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.MappingDotBien;
import vn.homtech.xddt.repository.MappingDotBienRepository;
import vn.homtech.xddt.repository.search.MappingDotBienSearchRepository;
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
 * Test class for the MappingDotBienResource REST controller.
 *
 * @see MappingDotBienResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class MappingDotBienResourceIntTest {

    private static final String DEFAULT_MA_DOT_BIEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_DOT_BIEN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_MAPPING = "AAAAAAAAAA";
    private static final String UPDATED_MA_MAPPING = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private MappingDotBienRepository mappingDotBienRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.MappingDotBienSearchRepositoryMockConfiguration
     */
    @Autowired
    private MappingDotBienSearchRepository mockMappingDotBienSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMappingDotBienMockMvc;

    private MappingDotBien mappingDotBien;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MappingDotBienResource mappingDotBienResource = new MappingDotBienResource(mappingDotBienRepository, mockMappingDotBienSearchRepository);
        this.restMappingDotBienMockMvc = MockMvcBuilders.standaloneSetup(mappingDotBienResource)
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
    public static MappingDotBien createEntity(EntityManager em) {
        MappingDotBien mappingDotBien = new MappingDotBien()
            .maDotBien(DEFAULT_MA_DOT_BIEN)
            .maMapping(DEFAULT_MA_MAPPING)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return mappingDotBien;
    }

    @Before
    public void initTest() {
        mappingDotBien = createEntity(em);
    }

    @Test
    @Transactional
    public void createMappingDotBien() throws Exception {
        int databaseSizeBeforeCreate = mappingDotBienRepository.findAll().size();

        // Create the MappingDotBien
        restMappingDotBienMockMvc.perform(post("/api/mapping-dot-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mappingDotBien)))
            .andExpect(status().isCreated());

        // Validate the MappingDotBien in the database
        List<MappingDotBien> mappingDotBienList = mappingDotBienRepository.findAll();
        assertThat(mappingDotBienList).hasSize(databaseSizeBeforeCreate + 1);
        MappingDotBien testMappingDotBien = mappingDotBienList.get(mappingDotBienList.size() - 1);
        assertThat(testMappingDotBien.getMaDotBien()).isEqualTo(DEFAULT_MA_DOT_BIEN);
        assertThat(testMappingDotBien.getMaMapping()).isEqualTo(DEFAULT_MA_MAPPING);
        assertThat(testMappingDotBien.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testMappingDotBien.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testMappingDotBien.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testMappingDotBien.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the MappingDotBien in Elasticsearch
        verify(mockMappingDotBienSearchRepository, times(1)).save(testMappingDotBien);
    }

    @Test
    @Transactional
    public void createMappingDotBienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mappingDotBienRepository.findAll().size();

        // Create the MappingDotBien with an existing ID
        mappingDotBien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMappingDotBienMockMvc.perform(post("/api/mapping-dot-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mappingDotBien)))
            .andExpect(status().isBadRequest());

        // Validate the MappingDotBien in the database
        List<MappingDotBien> mappingDotBienList = mappingDotBienRepository.findAll();
        assertThat(mappingDotBienList).hasSize(databaseSizeBeforeCreate);

        // Validate the MappingDotBien in Elasticsearch
        verify(mockMappingDotBienSearchRepository, times(0)).save(mappingDotBien);
    }

    @Test
    @Transactional
    public void getAllMappingDotBiens() throws Exception {
        // Initialize the database
        mappingDotBienRepository.saveAndFlush(mappingDotBien);

        // Get all the mappingDotBienList
        restMappingDotBienMockMvc.perform(get("/api/mapping-dot-biens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mappingDotBien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDotBien").value(hasItem(DEFAULT_MA_DOT_BIEN.toString())))
            .andExpect(jsonPath("$.[*].maMapping").value(hasItem(DEFAULT_MA_MAPPING.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getMappingDotBien() throws Exception {
        // Initialize the database
        mappingDotBienRepository.saveAndFlush(mappingDotBien);

        // Get the mappingDotBien
        restMappingDotBienMockMvc.perform(get("/api/mapping-dot-biens/{id}", mappingDotBien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mappingDotBien.getId().intValue()))
            .andExpect(jsonPath("$.maDotBien").value(DEFAULT_MA_DOT_BIEN.toString()))
            .andExpect(jsonPath("$.maMapping").value(DEFAULT_MA_MAPPING.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMappingDotBien() throws Exception {
        // Get the mappingDotBien
        restMappingDotBienMockMvc.perform(get("/api/mapping-dot-biens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMappingDotBien() throws Exception {
        // Initialize the database
        mappingDotBienRepository.saveAndFlush(mappingDotBien);

        int databaseSizeBeforeUpdate = mappingDotBienRepository.findAll().size();

        // Update the mappingDotBien
        MappingDotBien updatedMappingDotBien = mappingDotBienRepository.findById(mappingDotBien.getId()).get();
        // Disconnect from session so that the updates on updatedMappingDotBien are not directly saved in db
        em.detach(updatedMappingDotBien);
        updatedMappingDotBien
            .maDotBien(UPDATED_MA_DOT_BIEN)
            .maMapping(UPDATED_MA_MAPPING)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restMappingDotBienMockMvc.perform(put("/api/mapping-dot-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMappingDotBien)))
            .andExpect(status().isOk());

        // Validate the MappingDotBien in the database
        List<MappingDotBien> mappingDotBienList = mappingDotBienRepository.findAll();
        assertThat(mappingDotBienList).hasSize(databaseSizeBeforeUpdate);
        MappingDotBien testMappingDotBien = mappingDotBienList.get(mappingDotBienList.size() - 1);
        assertThat(testMappingDotBien.getMaDotBien()).isEqualTo(UPDATED_MA_DOT_BIEN);
        assertThat(testMappingDotBien.getMaMapping()).isEqualTo(UPDATED_MA_MAPPING);
        assertThat(testMappingDotBien.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testMappingDotBien.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testMappingDotBien.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testMappingDotBien.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the MappingDotBien in Elasticsearch
        verify(mockMappingDotBienSearchRepository, times(1)).save(testMappingDotBien);
    }

    @Test
    @Transactional
    public void updateNonExistingMappingDotBien() throws Exception {
        int databaseSizeBeforeUpdate = mappingDotBienRepository.findAll().size();

        // Create the MappingDotBien

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMappingDotBienMockMvc.perform(put("/api/mapping-dot-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mappingDotBien)))
            .andExpect(status().isBadRequest());

        // Validate the MappingDotBien in the database
        List<MappingDotBien> mappingDotBienList = mappingDotBienRepository.findAll();
        assertThat(mappingDotBienList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MappingDotBien in Elasticsearch
        verify(mockMappingDotBienSearchRepository, times(0)).save(mappingDotBien);
    }

    @Test
    @Transactional
    public void deleteMappingDotBien() throws Exception {
        // Initialize the database
        mappingDotBienRepository.saveAndFlush(mappingDotBien);

        int databaseSizeBeforeDelete = mappingDotBienRepository.findAll().size();

        // Get the mappingDotBien
        restMappingDotBienMockMvc.perform(delete("/api/mapping-dot-biens/{id}", mappingDotBien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MappingDotBien> mappingDotBienList = mappingDotBienRepository.findAll();
        assertThat(mappingDotBienList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MappingDotBien in Elasticsearch
        verify(mockMappingDotBienSearchRepository, times(1)).deleteById(mappingDotBien.getId());
    }

    @Test
    @Transactional
    public void searchMappingDotBien() throws Exception {
        // Initialize the database
        mappingDotBienRepository.saveAndFlush(mappingDotBien);
        when(mockMappingDotBienSearchRepository.search(queryStringQuery("id:" + mappingDotBien.getId())))
            .thenReturn(Collections.singletonList(mappingDotBien));
        // Search the mappingDotBien
        restMappingDotBienMockMvc.perform(get("/api/_search/mapping-dot-biens?query=id:" + mappingDotBien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mappingDotBien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDotBien").value(hasItem(DEFAULT_MA_DOT_BIEN)))
            .andExpect(jsonPath("$.[*].maMapping").value(hasItem(DEFAULT_MA_MAPPING)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MappingDotBien.class);
        MappingDotBien mappingDotBien1 = new MappingDotBien();
        mappingDotBien1.setId(1L);
        MappingDotBien mappingDotBien2 = new MappingDotBien();
        mappingDotBien2.setId(mappingDotBien1.getId());
        assertThat(mappingDotBien1).isEqualTo(mappingDotBien2);
        mappingDotBien2.setId(2L);
        assertThat(mappingDotBien1).isNotEqualTo(mappingDotBien2);
        mappingDotBien1.setId(null);
        assertThat(mappingDotBien1).isNotEqualTo(mappingDotBien2);
    }
}
