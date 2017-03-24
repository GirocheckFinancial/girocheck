
package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Date;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DOB complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DOB">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Month" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Day" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DOB", propOrder = {
    "year",
    "month",
    "day"
})
public class DOB {

    @XmlElement(name = "Year", required = true)
    protected String year;
    @XmlElement(name = "Month", required = true)
    protected String month;
    @XmlElement(name = "Day", required = true)
    protected String day;
    
    public static DOB build(Map map){
        Date dateOfBirth = (Date)map.get(ParameterName.BORNDATE_AS_DATE);
        DOB dob = new DOB();
        String month = 1 + dateOfBirth.getMonth() + "";
        if(month.length() == 1){
            month = "0" + month;
        }
        
        String day = dateOfBirth.getDate() + "";
        if(day.length() == 1){
            day = "0" + day;
        }
        
        dob.setYear(1900 + dateOfBirth.getYear() + "");
        dob.setMonth(month);
        dob.setDay(day);
        
        return dob;
    } 
    
    
     @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("            <DOB>" ).append('\n');
        s.append("                <Year>").append(year).append("</Year>" ).append('\n'); 
        s.append("                <Month>").append(month).append("</Month>" ).append('\n'); 
        s.append("                <Day>").append(day).append("</Day>" ).append('\n'); 
        s.append("            <DOB>" ).append('\n');
        return s.toString();
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Gets the value of the month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonth(String value) {
        this.month = value;
    }

    /**
     * Gets the value of the day property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDay() {
        return day;
    }

    /**
     * Sets the value of the day property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDay(String value) {
        this.day = value;
    }

}
