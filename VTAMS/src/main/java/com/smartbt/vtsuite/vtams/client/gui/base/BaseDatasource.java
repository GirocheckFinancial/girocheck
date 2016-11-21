/*
 ** File: BaseDatasource.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.vtams.client.gui.base;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import static com.smartbt.vtsuite.vtams.client.utils.Utils.debug;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.XMLTools;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import static com.smartgwt.client.types.DSOperationType.ADD;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.JSON;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Dialog;
import java.util.HashMap;
import java.util.Map;

/**
 * The Base DataSource
 *
 * @author Ariamnet Lopez
 */
public class BaseDatasource extends RestDataSource {

    private DataSourceIntegerField idField = new DataSourceIntegerField("id");
    private OperationBinding fetchOperation;
    private OperationBinding addOperation;
    private OperationBinding updateOperation;
    private OperationBinding removeOperation;

    public BaseDatasource(Record... records) {
        setDataURL("");
        setClientOnly(true);
        setCacheData(records);
    }

    /**
     * Constructor
     */
    public BaseDatasource() {
        super();

        fetchOperation = new OperationBinding();
        fetchOperation.setOperationType(DSOperationType.FETCH);
        fetchOperation.setDataProtocol(DSProtocol.POSTPARAMS);

        addOperation = new OperationBinding();
        addOperation.setOperationType(DSOperationType.ADD);
        addOperation.setDataProtocol(DSProtocol.POSTMESSAGE);

        updateOperation = new OperationBinding();
        updateOperation.setOperationType(DSOperationType.UPDATE);
        updateOperation.setDataProtocol(DSProtocol.POSTMESSAGE);

        removeOperation = new OperationBinding();
        removeOperation.setOperationType(DSOperationType.REMOVE);
        removeOperation.setDataProtocol(DSProtocol.POSTPARAMS);

        idField.setPrimaryKey(true);
        idField.setCanEdit(false);
        idField.setHidden(true);

        setDropExtraFields(true);
        setSendExtraFields(true);
        setDataFormat(DSDataFormat.JSON);
        setSendMetaData(false);
        setJsonRecordXPath("data");
        setOperationBindings(fetchOperation, removeOperation, addOperation, updateOperation);
    }

    @Override
    public void setFields(DataSourceField... fields) throws IllegalStateException {
        super.setFields(fields);
        addField(idField);
    }

    @Override
    public Object transformRequest(DSRequest dsRequest) {
        Map headers = new HashMap();
        headers.put("token", Settings.TOKEN);
        dsRequest.setHttpHeaders(headers);

        dsRequest.setShowPrompt(false);//This line is to make all request async

        switch (dsRequest.getOperationType()) {
            case ADD: {
                dsRequest.setHttpMethod("PUT");
                if (addOperation.getDataProtocol().equals(DSProtocol.POSTMESSAGE)) {
                    dsRequest.setContentType("application/json");
                    dsRequest.setData(JSON.encode(dsRequest.getData()));
                }
                break;
            }
            case UPDATE: {
                dsRequest.setHttpMethod("PUT");
                if (updateOperation.getDataProtocol().equals(DSProtocol.POSTMESSAGE)) {
                    dsRequest.setContentType("application/json");
                    dsRequest.setData(JSON.encode(dsRequest.getData()));
                }
                break;
            }
            case REMOVE: {
                dsRequest.setHttpMethod("DELETE");
                break;
            }
        }
        return dsRequest.getData();
    }

    @Override
    protected void transformResponse(DSResponse response, DSRequest request, Object data) {
        //super.transformResponse(response, request, data);
        // Following code is to control IE behavior
        Map httpHeaders = response.getHttpHeaders();

        if (httpHeaders != null) {
            String contentType = response.getHttpHeaders().get("Content-Type") == null ? "" : (String) response.getHttpHeaders().get("Content-Type");

            if (contentType.contains("text/html")) {
                 SC.warn(I18N.GET.WINDOW_INFORMATION_TITLE(), I18N.GET.MESSAGE_SESSION_TIMEOUT_ERROR(), new BooleanCallback() {
                    public void execute(Boolean value) {
                        if (value != null) {
                            Utils.redirectToLoginPage();
                        }
                    }
                }, new Dialog());
            }
        }

        switch (response.getStatus()) {
            case DSResponse.STATUS_SUCCESS:
                JSONArray valueStatus = XMLTools.selectObjects(data, "/status");
                String status = ((JSONNumber) valueStatus.get(0)).toString();
               
                JSONArray valueTotalPages = XMLTools.selectObjects(data, "/totalPages");
                if (valueTotalPages.size() > 0) {
                    response.setAttribute("totalPages", Integer.valueOf(((JSONNumber) valueTotalPages.get(0)).toString()));
                } else {
                    response.setAttribute("totalPages", 0);
                }

                if (status.equals(String.valueOf(Constants.CODE_SUCCESS))) {
                    response.setStatus(Constants.CODE_SUCCESS);
                    JSONArray errors = XMLTools.selectObjects(data, "/statusMessage");
                    response.setErrors(errors.getJavaScriptObject());
                } else if (status.equals(String.valueOf(Constants.CODE_NOT_PRIVILEGE))) {
                    response.setStatus(Constants.CODE_NOT_PRIVILEGE);
                    JSONArray errors = XMLTools.selectObjects(data, "/statusMessage");
                    response.setErrors(errors.getJavaScriptObject());

                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_LACK_OF_PRIVILEGE_ERROR());
                } else if (status.equals(String.valueOf(Constants.CODE_INVALID_ENTRY_DATA))) {
                    response.setStatus(Constants.CODE_INVALID_ENTRY_DATA);
                    JSONArray errors = XMLTools.selectObjects(data, "/statusMessage");
                    response.setErrors(errors.getJavaScriptObject());

                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), errors.get(0).isString().stringValue());
                } else if (status.equals(String.valueOf(Constants.CODE_ERROR_GENERAL))) {
                    response.setStatus(Constants.CODE_ERROR_GENERAL);
                    JSONArray errors = XMLTools.selectObjects(data, "/statusMessage");
                    response.setErrors(errors.getJavaScriptObject());

                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_GLOBAL_ERROR());
                } else if (status.equals(String.valueOf(Constants.CODE_INVALID_GROUP))) {
                    Utils.redirectToLoginPage();
                }else if (status.equals(String.valueOf(Constants.CODE_WRONG_USER))) {
                    response.setStatus(Constants.CODE_WRONG_USER);
                    JSONArray errors = XMLTools.selectObjects(data, "/statusMessage");
                    response.setErrors(errors.getJavaScriptObject());
                    SC.warn(I18N.GET.WINDOW_INFORMATION_TITLE(), I18N.GET.MESSAGE_WRONG_USER_ERROR(), new BooleanCallback() {
                        public void execute(Boolean value) {
                            if (value != null) {
                                Utils.redirectToLoginPage();
                            }
                        }
                    }, new Dialog());
                }else if (status.equals(String.valueOf(Constants.CODE_SESSION_EXPIRE))) {
                    response.setStatus(Constants.CODE_SESSION_EXPIRE);
                    JSONArray errors = XMLTools.selectObjects(data, "/statusMessage");
                    response.setErrors(errors.getJavaScriptObject());
                    SC.warn(I18N.GET.WINDOW_INFORMATION_TITLE(), I18N.GET.MESSAGE_SESSION_TIMEOUT_ERROR(), new BooleanCallback() {
                        public void execute(Boolean value) {
                            if (value != null) {
                                Utils.redirectToLoginPage();
                            }
                        }
                    }, new Dialog());
                } // STATUS: 408 [CODE_SESSION_LOST]
                else if (status.equals(String.valueOf(Constants.CODE_SESSION_LOST))) {
                    response.setStatus(Constants.CODE_SESSION_LOST);
                    JSONArray errors = XMLTools.selectObjects(data, "/statusMessage");
                    response.setErrors(errors.getJavaScriptObject());
                    SC.warn(I18N.GET.WINDOW_INFORMATION_TITLE(), I18N.GET.MESSAGE_SESSION_LOST_ERROR_MESSAGE(), new BooleanCallback() {
                        public void execute(Boolean value) {
                            if (value != null) {
                                Utils.doTokenLogout();
                            }
                        }
                    }, new Dialog());
                }
                else if(status.equals(String.valueOf(Constants.INVALID_PASSWORD))){
                   response.setStatus(Constants.INVALID_PASSWORD);
                   JSONArray errors = XMLTools.selectObjects(data, "/statusMessage");
                   String statusMessage = errors.get(0).isString().toString().replaceAll("\"", "");
                   debug("--BaseDataSource statusMessage = " + statusMessage);
                   
                   String msg = "The password needs to be at least 8 characters long and contain Lower Case, Upper Case letters, Digits, and Special Characters.";
                   
                   SC.warn("Invalid Password", msg);
                }

                break;
        }
    }

    /**
     * Get idField
     *
     * @return DataSourceIntegerField idField
     */
    public DataSourceIntegerField getIdField() {
        return idField;
    }

    /**
     * Set idField
     *
     * @param idField
     */
    public void setIdField(DataSourceIntegerField idField) {
        this.idField = idField;
    }

    /**
     * Get fetchOperation
     *
     * @return OperationBinding fetchOperation
     */
    public OperationBinding getFetchOperation() {
        return fetchOperation;
    }

    /**
     * Set fetchOperation
     *
     * @param fetchOperation
     */
    public void setFetchOperation(OperationBinding fetchOperation) {
        this.fetchOperation = fetchOperation;
    }

    /**
     * Get addOperation
     *
     * @return OperationBinding addOperation
     */
    public OperationBinding getAddOperation() {
        return addOperation;
    }

    /**
     * Set addOperation
     *
     * @param addOperation
     */
    public void setAddOperation(OperationBinding addOperation) {
        this.addOperation = addOperation;
    }

    /**
     * Get updateOperation
     *
     * @return OperationBinding updateOperation
     */
    public OperationBinding getUpdateOperation() {
        return updateOperation;
    }

    /**
     * Set updateOperation
     *
     * @param updateOperation
     */
    public void setUpdateOperation(OperationBinding updateOperation) {
        this.updateOperation = updateOperation;
    }

    /**
     * Get removeOperation
     *
     * @return OperationBinding removeOperation
     */
    public OperationBinding getRemoveOperation() {
        return removeOperation;
    }

    /**
     * Set removeOperation
     *
     * @param removeOperation
     */
    public void setRemoveOperation(OperationBinding removeOperation) {
        this.removeOperation = removeOperation;
    }
}
