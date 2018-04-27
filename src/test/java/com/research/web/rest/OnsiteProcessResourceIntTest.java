package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.OnsiteProcess;
import com.research.repository.OnsiteProcessRepository;
import com.research.service.OnsiteProcessService;
import com.research.service.dto.OnsiteProcessDTO;
import com.research.service.mapper.OnsiteProcessMapper;
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
 * Test class for the OnsiteProcessResource REST controller.
 *
 * @see OnsiteProcessResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class OnsiteProcessResourceIntTest {

    private static final String DEFAULT_CONFIRM = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRM = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private OnsiteProcessRepository onsiteProcessRepository;

    @Autowired
    private OnsiteProcessMapper onsiteProcessMapper;

    @Autowired
    private OnsiteProcessService onsiteProcessService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOnsiteProcessMockMvc;

    private OnsiteProcess onsiteProcess;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OnsiteProcessResource onsiteProcessResource = new OnsiteProcessResource(onsiteProcessService);
        this.restOnsiteProcessMockMvc = MockMvcBuilders.standaloneSetup(onsiteProcessResource)
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
    public static OnsiteProcess createEntity(EntityManager em) {
        OnsiteProcess onsiteProcess = new OnsiteProcess()
            .confirm(DEFAULT_CONFIRM)
            .remarks(DEFAULT_REMARKS);
        return onsiteProcess;
    }

    @Before
    public void initTest() {
        onsiteProcess = createEntity(em);
    }

    @Test
    @Transactional
    public void createOnsiteProcess() throws Exception {
        int databaseSizeBeforeCreate = onsiteProcessRepository.findAll().size();

        // Create the OnsiteProcess
        OnsiteProcessDTO onsiteProcessDTO = onsiteProcessMapper.toDto(onsiteProcess);
        restOnsiteProcessMockMvc.perform(post("/api/onsite-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onsiteProcessDTO)))
            .andExpect(status().isCreated());

        // Validate the OnsiteProcess in the database
        List<OnsiteProcess> onsiteProcessList = onsiteProcessRepository.findAll();
        assertThat(onsiteProcessList).hasSize(databaseSizeBeforeCreate + 1);
        OnsiteProcess testOnsiteProcess = onsiteProcessList.get(onsiteProcessList.size() - 1);
        assertThat(testOnsiteProcess.getConfirm()).isEqualTo(DEFAULT_CONFIRM);
        assertThat(testOnsiteProcess.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createOnsiteProcessWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = onsiteProcessRepository.findAll().size();

        // Create the OnsiteProcess with an existing ID
        onsiteProcess.setId(1L);
        OnsiteProcessDTO onsiteProcessDTO = onsiteProcessMapper.toDto(onsiteProcess);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsiteProcessMockMvc.perform(post("/api/onsite-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onsiteProcessDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OnsiteProcess in the database
        List<OnsiteProcess> onsiteProcessList = onsiteProcessRepository.findAll();
        assertThat(onsiteProcessList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOnsiteProcesses() throws Exception {
        // Initialize the database
        onsiteProcessRepository.saveAndFlush(onsiteProcess);

        // Get all the onsiteProcessList
        restOnsiteProcessMockMvc.perform(get("/api/onsite-processes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsiteProcess.getId().intValue())))
            .andExpect(jsonPath("$.[*].confirm").value(hasItem(DEFAULT_CONFIRM.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }

    @Test
    @Transactional
    public void getOnsiteProcess() throws Exception {
        // Initialize the database
        onsiteProcessRepository.saveAndFlush(onsiteProcess);

        // Get the onsiteProcess
        restOnsiteProcessMockMvc.perform(get("/api/onsite-processes/{id}", onsiteProcess.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(onsiteProcess.getId().intValue()))
            .andExpect(jsonPath("$.confirm").value(DEFAULT_CONFIRM.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOnsiteProcess() throws Exception {
        // Get the onsiteProcess
        restOnsiteProcessMockMvc.perform(get("/api/onsite-processes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOnsiteProcess() throws Exception {
        // Initialize the database
        onsiteProcessRepository.saveAndFlush(onsiteProcess);
        int databaseSizeBeforeUpdate = onsiteProcessRepository.findAll().size();

        // Update the onsiteProcess
        OnsiteProcess updatedOnsiteProcess = onsiteProcessRepository.findOne(onsiteProcess.getId());
        // Disconnect from session so that the updates on updatedOnsiteProcess are not directly saved in db
        em.detach(updatedOnsiteProcess);
        updatedOnsiteProcess
            .confirm(UPDATED_CONFIRM)
            .remarks(UPDATED_REMARKS);
        OnsiteProcessDTO onsiteProcessDTO = onsiteProcessMapper.toDto(updatedOnsiteProcess);

        restOnsiteProcessMockMvc.perform(put("/api/onsite-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onsiteProcessDTO)))
            .andExpect(status().isOk());

        // Validate the OnsiteProcess in the database
        List<OnsiteProcess> onsiteProcessList = onsiteProcessRepository.findAll();
        assertThat(onsiteProcessList).hasSize(databaseSizeBeforeUpdate);
        OnsiteProcess testOnsiteProcess = onsiteProcessList.get(onsiteProcessList.size() - 1);
        assertThat(testOnsiteProcess.getConfirm()).isEqualTo(UPDATED_CONFIRM);
        assertThat(testOnsiteProcess.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingOnsiteProcess() throws Exception {
        int databaseSizeBeforeUpdate = onsiteProcessRepository.findAll().size();

        // Create the OnsiteProcess
        OnsiteProcessDTO onsiteProcessDTO = onsiteProcessMapper.toDto(onsiteProcess);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOnsiteProcessMockMvc.perform(put("/api/onsite-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onsiteProcessDTO)))
            .andExpect(status().isCreated());

        // Validate the OnsiteProcess in the database
        List<OnsiteProcess> onsiteProcessList = onsiteProcessRepository.findAll();
        assertThat(onsiteProcessList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOnsiteProcess() throws Exception {
        // Initialize the database
        onsiteProcessRepository.saveAndFlush(onsiteProcess);
        int databaseSizeBeforeDelete = onsiteProcessRepository.findAll().size();

        // Get the onsiteProcess
        restOnsiteProcessMockMvc.perform(delete("/api/onsite-processes/{id}", onsiteProcess.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OnsiteProcess> onsiteProcessList = onsiteProcessRepository.findAll();
        assertThat(onsiteProcessList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsiteProcess.class);
        OnsiteProcess onsiteProcess1 = new OnsiteProcess();
        onsiteProcess1.setId(1L);
        OnsiteProcess onsiteProcess2 = new OnsiteProcess();
        onsiteProcess2.setId(onsiteProcess1.getId());
        assertThat(onsiteProcess1).isEqualTo(onsiteProcess2);
        onsiteProcess2.setId(2L);
        assertThat(onsiteProcess1).isNotEqualTo(onsiteProcess2);
        onsiteProcess1.setId(null);
        assertThat(onsiteProcess1).isNotEqualTo(onsiteProcess2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnsiteProcessDTO.class);
        OnsiteProcessDTO onsiteProcessDTO1 = new OnsiteProcessDTO();
        onsiteProcessDTO1.setId(1L);
        OnsiteProcessDTO onsiteProcessDTO2 = new OnsiteProcessDTO();
        assertThat(onsiteProcessDTO1).isNotEqualTo(onsiteProcessDTO2);
        onsiteProcessDTO2.setId(onsiteProcessDTO1.getId());
        assertThat(onsiteProcessDTO1).isEqualTo(onsiteProcessDTO2);
        onsiteProcessDTO2.setId(2L);
        assertThat(onsiteProcessDTO1).isNotEqualTo(onsiteProcessDTO2);
        onsiteProcessDTO1.setId(null);
        assertThat(onsiteProcessDTO1).isNotEqualTo(onsiteProcessDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(onsiteProcessMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(onsiteProcessMapper.fromId(null)).isNull();
    }
}
