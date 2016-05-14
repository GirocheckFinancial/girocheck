/*
 ** File: Settings.java
 **
 ** Date Created: December 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.classes;

import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Record;
import java.util.Map;

/**
 *
 * @author Ariel Saavedra
 */
public class Settings {

    /**
     * Instance of the Settings class
     */
    public static Settings INSTANCE = getInstance();
    /**
     * Merchant Parameters
     */
//    private static Map AMS_PARAM;
    /**
     * Clerk Privileges
     */
    private static Map USER_PRIVILEGES;
    /**
     * Token
     */
    public static String TOKEN;

    private Settings() {
    }

    /**
     * Get Settings Instance
     *
     * @return
     */
    private static Settings getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Settings();
        }
        return INSTANCE;
    }

    /**
     * Set all the parameters and privileges that comes from the server
     *
     * @param settingDisplay
     */
    public void setSetting(Record settingDisplay) {
//        AMS_PARAM = settingDisplay.getAttributeAsMap("parameters");
        USER_PRIVILEGES = settingDisplay.getAttributeAsMap("rolePrivileges");
    }

    /**
     * Check if a AMS user has a privilege
     *
     * @param privilege
     * @return
     */
    public boolean hasPrivilege(NomUserPrivileges privilege) {
        return USER_PRIVILEGES.containsKey(String.valueOf(privilege.getId()));
    }

//    /**
//     * Get parameter value
//     * 
//     * @param parameter
//     * @return
//     */
//    public String getParameterValue(ParameterType parameter){
//        return AMS_PARAM.getAttribute(parameter.getValue());
//    }
}
