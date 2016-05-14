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

package com.smartbt.girocheck.servercommon.enums;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public enum CardStatus {

    ACTIVE(2),
    WAITING_OFFICIAL_NUMBER(1),
    UNACTIVE(0);
    
    private CardStatus( int id ) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }
    
    
}
