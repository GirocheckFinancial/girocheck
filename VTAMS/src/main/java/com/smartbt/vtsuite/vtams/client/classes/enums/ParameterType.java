

package com.smartbt.vtsuite.vtams.client.classes.enums;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public enum ParameterType {
    GLOBAL(1),
    ISTREAM(2),
    TECNICARD(3),
    ORDER_EXPRESS(4);
    
    private int code;

    private ParameterType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    
}
