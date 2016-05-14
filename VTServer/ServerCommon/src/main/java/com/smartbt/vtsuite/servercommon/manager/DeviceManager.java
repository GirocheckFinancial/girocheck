/*
 ** File: DeviceManager.java
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
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.dao.DeviceDAO;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class DeviceManager {

    private static final Logger log = Logger.getLogger(DeviceManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private DeviceDAO deviceDAO = DeviceDAO.get();

    /**
     * Get a Device by a given id
     *
     * @param id
     * @return
     */
    public ResponseData getDevice(int id) {
        ResponseData response = new ResponseData();
        if (deviceDAO.findById(id) == null) {
            log.info("----->  getDevice: This Device does not exist <-----");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.DEVICE_DOES_NOT_EXIST);
        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(deviceDAO.getDevice(id));
        }
        return response;
    }

    /**
     * Get the Devices by a given Terminal's id
     *
     * @param idTerminal
     * @return
     */
    public ResponseDataList getDevicesByTerminal(int idTerminal) {
        ResponseDataList response = new ResponseDataList();
//            if (!frontFacade.existObject(Terminal.class, idTerminal)) {
//                log.info("----->  getDevicesByTerminal: This Terminal does not exist <-----");
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.TERMINAL_DOES_NOT_EXIST);
//            } else {
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(deviceDAO.getDevicesByTerminal(idTerminal));
//            }
        return response;
    }
}
