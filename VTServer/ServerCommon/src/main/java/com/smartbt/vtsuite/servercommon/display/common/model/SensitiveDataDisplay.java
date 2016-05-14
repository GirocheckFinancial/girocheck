/*
 ** File: SensitiveDataDisplay.java
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
package com.smartbt.vtsuite.servercommon.display.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ariel Saavedra
 */
@XmlRootElement
public class SensitiveDataDisplay implements Serializable {

    private int id;
    private String encryptedData;

    /**
     *
     */
    public SensitiveDataDisplay() {
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getEncryptedData() {
        return encryptedData;
    }

    /**
     *
     * @param encryptedData
     */
    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }
}
