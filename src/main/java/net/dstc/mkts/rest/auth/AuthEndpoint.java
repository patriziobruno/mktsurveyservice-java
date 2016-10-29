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
package net.dstc.mkts.rest.auth;

import java.net.URI;
import java.net.URISyntaxException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import net.dstc.mkts.api.AuthManager;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Path("oauth2")
public class AuthEndpoint {

    @Inject
    private AuthManager authManager;

    @GET
    @Path("authorize")
    public Response authorize(@Context HttpServletRequest request)
            throws URISyntaxException, OAuthSystemException,
            OAuthProblemException {
        try {
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(
                    new MD5Generator());

            //build response according to response_type
            String responseType = oauthRequest.getParam(
                    OAuth.OAUTH_RESPONSE_TYPE);

            OAuthASResponse.OAuthAuthorizationResponseBuilder builder
                    = OAuthASResponse.authorizationResponse(request,
                            HttpServletResponse.SC_FOUND);

            if (responseType.equals(ResponseType.CODE.toString())) {
                final String authorizationCode = oauthIssuerImpl.
                        authorizationCode();
                authManager.addAuthCode(authorizationCode);
                builder.setCode(authorizationCode);
            }
            if (responseType.equals(ResponseType.TOKEN.toString())) {
                final String accessToken = oauthIssuerImpl.accessToken();
                authManager.addToken(accessToken);

                builder.setAccessToken(accessToken);
                builder.setExpiresIn(3600l);
            }

            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
            final OAuthResponse response = builder.location(redirectURI).
                    buildQueryMessage();
            URI url = new URI(response.getLocationUri());
            return Response.status(response.getResponseStatus()).location(url).
                    build();
        } catch (OAuthProblemException e) {
            final Response.ResponseBuilder responseBuilder = Response.status(
                    HttpServletResponse.SC_FOUND);
            String redirectUri = e.getRedirectUri();

            if (OAuthUtils.isEmpty(redirectUri)) {
                throw new WebApplicationException(
                        responseBuilder.entity(
                                "OAuth callback url needs to be provided by client!!!").
                                build());
            }
            final OAuthResponse response = OAuthASResponse.errorResponse(
                    HttpServletResponse.SC_FOUND)
                    .error(e).location(redirectUri).buildQueryMessage();
            final URI location = new URI(response.getLocationUri());
            return responseBuilder.location(location).build();
        }
    }

    public static final String INVALID_CLIENT_DESCRIPTION = "Client authentication failed (e.g., unknown client, no client authentication included, or unsupported authentication method).";

    @Path("token")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response token(@Context HttpServletRequest request) throws OAuthSystemException {
        try {
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

            // check if clientid is valid
            if (!checkClientId(oauthRequest.getClientId())) {
                return buildInvalidClientIdResponse();
            }

            // check if client_secret is valid
            if (!checkClientSecret(oauthRequest.getClientSecret())) {
                return buildInvalidClientSecretResponse();
            }

            // do checking for different grant types
            if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                if (!authManager.isValidAuthCode(oauthRequest.getParam(OAuth.OAUTH_CODE))) {
                    return buildBadAuthCodeResponse();
                }
            } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.PASSWORD.toString())) {
                if (!checkUserPass(oauthRequest.getUsername(), oauthRequest.getPassword())) {
                    return buildInvalidUserPassResponse();
                }
            } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.REFRESH_TOKEN.toString())) {
                // refresh token is not supported in this implementation
                return buildInvalidUserPassResponse();
            }

            final String accessToken = oauthIssuerImpl.accessToken();
            authManager.addToken(accessToken);

            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setExpiresIn("3600")
                    .buildJSONMessage();
            return Response.status(response.getResponseStatus()).entity(response.getBody()).build();

        } catch (OAuthProblemException e) {
            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
                    .buildJSONMessage();
            return Response.status(res.getResponseStatus()).entity(res.getBody()).build();
        }
    }

    private Response buildInvalidClientIdResponse() throws OAuthSystemException {
        OAuthResponse response
                = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                        .setErrorDescription(INVALID_CLIENT_DESCRIPTION)
                        .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    private Response buildInvalidClientSecretResponse() throws OAuthSystemException {
        OAuthResponse response
                = OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT).setErrorDescription(INVALID_CLIENT_DESCRIPTION)
                        .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    private Response buildBadAuthCodeResponse() throws OAuthSystemException {
        OAuthResponse response = OAuthASResponse
                .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setError(OAuthError.TokenResponse.INVALID_GRANT)
                .setErrorDescription("invalid authorization code")
                .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    private Response buildInvalidUserPassResponse() throws OAuthSystemException {
        OAuthResponse response = OAuthASResponse
                .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setError(OAuthError.TokenResponse.INVALID_GRANT)
                .setErrorDescription("invalid username or password")
                .buildJSONMessage();
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    private boolean checkClientId(String clientId) {
        return "test".equals(clientId);
    }

    private boolean checkClientSecret(String secret) {
        return "test".equals(secret);
    }

    private boolean checkUserPass(String user, String pass) {
        return "test".equals(pass) && "test".equals(user);
    }
}
