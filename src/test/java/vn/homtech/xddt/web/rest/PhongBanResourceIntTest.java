package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.PhongBan;
import vn.homtech.xddt.repository.PhongBanRepository;
import vn.homtech.xddt.repository.search.PhongBanSearchRepository;
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
 * Test class for the PhongBanResource REST controller.
 *
 * @see PhongBanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class PhongBanResourceIntTest {

    private static final String DEFAULT_MA_PHONG_BAN = "AAAAAAAAAA";
    private static final String UPDATED_MA_PHONG_BAN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_PHONG_BAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_PHONG_BAN = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private PhongBanRepository phongBanRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.PhongBanSearchRepositoryMockConfiguration
     */
    @Autowired
    private PhongBanSearchRepository mockPhongBanSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPhongBanMockMvc;

    private PhongBan phongBan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhongBanResource phongBanResource = new PhongBanResource(phongBanRepository, mockPhongBanSearchRepository);
        this.restPhongBanMockMvc = MockMvcBuilders.standaloneSetup(phongBanResource)
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
    public static PhongBan createEntity(EntityManager em) {
        PhongBan phongBan = new PhongBan()
            .maPhongBan(DEFAULT_MA_PHONG_BAN)
            .tenPhongBan(DEFAULT_TEN_PHONG_BAN)
            .moTa(DEFAULT_MO_TA)
            .isActive(DEFAULT_IS_ACTIVE)
            .isDeleted(DEFAULT_IS_DELETED);
        return phongBan;
    }

    @Before
    public void initTest() {
        phongBan = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhongBan() throws Exception {
        int databaseSizeBeforeCreate = phongBanRepository.findAll().size();

        // Create the PhongBan
        restPhongBanMockMvc.perform(post("/api/phong-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phongBan)))
            .andExpect(status().isCreated());

        // Validate the PhongBan in the database
        List<PhongBan> phongBanList = phongBanRepository.findAll();
        assertThat(phongBanList).hasSize(databaseSizeBeforeCreate + 1);
        PhongBan testPhongBan = phongBanList.get(phongBanList.size() - 1);
        assertThat(testPhongBan.getMaPhongBan()).isEqualTo(DEFAULT_MA_PHONG_BAN);
        assertThat(testPhongBan.getTenPhongBan()).isEqualTo(DEFAULT_TEN_PHONG_BAN);
        assertThat(testPhongBan.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testPhongBan.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testPhongBan.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the PhongBan in Elasticsearch
        verify(mockPhongBanSearchRepository, times(1)).save(testPhongBan);
    }

    @Test
    @Transactional
    public void createPhongBanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = phongBanRepository.findAll().size();

        // Create the PhongBan with an existing ID
        phongBan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhongBanMockMvc.perform(post("/api/phong-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phongBan)))
            .andExpect(status().isBadRequest());

        // Validate the PhongBan in the database
        List<PhongBan> phongBanList = phongBanRepository.findAll();
        assertThat(phongBanList).hasSize(databaseSizeBeforeCreate);

        // Validate the PhongBan in Elasticsearch
        verify(mockPhongBanSearchRepository, times(0)).save(phongBan);
    }

    @Test
    @Transactional
    public void getAllPhongBans() throws Exception {
        // Initialize the database
        phongBanRepository.saveAndFlush(phongBan);

        // Get all the phongBanList
        restPhongBanMockMvc.perform(get("/api/phong-bans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phongBan.getId().intValue())))
            .andExpect(jsonPath("$.[*].maPhongBan").value(hasItem(DEFAULT_MA_PHONG_BAN.toString())))
            .andExpect(jsonPath("$.[*].tenPhongBan").value(hasItem(DEFAULT_TEN_PHONG_BAN.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPhongBan() throws Exception {
        // Initialize the database
        phongBanRepository.saveAndFlush(phongBan);

        // Get the phongBan
        restPhongBanMockMvc.perform(get("/api/phong-bans/{id}", phongBan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(phongBan.getId().intValue()))
            .andExpect(jsonPath("$.maPhongBan").value(DEFAULT_MA_PHONG_BAN.toString()))
            .andExpect(jsonPath("$.tenPhongBan").value(DEFAULT_TEN_PHONG_BAN.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPhongBan() throws Exception {
        // Get the phongBan
        restPhongBanMockMvc.perform(get("/api/phong-bans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhongBan() throws Exception {
        // Initialize the database
        phongBanRepository.saveAndFlush(phongBan);

        int databaseSizeBeforeUpdate = phongBanRepository.findAll().size();

        // Update the phongBan
        PhongBan updatedPhongBan = phongBanRepository.findById(phongBan.getId()).get();
        // Disconnect from session so that the updates on updatedPhongBan are not directly saved in db
        em.detach(updatedPhongBan);
        updatedPhongBan
            .maPhongBan(UPDATED_MA_PHONG_BAN)
            .tenPhongBan(UPDATED_TEN_PHONG_BAN)
            .moTa(UPDATED_MO_TA)
            .isActive(UPDATED_IS_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED);

        restPhongBanMockMvc.perform(put("/api/phong-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPhongBan)))
            .andExpect(status().isOk());

        // Validate the PhongBan in the database
        List<PhongBan> phongBanList = phongBanRepository.findAll();
        assertThat(phongBanList).hasSize(databaseSizeBeforeUpdate);
        PhongBan testPhongBan = phongBanList.get(phongBanList.size() - 1);
        assertThat(testPhongBan.getMaPhongBan()).isEqualTo(UPDATED_MA_PHONG_BAN);
        assertThat(testPhongBan.getTenPhongBan()).isEqualTo(UPDATED_TEN_PHONG_BAN);
        assertThat(testPhongBan.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testPhongBan.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testPhongBan.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the PhongBan in Elasticsearch
        verify(mockPhongBanSearchRepository, times(1)).save(testPhongBan);
    }

    @Test
    @Transactional
    public void updateNonExistingPhongBan() throws Exception {
        int databaseSizeBeforeUpdate = phongBanRepository.findAll().size();

        // Create the PhongBan

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhongBanMockMvc.perform(put("/api/phong-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phongBan)))
            .andExpect(status().isBadRequest());

        // Validate the PhongBan in the database
        List<PhongBan> phongBanList = phongBanRepository.findAll();
        assertThat(phongBanList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PhongBan in Elasticsearch
        verify(mockPhongBanSearchRepository, times(0)).save(phongBan);
    }

    @Test
    @Transactional
    public void deletePhongBan() throws Exception {
        // Initialize the database
        phongBanRepository.saveAndFlush(phongBan);

        int databaseSizeBeforeDelete = phongBanRepository.findAll().size();

        // Get the phongBan
        restPhongBanMockMvc.perform(delete("/api/phong-bans/{id}", phongBan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PhongBan> phongBanList = phongBanRepository.findAll();
        assertThat(phongBanList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PhongBan in Elasticsearch
        verify(mockPhongBanSearchRepository, times(1)).deleteById(phongBan.getId());
    }

    @Test
    @Transactional
    public void searchPhongBan() throws Exception {
        // Initialize the database
        phongBanRepository.saveAndFlush(phongBan);
        when(mockPhongBanSearchRepository.search(queryStringQuery("id:" + phongBan.getId())))
            .thenReturn(Collections.singletonList(phongBan));
        // Search the phongBan
        restPhongBanMockMvc.perform(get("/api/_search/phong-bans?query=id:" + phongBan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phongBan.getId().intValue())))
            .andExpect(jsonPath("$.[*].maPhongBan").value(hasItem(DEFAULT_MA_PHONG_BAN)))
            .andExpect(jsonPath("$.[*].tenPhongBan").value(hasItem(DEFAULT_TEN_PHONG_BAN)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhongBan.class);
        PhongBan phongBan1 = new PhongBan();
        phongBan1.setId(1L);
        PhongBan phongBan2 = new PhongBan();
        phongBan2.setId(phongBan1.getId());
        assertThat(phongBan1).isEqualTo(phongBan2);
        phongBan2.setId(2L);
        assertThat(phongBan1).isNotEqualTo(phongBan2);
        phongBan1.setId(null);
        assertThat(phongBan1).isNotEqualTo(phongBan2);
    }
}
