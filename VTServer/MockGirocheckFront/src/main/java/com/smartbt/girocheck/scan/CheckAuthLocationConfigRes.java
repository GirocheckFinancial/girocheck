package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import com.smartbt.vtsuite.util.MapUtil;
import java.util.ArrayList;
import java.util.List;
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
    "param1",
    "param2",
    "param3",
    "param4"
   
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
    @XmlElement( name = "param1" )
    private String param1;
    @XmlElement( name = "param2" )
    private String param2;
    @XmlElement( name = "param3" )
    private String param3;
    @XmlElement( name = "param4" )
    private String param4;
    
    @Override
    public CheckAuthLocationConfigRes build(Map map) throws Exception {

        setResultCode((String) map.get(ParameterName.RESULT_CODE));
        setResultMessage((String) map.get(ParameterName.RESULT_MESSAGE));

        
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
     * @return the param1
     */
    public String getParam1() {
        return param1;
    }

    /**
     * @param param1 the param1 to set
     */
    public void setParam1( String param1 ) {
        this.param1 = param1;
    }

    /**
     * @return the param2
     */
    public String getParam2() {
        return param2;
    }

    /**
     * @param param2 the param2 to set
     */
    public void setParam2( String param2 ) {
        this.param2 = param2;
    }

    /**
     * @return the param3
     */
    public String getParam3() {
        return param3;
    }

    /**
     * @param param3 the param3 to set
     */
    public void setParam3( String param3 ) {
        this.param3 = param3;
    }

    /**
     * @return the param4
     */
    public String getParam4() {
        return param4;
    }

    /**
     * @param param4 the param4 to set
     */
    public void setParam4( String param4 ) {
        this.param4 = param4;
    }

     
}
