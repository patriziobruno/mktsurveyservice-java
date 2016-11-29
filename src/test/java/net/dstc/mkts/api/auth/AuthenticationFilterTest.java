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
package net.dstc.mkts.api.auth;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import net.dstc.mkts.rest.auth.NotAuthException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public class AuthenticationFilterTest {

    @Injectable
    private AuthManager authManager;

    @Tested
    AuthenticationFilter instance;

    @Mocked
    private ContainerRequestContext requestContext;

    @Injectable
    private HttpServletRequest request;

    public AuthenticationFilterTest() {
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
     * Test of filter method, of class AuthenticationFilter.
     */
    @Test
    public void testFilter()
            throws IOException {
        System.out.println("filter");
        instance.filter(requestContext);
    }

    /**
     * Test of filter method, of class AuthenticationFilter.
     */
    @Test(expected = NotAuthException.class)
    public void testFilterNoAuth()
            throws NotAuthException, IOException, OAuthSystemException {
        System.out.println("filter");
        new Expectations() {
            {
                authManager.assertIsValidToken(request);
                result = new NotAuthException("test");
            }
        };
        instance.filter(requestContext);
    }

    /**
     * Test of filter method, of class AuthenticationFilter.
     */
    @Test
    public void testFilterOAuthSystemException() throws
            NotAuthException, IOException, OAuthSystemException {
        System.out.println("filter");
        new Expectations() {
            {
                authManager.assertIsValidToken(request);
                result = new OAuthSystemException();
            }
        };
        instance.filter(requestContext);
    }
}
