/*
 ** File: MerchantDisplay.java
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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;  

/**
 * The Merchant Display Class - containing all sets/gets
 */
@XmlRootElement
public class InventoryDisplay implements Serializable {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MerchantDisplay.class);

    private static long serialVersionUID = 1L; 
    private Integer id;
    private String merchant;
    private Integer inventory;
    private Integer threshold;
   
    public InventoryDisplay() {

    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the merchant
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    } 

    /**
     * @return the inventory
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the threshold
     */
    public Integer getThreshold() {
        return threshold;
    }

    /**
     * @param threshold the threshold to set
     */
    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
   }
