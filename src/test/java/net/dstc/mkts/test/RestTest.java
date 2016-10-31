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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dstc.mkts.api.SurveyDTO;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import static org.hamcrest.Matchers.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import net.dstc.mkts.ServerRunner;

/**
 *
 * @author patrizio
 */
public class RestTest {

    static ServerRunner server;

    @BeforeClass
    public static void setUpClass() throws Exception {
        server = new ServerRunner(null);
        try {
            server.run(false);
        } catch (Exception ex) {
            Logger.getLogger(RestTest.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        try {
            server.stop();
        } catch (Exception ex) {
            Logger.getLogger(RestTest.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    @Test
    public void testGetListNotAuthorized() {
        expect()
                .statusCode(401)
                .when()
                .get("/api/marketing-survey");
    }

    private String authorizeRequest() throws MalformedURLException {
        String accessTokenUrl = given()
                .redirects().follow(false)
                .expect()
                .statusCode(302)
                .header("Location", anything())
                .body(anything())
                .when()
                .get("/api/oauth2/authorize?redirect_uri=/app.html&response_type=token&client_id=test&client_secret=test").
                header("Location");

        URL url = new URL(accessTokenUrl);
        List<NameValuePair> query = URLEncodedUtils.parse(url.getRef(), Charset.
                defaultCharset());
        String accessToken = query.stream()
                .filter(nvp -> "access_token".equals(nvp.getName()))
                .findFirst()
                .orElse(new BasicNameValuePair("access_token", "NOT_VALID_TOKEN")).
                getValue();

        return accessToken;
    }

    @Test
    public void testGetList() throws MalformedURLException {

        given().auth().preemptive().oauth2(authorizeRequest())
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(anything())
                .when()
                .get("/api/marketing-survey");
    }

    @Test
    public void testSearch() throws MalformedURLException,
            JsonProcessingException {

        SurveyDTO surveyFilter = new SurveyDTO();
        surveyFilter.setTitle("test");
        String filter = new ObjectMapper().writeValueAsString(surveyFilter);

        given().auth().preemptive().oauth2(authorizeRequest())
                .request()
                .param("filter", filter)
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(anything())
                .when()
                .get("/api/marketing-survey");
    }

    @Test
    public void testInsert() throws MalformedURLException,
            JsonProcessingException,
            IOException {

        SurveyDTO survey = new SurveyDTO();
        survey.setTitle("auto-test 1");
//        survey.setStartDate(new Date());
        String body = new ObjectMapper().writeValueAsString(survey);
        String accessToken = authorizeRequest();

        given().auth().preemptive().oauth2(accessToken)
                .request().contentType(ContentType.JSON).body(body)
                .expect().statusCode(201).body(anything())
                .when().post("/api/marketing-survey");

        given().auth().preemptive().oauth2(accessToken)
                .request().param("filter", body)
                .expect().statusCode(200).contentType(ContentType.JSON)
                                .body("[0].title", equalTo(survey.getTitle()))
                .when().get("/api/marketing-survey");
    }
}
