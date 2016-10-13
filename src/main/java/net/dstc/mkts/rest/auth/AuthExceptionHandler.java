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
package net.dstc.mkts.rest.auth;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.oltu.oauth2.common.OAuth;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Provider
public class AuthExceptionHandler implements ExceptionMapper<ClientErrorException> {

    @Override
    public Response toResponse(ClientErrorException exception) {
        Response response = null;
        if (exception instanceof NotAuthException) {
            NotAuthException ex = (NotAuthException) exception;
            response = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header(OAuth.HeaderType.WWW_AUTHENTICATE, ex.getWwwAuthHeader())
                    .build();
        }
        return response;
    }
}
