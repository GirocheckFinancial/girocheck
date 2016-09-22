package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for identification complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="identification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dob" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identification", propOrder = {
    "address",
    "city",
    "dob",
    "firstName",
    "id",
    "lastName",
    "state",
    "zip"
})
public class Identification {

    protected String address;
    protected String city;
    protected String dob;
    protected String firstName;
    protected String id;
    protected String lastName;
    protected String state;
    protected String zip;

    public String getAsXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("      <Identification>");
        sb.append("             <address> " + address + " </address>");
        sb.append("             <city> " + city + " </city>");
        sb.append("             <dob> " + dob + " </dob>"); 
        sb.append("             <id> " + id + " </id>");
        sb.append("             <firstName> " + firstName + " </firstName>");
        sb.append("             <lastName> " + lastName + " </lastName>");
        sb.append("             <state> " + state + " </state>");
        sb.append("             <zip> " + zip + " </zip>");
        sb.append("      </Identification>");
        return sb.toString();
    }

    public String printAsXML() {
        StringBuilder sb = new StringBuilder();
        System.out.println("      <Identification>");
        System.out.println("             <address> " + address + " </address>");
        System.out.println("             <city> " + city + " </city>");
        System.out.println("             <dob> " + dob + " </dob>"); 
        System.out.println("             <id> " + id + " </id>");
        System.out.println("             <firstName> " + firstName + " </firstName>");
        System.out.println("             <lastName> " + lastName + " </lastName>");
        System.out.println("             <state> " + state + " </state>");
        System.out.println("             <zip> " + zip + " </zip>");
        System.out.println("      </Identification>");
        return sb.toString();
    }

    /**
     * Gets the value of the address property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the city property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the dob property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDob(String value) {
        this.dob = value;
    }

    /**
     * Gets the value of the firstName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the lastName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the state property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the zip property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setZip(String value) {
        this.zip = value;
    }

}
