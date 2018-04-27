package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.Files;
import com.research.repository.FilesRepository;
import com.research.service.FilesService;
import com.research.service.dto.FilesDTO;
import com.research.service.mapper.FilesMapper;
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
 * Test class for the FilesResource REST controller.
 *
 * @see FilesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class FilesResourceIntTest {

    private static final String DEFAULT_FUNC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUNC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LINK_ID = "AAAAAAAAAA";
    private static final String UPDATED_LINK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ID_2 = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ID_2 = "BBBBBBBBBB";

    private static final String DEFAULT_AUTH_STR = "AAAAAAAAAA";
    private static final String UPDATED_AUTH_STR = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SUB_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FNAME = "AAAAAAAAAA";
    private static final String UPDATED_FNAME = "BBBBBBBBBB";

    private static final String DEFAULT_RAW_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RAW_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FSIZE = "AAAAAAAAAA";
    private static final String UPDATED_FSIZE = "BBBBBBBBBB";

    private static final String DEFAULT_FTYPE = "AAAAAAAAAA";
    private static final String UPDATED_FTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    private FilesService filesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFilesMockMvc;

    private Files files;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FilesResource filesResource = new FilesResource(filesService);
        this.restFilesMockMvc = MockMvcBuilders.standaloneSetup(filesResource)
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
    public static Files createEntity(EntityManager em) {
        Files files = new Files()
            .funcName(DEFAULT_FUNC_NAME)
            .linkId(DEFAULT_LINK_ID)
            .subId(DEFAULT_SUB_ID)
            .subId2(DEFAULT_SUB_ID_2)
            .authStr(DEFAULT_AUTH_STR)
            .subType(DEFAULT_SUB_TYPE)
            .fname(DEFAULT_FNAME)
            .rawName(DEFAULT_RAW_NAME)
            .fsize(DEFAULT_FSIZE)
            .ftype(DEFAULT_FTYPE)
            .remarks(DEFAULT_REMARKS);
        return files;
    }

    @Before
    public void initTest() {
        files = createEntity(em);
    }

    @Test
    @Transactional
    public void createFiles() throws Exception {
        int databaseSizeBeforeCreate = filesRepository.findAll().size();

        // Create the Files
        FilesDTO filesDTO = filesMapper.toDto(files);
        restFilesMockMvc.perform(post("/api/files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isCreated());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeCreate + 1);
        Files testFiles = filesList.get(filesList.size() - 1);
        assertThat(testFiles.getFuncName()).isEqualTo(DEFAULT_FUNC_NAME);
        assertThat(testFiles.getLinkId()).isEqualTo(DEFAULT_LINK_ID);
        assertThat(testFiles.getSubId()).isEqualTo(DEFAULT_SUB_ID);
        assertThat(testFiles.getSubId2()).isEqualTo(DEFAULT_SUB_ID_2);
        assertThat(testFiles.getAuthStr()).isEqualTo(DEFAULT_AUTH_STR);
        assertThat(testFiles.getSubType()).isEqualTo(DEFAULT_SUB_TYPE);
        assertThat(testFiles.getFname()).isEqualTo(DEFAULT_FNAME);
        assertThat(testFiles.getRawName()).isEqualTo(DEFAULT_RAW_NAME);
        assertThat(testFiles.getFsize()).isEqualTo(DEFAULT_FSIZE);
        assertThat(testFiles.getFtype()).isEqualTo(DEFAULT_FTYPE);
        assertThat(testFiles.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createFilesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = filesRepository.findAll().size();

        // Create the Files with an existing ID
        files.setId(1L);
        FilesDTO filesDTO = filesMapper.toDto(files);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFilesMockMvc.perform(post("/api/files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        // Get all the filesList
        restFilesMockMvc.perform(get("/api/files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(files.getId().intValue())))
            .andExpect(jsonPath("$.[*].funcName").value(hasItem(DEFAULT_FUNC_NAME.toString())))
            .andExpect(jsonPath("$.[*].linkId").value(hasItem(DEFAULT_LINK_ID.toString())))
            .andExpect(jsonPath("$.[*].subId").value(hasItem(DEFAULT_SUB_ID.toString())))
            .andExpect(jsonPath("$.[*].subId2").value(hasItem(DEFAULT_SUB_ID_2.toString())))
            .andExpect(jsonPath("$.[*].authStr").value(hasItem(DEFAULT_AUTH_STR.toString())))
            .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE.toString())))
            .andExpect(jsonPath("$.[*].fname").value(hasItem(DEFAULT_FNAME.toString())))
            .andExpect(jsonPath("$.[*].rawName").value(hasItem(DEFAULT_RAW_NAME.toString())))
            .andExpect(jsonPath("$.[*].fsize").value(hasItem(DEFAULT_FSIZE.toString())))
            .andExpect(jsonPath("$.[*].ftype").value(hasItem(DEFAULT_FTYPE.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }

    @Test
    @Transactional
    public void getFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        // Get the files
        restFilesMockMvc.perform(get("/api/files/{id}", files.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(files.getId().intValue()))
            .andExpect(jsonPath("$.funcName").value(DEFAULT_FUNC_NAME.toString()))
            .andExpect(jsonPath("$.linkId").value(DEFAULT_LINK_ID.toString()))
            .andExpect(jsonPath("$.subId").value(DEFAULT_SUB_ID.toString()))
            .andExpect(jsonPath("$.subId2").value(DEFAULT_SUB_ID_2.toString()))
            .andExpect(jsonPath("$.authStr").value(DEFAULT_AUTH_STR.toString()))
            .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE.toString()))
            .andExpect(jsonPath("$.fname").value(DEFAULT_FNAME.toString()))
            .andExpect(jsonPath("$.rawName").value(DEFAULT_RAW_NAME.toString()))
            .andExpect(jsonPath("$.fsize").value(DEFAULT_FSIZE.toString()))
            .andExpect(jsonPath("$.ftype").value(DEFAULT_FTYPE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFiles() throws Exception {
        // Get the files
        restFilesMockMvc.perform(get("/api/files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);
        int databaseSizeBeforeUpdate = filesRepository.findAll().size();

        // Update the files
        Files updatedFiles = filesRepository.findOne(files.getId());
        // Disconnect from session so that the updates on updatedFiles are not directly saved in db
        em.detach(updatedFiles);
        updatedFiles
            .funcName(UPDATED_FUNC_NAME)
            .linkId(UPDATED_LINK_ID)
            .subId(UPDATED_SUB_ID)
            .subId2(UPDATED_SUB_ID_2)
            .authStr(UPDATED_AUTH_STR)
            .subType(UPDATED_SUB_TYPE)
            .fname(UPDATED_FNAME)
            .rawName(UPDATED_RAW_NAME)
            .fsize(UPDATED_FSIZE)
            .ftype(UPDATED_FTYPE)
            .remarks(UPDATED_REMARKS);
        FilesDTO filesDTO = filesMapper.toDto(updatedFiles);

        restFilesMockMvc.perform(put("/api/files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isOk());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeUpdate);
        Files testFiles = filesList.get(filesList.size() - 1);
        assertThat(testFiles.getFuncName()).isEqualTo(UPDATED_FUNC_NAME);
        assertThat(testFiles.getLinkId()).isEqualTo(UPDATED_LINK_ID);
        assertThat(testFiles.getSubId()).isEqualTo(UPDATED_SUB_ID);
        assertThat(testFiles.getSubId2()).isEqualTo(UPDATED_SUB_ID_2);
        assertThat(testFiles.getAuthStr()).isEqualTo(UPDATED_AUTH_STR);
        assertThat(testFiles.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
        assertThat(testFiles.getFname()).isEqualTo(UPDATED_FNAME);
        assertThat(testFiles.getRawName()).isEqualTo(UPDATED_RAW_NAME);
        assertThat(testFiles.getFsize()).isEqualTo(UPDATED_FSIZE);
        assertThat(testFiles.getFtype()).isEqualTo(UPDATED_FTYPE);
        assertThat(testFiles.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingFiles() throws Exception {
        int databaseSizeBeforeUpdate = filesRepository.findAll().size();

        // Create the Files
        FilesDTO filesDTO = filesMapper.toDto(files);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFilesMockMvc.perform(put("/api/files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isCreated());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);
        int databaseSizeBeforeDelete = filesRepository.findAll().size();

        // Get the files
        restFilesMockMvc.perform(delete("/api/files/{id}", files.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Files.class);
        Files files1 = new Files();
        files1.setId(1L);
        Files files2 = new Files();
        files2.setId(files1.getId());
        assertThat(files1).isEqualTo(files2);
        files2.setId(2L);
        assertThat(files1).isNotEqualTo(files2);
        files1.setId(null);
        assertThat(files1).isNotEqualTo(files2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FilesDTO.class);
        FilesDTO filesDTO1 = new FilesDTO();
        filesDTO1.setId(1L);
        FilesDTO filesDTO2 = new FilesDTO();
        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
        filesDTO2.setId(filesDTO1.getId());
        assertThat(filesDTO1).isEqualTo(filesDTO2);
        filesDTO2.setId(2L);
        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
        filesDTO1.setId(null);
        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(filesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(filesMapper.fromId(null)).isNull();
    }
}
