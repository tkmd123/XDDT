package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.LoaiMauXetNghiem;
import vn.homtech.xddt.repository.LoaiMauXetNghiemRepository;
import vn.homtech.xddt.repository.search.LoaiMauXetNghiemSearchRepository;
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
 * Test class for the LoaiMauXetNghiemResource REST controller.
 *
 * @see LoaiMauXetNghiemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class LoaiMauXetNghiemResourceIntTest {

    private static final String DEFAULT_MA_LOAI_MAU = "AAAAAAAAAA";
    private static final String UPDATED_MA_LOAI_MAU = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_LOAI_MAU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI_MAU = "BBBBBBBBBB";

    private static final String DEFAULT_PHAN_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_PHAN_LOAI = "BBBBBBBBBB";

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
    private LoaiMauXetNghiemRepository loaiMauXetNghiemRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.LoaiMauXetNghiemSearchRepositoryMockConfiguration
     */
    @Autowired
    private LoaiMauXetNghiemSearchRepository mockLoaiMauXetNghiemSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoaiMauXetNghiemMockMvc;

    private LoaiMauXetNghiem loaiMauXetNghiem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoaiMauXetNghiemResource loaiMauXetNghiemResource = new LoaiMauXetNghiemResource(loaiMauXetNghiemRepository, mockLoaiMauXetNghiemSearchRepository);
        this.restLoaiMauXetNghiemMockMvc = MockMvcBuilders.standaloneSetup(loaiMauXetNghiemResource)
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
    public static LoaiMauXetNghiem createEntity(EntityManager em) {
        LoaiMauXetNghiem loaiMauXetNghiem = new LoaiMauXetNghiem()
            .maLoaiMau(DEFAULT_MA_LOAI_MAU)
            .tenLoaiMau(DEFAULT_TEN_LOAI_MAU)
            .phanLoai(DEFAULT_PHAN_LOAI)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return loaiMauXetNghiem;
    }

    @Before
    public void initTest() {
        loaiMauXetNghiem = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoaiMauXetNghiem() throws Exception {
        int databaseSizeBeforeCreate = loaiMauXetNghiemRepository.findAll().size();

        // Create the LoaiMauXetNghiem
        restLoaiMauXetNghiemMockMvc.perform(post("/api/loai-mau-xet-nghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiMauXetNghiem)))
            .andExpect(status().isCreated());

        // Validate the LoaiMauXetNghiem in the database
        List<LoaiMauXetNghiem> loaiMauXetNghiemList = loaiMauXetNghiemRepository.findAll();
        assertThat(loaiMauXetNghiemList).hasSize(databaseSizeBeforeCreate + 1);
        LoaiMauXetNghiem testLoaiMauXetNghiem = loaiMauXetNghiemList.get(loaiMauXetNghiemList.size() - 1);
        assertThat(testLoaiMauXetNghiem.getMaLoaiMau()).isEqualTo(DEFAULT_MA_LOAI_MAU);
        assertThat(testLoaiMauXetNghiem.getTenLoaiMau()).isEqualTo(DEFAULT_TEN_LOAI_MAU);
        assertThat(testLoaiMauXetNghiem.getPhanLoai()).isEqualTo(DEFAULT_PHAN_LOAI);
        assertThat(testLoaiMauXetNghiem.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testLoaiMauXetNghiem.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testLoaiMauXetNghiem.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testLoaiMauXetNghiem.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testLoaiMauXetNghiem.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the LoaiMauXetNghiem in Elasticsearch
        verify(mockLoaiMauXetNghiemSearchRepository, times(1)).save(testLoaiMauXetNghiem);
    }

    @Test
    @Transactional
    public void createLoaiMauXetNghiemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loaiMauXetNghiemRepository.findAll().size();

        // Create the LoaiMauXetNghiem with an existing ID
        loaiMauXetNghiem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiMauXetNghiemMockMvc.perform(post("/api/loai-mau-xet-nghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiMauXetNghiem)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiMauXetNghiem in the database
        List<LoaiMauXetNghiem> loaiMauXetNghiemList = loaiMauXetNghiemRepository.findAll();
        assertThat(loaiMauXetNghiemList).hasSize(databaseSizeBeforeCreate);

        // Validate the LoaiMauXetNghiem in Elasticsearch
        verify(mockLoaiMauXetNghiemSearchRepository, times(0)).save(loaiMauXetNghiem);
    }

    @Test
    @Transactional
    public void getAllLoaiMauXetNghiems() throws Exception {
        // Initialize the database
        loaiMauXetNghiemRepository.saveAndFlush(loaiMauXetNghiem);

        // Get all the loaiMauXetNghiemList
        restLoaiMauXetNghiemMockMvc.perform(get("/api/loai-mau-xet-nghiems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiMauXetNghiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoaiMau").value(hasItem(DEFAULT_MA_LOAI_MAU.toString())))
            .andExpect(jsonPath("$.[*].tenLoaiMau").value(hasItem(DEFAULT_TEN_LOAI_MAU.toString())))
            .andExpect(jsonPath("$.[*].phanLoai").value(hasItem(DEFAULT_PHAN_LOAI.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getLoaiMauXetNghiem() throws Exception {
        // Initialize the database
        loaiMauXetNghiemRepository.saveAndFlush(loaiMauXetNghiem);

        // Get the loaiMauXetNghiem
        restLoaiMauXetNghiemMockMvc.perform(get("/api/loai-mau-xet-nghiems/{id}", loaiMauXetNghiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loaiMauXetNghiem.getId().intValue()))
            .andExpect(jsonPath("$.maLoaiMau").value(DEFAULT_MA_LOAI_MAU.toString()))
            .andExpect(jsonPath("$.tenLoaiMau").value(DEFAULT_TEN_LOAI_MAU.toString()))
            .andExpect(jsonPath("$.phanLoai").value(DEFAULT_PHAN_LOAI.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLoaiMauXetNghiem() throws Exception {
        // Get the loaiMauXetNghiem
        restLoaiMauXetNghiemMockMvc.perform(get("/api/loai-mau-xet-nghiems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoaiMauXetNghiem() throws Exception {
        // Initialize the database
        loaiMauXetNghiemRepository.saveAndFlush(loaiMauXetNghiem);

        int databaseSizeBeforeUpdate = loaiMauXetNghiemRepository.findAll().size();

        // Update the loaiMauXetNghiem
        LoaiMauXetNghiem updatedLoaiMauXetNghiem = loaiMauXetNghiemRepository.findById(loaiMauXetNghiem.getId()).get();
        // Disconnect from session so that the updates on updatedLoaiMauXetNghiem are not directly saved in db
        em.detach(updatedLoaiMauXetNghiem);
        updatedLoaiMauXetNghiem
            .maLoaiMau(UPDATED_MA_LOAI_MAU)
            .tenLoaiMau(UPDATED_TEN_LOAI_MAU)
            .phanLoai(UPDATED_PHAN_LOAI)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restLoaiMauXetNghiemMockMvc.perform(put("/api/loai-mau-xet-nghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoaiMauXetNghiem)))
            .andExpect(status().isOk());

        // Validate the LoaiMauXetNghiem in the database
        List<LoaiMauXetNghiem> loaiMauXetNghiemList = loaiMauXetNghiemRepository.findAll();
        assertThat(loaiMauXetNghiemList).hasSize(databaseSizeBeforeUpdate);
        LoaiMauXetNghiem testLoaiMauXetNghiem = loaiMauXetNghiemList.get(loaiMauXetNghiemList.size() - 1);
        assertThat(testLoaiMauXetNghiem.getMaLoaiMau()).isEqualTo(UPDATED_MA_LOAI_MAU);
        assertThat(testLoaiMauXetNghiem.getTenLoaiMau()).isEqualTo(UPDATED_TEN_LOAI_MAU);
        assertThat(testLoaiMauXetNghiem.getPhanLoai()).isEqualTo(UPDATED_PHAN_LOAI);
        assertThat(testLoaiMauXetNghiem.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testLoaiMauXetNghiem.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testLoaiMauXetNghiem.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testLoaiMauXetNghiem.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testLoaiMauXetNghiem.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the LoaiMauXetNghiem in Elasticsearch
        verify(mockLoaiMauXetNghiemSearchRepository, times(1)).save(testLoaiMauXetNghiem);
    }

    @Test
    @Transactional
    public void updateNonExistingLoaiMauXetNghiem() throws Exception {
        int databaseSizeBeforeUpdate = loaiMauXetNghiemRepository.findAll().size();

        // Create the LoaiMauXetNghiem

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiMauXetNghiemMockMvc.perform(put("/api/loai-mau-xet-nghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiMauXetNghiem)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiMauXetNghiem in the database
        List<LoaiMauXetNghiem> loaiMauXetNghiemList = loaiMauXetNghiemRepository.findAll();
        assertThat(loaiMauXetNghiemList).hasSize(databaseSizeBeforeUpdate);

        // Validate the LoaiMauXetNghiem in Elasticsearch
        verify(mockLoaiMauXetNghiemSearchRepository, times(0)).save(loaiMauXetNghiem);
    }

    @Test
    @Transactional
    public void deleteLoaiMauXetNghiem() throws Exception {
        // Initialize the database
        loaiMauXetNghiemRepository.saveAndFlush(loaiMauXetNghiem);

        int databaseSizeBeforeDelete = loaiMauXetNghiemRepository.findAll().size();

        // Get the loaiMauXetNghiem
        restLoaiMauXetNghiemMockMvc.perform(delete("/api/loai-mau-xet-nghiems/{id}", loaiMauXetNghiem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LoaiMauXetNghiem> loaiMauXetNghiemList = loaiMauXetNghiemRepository.findAll();
        assertThat(loaiMauXetNghiemList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the LoaiMauXetNghiem in Elasticsearch
        verify(mockLoaiMauXetNghiemSearchRepository, times(1)).deleteById(loaiMauXetNghiem.getId());
    }

    @Test
    @Transactional
    public void searchLoaiMauXetNghiem() throws Exception {
        // Initialize the database
        loaiMauXetNghiemRepository.saveAndFlush(loaiMauXetNghiem);
        when(mockLoaiMauXetNghiemSearchRepository.search(queryStringQuery("id:" + loaiMauXetNghiem.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(loaiMauXetNghiem), PageRequest.of(0, 1), 1));
        // Search the loaiMauXetNghiem
        restLoaiMauXetNghiemMockMvc.perform(get("/api/_search/loai-mau-xet-nghiems?query=id:" + loaiMauXetNghiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiMauXetNghiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoaiMau").value(hasItem(DEFAULT_MA_LOAI_MAU)))
            .andExpect(jsonPath("$.[*].tenLoaiMau").value(hasItem(DEFAULT_TEN_LOAI_MAU)))
            .andExpect(jsonPath("$.[*].phanLoai").value(hasItem(DEFAULT_PHAN_LOAI)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiMauXetNghiem.class);
        LoaiMauXetNghiem loaiMauXetNghiem1 = new LoaiMauXetNghiem();
        loaiMauXetNghiem1.setId(1L);
        LoaiMauXetNghiem loaiMauXetNghiem2 = new LoaiMauXetNghiem();
        loaiMauXetNghiem2.setId(loaiMauXetNghiem1.getId());
        assertThat(loaiMauXetNghiem1).isEqualTo(loaiMauXetNghiem2);
        loaiMauXetNghiem2.setId(2L);
        assertThat(loaiMauXetNghiem1).isNotEqualTo(loaiMauXetNghiem2);
        loaiMauXetNghiem1.setId(null);
        assertThat(loaiMauXetNghiem1).isNotEqualTo(loaiMauXetNghiem2);
    }
}
