/*
 * Copyright 2016 patrizio.
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
package net.dstc.mkts.rest;

import java.util.Collection;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import net.dstc.mkts.api.AuthManager;
import net.dstc.mkts.api.MarketingSurveyApi;
import net.dstc.mkts.api.SurveyDTO;
import net.dstc.mkts.rest.auth.NotAuthException;
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
public class MktSurveyServiceTest {

    @Mocked
    private HttpServletResponse response;

    @Mocked
    private HttpServletRequest request;

    @Injectable
    private AuthManager authManager;

    @Injectable
    private MarketingSurveyApi api;

    @Tested
    private MktSurveyService instance;

    public MktSurveyServiceTest() {
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
     * Test of getSurveys method, of class MktSurveyService.
     */
    @Test
    public void testGetSurveys() throws Exception {
        System.out.println("getSurveys");
        String filter = "";
        HttpServletRequest request = null;
        Collection<SurveyDTO> expResult = Collections.emptyList();
        Collection<SurveyDTO> result
                = instance.getSurveys(filter, request, response);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSurveys method, of class MktSurveyService.
     */
    @Test
    public void testGetSurveysWithQuery() throws Exception {
        System.out.println("getSurveys");
        String filter = "{\"title\":\"test\"}";
        HttpServletRequest request = null;
        Collection<SurveyDTO> expResult = Collections.emptyList();
        Collection<SurveyDTO> result
                = instance.getSurveys(filter, request, response);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class MktSurveyService.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        SurveyDTO survey = null;
        HttpServletRequest request = null;
        instance.update(survey, request, response);
    }

    /**
     * Test of insert method, of class MktSurveyService.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        SurveyDTO survey = new SurveyDTO();
        HttpServletRequest request = null;
        instance.insert(survey, request, response);
    }

    /**
     * Test of insert method, of class MktSurveyService.
     */
    @Test(expected = NotAuthException.class)
    public void testInsertNotAuthenticated() throws Exception {
        new Expectations() {
            {
                authManager.assertIsValidToken(request);
                result = new NotAuthException(anyString);
            }
        };
        System.out.println("insert");
        SurveyDTO survey = new SurveyDTO();
        instance.insert(survey, request, response);
    }
}
