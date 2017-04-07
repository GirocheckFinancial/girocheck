
package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.manager.CertegyBusinessLogic;
import static com.smartbt.vtsuite.manager.CertegyBusinessLogic.CERTEGY_SITE_ID;
import static com.smartbt.vtsuite.manager.CertegyBusinessLogic.CERTEGY_VERSION;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PCARequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PCARequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TranType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SiteID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SSN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Check" type="{http://fis.certegy.pca.com/}Check" minOccurs="0"/>
 *         &lt;element name="Enroll" type="{http://fis.certegy.pca.com/}Enrollment" minOccurs="0"/>
 *         &lt;element name="Device" type="{http://fis.certegy.pca.com/}Device" minOccurs="0"/>
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
@XmlType(name = "PCARequest", propOrder = {
    "transID",
    "version",
    "tranType",
    "siteID",
    "ssn",
    "check",
    "enroll",
    "device",
    "roundtrip",
    "custom"
})
public class PCARequest {

    @XmlElement(name = "TransID", required = true)
    protected String transID;
    @XmlElement(name = "Version", required = true, defaultValue = "1.2")
    protected BigDecimal version;
    @XmlElement(name = "TranType", required = true)
    protected String tranType;
    @XmlElement(name = "SiteID", required = true)
    protected String siteID;
    @XmlElement(name = "SSN")
    protected String ssn;
    @XmlElement(name = "Check")
    protected Check check;
    @XmlElement(name = "Enroll")
    protected Enrollment enroll;
    @XmlElement(name = "Device")
    protected Device device;
    @XmlElement(name = "Roundtrip")
    protected List<String> roundtrip;
    @XmlElement(name = "Custom")
    protected List<Custom> custom;

    
    public static PCARequest build(Map map){
        PCARequest _this = new PCARequest();
        _this.setTransID(map.get(ParameterName.REQUEST_ID) + "");
      
        _this.setVersion(CERTEGY_VERSION);
        
        //TODO put tranType here
        _this.setTranType("C");
         
        //TODO put this in a System property var
        String certegyLocationId = (String)map.get(ParameterName.CERTEGY_LOCATION_ID); 
        _this.setSiteID(certegyLocationId);
        
        _this.setSSN((String)map.get(ParameterName.SSN));
        
        Check check = Check.build(map);
        _this.setCheck(check);
        
        Enrollment enrollment = Enrollment.build(map);
        _this.setEnroll(enrollment);
        
        
        return _this;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append("-------------  Certegy PCA Request ----------" ).append('\n');
        s.append("<PCARequest>" ).append('\n');
        s.append("    <TransID>").append(transID).append("</TransID>" ).append('\n');
        s.append("    <Version>").append(version).append("</Version>" ).append('\n');
        s.append("    <TranType>").append(tranType).append("</TranType>" ).append('\n');
        s.append("    <SiteID>").append(siteID).append("</SiteID>" ).append('\n');
        s.append("    <SSN>").append(ssn).append("</SSN>" ).append('\n');
        s.append( check.toString() ).append('\n');
        s.append( enroll.toString() ).append('\n');
        s.append("</PCARequest>" ).append('\n');
        
        return s.toString();
    }
    /**
     * Gets the value of the transID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransID() {
        return transID;
    }

    /**
     * Sets the value of the transID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransID(String value) {
        this.transID = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }

    /**
     * Gets the value of the tranType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranType() {
        return tranType;
    }

    /**
     * Sets the value of the tranType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranType(String value) {
        this.tranType = value;
    }

    /**
     * Gets the value of the siteID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteID() {
        return siteID;
    }

    /**
     * Sets the value of the siteID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteID(String value) {
        this.siteID = value;
    }

    /**
     * Gets the value of the ssn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSN() {
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
    public void setSSN(String value) {
        this.ssn = value;
    }

    /**
     * Gets the value of the check property.
     * 
     * @return
     *     possible object is
     *     {@link Check }
     *     
     */
    public Check getCheck() {
        return check;
    }

    /**
     * Sets the value of the check property.
     * 
     * @param value
     *     allowed object is
     *     {@link Check }
     *     
     */
    public void setCheck(Check value) {
        this.check = value;
    }

    /**
     * Gets the value of the enroll property.
     * 
     * @return
     *     possible object is
     *     {@link Enrollment }
     *     
     */
    public Enrollment getEnroll() {
        return enroll;
    }

    /**
     * Sets the value of the enroll property.
     * 
     * @param value
     *     allowed object is
     *     {@link Enrollment }
     *     
     */
    public void setEnroll(Enrollment value) {
        this.enroll = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link Device }
     *     
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link Device }
     *     
     */
    public void setDevice(Device value) {
        this.device = value;
    }

    /**
     * Gets the value of the roundtrip property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roundtrip property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoundtrip().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
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
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Custom }
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
