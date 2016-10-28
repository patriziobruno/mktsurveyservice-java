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
package net.dstc.mkts.rest;

import io.swagger.jaxrs.Reader;
import io.swagger.models.Swagger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import net.dstc.mkts.EmbeddedServer;
import net.dstc.mkts.config.ServerSettingsImpl;

/**
 *
 * @author patrizio
 */
public class MktSurveyApiDefinitionTest {

    static EmbeddedServer server;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private Swagger getSwagger() throws IOException {
//        String swaggerJson = expect().statusCode(200)
//                .contentType(ContentType.JSON)
//                .get("/api/swagger.json").body().as(String.class);
//        return new SwaggerParser().
//                read(new ObjectMapper().readTree(swaggerJson));
        Swagger swagger = new Swagger();
        swagger.setHost("localhost:8080");
        swagger.setBasePath(ServerSettingsImpl.CONTEXT_PATH);
        return swagger;
    }

    /**
     * Test of beforeScan method, of class MktSurveyApiDefinition.
     */
    @Test
    public void testBeforeScan() throws IOException {
        System.out.println("beforeScan");

        Reader reader = null;
        MktSurveyApiDefinition instance = new MktSurveyApiDefinition();
        instance.beforeScan(null, getSwagger());
    }

    /**
     * Test of afterScan method, of class MktSurveyApiDefinition.
     */
    @Test
    public void testAfterScan() throws IOException {
        System.out.println("afterScan");
        Reader reader = null;
        MktSurveyApiDefinition instance = new MktSurveyApiDefinition();
        instance.afterScan(reader, getSwagger());
    }

}
