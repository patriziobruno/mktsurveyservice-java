/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts;

/**
 *
 * @author patrizio
 */
public interface ServerMain {

    String getContextPath();
    void setContextPath(String path);
    
    int getPort();
    void setPort(int port);
    
    public void run() throws Exception;
}
