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
package net.dstc.mkts.config;

import javax.annotation.Nonnull;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import net.dstc.mkts.EmbeddedServer;

/**
 * Interface defining classes that gives settings to {@link EmbeddedServer} implementations
 * 
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public interface ServerSettings {

    /**
     * Returns a string containing the context path for the Jersey servlet
     * @return a string containing the context path for the Jersey servlet
     */
    String getContextPath();

    /**
     * Sets the context path for the Jersey servlet
     * @param contextPath context path for the Jersey servlet
     */
    void setContextPath(@Nonnull String contextPath);

    /**
     * Gets the port used by the Jetty server
     * @return port used by the Jetty server
     */
    int getPort();

    /**
     * Sets the port to be used by the Jetty server
     * @param port port to be used by the Jetty server
     */
    void setPort(@DecimalMin("1024") @DecimalMax("65535") int port);
}
