/*
 ** File: VTSuiteSoapAMSWebServices.java
 **
 ** Date Created: March 2013
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
package com.smartbt.vtsuite.controller;

//import com.smartbt.vtsuite.display.common.model.RoleDisplay;
//import com.smartbt.vtsuite.display.common.message.BaseResponse;
//import com.smartbt.vtsuite.display.common.model.ApplicationDisplay;
//import com.smartbt.vtsuite.display.common.model.UserDisplay;
//import com.smartbt.vtsuite.display.common.model.ClerkDisplay;
//import com.smartbt.vtsuite.manager.Manager;
//import com.smartbt.vtsuite.conf.AppConf;
//import com.smartbt.vtsuite.display.common.message.ResponseDataList;
//import com.smartbt.vtsuite.vtcommon.Constants;
//import com.smartbt.vtsuite.vtcommon.enums.ActivityFilter;
//import com.smartbt.vtsuite.vtcommon.enums.EntityType;
//import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import javax.annotation.Resource;
//import javax.jws.WebParam;
//import javax.jws.WebResult;
//import javax.jws.WebService;
//import javax.xml.ws.WebServiceContext;
//import javax.xml.ws.handler.MessageContext;

/**
 * VT Suite SOAP AMS Web Services
 */
//@WebService(serviceName = "VTSuiteSoapAMSWebServices")
public class VTSuiteSoapAMSWebServices {

  //  @Resource
  //  private WebServiceContext wsctx;
   // private Manager frontManager = new Manager();
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VTSuiteSoapAMSWebServices.class);
//
//    /**
//     * Search all the Transactions by a given Merchant's id and a filter
//     *
//     * @param idMerchant
//     * @param searchFilter
//     * @return
//     */
//    @WebResult(name = "searchMerchantTransactions")
//    public ResponseDataList searchTransactions(@WebParam(name = "idMerchant") int idMerchant, @WebParam(name = "searchFilter") String searchFilter) {
//        log.info("-----> [AMS] Request getting MerchantTransactions has been sent <-----");
//        log.info("Incoming parameters : \n idMerchant: " + idMerchant + " \n searchFilter: " + searchFilter);
//        return frontManager.getTransactions(idMerchant, searchFilter, EntityType.MERCHANT, null, null, 0, 0);
//    }
//
//    /**
//     * Search all the Customers by a given filter
//     *
//     * @param searchFilter
//     * @return
//     */
//    @WebResult(name = "search")
//    public ResponseDataList search(@WebParam(name = "searchFilter") String searchFilter) {
//        log.info("-----> [AMS] Request searching Customers has been sent <-----");
//        log.info("Incoming parameters : \n searchFilter: " + searchFilter);
//        return frontManager.search(searchFilter);
//    }
//
//    /**
//     * Get the Merchants by a given Customer's id
//     *
//     * @param id
//     * @return
//     */
//    @WebResult(name = "getMerchantsByCustomer")
//    public ResponseDataList getMerchantsByCustomer(@WebParam(name = "idCustomer") int idCustomer) {
//        log.info("-----> [AMS] Request getting MerchantsByCustomers has been sent <-----");
//        log.info("Incoming parameters : \n idCustomer: " + idCustomer);
//        return frontManager.getMerchantsByCustomer(idCustomer);
//    }
//
//    /**
//     * Get the Terminals by a given Merchant's id
//     *
//     * @param idMerchant
//     * @return
//     */
//    @WebResult(name = "getTerminalsByMerchant")
//    public ResponseDataList getTerminalsByMerchant(@WebParam(name = "idMerchant") int idMerchant) {
//        log.info("-----> [AMS] Request getting TerminalsByMerchant has been sent <-----");
//        log.info("Incoming parameters : \n idMerchant: " + idMerchant);
//        return frontManager.getTerminalsByMerchant(idMerchant);
//    }
//
//    /**
//     * Get the Devices by a given Terminal's id
//     *
//     * @param idTerminal
//     * @return
//     */
//    @WebResult(name = "getDevicesByTerminal")
//    public ResponseDataList getDevicesByTerminal(@WebParam(name = "idTerminal") int idTerminal) {
//        log.info("-----> [AMS] Request getting DevicesByTerminal has been sent <-----");
//        log.info("Incoming parameters : \n idTerminal: " + idTerminal);
//        return frontManager.getDevicesByTerminal(idTerminal);
//    }
//
//    /**
//     * Get the Device by a given id
//     *
//     * @param idDevice
//     * @return
//     */
//    @WebResult(name = "getDevice")
//    public ResponseDataList getDevice(@WebParam(name = "idDevice") int idDevice) {
//        log.info("-----> [AMS] Request getting Device has been sent <-----");
//        log.info("Incoming parameters : \n idDevice: " + idDevice);
//        return frontManager.getDevice(idDevice);
//    }
//
//    /**
//     * Get the Transaction by a given id
//     *
//     * @param idTransaction
//     * @return
//     */
//    @WebResult(name = "getTransaction")
//    public ResponseDataList getTransaction(@WebParam(name = "idTransaction") int idTransaction) {
//        log.info("-----> [AMS] Request getting Transaction has been sent <-----");
//        log.info("Incoming parameters : \n idTransaction: " + idTransaction);
//        return frontManager.getTransaction(idTransaction);
//    }
//
//    /**
//     * Search all the MerchantParameterValues by a given Merchant's id and a
//     * filter
//     *
//     * @param idMerchant
//     * @param searchFilter
//     * @return
//     */
//    @WebResult(name = "searchMerchantParameterValues")
//    public ResponseDataList searchMerchantParameterValues(@WebParam(name = "idMerchant") int idMerchant, @WebParam(name = "searchFilter") String searchFilter) {
//        log.info("-----> [AMS] Request searching MerchantParameterValues has been sent <-----");
//        log.info("Incoming parameters : \n idMerchant: " + idMerchant + "\n searchFilter: " + searchFilter);
//        return frontManager.searchMerchantParameterValue(idMerchant, searchFilter);
//    }
//
//    /**
//     * Add a MerchantParameterValue
//     *
//     * @param idMerchant
//     * @param idMerchantParameter
//     * @param value
//     * @return
//     */
//    @WebResult(name = "addMerchantParameterValue")
//    public BaseResponse addMerchantParameterValue(@WebParam(name = "idMerchant") int idMerchant, @WebParam(name = "idParameter") int idMerchantParameter, @WebParam(name = "value") String value) {
//        log.info("-----> [AMS] Request adding MerchantParameterValue has been sent <-----");
//        log.info("Incoming parameters : \n idMerchant: " + idMerchant + "\n idMerchantParameter: " + idMerchantParameter + "\n value: " + value);
//        return frontManager.addMerchantParameterValue(idMerchant, idMerchantParameter, value);
//    }
//
//    /**
//     * Delete a MerchantParameterValue
//     *
//     * @param id
//     * @return
//     */
//    @WebResult(name = "deleteMerchantParameterValue")
//    public BaseResponse deleteMerchantParameterValue(@WebParam(name = "id") int id) {
//        log.info("-----> [AMS] Request deleting MerchantParameterValue has been sent <-----");
//        log.info("Incoming parameters : \n id: " + id);
//        return frontManager.deleteMerchantParameterValue(id);
//    }
//
//    /**
//     * Update a MerchantParameterValues
//     *
//     * @param id
//     * @param value
//     * @return
//     */
//    @WebResult(name = "updateMerchantParameterValue")
//    public BaseResponse updateMerchantParameterValue(@WebParam(name = "id") int id, @WebParam(name = "value") String value) {
//        log.info("-----> [AMS] Request updating MerchantParameterValue has been sent <-----");
//        log.info("Incoming parameters : \n id: " + id + "\n value: " + value);
//        return frontManager.updateMerchantParameterValue(id, value);
//    }
//
//    /**
//     * Search all the Clerks by a given Merchant's id and a filter
//     *
//     * @param idMerchant
//     * @param searchFilter
//     * @return
//     */
//    @WebResult(name = "searchClerks")
//    public ResponseDataList searchClerks(@WebParam(name = "idMerchant") int idMerchant, @WebParam(name = "searchFilter") String searchFilter) {
//        log.info("-----> [AMS] Request searching Clerks has been sent <-----");
//        log.info("Incoming parameters : \n idMerchant: " + idMerchant + "\n searchFilter: " + searchFilter);
//        
//        //return frontManager.searchClerk(idMerchant, searchFilter);
//        return null;
//    }
//    
//    /**
//     * Search all the Clients by a given id, type, page number, rows per page, and a filter
//     *
//     * @param idEntity
//     * @param entityType
//     * @param searchFilter
//     * @param pageNumer
//     * @param rowsPerPage
//     * @return
//     */
//    @WebResult(name = "searchClients")
//    public ResponseDataList searchClients(@WebParam(name = "idEntity") int idEntity, @WebParam(name = "entityType") EntityType entityType,
//            @WebParam(name = "searchFilter") String searchFilter, @WebParam(name = "activityFilter") ActivityFilter activityFilter, @WebParam(name = "pageNumer") int pageNumber, 
//            @WebParam(name = "rowsPerPage") int rowsPerPage) {
//        log.info("-----> [AMS] Request searching Clerks has been sent <-----");
//        log.info("Incoming parameters : \n idEntity: " + idEntity + " \n EntityType: " + entityType.toString() + " \n searchFilter: " + searchFilter
//                + "\n pageNumber: " + pageNumber + "\n rowsPerPage: " + rowsPerPage);
//        
//        return  frontManager.searchClients(idEntity, entityType, searchFilter, activityFilter, pageNumber, rowsPerPage);
//    }
//    
//    /**
//     * delete client with given id (set to inactive)
//     *
//     * @param clientId
//     * @return
//     */
//    @WebResult(name = "deleteClient")
//    public BaseResponse deleteClient(@WebParam(name = "clientId") int clientId) {
//        log.info("-----> [AMS] Request searching Clerks has been sent <-----");
//        log.info("Incoming parameters : \n clientId: " + clientId);
//        
//        return frontManager.deleteClient(clientId);
//    }
//
//    /**
//     * Add a Application
//     *
//     * @param application
//     * @return
//     */
//    @WebResult(name = "addAplicacion")
//    public BaseResponse addAplicacion(@WebParam(name = "application") ApplicationDisplay application) {
//        log.info("-----> [AMS] Request adding Application has been sent <-----");
//        log.info("Incoming parameters : \n application: " + application.toString());
//        //TODO return proper representation object
//        return frontManager.addApplication(application);
//    }
//
//    /**
//     * Get the ApplicationParametersValues by a given Application's id
//     *
//     * @param idApplication
//     * @return
//     */
//    @WebResult(name = "getApplicationParameterValues")
//    public ResponseDataList getApplicationParameterValues(@WebParam(name = "idApplication") int idApplication) {
//        log.info("-----> [AMS] Request getting ApplicationParameterValues has been sent <-----");
//        log.info("Incoming parameters : \n idApplication: " + idApplication);
//        //TODO return proper representation object
//        return frontManager.getApplicationParameterValues(idApplication);
//    }
//
//    /**
//     * Add a ApplicationParameterValue
//     *
//     * @param idApplication
//     * @param idParameter
//     * @param value
//     * @return
//     */
//    @WebResult(name = "addApplicationParameterValue")
//    public BaseResponse addApplicationParameterValue(@WebParam(name = "idApplication") int idApplication, @WebParam(name = "idParameter") int idParameter, @WebParam(name = "value") String value) {
//        log.info("-----> [AMS] Request adding ApplicationParameterValue has been sent <-----");
//        log.info("Incoming parameters : \n idApplication: " + idApplication + "\n idParameter: " + idParameter + "\n value: " + value);
//        //TODO return proper representation object
//        return frontManager.addApplicationParameterValue(idApplication, idParameter, value);
//    }
//
//    /**
//     * Delete a ApplicationParameterValue
//     *
//     * @param id
//     * @return
//     */
//    @WebResult(name = "deleteApplicationParameterValue")
//    public BaseResponse deleteApplicationParameterValue(@WebParam(name = "id") int id) {
//        log.info("-----> [AMS] Request deleting ApplicationParameterValue has been sent <-----");
//        log.info("Incoming parameters : \n id: " + id);
//        //TODO return proper representation object
//        return frontManager.deleteApplicationParameterValue(id);
//    }
//
//    /**
//     * Update a Clerk
//     *
//     * @param clerk
//     * @return
//     */
//    @WebResult(name = "updateClerk")
//    public BaseResponse updateClerk(@WebParam(name = "clerk") ClerkDisplay clerk) {
//        log.info("-----> [AMS] Request updating Clerk has been sent <-----");
//        log.info("Incoming parameters : \n clerk: " + clerk.toString());
//        //TODO return proper representation object
//        return frontManager.updateClerk(clerk);
//    }
//
//    /**
//     * Get a Merchant by a given id
//     *
//     * @param idMerchant
//     * @return
//     */
//    @WebResult(name = "getMerchant")
//    public ResponseDataList getMerchant(@WebParam(name = "idMerchant") int idMerchant) {
//        log.info("-----> [AMS] Request getting Merchant has been sent <-----");
//        log.info("Incoming parameters : \n idMerchant: " + idMerchant);
//        //TODO return proper representation object
//        return frontManager.getMerchant(idMerchant);
//    }
//
//    /**
//     * Get a Customer by a given id
//     *
//     * @param idCustomer
//     * @return
//     */
//    @WebResult(name = "getCustomer")
//    public ResponseDataList getCustomer(@WebParam(name = "idCustomer") int idCustomer) {
//        log.info("-----> [AMS] Request getting Customer has been sent <-----");
//        log.info("Incoming parameters : \n idMerchant: " + idCustomer);
//        //TODO return proper representation object
//        return frontManager.getCustomer(idCustomer);
//    }
//
//    /**
//     * Get a Terminal by a given id
//     *
//     * @param idTerminal
//     * @return
//     */
//    @WebResult(name = "getTerminal")
//    public ResponseDataList getTerminal(@WebParam(name = "idTerminal") int idTerminal) {
//        log.info("-----> [AMS] Request getting Terminal has been sent <-----");
//        log.info("Incoming parameters : \n idTerminal: " + idTerminal);
//        //TODO return proper representation object
//        return frontManager.getTerminal(idTerminal);
//    }
//
//    /**
//     * Search all the AMSParameterValues by a given filter
//     *
//     * @param searchFilter
//     * @return
//     */
//    @WebResult(name = "searchAMSParameterValues")
//    public ResponseDataList searchAMSParameterValues(@WebParam(name = "searchFilter") String searchFilter) {
//        log.info("-----> [AMS] Request searching AMSParameterValues has been sent <-----");
//        log.info("Incoming parameters : \n searchFilter: " + searchFilter);
//        return frontManager.searchAMSParameter(searchFilter);
//    }
//
//    /**
//     * Add a AMSParameterValue
//     *
//     * @param idApplicationParameter
//     * @param value
//     * @return
//     */
//    @WebResult(name = "addAMSParameterValue")
//    public BaseResponse addAMSParameterValue(@WebParam(name = "idParameter") int idParameter, @WebParam(name = "value") String value) {
//        log.info("-----> [AMS] Request adding AMSParameterValue has been sent <-----");
//        log.info("Incoming parameters : \n idParameter: " + idParameter + "\n value: " + value);
//        return frontManager.addApplicationParameterValue(NomApplication.VT_AMS.getId(), idParameter, value);
//    }
//
//    /**
//     * Get all the ClerkRoles
//     *
//     * @return
//     */
//    @WebResult(name = "getClerkRoles")
//    public ResponseDataList getClerkRoles() {
//        log.info("-----> [AMS] Request getting ClerkRoles has been sent <-----");
//        log.info("Incoming parameters : [none]");
//        return frontManager.getClerkRoles();
//    }
//
//    /**
//     * Get the MerchantParameters by a given Merchant's id
//     *
//     * @param idMerchant
//     * @return
//     */
//    @WebResult(name = "getMerchantParameters")
//    public ResponseDataList getMerchantParameters(@WebParam(name = "idMerchant") int idMerchant) {
//        log.info("-----> [AMS] Request getting MerchantParameters has been sent <-----");
//        log.info("Incoming parameters : \n idMerchant: " + idMerchant);
//        return frontManager.getMerchantParameters(idMerchant);
//    }
//
//    /**
//     * Get all the AMSParameters
//     *
//     * @return
//     */
//    @WebResult(name = "getAMSParameters")
//    public ResponseDataList getAMSParameters() {
//        log.info("-----> [AMS] Request getting AMSParameters has been sent <-----");
//        log.info("Incoming parameters : [none]");
//        return frontManager.getApplicationParameters(NomApplication.VT_AMS.getId());
//    }
//
//    /**
//     * Get all the ApplicationParameters
//     *
//     * @param idApplication
//     * @return
//     */
//    @WebResult(name = "getApplicationParameters")
//    public ResponseDataList getApplicationParameters(@WebParam(name = "idApplication") int idApplication) {
//
//        return frontManager.getApplicationParameters(idApplication);
//    }
//
//    /**
//     * Add a ClerkRole
//     *
//     * @param role
//     * @return
//     */
//    @WebResult(name = "addClerkRole")
//    public BaseResponse addClerkRole(@WebParam(name = "role") RoleDisplay role) {
//        log.info("-----> [AMS] Request adding ClerkRole has been sent <-----");
//        log.info("Incoming parameters : \n role: " + role.toString());
//        return frontManager.addClerkRole(role);
//    }
//
//    /**
//     * Update a ClerkRole
//     *
//     * @param role
//     * @return
//     */
//    @WebResult(name = "updateClerkRole")
//    public BaseResponse updateClerkRole(@WebParam(name = "role") RoleDisplay role) {
//        log.info("-----> [AMS] Request updating ClerkRole has been sent <-----");
//        log.info("Incoming parameters : \n role: " + role.toString());
//        return frontManager.updateClerkRole(role);
//    }
//
//    /**
//     * Delete a ClerkRolePrivilege
//     *
//     * @param id
//     * @return
//     */
//    @WebResult(name = "deleteClerkRolePrivilege")
//    public BaseResponse deleteClerkRolePrivilege(@WebParam(name = "id") int id) {
//        log.info("-----> [AMS] Request deleting ClerkRolePrivilege has been sent <-----");
//        log.info("Incoming parameters : \n id: " + id);
//        return frontManager.deleteClerkRolePrivilege(id);
//    }
//
//    /**
//     * Get the ClerkRolePrivileges by a given idRole
//     *
//     * @param idRole
//     * @return
//     */
//    @WebResult(name = "getClerkRolePrivileges")
//    public ResponseDataList getClerkRolePrivileges(@WebParam(name = "idRole") int idRole) {
//        log.info("-----> [AMS] Request getting ClerkRolePrivileges has been sent <-----");
//        log.info("Incoming parameters : \n idRole: " + idRole);
//        return frontManager.getClerkRolePriviliges(idRole);
//    }
//
//    /**
//     * Get the ClerkPrivileges by a given Clerk's id
//     *
//     * @param id
//     * @return
//     */
//    @WebResult(name = "getClerkPrivileges")
//    public ResponseDataList getClerkPrivileges(@WebParam(name = "idRole") int idRole) {
//        log.info("-----> [AMS] Request getting ClerkPrivileges has been sent <-----");
//        log.info("Incoming parameters : \n id: " + idRole);
//        return frontManager.getClerkPrivileges(idRole);
//    }
//
//    /**
//     * Add a ClerkRolePrivilege
//     *
//     * @param idRole
//     * @param idPrivilege
//     * @return
//     */
//    @WebResult(name = "searchClerkRole")
//    public BaseResponse addClerkRolePrivilege(@WebParam(name = "idRole") int idRole, @WebParam(name = "idPrivilege") int idPrivilege) {
//        log.info("-----> [AMS] Request adding ClerkRolePrivilege has been sent <-----");
//        log.info("Incoming parameters : \n idRole: " + idRole + "\n idPrivilege: " + idPrivilege);
//        return frontManager.addClerkRolePrivilege(idRole, idPrivilege);
//    }
//
//    /**
//     * Search all the Users by a given filter
//     *
//     * @param searchFilter
//     * @return
//     */
//    @WebResult(name = "SmartResponseUserRolePrivilege")
//    public ResponseDataList searchUsers(@WebParam(name = "searchFilter") String searchFilter) {
//        log.info("-----> [AMS] Request searching Users has been sent <-----");
//        log.info("Incoming parameters : \n searchFilter: " + searchFilter);
//        return frontManager.searchUsers(searchFilter);
//    }
//
//    /**
//     * Update a User
//     *
//     * @param user
//     * @return
//     */
//    @WebResult(name = "updateUser")
//    public BaseResponse updateUser(@WebParam(name = "user") UserDisplay user) {
//        log.info("-----> [AMS] Request updating User has been sent <-----");
//        log.info("Incoming parameters : \n user: " + user.toString());
//        return frontManager.updateUser(user);
//    }
//
//    /**
//     * Search all the UserRole by a given filter
//     *
//     * @param searchFilter
//     * @return
//     */
//    @WebResult(name = "searchUsersRole")
//    public ResponseDataList searchUsersRole(@WebParam(name = "searchFilter") String searchFilter) {
//        log.info("-----> [AMS] Request searching UserRole has been sent <-----");
//        log.info("Incoming parameters : \n searchFilter: " + searchFilter);
//        return frontManager.searchUsersRole(searchFilter);
//    }
//
//    /**
//     * Get the UserPrivileges by a given User's id
//     *
//     * @param id
//     * @return
//     */
//    @WebResult(name = "getUserPrivileges")
//    public ResponseDataList getUserPrivileges(@WebParam(name = "idRole") int idRole) {
//        log.info("-----> [AMS] Request getting UsersPrivileges has been sent <-----");
//        log.info("Incoming parameters : \n idRole: " + idRole);
//        return frontManager.getUserPrivileges(idRole);
//    }
//
//    /**
//     * Add a UserRole
//     *
//     * @param role
//     * @return
//     */
//    @WebResult(name = "addUserRole")
//    public BaseResponse addUserRole(@WebParam(name = "role") RoleDisplay role) {
//        log.info("-----> [AMS] Request adding UserRole has been sent <-----");
//        log.info("Incoming parameters : \n role: " + role.toString());
//        return frontManager.addUserRole(role);
//    }
//
//    /**
//     * Update a UserRole
//     *
//     * @param role
//     * @return
//     */
//    @WebResult(name = "updateUserRole")
//    public BaseResponse updateUserRole(@WebParam(name = "role") RoleDisplay role) {
//        log.info("-----> [AMS] Request updating UserRole has been sent <-----");
//        log.info("Incoming parameters : \n role: " + role.toString());
//        return frontManager.updateUserRole(role);
//    }
//
//    /**
//     * Delete a UserRole
//     *
//     * @param id
//     * @return
//     */
//    @WebResult(name = "deleteUserRole")
//    public BaseResponse deleteUserRole(@WebParam(name = "id") int id) {
//        log.info("-----> [AMS] Request deleting UserRole has been sent <-----");
//        log.info("Incoming parameters : \n id: " + id);
//        return frontManager.deleteUserRole(id);
//    }
//
//    /**
//     * Get the UserRolePrivileges by a given Role's id
//     *
//     * @param idRole
//     * @return
//     */
//    @WebResult(name = "getUserRolePrivileges")
//    public ResponseDataList getUserRolePrivileges(@WebParam(name = "idRole") int idRole) {
//        log.info("-----> [AMS] Request getting UserRolePrivileges has been sent <-----");
//        log.info("Incoming parameters : \n idRole: " + idRole);
//        return frontManager.getUserRolePrivileges(idRole);
//    }
//
//    /**
//     * Add a UserRolePrivilege
//     *
//     * @param idRole
//     * @param idPrivilege
//     * @return
//     */
//    @WebResult(name = "addUserRolePrivilege")
//    public BaseResponse addUserRolePrivilege(@WebParam(name = "idRole") int idRole, @WebParam(name = "idPrivilege") int idPrivilege) {
//        log.info("-----> [AMS] Request adding UserRolePrivilege has been sent <-----");
//        log.info("Incoming parameters : \n idRole: " + idRole + "\n idPrivilege: " + idPrivilege);
//        return frontManager.addUserRolePrivilege(idRole, idPrivilege);
//    }
//
//    /**
//     * Delete a UserRolePrivilege
//     *
//     * @param id
//     * @return
//     */
//    @WebResult(name = "deleteUserRolePrivilege")
//    public BaseResponse deleteUserRolePrivilege(@WebParam(name = "id") int id) {
//        log.info("-----> [AMS] Request deleting UserRolePrivilege has been sent <-----");
//        log.info("Incoming parameters : \n id: " + id);
//        return frontManager.deleteUserRolePrivilege(id);
//    }
//
//    /**
//     * Update a ApplicationParameterValue
//     *
//     * @param id
//     * @param value
//     * @return
//     */
//    @WebResult(name = "updateApplicationParameterValue")
//    public BaseResponse updateApplicationParameterValue(@WebParam(name = "id") int id,
//            @WebParam(name = "value") String value) {
//        log.info("-----> [AMS] Request updating ApplicationParameterValue has been sent <-----");
//        log.info("Incoming parameters : \n id: " + id + "\n value: " + value);
//        return frontManager.updateApplicationParameterValue(id, value);
//    }
//
//    /**
//     * Get all the UserRoles
//     *
//     * @return
//     */
//    @WebResult(name = "getUserRoles")
//    public ResponseDataList getUserRoles() {
//        log.info("-----> [AMS] Request getting UserRoles has been sent <-----");
//        log.info("Incoming parameters : [none]");
//        return frontManager.getUserRoles();
//    }
//
//    /**
//     * Get all the applications
//     *
//     * @return
//     */
//    @WebResult(name = "getApplications")
//    public ResponseDataList getApplications() {
//        log.info("-----> [AMS] Request getting Applications has been sent <-----");
//        log.info("Incoming parameters : [none]");
//        return frontManager.getApplications();
//    }
//
//    /**
//     * Search all the BoardingStatus by a given filter
//     *
//     * @param searchFilter
//     * @return
//     */
//    @WebResult(name = "searchBoardingStatus")
//    public ResponseDataList searchBoardingStatus(@WebParam(name = "searchFilter") String searchFilter) {
//        log.info("-----> [AMS] Request searching BoardingStatus has been sent <-----");
//        log.info("Incoming parameters : \n searchFilter: " + searchFilter);
//        return frontManager.searchBoardingStatus(searchFilter);
//    }
//
//    /**
//     * Get the Cookies
//     *
//     * @return
//     */
//    public int access() {
//        log.info("-----> [AMS] Getting the Cookies <-----");
//        int accessResult = 0;
//        try {
//            accessResult = accessResult(((Map) (wsctx.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS))).get("Cookie"));
//        } catch (Exception e) {
//            accessResult = Constants.CODE_INVALID_GROUP;
//        }
//        return accessResult;
//    }
//
//    /**
//     * Verify the access
//     *
//     * @param access
//     * @return
//     */
//    public int accessResult(Object access) {
//        log.info("-----> [AMS] Verifying the access <-----");
//        List cookies = (List) access;
//
//        if (cookies != null) {
//            List realCookies = new ArrayList();
//            for (int i = 0; i < cookies.size(); i++) {
//                realCookies.addAll(Arrays.asList(((String) cookies.get(i)).split(";")));
//            }
//            String[] cookie;
//            for (int i = 0; i < realCookies.size(); i++) {
//                cookie = ((String) realCookies.get(i)).split("=");
//                if (cookie != null && cookie.length == 2) {
//                    if (cookie[0].trim().equals("GROUPS") && cookie[1].trim().contains(AppConf.getInst().getProp(AppConf.AUTHORIZED_USER_GROUP))) {
//                        return Constants.CODE_SUCCESS;
//                        // mapCookieHeader.put(cookie[0].trim(), cookie[1].replace("\"", ""));
//                    }
//                }
//            }
//        }
//        return Constants.CODE_INVALID_GROUP;
//    }
}