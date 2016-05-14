/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.ClerkDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.dao.WatchdogDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.EntityObject;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.WatchdogAlert;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntity;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk;
import com.smartbt.vtsuite.servercommon.validators.WatchdogValidator;
import com.smartbt.vtsuite.servercommon.watchdog.WatchdogWrapper;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomWatchdog;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Maite
 */
public class WatchdogManager {

    private static final Logger log = Logger.getLogger(ClientManager.class);
    private WatchdogDAO watchdogDAO = WatchdogDAO.get();

    /**
     * Save or Update a WatchdogEntity
     *
     * @param watchdogEntity
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse saveOrUpdateWatchdogEntity(WatchdogEntity watchdogEntity) throws Exception {
        WatchdogValidator.saveOrUpdateWatchdogEntity(watchdogEntity);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        fillWatchdogEntityClerkFromSendAlerTo(watchdogEntity);
        if (watchdogEntity.getWatchdog().getId() == NomWatchdog.INFO.getId()) {
            WatchdogWrapper.wrapINFOMessage(watchdogEntity);
        } else {
            watchdogDAO.saveOrUpdateWatchdogEntity(watchdogEntity);
        }
        return response;
    }

    private void fillWatchdogEntityClerkFromSendAlerTo(WatchdogEntity watchdogEntity) {
        if (watchdogEntity.getSendAlertTo() != null) {
            Collection<WatchdogEntityClerk> watchdogEntityClerks = new LinkedList<WatchdogEntityClerk>();
            List<Integer> idProccesedClerks = new LinkedList<Integer>();
            for (EntityObject entityObject : watchdogEntity.getSendAlertTo()) {
                List<Clerk> sendAlertTo = new LinkedList<Clerk>();
                switch (entityObject.getEntityType()) {
                    case ENTERPRISE:
                    case CUSTOMER:
                    case MERCHANT:
                    case TERMINAL:
                        sendAlertTo = ClerkDAO.get().searchClerksDB(entityObject.getId(), null, entityObject.getEntityType(), -1, -1);
                        break;
                    case CLERK:
                        sendAlertTo.add(ClerkDAO.get().findById(entityObject.getId()));
                        break;
                }
                
                for (Clerk clerk : sendAlertTo) {
                    if (!idProccesedClerks.contains(clerk.getId())) {
                        WatchdogEntityClerk watchdogEntityClerkSave = new WatchdogEntityClerk();
                        watchdogEntityClerkSave.setClerk(clerk);
                        watchdogEntityClerkSave.setWatchdogEntity(watchdogEntity);
                        
                        watchdogEntityClerks.add(watchdogEntityClerkSave);                       
                        idProccesedClerks.add(clerk.getId());
                    }
                }
            }
            watchdogEntity.setWatchdogEntityClerk(watchdogEntityClerks);
            watchdogEntity.setSendAlertTo(null);
        }
    }

    /**
     * Add a WatchdogAlert
     *
     * @param watchdogAlert
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse addWatchdogAlert(WatchdogAlert watchdogAlert) throws Exception {
        WatchdogValidator.addWatchdogAlert(watchdogAlert);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        watchdogDAO.addWatchdogAlert(watchdogAlert);
        return response;
    }

    /**
     * Delete watchdog rule 
     * @param watchdogEntityId
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse deleteWatchdogEntity(int watchdogEntityId) throws Exception {
        WatchdogValidator.deleteWatchdogEntity(watchdogEntityId);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        watchdogDAO.deleteWatchdogEntity(watchdogEntityId);
        return response;
    }

    /**
     * Delete watchdog alert
     * 
     * @param watchdogAlertId
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse deleteWatchdogAlert(int watchdogAlertId) throws Exception {
        WatchdogValidator.deleteWatchdogAlert(watchdogAlertId);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        watchdogDAO.deleteWatchdogAlert(watchdogAlertId);
        return response;
    }

    /**
     * Delete watchdog alert for a specific clerk
     * 
     * @param clerk
     * @param watchdogAlertId
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse deleteWatchdogAlertByClerkDestination(Clerk clerk, int watchdogAlertId) throws Exception {
        WatchdogValidator.deleteWatchdogAlertByClerkDestination(clerk, watchdogAlertId);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        watchdogDAO.deleteWatchdogAlertByClerkDestination(clerk, watchdogAlertId);
        return response;
    }

    /**
     * Delete all watchdogAlerts for a specific clerk
     * @param clerk
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse deleteAllWatchdogAlertsByClerkDestination(Clerk clerk) throws Exception {
        WatchdogValidator.deleteAllWatchdogAlertsByClerkDestination(clerk);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        watchdogDAO.deleteAllWatchdogAlertsByClerkDestination(clerk);
        return response;
    }

    /**
     * Delete all watchdogAlerts for a specific entity (Customer, Merchant or Terminal)
     * 
     * @param entityType
     * @param idEntity
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse deleteAllWatchdogAlertsByEntity(EntityType entityType, int idEntity) throws Exception {
        WatchdogValidator.deleteAllWatchdogAlertsByEntity(entityType, idEntity);
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        watchdogDAO.deleteAllWatchdogAlertsByEntity(entityType, idEntity);
        return response;
    }

    /**
     * Get a rule
     * 
     * @param watchdogEntityId
     * @return
     * @throws java.lang.Exception
     */
    public ResponseData getWatchdogEntity(int watchdogEntityId) throws Exception {
        WatchdogValidator.getWatchdogEntity(watchdogEntityId);
        ResponseData response = new ResponseData();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(watchdogDAO.getWatchdogEntity(watchdogEntityId));
        return response;
    }

    /**
     * Get all watchdogs
     * 
     * @return
     * @throws java.lang.Exception
     */
    public ResponseDataList getWatchdogs() throws Exception {
        WatchdogValidator.getWatchdogs();
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(watchdogDAO.getWatchdogs());
        return response;
    }

    /**
     * Get rule by entity type
     * 
     * @param entityType
     * @param idEntity
     * @return
     */
    public ResponseDataList getWatchdogEntitiesByEntity(EntityType entityType, int idEntity) throws Exception {
        WatchdogValidator.getWatchdogEntitiesByEntity(entityType, idEntity);
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(watchdogDAO.getWatchdogEntitiesByEntity(entityType, idEntity));
        return response;
    }

    /**
     * Get watchdog alert by entity
     * 
     * @param entityType
     * @param idEntity
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    public ResponseDataList getWatchdogAlertsByEntity(EntityType entityType, int idEntity,
            String searchFilter, Date startRangeDate, Date endRangeDate, int pageNumber, int rowsPerPage) throws Exception {
        WatchdogValidator.getWatchdogAlertsByEntity(entityType, idEntity, searchFilter, startRangeDate, endRangeDate, pageNumber, rowsPerPage);
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(watchdogDAO.getWatchdogAlertsByEntity(entityType, idEntity, searchFilter, startRangeDate, endRangeDate, pageNumber * rowsPerPage, rowsPerPage));

        int total = watchdogDAO.getWatchdogAlertsByEntity(entityType, idEntity, searchFilter, startRangeDate, endRangeDate, pageNumber * rowsPerPage, rowsPerPage).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));
        return response;
    }

    /**
     * Get watchdog alert by clerk destination
     * 
     * @param clerk
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    public ResponseDataList getWatchdogAlertsByClerkDestination(Clerk clerk, String searchFilter, Date startRangeDate, Date endRangeDate, int pageNumber, int rowsPerPage) throws Exception {
        WatchdogValidator.getWatchdogAlertsByClerkDestination(clerk, searchFilter, startRangeDate, endRangeDate, pageNumber, rowsPerPage);
        
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        response.setData(watchdogDAO.getWatchdogAlertsByClerkDestination(clerk, searchFilter, startRangeDate, endRangeDate, pageNumber * rowsPerPage, rowsPerPage));

        int total = watchdogDAO.getWatchdogAlertsByClerkDestination(clerk, searchFilter, startRangeDate, endRangeDate, -1, -1).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));
        return response;
    }
}
