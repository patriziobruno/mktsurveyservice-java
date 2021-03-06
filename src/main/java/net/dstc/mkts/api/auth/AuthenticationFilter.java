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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

/**
 *
 * @author patrizio
 */
@Protected
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest request;
    
    @Inject
    private AuthManager authManager;

    @Override
    public void filter(ContainerRequestContext requestContext) throws
            IOException {
        try {
            authManager.assertIsValidToken(request);
        } catch (OAuthSystemException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
}
