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

package com.smartbt.girocheck.servercommon.messageFormat;

import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.model.Transaction;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class DirexTransaction  implements Serializable {

     private static final long serialVersionUID = 1L;
      
     private Map transactionData; 

     private TransactionType transactionType;
     
     private Transaction transaction;
     
    public DirexTransaction() {
         transactionData = new HashMap();
         transaction = new Transaction();
    }    
     

    public void setTransactionData( Map transactionData ) {
        this.transactionData = transactionData;
    }

    public Map getTransactionData() {
        return transactionData;
    } 
     
    public void setTransactionType( TransactionType transactionType ) {
        this.transactionType = transactionType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransaction( Transaction transaction ) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

     
}
