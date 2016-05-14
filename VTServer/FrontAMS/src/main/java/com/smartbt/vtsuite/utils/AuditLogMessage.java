/*
 ** File: AuditLogMessage.java
 **
 ** Date Created: October 2013
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
package com.smartbt.vtsuite.utils;

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.i18n.I18N;
import com.smartbt.girocheck.servercommon.i18n.Messages;
import com.smartbt.girocheck.servercommon.manager.AuditLogManager;
//import com.smartbt.girocheck.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.vtcommon.Constants;

/**
 *
 * @author Ariel Saavedra
 */
public class AuditLogMessage {

    static BaseResponse setLog(String message, BaseResponse response) {
        if (response.getStatus() == Constants.CODE_SUCCESS) {
            AuditLogManager.get().addAuditLog(null, SessionAMSUser.get(), message);
        }
        return response;
    }

    /**
     *
     * @param appName
     * @param response
     * @return
     */
    public static BaseResponse logAddApplication(String appName, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_APPLICATION_ADDED, appName), response);
    }

    /**
     *
     * @param clerkId
     * @param clerkRoleName
     * @param response
     * @return
     */
    public static BaseResponse logUpdateClerk(String clerkId, String clerkRoleName, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_CLERK_UPDATED, clerkId, clerkRoleName), response);
    }

    /**
     *
     * @param clientId
     * @param response
     * @return
     */
    public static BaseResponse logDeleteClient(int clientId, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_CLIENT_DELETED, clientId), response);
    }

    /**
     *
     * @param clientId
     * @param response
     * @return
     */
    public static BaseResponse logSaveOrUpdateClient(int clientId, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_CLIENT_UPDATED, clientId), response);
    }

    /**
     *
     * @param entityType
     * @param idEntity
     * @param response
     * @return
     */
    public static BaseResponse logDeleteAllClients(String entityType, int idEntity, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_CLIENTS_ALL_DELETED, entityType, idEntity), response);
    }

    /**
     *
     * @param idMerchant
     * @param response
     * @return
     */
    public static BaseResponse logImportClients(int idMerchant, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_CLIENTS_IMPORTED, idMerchant), response);
    }

    /**
     *
     * @param entityType
     * @param idParameter
     * @param value
     * @param response
     * @return
     */
    public static BaseResponse logAddParameterValue(String entityType, int idParameter, String value, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_PARAMETER_VALUE_UPDATED, entityType, idParameter, value), response);
    }

    /**
     *
     * @param applicationParameter
     * @param response
     * @return
     */
//    public static BaseResponse logSaveOrUpdateApplicationParameter(ApplicationParameter applicationParameter, BaseResponse response) {
//        if (applicationParameter.getId() > 0) {
//            return setLog(I18N.get(Messages.AUDIT_LOG_APPLICATION_PARAMETER_UPDATED, applicationParameter.getParameter()), response);
//        } else {
//            return setLog(I18N.get(Messages.AUDIT_LOG_APPLICATION_PARAMETER_ADDED, applicationParameter.getParameter()), response);
//        }
//    }

    /**
     *
     * @param idApplicationParameter
     * @param response
     * @return
     */
    public static BaseResponse logDeleteApplicationParameter(int idApplicationParameter, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_APPLICATION_PARAMETER_DELETED, idApplicationParameter), response);
    }

    /**
     *
     * @param entityType
     * @param idParameter
     * @param value
     * @param response
     * @return
     */
    public static BaseResponse logUpdateParameterValue(String entityType, int idParameter, String value, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_PARAMETER_VALUE_UPDATED, entityType, idParameter, value), response);
    }

    /**
     *
     * @param entityType
     * @param idParameter
     * @param response
     * @return
     */
    public static BaseResponse logDeleteParameterValue(String entityType, int idParameter, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_PARAMETER_DELETED, entityType, idParameter), response);
    }

    /**
     *
     * @param entityType
     * @param idPrivilege
     * @param idRole
     * @param response
     * @return
     */
    public static BaseResponse logAddRolePrivilege(String entityType, int idPrivilege, int idRole, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_ROLE_PRIVILEGE_ADDED, entityType, idPrivilege, idRole), response);
    }

    /**
     *
     * @param entityType
     * @param id
     * @param response
     * @return
     */
    public static BaseResponse logDeleteRolePrivilege(String entityType, int id, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_ROLE_PRIVILEGE_DELETED, entityType, id), response);
    }

    /**
     *
     * @param user
     * @param response
     * @return
     */
    public static BaseResponse logUpdateUser(String user, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_USER_UPDATED, user), response);
    }

    /**
     *
     * @param entityType
     * @param name
     * @param response
     * @return
     */
    public static BaseResponse logAddRole(String entityType, String name, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_ROLE_ADDED, entityType, name), response);
    }

    /**
     *
     * @param entityType
     * @param name
     * @param idRole
     * @param response
     * @return
     */
    public static BaseResponse logUpdateRole(String entityType, String name, int idRole, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_ROLE_UPDATED, entityType, name, idRole), response);
    }

    /**
     *
     * @param entityType
     * @param idRole
     * @param response
     * @return
     */
    public static BaseResponse logDeleteRole(String entityType, int idRole, BaseResponse response) {
        return setLog(I18N.get(Messages.AUDIT_LOG_ROLE_DELETED, entityType, idRole), response);
    }
}
