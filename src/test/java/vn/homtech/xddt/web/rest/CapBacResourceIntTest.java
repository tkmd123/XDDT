package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.CapBac;
import vn.homtech.xddt.repository.CapBacRepository;
import vn.homtech.xddt.repository.search.CapBacSearchRepository;
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
 * Test class for the CapBacResource REST controller.
 *
 * @see CapBacResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class CapBacResourceIntTest {

    private static final String DEFAULT_MA_CAP_BAC = "AAAAAAAAAA";
    private static final String UPDATED_MA_CAP_BAC = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_CAP_BAC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CAP_BAC = "BBBBBBBBBB";

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
    private CapBacRepository capBacRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.CapBacSearchRepositoryMockConfiguration
     */
    @Autowired
    private CapBacSearchRepository mockCapBacSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCapBacMockMvc;

    private CapBac capBac;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CapBacResource capBacResource = new CapBacResource(capBacRepository, mockCapBacSearchRepository);
        this.restCapBacMockMvc = MockMvcBuilders.standaloneSetup(capBacResource)
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
    public static CapBac createEntity(EntityManager em) {
        CapBac capBac = new CapBac()
            .maCapBac(DEFAULT_MA_CAP_BAC)
            .tenCapBac(DEFAULT_TEN_CAP_BAC)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return capBac;
    }

    @Before
    public void initTest() {
        capBac = createEntity(em);
    }

    @Test
    @Transactional
    public void createCapBac() throws Exception {
        int databaseSizeBeforeCreate = capBacRepository.findAll().size();

        // Create the CapBac
        restCapBacMockMvc.perform(post("/api/cap-bacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(capBac)))
            .andExpect(status().isCreated());

        // Validate the CapBac in the database
        List<CapBac> capBacList = capBacRepository.findAll();
        assertThat(capBacList).hasSize(databaseSizeBeforeCreate + 1);
        CapBac testCapBac = capBacList.get(capBacList.size() - 1);
        assertThat(testCapBac.getMaCapBac()).isEqualTo(DEFAULT_MA_CAP_BAC);
        assertThat(testCapBac.getTenCapBac()).isEqualTo(DEFAULT_TEN_CAP_BAC);
        assertThat(testCapBac.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testCapBac.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testCapBac.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testCapBac.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testCapBac.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the CapBac in Elasticsearch
        verify(mockCapBacSearchRepository, times(1)).save(testCapBac);
    }

    @Test
    @Transactional
    public void createCapBacWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = capBacRepository.findAll().size();

        // Create the CapBac with an existing ID
        capBac.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCapBacMockMvc.perform(post("/api/cap-bacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(capBac)))
            .andExpect(status().isBadRequest());

        // Validate the CapBac in the database
        List<CapBac> capBacList = capBacRepository.findAll();
        assertThat(capBacList).hasSize(databaseSizeBeforeCreate);

        // Validate the CapBac in Elasticsearch
        verify(mockCapBacSearchRepository, times(0)).save(capBac);
    }

    @Test
    @Transactional
    public void getAllCapBacs() throws Exception {
        // Initialize the database
        capBacRepository.saveAndFlush(capBac);

        // Get all the capBacList
        restCapBacMockMvc.perform(get("/api/cap-bacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(capBac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCapBac").value(hasItem(DEFAULT_MA_CAP_BAC.toString())))
            .andExpect(jsonPath("$.[*].tenCapBac").value(hasItem(DEFAULT_TEN_CAP_BAC.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getCapBac() throws Exception {
        // Initialize the database
        capBacRepository.saveAndFlush(capBac);

        // Get the capBac
        restCapBacMockMvc.perform(get("/api/cap-bacs/{id}", capBac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(capBac.getId().intValue()))
            .andExpect(jsonPath("$.maCapBac").value(DEFAULT_MA_CAP_BAC.toString()))
            .andExpect(jsonPath("$.tenCapBac").value(DEFAULT_TEN_CAP_BAC.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCapBac() throws Exception {
        // Get the capBac
        restCapBacMockMvc.perform(get("/api/cap-bacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCapBac() throws Exception {
        // Initialize the database
        capBacRepository.saveAndFlush(capBac);

        int databaseSizeBeforeUpdate = capBacRepository.findAll().size();

        // Update the capBac
        CapBac updatedCapBac = capBacRepository.findById(capBac.getId()).get();
        // Disconnect from session so that the updates on updatedCapBac are not directly saved in db
        em.detach(updatedCapBac);
        updatedCapBac
            .maCapBac(UPDATED_MA_CAP_BAC)
            .tenCapBac(UPDATED_TEN_CAP_BAC)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restCapBacMockMvc.perform(put("/api/cap-bacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCapBac)))
            .andExpect(status().isOk());

        // Validate the CapBac in the database
        List<CapBac> capBacList = capBacRepository.findAll();
        assertThat(capBacList).hasSize(databaseSizeBeforeUpdate);
        CapBac testCapBac = capBacList.get(capBacList.size() - 1);
        assertThat(testCapBac.getMaCapBac()).isEqualTo(UPDATED_MA_CAP_BAC);
        assertThat(testCapBac.getTenCapBac()).isEqualTo(UPDATED_TEN_CAP_BAC);
        assertThat(testCapBac.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testCapBac.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testCapBac.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testCapBac.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testCapBac.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the CapBac in Elasticsearch
        verify(mockCapBacSearchRepository, times(1)).save(testCapBac);
    }

    @Test
    @Transactional
    public void updateNonExistingCapBac() throws Exception {
        int databaseSizeBeforeUpdate = capBacRepository.findAll().size();

        // Create the CapBac

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCapBacMockMvc.perform(put("/api/cap-bacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(capBac)))
            .andExpect(status().isBadRequest());

        // Validate the CapBac in the database
        List<CapBac> capBacList = capBacRepository.findAll();
        assertThat(capBacList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CapBac in Elasticsearch
        verify(mockCapBacSearchRepository, times(0)).save(capBac);
    }

    @Test
    @Transactional
    public void deleteCapBac() throws Exception {
        // Initialize the database
        capBacRepository.saveAndFlush(capBac);

        int databaseSizeBeforeDelete = capBacRepository.findAll().size();

        // Get the capBac
        restCapBacMockMvc.perform(delete("/api/cap-bacs/{id}", capBac.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CapBac> capBacList = capBacRepository.findAll();
        assertThat(capBacList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CapBac in Elasticsearch
        verify(mockCapBacSearchRepository, times(1)).deleteById(capBac.getId());
    }

    @Test
    @Transactional
    public void searchCapBac() throws Exception {
        // Initialize the database
        capBacRepository.saveAndFlush(capBac);
        when(mockCapBacSearchRepository.search(queryStringQuery("id:" + capBac.getId())))
            .thenReturn(Collections.singletonList(capBac));
        // Search the capBac
        restCapBacMockMvc.perform(get("/api/_search/cap-bacs?query=id:" + capBac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(capBac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCapBac").value(hasItem(DEFAULT_MA_CAP_BAC)))
            .andExpect(jsonPath("$.[*].tenCapBac").value(hasItem(DEFAULT_TEN_CAP_BAC)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CapBac.class);
        CapBac capBac1 = new CapBac();
        capBac1.setId(1L);
        CapBac capBac2 = new CapBac();
        capBac2.setId(capBac1.getId());
        assertThat(capBac1).isEqualTo(capBac2);
        capBac2.setId(2L);
        assertThat(capBac1).isNotEqualTo(capBac2);
        capBac1.setId(null);
        assertThat(capBac1).isNotEqualTo(capBac2);
    }
}
