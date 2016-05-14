/*
 ** File: ClerkManager.java
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
import com.smartbt.vtsuite.servercommon.dao.ClerkDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.display.common.model.CustomerDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.MerchantDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.RoleDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TerminalDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.UserDisplay;
import com.smartbt.vtsuite.servercommon.validators.ClerkValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class ClerkManager {

    private static final Logger log = Logger.getLogger(ClerkManager.class);
    private ClerkDAO clerkDAO = ClerkDAO.get();

    /**
     * Search all the Clerks by a given Entity id and a filter
     *
     * @param idEntity
     * @param searchFilter
     * @param entityType
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    public ResponseDataList searchClerk(int idEntity, String searchFilter, EntityType entityType, int pageNumber, int rowsPerPage) throws Exception {
       // ClerkValidator.searchClerk(idEntity, searchFilter, entityType, pageNumber, rowsPerPage);
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        response.setData(listClerks());
//        response.setData(clerkDAO.searchClerks(idEntity, searchFilter, entityType, pageNumber * rowsPerPage, rowsPerPage));
        int totalTrans = 1;//clerkDAO.searchClerks(idEntity, searchFilter, entityType, -1, -1).size();
        response.setTotalPages((int) Math.ceil((float) totalTrans / (float) rowsPerPage));
        return response;
    }
    
    private List<UserDisplay>  listClerks(){
        List<UserDisplay>  list = new ArrayList<UserDisplay>();
        
        UserDisplay u = new UserDisplay();
        u.setId(1);
        u.setRole(new RoleDisplay(1, "Admin"));
        u.setUsername("username");
        u.setFirstName("FirstName");
        u.setLastName("LastName");
        u.setActive(Boolean.TRUE);
        u.setPassword("aa");
        u.setCustomer(new CustomerDisplay(1, "Girocheck"));
        u.setMerchant(new MerchantDisplay(1, "Girocheck's Merchant"));
        u.setTerminal(new TerminalDisplay(1, "Girocheck's Terminal"));
        
        list.add(u);
        return list;
    }

    /**
     * Get clerks by Entity
     *
     * @param idEntity
     * @param entityType
     * @param pageNumber
     * @param rowsPerPage
     * @return ResponseDataList
     */
    public ResponseDataList getClerksByEntityType(int idEntity, EntityType entityType, int pageNumber, int rowsPerPage) throws Exception {
        ClerkValidator.getClerksByEntityType(idEntity, entityType, pageNumber, rowsPerPage);
        ResponseDataList response = new ResponseDataList();

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        response.setData(listClerks());
//        response.setData(clerkDAO.getClerksByEntityType(idEntity, entityType, pageNumber * rowsPerPage, rowsPerPage));

        //int totalTrans = clerkDAO.getAllClerksFromCustomerByEntityType(idEntity, entityType, -1, -1).size();
        //response.setTotalPages((int) Math.ceil((float) totalTrans / (float) rowsPerPage));
        return response;
    }

    /**
     * Update a Clerk
     *
     * @param clerk
     * @param application
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse updateClerk(UserDisplay clerk, NomApplication application) throws Exception {
        ClerkValidator.updateClerk(clerk, application);
        BaseResponse response = new BaseResponse();

        clerkDAO.updateClerk(clerk, application);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        return response;
    }

    /**
     * Complete clerk's First Time Installation
     *
     * @param clerk
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse completeFirstTimeInstall(UserDisplay clerk) throws Exception {
        ClerkValidator.completeFirstTimeInstall(clerk);
        BaseResponse response = new BaseResponse();

        clerkDAO.completeFirstTimeInstall(clerk);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        return response;
    }
}
