package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.text.ParseException;
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
    "telephone",
    "email",
    "address",
    "city",
    "state",
    "idState",
    "zipCode",
    "country",
    "idCountry",
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
    "locationId",
    "paymentCheck",
    
    "checkIssueDate",
    "checkType",
    "expansionType",
    "entryType"
})
public class CheckInfoRequest implements IMap {
    
    private String user;
    private String password;
    private String checkId; 
    private String id;
    private String telephone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String idState;
    private String zipCode;
    private String country;
    private String idCountry;
    private String firstName;
    private String lastName;
    private Date bornDate; //date format should be yyyyMMdd
    private Date expirationDate; // pendiente de verificacion
    private String micr;
    private String makerName;
    private String makerCity;
    private String makerState;
    private String makerZip;
    private String makerPhone;
    private String makerAddress;
    private String locationId;
    private String paymentCheck;
    private String checkIssueDate;  // date format should be yyyyMMdd
    private String checkType;
    private String expansionType;
    private String entryType;
    
    @Override
    public Map toMap() {
        Map map = new HashMap();
        String errors = "";
 
            map.put(ParameterName.USER, getUser());
            map.put(ParameterName.PASSWORD, getPassword());
            map.put(ParameterName.CHECK_ID, getCheckId());
            map.put(ParameterName.ID, getId());
            map.put(ParameterName.TELEPHONE, getTelephone());
            map.put(ParameterName.PHONE, getTelephone());
            map.put(ParameterName.EMAIL, getEmail());
            map.put(ParameterName.ADDRESS, getAddress());
            map.put(ParameterName.CITY, getCity());
            map.put(ParameterName.STATE, getState());
            map.put(ParameterName.IDSTATE, getIdState()); 
            map.put(ParameterName.COUNTRY, getCountry());
            map.put(ParameterName.IDCOUNTRY, getIdCountry());
            map.put(ParameterName.FIRST_NAME, getFirstName());
            map.put(ParameterName.BORNDATE, getBornDate());
            map.put(ParameterName.BORNDATE_AS_DATE, getBornDate());

            //TODO define which fields to validate
        //    errors += validateRequiredFields(map);

            if(!errors.isEmpty()){
                map.put(ParameterName.VALIDATION_ERROR, errors);
            }

            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.CHECK_INFO);
            map.put(ParameterName.EXPIRATION_DATE, getExpirationDate());
            map.put(ParameterName.LAST_NAME, getLastName());
            map.put(ParameterName.ZIPCODE, getZipCode());
            map.put(ParameterName.MICR, getMicr());
            map.put(ParameterName.MAKER_NAME, getMakerName());
            map.put(ParameterName.MAKER_CITY, getMakerCity());
            map.put(ParameterName.MAKER_STATE, getMakerState());
            map.put(ParameterName.MAKER_ZIP, getMakerZip());
            map.put(ParameterName.MAKER_PHONE, getMakerPhone());
            map.put(ParameterName.MAKER_ADDRESS, getMakerAddress());

            map.put(ParameterName.LOCATION_ID, getLocationId());
            map.put(ParameterName.PAYMENTCHECK, getPaymentCheck());
          
            map.put(ParameterName.PAYMENTCHECK, getPaymentCheck());
            
            map.put(ParameterName.CHECK_ISSUE_DATE, getCheckIssueDate());
            map.put(ParameterName.CHECK_TYPE, getCheckType());
            map.put(ParameterName.CHECK_EXPANSION_TYPE, getExpansionType());
            map.put(ParameterName.CHECK_ENTRY_TYPE, getEntryType());
          
        return map;
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
 
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the idCountry
     */
    public String getIdCountry() {
        return idCountry;
    }

    /**
     * @param idCountry the idCountry to set
     */
    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
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

    /**
     * @return the bornDate
     */
    public Date getBornDate() {
        return bornDate;
    }

    /**
     * @param bornDate the bornDate to set
     */
    //@JsonDeserialize(using=CustomDateDeserializer.class)
    public void setBornDate(Date bornDate) throws ParseException {
        System.out.println("setBornDate = " + bornDate);
        this.bornDate = bornDate;
    }

    public void setExpirationDate(String expirationDate) throws ParseException {
        if (expirationDate.isEmpty()) {
            this.expirationDate = null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            this.expirationDate = sdf.parse(expirationDate);
        }
    }

    public Date getExpirationDate() {
        return expirationDate;
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
     * @return the locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    } 

    /**
     * @return the checkIssueDate
     */
    public String getCheckIssueDate() {
        return checkIssueDate;
    }

    /**
     * @param checkIssueDate the checkIssueDate to set
     */
    public void setCheckIssueDate(String checkIssueDate) {
        this.checkIssueDate = checkIssueDate;
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
     * @return the expansionType
     */
    public String getExpansionType() {
        return expansionType;
    }

    /**
     * @param expansionType the expansionType to set
     */
    public void setExpansionType(String expansionType) {
        this.expansionType = expansionType;
    }

    /**
     * @return the entryType
     */
    public String getEntryType() {
        return entryType;
    }

    /**
     * @param entryType the entryType to set
     */
    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

}
