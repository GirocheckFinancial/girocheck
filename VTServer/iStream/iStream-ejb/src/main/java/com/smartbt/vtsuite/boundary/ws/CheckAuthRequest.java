package com.smartbt.vtsuite.boundary.ws;

import com.smartbt.girocheck.servercommon.enums.EnumApplicationParameter;
import com.smartbt.vtsuite.boundary.util.MapUtil;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for checkAuthRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkAuthRequest", propOrder = {
    "addressCorrect",
    "amount",
    "backTiff",
    "batchName",
    "cardNumber",
    "checkBack",
    "checkFront",
    "crc",
    "data",
    "emailAddress",
    "entityNumber",
    "frontTiff",
    "idBack",
    "idFront",
    "identification",
    "iqaRawData",
    "micr",
    "password",
    "phone",
    "scanTime",
    "scannerManufacturer",
    "sendTime",
    "sendUser",
    "serial",
    "ssn"   
})
public class CheckAuthRequest {

    protected String addressCorrect;
    protected String amount;
    protected byte[] backTiff;
    protected String batchName;
    protected String cardNumber;
    protected byte[] checkBack;
    protected byte[] checkFront;
    protected String crc;
    @XmlElement(nillable = true)
    protected List<Object> data;
    protected String emailAddress;
    protected String entityNumber;
    protected byte[] frontTiff;
    protected byte[] idBack;
    protected byte[] idFront;
    protected Identification identification;
    protected String iqaRawData;
    protected String micr;
    protected String password;
    protected String phone;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scanTime;
    protected String scannerManufacturer;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sendTime;
    protected String sendUser;
    protected String serial;
    protected String ssn;

    public CheckAuthRequest build( java.util.Map map ) throws Exception {
        
        this.addressCorrect = MapUtil.getStringValueFromMap( map, ParameterName.ADDRESS_CORRECT, true );
        this.amount = String.valueOf( MapUtil.getDoubleValueFromMap( map, ParameterName.AMMOUNT, true ) );
        this.backTiff = MapUtil.getByteArrayFromMap( map, ParameterName.BACK_TIFF );
        this.frontTiff = MapUtil.getByteArrayFromMap( map, ParameterName.FRONT_TIFF );
        this.batchName = MapUtil.getStringValueFromMap( map, ParameterName.BATCH_NAME, false );
        this.cardNumber = "";
        this.checkBack = MapUtil.getByteArrayFromMap( map, ParameterName.CHECK_BACK );
        this.checkFront = MapUtil.getByteArrayFromMap( map, ParameterName.CHECK_FRONT );
        this.crc = MapUtil.getStringValueFromMap( map, ParameterName.CRC, false );
        this.data = MapUtil.getObjectListFromMap( map, ParameterName.DATA );
        this.emailAddress = MapUtil.getStringValueFromMap( map, ParameterName.EMAIL_ADDRESS, false );
        this.entityNumber = MapUtil.getStringValueFromMap( map, ParameterName.TERMINAL_ID_ISTREAM, true ); //privissional, this value has to be the ENTITYI_ID taken out of the DB, starting from the terminal ID 

        this.idBack = MapUtil.getByteArrayFromMap( map, ParameterName.IDBACK );
        this.idFront = MapUtil.getByteArrayFromMap( map, ParameterName.IDFRONT );
        
        Identification identificationn = new Identification();
        
        identificationn.setId(MapUtil.getStringValueFromMap( map, ParameterName.ID, false ));
       
        System.out.println("map.get(ParameterName.BORNDATE) " + map.get(ParameterName.BORNDATE));
        
        if(map.get(ParameterName.BORNDATE_AS_DATE) != null){
                Date dob = (Date)map.get(ParameterName.BORNDATE_AS_DATE);             
//                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckAuthRequest] BORNDATE original DATE: "+dob,null);
//                    Date dobIn = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                    SimpleDateFormat dobOut = new SimpleDateFormat("MM-dd-yyyy");
                    identificationn.setDob(dobOut.format(dob));
                   
        }else{
            identificationn.setDob(MapUtil.getStringValueFromMap( map, ParameterName.BORNDATE, false ));
        }
        
         CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckAuthRequest] BORNDATE value : "+ identificationn.getDob(),null);
        
//        identificationn.setDob(MapUtil.getStringValueFromMap( map, ParameterName.BORNDATE, false ));
//        identificationn.setDob(MapUtil.getStringValueFromMap( map, ParameterName.BORNDATE, false ));
        identificationn.setFirstName(MapUtil.getStringValueFromMap( map, ParameterName.FIRST_NAME, false ));
        identificationn.setLastName(MapUtil.getStringValueFromMap( map, ParameterName.LAST_NAME, false ));
        identificationn.setAddress(MapUtil.getStringValueFromMap( map, ParameterName.ADDRESS, false ));
        identificationn.setCity(MapUtil.getStringValueFromMap( map, ParameterName.CITY, false ));
        identificationn.setState(MapUtil.getStringValueFromMap( map, ParameterName.OEIDSTATE, false ));
//        identificationn.setState(MapUtil.getStringValueFromMap( map, ParameterName.STATE, false ));
        identificationn.setZip(MapUtil.getStringValueFromMap( map, ParameterName.ZIPCODE, false ));
        
        this.identification = identificationn;
        
        this.iqaRawData = MapUtil.getStringValueFromMap( map, ParameterName.IQARAW_DATA, false );
        this.micr = MapUtil.getStringValueFromMap( map, ParameterName.MICR, false );
        this.phone = MapUtil.getStringValueFromMap( map, ParameterName.PHONE, false );
        this.scanTime = MapUtil.getGregorianCalendarFromMap( map, ParameterName.SCAN_TIME );
        this.scannerManufacturer = MapUtil.getStringValueFromMap( map, ParameterName.SCANNER_MANUFACTURER, true );
        this.sendTime = MapUtil.getGregorianCalendarFromMap( map, ParameterName.SEND_TIME );
        this.serial = MapUtil.getStringValueFromMap( map, ParameterName.SERIAL, true );
        this.ssn = MapUtil.getStringValueFromMap( map, ParameterName.SSN, true );

//        System.out.println("CHECKAUTH VALUES: sendUser: "+(String)map.get( ParameterName.TERMINAL_USER_ISTREAM )+" password: "+(String) map.get( ParameterName.TERMINAL_PASSWORD_ISTREAM));
//        
        this.sendUser = (String) map.get( ParameterName.TERMINAL_USER_ISTREAM );

        this.password = (String) map.get( ParameterName.TERMINAL_PASSWORD_ISTREAM);

       // printAsXML();
        String print = System.getProperty("ISTREAM_DUMP");
        if(print != null && print.equals("1")){
            toXml();
        }
        return this;
    }

    public void toXml() {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckAuthRequest] toXml()... ",null);
        try {
            JAXBContext ctx = JAXBContext.newInstance(CheckAuthRequest.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(this, System.out);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckAuthRequest] toXml()... DONE. ",null);
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckAuthRequest] toXml()... Error. ",null);
            e.printStackTrace();
            //catch exception 
        }
    }
    public String getAsXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<CheckAuthRequest>" );
        sb.append( "      <sendUser> " + sendUser + " </sendUser>" );
        sb.append( "      <password> " + password + " </password>" );
        sb.append( "      <addressCorrect> " + addressCorrect + " </addressCorrect>" );
        sb.append( "      <amount> " + amount + " </amount>" );
        sb.append( "      <backTiff> " + ( backTiff != null && backTiff.length > 0 ? "an image" : " null" ) + " </backTiff>" );
        sb.append( "      <frontTiff> " + ( frontTiff != null && frontTiff.length > 0 ? "an image" : " null" ) + " </frontTiff>" );
        sb.append( "      <checkBack> " + ( checkBack != null && checkBack.length > 0 ? "an image" : " null" ) + " </checkBack>" );
        sb.append( "      <checkFront> " + ( checkFront != null && checkFront.length > 0 ? "an image" : " null" ) + " </checkFront>" );
        sb.append( "      <idBack> " + ( idBack != null && idBack.length > 0 ? "an image" : " null" ) + " </idBack>" );
        sb.append( "      <idFront> " + ( idFront != null && idFront.length > 0 ? "an image" : " null" ) + " </idFront>" );
        sb.append( "      <batchName> " + batchName + " </batchName>" );
        sb.append( "      <cardNumber></cardNumber>" );
        sb.append( "      <crc> " + crc + " </crc>" );
        sb.append( "      <data> " + ( data != null ? "an Object" : "null" ) + " </data>" );
        sb.append( "      <emailAddress> " + emailAddress + " </emailAddress>" );
        sb.append( "      <entityNumber> " + entityNumber + " </entityNumber>" );
        sb.append( "      <iqaRawData> " + iqaRawData + " </iqaRawData>" );
        sb.append( "      <micr> " + micr + " </micr>" );
        sb.append( "      <phone> " + phone + " </phone>" );
        sb.append( "      <scanTime> " + scanTime + " </scanTime>" );
        sb.append( "      <scannerManufacturer> " + scannerManufacturer + " </scannerManufacturer>" );
        sb.append( "      <sendTime> " + sendTime + " </sendTime>" );
        sb.append( "      <serial> " + serial + " </serial>" );
        sb.append( "      <ssn> " + ssn + " </ssn>" );
        
        if(identification != null){
            sb.append(identification.getAsXML());
        }else{
            sb.append( "identification iS NULL");
        }
        
        sb.append( "</CheckAuthRequest>" );
        
        return sb.toString();
    }
    
    
    public void printAsXML() {
        System.out.println( "<CheckAuthRequest>" );
        System.out.println( "      <sendUser> " + sendUser + " </sendUser>" );
        System.out.println( "      <password> " + password + " </password>" );
        System.out.println( "      <addressCorrect> " + addressCorrect + " </addressCorrect>" );
        System.out.println( "      <amount> " + amount + " </amount>" );
        System.out.println( "      <backTiff> " + ( backTiff != null && backTiff.length > 0 ? "an image" : " null" ) + " </backTiff>" );
        System.out.println( "      <frontTiff> " + ( frontTiff != null && frontTiff.length > 0 ? "an image" : " null" ) + " </frontTiff>" );
        System.out.println( "      <checkBack> " + ( checkBack != null && checkBack.length > 0 ? "an image" : " null" ) + " </checkBack>" );
        System.out.println( "      <checkFront> " + ( checkFront != null && checkFront.length > 0 ? "an image" : " null" ) + " </checkFront>" );
        System.out.println( "      <idBack> " + ( idBack != null && idBack.length > 0 ? "an image" : " null" ) + " </idBack>" );
        System.out.println( "      <idFront> " + ( idFront != null && idFront.length > 0 ? "an image" : " null" ) + " </idFront>" );
        System.out.println( "      <batchName> " + batchName + " </batchName>" );
        System.out.println( "      <cardNumber></cardNumber>" );
        System.out.println( "      <crc> " + crc + " </crc>" );
        System.out.println( "      <data> " + ( data != null ? "an Object" : "null" ) + " </data>" );
        System.out.println( "      <emailAddress> " + emailAddress + " </emailAddress>" );
        System.out.println( "      <entityNumber> " + entityNumber + " </entityNumber>" );
        System.out.println( "      <iqaRawData> " + iqaRawData + " </iqaRawData>" );
        System.out.println( "      <micr> " + micr + " </micr>" );
        System.out.println( "      <phone> " + phone + " </phone>" );
        System.out.println( "      <scanTime> " + scanTime + " </scanTime>" );
        System.out.println( "      <scannerManufacturer> " + scannerManufacturer + " </scannerManufacturer>" );
        System.out.println( "      <sendTime> " + sendTime + " </sendTime>" );
        System.out.println( "      <serial> " + serial + " </serial>" );
        identification.printAsXML();
        System.out.println( "      <ssn> " + ssn + " </ssn>" );
        System.out.println( "</CheckAuthRequest>" );
    }

    /**
     * Gets the value of the addressCorrect property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAddressCorrect() {
        return addressCorrect;
    }

    /**
     * Sets the value of the addressCorrect property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAddressCorrect( String value ) {
        this.addressCorrect = value;
    }

    /**
     * Gets the value of the amount property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAmount( String value ) {
        this.amount = value;
    }

    /**
     * Gets the value of the backTiff property.
     *
     * @return possible object is byte[]
     */
    public byte[] getBackTiff() {
        return backTiff;
    }

    /**
     * Sets the value of the backTiff property.
     *
     * @param value allowed object is byte[]
     */
    public void setBackTiff( byte[] value ) {
        this.backTiff = value;
    }

    /**
     * Gets the value of the batchName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * Sets the value of the batchName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setBatchName( String value ) {
        this.batchName = value;
    }

    /**
     * Gets the value of the cardNumber property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCardNumber( String value ) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the checkBack property.
     *
     * @return possible object is byte[]
     */
    public byte[] getCheckBack() {
        return checkBack;
    }

    /**
     * Sets the value of the checkBack property.
     *
     * @param value allowed object is byte[]
     */
    public void setCheckBack( byte[] value ) {
        this.checkBack = value;
    }

    /**
     * Gets the value of the checkFront property.
     *
     * @return possible object is byte[]
     */
    public byte[] getCheckFront() {
        return checkFront;
    }

    /**
     * Sets the value of the checkFront property.
     *
     * @param value allowed object is byte[]
     */
    public void setCheckFront( byte[] value ) {
        this.checkFront = value;
    }

    /**
     * Gets the value of the crc property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCrc() {
        return crc;
    }

    /**
     * Sets the value of the crc property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCrc( String value ) {
        this.crc = value;
    }

    /**
     * Gets the value of the data property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the data property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getData().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Object }
     *
     *
     */
    public List<Object> getData() {
        if ( data == null ) {
            data = new ArrayList<Object>();
        }
        return this.data;
    }

    /**
     * Gets the value of the emailAddress property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEmailAddress( String value ) {
        this.emailAddress = value;
    }

    /**
     * Gets the value of the entityNumber property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEntityNumber() {
        return entityNumber;
    }

    /**
     * Sets the value of the entityNumber property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEntityNumber( String value ) {
        this.entityNumber = value;
    }

    /**
     * Gets the value of the frontTiff property.
     *
     * @return possible object is byte[]
     */
    public byte[] getFrontTiff() {
        return frontTiff;
    }

    /**
     * Sets the value of the frontTiff property.
     *
     * @param value allowed object is byte[]
     */
    public void setFrontTiff( byte[] value ) {
        this.frontTiff = value;
    }

    /**
     * Gets the value of the idBack property.
     *
     * @return possible object is byte[]
     */
    public byte[] getIdBack() {
        return idBack;
    }

    /**
     * Sets the value of the idBack property.
     *
     * @param value allowed object is byte[]
     */
    public void setIdBack( byte[] value ) {
        this.idBack = value;
    }

    /**
     * Gets the value of the idFront property.
     *
     * @return possible object is byte[]
     */
    public byte[] getIdFront() {
        return idFront;
    }

    /**
     * Sets the value of the idFront property.
     *
     * @param value allowed object is byte[]
     */
    public void setIdFront( byte[] value ) {
        this.idFront = value;
    }

    /**
     * Gets the value of the iqaRawData property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIqaRawData() {
        return iqaRawData;
    }

    /**
     * Sets the value of the iqaRawData property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIqaRawData( String value ) {
        this.iqaRawData = value;
    }

    /**
     * Gets the value of the micr property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMicr() {
        return micr;
    }

    /**
     * Sets the value of the micr property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setMicr( String value ) {
        this.micr = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPassword( String value ) {
        this.password = value;
    }

    /**
     * Gets the value of the phone property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPhone( String value ) {
        this.phone = value;
    }

    /**
     * Gets the value of the scanTime property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getScanTime() {
        return scanTime;
    }

    /**
     * Sets the value of the scanTime property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setScanTime( XMLGregorianCalendar value ) {
        this.scanTime = value;
    }

    /**
     * Gets the value of the scannerManufacturer property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getScannerManufacturer() {
        return scannerManufacturer;
    }

    /**
     * Sets the value of the scannerManufacturer property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setScannerManufacturer( String value ) {
        this.scannerManufacturer = value;
    }

    /**
     * Gets the value of the sendTime property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getSendTime() {
        return sendTime;
    }

    /**
     * Sets the value of the sendTime property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setSendTime( XMLGregorianCalendar value ) {
        this.sendTime = value;
    }

    /**
     * Gets the value of the sendUser property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSendUser() {
        return sendUser;
    }

    /**
     * Sets the value of the sendUser property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSendUser( String value ) {
        this.sendUser = value;
    }

    /**
     * Gets the value of the serial property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSerial() {
        return serial;
    }

    /**
     * Sets the value of the serial property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSerial( String value ) {
        this.serial = value;
    }

    /**
     * Gets the value of the ssn property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the value of the ssn property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSsn( String value ) {
        this.ssn = value;
    }

    /**
     * @return the identification
     */
    public Identification getIdentification() {
        return identification;
    }

    /**
     * @param identification the identification to set
     */
    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    }
