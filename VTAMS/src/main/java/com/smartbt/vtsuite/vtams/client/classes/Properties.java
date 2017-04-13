/*
 ** File: Properties.java
 **
 ** Date Created: May 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.classes;

/**
 * The Properties class
 *
 * @author Ariamnet Lopez
 */
public class Properties {

    /*
     * This flag is set to true just for Dev Mode. Need to be set to FALSE for Production
     */
    public static boolean DEV_MODE = Boolean.FALSE;
    public static String EXT_HOST = "http://localhost:2080";// "http://mi2dev.warp68.net:17011";
    /**
     * Web Service Root URL
     */
    public static final String WS_URL = Properties.DEV_MODE ? Properties.EXT_HOST + "/FrontAMS/webresources/VTAMS/" : "/FrontAMS/webresources/VTAMS/";
    /**
     * Web Service to HeartBeat
     *
     * Parameters: [NONE]
     */
    public static final String DO_HEARBEAT_WS = WS_URL + "doHeartBeat";
    /**
     * Web Service for get a System Property by a given property name.
     *
     * Parameters: name
     */
    public static final String GET_SYSTEM_PROPERTY_WS = WS_URL + "getSystemProperty";
    /**
     * Web Service for get all Customers
     *
     */
    public static final String GET_CUSTOMERS_WS = WS_URL + "getCustomers";
    public static final String GET_AGRUPATION_WS = WS_URL + "getAgrupation";
    public static final String GET_SEARCH_AGRUPATIONS_WS = WS_URL + "searchAgrupations";
    /**
     * Web Service for searching Customers, Merchants, Terminals that match the
     * filter.
     *
     * Parameters: searchFilter
     */
    //public static final String SEARCH_CUSTOMER_WS = WS_URL + "searchCustomer";
    /**
     * Web Service for list all Agrupations.
     *
     * Parameters: idCustomer
     */
    public static final String LIST_AGRUPATIONS_WS = WS_URL + "listAgrupations";
    /**
     * Web Service for get all the merchants of a Customer.
     *
     * Parameters: idCustomer
     */
    public static final String GET_MERCHANTS_BY_AGRUPATION_WS = WS_URL + "getMerchantsByAgrupation";
    public static final String GET_MERCHANTS_BY_ID_WS = WS_URL + "getMerchantsById";
    public static final String GET_SEARCH_MERCHANTS_WS = WS_URL + "searchMerchants";
    /**
     * Web Service for get all the merchants of a Customer.
     *
     * Parameters: idCustomer
     */
    public static final String SEARCH_MERCHANTS_WS = WS_URL + "searchMerchants";
    /**
     * Web Service for get all the terminals of a Merchant.
     *
     * Parameters: idMerchant
     */
    public static final String GET_TERMINALS_BY_MERCHANT_WS = WS_URL + "getTerminalsByMerchant";
    public static final String SAVE_OR_UPDATE_TERMINAL_WS = WS_URL + "saveOrUpdateTerminal";
    public static final String DELETE_TERMINAL_WS = WS_URL + "deleteTerminal";
    public static final String GET_TERMINAL_BY_ID_WS = WS_URL + "getTerminalById";
    /**
     * Web Service for get all the devices of a Terminal.
     *
     * Parameters: idTerminal
     */
    public static final String GET_DEVICES_BY_TERMINAL_WS = WS_URL + "getDevicesByTerminal";
    /**
     * Web Service to reset a merchant parameter.
     *
     * Parameters: idCustomer
     */
    public static final String RESET_MERCHANT_PARAMETER_WS = WS_URL + "resetMerchantParameter";
    /**
     * Web Service to set a new Merchant Logo.
     *
     * Parameters: idMerchant
     */
    public static final String SET_MERCHANT_LOGO_WS = WS_URL + "setMerchantLogo";
    /**
     * Web Service for get a Customer details.
     *
     * Parameters: idCustomer
     */
    public static final String GET_CUSTOMER_WS = WS_URL + "getCustomer";
    /**
     * Web Service for get a Merchant details.
     *
     * Parameters: idMerchant
     */
    public static final String GET_MERCHANT_WS = WS_URL + "getMerchant";
    /**
     * Web Service for get a Terminal details.
     *
     * Parameters: idTerminal
     */
    public static final String GET_TERMINAL_WS = WS_URL + "getTerminal";
    /**
     * Web Service for get a Device details.
     *
     * Parameters: idDevice
     */
    public static final String GET_DEVICE_WS = WS_URL + "getDevice";
    /**
     * Web Service for get a Client details.
     *
     * Parameters: getClient
     */
    public static final String GET_CLIENT_WS = WS_URL + "getClient";
    public static final String UPDATE_CLIENT_BLACK_LIST_WS = WS_URL + "updateClientBlackList";
    public static final String GET_USER_WS = WS_URL + "getUser";
    /**
     * Web Service for search all transactions that match the filter.
     *
     * Parameters: searchFilter
     */
    public static final String SEARCH_INVENTORY_WS = WS_URL + "searchInventory";
    public static final String SEARCH_TRANSACTIONS_WS = WS_URL + "searchTransactions";
    public static final String SEARCH_SUB_TRANSACTIONS_WS = WS_URL + "listSubTransactions";
    /**
     * Web Service for get a Transaction details.
     *
     * Parameters: idTransaction
     */
    public static final String GET_TRANSACTION_WS = WS_URL + "getTransaction";
    /**
     * Web Service for get a Transaction receipt.
     *
     * Parameters: id
     */
    public static final String GET_TRANSACTION_RECEIPT_WS = WS_URL + "getTransactionReceipt";
    /**
     * Web Service for get all the Parameters given the Entity Type.
     *
     * Parameters: [NONE]
     */
    public static final String GET_NOT_CONTAINED_PARAMETERS_WS = WS_URL + "getNotContainedParameters";
    /**
     * Web Service for search the Parameter Values that match the filter given
     * the Entity Type.
     *
     * Parameters: searchFilter
     */
    public static final String SEARCH_PARAMETERS_VALUE_WS = WS_URL + "searchParametersValue";
    /**
     * Web Service for add a new Parameter Value to an Entity.
     *
     * Parameters: idMerchant, idParameter, value
     */
    public static final String ADD_PARAMETER_VALUE_WS = WS_URL + "addParameterValue";
    /**
     * Web Service for update the Parameter Value of an Entity.
     *
     * Parameters: id, value
     */
    public static final String UPDATE_PARAMETER_VALUE_WS = WS_URL + "updateParameterValue";
    /**
     * Web Service for delete the Parameter Value of an Entity.
     *
     * Parameters: id
     */
    public static final String DELETE_PARAMETER_VALUE_WS = WS_URL + "deleteParameterValue";
    /**
     * Web Service for search Application Parameters that match the given
     * filter.
     *
     * Parameters: searchFilter
     */
    //public static final String SEARCH_APPLICATION_PARAMETERS_WS = WS_URL + "searchApplicationParameters";
    public static final String SEARCH_PARAMETERS_WS = WS_URL + "searchParameters";
    /**
     * Web Service for save or update an application parameter.
     */
    public static final String SAVE_OR_UPDATE_APPLICATION_PARAMETER_WS = WS_URL + "saveOrUpdateApplicationParameter";
    /**
     * Web Service for delete an application parameter.
     */
    public static final String DELETE_APPLICATION_PARAMETER_WS = WS_URL + "deleteApplicationParameter";
    /**
     * Web Service for search all clerks that match the filter.
     *
     * Parameters: idMerchant, searchFilter
     */
    public static final String SEARCH_CLERKS_WS = WS_URL + "searchClerks";
    /**
     * Web Service for get all the Customer's clerks given an EntityType and its
     * id.
     *
     * Parameters: idMerchant, searchFilter
     */
    public static final String GET_CLERKS_BY_ENTITY_TYPE_WS = WS_URL + "getClerksByEntityType";
    /**
     * Web Service for update a Clerk Information (JUST THE ROLE).
     *
     * Parameters: Clerk
     */
    public static final String UPDATE_CLERK_WS = WS_URL + "updateClerk";
    /**
     * Web Service for get all the roles that can be assigned to a Clerk or a
     * User.
     *
     * Parameters: [NONE]
     */
    public static final String GET_ROLES_WS = WS_URL + "getRoles";
    /**
     * Web Service for add a new Clerk role or a new User Role.
     *
     * Parameters: ClerkRole
     */
    public static final String ADD_ROLE_WS = WS_URL + "addRole";
    /**
     * Web Service for update a role.
     *
     * Parameters: ClerkRole
     */
    public static final String UPDATE_ROLE_WS = WS_URL + "updateRole";
    /**
     * Web Service for delete a Role.
     *
     * Parameters: id
     */
    public static final String DELETE_ROLE_WS = WS_URL + "deleteRole";
    public static final String DELETE_MERCHANT_WS = WS_URL + "deleteMerchant";
    public static final String SEARCH_BOARDING_STATUS_WS = WS_URL + "searchBoardingStatus";
    /**
     * Web Service for get all the Applications.
     *
     * Parameters: [NONE]
     */
    public static final String GET_APPLICATIONS_WS = WS_URL + "getApplications";
    /**
     * Web Service for add a new Application.
     *
     * Parameters: Application {name, description}
     */
    public static final String ADD_APPLICATION_WS = WS_URL + "addApplication";
    /**
     * Web Service for search the AMS users that match the filter.
     *
     * Parameters: searchFilter
     */
    public static final String SEARCH_USERS_WS = WS_URL + "searchUsers";
    /**
     * Web Service for update an AMS user(JUST THE ROLE).
     *
     * Parameters: User
     */
    public static final String UPDATE_USER_WS = WS_URL + "updateUser";
    public static final String ADD_USER_WS = WS_URL + "addUser";
    public static final String DELETE_USER_WS = WS_URL + "deleteUser";
    public static final String SEARCH_ROLE_PRIVILEGES_WS = WS_URL + "searchRolePrivileges";
    public static final String ADD_ROLE_PRIVILEGE_WS = WS_URL + "addRolePrivilege";
    public static final String DELETE_ROLE_PRIVILEGE_WS = WS_URL + "deleteRolePrivilege";
    /**
     * Web Service for search all the Merchant's clients that match the filter.
     *
     * Parameters: idMerchant, searchFilter
     */
    public static final String SEARCH_CLIENTS_WS = WS_URL + "searchClients";
    /**
     * Web Service for import new Merchant's clients.
     *
     * Parameters: idMerchant, base64 csv file to import
     */
    public static final String IMPORT_MERCHANT_CLIENTS_WS = WS_URL + "importMerchantClients";
    /**
     * Web Service for update a Client Information.
     *
     * Parameters: Client
     */
    public static final String SAVE_OR_UPDATE_CLIENT_WS = WS_URL + "saveOrUpdateClient";
    /**
     * Web Service for delete a Client.
     *
     * Parameters: id
     */
    public static final String DELETE_CLIENT_WS = WS_URL + "deleteClient";
    public static final String DELETE_AGRUPATION_WS = WS_URL + "deleteAgrupation";
    /**
     * Web Service for delete all Merchant's clients.
     *
     * Parameters: idMerchant
     */
    public static final String DELETE_ALL_CLIENT_WS = WS_URL + "deleteAllClients";
    /**
     * Import clients from csv file.
     *
     * Parameters: idMerchant
     */
    public static final String IMPORT_CLIENTS_WS = WS_URL + "importClients";
    /**
     * Web Service to get the States.
     *
     * Parameters: [NONE]
     */
    public static final String LIST_STATES_WS = WS_URL + "listStates";
    public static final String LIST_CARD_PROGRAM_WS = WS_URL + "listCardProgram";
    /**
     * Web Service to get the dataTypes.
     *
     * Parameters: [NONE]
     */
    public static final String LIST_DATA_TYPES_WS = WS_URL + "listDataTypes";
    /**
     * Web Service to get the Telephone Types.
     *
     * Parameters: [NONE]
     */
    public static final String LIST_PHONE_TYPES_WS = WS_URL + "listPhoneTypes";
    public static final String SEARCH_AUDIT_LOGS = WS_URL + "searchAuditLogs";
    public static final String SAVE_OR_UPDATE_WATCHDOG_ENTITY = WS_URL + "saveOrUpdateWatchdogEntity";
    public static final String DELETE_WATCHDOG_ENTITY = WS_URL + "deleteWatchdogEntity";
    public static final String DELETE_WATCHDOG_ALERT = WS_URL + "deleteWatchdogAlert";
    public static final String DELETE_ALL_WATCHDOG_ALERTS_BY_ENTITY = WS_URL + "deleteAllWatchdogAlertsByEntity";
    public static final String GET_WATCHDOG_ENTITIES_BY_ENTITY = WS_URL + "getWatchdogEntitiesByEntity";
    public static final String GET_WATCHDOG_ALERTS_BY_ENTITY = WS_URL + "getWatchdogAlertsByEntity";
    public static final String GET_WATCHDOG_ENTITY = WS_URL + "getWatchdogEntity";
    public static final String GET_WATCHDOGS = WS_URL + "getWatchdogs";
    public static final String GET_DASHBOARD_ENVIROMENTAL = WS_URL + "getDashboardEnvironmental";
    public static final String GET_MONITORING = WS_URL + "getMonitoring";
    public static final String SEARCH_MONITORING = WS_URL + "searchMonitoring";
    public static final String START_MONITORING_TERMINAL = WS_URL + "startMonitoringTerminal";
    public static final String STOP_MONITORING_TERMINAL = WS_URL + "stopMonitoringTerminal";
    public static final String SAVE_OR_UPDATE_MERCHANT_LIST = WS_URL + "saveOrUpdateMerchantList";
    public static final String SAVE_OR_UPDATE_MERCHANT = WS_URL + "saveOrUpdateMerchant";
    public static final String SAVE_OR_UPDATE_AGRUPATION_WS = WS_URL + "saveOrUpdateAgrupation";
    public static final String SAVE_OR_UPDATE_INVENTORY_WS = WS_URL + "saveOrUpdateInventory";
    public static final String LIST_COUNTRY_WS = WS_URL + "listCountry";
    public static final String GET_ADDRESS_IMAGE_FORM_WS = WS_URL + "getAddressFormImage";
    public static final String GET_TRANSACTION_IMAGE_WS = WS_URL + "getTransactionImage";
    public static final String DELETE_SESSION_WS = WS_URL + "deleteSession";
    public static final String CHANGE_PASSWORD_WS = WS_URL + "changePaswword";
    public static final String TRANSACTIONREPORTS_WS = WS_URL + "searchTransactionReport";
    public static final String ENTITYREPORTS_WS = WS_URL + "searchEntityReport";
    public static final String SEARCH_FEESCHEDULE_WS = WS_URL + "searchFeeSchedule";
    public static final String UPDATE_FEESCHEDULE_WS = WS_URL + "updateFeeSchedule";
    public static final String ADD_FEESCHEDULE_WS = WS_URL + "addFeeSchedule";
    public static final String DELETE_FEESCHEDULE_WS = WS_URL + "deleteFeeSchedule";
    public static final String SEARCH_TXN_METHOD_WS = WS_URL + "searchTransactionMethod";
    public static final String SEARCH_FEEBUCKETS_WS = WS_URL + "searchFeeBucket";
    public static final String ADD_FEEBUCKETS_WS = WS_URL + "addFeeBucket";
    public static final String UPDATE_FEEBUCKETS_WS = WS_URL + "updateFeeBucket";
    public static final String DELETE_FEEBUCKETS_WS = WS_URL + "deleteFeeBucket";
    public static final String SEARCH_CHECK_DETAILS_WS = WS_URL + "searchCheckDetails";
    public static final String RESEND_CHECK_WS = WS_URL + "resendCheck";
    public static final String UPDATE_OPTOUT_CLIENTS_WS = WS_URL + "updateClientOptOut";

    public static String getUrl(String url) {
        return WS_URL + url;
    }
}
