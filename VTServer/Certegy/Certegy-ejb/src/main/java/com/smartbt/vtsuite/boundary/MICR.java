
package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MICR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MICR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExpansionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntryType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Line" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MICR", propOrder = {
    "expansionType",
    "entryType",
    "line"
})
public class MICR {

    @XmlElement(name = "ExpansionType")
    protected String expansionType;
    @XmlElement(name = "EntryType", required = true, defaultValue = "S")
    protected String entryType;
    @XmlElement(name = "Line", required = true)
    protected String line;
    
    
    public static MICR build(Map map){
        MICR _this = new MICR();
        _this.setExpansionType("TOAD");
        _this.setEntryType("S");
        
        String originalMICR = (String)map.get(ParameterName.MICR);
        System.out.println("MICR.build() -> originalMICR = " + originalMICR);
        String translatedMICR = translateMICR(originalMICR);
        System.out.println("MICR.build() -> translatedMICR = " + translatedMICR);
        _this.setLine(translatedMICR);
        
        return _this;
    }
    
     private static String translateMICR(String originalMICR){
        if(originalMICR != null){
            return originalMICR.replaceAll("C", "O").replaceAll("A", "T");
        }
        return "";
    }
   
    
     @Override
    public String toString(){
        StringBuilder s = new StringBuilder(); 
        s.append("            <MICR>" ).append('\n');
        s.append("                <ExpansionType>").append(expansionType).append("</ExpansionType>" ).append('\n');
        s.append("                <EntryType>").append(entryType).append("</EntryType>" ).append('\n');
        s.append("                <Line>").append(line).append("</Line>" ).append('\n');
        s.append("            </MICR>" ).append('\n');
        return s.toString(); 
    }

    /**
     * Gets the value of the expansionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpansionType() {
        return expansionType;
    }

    /**
     * Sets the value of the expansionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpansionType(String value) {
        this.expansionType = value;
    }

    /**
     * Gets the value of the entryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryType() {
        return entryType;
    }

    /**
     * Sets the value of the entryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryType(String value) {
        this.entryType = value;
    }

    /**
     * Gets the value of the line property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLine() {
        return line;
    }

    /**
     * Sets the value of the line property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLine(String value) {
        this.line = value;
    }

}
