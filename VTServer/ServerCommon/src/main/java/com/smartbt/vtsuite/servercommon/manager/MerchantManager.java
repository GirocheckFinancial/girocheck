/*
 ** File: MerchantManager.java
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
import com.smartbt.vtsuite.servercommon.dao.CustomerDAO;
import com.smartbt.vtsuite.servercommon.dao.MerchantDAO;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.io.InputStream;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class MerchantManager {

    private static final Logger log = Logger.getLogger(MerchantManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private MerchantDAO merchantDAO = MerchantDAO.get();

    /**
     * Get a Merchant by a given id
     *
     * @param id
     * @return
     */
    public ResponseData getMerchant(int id) {
        ResponseData response = new ResponseData();
        if (merchantDAO.findById(id) == null) {
            log.info("----->  getMerchant: This Merchant does not exist <-----");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.MERCHANT_DOES_NOT_EXIST);
        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            try {
                response.setData(merchantDAO.getMerchant(id));
            } catch (SQLException ex) {
                response.setStatus(Constants.CODE_ERROR_GENERAL);
            }
        }
        return response;
    }

    /**
     * Get a Merchant by a given id
     *
     * @param id
     * @param logoImageInputStream
     * @param fileName
     * @return
     */
    public ResponseData setMerchantLogo(int id, InputStream logoImageInputStream, String fileName) {
        ResponseData response = new ResponseData();
        if (merchantDAO.findById(id) == null) {
            log.info("----->  setMerchantLogo: This Merchant does not exist <-----");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.MERCHANT_DOES_NOT_EXIST);
        } else if (!validImageLogo(fileName)) {
            log.info("----->  setMerchantLogo: Bad imageLogo  <-----");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage("Bad imageLogo");
        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            try {
                response.setData(merchantDAO.setMerchantLogo(id, logoImageInputStream));
            } catch (Exception ex) {
                response.setStatus(Constants.CODE_ERROR_GENERAL);
            }
        }
        return response;
    }

    private boolean validImageLogo(String fileName) {
        return fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".png"));
    }

    /**
     * Get the Merchants by a given Customer's id
     *
     * @param idCustomer
     * @return
     */
    public ResponseDataList getMerchantsByCustomer(int idCustomer) {
        ResponseDataList response = new ResponseDataList();
//        if (CustomerDAO.get().findById(idCustomer) == null) {
//            log.info("----->  getMerchantsByCustomer: This Customer does not exist <-----");
//            response.setStatus(Constants.CODE_ERROR_GENERAL);
//            response.setStatusMessage(VTSuiteMessages.CUSTOMER_DOES_NOT_EXIST);
//        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(merchantDAO.getMerchantsByCustomer(idCustomer));
//        }
        return response;
    }
}
