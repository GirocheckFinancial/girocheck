/*
 *  File UtilValidator
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
package com.smartbt.girocheck.servercommon.validators.utils;

import com.smartbt.girocheck.common.SessionAMSUser;
//import com.smartbt.vtsuite.common.SessionClerk;
import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.PrivilegesDAO;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import javax.ws.rs.ForbiddenException;
import javax.xml.bind.ValidationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.apache.log4j.Logger;
/**
 *
 * @author Ariel
 */
public class UtilValidator {

    private static final Logger log = Logger.getLogger(UtilValidator.class);

    public static void validatePrivilegesThrowEx(int... idsPrivileges) throws ForbiddenException {
        if (!validatePrivileges(idsPrivileges)) {
            throw new ForbiddenException();
        }
    }

    public static boolean validatePrivileges(int... idsPrivileges) throws ForbiddenException {
        for (int id : idsPrivileges) {
//            if (SessionClerk.get() != null) {
//                if (!PrivilegesDAO.get().existRolePrivilege(EntityType.CLERK,
//                        SessionClerk.get().getClerk().getClerkRole().getId(), id)) {
//                    return false;
//                }
//            } else 
                if (SessionAMSUser.get() != null) {
                if (!PrivilegesDAO.get().existRolePrivilege(EntityType.AMS,
                        SessionAMSUser.get().getRole().getId(), id)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void validateSearchFilter(String searchFilter) throws ValidationException {
        if (searchFilter != null && !Validator.isSearchAllowed(searchFilter)) {
            log.info("----->  This filter is not allowed <-----");
            throw new ValidationException(VTSuiteMessages.FILTER_IS_NOT_ALLOWED);
        }
    }
    
    private static Set<ConstraintViolation<Object>> getViolations(Object object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();

        return validator.validate(object);
    }

    public static boolean isValid(Object object) {
        return getViolations(object).isEmpty();
    }

    public static List<String> getErrorsAsList(Object object) {
        List<String> errors = new LinkedList<String>();

        Set<ConstraintViolation<Object>> violations = getViolations(object);
        for (ConstraintViolation violation : violations) {
            errors.add(violation.getMessage());
        }
        return errors;
    }
    
    public static String getErrorsAsString(Object object) {
        String errors="";

        Set<ConstraintViolation<Object>> violations = getViolations(object);
        for (ConstraintViolation violation : violations) {
            errors+= violation.getMessage()+"\n";
        }
        
        return errors;
    }
}
