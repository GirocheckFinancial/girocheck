/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.manager.processor;

import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import java.util.Map;

/**
 *
 * @author Alejo
 */
public abstract class FuzeBaseManager {
    
    public FuzeBaseManager(){}
    
    public abstract Map process(DirexTransactionRequest request) throws Exception;
    
}
