/*
 ** File: TransactionDisplay.java
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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Transaction Display Class - containing all sets/gets
 */
@XmlRootElement
public class SubTransactionDisplay implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID( long aSerialVersionUID ) {
        serialVersionUID = aSerialVersionUID;
    }

    private Integer id;
    /**
     * REQUEST parameters
     */
    private Integer subTransactionType;;
    private String hostCode;
    private Integer resultCode;
    private String resultMessage;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Integer id ) {
        this.id = id;
    }

    /**
     * @return the subTransactionType
     */
    public Integer getSubTransactionType() {
        return subTransactionType;
    }

    /**
     * @param subTransactionType the subTransactionType to set
     */
    public void setSubTransactionType( Integer subTransactionType ) {
        this.subTransactionType = subTransactionType;
    }

    /**
     * @return the hostCode
     */
    public String getHostCode() {
        return hostCode;
    }

    /**
     * @param hostCode the hostCode to set
     */
    public void setHostCode( String hostCode ) {
        this.hostCode = hostCode;
    }

    /**
     * @return the resultCode
     */
    public Integer getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode( Integer resultCode ) {
        this.resultCode = resultCode;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage( String resultMessage ) {
        this.resultMessage = resultMessage;
    }


}
