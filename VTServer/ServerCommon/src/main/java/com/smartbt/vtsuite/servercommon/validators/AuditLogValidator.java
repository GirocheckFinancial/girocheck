/*
 *  File AuditLogValidator
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
import com.smartbt.vtsuite.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class AuditLogValidator {

    private static final Logger log = Logger.getLogger(AuditLogValidator.class);

    public static void searchAuditLogs(int idEntity, EntityType entityType, String searchFilter, Date startRangeDate, Date endRangeDate, int pageNumber, int rowsPerPage) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_AUDIT.getId());
        }

        UtilValidator.validateSearchFilter(searchFilter);
    }
}
