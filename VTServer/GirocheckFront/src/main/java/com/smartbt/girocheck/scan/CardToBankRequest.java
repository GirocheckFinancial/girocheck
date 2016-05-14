
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
    
    "cardNumber"
//    "accountNumber",
//    "routingBankNumber",
//    "amount"
})
@XmlRootElement(name = "cardToBankRequest")
public class CardToBankRequest implements IMap{
    private  String user;
    private  String password;
    private  String requestId;
    private  String terminalId;
    
    private String cardNumber;
//    private String accountNumber;
//    private String routingBankNumber;
//    private String amount;
    
    
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put( TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_CARD_TO_BANK );

        map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.REQUEST_ID, getRequestId());
        map.put(ParameterName.TERMINAL_ID, getTerminalId());
        
        map.put( ParameterName.CARD_NUMBER, getCardNumber());
//        map.put( ParameterName.ACCOUNT_NUMBER, accountNumber );
//        map.put( ParameterName.ROUTING_BANK_NUMBER, getRoutingBankNumber());
//        map.put( ParameterName.AMMOUNT, getAmount());

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

    /**
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

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
//
//    /**
//     * @return the amount
//     */
//    public String getAmount() {
//        return amount;
//    }
//
//    /**
//     * @param amount the amount to set
//     */
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }


  
}
