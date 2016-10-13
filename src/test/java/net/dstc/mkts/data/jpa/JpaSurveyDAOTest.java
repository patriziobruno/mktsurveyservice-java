/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.data.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyTargetDO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrizio
 */
public class JpaSurveyDAOTest {

    MockUp<Persistence> persistenceMock;
    MockUp<EntityManagerFactory> emfMock;
    MockUp<EntityManager> emMock;
    MockUp<Query> queryMock;
    @Mocked
    EntityTransaction emtMock;

    public JpaSurveyDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        persistenceMock = new MockUp<Persistence>() {
            @Mock
            public EntityManagerFactory createEntityManagerFactory(String unit) {
                return emfMock.getMockInstance();
            }
        };

        emfMock = new MockUp<EntityManagerFactory>() {
            @Mock
            public EntityManager createEntityManager() {
                return emMock.getMockInstance();
            }
        };

        emMock = new MockUp<EntityManager>() {
            @Mock
            public Query createQuery(String q) {
                return queryMock.getMockInstance();
            }

            @Mock
            public EntityTransaction getTransaction() {
                return emtMock;
            }
        };

        queryMock = new MockUp<Query>() {
            @Mock
            public List getResultList() {
                List<SurveyDO> rv = new ArrayList<>();
                SurveyDO survey = new JpaSurveyDO();
                survey.setTitle("#test");
                rv.add(survey);
                return rv;
            }
        };
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class JpaSurveyDAO.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        SurveyDO survey = null;
        JpaSurveyDAO instance = new JpaSurveyDAO();
        instance.add(survey);
    }

    /**
     * Test of delete method, of class JpaSurveyDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String id = "";
        JpaSurveyDAO instance = new JpaSurveyDAO();
        instance.delete(id);
    }

    /**
     * Test of update method, of class JpaSurveyDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        SurveyDO survey = null;
        JpaSurveyDAO instance = new JpaSurveyDAO();
        instance.update(survey);
    }

    /**
     * Test of get method, of class JpaSurveyDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String id = "";
        JpaSurveyDAO instance = new JpaSurveyDAO();
        SurveyDO expResult = null;
        SurveyDO result = instance.get(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createSurvey method, of class JpaSurveyDAO.
     */
    @Test
    public void testCreateSurvey() {
        System.out.println("createSurvey");
        JpaSurveyDAO instance = new JpaSurveyDAO();
        SurveyDO expResult = null;
        SurveyDO result = instance.createSurvey();
        assertTrue(result instanceof JpaSurveyDO);
    }

    /**
     * Test of createSurveyTarget method, of class JpaSurveyDAO.
     */
    @Test
    public void testCreateSurveyTarget() {
        System.out.println("createSurveyTarget");
        JpaSurveyDAO instance = new JpaSurveyDAO();
        SurveyTargetDO expResult = new JpaSurveyTargetDO();
        SurveyTargetDO result = instance.createSurveyTarget();
        assertEquals(expResult, result);
    }

    /**
     * Test of list method, of class JpaSurveyDAO.
     */
    @Test
    public void testList() {
        System.out.println("list");
        SurveyDO query = null;
        JpaSurveyDAO instance = new JpaSurveyDAO();
        Collection<SurveyDO> expResult = Collections.emptyList();
        Collection<SurveyDO> result = instance.list(query);
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertTrue(result.toArray()[0] instanceof JpaSurveyDO);
        JpaSurveyDO survey = (JpaSurveyDO) result.toArray()[0];
        assertEquals(survey.getTitle(), "#test");
    }

    /**
     * Test of list method, of class JpaSurveyDAO.
     */
    @Test
    public void testListOnJpaException() {
        System.out.println("list");

        emMock = new MockUp<EntityManager>() {
            @Mock
            public Query createQuery(String query) throws IllegalArgumentException {
                throw new IllegalArgumentException();
            }
        };

        SurveyDO query = null;
        JpaSurveyDAO instance = new JpaSurveyDAO();
        Collection<SurveyDO> expResult = Collections.emptyList();
        Collection<SurveyDO> result = instance.list(query);
        assertEquals(expResult, result);
    }
}
