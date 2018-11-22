package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.DonVi;
import vn.homtech.xddt.repository.DonViRepository;
import vn.homtech.xddt.repository.search.DonViSearchRepository;
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
 * Test class for the DonViResource REST controller.
 *
 * @see DonViResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class DonViResourceIntTest {

    private static final String DEFAULT_MA_DON_VI = "AAAAAAAAAA";
    private static final String UPDATED_MA_DON_VI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_DON_VI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DON_VI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_TAT = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TAT = "BBBBBBBBBB";

    private static final String DEFAULT_PHAN_MUC = "AAAAAAAAAA";
    private static final String UPDATED_PHAN_MUC = "BBBBBBBBBB";

    private static final String DEFAULT_PHAN_CAP = "AAAAAAAAAA";
    private static final String UPDATED_PHAN_CAP = "BBBBBBBBBB";

    private static final String DEFAULT_PHAN_KHOI = "AAAAAAAAAA";
    private static final String UPDATED_PHAN_KHOI = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private DonViRepository donViRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.DonViSearchRepositoryMockConfiguration
     */
    @Autowired
    private DonViSearchRepository mockDonViSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDonViMockMvc;

    private DonVi donVi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DonViResource donViResource = new DonViResource(donViRepository, mockDonViSearchRepository);
        this.restDonViMockMvc = MockMvcBuilders.standaloneSetup(donViResource)
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
    public static DonVi createEntity(EntityManager em) {
        DonVi donVi = new DonVi()
            .maDonVi(DEFAULT_MA_DON_VI)
            .tenDonVi(DEFAULT_TEN_DON_VI)
            .tenTat(DEFAULT_TEN_TAT)
            .phanMuc(DEFAULT_PHAN_MUC)
            .phanCap(DEFAULT_PHAN_CAP)
            .phanKhoi(DEFAULT_PHAN_KHOI)
            .moTa(DEFAULT_MO_TA)
            .ghiChu(DEFAULT_GHI_CHU)
            .isDeleted(DEFAULT_IS_DELETED);
        return donVi;
    }

    @Before
    public void initTest() {
        donVi = createEntity(em);
    }

    @Test
    @Transactional
    public void createDonVi() throws Exception {
        int databaseSizeBeforeCreate = donViRepository.findAll().size();

        // Create the DonVi
        restDonViMockMvc.perform(post("/api/don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donVi)))
            .andExpect(status().isCreated());

        // Validate the DonVi in the database
        List<DonVi> donViList = donViRepository.findAll();
        assertThat(donViList).hasSize(databaseSizeBeforeCreate + 1);
        DonVi testDonVi = donViList.get(donViList.size() - 1);
        assertThat(testDonVi.getMaDonVi()).isEqualTo(DEFAULT_MA_DON_VI);
        assertThat(testDonVi.getTenDonVi()).isEqualTo(DEFAULT_TEN_DON_VI);
        assertThat(testDonVi.getTenTat()).isEqualTo(DEFAULT_TEN_TAT);
        assertThat(testDonVi.getPhanMuc()).isEqualTo(DEFAULT_PHAN_MUC);
        assertThat(testDonVi.getPhanCap()).isEqualTo(DEFAULT_PHAN_CAP);
        assertThat(testDonVi.getPhanKhoi()).isEqualTo(DEFAULT_PHAN_KHOI);
        assertThat(testDonVi.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testDonVi.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testDonVi.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the DonVi in Elasticsearch
        verify(mockDonViSearchRepository, times(1)).save(testDonVi);
    }

    @Test
    @Transactional
    public void createDonViWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = donViRepository.findAll().size();

        // Create the DonVi with an existing ID
        donVi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonViMockMvc.perform(post("/api/don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donVi)))
            .andExpect(status().isBadRequest());

        // Validate the DonVi in the database
        List<DonVi> donViList = donViRepository.findAll();
        assertThat(donViList).hasSize(databaseSizeBeforeCreate);

        // Validate the DonVi in Elasticsearch
        verify(mockDonViSearchRepository, times(0)).save(donVi);
    }

    @Test
    @Transactional
    public void getAllDonVis() throws Exception {
        // Initialize the database
        donViRepository.saveAndFlush(donVi);

        // Get all the donViList
        restDonViMockMvc.perform(get("/api/don-vis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donVi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDonVi").value(hasItem(DEFAULT_MA_DON_VI.toString())))
            .andExpect(jsonPath("$.[*].tenDonVi").value(hasItem(DEFAULT_TEN_DON_VI.toString())))
            .andExpect(jsonPath("$.[*].tenTat").value(hasItem(DEFAULT_TEN_TAT.toString())))
            .andExpect(jsonPath("$.[*].phanMuc").value(hasItem(DEFAULT_PHAN_MUC.toString())))
            .andExpect(jsonPath("$.[*].phanCap").value(hasItem(DEFAULT_PHAN_CAP.toString())))
            .andExpect(jsonPath("$.[*].phanKhoi").value(hasItem(DEFAULT_PHAN_KHOI.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDonVi() throws Exception {
        // Initialize the database
        donViRepository.saveAndFlush(donVi);

        // Get the donVi
        restDonViMockMvc.perform(get("/api/don-vis/{id}", donVi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(donVi.getId().intValue()))
            .andExpect(jsonPath("$.maDonVi").value(DEFAULT_MA_DON_VI.toString()))
            .andExpect(jsonPath("$.tenDonVi").value(DEFAULT_TEN_DON_VI.toString()))
            .andExpect(jsonPath("$.tenTat").value(DEFAULT_TEN_TAT.toString()))
            .andExpect(jsonPath("$.phanMuc").value(DEFAULT_PHAN_MUC.toString()))
            .andExpect(jsonPath("$.phanCap").value(DEFAULT_PHAN_CAP.toString()))
            .andExpect(jsonPath("$.phanKhoi").value(DEFAULT_PHAN_KHOI.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDonVi() throws Exception {
        // Get the donVi
        restDonViMockMvc.perform(get("/api/don-vis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonVi() throws Exception {
        // Initialize the database
        donViRepository.saveAndFlush(donVi);

        int databaseSizeBeforeUpdate = donViRepository.findAll().size();

        // Update the donVi
        DonVi updatedDonVi = donViRepository.findById(donVi.getId()).get();
        // Disconnect from session so that the updates on updatedDonVi are not directly saved in db
        em.detach(updatedDonVi);
        updatedDonVi
            .maDonVi(UPDATED_MA_DON_VI)
            .tenDonVi(UPDATED_TEN_DON_VI)
            .tenTat(UPDATED_TEN_TAT)
            .phanMuc(UPDATED_PHAN_MUC)
            .phanCap(UPDATED_PHAN_CAP)
            .phanKhoi(UPDATED_PHAN_KHOI)
            .moTa(UPDATED_MO_TA)
            .ghiChu(UPDATED_GHI_CHU)
            .isDeleted(UPDATED_IS_DELETED);

        restDonViMockMvc.perform(put("/api/don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDonVi)))
            .andExpect(status().isOk());

        // Validate the DonVi in the database
        List<DonVi> donViList = donViRepository.findAll();
        assertThat(donViList).hasSize(databaseSizeBeforeUpdate);
        DonVi testDonVi = donViList.get(donViList.size() - 1);
        assertThat(testDonVi.getMaDonVi()).isEqualTo(UPDATED_MA_DON_VI);
        assertThat(testDonVi.getTenDonVi()).isEqualTo(UPDATED_TEN_DON_VI);
        assertThat(testDonVi.getTenTat()).isEqualTo(UPDATED_TEN_TAT);
        assertThat(testDonVi.getPhanMuc()).isEqualTo(UPDATED_PHAN_MUC);
        assertThat(testDonVi.getPhanCap()).isEqualTo(UPDATED_PHAN_CAP);
        assertThat(testDonVi.getPhanKhoi()).isEqualTo(UPDATED_PHAN_KHOI);
        assertThat(testDonVi.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testDonVi.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testDonVi.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the DonVi in Elasticsearch
        verify(mockDonViSearchRepository, times(1)).save(testDonVi);
    }

    @Test
    @Transactional
    public void updateNonExistingDonVi() throws Exception {
        int databaseSizeBeforeUpdate = donViRepository.findAll().size();

        // Create the DonVi

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonViMockMvc.perform(put("/api/don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donVi)))
            .andExpect(status().isBadRequest());

        // Validate the DonVi in the database
        List<DonVi> donViList = donViRepository.findAll();
        assertThat(donViList).hasSize(databaseSizeBeforeUpdate);

        // Validate the DonVi in Elasticsearch
        verify(mockDonViSearchRepository, times(0)).save(donVi);
    }

    @Test
    @Transactional
    public void deleteDonVi() throws Exception {
        // Initialize the database
        donViRepository.saveAndFlush(donVi);

        int databaseSizeBeforeDelete = donViRepository.findAll().size();

        // Get the donVi
        restDonViMockMvc.perform(delete("/api/don-vis/{id}", donVi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DonVi> donViList = donViRepository.findAll();
        assertThat(donViList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the DonVi in Elasticsearch
        verify(mockDonViSearchRepository, times(1)).deleteById(donVi.getId());
    }

    @Test
    @Transactional
    public void searchDonVi() throws Exception {
        // Initialize the database
        donViRepository.saveAndFlush(donVi);
        when(mockDonViSearchRepository.search(queryStringQuery("id:" + donVi.getId())))
            .thenReturn(Collections.singletonList(donVi));
        // Search the donVi
        restDonViMockMvc.perform(get("/api/_search/don-vis?query=id:" + donVi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donVi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDonVi").value(hasItem(DEFAULT_MA_DON_VI)))
            .andExpect(jsonPath("$.[*].tenDonVi").value(hasItem(DEFAULT_TEN_DON_VI)))
            .andExpect(jsonPath("$.[*].tenTat").value(hasItem(DEFAULT_TEN_TAT)))
            .andExpect(jsonPath("$.[*].phanMuc").value(hasItem(DEFAULT_PHAN_MUC)))
            .andExpect(jsonPath("$.[*].phanCap").value(hasItem(DEFAULT_PHAN_CAP)))
            .andExpect(jsonPath("$.[*].phanKhoi").value(hasItem(DEFAULT_PHAN_KHOI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonVi.class);
        DonVi donVi1 = new DonVi();
        donVi1.setId(1L);
        DonVi donVi2 = new DonVi();
        donVi2.setId(donVi1.getId());
        assertThat(donVi1).isEqualTo(donVi2);
        donVi2.setId(2L);
        assertThat(donVi1).isNotEqualTo(donVi2);
        donVi1.setId(null);
        assertThat(donVi1).isNotEqualTo(donVi2);
    }
}
