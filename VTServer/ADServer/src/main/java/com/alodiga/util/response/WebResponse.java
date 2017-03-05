/*
 ** File: WebResponse.java
 **
 ** Date Created: February 2014
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
package com.alodiga.util.response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rober
 */
@XmlRootElement
public class WebResponse implements Serializable {

    /**
     *
     */
    private int status;
    /**
     *
     */
    private String statusMessage;

    /**
     * The default constructor
     */
    public WebResponse() {
        this.status = 100;
        this.statusMessage = "SUCCESS";
    }

    public WebResponse(int status, String statusMessage) {
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
