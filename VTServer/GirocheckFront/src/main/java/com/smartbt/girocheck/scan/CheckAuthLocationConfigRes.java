package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import com.smartbt.vtsuite.util.MapUtil;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CheckAuthLocationConfigRes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CheckAuthLocationConfigRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configList" type="{http://scan.girocheck.smartbt.com/}code" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAuthLocationConfigRes", propOrder = {
    "resultCode",
    "resultMessage",
    "authFeem",
    "authFeep",
    "crdldf",
    "activationFee" 
   
})
public class CheckAuthLocationConfigRes extends MainResponseContainer implements IBuilder {

    @XmlElement( name = "resultCode" )
    protected String resultCode;
    @XmlElement( name = "resultMessage" )
    protected String resultMessage;
    @XmlElement( name = "authFeem" )
    private String authFeem;
    @XmlElement( name = "authFeep" )
    private String authFeep;
    @XmlElement( name = "crdldf" )
    private String crdldf;
    @XmlElement( name = "activationFee" )
    private Double activationFee; 
    
    @Override
    public CheckAuthLocationConfigRes build(Map map) throws Exception {
         LogUtil.logAndStore( "Front", "Login -> RESULT_MESSAGE:: " + (String) map.get(ParameterName.RESULT_MESSAGE)  );
        setResultCode((String) map.get(ParameterName.RESULT_CODE));
        setResultMessage((String) map.get(ParameterName.RESULT_MESSAGE));
        setAuthFeem( MapUtil.getStringValueFromMap( map, ParameterName.AUTH_FEEM));
        setAuthFeep( MapUtil.getStringValueFromMap( map, ParameterName.AUTH_FEEP));
        setCrdldf(MapUtil.getStringValueFromMap( map, ParameterName.CRDLDF)); 
        setActivationFee(MapUtil.getDoubleValueFromMap(map, ParameterName.ACTIVATION_FEE, false));
         
        return this;
    }
    
    
     public CheckAuthLocationConfigRes mock(){
         this.setAuthFeem( "1.5" );
         this.setAuthFeep( "0.015" );
         this.setCrdldf("3.95" );
        
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

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @return the authFeem
     */
    public String getAuthFeem() {
        return authFeem;
    }

    /**
     * @param authFeem the authFeem to set
     */
    public void setAuthFeem( String authFeem ) {
        this.authFeem = authFeem;
    }

    /**
     * @return the authFeep
     */
    public String getAuthFeep() {
        return authFeep;
    }

    /**
     * @param authFeep the authFeep to set
     */
    public void setAuthFeep( String authFeep ) {
        this.authFeep = authFeep;
    }

    /**
     * @return the crdldf
     */
    public String getCrdldf() {
        return crdldf;
    }

    /**
     * @param crdldf the crdldf to set
     */
    public void setCrdldf( String crdldf ) {
        this.crdldf = crdldf;
    } 

    /**
     * @return the activationFee
     */
    public Double getActivationFee() {
        return activationFee;
    }

    /**
     * @param activationFee the activationFee to set
     */
    public void setActivationFee(Double activationFee) {
        this.activationFee = activationFee;
    }
     
}
