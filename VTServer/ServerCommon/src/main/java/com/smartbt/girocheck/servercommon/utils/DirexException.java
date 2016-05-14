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

package com.smartbt.girocheck.servercommon.utils;

import com.smartbt.girocheck.servercommon.enums.ResultCode;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class DirexException extends Exception{
    private ResultCode resultCode;

    public DirexException( ResultCode resultCode, String message ) {
        super( message );
        this.resultCode = resultCode;
    }

    
    
    public void setResultCode( ResultCode resultCode ) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
    
    
}
