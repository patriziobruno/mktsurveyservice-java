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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.expect;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dstc.mkts.ServerMain;
import net.dstc.mkts.ServerMainImpl;
import net.dstc.mkts.api.SurveyDTO;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author patrizio
 */
public class RestTest {
//
//    ServerMain server;
//
//    @Before
//    public void setUp() {
//        server = new ServerMainImpl();
//        try {
//            server.run(false);
//        } catch (Exception ex) {
//            Logger.getLogger(RestTest.class.getName()).log(Level.SEVERE, null,
//                    ex);
//        }
//    }
//
//    @After
//    public void tearDown() {
//        try {
//            server.stop();
//        } catch (Exception ex) {
//            Logger.getLogger(RestTest.class.getName()).log(Level.SEVERE, null,
//                    ex);
//        }
//    }
//
//    @Test
//    public void testGetListNotAuth() {
//        expect()
//                .statusCode(401)
//                .when()
//                .get("/api/marketing-survey");
//    }
//
//    @Test
//    public void testGetList() throws MalformedURLException {
//        String accessTokenUrl = given()
//                .redirects().follow(false)
//                .expect()
//                .statusCode(302)
//                .header("Location", anything())
//                .body(anything())
//                .when()
//                .get("/api/oauth2/authorize?redirect_uri=/app.html&response_type=token&client_id=test&client_secret=test")
//                .header("Location");
//
//        URL url = new URL(accessTokenUrl);
//        List<NameValuePair> query = URLEncodedUtils.parse(url.getRef(), Charset.defaultCharset());
//        String accessToken = query.stream()
//                .filter(nvp -> "access_token".equals(nvp.getName()))
//                .findFirst()
//                .orElse(new BasicNameValuePair("access_token", "NOT_VALID_TOKEN"))
//                .getValue();
//        
//        given().auth().preemptive().oauth2(accessToken)
//                .expect()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .body(anything())
//                .when()
//                .get("/api/marketing-survey");
//    }
//
//    @Test
//    public void testSearch() throws MalformedURLException, JsonProcessingException {
//        String accessTokenUrl = given()
//                .redirects().follow(false)
//                .expect()
//                .statusCode(302)
//                .header("Location", anything())
//                .body(anything())
//                .when()
//                .get("/api/oauth2/authorize?redirect_uri=/app.html&response_type=token&client_id=test&client_secret=test")
//                .header("Location");
//
//        URL url = new URL(accessTokenUrl);
//        List<NameValuePair> query = URLEncodedUtils.parse(url.getRef(), Charset.defaultCharset());
//        String accessToken = query.stream()
//                .filter(nvp -> "access_token".equals(nvp.getName()))
//                .findFirst()
//                .orElse(new BasicNameValuePair("access_token", "NOT_VALID_TOKEN"))
//                .getValue();
//        
//        SurveyDTO surveyFilter = new SurveyDTO();
//        surveyFilter.setTitle("test");
//        String filter = new ObjectMapper().writeValueAsString(surveyFilter);
//        
//        given().auth().preemptive().oauth2(accessToken)
//                .request()
//                .param("filter", filter)
//                .expect()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .body(anything())
//                .when()
//                .get("/api/marketing-survey");
//    }
}
