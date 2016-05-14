
package com.smartbt.vtsuite.boundary.OEWS;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfObtenUltimaFacHResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfObtenUltimaFacHResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObtenUltimaFacHResult" type="{http://tempuri.org/}ObtenUltimaFacHResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfObtenUltimaFacHResult", propOrder = {
    "obtenUltimaFacHResult"
})
public class ArrayOfObtenUltimaFacHResult {

    @XmlElement(name = "ObtenUltimaFacHResult", nillable = true)
    protected List<ObtenUltimaFacHResult> obtenUltimaFacHResult;

    /**
     * Gets the value of the obtenUltimaFacHResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the obtenUltimaFacHResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObtenUltimaFacHResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObtenUltimaFacHResult }
     * 
     * 
     */
    public List<ObtenUltimaFacHResult> getObtenUltimaFacHResult() {
        if (obtenUltimaFacHResult == null) {
            obtenUltimaFacHResult = new ArrayList<ObtenUltimaFacHResult>();
        }
        return this.obtenUltimaFacHResult;
    }

}
