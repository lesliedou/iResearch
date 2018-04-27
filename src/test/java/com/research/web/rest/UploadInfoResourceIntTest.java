package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.UploadInfo;
import com.research.repository.UploadInfoRepository;
import com.research.service.UploadInfoService;
import com.research.service.dto.UploadInfoDTO;
import com.research.service.mapper.UploadInfoMapper;
import com.research.web.rest.errors.ExceptionTranslator;

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
import java.util.List;

import static com.research.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UploadInfoResource REST controller.
 *
 * @see UploadInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class UploadInfoResourceIntTest {

    private static final String DEFAULT_INFO_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INFO_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJ = "AAAAAAAAAA";
    private static final String UPDATED_SUBJ = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    @Autowired
    private UploadInfoRepository uploadInfoRepository;

    @Autowired
    private UploadInfoMapper uploadInfoMapper;

    @Autowired
    private UploadInfoService uploadInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUploadInfoMockMvc;

    private UploadInfo uploadInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UploadInfoResource uploadInfoResource = new UploadInfoResource(uploadInfoService);
        this.restUploadInfoMockMvc = MockMvcBuilders.standaloneSetup(uploadInfoResource)
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
    public static UploadInfo createEntity(EntityManager em) {
        UploadInfo uploadInfo = new UploadInfo()
            .infoType(DEFAULT_INFO_TYPE)
            .subj(DEFAULT_SUBJ)
            .content(DEFAULT_CONTENT);
        return uploadInfo;
    }

    @Before
    public void initTest() {
        uploadInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createUploadInfo() throws Exception {
        int databaseSizeBeforeCreate = uploadInfoRepository.findAll().size();

        // Create the UploadInfo
        UploadInfoDTO uploadInfoDTO = uploadInfoMapper.toDto(uploadInfo);
        restUploadInfoMockMvc.perform(post("/api/upload-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uploadInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the UploadInfo in the database
        List<UploadInfo> uploadInfoList = uploadInfoRepository.findAll();
        assertThat(uploadInfoList).hasSize(databaseSizeBeforeCreate + 1);
        UploadInfo testUploadInfo = uploadInfoList.get(uploadInfoList.size() - 1);
        assertThat(testUploadInfo.getInfoType()).isEqualTo(DEFAULT_INFO_TYPE);
        assertThat(testUploadInfo.getSubj()).isEqualTo(DEFAULT_SUBJ);
        assertThat(testUploadInfo.getContent()).isEqualTo(DEFAULT_CONTENT);
    }

    @Test
    @Transactional
    public void createUploadInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uploadInfoRepository.findAll().size();

        // Create the UploadInfo with an existing ID
        uploadInfo.setId(1L);
        UploadInfoDTO uploadInfoDTO = uploadInfoMapper.toDto(uploadInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUploadInfoMockMvc.perform(post("/api/upload-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uploadInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UploadInfo in the database
        List<UploadInfo> uploadInfoList = uploadInfoRepository.findAll();
        assertThat(uploadInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUploadInfos() throws Exception {
        // Initialize the database
        uploadInfoRepository.saveAndFlush(uploadInfo);

        // Get all the uploadInfoList
        restUploadInfoMockMvc.perform(get("/api/upload-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uploadInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].infoType").value(hasItem(DEFAULT_INFO_TYPE.toString())))
            .andExpect(jsonPath("$.[*].subj").value(hasItem(DEFAULT_SUBJ.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
    }

    @Test
    @Transactional
    public void getUploadInfo() throws Exception {
        // Initialize the database
        uploadInfoRepository.saveAndFlush(uploadInfo);

        // Get the uploadInfo
        restUploadInfoMockMvc.perform(get("/api/upload-infos/{id}", uploadInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uploadInfo.getId().intValue()))
            .andExpect(jsonPath("$.infoType").value(DEFAULT_INFO_TYPE.toString()))
            .andExpect(jsonPath("$.subj").value(DEFAULT_SUBJ.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUploadInfo() throws Exception {
        // Get the uploadInfo
        restUploadInfoMockMvc.perform(get("/api/upload-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUploadInfo() throws Exception {
        // Initialize the database
        uploadInfoRepository.saveAndFlush(uploadInfo);
        int databaseSizeBeforeUpdate = uploadInfoRepository.findAll().size();

        // Update the uploadInfo
        UploadInfo updatedUploadInfo = uploadInfoRepository.findOne(uploadInfo.getId());
        // Disconnect from session so that the updates on updatedUploadInfo are not directly saved in db
        em.detach(updatedUploadInfo);
        updatedUploadInfo
            .infoType(UPDATED_INFO_TYPE)
            .subj(UPDATED_SUBJ)
            .content(UPDATED_CONTENT);
        UploadInfoDTO uploadInfoDTO = uploadInfoMapper.toDto(updatedUploadInfo);

        restUploadInfoMockMvc.perform(put("/api/upload-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uploadInfoDTO)))
            .andExpect(status().isOk());

        // Validate the UploadInfo in the database
        List<UploadInfo> uploadInfoList = uploadInfoRepository.findAll();
        assertThat(uploadInfoList).hasSize(databaseSizeBeforeUpdate);
        UploadInfo testUploadInfo = uploadInfoList.get(uploadInfoList.size() - 1);
        assertThat(testUploadInfo.getInfoType()).isEqualTo(UPDATED_INFO_TYPE);
        assertThat(testUploadInfo.getSubj()).isEqualTo(UPDATED_SUBJ);
        assertThat(testUploadInfo.getContent()).isEqualTo(UPDATED_CONTENT);
    }

    @Test
    @Transactional
    public void updateNonExistingUploadInfo() throws Exception {
        int databaseSizeBeforeUpdate = uploadInfoRepository.findAll().size();

        // Create the UploadInfo
        UploadInfoDTO uploadInfoDTO = uploadInfoMapper.toDto(uploadInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUploadInfoMockMvc.perform(put("/api/upload-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uploadInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the UploadInfo in the database
        List<UploadInfo> uploadInfoList = uploadInfoRepository.findAll();
        assertThat(uploadInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUploadInfo() throws Exception {
        // Initialize the database
        uploadInfoRepository.saveAndFlush(uploadInfo);
        int databaseSizeBeforeDelete = uploadInfoRepository.findAll().size();

        // Get the uploadInfo
        restUploadInfoMockMvc.perform(delete("/api/upload-infos/{id}", uploadInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UploadInfo> uploadInfoList = uploadInfoRepository.findAll();
        assertThat(uploadInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UploadInfo.class);
        UploadInfo uploadInfo1 = new UploadInfo();
        uploadInfo1.setId(1L);
        UploadInfo uploadInfo2 = new UploadInfo();
        uploadInfo2.setId(uploadInfo1.getId());
        assertThat(uploadInfo1).isEqualTo(uploadInfo2);
        uploadInfo2.setId(2L);
        assertThat(uploadInfo1).isNotEqualTo(uploadInfo2);
        uploadInfo1.setId(null);
        assertThat(uploadInfo1).isNotEqualTo(uploadInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UploadInfoDTO.class);
        UploadInfoDTO uploadInfoDTO1 = new UploadInfoDTO();
        uploadInfoDTO1.setId(1L);
        UploadInfoDTO uploadInfoDTO2 = new UploadInfoDTO();
        assertThat(uploadInfoDTO1).isNotEqualTo(uploadInfoDTO2);
        uploadInfoDTO2.setId(uploadInfoDTO1.getId());
        assertThat(uploadInfoDTO1).isEqualTo(uploadInfoDTO2);
        uploadInfoDTO2.setId(2L);
        assertThat(uploadInfoDTO1).isNotEqualTo(uploadInfoDTO2);
        uploadInfoDTO1.setId(null);
        assertThat(uploadInfoDTO1).isNotEqualTo(uploadInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uploadInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uploadInfoMapper.fromId(null)).isNull();
    }
}
