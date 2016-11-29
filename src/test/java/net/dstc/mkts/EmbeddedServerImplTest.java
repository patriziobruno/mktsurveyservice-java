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

import mockit.MockUp;
import net.dstc.mkts.config.ServerSettings;
import net.dstc.mkts.config.ServerSettingsImpl;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {@link EmbeddedServerImpl}
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public class EmbeddedServerImplTest {

    public EmbeddedServerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setSettings method, of class EmbeddedServerImpl.
     */
    @Test
    public void testSetSettings() {
        System.out.println("setSettings");
        ServerSettings settings = new ServerSettingsImpl();
        EmbeddedServerImpl instance = new EmbeddedServerImpl(null, settings);
        instance.setSettings(settings);
        assertEquals(settings, instance.getSettings());
    }

    /**
     * Test of preDestroy method, of class EmbeddedServerImpl.
     */
    @Test
    public void testPreDestroy() {
        new MockUp<Server>() {};
        System.out.println("preDestroy");
        EmbeddedServerImpl instance = new EmbeddedServerImpl(null, null);
        instance.preDestroy();
    }

}
