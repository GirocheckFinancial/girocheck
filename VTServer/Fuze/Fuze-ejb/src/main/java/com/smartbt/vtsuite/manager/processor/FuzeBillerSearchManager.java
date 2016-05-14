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
import com.smartbt.vtsuite.utils.FixUtil;
import com.smartbt.vtsuite.utils.FuzeUtil;
import com.smartbt.vtsuite.utils.MapUtil;
import java.util.*;
import java.util.HashMap;
/**
 *
 * @author Alejo
 */
public class FuzeBillerSearchManager extends FuzeBaseManager{
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FuzeBillerSearchManager.class);
    
    private static FuzeBillerSearchManager manager;
  //  private Transaction transaction;
    
    public FuzeBillerSearchManager() throws Exception {
        super();
    }
    
    public static FuzeBillerSearchManager get() throws Exception {
        if (manager == null) {
            manager = new FuzeBillerSearchManager();
        }
        return manager;
    }
    
    @Override
//    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
    public Map process(DirexTransactionRequest request) throws Exception {
        
//        System.out.println("FuzeBillerSearchManager.process()");
//        log.info("[FuzeBillerSearchManager] process()");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FuzeBillerSearchManager] process()",null);
//        transaction = request.getTransaction();
        Map transactionData = request.getTransactionData();
        Map data = new HashMap();
        FuzeUtil fuzeUtil = new FuzeUtil();
        
        data.put("S2P", "1");
	data.put("account", MapUtil.getStringValueFromMap( transactionData, ParameterName.CARD_NUMBER, true ));
	data.put("amount", FixUtil.fixAmount(MapUtil.getStringValueFromMap( transactionData, ParameterName.PAYOUT_AMMOUNT, true )));
	data.put("clerk", MapUtil.getStringValueFromMap( transactionData, ParameterName.USER, true ));
	data.put("error_multiples", "1");
	data.put("fuze_id", fuzeUtil.getFuzeID());
	data.put("store", MapUtil.getStringValueFromMap( transactionData, ParameterName.IDPOS, true ));//ask for this store
	data.put("terminal", MapUtil.getStringValueFromMap( transactionData, ParameterName.TERMINAL_ID, true ));
	data.put("version", "5");
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug,"[FuzeBillerSearchManager] ParameterName.S2P "+ data.get("S2P") + " ParameterName.CARD_NUMBER "+ data.get("account")+
                " ParameterName.AMOUNT "+ data.get("amount")+
                " ParameterName.USER "+ data.get("clerk")+
                " error_multiples "+ data.get("error_multiples")+
                " MERCHANT_NAME "+ data.get("store")+
                " ParameterName.TERMINAL_ID "+ data.get("terminal"),null);
        
        //test data-set
//      data.put("S2P", "1");
//	data.put("account", "6045782987654321");
//	data.put("amount", "10");
//	data.put("clerk", "Amazon");
//	data.put("error_multiples", "1");
//	data.put("fuze_id", fuzeUtil.getFuzeID());
//	data.put("store", "Amazon");
//	data.put("terminal", "po123456");
//	data.put("version", "5");
        

//        System.out.println("In - > fuzeUtil.billerSearch(data)");
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug,"[FuzeBillerSearchManager]  Connecting ... FuzeUtil.billerSearch(...)",null);
        FuzeResponse fuzeresponse;
        try{
            fuzeresponse = fuzeUtil.billerSearch(data);        
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
            
//        System.out.println("Out - > fuzeUtil.billerSearch(data)");
//        log.debug("[FuzeBillerSearchManager] Out - > fuzeUtil.billerSearch(data)");
//        log.debug("[FuzeBillerSearchManager] In - > fuzeUtil.mapResponse(fuzeresponse)");
 //       DirexTransactionResponse response = mapResponse(fuzeresponse, request);
        
//        log.debug("[FuzeBillerSearchManager] Out - > fuzeUtil.mapResponse(fuzeresponse)");
        
        return fuzeresponse.toMap();//this gonna be the return
 //       return mock();

    }

    public Map mock() throws Exception {

        Map data = new HashMap();
        
            data.put(ParameterName.STATUS, "100");
            data.put(ParameterName.MESSAGE, "Success");
            data.put(ParameterName.BILLER_ID, "525587");
            data.put(ParameterName.TIME, "Payment will post 08/30/2015");

//        DirexTransactionResponse response = new DirexTransactionResponse();
//        response.setTransactionData(data);
//        response.setResultCode(ResultCode.SUCCESS);
//        response.setResultMessage("Payment will post 08/30/2014");
        return data;

    }

//    @Override
//    public DirexTransactionResponse mapResponse(FuzeResponse fuzeResp, DirexTransactionRequest request) {
//
//        System.out.println("Creating the response to core >> FuzeBillerSearchManager.mapResponse()");
//        
//        Map transactionData = request.getTransactionData();
//        DirexTransactionResponse response = null;
//
//        if (fuzeResp.getStatus().equals("601")) {//validate error codes
//            System.out.println("Invalid	velocity specified");
//            return manageExceptionAnswer(TransactionType.FUZE_BILLER_SEARCH, fuzeResp.getStatus(), fuzeResp.getMessage(),"");
//        }
//        if (!fuzeResp.getStatus().equals("100")) {
//            System.out.println("FUZE Status != 100");
//            return manageExceptionAnswer(TransactionType.FUZE_BILLER_SEARCH, fuzeResp.getStatus(), fuzeResp.getMessage(),"");
//        } else {
//
//            System.out.println("ToResponse All good");
//
//            Map fuzeData = fuzeResp.toMap();//when I select .toMap the response get the first biller no matter if there are more than 1. 
//
//            if (fuzeData != null) {
//                fuzeData.put(ParameterName.S2P, transactionData.get(ParameterName.S2P));
//                fuzeData.put(ParameterName.ACCOUNT_NUMBER, transactionData.get(ParameterName.ACCOUNT_NUMBER));
//                fuzeData.put(ParameterName.AMMOUNT, transactionData.get(ParameterName.AMMOUNT));
//    //            fuzeData.put(ParameterName.CLERK, transactionData.get(ParameterName.CLERK));
//                
//                addSubTransaction(TransactionType.FUZE_BILLER_SEARCH, ResultCode.SUCCESS, ResultMessage.SUCCESS.getMessage(), "");
//                response = new DirexTransactionResponse();
//                response.getTransaction().addSubTransactionList(transaction.getSub_Transaction());
//                response.setTransactionData(fuzeData);
//                response.setResultCode(ResultCode.SUCCESS);
//                response.setResultMessage((String) fuzeData.get(ParameterName.TIME));
//                return response;
//            } else {
//                return manageExceptionAnswer(TransactionType.FUZE_BILLER_SEARCH, "", "", "Billers list is empty");
//            }        
//        }
//    }

//    private DirexTransactionResponse manageExceptionAnswer( TransactionType transactionType, String hostExceptionCode, String  hostExceptionMessage, String errorCode) {
//
//        String message = "Error ResultMessage "+ResultMessage.FUZE_HOST_FAILED.getMessage() + ". Fuze error description: " + "Status: "+hostExceptionCode+" Message: "+ hostExceptionMessage;
//
//        addSubTransaction( transactionType, ResultCode.FUZE_HOST_ERROR, message, errorCode );
//        DirexTransactionResponse exRsp = DirexTransactionResponse.forException( ResultCode.FUZE_HOST_ERROR, message );
//
//        exRsp.getTransaction().addSubTransactionList( transaction.getSub_Transaction() );
//        return exRsp;
//    }
//    
//    private void addSubTransaction( TransactionType transactionType, ResultCode resultCode, String resultMessage, String errorCode ) {
//        SubTransaction subTransaction = new SubTransaction();
//            subTransaction.setType( transactionType.getCode() );
//            subTransaction.setResultCode( resultCode.getCode());
//            subTransaction.setResultMessage(resultMessage);
//            subTransaction.setErrorCode(errorCode);
//            subTransaction.setHost( NomHost.FUZE.getId());
//        transaction.addSubTransaction( subTransaction );//
//    }
    
    
}
