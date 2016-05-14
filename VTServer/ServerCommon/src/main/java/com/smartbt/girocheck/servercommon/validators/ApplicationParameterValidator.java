/*
 *  File ApplicationParameterValidator
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
import com.smartbt.vtsuite.servercommon.dao.ApplicationParameterDAO;
import com.smartbt.vtsuite.servercommon.dao.GeneralDAO;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue;
import com.smartbt.vtsuite.servercommon.model.TerminalParameterValue;
import com.smartbt.vtsuite.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class ApplicationParameterValidator {

    private static final Logger log = Logger.getLogger(ApplicationParameterValidator.class);

    public static void saveOrUpdate(ApplicationParameter applicationParameter) throws Exception {
       if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                      NomUserPrivileges.ALLOW_BOARDING.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_GLOBAL_LISTING.getId()
//                    , applicationParameter.getId() > 0 ? NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_GLOBAL_LISTING_UPDATE_PARAMETER.getId()
//                            : NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_GLOBAL_LISTING_ADD_PARAMETER.getId());
            UtilValidator.validatePrivilegesThrowEx(
                      NomUserPrivileges.ALLOW_BOARDING.getId());
        }
        
        if (applicationParameter.getId() > 0) {
            ApplicationParameter appParamValidation = ApplicationParameterDAO.get().findById(applicationParameter.getId());
            if (appParamValidation == null) {
                throw new ValidationException(VTSuiteMessages.PARAMETER_DOES_NOT_EXIST);
            }
            if (appParamValidation.isReadOnly()) {
                throw new ValidationException(VTSuiteMessages.PARAMETER_READ_ONLY);
            }
        }
        ParameterValueValidator.validateDataType(
                GeneralDAO.get().findDataTypeById(applicationParameter.getDataType().getId()), applicationParameter.getDefaultValue());
    }

    public static void delete(int applicationParameterId) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                      NomUserPrivileges.ALLOW_BOARDING.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_GLOBAL_LISTING.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_GLOBAL_LISTING_DELETE_PARAMETER.getId());
            UtilValidator.validatePrivilegesThrowEx(
                      NomUserPrivileges.ALLOW_BOARDING.getId());
        }

        ApplicationParameter appParamValidation = ApplicationParameterDAO.get().findById(applicationParameterId);
        if (appParamValidation == null) {
            throw new ValidationException(VTSuiteMessages.PARAMETER_DOES_NOT_EXIST);
        }
        if (appParamValidation.isReadOnly()) {
            throw new ValidationException(VTSuiteMessages.PARAMETER_READ_ONLY);
        }

        if (!appParamValidation.getApplicationParameterValues().isEmpty()) {
            String apps = "";
            for (ApplicationParameterValue applicationParameterValue : appParamValidation.getApplicationParameterValues()) {
                apps += "'" + applicationParameterValue.getApplication().getName() + "', ";
            }
            throw new ValidationException("The parameter '" + appParamValidation.getParameter() + "' is associated with application(s) "
                    + apps.substring(0, apps.length() - 2));
        }
        if (!appParamValidation.getTerminalParameterValues().isEmpty()) {
            String terminals = "";
            for (TerminalParameterValue terminalParameterValue : appParamValidation.getTerminalParameterValues()) {
                terminals += "'" + terminalParameterValue.getTerminal().getTerminalId() + "', ";
            }
            throw new ValidationException("The parameter '" + appParamValidation.getParameter() + "' is associated with terminals(s) "
                    + terminals.substring(0, terminals.length() - 2));
        }
    }
    
     public static void search(String searchFilter, int pageNumber, int rowsPerPage) throws Exception{
       if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                      NomUserPrivileges.ALLOW_BOARDING.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM.getId()
//                    , NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_GLOBAL_LISTING.getId());
            UtilValidator.validatePrivilegesThrowEx(
                      NomUserPrivileges.ALLOW_BOARDING.getId());
        }
        UtilValidator.validateSearchFilter(searchFilter);
    }
}
