
package com.smartbt.vtsuite.boundary.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BasicExternalInfoObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasicExternalInfoObject">
 *   &lt;complexContent>
 *     &lt;extension base="{https://SistemasGalileo.com/Services/}BasicInfoObject">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasicExternalInfoObject")
@XmlSeeAlso({
    Transaction.class
})
public abstract class BasicExternalInfoObject
    extends BasicInfoObject
{


}
