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
package net.dstc.mkts.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import java.io.IOException;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import net.dstc.mkts.api.auth.AuthManager;
import net.dstc.mkts.api.SurveyDTO;
import net.dstc.mkts.api.MarketingSurveyApi;
import net.dstc.mkts.api.auth.Protected;
import net.dstc.mkts.rest.auth.NotAuthException;
import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Path("marketing-survey")
@Api(value = "marketing-survey")
public class MktSurveyService {

    @Inject
    private MarketingSurveyApi service;

    @Inject
    private AuthManager oAuthManager;

    @GET
    @Protected
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List surveys", notes = "List all surveys", code = 200,
            responseContainer = "List", response = SurveyDTO.class,
            authorizations = {
                @Authorization(value = "oauth2")})
    public Collection<SurveyDTO> getSurveys(
            @ApiParam @QueryParam("filter") String filter,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response
    ) throws NotAuthException, OAuthSystemException, IOException {
        SurveyDTO query = null;

        if (!StringUtils.isEmpty(filter)) {
            query = new ObjectMapper().readValue(filter, SurveyDTO.class);
        }

        return service.getList(query);
    }

    @PUT
    @Protected
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update a survey", code = 200, authorizations = {
        @Authorization(value = "oauth2")})
    public void update(SurveyDTO survey,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response
    ) throws NotAuthException, OAuthSystemException {

        service.update(survey);
    }

    @POST
    @Protected
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Register new survey", code = 201, authorizations = {
        @Authorization(value = "oauth2")})
    public void insert(SurveyDTO survey,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response
    ) throws NotAuthException, OAuthSystemException {

        service.insert(survey);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.getOutputStream().close();
        } catch(Exception ex) {}
    }
}
