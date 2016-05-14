/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager.processor;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.model.FuzeResponse;
import com.smartbt.vtsuite.utils.FuzeUtil;
import com.smartbt.vtsuite.utils.MapUtil;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alejo
 */
public class FuzeLookupTransactionManager {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FuzeLookupTransactionManager.class);
    
    
    private static FuzeLookupTransactionManager manager;
  //  private Transaction transaction;
    
    public FuzeLookupTransactionManager(){
    }
    
    public static FuzeLookupTransactionManager get() throws Exception {
        if (manager == null) {
            manager = new FuzeLookupTransactionManager();
        }
        return manager;
    }

    public Map process(DirexTransactionRequest request) throws Exception {
        
//        System.out.println("FuzeLookupTransactionManager.process()");
//        log.info("[FuzeLookupTransactionManager] process()");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FuzeLookupTransactionManager] process()",null);
//        transaction = request.getTransaction();
        Map transactionData = request.getTransactionData();
        Map data = new HashMap();
        FuzeUtil fuzeUtil = new FuzeUtil();
        
//        data.put("reference_id", MapUtil.getStringValueFromMap( transactionData, ParameterName.TRANSACTION_ID, true ));  
        data.put("reference", MapUtil.getStringValueFromMap( transactionData, ParameterName.TRANSACTION_ID, true ));  
//        data.put("transaction_id", MapUtil.getStringValueFromMap( transactionData, ParameterName.FUZE_TRANSACTION_ID, true ));
        data.put("fuze_id", fuzeUtil.getFuzeID());
	data.put("version", "5");
        
//        System.out.println("Data from the Request > ------------------------");
//        System.out.println("ParameterName.TRANSACTION_ID"+ data.get("reference"));
//        System.out.println("ParameterName.FUZE_TRANSACTION_ID"+ data.get("transaction_id"));

        
        //test data-set
//      data.put("reference", "123456789");
//	data.put("transaction_id", "6045782987654321");

        

//        System.out.println("In - > fuzeUtil.lookupTransaction(data)");
//        log.debug("[FuzeLookupTransactionManager] In - > fuzeUtil.lookupTransaction(data)");
        FuzeResponse fuzeresponse;
        try{
            fuzeresponse = fuzeUtil.lookupTransaction(data);        
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
            
//        System.out.println("Out - > fuzeUtil.lookupTransaction(data)");
//        System.out.println("In - > fuzeUtil.mapResponse(fuzeresponse)");
//        log.debug("[FuzeLookupTransactionManager] Out - > fuzeUtil.lookupTransaction(data)");
//        log.debug("[FuzeLookupTransactionManager] In - > fuzeUtil.mapResponse(fuzeresponse)");
//        
//        log.debug("[FuzeLookupTransactionManager] Out - > fuzeUtil.mapResponse(fuzeresponse)");
        
        return fuzeresponse.toMapLookUpTransaction();//this gonna be the return


    }

    public Map mock() throws Exception {

        Map data = new HashMap();
        
            data.put(ParameterName.STATUS, "100");
            data.put(ParameterName.MESSAGE, "Success");
            data.put(ParameterName.FUZE_TRANSACTION_STATUS, "Success");
            data.put(ParameterName.AMMOUNT, "Amount");
            data.put(ParameterName.TIME, "Payment will post 08/30/2015");

        return data;

    }
    
    
}
