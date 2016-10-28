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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Service
@Contract
public class ServerSettingsImpl implements ServerSettings {

    public static final int SERVER_PORT = 8080;
    public static final String CONTEXT_PATH = "/api";

    private String contextPath;
    private int port;

    @Override
    public String getContextPath() {
        if (StringUtils.isEmpty(contextPath)) {
            contextPath = CONTEXT_PATH + "/*";
        }
        return contextPath;
    }

    @Override
    public void setContextPath(@Nonnull String contextPath) {
        Validate.notEmpty(contextPath);

        this.contextPath = contextPath;
    }

    @Override
    public int getPort() {
        if (port == 0) {
            port = SERVER_PORT;
        }
        return port;
    }

    @Override
    public void setPort(@DecimalMin("1024") @DecimalMax("65535") int port) {
        Validate.validState(port >= 1024 && port < 644536);
        this.port = port;
    }
}
