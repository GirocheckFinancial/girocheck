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
public enum EnumApplicationParameter {

    ISTREAM_USER("IStreamUser"),
    ISTREAM_PASSWORD("IStreamPassword"),
    ORDER_EXPRESS_USER("OrderExpressUser"),
    ORDER_EXPRESS_PASSWORD("OrderExpressPassword"),
    AUTH_FEEM("AuthFeem"),
    AUTH_FEEP("AuthFeep"),
    CRDLDF("CRDLDF"),
    AMOUNT_MIN_CHECK("amountMinCheck"),
    AMOUNT_MAX_CHECK("amountMaxCheck"),
    AMOUNT_MIN_CASH("amountMinCash"),
    AMOUNT_MAX_CASH("amountMaxCash"),
    ACTIVATION_FEE("activation_fee");
    
    private String parameter;

    private EnumApplicationParameter( String parameter ) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return parameter;
    }

   
    
    
}
