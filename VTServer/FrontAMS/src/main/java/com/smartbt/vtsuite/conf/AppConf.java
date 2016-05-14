/*
 ** File: AppConf.java
 **
 ** Date Created: March 2013
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
package com.smartbt.vtsuite.conf;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Application Configuration Class.
 */
public class AppConf {

    private static AppConf appConf;
    private Properties appProps;
    /**
     *
     */
    public static String AUTHORIZED_USER_GROUP = "AUTHORIZED_USER_GROUP";

    /**
     * Constructor
     */
    public AppConf() {
        this.appProps = new Properties();
        load();
    }

    /**
     * Get Instance
     * @return Application Configuration instance.
     */
    public static AppConf getInst() {
        if (appConf == null) {
            appConf = new AppConf();
        }
        return appConf;
    }

    private void load() {
        InputStream fis;
        try {
            fis = this.getClass().getResourceAsStream("/app.properties");
            appProps.load(fis);
        } catch (Exception ex) {
            Logger.getLogger(AppConf.class.getName()).log(Level.ERROR, null, ex);
        }
    }

    /**
     * Get Property
     * @param pName Property name.
     * @return Property value.
     */
    public String getProp(String pName) {
        String value = appProps.getProperty(pName);
        return value == null ? "null" : value;
    }
}
