
package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for checkAuthRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkAuthRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressCorrect" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="backTiff" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="batchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkBack" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="checkFront" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="crc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entityNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="frontTiff" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="idBack" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="idFront" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="iqaRawData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="micr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scanTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="scannerManufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sendUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ssn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkAuthRequest", propOrder = {
   "user",
    "password",
    "requestId",
    "terminalId",
    
    "addressCorrect",
    "addressForm",
    "amount",
    "backTiff",
    "batchName",
    "cardNumber",
    "checkBack",
    "checkFront",
    "crc",
    "emailAddress",
    "frontTiff",
    "idBack",
    "idFront",
    "micr",
    "phone",
    "scannerManufacturer",
    "serial",
    "ssn",
    "operation",
    "track1",
    "track2",
    "dlDataScan",//new field driver license data
    "dlDataSwipe",//new field driver license data
    
    "tecnicardValidationResponse"
})
public class CheckAuthRequest implements IMap{

    private String user;
    private String password;
    private String requestId;
    private String terminalId;  //OJOOO cambiar a String

    protected String addressCorrect;//y o n
    protected byte[] addressForm; //si es no (almacenar en DB)
//    protected byte[] achForm; 
    protected String amount;//
    protected byte[] backTiff;//
    protected String batchName;  //product type
    protected String cardNumber;//
    protected byte[] checkBack;//
    protected byte[] checkFront;//
    protected String crc;//
    protected String emailAddress;//
   // protected String entityNumber;// buscar en la bd a prti del terminal id
    protected byte[] frontTiff;//
    protected byte[] idBack;//
    protected byte[] idFront;//
    protected String micr;//
    protected String phone;//a la hora de almacenar en la bd tomo el d istream
    protected String scannerManufacturer;//
    protected String serial;//
    protected String ssn;//
    private String operation;//
    
    private String track1;
    private String track2;
    private String dlDataScan;
    private String dlDataSwipe;

    private String tecnicardValidationResponse;
    
     @Override
   public Map toMap() {
      Map map = new HashMap();
      
    //  map.put(TransactionType.TRANSACTION_TYPE, TransactionType.ISTREAM_CHECK_AUTH);
     map.put(TransactionType.TRANSACTION_TYPE, TransactionType.NEW_CARD_LOAD);
      
     map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.REQUEST_ID, getRequestId());
        map.put(ParameterName.TERMINAL_ID, getTerminalId());

        map.put(ParameterName.OPERATION, getOperation());
        map.put(ParameterName.ADDRESS_CORRECT, addressCorrect);
        map.put(ParameterName.ADDRESS_FORM, addressForm);
        map.put(ParameterName.AMMOUNT, amount);
        map.put(ParameterName.BACK_TIFF, backTiff);
        map.put(ParameterName.BATCH_NAME, batchName);
        map.put(ParameterName.CARD_NUMBER, cardNumber);
        map.put(ParameterName.CHECK_BACK, checkBack);
        map.put(ParameterName.CHECK_FRONT, checkFront);
        map.put(ParameterName.CRC, crc);
        map.put(ParameterName.EMAIL_ADDRESS, emailAddress);
        map.put(ParameterName.FRONT_TIFF, frontTiff);
        map.put(ParameterName.IDBACK, idBack);
        map.put(ParameterName.IDFRONT, idFront);
        map.put(ParameterName.MICR, micr);
        map.put(ParameterName.PHONE, phone);
        map.put(ParameterName.TELEPHONE, phone);
        map.put(ParameterName.SCANNER_MANUFACTURER, scannerManufacturer);
        map.put(ParameterName.SERIAL, serial);
        map.put(ParameterName.SSN, ssn);
        map.put(ParameterName.TRACK1, track1);
        map.put(ParameterName.TRACK2, track2);
        map.put(ParameterName.DLDATASCAN, dlDataScan);
        map.put(ParameterName.DLDATASWIPE, dlDataSwipe);
        
        if(tecnicardValidationResponse != null && !tecnicardValidationResponse.isEmpty()){ //for dev and test
            map.put(ParameterName.TECNICARD_VALIDATION_RESPONSE, tecnicardValidationResponse);
        }
       
    return map;
    }

    public Map mock() {
      Map map = new HashMap();
      
      map.put(TransactionType.TRANSACTION_TYPE, TransactionType.NEW_CARD_LOAD);
      
     map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.REQUEST_ID, getRequestId());
        map.put(ParameterName.TERMINAL_ID, getTerminalId());

        
        map.put(ParameterName.ADDRESS_CORRECT, addressCorrect);
        map.put(ParameterName.ADDRESS_FORM, ParameterName.IMAGE);
//        map.put(ParameterName.ACH_FORM, ParameterName.IMAGE);
        map.put(ParameterName.AMMOUNT, amount);
        map.put(ParameterName.BACK_TIFF, ParameterName.IMAGE);
        map.put(ParameterName.BATCH_NAME, batchName);
        map.put(ParameterName.CARD_NUMBER, cardNumber);
        map.put(ParameterName.CHECK_BACK,  ParameterName.IMAGE);
        map.put(ParameterName.CHECK_FRONT,  ParameterName.IMAGE);
        map.put(ParameterName.CRC, crc);
        map.put(ParameterName.EMAIL_ADDRESS, emailAddress);
        map.put(ParameterName.FRONT_TIFF,  ParameterName.IMAGE);
        map.put(ParameterName.IDBACK,  ParameterName.IMAGE);
        map.put(ParameterName.IDFRONT,  ParameterName.IMAGE);
        map.put(ParameterName.MICR, micr);
        map.put(ParameterName.PHONE, phone);
        map.put(ParameterName.TELEPHONE, phone);
        map.put(ParameterName.SCANNER_MANUFACTURER, scannerManufacturer);
        map.put(ParameterName.SERIAL, serial);
        map.put(ParameterName.SSN, ssn);
        map.put(ParameterName.OPERATION, getOperation());
    return map;
    }
   

    public byte[] getAddressForm() {
        return addressForm;
    }

    public void setTrack1(String track1) {
        this.track1 = track1;
    }

    public void setTrack2(String track2) {
        this.track2 = track2;
    }

    public String getTrack1() {
        return track1;
    }

    public String getTrack2() {
        return track2;
    }

   
   
   
   
    /**
     * Gets the value of the addressCorrect property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCorrect() {
        return addressCorrect;
    }

    /**
     * Sets the value of the addressCorrect property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCorrect(String value) {
        this.addressCorrect = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the backTiff property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBackTiff() {
        return backTiff;
    }

    /**
     * Sets the value of the backTiff property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBackTiff(byte[] value) {
        this.backTiff = value;
    }

    /**
     * Gets the value of the batchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * Sets the value of the batchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchName(String value) {
        this.batchName = value;
    }

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the checkBack property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCheckBack() {
        return checkBack;
    }

    /**
     * Sets the value of the checkBack property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCheckBack(byte[] value) {
        this.checkBack = value;
    }

    /**
     * Gets the value of the checkFront property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCheckFront() {
        return checkFront;
    }

    /**
     * Sets the value of the checkFront property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCheckFront(byte[] value) {
        this.checkFront = value;
    }

    /**
     * Gets the value of the crc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrc() {
        return crc;
    }

    /**
     * Sets the value of the crc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrc(String value) {
        this.crc = value;
    }

   
    /**
     * Gets the value of the emailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    

    /**
     * Gets the value of the frontTiff property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFrontTiff() {
        return frontTiff;
    }

    /**
     * Sets the value of the frontTiff property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFrontTiff(byte[] value) {
        this.frontTiff = value;
    }

    /**
     * Gets the value of the idBack property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIdBack() {
        return idBack;
    }

    /**
     * Sets the value of the idBack property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIdBack(byte[] value) {
        this.idBack = value;
    }

    /**
     * Gets the value of the idFront property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIdFront() {
        return idFront;
    }

    /**
     * Sets the value of the idFront property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIdFront(byte[] value) {
        this.idFront = value;
    }

 

    /**
     * Gets the value of the micr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMicr() {
        return micr;
    }

    /**
     * Sets the value of the micr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMicr(String value) {
        this.micr = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

 
    /**
     * Gets the value of the scannerManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScannerManufacturer() {
        return scannerManufacturer;
    }

    /**
     * Sets the value of the scannerManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScannerManufacturer(String value) {
        this.scannerManufacturer = value;
    }

  
    public String getSerial() {
        return serial;
    }

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerial(String value) {
        this.serial = value;
    }

    /**
     * Gets the value of the ssn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsn(String value) {
        this.ssn = value;
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
     * @return the dlDataScan
     */
    public String getDlDataScan() {
        return dlDataScan;
    }

    /**
     * @param dlDataScan the dlDataScan to set
     */
    public void setDlDataScan(String dlDataScan) {
        this.dlDataScan = dlDataScan;
    }

    /**
     * @return the dlDataSwipe
     */
    public String getDlDataSwipe() {
        return dlDataSwipe;
    }

    /**
     * @param dlDataSwipe the dlDataSwipe to set
     */
    public void setDlDataSwipe(String dlDataSwipe) {
        this.dlDataSwipe = dlDataSwipe;
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
