/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.dao.CardDao;
import com.smartbt.vtsuite.dao.MobileClientDao;
import com.smartbt.vtsuite.mock.MockFrontMobileBusinessLogic;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author suresh
 */
public class TransactionManager {
    
   private CardDao cardDao = CardDao.get(); 
   private MobileClientDao mobileClientDao = MobileClientDao.get();
    protected static TransactionManager _this;

    public static TransactionManager get() {
        if (_this == null) {
            _this = new TransactionManager();
        }
        return _this;
    }
    
    public ResponseData transactionHistory(int clientId,int startPage, int limit,String startDateStr, String endDateStr) {
       
        System.out.println("[TransactionManager]");
        ResponseData response = new ResponseData();             
        CreditCard card =null;
        String checkId = String.valueOf(clientId);
        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionManager] processTransaction " + checkId, null);
        DirexTransactionResponse technicardResponse;
        try {
            CreditCard m = mobileClientDao.getMobileClientId(clientId);
            if(m!=null){
                 card = cardDao.getCardNumberByClientId(clientId);
                 
            }
            //CreditCard card = cardDao.getCardNumberByClientId(clientId);
            System.out.println("[TransactionManager] - CARD_MASK_NUMBER:- "+card.getMaskCardNumber()+ " CARD_NUMBER:- "+card.getCardNumber());
            Map map = new HashMap();
            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_LAST_TRANSACTIONS);                        
            map.put(ParameterName.CARD_NUMBER, card.getCardNumber());
            map.put(ParameterName.START_DATE, startDateStr);
            map.put(ParameterName.END_DATE, endDateStr);
            map.put(ParameterName.TRANSACTION_QUANTITY, limit);                       
            direxTransactionRequest.setTransactionData(map);
            direxTransactionRequest.setCorrelation(card.getCardNumber());
            direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_LAST_TRANSACTIONS);
            System.out.println("[TransactionManager] - CLIENT_ID:- "+clientId);
            if((clientId == 1 || clientId == 0)){
               technicardResponse= MockFrontMobileBusinessLogic.get().process(direxTransactionRequest);
            }else{
                technicardResponse = FrontMobileBusinessLogic.get().process(direxTransactionRequest);
            }            
            if (technicardResponse != null && technicardResponse.wasApproved()) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "technicardResponse.wasApproved()" +technicardResponse.wasApproved(), null);
                response.setStatus(Constants.CODE_SUCCESS);
                response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);               
                if(technicardResponse.getTransactionData()!=null){                   
                    response.setData(technicardResponse.getTransactionData());
                }                
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionManager] processTransaction finished" + checkId, null);
                
            } else {
                response.setStatus(Constants.CODE_ERROR_GENERAL);
                response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.ERROR_GENERAL);
            }

           
        } catch (Exception e) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.ERROR_GENERAL);
            e.printStackTrace();
        }

        return response;
    }

    
}
