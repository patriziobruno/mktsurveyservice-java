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

import net.dstc.mkts.EmbeddedServer;
import net.dstc.mkts.EmbeddedServerImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author eul0860
 */
public class ServerBinder extends AbstractBinder {

    @Override
    protected void configure() {
        final ServerSettings serverSettings = new ServerSettingsImpl();
        
        bind(serverSettings).to(ServerSettings.class);
        bind(EmbeddedServerImpl.class).to(EmbeddedServer.class);

        addActiveDescriptor(EmbeddedServerImpl.class);
    }

}
