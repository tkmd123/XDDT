package vn.homtech.xddt.web.rest;

import vn.homtech.xddt.XddtApp;

import vn.homtech.xddt.domain.HoaChat;
import vn.homtech.xddt.repository.HoaChatRepository;
import vn.homtech.xddt.repository.search.HoaChatSearchRepository;
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
 * Test class for the HoaChatResource REST controller.
 *
 * @see HoaChatResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XddtApp.class)
public class HoaChatResourceIntTest {

    private static final String DEFAULT_MA_HOA_CHAT = "AAAAAAAAAA";
    private static final String UPDATED_MA_HOA_CHAT = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_HOA_CHAT = "AAAAAAAAAA";
    private static final String UPDATED_TEN_HOA_CHAT = "BBBBBBBBBB";

    private static final String DEFAULT_HANG_HOA_CHAT = "AAAAAAAAAA";
    private static final String UPDATED_HANG_HOA_CHAT = "BBBBBBBBBB";

    private static final String DEFAULT_DON_VI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_DON_VI_TINH = "BBBBBBBBBB";

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
    private HoaChatRepository hoaChatRepository;

    /**
     * This repository is mocked in the vn.homtech.xddt.repository.search test package.
     *
     * @see vn.homtech.xddt.repository.search.HoaChatSearchRepositoryMockConfiguration
     */
    @Autowired
    private HoaChatSearchRepository mockHoaChatSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHoaChatMockMvc;

    private HoaChat hoaChat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HoaChatResource hoaChatResource = new HoaChatResource(hoaChatRepository, mockHoaChatSearchRepository);
        this.restHoaChatMockMvc = MockMvcBuilders.standaloneSetup(hoaChatResource)
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
    public static HoaChat createEntity(EntityManager em) {
        HoaChat hoaChat = new HoaChat()
            .maHoaChat(DEFAULT_MA_HOA_CHAT)
            .tenHoaChat(DEFAULT_TEN_HOA_CHAT)
            .hangHoaChat(DEFAULT_HANG_HOA_CHAT)
            .donViTinh(DEFAULT_DON_VI_TINH)
            .moTa(DEFAULT_MO_TA)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return hoaChat;
    }

    @Before
    public void initTest() {
        hoaChat = createEntity(em);
    }

    @Test
    @Transactional
    public void createHoaChat() throws Exception {
        int databaseSizeBeforeCreate = hoaChatRepository.findAll().size();

        // Create the HoaChat
        restHoaChatMockMvc.perform(post("/api/hoa-chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChat)))
            .andExpect(status().isCreated());

        // Validate the HoaChat in the database
        List<HoaChat> hoaChatList = hoaChatRepository.findAll();
        assertThat(hoaChatList).hasSize(databaseSizeBeforeCreate + 1);
        HoaChat testHoaChat = hoaChatList.get(hoaChatList.size() - 1);
        assertThat(testHoaChat.getMaHoaChat()).isEqualTo(DEFAULT_MA_HOA_CHAT);
        assertThat(testHoaChat.getTenHoaChat()).isEqualTo(DEFAULT_TEN_HOA_CHAT);
        assertThat(testHoaChat.getHangHoaChat()).isEqualTo(DEFAULT_HANG_HOA_CHAT);
        assertThat(testHoaChat.getDonViTinh()).isEqualTo(DEFAULT_DON_VI_TINH);
        assertThat(testHoaChat.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testHoaChat.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testHoaChat.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testHoaChat.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testHoaChat.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the HoaChat in Elasticsearch
        verify(mockHoaChatSearchRepository, times(1)).save(testHoaChat);
    }

    @Test
    @Transactional
    public void createHoaChatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hoaChatRepository.findAll().size();

        // Create the HoaChat with an existing ID
        hoaChat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoaChatMockMvc.perform(post("/api/hoa-chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChat)))
            .andExpect(status().isBadRequest());

        // Validate the HoaChat in the database
        List<HoaChat> hoaChatList = hoaChatRepository.findAll();
        assertThat(hoaChatList).hasSize(databaseSizeBeforeCreate);

        // Validate the HoaChat in Elasticsearch
        verify(mockHoaChatSearchRepository, times(0)).save(hoaChat);
    }

    @Test
    @Transactional
    public void getAllHoaChats() throws Exception {
        // Initialize the database
        hoaChatRepository.saveAndFlush(hoaChat);

        // Get all the hoaChatList
        restHoaChatMockMvc.perform(get("/api/hoa-chats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoaChat.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHoaChat").value(hasItem(DEFAULT_MA_HOA_CHAT.toString())))
            .andExpect(jsonPath("$.[*].tenHoaChat").value(hasItem(DEFAULT_TEN_HOA_CHAT.toString())))
            .andExpect(jsonPath("$.[*].hangHoaChat").value(hasItem(DEFAULT_HANG_HOA_CHAT.toString())))
            .andExpect(jsonPath("$.[*].donViTinh").value(hasItem(DEFAULT_DON_VI_TINH.toString())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getHoaChat() throws Exception {
        // Initialize the database
        hoaChatRepository.saveAndFlush(hoaChat);

        // Get the hoaChat
        restHoaChatMockMvc.perform(get("/api/hoa-chats/{id}", hoaChat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hoaChat.getId().intValue()))
            .andExpect(jsonPath("$.maHoaChat").value(DEFAULT_MA_HOA_CHAT.toString()))
            .andExpect(jsonPath("$.tenHoaChat").value(DEFAULT_TEN_HOA_CHAT.toString()))
            .andExpect(jsonPath("$.hangHoaChat").value(DEFAULT_HANG_HOA_CHAT.toString()))
            .andExpect(jsonPath("$.donViTinh").value(DEFAULT_DON_VI_TINH.toString()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHoaChat() throws Exception {
        // Get the hoaChat
        restHoaChatMockMvc.perform(get("/api/hoa-chats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHoaChat() throws Exception {
        // Initialize the database
        hoaChatRepository.saveAndFlush(hoaChat);

        int databaseSizeBeforeUpdate = hoaChatRepository.findAll().size();

        // Update the hoaChat
        HoaChat updatedHoaChat = hoaChatRepository.findById(hoaChat.getId()).get();
        // Disconnect from session so that the updates on updatedHoaChat are not directly saved in db
        em.detach(updatedHoaChat);
        updatedHoaChat
            .maHoaChat(UPDATED_MA_HOA_CHAT)
            .tenHoaChat(UPDATED_TEN_HOA_CHAT)
            .hangHoaChat(UPDATED_HANG_HOA_CHAT)
            .donViTinh(UPDATED_DON_VI_TINH)
            .moTa(UPDATED_MO_TA)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restHoaChatMockMvc.perform(put("/api/hoa-chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHoaChat)))
            .andExpect(status().isOk());

        // Validate the HoaChat in the database
        List<HoaChat> hoaChatList = hoaChatRepository.findAll();
        assertThat(hoaChatList).hasSize(databaseSizeBeforeUpdate);
        HoaChat testHoaChat = hoaChatList.get(hoaChatList.size() - 1);
        assertThat(testHoaChat.getMaHoaChat()).isEqualTo(UPDATED_MA_HOA_CHAT);
        assertThat(testHoaChat.getTenHoaChat()).isEqualTo(UPDATED_TEN_HOA_CHAT);
        assertThat(testHoaChat.getHangHoaChat()).isEqualTo(UPDATED_HANG_HOA_CHAT);
        assertThat(testHoaChat.getDonViTinh()).isEqualTo(UPDATED_DON_VI_TINH);
        assertThat(testHoaChat.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testHoaChat.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testHoaChat.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testHoaChat.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testHoaChat.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the HoaChat in Elasticsearch
        verify(mockHoaChatSearchRepository, times(1)).save(testHoaChat);
    }

    @Test
    @Transactional
    public void updateNonExistingHoaChat() throws Exception {
        int databaseSizeBeforeUpdate = hoaChatRepository.findAll().size();

        // Create the HoaChat

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoaChatMockMvc.perform(put("/api/hoa-chats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hoaChat)))
            .andExpect(status().isBadRequest());

        // Validate the HoaChat in the database
        List<HoaChat> hoaChatList = hoaChatRepository.findAll();
        assertThat(hoaChatList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HoaChat in Elasticsearch
        verify(mockHoaChatSearchRepository, times(0)).save(hoaChat);
    }

    @Test
    @Transactional
    public void deleteHoaChat() throws Exception {
        // Initialize the database
        hoaChatRepository.saveAndFlush(hoaChat);

        int databaseSizeBeforeDelete = hoaChatRepository.findAll().size();

        // Get the hoaChat
        restHoaChatMockMvc.perform(delete("/api/hoa-chats/{id}", hoaChat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HoaChat> hoaChatList = hoaChatRepository.findAll();
        assertThat(hoaChatList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HoaChat in Elasticsearch
        verify(mockHoaChatSearchRepository, times(1)).deleteById(hoaChat.getId());
    }

    @Test
    @Transactional
    public void searchHoaChat() throws Exception {
        // Initialize the database
        hoaChatRepository.saveAndFlush(hoaChat);
        when(mockHoaChatSearchRepository.search(queryStringQuery("id:" + hoaChat.getId())))
            .thenReturn(Collections.singletonList(hoaChat));
        // Search the hoaChat
        restHoaChatMockMvc.perform(get("/api/_search/hoa-chats?query=id:" + hoaChat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoaChat.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHoaChat").value(hasItem(DEFAULT_MA_HOA_CHAT)))
            .andExpect(jsonPath("$.[*].tenHoaChat").value(hasItem(DEFAULT_TEN_HOA_CHAT)))
            .andExpect(jsonPath("$.[*].hangHoaChat").value(hasItem(DEFAULT_HANG_HOA_CHAT)))
            .andExpect(jsonPath("$.[*].donViTinh").value(hasItem(DEFAULT_DON_VI_TINH)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoaChat.class);
        HoaChat hoaChat1 = new HoaChat();
        hoaChat1.setId(1L);
        HoaChat hoaChat2 = new HoaChat();
        hoaChat2.setId(hoaChat1.getId());
        assertThat(hoaChat1).isEqualTo(hoaChat2);
        hoaChat2.setId(2L);
        assertThat(hoaChat1).isNotEqualTo(hoaChat2);
        hoaChat1.setId(null);
        assertThat(hoaChat1).isNotEqualTo(hoaChat2);
    }
}
