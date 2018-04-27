package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.Survey;
import com.research.repository.SurveyRepository;
import com.research.service.SurveyService;
import com.research.service.dto.SurveyDTO;
import com.research.service.mapper.SurveyMapper;
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
 * Test class for the SurveyResource REST controller.
 *
 * @see SurveyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class SurveyResourceIntTest {

    private static final String DEFAULT_SURVEY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_TYPE_README = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_TYPE_README = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_CONTACT_INFO = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_CONTACT_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_ID = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_CONTACT_INFO = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_CONTACT_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_DATE = "BBBBBBBBBB";

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSurveyMockMvc;

    private Survey survey;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SurveyResource surveyResource = new SurveyResource(surveyService);
        this.restSurveyMockMvc = MockMvcBuilders.standaloneSetup(surveyResource)
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
    public static Survey createEntity(EntityManager em) {
        Survey survey = new Survey()
            .surveyType(DEFAULT_SURVEY_TYPE)
            .targetTypeReadme(DEFAULT_TARGET_TYPE_README)
            .targetType(DEFAULT_TARGET_TYPE)
            .targetContactInfo(DEFAULT_TARGET_CONTACT_INFO)
            .targetContactPerson(DEFAULT_TARGET_CONTACT_PERSON)
            .targetName(DEFAULT_TARGET_NAME)
            .targetId(DEFAULT_TARGET_ID)
            .deptContactInfo(DEFAULT_DEPT_CONTACT_INFO)
            .deptContactPerson(DEFAULT_DEPT_CONTACT_PERSON)
            .surveyDate(DEFAULT_SURVEY_DATE);
        return survey;
    }

    @Before
    public void initTest() {
        survey = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurvey() throws Exception {
        int databaseSizeBeforeCreate = surveyRepository.findAll().size();

        // Create the Survey
        SurveyDTO surveyDTO = surveyMapper.toDto(survey);
        restSurveyMockMvc.perform(post("/api/surveys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyDTO)))
            .andExpect(status().isCreated());

        // Validate the Survey in the database
        List<Survey> surveyList = surveyRepository.findAll();
        assertThat(surveyList).hasSize(databaseSizeBeforeCreate + 1);
        Survey testSurvey = surveyList.get(surveyList.size() - 1);
        assertThat(testSurvey.getSurveyType()).isEqualTo(DEFAULT_SURVEY_TYPE);
        assertThat(testSurvey.getTargetTypeReadme()).isEqualTo(DEFAULT_TARGET_TYPE_README);
        assertThat(testSurvey.getTargetType()).isEqualTo(DEFAULT_TARGET_TYPE);
        assertThat(testSurvey.getTargetContactInfo()).isEqualTo(DEFAULT_TARGET_CONTACT_INFO);
        assertThat(testSurvey.getTargetContactPerson()).isEqualTo(DEFAULT_TARGET_CONTACT_PERSON);
        assertThat(testSurvey.getTargetName()).isEqualTo(DEFAULT_TARGET_NAME);
        assertThat(testSurvey.getTargetId()).isEqualTo(DEFAULT_TARGET_ID);
        assertThat(testSurvey.getDeptContactInfo()).isEqualTo(DEFAULT_DEPT_CONTACT_INFO);
        assertThat(testSurvey.getDeptContactPerson()).isEqualTo(DEFAULT_DEPT_CONTACT_PERSON);
        assertThat(testSurvey.getSurveyDate()).isEqualTo(DEFAULT_SURVEY_DATE);
    }

    @Test
    @Transactional
    public void createSurveyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surveyRepository.findAll().size();

        // Create the Survey with an existing ID
        survey.setId(1L);
        SurveyDTO surveyDTO = surveyMapper.toDto(survey);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurveyMockMvc.perform(post("/api/surveys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Survey in the database
        List<Survey> surveyList = surveyRepository.findAll();
        assertThat(surveyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSurveys() throws Exception {
        // Initialize the database
        surveyRepository.saveAndFlush(survey);

        // Get all the surveyList
        restSurveyMockMvc.perform(get("/api/surveys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(survey.getId().intValue())))
            .andExpect(jsonPath("$.[*].surveyType").value(hasItem(DEFAULT_SURVEY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].targetTypeReadme").value(hasItem(DEFAULT_TARGET_TYPE_README.toString())))
            .andExpect(jsonPath("$.[*].targetType").value(hasItem(DEFAULT_TARGET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].targetContactInfo").value(hasItem(DEFAULT_TARGET_CONTACT_INFO.toString())))
            .andExpect(jsonPath("$.[*].targetContactPerson").value(hasItem(DEFAULT_TARGET_CONTACT_PERSON.toString())))
            .andExpect(jsonPath("$.[*].targetName").value(hasItem(DEFAULT_TARGET_NAME.toString())))
            .andExpect(jsonPath("$.[*].targetId").value(hasItem(DEFAULT_TARGET_ID.toString())))
            .andExpect(jsonPath("$.[*].deptContactInfo").value(hasItem(DEFAULT_DEPT_CONTACT_INFO.toString())))
            .andExpect(jsonPath("$.[*].deptContactPerson").value(hasItem(DEFAULT_DEPT_CONTACT_PERSON.toString())))
            .andExpect(jsonPath("$.[*].surveyDate").value(hasItem(DEFAULT_SURVEY_DATE.toString())));
    }

    @Test
    @Transactional
    public void getSurvey() throws Exception {
        // Initialize the database
        surveyRepository.saveAndFlush(survey);

        // Get the survey
        restSurveyMockMvc.perform(get("/api/surveys/{id}", survey.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(survey.getId().intValue()))
            .andExpect(jsonPath("$.surveyType").value(DEFAULT_SURVEY_TYPE.toString()))
            .andExpect(jsonPath("$.targetTypeReadme").value(DEFAULT_TARGET_TYPE_README.toString()))
            .andExpect(jsonPath("$.targetType").value(DEFAULT_TARGET_TYPE.toString()))
            .andExpect(jsonPath("$.targetContactInfo").value(DEFAULT_TARGET_CONTACT_INFO.toString()))
            .andExpect(jsonPath("$.targetContactPerson").value(DEFAULT_TARGET_CONTACT_PERSON.toString()))
            .andExpect(jsonPath("$.targetName").value(DEFAULT_TARGET_NAME.toString()))
            .andExpect(jsonPath("$.targetId").value(DEFAULT_TARGET_ID.toString()))
            .andExpect(jsonPath("$.deptContactInfo").value(DEFAULT_DEPT_CONTACT_INFO.toString()))
            .andExpect(jsonPath("$.deptContactPerson").value(DEFAULT_DEPT_CONTACT_PERSON.toString()))
            .andExpect(jsonPath("$.surveyDate").value(DEFAULT_SURVEY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSurvey() throws Exception {
        // Get the survey
        restSurveyMockMvc.perform(get("/api/surveys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurvey() throws Exception {
        // Initialize the database
        surveyRepository.saveAndFlush(survey);
        int databaseSizeBeforeUpdate = surveyRepository.findAll().size();

        // Update the survey
        Survey updatedSurvey = surveyRepository.findOne(survey.getId());
        // Disconnect from session so that the updates on updatedSurvey are not directly saved in db
        em.detach(updatedSurvey);
        updatedSurvey
            .surveyType(UPDATED_SURVEY_TYPE)
            .targetTypeReadme(UPDATED_TARGET_TYPE_README)
            .targetType(UPDATED_TARGET_TYPE)
            .targetContactInfo(UPDATED_TARGET_CONTACT_INFO)
            .targetContactPerson(UPDATED_TARGET_CONTACT_PERSON)
            .targetName(UPDATED_TARGET_NAME)
            .targetId(UPDATED_TARGET_ID)
            .deptContactInfo(UPDATED_DEPT_CONTACT_INFO)
            .deptContactPerson(UPDATED_DEPT_CONTACT_PERSON)
            .surveyDate(UPDATED_SURVEY_DATE);
        SurveyDTO surveyDTO = surveyMapper.toDto(updatedSurvey);

        restSurveyMockMvc.perform(put("/api/surveys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyDTO)))
            .andExpect(status().isOk());

        // Validate the Survey in the database
        List<Survey> surveyList = surveyRepository.findAll();
        assertThat(surveyList).hasSize(databaseSizeBeforeUpdate);
        Survey testSurvey = surveyList.get(surveyList.size() - 1);
        assertThat(testSurvey.getSurveyType()).isEqualTo(UPDATED_SURVEY_TYPE);
        assertThat(testSurvey.getTargetTypeReadme()).isEqualTo(UPDATED_TARGET_TYPE_README);
        assertThat(testSurvey.getTargetType()).isEqualTo(UPDATED_TARGET_TYPE);
        assertThat(testSurvey.getTargetContactInfo()).isEqualTo(UPDATED_TARGET_CONTACT_INFO);
        assertThat(testSurvey.getTargetContactPerson()).isEqualTo(UPDATED_TARGET_CONTACT_PERSON);
        assertThat(testSurvey.getTargetName()).isEqualTo(UPDATED_TARGET_NAME);
        assertThat(testSurvey.getTargetId()).isEqualTo(UPDATED_TARGET_ID);
        assertThat(testSurvey.getDeptContactInfo()).isEqualTo(UPDATED_DEPT_CONTACT_INFO);
        assertThat(testSurvey.getDeptContactPerson()).isEqualTo(UPDATED_DEPT_CONTACT_PERSON);
        assertThat(testSurvey.getSurveyDate()).isEqualTo(UPDATED_SURVEY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSurvey() throws Exception {
        int databaseSizeBeforeUpdate = surveyRepository.findAll().size();

        // Create the Survey
        SurveyDTO surveyDTO = surveyMapper.toDto(survey);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSurveyMockMvc.perform(put("/api/surveys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyDTO)))
            .andExpect(status().isCreated());

        // Validate the Survey in the database
        List<Survey> surveyList = surveyRepository.findAll();
        assertThat(surveyList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSurvey() throws Exception {
        // Initialize the database
        surveyRepository.saveAndFlush(survey);
        int databaseSizeBeforeDelete = surveyRepository.findAll().size();

        // Get the survey
        restSurveyMockMvc.perform(delete("/api/surveys/{id}", survey.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Survey> surveyList = surveyRepository.findAll();
        assertThat(surveyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Survey.class);
        Survey survey1 = new Survey();
        survey1.setId(1L);
        Survey survey2 = new Survey();
        survey2.setId(survey1.getId());
        assertThat(survey1).isEqualTo(survey2);
        survey2.setId(2L);
        assertThat(survey1).isNotEqualTo(survey2);
        survey1.setId(null);
        assertThat(survey1).isNotEqualTo(survey2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyDTO.class);
        SurveyDTO surveyDTO1 = new SurveyDTO();
        surveyDTO1.setId(1L);
        SurveyDTO surveyDTO2 = new SurveyDTO();
        assertThat(surveyDTO1).isNotEqualTo(surveyDTO2);
        surveyDTO2.setId(surveyDTO1.getId());
        assertThat(surveyDTO1).isEqualTo(surveyDTO2);
        surveyDTO2.setId(2L);
        assertThat(surveyDTO1).isNotEqualTo(surveyDTO2);
        surveyDTO1.setId(null);
        assertThat(surveyDTO1).isNotEqualTo(surveyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(surveyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(surveyMapper.fromId(null)).isNull();
    }
}
