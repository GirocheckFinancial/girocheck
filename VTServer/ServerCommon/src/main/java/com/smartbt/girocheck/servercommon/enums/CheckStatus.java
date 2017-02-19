 
package com.smartbt.girocheck.servercommon.enums;

/**
 *
 * @author rrodriguez
 */


public enum CheckStatus {

    PROCESSING("P"),
    DENIED("D"),
    HOLD("H"),
    COMPLETED("C");

    private String status;

    CheckStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    
}
