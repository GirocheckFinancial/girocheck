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
public class WebResponseData<T> extends WebResponse implements Serializable {

    private T data;
    /**
     * The default constructor
     */
    public WebResponseData() {
       
    }

    public WebResponseData(T data) {
        this.data = data;
    }

    public WebResponseData(int status, String statusMessage) {
        super(status, statusMessage);
    }

     public static WebResponseData toLoginFail(){
      return new WebResponseData(403, "Invalid Login credentials");
    }
    /**
     *
     * @return
     */
    public T getData() {
        return data;
    }

    public void setData( T data ) {
        this.data = data;
    }

}
