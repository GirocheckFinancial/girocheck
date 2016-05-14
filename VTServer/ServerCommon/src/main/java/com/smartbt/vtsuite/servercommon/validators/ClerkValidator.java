/*
 *  File ClerkValidator
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
import com.smartbt.vtsuite.servercommon.dao.ClerkDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.UserDisplay;
import com.smartbt.vtsuite.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class ClerkValidator {

    private static final Logger log = Logger.getLogger(ClerkValidator.class);

    public static void searchClerk(int idEntity, String searchFilter, EntityType entityType, int pageNumber, int rowsPerPage) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SERVICES.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_USERS.getId());
        }
        UtilValidator.validateSearchFilter(searchFilter);

        if (entityType == null) {
            throw new ValidationException(VTSuiteMessages.VALUE_IS_EMPTY + " (entityType)");
        }
    }

    /**
     * Get clerks by Entity Validator
     *
     * @param idEntity
     * @param entityType
     * @param pageNumber
     * @param rowsPerPage
     * @throws java.lang.Exception
     */
    public static void getClerksByEntityType(int idEntity, EntityType entityType, int pageNumber, int rowsPerPage) throws Exception {
    }

    /**
     * Update a Clerk Validator
     *
     * @param clerk
     * @param application
     * @throws java.lang.Exception
     */
    public static void updateClerk(UserDisplay clerk, NomApplication application) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SETTINGS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_USERS.getId()
//                    , NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_UPDATE.getId());
        }
        if (ClerkDAO.get().findById(clerk.getId()) == null) {
            log.info("----->  updateClerk: This Clerk does not exist <-----");
            throw new ValidationException(VTSuiteMessages.CLERK_DOES_NOT_EXIST);
        }

        String errors = UtilValidator.getErrorsAsString(clerk);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    /**
     * Complete clerk's First Time Installation Validator
     *
     * @param clerk
     * @throws java.lang.Exception
     */
    public static void completeFirstTimeInstall(UserDisplay clerk) throws Exception {
    }
}
