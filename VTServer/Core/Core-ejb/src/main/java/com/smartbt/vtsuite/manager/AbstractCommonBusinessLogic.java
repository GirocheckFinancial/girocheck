
/*

 @Author Roberto Rodriguez
 robertoSoftwareEngineer@gmail.com

 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.CheckStatus;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.girocheck.servercommon.enums.EnumCountry;
import com.smartbt.girocheck.servercommon.enums.EnumState;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.manager.CountryManager;
import com.smartbt.girocheck.servercommon.manager.FeeBucketsManager;
import com.smartbt.girocheck.servercommon.manager.PersonalIdentificationManager;
import com.smartbt.girocheck.servercommon.manager.StateManager;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.Check;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.IDScanner;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.sql.rowset.serial.SerialBlob;
import static com.smartbt.vtsuite.util.CoreTransactionUtil.*;
import com.smartbt.vtsuite.util.TransactionalException;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomState;
import java.util.Arrays;

@TransactionManagement(value = TransactionManagementType.BEAN)
public abstract class AbstractCommonBusinessLogic extends CoreAbstractTransactionBusinessLogic {

    protected JMSManager jmsManager = JMSManager.get();
//    protected int state = 0;

    // WAITING TIMES
    protected static final long TECNICARD_CONFIRMATION_WAIT_TIME = 180000;//3min
    protected static final long ISTREAM_HOST_WAIT_TIME = 30000;//30sec
    protected static final long WESTECH_HOST_WAIT_TIME = 30000;//30sec
    protected static final long PERSONAL_INFO_WAIT_TIME = 420000;//7min 
    protected static final long CHOICE_WAIT_TIME = 300000;//5min 
    public static final long GENERIC_VALIDATION_WAIT_TIME = 60000;//1min
    protected static final long GENERIC_CARD_LOAD_WAIT_TIME = 60000;//1min 
    protected static final long CERTEGY_WAIT_TIME = 30000;//30sec
    protected static final long CERTEGY_INFO_WAIT_TIME = 420000;//7min
    protected static final long ORDER_EXPRESS_WAIT_TIME = 300000;//5min

    protected static CountryManager countryManager = CountryManager.get();
    protected static StateManager stateManager = StateManager.get();
    protected PersonalIdentificationManager personalIdentificationManager = PersonalIdentificationManager.get();

    protected void sendAnswerToTerminal(TransactionType transactionType, ResultCode resultCode, String estimated_posting_time, String correlationId) throws JMSException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Send answer to TERMINAL", null);

        DirexTransactionResponse provissionalResponse = new DirexTransactionResponse();
        provissionalResponse.setResultCode(resultCode);

        if (!estimated_posting_time.isEmpty()) {
            provissionalResponse.setResultMessage(estimated_posting_time);
        } else {
            provissionalResponse.setResultMessage(ResultMessage.SUCCESS.getMessage());
        }

        Queue queue;

        if (transactionType == TransactionType.TECNICARD_CONFIRMATION) {
            provissionalResponse.getTransactionData().put(ParameterName.PRINTLOGO, "01");
            queue = jmsManager.getCore2OutQueue();
        } else {
            queue = jmsManager.getCoreOutQueue();
        }

        //provissionalResponse.getTransactionData().put("host", host);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Send message to TERMINAL:: queue = " + queue.getQueueName() + ", correlationId = " + correlationId, null);
        JMSManager.get().send(provissionalResponse, queue, correlationId);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Send message to TERMINAL Done.", null);
    }

    protected DirexTransactionResponse sendMessageToHost(DirexTransactionRequest request, NomHost host, long waitTime, Transaction transaction) throws JMSException, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Send " + request.getTransactionType() + " to host " + host, null);

        Properties props = new Properties();
        props.setProperty("hostName", host.toString());
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        jmsManager.sendWithProps(request, jmsManager.getHostInQueue(), request.getCorrelation(), props);
        //TODO change this for IStream.sendSingleICL (and add an else logic for it)
        if (request.getTransactionType() != TransactionType.ISTREAM_CHECK_AUTH_SUBMIT) {
            direxTransactionResponse = receiveMessageFromHost(request.getTransactionType(), host, waitTime, request.getCorrelation());
            transaction.addSubTransactionList(direxTransactionResponse.getTransaction().getSub_Transaction());
        }

        if (!direxTransactionResponse.wasApproved()) {
            direxTransactionResponse.setTransactionType(request.getTransactionType());
            throw new TransactionalException(direxTransactionResponse);
        }

        return direxTransactionResponse;
    }

    protected DirexTransactionResponse receiveMessageFromHost(TransactionType transactionType, NomHost host, long waitTime, String correlationId) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Receiving message from host " + host, null);

        Message message = null;
        DirexTransactionResponse response;
        try {
            message = jmsManager.receive(jmsManager.getHostOutQueue(), correlationId, waitTime);
        } catch (Exception e) {
            throw new TransactionalException(ResultCode.getFromHost(host), transactionType, e);
        }

        if (message == null || !(message instanceof ObjectMessage)) {
            throw new TransactionalException(ResultCode.getFromHost(host), transactionType, "Message received is null.");
        }

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        response = (DirexTransactionResponse) s;

        if (response == null) {
            throw new TransactionalException(ResultCode.getFromHost(host), transactionType, "Message received is null.");
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Message received:: for SubTransaction: " + transactionType + ". response.wasApproved() = " + response.wasApproved(), null);

        return response;
    }

    public void postprocess(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {
    }

    protected void choiceNotifyPayment(DirexTransactionRequest request, Transaction transaction) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] choiceNotifyPayment", null);
        DirexTransactionRequest choiceRequest = request.clone();
        choiceRequest.setTransactionType(TransactionType.CHOICE_NOTIFY_PAYMENT);

        sendMessageToHost(choiceRequest, NomHost.CHOICE, CHOICE_WAIT_TIME, transaction);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] Response from choiceNotifyPayment", null);

    }

    //Don't remove  (This is for when switch to Choice)
//    protected void choiceCancellationRequest(DirexTransactionRequest request, Transaction transaction) throws Exception {
//
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic]  Sending  ChoiceCancellationRequest", null);
//        DirexTransactionRequest choiceRequest = request.clone();
//        choiceRequest.setTransactionType(TransactionType.CHOICE_CANCELATION_REQUEST);
//
//        sendMessageToHost(choiceRequest, NomHost.CHOICE, CHOICE_WAIT_TIME, transaction);
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] Response from CHOICE, subTRANSACTION FINISHED. ", null);
//
//    }
    protected void certegyReverseRequest(DirexTransactionRequest request, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic]  Sending  CertegyReverseRequest", null);
        DirexTransactionRequest certegyRequest = request.clone();
        certegyRequest.setTransactionType(TransactionType.CERTEGY_REVERSE_REQUEST);

        sendMessageToHost(certegyRequest, NomHost.CERTEGY, CERTEGY_WAIT_TIME, transaction);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] Response from CERTEGY, subTRANSACTION FINISHED. ", null);

    }

    protected String getFromMap(Map map, ParameterName parameterName) {
        if (map.containsKey(parameterName)) {
            return (String) map.get(parameterName);
        } else {
            return null;
        }
    }

    protected void feeCalculator(DirexTransactionRequest request, Transaction transaction) throws TransactionalException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] feeCalculator() start ...", null);

        try {
            HibernateUtil.beginTransaction();

            FeeBucketsManager feeBucketsManager = new FeeBucketsManager();
            Map map = (Map) feeBucketsManager.getFees(request.getTransactionData().get(ParameterName.IDMERCHANT) + "",
                    request.getTransactionData().get(ParameterName.OPERATION) + "",
                    request.getTransactionData().get(ParameterName.AMMOUNT) + "");

            String finalFee = map.get(ParameterName.CRDLDF) + "";
            Double feeAmount = Double.parseDouble(finalFee);

            Double amount = (Double) request.getTransactionData().get(ParameterName.AMMOUNT);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] FEE_AMOUNT applied: " + feeAmount, null);
            Double payOut = amount - feeAmount;//No se le resta ese fee a peticion de carlos aparicio dic/04/2014

            request.getTransactionData().put(ParameterName.PAYOUT_AMMOUNT, payOut);
            request.getTransactionData().put(ParameterName.FEE_AMMOUNT, feeAmount);

            transaction.setFeeAmmount(feeAmount);
            transaction.setPayoutAmmount(payOut);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] feeCalculator() done with PAYOUT_AMOUNT: " + payOut, null);

            HibernateUtil.commitTransaction();
        } catch (Exception e) {

            HibernateUtil.rollbackTransaction();
            throw new TransactionalException(ResultCode.CORE_FEE_CALCULATION_ERROR, request.getTransactionType(), e);
        }
    }

    protected void fillOutClient(Map transactionMap, Transaction transaction) throws SQLException, Exception {

        if (transactionMap.containsKey(ParameterName.FIRST_NAME)) {
            transaction.getClient().setFirstName((String) transactionMap.get(ParameterName.FIRST_NAME));
        }
        if (transactionMap.containsKey(ParameterName.LAST_NAME)) {
            transaction.getClient().setLastName((String) transactionMap.get(ParameterName.LAST_NAME));
        }
        if (transactionMap.containsKey(ParameterName.MIDDLE_NAME)) {
            if (transaction.getClient().getFirstName() == null) {
                transaction.getClient().setFirstName((String) transactionMap.get(ParameterName.MIDDLE_NAME));
            } else {
                transaction.getClient().setFirstName(transaction.getClient().getFirstName() + " " + ((String) transactionMap.get(ParameterName.MIDDLE_NAME)));
            }
        }
        if (transactionMap.containsKey(ParameterName.MAIDEN_NAME)) { //in case the last name comes in the maiden name
            if (transaction.getClient().getLastName() == null || transaction.getClient().getLastName().isEmpty()) {
                transaction.getClient().setLastName((String) transactionMap.get(ParameterName.MAIDEN_NAME));
            }
        }
        if (transactionMap.containsKey(ParameterName.TELEPHONE)) {
            transaction.getClient().setTelephone((String) transactionMap.get(ParameterName.TELEPHONE));
        }
        if (transactionMap.containsKey(ParameterName.EMAIL)) {
            transaction.getClient().setEmail((String) transactionMap.get(ParameterName.EMAIL));
        }
        if (transactionMap.containsKey(ParameterName.BORNDATE_AS_DATE)) {
            transaction.getClient().setBornDate((Date) transactionMap.get(ParameterName.BORNDATE_AS_DATE));
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) transaction.getClient().getBornDate() = " + transaction.getClient().getBornDate(), null);

        if (transactionMap.containsKey(ParameterName.ADDRESS_CORRECT) && transactionMap.get(ParameterName.ADDRESS_CORRECT) != null) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) ADDRESS_CORRECT != null: true.", null);
            if ((((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("n")) || ((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("N")) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) ADDRESS_CORRECT = [" + transactionMap.get(ParameterName.ADDRESS_CORRECT) + "]", null);
                if (transactionMap.containsKey(ParameterName.ADDRESS_FORM) && transactionMap.get(ParameterName.ADDRESS_FORM) != null) {
                    byte[] addressForm = (byte[]) transactionMap.get(ParameterName.ADDRESS_FORM);
                    if (addressForm != null) {
                        if (addressForm.length > 0) {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) addressForm.length > 0", null);
                            java.sql.Blob addressFormBlob = new SerialBlob(addressForm);
                            transaction.getClient().setAddressForm(addressFormBlob);
                        } else {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) addressForm.length = 0", null);
                        }

                    } else {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) addressForm is null", null);
                    }
                }
            }
        }
    }

    protected void fillOutClientAddress(Map transactionMap, Transaction transaction) {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClientAddress(...) ", null);
        if (transaction.getClient().getAddress() == null) {
            transaction.getClient().setAddress(new Address());
            transaction.getClient().getAddress().setClient(transaction.getClient());
        }

        if (transactionMap.containsKey(ParameterName.ADDRESS)) {
            transaction.getClient().getAddress().setAddress((String) transactionMap.get(ParameterName.ADDRESS));
        }
        if (transactionMap.containsKey(ParameterName.CITY)) {
            transaction.getClient().getAddress().setCity((String) transactionMap.get(ParameterName.CITY));
        }
        if (transactionMap.containsKey(ParameterName.ZIPCODE)) {
            transaction.getClient().getAddress().setZipcode((String) transactionMap.get(ParameterName.ZIPCODE));
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClientAddress(...) DONE", null);
    }

    //TODO Make sure this is not creating a new Personl Identification
    protected PersonalIdentification fillOutPersonalIdentification(Map transactionMap, Transaction transaction) throws SQLException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutPersonalIdentification(...) ", null);

        PersonalIdentification identidication = new PersonalIdentification();
        identidication.setClient(transaction.getClient());

        if (transactionMap.containsKey(ParameterName.ID)) {
            identidication.setIdentification((String) transactionMap.get(ParameterName.ID));
        }
        if (transactionMap.containsKey(ParameterName.IDTYPE)) {
            identidication.setIdType(((IdType) transactionMap.get(ParameterName.IDTYPE)).getId());
        }

        if (transactionMap.containsKey(ParameterName.EXPIRATION_DATE_AS_DATE)) {
            identidication.setExpirationDate(((Date) transactionMap.get(ParameterName.EXPIRATION_DATE_AS_DATE)));
        }
        if (transactionMap.containsKey(ParameterName.IDFRONT) && transactionMap.get(ParameterName.IDFRONT) != null) {
            byte[] idFront = (byte[]) transactionMap.get(ParameterName.IDFRONT);
            java.sql.Blob idFrontBlob = new SerialBlob(idFront);
            identidication.setIdFront(idFrontBlob);
        }
        if (transactionMap.containsKey(ParameterName.IDBACK) && transactionMap.get(ParameterName.IDBACK) != null) {
            byte[] idBack = (byte[]) transactionMap.get(ParameterName.IDBACK);
            java.sql.Blob idBackBlob = new SerialBlob(idBack);
            identidication.setIdBack(idBackBlob);
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutPersonalIdentification(...) DONE", null);
        return identidication;
    }

    protected void fillOutCheck(Map transactionMap, Transaction transaction) throws SQLException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutCheck(...) ", null);
        Check check = new Check();

        if (transactionMap.containsKey(ParameterName.MICR)) {
            check.setMicr((String) transactionMap.get(ParameterName.MICR));
        }
        if (transactionMap.containsKey(ParameterName.CRC)) {
            check.setCrc((String) transactionMap.get(ParameterName.CRC));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_NAME)) {
            check.setMakerName((String) transactionMap.get(ParameterName.MAKER_NAME));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_ADDRESS)) {
            check.setMakerAddress((String) transactionMap.get(ParameterName.MAKER_ADDRESS));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_CITY)) {
            check.setMakerCity((String) transactionMap.get(ParameterName.MAKER_CITY));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_STATE)) {
            check.setMakerState((String) transactionMap.get(ParameterName.MAKER_STATE));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_ZIP)) {
            check.setMakerZip((String) transactionMap.get(ParameterName.MAKER_ZIP));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_PHONE)) {
            check.setMakerPhone((String) transactionMap.get(ParameterName.MAKER_PHONE));
        }
        if (transactionMap.containsKey(ParameterName.LOCATION_ID)) {
            check.setLocationId((String) transactionMap.get(ParameterName.LOCATION_ID));
        }
        if (transactionMap.containsKey(ParameterName.PAYMENTCHECK)) {
            check.setPaymentCheck((String) transactionMap.get(ParameterName.PAYMENTCHECK));
        }

        if (transactionMap.containsKey(ParameterName.CHECK_BACK) && transactionMap.get(ParameterName.CHECK_BACK) != null) {
            byte[] checkBack = (byte[]) transactionMap.get(ParameterName.CHECK_BACK);
            java.sql.Blob checkBackBlob = new SerialBlob(checkBack);
            check.setCheckBack(checkBackBlob);
        }

        if (transactionMap.containsKey(ParameterName.CHECK_FRONT) && transactionMap.get(ParameterName.CHECK_FRONT) != null) {
            byte[] checkFront = (byte[]) transactionMap.get(ParameterName.CHECK_FRONT);
            java.sql.Blob checkFrontBlob = new SerialBlob(checkFront);
            check.setCheckFront(checkFrontBlob);
        }

        check.setStatus(CheckStatus.PROCESSING.getStatus());
        check.setCreationDate(new Date());

        check.setTransaction(transaction);
        check.setClient1(transaction.getClient());
        transaction.setCheck(check);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutCheck(...) DONE", null);
    }

    protected DirexTransactionResponse getPersonalInfoFromIDReader(DirexTransactionRequest request) throws TransactionalException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Into the method getPersonalInfoFromIDReader(...)", null);

        DirexTransactionResponse dtr = new DirexTransactionResponse();
        dtr.getTransactionData().putAll(request.getTransactionData());
        try {
            if (request.getTransactionData().containsKey(ParameterName.DLDATASCAN) || request.getTransactionData().containsKey(ParameterName.DLDATASWIPE)) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] getPersonalInfoFromIDReader() contains datascan or dataswipe", null);

                Map personalInfoMap = null;
                String dlData = "";

                if (request.getTransactionData().get(ParameterName.DLDATASCAN) != null && !request.getTransactionData().get(ParameterName.DLDATASCAN).equals("")) {

                    dlData = (String) request.getTransactionData().get(ParameterName.DLDATASCAN);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] getPersonalInfoFromIDReader(...) with xmlStringfrom DLDATASCAN", null);

                } else {

                    dlData = (String) request.getTransactionData().get(ParameterName.DLDATASWIPE);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] getPersonalInfoFromIDReader(...) with xmlString from DLDATASWIPE", null);

                }

                if (dlData != null && !dlData.isEmpty()) {
                    personalInfoMap = IDScanner.parseID(CoreTransactionManager.ID_SCAN_AUTH_KEY, dlData);
                }

                if (personalInfoMap != null) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] ----------------- Printing IDScanner output map  -----------------  ", null);
                    System.out.println(Arrays.toString(personalInfoMap.entrySet().toArray()));

                    String ssn = (String) request.getTransactionData().get(ParameterName.SSN);
                    dtr.getTransactionData().put(ParameterName.IDTYPE, CoreTransactionUtil.getIdTypeFromId(ssn));
                    dtr.getTransactionData().put(ParameterName.ID, personalInfoMap.get(ParameterName.ID));
                    fixPersonInfoName(personalInfoMap);
                    dtr.getTransactionData().putAll(personalInfoMap);
                    dtr.setResultCode(ResultCode.SUCCESS);
                    dtr.setResultMessage(ResultMessage.SUCCESS.getMessage());
                    dtr.setTerminalResultMessage(ResultMessage.SUCCESS.getMessage());
                } else {
                    throw new TransactionalException(ResultCode.CORE_ERROR, TransactionType.PERSONAL_INFO, "IDReader failed");
                }

            } else {
                throw new TransactionalException(ResultCode.CORE_ERROR, TransactionType.PERSONAL_INFO, " The request doesn't contain DLDATASCAN or DLDATASWIPE ");
            }

        } catch (Exception e) {
            throw new TransactionalException(ResultCode.CORE_ERROR, TransactionType.PERSONAL_INFO, e);
        }

        return dtr;
    }

    protected static void fixPersonInfoName(Map<ParameterName, String> personalInfo) {

        String name = personalInfo.get(ParameterName.FIRST_NAME).trim();
        String middleName = "";
        if (personalInfo.containsKey(ParameterName.MIDDLE_NAME) && personalInfo.get(ParameterName.MIDDLE_NAME) != null) {
            middleName = personalInfo.get(ParameterName.MIDDLE_NAME).trim();
        }
        String lastName = personalInfo.get(ParameterName.LAST_NAME).trim();
        String[] aux;
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fixPersonalInfoName with name: " + name, null);
        if (name != null && name.contains(" ")) {
            aux = name.split(" ");
            name = aux[0];
            middleName = aux[1];
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fixPersonalInfoName fixed with name: " + name, null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fixPersonalInfoName fixed with Middle name: " + middleName, null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fixPersonalInfoName fixed with last name: " + lastName, null);

        if (name != null) {
            personalInfo.put(ParameterName.FIRST_NAME, name);
        }
        if (name != null) {
            personalInfo.put(ParameterName.MIDDLE_NAME, middleName);
        }
        if (name != null) {
            personalInfo.put(ParameterName.LAST_NAME, lastName);
        }
    }

    protected void processPersonalInfo(Transaction transaction, DirexTransactionRequest request, Map personalInfoRequestMap) throws SQLException, Exception {
        //------ CREATE PERSONAL INFO SUBTRANSACTION ------
        addSuccessfulSubTransaction(transaction, TransactionType.PERSONAL_INFO);

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Persist personal Info", null);
        //PERSIST PERSONAL INFO

        fixPersonInfoName(request.getTransactionData());
        fillOutClient(request.getTransactionData(), transaction);
        fillOutClientAddress(request.getTransactionData(), transaction);
        PersonalIdentification identification = fillOutPersonalIdentification(request.getTransactionData(), transaction);

        if (transaction.getOperation().equals("01")) {
            fillOutCheck(request.getTransactionData(), transaction);
        }

        if (personalInfoRequestMap.containsKey(ParameterName.IDCOUNTRY) || personalInfoRequestMap.containsKey(ParameterName.IDSTATE)
                || personalInfoRequestMap.containsKey(ParameterName.COUNTRY) || personalInfoRequestMap.containsKey(ParameterName.STATE)) {
            try {
                HibernateUtil.beginTransaction();

                if (identification.getIdType() != null) {
                    personalIdentificationManager.removeByClientAndType(transaction.getClient().getId(), identification.getIdType(), identification.getId());
                }

                request.getTransactionData().put(ParameterName.IDCOUNTRY, EnumCountry.EUA.getCode() + "");

                if (personalInfoRequestMap.containsKey(ParameterName.IDSTATE)) {
                    String idStateAbbreviation = (String) personalInfoRequestMap.get(ParameterName.IDSTATE);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] For IDSTATE StateAbbreviation >>> = " + idStateAbbreviation, null);
                    State statee = stateManager.getByAbbreviation(idStateAbbreviation);

                    if (statee != null) {
                        request.getTransactionData().put(ParameterName.IDSTATE, statee.getCode() + "");
                        identification.setState(statee);
                    } else {
                        request.getTransactionData().put(ParameterName.IDSTATE, EnumState.FL.getId() + "");
                    }

                    try {
                        request.getTransactionData().put(ParameterName.OEIDSTATE, NomState.valueOf(idStateAbbreviation).getId() + "");
                    } catch (Exception e) {
                        request.getTransactionData().put(ParameterName.OEIDSTATE, NomState.FL.getId());

                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] Failed to get state by abbreviation : " + idStateAbbreviation + " sending FL instead.", null);
                        e.printStackTrace();
                    }
                }

                request.getTransactionData().put(ParameterName.COUNTRY, EnumCountry.EUA.getCode() + "");

                if (personalInfoRequestMap.containsKey(ParameterName.STATE)) {
                    String stateAbbreviation = (String) personalInfoRequestMap.get(ParameterName.STATE);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] For STATE StateAbbreviation >>> = " + stateAbbreviation, null);
                    State statee = stateManager.getByAbbreviation(stateAbbreviation);

                    if (statee != null) {
                        request.getTransactionData().put(ParameterName.STATE, statee.getCode() + "");
                        transaction.getClient().getAddress().setState(statee);
                    } else {
                        request.getTransactionData().put(ParameterName.STATE, EnumState.FL.getId() + "");
                    }
                }

                if (request.getTransactionData().containsKey(ParameterName.PHONE)) {
                    String cell_area_code = (String) request.getTransactionData().get(ParameterName.PHONE);
                    request.getTransactionData().put(ParameterName.CELL_PHONE_AREA, cell_area_code.substring(0, 3));

                    String cell_phone = (String) request.getTransactionData().get(ParameterName.PHONE);
                    request.getTransactionData().put(ParameterName.CELL_PHONE, cell_phone.substring(3));
                }

                HibernateUtil.commitTransaction();
            } catch (Exception e) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] Error 2 ", e.getMessage());
                HibernateUtil.rollbackTransaction();

                throw new TransactionalException(ResultCode.CORE_ERROR, TransactionType.PERSONAL_INFO, e);
            }
        }

        identification.setClient(transaction.getClient());
        Set set = new HashSet();
        set.add(identification);
        transaction.getClient().setData_SD(set);
    }

}
