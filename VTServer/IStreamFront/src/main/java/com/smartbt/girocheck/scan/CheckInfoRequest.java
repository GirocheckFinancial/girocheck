package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkInfoRequest", propOrder = {
    "user",
    "password",
    "checkId",
    "id",
    "address",
    "city",
    "state",
    "idState",
    "zipCode",
    "firstName",
    "lastName",
    "bornDate",
    "expirationDate",
    "micr",
    "makerName",
    "makerCity",
    "makerState",
    "makerZip",
    "makerPhone",
    "makerAddress",
    "paymentCheck", //(We send it to OE)
    "checkIssueDate", //CheckDate

    // optional fields
    "checkType",
    "checkNumber",
    "checkCAR",
    "checkLAR",
    "micrAcountNumber",
    "micrRoutingNumber",
    "micrCheckNumber",
    "micrCheckAmount",
    "signaturePresent",
    "aboveThreshold"})

public class CheckInfoRequest implements IMap {

    public static final DateFormat MDY_DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
    public static final DateFormat ISTREAM_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    private String user;
    private String password;
    private String checkId;
    private String id;
    private String address;
    private String city;
    private String state;
    private String idState;
    private String zipCode;
    private String firstName;
    private String lastName;
    private String bornDate; //date format should be yyyyMMdd
    private String expirationDate; // pendiente de verificacion
    private String micr;
    private String makerName;
    private String makerCity;
    private String makerState;
    private String makerZip;
    private String makerPhone;
    private String makerAddress;
    private String paymentCheck;
    private String checkIssueDate;  // date format should be yyyyMMdd

    //optional fields
    private String checkType;
    private String checkNumber;
    private String checkCAR;
    private String checkLAR;
    private String micrAcountNumber;
    private String micrRoutingNumber;
    private String micrCheckNumber;
    private String micrCheckAmount;
    private String signaturePresent;
    private String aboveThreshold;

    @Override
    public Map toMap() {
        System.out.println("CheckInfoRequest -> toMap() expirationDate = " + expirationDate);

        Map map = new HashMap();
        String errors = "";

        map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.CHECK_ID, getCheckId());
        map.put(ParameterName.ID, getId());
        map.put(ParameterName.ADDRESS, getAddress());
        map.put(ParameterName.CITY, getCity());
        map.put(ParameterName.STATE, getState());
        map.put(ParameterName.IDSTATE, getState());
        map.put(ParameterName.FIRST_NAME, getFirstName()); 
        map.put(ParameterName.AMMOUNT, checkCAR);

        Date bornD = getDateFromString(getBornDate());
        if (bornD != null) {
            System.out.println("CheckInfoRequest -> toMap() :: BORNDATE_AS_DATE = " + bornD);
            map.put(ParameterName.BORNDATE_AS_DATE, bornD);

            String bornDstr = ISTREAM_DATE_FORMAT.format(bornD);
            System.out.println("CheckInfoRequest -> toMap() :: BORNDATE = " + bornDstr);
            map.put(ParameterName.BORNDATE, bornDstr);
        }

        Date expDate = getDateFromString(getExpirationDate());
        if (expDate != null) {
            System.out.println("CheckInfoRequest -> toMap() :: EXPIRATION_DATE_AS_DATE = " + expDate);
            map.put(ParameterName.EXPIRATION_DATE_AS_DATE, expDate);

            String expDstr = ISTREAM_DATE_FORMAT.format(expDate);
            System.out.println("CheckInfoRequest -> toMap() :: EXPIRATION_DATE = " + expDstr);
            map.put(ParameterName.EXPIRATION_DATE, expDstr);
        }

        //TODO define which fields to validate
        //    errors += validateRequiredFields(map);
        if (!errors.isEmpty()) {
            map.put(ParameterName.VALIDATION_ERROR, errors);
        }

        map.put(TransactionType.TRANSACTION_TYPE, TransactionType.CHECK_INFO);
        map.put(ParameterName.EXPIRATION_DATE, getExpirationDate());
        map.put(ParameterName.LAST_NAME, getLastName());
        map.put(ParameterName.ZIPCODE, getZipCode());
        map.put(ParameterName.MICR, getMicr());
       // map.put(ParameterName.CHECK_TYPE, checkType);
        map.put(ParameterName.MAKER_NAME, getMakerName());
        map.put(ParameterName.MAKER_CITY, getMakerCity());
        map.put(ParameterName.MAKER_STATE, getMakerState());
        map.put(ParameterName.MAKER_ZIP, getMakerZip());
        map.put(ParameterName.MAKER_PHONE, getMakerPhone());
        map.put(ParameterName.MAKER_ADDRESS, getMakerAddress());
        map.put(ParameterName.PAYMENTCHECK, getPaymentCheck());

        map.put(ParameterName.PAYMENTCHECK, getPaymentCheck());

        map.put(ParameterName.CHECK_ISSUE_DATE, getCheckIssueDate());

        Iterator<Map.Entry<Object, Object>> it = map.entrySet().iterator();
        System.out.println("Printing input params:: ");
        System.out.println("");
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("");
        System.out.println("");
        System.out.println("");

        return map;
    }

    private Date getDateFromString(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }

        try {
            return MDY_DATE_FORMAT.parse(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Date Format");
        }
    }

    public String validateRequiredFields(Map map) {

        StringBuffer buffer = new StringBuffer();

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

    public String getPaymentCheck() {
        return paymentCheck;
    }

    public void setMicr(String micr) {
        this.micr = micr;
    }

    public String getMicr() {
        return micr;
    }

    public void setMakerAddress(String makerAddress) {
        this.makerAddress = makerAddress;
    }

    public String getMakerAddress() {
        return makerAddress;
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
    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the idState
     */
    public String getIdState() {
        return idState;
    }

    /**
     * @param idState the idState to set
     */
    public void setIdState(String idState) {
        this.idState = idState;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param bornDate the bornDate to set
     */
    //@JsonDeserialize(using=CustomDateDeserializer.class)
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
     * @return the makerName
     */
    public String getMakerName() {
        return makerName;
    }

    /**
     * @param makerName the makerName to set
     */
    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    /**
     * @return the makerCity
     */
    public String getMakerCity() {
        return makerCity;
    }

    /**
     * @param makerCity the makerCity to set
     */
    public void setMakerCity(String makerCity) {
        this.makerCity = makerCity;
    }

    /**
     * @return the makerState
     */
    public String getMakerState() {
        return makerState;
    }

    /**
     * @param makerState the makerState to set
     */
    public void setMakerState(String makerState) {
        this.makerState = makerState;
    }

    /**
     * @return the makerZip
     */
    public String getMakerZip() {
        return makerZip;
    }

    /**
     * @param makerZip the makerZip to set
     */
    public void setMakerZip(String makerZip) {
        this.makerZip = makerZip;
    }

    /**
     * @return the makerPhone
     */
    public String getMakerPhone() {
        return makerPhone;
    }

    /**
     * @param makerPhone the makerPhone to set
     */
    public void setMakerPhone(String makerPhone) {
        this.makerPhone = makerPhone;
    }

    /**
     * @return the checkIssueDate
     */
    public String getCheckIssueDate() {
        return checkIssueDate;
    }

    /**
     * @return the checkType
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * @param checkType the checkType to set
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    /**
     * @return the checkNumber
     */
    public String getCheckNumber() {
        return checkNumber;
    }

    /**
     * @param checkNumber the checkNumber to set
     */
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
     * @return the checkCAR
     */
    public String getCheckCAR() {
        return checkCAR;
    }

    /**
     * @param checkCAR the checkCAR to set
     */
    public void setCheckCAR(String checkCAR) {
        this.checkCAR = checkCAR;
    }

    /**
     * @return the checkLAR
     */
    public String getCheckLAR() {
        return checkLAR;
    }

    /**
     * @param checkLAR the checkLAR to set
     */
    public void setCheckLAR(String checkLAR) {
        this.checkLAR = checkLAR;
    }

    /**
     * @return the micrAcountNumber
     */
    public String getMicrAcountNumber() {
        return micrAcountNumber;
    }

    /**
     * @param micrAcountNumber the micrAcountNumber to set
     */
    public void setMicrAcountNumber(String micrAcountNumber) {
        this.micrAcountNumber = micrAcountNumber;
    }

    /**
     * @return the micrRoutingNumber
     */
    public String getMicrRoutingNumber() {
        return micrRoutingNumber;
    }

    /**
     * @param micrRoutingNumber the micrRoutingNumber to set
     */
    public void setMicrRoutingNumber(String micrRoutingNumber) {
        this.micrRoutingNumber = micrRoutingNumber;
    }

    /**
     * @return the micrCheckNumber
     */
    public String getMicrCheckNumber() {
        return micrCheckNumber;
    }

    /**
     * @param micrCheckNumber the micrCheckNumber to set
     */
    public void setMicrCheckNumber(String micrCheckNumber) {
        this.micrCheckNumber = micrCheckNumber;
    }

    /**
     * @return the micrCheckAmount
     */
    public String getMicrCheckAmount() {
        return micrCheckAmount;
    }

    /**
     * @param micrCheckAmount the micrCheckAmount to set
     */
    public void setMicrCheckAmount(String micrCheckAmount) {
        this.micrCheckAmount = micrCheckAmount;
    }

    /**
     * @return the signaturePresent
     */
    public String getSignaturePresent() {
        return signaturePresent;
    }

    /**
     * @param signaturePresent the signaturePresent to set
     */
    public void setSignaturePresent(String signaturePresent) {
        this.signaturePresent = signaturePresent;
    }

    /**
     * @return the aboveThreshold
     */
    public String getAboveThreshold() {
        return aboveThreshold;
    }

    /**
     * @param aboveThreshold the aboveThreshold to set
     */
    public void setAboveThreshold(String aboveThreshold) {
        this.aboveThreshold = aboveThreshold;
    }

}
