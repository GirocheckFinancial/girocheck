/*
 ** File: MerchantParameterMobile.java
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
package com.smartbt.vtsuite.servercommon.display.common.model;

import java.io.Serializable;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Merchant Parameter Mobile class - containing all sets/gets
 */
@XmlRootElement
public class MerchantParameterDisplay implements Serializable{

    private boolean isRestaurant;
    private String headerLine1;
    private String headerLine2;
    private String headerLine3;
    private String headerLine4;
    private String headerLine5;
    private String promissoryVerbiage1;
    private String promissoryVerbiage2;
    private String promissoryVerbiage3;
    private Double gratuityRate1;
    private Double gratuityRate2;
    private Double gratuityRate3;

    /**
     * The default constructor
     */
    public MerchantParameterDisplay() {
    }
    
     /**
     *
     * @return
     */
    public boolean getIsRestaurant() {
        return isRestaurant;
    }

    /**
     *
     * @param isRestaurant
     */
    public void setIsRestaurant(boolean isRestaurant) {
        this.isRestaurant = isRestaurant;
    }


    /**
     *
     * @return
     */
    public String getPromissoryVerbiage1() {
        return promissoryVerbiage1;
    }

    /**
     *
     * @param promissoryVerbiage1
     */
    public void setPromissoryVerbiage1(String promissoryVerbiage1) {
        this.promissoryVerbiage1 = promissoryVerbiage1;
    }

    /**
     *
     * @return
     */
    public String getPromissoryVerbiage2() {
        return promissoryVerbiage2;
    }

    /**
     *
     * @param promissoryVerbiage2
     */
    public void setPromissoryVerbiage2(String promissoryVerbiage2) {
        this.promissoryVerbiage2 = promissoryVerbiage2;
    }

    /**
     *
     * @return
     */
    public String getPromissoryVerbiage3() {
        return promissoryVerbiage3;
    }

    /**
     *
     * @param promissoryVerbiage3
     */
    public void setPromissoryVerbiage3(String promissoryVerbiage3) {
        this.promissoryVerbiage3 = promissoryVerbiage3;
    }

    /**
     *
     * @return
     */
    public String getHeaderLine1() {
        return headerLine1;
    }

    /**
     *
     * @param headerLine1
     */
    public void setHeaderLine1(String headerLine1) {
        this.headerLine1 = headerLine1;
    }

    /**
     *
     * @return
     */
    public String getHeaderLine2() {
        return headerLine2;
    }

    /**
     *
     * @param headerLine2
     */
    public void setHeaderLine2(String headerLine2) {
        this.headerLine2 = headerLine2;
    }

    /**
     *
     * @return
     */
    public String getHeaderLine3() {
        return headerLine3;
    }

    /**
     *
     * @param headerLine3
     */
    public void setHeaderLine3(String headerLine3) {
        this.headerLine3 = headerLine3;
    }

    /**
     *
     * @return
     */
    public String getHeaderLine4() {
        return headerLine4;
    }

    /**
     *
     * @param headerLine4
     */
    public void setHeaderLine4(String headerLine4) {
        this.headerLine4 = headerLine4;
    }

    /**
     *
     * @return
     */
    public String getHeaderLine5() {
        return headerLine5;
    }

    /**
     *
     * @param headerLine5
     */
    public void setHeaderLine5(String headerLine5) {
        this.headerLine5 = headerLine5;
    }
    
    /**
     *
     * @return
     */
    public Double getGratuityRate1() {
        return gratuityRate1;
    }

    /**
     *
     * @param gratuityRate1
     */
    public void setGratuityRate1(Double gratuityRate1) {
        this.gratuityRate1 = gratuityRate1;
    }

    /**
     *
     * @return
     */
    public Double getGratuityRate2() {
        return gratuityRate2;
    }

    /**
     *
     * @param gratuityRate2
     */
    public void setGratuityRate2(Double gratuityRate2) {
        this.gratuityRate2 = gratuityRate2;
    }

    /**
     *
     * @return
     */
    public Double getGratuityRate3() {
        return gratuityRate3;
    }

    /**
     *
     * @param gratuityRate3
     */
    public void setGratuityRate3(Double gratuityRate3) {
        this.gratuityRate3 = gratuityRate3;
    }
    
    /**
     * Initialize class from map
     *
     * @param map The data
     */
    public void initFromMap(Map<String, Object> map) {
        if (map != null) {
            setHeaderLine1(map.get("headerLine1").toString());
            setHeaderLine2(map.get("headerLine2").toString());
            setHeaderLine3(map.get("headerLine3").toString());
            setHeaderLine4(map.get("headerLine4").toString());
            setHeaderLine5(map.get("headerLine5").toString());

            setPromissoryVerbiage1(map.get("promissoryVerbiage1").toString());
            setPromissoryVerbiage2(map.get("promissoryVerbiage2").toString());
            setPromissoryVerbiage3(map.get("promissoryVerbiage3").toString());
            
            setGratuityRate1(Double.parseDouble(map.get("gratuityRate1").toString()));
            setGratuityRate2(Double.parseDouble(map.get("gratuityRate2").toString()));
            setGratuityRate3(Double.parseDouble(map.get("gratuityRate3").toString()));
        }
    }
}
