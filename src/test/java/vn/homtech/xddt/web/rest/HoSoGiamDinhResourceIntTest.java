package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.HoSoGiamDinh;
import vn.homtech.xddt.repository.HoSoGiamDinhRepository;
import vn.homtech.xddt.repository.search.HoSoGiamDinhSearchRepository;
import vn.homtech.xddt.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import java.util.ArrayList;
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
 * Test class for the HoSoGiamDinhResource REST controller.
 *
 * @see HoSoGiamDinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class HoSoGiamDinhResourceIntTest {

    private static final String DEFAULT_MA_HSGD = "AAAAAAAAAA";
    private static final String UPDATED_MA_HSGD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private HoSoGiamDinhRepository hoSoGiamDinhRepository;

    @Mock
    private HoSoGiamDinhRepository hoSoGiamDinhRepositoryMock;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.HoSoGiamDinhSearchRepositoryMockConfiguration
     */
    @Autowired
    private HoSoGiamDinhSearchRepository mockHoSoGiamDinhSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHoSoGiamDinhMockMvc;

    private HoSoGiamDinh hoSoGiamDinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HoSoGiamDinhResource hoSoGiamDinhResource = new HoSoGiamDinhResource(hoSoGiamDinhRepository, mockHoSoGiamDinhSearchRepository);
        this.restHoSoGiamDinhMockMvc = MockMvcBuilders.standaloneSetup(hoSoGiamDinhResource)
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
    public static HoSoGiamDinh createEntity(EntityManager em) {
        HoSoGiamDinh hoSoGiamDinh = new HoSoGiamDinh()
            .maHSGD(DEFAULT_MA_HSGD)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return hoSoGiamDinh;
    }

    @Before
    public void initTest() {
        hoSoGiamDinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createHoSoGiamDinh() throws Exception {
        int databaseSizeBeforeCreate = hoSoGiamDinhRepository.findAll().size();

        // Create the HoSoGiamDinh
        restHoSoGiamDinhMockMvc.perform(post("/api/ho-so-giam-dinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoGiamDinh)))
            .andExpect(status().isCreated());

        // Validate the HoSoGiamDinh in the database
        List<HoSoGiamDinh> hoSoGiamDinhList = hoSoGiamDinhRepository.findAll();
        assertThat(hoSoGiamDinhList).hasSize(databaseSizeBeforeCreate + 1);
        HoSoGiamDinh testHoSoGiamDinh = hoSoGiamDinhList.get(hoSoGiamDinhList.size() - 1);
        assertThat(testHoSoGiamDinh.getMaHSGD()).isEqualTo(DEFAULT_MA_HSGD);
        assertThat(testHoSoGiamDinh.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testHoSoGiamDinh.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testHoSoGiamDinh.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testHoSoGiamDinh.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the HoSoGiamDinh in Elasticsearch
        verify(mockHoSoGiamDinhSearchRepository, times(1)).save(testHoSoGiamDinh);
    }

    @Test
    @Transactional
    public void createHoSoGiamDinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hoSoGiamDinhRepository.findAll().size();

        // Create the HoSoGiamDinh with an existing ID
        hoSoGiamDinh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoSoGiamDinhMockMvc.perform(post("/api/ho-so-giam-dinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoGiamDinh)))
            .andExpect(status().isBadRequest());

        // Validate the HoSoGiamDinh in the database
        List<HoSoGiamDinh> hoSoGiamDinhList = hoSoGiamDinhRepository.findAll();
        assertThat(hoSoGiamDinhList).hasSize(databaseSizeBeforeCreate);

        // Validate the HoSoGiamDinh in Elasticsearch
        verify(mockHoSoGiamDinhSearchRepository, times(0)).save(hoSoGiamDinh);
    }

    @Test
    @Transactional
    public void getAllHoSoGiamDinhs() throws Exception {
        // Initialize the database
        hoSoGiamDinhRepository.saveAndFlush(hoSoGiamDinh);

        // Get all the hoSoGiamDinhList
        restHoSoGiamDinhMockMvc.perform(get("/api/ho-so-giam-dinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoSoGiamDinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHSGD").value(hasItem(DEFAULT_MA_HSGD.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllHoSoGiamDinhsWithEagerRelationshipsIsEnabled() throws Exception {
        HoSoGiamDinhResource hoSoGiamDinhResource = new HoSoGiamDinhResource(hoSoGiamDinhRepositoryMock, mockHoSoGiamDinhSearchRepository);
        when(hoSoGiamDinhRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restHoSoGiamDinhMockMvc = MockMvcBuilders.standaloneSetup(hoSoGiamDinhResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restHoSoGiamDinhMockMvc.perform(get("/api/ho-so-giam-dinhs?eagerload=true"))
        .andExpect(status().isOk());

        verify(hoSoGiamDinhRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllHoSoGiamDinhsWithEagerRelationshipsIsNotEnabled() throws Exception {
        HoSoGiamDinhResource hoSoGiamDinhResource = new HoSoGiamDinhResource(hoSoGiamDinhRepositoryMock, mockHoSoGiamDinhSearchRepository);
            when(hoSoGiamDinhRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restHoSoGiamDinhMockMvc = MockMvcBuilders.standaloneSetup(hoSoGiamDinhResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restHoSoGiamDinhMockMvc.perform(get("/api/ho-so-giam-dinhs?eagerload=true"))
        .andExpect(status().isOk());

            verify(hoSoGiamDinhRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getHoSoGiamDinh() throws Exception {
        // Initialize the database
        hoSoGiamDinhRepository.saveAndFlush(hoSoGiamDinh);

        // Get the hoSoGiamDinh
        restHoSoGiamDinhMockMvc.perform(get("/api/ho-so-giam-dinhs/{id}", hoSoGiamDinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hoSoGiamDinh.getId().intValue()))
            .andExpect(jsonPath("$.maHSGD").value(DEFAULT_MA_HSGD.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHoSoGiamDinh() throws Exception {
        // Get the hoSoGiamDinh
        restHoSoGiamDinhMockMvc.perform(get("/api/ho-so-giam-dinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHoSoGiamDinh() throws Exception {
        // Initialize the database
        hoSoGiamDinhRepository.saveAndFlush(hoSoGiamDinh);

        int databaseSizeBeforeUpdate = hoSoGiamDinhRepository.findAll().size();

        // Update the hoSoGiamDinh
        HoSoGiamDinh updatedHoSoGiamDinh = hoSoGiamDinhRepository.findById(hoSoGiamDinh.getId()).get();
        // Disconnect from session so that the updates on updatedHoSoGiamDinh are not directly saved in db
        em.detach(updatedHoSoGiamDinh);
        updatedHoSoGiamDinh
            .maHSGD(UPDATED_MA_HSGD)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restHoSoGiamDinhMockMvc.perform(put("/api/ho-so-giam-dinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHoSoGiamDinh)))
            .andExpect(status().isOk());

        // Validate the HoSoGiamDinh in the database
        List<HoSoGiamDinh> hoSoGiamDinhList = hoSoGiamDinhRepository.findAll();
        assertThat(hoSoGiamDinhList).hasSize(databaseSizeBeforeUpdate);
        HoSoGiamDinh testHoSoGiamDinh = hoSoGiamDinhList.get(hoSoGiamDinhList.size() - 1);
        assertThat(testHoSoGiamDinh.getMaHSGD()).isEqualTo(UPDATED_MA_HSGD);
        assertThat(testHoSoGiamDinh.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testHoSoGiamDinh.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testHoSoGiamDinh.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testHoSoGiamDinh.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the HoSoGiamDinh in Elasticsearch
        verify(mockHoSoGiamDinhSearchRepository, times(1)).save(testHoSoGiamDinh);
    }

    @Test
    @Transactional
    public void updateNonExistingHoSoGiamDinh() throws Exception {
        int databaseSizeBeforeUpdate = hoSoGiamDinhRepository.findAll().size();

        // Create the HoSoGiamDinh

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoSoGiamDinhMockMvc.perform(put("/api/ho-so-giam-dinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoSoGiamDinh)))
            .andExpect(status().isBadRequest());

        // Validate the HoSoGiamDinh in the database
        List<HoSoGiamDinh> hoSoGiamDinhList = hoSoGiamDinhRepository.findAll();
        assertThat(hoSoGiamDinhList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HoSoGiamDinh in Elasticsearch
        verify(mockHoSoGiamDinhSearchRepository, times(0)).save(hoSoGiamDinh);
    }

    @Test
    @Transactional
    public void deleteHoSoGiamDinh() throws Exception {
        // Initialize the database
        hoSoGiamDinhRepository.saveAndFlush(hoSoGiamDinh);

        int databaseSizeBeforeDelete = hoSoGiamDinhRepository.findAll().size();

        // Get the hoSoGiamDinh
        restHoSoGiamDinhMockMvc.perform(delete("/api/ho-so-giam-dinhs/{id}", hoSoGiamDinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HoSoGiamDinh> hoSoGiamDinhList = hoSoGiamDinhRepository.findAll();
        assertThat(hoSoGiamDinhList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HoSoGiamDinh in Elasticsearch
        verify(mockHoSoGiamDinhSearchRepository, times(1)).deleteById(hoSoGiamDinh.getId());
    }

    @Test
    @Transactional
    public void searchHoSoGiamDinh() throws Exception {
        // Initialize the database
        hoSoGiamDinhRepository.saveAndFlush(hoSoGiamDinh);
        when(mockHoSoGiamDinhSearchRepository.search(queryStringQuery("id:" + hoSoGiamDinh.getId())))
            .thenReturn(Collections.singletonList(hoSoGiamDinh));
        // Search the hoSoGiamDinh
        restHoSoGiamDinhMockMvc.perform(get("/api/_search/ho-so-giam-dinhs?query=id:" + hoSoGiamDinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoSoGiamDinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHSGD").value(hasItem(DEFAULT_MA_HSGD)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoSoGiamDinh.class);
        HoSoGiamDinh hoSoGiamDinh1 = new HoSoGiamDinh();
        hoSoGiamDinh1.setId(1L);
        HoSoGiamDinh hoSoGiamDinh2 = new HoSoGiamDinh();
        hoSoGiamDinh2.setId(hoSoGiamDinh1.getId());
        assertThat(hoSoGiamDinh1).isEqualTo(hoSoGiamDinh2);
        hoSoGiamDinh2.setId(2L);
        assertThat(hoSoGiamDinh1).isNotEqualTo(hoSoGiamDinh2);
        hoSoGiamDinh1.setId(null);
        assertThat(hoSoGiamDinh1).isNotEqualTo(hoSoGiamDinh2);
    }
}
