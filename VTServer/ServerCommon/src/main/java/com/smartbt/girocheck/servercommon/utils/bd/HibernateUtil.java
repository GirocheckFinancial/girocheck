/*
 ** File: HibernateUtil.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.servercommon.utils.bd;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.stat.Statistics;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 */
public class HibernateUtil {

    private static final Logger log = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    /**
     * Gets the session factory
     *
     * @return Session Factory
     */
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            Configuration conf = new Configuration();
            conf.setProperty(Environment.GENERATE_STATISTICS, "true");
            sessionFactory = conf.configure().buildSessionFactory();
        }
        return sessionFactory;
    }

//    public static Session openSession() {
//        return HibernateUtil.getSessionFactory().openSession();
//    }
    public static Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public static void beginTransaction() {
//        if (getSession().getTransaction() == null) {
            getSession().beginTransaction();
//        }
    }

    public static void commitTransaction() throws Exception {
        getSession().getTransaction().commit();
        printStatistics();
    }

    public static void rollbackTransaction() {
        getSession().getTransaction().rollback();
        getSession().close();
        Statistics s = sessionFactory.getStatistics();
        log.info("rollbackTransaction -> Open Sessions/Closed Sessions: " + s.getSessionOpenCount() + "/ " + s.getSessionCloseCount());
    }

    /**
     * Close the session factory.
     */
    public static void shutDown() {
        sessionFactory.close();
    }

    public static void initialize(Object object) {
        if (object != null) {
            Hibernate.initialize(object);
        }
    }

    public static void printStatistics() {
        Statistics s = sessionFactory.getStatistics();
        log.info("Open Sessions/Closed Sessions: " + s.getSessionOpenCount() + "/ " + s.getSessionCloseCount());
    }
}
