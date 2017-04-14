/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.enums.EnumApplicationParameter;
import com.smartbt.girocheck.servercommon.manager.ApplicationParameterManager;
import com.smartbt.girocheck.servercommon.utils.IDScanner;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        try {
//            System.out.println("MyContextListener -> contextInitialized");
//
//            HibernateUtil.beginTransaction();
//            System.out.println("HibernateUtil.beginTransaction();");
//            Map<EnumApplicationParameter, Double> amountAplicationParameters = ApplicationParameterManager.get().getAmountAplicationParameters();
//            System.out.println("amountAplicationParameters.SIZE() =  " + amountAplicationParameters.size());
//            HibernateUtil.commitTransaction();
//            System.out.println("HibernateUtil.commitTransaction();");
//        } catch (Exception ex) {
//            Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
