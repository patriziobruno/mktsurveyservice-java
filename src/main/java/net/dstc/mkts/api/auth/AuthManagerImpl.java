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
package net.dstc.mkts.api.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.dstc.mkts.rest.auth.NotAuthException;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Resource
@ManagedBean
@Singleton
public class AuthManagerImpl implements AuthManager {

    public final static Logger LOGGER = Logger.getLogger(AuthManagerImpl.class.getName());
    public final List<String> tokens = new ArrayList<>();
    public final List<String> authCodes = new ArrayList<>();

    @Override
    public void assertIsValidToken(HttpServletRequest request) throws
            NotAuthException, OAuthSystemException {
        try {
            // Make the OAuth Request out of this request
            OAuthAccessResourceRequest oauthRequest
                    = new OAuthAccessResourceRequest(request,
                            ParameterStyle.HEADER);
            // Get the access token
            String accessToken = oauthRequest.getAccessToken();

            // Validate the access token
            if (!isValidToken(accessToken)) {
                // Return the OAuth error message
                OAuthResponse oauthResponse = OAuthRSResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setRealm("mktsurvey")
                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                        .buildHeaderMessage();
                throw new NotAuthException(oauthResponse.getHeader(
                        OAuth.HeaderType.WWW_AUTHENTICATE));
            }
        } catch (OAuthProblemException e) {
            // Check if the error code has been set
            String errorCode = e.getError();
            if (OAuthUtils.isEmpty(errorCode)) {

                try {
                    // Return the OAuth error message
                    OAuthResponse oauthResponse = OAuthRSResponse
                            .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                            .setRealm("mktsurvey")
                            .buildHeaderMessage();

                    throw new NotAuthException(oauthResponse.getHeader(
                            OAuth.HeaderType.WWW_AUTHENTICATE));
                } catch (OAuthSystemException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }

            OAuthResponse oauthResponse;
            try {
                oauthResponse
                        = OAuthRSResponse
                                .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                                .setRealm("mktsurvey")
                                .setError(e.getError())
                                .setErrorDescription(e.getDescription())
                                .setErrorUri(e.getUri())
                                .buildHeaderMessage();

                throw new NotAuthException(oauthResponse.getHeader(
                        OAuth.HeaderType.WWW_AUTHENTICATE));

            } catch (OAuthSystemException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                throw ex;
            }
        } catch (OAuthSystemException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void addToken(String token) {
        tokens.add(token);
    }

    @Override
    public void addAuthCode(String authCode) {
        authCodes.add(authCode);
    }

    @Override
    public boolean isValidAuthCode(String authCode) {
        return authCodes.contains(authCode);
    }

    @Override
    public boolean isValidToken(String token) {
        return tokens.contains(token);
    }
}
