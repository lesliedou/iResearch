package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.SurveyQuestionIntf;
import com.research.repository.SurveyQuestionIntfRepository;
import com.research.service.SurveyQuestionIntfService;
import com.research.service.dto.SurveyQuestionIntfDTO;
import com.research.service.mapper.SurveyQuestionIntfMapper;
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
 * Test class for the SurveyQuestionIntfResource REST controller.
 *
 * @see SurveyQuestionIntfResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class SurveyQuestionIntfResourceIntTest {

    private static final String DEFAULT_TASK_ID = "AAAAAAAAAA";
    private static final String UPDATED_TASK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RETURN_TIME = "AAAAAAAAAA";
    private static final String UPDATED_RETURN_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_RETURN_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_RETURN_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_ALL_END_TIME = "AAAAAAAAAA";
    private static final String UPDATED_ALL_END_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_END_TIME = "AAAAAAAAAA";
    private static final String UPDATED_END_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_SOLVING_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_SOLVING_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_SOLVING_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_SOLVING_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_END_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_END_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_BANLI_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_BANLI_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_EXECUTE_DEPT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTE_DEPT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ASSISTANT_DEPT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSISTANT_DEPT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FEED_BACK_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_FEED_BACK_NOTE = "BBBBBBBBBB";

    @Autowired
    private SurveyQuestionIntfRepository surveyQuestionIntfRepository;

    @Autowired
    private SurveyQuestionIntfMapper surveyQuestionIntfMapper;

    @Autowired
    private SurveyQuestionIntfService surveyQuestionIntfService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSurveyQuestionIntfMockMvc;

    private SurveyQuestionIntf surveyQuestionIntf;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SurveyQuestionIntfResource surveyQuestionIntfResource = new SurveyQuestionIntfResource(surveyQuestionIntfService);
        this.restSurveyQuestionIntfMockMvc = MockMvcBuilders.standaloneSetup(surveyQuestionIntfResource)
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
    public static SurveyQuestionIntf createEntity(EntityManager em) {
        SurveyQuestionIntf surveyQuestionIntf = new SurveyQuestionIntf()
            .taskID(DEFAULT_TASK_ID)
            .statusType(DEFAULT_STATUS_TYPE)
            .returnTime(DEFAULT_RETURN_TIME)
            .returnNote(DEFAULT_RETURN_NOTE)
            .createTime(DEFAULT_CREATE_TIME)
            .allEndTime(DEFAULT_ALL_END_TIME)
            .createNote(DEFAULT_CREATE_NOTE)
            .endTime(DEFAULT_END_TIME)
            .lastSolvingTime(DEFAULT_LAST_SOLVING_TIME)
            .solvingNote(DEFAULT_SOLVING_NOTE)
            .endNote(DEFAULT_END_NOTE)
            .banliResult(DEFAULT_BANLI_RESULT)
            .executeDeptName(DEFAULT_EXECUTE_DEPT_NAME)
            .assistantDeptName(DEFAULT_ASSISTANT_DEPT_NAME)
            .feedBackNote(DEFAULT_FEED_BACK_NOTE);
        return surveyQuestionIntf;
    }

    @Before
    public void initTest() {
        surveyQuestionIntf = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurveyQuestionIntf() throws Exception {
        int databaseSizeBeforeCreate = surveyQuestionIntfRepository.findAll().size();

        // Create the SurveyQuestionIntf
        SurveyQuestionIntfDTO surveyQuestionIntfDTO = surveyQuestionIntfMapper.toDto(surveyQuestionIntf);
        restSurveyQuestionIntfMockMvc.perform(post("/api/survey-question-intfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionIntfDTO)))
            .andExpect(status().isCreated());

        // Validate the SurveyQuestionIntf in the database
        List<SurveyQuestionIntf> surveyQuestionIntfList = surveyQuestionIntfRepository.findAll();
        assertThat(surveyQuestionIntfList).hasSize(databaseSizeBeforeCreate + 1);
        SurveyQuestionIntf testSurveyQuestionIntf = surveyQuestionIntfList.get(surveyQuestionIntfList.size() - 1);
        assertThat(testSurveyQuestionIntf.getTaskID()).isEqualTo(DEFAULT_TASK_ID);
        assertThat(testSurveyQuestionIntf.getStatusType()).isEqualTo(DEFAULT_STATUS_TYPE);
        assertThat(testSurveyQuestionIntf.getReturnTime()).isEqualTo(DEFAULT_RETURN_TIME);
        assertThat(testSurveyQuestionIntf.getReturnNote()).isEqualTo(DEFAULT_RETURN_NOTE);
        assertThat(testSurveyQuestionIntf.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSurveyQuestionIntf.getAllEndTime()).isEqualTo(DEFAULT_ALL_END_TIME);
        assertThat(testSurveyQuestionIntf.getCreateNote()).isEqualTo(DEFAULT_CREATE_NOTE);
        assertThat(testSurveyQuestionIntf.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testSurveyQuestionIntf.getLastSolvingTime()).isEqualTo(DEFAULT_LAST_SOLVING_TIME);
        assertThat(testSurveyQuestionIntf.getSolvingNote()).isEqualTo(DEFAULT_SOLVING_NOTE);
        assertThat(testSurveyQuestionIntf.getEndNote()).isEqualTo(DEFAULT_END_NOTE);
        assertThat(testSurveyQuestionIntf.getBanliResult()).isEqualTo(DEFAULT_BANLI_RESULT);
        assertThat(testSurveyQuestionIntf.getExecuteDeptName()).isEqualTo(DEFAULT_EXECUTE_DEPT_NAME);
        assertThat(testSurveyQuestionIntf.getAssistantDeptName()).isEqualTo(DEFAULT_ASSISTANT_DEPT_NAME);
        assertThat(testSurveyQuestionIntf.getFeedBackNote()).isEqualTo(DEFAULT_FEED_BACK_NOTE);
    }

    @Test
    @Transactional
    public void createSurveyQuestionIntfWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surveyQuestionIntfRepository.findAll().size();

        // Create the SurveyQuestionIntf with an existing ID
        surveyQuestionIntf.setId(1L);
        SurveyQuestionIntfDTO surveyQuestionIntfDTO = surveyQuestionIntfMapper.toDto(surveyQuestionIntf);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurveyQuestionIntfMockMvc.perform(post("/api/survey-question-intfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionIntfDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SurveyQuestionIntf in the database
        List<SurveyQuestionIntf> surveyQuestionIntfList = surveyQuestionIntfRepository.findAll();
        assertThat(surveyQuestionIntfList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSurveyQuestionIntfs() throws Exception {
        // Initialize the database
        surveyQuestionIntfRepository.saveAndFlush(surveyQuestionIntf);

        // Get all the surveyQuestionIntfList
        restSurveyQuestionIntfMockMvc.perform(get("/api/survey-question-intfs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surveyQuestionIntf.getId().intValue())))
            .andExpect(jsonPath("$.[*].taskID").value(hasItem(DEFAULT_TASK_ID.toString())))
            .andExpect(jsonPath("$.[*].statusType").value(hasItem(DEFAULT_STATUS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].returnTime").value(hasItem(DEFAULT_RETURN_TIME.toString())))
            .andExpect(jsonPath("$.[*].returnNote").value(hasItem(DEFAULT_RETURN_NOTE.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].allEndTime").value(hasItem(DEFAULT_ALL_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].createNote").value(hasItem(DEFAULT_CREATE_NOTE.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastSolvingTime").value(hasItem(DEFAULT_LAST_SOLVING_TIME.toString())))
            .andExpect(jsonPath("$.[*].solvingNote").value(hasItem(DEFAULT_SOLVING_NOTE.toString())))
            .andExpect(jsonPath("$.[*].endNote").value(hasItem(DEFAULT_END_NOTE.toString())))
            .andExpect(jsonPath("$.[*].banliResult").value(hasItem(DEFAULT_BANLI_RESULT.toString())))
            .andExpect(jsonPath("$.[*].executeDeptName").value(hasItem(DEFAULT_EXECUTE_DEPT_NAME.toString())))
            .andExpect(jsonPath("$.[*].assistantDeptName").value(hasItem(DEFAULT_ASSISTANT_DEPT_NAME.toString())))
            .andExpect(jsonPath("$.[*].feedBackNote").value(hasItem(DEFAULT_FEED_BACK_NOTE.toString())));
    }

    @Test
    @Transactional
    public void getSurveyQuestionIntf() throws Exception {
        // Initialize the database
        surveyQuestionIntfRepository.saveAndFlush(surveyQuestionIntf);

        // Get the surveyQuestionIntf
        restSurveyQuestionIntfMockMvc.perform(get("/api/survey-question-intfs/{id}", surveyQuestionIntf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(surveyQuestionIntf.getId().intValue()))
            .andExpect(jsonPath("$.taskID").value(DEFAULT_TASK_ID.toString()))
            .andExpect(jsonPath("$.statusType").value(DEFAULT_STATUS_TYPE.toString()))
            .andExpect(jsonPath("$.returnTime").value(DEFAULT_RETURN_TIME.toString()))
            .andExpect(jsonPath("$.returnNote").value(DEFAULT_RETURN_NOTE.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.allEndTime").value(DEFAULT_ALL_END_TIME.toString()))
            .andExpect(jsonPath("$.createNote").value(DEFAULT_CREATE_NOTE.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.lastSolvingTime").value(DEFAULT_LAST_SOLVING_TIME.toString()))
            .andExpect(jsonPath("$.solvingNote").value(DEFAULT_SOLVING_NOTE.toString()))
            .andExpect(jsonPath("$.endNote").value(DEFAULT_END_NOTE.toString()))
            .andExpect(jsonPath("$.banliResult").value(DEFAULT_BANLI_RESULT.toString()))
            .andExpect(jsonPath("$.executeDeptName").value(DEFAULT_EXECUTE_DEPT_NAME.toString()))
            .andExpect(jsonPath("$.assistantDeptName").value(DEFAULT_ASSISTANT_DEPT_NAME.toString()))
            .andExpect(jsonPath("$.feedBackNote").value(DEFAULT_FEED_BACK_NOTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSurveyQuestionIntf() throws Exception {
        // Get the surveyQuestionIntf
        restSurveyQuestionIntfMockMvc.perform(get("/api/survey-question-intfs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurveyQuestionIntf() throws Exception {
        // Initialize the database
        surveyQuestionIntfRepository.saveAndFlush(surveyQuestionIntf);
        int databaseSizeBeforeUpdate = surveyQuestionIntfRepository.findAll().size();

        // Update the surveyQuestionIntf
        SurveyQuestionIntf updatedSurveyQuestionIntf = surveyQuestionIntfRepository.findOne(surveyQuestionIntf.getId());
        // Disconnect from session so that the updates on updatedSurveyQuestionIntf are not directly saved in db
        em.detach(updatedSurveyQuestionIntf);
        updatedSurveyQuestionIntf
            .taskID(UPDATED_TASK_ID)
            .statusType(UPDATED_STATUS_TYPE)
            .returnTime(UPDATED_RETURN_TIME)
            .returnNote(UPDATED_RETURN_NOTE)
            .createTime(UPDATED_CREATE_TIME)
            .allEndTime(UPDATED_ALL_END_TIME)
            .createNote(UPDATED_CREATE_NOTE)
            .endTime(UPDATED_END_TIME)
            .lastSolvingTime(UPDATED_LAST_SOLVING_TIME)
            .solvingNote(UPDATED_SOLVING_NOTE)
            .endNote(UPDATED_END_NOTE)
            .banliResult(UPDATED_BANLI_RESULT)
            .executeDeptName(UPDATED_EXECUTE_DEPT_NAME)
            .assistantDeptName(UPDATED_ASSISTANT_DEPT_NAME)
            .feedBackNote(UPDATED_FEED_BACK_NOTE);
        SurveyQuestionIntfDTO surveyQuestionIntfDTO = surveyQuestionIntfMapper.toDto(updatedSurveyQuestionIntf);

        restSurveyQuestionIntfMockMvc.perform(put("/api/survey-question-intfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionIntfDTO)))
            .andExpect(status().isOk());

        // Validate the SurveyQuestionIntf in the database
        List<SurveyQuestionIntf> surveyQuestionIntfList = surveyQuestionIntfRepository.findAll();
        assertThat(surveyQuestionIntfList).hasSize(databaseSizeBeforeUpdate);
        SurveyQuestionIntf testSurveyQuestionIntf = surveyQuestionIntfList.get(surveyQuestionIntfList.size() - 1);
        assertThat(testSurveyQuestionIntf.getTaskID()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testSurveyQuestionIntf.getStatusType()).isEqualTo(UPDATED_STATUS_TYPE);
        assertThat(testSurveyQuestionIntf.getReturnTime()).isEqualTo(UPDATED_RETURN_TIME);
        assertThat(testSurveyQuestionIntf.getReturnNote()).isEqualTo(UPDATED_RETURN_NOTE);
        assertThat(testSurveyQuestionIntf.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSurveyQuestionIntf.getAllEndTime()).isEqualTo(UPDATED_ALL_END_TIME);
        assertThat(testSurveyQuestionIntf.getCreateNote()).isEqualTo(UPDATED_CREATE_NOTE);
        assertThat(testSurveyQuestionIntf.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testSurveyQuestionIntf.getLastSolvingTime()).isEqualTo(UPDATED_LAST_SOLVING_TIME);
        assertThat(testSurveyQuestionIntf.getSolvingNote()).isEqualTo(UPDATED_SOLVING_NOTE);
        assertThat(testSurveyQuestionIntf.getEndNote()).isEqualTo(UPDATED_END_NOTE);
        assertThat(testSurveyQuestionIntf.getBanliResult()).isEqualTo(UPDATED_BANLI_RESULT);
        assertThat(testSurveyQuestionIntf.getExecuteDeptName()).isEqualTo(UPDATED_EXECUTE_DEPT_NAME);
        assertThat(testSurveyQuestionIntf.getAssistantDeptName()).isEqualTo(UPDATED_ASSISTANT_DEPT_NAME);
        assertThat(testSurveyQuestionIntf.getFeedBackNote()).isEqualTo(UPDATED_FEED_BACK_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingSurveyQuestionIntf() throws Exception {
        int databaseSizeBeforeUpdate = surveyQuestionIntfRepository.findAll().size();

        // Create the SurveyQuestionIntf
        SurveyQuestionIntfDTO surveyQuestionIntfDTO = surveyQuestionIntfMapper.toDto(surveyQuestionIntf);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSurveyQuestionIntfMockMvc.perform(put("/api/survey-question-intfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionIntfDTO)))
            .andExpect(status().isCreated());

        // Validate the SurveyQuestionIntf in the database
        List<SurveyQuestionIntf> surveyQuestionIntfList = surveyQuestionIntfRepository.findAll();
        assertThat(surveyQuestionIntfList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSurveyQuestionIntf() throws Exception {
        // Initialize the database
        surveyQuestionIntfRepository.saveAndFlush(surveyQuestionIntf);
        int databaseSizeBeforeDelete = surveyQuestionIntfRepository.findAll().size();

        // Get the surveyQuestionIntf
        restSurveyQuestionIntfMockMvc.perform(delete("/api/survey-question-intfs/{id}", surveyQuestionIntf.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SurveyQuestionIntf> surveyQuestionIntfList = surveyQuestionIntfRepository.findAll();
        assertThat(surveyQuestionIntfList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyQuestionIntf.class);
        SurveyQuestionIntf surveyQuestionIntf1 = new SurveyQuestionIntf();
        surveyQuestionIntf1.setId(1L);
        SurveyQuestionIntf surveyQuestionIntf2 = new SurveyQuestionIntf();
        surveyQuestionIntf2.setId(surveyQuestionIntf1.getId());
        assertThat(surveyQuestionIntf1).isEqualTo(surveyQuestionIntf2);
        surveyQuestionIntf2.setId(2L);
        assertThat(surveyQuestionIntf1).isNotEqualTo(surveyQuestionIntf2);
        surveyQuestionIntf1.setId(null);
        assertThat(surveyQuestionIntf1).isNotEqualTo(surveyQuestionIntf2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyQuestionIntfDTO.class);
        SurveyQuestionIntfDTO surveyQuestionIntfDTO1 = new SurveyQuestionIntfDTO();
        surveyQuestionIntfDTO1.setId(1L);
        SurveyQuestionIntfDTO surveyQuestionIntfDTO2 = new SurveyQuestionIntfDTO();
        assertThat(surveyQuestionIntfDTO1).isNotEqualTo(surveyQuestionIntfDTO2);
        surveyQuestionIntfDTO2.setId(surveyQuestionIntfDTO1.getId());
        assertThat(surveyQuestionIntfDTO1).isEqualTo(surveyQuestionIntfDTO2);
        surveyQuestionIntfDTO2.setId(2L);
        assertThat(surveyQuestionIntfDTO1).isNotEqualTo(surveyQuestionIntfDTO2);
        surveyQuestionIntfDTO1.setId(null);
        assertThat(surveyQuestionIntfDTO1).isNotEqualTo(surveyQuestionIntfDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(surveyQuestionIntfMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(surveyQuestionIntfMapper.fromId(null)).isNull();
    }
}
