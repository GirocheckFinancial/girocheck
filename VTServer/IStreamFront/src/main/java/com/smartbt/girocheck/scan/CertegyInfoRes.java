package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
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
@XmlType( name = "CertegyInfoRes", propOrder = {
    "checkId",
    "resultCode",
    "resultMessage"
} )
public class CertegyInfoRes implements IBuilder {

    protected String checkId;
    private String resultCode;
    private String resultMessage;

    @Override
    public CertegyInfoRes build( Map map ) {
        checkId =  map.containsKey(ParameterName.CHECK_ID ) ? map.get( ParameterName.CHECK_ID ).toString() : null;
        resultCode = map.get( ParameterName.RESULT_CODE ).toString();
        resultMessage = map.get( ParameterName.RESULT_MESSAGE ).toString();
        return this;
    }
    public CertegyInfoRes mock(String user, String pass) {
        if (user.equals("sbt") && pass.equals("sbt")) {
            checkId = "checkId";
            resultCode = ResultCode.SUCCESS.getCode() + "";
            resultMessage = ResultMessage.SUCCESS.getMessage();
        } else {
            checkId = "checkId";
            resultCode = ResultCode.ISTREAM_LOGIN_FAILED.getCode() + "";
            resultMessage = ResultMessage.LOGIN_FAILED.getMessage();
        }
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

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    
           
}
