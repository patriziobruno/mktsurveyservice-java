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
package net.dstc.mkts;

import java.io.IOException;
import java.util.Properties;
import net.dstc.mkts.api.auth.AuthenticationFilter;
import net.dstc.mkts.config.ApiBinder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public class MktSurveyApplication extends ResourceConfig {

    public MktSurveyApplication() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/configuration.properties"));
        register(new ApiBinder(props));
        register(AuthenticationFilter.class);
        packages(true, "net.dstc.mkts.rest",
                "net.dstc.mkts.rest.auth",
                "io.swagger.jaxrs.listing", "io.swagger.jaxrs.config");
    }
}
