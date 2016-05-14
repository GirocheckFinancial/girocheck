/*
 ** File: NomSystemProperties.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.vtcommon.nomenclators;

/**
 *
 * System Properties
 */
public enum NomSystemProperties {

    /**
     * SYSTEM_PROPERTY - LDAP_ACTIVE
     */
    LDAP_ACTIVE(1),
    /**
     * SYSTEM_PROPERTY - AUDIT_LOG_ACTIVE
     */
    AUDIT_LOG_ACTIVE(2),
    /**
     * SYSTEM_PROPERTY - EMAIL_SERVER_ADDRESS
     */
    EMAIL_SERVER_ADDRESS(3),
    /**
     * SYSTEM_PROPERTY - EMAIL_SERVER_PORT
     */
    EMAIL_SERVER_PORT(4),
    /**
     * SYSTEM_PROPERTY - EMAIL_SERVER_USERNAME
     */
    EMAIL_SERVER_USERNAME(5),
    /**
     * SYSTEM_PROPERTY - EMAIL_SERVER_PASSWORD
     */
    EMAIL_SERVER_PASSWORD(6),
    /**
     * SYSTEM_PROPERTY - EMAIL_FROM_ADDRESS
     */
    EMAIL_FROM_ADDRESS(7),
    /**
     * SYSTEM_PROPERTY - EMAIL_DEBUG
     */
    EMAIL_DEBUG(8),
    /**
     * SYSTEM_PROPERTY - TIMINGS_DEBUG
     */
    TIMINGS_DEBUG(9),
    /**
     * SYSTEM_PROPERTY - VTAMS_LOGOUT_URL
     */
    VTAMS_LOGOUT_URL(10),
    /**
     * SYSTEM_PROPERTY - HSM_ADDRESS
     */
    HSM_ADDRESS(11),
    /**
     * SYSTEM_PROPERTY - HSM_PORT
     */
    HSM_PORT(12),
    /**
     * SYSTEM_PROPERTY - BDK
     */
    BDK(13),
    /**
     * SYSTEM_PROPERTY - INITIALIZATION_VECTOR
     */
    INITIALIZATION_VECTOR(14),
    /**
     * SYSTEM_PROPERTY - HOST_TIMEOUT_MS
     */
    HOST_TIMEOUT_MS(15),
    /**
     * SYSTEM_PROPERTY - BEAN_SEND_TIMEOUT_MS
     */
    BEAN_SEND_TIMEOUT_MS(16),
    /**
     * SYSTEM_PROPERTY - BEAN_RECEIVE_TIMEOUT_MS
     */
    BEAN_RECEIVE_TIMEOUT_MS(17),
    /**
     * SYSTEM_PROPERTY - VTAPPLICATION_LOGOUT_URL
     */
    VTAPPLICATION_LOGOUT_URL(18);

    private NomSystemProperties(int id) {
        this.id = id;
    }
    private int id;

    /**
     * Get id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * getViewValue
     *
     * @return the value
     */
    public String getViewValue() {
        switch (this) {
            case LDAP_ACTIVE:
                return "ldap_active";

            case AUDIT_LOG_ACTIVE:
                return "audit_log_active";

            case EMAIL_SERVER_ADDRESS:
                return "email_server_address";

            case EMAIL_SERVER_PORT:
                return "email_server_port";

            case EMAIL_SERVER_USERNAME:
                return "email_server_username";

            case EMAIL_SERVER_PASSWORD:
                return "email_server_password";

            case EMAIL_FROM_ADDRESS:
                return "email_from_address";

            case EMAIL_DEBUG:
                return "email_debug";

            case TIMINGS_DEBUG:
                return "timings_debug";

            case VTAMS_LOGOUT_URL:
                return "vtams_logout_url";

            case HSM_ADDRESS:
                return "hsm_address";

            case HSM_PORT:
                return "hsm_port";

            case BDK:
                return "bdk";

            case INITIALIZATION_VECTOR:
                return "initialization_vector";

            case HOST_TIMEOUT_MS:
                return "host_timeout_ms";

            case BEAN_SEND_TIMEOUT_MS:
                return "bean_send_timeout_ms";

            case BEAN_RECEIVE_TIMEOUT_MS:
                return "bean_receive_timeout_ms";
                
            case VTAPPLICATION_LOGOUT_URL:
                return "vtapplication_logout_url";
                
            default:
                return "";
        }
    }
}
