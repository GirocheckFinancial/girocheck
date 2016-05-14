package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.jboss.jandex.Main;

/**
 * <p>
 * Java class for PersonalInfoRes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CheckAuthRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "CheckAuthRes", propOrder = {
    "checkId",
    "resultCode",
    "resultMessage"
} )
public class PersonalInfoRes implements IBuilder {

    protected String checkId;
    private String resultCode;
    private String resultMessage;

    @Override
    public PersonalInfoRes build( Map map ) {
        checkId = map.get( ParameterName.CHECK_ID ).toString();
        setResultCode(map.get( ParameterName.RESULT_CODE ).toString());
        setResultMessage(map.get( ParameterName.RESULT_MESSAGE ).toString());
        return this;
    }

    /**
     * Gets the value of the checkId property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCheckId() {
        return checkId;
    }

    /**
     * Sets the value of the checkId property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCheckId( String value ) {
        this.checkId = value;
    }

    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    
           
}
