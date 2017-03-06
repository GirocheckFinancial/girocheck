/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;

/**
 *
 * @author rrodriguez
 */
public class MobileValidationException extends Exception{
    private ResponseData response;

    public MobileValidationException(int status, String statusMessage) {
        this.response = new ResponseData(status, statusMessage);
    }

    /**
     * @return the response
     */
    public ResponseData getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(ResponseData response) {
        this.response = response;
    }

    
    
}
