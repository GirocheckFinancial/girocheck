/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.ClientDAO;
import com.smartbt.girocheck.servercommon.dao.VTSessionDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.mobile.MobileRegistration;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.model.VTSession;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.PasswordUtil;
import com.smartbt.vtsuite.dao.CardDao;
import com.smartbt.vtsuite.dao.MobileClientDao;
import com.smartbt.vtsuite.mock.MockFrontMobileBusinessLogic;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.ValidationException;

/**
 *
 * @author suresh
 */
public class RegistrationManager {

    protected static ClientDAO clientDao;
    protected static CardDao cardDao;
    protected static MobileClientDao mobileClientDao;
    private TransactionManager txnManager = new TransactionManager();
    protected static RegistrationManager _this;

    public static RegistrationManager get() {
        if (_this == null) {
            _this = new RegistrationManager();
        }
        return _this;
    }

    public ResponseData register(LinkedHashMap params) {

        ResponseData response = new ResponseData();
        Client client = null;
        MobileClient mobileClient = null;

        DirexTransactionResponse technicardResponse;
        Map map = new HashMap();

        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String ssn = (String) params.get("ssn");
        String deviceId = (String) params.get("deviceId");
        String email = (String) params.get("email");
        String phone = (String) params.get("phone");
        String pin = (String) params.get("pin");
        String cardNumber = (String) params.get("cardNumber");

        if (ssn != null && ssn.equals("0")) {
            Map data = MockFrontMobileBusinessLogic.get().processRegistration();
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(data);
        }
        try {
            //Find client by SSN
            if (ssn != null) {
                client = clientDao.getClientBySSN(ssn);
                if (client == null) {
                    response.setStatus(Constants.LOGIN_FAILED);
                    response.setStatusMessage(VTSuiteMessages.LOGIN_FAILED);
                    return response;
                }
            } else {
                response.setStatus(Constants.LOGIN_FAILED);
                response.setStatusMessage(VTSuiteMessages.LOGIN_FAILED);
                return response;
            }

            //Consume Tecnicard's cardHolderValidation

            DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[RegistrationManager] processTransaction " + client.getId(), null);

            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);
            map.put(ParameterName.IDTYPE, client.getId());//Not sure what dta need to pass
            map.put(ParameterName.SSN, ssn);
            map.put(ParameterName.CARD_NUMBER, cardNumber);
            map.put(ParameterName.REQUEST_ID, client.getId());//Not sure what dta need to pass

            direxTransactionRequest.setTransactionData(map);
            direxTransactionRequest.setCorrelation(ssn);//Not sure what dta need to pass
            direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);
            System.out.println("[TransactionManager:transactionHistory] - CLIENT_ID:- " + client.getId());

            technicardResponse = FrontMobileBusinessLogic.get().process(direxTransactionRequest);

            if (technicardResponse != null && !technicardResponse.wasApproved()) {
                CreditCard card = null;
                card = cardDao.getCardNumberByClientId(client.getId());

                // to crete new card if card does not exists
                if (card == null) {
                    card = createCard(cardNumber, client);
                }

                // to crete new mobile client
                mobileClient = createMobileClient(username, password, ssn, deviceId, pin, card, client);

                client.setEmail(email);
                client.setTelephone(phone);
                clientDao.saveOrUpdate(client);

                // to create session
                VTSession userSession = createSession(mobileClient);
                if (userSession != null) {
                    response.setStatus(Constants.CODE_SUCCESS);
                    response.setStatusMessage(VTSuiteMessages.SUCCESS);
                } else {
                    failLogin(response);
                    return response;
                }
                
                //consume balance enquiry
                Map balanceMap = txnManager.balanceInquiry(mobileClient.getClient().getId());
                
                // To send details to Mobile application
                Map data = new HashMap();
                MobileRegistration mobileRegistration = new MobileRegistration();
                mobileRegistration.setClientId("1");
                mobileRegistration.setBalance((String)balanceMap.get(ParameterName.BALANCE));
                mobileRegistration.setToken(userSession.getToken());                
               
                data.put("details", mobileRegistration);
                response.setStatus(Constants.CODE_SUCCESS);
                response.setStatusMessage(VTSuiteMessages.SUCCESS);
                response.setData(data);
                
            } else {
                response.setStatus(Constants.CARD_NOT_PERSONALIZED);
                response.setStatusMessage(VTSuiteMessages.CARD_NOT_PERSONALIZED);
                return response;
            }
        } catch (Exception e) {
            response.setStatus(Constants.LOGIN_FAILED);
            response.setStatusMessage(VTSuiteMessages.LOGIN_FAILED);
            e.printStackTrace();
        }
        return response;
    }

    private CreditCard createCard(String cardNumber, Client client) {
        CreditCard card = new CreditCard();
        card.setCardNumber(cardNumber);
        String maskCardNumber = "";
        if (cardNumber.length() >= 16) {
            maskCardNumber = cardNumber.substring(0, 4) + "********" + cardNumber.substring(12);
        } else {
            if (cardNumber.length() >= 4) {
                maskCardNumber = cardNumber.substring(0, 4) + "********";
            }
        }
        card.setMaskCardNumber(maskCardNumber);
        card.setMerchant(null);//Need to know what merhant need to map here;
        card.setClient(client);
        cardDao.saveOrUpdate(card);
        return card;
    }

    private MobileClient createMobileClient(String username, String password, String ssn, String deviceId, String pin, CreditCard card, Client client) throws ValidationException, NoSuchAlgorithmException {
        MobileClient mobileClient = new MobileClient();
        mobileClient.setCard(card);
        mobileClient.setClient(client);
        mobileClient.setUserName(username);
        String generatedPassword = PasswordUtil.generatePassword(8);
        String encyptedPassword = PasswordUtil.encryptPassword(generatedPassword);
        mobileClient.setPassword(encyptedPassword); 
        mobileClient.setDeviceType("device");//need to get device type
        mobileClient.setRegistrationDate(new Date());

        mobileClientDao.saveOrUpdate(mobileClient);
        return mobileClient;
    }

    private void failLogin(ResponseData response) {
        failLogin(response, VTSuiteMessages.INVALID_LOGIN_CREDENTIALS);
    }

    private void failLogin(ResponseData response, String message) {
        response.setStatus(Constants.CODE_INVALID_USER);
        response.setStatusMessage(message);
    }

    private VTSession createSession(MobileClient user) {
     //   VTSession session = VTSessionDAO.get().saveOrUpdateSession(user);
        return null;
    }
}
