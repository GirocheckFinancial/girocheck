
package com.smartbt.girocheck.servercommon.messageFormat;

/**
 *
 * @author Roberto
 */


public enum IdType {
    SSN(1),
    DRIVER_LICENSE(2),
    MATRICULA_CONSULAR(3),
    PASSPORT(4),
    VISA(5),
    GREEN_CARD(6),
    STATE_ID(7),
    MILITARY_ID(8),
    OTHERS(100);
    
    private final int id;
    
    public static IdType getIdType(int id){
        switch(id){
            case 1: return IdType.SSN;
            case 2: return IdType.DRIVER_LICENSE;
            case 3: return IdType.MATRICULA_CONSULAR;
            case 4: return IdType.PASSPORT;
            case 5: return IdType.VISA;
            case 6: return IdType.GREEN_CARD;
            case 7: return IdType.STATE_ID;
            case 8: return IdType.MILITARY_ID;
            default:return IdType.OTHERS;
        }
    }

    private IdType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    
}
