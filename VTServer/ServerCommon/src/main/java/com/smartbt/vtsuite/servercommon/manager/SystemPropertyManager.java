/*
 ** File: SystemPropertyManager.java
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
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.vtsuite.servercommon.dao.SystemPropertyDAO;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class SystemPropertyManager {

    private static final Logger log = Logger.getLogger(SystemPropertyManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private SystemPropertyDAO systemPropertyDAO = SystemPropertyDAO.get();

    /**
     * Get system property by a given name
     *
     * @param name
     * @return
     */
    public ResponseData getSystemProperty(String name) {
        ResponseData response = new ResponseData();
        if (!systemPropertyDAO.existSystemProperty(name)) {
            log.info("----->  getSystemProperty: This Property does not exist <-----");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.PROPERTY_DOES_NOT_EXIST);
        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(systemPropertyDAO.getSystemProperty(name));
        }
        return response;
    }
}
