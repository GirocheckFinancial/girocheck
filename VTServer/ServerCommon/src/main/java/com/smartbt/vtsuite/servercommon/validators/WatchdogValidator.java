/*
 *  File WatchdogValidator
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
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.WatchdogAlert;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntity;
import com.smartbt.vtsuite.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class WatchdogValidator {

    private static final Logger log = Logger.getLogger(WatchdogValidator.class);

    /**
     * Save or Update a WatchdogEntity Validator
     *
     * @param watchdogEntity
     * @throws javax.jms.JMSException
     */
    public static void saveOrUpdateWatchdogEntity(WatchdogEntity watchdogEntity) throws Exception {
        if (SessionClerk.get() != null) {

        } else if (SessionAMSUser.get() != null) {
//           UtilValidator.validatePrivilegesThrowEx(
//                        NomUserPrivileges.ALLOW_WATCHDOG.getId(), NomUserPrivileges.ALLOW_WATCHDOG_RULES.getId());
//            
//           if (watchdogEntity.getId() != 0) {
//                UtilValidator.validatePrivilegesThrowEx( NomUserPrivileges.ALLOW_WATCHDOG_RULES_UPDATE.getId());
//            } else {
//                UtilValidator.validatePrivilegesThrowEx(NomUserPrivileges.ALLOW_WATCHDOG_RULES_ADD.getId());
//            }
        }
    }

    /**
     * Add a WatchdogAlert Validator
     *
     * @param watchdogAlert
     * @throws java.lang.Exception
     */
    public static void addWatchdogAlert(WatchdogAlert watchdogAlert) throws Exception {
    }

    /**
     * Delete watchdog rule Validator
     *
     * @param watchdogEntityId
     * @throws java.lang.Exception
     */
    public static void deleteWatchdogEntity(int watchdogEntityId) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//             UtilValidator.validatePrivilegesThrowEx(
//                        NomUserPrivileges.ALLOW_WATCHDOG.getId()
//                     , NomUserPrivileges.ALLOW_WATCHDOG_RULES.getId()
//                     ,NomUserPrivileges.ALLOW_WATCHDOG_RULES_DELETE.getId());
        }
    }

    /**
     * Delete watchdog alert Validator
     *
     * @param watchdogAlertId
     * @throws java.lang.Exception
     */
    public static void deleteWatchdogAlert(int watchdogAlertId) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
//             UtilValidator.validatePrivilegesThrowEx(
//                        NomUserPrivileges.ALLOW_WATCHDOG.getId()
//                     , NomUserPrivileges.ALLOW_WATCHDOG_ALERT.getId()
//                     ,NomUserPrivileges.ALLOW_WATCHDOG_ALERT_DELETE.getId());
        }
    }

    /**
     * Delete watchdog alert for a specific clerk Validator
     *
     * @param clerk
     * @param watchdogAlertId
     * @throws java.lang.Exception
     */
    public static void deleteWatchdogAlertByClerkDestination(Clerk clerk, int watchdogAlertId) throws Exception {
      
    }

    /**
     * Delete all watchdogAlerts for a specific clerk Validator
     *
     * @param clerk
     * @throws java.lang.Exception
     */
    public static void deleteAllWatchdogAlertsByClerkDestination(Clerk clerk) throws Exception {
       
    }

    /**
     * Delete all watchdogAlerts for a specific entity (Customer, Merchant or
     * Terminal) Validator
     *
     * @param entityType
     * @param idEntity
     * @throws java.lang.Exception
     */
    public static void deleteAllWatchdogAlertsByEntity(EntityType entityType, int idEntity) throws Exception {
//        UtilValidator.validatePrivilegesThrowEx(
//                        NomUserPrivileges.ALLOW_WATCHDOG.getId()
//                     , NomUserPrivileges.ALLOW_WATCHDOG_ALERT.getId()
//                     ,NomUserPrivileges.ALLOW_WATCHDOG_ALERT_DELETE_ALL.getId());
    }

    /**
     * Get a rule Validator
     *
     * @param watchdogEntityId
     * @throws java.lang.Exception
     */
    public static void getWatchdogEntity(int watchdogEntityId) throws Exception {
        if (SessionClerk.get() != null) {
        } else if (SessionAMSUser.get() != null) {
        }
    }

    /**
     * Get all watchdogs Validator
     *
     * @throws java.lang.Exception
     */
    public static void getWatchdogs() throws Exception {
//        if (SessionClerk.get() != null) {
//        } else if (SessionAMSUser.get() != null) {
//             UtilValidator.validatePrivilegesThrowEx(
//                        NomUserPrivileges.ALLOW_WATCHDOG.getId());
//        }
    }

    /**
     * Get rule by entity type Validator
     *
     * @param entityType
     * @param idEntity
     * @throws java.lang.Exception
     */
    public static void getWatchdogEntitiesByEntity(EntityType entityType, int idEntity) throws Exception {
//             UtilValidator.validatePrivilegesThrowEx(
//                        NomUserPrivileges.ALLOW_WATCHDOG.getId()
//                     , NomUserPrivileges.ALLOW_WATCHDOG_RULES.getId());
    }

    /**
     * Get watchdog alert by entity Validator
     *
     * @param entityType
     * @param idEntity
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param pageNumber
     * @param rowsPerPage
     * @throws java.lang.Exception
     */
    public static void getWatchdogAlertsByEntity(EntityType entityType, int idEntity,
            String searchFilter, Date startRangeDate, Date endRangeDate, int pageNumber, int rowsPerPage) throws Exception {
//            UtilValidator.validatePrivilegesThrowEx(
//                                   NomUserPrivileges.ALLOW_WATCHDOG.getId()
//                                , NomUserPrivileges.ALLOW_WATCHDOG_ALERT.getId());
    }

    /**
     * Get watchdog alert by clerk destination Validator
     *
     * @param clerk
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param pageNumber
     * @param rowsPerPage
     * @throws java.lang.Exception
     */
    public static void getWatchdogAlertsByClerkDestination(Clerk clerk, String searchFilter, Date startRangeDate, Date endRangeDate, int pageNumber, int rowsPerPage) throws Exception {
        
    }
}
