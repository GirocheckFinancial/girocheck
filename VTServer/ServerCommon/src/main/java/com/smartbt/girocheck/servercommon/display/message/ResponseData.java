/*
 ** File: ResponseData.java
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
package com.smartbt.girocheck.servercommon.display.message;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.vtsuite.vtcommon.Constants;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ariel Saavedra
 * @param <T>
 */
@XmlRootElement
public class ResponseData<T> extends BaseResponse {

    private T data;

    public ResponseData() {
    }

    public static ResponseData OK() {
        ResponseData response = new ResponseData();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    public static ResponseData ERROR() {
        ResponseData response = new ResponseData();
        response.setStatus(Constants.CODE_ERROR_GENERAL);
        response.setStatusMessage(VTSuiteMessages.ERROR_GENERAL);
        return response;
    }

    public ResponseData(int status, String statusMessage) {
        super(status, statusMessage); 
    }
    
    

    public ResponseData(T data) {
        super(Constants.CODE_SUCCESS, VTSuiteMessages.SUCCESS);
        this.data = data;
    }

    /**
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }
}
