package com.smartbt.girocheck.servercommon.display;

/**
 *
 * @author Alejo
 */


public class AddressReportDisplay {
    
    private Integer id;
    private String address;
    private String city;
    private CountryDisplay country;
    private String zip;
    private StateDisplay state;

    public AddressReportDisplay() {
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
     * @return the country
     */
    public CountryDisplay getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(CountryDisplay country) {
        this.country = country;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the state
     */
    public StateDisplay getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(StateDisplay state) {
        this.state = state;
    }

}
