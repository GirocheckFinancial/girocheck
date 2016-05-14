/*
 ** File: AbstractBusinessLogicModule.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.common;

import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;

/**
 * Abstract Business Logic Class
 */
public abstract class AbstractBusinessLogicModule {

   
    public DirexTransactionResponse handle(DirexTransactionRequest request) throws Exception {
        preprocess(request);
        DirexTransactionResponse response = process(request);
        postprocess(request, response);
        return response;
    }

 
    protected abstract void preprocess(DirexTransactionRequest request) throws Exception;

  
    protected abstract DirexTransactionResponse process(DirexTransactionRequest request) throws Exception;

    
    protected abstract void postprocess(DirexTransactionRequest request, DirexTransactionResponse response) throws Exception;
}
