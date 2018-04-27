package com.research.web.rest;

import com.research.IResearchApp;

import com.research.domain.Acl;
import com.research.repository.AclRepository;
import com.research.service.AclService;
import com.research.service.dto.AclDTO;
import com.research.service.mapper.AclMapper;
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
 * Test class for the AclResource REST controller.
 *
 * @see AclResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IResearchApp.class)
public class AclResourceIntTest {

    private static final String DEFAULT_MOD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FUNC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUNC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACL_LIST = "AAAAAAAAAA";
    private static final String UPDATED_ACL_LIST = "BBBBBBBBBB";

    @Autowired
    private AclRepository aclRepository;

    @Autowired
    private AclMapper aclMapper;

    @Autowired
    private AclService aclService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAclMockMvc;

    private Acl acl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AclResource aclResource = new AclResource(aclService);
        this.restAclMockMvc = MockMvcBuilders.standaloneSetup(aclResource)
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
    public static Acl createEntity(EntityManager em) {
        Acl acl = new Acl()
            .modName(DEFAULT_MOD_NAME)
            .funcName(DEFAULT_FUNC_NAME)
            .aclList(DEFAULT_ACL_LIST);
        return acl;
    }

    @Before
    public void initTest() {
        acl = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcl() throws Exception {
        int databaseSizeBeforeCreate = aclRepository.findAll().size();

        // Create the Acl
        AclDTO aclDTO = aclMapper.toDto(acl);
        restAclMockMvc.perform(post("/api/acls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aclDTO)))
            .andExpect(status().isCreated());

        // Validate the Acl in the database
        List<Acl> aclList = aclRepository.findAll();
        assertThat(aclList).hasSize(databaseSizeBeforeCreate + 1);
        Acl testAcl = aclList.get(aclList.size() - 1);
        assertThat(testAcl.getModName()).isEqualTo(DEFAULT_MOD_NAME);
        assertThat(testAcl.getFuncName()).isEqualTo(DEFAULT_FUNC_NAME);
        assertThat(testAcl.getAclList()).isEqualTo(DEFAULT_ACL_LIST);
    }

    @Test
    @Transactional
    public void createAclWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aclRepository.findAll().size();

        // Create the Acl with an existing ID
        acl.setId(1L);
        AclDTO aclDTO = aclMapper.toDto(acl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAclMockMvc.perform(post("/api/acls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aclDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Acl in the database
        List<Acl> aclList = aclRepository.findAll();
        assertThat(aclList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAcls() throws Exception {
        // Initialize the database
        aclRepository.saveAndFlush(acl);

        // Get all the aclList
        restAclMockMvc.perform(get("/api/acls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acl.getId().intValue())))
            .andExpect(jsonPath("$.[*].modName").value(hasItem(DEFAULT_MOD_NAME.toString())))
            .andExpect(jsonPath("$.[*].funcName").value(hasItem(DEFAULT_FUNC_NAME.toString())))
            .andExpect(jsonPath("$.[*].aclList").value(hasItem(DEFAULT_ACL_LIST.toString())));
    }

    @Test
    @Transactional
    public void getAcl() throws Exception {
        // Initialize the database
        aclRepository.saveAndFlush(acl);

        // Get the acl
        restAclMockMvc.perform(get("/api/acls/{id}", acl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(acl.getId().intValue()))
            .andExpect(jsonPath("$.modName").value(DEFAULT_MOD_NAME.toString()))
            .andExpect(jsonPath("$.funcName").value(DEFAULT_FUNC_NAME.toString()))
            .andExpect(jsonPath("$.aclList").value(DEFAULT_ACL_LIST.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAcl() throws Exception {
        // Get the acl
        restAclMockMvc.perform(get("/api/acls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcl() throws Exception {
        // Initialize the database
        aclRepository.saveAndFlush(acl);
        int databaseSizeBeforeUpdate = aclRepository.findAll().size();

        // Update the acl
        Acl updatedAcl = aclRepository.findOne(acl.getId());
        // Disconnect from session so that the updates on updatedAcl are not directly saved in db
        em.detach(updatedAcl);
        updatedAcl
            .modName(UPDATED_MOD_NAME)
            .funcName(UPDATED_FUNC_NAME)
            .aclList(UPDATED_ACL_LIST);
        AclDTO aclDTO = aclMapper.toDto(updatedAcl);

        restAclMockMvc.perform(put("/api/acls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aclDTO)))
            .andExpect(status().isOk());

        // Validate the Acl in the database
        List<Acl> aclList = aclRepository.findAll();
        assertThat(aclList).hasSize(databaseSizeBeforeUpdate);
        Acl testAcl = aclList.get(aclList.size() - 1);
        assertThat(testAcl.getModName()).isEqualTo(UPDATED_MOD_NAME);
        assertThat(testAcl.getFuncName()).isEqualTo(UPDATED_FUNC_NAME);
        assertThat(testAcl.getAclList()).isEqualTo(UPDATED_ACL_LIST);
    }

    @Test
    @Transactional
    public void updateNonExistingAcl() throws Exception {
        int databaseSizeBeforeUpdate = aclRepository.findAll().size();

        // Create the Acl
        AclDTO aclDTO = aclMapper.toDto(acl);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAclMockMvc.perform(put("/api/acls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aclDTO)))
            .andExpect(status().isCreated());

        // Validate the Acl in the database
        List<Acl> aclList = aclRepository.findAll();
        assertThat(aclList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAcl() throws Exception {
        // Initialize the database
        aclRepository.saveAndFlush(acl);
        int databaseSizeBeforeDelete = aclRepository.findAll().size();

        // Get the acl
        restAclMockMvc.perform(delete("/api/acls/{id}", acl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Acl> aclList = aclRepository.findAll();
        assertThat(aclList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Acl.class);
        Acl acl1 = new Acl();
        acl1.setId(1L);
        Acl acl2 = new Acl();
        acl2.setId(acl1.getId());
        assertThat(acl1).isEqualTo(acl2);
        acl2.setId(2L);
        assertThat(acl1).isNotEqualTo(acl2);
        acl1.setId(null);
        assertThat(acl1).isNotEqualTo(acl2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AclDTO.class);
        AclDTO aclDTO1 = new AclDTO();
        aclDTO1.setId(1L);
        AclDTO aclDTO2 = new AclDTO();
        assertThat(aclDTO1).isNotEqualTo(aclDTO2);
        aclDTO2.setId(aclDTO1.getId());
        assertThat(aclDTO1).isEqualTo(aclDTO2);
        aclDTO2.setId(2L);
        assertThat(aclDTO1).isNotEqualTo(aclDTO2);
        aclDTO1.setId(null);
        assertThat(aclDTO1).isNotEqualTo(aclDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(aclMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(aclMapper.fromId(null)).isNull();
    }
}
