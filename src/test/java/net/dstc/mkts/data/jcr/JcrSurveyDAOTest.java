/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.data.jcr;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import javax.jcr.RepositoryException;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyStatus;
import net.dstc.mkts.data.SurveyTargetDO;
import org.apache.jackrabbit.core.RepositoryImpl;
import org.apache.jackrabbit.core.config.RepositoryConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl;

/**
 *
 * @author eul0860
 */
public class JcrSurveyDAOTest {

    public JcrSurveyDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Mocked
    RepositoryConfig config;

    @Mocked
    RepositoryImpl repository;

    @Mocked
    ObjectContentManagerImpl objectContentManager;

    /**
     * Test of add method, of class JcrSurveyDAO.
     */
    @Test
    public void testAdd() {

        System.out.println("add");
        SurveyDO survey = null;
        JcrSurveyDAO instance = new JcrSurveyDAO();
        instance.add(survey);
    }

    /**
     * Test of list method, of class JcrSurveyDAO.
     */
    @Test
    public void testList() {
        System.out.println("list");
        SurveyDO query = null;
        JcrSurveyDAO instance = new JcrSurveyDAO();
        Collection<SurveyDO> expResult = Collections.emptyList();
        Collection<SurveyDO> result = instance.list(query);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSearch() {
        System.out.println("search");
        SurveyDO query = new JcrSurveyDO();
        query.setStartDate(new Date());
        query.setStatus(SurveyStatus.READY);
        query.setTitle("test");
        
        SurveyTargetDO target = new JcrSurveyTargetDO();
        query.setTarget(target);
        
        target.setCountry("IT");
        target.setGender("M");
        target.setMaxAge(80);
        target.setMinAge(30);
        target.setMaxIncome(50000);
        target.setMinIncome(35000);
        JcrSurveyDAO instance = new JcrSurveyDAO();
        Collection<SurveyDO> expResult = Collections.emptyList();
        Collection<SurveyDO> result = instance.list(query);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class JcrSurveyDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String id = "";
        JcrSurveyDAO instance = new JcrSurveyDAO();
        SurveyDO expResult = null;
        SurveyDO result = instance.get(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class JcrSurveyDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String id = "";
        JcrSurveyDAO instance = new JcrSurveyDAO();
        instance.delete(id);
    }

    /**
     * Test of update method, of class JcrSurveyDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        SurveyDO s = null;
        JcrSurveyDAO instance = new JcrSurveyDAO();
        instance.update(s);
    }

    /**
     * Test of createSurvey method, of class JcrSurveyDAO.
     */
    @Test
    public void testCreateSurvey() {
        System.out.println("createSurvey");
        JcrSurveyDAO instance = new JcrSurveyDAO();
        SurveyDO result = instance.createSurvey();
        assertTrue(result instanceof JcrSurveyDO);
    }

    /**
     * Test of createSurveyTarget method, of class JcrSurveyDAO.
     */
    @Test
    public void testCreateSurveyTarget() {
        System.out.println("createSurveyTarget");
        JcrSurveyDAO instance = new JcrSurveyDAO();
        SurveyTargetDO expResult = new JcrSurveyTargetDO();
        SurveyTargetDO result = instance.createSurveyTarget();
        assertEquals(expResult, result);
    }
}
