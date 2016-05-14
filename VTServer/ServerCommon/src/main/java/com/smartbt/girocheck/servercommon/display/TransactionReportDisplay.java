package com.smartbt.girocheck.servercommon.display;

import com.smartbt.girocheck.servercommon.model.CreditCard;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejo
 */

@XmlRootElement
public class TransactionReportDisplay {

    private int id;
    
    private ClientReportDisplay client;
    private TerminalReportDisplay terminal;
    private MerchantReportDisplay merchant;
    private CreditCard card;
    
    private String maskCardNumber;

    private String operation;

    private Integer resultCode;

    private String resultMessage;

    private Date dateTime;

    private Integer transactionType;
    private String type;
    
    private Double amount;
    private Double feeAmount;
    private Double payoutAmount;
    private String requestId;
    private String hostCode;
    
    //paymentCheck
    private String checkNumber;
    
    private String makerName;

    private java.sql.Blob checkFront;

    
    public TransactionReportDisplay(){}

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the client
     */
    public ClientReportDisplay getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(ClientReportDisplay client) {
        this.client = client;
    }

    /**
     * @return the terminal
     */
    public TerminalReportDisplay getTerminal() {
        return terminal;
    }

    /**
     * @param terminal the terminal to set
     */
    public void setTerminal(TerminalReportDisplay terminal) {
        this.terminal = terminal;
    }

    /**
     * @return the merchant
     */
    public MerchantReportDisplay getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(MerchantReportDisplay merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the card
     */
    public CreditCard getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(CreditCard card) {
        this.card = card;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return the resultCode
     */
    public Integer getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(Integer resultCode) {
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

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the transactionType
     */
    public Integer getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the feeAmount
     */
    public Double getFeeAmount() {
        return feeAmount;
    }

    /**
     * @param feeAmount the feeAmount to set
     */
    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * @return the payoutAmount
     */
    public Double getPayoutAmount() {
        return payoutAmount;
    }

    /**
     * @param payoutAmount the payoutAmount to set
     */
    public void setPayoutAmount(Double payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    /**
     * @return the checkFront
     */
    public java.sql.Blob getCheckFront() {
        return checkFront;
    }

    /**
     * @param checkFront the checkFront to set
     */
    public void setCheckFront(java.sql.Blob checkFront) {
        this.checkFront = checkFront;
    }

    /**
     * @return the maskCardNumber
     */
    public String getMaskCardNumber() {
        return maskCardNumber;
    }

    /**
     * @param maskCardNumber the maskCardNumber to set
     */
    public void setMaskCardNumber(String maskCardNumber) {
        this.maskCardNumber = maskCardNumber;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the checkNumber
     */
    public String getCheckNumber() {
        return checkNumber;
    }

    /**
     * @param checkNumber the checkNumber to set
     */
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
     * @return the makerName
     */
    public String getMakerName() {
        return makerName;
    }

    /**
     * @param makerName the makerName to set
     */
    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    /**
     * @return the hostCode
     */
    public String getHostCode() {
        return hostCode;
    }

    /**
     * @param hostCode the hostCode to set
     */
    public void setHostCode(String hostCode) {
        this.hostCode = hostCode;
    }

    
}