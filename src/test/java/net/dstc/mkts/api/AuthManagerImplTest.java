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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import net.dstc.mkts.rest.auth.NotAuthException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public class AuthManagerImplTest {

    public AuthManagerImplTest() {
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
    HttpServletRequest request;
    
    private static final String VALID_TOKEN = "8fce334ce9dd6e06a58a7c6e4ef5e8af";
    private static final String NOT_VALID_TOKEN = "not_valid_token";

    /**
     * Test of assertIsValidToken method, of class AuthManagerImpl.
     */
    @Test
    public void testAssertIsValidToken() {
        new Expectations() {{
            request.getHeader("Authorization"); returns("Bearer " + VALID_TOKEN);
        }};
        System.out.println("assertIsValidToken");
        AuthManagerImpl instance = new AuthManagerImpl();
        instance.addToken(VALID_TOKEN);
        instance.assertIsValidToken(request);
    }

    /**
     * Test of assertIsValidToken method, of class AuthManagerImpl.
     */
    @Test(expected = NotAuthException.class)
    public void testAssertIsValidTokenThrowsNotAuthException() {
        new Expectations() {{
            request.getHeader("Authorization"); returns("Bearer " + NOT_VALID_TOKEN);
        }};
        System.out.println("assertIsValidToken");
        AuthManagerImpl instance = new AuthManagerImpl();
        instance.addToken(VALID_TOKEN);
        instance.assertIsValidToken(request);
    }

    /**
     * Test of addToken method, of class AuthManagerImpl.
     */
    @Test
    public void testAddToken() {
        System.out.println("addToken");
        AuthManagerImpl instance = new AuthManagerImpl();
        instance.addToken(VALID_TOKEN);
    }

    /**
     * Test of addAuthCode method, of class AuthManagerImpl.
     */
    @Test
    public void testAddAuthCode() {
        System.out.println("addAuthCode");
        String authCode = "valid_code";
        AuthManagerImpl instance = new AuthManagerImpl();
        instance.addAuthCode(authCode);
    }

    /**
     * Test of isValidAuthCode method, of class AuthManagerImpl.
     */
    @Test
    public void testIsValidAuthCode() {
        System.out.println("isValidAuthCode");
        String authCode = "valid_code";
        AuthManagerImpl instance = new AuthManagerImpl();
        instance.addAuthCode(authCode);
        boolean expResult = true;
        boolean result = instance.isValidAuthCode(authCode);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidToken method, of class AuthManagerImpl.
     */
    @Test
    public void testIsValidToken() {
        System.out.println("isValidToken");
        String token = VALID_TOKEN;
        AuthManagerImpl instance = new AuthManagerImpl();
        instance.addToken(VALID_TOKEN);
        boolean expResult = true;
        boolean result = instance.isValidToken(token);
        assertEquals(expResult, result);
    }

}
