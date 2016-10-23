/*
 ** File: BaseResponse.java
 **
 ** Date Created: February 2013
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

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Smart BaseResponse Class - containing all sets/gets
 */
@XmlRootElement
public class BaseResponse implements Serializable {

    /**
     *
     */
    protected int status;
    /**
     *
     */
    protected String statusMessage;

    /**
     * The default constructor
     */
    public BaseResponse() {
    }

    public BaseResponse(int status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }
    
    

    /**
     *
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     *
     * @param statusMessage
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
