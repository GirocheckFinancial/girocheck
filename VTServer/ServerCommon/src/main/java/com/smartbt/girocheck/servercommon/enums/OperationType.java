

package com.smartbt.girocheck.servercommon.enums;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public enum OperationType {
    CHECK(1),
    CASH(2);
    
    private int code;

    private OperationType(int code) {
        this.code = code;
    }
    
    
}
