/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.rest.auth;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.oltu.oauth2.common.OAuth;

/**
 *
 * @author patrizio
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
