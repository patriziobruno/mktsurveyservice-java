/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts;

import org.jhades.JHades;

/**
 *
 * @author eul0860
 */
public class Main {

    public static void main(final String[] args) throws Exception {
        new JHades().dumpClassloaderInfo();

        new ServerMainImpl().run();
    }
}
