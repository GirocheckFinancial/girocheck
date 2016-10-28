/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.enums.EmailName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.manager.ClientManager;
import com.smartbt.girocheck.servercommon.manager.EmailManager;
import com.smartbt.girocheck.servercommon.manager.TerminalManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Email;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.util.email.GoogleMail;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.jms.Queue;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CoreTransactionUtil {

    public static void subTransactionFailed(Transaction transaction, DirexTransactionResponse response, Queue queue, String CorrelationId) throws Exception {
        if (queue != null) {
            response.setTransaction(null);
            JMSManager.get().send(response, queue, CorrelationId);
        }

        String transactionType = response.getTransactionType() != null ? response.getTransactionType().toString() : "Unknown";

        LogUtil.logAndStore("CoreBL", "SubTransactionFailed  -> " + transactionType + "   " + response.getResultMessage());

        if (response.getTransactionType() != null && response.getTransactionType() != TransactionType.TRANSACTION_TYPE) { // si entra aki es pk alguna sub_transaccion especifica fallo.
            SubTransaction subTransaction = new SubTransaction();
            subTransaction.setType(response.getTransactionType().getCode());
            subTransaction.setResultCode(response.getResultCode().getCode());
            subTransaction.setResultMessage(response.getResultMessage());
            subTransaction.setErrorCode(response.getErrorCode());
            NomHost host = response.getTransactionType().getHost();
            subTransaction.setHost(host == null ? 0 : host.getId());
            //subTransaction.setTransaction( transaction );
            transaction.addSubTransaction(subTransaction);
        }

        transaction.setResultCode(response.getResultCode().getCode());
        String msg = (response.getResultMessage() != null && response.getResultMessage().length() > 254) ? response.getResultMessage().substring(0, 254) : response.getResultMessage();
        transaction.setResultMessage(msg);

        persistTransaction(transaction);

    }

    public static void persistTransaction(Transaction transaction) throws Exception {
        Boolean send2LoadsEmail = false;
        String cardNumber = "";
        TerminalManager terminalManager = TerminalManager.get();
        TransactionManager transactionManager = TransactionManager.get();
        ClientManager clientManager = ClientManager.get();
        transaction.setTransactionFinished(true);
        printTransaction(transaction);

        try {
            HibernateUtil.beginTransaction();

            int idTerminal = transaction.getTerminal().getId();
            Terminal persistentTerminal = terminalManager.findById(idTerminal);
            transaction.setTerminal(persistentTerminal);
            // transaction.getClient().getTransaction().add( transaction );

            Client client = transaction.getClient();
            if (transaction.getClient() != null) {
                if (transaction.getResultCode() == ResultCode.SUCCESS.getCode()
                        && (transaction.getOperation().equals("01") || transaction.getOperation().equals("02"))) {

                    CreditCard card = transaction.getData_sc1();

                    Integer successfulLoads = client.getSuccessfulLoads();
                    if (successfulLoads == null) {
                        successfulLoads = 0;
                    }

                    System.out.println("previous successfulLoads = " + successfulLoads);
                    client.setSuccessfulLoads(successfulLoads + 1);

                    if (client.getSuccessfulLoads() == 2) {
                        send2LoadsEmail = true;
                        cardNumber = card.getCardNumber();
                    }

                    transaction.setData_sc1(card);
                } 
                
                clientManager.saveOrUpdate(client);
            }
            
            transactionManager.saveOrUpdate(transaction);

            System.out.println("**************  TRANSACTION SAVED SUCCESSFULY **************");

            if (send2LoadsEmail) {
                System.out.println("--------------  SENDING 2 SUCCESSFULL LOADS EMAIL TO TECNICARD --------------");
                Email email = EmailManager.get().getByName(EmailName.TWO_SUCCESSFUL_LOADS_TO_TECNICARD);
 
                Map<String, String> values = new HashMap<>();
                values.put("user_name", client.getFirstName());
                values.put("user_lastname", client.getLastName());
                values.put("user_ssn", client.getSsn());
                values.put("card_last4", cardNumber.substring(12));

                values.put("user_phone", client.getTelephone());

                Date dob = client.getBornDate();
                if (dob != null) {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    values.put("user_dob", df.format(client.getBornDate()));
                }

                if (client.getAddress() != null) {
                    values.put("user_address", client.getAddress().getFullAddress());
                }

                email.setValues(values);

                GoogleMail.get().sendEmail(email);
            }

            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            LogUtil.logAndStore("Exception in persistTransaction", e.getMessage());
            throw e;
        }

    }

    public static void printTransaction(Transaction transaction) {

        LogUtil.logAndStore("");

        LogUtil.logAndStore("--****************  SAVING TRANSACTION *****************--");
        LogUtil.logAndStore("type :: " + TransactionType.get(transaction.getTransactionType()));
        LogUtil.logAndStore("requestId :: " + transaction.getRequestId());
        LogUtil.logAndStore("date :: " + transaction.getDateTime());
        LogUtil.logAndStore("operation :: " + transaction.getOperation());
        LogUtil.logAndStore("ResultCode :: " + transaction.getResultCode());
        LogUtil.logAndStore("ResultMessage :: " + transaction.getResultMessage());

        LogUtil.logAndStore("");
        for (SubTransaction subTransaction : transaction.getSub_Transaction()) {
            LogUtil.logAndStore("________________  " + subTransaction.getOrder() + " :: " + TransactionType.get(subTransaction.getType()) + " __________________");
            LogUtil.logAndStore("                ResultCode :: " + subTransaction.getResultCode());
            LogUtil.logAndStore("                ResultMessage :: " + subTransaction.getResultMessage());
            LogUtil.logAndStore("                Errorcode :: " + subTransaction.getErrorCode());
            LogUtil.logAndStore("");
        }
    }

//This method determines if the given ID is an ITIN, 
//-If it is an ITIN, it returns 100 otherwise it is SSN and the method returns 2
/*
     1.	it is an ITIN if it starts with 9
     and has a range of 70 - 88 in the fourth and fifth digit 

     or if it is included in one of the following ranges:

     •	900-70-0000 through 999-88-9999.
     •	900-90-0000 through 999-92-9999.
     •	900-94-0000 through 999-99-9999.

     */
    public static IdType getIdTypeFromId(String ssn) {
        IdType t;
        if (ssn == null || ssn.length() != 9) {
            return IdType.OTHERS;
        }

        for (int i = 0; i < ssn.length(); i++) {
            if (!Character.isDigit(ssn.charAt(i))) {
                return IdType.OTHERS;
            }
        }

        return isITIN(ssn) ? IdType.OTHERS : IdType.SSN;
    }

    private static boolean isITIN(String ssn) {
        return condition1(ssn) || condition2(ssn);
    }

    private static boolean condition1(String ssn) {
        Integer sub = Integer.parseInt(ssn.substring(3, 5));
        System.out.println("sub = " + sub);
        return ssn.charAt(0) == '9' && sub >= 70 && sub <= 88;
    }

    private static boolean condition2(String ssn) {
        Long sub = Long.parseLong(ssn);
        Boolean b1 = (sub >= 900_70_0000L && sub <= 999_88_9999L);
        Boolean b2 = (sub >= 900_90_0000L && sub <= 999_92_9999L);
        Boolean b3 = (sub >= 900_94_0000L && sub <= 999_99_9999L);

        return b1 || b2 || b3;
    }

//    public static void printMap(DirexTransactionRequest request) {
//        Map map = request.getTransactionData();
//
//        Iterator it = map.keySet().iterator();
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Printing map", null);
//        while (it.hasNext()) {
//            Object key = it.next();
//            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] " + key + " -> " + map.get(key), null);
//        }
//    }
}
