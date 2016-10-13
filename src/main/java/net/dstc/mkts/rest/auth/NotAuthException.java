/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.rest.auth;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

/**
 *
 * @author patrizio
 */
public class NotAuthException extends NotAuthorizedException {

    private String wwwAuthHeader;

    public String getWwwAuthHeader() {
        return wwwAuthHeader;
    }

    public NotAuthException(String wwwAuthHeader) {
        super("unauthorized");
        this.wwwAuthHeader = wwwAuthHeader;
    }

    public NotAuthException(Object challenge, Object... moreChallenges) {
        super(challenge, moreChallenges);
    }

    public NotAuthException(Response response) {
        super(response);
    }

    public NotAuthException(Response response, Throwable cause) {
        super(response, cause);
    }

    public NotAuthException(String message, Response response) {
        super(message, response);

    }

    public NotAuthException(String message, Response response,
            Throwable cause) {
        super(message, response, cause);
    }

    public NotAuthException(String message, Object challenge,
            Object... moreChallenges) {
        super(message, challenge, moreChallenges);
    }

    public NotAuthException(Throwable cause, Object challenge,
            Object... moreChallenges) {
        super(cause, challenge, moreChallenges);
    }

    public NotAuthException(String message, Throwable cause, Object challenge,
            Object... moreChallenges) {
        super(message, cause, challenge, moreChallenges);
    }
}
