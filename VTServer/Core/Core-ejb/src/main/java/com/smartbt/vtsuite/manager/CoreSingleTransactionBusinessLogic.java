/*
 ** File: CoreTransactionBusinessLogic.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.CardStatus;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.io.Serializable;
import java.util.Properties;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@TransactionManagement(value = TransactionManagementType.BEAN)
public class CoreSingleTransactionBusinessLogic extends CoreAbstractTransactionBusinessLogic {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CoreSingleTransactionBusinessLogic.class);

    private Transaction transaction;

    private JMSManager jmsManager = JMSManager.get();

    public CoreSingleTransactionBusinessLogic() {
        super();
    }

    @Override
    public void process(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {
        this.transaction = transaction;

        transaction.setSingle(Boolean.TRUE);

        DirexTransactionResponse direxTransactionResponse = null;
        try {
            Properties msgProps = new Properties();

            TransactionType transactionType = direxTransactionRequest.getTransactionType();
            String host = transactionType.getHost().toString();

            msgProps.setProperty("hostName", host);

//            coreLogger.logAndStore("CoreSingleBL", "             Send message to " + host);
//            log.info("[CoreSingleTransactionBusinessLogic] Send message to " + host);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreSingleTransactionBusinessLogic] Send message to " + host,null);

            long wait_time = 10000;
            jmsManager.sendWithProps(direxTransactionRequest, jmsManager.getHostInQueue(), direxTransactionRequest.getCorrelation(), msgProps);
            ObjectMessage tmsg;

            Message message = null;
            try {
                message = jmsManager.receive(jmsManager.getHostOutQueue(), direxTransactionRequest.getCorrelation(), wait_time);
            } catch (Exception e) {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " for " + transactionType.toString(),"");
                throw new SingleException();
            }
            if (message == null || !(message instanceof ObjectMessage)) {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED," for " + transactionType.toString(),"");
//               System.out.println("antes " + direxTransactionResponse.getResultMessage());
//               log.debug("[CoreSingleTransactionBusinessLogic] Message null " + direxTransactionResponse.getResultMessage());
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreSingleTransactionBusinessLogic] Message null " + direxTransactionResponse.getResultMessage(),null);
                throw new SingleException();
            }

            tmsg = (ObjectMessage) message;
            Serializable s = tmsg.getObject();
            direxTransactionResponse = (DirexTransactionResponse) s;

            if (direxTransactionResponse.wasApproved() && transaction.getData_sc1() != null) {
                transaction.getData_sc1().setCardStatus(CardStatus.ACTIVE.getId());
            }

            direxTransactionResponse.getTransactionData().put(ParameterName.PRINTLOGO, "01");
//            coreLogger.logAndStore("CoreSingleBL", "             Recived message from: " + host);
//            log.info("[CoreSingleTransactionBusinessLogic] Recived message from: " + host);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreSingleTransactionBusinessLogic] Recived message from: " + host,null);

        }catch(SingleException se) {
//             System.out.println("SingleException " + direxTransactionResponse.getResultMessage());
//             log.debug("[CoreSingleTransactionBusinessLogic] Error "+se);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreSingleTransactionBusinessLogic] Error ",se.getMessage());
        
        } catch (Exception e) {
            System.out.println("Exception");
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, e);
//            coreLogger.logAndStore("CoreSingleBL", "             Exception: " + e.getMessage());
//            log.debug("[CoreSingleTransactionBusinessLogic] Exception",e);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreSingleTransactionBusinessLogic] Error ",e.getMessage());
        }   
           
            jmsManager.send(direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());

            transaction.setResultCode(direxTransactionResponse.getResultCode().getCode());
            transaction.setResultMessage(direxTransactionResponse.getResultMessage());
            transaction.setErrorCode(direxTransactionResponse.getErrorCode());

            CoreTransactionUtil.persistTransaction(transaction);
        }

    }

    class SingleException extends Exception {
    }


