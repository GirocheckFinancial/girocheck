/*
 ** File: MonitoringManager.java
 **
 ** Date Created: January 2014
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
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.SessionClerk;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.MonitoringDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.vtsuite.servercommon.model.Monitoring;
import com.smartbt.vtsuite.servercommon.validators.MonitoringValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class MonitoringManager {

    private static final Logger log = Logger.getLogger(MonitoringManager.class);
    private MonitoringDAO monitoringDAO = MonitoringDAO.get();

    /**
     * Get a monitoring by a given id
     *
     * @param idMonitoring
     * @return ResponseData
     * @throws java.lang.Exception
     *
     */
    public ResponseData getMonitoring(int idMonitoring) throws Exception {
        MonitoringValidator.getMonitoring(idMonitoring);

        ResponseData response = new ResponseData();
        response.setData(monitoringDAO.getMonitoring(idMonitoring));
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    /**
     * Search all the Monitoring by a given filter
     *
     * @param entityType
     * @param idEntity
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return ResponseDataList
     * @throws java.lang.Exception
     */
    public ResponseDataList searchMonitoring(EntityType entityType, int idEntity, String searchFilter, int pageNumber, int rowsPerPage) throws Exception {
        MonitoringValidator.searchMonitoring(entityType, idEntity, searchFilter, pageNumber, rowsPerPage);

        ResponseDataList response = new ResponseDataList();
        response.setData(monitoringDAO.searchMonitoring(entityType, idEntity, searchFilter, pageNumber * rowsPerPage, rowsPerPage));

        int total = monitoringDAO.searchMonitoring(entityType, idEntity, searchFilter, -1, -1).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    /**
     * Start monitoring terminal
     *
     * @param idTerminal
     * @return BaseResponse
     * @throws java.lang.Exception
     */
    public BaseResponse startMonitoringTerminal(int idTerminal) throws Exception {
        MonitoringValidator.startMonitoringTerminal(idTerminal);

        monitoringDAO.startMonitoringTerminal(idTerminal);

        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    /**
     * Stop monitoring terminal
     *
     * @param idTerminal
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse stopMonitoringTerminal(int idTerminal) throws Exception {
        MonitoringValidator.stopMonitoringTerminal(idTerminal);

        monitoringDAO.stopMonitoringTerminal(idTerminal);

        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    //Monitoring Utils --------------------------------------------------------------------
    /**
     * Gather information about the request, if the terminal is activated for
     * being monitored.
     *
     * @param requestContext
     * @throws java.io.IOException
     */
    public static void proccess(ContainerRequestContext requestContext) throws IOException {
        if (SessionClerk.get() != null && SessionClerk.get().getTerminal().isMonitored() == Boolean.TRUE) {
            log.info("Monitoring terminal: " + SessionClerk.get().getTerminal().getTerminalId());
            if (MonitoringDAO.get().shouldBeStopedMonitoring(SessionClerk.get().getTerminal().getId())) {
                return;
            }

            java.util.Scanner s = new java.util.Scanner(requestContext.getEntityStream()).useDelimiter("\\A");
            String data = s.hasNext() ? s.next() : "";

            requestContext.getEntityStream().close();
            s.close();

            requestContext.setEntityStream(new ByteArrayInputStream(data.getBytes()));

            if (data.isEmpty()) {
                MultivaluedMap mapPathParams = requestContext.getUriInfo().getPathParameters();
                for (Object key : mapPathParams.keySet()) {
                    data += "Parameter: " + key + "\n";
                    data += "Value: " + mapPathParams.get(key);
                }
            }
            if (data.isEmpty()) {
                MultivaluedMap mapQueryParams = requestContext.getUriInfo().getQueryParameters();
                for (Object key : mapQueryParams.keySet()) {
                    data += "Parameter: " + key + "\n";
                    data += "Value: " + mapQueryParams.get(key);
                }
            }

            if (requestContext.getHeaderString("Content-Type").toLowerCase().contains("json") && !data.isEmpty()) {
                StringBuilder dataEditor = new StringBuilder(data);
                int posSensibleAttr = dataEditor.indexOf("\"pan\":");
                if (posSensibleAttr != -1) {
                    posSensibleAttr += 7;
                    while (dataEditor.charAt(posSensibleAttr) != '\"') {
                        dataEditor.setCharAt(posSensibleAttr++, '*');
                    }
                }
                posSensibleAttr = dataEditor.indexOf("\"expDate\":");
                if (posSensibleAttr != -1) {
                    posSensibleAttr += 11;
                    while (dataEditor.charAt(posSensibleAttr) != '\"') {
                        dataEditor.setCharAt(posSensibleAttr++, '*');
                    }
                }
                data = dataEditor.toString();
            }

            Monitoring monitoring = new Monitoring();
            monitoring.setClerk(SessionClerk.get().getClerk());
            monitoring.setTerminal(SessionClerk.get().getTerminal());

            monitoring.setFunction(requestContext.getUriInfo().getPath());
            monitoring.setHost(requestContext.getHeaderString("Host"));
            monitoring.setMethod(requestContext.getMethod());
            monitoring.setUserAgent(requestContext.getHeaderString("User-Agent"));
            monitoring.setContentType(requestContext.getHeaderString("Content-Type"));
            monitoring.setContent(data);

            MonitoringDAO.get().addMonitoring(monitoring);

//            log.info("WS: " + requestContext.getUriInfo().getPath());
//            log.info("Host: " + requestContext.getHeaderString("Host"));
//
//            log.info("Method: " + requestContext.getMethod());
//            log.info("Content-Type: " + requestContext.getHeaderString("Content-Type"));
//            log.info("User-Agent: " + requestContext.getHeaderString("User-Agent"));
//            log.info("Content: " + data);
        }

    }   

}
