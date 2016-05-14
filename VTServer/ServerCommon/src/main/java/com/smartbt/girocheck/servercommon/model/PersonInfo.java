
package com.smartbt.girocheck.servercommon.model;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alejo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "firstName",
    "middleName",
    "lastName",   
    "dob",
    "identification",
    "contactInfo",
    "appearance"
})
@XmlRootElement( name = "personInfo" )
public class PersonInfo implements Serializable{

    @XmlElement(name = "firstName")
    protected String firstName;
    @XmlElement(name = "middleName")
    private String middleName;
    @XmlElement(name = "lastName")
    protected String lastName;
    @XmlElement(name = "dob")
    protected String dob; // MMddyyyy
    @XmlElement(name = "identification")
    protected Identification identification;
    @XmlElement(name = "contactInfo")
    protected ContactInfo contactInfo;
    @XmlElement(name = "appearance")
    protected Appearance appearance;

    public PersonInfo() {
    }

    public Map toMap(){
        
        Map map = new HashMap();
        
        map.put(ParameterName.ADDRESS, getContactInfo().getAddress1());
        map.put(ParameterName.GENDER, getAppearance().getGender());
        map.put(ParameterName.CITY, getContactInfo().getCity());
        map.put(ParameterName.STATE, getContactInfo().getState());
        map.put(TransactionType.TRANSACTION_TYPE, TransactionType.PERSONAL_INFO);
        map.put(ParameterName.EXPIRATION_DATE, getIdentification().getExpDate());
        map.put(ParameterName.LAST_NAME, getLastName());
        map.put(ParameterName.ZIPCODE, getContactInfo().getZip());
        map.put(ParameterName.FIRST_NAME, getFirstName());
        map.put(ParameterName.MIDDLE_NAME, getMiddleName());
        map.put(ParameterName.BORNDATE, getDob());        
        map.put(ParameterName.IDSTATE, getIdentification().getState());
//        map.put(ParameterName.TELEPHONE, getTelephone());
//        map.put(ParameterName.EMAIL, getEmail());
//        map.put(ParameterName.COUNTRY, getCountry());
        map.put(ParameterName.COUNTRY, "US");
//        map.put(ParameterName.IDCOUNTRY, getIdCountry());
        map.put(ParameterName.IDCOUNTRY, "US");
  
        return map;
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
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
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

    /**
     * @return the contactInfo
     */
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    /**
     * @param contactInfo the contactInfo to set
     */
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * @return the appearance
     */
    public Appearance getAppearance() {
        return appearance;
    }

    /**
     * @param appearance the appearance to set
     */
    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    

}
