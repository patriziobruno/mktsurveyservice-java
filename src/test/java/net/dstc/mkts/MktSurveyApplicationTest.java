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

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {@link MktSurveyApplication}
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public class MktSurveyApplicationTest {

    public MktSurveyApplicationTest() {
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

    @Test
    public void testConstructor() throws IOException {
        Set<Class> expected = new HashSet<Class>() {
            {
                add(net.dstc.mkts.rest.MktSurveyApiDefinition.class);
                add(net.dstc.mkts.rest.MktSurveyService.class);
                add(net.dstc.mkts.rest.auth.AuthEndpoint.class);
                add(net.dstc.mkts.rest.auth.AuthExceptionHandler.class);
//                add(io.swagger.jaxrs.listing.ApiListingResource.class);
//                add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
//                add(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class);
            }
        };

        MktSurveyApplication instance = new MktSurveyApplication();
        Set<Class<?>> classes = instance.getClasses();
        expected.forEach((cl) -> {
            assertTrue(classes.contains(cl));
        });
    }

}
