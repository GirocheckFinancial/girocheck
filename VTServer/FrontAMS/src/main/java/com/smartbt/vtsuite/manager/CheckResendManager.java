package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.dao.CheckResendDAO;
import com.smartbt.girocheck.servercommon.display.CheckDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.Check;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 *
 * @author suresh
 */
public class CheckResendManager {

    private CheckResendDAO checkResendDAO = CheckResendDAO.get();
    private JMSManager jmsManager = JMSManager.get();
    String correlationId;
    protected static CheckResendManager _this;

    public static CheckResendManager get() {
        if (_this == null) {
            _this = new CheckResendManager();
        }
        return _this;
    }

    public ResponseDataList searchCheckDetails(int firstResult, int maxResult, String status, Date startRangeDateStr, Date endRangeDateStr) {
        return checkResendDAO.searchCheckDetails(firstResult, maxResult, status, startRangeDateStr, endRangeDateStr);
    }

    public BaseResponse resendCheck(int id) {
        System.out.println("[CheckResendManager] resendCheck()");
        ResponseData response = new ResponseData();
        String checkId = String.valueOf(id);
        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckResendManager] processTransaction " + checkId, null);
        
        try {
            Check check = checkResendDAO.getCheckDetails(id);
            Map map = new HashMap();
            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.ISTREAM2_SEND_SINCE_ICL);
            String prodProperty = System.getProperty("PROD");
            Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");
            if(isProd)
            {
              String userName = System.getProperty("ISTREAM2_USER_NAME");
              String password = System.getProperty("ISTREAM2_PWD");

               map.put(ParameterName.USER, userName);
               map.put(ParameterName.PASSWORD, password);
               
            }else{
                 map.put(ParameterName.USER, "GCTLS");
                 map.put(ParameterName.PASSWORD, "sts283");
                 map.put(ParameterName.DEPOSIT, "4pm Deposit");

            }
            // map.put(ParameterName.USER, "GCTLS");
            //map.put(ParameterName.PASSWORD, "sts283");
            //map.put(ParameterName.LOCATION_ID, check.getLocationId());
            //map.put(ParameterName.LOCATION_ID, "4769778");
            map.put(ParameterName.AMMOUNT, check.getTransaction().getAmmount());
            map.put(ParameterName.DEPOSIT, "4pm Deposit");
            map.put(ParameterName.MICR, check.getMicr());
            map.put(ParameterName.CHECK_ID, check.getId());
            map.put(ParameterName.CHECK_FRONT, check.getCheckFront());
            map.put(ParameterName.CHECK_BACK, check.getCheckBack());            
            
            direxTransactionRequest.setTransactionData(map);
            direxTransactionRequest.setCorrelation(checkId);
            direxTransactionRequest.setTransactionType(TransactionType.ISTREAM2_SEND_SINCE_ICL);

            correlationId = checkId;

            long wait_time = 1800000;//30 mins

            DirexTransactionResponse iStreamResponse = sendMessageToHost(direxTransactionRequest, "ISTREAM2", wait_time, false);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "iStreamResponse " +iStreamResponse, null);         
            
            if (iStreamResponse != null && iStreamResponse.wasApproved()) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "iStreamResponse.wasApproved()" +iStreamResponse.wasApproved(), null);
                CheckDisplay checkDisplay = checkResendDAO.resendCheck(id);
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "iStreamResponse.wasApproved() = true" +checkDisplay, null);
                if (checkDisplay != null) {                  
                    response.setStatus(Constants.CODE_SUCCESS);
                    response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckResendManager] processTransaction finished" + checkId, null);
                } else {
                    response.setStatus(Constants.CODE_ERROR_GENERAL);
                    response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.ERROR_GENERAL);
                }
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

    private DirexTransactionResponse sendMessageToHost(DirexTransactionRequest request, String hostName, long waitTime, boolean notifyToIstream) throws JMSException, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckResendManager] Send message to host " + hostName, null);

        Properties props = new Properties();
        props.setProperty("hostName", hostName);

        jmsManager.sendWithProps(request, jmsManager.getHostInQueue(), correlationId, props);

        if (request.getTransactionType() == TransactionType.ISTREAM2_SEND_SINCE_ICL) {
            return receiveMessageFromHost(request.getTransactionType(), hostName, waitTime, notifyToIstream);
        } else {
            return null;
        }
    }

    private DirexTransactionResponse receiveMessageFromHost(TransactionType transactionType, String hostName, long waitTime, boolean notifyToIstream) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckResendManager] Receiving message from host " + hostName, null);

        Message message = null;
        DirexTransactionResponse response;
        try {
            message = jmsManager.receive(jmsManager.getHostOutQueue(), correlationId, waitTime);
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckResendManager] receiving message on receiveMessageFromHost() throws an exeption.", null);
            response = DirexTransactionResponse.forException(transactionType, ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " " + hostName);
            response.setTransactionType(transactionType);

            throw new Exception();
        }


        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        response = (DirexTransactionResponse) s;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckResendManager] Message received", null);

        return response;
    }
}
