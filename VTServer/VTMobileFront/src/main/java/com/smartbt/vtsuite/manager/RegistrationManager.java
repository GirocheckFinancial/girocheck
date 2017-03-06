/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.PasswordUtil;
import com.smartbt.girocheck.servercommon.dao.ClientDAO;
import com.smartbt.girocheck.servercommon.dao.CreditCardDAO;
import com.smartbt.girocheck.servercommon.dao.MobileClientDao;
import com.smartbt.girocheck.servercommon.display.mobile.MobileClientDisplay;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.ValidationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sreekanth
 */
@Service
public class RegistrationManager {

    private TransactionManager txnManager = new TransactionManager();

    protected static RegistrationManager _this;

    public static RegistrationManager get() {
        if (_this == null) {
            _this = new RegistrationManager();
        }
        return _this;
    }

    public ResponseData register(String username, String password, String ssn, String email, String phone, String cardNumber) {

        ResponseData response = new ResponseData();
        Client client = null;
        MobileClient mobileClient = null;

        DirexTransactionResponse technicardResponse;
        Map map = new HashMap();

        if (ssn != null && ssn.equals("0")) { //Mock Response object
            MobileClientDisplay mobileRegistration = new MobileClientDisplay();
            mobileRegistration.setClientId(1);
            mobileRegistration.setBalance("120.00");
            mobileRegistration.setToken(Utils.generateToken());
            Map data = new HashMap();
            data.put("details", mobileRegistration);
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(data);
        }
        try {
            //Find client by SSN
            if (ssn != null) {
                client = ClientDAO.get().getClientBySSN(ssn);
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
            map.put(ParameterName.IDTYPE, client.getId());//Not sure what data need to pass
            map.put(ParameterName.SSN, ssn);
            map.put(ParameterName.CARD_NUMBER, cardNumber);
            map.put(ParameterName.REQUEST_ID, client.getId());//Not sure what data need to pass

            direxTransactionRequest.setTransactionData(map);
            direxTransactionRequest.setCorrelation(ssn);//Not sure what data need to pass
            direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);
            System.out.println("[TransactionManager:transactionHistory] - CLIENT_ID:- " + client.getId());

            technicardResponse = TecnicardHostManager.get().processTransaction(direxTransactionRequest);

            if (technicardResponse != null && !technicardResponse.wasApproved()) {
                CreditCard card = null;
                card = CreditCardDAO.get().getCardByClientId(client.getId());

                // to crete new card if card does not exists
                if (card == null) {
                    card = createCard(cardNumber, client);
                }

                // to crete new mobile client
                mobileClient = createMobileClient(username, password,card, client);

                client.setEmail(email);
                client.setTelephone(phone);
                ClientDAO.get().saveOrUpdate(client);

                String token = Utils.generateToken();
                //consume balance enquiry
                String clientId = String.valueOf(mobileClient.getClient().getId());
                String balance = txnManager.balanceInquiry(clientId, token);

                // To send details to Mobile application
                Map data = new HashMap();
                MobileClientDisplay mobileRegistration = new MobileClientDisplay();
                mobileRegistration.setClientId(mobileClient.getClient().getId());
                mobileRegistration.setBalance(balance);
                mobileRegistration.setToken(token);

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
        CreditCardDAO.get().saveOrUpdate(card);
        return card;
    }

    private MobileClient createMobileClient(String username, String password, CreditCard card, Client client) throws ValidationException, NoSuchAlgorithmException {
        MobileClient mobileClient = new MobileClient();
        mobileClient.setCard(card);
        mobileClient.setClient(client);
        mobileClient.setUserName(username);        
        String encyptedPassword = PasswordUtil.encryptPassword(password);
        mobileClient.setPassword(encyptedPassword);        
        mobileClient.setDeviceType("device");//need to get device type
        mobileClient.setRegistrationDate(new Date());

        MobileClientDao.get().saveOrUpdate(mobileClient);
        return mobileClient;
    }

}
