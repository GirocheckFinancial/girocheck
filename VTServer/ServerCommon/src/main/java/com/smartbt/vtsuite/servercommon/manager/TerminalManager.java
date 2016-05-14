/*
 ** File: TerminalManager.java
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
import com.smartbt.vtsuite.servercommon.dao.MerchantDAO;
import com.smartbt.vtsuite.servercommon.dao.TerminalDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.display.common.model.ParameterDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TerminalDisplay;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class TerminalManager {

    private static final Logger log = Logger.getLogger(TerminalManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private TerminalDAO terminalDAO = TerminalDAO.get();

    /**
     * Get a Terminal by a given id
     *
     * @param id
     * @return
     */
    public ResponseData getTerminal(int id) {
        ResponseData response = new ResponseData();
        if (terminalDAO.findById(id) == null) {
            log.info("----->  getTerminal: This Terminal does not exist <-----");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.TERMINAL_DOES_NOT_EXIST);
        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(getTerminal());
//            response.setData(terminalDAO.getTerminal(id));
        }
        return response;
    }

    /**
     * Get the Terminals by a given Merchant's id
     *
     * @param id
     * @return
     */
    public ResponseDataList getTerminalsByMerchant(int id) {
        System.out.println("[Terminal Manager] :: getTerminalsByMerchant()" );
        ResponseDataList response = new ResponseDataList();
//        if (MerchantDAO.get().findById(id) == null) {
//            log.info("----->  getTerminalsByMerchant: This Merchant does not exist <-----");
//            response.setStatus(Constants.CODE_ERROR_GENERAL);
//            response.setStatusMessage(VTSuiteMessages.MERCHANT_DOES_NOT_EXIST);
//        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            
            List<TerminalDisplay> list = listTerminal(); 
            response.setData(list);
//        }
            
           System.out.println("return a list with " + list.size()); 
        return response;
    }
    
    public List<TerminalDisplay> listTerminal(){
        List<TerminalDisplay> list = new ArrayList<TerminalDisplay>();
        
        list.add(getTerminal());
        return list;
    }
    
    public TerminalDisplay getTerminal(){
        TerminalDisplay td = new TerminalDisplay();
        td.setId(1);
        td.setSerialNumber("123");
        td.setDescription("Descrption");
        td.setActive(true);
        td.setTerminalId("12");
        td.setParameter(new ArrayList<ParameterDisplay>());
        td.setActivationDate(null);
        td.setDeactivationDate(null);
        td.setMonitored(Boolean.FALSE);
        td.setCustomerName("Girocheck");
        td.setMerchantName("Merchant Name");
        td.setProductType("product type");
        td.setApplication("application");
        td.setIdApplication(1);
        return td;
    }
}
