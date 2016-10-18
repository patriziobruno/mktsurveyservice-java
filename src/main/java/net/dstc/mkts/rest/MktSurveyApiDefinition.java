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

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.ReaderListener;
import io.swagger.models.Swagger;
import io.swagger.models.auth.OAuth2Definition;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Path;
import net.dstc.mkts.ServerMainImpl;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@SwaggerDefinition(
        info = @Info(
                description = "Marketing Survey API",
                version = "0.0.1",
                title = "demo API",
                contact = @Contact(name = "Patrizio Bruno", email
                        = "desertconsulting@gmail.com", url
                        = "https://github.com/patriziobruno"),
                license = @License(name = "Apache 2.0", url
                        = "http://www.apache.org")),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes
        = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        basePath = ServerMainImpl.CONTEXT_PATH,
        host = "localhost:" + ServerMainImpl.SERVER_PORT
)
@Path("")
public class MktSurveyApiDefinition implements ReaderListener {

    public static final String TOKEN_AUTH_SCHEME = "oauth2";
    public static final String READ_SCOPE = "read";
    public static final String WRITE_SCOPE = "write";

    @Override
    public void beforeScan(Reader reader, Swagger swagger) {
    }

    @Override
    public void afterScan(Reader reader, Swagger swagger) {
        OAuth2Definition tokenScheme = new OAuth2Definition();
        tokenScheme.setFlow("password");
        tokenScheme.
                setTokenUrl("https://" + swagger.getHost() + "/oauth2/token");
        tokenScheme.
                setAuthorizationUrl(String.format("https://%s%s/oauth2/authorize"
                , swagger.getHost() , swagger.getBasePath()));

        Map<String, String> scopes = new HashMap<>();
        scopes.put(READ_SCOPE, "Read my data");
        scopes.put(WRITE_SCOPE, "Update my data");
        tokenScheme.setScopes(scopes);
        swagger.addSecurityDefinition(TOKEN_AUTH_SCHEME, tokenScheme);
    }
}
