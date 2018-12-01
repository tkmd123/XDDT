package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.HoaChatTachChiet;
import vn.homtech.xddt.repository.HoaChatTachChietRepository;
import vn.homtech.xddt.repository.search.HoaChatTachChietSearchRepository;
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
 * Test class for the HoaChatTachChietResource REST controller.
 *
 * @see HoaChatTachChietResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class HoaChatTachChietResourceIntTest {

    private static final Float DEFAULT_SO_LUONG = 1F;
    private static final Float UPDATED_SO_LUONG = 2F;

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
    private HoaChatTachChietRepository hoaChatTachChietRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.HoaChatTachChietSearchRepositoryMockConfiguration
     */
    @Autowired
    private HoaChatTachChietSearchRepository mockHoaChatTachChietSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHoaChatTachChietMockMvc;

    private HoaChatTachChiet hoaChatTachChiet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HoaChatTachChietResource hoaChatTachChietResource = new HoaChatTachChietResource(hoaChatTachChietRepository, mockHoaChatTachChietSearchRepository);
        this.restHoaChatTachChietMockMvc = MockMvcBuilders.standaloneSetup(hoaChatTachChietResource)
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
    public static HoaChatTachChiet createEntity(EntityManager em) {
        HoaChatTachChiet hoaChatTachChiet = new HoaChatTachChiet()
            .soLuong(DEFAULT_SO_LUONG)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return hoaChatTachChiet;
    }

    @Before
    public void initTest() {
        hoaChatTachChiet = createEntity(em);
    }

    @Test
    @Transactional
    public void createHoaChatTachChiet() throws Exception {
        int databaseSizeBeforeCreate = hoaChatTachChietRepository.findAll().size();

        // Create the HoaChatTachChiet
        restHoaChatTachChietMockMvc.perform(post("/api/hoa-chat-tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChatTachChiet)))
            .andExpect(status().isCreated());

        // Validate the HoaChatTachChiet in the database
        List<HoaChatTachChiet> hoaChatTachChietList = hoaChatTachChietRepository.findAll();
        assertThat(hoaChatTachChietList).hasSize(databaseSizeBeforeCreate + 1);
        HoaChatTachChiet testHoaChatTachChiet = hoaChatTachChietList.get(hoaChatTachChietList.size() - 1);
        assertThat(testHoaChatTachChiet.getSoLuong()).isEqualTo(DEFAULT_SO_LUONG);
        assertThat(testHoaChatTachChiet.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testHoaChatTachChiet.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testHoaChatTachChiet.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testHoaChatTachChiet.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testHoaChatTachChiet.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the HoaChatTachChiet in Elasticsearch
        verify(mockHoaChatTachChietSearchRepository, times(1)).save(testHoaChatTachChiet);
    }

    @Test
    @Transactional
    public void createHoaChatTachChietWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hoaChatTachChietRepository.findAll().size();

        // Create the HoaChatTachChiet with an existing ID
        hoaChatTachChiet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoaChatTachChietMockMvc.perform(post("/api/hoa-chat-tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChatTachChiet)))
            .andExpect(status().isBadRequest());

        // Validate the HoaChatTachChiet in the database
        List<HoaChatTachChiet> hoaChatTachChietList = hoaChatTachChietRepository.findAll();
        assertThat(hoaChatTachChietList).hasSize(databaseSizeBeforeCreate);

        // Validate the HoaChatTachChiet in Elasticsearch
        verify(mockHoaChatTachChietSearchRepository, times(0)).save(hoaChatTachChiet);
    }

    @Test
    @Transactional
    public void getAllHoaChatTachChiets() throws Exception {
        // Initialize the database
        hoaChatTachChietRepository.saveAndFlush(hoaChatTachChiet);

        // Get all the hoaChatTachChietList
        restHoaChatTachChietMockMvc.perform(get("/api/hoa-chat-tach-chiets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoaChatTachChiet.getId().intValue())))
            .andExpect(jsonPath("$.[*].soLuong").value(hasItem(DEFAULT_SO_LUONG.doubleValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getHoaChatTachChiet() throws Exception {
        // Initialize the database
        hoaChatTachChietRepository.saveAndFlush(hoaChatTachChiet);

        // Get the hoaChatTachChiet
        restHoaChatTachChietMockMvc.perform(get("/api/hoa-chat-tach-chiets/{id}", hoaChatTachChiet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hoaChatTachChiet.getId().intValue()))
            .andExpect(jsonPath("$.soLuong").value(DEFAULT_SO_LUONG.doubleValue()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHoaChatTachChiet() throws Exception {
        // Get the hoaChatTachChiet
        restHoaChatTachChietMockMvc.perform(get("/api/hoa-chat-tach-chiets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHoaChatTachChiet() throws Exception {
        // Initialize the database
        hoaChatTachChietRepository.saveAndFlush(hoaChatTachChiet);

        int databaseSizeBeforeUpdate = hoaChatTachChietRepository.findAll().size();

        // Update the hoaChatTachChiet
        HoaChatTachChiet updatedHoaChatTachChiet = hoaChatTachChietRepository.findById(hoaChatTachChiet.getId()).get();
        // Disconnect from session so that the updates on updatedHoaChatTachChiet are not directly saved in db
        em.detach(updatedHoaChatTachChiet);
        updatedHoaChatTachChiet
            .soLuong(UPDATED_SO_LUONG)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restHoaChatTachChietMockMvc.perform(put("/api/hoa-chat-tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHoaChatTachChiet)))
            .andExpect(status().isOk());

        // Validate the HoaChatTachChiet in the database
        List<HoaChatTachChiet> hoaChatTachChietList = hoaChatTachChietRepository.findAll();
        assertThat(hoaChatTachChietList).hasSize(databaseSizeBeforeUpdate);
        HoaChatTachChiet testHoaChatTachChiet = hoaChatTachChietList.get(hoaChatTachChietList.size() - 1);
        assertThat(testHoaChatTachChiet.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
        assertThat(testHoaChatTachChiet.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testHoaChatTachChiet.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testHoaChatTachChiet.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testHoaChatTachChiet.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testHoaChatTachChiet.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the HoaChatTachChiet in Elasticsearch
        verify(mockHoaChatTachChietSearchRepository, times(1)).save(testHoaChatTachChiet);
    }

    @Test
    @Transactional
    public void updateNonExistingHoaChatTachChiet() throws Exception {
        int databaseSizeBeforeUpdate = hoaChatTachChietRepository.findAll().size();

        // Create the HoaChatTachChiet

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoaChatTachChietMockMvc.perform(put("/api/hoa-chat-tach-chiets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChatTachChiet)))
            .andExpect(status().isBadRequest());

        // Validate the HoaChatTachChiet in the database
        List<HoaChatTachChiet> hoaChatTachChietList = hoaChatTachChietRepository.findAll();
        assertThat(hoaChatTachChietList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HoaChatTachChiet in Elasticsearch
        verify(mockHoaChatTachChietSearchRepository, times(0)).save(hoaChatTachChiet);
    }

    @Test
    @Transactional
    public void deleteHoaChatTachChiet() throws Exception {
        // Initialize the database
        hoaChatTachChietRepository.saveAndFlush(hoaChatTachChiet);

        int databaseSizeBeforeDelete = hoaChatTachChietRepository.findAll().size();

        // Get the hoaChatTachChiet
        restHoaChatTachChietMockMvc.perform(delete("/api/hoa-chat-tach-chiets/{id}", hoaChatTachChiet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HoaChatTachChiet> hoaChatTachChietList = hoaChatTachChietRepository.findAll();
        assertThat(hoaChatTachChietList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HoaChatTachChiet in Elasticsearch
        verify(mockHoaChatTachChietSearchRepository, times(1)).deleteById(hoaChatTachChiet.getId());
    }

    @Test
    @Transactional
    public void searchHoaChatTachChiet() throws Exception {
        // Initialize the database
        hoaChatTachChietRepository.saveAndFlush(hoaChatTachChiet);
        when(mockHoaChatTachChietSearchRepository.search(queryStringQuery("id:" + hoaChatTachChiet.getId())))
            .thenReturn(Collections.singletonList(hoaChatTachChiet));
        // Search the hoaChatTachChiet
        restHoaChatTachChietMockMvc.perform(get("/api/_search/hoa-chat-tach-chiets?query=id:" + hoaChatTachChiet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoaChatTachChiet.getId().intValue())))
            .andExpect(jsonPath("$.[*].soLuong").value(hasItem(DEFAULT_SO_LUONG.doubleValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoaChatTachChiet.class);
        HoaChatTachChiet hoaChatTachChiet1 = new HoaChatTachChiet();
        hoaChatTachChiet1.setId(1L);
        HoaChatTachChiet hoaChatTachChiet2 = new HoaChatTachChiet();
        hoaChatTachChiet2.setId(hoaChatTachChiet1.getId());
        assertThat(hoaChatTachChiet1).isEqualTo(hoaChatTachChiet2);
        hoaChatTachChiet2.setId(2L);
        assertThat(hoaChatTachChiet1).isNotEqualTo(hoaChatTachChiet2);
        hoaChatTachChiet1.setId(null);
        assertThat(hoaChatTachChiet1).isNotEqualTo(hoaChatTachChiet2);
    }
}
