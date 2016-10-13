
package com.smartbt.girocheck.scan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MainResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MainResponseContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SessionTag" type="{https://SistemasGalileo.com/Services/}SessionTag" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MainResponseContainer", propOrder = {
   
})
@XmlSeeAlso({
    BalanceInquiryRes.class,
    CardReloadRes.class,
    CardReloadDataRes.class,
    CardToBankRes.class,
    CardToBankConfirmationRes.class,
    CheckAuthLocationConfigRes.class,
    CheckAuthRes.class,
    ActivityReportRes.class,
    TecnicardConfirmationRes.class
})
public abstract class MainResponseContainer {

    
   

   

}
