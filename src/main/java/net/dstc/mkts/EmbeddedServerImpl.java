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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import net.dstc.mkts.config.ServerSettings;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.hk2.api.PreDestroy;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Service
@Contract
public class EmbeddedServerImpl implements EmbeddedServer, PreDestroy {

    private static final Logger LOGGER = Logger.getLogger(EmbeddedServerImpl.class.getName());

    private ServerSettings settings;

    private Server jettyServer;

    private final ServiceLocator serviceLocator;

    @Inject
    public EmbeddedServerImpl(ServiceLocator serviceLocator, ServerSettings settings) {
        this.serviceLocator = serviceLocator;
        this.settings = settings;
    }

    @Override
    public void run() throws Exception {
        run(true);
    }

    @Override
    public void run(boolean join) throws Exception {
        if (jettyServer == null || !jettyServer.isRunning()) {
            ServletContextHandler context = new ServletContextHandler(
                    ServletContextHandler.SESSIONS);
            String webDir = getClass().getClassLoader().getResource("html").
                    toExternalForm();
            Logger.getLogger(getClass().getName()).log(Level.INFO, webDir);
            context.setResourceBase(webDir);
            context.setContextPath("/");

            jettyServer = new org.eclipse.jetty.server.Server(settings.getPort());
            jettyServer.setHandler(context);

            final ServletContainer servletContainer = new ServletContainer(
                    ResourceConfig.forApplicationClass(MktSurveyApplication.class)) {
                @Override
                public void init()
                        throws ServletException {
                    getServletContext().setAttribute(ServletProperties.SERVICE_LOCATOR, serviceLocator);
                    super.init();
                }
            };

            ServletHolder jerseyServlet = new ServletHolder(servletContainer);
            jerseyServlet.setInitOrder(0);

            context.getServletHandler().addServletWithMapping(
                    jerseyServlet,
                    settings.getContextPath());
            DefaultServlet defaultServlet = new DefaultServlet();
            ServletHolder holderPwd = new ServletHolder("default",
                    defaultServlet);
            holderPwd.setInitParameter("resourceBase", webDir);
            holderPwd.setInitParameter("dirAllowed", "true");
            context.addServlet(holderPwd, "/*");

            try {
                jettyServer.start();
                if (join) {
                    jettyServer.join();
                }
            } finally {
                if (join) {
                    jettyServer.destroy();
                    jettyServer = null;
                }
            }
        }
    }

    @Override
    public void stop() throws Exception {
        try {
            if (jettyServer != null && jettyServer.isRunning()) {
                jettyServer.stop();
            }
        } finally {
            if (jettyServer != null) {
                jettyServer.destroy();
                jettyServer = null;
            }
        }
    }

    public ServerSettings getSettings() {
        return settings;
    }

    public void setSettings(ServerSettings settings) {
        this.settings = settings;
    }

    @Override
    public void preDestroy() {
        try {
            stop();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
