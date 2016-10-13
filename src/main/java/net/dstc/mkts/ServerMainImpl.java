/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author patrizio
 */
public class ServerMainImpl implements ServerMain {

    private static final int SERVER_PORT = 8080;
    private static final String CONTEXT_PATH = "/api/*";

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
            contextPath = CONTEXT_PATH;
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
