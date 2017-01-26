
package com.smartbt.vtsuite.boundary.client;

import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardCreationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardCreationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://SistemasGalileo.com/Services/}MainResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="CardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActualCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EmbossedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VerificationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardCreationResponse", propOrder = {
    "cardNumber",
    "actualCardNumber",
    "embossedName",
    "expirationDate",
    "verificationCode"
})
public class CardCreationResponse
    extends MainResponseContainer implements IMap{
    private static String EXPECTED_RESULT_CODE = "000000";
    
    @XmlElement(name = "CardNumber")
    protected String cardNumber;
    @XmlElement(name = "ActualCardNumber")
    protected String actualCardNumber;
    @XmlElement(name = "EmbossedName")
    protected String embossedName;
    @XmlElement(name = "ExpirationDate")
    protected String expirationDate;
    @XmlElement(name = "VerificationCode")
    protected String verificationCode;

    
        @Override
    public Map toMap() {
     Map map = super.getMap(EXPECTED_RESULT_CODE);
       
         map.put(ParameterName.CARD_NUMBER, cardNumber);
         map.put(ParameterName.ACTUAL_CARD_NUMBER, actualCardNumber);
         map.put(ParameterName.EMBOSSED_NAME, embossedName);
         map.put(ParameterName.EXPIRATION_DATE, expirationDate);
         map.put(ParameterName.VERIFICATION_CODE, verificationCode);
       return map;
    }
    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the actualCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActualCardNumber() {
        return actualCardNumber;
    }

    /**
     * Sets the value of the actualCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActualCardNumber(String value) {
        this.actualCardNumber = value;
    }

    /**
     * Gets the value of the embossedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmbossedName() {
        return embossedName;
    }

    /**
     * Sets the value of the embossedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmbossedName(String value) {
        this.embossedName = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the verificationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * Sets the value of the verificationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerificationCode(String value) {
        this.verificationCode = value;
    }

}
