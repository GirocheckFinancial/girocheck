package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import com.smartbt.vtsuite.util.MapUtil;
import java.util.Map;
import javax.jms.Session;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 Java class for BalanceInquiryRes complex type.

 <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="BalanceInquiryRes">
 *   &lt;complexContent>
 *     &lt;extension base="{https://SistemasGalileo.com/Services/}MainResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="Balance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InTransitFunds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "BalanceInquiryResponse", propOrder = {
    "balance",
    "inTransitFunds",
    "printLogo",
    "resultCode",
    "resultMessage"
} )
public class BalanceInquiryRes extends MainResponseContainer implements IBuilder {

    @XmlElement( name = "Balance" )
    protected String balance;
    @XmlElement( name = "InTransitFunds" )
    protected String inTransitFunds;
    @XmlElement( name = "PrintLogo" )
    private String printLogo;
    @XmlElement( name = "resultCode" )
    protected String resultCode;
    @XmlElement( name = "resultMessage" )
    protected String resultMessage;

    @Override
    public BalanceInquiryRes build(Map map) throws Exception {
        balance =  MapUtil.getStringValueFromMap(map, ParameterName.BALANCE);
        inTransitFunds = MapUtil.getStringValueFromMap(map, ParameterName.IN_TRANSIT_FUNDS);
        printLogo = MapUtil.getStringValueFromMap(map, ParameterName.PRINTLOGO);
        
        setResultCode( (String)map.get(ParameterName.RESULT_CODE));
        setResultMessage((String)map.get(ParameterName.RESULT_MESSAGE));
        
        return this;
    }
    
    public BalanceInquiryRes mock(){
        setBalance("balance");
        setInTransitFunds("InTransitFunds");
        setPrintLogo("01");
        setResultCode(ResultCode.SUCCESS.getCode() + "");
        setResultMessage(ResultMessage.SUCCESS.getMessage());
        return this;
    }

    /**
     * Gets the value of the balance property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBalance() {
        return balance;
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
     * Sets the value of the balance property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setBalance( String value ) {
        this.balance = value;
    }

    /**
     * Gets the value of the inTransitFunds property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getInTransitFunds() {
        return inTransitFunds;
    }

    /**
     * Sets the value of the inTransitFunds property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setInTransitFunds( String value ) {
        this.inTransitFunds = value;
    }

    /**
     * @return the printLogo
     */
    public String getPrintLogo() {
        return printLogo;
    }

    /**
     * @param printLogo the printLogo to set
     */
    public void setPrintLogo(String printLogo) {
        this.printLogo = printLogo;
    }

}
