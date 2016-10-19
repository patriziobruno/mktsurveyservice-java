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
package net.dstc.mkts.test;

import static com.jayway.restassured.RestAssured.expect;
import com.jayway.restassured.http.ContentType;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dstc.mkts.ServerMain;
import net.dstc.mkts.ServerMainImpl;
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author patrizio
 */
public class RestTest  {
    
    ServerMain server;
    
    @Before
    public void setUp() {
        server = new ServerMainImpl();
        try {
            server.run(false);
        } catch (Exception ex) {
            Logger.getLogger(RestTest.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            server.stop();
        } catch (Exception ex) {
            Logger.getLogger(RestTest.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
    
    @Test
    public void test(){
//        expect()
//                .statusCode(401)
////                .contentType(ContentType.JSON)
//                .body(anything())
//                .when()
//                .get("/api/market-survey");
    }
}
