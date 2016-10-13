/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.api;

import javax.servlet.http.HttpServletRequest;
import net.dstc.mkts.rest.auth.NotAuthException;

/**
 *
 * @author patrizio
 */
public interface AuthManager {

    void assertIsValidToken(HttpServletRequest request) throws NotAuthException;

    void addToken(String token);

    void addAuthCode(String authCode);

    boolean isValidAuthCode(String authCode);

    boolean isValidToken(String token);
}
