/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;

/**
 *
 * @author rrodriguez
 */
public enum OperationType {

    CHECK2CARD("check2Card"),
    CASH2CARD("cash2Card"),
    CARD2MERCHANT("card2Merchant");

    private String operation;

    private OperationType(String operation) {
        this.operation = operation;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

}
