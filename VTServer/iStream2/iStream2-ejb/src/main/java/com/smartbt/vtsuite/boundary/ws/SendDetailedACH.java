
package com.smartbt.vtsuite.boundary.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendDetailedACH complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendDetailedACH">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aba" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="depositName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditOrDebit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entryClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="traceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discretionaryData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineItem" type="{http://web.service.fileloader.tc.com/}lineItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendDetailedACH", propOrder = {
    "username",
    "password",
    "name",
    "aba",
    "dda",
    "accountType",
    "locationId",
    "amount",
    "depositName",
    "creditOrDebit",
    "entryClassCode",
    "checkSerialNumber",
    "addenda",
    "traceNumber",
    "discretionaryData",
    "companyDescription",
    "terminalCity",
    "terminalState",
    "effectiveDate",
    "lineItem"
})
public class SendDetailedACH {

    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String aba;
    @XmlElement(required = true)
    protected String dda;
    @XmlElement(required = true)
    protected String accountType;
    protected int locationId;
    @XmlElement(required = true)
    protected String amount;
    @XmlElementRef(name = "depositName", type = JAXBElement.class, required = false)
    protected JAXBElement<String> depositName;
    @XmlElement(required = true)
    protected String creditOrDebit;
    @XmlElementRef(name = "entryClassCode", type = JAXBElement.class, required = false)
    protected JAXBElement<String> entryClassCode;
    @XmlElementRef(name = "checkSerialNumber", type = JAXBElement.class, required = false)
    protected JAXBElement<String> checkSerialNumber;
    @XmlElementRef(name = "addenda", type = JAXBElement.class, required = false)
    protected JAXBElement<String> addenda;
    @XmlElementRef(name = "traceNumber", type = JAXBElement.class, required = false)
    protected JAXBElement<String> traceNumber;
    @XmlElementRef(name = "discretionaryData", type = JAXBElement.class, required = false)
    protected JAXBElement<String> discretionaryData;
    @XmlElementRef(name = "companyDescription", type = JAXBElement.class, required = false)
    protected JAXBElement<String> companyDescription;
    @XmlElementRef(name = "terminalCity", type = JAXBElement.class, required = false)
    protected JAXBElement<String> terminalCity;
    @XmlElementRef(name = "terminalState", type = JAXBElement.class, required = false)
    protected JAXBElement<String> terminalState;
    @XmlElementRef(name = "effectiveDate", type = JAXBElement.class, required = false)
    protected JAXBElement<String> effectiveDate;
    @XmlElement(nillable = true)
    protected List<LineItem> lineItem;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the aba property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAba() {
        return aba;
    }

    /**
     * Sets the value of the aba property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAba(String value) {
        this.aba = value;
    }

    /**
     * Gets the value of the dda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDda() {
        return dda;
    }

    /**
     * Sets the value of the dda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDda(String value) {
        this.dda = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the locationId property.
     * 
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Sets the value of the locationId property.
     * 
     */
    public void setLocationId(int value) {
        this.locationId = value;
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
     * Gets the value of the depositName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositName() {
        return depositName;
    }

    /**
     * Sets the value of the depositName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositName(JAXBElement<String> value) {
        this.depositName = value;
    }

    /**
     * Gets the value of the creditOrDebit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditOrDebit() {
        return creditOrDebit;
    }

    /**
     * Sets the value of the creditOrDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditOrDebit(String value) {
        this.creditOrDebit = value;
    }

    /**
     * Gets the value of the entryClassCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntryClassCode() {
        return entryClassCode;
    }

    /**
     * Sets the value of the entryClassCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntryClassCode(JAXBElement<String> value) {
        this.entryClassCode = value;
    }

    /**
     * Gets the value of the checkSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCheckSerialNumber() {
        return checkSerialNumber;
    }

    /**
     * Sets the value of the checkSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCheckSerialNumber(JAXBElement<String> value) {
        this.checkSerialNumber = value;
    }

    /**
     * Gets the value of the addenda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAddenda() {
        return addenda;
    }

    /**
     * Sets the value of the addenda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAddenda(JAXBElement<String> value) {
        this.addenda = value;
    }

    /**
     * Gets the value of the traceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTraceNumber() {
        return traceNumber;
    }

    /**
     * Sets the value of the traceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTraceNumber(JAXBElement<String> value) {
        this.traceNumber = value;
    }

    /**
     * Gets the value of the discretionaryData property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDiscretionaryData() {
        return discretionaryData;
    }

    /**
     * Sets the value of the discretionaryData property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDiscretionaryData(JAXBElement<String> value) {
        this.discretionaryData = value;
    }

    /**
     * Gets the value of the companyDescription property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCompanyDescription() {
        return companyDescription;
    }

    /**
     * Sets the value of the companyDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCompanyDescription(JAXBElement<String> value) {
        this.companyDescription = value;
    }

    /**
     * Gets the value of the terminalCity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTerminalCity() {
        return terminalCity;
    }

    /**
     * Sets the value of the terminalCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTerminalCity(JAXBElement<String> value) {
        this.terminalCity = value;
    }

    /**
     * Gets the value of the terminalState property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTerminalState() {
        return terminalState;
    }

    /**
     * Sets the value of the terminalState property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTerminalState(JAXBElement<String> value) {
        this.terminalState = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEffectiveDate(JAXBElement<String> value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the lineItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineItem }
     * 
     * 
     */
    public List<LineItem> getLineItem() {
        if (lineItem == null) {
            lineItem = new ArrayList<LineItem>();
        }
        return this.lineItem;
    }

}
