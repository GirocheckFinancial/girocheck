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

package com.smartbt.vtsuite.util;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public enum TecnicardParameter {
    ROUTING_BANK_NUMBER("routingBankNumber");

    private String name;
    
    private TecnicardParameter(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    
    
    
}
