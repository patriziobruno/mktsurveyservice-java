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

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
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
