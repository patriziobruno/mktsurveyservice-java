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
import java.util.UUID;
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
    ObjectContentManagerImpl objectContentManager;

    @Test
    public void testConstructorHandlingRepositoryException() throws RepositoryException {
        System.out.println("JcrSurveyDAO");

        new MockUp<RepositoryImpl>() {
            @Mock
            public RepositoryImpl create(RepositoryConfig config) throws RepositoryException {
                throw new RepositoryException();
            }
        };

        JcrSurveyDAO dao = new JcrSurveyDAO();
        dao.shutdown();
    }

    /**
     * Test of add method, of class JcrSurveyDAO.
     */
    @Test
    public void testAdd(@Mocked RepositoryImpl repository) {

        System.out.println("add");
        JcrSurveyDAO instance = new JcrSurveyDAO();
        SurveyDO survey = instance.createSurvey();
        instance.add(survey);
        instance.shutdown();
    }

    /**
     * Test of list method, of class JcrSurveyDAO.
     */
    @Test
    public void testListWithNullQuery(@Mocked RepositoryImpl repository) {
        System.out.println("list");
        SurveyDO query = null;
        JcrSurveyDAO instance = new JcrSurveyDAO();
        Collection<SurveyDO> expResult = Collections.emptyList();
        Collection<SurveyDO> result = instance.list(query);
        assertEquals(expResult, result);
        instance.shutdown();
    }

    /**
     * Test of list method, of class JcrSurveyDAO.
     */
    @Test
    public void testListWithQuery(@Mocked RepositoryImpl repository) {
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
        instance.shutdown();
    }

    @Test
    public void testJcrSurveyDOEquals(@Mocked RepositoryImpl repository) {
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
    public void testJcrTargetSurveyDOEquals(@Mocked RepositoryImpl repository) {
        System.out.println("JcrSurveyTargetDO.equals");
        JcrSurveyTargetDO targetA = new JcrSurveyTargetDO();
        JcrSurveyTargetDO targetB = new JcrSurveyTargetDO();

        assertNotEquals(targetA, null);
        assertNotEquals(targetA, "");
        assertEquals(targetA, targetA);
        assertEquals(targetA, targetB);
    }

    @Test
    public void testSearch(@Mocked RepositoryImpl repository) {
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
        instance.shutdown();
    }

    /**
     * Test of get method, of class JcrSurveyDAO.
     */
    @Test
    public void testGet(@Mocked RepositoryImpl repository) {
        System.out.println("get");
        String id = "";
        JcrSurveyDAO instance = new JcrSurveyDAO();
        SurveyDO expResult = null;
        SurveyDO result = instance.get(id);
        assertEquals(expResult, result);
        instance.shutdown();
    }

    /**
     * Test of delete method, of class JcrSurveyDAO.
     */
    @Test
    public void testDelete(@Mocked RepositoryImpl repository) {
        System.out.println("delete");
        String id = "";
        JcrSurveyDAO instance = new JcrSurveyDAO();
        instance.delete(id);
        instance.shutdown();
    }

    /**
     * Test of update method, of class JcrSurveyDAO.
     */
    @Test
    public void testUpdate(@Mocked RepositoryImpl repository) {
        System.out.println("update");
        SurveyDO s = new JcrSurveyDO();
        s.setId("/survey/" + UUID.randomUUID());
        JcrSurveyDAO instance = new JcrSurveyDAO();
        instance.update(s);
        instance.shutdown();
    }

    /**
     * Test of createSurvey method, of class JcrSurveyDAO.
     */
    @Test
    public void testCreateSurvey(@Mocked RepositoryImpl repository) {
        System.out.println("createSurvey");
        JcrSurveyDAO instance = new JcrSurveyDAO();
        SurveyDO result = instance.createSurvey();
        assertTrue(result instanceof JcrSurveyDO);
        instance.shutdown();
    }

    /**
     * Test of createSurveyTarget method, of class JcrSurveyDAO.
     */
    @Test
    public void testCreateSurveyTarget(@Mocked RepositoryImpl repository) {
        System.out.println("createSurveyTarget");
        JcrSurveyDAO instance = new JcrSurveyDAO();
        SurveyTargetDO expResult = new JcrSurveyTargetDO();
        SurveyTargetDO result = instance.createSurveyTarget();
        assertEquals(expResult, result);
        instance.shutdown();
    }
}
