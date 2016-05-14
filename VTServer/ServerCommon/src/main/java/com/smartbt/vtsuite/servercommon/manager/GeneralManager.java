/*
 ** File: GeneralManager.java
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
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.display.common.model.SettingDisplay;
import com.smartbt.vtsuite.servercommon.dao.GeneralDAO;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class GeneralManager {

    protected static final Logger log = Logger.getLogger(GeneralManager.class);
    protected SbtServerCommonValidators validators = new SbtServerCommonValidators();
    protected GeneralDAO generalDAO = GeneralDAO.get();

    /**
     * Do Heartbeat
     *
     * @return Smart BaseResponse
     */
    public BaseResponse doHeartBeat() {
        BaseResponse response = new BaseResponse();
        log.info("----->  HeartBeat: Success <-----");
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    
    /**
     * Lists all data types
     *
     * @return ResponseDataList
     */
    public ResponseDataList listDataTypes() {
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(generalDAO.listDataTypes());
        return response;
    }
    
    
    /**
     * Lists all available states
     *
     * @return ResponseDataList
     */
    public ResponseDataList listStates() {
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(generalDAO.listStates());
        return response;
    }

    /**
     * Lists all available phone types
     *
     * @return ResponseDataList
     */
    public ResponseDataList listPhoneTypes() {
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(generalDAO.listPhoneTypes());
        return response;
    }
    
    /**
     * Login
     *
     * @param userName The username
     * @param password
     * @param application
     * @return Login response
     */
    public ResponseData login(String userName, String password, NomApplication application) {
        ResponseData response = new ResponseData();
        SettingDisplay settingDisplay;
        try {
            Criteria criteria = HibernateUtil.getSession().createCriteria(Clerk.class).
                    add(Restrictions.eq("username", userName).ignoreCase()).
                    add(Restrictions.eq("password", Utils.encryptPassword(password)));
            Clerk clerk = (Clerk) criteria.uniqueResult();
            
            if (clerk != null) {
                settingDisplay = generalDAO.login(clerk, 0, application);

                if (settingDisplay != null) {
                    response.setStatus(Constants.CODE_SUCCESS);
                    response.setStatusMessage(VTSuiteMessages.SUCCESS);
                    response.setData(settingDisplay);
                }
            } else {
                response.setStatus(Constants.CODE_WRONG_USER);
            }
        } catch (Exception ex) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
        }       
        
        return response;
    }

    /**
     * Get Settings
     *
     * @param clerk
     * @param merchantId
     * @param application
     * @return Login response
     */
    public ResponseData getSettings(Clerk clerk, int merchantId, NomApplication application) {
        ResponseData response = new ResponseData();
        SettingDisplay settingDisplay;
        try {
            settingDisplay = generalDAO.getSettings(clerk, merchantId, application);
            
            if (settingDisplay == null) {
                response.setStatus(Constants.CODE_WRONG_USER);
            } else if (settingDisplay.getTerminalParameters() == null) {
                response.setStatus(Constants.CODE_BADLOGIN);
            } else {
                response.setStatus(Constants.CODE_SUCCESS);
                response.setStatusMessage(VTSuiteMessages.SUCCESS);
                response.setData(settingDisplay);
            }
        } catch (Exception ex) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
        }       
        
        return response;
    }
}
