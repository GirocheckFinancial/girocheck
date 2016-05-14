/*
 ** File: PropertiesBean.java
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
package com.smartbt.vtsuite.servercommon.utils;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;

/**
 * The Properties Bean class
 */
//@Singleton
//@Startup
public class PropertiesBean {

    @PostConstruct
    private void startup() {
        HibernateUtil.getSessionFactory();
    }

    @PreDestroy
    private void shutdown() {
        HibernateUtil.shutDown();
    }
}
