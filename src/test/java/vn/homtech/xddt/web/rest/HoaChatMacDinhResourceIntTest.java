package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.HoaChatMacDinh;
import vn.homtech.xddt.repository.HoaChatMacDinhRepository;
import vn.homtech.xddt.repository.search.HoaChatMacDinhSearchRepository;
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
 * Test class for the HoaChatMacDinhResource REST controller.
 *
 * @see HoaChatMacDinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class HoaChatMacDinhResourceIntTest {

    private static final String DEFAULT_LOAI_THAO_TAC = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_THAO_TAC = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DEFAULT = false;
    private static final Boolean UPDATED_IS_DEFAULT = true;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private HoaChatMacDinhRepository hoaChatMacDinhRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.HoaChatMacDinhSearchRepositoryMockConfiguration
     */
    @Autowired
    private HoaChatMacDinhSearchRepository mockHoaChatMacDinhSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHoaChatMacDinhMockMvc;

    private HoaChatMacDinh hoaChatMacDinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HoaChatMacDinhResource hoaChatMacDinhResource = new HoaChatMacDinhResource(hoaChatMacDinhRepository, mockHoaChatMacDinhSearchRepository);
        this.restHoaChatMacDinhMockMvc = MockMvcBuilders.standaloneSetup(hoaChatMacDinhResource)
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
    public static HoaChatMacDinh createEntity(EntityManager em) {
        HoaChatMacDinh hoaChatMacDinh = new HoaChatMacDinh()
            .loaiThaoTac(DEFAULT_LOAI_THAO_TAC)
            .isDefault(DEFAULT_IS_DEFAULT)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return hoaChatMacDinh;
    }

    @Before
    public void initTest() {
        hoaChatMacDinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createHoaChatMacDinh() throws Exception {
        int databaseSizeBeforeCreate = hoaChatMacDinhRepository.findAll().size();

        // Create the HoaChatMacDinh
        restHoaChatMacDinhMockMvc.perform(post("/api/hoa-chat-mac-dinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChatMacDinh)))
            .andExpect(status().isCreated());

        // Validate the HoaChatMacDinh in the database
        List<HoaChatMacDinh> hoaChatMacDinhList = hoaChatMacDinhRepository.findAll();
        assertThat(hoaChatMacDinhList).hasSize(databaseSizeBeforeCreate + 1);
        HoaChatMacDinh testHoaChatMacDinh = hoaChatMacDinhList.get(hoaChatMacDinhList.size() - 1);
        assertThat(testHoaChatMacDinh.getLoaiThaoTac()).isEqualTo(DEFAULT_LOAI_THAO_TAC);
        assertThat(testHoaChatMacDinh.isIsDefault()).isEqualTo(DEFAULT_IS_DEFAULT);
        assertThat(testHoaChatMacDinh.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testHoaChatMacDinh.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testHoaChatMacDinh.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testHoaChatMacDinh.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the HoaChatMacDinh in Elasticsearch
        verify(mockHoaChatMacDinhSearchRepository, times(1)).save(testHoaChatMacDinh);
    }

    @Test
    @Transactional
    public void createHoaChatMacDinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hoaChatMacDinhRepository.findAll().size();

        // Create the HoaChatMacDinh with an existing ID
        hoaChatMacDinh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoaChatMacDinhMockMvc.perform(post("/api/hoa-chat-mac-dinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChatMacDinh)))
            .andExpect(status().isBadRequest());

        // Validate the HoaChatMacDinh in the database
        List<HoaChatMacDinh> hoaChatMacDinhList = hoaChatMacDinhRepository.findAll();
        assertThat(hoaChatMacDinhList).hasSize(databaseSizeBeforeCreate);

        // Validate the HoaChatMacDinh in Elasticsearch
        verify(mockHoaChatMacDinhSearchRepository, times(0)).save(hoaChatMacDinh);
    }

    @Test
    @Transactional
    public void getAllHoaChatMacDinhs() throws Exception {
        // Initialize the database
        hoaChatMacDinhRepository.saveAndFlush(hoaChatMacDinh);

        // Get all the hoaChatMacDinhList
        restHoaChatMacDinhMockMvc.perform(get("/api/hoa-chat-mac-dinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoaChatMacDinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].loaiThaoTac").value(hasItem(DEFAULT_LOAI_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].isDefault").value(hasItem(DEFAULT_IS_DEFAULT.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getHoaChatMacDinh() throws Exception {
        // Initialize the database
        hoaChatMacDinhRepository.saveAndFlush(hoaChatMacDinh);

        // Get the hoaChatMacDinh
        restHoaChatMacDinhMockMvc.perform(get("/api/hoa-chat-mac-dinhs/{id}", hoaChatMacDinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hoaChatMacDinh.getId().intValue()))
            .andExpect(jsonPath("$.loaiThaoTac").value(DEFAULT_LOAI_THAO_TAC.toString()))
            .andExpect(jsonPath("$.isDefault").value(DEFAULT_IS_DEFAULT.booleanValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHoaChatMacDinh() throws Exception {
        // Get the hoaChatMacDinh
        restHoaChatMacDinhMockMvc.perform(get("/api/hoa-chat-mac-dinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHoaChatMacDinh() throws Exception {
        // Initialize the database
        hoaChatMacDinhRepository.saveAndFlush(hoaChatMacDinh);

        int databaseSizeBeforeUpdate = hoaChatMacDinhRepository.findAll().size();

        // Update the hoaChatMacDinh
        HoaChatMacDinh updatedHoaChatMacDinh = hoaChatMacDinhRepository.findById(hoaChatMacDinh.getId()).get();
        // Disconnect from session so that the updates on updatedHoaChatMacDinh are not directly saved in db
        em.detach(updatedHoaChatMacDinh);
        updatedHoaChatMacDinh
            .loaiThaoTac(UPDATED_LOAI_THAO_TAC)
            .isDefault(UPDATED_IS_DEFAULT)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restHoaChatMacDinhMockMvc.perform(put("/api/hoa-chat-mac-dinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHoaChatMacDinh)))
            .andExpect(status().isOk());

        // Validate the HoaChatMacDinh in the database
        List<HoaChatMacDinh> hoaChatMacDinhList = hoaChatMacDinhRepository.findAll();
        assertThat(hoaChatMacDinhList).hasSize(databaseSizeBeforeUpdate);
        HoaChatMacDinh testHoaChatMacDinh = hoaChatMacDinhList.get(hoaChatMacDinhList.size() - 1);
        assertThat(testHoaChatMacDinh.getLoaiThaoTac()).isEqualTo(UPDATED_LOAI_THAO_TAC);
        assertThat(testHoaChatMacDinh.isIsDefault()).isEqualTo(UPDATED_IS_DEFAULT);
        assertThat(testHoaChatMacDinh.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testHoaChatMacDinh.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testHoaChatMacDinh.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testHoaChatMacDinh.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the HoaChatMacDinh in Elasticsearch
        verify(mockHoaChatMacDinhSearchRepository, times(1)).save(testHoaChatMacDinh);
    }

    @Test
    @Transactional
    public void updateNonExistingHoaChatMacDinh() throws Exception {
        int databaseSizeBeforeUpdate = hoaChatMacDinhRepository.findAll().size();

        // Create the HoaChatMacDinh

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoaChatMacDinhMockMvc.perform(put("/api/hoa-chat-mac-dinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChatMacDinh)))
            .andExpect(status().isBadRequest());

        // Validate the HoaChatMacDinh in the database
        List<HoaChatMacDinh> hoaChatMacDinhList = hoaChatMacDinhRepository.findAll();
        assertThat(hoaChatMacDinhList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HoaChatMacDinh in Elasticsearch
        verify(mockHoaChatMacDinhSearchRepository, times(0)).save(hoaChatMacDinh);
    }

    @Test
    @Transactional
    public void deleteHoaChatMacDinh() throws Exception {
        // Initialize the database
        hoaChatMacDinhRepository.saveAndFlush(hoaChatMacDinh);

        int databaseSizeBeforeDelete = hoaChatMacDinhRepository.findAll().size();

        // Get the hoaChatMacDinh
        restHoaChatMacDinhMockMvc.perform(delete("/api/hoa-chat-mac-dinhs/{id}", hoaChatMacDinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HoaChatMacDinh> hoaChatMacDinhList = hoaChatMacDinhRepository.findAll();
        assertThat(hoaChatMacDinhList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HoaChatMacDinh in Elasticsearch
        verify(mockHoaChatMacDinhSearchRepository, times(1)).deleteById(hoaChatMacDinh.getId());
    }

    @Test
    @Transactional
    public void searchHoaChatMacDinh() throws Exception {
        // Initialize the database
        hoaChatMacDinhRepository.saveAndFlush(hoaChatMacDinh);
        when(mockHoaChatMacDinhSearchRepository.search(queryStringQuery("id:" + hoaChatMacDinh.getId())))
            .thenReturn(Collections.singletonList(hoaChatMacDinh));
        // Search the hoaChatMacDinh
        restHoaChatMacDinhMockMvc.perform(get("/api/_search/hoa-chat-mac-dinhs?query=id:" + hoaChatMacDinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoaChatMacDinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].loaiThaoTac").value(hasItem(DEFAULT_LOAI_THAO_TAC)))
            .andExpect(jsonPath("$.[*].isDefault").value(hasItem(DEFAULT_IS_DEFAULT.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoaChatMacDinh.class);
        HoaChatMacDinh hoaChatMacDinh1 = new HoaChatMacDinh();
        hoaChatMacDinh1.setId(1L);
        HoaChatMacDinh hoaChatMacDinh2 = new HoaChatMacDinh();
        hoaChatMacDinh2.setId(hoaChatMacDinh1.getId());
        assertThat(hoaChatMacDinh1).isEqualTo(hoaChatMacDinh2);
        hoaChatMacDinh2.setId(2L);
        assertThat(hoaChatMacDinh1).isNotEqualTo(hoaChatMacDinh2);
        hoaChatMacDinh1.setId(null);
        assertThat(hoaChatMacDinh1).isNotEqualTo(hoaChatMacDinh2);
    }
}
