package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.SurveyQuestion;
import com.research.repository.SurveyQuestionRepository;
import com.research.service.SurveyQuestionService;
import com.research.service.dto.SurveyQuestionDTO;
import com.research.service.mapper.SurveyQuestionMapper;
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
 * Test class for the SurveyQuestionResource REST controller.
 *
 * @see SurveyQuestionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class SurveyQuestionResourceIntTest {

    private static final String DEFAULT_TARGET_ID = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_SEQ = "AAAAAAAAAA";
    private static final String UPDATED_SUB_SEQ = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT = "AAAAAAAAAA";
    private static final String UPDATED_REPORT = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_SUMMARY = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_SUMMARY = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_INFO = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_PLAN = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_PLAN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_PLAN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_PLAN_EXTRA = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_PLAN_EXTRA = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_MASTER_DEPT_ID = "AAAAAAAAAA";
    private static final String UPDATED_MASTER_DEPT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AUX_DEPT_ID = "AAAAAAAAAA";
    private static final String UPDATED_AUX_DEPT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_PLAN = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_PLAN_JSON = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_PLAN_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_SUBMIT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_SUBMIT_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_SUBMIT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_SUBMIT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LEADER_CONFIRM = "AAAAAAAAAA";
    private static final String UPDATED_LEADER_CONFIRM = "BBBBBBBBBB";

    private static final String DEFAULT_LEADER_CONFIRM_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LEADER_CONFIRM_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LEADER_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_LEADER_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_LEADER_SUBMIT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_LEADER_SUBMIT_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_LEADER_SUBMIT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LEADER_SUBMIT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_RST_PROCEDURE = "AAAAAAAAAA";
    private static final String UPDATED_RST_PROCEDURE = "BBBBBBBBBB";

    private static final String DEFAULT_RST_OUTCOME = "AAAAAAAAAA";
    private static final String UPDATED_RST_OUTCOME = "BBBBBBBBBB";

    private static final String DEFAULT_RST_DATE = "AAAAAAAAAA";
    private static final String UPDATED_RST_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_RST_TARGET = "AAAAAAAAAA";
    private static final String UPDATED_RST_TARGET = "BBBBBBBBBB";

    private static final String DEFAULT_FLOW_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FLOW_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_INPUT_DEPT_ID = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_DEPT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INPUT_USER = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_USER = "BBBBBBBBBB";

    private static final String DEFAULT_INPUT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_INPUT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_TIME = "BBBBBBBBBB";

    @Autowired
    private SurveyQuestionRepository surveyQuestionRepository;

    @Autowired
    private SurveyQuestionMapper surveyQuestionMapper;

    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSurveyQuestionMockMvc;

    private SurveyQuestion surveyQuestion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SurveyQuestionResource surveyQuestionResource = new SurveyQuestionResource(surveyQuestionService);
        this.restSurveyQuestionMockMvc = MockMvcBuilders.standaloneSetup(surveyQuestionResource)
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
    public static SurveyQuestion createEntity(EntityManager em) {
        SurveyQuestion surveyQuestion = new SurveyQuestion()
            .targetId(DEFAULT_TARGET_ID)
            .targetName(DEFAULT_TARGET_NAME)
            .surveyCode(DEFAULT_SURVEY_CODE)
            .subSeq(DEFAULT_SUB_SEQ)
            .report(DEFAULT_REPORT)
            .reportTime(DEFAULT_REPORT_TIME)
            .questionSummary(DEFAULT_QUESTION_SUMMARY)
            .questionType(DEFAULT_QUESTION_TYPE)
            .questionInfo(DEFAULT_QUESTION_INFO)
            .deptPlan(DEFAULT_DEPT_PLAN)
            .deptPlanType(DEFAULT_DEPT_PLAN_TYPE)
            .deptPlanExtra(DEFAULT_DEPT_PLAN_EXTRA)
            .questionStatus(DEFAULT_QUESTION_STATUS)
            .masterDeptId(DEFAULT_MASTER_DEPT_ID)
            .auxDeptId(DEFAULT_AUX_DEPT_ID)
            .officePlan(DEFAULT_OFFICE_PLAN)
            .officePlanJson(DEFAULT_OFFICE_PLAN_JSON)
            .officeSubmitUuid(DEFAULT_OFFICE_SUBMIT_UUID)
            .officeSubmitTime(DEFAULT_OFFICE_SUBMIT_TIME)
            .leaderConfirm(DEFAULT_LEADER_CONFIRM)
            .leaderConfirmTime(DEFAULT_LEADER_CONFIRM_TIME)
            .leaderRemarks(DEFAULT_LEADER_REMARKS)
            .leaderSubmitUuid(DEFAULT_LEADER_SUBMIT_UUID)
            .leaderSubmitTime(DEFAULT_LEADER_SUBMIT_TIME)
            .rstProcedure(DEFAULT_RST_PROCEDURE)
            .rstOutcome(DEFAULT_RST_OUTCOME)
            .rstDate(DEFAULT_RST_DATE)
            .rstTarget(DEFAULT_RST_TARGET)
            .flowStatus(DEFAULT_FLOW_STATUS)
            .inputDeptId(DEFAULT_INPUT_DEPT_ID)
            .inputUser(DEFAULT_INPUT_USER)
            .inputPhone(DEFAULT_INPUT_PHONE)
            .inputTime(DEFAULT_INPUT_TIME);
        return surveyQuestion;
    }

    @Before
    public void initTest() {
        surveyQuestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurveyQuestion() throws Exception {
        int databaseSizeBeforeCreate = surveyQuestionRepository.findAll().size();

        // Create the SurveyQuestion
        SurveyQuestionDTO surveyQuestionDTO = surveyQuestionMapper.toDto(surveyQuestion);
        restSurveyQuestionMockMvc.perform(post("/api/survey-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionDTO)))
            .andExpect(status().isCreated());

        // Validate the SurveyQuestion in the database
        List<SurveyQuestion> surveyQuestionList = surveyQuestionRepository.findAll();
        assertThat(surveyQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        SurveyQuestion testSurveyQuestion = surveyQuestionList.get(surveyQuestionList.size() - 1);
        assertThat(testSurveyQuestion.getTargetId()).isEqualTo(DEFAULT_TARGET_ID);
        assertThat(testSurveyQuestion.getTargetName()).isEqualTo(DEFAULT_TARGET_NAME);
        assertThat(testSurveyQuestion.getSurveyCode()).isEqualTo(DEFAULT_SURVEY_CODE);
        assertThat(testSurveyQuestion.getSubSeq()).isEqualTo(DEFAULT_SUB_SEQ);
        assertThat(testSurveyQuestion.getReport()).isEqualTo(DEFAULT_REPORT);
        assertThat(testSurveyQuestion.getReportTime()).isEqualTo(DEFAULT_REPORT_TIME);
        assertThat(testSurveyQuestion.getQuestionSummary()).isEqualTo(DEFAULT_QUESTION_SUMMARY);
        assertThat(testSurveyQuestion.getQuestionType()).isEqualTo(DEFAULT_QUESTION_TYPE);
        assertThat(testSurveyQuestion.getQuestionInfo()).isEqualTo(DEFAULT_QUESTION_INFO);
        assertThat(testSurveyQuestion.getDeptPlan()).isEqualTo(DEFAULT_DEPT_PLAN);
        assertThat(testSurveyQuestion.getDeptPlanType()).isEqualTo(DEFAULT_DEPT_PLAN_TYPE);
        assertThat(testSurveyQuestion.getDeptPlanExtra()).isEqualTo(DEFAULT_DEPT_PLAN_EXTRA);
        assertThat(testSurveyQuestion.getQuestionStatus()).isEqualTo(DEFAULT_QUESTION_STATUS);
        assertThat(testSurveyQuestion.getMasterDeptId()).isEqualTo(DEFAULT_MASTER_DEPT_ID);
        assertThat(testSurveyQuestion.getAuxDeptId()).isEqualTo(DEFAULT_AUX_DEPT_ID);
        assertThat(testSurveyQuestion.getOfficePlan()).isEqualTo(DEFAULT_OFFICE_PLAN);
        assertThat(testSurveyQuestion.getOfficePlanJson()).isEqualTo(DEFAULT_OFFICE_PLAN_JSON);
        assertThat(testSurveyQuestion.getOfficeSubmitUuid()).isEqualTo(DEFAULT_OFFICE_SUBMIT_UUID);
        assertThat(testSurveyQuestion.getOfficeSubmitTime()).isEqualTo(DEFAULT_OFFICE_SUBMIT_TIME);
        assertThat(testSurveyQuestion.getLeaderConfirm()).isEqualTo(DEFAULT_LEADER_CONFIRM);
        assertThat(testSurveyQuestion.getLeaderConfirmTime()).isEqualTo(DEFAULT_LEADER_CONFIRM_TIME);
        assertThat(testSurveyQuestion.getLeaderRemarks()).isEqualTo(DEFAULT_LEADER_REMARKS);
        assertThat(testSurveyQuestion.getLeaderSubmitUuid()).isEqualTo(DEFAULT_LEADER_SUBMIT_UUID);
        assertThat(testSurveyQuestion.getLeaderSubmitTime()).isEqualTo(DEFAULT_LEADER_SUBMIT_TIME);
        assertThat(testSurveyQuestion.getRstProcedure()).isEqualTo(DEFAULT_RST_PROCEDURE);
        assertThat(testSurveyQuestion.getRstOutcome()).isEqualTo(DEFAULT_RST_OUTCOME);
        assertThat(testSurveyQuestion.getRstDate()).isEqualTo(DEFAULT_RST_DATE);
        assertThat(testSurveyQuestion.getRstTarget()).isEqualTo(DEFAULT_RST_TARGET);
        assertThat(testSurveyQuestion.getFlowStatus()).isEqualTo(DEFAULT_FLOW_STATUS);
        assertThat(testSurveyQuestion.getInputDeptId()).isEqualTo(DEFAULT_INPUT_DEPT_ID);
        assertThat(testSurveyQuestion.getInputUser()).isEqualTo(DEFAULT_INPUT_USER);
        assertThat(testSurveyQuestion.getInputPhone()).isEqualTo(DEFAULT_INPUT_PHONE);
        assertThat(testSurveyQuestion.getInputTime()).isEqualTo(DEFAULT_INPUT_TIME);
    }

    @Test
    @Transactional
    public void createSurveyQuestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surveyQuestionRepository.findAll().size();

        // Create the SurveyQuestion with an existing ID
        surveyQuestion.setId(1L);
        SurveyQuestionDTO surveyQuestionDTO = surveyQuestionMapper.toDto(surveyQuestion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurveyQuestionMockMvc.perform(post("/api/survey-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SurveyQuestion in the database
        List<SurveyQuestion> surveyQuestionList = surveyQuestionRepository.findAll();
        assertThat(surveyQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSurveyQuestions() throws Exception {
        // Initialize the database
        surveyQuestionRepository.saveAndFlush(surveyQuestion);

        // Get all the surveyQuestionList
        restSurveyQuestionMockMvc.perform(get("/api/survey-questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surveyQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetId").value(hasItem(DEFAULT_TARGET_ID.toString())))
            .andExpect(jsonPath("$.[*].targetName").value(hasItem(DEFAULT_TARGET_NAME.toString())))
            .andExpect(jsonPath("$.[*].surveyCode").value(hasItem(DEFAULT_SURVEY_CODE.toString())))
            .andExpect(jsonPath("$.[*].subSeq").value(hasItem(DEFAULT_SUB_SEQ.toString())))
            .andExpect(jsonPath("$.[*].report").value(hasItem(DEFAULT_REPORT.toString())))
            .andExpect(jsonPath("$.[*].reportTime").value(hasItem(DEFAULT_REPORT_TIME.toString())))
            .andExpect(jsonPath("$.[*].questionSummary").value(hasItem(DEFAULT_QUESTION_SUMMARY.toString())))
            .andExpect(jsonPath("$.[*].questionType").value(hasItem(DEFAULT_QUESTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].questionInfo").value(hasItem(DEFAULT_QUESTION_INFO.toString())))
            .andExpect(jsonPath("$.[*].deptPlan").value(hasItem(DEFAULT_DEPT_PLAN.toString())))
            .andExpect(jsonPath("$.[*].deptPlanType").value(hasItem(DEFAULT_DEPT_PLAN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].deptPlanExtra").value(hasItem(DEFAULT_DEPT_PLAN_EXTRA.toString())))
            .andExpect(jsonPath("$.[*].questionStatus").value(hasItem(DEFAULT_QUESTION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].masterDeptId").value(hasItem(DEFAULT_MASTER_DEPT_ID.toString())))
            .andExpect(jsonPath("$.[*].auxDeptId").value(hasItem(DEFAULT_AUX_DEPT_ID.toString())))
            .andExpect(jsonPath("$.[*].officePlan").value(hasItem(DEFAULT_OFFICE_PLAN.toString())))
            .andExpect(jsonPath("$.[*].officePlanJson").value(hasItem(DEFAULT_OFFICE_PLAN_JSON.toString())))
            .andExpect(jsonPath("$.[*].officeSubmitUuid").value(hasItem(DEFAULT_OFFICE_SUBMIT_UUID.toString())))
            .andExpect(jsonPath("$.[*].officeSubmitTime").value(hasItem(DEFAULT_OFFICE_SUBMIT_TIME.toString())))
            .andExpect(jsonPath("$.[*].leaderConfirm").value(hasItem(DEFAULT_LEADER_CONFIRM.toString())))
            .andExpect(jsonPath("$.[*].leaderConfirmTime").value(hasItem(DEFAULT_LEADER_CONFIRM_TIME.toString())))
            .andExpect(jsonPath("$.[*].leaderRemarks").value(hasItem(DEFAULT_LEADER_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].leaderSubmitUuid").value(hasItem(DEFAULT_LEADER_SUBMIT_UUID.toString())))
            .andExpect(jsonPath("$.[*].leaderSubmitTime").value(hasItem(DEFAULT_LEADER_SUBMIT_TIME.toString())))
            .andExpect(jsonPath("$.[*].rstProcedure").value(hasItem(DEFAULT_RST_PROCEDURE.toString())))
            .andExpect(jsonPath("$.[*].rstOutcome").value(hasItem(DEFAULT_RST_OUTCOME.toString())))
            .andExpect(jsonPath("$.[*].rstDate").value(hasItem(DEFAULT_RST_DATE.toString())))
            .andExpect(jsonPath("$.[*].rstTarget").value(hasItem(DEFAULT_RST_TARGET.toString())))
            .andExpect(jsonPath("$.[*].flowStatus").value(hasItem(DEFAULT_FLOW_STATUS.toString())))
            .andExpect(jsonPath("$.[*].inputDeptId").value(hasItem(DEFAULT_INPUT_DEPT_ID.toString())))
            .andExpect(jsonPath("$.[*].inputUser").value(hasItem(DEFAULT_INPUT_USER.toString())))
            .andExpect(jsonPath("$.[*].inputPhone").value(hasItem(DEFAULT_INPUT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].inputTime").value(hasItem(DEFAULT_INPUT_TIME.toString())));
    }

    @Test
    @Transactional
    public void getSurveyQuestion() throws Exception {
        // Initialize the database
        surveyQuestionRepository.saveAndFlush(surveyQuestion);

        // Get the surveyQuestion
        restSurveyQuestionMockMvc.perform(get("/api/survey-questions/{id}", surveyQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(surveyQuestion.getId().intValue()))
            .andExpect(jsonPath("$.targetId").value(DEFAULT_TARGET_ID.toString()))
            .andExpect(jsonPath("$.targetName").value(DEFAULT_TARGET_NAME.toString()))
            .andExpect(jsonPath("$.surveyCode").value(DEFAULT_SURVEY_CODE.toString()))
            .andExpect(jsonPath("$.subSeq").value(DEFAULT_SUB_SEQ.toString()))
            .andExpect(jsonPath("$.report").value(DEFAULT_REPORT.toString()))
            .andExpect(jsonPath("$.reportTime").value(DEFAULT_REPORT_TIME.toString()))
            .andExpect(jsonPath("$.questionSummary").value(DEFAULT_QUESTION_SUMMARY.toString()))
            .andExpect(jsonPath("$.questionType").value(DEFAULT_QUESTION_TYPE.toString()))
            .andExpect(jsonPath("$.questionInfo").value(DEFAULT_QUESTION_INFO.toString()))
            .andExpect(jsonPath("$.deptPlan").value(DEFAULT_DEPT_PLAN.toString()))
            .andExpect(jsonPath("$.deptPlanType").value(DEFAULT_DEPT_PLAN_TYPE.toString()))
            .andExpect(jsonPath("$.deptPlanExtra").value(DEFAULT_DEPT_PLAN_EXTRA.toString()))
            .andExpect(jsonPath("$.questionStatus").value(DEFAULT_QUESTION_STATUS.toString()))
            .andExpect(jsonPath("$.masterDeptId").value(DEFAULT_MASTER_DEPT_ID.toString()))
            .andExpect(jsonPath("$.auxDeptId").value(DEFAULT_AUX_DEPT_ID.toString()))
            .andExpect(jsonPath("$.officePlan").value(DEFAULT_OFFICE_PLAN.toString()))
            .andExpect(jsonPath("$.officePlanJson").value(DEFAULT_OFFICE_PLAN_JSON.toString()))
            .andExpect(jsonPath("$.officeSubmitUuid").value(DEFAULT_OFFICE_SUBMIT_UUID.toString()))
            .andExpect(jsonPath("$.officeSubmitTime").value(DEFAULT_OFFICE_SUBMIT_TIME.toString()))
            .andExpect(jsonPath("$.leaderConfirm").value(DEFAULT_LEADER_CONFIRM.toString()))
            .andExpect(jsonPath("$.leaderConfirmTime").value(DEFAULT_LEADER_CONFIRM_TIME.toString()))
            .andExpect(jsonPath("$.leaderRemarks").value(DEFAULT_LEADER_REMARKS.toString()))
            .andExpect(jsonPath("$.leaderSubmitUuid").value(DEFAULT_LEADER_SUBMIT_UUID.toString()))
            .andExpect(jsonPath("$.leaderSubmitTime").value(DEFAULT_LEADER_SUBMIT_TIME.toString()))
            .andExpect(jsonPath("$.rstProcedure").value(DEFAULT_RST_PROCEDURE.toString()))
            .andExpect(jsonPath("$.rstOutcome").value(DEFAULT_RST_OUTCOME.toString()))
            .andExpect(jsonPath("$.rstDate").value(DEFAULT_RST_DATE.toString()))
            .andExpect(jsonPath("$.rstTarget").value(DEFAULT_RST_TARGET.toString()))
            .andExpect(jsonPath("$.flowStatus").value(DEFAULT_FLOW_STATUS.toString()))
            .andExpect(jsonPath("$.inputDeptId").value(DEFAULT_INPUT_DEPT_ID.toString()))
            .andExpect(jsonPath("$.inputUser").value(DEFAULT_INPUT_USER.toString()))
            .andExpect(jsonPath("$.inputPhone").value(DEFAULT_INPUT_PHONE.toString()))
            .andExpect(jsonPath("$.inputTime").value(DEFAULT_INPUT_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSurveyQuestion() throws Exception {
        // Get the surveyQuestion
        restSurveyQuestionMockMvc.perform(get("/api/survey-questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurveyQuestion() throws Exception {
        // Initialize the database
        surveyQuestionRepository.saveAndFlush(surveyQuestion);
        int databaseSizeBeforeUpdate = surveyQuestionRepository.findAll().size();

        // Update the surveyQuestion
        SurveyQuestion updatedSurveyQuestion = surveyQuestionRepository.findOne(surveyQuestion.getId());
        // Disconnect from session so that the updates on updatedSurveyQuestion are not directly saved in db
        em.detach(updatedSurveyQuestion);
        updatedSurveyQuestion
            .targetId(UPDATED_TARGET_ID)
            .targetName(UPDATED_TARGET_NAME)
            .surveyCode(UPDATED_SURVEY_CODE)
            .subSeq(UPDATED_SUB_SEQ)
            .report(UPDATED_REPORT)
            .reportTime(UPDATED_REPORT_TIME)
            .questionSummary(UPDATED_QUESTION_SUMMARY)
            .questionType(UPDATED_QUESTION_TYPE)
            .questionInfo(UPDATED_QUESTION_INFO)
            .deptPlan(UPDATED_DEPT_PLAN)
            .deptPlanType(UPDATED_DEPT_PLAN_TYPE)
            .deptPlanExtra(UPDATED_DEPT_PLAN_EXTRA)
            .questionStatus(UPDATED_QUESTION_STATUS)
            .masterDeptId(UPDATED_MASTER_DEPT_ID)
            .auxDeptId(UPDATED_AUX_DEPT_ID)
            .officePlan(UPDATED_OFFICE_PLAN)
            .officePlanJson(UPDATED_OFFICE_PLAN_JSON)
            .officeSubmitUuid(UPDATED_OFFICE_SUBMIT_UUID)
            .officeSubmitTime(UPDATED_OFFICE_SUBMIT_TIME)
            .leaderConfirm(UPDATED_LEADER_CONFIRM)
            .leaderConfirmTime(UPDATED_LEADER_CONFIRM_TIME)
            .leaderRemarks(UPDATED_LEADER_REMARKS)
            .leaderSubmitUuid(UPDATED_LEADER_SUBMIT_UUID)
            .leaderSubmitTime(UPDATED_LEADER_SUBMIT_TIME)
            .rstProcedure(UPDATED_RST_PROCEDURE)
            .rstOutcome(UPDATED_RST_OUTCOME)
            .rstDate(UPDATED_RST_DATE)
            .rstTarget(UPDATED_RST_TARGET)
            .flowStatus(UPDATED_FLOW_STATUS)
            .inputDeptId(UPDATED_INPUT_DEPT_ID)
            .inputUser(UPDATED_INPUT_USER)
            .inputPhone(UPDATED_INPUT_PHONE)
            .inputTime(UPDATED_INPUT_TIME);
        SurveyQuestionDTO surveyQuestionDTO = surveyQuestionMapper.toDto(updatedSurveyQuestion);

        restSurveyQuestionMockMvc.perform(put("/api/survey-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionDTO)))
            .andExpect(status().isOk());

        // Validate the SurveyQuestion in the database
        List<SurveyQuestion> surveyQuestionList = surveyQuestionRepository.findAll();
        assertThat(surveyQuestionList).hasSize(databaseSizeBeforeUpdate);
        SurveyQuestion testSurveyQuestion = surveyQuestionList.get(surveyQuestionList.size() - 1);
        assertThat(testSurveyQuestion.getTargetId()).isEqualTo(UPDATED_TARGET_ID);
        assertThat(testSurveyQuestion.getTargetName()).isEqualTo(UPDATED_TARGET_NAME);
        assertThat(testSurveyQuestion.getSurveyCode()).isEqualTo(UPDATED_SURVEY_CODE);
        assertThat(testSurveyQuestion.getSubSeq()).isEqualTo(UPDATED_SUB_SEQ);
        assertThat(testSurveyQuestion.getReport()).isEqualTo(UPDATED_REPORT);
        assertThat(testSurveyQuestion.getReportTime()).isEqualTo(UPDATED_REPORT_TIME);
        assertThat(testSurveyQuestion.getQuestionSummary()).isEqualTo(UPDATED_QUESTION_SUMMARY);
        assertThat(testSurveyQuestion.getQuestionType()).isEqualTo(UPDATED_QUESTION_TYPE);
        assertThat(testSurveyQuestion.getQuestionInfo()).isEqualTo(UPDATED_QUESTION_INFO);
        assertThat(testSurveyQuestion.getDeptPlan()).isEqualTo(UPDATED_DEPT_PLAN);
        assertThat(testSurveyQuestion.getDeptPlanType()).isEqualTo(UPDATED_DEPT_PLAN_TYPE);
        assertThat(testSurveyQuestion.getDeptPlanExtra()).isEqualTo(UPDATED_DEPT_PLAN_EXTRA);
        assertThat(testSurveyQuestion.getQuestionStatus()).isEqualTo(UPDATED_QUESTION_STATUS);
        assertThat(testSurveyQuestion.getMasterDeptId()).isEqualTo(UPDATED_MASTER_DEPT_ID);
        assertThat(testSurveyQuestion.getAuxDeptId()).isEqualTo(UPDATED_AUX_DEPT_ID);
        assertThat(testSurveyQuestion.getOfficePlan()).isEqualTo(UPDATED_OFFICE_PLAN);
        assertThat(testSurveyQuestion.getOfficePlanJson()).isEqualTo(UPDATED_OFFICE_PLAN_JSON);
        assertThat(testSurveyQuestion.getOfficeSubmitUuid()).isEqualTo(UPDATED_OFFICE_SUBMIT_UUID);
        assertThat(testSurveyQuestion.getOfficeSubmitTime()).isEqualTo(UPDATED_OFFICE_SUBMIT_TIME);
        assertThat(testSurveyQuestion.getLeaderConfirm()).isEqualTo(UPDATED_LEADER_CONFIRM);
        assertThat(testSurveyQuestion.getLeaderConfirmTime()).isEqualTo(UPDATED_LEADER_CONFIRM_TIME);
        assertThat(testSurveyQuestion.getLeaderRemarks()).isEqualTo(UPDATED_LEADER_REMARKS);
        assertThat(testSurveyQuestion.getLeaderSubmitUuid()).isEqualTo(UPDATED_LEADER_SUBMIT_UUID);
        assertThat(testSurveyQuestion.getLeaderSubmitTime()).isEqualTo(UPDATED_LEADER_SUBMIT_TIME);
        assertThat(testSurveyQuestion.getRstProcedure()).isEqualTo(UPDATED_RST_PROCEDURE);
        assertThat(testSurveyQuestion.getRstOutcome()).isEqualTo(UPDATED_RST_OUTCOME);
        assertThat(testSurveyQuestion.getRstDate()).isEqualTo(UPDATED_RST_DATE);
        assertThat(testSurveyQuestion.getRstTarget()).isEqualTo(UPDATED_RST_TARGET);
        assertThat(testSurveyQuestion.getFlowStatus()).isEqualTo(UPDATED_FLOW_STATUS);
        assertThat(testSurveyQuestion.getInputDeptId()).isEqualTo(UPDATED_INPUT_DEPT_ID);
        assertThat(testSurveyQuestion.getInputUser()).isEqualTo(UPDATED_INPUT_USER);
        assertThat(testSurveyQuestion.getInputPhone()).isEqualTo(UPDATED_INPUT_PHONE);
        assertThat(testSurveyQuestion.getInputTime()).isEqualTo(UPDATED_INPUT_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSurveyQuestion() throws Exception {
        int databaseSizeBeforeUpdate = surveyQuestionRepository.findAll().size();

        // Create the SurveyQuestion
        SurveyQuestionDTO surveyQuestionDTO = surveyQuestionMapper.toDto(surveyQuestion);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSurveyQuestionMockMvc.perform(put("/api/survey-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionDTO)))
            .andExpect(status().isCreated());

        // Validate the SurveyQuestion in the database
        List<SurveyQuestion> surveyQuestionList = surveyQuestionRepository.findAll();
        assertThat(surveyQuestionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSurveyQuestion() throws Exception {
        // Initialize the database
        surveyQuestionRepository.saveAndFlush(surveyQuestion);
        int databaseSizeBeforeDelete = surveyQuestionRepository.findAll().size();

        // Get the surveyQuestion
        restSurveyQuestionMockMvc.perform(delete("/api/survey-questions/{id}", surveyQuestion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SurveyQuestion> surveyQuestionList = surveyQuestionRepository.findAll();
        assertThat(surveyQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyQuestion.class);
        SurveyQuestion surveyQuestion1 = new SurveyQuestion();
        surveyQuestion1.setId(1L);
        SurveyQuestion surveyQuestion2 = new SurveyQuestion();
        surveyQuestion2.setId(surveyQuestion1.getId());
        assertThat(surveyQuestion1).isEqualTo(surveyQuestion2);
        surveyQuestion2.setId(2L);
        assertThat(surveyQuestion1).isNotEqualTo(surveyQuestion2);
        surveyQuestion1.setId(null);
        assertThat(surveyQuestion1).isNotEqualTo(surveyQuestion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyQuestionDTO.class);
        SurveyQuestionDTO surveyQuestionDTO1 = new SurveyQuestionDTO();
        surveyQuestionDTO1.setId(1L);
        SurveyQuestionDTO surveyQuestionDTO2 = new SurveyQuestionDTO();
        assertThat(surveyQuestionDTO1).isNotEqualTo(surveyQuestionDTO2);
        surveyQuestionDTO2.setId(surveyQuestionDTO1.getId());
        assertThat(surveyQuestionDTO1).isEqualTo(surveyQuestionDTO2);
        surveyQuestionDTO2.setId(2L);
        assertThat(surveyQuestionDTO1).isNotEqualTo(surveyQuestionDTO2);
        surveyQuestionDTO1.setId(null);
        assertThat(surveyQuestionDTO1).isNotEqualTo(surveyQuestionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(surveyQuestionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(surveyQuestionMapper.fromId(null)).isNull();
    }
}
