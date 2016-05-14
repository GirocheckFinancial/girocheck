/*
 *  File MonitoringValidator
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
import com.smartbt.vtsuite.servercommon.model.Monitoring;
import com.smartbt.vtsuite.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class MonitoringValidator {

    private static final Logger log = Logger.getLogger(MonitoringValidator.class);

    /**
     * Get a monitoring by a given id
     *
     * @param id Terminal id
     *
     */
    public static void getMonitoring(int id) {

    }

    /**
     * Search all the Monitoring
     *
     * @param idEntity
     * @param searchFilter
     * @param entityType
     * @param firstResult
     * @param maxResult
     */
    public static void searchMonitoring(EntityType entityType, int idEntity, String searchFilter, int firstResult, int maxResult) {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MONITORING.getId());
        }
    }

    public static void addMonitoring(Monitoring monitoring) throws Exception {
    }

    public static void startMonitoringTerminal(int terminalId) {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MONITORING.getId(), NomUserPrivileges.ALLOW_MONITORING_ACTIVATION.getId());
        }
    }

    public static void stopMonitoringTerminal(int terminalId) {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MONITORING.getId(), NomUserPrivileges.ALLOW_MONITORING_DEACTIVATION.getId());
        }
    }
}
