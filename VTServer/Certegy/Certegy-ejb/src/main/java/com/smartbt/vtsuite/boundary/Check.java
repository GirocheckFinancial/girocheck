package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.text.DecimalFormat;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Check complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="Check">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MICR" type="{http://fis.certegy.pca.com/}MICR"/>
 *         &lt;element name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Check", propOrder = {
    "amount",
    "micr",
    "issueDate",
    "type"
})
public class Check {

    @XmlElement(name = "Amount", required = true)
    protected String amount;
    @XmlElement(name = "MICR", required = true)
    protected MICR micr;
    @XmlElement(name = "IssueDate")
    protected String issueDate;
    @XmlElement(name = "Type")
    protected String type;  //This is optional

    public static Check build(Map map) {
        Check _this = new Check();
        Double amount = (Double) map.get(ParameterName.AMMOUNT);
        
        DecimalFormat twoPlaces = new DecimalFormat("0.00");
        String amountString = twoPlaces.format(amount);
        
        _this.setAmount(amountString);

        MICR micr = MICR.build(map);
        _this.setMICR(micr);

        String issueDate = (String) map.get(ParameterName.CHECK_ISSUE_DATE);
        
        if(issueDate!= null){ 
             _this.setIssueDate(issueDate.replaceAll("-", ""));
        }
      

        _this.setType((String) map.get(ParameterName.CHECK_TYPE));  //This is optional

        return _this;
    }
    
 
  

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("        <Check>" ).append('\n');  
        s.append("            <Amount>").append(amount).append("</Amount>" ).append('\n');
        s.append(micr.toString() ).append('\n');
        s.append("            <IssueDate>").append(issueDate).append("</IssueDate>" ).append('\n');
        s.append("            <Type>").append(type).append("</Type>" ).append('\n');
        s.append("        <Check>" ).append('\n');
        return s.toString();
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
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the micr property.
     *
     * @return possible object is {@link MICR }
     *
     */
    public MICR getMICR() {
        return micr;
    }

    /**
     * Sets the value of the micr property.
     *
     * @param value allowed object is {@link MICR }
     *
     */
    public void setMICR(MICR value) {
        this.micr = value;
    }

    /**
     * Gets the value of the issueDate property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
    }

}
