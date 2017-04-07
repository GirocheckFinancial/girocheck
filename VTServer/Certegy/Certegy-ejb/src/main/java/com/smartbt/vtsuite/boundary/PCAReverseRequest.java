package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import static com.smartbt.vtsuite.manager.CertegyBusinessLogic.CERTEGY_SITE_ID;
import static com.smartbt.vtsuite.manager.CertegyBusinessLogic.CERTEGY_VERSION;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PCAReverseRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="PCAReverseRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="SiteID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SSN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Check" type="{http://fis.certegy.pca.com/}Check"/>
 *         &lt;element name="Roundtrip" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="Custom" type="{http://fis.certegy.pca.com/}Custom" maxOccurs="50" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PCAReverseRequest", propOrder = {
    "transID",
    "version",
    "siteID",
    "ssn",
    "check",
    "roundtrip",
    "custom"
})
public class PCAReverseRequest {

    @XmlElement(name = "TransID", required = true)
    protected String transID;
    @XmlElement(name = "Version", required = true, defaultValue = "1.2")
    protected BigDecimal version;
    @XmlElement(name = "SiteID", required = true)
    protected String siteID;
    @XmlElement(name = "SSN")
    protected String ssn;
    @XmlElement(name = "Check", required = true)
    protected Check check;
    @XmlElement(name = "Roundtrip")
    protected List<String> roundtrip;
    @XmlElement(name = "Custom")
    protected List<Custom> custom;

    public static PCAReverseRequest build(Map map) { 
        PCAReverseRequest req = new PCAReverseRequest();
        Object transactionId = map.get(ParameterName.REQUEST_ID); 
        req.setTransID(transactionId.toString());
        req.setVersion(CERTEGY_VERSION);
        //TODO put this in a System property var
        req.setSiteID((String) map.get(ParameterName.CERTEGY_LOCATION_ID));

        req.setSSN((String) map.get(ParameterName.SSN));

        Check check = Check.build(map);
        req.setCheck(check);

        return req;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("-------------  Certegy PCAReverseRequest ----------").append('\n');
        s.append("<PCAReverseRequest>").append('\n');
        s.append("    <TransID>").append(transID).append("</TransID>").append('\n');
        s.append("    <Version>").append(version).append("</Version>").append('\n');
        s.append("    <SiteID>").append(siteID).append("</SiteID>").append('\n');
        s.append("    <SSN>").append(ssn).append("</SSN>").append('\n');
        s.append(check.toString()).append('\n');
        s.append("</PCAReverseRequest>").append('\n');

        return s.toString();
    }

    /**
     * Gets the value of the transID property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTransID() {
        return transID;
    }

    /**
     * Sets the value of the transID property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTransID(String value) {
        this.transID = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }

    /**
     * Gets the value of the siteID property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSiteID() {
        return siteID;
    }

    /**
     * Sets the value of the siteID property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSiteID(String value) {
        this.siteID = value;
    }

    /**
     * Gets the value of the ssn property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSSN() {
        return ssn;
    }

    /**
     * Sets the value of the ssn property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSSN(String value) {
        this.ssn = value;
    }

    /**
     * Gets the value of the check property.
     *
     * @return possible object is {@link Check }
     *
     */
    public Check getCheck() {
        return check;
    }

    /**
     * Sets the value of the check property.
     *
     * @param value allowed object is {@link Check }
     *
     */
    public void setCheck(Check value) {
        this.check = value;
    }

    /**
     * Gets the value of the roundtrip property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the roundtrip property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoundtrip().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     *
     *
     */
    public List<String> getRoundtrip() {
        if (roundtrip == null) {
            roundtrip = new ArrayList<String>();
        }
        return this.roundtrip;
    }

    /**
     * Gets the value of the custom property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the custom property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustom().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Custom }
     *
     *
     */
    public List<Custom> getCustom() {
        if (custom == null) {
            custom = new ArrayList<Custom>();
        }
        return this.custom;
    }

}
