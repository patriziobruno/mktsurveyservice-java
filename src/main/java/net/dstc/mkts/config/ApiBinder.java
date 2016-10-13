/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.config;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author eul0860
 */
public class ApiBinder extends AbstractBinder {

    private final Properties bindings;

    public ApiBinder(Properties properties) {
        this.bindings = properties;
    }

    @Override
    protected void configure() {
        ClassLoader loader = getClass().getClassLoader();
        bindings.forEach((Object bindingInterface, Object bindingClass) -> {

            try {
                if (!bindingInterface.toString().endsWith(".singleton")) {
                    Class iface = loader.loadClass(bindingInterface.toString());
                    Class cl = loader.loadClass(bindingClass.toString());
                    boolean singleton = Boolean.parseBoolean(bindings.
                            getProperty(
                                    bindingInterface + ".singleton"));

                    if (singleton) {
                        bind(cl.newInstance()).to(iface);
                    } else {
                        bind(cl).to(iface);
                    }
                }
            } catch (ClassNotFoundException | InstantiationException |
                    IllegalAccessException ex) {
                Logger.getLogger(ApiBinder.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        });
    }
}
