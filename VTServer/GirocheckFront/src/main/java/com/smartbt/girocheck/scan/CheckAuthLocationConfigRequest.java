package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for checkAuthLocationConfigRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="checkAuthLocationConfigRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkAuthLocationConfigRequest", propOrder = {
    "user",
    "password",
    "requestId",
    "terminalId",
    "amount",
    "cardNumber",
    "operation",
    "tecnicardValidationResponse"
})
public class CheckAuthLocationConfigRequest implements IMap {

    private String user;
    private String password;
    private String requestId;
    private String terminalId;
    private String amount;
    private String cardNumber;
    private String operation;

    private String tecnicardValidationResponse;

    @Override
    public Map toMap() {
        Map map = new HashMap();

        String errors = "";

        map.put(TransactionType.TRANSACTION_TYPE, TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG);
        map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.REQUEST_ID, getRequestId());
        map.put(ParameterName.TERMINAL_ID, getTerminalId());
        
        errors += validateRequiredFields(map);
        
        if(amount != null && !amount.isEmpty()){
             map.put(ParameterName.AMMOUNT, getAmount());
        }
       
        if(cardNumber != null && !cardNumber.isEmpty()){
              map.put(ParameterName.CARD_NUMBER, getCardNumber());
        }
        
        if(operation != null && !operation.isEmpty()){
               map.put(ParameterName.OPERATION, getOperation());   
        }

        if (!errors.isEmpty()) {
            map.put(ParameterName.VALIDATION_ERROR, errors);
        }

        if (tecnicardValidationResponse != null && !tecnicardValidationResponse.isEmpty()) { //for dev and test
            map.put(ParameterName.TECNICARD_VALIDATION_RESPONSE, tecnicardValidationResponse);
        }
        return map;
    }

    public String validateRequiredFields(Map map) {

        StringBuilder buffer = new StringBuilder();

        Iterator keySet = map.keySet().iterator();

        for (Iterator it = keySet; it.hasNext();) {
            Object key = it.next();
            Object value = map.get(key);
            if (value == null || value.toString().isEmpty()) {
                buffer.append("Field ").append(key).append(" required. " + '\n');
            }

        }
        return buffer.toString();
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

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return the tecnicardValidationResponse
     */
    public String getTecnicardValidationResponse() {
        return tecnicardValidationResponse;
    }

    /**
     * @param tecnicardValidationResponse the tecnicardValidationResponse to set
     */
    public void setTecnicardValidationResponse(String tecnicardValidationResponse) {
        this.tecnicardValidationResponse = tecnicardValidationResponse;
    }

}
