package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for personalInfo complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="personalInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *        &lt;element name="checkId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="idType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="telephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="telephoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="cellphone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="cellphoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="faxphone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="faxAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="workphone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="workphoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="idState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="idCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="personTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="maidenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="lastNameM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;element name="bornDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certegyInfoRequest", propOrder = {
    "user",
    "password",
    "checkId",
    "additionalInfo",
    "entityId",
    "feeAmount",
    "payoutAmount",
    "depositId",
    "certegyCode"
})
public class CertegyInfoRequest implements IMap {
    private String user;
    private String password;
    private String checkId;
    
    private String additionalInfo;  // por ahora no se usa
    private int entityId;
  //  private String entityName;
    private String feeAmount;
    private String payoutAmount;
   // private String response; //??
  //  private String transactionDate;  // innecessary ??
    private String depositId;
    private String certegyCode;

    @Override
    public Map toMap() {
        Map map = new HashMap();

        map.put(TransactionType.TRANSACTION_TYPE, TransactionType.CERTEGY_INFO);
        
        map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.CHECK_ID, getCheckId());
        
        map.put(ParameterName.ADDITIONAL_INFO, getAdditionalInfo());
        map.put(ParameterName.ENTITY_ID, getEntityId());
        map.put(ParameterName.FEE_AMMOUNT, getFeeAmount());
        map.put(ParameterName.PAYOUT_AMMOUNT, getPayoutAmount());
        map.put(ParameterName.DEPOSIT_ID, getDepositId());
        map.put(ParameterName.CERTEGY_CODE, getCertegyCode());
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
    public void setUser( String user ) {
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
    public void setPassword( String password ) {
        this.password = password;
    }

    /**
     * @return the checkId
     */
    public String getCheckId() {
        return checkId;
    }

    /**
     * @param checkId the checkId to set
     */
    public void setCheckId( String checkId ) {
        this.checkId = checkId;
    }

    /**
     * @return the additionalInfo
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * @param additionalInfo the additionalInfo to set
     */
    public void setAdditionalInfo( String additionalInfo ) {
        this.additionalInfo = additionalInfo;
    }

    /**
     * @return the entityId
     */
    public int getEntityId() {
        return entityId;
    }

    /**
     * @param entityId the entityId to set
     */
    public void setEntityId( int entityId ) {
        this.entityId = entityId;
    }

   

    /**
     * @return the feeAmount
     */
    public String getFeeAmount() {
        return feeAmount;
    }

    /**
     * @param feeAmount the feeAmount to set
     */
    public void setFeeAmount( String feeAmount ) {
        this.feeAmount = feeAmount;
    }

    /**
     * @return the payoutAmount
     */
    public String getPayoutAmount() {
        return payoutAmount;
    }

    /**
     * @param payoutAmount the payoutAmount to set
     */
    public void setPayoutAmount( String payoutAmount ) {
        this.payoutAmount = payoutAmount;
    }

 

    /**
     * @return the depositId
     */
    public String getDepositId() {
        return depositId;
    }

    /**
     * @param depositId the depositId to set
     */
    public void setDepositId( String depositId ) {
        this.depositId = depositId;
    }

    public void setCertegyCode( String certegyCode ) {
        this.certegyCode = certegyCode;
    }

    public String getCertegyCode() {
        return certegyCode;
    }

    
   
}
