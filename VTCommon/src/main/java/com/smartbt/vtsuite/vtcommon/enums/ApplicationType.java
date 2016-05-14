/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */

package com.smartbt.vtsuite.vtcommon.enums;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public enum ApplicationType {

    GLOBAL(1),
    ISTREAM(2),
    ORDER_EXPRESS(3),
    TECNICARD(4);
    
    private int id;
    
     ApplicationType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
     
    public static String get(Integer index){
        if(index == null)return "GLOBAL";
        
        switch(index){
            case 1:return "GLOBAL";
            case 2:return "ISTREAM";
            case 3:return "ORDER_EXPRESS";
            case 4:return "TECNICARD";
            default:  return "GLOBAL";
        }
       
    } 
    
}
