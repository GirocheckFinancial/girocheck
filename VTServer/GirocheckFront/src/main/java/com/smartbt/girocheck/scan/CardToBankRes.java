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
 * Java class for CardToBankRes complex type.
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
@XmlType( name = "CardToBankResponse", propOrder = {
    "merchantName",
    "customerAddress",
    "customerName", 
    "bankName",
    "routingBankNumber",
    "accountNumber",
    "existAch",
    "resultCode",
    "resultMessage"
} )
public class CardToBankRes extends MainResponseContainer implements IBuilder {

//    @XmlElement( name = "TransactionNumber" )
//    protected String transactionNumber;
//    @XmlElement( name = "Amount" )
//    protected String amount;
//    @XmlElement( name = "Date" )
//    protected String date;
//    @XmlElement( name = "ExistAch" )
//    private String existAch;
    @XmlElement( name = "merchantName" )
    private String merchantName;
    @XmlElement( name = "customerAddress" )
    private String customerAddress;
    @XmlElement( name = "customerName" )
    private String customerName; 
    @XmlElement( name = "bankName" )
    private String bankName;
    @XmlElement( name = "routingBankNumber" )
    private String routingBankNumber;
    @XmlElement( name = "accountNumber" )
    private String accountNumber;
    @XmlElement( name = "existAch" )
    private String existAch;
    
    @XmlElement( name = "resultCode" )
    protected String resultCode;
    @XmlElement( name = "resultMessage" )
    protected String resultMessage;

    @Override
    public CardToBankRes build( Map map ) throws Exception {

        existAch = MapUtil.getStringValueFromMap(map, ParameterName.EXISTACH);
        setMerchantName(MapUtil.getStringValueFromMap(map, ParameterName.MERCHANT_NAME));
        setCustomerAddress(MapUtil.getStringValueFromMap(map, ParameterName.CUSTUMER_ADDRESS)); 
        setCustomerName(MapUtil.getStringValueFromMap(map, ParameterName.CUSTUMER_NAME)); 
        setBankName(MapUtil.getStringValueFromMap(map, ParameterName.BANK_NAME));
        setRoutingBankNumber(MapUtil.getStringValueFromMap(map, ParameterName.ROUTING_BANK_NUMBER));
        setAccountNumber(MapUtil.getStringValueFromMap(map, ParameterName.ACCOUNT_NUMBER));
        setResultCode( (String)map.get(ParameterName.RESULT_CODE));
        setResultMessage((String)map.get(ParameterName.RESULT_MESSAGE));
        return this;
    }

    public CardToBankRes mock(){
         setMerchantName("MerchantName");
         setCustomerAddress("CustomerAddress");
         setCustomerName("CustomerName");
         setBankName("BankName");
         setRoutingBankNumber("RoutingBankNumber");
         setAccountNumber("AccountNumber");
         setExistAch("False");

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
    
    
//    /**
//     * Gets the value of the transactionNumber property.
//     *
//     * @return possible object is {@link String }
//     *
//     */
//    public String getTransactionNumber() {
//        return transactionNumber;
//    }
//
//    /**
//     * Sets the value of the transactionNumber property.
//     *
//     * @param value allowed object is {@link String }
//     *
//     */
//    public void setTransactionNumber( String value ) {
//        this.transactionNumber = value;
//    }
//
//    /**
//     * Gets the value of the amount property.
//     *
//     * @return possible object is {@link String }
//     *
//     */
//    public String getAmount() {
//        return amount;
//    }
//
//    /**
//     * Sets the value of the amount property.
//     *
//     * @param value allowed object is {@link String }
//     *
//     */
//    public void setAmount( String value ) {
//        this.amount = value;
//    }
//
//    /**
//     * Gets the value of the date property.
//     *
//     * @return possible object is {@link String }
//     *
//     */
//    public String getDate() {
//        return date;
//    }
//
//    /**
//     * Sets the value of the date property.
//     *
//     * @param value allowed object is {@link String }
//     *
//     */
//    public void setDate( String value ) {
//        this.date = value;
//    }

    /**
     * @return the existAch
     */
    public String isExistAch() {
        return existAch;
    }

    /**
     * @param existAch the existAch to set
     */
    public void setExistAch(String existAch) {
        this.existAch = existAch;
    }

    /**
     * @return the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the routingBankNumber
     */
    public String getRoutingBankNumber() {
        return routingBankNumber;
    }

    /**
     * @param routingBankNumber the routingBankNumber to set
     */
    public void setRoutingBankNumber(String routingBankNumber) {
        this.routingBankNumber = routingBankNumber;
    }

    /**
     * @return the accountNumer
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumer the accountNumer to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the merchantName
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param merchantName the merchantName to set
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
 

}
