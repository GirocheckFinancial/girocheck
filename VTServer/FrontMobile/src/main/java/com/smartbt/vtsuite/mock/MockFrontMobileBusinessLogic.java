/*
 ** File: IStreamBusinessLogic.java
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
package com.smartbt.vtsuite.mock;

import com.smartbt.girocheck.servercommon.display.mobile.MobileTransaction;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.vtsuite.boundary.util.MapUtil;
import java.util.HashMap;
import java.util.Map;
import com.smartbt.vtsuite.boundary.client.LastTransactionsResponse;
import com.smartbt.vtsuite.boundary.client.SessionTag;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Mpowa Business Logic Class
 */
public class MockFrontMobileBusinessLogic {

    private static MockFrontMobileBusinessLogic INSTANCE;

    public static synchronized MockFrontMobileBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFrontMobileBusinessLogic();
        }
        return INSTANCE;
    }

    public DirexTransactionResponse process(){
         Map transactionData = new HashMap();
 
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        Map transactionHistory = new HashMap();
        transactionHistory.put("items", buildTransactionList());
        transactionHistory.put("total", 40); // 40 is the total, here we are sending just the first page
            
        transactionData.put(ParameterName.TRANSACTIONS_LIST, transactionHistory);
        direxTransactionResponse.setTransactionData(transactionData);
        return direxTransactionResponse;
    }
     
    public List buildTransactionList(){
        List list = new ArrayList();
        
        list.add(new MobileTransaction("Feb 01 2017", "-58.42", "Store Purchase VONAGE America"));
        list.add(new MobileTransaction("Feb 01 2017", "6.00", "Transferred from Savings"));
        list.add(new MobileTransaction("Feb 02 2017", "4.00", "Received Money From Marilyn Lebrija"));
        list.add(new MobileTransaction("Feb 03 2017", "50.00", "Money Added From Debit Card"));
        list.add(new MobileTransaction("Feb 03 2017", "58.00", "Store Purchase CVS Farmacy"));
        list.add(new MobileTransaction("Feb 04 2017", "10.45", "Store Purchase Gas Station"));
        list.add(new MobileTransaction("Feb 05 2017", "12.42", "Card Reload at Walmart Supermarket"));
        list.add(new MobileTransaction("Feb 05 2017", "34.12", "Money transfer at Western Union"));
        list.add(new MobileTransaction("Feb 05 2017", "-32.40", "Check cash at Hiealeash Market Center"));
        list.add(new MobileTransaction("Feb 06 2017", "11.52", "Store Purchase Publix Supermarket"));
        
        return list;
    }
}
