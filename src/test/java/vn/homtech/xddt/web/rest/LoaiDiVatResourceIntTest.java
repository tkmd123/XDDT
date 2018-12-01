package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.LoaiDiVat;
import vn.homtech.xddt.repository.LoaiDiVatRepository;
import vn.homtech.xddt.repository.search.LoaiDiVatSearchRepository;
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
 * Test class for the LoaiDiVatResource REST controller.
 *
 * @see LoaiDiVatResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class LoaiDiVatResourceIntTest {

    private static final String DEFAULT_MA_DI_VAT = "AAAAAAAAAA";
    private static final String UPDATED_MA_DI_VAT = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_DI_VAT = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_DI_VAT = "BBBBBBBBBB";

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
    private LoaiDiVatRepository loaiDiVatRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.LoaiDiVatSearchRepositoryMockConfiguration
     */
    @Autowired
    private LoaiDiVatSearchRepository mockLoaiDiVatSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoaiDiVatMockMvc;

    private LoaiDiVat loaiDiVat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoaiDiVatResource loaiDiVatResource = new LoaiDiVatResource(loaiDiVatRepository, mockLoaiDiVatSearchRepository);
        this.restLoaiDiVatMockMvc = MockMvcBuilders.standaloneSetup(loaiDiVatResource)
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
    public static LoaiDiVat createEntity(EntityManager em) {
        LoaiDiVat loaiDiVat = new LoaiDiVat()
            .maDiVat(DEFAULT_MA_DI_VAT)
            .loaiDiVat(DEFAULT_LOAI_DI_VAT)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return loaiDiVat;
    }

    @Before
    public void initTest() {
        loaiDiVat = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoaiDiVat() throws Exception {
        int databaseSizeBeforeCreate = loaiDiVatRepository.findAll().size();

        // Create the LoaiDiVat
        restLoaiDiVatMockMvc.perform(post("/api/loai-di-vats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDiVat)))
            .andExpect(status().isCreated());

        // Validate the LoaiDiVat in the database
        List<LoaiDiVat> loaiDiVatList = loaiDiVatRepository.findAll();
        assertThat(loaiDiVatList).hasSize(databaseSizeBeforeCreate + 1);
        LoaiDiVat testLoaiDiVat = loaiDiVatList.get(loaiDiVatList.size() - 1);
        assertThat(testLoaiDiVat.getMaDiVat()).isEqualTo(DEFAULT_MA_DI_VAT);
        assertThat(testLoaiDiVat.getLoaiDiVat()).isEqualTo(DEFAULT_LOAI_DI_VAT);
        assertThat(testLoaiDiVat.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testLoaiDiVat.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testLoaiDiVat.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testLoaiDiVat.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testLoaiDiVat.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the LoaiDiVat in Elasticsearch
        verify(mockLoaiDiVatSearchRepository, times(1)).save(testLoaiDiVat);
    }

    @Test
    @Transactional
    public void createLoaiDiVatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loaiDiVatRepository.findAll().size();

        // Create the LoaiDiVat with an existing ID
        loaiDiVat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiDiVatMockMvc.perform(post("/api/loai-di-vats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDiVat)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiDiVat in the database
        List<LoaiDiVat> loaiDiVatList = loaiDiVatRepository.findAll();
        assertThat(loaiDiVatList).hasSize(databaseSizeBeforeCreate);

        // Validate the LoaiDiVat in Elasticsearch
        verify(mockLoaiDiVatSearchRepository, times(0)).save(loaiDiVat);
    }

    @Test
    @Transactional
    public void getAllLoaiDiVats() throws Exception {
        // Initialize the database
        loaiDiVatRepository.saveAndFlush(loaiDiVat);

        // Get all the loaiDiVatList
        restLoaiDiVatMockMvc.perform(get("/api/loai-di-vats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiDiVat.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDiVat").value(hasItem(DEFAULT_MA_DI_VAT.toString())))
            .andExpect(jsonPath("$.[*].loaiDiVat").value(hasItem(DEFAULT_LOAI_DI_VAT.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getLoaiDiVat() throws Exception {
        // Initialize the database
        loaiDiVatRepository.saveAndFlush(loaiDiVat);

        // Get the loaiDiVat
        restLoaiDiVatMockMvc.perform(get("/api/loai-di-vats/{id}", loaiDiVat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loaiDiVat.getId().intValue()))
            .andExpect(jsonPath("$.maDiVat").value(DEFAULT_MA_DI_VAT.toString()))
            .andExpect(jsonPath("$.loaiDiVat").value(DEFAULT_LOAI_DI_VAT.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLoaiDiVat() throws Exception {
        // Get the loaiDiVat
        restLoaiDiVatMockMvc.perform(get("/api/loai-di-vats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoaiDiVat() throws Exception {
        // Initialize the database
        loaiDiVatRepository.saveAndFlush(loaiDiVat);

        int databaseSizeBeforeUpdate = loaiDiVatRepository.findAll().size();

        // Update the loaiDiVat
        LoaiDiVat updatedLoaiDiVat = loaiDiVatRepository.findById(loaiDiVat.getId()).get();
        // Disconnect from session so that the updates on updatedLoaiDiVat are not directly saved in db
        em.detach(updatedLoaiDiVat);
        updatedLoaiDiVat
            .maDiVat(UPDATED_MA_DI_VAT)
            .loaiDiVat(UPDATED_LOAI_DI_VAT)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restLoaiDiVatMockMvc.perform(put("/api/loai-di-vats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoaiDiVat)))
            .andExpect(status().isOk());

        // Validate the LoaiDiVat in the database
        List<LoaiDiVat> loaiDiVatList = loaiDiVatRepository.findAll();
        assertThat(loaiDiVatList).hasSize(databaseSizeBeforeUpdate);
        LoaiDiVat testLoaiDiVat = loaiDiVatList.get(loaiDiVatList.size() - 1);
        assertThat(testLoaiDiVat.getMaDiVat()).isEqualTo(UPDATED_MA_DI_VAT);
        assertThat(testLoaiDiVat.getLoaiDiVat()).isEqualTo(UPDATED_LOAI_DI_VAT);
        assertThat(testLoaiDiVat.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testLoaiDiVat.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testLoaiDiVat.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testLoaiDiVat.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testLoaiDiVat.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the LoaiDiVat in Elasticsearch
        verify(mockLoaiDiVatSearchRepository, times(1)).save(testLoaiDiVat);
    }

    @Test
    @Transactional
    public void updateNonExistingLoaiDiVat() throws Exception {
        int databaseSizeBeforeUpdate = loaiDiVatRepository.findAll().size();

        // Create the LoaiDiVat

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiDiVatMockMvc.perform(put("/api/loai-di-vats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDiVat)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiDiVat in the database
        List<LoaiDiVat> loaiDiVatList = loaiDiVatRepository.findAll();
        assertThat(loaiDiVatList).hasSize(databaseSizeBeforeUpdate);

        // Validate the LoaiDiVat in Elasticsearch
        verify(mockLoaiDiVatSearchRepository, times(0)).save(loaiDiVat);
    }

    @Test
    @Transactional
    public void deleteLoaiDiVat() throws Exception {
        // Initialize the database
        loaiDiVatRepository.saveAndFlush(loaiDiVat);

        int databaseSizeBeforeDelete = loaiDiVatRepository.findAll().size();

        // Get the loaiDiVat
        restLoaiDiVatMockMvc.perform(delete("/api/loai-di-vats/{id}", loaiDiVat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LoaiDiVat> loaiDiVatList = loaiDiVatRepository.findAll();
        assertThat(loaiDiVatList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the LoaiDiVat in Elasticsearch
        verify(mockLoaiDiVatSearchRepository, times(1)).deleteById(loaiDiVat.getId());
    }

    @Test
    @Transactional
    public void searchLoaiDiVat() throws Exception {
        // Initialize the database
        loaiDiVatRepository.saveAndFlush(loaiDiVat);
        when(mockLoaiDiVatSearchRepository.search(queryStringQuery("id:" + loaiDiVat.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(loaiDiVat), PageRequest.of(0, 1), 1));
        // Search the loaiDiVat
        restLoaiDiVatMockMvc.perform(get("/api/_search/loai-di-vats?query=id:" + loaiDiVat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiDiVat.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDiVat").value(hasItem(DEFAULT_MA_DI_VAT)))
            .andExpect(jsonPath("$.[*].loaiDiVat").value(hasItem(DEFAULT_LOAI_DI_VAT)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiDiVat.class);
        LoaiDiVat loaiDiVat1 = new LoaiDiVat();
        loaiDiVat1.setId(1L);
        LoaiDiVat loaiDiVat2 = new LoaiDiVat();
        loaiDiVat2.setId(loaiDiVat1.getId());
        assertThat(loaiDiVat1).isEqualTo(loaiDiVat2);
        loaiDiVat2.setId(2L);
        assertThat(loaiDiVat1).isNotEqualTo(loaiDiVat2);
        loaiDiVat1.setId(null);
        assertThat(loaiDiVat1).isNotEqualTo(loaiDiVat2);
    }
}
