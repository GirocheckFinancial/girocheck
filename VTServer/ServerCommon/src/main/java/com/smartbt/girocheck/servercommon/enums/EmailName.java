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
public enum EmailName {

    TWO_SUCCESSFUL_LOADS_TO_TECNICARD("2_successful_loads_to_tecnicard"),
    ALERT_INVENTORY_REACH_THRESHOLD("alert_inventory_reach_threshold"),
    ALERT_INVENTORY_REACH_ZERO("alert_inventory_reach_zero"),
    ALERT_MOBILE_FORGOT_PASSWORD_KEY("alert_mobile_forgot_password_key"),
    ALERT_MOBILE_FORGOT_PASSWORD_KEY_ES("alert_mobile_forgot_password_key_ES");
    
    private EmailName(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
 
    
    
}
