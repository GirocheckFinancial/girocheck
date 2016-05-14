
package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pRequestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pRoutingBankNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "user",
    "password",
    "requestId",
    "terminalId",
    
//    "cardNumber",
//    "accountNumber",
//    "routingBankNumber",
//    "amount"
    "achForm",
    "existAch",
    "amount"
})
@XmlRootElement(name = "cardToBankConfirmationRequest")
public class CardToBankConfirmationRequest implements IMap{
    private  String user;
    private  String password;
    private  String requestId;
    private  String terminalId;
    
    private String achForm;
    private String existAch;
//    private String approved;
    private String amount;
    
    
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put( TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_CARD_TO_BANK_CONFIRMATION );

        map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.REQUEST_ID, getRequestId());
        map.put(ParameterName.TERMINAL_ID, getTerminalId());
        
        map.put( ParameterName.ACH_FORM, getAchForm());
        map.put( ParameterName.EXISTACH, getExistAch() );
//        map.put( ParameterName.APPROVED, getApproved());
        map.put( ParameterName.AMMOUNT, getAmount());

        return map;

    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the terminalId
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * @param terminalId the terminalId to set
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

//    /**
//     * @return the cardNumber
//     */
//    public String getCardNumber() {
//        return cardNumber;
//    }
//
//    /**
//     * @param cardNumber the cardNumber to set
//     */
//    public void setCardNumber(String cardNumber) {
//        this.cardNumber = cardNumber;
//    }
//
//    public void setAccountNumber(String accountNumber) {
//        this.accountNumber = accountNumber;
//    }
//
//    public String getAccountNumber() {
//        return accountNumber;
//    }
//
//   
//
//    /**
//     * @return the routingBankNumber
//     */
//    public String getRoutingBankNumber() {
//        return routingBankNumber;
//    }
//
//    /**
//     * @param routingBankNumber the routingBankNumber to set
//     */
//    public void setRoutingBankNumber(String routingBankNumber) {
//        this.routingBankNumber = routingBankNumber;
//    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the achForm
     */
    public String getAchForm() {
        return achForm;
    }

    /**
     * @param achForm the achForm to set
     */
    public void setAchForm(String achForm) {
        this.achForm = achForm;
    }

    /**
     * @return the flag
     */
    public String getExistAch() {
        return existAch;
    }

    /**
     * @param flag the flag to set
     */
    public void setExistAch(String existAch) {
        this.existAch = existAch;
    }

//    /**
//     * @return the approved
//     */
//    public String getApproved() {
//        return approved;
//    }
//
//    /**
//     * @param approved the approved to set
//     */
//    public void setApproved(String approved) {
//        this.approved = approved;
//    }


  
}
