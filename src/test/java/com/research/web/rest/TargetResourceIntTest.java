package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.Target;
import com.research.repository.TargetRepository;
import com.research.service.TargetService;
import com.research.service.dto.TargetDTO;
import com.research.service.mapper.TargetMapper;
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
 * Test class for the TargetResource REST controller.
 *
 * @see TargetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class TargetResourceIntTest {

    private static final String DEFAULT_TARGET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_TYPE_README = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_TYPE_README = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_ADDR = "BBBBBBBBBB";

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private TargetMapper targetMapper;

    @Autowired
    private TargetService targetService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTargetMockMvc;

    private Target target;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TargetResource targetResource = new TargetResource(targetService);
        this.restTargetMockMvc = MockMvcBuilders.standaloneSetup(targetResource)
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
    public static Target createEntity(EntityManager em) {
        Target target = new Target()
            .targetName(DEFAULT_TARGET_NAME)
            .targetType(DEFAULT_TARGET_TYPE)
            .targetTypeReadme(DEFAULT_TARGET_TYPE_README)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .contactPhone(DEFAULT_CONTACT_PHONE)
            .contactEmail(DEFAULT_CONTACT_EMAIL)
            .contactAddr(DEFAULT_CONTACT_ADDR);
        return target;
    }

    @Before
    public void initTest() {
        target = createEntity(em);
    }

    @Test
    @Transactional
    public void createTarget() throws Exception {
        int databaseSizeBeforeCreate = targetRepository.findAll().size();

        // Create the Target
        TargetDTO targetDTO = targetMapper.toDto(target);
        restTargetMockMvc.perform(post("/api/targets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(targetDTO)))
            .andExpect(status().isCreated());

        // Validate the Target in the database
        List<Target> targetList = targetRepository.findAll();
        assertThat(targetList).hasSize(databaseSizeBeforeCreate + 1);
        Target testTarget = targetList.get(targetList.size() - 1);
        assertThat(testTarget.getTargetName()).isEqualTo(DEFAULT_TARGET_NAME);
        assertThat(testTarget.getTargetType()).isEqualTo(DEFAULT_TARGET_TYPE);
        assertThat(testTarget.getTargetTypeReadme()).isEqualTo(DEFAULT_TARGET_TYPE_README);
        assertThat(testTarget.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testTarget.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testTarget.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testTarget.getContactAddr()).isEqualTo(DEFAULT_CONTACT_ADDR);
    }

    @Test
    @Transactional
    public void createTargetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = targetRepository.findAll().size();

        // Create the Target with an existing ID
        target.setId(1L);
        TargetDTO targetDTO = targetMapper.toDto(target);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTargetMockMvc.perform(post("/api/targets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(targetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Target in the database
        List<Target> targetList = targetRepository.findAll();
        assertThat(targetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTargets() throws Exception {
        // Initialize the database
        targetRepository.saveAndFlush(target);

        // Get all the targetList
        restTargetMockMvc.perform(get("/api/targets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(target.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetName").value(hasItem(DEFAULT_TARGET_NAME.toString())))
            .andExpect(jsonPath("$.[*].targetType").value(hasItem(DEFAULT_TARGET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].targetTypeReadme").value(hasItem(DEFAULT_TARGET_TYPE_README.toString())))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON.toString())))
            .andExpect(jsonPath("$.[*].contactPhone").value(hasItem(DEFAULT_CONTACT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].contactEmail").value(hasItem(DEFAULT_CONTACT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].contactAddr").value(hasItem(DEFAULT_CONTACT_ADDR.toString())));
    }

    @Test
    @Transactional
    public void getTarget() throws Exception {
        // Initialize the database
        targetRepository.saveAndFlush(target);

        // Get the target
        restTargetMockMvc.perform(get("/api/targets/{id}", target.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(target.getId().intValue()))
            .andExpect(jsonPath("$.targetName").value(DEFAULT_TARGET_NAME.toString()))
            .andExpect(jsonPath("$.targetType").value(DEFAULT_TARGET_TYPE.toString()))
            .andExpect(jsonPath("$.targetTypeReadme").value(DEFAULT_TARGET_TYPE_README.toString()))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON.toString()))
            .andExpect(jsonPath("$.contactPhone").value(DEFAULT_CONTACT_PHONE.toString()))
            .andExpect(jsonPath("$.contactEmail").value(DEFAULT_CONTACT_EMAIL.toString()))
            .andExpect(jsonPath("$.contactAddr").value(DEFAULT_CONTACT_ADDR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTarget() throws Exception {
        // Get the target
        restTargetMockMvc.perform(get("/api/targets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTarget() throws Exception {
        // Initialize the database
        targetRepository.saveAndFlush(target);
        int databaseSizeBeforeUpdate = targetRepository.findAll().size();

        // Update the target
        Target updatedTarget = targetRepository.findOne(target.getId());
        // Disconnect from session so that the updates on updatedTarget are not directly saved in db
        em.detach(updatedTarget);
        updatedTarget
            .targetName(UPDATED_TARGET_NAME)
            .targetType(UPDATED_TARGET_TYPE)
            .targetTypeReadme(UPDATED_TARGET_TYPE_README)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .contactPhone(UPDATED_CONTACT_PHONE)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .contactAddr(UPDATED_CONTACT_ADDR);
        TargetDTO targetDTO = targetMapper.toDto(updatedTarget);

        restTargetMockMvc.perform(put("/api/targets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(targetDTO)))
            .andExpect(status().isOk());

        // Validate the Target in the database
        List<Target> targetList = targetRepository.findAll();
        assertThat(targetList).hasSize(databaseSizeBeforeUpdate);
        Target testTarget = targetList.get(targetList.size() - 1);
        assertThat(testTarget.getTargetName()).isEqualTo(UPDATED_TARGET_NAME);
        assertThat(testTarget.getTargetType()).isEqualTo(UPDATED_TARGET_TYPE);
        assertThat(testTarget.getTargetTypeReadme()).isEqualTo(UPDATED_TARGET_TYPE_README);
        assertThat(testTarget.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testTarget.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testTarget.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testTarget.getContactAddr()).isEqualTo(UPDATED_CONTACT_ADDR);
    }

    @Test
    @Transactional
    public void updateNonExistingTarget() throws Exception {
        int databaseSizeBeforeUpdate = targetRepository.findAll().size();

        // Create the Target
        TargetDTO targetDTO = targetMapper.toDto(target);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTargetMockMvc.perform(put("/api/targets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(targetDTO)))
            .andExpect(status().isCreated());

        // Validate the Target in the database
        List<Target> targetList = targetRepository.findAll();
        assertThat(targetList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTarget() throws Exception {
        // Initialize the database
        targetRepository.saveAndFlush(target);
        int databaseSizeBeforeDelete = targetRepository.findAll().size();

        // Get the target
        restTargetMockMvc.perform(delete("/api/targets/{id}", target.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Target> targetList = targetRepository.findAll();
        assertThat(targetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Target.class);
        Target target1 = new Target();
        target1.setId(1L);
        Target target2 = new Target();
        target2.setId(target1.getId());
        assertThat(target1).isEqualTo(target2);
        target2.setId(2L);
        assertThat(target1).isNotEqualTo(target2);
        target1.setId(null);
        assertThat(target1).isNotEqualTo(target2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TargetDTO.class);
        TargetDTO targetDTO1 = new TargetDTO();
        targetDTO1.setId(1L);
        TargetDTO targetDTO2 = new TargetDTO();
        assertThat(targetDTO1).isNotEqualTo(targetDTO2);
        targetDTO2.setId(targetDTO1.getId());
        assertThat(targetDTO1).isEqualTo(targetDTO2);
        targetDTO2.setId(2L);
        assertThat(targetDTO1).isNotEqualTo(targetDTO2);
        targetDTO1.setId(null);
        assertThat(targetDTO1).isNotEqualTo(targetDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(targetMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(targetMapper.fromId(null)).isNull();
    }
}
