package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.ThaoTac;
import vn.homtech.xddt.repository.ThaoTacRepository;
import vn.homtech.xddt.repository.search.ThaoTacSearchRepository;
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
 * Test class for the ThaoTacResource REST controller.
 *
 * @see ThaoTacResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class ThaoTacResourceIntTest {

    private static final String DEFAULT_MO_TA_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA_KET_QUA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TRANG_THAI_XU_LY = false;
    private static final Boolean UPDATED_TRANG_THAI_XU_LY = true;

    private static final Boolean DEFAULT_IS_DANG_THUC_HIEN = false;
    private static final Boolean UPDATED_IS_DANG_THUC_HIEN = true;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ThaoTacRepository thaoTacRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.ThaoTacSearchRepositoryMockConfiguration
     */
    @Autowired
    private ThaoTacSearchRepository mockThaoTacSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restThaoTacMockMvc;

    private ThaoTac thaoTac;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThaoTacResource thaoTacResource = new ThaoTacResource(thaoTacRepository, mockThaoTacSearchRepository);
        this.restThaoTacMockMvc = MockMvcBuilders.standaloneSetup(thaoTacResource)
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
    public static ThaoTac createEntity(EntityManager em) {
        ThaoTac thaoTac = new ThaoTac()
            .moTaKetQua(DEFAULT_MO_TA_KET_QUA)
            .trangThaiXuLy(DEFAULT_TRANG_THAI_XU_LY)
            .isDangThucHien(DEFAULT_IS_DANG_THUC_HIEN)
            .isDeleted(DEFAULT_IS_DELETED);
        return thaoTac;
    }

    @Before
    public void initTest() {
        thaoTac = createEntity(em);
    }

    @Test
    @Transactional
    public void createThaoTac() throws Exception {
        int databaseSizeBeforeCreate = thaoTacRepository.findAll().size();

        // Create the ThaoTac
        restThaoTacMockMvc.perform(post("/api/thao-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thaoTac)))
            .andExpect(status().isCreated());

        // Validate the ThaoTac in the database
        List<ThaoTac> thaoTacList = thaoTacRepository.findAll();
        assertThat(thaoTacList).hasSize(databaseSizeBeforeCreate + 1);
        ThaoTac testThaoTac = thaoTacList.get(thaoTacList.size() - 1);
        assertThat(testThaoTac.getMoTaKetQua()).isEqualTo(DEFAULT_MO_TA_KET_QUA);
        assertThat(testThaoTac.isTrangThaiXuLy()).isEqualTo(DEFAULT_TRANG_THAI_XU_LY);
        assertThat(testThaoTac.isIsDangThucHien()).isEqualTo(DEFAULT_IS_DANG_THUC_HIEN);
        assertThat(testThaoTac.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the ThaoTac in Elasticsearch
        verify(mockThaoTacSearchRepository, times(1)).save(testThaoTac);
    }

    @Test
    @Transactional
    public void createThaoTacWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thaoTacRepository.findAll().size();

        // Create the ThaoTac with an existing ID
        thaoTac.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThaoTacMockMvc.perform(post("/api/thao-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thaoTac)))
            .andExpect(status().isBadRequest());

        // Validate the ThaoTac in the database
        List<ThaoTac> thaoTacList = thaoTacRepository.findAll();
        assertThat(thaoTacList).hasSize(databaseSizeBeforeCreate);

        // Validate the ThaoTac in Elasticsearch
        verify(mockThaoTacSearchRepository, times(0)).save(thaoTac);
    }

    @Test
    @Transactional
    public void getAllThaoTacs() throws Exception {
        // Initialize the database
        thaoTacRepository.saveAndFlush(thaoTac);

        // Get all the thaoTacList
        restThaoTacMockMvc.perform(get("/api/thao-tacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thaoTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTaKetQua").value(hasItem(DEFAULT_MO_TA_KET_QUA.toString())))
            .andExpect(jsonPath("$.[*].trangThaiXuLy").value(hasItem(DEFAULT_TRANG_THAI_XU_LY.booleanValue())))
            .andExpect(jsonPath("$.[*].isDangThucHien").value(hasItem(DEFAULT_IS_DANG_THUC_HIEN.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getThaoTac() throws Exception {
        // Initialize the database
        thaoTacRepository.saveAndFlush(thaoTac);

        // Get the thaoTac
        restThaoTacMockMvc.perform(get("/api/thao-tacs/{id}", thaoTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thaoTac.getId().intValue()))
            .andExpect(jsonPath("$.moTaKetQua").value(DEFAULT_MO_TA_KET_QUA.toString()))
            .andExpect(jsonPath("$.trangThaiXuLy").value(DEFAULT_TRANG_THAI_XU_LY.booleanValue()))
            .andExpect(jsonPath("$.isDangThucHien").value(DEFAULT_IS_DANG_THUC_HIEN.booleanValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingThaoTac() throws Exception {
        // Get the thaoTac
        restThaoTacMockMvc.perform(get("/api/thao-tacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThaoTac() throws Exception {
        // Initialize the database
        thaoTacRepository.saveAndFlush(thaoTac);

        int databaseSizeBeforeUpdate = thaoTacRepository.findAll().size();

        // Update the thaoTac
        ThaoTac updatedThaoTac = thaoTacRepository.findById(thaoTac.getId()).get();
        // Disconnect from session so that the updates on updatedThaoTac are not directly saved in db
        em.detach(updatedThaoTac);
        updatedThaoTac
            .moTaKetQua(UPDATED_MO_TA_KET_QUA)
            .trangThaiXuLy(UPDATED_TRANG_THAI_XU_LY)
            .isDangThucHien(UPDATED_IS_DANG_THUC_HIEN)
            .isDeleted(UPDATED_IS_DELETED);

        restThaoTacMockMvc.perform(put("/api/thao-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedThaoTac)))
            .andExpect(status().isOk());

        // Validate the ThaoTac in the database
        List<ThaoTac> thaoTacList = thaoTacRepository.findAll();
        assertThat(thaoTacList).hasSize(databaseSizeBeforeUpdate);
        ThaoTac testThaoTac = thaoTacList.get(thaoTacList.size() - 1);
        assertThat(testThaoTac.getMoTaKetQua()).isEqualTo(UPDATED_MO_TA_KET_QUA);
        assertThat(testThaoTac.isTrangThaiXuLy()).isEqualTo(UPDATED_TRANG_THAI_XU_LY);
        assertThat(testThaoTac.isIsDangThucHien()).isEqualTo(UPDATED_IS_DANG_THUC_HIEN);
        assertThat(testThaoTac.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the ThaoTac in Elasticsearch
        verify(mockThaoTacSearchRepository, times(1)).save(testThaoTac);
    }

    @Test
    @Transactional
    public void updateNonExistingThaoTac() throws Exception {
        int databaseSizeBeforeUpdate = thaoTacRepository.findAll().size();

        // Create the ThaoTac

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThaoTacMockMvc.perform(put("/api/thao-tacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thaoTac)))
            .andExpect(status().isBadRequest());

        // Validate the ThaoTac in the database
        List<ThaoTac> thaoTacList = thaoTacRepository.findAll();
        assertThat(thaoTacList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ThaoTac in Elasticsearch
        verify(mockThaoTacSearchRepository, times(0)).save(thaoTac);
    }

    @Test
    @Transactional
    public void deleteThaoTac() throws Exception {
        // Initialize the database
        thaoTacRepository.saveAndFlush(thaoTac);

        int databaseSizeBeforeDelete = thaoTacRepository.findAll().size();

        // Get the thaoTac
        restThaoTacMockMvc.perform(delete("/api/thao-tacs/{id}", thaoTac.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ThaoTac> thaoTacList = thaoTacRepository.findAll();
        assertThat(thaoTacList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ThaoTac in Elasticsearch
        verify(mockThaoTacSearchRepository, times(1)).deleteById(thaoTac.getId());
    }

    @Test
    @Transactional
    public void searchThaoTac() throws Exception {
        // Initialize the database
        thaoTacRepository.saveAndFlush(thaoTac);
        when(mockThaoTacSearchRepository.search(queryStringQuery("id:" + thaoTac.getId())))
            .thenReturn(Collections.singletonList(thaoTac));
        // Search the thaoTac
        restThaoTacMockMvc.perform(get("/api/_search/thao-tacs?query=id:" + thaoTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thaoTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTaKetQua").value(hasItem(DEFAULT_MO_TA_KET_QUA)))
            .andExpect(jsonPath("$.[*].trangThaiXuLy").value(hasItem(DEFAULT_TRANG_THAI_XU_LY.booleanValue())))
            .andExpect(jsonPath("$.[*].isDangThucHien").value(hasItem(DEFAULT_IS_DANG_THUC_HIEN.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThaoTac.class);
        ThaoTac thaoTac1 = new ThaoTac();
        thaoTac1.setId(1L);
        ThaoTac thaoTac2 = new ThaoTac();
        thaoTac2.setId(thaoTac1.getId());
        assertThat(thaoTac1).isEqualTo(thaoTac2);
        thaoTac2.setId(2L);
        assertThat(thaoTac1).isNotEqualTo(thaoTac2);
        thaoTac1.setId(null);
        assertThat(thaoTac1).isNotEqualTo(thaoTac2);
    }
}
