/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.utils.IDScanner;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author rrodriguez
 */
public class MyContextListener
        implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //do stuff
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&..............................&&&&&&");
        System.out.println("&&&&&&......... STARTING............&&&&&&");
        System.out.println("&&&&&&..............................&&&&&&");
        System.out.println("&&&&&&........TESTING ID SCAN.......&&&&&&");
        System.out.println("&&&&&&..............................&&&&&&");

        if (IDScanner.testParseID()) {
            System.out.println("&&&&&&............SUCCESS...........&&&&&&");
        } else {
            System.out.println("&&&&&&......FAILED FIRST ATTEMPT....&&&&&&");

            if (IDScanner.testParseID()) {
                System.out.println("&&&&&&............SUCCESS...........&&&&&&");
            } else {
                System.out.println("&&&&&&......FAILED SECOND ATTEMPT....&&&&&&");
            }
        }

        System.out.println("&&&&&&..............................&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }
}
