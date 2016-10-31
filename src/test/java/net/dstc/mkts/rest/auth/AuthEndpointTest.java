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
package net.dstc.mkts.rest.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;
import net.dstc.mkts.api.auth.AuthManager;
import org.apache.oltu.oauth2.as.request.OAuthRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
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
public class AuthEndpointTest {

    public AuthEndpointTest() {
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
    private HttpServletRequest request;

    @Injectable
    private AuthManager authManager;

    @Tested
    private AuthEndpoint instance;

    private static final String VALID_TOKEN = "8fce334ce9dd6e06a58a7c6e4ef5e8af";
    private static final String NOT_VALID_TOKEN = "not_valid_token";

    /**
     * Test of authorize method, of class AuthEndpoint.
     */
    @Test
    public void testAuthorize() throws Exception {
        System.out.println("authorize");
        new Expectations() {
            {
//                request.getHeader("Authorization");
//                returns("Bearer " + VALID_TOKEN);
//
                request.getParameter(OAuth.OAUTH_RESPONSE_TYPE);
                returns(OAuth.OAUTH_CODE);

                request.getParameter(OAuth.OAUTH_CLIENT_ID);
                returns("test");

//                request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
//                returns("test");
//                
                request.getParameter(OAuth.OAUTH_REDIRECT_URI);
                returns("done");
//                
//                request.getParameter(OAuth.OAUTH_GRANT_TYPE);
//                returns (GrantType.AUTHORIZATION_CODE.toString());

                request.getMethod();
                returns("GET");

//                request.getContentType();
//                returns(MediaType.APPLICATION_FORM_URLENCODED);
            }
        };

        Response result = instance.authorize(request);
        assertEquals("done?code=", result.getHeaderString("Location").substring(
                0, 10));
    }

    /**
     * Test of authorize method, of class AuthEndpoint.
     */
    @Test(expected = WebApplicationException.class)
    public void testAuthorizeHandleOAuthProblemExceptionNoRedirectUri() throws
            Exception {
        System.out.println("authorize");
        new MockUp<OAuthRequest>() {
            @Mock
            public void $init(HttpServletRequest request) throws
                    OAuthSystemException, OAuthProblemException {
                throw OAuthProblemException.error("test");
            }
        };

        Response result = instance.authorize(request);
    }

    /**
     * Test of authorize method, of class AuthEndpoint.
     */
    @Test
    public void testAuthorizeHandleOAuthProblemException() throws Exception {
        System.out.println("authorize");
        new MockUp<OAuthRequest>() {
            @Mock
            public void $init(HttpServletRequest request) throws
                    OAuthSystemException, OAuthProblemException {
                OAuthProblemException exc = OAuthProblemException.error("test");
                exc.setRedirectUri("done");
                throw exc;
            }
        };

        Response result = instance.authorize(request);
        assertEquals(HttpServletResponse.SC_MOVED_TEMPORARILY, result.
                getStatus());
        assertEquals("done?error=", result.getHeaderString("Location").
                substring(0, 11));
    }

    /**
     * Test of token method, of class AuthEndpoint.
     */
    @Test
    public void testToken() throws Exception {
        System.out.println("token");
        new Expectations() {
            {
//                request.getHeader("Authorization");
//                returns("Bearer " + VALID_TOKEN);
//
                request.getParameter(OAuth.OAUTH_USERNAME);
                returns("test");

                request.getParameter(OAuth.OAUTH_PASSWORD);
                returns("test");

                request.getParameter(OAuth.OAUTH_CLIENT_ID);
                returns("test");

                request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
                returns("test");

                request.getParameter(OAuth.OAUTH_GRANT_TYPE);
                returns(GrantType.PASSWORD.toString());

                request.getMethod();
                returns("POST");

                request.getContentType();
                returns(MediaType.APPLICATION_FORM_URLENCODED);
            }
        };
        Response result = instance.token(request);
        assertEquals(200, result.getStatus());
    }

    /**
     * Test of token method, of class AuthEndpoint.
     */
    @Test
    public void testTokenInvalidUserPass() throws Exception {
        System.out.println("token");
        new Expectations() {
            {
//                request.getHeader("Authorization");
//                returns("Bearer " + VALID_TOKEN);
//
                request.getParameter(OAuth.OAUTH_USERNAME);
                returns("wronguser");

                request.getParameter(OAuth.OAUTH_PASSWORD);
                returns("wrongpass");

                request.getParameter(OAuth.OAUTH_CLIENT_ID);
                returns("test");

                request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
                returns("test");

                request.getParameter(OAuth.OAUTH_GRANT_TYPE);
                returns(GrantType.PASSWORD.toString());

                request.getMethod();
                returns("POST");

                request.getContentType();
                returns(MediaType.APPLICATION_FORM_URLENCODED);
            }
        };
        Response result = instance.token(request);
        assertEquals(400, result.getStatus());
    }

    /**
     * Test of token method, of class AuthEndpoint.
     */
    @Test
    public void testTokenTokenRefreshNotSupported() throws Exception {
        System.out.println("token");
        new Expectations() {
            {
                request.getParameter(OAuth.OAUTH_CLIENT_ID);
                returns("test");

                request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
                returns("test");

                request.getParameter(OAuth.OAUTH_REFRESH_TOKEN);
                returns(NOT_VALID_TOKEN);
                
                request.getParameter(OAuth.OAUTH_GRANT_TYPE);
                returns(GrantType.REFRESH_TOKEN.toString());

                request.getMethod();
                returns("POST");

                request.getContentType();
                returns(MediaType.APPLICATION_FORM_URLENCODED);
            }
        };
        Response result = instance.token(request);
        assertEquals(400, result.getStatus());
    }

    /**
     * Test of token method, of class AuthEndpoint.
     */
    @Test
    public void testTokenOAuthProblemException() throws Exception {
        System.out.println("token");
        new MockUp<OAuthRequest>() {
            @Mock
            public void $init(HttpServletRequest request) throws
                    OAuthSystemException, OAuthProblemException {
                OAuthProblemException exc = OAuthProblemException.error("test");
                exc.setRedirectUri("done");
                throw exc;
            }
        };

        Response result = instance.token(request);
        assertEquals(400, result.getStatus());
    }

    /**
     * Test of token method, of class AuthEndpoint.
     */
    @Test
    public void testTokenAuthCodeNotValid() throws Exception {
        System.out.println("token");
        new Expectations() {
            {
                request.getParameter(OAuth.OAUTH_CLIENT_ID);
                returns("test");

                request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
                returns("test");

                request.getParameter(OAuth.OAUTH_CODE);
                returns(NOT_VALID_TOKEN);

                request.getParameter(OAuth.OAUTH_GRANT_TYPE);
                returns(GrantType.AUTHORIZATION_CODE.toString());

                request.getParameter(OAuth.OAUTH_REDIRECT_URI);
                returns("done");

                request.getMethod();
                returns("POST");

                request.getContentType();
                returns(MediaType.APPLICATION_FORM_URLENCODED);

                authManager.isValidAuthCode(NOT_VALID_TOKEN);
                returns(false);
            }
        };

        Response result = instance.token(request);
        assertEquals(400, result.getStatus());
    }

    /**
     * Test of token method, of class AuthEndpoint.
     */
    @Test
    public void testTokenClientIdNotValid() throws Exception {
        System.out.println("token");
        new Expectations() {
            {
                request.getParameter(OAuth.OAUTH_CLIENT_ID);
                returns("wrongclientid");

                request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
                returns("test");

                request.getParameter(OAuth.OAUTH_CODE);
                returns(NOT_VALID_TOKEN);

                request.getParameter(OAuth.OAUTH_GRANT_TYPE);
                returns(GrantType.AUTHORIZATION_CODE.toString());

                request.getParameter(OAuth.OAUTH_REDIRECT_URI);
                returns("done");

                request.getMethod();
                returns("POST");

                request.getContentType();
                returns(MediaType.APPLICATION_FORM_URLENCODED);
            }
        };

        Response result = instance.token(request);
        assertEquals(400, result.getStatus());
    }

    /**
     * Test of token method, of class AuthEndpoint.
     */
    @Test
    public void testTokenClientSecretNotValid() throws Exception {
        System.out.println("token");
        new Expectations() {
            {
                request.getParameter(OAuth.OAUTH_CLIENT_ID);
                returns("test");

                request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
                returns("wrongclientsecret");

                request.getParameter(OAuth.OAUTH_CODE);
                returns(NOT_VALID_TOKEN);

                request.getParameter(OAuth.OAUTH_GRANT_TYPE);
                returns(GrantType.AUTHORIZATION_CODE.toString());

                request.getParameter(OAuth.OAUTH_REDIRECT_URI);
                returns("done");

                request.getMethod();
                returns("POST");

                request.getContentType();
                returns(MediaType.APPLICATION_FORM_URLENCODED);
            }
        };

        Response result = instance.token(request);
        assertEquals(401, result.getStatus());
    }
}
