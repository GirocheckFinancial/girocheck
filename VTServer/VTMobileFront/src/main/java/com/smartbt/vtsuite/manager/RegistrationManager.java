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
import com.smartbt.girocheck.servercommon.utils.PasswordUtil;
import com.smartbt.girocheck.servercommon.dao.ClientDAO;
import com.smartbt.girocheck.servercommon.dao.CreditCardDAO;
import com.smartbt.girocheck.servercommon.dao.MobileClientDao;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.vtsuite.util.MobileValidationException;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sreekanth
 */
@Service
public class RegistrationManager {

    @Autowired
    private TransactionManager transactionManager;

    protected static RegistrationManager _this;

    public static RegistrationManager get() {
        if (_this == null) {
            _this = new RegistrationManager();
        }
        return _this;
    }

    public ResponseData register(String username, String password, String ssn, String email, String phone, String cardNumber, String token) {

        ResponseData response = ResponseData.OK();
        MobileClient mobileClient = null;

        DirexTransactionResponse technicardResponse;
        Map map = new HashMap();

        try {
            Client client = validateAndGetClient(ssn, cardNumber, username, password);
            //Consume Tecnicard's cardHolderValidation
            DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();

            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);
            map.put(ParameterName.SSN, ssn);
            map.put(ParameterName.CARD_NUMBER, cardNumber);
            map.put(ParameterName.REQUEST_ID, token);

            direxTransactionRequest.setTransactionData(map);
            direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);

            System.out.println("[FrontMobile.RegistrationManager] Calling TECNICARD_CARD_HOLDER_VALIDATION...");
            technicardResponse = TecnicardHostManager.get().processTransaction(direxTransactionRequest);

            if (technicardResponse != null && technicardResponse.wasApproved()) {
                System.out.println("[FrontMobile.RegistrationManager] Loading card by number: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
                CreditCard card = CreditCardDAO.get().getCard(cardNumber);

                // to crete new card if card does not exists
                if (card == null) {
                    System.out.println("[FrontMobile.RegistrationManager] Card didn't exist, creating new card...");
                    card = createCard(cardNumber, client);
                } else {
                    System.out.println("[FrontMobile.RegistrationManager] Card exist...");

                    System.out.println("card.getClient().getFirstName() = " + card.getClient().getFirstName());
                    System.out.println("card.getClient().getSsn() = " + card.getClient().getSsn());

                    if (!ssn.equals(card.getClient().getSsn())) {
                        response.setStatusMessage(VTSuiteMessages.CARD_BELONG_TO_ANOTHER_CLIENT);
                        throw new MobileValidationException(Constants.CARD_BELONG_TO_ANOTHER_CLIENT, VTSuiteMessages.CARD_BELONG_TO_ANOTHER_CLIENT);
                    }
                }

                System.out.println("[FrontMobile.RegistrationManager] Creating Mobile Client...");
                mobileClient = createMobileClient(username, password,card, client);

                client.setEmail(email);
                client.setTelephone(phone);
                System.out.println("[FrontMobile.RegistrationManager] Saving Client...");
                ClientDAO.get().saveOrUpdate(client);

                //consume balance enquiry 
                System.out.println("[FrontMobile.RegistrationManager] calling balanceInquiry");
                String balance = transactionManager.balanceInquiry(cardNumber, token);

                // To send details to Mobile application
                Map data = new HashMap();
                data.put("clientId", mobileClient.getId());
                data.put("clientName", mobileClient.getClient().getFirstName());
                data.put("balance", balance);
                data.put("token", token);

                response.setData(data);

            } else {
                throw new MobileValidationException(Constants.CARD_NOT_PERSONALIZED, VTSuiteMessages.CARD_NOT_PERSONALIZED);
            }
        } catch (MobileValidationException mbe) {
            System.out.println("MobileValidationException:: " + mbe.getResponse().getStatusMessage());
            mbe.printStackTrace();
            response = mbe.getResponse();
        } catch (Exception e) {
            System.out.println("[FrontMobile.RegistrationManager] LOGIN_FAILED");
            response.setStatus(Constants.LOGIN_FAILED);
            response.setStatusMessage(VTSuiteMessages.LOGIN_FAILED);
            e.printStackTrace();
        }

        System.out.println("[FrontMobile.RegistrationManager] return response.");
        return response;
    }

    public ResponseData replaceCard(String clientId, String cardNumber, String token) {

        ResponseData response = ResponseData.OK();
        MobileClient mobileClient = null;

        DirexTransactionResponse technicardResponse;
        Map map = new HashMap();

        try {
            Client client = validateAndGetClient(clientId, cardNumber);
            //Consume Tecnicard's cardHolderValidation
            DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();

            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);
            map.put(ParameterName.SSN, client.getSsn());
            map.put(ParameterName.CARD_NUMBER, cardNumber);
            map.put(ParameterName.REQUEST_ID, token);

            direxTransactionRequest.setTransactionData(map);
            direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);

            System.out.println("[FrontMobile.RegistrationManager] Calling TECNICARD_CARD_HOLDER_VALIDATION...");
            technicardResponse = TecnicardHostManager.get().processTransaction(direxTransactionRequest);

            if (technicardResponse != null && technicardResponse.wasApproved()) {
                System.out.println("[FrontMobile.RegistrationManager] Loading card by number: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
                CreditCard card = CreditCardDAO.get().getCard(cardNumber);

                // to create new card if card does not exists
                if (card == null) {
                    System.out.println("[FrontMobile.RegistrationManager] Card didn't exist, creating new card...");
                    card = createCard(cardNumber, client);
                } else {
                    System.out.println("[FrontMobile.RegistrationManager] Card exist...");

                    System.out.println("card.getClient().getFirstName() = " + card.getClient().getFirstName());
                    System.out.println("card.getClient().getSsn() = " + card.getClient().getSsn());

                    if (!client.getSsn().equals(card.getClient().getSsn())) {
                        response.setStatusMessage(VTSuiteMessages.CARD_BELONG_TO_ANOTHER_CLIENT);
                        throw new MobileValidationException(Constants.CARD_BELONG_TO_ANOTHER_CLIENT, VTSuiteMessages.CARD_BELONG_TO_ANOTHER_CLIENT);
                    }

                }

                System.out.println("[FrontMobile.RegistrationManager] getting Mobile Client and associating card/client...");
                mobileClient = MobileClientDao.get().getMobileClientId(client.getId());
                if (mobileClient != null) {
                    mobileClient.setCard(card);
                    mobileClient.setClient(client);
                    System.out.println("[FrontMobile.RegistrationManager] Saving MobileClient...");
                    MobileClientDao.get().saveOrUpdate(mobileClient);
                }else{
                    response.setStatusMessage(VTSuiteMessages.MOBILE_CLIENT_NOT_EXIST);
                    throw new MobileValidationException(Constants.MOBILE_CLIENT_NOT_EXIST, VTSuiteMessages.MOBILE_CLIENT_NOT_EXIST);
                }

                response.setStatus(Constants.SUCCESS);
                response.setStatusMessage(VTSuiteMessages.SUCCESS);

            } else {
                throw new MobileValidationException(Constants.CARD_NOT_PERSONALIZED, VTSuiteMessages.CARD_NOT_PERSONALIZED);
            }
        } catch (MobileValidationException mbe) {
            System.out.println("MobileValidationException:: " + mbe.getResponse().getStatusMessage());
            mbe.printStackTrace();
            response = mbe.getResponse();
        } catch (Exception e) {
            System.out.println("[FrontMobile.RegistrationManager] LOGIN_FAILED");
            response.setStatus(Constants.LOGIN_FAILED);
            response.setStatusMessage(VTSuiteMessages.LOGIN_FAILED);
            e.printStackTrace();
        }

        System.out.println("[FrontMobile.RegistrationManager] return response.");
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
        card.setMerchant(getMerchantFromExistentCard(client));
        card.setClient(client);
        CreditCardDAO.get().saveOrUpdate(card);
        return card;
    }

    private Merchant getMerchantFromExistentCard(Client client) {
        CreditCard existentCard = CreditCardDAO.get().getCardByClientId(client.getId());

        if (existentCard != null) {
            return existentCard.getMerchant();
        }
        return null;
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

    private Client validateAndGetClient(String ssn, String cardNumber, String username, String password) throws MobileValidationException {

        if (ssn == null || ssn.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, VTSuiteMessages.REQUIRED_FIELD + "ssn");
        }

        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, VTSuiteMessages.REQUIRED_FIELD + "Card Number");
        }

        if (username == null || username.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, VTSuiteMessages.REQUIRED_FIELD + "UserName");
        }

        if (password == null || password.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, VTSuiteMessages.REQUIRED_FIELD + "Password");
        }       

        System.out.println("[FrontMobile.RegistrationManager] Validating if existMobileClientBySSN:");
        if (MobileClientDao.get().existMobileClientBySSN(ssn)) {
            throw new MobileValidationException(Constants.MOBILE_CLIENT_ALREADY_EXIST, VTSuiteMessages.MOBILE_CLIENT_ALREADY_EXIST);
        }

        System.out.println("[FrontMobile.RegistrationManager] Validating if existMobileClientByUsername:");
        if (MobileClientDao.get().existMobileClientByUsername(username)) {
            throw new MobileValidationException(Constants.USERNAME_IN_USE, VTSuiteMessages.USERNAME_IN_USE);
        }

        System.out.println("[FrontMobile.RegistrationManager] Validating if existMobileClientByUsername:");
        if (MobileClientDao.get().existMobileAssociatedToCard(cardNumber)) {
            throw new MobileValidationException(Constants.CARD_BELONG_TO_ANOTHER_CLIENT, VTSuiteMessages.CARD_BELONG_TO_ANOTHER_CLIENT);
        }

        System.out.println("[FrontMobile.RegistrationManager] Loading client by ssn : *** ** " + ssn.substring(ssn.length() - 4));
        Client client = ClientDAO.get().getClientBySSN(ssn);

        if (client == null) {
            throw new MobileValidationException(Constants.CLIENT_DOES_NOT_EXIST, VTSuiteMessages.CLIENT_DOES_NOT_EXIST);
        } else {
            System.out.println("[FrontMobile.RegistrationManager] Found client: " + client.getFirstName());
        }
        return client;
    }

    private Client validateAndGetClient(String clientId, String cardNumber) throws MobileValidationException {

        if (clientId == null || clientId.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, VTSuiteMessages.REQUIRED_FIELD + "clientId");
        }

        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, VTSuiteMessages.REQUIRED_FIELD + "Card Number");
        }

        if (MobileClientDao.get().existMobileAssociatedToCard(cardNumber)) {
            throw new MobileValidationException(Constants.CARD_BELONG_TO_ANOTHER_CLIENT, VTSuiteMessages.CARD_BELONG_TO_ANOTHER_CLIENT);
        }

        System.out.println("[FrontMobile.RegistrationManager] Loading client by clientId : " + clientId);
        int id = Integer.parseInt(clientId);
        Client client = ClientDAO.get().findById(id);

        if (client == null) {
            throw new MobileValidationException(Constants.CLIENT_DOES_NOT_EXIST, VTSuiteMessages.CLIENT_DOES_NOT_EXIST);
        } else {
            System.out.println("[FrontMobile.RegistrationManager] Found client: " + client.getFirstName());
        }
        return client;
    }

}
