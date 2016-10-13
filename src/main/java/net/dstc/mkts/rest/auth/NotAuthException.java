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

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
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
