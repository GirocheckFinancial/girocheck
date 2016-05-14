package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import com.smartbt.vtsuite.util.MapUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CardToBankConfirmationRes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CardToBankRes">
 *   &lt;complexContent>
 *     &lt;extension base="{https://SistemasGalileo.com/Services/}MainResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="TransactionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "CardToBankConfirmationResponse", propOrder = {
    "transactionNumber",
    "amount",
    "date",
    "printLogo",
    "resultCode",
    "resultMessage"
} )
public class CardToBankConfirmationRes extends MainResponseContainer implements IBuilder {

    @XmlElement( name = "transactionNumber" )
    protected String transactionNumber;
    @XmlElement( name = "amount" )
    protected String amount;
    @XmlElement( name = "printLogo" )
    private String printLogo;
    @XmlElement( name = "date" )
    protected String date;
    
    @XmlElement( name = "resultCode" )
    protected String resultCode;
    @XmlElement( name = "resultMessage" )
    protected String resultMessage;

    @Override
    public CardToBankConfirmationRes build( Map map ) throws Exception {
        transactionNumber = MapUtil.getStringValueFromMap(map, ParameterName.TRANSACTION_NUMBER);
        amount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT);
        printLogo = MapUtil.getStringValueFromMap(map, ParameterName.PRINTLOGO);
        date = MapUtil.getStringValueFromMap(map, ParameterName.DATE);
        setResultCode( (String)map.get(ParameterName.RESULT_CODE));
        setResultMessage((String)map.get(ParameterName.RESULT_MESSAGE));
        return this;
    }

    public CardToBankConfirmationRes mock(){
         setTransactionNumber("transactionNumber");
         setAmount("ammount");
         setPrintLogo("01");
         Date d = new Date();
         SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
         setDate(format.format(d));
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
     * Gets the value of the transactionNumber property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * Sets the value of the transactionNumber property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTransactionNumber( String value ) {
        this.transactionNumber = value;
    }

    /**
     * Gets the value of the amount property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAmount( String value ) {
        this.amount = value;
    }

    /**
     * Gets the value of the date property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDate( String value ) {
        this.date = value;
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
