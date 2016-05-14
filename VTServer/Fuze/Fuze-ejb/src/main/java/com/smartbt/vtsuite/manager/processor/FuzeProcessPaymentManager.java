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
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Alejo
 */
public class FuzeProcessPaymentManager extends FuzeBaseManager{
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FuzeProcessPaymentManager.class);
    
    private static FuzeProcessPaymentManager manager;
//    private Transaction transaction;
    
    public FuzeProcessPaymentManager() throws Exception {
        super();
    }
    
    public static FuzeProcessPaymentManager get() throws Exception {
        if (manager == null) {
            manager = new FuzeProcessPaymentManager();
        }
        return manager;
    }
     @Override
//    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
    public Map process(DirexTransactionRequest request) throws Exception {
        
//        System.out.println("FuzeProcessPaymentManager.process()");
//        log.info("[FuzeProcessPaymentManager] process()");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FuzeProcessPaymentManager] process()",null);
//        transaction = request.getTransaction();
        Map transactionData = request.getTransactionData();
        Map data = new HashMap();
        FuzeUtil fuzeUtil = new FuzeUtil();
        
        data.put("S2P", "1");
	data.put("account", MapUtil.getStringValueFromMap( transactionData, ParameterName.CARD_NUMBER, true ));//customer
	data.put("amount", FixUtil.fixAmount(MapUtil.getStringValueFromMap( transactionData, ParameterName.PAYOUT_AMMOUNT, true )));
        data.put("biller_id", MapUtil.getStringValueFromMap( transactionData, ParameterName.BILLER_ID, true ));
	data.put("clerk", MapUtil.getStringValueFromMap( transactionData, ParameterName.USER, true ));
	data.put("first_name", MapUtil.getStringValueFromMap( transactionData, ParameterName.FIRST_NAME, true ));//customer
	data.put("fuze_id", fuzeUtil.getFuzeID());
	data.put("last_name", MapUtil.getStringValueFromMap( transactionData, ParameterName.LAST_NAME, true ));//customer
	data.put("phone", MapUtil.getStringValueFromMap( transactionData, ParameterName.PHONE, true ));//customer
//	data.put("reference_id", MapUtil.getStringValueFromMap( transactionData, ParameterName.TRANSACTION_ID, true ));
	data.put("reference", MapUtil.getStringValueFromMap( transactionData, ParameterName.TRANSACTION_ID, true ));
	data.put("send_text", "1");
//	data.put("store", MapUtil.getStringValueFromMap( transactionData, ParameterName.ParameterName.IDPOS, true ));
	data.put("store", MapUtil.getStringValueFromMap( transactionData, ParameterName.IDPOS, true ));
	data.put("tender_type", "1");//if is cash(1) or check(2)
	data.put("terminal", MapUtil.getStringValueFromMap( transactionData, ParameterName.TERMINAL_ID, true ));
	data.put("track1", MapUtil.getStringValueFromMap( transactionData, ParameterName.TRACK1, true ));//Conditional-URL encoded track 1 data from and including the beginning sentinel to and including the ending sentinel. Required for some transaction types.
	data.put("track2", MapUtil.getStringValueFromMap( transactionData, ParameterName.TRACK2, true ));//Conditional-URL encoded track 2 data from and including the beginning sentinel to and including the ending sentinel. Required for some transaction types.
	data.put("version", "5");// value = 5
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeProcessPaymentManager] ParameterName.S2P "+ data.get("S2P")+
                " ParameterName.CARD_NUMBER "+ data.get("account")+
                " ParameterName.AMMOUNT "+ data.get("amount")+
                " biller_id "+ data.get("biller_id")+
                " USER "+ data.get("clerk")+
                " first_name "+ data.get("first_name")+
                " last_name "+ data.get("last_name")+
                " phone "+ data.get("phone")+
                " TRANSACTION_ID "+ data.get("reference")+
                " send_text "+ data.get("send_text")+
                " MERCHANT_NAME "+ data.get("store")+
                " tender_type "+ data.get("tender_type")+
                " TRACK1 "+ data.get("track1")+
                " TRACK2 "+ data.get("track2")+
                " terminal "+ data.get("terminal"),null);
        
        
        //test data-set
//        data.put("S2P", "1");
//	data.put("account", "6045782987654321");
//	data.put("amount", "10");
//        data.put("biller_id", "525587");
//	data.put("clerk", "Amazon");
//	data.put("exp", "1705");
//	data.put("first_name", "John");
//	data.put("fuze_id", fuzeUtil.getFuzeID());
//	data.put("last_name",  "Smith");
//	data.put("phone", "7897897896");
//	data.put("reference_id", "ssdf678568ssdf");
//	data.put("send_text", "1");
//	data.put("store", "Amazon");
//	data.put("tender_type", "1");
//	data.put("terminal", "po123456");
//	data.put("version", "5");
        

//        log.debug("[FuzeProcessPaymentManager] In - > fuzeUtil.processPayment(data)");
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeProcessPaymentManager] Connecting ... FuzeUtil.processPayment(...)",null);
        FuzeResponse fuzeresponse;
        try{
            fuzeresponse = fuzeUtil.processPayment(data);        
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
//        log.debug("[FuzeProcessPaymentManager] Out - > fuzeUtil.processPayment(data)");
//        log.debug("[FuzeProcessPaymentManager] In - > fuzeUtil.mapResponse(fuzeresponse)");
//        DirexTransactionResponse response = mapResponse(fuzeresponse, request);
        
//        log.debug("[FuzeProcessPaymentManager] Out - > fuzeUtil.mapResponse(fuzeresponse)");
        
        return fuzeresponse.toMapProcessPayment();
 //       return mock();
    }
    
//    public DirexTransactionResponse mock() throws Exception {
    public Map mock() throws Exception {

        Map data = new HashMap();
        
        data.put(ParameterName.STATUS, "100");
	data.put(ParameterName.MESSAGE, "Success");
	data.put(ParameterName.REFERENCE, "reference");
	data.put(ParameterName.TIME, "estimated_posting_time");
	data.put(ParameterName.RECEIPT_TEXT, "receipt_text");
	data.put(ParameterName.PENDING_BALANCE, "pending_balance");//New card balance, if vailable. NULL returned if not available

//        DirexTransactionResponse response = new DirexTransactionResponse();
//        response.setTransactionData(data);
//        response.setResultCode(ResultCode.SUCCESS);
//        response.setResultMessage(ResultMessage.SUCCESS.getMessage());
//        return response;
        return data;
//
    }
//
//    @Override
//    public DirexTransactionResponse mapResponse(FuzeResponse fuzeResp, DirexTransactionRequest request) {
//
//        System.out.println("Creating the response to core >> FuzeBillerSearchManager.mapResponse()");
//        
//        DirexTransactionResponse response = null;
//
//        if (fuzeResp.getStatus().equals("601")) {//validate error codes
//            System.out.println("Invalid	velocity specified");
//            return manageExceptionAnswer(TransactionType.FUZE_PROCESS_PAYMENT, fuzeResp.getStatus(), fuzeResp.getMessage(),"");
//        }
//        if (!fuzeResp.getStatus().equals("100")) {
//            System.out.println("FUZE Status != 100");
//            return manageExceptionAnswer(TransactionType.FUZE_PROCESS_PAYMENT, fuzeResp.getStatus(), fuzeResp.getMessage(),"");
//        } else {
//
//            System.out.println("ToResponse All good");
//
//            Map fuzeData = fuzeResp.toMapProcessPayment();//when I select .toMap the response get the first biller no matter if there are more than 1.
//
//            if (fuzeData != null) {
//                addSubTransaction(TransactionType.FUZE_PROCESS_PAYMENT, ResultCode.SUCCESS, ResultMessage.SUCCESS.getMessage(), "");
//                response = new DirexTransactionResponse();
//                response.getTransaction().addSubTransactionList(transaction.getSub_Transaction());
//                response.setTransactionData(fuzeData);
//                response.setResultCode(ResultCode.SUCCESS);
//                response.setResultMessage(ResultMessage.SUCCESS.getMessage());
//                return response;
//            } else {
//                return manageExceptionAnswer(TransactionType.FUZE_PROCESS_PAYMENT, "", "Fuze Data came null","Fuze Data is null");
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
