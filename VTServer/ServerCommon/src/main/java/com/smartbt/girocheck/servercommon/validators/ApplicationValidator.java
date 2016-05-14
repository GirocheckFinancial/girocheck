/*
 *  File ApplicationValidator
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
package com.smartbt.girocheck.servercommon.validators;

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.vtsuite.common.SessionClerk;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.ApplicationDAO;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class ApplicationValidator {

    private static final Logger log = Logger.getLogger(ApplicationValidator.class);

    /**
     * Get all the Applications Validator
     *
     * @throws java.lang.Exception
     */
    public static void getApplications() throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_BOARDING.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT.getId());
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_BOARDING.getId());
        }
    }

    /**
     * Add a Application Validator
     *
     * @param application
     * @throws java.lang.Exception
     */
    public static void addApplication(Application application) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_BOARDING.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT_NEW_APP.getId());
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_BOARDING.getId());
        }
        String errors = UtilValidator.getErrorsAsString(application);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        if (!ApplicationDAO.get().findApplicationByName(application.getName()).isEmpty()) {
            throw new ValidationException(VTSuiteMessages.ERROR_APPLICATION_REPEATED + " '" + application.getName() + "'.");
        }
    }
}
