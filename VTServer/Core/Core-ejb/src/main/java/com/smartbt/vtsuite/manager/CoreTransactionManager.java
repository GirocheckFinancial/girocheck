/*
 ** File: CoreTransactionManager.java
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

import com.smartbt.girocheck.servercommon.enums.EnumApplicationParameter;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.manager.ApplicationParameterManager;
import com.smartbt.girocheck.servercommon.manager.ClientManager;
import com.smartbt.girocheck.servercommon.manager.CreditCardManager;
import com.smartbt.girocheck.servercommon.manager.HostManager;
import com.smartbt.girocheck.servercommon.manager.TerminalManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Host;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.DirexException;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Core Manager Class
 */
public class CoreTransactionManager {

    private static CoreAbstractTransactionBusinessLogic businessLogic;
    public static List SINGLE_TRANSACTION_LIST;
    public static List COMPLEX_TRANSACTION_LIST;
    public static List LOCAL_TRANSACTION_LIST;
    public static List CARD_TO_BANK_BL_LIST;
    private Host cardHost;
    private static HostManager hostManager = new HostManager();
    private   ApplicationParameterManager applicationParameterManager = new ApplicationParameterManager();
    private Map<EnumApplicationParameter, Double> amountAplicationParameters;
    
    //TODO move this to System Properties
    public static final String ID_SCAN_AUTH_KEY = "48fa49a3-8ca4-4fc5-9a60-93271739969d";

    static {
        SINGLE_TRANSACTION_LIST = Arrays.asList(TransactionType.ISTREAM_CHECK_AUTH, TransactionType.TECNICARD_BALANCE_INQUIRY, TransactionType.ORDER_EXPRESS_CONTRATACIONES);
        COMPLEX_TRANSACTION_LIST = Arrays.asList(TransactionType.NEW_CARD_LOAD, TransactionType.CARD_RELOAD, TransactionType.CARD_RELOAD_WITH_DATA);
        LOCAL_TRANSACTION_LIST = Arrays.asList(TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG);
        CARD_TO_BANK_BL_LIST = Arrays.asList(TransactionType.TECNICARD_CARD_TO_BANK);
    }

    /**
     * The default constructor
     */
    public CoreTransactionManager() {
    }

    /**
     * Process Direx Transaction Request.
     *
     * @param direxTransactionRequest The transaction request object
     * @throws Exception
     */
    public void processTransaction(DirexTransactionRequest direxTransactionRequest) throws Exception {

        try {
            if (direxTransactionRequest.getTransactionData() != null && direxTransactionRequest.getTransactionData().containsKey(TransactionType.TRANSACTION_TYPE)) {

                TransactionType transactionType = direxTransactionRequest.getTransactionType();

                DateFormat df = DateFormat.getDateTimeInstance();
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreTransactionManager] Processing: " + transactionType + " at " + df.format(new Date()), null);

                Transaction transaction = createTransaction(direxTransactionRequest, transactionType);
                if (transaction.getResultCode() != null) {
                    if (transaction.getResultCode() == 3) {
                        DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CARD_RELOAD_DATA_CANCELED, ResultMessage.CARD_RELOAD_DATA_CANCELED);
                        JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                        return;
                    }
                    if (transaction.getResultCode() == 900) {
                        DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CARD_UNAUTHORIZED_BY_MIDDLEWARE, ResultMessage.CARD_UNAUTHORIZED_BY_MIDDLEWARE);
                        JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                        return;
                    }
                }
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[CoreTransactionManager] SWITCH: " + transactionType, null);

                switch (transactionType) {
                    case TECNICARD_BALANCE_INQUIRY:
                        businessLogic = new CoreSingleTransactionBusinessLogic();
                        break;
                    case CARD_RELOAD:
                    case CARD_RELOAD_WITH_DATA:
                    case NEW_CARD_LOAD:
//                        direxTransactionRequest.getTransactionData().put(ParameterName.CARDLOADTYPE, 1);
                        if (transaction.getOperation().contains("02")) {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] businessLogic = new CoreComplexCashTransactionBusinessLogic(logger);", null);
                            businessLogic = new CoreComplexCashTransactionBusinessLogic();
                        } else {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] businessLogic = new NewCoreComplexTransactionBusinessLogic(logger);", null);
                            businessLogic = new NewCoreComplexTransactionBusinessLogic();
                        }
                        break;
//                    case NEW_CARD_LOAD:
//                        direxTransactionRequest.getTransactionData().put(ParameterName.CARDLOADTYPE, 0);
//                        if(transaction.getOperation().contains( "02" )){
//                            businessLogic = new CoreComplexCashTransactionBusinessLogic(logger);
//                        }else{
//                            businessLogic = new NewCoreComplexTransactionBusinessLogic(logger);
//                        }
//                        break;
                    case ISTREAM_CHECK_AUTH_LOCATION_CONFIG:
                        businessLogic = new CoreLocalTransactionBusinessLogic();
                        break;
                    case TECNICARD_CARD_TO_BANK:
                        businessLogic = new CoreCardToBankBusinessLogic();
                        break;
                    default:
                        String msg = "Transaction " + transactionType + " not supported.";
                        DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, msg, "");
                        JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Exception: " + msg, null);
                        return;
                }

                businessLogic.process(direxTransactionRequest, transaction);

            } else {
                DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.CORE_RECEIVED_NULL);
                JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());

            }
        } catch (LoggingValidationException lve) {
            DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, lve.getResultCode() + lve.getMessage(), "");
            JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
        } catch (Exception ex) {
            DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ex);
            JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Exception processing transaction. ", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private Transaction createTransaction(DirexTransactionRequest direxTransactionRequest, TransactionType transactionType) throws Exception {
        TerminalManager terminalManager = new TerminalManager();
        TransactionManager transactionManager = new TransactionManager();
        ClientManager clientManager = new ClientManager();
        CreditCardManager creditCardManager = new CreditCardManager();
        cardHost = findingHost(direxTransactionRequest);

        Transaction transaction = new Transaction();

        if (cardHost.getHostName().equals(NomHost.FUZE.toString())) {
            transaction.setResultCode(900);
            return transaction;
        }

        String requestId = direxTransactionRequest.getRequestId();
        TransactionType originalTransactionType = direxTransactionRequest.getTransactionType();

        transaction.setRequestId(requestId);
        transaction.setTransactionType(originalTransactionType.getCode());
        Date currentDate = new Date();
        transaction.setDateTime(currentDate);

        String terminalId = (String) direxTransactionRequest.getTransactionData().get(ParameterName.TERMINAL_ID);

        Client client = null;

        try {
            HibernateUtil.beginTransaction();

            /*
        
             new card reload stuff
        
             */
            if (transactionType == TransactionType.CARD_RELOAD_WITH_DATA) {
                System.out.println("[CoreTransactionManager] transactionType == TransactionType.CARD_RELOAD_WITH_DATA");
                String cardNumberCR = (String) direxTransactionRequest.getTransactionData().get(ParameterName.CARD_NUMBER);
                CreditCard creditCardCR = creditCardManager.getByNumber(cardNumberCR);

                if (creditCardCR == null || creditCardCR.getClient() == null || creditCardCR.getClient().getFirstName().equals("BIQorCTB")) {
                    transaction.setResultCode(3);
                    return transaction;
                }

                //put in the direxTransactionRequest data all the data needed for the transaction.
                client = creditCardCR.getClient();
                Address address = new Address();
                State state = new State();
                try {
                    address = client.getAddress();
                    state = address.getState();
                } catch (Exception e) {

                    System.out.println("[CoreTransactionManager] createTransaction() ... address or state null.");
                    e.printStackTrace();
                }

                PersonalIdentification identification = clientManager.getIdentificationByClientId(client.getId());

                System.out.println("[CoreTransactionManager] createTransaction() ... personalIdentificationId: " + identification.getId());

                /*
                 * Personal Identification
                 */
                direxTransactionRequest.getTransactionData().put(ParameterName.IDBACK, identification.getIdFront());
                direxTransactionRequest.getTransactionData().put(ParameterName.IDFRONT, identification.getIdBack());
                direxTransactionRequest.getTransactionData().put(ParameterName.PHONE, client.getTelephone());
                direxTransactionRequest.getTransactionData().put(ParameterName.SSN, client.getSsn());
                direxTransactionRequest.getTransactionData().put(ParameterName.IDTYPE, IdType.getIdType(identification.getIdType()));
                direxTransactionRequest.getTransactionData().put(ParameterName.ID, identification.getIdentification());

                direxTransactionRequest.getTransactionData().put(ParameterName.BORNDATE, client.getBornDate());
                direxTransactionRequest.getTransactionData().put(ParameterName.FIRST_NAME, client.getFirstName());
                direxTransactionRequest.getTransactionData().put(ParameterName.LAST_NAME, client.getLastName());
                direxTransactionRequest.getTransactionData().put(ParameterName.ADDRESS, address.getAddress());
                direxTransactionRequest.getTransactionData().put(ParameterName.CITY, address.getCity());
                direxTransactionRequest.getTransactionData().put(ParameterName.STATE, state.getCode());
                direxTransactionRequest.getTransactionData().put(ParameterName.ZIPCODE, address.getZipcode());

            }
            /*
        
             end 
            
             */

            if (client != null && client.hasITIN()) {
                /*
                 * ITIN value 100
                 */
                direxTransactionRequest.getTransactionData().put(ParameterName.SENSITIVEIDTYPE, IdType.OTHERS);
            } else {
                direxTransactionRequest.getTransactionData().put(ParameterName.SENSITIVEIDTYPE, IdType.SSN);
            }

            Terminal terminal = terminalManager.findBySerialNumber(terminalId);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] (terminalId) :: " + terminalId, null);
            if (terminal == null) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Terminal with id : " + terminalId + " doesn't exist. ", null);
                throw new LoggingValidationException(ResultCode.TERMINAL_ID_NOT_EXIST, " Terminal with id : " + terminalId + " doesn't exist. ", transaction);
            } else {
                String user = (String) direxTransactionRequest.getTransactionData().get(ParameterName.USER);
                String password = (String) direxTransactionRequest.getTransactionData().get(ParameterName.PASSWORD);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) (user/passw) :: (" + user + "/" + password + "). Originals (" + terminal.getGirocheckUser() + "/" + terminal.getGirocheckPassword() + ")", null);
                if (!terminal.getGirocheckUser().equals(user) || !terminal.getGirocheckPassword().equals(password)) {
                    throw new LoggingValidationException(ResultCode.LOGIN_FAILED, "Login Failed. For this terminal is (" + terminal.getGirocheckUser() + "/" + terminal.getGirocheckPassword() + "). Received(" + user + "/" + password + ")", transaction);
                }
            }

            direxTransactionRequest.getTransactionData().put(ParameterName.IDMERCHANT, terminal.getMerchant().getId());

            if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.AMMOUNT)) {
                double ammount = (Double) direxTransactionRequest.getTransactionData().get(ParameterName.AMMOUNT);
                 
                transaction.setAmmount(ammount);
                
                String operation = (String) direxTransactionRequest.getTransactionData().get(ParameterName.OPERATION);
               
                if(amountAplicationParameters == null){
                    amountAplicationParameters = applicationParameterManager.getAmountAplicationParameters();
                }
                
                validateAmount( ammount,  operation,  transaction, amountAplicationParameters);
            }

            if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.SSN)) {
                String ssn = (String) direxTransactionRequest.getTransactionData().get(ParameterName.SSN);

                byte[] addressForm = null;

                if (transactionType == TransactionType.NEW_CARD_LOAD
                        && direxTransactionRequest.getTransactionData().containsKey(ParameterName.ADDRESS_CORRECT)
                        && (direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_CORRECT) != null)
                        && (((String) direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_CORRECT)).contains("n") || ((String) direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_CORRECT)).contains("N"))
                        && direxTransactionRequest.getTransactionData().containsKey(ParameterName.ADDRESS_FORM) && direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_FORM) != null) {
                    addressForm = (byte[]) direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_FORM);
                }
                System.out.println("[CoreTransactionManager] client = clientManager.createOrGet( ssn, addressForm );");
                client = clientManager.createOrGet(ssn, addressForm);

                if (client.getEmail() != null && !client.getEmail().isEmpty()) {
                    direxTransactionRequest.getTransactionData().put(ParameterName.EMAIL, client.getEmail());
                }
            }

            if (client != null && client.hasITIN()) {
                /*
                 * ITIN value 100
                 */
                direxTransactionRequest.getTransactionData().put(ParameterName.IDTYPE, IdType.OTHERS);
            } else {
                direxTransactionRequest.getTransactionData().put(ParameterName.IDTYPE, IdType.SSN);
            }

            if (transactionType == TransactionType.NEW_CARD_LOAD || transactionType == TransactionType.CARD_RELOAD || transactionType == TransactionType.CARD_RELOAD_WITH_DATA) {
                direxTransactionRequest.getTransactionData().put(ParameterName.MERCHANT_NAME, terminal.getMerchant().getLegalName());
                direxTransactionRequest.getTransactionData().put(ParameterName.EMAIL, "girocheck@cardmarte.com");
            }
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] IDPOS$IDTELLERR ", null);
            direxTransactionRequest.getTransactionData().put(ParameterName.IDPOS, terminal.getMerchant().getIdPosOrderExp());
            direxTransactionRequest.getTransactionData().put(ParameterName.IDTELLER, terminal.getMerchant().getIdTellerOrderExp());
            direxTransactionRequest.getTransactionData().put(ParameterName.IDTELLERPAGO, terminal.getMerchant().getIdTellerPagoOrderExp());
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] IDPOS: " + terminal.getMerchant().getIdPosOrderExp() + " IDTELLER: " + terminal.getMerchant().getIdTellerOrderExp(), null);

            if (transactionType == TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG) {
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_ISTREAM, terminal.getMerchant().getIdIstreamTecnicardCheck());
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_USER_ISTREAM, terminal.getMerchant().getIstreamUser());
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_PASSWORD_ISTREAM, terminal.getMerchant().getIstreamPassword());
            }

            transaction.setTerminal(terminal);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager]transaction.setClient( client ) client has value = " + (client != null), null);
            transaction.setClient(client);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] ASKING FOR PARAMETER.PHONE. ", null);

            if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.PHONE)) {
                String cell_area_code = (String) direxTransactionRequest.getTransactionData().get(ParameterName.PHONE);
                direxTransactionRequest.getTransactionData().put(ParameterName.CELL_PHONE_AREA, cell_area_code.substring(0, 3));

                String cell_phone = (String) direxTransactionRequest.getTransactionData().get(ParameterName.PHONE);
                direxTransactionRequest.getTransactionData().put(ParameterName.CELL_PHONE, cell_phone.substring(3));

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] CELL AREA CODE: " + cell_phone.substring(0, 3) + " CELL NUMBER " + cell_phone.substring(3), null);
            }
            if (!direxTransactionRequest.getTransactionData().containsKey(ParameterName.ACCOUNT_NUMBER)) {
                String account = terminalManager.getAccountFromMerchantByTerminalSerialNumber(terminal.getSerialNumber());
                transaction.setAccount(account);
                direxTransactionRequest.getTransactionData().put(ParameterName.ACCOUNT_NUMBER, account);
            } else {
                transaction.setAccount((String) direxTransactionRequest.getTransactionData().get(ParameterName.ACCOUNT_NUMBER));
            }

            // ---------------------  CREDIT CARD LOGIC --------   ( not used in this verssion )   ----------------
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] transactionType = " + transactionType.toString(), null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] transactionType = " + transactionType, null);

            if (transactionType != TransactionType.TECNICARD_BALANCE_INQUIRY
                    && transactionType != TransactionType.TECNICARD_CARD_TO_BANK
                    && transactionType != TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG) {
                if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.CARD_NUMBER)) {
                    String cardNumber = (String) direxTransactionRequest.getTransactionData().get(ParameterName.CARD_NUMBER);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) Getting creditCard with cardNumber: [" + cardNumber + "]", null);
                    if (!cardNumber.equals("")) {
                        transaction.setCardNumber(cardNumber);
                        CreditCard creditCard = null;

                        if (transactionType == TransactionType.NEW_CARD_LOAD || transactionType == TransactionType.CARD_RELOAD || transactionType == TransactionType.CARD_RELOAD_WITH_DATA) {
                            try {
                                creditCard = creditCardManager.createOrGet(cardNumber, client, terminal.getMerchant());
                            } catch (Exception e) {
                                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) Error in creditCardManager.createOrGet(...) ", null);
                                e.printStackTrace();
                                throw new CreditCardException(ResultCode.CORE_ERROR, " CreditCard obtainment operation problem. ", transaction);
                            }
                        } else {
//                        creditCard = creditCardManager.get(cardNumber);
                            Client client1 = new Client();
                            client1.setFirstName("BIQorCTB");
                            creditCard = creditCardManager.createOrGet(cardNumber, client1, terminal.getMerchant());

                        }
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) Before ask if creditcard from database is null.", null);
                        if (creditCard == null) {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) CREDIT CARD FROM DATABASE IS NULL !!!", null);
                            throw new CreditCardException(ResultCode.CREDIT_CARD_NOT_EXIST, " CreditCard value is null. ", transaction);
                        } else {
                            transaction.setData_sc1(creditCard);
                        }
                    } else {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) CreditCard value from the Terminal is NULL. ", null);
                        throw new CreditCardException(ResultCode.CREDIT_CARD_NOT_EXIST, "CreditCard Value from Terminal : Is Null. ", transaction);
                    }
//
//                CreditCard creditCard;
//                if ( transactionType == TransactionType.NEW_CARD_LOAD ) {
//                    creditCard = creditCardManager.getByNumber( cardNumber );
//
//                    if ( creditCard == null ) {
//                        creditCard = new CreditCard();
//                        creditCard.setCardNumber( cardNumber );
//                        creditCard.setClient( client );
//                        creditCard.setCardStatus( CardStatus.UNACTIVE.getId() );
//                    } else {
//                        if ( creditCard.getCardStatus() != CardStatus.UNACTIVE.getId() ) {
//                            throw new LoggingValidationException( ResultCode.CARD_ALREADY_PERSONALIZED, "Credit Card has already been personalized.", transaction );
//                        }
//                    }
//                } else {
//                    // si no es un NEW_CARD_LOAD, la tarjeta tiene que tener ese numero en la base de datos, 
//                    // o tiene que existir una tarjeta con status WAITING_OFFICIAL_NUMBER perteneciente a este cliente.
//                    creditCard = creditCardManager.getByNumber( cardNumber );
//
//                    if ( creditCard == null ) {
//                        if ( transactionType == TransactionType.TECNICARD_BALANCE_INQUIRY ) {
//                            creditCard = null;
//                        } else {
//                            creditCard = creditCardManager.getCardWaitingOfficialNumber( cardNumber );
//                            if ( creditCard == null ) {//si no existe 
//                                throw new LoggingValidationException( ResultCode.CARD_NOT_PERSONALIZED, "Credit Card need to be personalized.", transaction );
//                            }
//                        }
//                    }
//                }
//
//                transaction.setData_sc1( creditCard );
                }
            }
            // -----------------------------------------------------------------

            direxTransactionRequest.getTransactionData().put(ParameterName.HOSTNAME, cardHost.getHostName());

            if (COMPLEX_TRANSACTION_LIST.contains(transactionType)) {
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_USER_ISTREAM, terminal.getMerchant().getIstreamUser());
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_PASSWORD_ISTREAM, terminal.getMerchant().getIstreamPassword());
            }

            if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.OPERATION)) {
                String operation = (String) direxTransactionRequest.getTransactionData().get(ParameterName.OPERATION);
                transaction.setOperation(operation);
                if (operation.contains("01")) {// check
                    if (cardHost.getHostName().equals("FUZE")) {
                        direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_ISTREAM, terminal.getMerchant().getIdIstreamFuzeCheck());
                    } else {
                        direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_ISTREAM, terminal.getMerchant().getIdIstreamTecnicardCheck());
                    }
                    direxTransactionRequest.getTransactionData().put(ParameterName.IDSERVICE, "1");
                    direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_TECNICARD, terminal.getMerchant().getIdTecnicardCheck());
                } else {  // if operation == 02 cash
                    if (cardHost.getHostName().equals("FUZE")) {
                        direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_ISTREAM, terminal.getMerchant().getIdIstreamFuzeCash());
                    } else {
                        direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_ISTREAM, terminal.getMerchant().getIdIstreamTecnicardCash());
                    }
                    direxTransactionRequest.getTransactionData().put(ParameterName.IDSERVICE, "2");
                    direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_TECNICARD, terminal.getMerchant().getIdTecnicardCash());

                }
            }

            transaction.setTransactionFinished(false);

            transactionManager.saveOrUpdate(transaction);
            HibernateUtil.commitTransaction();
        } catch (AmountException amountException) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) amountException " + amountException.getMessage(), null);
            transactionManager.saveOrUpdate(amountException.getTransaction());
            HibernateUtil.commitTransaction();
            throw amountException;
        } catch (LoggingValidationException loggingValidationException) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) LoggingValidationException " + loggingValidationException.getMessage(), null);
            transactionManager.saveOrUpdate(loggingValidationException.getTransaction());
            HibernateUtil.commitTransaction();
            throw loggingValidationException;
        } catch (CreditCardException cardException) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) CreditCardException " + cardException.getMessage(), null);
            transactionManager.saveOrUpdate(cardException.getTransaction());
            HibernateUtil.commitTransaction();
            throw cardException;
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) Exception. ", e.getMessage());
            HibernateUtil.rollbackTransaction();
            throw e;
        }

        return transaction;
    }

    public Host findingHost(DirexTransactionRequest request) throws Exception {

        Host host = new Host();

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] findingHost(DirexTransactionRequest request). ", null);

        Map transactionData = request.getTransactionData();

        if (transactionData.containsKey(ParameterName.CARD_NUMBER)) {

            String cardNumber = (String) transactionData.get(ParameterName.CARD_NUMBER);

            String binNumber = "";
            try {
                binNumber = cardNumber.substring(0, 6);
            } catch (Exception e) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] BAD CARD NUMBER WAS RECEIVED " + cardNumber + " and the exception was: ", e.getMessage());
            }

            if (!binNumber.isEmpty()) {
                try {
                    HibernateUtil.beginTransaction();
                    host = hostManager.getHostByBinNumber(binNumber);

                    HibernateUtil.commitTransaction();

                } catch (Exception e) {
                    HibernateUtil.rollbackTransaction();
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Error getting the host by bin number. ", e.getMessage());
                    e.printStackTrace();
                }
            }

        } else {
            try {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Looking defaultHost", null);
                HibernateUtil.beginTransaction();
                host = hostManager.getDefaultHost();
                HibernateUtil.commitTransaction();
            } catch (Exception e) {
                HibernateUtil.rollbackTransaction();
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Error getting default host", e.getMessage());
                e.printStackTrace();
            }
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Finding Host result: " + host.getHostName(), null);
        return host;

    }
    
    public void validateAmount(Double amount, String operation, Transaction transaction,Map<EnumApplicationParameter, Double> amountParameters) throws AmountException{
       Double minCheck = amountParameters.get(EnumApplicationParameter.AMOUNT_MIN_CHECK);
       Double maxCheck = amountParameters.get(EnumApplicationParameter.AMOUNT_MAX_CHECK);
       Double minCash = amountParameters.get(EnumApplicationParameter.AMOUNT_MIN_CASH);
       Double maxCash= amountParameters.get(EnumApplicationParameter.AMOUNT_MAX_CASH);
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] AMOUNT_MIN_CHECK = " + minCheck,null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] AMOUNT_MAX_CHECK = " + maxCheck,null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] AMOUNT_MIN_CASH = " + minCash,null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] AMOUNT_MAX_CASH = " + maxCash,null);
       
        boolean isValid;
        if (operation != null && operation.contains("01")) {// check
            isValid = amount >= minCheck && amount <= maxCheck;
        }else{
            isValid = amount >= minCash && amount <= maxCash;
        }
        if(!isValid){
             CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Exception => INVALID AMOUNT",null);
            
            throw new AmountException("Amount " + amount + " is out of the allowed range.",transaction);
        }
    }

}

class TransactionException extends DirexException {

    private Transaction transaction;

    public TransactionException(ResultCode resultCode, String message, Transaction transaction) {
        super(resultCode, message);
        this.transaction = transaction;
        this.transaction.setResultCode(resultCode.getCode());
        this.transaction.setResultMessage(message);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    
    
}

class LoggingValidationException extends TransactionException {

    public LoggingValidationException(ResultCode resultCode, String message, Transaction transaction) {
        super(resultCode, message, transaction);
    }
}

class CreditCardException extends TransactionException {

    public CreditCardException(ResultCode resultCode, String message, Transaction transaction) {
        super(resultCode, message, transaction);
    }
}

class AmountException extends TransactionException {

    public AmountException(String message, Transaction transaction) {
        super(ResultCode.INVALID_AMOUNT, message, transaction);
    }
}
