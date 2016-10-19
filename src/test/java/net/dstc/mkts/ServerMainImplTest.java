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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eul0860
 */
public class ServerMainImplTest {
    
    public ServerMainImplTest() {
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
     * Test of run method, of class ServerMainImpl.
     */
    @Test
    public void testRun() throws Exception {
        System.out.println("run");
        boolean keepRunning = false;
        ServerMainImpl instance = new ServerMainImpl();
        instance.run(keepRunning);
    }

    /**
     * Test of getContextPath method, of class ServerMainImpl.
     */
    @Test
    public void testGetContextPath() {
        System.out.println("getContextPath");
        ServerMainImpl instance = new ServerMainImpl();
        String expResult = "/api/*";
        String result = instance.getContextPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of setContextPath method, of class ServerMainImpl.
     */
    @Test
    public void testSetContextPath() {
        System.out.println("setContextPath");
        String path = "";
        ServerMainImpl instance = new ServerMainImpl();
        instance.setContextPath(path);
    }

    /**
     * Test of getPort method, of class ServerMainImpl.
     */
    @Test
    public void testGetPort() {
        System.out.println("getPort");
        ServerMainImpl instance = new ServerMainImpl();
        int expResult = 8080;
        int result = instance.getPort();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPort method, of class ServerMainImpl.
     */
    @Test
    public void testSetPort() {
        System.out.println("setPort");
        int port = 0;
        ServerMainImpl instance = new ServerMainImpl();
        instance.setPort(port);
    }    
}
