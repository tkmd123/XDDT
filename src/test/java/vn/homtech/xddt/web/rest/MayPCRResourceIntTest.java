package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.MayPCR;
import vn.homtech.xddt.repository.MayPCRRepository;
import vn.homtech.xddt.repository.search.MayPCRSearchRepository;
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
 * Test class for the MayPCRResource REST controller.
 *
 * @see MayPCRResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class MayPCRResourceIntTest {

    private static final String DEFAULT_MA_MAY_PCR = "AAAAAAAAAA";
    private static final String UPDATED_MA_MAY_PCR = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_MAY_PCR = "AAAAAAAAAA";
    private static final String UPDATED_TEN_MAY_PCR = "BBBBBBBBBB";

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
    private MayPCRRepository mayPCRRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.MayPCRSearchRepositoryMockConfiguration
     */
    @Autowired
    private MayPCRSearchRepository mockMayPCRSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMayPCRMockMvc;

    private MayPCR mayPCR;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MayPCRResource mayPCRResource = new MayPCRResource(mayPCRRepository, mockMayPCRSearchRepository);
        this.restMayPCRMockMvc = MockMvcBuilders.standaloneSetup(mayPCRResource)
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
    public static MayPCR createEntity(EntityManager em) {
        MayPCR mayPCR = new MayPCR()
            .maMayPCR(DEFAULT_MA_MAY_PCR)
            .tenMayPCR(DEFAULT_TEN_MAY_PCR)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return mayPCR;
    }

    @Before
    public void initTest() {
        mayPCR = createEntity(em);
    }

    @Test
    @Transactional
    public void createMayPCR() throws Exception {
        int databaseSizeBeforeCreate = mayPCRRepository.findAll().size();

        // Create the MayPCR
        restMayPCRMockMvc.perform(post("/api/may-pcrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mayPCR)))
            .andExpect(status().isCreated());

        // Validate the MayPCR in the database
        List<MayPCR> mayPCRList = mayPCRRepository.findAll();
        assertThat(mayPCRList).hasSize(databaseSizeBeforeCreate + 1);
        MayPCR testMayPCR = mayPCRList.get(mayPCRList.size() - 1);
        assertThat(testMayPCR.getMaMayPCR()).isEqualTo(DEFAULT_MA_MAY_PCR);
        assertThat(testMayPCR.getTenMayPCR()).isEqualTo(DEFAULT_TEN_MAY_PCR);
        assertThat(testMayPCR.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testMayPCR.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testMayPCR.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testMayPCR.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testMayPCR.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the MayPCR in Elasticsearch
        verify(mockMayPCRSearchRepository, times(1)).save(testMayPCR);
    }

    @Test
    @Transactional
    public void createMayPCRWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mayPCRRepository.findAll().size();

        // Create the MayPCR with an existing ID
        mayPCR.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMayPCRMockMvc.perform(post("/api/may-pcrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mayPCR)))
            .andExpect(status().isBadRequest());

        // Validate the MayPCR in the database
        List<MayPCR> mayPCRList = mayPCRRepository.findAll();
        assertThat(mayPCRList).hasSize(databaseSizeBeforeCreate);

        // Validate the MayPCR in Elasticsearch
        verify(mockMayPCRSearchRepository, times(0)).save(mayPCR);
    }

    @Test
    @Transactional
    public void getAllMayPCRS() throws Exception {
        // Initialize the database
        mayPCRRepository.saveAndFlush(mayPCR);

        // Get all the mayPCRList
        restMayPCRMockMvc.perform(get("/api/may-pcrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mayPCR.getId().intValue())))
            .andExpect(jsonPath("$.[*].maMayPCR").value(hasItem(DEFAULT_MA_MAY_PCR.toString())))
            .andExpect(jsonPath("$.[*].tenMayPCR").value(hasItem(DEFAULT_TEN_MAY_PCR.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getMayPCR() throws Exception {
        // Initialize the database
        mayPCRRepository.saveAndFlush(mayPCR);

        // Get the mayPCR
        restMayPCRMockMvc.perform(get("/api/may-pcrs/{id}", mayPCR.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mayPCR.getId().intValue()))
            .andExpect(jsonPath("$.maMayPCR").value(DEFAULT_MA_MAY_PCR.toString()))
            .andExpect(jsonPath("$.tenMayPCR").value(DEFAULT_TEN_MAY_PCR.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMayPCR() throws Exception {
        // Get the mayPCR
        restMayPCRMockMvc.perform(get("/api/may-pcrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMayPCR() throws Exception {
        // Initialize the database
        mayPCRRepository.saveAndFlush(mayPCR);

        int databaseSizeBeforeUpdate = mayPCRRepository.findAll().size();

        // Update the mayPCR
        MayPCR updatedMayPCR = mayPCRRepository.findById(mayPCR.getId()).get();
        // Disconnect from session so that the updates on updatedMayPCR are not directly saved in db
        em.detach(updatedMayPCR);
        updatedMayPCR
            .maMayPCR(UPDATED_MA_MAY_PCR)
            .tenMayPCR(UPDATED_TEN_MAY_PCR)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restMayPCRMockMvc.perform(put("/api/may-pcrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMayPCR)))
            .andExpect(status().isOk());

        // Validate the MayPCR in the database
        List<MayPCR> mayPCRList = mayPCRRepository.findAll();
        assertThat(mayPCRList).hasSize(databaseSizeBeforeUpdate);
        MayPCR testMayPCR = mayPCRList.get(mayPCRList.size() - 1);
        assertThat(testMayPCR.getMaMayPCR()).isEqualTo(UPDATED_MA_MAY_PCR);
        assertThat(testMayPCR.getTenMayPCR()).isEqualTo(UPDATED_TEN_MAY_PCR);
        assertThat(testMayPCR.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testMayPCR.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testMayPCR.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testMayPCR.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testMayPCR.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the MayPCR in Elasticsearch
        verify(mockMayPCRSearchRepository, times(1)).save(testMayPCR);
    }

    @Test
    @Transactional
    public void updateNonExistingMayPCR() throws Exception {
        int databaseSizeBeforeUpdate = mayPCRRepository.findAll().size();

        // Create the MayPCR

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMayPCRMockMvc.perform(put("/api/may-pcrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mayPCR)))
            .andExpect(status().isBadRequest());

        // Validate the MayPCR in the database
        List<MayPCR> mayPCRList = mayPCRRepository.findAll();
        assertThat(mayPCRList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MayPCR in Elasticsearch
        verify(mockMayPCRSearchRepository, times(0)).save(mayPCR);
    }

    @Test
    @Transactional
    public void deleteMayPCR() throws Exception {
        // Initialize the database
        mayPCRRepository.saveAndFlush(mayPCR);

        int databaseSizeBeforeDelete = mayPCRRepository.findAll().size();

        // Get the mayPCR
        restMayPCRMockMvc.perform(delete("/api/may-pcrs/{id}", mayPCR.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MayPCR> mayPCRList = mayPCRRepository.findAll();
        assertThat(mayPCRList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MayPCR in Elasticsearch
        verify(mockMayPCRSearchRepository, times(1)).deleteById(mayPCR.getId());
    }

    @Test
    @Transactional
    public void searchMayPCR() throws Exception {
        // Initialize the database
        mayPCRRepository.saveAndFlush(mayPCR);
        when(mockMayPCRSearchRepository.search(queryStringQuery("id:" + mayPCR.getId())))
            .thenReturn(Collections.singletonList(mayPCR));
        // Search the mayPCR
        restMayPCRMockMvc.perform(get("/api/_search/may-pcrs?query=id:" + mayPCR.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mayPCR.getId().intValue())))
            .andExpect(jsonPath("$.[*].maMayPCR").value(hasItem(DEFAULT_MA_MAY_PCR)))
            .andExpect(jsonPath("$.[*].tenMayPCR").value(hasItem(DEFAULT_TEN_MAY_PCR)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MayPCR.class);
        MayPCR mayPCR1 = new MayPCR();
        mayPCR1.setId(1L);
        MayPCR mayPCR2 = new MayPCR();
        mayPCR2.setId(mayPCR1.getId());
        assertThat(mayPCR1).isEqualTo(mayPCR2);
        mayPCR2.setId(2L);
        assertThat(mayPCR1).isNotEqualTo(mayPCR2);
        mayPCR1.setId(null);
        assertThat(mayPCR1).isNotEqualTo(mayPCR2);
    }
}
