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
package net.dstc.mkts.api;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import mockit.Injectable;
import mockit.Tested;
import net.dstc.mkts.data.SurveyDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eul0860
 */
public class MarketingSurveyApiImplTest {
    
    @Injectable
    private SurveyDAO dao;
    
    @Tested
    private MarketingSurveyApiImpl instance;
    
    public MarketingSurveyApiImplTest() {
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

    /**
     * Test of getList method, of class MarketingSurveyApiImpl.
     */
    @Test
    public void testGetListWithNullQuery() {
        System.out.println("getList");
        SurveyDTO query = null;
        Collection<SurveyDTO> expResult = Collections.emptyList();
        Collection<SurveyDTO> result = instance.getList(query);
        assertEquals(expResult, result);
    }

    /**
     * Test of getList method, of class MarketingSurveyApiImpl.
     */
    @Test
    public void testGetList() {
        System.out.println("getList");
        SurveyDTO query = new SurveyDTO();
        Collection<SurveyDTO> expResult = Collections.emptyList();
        Collection<SurveyDTO> result = instance.getList(query);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class MarketingSurveyApiImpl.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String id = "/survey/1";
        SurveyDTO expResult = new SurveyDTO();
        SurveyDTO expResult2 = new SurveyDTO();
        assertEquals(expResult2, expResult);
        assertNotEquals(expResult, "");
        
        SurveyDTO result = instance.get(id);
        assertNotEquals(null, result);
        assertNotEquals(result, null);
        assertEquals(result, result);
        assertNotEquals(expResult, result);
    }
    
    @Test
    public void testSurveyDTOEquals() {
        String id = "/survey/1";
        SurveyDTO survey1 = new SurveyDTO();
        SurveyDTO survey2 = new SurveyDTO();
        SurveyDTO survey3 = new SurveyDTO();
        
        survey1.setId(id);
        assertNotEquals(survey1, survey3);
        
        survey1.setId(survey3.getId());
        survey1.setTitle("test");
        assertNotEquals(survey1, survey3);
        
        survey1.setId(survey2.getId());
        survey1.setTitle(survey2.getTitle());
        survey1.setStartDate(new Date());
        assertNotEquals(survey2, survey1);
        survey1.setStartDate(survey2.getStartDate());
        survey1.setTarget(new SurveyTargetDTO());
        assertNotEquals(survey2, survey1);
    }
    
    @Test
    public void testSurveyTargetDTOEquals() {
        SurveyTargetDTO target1 = new SurveyTargetDTO();
        assertEquals(target1, target1);
        
        SurveyTargetDTO target2 = new SurveyTargetDTO();
        assertEquals(target1, target2);
        
        target2.setGender("M");
        assertNotEquals(target1, target2);
        
        target2.setGender(null);
        target2.setCountry("US");
        assertNotEquals(target1, target2);
        
        target2.setCountry(null);
        target2.setAgeRange(new int[]{0});
        assertNotEquals(target1, target2);
        
        target2.setAgeRange(null);
        target2.setIncomeRange(new int[]{0});
        assertNotEquals(target1, target2);
        
        assertNotEquals(target1, null);
        assertNotEquals(null, target2);
        
        assertNotEquals(target1, "");
    }

    /**
     * Test of get method, of class MarketingSurveyApiImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetWithNullId() {
        System.out.println("get");
        String id = null;
        SurveyDTO expResult = new SurveyDTO();
        SurveyDTO result = instance.get(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of insert method, of class MarketingSurveyApiImpl.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        SurveyDTO survey = new SurveyDTO();
        SurveyTargetDTO target = new SurveyTargetDTO();
        target.setAgeRange(new int[]{30, 60});
        target.setIncomeRange(new int[]{68000, 88000});
        survey.setTarget(target);
        instance.insert(survey);
    }

    /**
     * Test of update method, of class MarketingSurveyApiImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        SurveyDTO survey = new SurveyDTO();
        instance.update(survey);
    }

    /**
     * Test of delete method, of class MarketingSurveyApiImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String id = "/survey/1";
        instance.delete(id);
    }

    /**
     * Test of delete method, of class MarketingSurveyApiImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWithNullId() {
        System.out.println("delete");
        String id = null;
        instance.delete(id);
    }

    /**
     * Test of delete method, of class MarketingSurveyApiImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWithEmptyId() {
        System.out.println("delete");
        String id = "";
        instance.delete(id);
    }

    /**
     * Test of delete method, of class MarketingSurveyApiImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWithBlankId() {
        System.out.println("delete");
        String id = " ";
        instance.delete(id);
    }
}
