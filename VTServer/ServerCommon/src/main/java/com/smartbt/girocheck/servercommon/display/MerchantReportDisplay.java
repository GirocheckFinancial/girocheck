package com.smartbt.girocheck.servercommon.display;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejo
 */

@XmlRootElement
public class MerchantReportDisplay {

    private Integer id;
    private String legalName;
    private String phone;
    private String sic;
    private String description;    
    private String customerName;
    
    private String address;
    private String zipcode;
    private String state;
    private String city;
    private String idPosOrderExp;
    private String idTellerOrderExp;
    private String oEAgentNumber;

    public MerchantReportDisplay() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the legalName
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * @param legalName the legalName to set
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return the sic
     */
    public String getSic() {
        return sic;
    }

    /**
     * @param sic the sic to set
     */
    public void setSic(String sic) {
        this.sic = sic;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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
     * @return the idPosOrderExp
     */
    public String getIdPosOrderExp() {
        return idPosOrderExp;
    }

    /**
     * @param idPosOrderExp the idPosOrderExp to set
     */
    public void setIdPosOrderExp(String idPosOrderExp) {
        this.idPosOrderExp = idPosOrderExp;
    }

    /**
     * @return the idTellerOrderExp
     */
    public String getIdTellerOrderExp() {
        return idTellerOrderExp;
    }

    /**
     * @param idTellerOrderExp the idTellerOrderExp to set
     */
    public void setIdTellerOrderExp(String idTellerOrderExp) {
        this.idTellerOrderExp = idTellerOrderExp;
    }

    /**
     * @return the oEAgentNumber
     */
    public String getoEAgentNumber() {
        return oEAgentNumber;
    }

    /**
     * @param oEAgentNumber the oEAgentNumber to set
     */
    public void setoEAgentNumber(String oEAgentNumber) {
        this.oEAgentNumber = oEAgentNumber;
    }

    
}
