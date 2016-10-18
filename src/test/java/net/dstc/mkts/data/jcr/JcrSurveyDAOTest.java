/* 
 * Copyright 2016 eul0860.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.dstc.mkts.data.jcr;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
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
 * @author Patrizio Bruno <desertconsulting@gmail.com>
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
    public void testListWithNullQuery() {
        System.out.println("list");
        SurveyDO query = null;
        JcrSurveyDAO instance = new JcrSurveyDAO();
        Collection<SurveyDO> expResult = Collections.emptyList();
        Collection<SurveyDO> result = instance.list(query);
        assertEquals(expResult, result);
    }

    /**
     * Test of list method, of class JcrSurveyDAO.
     */
    @Test
    public void testListWithQuery() {
        System.out.println("list");
        JcrSurveyDAO instance = new JcrSurveyDAO();
        SurveyDO query = instance.createSurvey();
        query.setTarget(instance.createSurveyTarget());
        query.setStartDate(new Date());
        query.setTitle("test");
        query.setStatus(SurveyStatus.READY);

        Collection<SurveyDO> expResult = Collections.emptyList();
        Collection<SurveyDO> result = instance.list(query);
        assertEquals(expResult, result);
    }

    @Test
    public void testJcrSurveyDOEquals() {
        System.out.println("JcrSurveyDO.equals");
        JcrSurveyDO surveyA = new JcrSurveyDO();
        surveyA.setTarget(new JcrSurveyTargetDO());

        JcrSurveyDO surveyB = new JcrSurveyDO();

        JcrSurveyDO surveyC = new JcrSurveyDO();
        JcrSurveyTargetDO targetC = new JcrSurveyTargetDO();
        targetC.setCountry("TS");
        surveyC.setTarget(targetC);

        JcrSurveyDO surveyD = new JcrSurveyDO();
        surveyD.setTarget(new JcrSurveyTargetDO());

        assertNotEquals(surveyA, null);
        assertNotEquals(surveyA, "");
        assertEquals(surveyA, surveyA);
        assertNotEquals(surveyA, surveyB);
        assertNotEquals(surveyB, surveyA);
        assertNotEquals(surveyA, surveyC);
        assertNotEquals(surveyA, surveyD);
    }

    @Test
    public void testJcrTargetSurveyDOEquals() {
        System.out.println("JcrSurveyTargetDO.equals");
        JcrSurveyTargetDO targetA = new JcrSurveyTargetDO();
        JcrSurveyTargetDO targetB = new JcrSurveyTargetDO();

        assertNotEquals(targetA, null);
        assertNotEquals(targetA, "");
        assertEquals(targetA, targetA);
        assertEquals(targetA, targetB);
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
        SurveyDO s = new JcrSurveyDO();
        s.setId("/survey/1");
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
