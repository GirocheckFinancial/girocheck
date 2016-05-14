/*
 *  File ClientValidator
 * 
 *  Date Created: January 2014
 * 
 *  Copyright @ @ 2004-2014 Smart Business Technology, Inc.
 *
 *  All rights reserved. No part of this software may be 
 *  reproduced, transmitted, transcribed, stored in a retrieval 
 *  system, or translated into any language or computer language,
 *  in any form or by any means, electronic, mechanical, magnetic, 
 *  optical, chemical, manual or otherwise, without the prior 
 *  written permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.servercommon.validators;

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.vtsuite.common.SessionClerk;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.ClientDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.AccountDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.AddressDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.ClientDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TelephoneDisplay;
import com.smartbt.vtsuite.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.enums.AccountType;
import com.smartbt.vtsuite.vtcommon.enums.ActivityFilter;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import java.io.InputStream;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class ClientValidator {

    private static final Logger log = Logger.getLogger(ClientValidator.class);

    /**
     * Delete client with given id Validator
     *
     * @param clientId
     * @throws java.lang.Exception
     */
    public static void deleteClient(int clientId) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_DELETE.getId());
        }
        
        if (ClientDAO.get().findById(clientId) == null) {
            log.info("----->  deleteClient: This Client does not exist <-----");
            throw new ValidationException(VTSuiteMessages.CLIENT_DOES_NOT_EXIST);
        }
    }

    /**
     * Set client with given ClientDisplay Validator
     *
     * @param client
     * @throws java.lang.Exception
     */
    public static void saveOrUpdateClient(ClientDisplay client) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SERVICES.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER.getId()
//                    , (client.getId() != null && client.getId() > 0) ? NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_UPDATE.getId()
//                    : NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_ADD.getId());
        }

        String errors = UtilValidator.getErrorsAsString(client);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        if (client.getClientTelephoneList() != null) {
            for (TelephoneDisplay telephone : client.getClientTelephoneList()) {
                errors = UtilValidator.getErrorsAsString(telephone);
                if (!errors.isEmpty()) {
                    throw new ValidationException(errors);
                }
            }
        }

//        if (client.getClientAddressList() == null) {
//            throw new ValidationException(" ");
//        }
//        
//        if (client.getClientAddressList().isEmpty() || client.getClientAddressList().size() > 2) {
//            throw new ValidationException(errors);
//        }
        if (client.getClientAddressList() != null) {
            for (AddressDisplay addressDisplay : client.getClientAddressList()) {
                errors = UtilValidator.getErrorsAsString(addressDisplay);
                if (!errors.isEmpty()) {
                    throw new ValidationException(errors);
                }
            }
        }
    }

    /**
     * Set clients with inputStream Validator
     *
     * @param inputStream
     * @param idMerchant
     * @throws java.lang.Exception
     */
    public static void updateWithStream(InputStream inputStream, int idMerchant) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SERVICES.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_IMPORT.getId());
        }
    }

    /**
     * Search all the Clients with given parameters Validator
     *
     * @param idEntity
     * @param entityType
     * @param searchFilter
     * @param activityFilter
     * @param pageNumber
     * @param rowsPerPage
     * @param application
     * @throws java.lang.Exception
     */
    public static void searchClients(int idEntity, EntityType entityType, String searchFilter, ActivityFilter activityFilter, int pageNumber, int rowsPerPage, NomApplication application) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SERVICES.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER.getId());
        }
         if (entityType == null) {
            throw new ValidationException(VTSuiteMessages.VALUE_IS_EMPTY + " (entityType)");
        }
        
        UtilValidator.validateSearchFilter(searchFilter);
    }

    /**
     * Set all specified clients to inactive Validator
     *
     * @param idEntity
     * @param entityType
     * @throws java.lang.Exception
     */
    public static void deleteAllClients(int idEntity, EntityType entityType) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SERVICES.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_DELETE_ALL.getId());
        }
        
         if (entityType == null) {
            throw new ValidationException(VTSuiteMessages.VALUE_IS_EMPTY + " (entityType)");
        }
    }

    /**
     * Get a Client by a given id Validator
     *
     * @param id
     * @throws java.lang.Exception
     */
    public static void getClient(int id) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SETTINGS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_USERS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_UPDATE.getId());
        }
        
         if (ClientDAO.get().findById(id) == null) {
            log.info("----->  getClient: This Client does not exist <-----");
            throw new ValidationException(VTSuiteMessages.CLIENT_DOES_NOT_EXIST);
        }
    }

    /**
     * Get a Client's Accounts by client id and account type Validator
     *
     * @param id
     * @param accountType
     * @throws java.lang.Exception
     */
    public static void getClientAccounts(int id, AccountType accountType) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SETTINGS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_USERS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_UPDATE.getId());
        }
        if (id != 0 && ClientDAO.get().findById(id) == null) {
            log.info("----->  getClientCards: This Client does not exist <-----");
            throw new ValidationException(VTSuiteMessages.CLIENT_DOES_NOT_EXIST);
        }
    }

    /**
     * Add an account to a client Validator
     *
     * @param clientDisplay
     * @param accountDisplay
     * @throws java.lang.Exception
     */
    public static void addAccountToClient(ClientDisplay clientDisplay, AccountDisplay accountDisplay) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SETTINGS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_USERS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_UPDATE.getId());
        }
    }
}
