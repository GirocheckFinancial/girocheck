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
import com.smartbt.girocheck.servercommon.email.GoogleMail;
import com.smartbt.girocheck.servercommon.enums.EmailName;
import com.smartbt.girocheck.servercommon.manager.EmailManager;
import com.smartbt.girocheck.servercommon.model.Email;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.utils.SMSUtils;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.vtsuite.util.MobileMessage;
import com.smartbt.vtsuite.util.MobileValidationException;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sreekanth
 */
@Service
@Transactional
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

    public ResponseData register(String username, String password, String ssn, String email, String phone, String cardNumber, String token, String lang) {

        ResponseData response = ResponseData.OK();
        MobileClient mobileClient = null;

        DirexTransactionResponse technicardResponse;
        Map map = new HashMap();

        try {
            Client client = validateAndGetClient(ssn, cardNumber, username, password, lang);
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
                        response.setStatusMessage(MobileMessage.CARD_BELONG_TO_ANOTHER_CLIENT.get(lang));
                        throw new MobileValidationException(Constants.CARD_BELONG_TO_ANOTHER_CLIENT, MobileMessage.CARD_BELONG_TO_ANOTHER_CLIENT.get(lang));
                    }
                }

                System.out.println("[FrontMobile.RegistrationManager] Creating Mobile Client...");
                mobileClient = createMobileClient(username, password, card, client);

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
                data.put("clientEmail", client.getEmail());
                data.put("clientPhone", client.getTelephone());
                data.put("mobileClientUserName", mobileClient.getUserName());
                data.put("balance", balance);
                data.put("token", token);

                response.setData(data);

            } else {
                throw new MobileValidationException(Constants.CARD_NOT_PERSONALIZED, MobileMessage.CARD_NOT_PERSONALIZED.get(lang));
            }
        } catch (MobileValidationException mbe) {
            System.out.println("MobileValidationException:: " + mbe.getResponse().getStatusMessage());
            mbe.printStackTrace();
            response = mbe.getResponse();
        } catch (Exception e) {
            System.out.println("[FrontMobile.RegistrationManager] LOGIN_FAILED");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(MobileMessage.ERROR_GENERAL.get(lang));
            e.printStackTrace();
        }

        System.out.println("[FrontMobile.RegistrationManager] return response.");
        return response;
    }

    public ResponseData replaceCard(String clientId, String cardNumber, String token, String lang) {

        ResponseData response = ResponseData.OK();

        DirexTransactionResponse technicardResponse;
        Map map = new HashMap();

        try {
            MobileClient mobileClient = validateAndGetClient(clientId, cardNumber, lang);
            //Consume Tecnicard's cardHolderValidation
            DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();

            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);
            map.put(ParameterName.SSN, mobileClient.getClient().getSsn());
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
                    card = createCard(cardNumber, mobileClient.getClient());
                } else {
                    System.out.println("[FrontMobile.RegistrationManager] Card exist...");

                    System.out.println("card.getClient().getFirstName() = " + card.getClient().getFirstName());
                    System.out.println("card.getClient().getSsn() = " + card.getClient().getSsn());

                    if (!mobileClient.getClient().getSsn().equals(card.getClient().getSsn())) {
                        throw new MobileValidationException(Constants.CARD_BELONG_TO_ANOTHER_CLIENT, MobileMessage.CARD_BELONG_TO_ANOTHER_CLIENT.get(lang));
                    }

                }

                mobileClient.setCard(card);
                System.out.println("[FrontMobile.RegistrationManager] Saving MobileClient...");
                MobileClientDao.get().saveOrUpdate(mobileClient);

                response.setStatus(Constants.SUCCESS);
                response.setStatusMessage(VTSuiteMessages.SUCCESS);

            } else {
                throw new MobileValidationException(Constants.CARD_NOT_PERSONALIZED, MobileMessage.CARD_NOT_PERSONALIZED.get(lang));
            }
        } catch (MobileValidationException mbe) {
            System.out.println("MobileValidationException:: " + mbe.getResponse().getStatusMessage());
            mbe.printStackTrace();
            response = mbe.getResponse();
        } catch (Exception e) {
            System.out.println("[FrontMobile.RegistrationManager] LOGIN_FAILED");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(MobileMessage.ERROR_GENERAL.get(lang));
            e.printStackTrace();
        }

        System.out.println("[FrontMobile.RegistrationManager] return response.");
        return response;
    }

    public ResponseData updateProfile(String clientId, String username, String email, String phone, String password, String oldPassword, String token, String lang) {

        ResponseData response = ResponseData.OK();

        try {
            MobileClient mobileClient = validateDataAndGetClient(clientId, username, email, phone,oldPassword, lang);

            mobileClient.setUserName(username);
            if (password != null && !password.isEmpty()) {
                String encyptedPassword = PasswordUtil.encryptPassword(password);
                mobileClient.setPassword(encyptedPassword);
            }

            System.out.println("[FrontMobile.RegistrationManager] updating Client information(email,phone)...");
            mobileClient.getClient().setEmail(email);
            mobileClient.getClient().setTelephone(phone);
            MobileClientDao.get().saveOrUpdate(mobileClient);

            response.setStatus(Constants.SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);

        } catch (MobileValidationException mbe) {
            System.out.println("MobileValidationException:: " + mbe.getResponse().getStatusMessage());
            mbe.printStackTrace();
            response = mbe.getResponse();
        } catch (Exception e) {
            System.out.println("[FrontMobile.RegistrationManager] LOGIN_FAILED");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(MobileMessage.ERROR_GENERAL.get(lang));
            e.printStackTrace();
        }

        System.out.println("[FrontMobile.RegistrationManager] return response.");
        return response;
    }

    public ResponseData forgotPassword(String maskSSN, String cardNumber, String sendBy, String code, String token, String lang) {

        ResponseData response = ResponseData.OK();

        try {
            MobileClient mobileClient = validateDataAndGetClient(maskSSN, cardNumber, sendBy, lang);

            if (code == null || code.isEmpty()) {
                System.out.println("[FrontMobile.RegistrationManager] Code is empty...");
                String forgotPwdKey = Utils.generateRandomNumber(6);
                mobileClient.setForgotPasswordKey(forgotPwdKey);
                mobileClient.setKeyExpirationTime(new Date());

                try {
                    sendEmailOrSMSNotification(mobileClient, sendBy,lang);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MobileValidationException(Constants.COULD_NOT_SEND_ACCESS_CODE, MobileMessage.COULD_NOT_SEND_ACCESS_CODE.get(lang));
                }

                MobileClientDao.get().saveOrUpdate(mobileClient);
            } else {
                System.out.println("[FrontMobile.RegistrationManager] Code exists...Code: " + code);
                if (!code.equals(mobileClient.getForgotPasswordKey())) {
                    throw new MobileValidationException(Constants.FORGOT_PASSWORD_KEY_MISMATCH, MobileMessage.FORGOT_PASSWORD_KEY_MISMATCH.get(lang));
                }

                Date keyExpirationTime = mobileClient.getKeyExpirationTime();
                Date now = new Date();
                System.out.println("[FrontMobile.RegistrationManager] Keyexpriration time: " + keyExpirationTime.getTime());
                System.out.println("[FrontMobile.RegistrationManager] now time: " + now.getTime());
                if (now.getTime() - keyExpirationTime.getTime() > 30 * 60 * 1000) {
                    System.out.println("[FrontMobile.RegistrationManager] difference is more than 30 minutes ");
                    throw new MobileValidationException(Constants.FORGOT_PASSWORD_KEY_EXPIRED, MobileMessage.FORGOT_PASSWORD_KEY_EXPIRED.get(lang));
                }

                //consume balance enquiry 
                System.out.println("[FrontMobile.RegistrationManager] calling balanceInquiry");
                String balance = transactionManager.balanceInquiry(cardNumber, token);

                // To send details to Mobile application
                Map data = new HashMap();
                data.put("clientId", mobileClient.getId());
                data.put("clientName", mobileClient.getClient().getFirstName());
                data.put("clientEmail", mobileClient.getClient().getEmail());
                data.put("clientPhone", mobileClient.getClient().getTelephone());
                data.put("mobileClientUserName", mobileClient.getUserName());
                data.put("balance", balance);
                data.put("token", token);
                response.setData(data);
            }

            response.setStatus(Constants.SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);

        } catch (MobileValidationException mbe) {
            System.out.println("MobileValidationException:: " + mbe.getResponse().getStatusMessage());
            mbe.printStackTrace();
            response = mbe.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[FrontMobile.RegistrationManager] LOGIN_FAILED");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(MobileMessage.ERROR_GENERAL.get(lang));
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
        System.out.println("maskCardNumber = " + maskCardNumber);
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

    private Client validateAndGetClient(String ssn, String cardNumber, String username, String password, String lang) throws MobileValidationException {

        if (ssn == null || ssn.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "ssn");
        }

        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "Card Number");
        }

        if (username == null || username.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "UserName");
        }

        if (password == null || password.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "Password");
        }

        System.out.println("[FrontMobile.RegistrationManager] Validating if existMobileClientBySSN:");
        if (MobileClientDao.get().existMobileClientBySSN(ssn)) {
            throw new MobileValidationException(Constants.MOBILE_CLIENT_ALREADY_EXIST, MobileMessage.MOBILE_CLIENT_ALREADY_EXIST.get(lang));
        }

        System.out.println("[FrontMobile.RegistrationManager] Validating if existMobileClientByUsername:");
        if (MobileClientDao.get().existMobileClientByUsername(username)) {
            throw new MobileValidationException(Constants.USERNAME_IN_USE, MobileMessage.USERNAME_IN_USE.get(lang));
        }

        System.out.println("[FrontMobile.RegistrationManager] Validating if existMobileClientByUsername:");
        if (MobileClientDao.get().existMobileAssociatedToCard(cardNumber)) {
            throw new MobileValidationException(Constants.CARD_BELONG_TO_ANOTHER_CLIENT, MobileMessage.CARD_BELONG_TO_ANOTHER_CLIENT.get(lang));
        }

        System.out.println("[FrontMobile.RegistrationManager] Loading client by ssn : *** ** " + ssn.substring(ssn.length() - 4));
        Client client = ClientDAO.get().getClientBySSN(ssn);

        if (client == null) {
            throw new MobileValidationException(Constants.CLIENT_DOES_NOT_EXIST, MobileMessage.CLIENT_DOES_NOT_EXIST.get(lang));
        } else {
            System.out.println("[FrontMobile.RegistrationManager] Found client: " + client.getFirstName());
        }
        return client;
    }

    private MobileClient validateAndGetClient(String clientId, String cardNumber, String lang) throws MobileValidationException {

        if (clientId == null || clientId.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "clientId");
        }

        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "Card Number");
        }

        if (MobileClientDao.get().existMobileAssociatedToCard(cardNumber)) {
            throw new MobileValidationException(Constants.CARD_BELONG_TO_ANOTHER_CLIENT, MobileMessage.CARD_BELONG_TO_ANOTHER_CLIENT.get(lang));
        }

        System.out.println("[FrontMobile.RegistrationManager] Loading client by clientId : " + clientId);
        int id = Integer.parseInt(clientId);
        MobileClient mobileClient = MobileClientDao.get().getMobileClientById(id);

        if (mobileClient == null) {
            throw new MobileValidationException(Constants.CLIENT_DOES_NOT_EXIST, MobileMessage.CLIENT_DOES_NOT_EXIST.get(lang));
        }
        return mobileClient;
    }

    private MobileClient validateDataAndGetClient(String maskSSN, String cardNumber, String sendBy, String lang) throws MobileValidationException {

        if (maskSSN == null || maskSSN.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "SSN");
        }

        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "Card Number");
        }

        if (sendBy == null || sendBy.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + " Send By");
        }

        System.out.println("[FrontMobile.RegistrationManager] Loading client by cardNumber : " + cardNumber + " , maskSSN : " + maskSSN);
        MobileClient mobileClient = MobileClientDao.get().getMobileClientByCardNumberAndMaskSSN(maskSSN, cardNumber);

        if (mobileClient == null) {
            throw new MobileValidationException(Constants.CLIENT_DOES_NOT_EXIST, MobileMessage.CLIENT_DOES_NOT_EXIST.get(lang));
        }
        return mobileClient;
    }

    private MobileClient validateDataAndGetClient(String clientId, String username, String email, String phone, String oldPassword, String lang) throws MobileValidationException, ValidationException, NoSuchAlgorithmException {

        if (clientId == null || clientId.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "clientId");
        }

        if (username == null || username.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "UserName");
        }

        if (email == null || email.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "email");
        }

        if (phone == null || phone.isEmpty()) {
            throw new MobileValidationException(Constants.REQUIRED_FIELD, MobileMessage.REQUIRED_FIELD.get(lang) + "phone");
        }

        System.out.println("[FrontMobile.RegistrationManager] Loading client by clientId : " + clientId);
        int id = Integer.parseInt(clientId);
        MobileClient mobileClient = MobileClientDao.get().getMobileClientById(id);

        if (mobileClient == null) {
            throw new MobileValidationException(Constants.CLIENT_DOES_NOT_EXIST, MobileMessage.CLIENT_DOES_NOT_EXIST.get(lang));
        }
        
        if(oldPassword != null && !oldPassword.isEmpty()){
            String encryptPassword = PasswordUtil.encryptPassword(oldPassword);
            if(!mobileClient.getPassword().equalsIgnoreCase(encryptPassword)){
                throw new MobileValidationException(Constants.INVALID_OLD_PASSWORD, MobileMessage.INVALID_OLD_PASSWORD.get(lang));
            }
        }
        
        return mobileClient;
    }

    private void sendEmailOrSMSNotification(MobileClient mobileClient, String sendBy, String lang) throws Exception {

        if (sendBy.equalsIgnoreCase("sms")) {
            String smsMessage;
            if(lang != null && lang.equalsIgnoreCase("en")){
                smsMessage = "Thank you for choosing VoltCash. Your Access Code key is: " + mobileClient.getForgotPasswordKey() + ". Ignore if you did not make this request.";
            }else{
               smsMessage = "Gracias por preferir VoltCash. Su código de acceso es: " + mobileClient.getForgotPasswordKey() + ". Ignora este mensaje si usted no hizo esta solicitud.";
            }
            
            String sendSMSProperty = System.getProperty("SEND_SMS");
            Boolean sendSMS = sendSMSProperty != null && sendSMSProperty.equalsIgnoreCase("true");

            if (sendSMS && mobileClient.getClient() != null && mobileClient.getClient().getTelephone() != null) {

                System.out.println("--------------  SENDING SMS MESSAGE TO: 1" + mobileClient.getClient().getTelephone() + " --------------");

                System.out.println("text: " + smsMessage);

                SMSUtils.sendSMS("1" + mobileClient.getClient().getTelephone(), smsMessage);
            }
        } else if (sendBy.equalsIgnoreCase("email")) {
            Map<String, String> emailValuesMap = new HashMap<>();
            emailValuesMap.put("client_name", mobileClient.getClient().getFirstName());
            emailValuesMap.put("forgot_password_key", mobileClient.getForgotPasswordKey());
            
            EmailName emailName = lang != null && lang.equalsIgnoreCase("en") ? EmailName.ALERT_MOBILE_FORGOT_PASSWORD_KEY : EmailName.ALERT_MOBILE_FORGOT_PASSWORD_KEY_ES;

            System.out.println("--------------  SENDING " + emailName + " EMAIL --------------");
            System.out.println("Access Code:: " + mobileClient.getForgotPasswordKey());

            Email email = EmailManager.get().getByName(emailName);
            email.setRecipients(mobileClient.getClient().getEmail());
            email.setValues(emailValuesMap);
            GoogleMail.get().sendEmail(email);

        }

    }

}
