/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.model.Transaction;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public abstract class CoreAbstractTransactionBusinessLogic {

    public CoreAbstractTransactionBusinessLogic() {
    }
    
    public abstract   void process( DirexTransactionRequest direxTransactionRequest, Transaction transaction ) throws Exception;
}
