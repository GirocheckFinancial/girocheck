/*
 ** File: ClientManager.java
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
import com.smartbt.vtsuite.servercommon.display.common.model.AccountDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.ClientDisplay;
import com.smartbt.vtsuite.servercommon.dao.ClientDAO;
import com.smartbt.vtsuite.servercommon.validators.ClientValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.AccountType;
import com.smartbt.vtsuite.vtcommon.enums.ActivityFilter;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.io.InputStream;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class ClientManager {

    private static final Logger log = Logger.getLogger(ClientManager.class);
    private ClientDAO clientDAO = ClientDAO.get();

    /**
     * Delete client with given id
     *
     * @param clientId
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse deleteClient(int clientId) throws Exception {
        ClientValidator.deleteClient(clientId);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        clientDAO.deleteClient(clientId);
        return response;
    }

    /**
     * Set client with given ClientDisplay
     *
     * @param client
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse saveOrUpdateClient(ClientDisplay client) throws Exception {
        ClientValidator.saveOrUpdateClient(client);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        clientDAO.saveOrUpdateClient(client);
        return response;
    }

    /**
     * Set clients with inputStream
     *
     * @param inputStream
     * @param idMerchant
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse updateWithStream(InputStream inputStream, int idMerchant) throws Exception {
        ClientValidator.updateWithStream(inputStream, idMerchant);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        clientDAO.updateWithStream(inputStream, idMerchant);

        return response;
    }

    /**
     * Search all the Clients with given parameters
     *
     * @param idEntity
     * @param entityType
     * @param searchFilter
     * @param activityFilter
     * @param pageNumber
     * @param rowsPerPage
     * @param application
     * @return
     */
    public ResponseDataList searchClients(int idEntity, EntityType entityType, String searchFilter, ActivityFilter activityFilter, int pageNumber, int rowsPerPage, NomApplication application) throws Exception {
        ClientValidator.searchClients(idEntity, entityType, searchFilter, activityFilter, pageNumber, rowsPerPage, application);
        ResponseDataList response = new ResponseDataList();

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        response.setData(clientDAO.searchClients(idEntity, entityType, searchFilter, activityFilter, pageNumber * rowsPerPage, rowsPerPage, application));
        int totalTrans = clientDAO.searchClients(idEntity, entityType, searchFilter, activityFilter, -1, -1, application).size();
        response.setTotalPages((int) Math.ceil((float) totalTrans / (float) rowsPerPage));

        return response;
    }

    /**
     * Set all specified clients to inactive
     *
     * @param idEntity
     * @param entityType
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse deleteAllClients(int idEntity, EntityType entityType) throws Exception {
        ClientValidator.deleteAllClients(idEntity, entityType);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        clientDAO.deleteAllClients(idEntity, entityType);
        return response;
    }

    /**
     * Get a Client by a given id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public ResponseData getClient(int id) throws Exception {
        ClientValidator.getClient(id);
        ResponseData response = new ResponseData();

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(clientDAO.getClient(id));

        return response;
    }

    /**
     * Get a Client's Accounts by client id and account type
     *
     * @param id
     * @param accountType
     * @return
     * @throws java.lang.Exception
     */
    public ResponseDataList getClientAccounts(int id, AccountType accountType) throws Exception {
        ClientValidator.getClientAccounts(id, accountType);
        ResponseDataList response = new ResponseDataList();

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(clientDAO.getClientAccounts(id, accountType));

        return response;
    }

    /**
     * Add an account to a client
     *
     * @param clientDisplay
     * @param accountDisplay
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse addAccountToClient(ClientDisplay clientDisplay, AccountDisplay accountDisplay) throws Exception {
        ClientValidator.addAccountToClient(clientDisplay, accountDisplay);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        clientDAO.addAccountToClient(clientDisplay, accountDisplay);
        return response;
    }
}
