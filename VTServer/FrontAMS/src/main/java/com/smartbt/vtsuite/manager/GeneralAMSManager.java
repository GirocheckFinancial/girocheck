/*
 ** File: GeneralManager.java
 **
 ** Date Created: November 2013
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
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.AMSSettingDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.RolePrivilegeDisplay;
import com.smartbt.vtsuite.servercommon.dao.ParameterValueDAO;
import com.smartbt.girocheck.servercommon.dao.PrivilegesDAO;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue;
import com.smartbt.girocheck.servercommon.model.User;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ariel Saavedra
 */
public class GeneralAMSManager extends com.smartbt.vtsuite.servercommon.manager.GeneralManager {

    /**
     * Get AMS's settings
     *
     * @param user
     * @return ResponseData
     */
    public ResponseData getSettings(User user) {
        ResponseData smartResponse = new ResponseData();

//        System.out.println("getSettings() incoming parameters user null?: "+user==null);
        
        smartResponse.setStatus(Constants.CODE_SUCCESS);
        smartResponse.setStatusMessage(VTSuiteMessages.SUCCESS);

//        List<ApplicationParameterValue> parametersValueDisplay = ParameterValueDAO.get().searchParametersValue(NomApplication.VT_AMS.getId(),
//                EntityType.AMS, null, 0, 1000);

        List<RolePrivilegeDisplay> rolePrivilegesDisplay = PrivilegesDAO.get().searchRolePrivileges(EntityType.AMS,
                false, user.getRole().getId(), 0, 1000);
//        System.out.println("getSettings() incoming parameters RolePrivilegeDisplay size?: "+rolePrivilegesDisplay.size());
        AMSSettingDisplay settingDisplay = new AMSSettingDisplay();
//        settingDisplay.setParameters(getParameters(parametersValueDisplay));
        settingDisplay.setRolePrivileges(getRolePrivileges(rolePrivilegesDisplay));
        smartResponse.setData(settingDisplay);
        return smartResponse;
    }

    /**
     * Gets AMS's user privileges
     *
     * @param rolePrivilegesDisplay
     * @return
     */
    public static Map getRolePrivileges(List<RolePrivilegeDisplay> rolePrivilegesDisplay) {
        Map rolePrivilegeMap = new LinkedHashMap();

        for (RolePrivilegeDisplay rolePrivilege : rolePrivilegesDisplay) {
            rolePrivilegeMap.put(rolePrivilege.getPrivilege().getId(), rolePrivilege.getPrivilege().getName());
        }
        return rolePrivilegeMap;
    }

    /**
     * Gets merchant parameters
     *
     * @param parametersValueDisplay
     * @return
     */
    public static Map getParameters(List<ApplicationParameterValue> parametersValueDisplay) {
        Map parametersMap = new LinkedHashMap();

        for (ApplicationParameterValue parameterValue : parametersValueDisplay) {
             parametersMap.put(parameterValue.getId(), parameterValue.getValue());
        }
        return parametersMap;
    }
}
