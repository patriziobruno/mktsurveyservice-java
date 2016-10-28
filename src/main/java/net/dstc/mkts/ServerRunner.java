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

import java.util.logging.Logger;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

/**
 *
 * @author eul0860
 */
public class ServerRunner {

    private static final Logger LOGGER = Logger.getLogger(ServerRunner.class.getName());

    private static final ServiceLocator SERVICE_LOCATOR
            = ServiceLocatorFactory.getInstance().create(null);

    private final EmbeddedServer service;

    public ServerRunner(String[] args) throws Exception {
        ServiceLocatorUtilities.bind(SERVICE_LOCATOR, new ServerBinder());
        service = SERVICE_LOCATOR.getService(EmbeddedServer.class);
    }

    public void run()
            throws Exception {
        LOGGER.info("Starting");

        service.run();
    }

    public void run(boolean join)
            throws Exception {
        LOGGER.info("Starting");

        service.run(join);

        LOGGER.info("Started");
    }

    public void stop() throws Exception {
        LOGGER.info("Stopping");

        service.stop();

        LOGGER.info("Stopped");
    }
}
