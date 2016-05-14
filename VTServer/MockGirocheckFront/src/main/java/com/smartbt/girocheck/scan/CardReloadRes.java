package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import com.smartbt.vtsuite.util.MapUtil;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CardReloadRes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CardReloadRes">
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
@XmlType( name = "CardReloadRes", propOrder = {
    "checkId",
    "resultCode",
    "resultMessage"
} )
public class CardReloadRes extends MainResponseContainer implements IBuilder {
    @XmlElement( name = "checkId" )
    protected String checkId;

    @XmlElement( name = "resultCode" )
    protected String resultCode;
    @XmlElement( name = "resultMessage" )
    protected String resultMessage;
    @Override
    public CardReloadRes build( Map map ) throws Exception {
        checkId = MapUtil.getStringValueFromMap(map, ParameterName.CHECK_ID);
        setResultCode( (String)map.get(ParameterName.RESULT_CODE));
        setResultMessage((String)map.get(ParameterName.RESULT_MESSAGE));
        return this;
    }
    
    public CardReloadRes mock(){
        setCheckId("checkId");
        setResultCode(ResultCode.SUCCESS.getCode() + "");
        setResultMessage(ResultMessage.SUCCESS.getMessage());
        return this;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
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

}
