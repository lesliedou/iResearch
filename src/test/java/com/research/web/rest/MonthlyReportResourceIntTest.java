package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.MonthlyReport;
import com.research.repository.MonthlyReportRepository;
import com.research.service.MonthlyReportService;
import com.research.service.dto.MonthlyReportDTO;
import com.research.service.mapper.MonthlyReportMapper;
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
 * Test class for the MonthlyReportResource REST controller.
 *
 * @see MonthlyReportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class MonthlyReportResourceIntTest {

    private static final String DEFAULT_MONTH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MONTH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT = "AAAAAAAAAA";
    private static final String UPDATED_REPORT = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNT = "AAAAAAAAAA";
    private static final String UPDATED_COUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_DEPT_COUNT = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_DEPT_COUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_COUNT = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_COUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_TARGET_COUNT_JSON = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_TARGET_COUNT_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_TARGET_COUNT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_TARGET_COUNT_TOTAL = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_TYPE_COUNT_JSON = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_TYPE_COUNT_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_TYPE_COUNT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_TYPE_COUNT_TOTAL = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_CCOUNT_JSON = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_CCOUNT_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_COUNT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_COUNT_TOTAL = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_CLOSED_COUNT_JSON = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_CLOSED_COUNT_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_CLOSED_COUNT_TOTA = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_CLOSED_COUNT_TOTA = "BBBBBBBBBB";

    private static final String DEFAULT_PROPOSAL_COUNT_JSON = "AAAAAAAAAA";
    private static final String UPDATED_PROPOSAL_COUNT_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_PROPOSAL_COUNT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_PROPOSAL_COUNT_TOTAL = "BBBBBBBBBB";

    @Autowired
    private MonthlyReportRepository monthlyReportRepository;

    @Autowired
    private MonthlyReportMapper monthlyReportMapper;

    @Autowired
    private MonthlyReportService monthlyReportService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMonthlyReportMockMvc;

    private MonthlyReport monthlyReport;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MonthlyReportResource monthlyReportResource = new MonthlyReportResource(monthlyReportService);
        this.restMonthlyReportMockMvc = MockMvcBuilders.standaloneSetup(monthlyReportResource)
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
    public static MonthlyReport createEntity(EntityManager em) {
        MonthlyReport monthlyReport = new MonthlyReport()
            .monthName(DEFAULT_MONTH_NAME)
            .report(DEFAULT_REPORT)
            .reportTime(DEFAULT_REPORT_TIME)
            .reportDate(DEFAULT_REPORT_DATE)
            .count(DEFAULT_COUNT)
            .surveyDeptCount(DEFAULT_SURVEY_DEPT_COUNT)
            .surveyCount(DEFAULT_SURVEY_COUNT)
            .surveyTargetCountJson(DEFAULT_SURVEY_TARGET_COUNT_JSON)
            .surveyTargetCountTotal(DEFAULT_SURVEY_TARGET_COUNT_TOTAL)
            .surveyTypeCountJson(DEFAULT_SURVEY_TYPE_COUNT_JSON)
            .surveyTypeCountTotal(DEFAULT_SURVEY_TYPE_COUNT_TOTAL)
            .questionCcountJson(DEFAULT_QUESTION_CCOUNT_JSON)
            .questionCountTotal(DEFAULT_QUESTION_COUNT_TOTAL)
            .questionClosedCountJson(DEFAULT_QUESTION_CLOSED_COUNT_JSON)
            .questionClosedCountTota(DEFAULT_QUESTION_CLOSED_COUNT_TOTA)
            .proposalCountJson(DEFAULT_PROPOSAL_COUNT_JSON)
            .proposalCountTotal(DEFAULT_PROPOSAL_COUNT_TOTAL);
        return monthlyReport;
    }

    @Before
    public void initTest() {
        monthlyReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createMonthlyReport() throws Exception {
        int databaseSizeBeforeCreate = monthlyReportRepository.findAll().size();

        // Create the MonthlyReport
        MonthlyReportDTO monthlyReportDTO = monthlyReportMapper.toDto(monthlyReport);
        restMonthlyReportMockMvc.perform(post("/api/monthly-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyReportDTO)))
            .andExpect(status().isCreated());

        // Validate the MonthlyReport in the database
        List<MonthlyReport> monthlyReportList = monthlyReportRepository.findAll();
        assertThat(monthlyReportList).hasSize(databaseSizeBeforeCreate + 1);
        MonthlyReport testMonthlyReport = monthlyReportList.get(monthlyReportList.size() - 1);
        assertThat(testMonthlyReport.getMonthName()).isEqualTo(DEFAULT_MONTH_NAME);
        assertThat(testMonthlyReport.getReport()).isEqualTo(DEFAULT_REPORT);
        assertThat(testMonthlyReport.getReportTime()).isEqualTo(DEFAULT_REPORT_TIME);
        assertThat(testMonthlyReport.getReportDate()).isEqualTo(DEFAULT_REPORT_DATE);
        assertThat(testMonthlyReport.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testMonthlyReport.getSurveyDeptCount()).isEqualTo(DEFAULT_SURVEY_DEPT_COUNT);
        assertThat(testMonthlyReport.getSurveyCount()).isEqualTo(DEFAULT_SURVEY_COUNT);
        assertThat(testMonthlyReport.getSurveyTargetCountJson()).isEqualTo(DEFAULT_SURVEY_TARGET_COUNT_JSON);
        assertThat(testMonthlyReport.getSurveyTargetCountTotal()).isEqualTo(DEFAULT_SURVEY_TARGET_COUNT_TOTAL);
        assertThat(testMonthlyReport.getSurveyTypeCountJson()).isEqualTo(DEFAULT_SURVEY_TYPE_COUNT_JSON);
        assertThat(testMonthlyReport.getSurveyTypeCountTotal()).isEqualTo(DEFAULT_SURVEY_TYPE_COUNT_TOTAL);
        assertThat(testMonthlyReport.getQuestionCcountJson()).isEqualTo(DEFAULT_QUESTION_CCOUNT_JSON);
        assertThat(testMonthlyReport.getQuestionCountTotal()).isEqualTo(DEFAULT_QUESTION_COUNT_TOTAL);
        assertThat(testMonthlyReport.getQuestionClosedCountJson()).isEqualTo(DEFAULT_QUESTION_CLOSED_COUNT_JSON);
        assertThat(testMonthlyReport.getQuestionClosedCountTota()).isEqualTo(DEFAULT_QUESTION_CLOSED_COUNT_TOTA);
        assertThat(testMonthlyReport.getProposalCountJson()).isEqualTo(DEFAULT_PROPOSAL_COUNT_JSON);
        assertThat(testMonthlyReport.getProposalCountTotal()).isEqualTo(DEFAULT_PROPOSAL_COUNT_TOTAL);
    }

    @Test
    @Transactional
    public void createMonthlyReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = monthlyReportRepository.findAll().size();

        // Create the MonthlyReport with an existing ID
        monthlyReport.setId(1L);
        MonthlyReportDTO monthlyReportDTO = monthlyReportMapper.toDto(monthlyReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMonthlyReportMockMvc.perform(post("/api/monthly-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MonthlyReport in the database
        List<MonthlyReport> monthlyReportList = monthlyReportRepository.findAll();
        assertThat(monthlyReportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMonthlyReports() throws Exception {
        // Initialize the database
        monthlyReportRepository.saveAndFlush(monthlyReport);

        // Get all the monthlyReportList
        restMonthlyReportMockMvc.perform(get("/api/monthly-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(monthlyReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].monthName").value(hasItem(DEFAULT_MONTH_NAME.toString())))
            .andExpect(jsonPath("$.[*].report").value(hasItem(DEFAULT_REPORT.toString())))
            .andExpect(jsonPath("$.[*].reportTime").value(hasItem(DEFAULT_REPORT_TIME.toString())))
            .andExpect(jsonPath("$.[*].reportDate").value(hasItem(DEFAULT_REPORT_DATE.toString())))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT.toString())))
            .andExpect(jsonPath("$.[*].surveyDeptCount").value(hasItem(DEFAULT_SURVEY_DEPT_COUNT.toString())))
            .andExpect(jsonPath("$.[*].surveyCount").value(hasItem(DEFAULT_SURVEY_COUNT.toString())))
            .andExpect(jsonPath("$.[*].surveyTargetCountJson").value(hasItem(DEFAULT_SURVEY_TARGET_COUNT_JSON.toString())))
            .andExpect(jsonPath("$.[*].surveyTargetCountTotal").value(hasItem(DEFAULT_SURVEY_TARGET_COUNT_TOTAL.toString())))
            .andExpect(jsonPath("$.[*].surveyTypeCountJson").value(hasItem(DEFAULT_SURVEY_TYPE_COUNT_JSON.toString())))
            .andExpect(jsonPath("$.[*].surveyTypeCountTotal").value(hasItem(DEFAULT_SURVEY_TYPE_COUNT_TOTAL.toString())))
            .andExpect(jsonPath("$.[*].questionCcountJson").value(hasItem(DEFAULT_QUESTION_CCOUNT_JSON.toString())))
            .andExpect(jsonPath("$.[*].questionCountTotal").value(hasItem(DEFAULT_QUESTION_COUNT_TOTAL.toString())))
            .andExpect(jsonPath("$.[*].questionClosedCountJson").value(hasItem(DEFAULT_QUESTION_CLOSED_COUNT_JSON.toString())))
            .andExpect(jsonPath("$.[*].questionClosedCountTota").value(hasItem(DEFAULT_QUESTION_CLOSED_COUNT_TOTA.toString())))
            .andExpect(jsonPath("$.[*].proposalCountJson").value(hasItem(DEFAULT_PROPOSAL_COUNT_JSON.toString())))
            .andExpect(jsonPath("$.[*].proposalCountTotal").value(hasItem(DEFAULT_PROPOSAL_COUNT_TOTAL.toString())));
    }

    @Test
    @Transactional
    public void getMonthlyReport() throws Exception {
        // Initialize the database
        monthlyReportRepository.saveAndFlush(monthlyReport);

        // Get the monthlyReport
        restMonthlyReportMockMvc.perform(get("/api/monthly-reports/{id}", monthlyReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(monthlyReport.getId().intValue()))
            .andExpect(jsonPath("$.monthName").value(DEFAULT_MONTH_NAME.toString()))
            .andExpect(jsonPath("$.report").value(DEFAULT_REPORT.toString()))
            .andExpect(jsonPath("$.reportTime").value(DEFAULT_REPORT_TIME.toString()))
            .andExpect(jsonPath("$.reportDate").value(DEFAULT_REPORT_DATE.toString()))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT.toString()))
            .andExpect(jsonPath("$.surveyDeptCount").value(DEFAULT_SURVEY_DEPT_COUNT.toString()))
            .andExpect(jsonPath("$.surveyCount").value(DEFAULT_SURVEY_COUNT.toString()))
            .andExpect(jsonPath("$.surveyTargetCountJson").value(DEFAULT_SURVEY_TARGET_COUNT_JSON.toString()))
            .andExpect(jsonPath("$.surveyTargetCountTotal").value(DEFAULT_SURVEY_TARGET_COUNT_TOTAL.toString()))
            .andExpect(jsonPath("$.surveyTypeCountJson").value(DEFAULT_SURVEY_TYPE_COUNT_JSON.toString()))
            .andExpect(jsonPath("$.surveyTypeCountTotal").value(DEFAULT_SURVEY_TYPE_COUNT_TOTAL.toString()))
            .andExpect(jsonPath("$.questionCcountJson").value(DEFAULT_QUESTION_CCOUNT_JSON.toString()))
            .andExpect(jsonPath("$.questionCountTotal").value(DEFAULT_QUESTION_COUNT_TOTAL.toString()))
            .andExpect(jsonPath("$.questionClosedCountJson").value(DEFAULT_QUESTION_CLOSED_COUNT_JSON.toString()))
            .andExpect(jsonPath("$.questionClosedCountTota").value(DEFAULT_QUESTION_CLOSED_COUNT_TOTA.toString()))
            .andExpect(jsonPath("$.proposalCountJson").value(DEFAULT_PROPOSAL_COUNT_JSON.toString()))
            .andExpect(jsonPath("$.proposalCountTotal").value(DEFAULT_PROPOSAL_COUNT_TOTAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMonthlyReport() throws Exception {
        // Get the monthlyReport
        restMonthlyReportMockMvc.perform(get("/api/monthly-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMonthlyReport() throws Exception {
        // Initialize the database
        monthlyReportRepository.saveAndFlush(monthlyReport);
        int databaseSizeBeforeUpdate = monthlyReportRepository.findAll().size();

        // Update the monthlyReport
        MonthlyReport updatedMonthlyReport = monthlyReportRepository.findOne(monthlyReport.getId());
        // Disconnect from session so that the updates on updatedMonthlyReport are not directly saved in db
        em.detach(updatedMonthlyReport);
        updatedMonthlyReport
            .monthName(UPDATED_MONTH_NAME)
            .report(UPDATED_REPORT)
            .reportTime(UPDATED_REPORT_TIME)
            .reportDate(UPDATED_REPORT_DATE)
            .count(UPDATED_COUNT)
            .surveyDeptCount(UPDATED_SURVEY_DEPT_COUNT)
            .surveyCount(UPDATED_SURVEY_COUNT)
            .surveyTargetCountJson(UPDATED_SURVEY_TARGET_COUNT_JSON)
            .surveyTargetCountTotal(UPDATED_SURVEY_TARGET_COUNT_TOTAL)
            .surveyTypeCountJson(UPDATED_SURVEY_TYPE_COUNT_JSON)
            .surveyTypeCountTotal(UPDATED_SURVEY_TYPE_COUNT_TOTAL)
            .questionCcountJson(UPDATED_QUESTION_CCOUNT_JSON)
            .questionCountTotal(UPDATED_QUESTION_COUNT_TOTAL)
            .questionClosedCountJson(UPDATED_QUESTION_CLOSED_COUNT_JSON)
            .questionClosedCountTota(UPDATED_QUESTION_CLOSED_COUNT_TOTA)
            .proposalCountJson(UPDATED_PROPOSAL_COUNT_JSON)
            .proposalCountTotal(UPDATED_PROPOSAL_COUNT_TOTAL);
        MonthlyReportDTO monthlyReportDTO = monthlyReportMapper.toDto(updatedMonthlyReport);

        restMonthlyReportMockMvc.perform(put("/api/monthly-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyReportDTO)))
            .andExpect(status().isOk());

        // Validate the MonthlyReport in the database
        List<MonthlyReport> monthlyReportList = monthlyReportRepository.findAll();
        assertThat(monthlyReportList).hasSize(databaseSizeBeforeUpdate);
        MonthlyReport testMonthlyReport = monthlyReportList.get(monthlyReportList.size() - 1);
        assertThat(testMonthlyReport.getMonthName()).isEqualTo(UPDATED_MONTH_NAME);
        assertThat(testMonthlyReport.getReport()).isEqualTo(UPDATED_REPORT);
        assertThat(testMonthlyReport.getReportTime()).isEqualTo(UPDATED_REPORT_TIME);
        assertThat(testMonthlyReport.getReportDate()).isEqualTo(UPDATED_REPORT_DATE);
        assertThat(testMonthlyReport.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testMonthlyReport.getSurveyDeptCount()).isEqualTo(UPDATED_SURVEY_DEPT_COUNT);
        assertThat(testMonthlyReport.getSurveyCount()).isEqualTo(UPDATED_SURVEY_COUNT);
        assertThat(testMonthlyReport.getSurveyTargetCountJson()).isEqualTo(UPDATED_SURVEY_TARGET_COUNT_JSON);
        assertThat(testMonthlyReport.getSurveyTargetCountTotal()).isEqualTo(UPDATED_SURVEY_TARGET_COUNT_TOTAL);
        assertThat(testMonthlyReport.getSurveyTypeCountJson()).isEqualTo(UPDATED_SURVEY_TYPE_COUNT_JSON);
        assertThat(testMonthlyReport.getSurveyTypeCountTotal()).isEqualTo(UPDATED_SURVEY_TYPE_COUNT_TOTAL);
        assertThat(testMonthlyReport.getQuestionCcountJson()).isEqualTo(UPDATED_QUESTION_CCOUNT_JSON);
        assertThat(testMonthlyReport.getQuestionCountTotal()).isEqualTo(UPDATED_QUESTION_COUNT_TOTAL);
        assertThat(testMonthlyReport.getQuestionClosedCountJson()).isEqualTo(UPDATED_QUESTION_CLOSED_COUNT_JSON);
        assertThat(testMonthlyReport.getQuestionClosedCountTota()).isEqualTo(UPDATED_QUESTION_CLOSED_COUNT_TOTA);
        assertThat(testMonthlyReport.getProposalCountJson()).isEqualTo(UPDATED_PROPOSAL_COUNT_JSON);
        assertThat(testMonthlyReport.getProposalCountTotal()).isEqualTo(UPDATED_PROPOSAL_COUNT_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingMonthlyReport() throws Exception {
        int databaseSizeBeforeUpdate = monthlyReportRepository.findAll().size();

        // Create the MonthlyReport
        MonthlyReportDTO monthlyReportDTO = monthlyReportMapper.toDto(monthlyReport);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMonthlyReportMockMvc.perform(put("/api/monthly-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyReportDTO)))
            .andExpect(status().isCreated());

        // Validate the MonthlyReport in the database
        List<MonthlyReport> monthlyReportList = monthlyReportRepository.findAll();
        assertThat(monthlyReportList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMonthlyReport() throws Exception {
        // Initialize the database
        monthlyReportRepository.saveAndFlush(monthlyReport);
        int databaseSizeBeforeDelete = monthlyReportRepository.findAll().size();

        // Get the monthlyReport
        restMonthlyReportMockMvc.perform(delete("/api/monthly-reports/{id}", monthlyReport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MonthlyReport> monthlyReportList = monthlyReportRepository.findAll();
        assertThat(monthlyReportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MonthlyReport.class);
        MonthlyReport monthlyReport1 = new MonthlyReport();
        monthlyReport1.setId(1L);
        MonthlyReport monthlyReport2 = new MonthlyReport();
        monthlyReport2.setId(monthlyReport1.getId());
        assertThat(monthlyReport1).isEqualTo(monthlyReport2);
        monthlyReport2.setId(2L);
        assertThat(monthlyReport1).isNotEqualTo(monthlyReport2);
        monthlyReport1.setId(null);
        assertThat(monthlyReport1).isNotEqualTo(monthlyReport2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MonthlyReportDTO.class);
        MonthlyReportDTO monthlyReportDTO1 = new MonthlyReportDTO();
        monthlyReportDTO1.setId(1L);
        MonthlyReportDTO monthlyReportDTO2 = new MonthlyReportDTO();
        assertThat(monthlyReportDTO1).isNotEqualTo(monthlyReportDTO2);
        monthlyReportDTO2.setId(monthlyReportDTO1.getId());
        assertThat(monthlyReportDTO1).isEqualTo(monthlyReportDTO2);
        monthlyReportDTO2.setId(2L);
        assertThat(monthlyReportDTO1).isNotEqualTo(monthlyReportDTO2);
        monthlyReportDTO1.setId(null);
        assertThat(monthlyReportDTO1).isNotEqualTo(monthlyReportDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(monthlyReportMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(monthlyReportMapper.fromId(null)).isNull();
    }
}
