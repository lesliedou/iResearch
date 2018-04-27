package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.Onsite;
import com.research.repository.OnsiteRepository;
import com.research.service.OnsiteService;
import com.research.service.dto.OnsiteDTO;
import com.research.service.mapper.OnsiteMapper;
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
 * Test class for the OnsiteResource REST controller.
 *
 * @see OnsiteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class OnsiteResourceIntTest {

    private static final String DEFAULT_REPORT = "AAAAAAAAAA";
    private static final String UPDATED_REPORT = "BBBBBBBBBB";

    private static final String DEFAULT_ONSITE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_ONSITE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOMPANY = "AAAAAAAAAA";
    private static final String UPDATED_ACCOMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_ONSITE_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_ONSITE_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_ONSITE_INFO = "AAAAAAAAAA";
    private static final String UPDATED_ONSITE_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_ID = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SURVEY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_INFO_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INFO_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ADVICE_DEPT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ADVICE_DEPT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ADVICE_READ_TIME = "AAAAAAAAAA";
    private static final String UPDATED_ADVICE_READ_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_ADVICE_READ_UUID = "AAAAAAAAAA";
    private static final String UPDATED_ADVICE_READ_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_ADVICE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ADVICE_STATUS = "BBBBBBBBBB";

    @Autowired
    private OnsiteRepository onsiteRepository;

    @Autowired
    private OnsiteMapper onsiteMapper;

    @Autowired
    private OnsiteService onsiteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOnsiteMockMvc;

    private Onsite onsite;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OnsiteResource onsiteResource = new OnsiteResource(onsiteService);
        this.restOnsiteMockMvc = MockMvcBuilders.standaloneSetup(onsiteResource)
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
    public static Onsite createEntity(EntityManager em) {
        Onsite onsite = new Onsite()
            .report(DEFAULT_REPORT)
            .onsiteTime(DEFAULT_ONSITE_TIME)
            .accompany(DEFAULT_ACCOMPANY)
            .onsiteAddr(DEFAULT_ONSITE_ADDR)
            .onsiteInfo(DEFAULT_ONSITE_INFO)
            .targetId(DEFAULT_TARGET_ID)
            .targetName(DEFAULT_TARGET_NAME)
            .surveyType(DEFAULT_SURVEY_TYPE)
            .infoType(DEFAULT_INFO_TYPE)
            .adviceDeptId(DEFAULT_ADVICE_DEPT_ID)
            .adviceReadTime(DEFAULT_ADVICE_READ_TIME)
            .adviceReadUuid(DEFAULT_ADVICE_READ_UUID)
            .adviceStatus(DEFAULT_ADVICE_STATUS);
        return onsite;
    }

    @Before
    public void initTest() {
        onsite = createEntity(em);
    }

    @Test
    @Transactional
    public void createOnsite() throws Exception {
        int databaseSizeBeforeCreate = onsiteRepository.findAll().size();

        // Create the Onsite
        OnsiteDTO onsiteDTO = onsiteMapper.toDto(onsite);
        restOnsiteMockMvc.perform(post("/api/onsites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onsiteDTO)))
            .andExpect(status().isCreated());

        // Validate the Onsite in the database
        List<Onsite> onsiteList = onsiteRepository.findAll();
        assertThat(onsiteList).hasSize(databaseSizeBeforeCreate + 1);
        Onsite testOnsite = onsiteList.get(onsiteList.size() - 1);
        assertThat(testOnsite.getReport()).isEqualTo(DEFAULT_REPORT);
        assertThat(testOnsite.getOnsiteTime()).isEqualTo(DEFAULT_ONSITE_TIME);
        assertThat(testOnsite.getAccompany()).isEqualTo(DEFAULT_ACCOMPANY);
        assertThat(testOnsite.getOnsiteAddr()).isEqualTo(DEFAULT_ONSITE_ADDR);
        assertThat(testOnsite.getOnsiteInfo()).isEqualTo(DEFAULT_ONSITE_INFO);
        assertThat(testOnsite.getTargetId()).isEqualTo(DEFAULT_TARGET_ID);
        assertThat(testOnsite.getTargetName()).isEqualTo(DEFAULT_TARGET_NAME);
        assertThat(testOnsite.getSurveyType()).isEqualTo(DEFAULT_SURVEY_TYPE);
        assertThat(testOnsite.getInfoType()).isEqualTo(DEFAULT_INFO_TYPE);
        assertThat(testOnsite.getAdviceDeptId()).isEqualTo(DEFAULT_ADVICE_DEPT_ID);
        assertThat(testOnsite.getAdviceReadTime()).isEqualTo(DEFAULT_ADVICE_READ_TIME);
        assertThat(testOnsite.getAdviceReadUuid()).isEqualTo(DEFAULT_ADVICE_READ_UUID);
        assertThat(testOnsite.getAdviceStatus()).isEqualTo(DEFAULT_ADVICE_STATUS);
    }

    @Test
    @Transactional
    public void createOnsiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = onsiteRepository.findAll().size();

        // Create the Onsite with an existing ID
        onsite.setId(1L);
        OnsiteDTO onsiteDTO = onsiteMapper.toDto(onsite);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsiteMockMvc.perform(post("/api/onsites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onsiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Onsite in the database
        List<Onsite> onsiteList = onsiteRepository.findAll();
        assertThat(onsiteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOnsites() throws Exception {
        // Initialize the database
        onsiteRepository.saveAndFlush(onsite);

        // Get all the onsiteList
        restOnsiteMockMvc.perform(get("/api/onsites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsite.getId().intValue())))
            .andExpect(jsonPath("$.[*].report").value(hasItem(DEFAULT_REPORT.toString())))
            .andExpect(jsonPath("$.[*].onsiteTime").value(hasItem(DEFAULT_ONSITE_TIME.toString())))
            .andExpect(jsonPath("$.[*].accompany").value(hasItem(DEFAULT_ACCOMPANY.toString())))
            .andExpect(jsonPath("$.[*].onsiteAddr").value(hasItem(DEFAULT_ONSITE_ADDR.toString())))
            .andExpect(jsonPath("$.[*].onsiteInfo").value(hasItem(DEFAULT_ONSITE_INFO.toString())))
            .andExpect(jsonPath("$.[*].targetId").value(hasItem(DEFAULT_TARGET_ID.toString())))
            .andExpect(jsonPath("$.[*].targetName").value(hasItem(DEFAULT_TARGET_NAME.toString())))
            .andExpect(jsonPath("$.[*].surveyType").value(hasItem(DEFAULT_SURVEY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].infoType").value(hasItem(DEFAULT_INFO_TYPE.toString())))
            .andExpect(jsonPath("$.[*].adviceDeptId").value(hasItem(DEFAULT_ADVICE_DEPT_ID.toString())))
            .andExpect(jsonPath("$.[*].adviceReadTime").value(hasItem(DEFAULT_ADVICE_READ_TIME.toString())))
            .andExpect(jsonPath("$.[*].adviceReadUuid").value(hasItem(DEFAULT_ADVICE_READ_UUID.toString())))
            .andExpect(jsonPath("$.[*].adviceStatus").value(hasItem(DEFAULT_ADVICE_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getOnsite() throws Exception {
        // Initialize the database
        onsiteRepository.saveAndFlush(onsite);

        // Get the onsite
        restOnsiteMockMvc.perform(get("/api/onsites/{id}", onsite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(onsite.getId().intValue()))
            .andExpect(jsonPath("$.report").value(DEFAULT_REPORT.toString()))
            .andExpect(jsonPath("$.onsiteTime").value(DEFAULT_ONSITE_TIME.toString()))
            .andExpect(jsonPath("$.accompany").value(DEFAULT_ACCOMPANY.toString()))
            .andExpect(jsonPath("$.onsiteAddr").value(DEFAULT_ONSITE_ADDR.toString()))
            .andExpect(jsonPath("$.onsiteInfo").value(DEFAULT_ONSITE_INFO.toString()))
            .andExpect(jsonPath("$.targetId").value(DEFAULT_TARGET_ID.toString()))
            .andExpect(jsonPath("$.targetName").value(DEFAULT_TARGET_NAME.toString()))
            .andExpect(jsonPath("$.surveyType").value(DEFAULT_SURVEY_TYPE.toString()))
            .andExpect(jsonPath("$.infoType").value(DEFAULT_INFO_TYPE.toString()))
            .andExpect(jsonPath("$.adviceDeptId").value(DEFAULT_ADVICE_DEPT_ID.toString()))
            .andExpect(jsonPath("$.adviceReadTime").value(DEFAULT_ADVICE_READ_TIME.toString()))
            .andExpect(jsonPath("$.adviceReadUuid").value(DEFAULT_ADVICE_READ_UUID.toString()))
            .andExpect(jsonPath("$.adviceStatus").value(DEFAULT_ADVICE_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOnsite() throws Exception {
        // Get the onsite
        restOnsiteMockMvc.perform(get("/api/onsites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOnsite() throws Exception {
        // Initialize the database
        onsiteRepository.saveAndFlush(onsite);
        int databaseSizeBeforeUpdate = onsiteRepository.findAll().size();

        // Update the onsite
        Onsite updatedOnsite = onsiteRepository.findOne(onsite.getId());
        // Disconnect from session so that the updates on updatedOnsite are not directly saved in db
        em.detach(updatedOnsite);
        updatedOnsite
            .report(UPDATED_REPORT)
            .onsiteTime(UPDATED_ONSITE_TIME)
            .accompany(UPDATED_ACCOMPANY)
            .onsiteAddr(UPDATED_ONSITE_ADDR)
            .onsiteInfo(UPDATED_ONSITE_INFO)
            .targetId(UPDATED_TARGET_ID)
            .targetName(UPDATED_TARGET_NAME)
            .surveyType(UPDATED_SURVEY_TYPE)
            .infoType(UPDATED_INFO_TYPE)
            .adviceDeptId(UPDATED_ADVICE_DEPT_ID)
            .adviceReadTime(UPDATED_ADVICE_READ_TIME)
            .adviceReadUuid(UPDATED_ADVICE_READ_UUID)
            .adviceStatus(UPDATED_ADVICE_STATUS);
        OnsiteDTO onsiteDTO = onsiteMapper.toDto(updatedOnsite);

        restOnsiteMockMvc.perform(put("/api/onsites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onsiteDTO)))
            .andExpect(status().isOk());

        // Validate the Onsite in the database
        List<Onsite> onsiteList = onsiteRepository.findAll();
        assertThat(onsiteList).hasSize(databaseSizeBeforeUpdate);
        Onsite testOnsite = onsiteList.get(onsiteList.size() - 1);
        assertThat(testOnsite.getReport()).isEqualTo(UPDATED_REPORT);
        assertThat(testOnsite.getOnsiteTime()).isEqualTo(UPDATED_ONSITE_TIME);
        assertThat(testOnsite.getAccompany()).isEqualTo(UPDATED_ACCOMPANY);
        assertThat(testOnsite.getOnsiteAddr()).isEqualTo(UPDATED_ONSITE_ADDR);
        assertThat(testOnsite.getOnsiteInfo()).isEqualTo(UPDATED_ONSITE_INFO);
        assertThat(testOnsite.getTargetId()).isEqualTo(UPDATED_TARGET_ID);
        assertThat(testOnsite.getTargetName()).isEqualTo(UPDATED_TARGET_NAME);
        assertThat(testOnsite.getSurveyType()).isEqualTo(UPDATED_SURVEY_TYPE);
        assertThat(testOnsite.getInfoType()).isEqualTo(UPDATED_INFO_TYPE);
        assertThat(testOnsite.getAdviceDeptId()).isEqualTo(UPDATED_ADVICE_DEPT_ID);
        assertThat(testOnsite.getAdviceReadTime()).isEqualTo(UPDATED_ADVICE_READ_TIME);
        assertThat(testOnsite.getAdviceReadUuid()).isEqualTo(UPDATED_ADVICE_READ_UUID);
        assertThat(testOnsite.getAdviceStatus()).isEqualTo(UPDATED_ADVICE_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOnsite() throws Exception {
        int databaseSizeBeforeUpdate = onsiteRepository.findAll().size();

        // Create the Onsite
        OnsiteDTO onsiteDTO = onsiteMapper.toDto(onsite);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOnsiteMockMvc.perform(put("/api/onsites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onsiteDTO)))
            .andExpect(status().isCreated());

        // Validate the Onsite in the database
        List<Onsite> onsiteList = onsiteRepository.findAll();
        assertThat(onsiteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOnsite() throws Exception {
        // Initialize the database
        onsiteRepository.saveAndFlush(onsite);
        int databaseSizeBeforeDelete = onsiteRepository.findAll().size();

        // Get the onsite
        restOnsiteMockMvc.perform(delete("/api/onsites/{id}", onsite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Onsite> onsiteList = onsiteRepository.findAll();
        assertThat(onsiteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Onsite.class);
        Onsite onsite1 = new Onsite();
        onsite1.setId(1L);
        Onsite onsite2 = new Onsite();
        onsite2.setId(onsite1.getId());
        assertThat(onsite1).isEqualTo(onsite2);
        onsite2.setId(2L);
        assertThat(onsite1).isNotEqualTo(onsite2);
        onsite1.setId(null);
        assertThat(onsite1).isNotEqualTo(onsite2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsiteDTO.class);
        OnsiteDTO onsiteDTO1 = new OnsiteDTO();
        onsiteDTO1.setId(1L);
        OnsiteDTO onsiteDTO2 = new OnsiteDTO();
        assertThat(onsiteDTO1).isNotEqualTo(onsiteDTO2);
        onsiteDTO2.setId(onsiteDTO1.getId());
        assertThat(onsiteDTO1).isEqualTo(onsiteDTO2);
        onsiteDTO2.setId(2L);
        assertThat(onsiteDTO1).isNotEqualTo(onsiteDTO2);
        onsiteDTO1.setId(null);
        assertThat(onsiteDTO1).isNotEqualTo(onsiteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(onsiteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(onsiteMapper.fromId(null)).isNull();
    }
}
