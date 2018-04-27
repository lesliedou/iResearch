package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.SurveyQuestionFlow;
import com.research.repository.SurveyQuestionFlowRepository;
import com.research.service.SurveyQuestionFlowService;
import com.research.service.dto.SurveyQuestionFlowDTO;
import com.research.service.mapper.SurveyQuestionFlowMapper;
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
 * Test class for the SurveyQuestionFlowResource REST controller.
 *
 * @see SurveyQuestionFlowResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class SurveyQuestionFlowResourceIntTest {

    private static final String DEFAULT_CONFIRM = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRM = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private SurveyQuestionFlowRepository surveyQuestionFlowRepository;

    @Autowired
    private SurveyQuestionFlowMapper surveyQuestionFlowMapper;

    @Autowired
    private SurveyQuestionFlowService surveyQuestionFlowService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSurveyQuestionFlowMockMvc;

    private SurveyQuestionFlow surveyQuestionFlow;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SurveyQuestionFlowResource surveyQuestionFlowResource = new SurveyQuestionFlowResource(surveyQuestionFlowService);
        this.restSurveyQuestionFlowMockMvc = MockMvcBuilders.standaloneSetup(surveyQuestionFlowResource)
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
    public static SurveyQuestionFlow createEntity(EntityManager em) {
        SurveyQuestionFlow surveyQuestionFlow = new SurveyQuestionFlow()
            .confirm(DEFAULT_CONFIRM)
            .remarks(DEFAULT_REMARKS);
        return surveyQuestionFlow;
    }

    @Before
    public void initTest() {
        surveyQuestionFlow = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurveyQuestionFlow() throws Exception {
        int databaseSizeBeforeCreate = surveyQuestionFlowRepository.findAll().size();

        // Create the SurveyQuestionFlow
        SurveyQuestionFlowDTO surveyQuestionFlowDTO = surveyQuestionFlowMapper.toDto(surveyQuestionFlow);
        restSurveyQuestionFlowMockMvc.perform(post("/api/survey-question-flows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionFlowDTO)))
            .andExpect(status().isCreated());

        // Validate the SurveyQuestionFlow in the database
        List<SurveyQuestionFlow> surveyQuestionFlowList = surveyQuestionFlowRepository.findAll();
        assertThat(surveyQuestionFlowList).hasSize(databaseSizeBeforeCreate + 1);
        SurveyQuestionFlow testSurveyQuestionFlow = surveyQuestionFlowList.get(surveyQuestionFlowList.size() - 1);
        assertThat(testSurveyQuestionFlow.getConfirm()).isEqualTo(DEFAULT_CONFIRM);
        assertThat(testSurveyQuestionFlow.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createSurveyQuestionFlowWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surveyQuestionFlowRepository.findAll().size();

        // Create the SurveyQuestionFlow with an existing ID
        surveyQuestionFlow.setId(1L);
        SurveyQuestionFlowDTO surveyQuestionFlowDTO = surveyQuestionFlowMapper.toDto(surveyQuestionFlow);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurveyQuestionFlowMockMvc.perform(post("/api/survey-question-flows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionFlowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SurveyQuestionFlow in the database
        List<SurveyQuestionFlow> surveyQuestionFlowList = surveyQuestionFlowRepository.findAll();
        assertThat(surveyQuestionFlowList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSurveyQuestionFlows() throws Exception {
        // Initialize the database
        surveyQuestionFlowRepository.saveAndFlush(surveyQuestionFlow);

        // Get all the surveyQuestionFlowList
        restSurveyQuestionFlowMockMvc.perform(get("/api/survey-question-flows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surveyQuestionFlow.getId().intValue())))
            .andExpect(jsonPath("$.[*].confirm").value(hasItem(DEFAULT_CONFIRM.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }

    @Test
    @Transactional
    public void getSurveyQuestionFlow() throws Exception {
        // Initialize the database
        surveyQuestionFlowRepository.saveAndFlush(surveyQuestionFlow);

        // Get the surveyQuestionFlow
        restSurveyQuestionFlowMockMvc.perform(get("/api/survey-question-flows/{id}", surveyQuestionFlow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(surveyQuestionFlow.getId().intValue()))
            .andExpect(jsonPath("$.confirm").value(DEFAULT_CONFIRM.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSurveyQuestionFlow() throws Exception {
        // Get the surveyQuestionFlow
        restSurveyQuestionFlowMockMvc.perform(get("/api/survey-question-flows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurveyQuestionFlow() throws Exception {
        // Initialize the database
        surveyQuestionFlowRepository.saveAndFlush(surveyQuestionFlow);
        int databaseSizeBeforeUpdate = surveyQuestionFlowRepository.findAll().size();

        // Update the surveyQuestionFlow
        SurveyQuestionFlow updatedSurveyQuestionFlow = surveyQuestionFlowRepository.findOne(surveyQuestionFlow.getId());
        // Disconnect from session so that the updates on updatedSurveyQuestionFlow are not directly saved in db
        em.detach(updatedSurveyQuestionFlow);
        updatedSurveyQuestionFlow
            .confirm(UPDATED_CONFIRM)
            .remarks(UPDATED_REMARKS);
        SurveyQuestionFlowDTO surveyQuestionFlowDTO = surveyQuestionFlowMapper.toDto(updatedSurveyQuestionFlow);

        restSurveyQuestionFlowMockMvc.perform(put("/api/survey-question-flows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionFlowDTO)))
            .andExpect(status().isOk());

        // Validate the SurveyQuestionFlow in the database
        List<SurveyQuestionFlow> surveyQuestionFlowList = surveyQuestionFlowRepository.findAll();
        assertThat(surveyQuestionFlowList).hasSize(databaseSizeBeforeUpdate);
        SurveyQuestionFlow testSurveyQuestionFlow = surveyQuestionFlowList.get(surveyQuestionFlowList.size() - 1);
        assertThat(testSurveyQuestionFlow.getConfirm()).isEqualTo(UPDATED_CONFIRM);
        assertThat(testSurveyQuestionFlow.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingSurveyQuestionFlow() throws Exception {
        int databaseSizeBeforeUpdate = surveyQuestionFlowRepository.findAll().size();

        // Create the SurveyQuestionFlow
        SurveyQuestionFlowDTO surveyQuestionFlowDTO = surveyQuestionFlowMapper.toDto(surveyQuestionFlow);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSurveyQuestionFlowMockMvc.perform(put("/api/survey-question-flows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(surveyQuestionFlowDTO)))
            .andExpect(status().isCreated());

        // Validate the SurveyQuestionFlow in the database
        List<SurveyQuestionFlow> surveyQuestionFlowList = surveyQuestionFlowRepository.findAll();
        assertThat(surveyQuestionFlowList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSurveyQuestionFlow() throws Exception {
        // Initialize the database
        surveyQuestionFlowRepository.saveAndFlush(surveyQuestionFlow);
        int databaseSizeBeforeDelete = surveyQuestionFlowRepository.findAll().size();

        // Get the surveyQuestionFlow
        restSurveyQuestionFlowMockMvc.perform(delete("/api/survey-question-flows/{id}", surveyQuestionFlow.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SurveyQuestionFlow> surveyQuestionFlowList = surveyQuestionFlowRepository.findAll();
        assertThat(surveyQuestionFlowList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyQuestionFlow.class);
        SurveyQuestionFlow surveyQuestionFlow1 = new SurveyQuestionFlow();
        surveyQuestionFlow1.setId(1L);
        SurveyQuestionFlow surveyQuestionFlow2 = new SurveyQuestionFlow();
        surveyQuestionFlow2.setId(surveyQuestionFlow1.getId());
        assertThat(surveyQuestionFlow1).isEqualTo(surveyQuestionFlow2);
        surveyQuestionFlow2.setId(2L);
        assertThat(surveyQuestionFlow1).isNotEqualTo(surveyQuestionFlow2);
        surveyQuestionFlow1.setId(null);
        assertThat(surveyQuestionFlow1).isNotEqualTo(surveyQuestionFlow2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveyQuestionFlowDTO.class);
        SurveyQuestionFlowDTO surveyQuestionFlowDTO1 = new SurveyQuestionFlowDTO();
        surveyQuestionFlowDTO1.setId(1L);
        SurveyQuestionFlowDTO surveyQuestionFlowDTO2 = new SurveyQuestionFlowDTO();
        assertThat(surveyQuestionFlowDTO1).isNotEqualTo(surveyQuestionFlowDTO2);
        surveyQuestionFlowDTO2.setId(surveyQuestionFlowDTO1.getId());
        assertThat(surveyQuestionFlowDTO1).isEqualTo(surveyQuestionFlowDTO2);
        surveyQuestionFlowDTO2.setId(2L);
        assertThat(surveyQuestionFlowDTO1).isNotEqualTo(surveyQuestionFlowDTO2);
        surveyQuestionFlowDTO1.setId(null);
        assertThat(surveyQuestionFlowDTO1).isNotEqualTo(surveyQuestionFlowDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(surveyQuestionFlowMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(surveyQuestionFlowMapper.fromId(null)).isNull();
    }
}
