package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.ConfigAcl;
import com.research.repository.ConfigAclRepository;
import com.research.service.ConfigAclService;
import com.research.service.dto.ConfigAclDTO;
import com.research.service.mapper.ConfigAclMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.research.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConfigAclResource REST controller.
 *
 * @see ConfigAclResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class ConfigAclResourceIntTest {

    private static final String DEFAULT_MOD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FUNC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUNC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACL_LIST = "AAAAAAAAAA";
    private static final String UPDATED_ACL_LIST = "BBBBBBBBBB";

    @Autowired
    private ConfigAclRepository configAclRepository;

    @Autowired
    private ConfigAclMapper configAclMapper;

    @Autowired
    private ConfigAclService configAclService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConfigAclMockMvc;

    private ConfigAcl configAcl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfigAclResource configAclResource = new ConfigAclResource(configAclService);
        this.restConfigAclMockMvc = MockMvcBuilders.standaloneSetup(configAclResource)
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
    public static ConfigAcl createEntity(EntityManager em) {
        ConfigAcl configAcl = new ConfigAcl()
            .modName(DEFAULT_MOD_NAME)
            .funcName(DEFAULT_FUNC_NAME)
            .aclList(DEFAULT_ACL_LIST);
        return configAcl;
    }

    @Before
    public void initTest() {
        configAcl = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfigAcl() throws Exception {
        int databaseSizeBeforeCreate = configAclRepository.findAll().size();

        // Create the ConfigAcl
        ConfigAclDTO configAclDTO = configAclMapper.toDto(configAcl);
        restConfigAclMockMvc.perform(post("/api/config-acls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configAclDTO)))
            .andExpect(status().isCreated());

        // Validate the ConfigAcl in the database
        List<ConfigAcl> configAclList = configAclRepository.findAll();
        assertThat(configAclList).hasSize(databaseSizeBeforeCreate + 1);
        ConfigAcl testConfigAcl = configAclList.get(configAclList.size() - 1);
        assertThat(testConfigAcl.getModName()).isEqualTo(DEFAULT_MOD_NAME);
        assertThat(testConfigAcl.getFuncName()).isEqualTo(DEFAULT_FUNC_NAME);
        assertThat(testConfigAcl.getAclList()).isEqualTo(DEFAULT_ACL_LIST);
    }

    @Test
    @Transactional
    public void createConfigAclWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configAclRepository.findAll().size();

        // Create the ConfigAcl with an existing ID
        configAcl.setId(1L);
        ConfigAclDTO configAclDTO = configAclMapper.toDto(configAcl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfigAclMockMvc.perform(post("/api/config-acls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configAclDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConfigAcl in the database
        List<ConfigAcl> configAclList = configAclRepository.findAll();
        assertThat(configAclList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfigAcls() throws Exception {
        // Initialize the database
        configAclRepository.saveAndFlush(configAcl);

        // Get all the configAclList
        restConfigAclMockMvc.perform(get("/api/config-acls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configAcl.getId().intValue())))
            .andExpect(jsonPath("$.[*].modName").value(hasItem(DEFAULT_MOD_NAME.toString())))
            .andExpect(jsonPath("$.[*].funcName").value(hasItem(DEFAULT_FUNC_NAME.toString())))
            .andExpect(jsonPath("$.[*].aclList").value(hasItem(DEFAULT_ACL_LIST.toString())));
    }

    @Test
    @Transactional
    public void getConfigAcl() throws Exception {
        // Initialize the database
        configAclRepository.saveAndFlush(configAcl);

        // Get the configAcl
        restConfigAclMockMvc.perform(get("/api/config-acls/{id}", configAcl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(configAcl.getId().intValue()))
            .andExpect(jsonPath("$.modName").value(DEFAULT_MOD_NAME.toString()))
            .andExpect(jsonPath("$.funcName").value(DEFAULT_FUNC_NAME.toString()))
            .andExpect(jsonPath("$.aclList").value(DEFAULT_ACL_LIST.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConfigAcl() throws Exception {
        // Get the configAcl
        restConfigAclMockMvc.perform(get("/api/config-acls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfigAcl() throws Exception {
        // Initialize the database
        configAclRepository.saveAndFlush(configAcl);
        int databaseSizeBeforeUpdate = configAclRepository.findAll().size();

        // Update the configAcl
        ConfigAcl updatedConfigAcl = configAclRepository.findOne(configAcl.getId());
        // Disconnect from session so that the updates on updatedConfigAcl are not directly saved in db
        em.detach(updatedConfigAcl);
        updatedConfigAcl
            .modName(UPDATED_MOD_NAME)
            .funcName(UPDATED_FUNC_NAME)
            .aclList(UPDATED_ACL_LIST);
        ConfigAclDTO configAclDTO = configAclMapper.toDto(updatedConfigAcl);

        restConfigAclMockMvc.perform(put("/api/config-acls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configAclDTO)))
            .andExpect(status().isOk());

        // Validate the ConfigAcl in the database
        List<ConfigAcl> configAclList = configAclRepository.findAll();
        assertThat(configAclList).hasSize(databaseSizeBeforeUpdate);
        ConfigAcl testConfigAcl = configAclList.get(configAclList.size() - 1);
        assertThat(testConfigAcl.getModName()).isEqualTo(UPDATED_MOD_NAME);
        assertThat(testConfigAcl.getFuncName()).isEqualTo(UPDATED_FUNC_NAME);
        assertThat(testConfigAcl.getAclList()).isEqualTo(UPDATED_ACL_LIST);
    }

    @Test
    @Transactional
    public void updateNonExistingConfigAcl() throws Exception {
        int databaseSizeBeforeUpdate = configAclRepository.findAll().size();

        // Create the ConfigAcl
        ConfigAclDTO configAclDTO = configAclMapper.toDto(configAcl);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConfigAclMockMvc.perform(put("/api/config-acls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configAclDTO)))
            .andExpect(status().isCreated());

        // Validate the ConfigAcl in the database
        List<ConfigAcl> configAclList = configAclRepository.findAll();
        assertThat(configAclList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConfigAcl() throws Exception {
        // Initialize the database
        configAclRepository.saveAndFlush(configAcl);
        int databaseSizeBeforeDelete = configAclRepository.findAll().size();

        // Get the configAcl
        restConfigAclMockMvc.perform(delete("/api/config-acls/{id}", configAcl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConfigAcl> configAclList = configAclRepository.findAll();
        assertThat(configAclList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigAcl.class);
        ConfigAcl configAcl1 = new ConfigAcl();
        configAcl1.setId(1L);
        ConfigAcl configAcl2 = new ConfigAcl();
        configAcl2.setId(configAcl1.getId());
        assertThat(configAcl1).isEqualTo(configAcl2);
        configAcl2.setId(2L);
        assertThat(configAcl1).isNotEqualTo(configAcl2);
        configAcl1.setId(null);
        assertThat(configAcl1).isNotEqualTo(configAcl2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigAclDTO.class);
        ConfigAclDTO configAclDTO1 = new ConfigAclDTO();
        configAclDTO1.setId(1L);
        ConfigAclDTO configAclDTO2 = new ConfigAclDTO();
        assertThat(configAclDTO1).isNotEqualTo(configAclDTO2);
        configAclDTO2.setId(configAclDTO1.getId());
        assertThat(configAclDTO1).isEqualTo(configAclDTO2);
        configAclDTO2.setId(2L);
        assertThat(configAclDTO1).isNotEqualTo(configAclDTO2);
        configAclDTO1.setId(null);
        assertThat(configAclDTO1).isNotEqualTo(configAclDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(configAclMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(configAclMapper.fromId(null)).isNull();
    }
}
