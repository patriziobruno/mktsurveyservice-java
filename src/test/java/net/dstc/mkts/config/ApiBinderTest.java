/*
 * Copyright 2016 patrizio.
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
import javax.inject.Singleton;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public class ApiBinderTest {

    public ApiBinderTest() {
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
     * Test of configure method, of class ApiBinder.
     */
    @Test
    public void testConfigureNotThrowingExceptionOnNotExistingClass() {
        Properties bindings = new Properties();
        bindings.put("not.existing.interface", "not.existing.class");
        ApiBinder instance = new ApiBinder(bindings);
        instance.configure();
    }

    private interface SingletonTestInterface {}
    @Singleton
    private class SingletonTestClass implements SingletonTestInterface {

        public SingletonTestClass() {
            throw new AssertionError();
        }
    }

    /**
     * Test of configure method, of class ApiBinder.
     */
    @Test
    public void testConfigureNotThrowingInstantiateException() {
        Properties bindings = new Properties();
        bindings.put(SingletonTestInterface.class.getName(), SingletonTestClass.class.getName());
        ApiBinder instance = new ApiBinder(bindings);
        instance.configure();
    }
}
