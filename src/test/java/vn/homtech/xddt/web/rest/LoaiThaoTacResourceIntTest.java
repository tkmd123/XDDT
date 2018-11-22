package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.LoaiThaoTac;
import vn.homtech.xddt.repository.LoaiThaoTacRepository;
import vn.homtech.xddt.repository.search.LoaiThaoTacSearchRepository;
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
 * Test class for the LoaiThaoTacResource REST controller.
 *
 * @see LoaiThaoTacResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class LoaiThaoTacResourceIntTest {

    private static final String DEFAULT_MA_LOAI_THAO_TAC = "AAAAAAAAAA";
    private static final String UPDATED_MA_LOAI_THAO_TAC = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_LOAI_THAO_TAC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI_THAO_TAC = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private LoaiThaoTacRepository loaiThaoTacRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.LoaiThaoTacSearchRepositoryMockConfiguration
     */
    @Autowired
    private LoaiThaoTacSearchRepository mockLoaiThaoTacSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoaiThaoTacMockMvc;

    private LoaiThaoTac loaiThaoTac;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoaiThaoTacResource loaiThaoTacResource = new LoaiThaoTacResource(loaiThaoTacRepository, mockLoaiThaoTacSearchRepository);
        this.restLoaiThaoTacMockMvc = MockMvcBuilders.standaloneSetup(loaiThaoTacResource)
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
    public static LoaiThaoTac createEntity(EntityManager em) {
        LoaiThaoTac loaiThaoTac = new LoaiThaoTac()
            .maLoaiThaoTac(DEFAULT_MA_LOAI_THAO_TAC)
            .tenLoaiThaoTac(DEFAULT_TEN_LOAI_THAO_TAC)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED);
        return loaiThaoTac;
    }

    @Before
    public void initTest() {
        loaiThaoTac = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoaiThaoTac() throws Exception {
        int databaseSizeBeforeCreate = loaiThaoTacRepository.findAll().size();

        // Create the LoaiThaoTac
        restLoaiThaoTacMockMvc.perform(post("/api/loai-thao-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiThaoTac)))
            .andExpect(status().isCreated());

        // Validate the LoaiThaoTac in the database
        List<LoaiThaoTac> loaiThaoTacList = loaiThaoTacRepository.findAll();
        assertThat(loaiThaoTacList).hasSize(databaseSizeBeforeCreate + 1);
        LoaiThaoTac testLoaiThaoTac = loaiThaoTacList.get(loaiThaoTacList.size() - 1);
        assertThat(testLoaiThaoTac.getMaLoaiThaoTac()).isEqualTo(DEFAULT_MA_LOAI_THAO_TAC);
        assertThat(testLoaiThaoTac.getTenLoaiThaoTac()).isEqualTo(DEFAULT_TEN_LOAI_THAO_TAC);
        assertThat(testLoaiThaoTac.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testLoaiThaoTac.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the LoaiThaoTac in Elasticsearch
        verify(mockLoaiThaoTacSearchRepository, times(1)).save(testLoaiThaoTac);
    }

    @Test
    @Transactional
    public void createLoaiThaoTacWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loaiThaoTacRepository.findAll().size();

        // Create the LoaiThaoTac with an existing ID
        loaiThaoTac.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiThaoTacMockMvc.perform(post("/api/loai-thao-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiThaoTac)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiThaoTac in the database
        List<LoaiThaoTac> loaiThaoTacList = loaiThaoTacRepository.findAll();
        assertThat(loaiThaoTacList).hasSize(databaseSizeBeforeCreate);

        // Validate the LoaiThaoTac in Elasticsearch
        verify(mockLoaiThaoTacSearchRepository, times(0)).save(loaiThaoTac);
    }

    @Test
    @Transactional
    public void getAllLoaiThaoTacs() throws Exception {
        // Initialize the database
        loaiThaoTacRepository.saveAndFlush(loaiThaoTac);

        // Get all the loaiThaoTacList
        restLoaiThaoTacMockMvc.perform(get("/api/loai-thao-tacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiThaoTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoaiThaoTac").value(hasItem(DEFAULT_MA_LOAI_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].tenLoaiThaoTac").value(hasItem(DEFAULT_TEN_LOAI_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getLoaiThaoTac() throws Exception {
        // Initialize the database
        loaiThaoTacRepository.saveAndFlush(loaiThaoTac);

        // Get the loaiThaoTac
        restLoaiThaoTacMockMvc.perform(get("/api/loai-thao-tacs/{id}", loaiThaoTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loaiThaoTac.getId().intValue()))
            .andExpect(jsonPath("$.maLoaiThaoTac").value(DEFAULT_MA_LOAI_THAO_TAC.toString()))
            .andExpect(jsonPath("$.tenLoaiThaoTac").value(DEFAULT_TEN_LOAI_THAO_TAC.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLoaiThaoTac() throws Exception {
        // Get the loaiThaoTac
        restLoaiThaoTacMockMvc.perform(get("/api/loai-thao-tacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoaiThaoTac() throws Exception {
        // Initialize the database
        loaiThaoTacRepository.saveAndFlush(loaiThaoTac);

        int databaseSizeBeforeUpdate = loaiThaoTacRepository.findAll().size();

        // Update the loaiThaoTac
        LoaiThaoTac updatedLoaiThaoTac = loaiThaoTacRepository.findById(loaiThaoTac.getId()).get();
        // Disconnect from session so that the updates on updatedLoaiThaoTac are not directly saved in db
        em.detach(updatedLoaiThaoTac);
        updatedLoaiThaoTac
            .maLoaiThaoTac(UPDATED_MA_LOAI_THAO_TAC)
            .tenLoaiThaoTac(UPDATED_TEN_LOAI_THAO_TAC)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED);

        restLoaiThaoTacMockMvc.perform(put("/api/loai-thao-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoaiThaoTac)))
            .andExpect(status().isOk());

        // Validate the LoaiThaoTac in the database
        List<LoaiThaoTac> loaiThaoTacList = loaiThaoTacRepository.findAll();
        assertThat(loaiThaoTacList).hasSize(databaseSizeBeforeUpdate);
        LoaiThaoTac testLoaiThaoTac = loaiThaoTacList.get(loaiThaoTacList.size() - 1);
        assertThat(testLoaiThaoTac.getMaLoaiThaoTac()).isEqualTo(UPDATED_MA_LOAI_THAO_TAC);
        assertThat(testLoaiThaoTac.getTenLoaiThaoTac()).isEqualTo(UPDATED_TEN_LOAI_THAO_TAC);
        assertThat(testLoaiThaoTac.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testLoaiThaoTac.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the LoaiThaoTac in Elasticsearch
        verify(mockLoaiThaoTacSearchRepository, times(1)).save(testLoaiThaoTac);
    }

    @Test
    @Transactional
    public void updateNonExistingLoaiThaoTac() throws Exception {
        int databaseSizeBeforeUpdate = loaiThaoTacRepository.findAll().size();

        // Create the LoaiThaoTac

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiThaoTacMockMvc.perform(put("/api/loai-thao-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiThaoTac)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiThaoTac in the database
        List<LoaiThaoTac> loaiThaoTacList = loaiThaoTacRepository.findAll();
        assertThat(loaiThaoTacList).hasSize(databaseSizeBeforeUpdate);

        // Validate the LoaiThaoTac in Elasticsearch
        verify(mockLoaiThaoTacSearchRepository, times(0)).save(loaiThaoTac);
    }

    @Test
    @Transactional
    public void deleteLoaiThaoTac() throws Exception {
        // Initialize the database
        loaiThaoTacRepository.saveAndFlush(loaiThaoTac);

        int databaseSizeBeforeDelete = loaiThaoTacRepository.findAll().size();

        // Get the loaiThaoTac
        restLoaiThaoTacMockMvc.perform(delete("/api/loai-thao-tacs/{id}", loaiThaoTac.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LoaiThaoTac> loaiThaoTacList = loaiThaoTacRepository.findAll();
        assertThat(loaiThaoTacList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the LoaiThaoTac in Elasticsearch
        verify(mockLoaiThaoTacSearchRepository, times(1)).deleteById(loaiThaoTac.getId());
    }

    @Test
    @Transactional
    public void searchLoaiThaoTac() throws Exception {
        // Initialize the database
        loaiThaoTacRepository.saveAndFlush(loaiThaoTac);
        when(mockLoaiThaoTacSearchRepository.search(queryStringQuery("id:" + loaiThaoTac.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(loaiThaoTac), PageRequest.of(0, 1), 1));
        // Search the loaiThaoTac
        restLoaiThaoTacMockMvc.perform(get("/api/_search/loai-thao-tacs?query=id:" + loaiThaoTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiThaoTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoaiThaoTac").value(hasItem(DEFAULT_MA_LOAI_THAO_TAC)))
            .andExpect(jsonPath("$.[*].tenLoaiThaoTac").value(hasItem(DEFAULT_TEN_LOAI_THAO_TAC)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiThaoTac.class);
        LoaiThaoTac loaiThaoTac1 = new LoaiThaoTac();
        loaiThaoTac1.setId(1L);
        LoaiThaoTac loaiThaoTac2 = new LoaiThaoTac();
        loaiThaoTac2.setId(loaiThaoTac1.getId());
        assertThat(loaiThaoTac1).isEqualTo(loaiThaoTac2);
        loaiThaoTac2.setId(2L);
        assertThat(loaiThaoTac1).isNotEqualTo(loaiThaoTac2);
        loaiThaoTac1.setId(null);
        assertThat(loaiThaoTac1).isNotEqualTo(loaiThaoTac2);
    }
}
