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
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public class ServerMainImpl implements ServerMain {

    public static final int SERVER_PORT = 8080;
    public static final String CONTEXT_PATH = "/api";

    @Override
    public void run() throws Exception {
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        String webDir = getClass().getClassLoader().getResource("html").
                toExternalForm();
        Logger.getLogger(getClass().getName()).log(Level.INFO, webDir);
        context.setResourceBase(webDir);
        context.setContextPath("/");

        org.eclipse.jetty.server.Server jettyServer
                = new org.eclipse.jetty.server.Server(getPort());
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class,
                getContextPath());
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("javax.ws.rs.Application",
                MktSurveyApplication.class.getCanonicalName());

        DefaultServlet defaultServlet = new DefaultServlet();
        ServletHolder holderPwd = new ServletHolder("default", defaultServlet);
        holderPwd.setInitParameter("resourceBase", webDir);
        holderPwd.setInitParameter("dirAllowed", "true");
        context.addServlet(holderPwd, "/*");

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }

    }

    private String contextPath;

    @Override
    public String getContextPath() {
        if (StringUtils.isEmpty(contextPath)) {
            contextPath = CONTEXT_PATH + "/*";
        }
        return contextPath;
    }

    @Override
    public void setContextPath(String path) {
        contextPath = path;
    }

    private int port;

    @Override
    public int getPort() {
        if (port == 0) {
            port = SERVER_PORT;
        }
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }
}
