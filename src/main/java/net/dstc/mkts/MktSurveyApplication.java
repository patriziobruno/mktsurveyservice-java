/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts;

import java.io.IOException;
import java.util.Properties;
import net.dstc.mkts.config.ApiBinder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author eul0860
 */
public class MktSurveyApplication extends ResourceConfig {

    public MktSurveyApplication() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/configuration.properties"));
        register(new ApiBinder(props));

        packages(true, "net.dstc.mkts.rest",
                "net.dstc.mkts.rest.auth",
                "io.swagger.jaxrs.listing", "io.swagger.jaxrs.config");
    }
}
