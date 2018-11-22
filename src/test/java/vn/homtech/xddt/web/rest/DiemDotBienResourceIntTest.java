package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.DiemDotBien;
import vn.homtech.xddt.repository.DiemDotBienRepository;
import vn.homtech.xddt.repository.search.DiemDotBienSearchRepository;
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
 * Test class for the DiemDotBienResource REST controller.
 *
 * @see DiemDotBienResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class DiemDotBienResourceIntTest {

    private static final Integer DEFAULT_VI_TRI = 1;
    private static final Integer UPDATED_VI_TRI = 2;

    private static final String DEFAULT_GIA_TRI = "AAAAAAAAAA";
    private static final String UPDATED_GIA_TRI = "BBBBBBBBBB";

    private static final String DEFAULT_GIA_TRI_1 = "AAAAAAAAAA";
    private static final String UPDATED_GIA_TRI_1 = "BBBBBBBBBB";

    private static final String DEFAULT_GIA_TRI_2 = "AAAAAAAAAA";
    private static final String UPDATED_GIA_TRI_2 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private DiemDotBienRepository diemDotBienRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.DiemDotBienSearchRepositoryMockConfiguration
     */
    @Autowired
    private DiemDotBienSearchRepository mockDiemDotBienSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDiemDotBienMockMvc;

    private DiemDotBien diemDotBien;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DiemDotBienResource diemDotBienResource = new DiemDotBienResource(diemDotBienRepository, mockDiemDotBienSearchRepository);
        this.restDiemDotBienMockMvc = MockMvcBuilders.standaloneSetup(diemDotBienResource)
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
    public static DiemDotBien createEntity(EntityManager em) {
        DiemDotBien diemDotBien = new DiemDotBien()
            .viTri(DEFAULT_VI_TRI)
            .giaTri(DEFAULT_GIA_TRI)
            .giaTri1(DEFAULT_GIA_TRI_1)
            .giaTri2(DEFAULT_GIA_TRI_2)
            .isDeleted(DEFAULT_IS_DELETED);
        return diemDotBien;
    }

    @Before
    public void initTest() {
        diemDotBien = createEntity(em);
    }

    @Test
    @Transactional
    public void createDiemDotBien() throws Exception {
        int databaseSizeBeforeCreate = diemDotBienRepository.findAll().size();

        // Create the DiemDotBien
        restDiemDotBienMockMvc.perform(post("/api/diem-dot-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diemDotBien)))
            .andExpect(status().isCreated());

        // Validate the DiemDotBien in the database
        List<DiemDotBien> diemDotBienList = diemDotBienRepository.findAll();
        assertThat(diemDotBienList).hasSize(databaseSizeBeforeCreate + 1);
        DiemDotBien testDiemDotBien = diemDotBienList.get(diemDotBienList.size() - 1);
        assertThat(testDiemDotBien.getViTri()).isEqualTo(DEFAULT_VI_TRI);
        assertThat(testDiemDotBien.getGiaTri()).isEqualTo(DEFAULT_GIA_TRI);
        assertThat(testDiemDotBien.getGiaTri1()).isEqualTo(DEFAULT_GIA_TRI_1);
        assertThat(testDiemDotBien.getGiaTri2()).isEqualTo(DEFAULT_GIA_TRI_2);
        assertThat(testDiemDotBien.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);

        // Validate the DiemDotBien in Elasticsearch
        verify(mockDiemDotBienSearchRepository, times(1)).save(testDiemDotBien);
    }

    @Test
    @Transactional
    public void createDiemDotBienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diemDotBienRepository.findAll().size();

        // Create the DiemDotBien with an existing ID
        diemDotBien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiemDotBienMockMvc.perform(post("/api/diem-dot-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diemDotBien)))
            .andExpect(status().isBadRequest());

        // Validate the DiemDotBien in the database
        List<DiemDotBien> diemDotBienList = diemDotBienRepository.findAll();
        assertThat(diemDotBienList).hasSize(databaseSizeBeforeCreate);

        // Validate the DiemDotBien in Elasticsearch
        verify(mockDiemDotBienSearchRepository, times(0)).save(diemDotBien);
    }

    @Test
    @Transactional
    public void getAllDiemDotBiens() throws Exception {
        // Initialize the database
        diemDotBienRepository.saveAndFlush(diemDotBien);

        // Get all the diemDotBienList
        restDiemDotBienMockMvc.perform(get("/api/diem-dot-biens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diemDotBien.getId().intValue())))
            .andExpect(jsonPath("$.[*].viTri").value(hasItem(DEFAULT_VI_TRI)))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.toString())))
            .andExpect(jsonPath("$.[*].giaTri1").value(hasItem(DEFAULT_GIA_TRI_1.toString())))
            .andExpect(jsonPath("$.[*].giaTri2").value(hasItem(DEFAULT_GIA_TRI_2.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDiemDotBien() throws Exception {
        // Initialize the database
        diemDotBienRepository.saveAndFlush(diemDotBien);

        // Get the diemDotBien
        restDiemDotBienMockMvc.perform(get("/api/diem-dot-biens/{id}", diemDotBien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(diemDotBien.getId().intValue()))
            .andExpect(jsonPath("$.viTri").value(DEFAULT_VI_TRI))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.toString()))
            .andExpect(jsonPath("$.giaTri1").value(DEFAULT_GIA_TRI_1.toString()))
            .andExpect(jsonPath("$.giaTri2").value(DEFAULT_GIA_TRI_2.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDiemDotBien() throws Exception {
        // Get the diemDotBien
        restDiemDotBienMockMvc.perform(get("/api/diem-dot-biens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiemDotBien() throws Exception {
        // Initialize the database
        diemDotBienRepository.saveAndFlush(diemDotBien);

        int databaseSizeBeforeUpdate = diemDotBienRepository.findAll().size();

        // Update the diemDotBien
        DiemDotBien updatedDiemDotBien = diemDotBienRepository.findById(diemDotBien.getId()).get();
        // Disconnect from session so that the updates on updatedDiemDotBien are not directly saved in db
        em.detach(updatedDiemDotBien);
        updatedDiemDotBien
            .viTri(UPDATED_VI_TRI)
            .giaTri(UPDATED_GIA_TRI)
            .giaTri1(UPDATED_GIA_TRI_1)
            .giaTri2(UPDATED_GIA_TRI_2)
            .isDeleted(UPDATED_IS_DELETED);

        restDiemDotBienMockMvc.perform(put("/api/diem-dot-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDiemDotBien)))
            .andExpect(status().isOk());

        // Validate the DiemDotBien in the database
        List<DiemDotBien> diemDotBienList = diemDotBienRepository.findAll();
        assertThat(diemDotBienList).hasSize(databaseSizeBeforeUpdate);
        DiemDotBien testDiemDotBien = diemDotBienList.get(diemDotBienList.size() - 1);
        assertThat(testDiemDotBien.getViTri()).isEqualTo(UPDATED_VI_TRI);
        assertThat(testDiemDotBien.getGiaTri()).isEqualTo(UPDATED_GIA_TRI);
        assertThat(testDiemDotBien.getGiaTri1()).isEqualTo(UPDATED_GIA_TRI_1);
        assertThat(testDiemDotBien.getGiaTri2()).isEqualTo(UPDATED_GIA_TRI_2);
        assertThat(testDiemDotBien.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);

        // Validate the DiemDotBien in Elasticsearch
        verify(mockDiemDotBienSearchRepository, times(1)).save(testDiemDotBien);
    }

    @Test
    @Transactional
    public void updateNonExistingDiemDotBien() throws Exception {
        int databaseSizeBeforeUpdate = diemDotBienRepository.findAll().size();

        // Create the DiemDotBien

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiemDotBienMockMvc.perform(put("/api/diem-dot-biens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diemDotBien)))
            .andExpect(status().isBadRequest());

        // Validate the DiemDotBien in the database
        List<DiemDotBien> diemDotBienList = diemDotBienRepository.findAll();
        assertThat(diemDotBienList).hasSize(databaseSizeBeforeUpdate);

        // Validate the DiemDotBien in Elasticsearch
        verify(mockDiemDotBienSearchRepository, times(0)).save(diemDotBien);
    }

    @Test
    @Transactional
    public void deleteDiemDotBien() throws Exception {
        // Initialize the database
        diemDotBienRepository.saveAndFlush(diemDotBien);

        int databaseSizeBeforeDelete = diemDotBienRepository.findAll().size();

        // Get the diemDotBien
        restDiemDotBienMockMvc.perform(delete("/api/diem-dot-biens/{id}", diemDotBien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DiemDotBien> diemDotBienList = diemDotBienRepository.findAll();
        assertThat(diemDotBienList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the DiemDotBien in Elasticsearch
        verify(mockDiemDotBienSearchRepository, times(1)).deleteById(diemDotBien.getId());
    }

    @Test
    @Transactional
    public void searchDiemDotBien() throws Exception {
        // Initialize the database
        diemDotBienRepository.saveAndFlush(diemDotBien);
        when(mockDiemDotBienSearchRepository.search(queryStringQuery("id:" + diemDotBien.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(diemDotBien), PageRequest.of(0, 1), 1));
        // Search the diemDotBien
        restDiemDotBienMockMvc.perform(get("/api/_search/diem-dot-biens?query=id:" + diemDotBien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diemDotBien.getId().intValue())))
            .andExpect(jsonPath("$.[*].viTri").value(hasItem(DEFAULT_VI_TRI)))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI)))
            .andExpect(jsonPath("$.[*].giaTri1").value(hasItem(DEFAULT_GIA_TRI_1)))
            .andExpect(jsonPath("$.[*].giaTri2").value(hasItem(DEFAULT_GIA_TRI_2)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiemDotBien.class);
        DiemDotBien diemDotBien1 = new DiemDotBien();
        diemDotBien1.setId(1L);
        DiemDotBien diemDotBien2 = new DiemDotBien();
        diemDotBien2.setId(diemDotBien1.getId());
        assertThat(diemDotBien1).isEqualTo(diemDotBien2);
        diemDotBien2.setId(2L);
        assertThat(diemDotBien1).isNotEqualTo(diemDotBien2);
        diemDotBien1.setId(null);
        assertThat(diemDotBien1).isNotEqualTo(diemDotBien2);
    }
}
