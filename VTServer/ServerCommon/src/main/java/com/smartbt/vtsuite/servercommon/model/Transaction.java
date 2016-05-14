/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: SMART BUSINESS TECHNOLOGY
 * License Type: Purchased
 */
package com.smartbt.vtsuite.servercommon.model;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;
/**
 * Stores all transactions.
 */
@XmlRootElement
public class Transaction implements Serializable {
	public Transaction() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Terminal terminal;
	
	private com.smartbt.vtsuite.servercommon.model.Client client;
	
	private com.smartbt.vtsuite.servercommon.model.Clerk clerk;
	
	private com.smartbt.vtsuite.servercommon.model.Host host;
	
	private com.smartbt.vtsuite.servercommon.model.Operation operation;
	
	private com.smartbt.vtsuite.servercommon.model.Mode mode;
	
	private com.smartbt.vtsuite.servercommon.model.CardBrand cardBrand;
	
	private com.smartbt.vtsuite.servercommon.model.Signature signature;
	
	private com.smartbt.vtsuite.servercommon.model.Account account;
	
	private double subTotalAmount;
	
	private double taxAmount;
	
	private double tipAmount;
	
	private double totalAmount;
	
	private String disposition;
	
	private int sequence;
	
	private String approvalNumber;
	
	private String approvalCode;
	
	private String accountSuffix;
	
	private Boolean voided;
	
	private String poNumber;
	
	private String invoiceNumber;
	
	private java.util.Date createdAt;
	
	private java.util.Date updatedAt;
	
	private String geoLocation;
	
	private Boolean finalized;
	
	private Boolean hostCapture;
	
	private String retrievalData;
	
	private Integer batchNumber;
	
	private String cvvResult;
	
	private Integer entryMethod;
	
	private String cardHolderEmail;
	
	private Integer idOriginalTransaction;
        
        
        
        
	
	/**
	 * Table record identification.
	 */
	public void setId(int value) {
		this.id = value;
	}
	
	/**
	 * Table record identification.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Transaction's subtotal amount.
	 */
	public void setSubTotalAmount(double value) {
		this.subTotalAmount = value;
	}
	
	/**
	 * Transaction's subtotal amount.
	 */
	public double getSubTotalAmount() {
		return subTotalAmount;
	}
	
	/**
	 * Transaction's tax amount.
	 */
	public void setTaxAmount(double value) {
		this.taxAmount = value;
	}
	
	/**
	 * Transaction's tax amount.
	 */
	public double getTaxAmount() {
		return taxAmount;
	}
	
	/**
	 * Transaction's tip amount.
	 */
	public void setTipAmount(double value) {
		this.tipAmount = value;
	}
	
	/**
	 * Transaction's tip amount.
	 */
	public double getTipAmount() {
		return tipAmount;
	}
	
	/**
	 * Transaction's total amount. This is the addition of "sub_total_amount", "tax_amount" and "tip_amount".
	 */
	public void setTotalAmount(double value) {
		this.totalAmount = value;
	}
	
	/**
	 * Transaction's total amount. This is the addition of "sub_total_amount", "tax_amount" and "tip_amount".
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * Transaction's disposition. Eg: "APPROVED", "DENIED".
	 */
	public void setDisposition(String value) {
		this.disposition = value;
	}
	
	/**
	 * Transaction's disposition. Eg: "APPROVED", "DENIED".
	 */
	public String getDisposition() {
		return disposition;
	}
	
	/**
	 * The sequence number.
	 */
	public void setSequence(int value) {
		this.sequence = value;
	}
	
	/**
	 * The sequence number.
	 */
	public int getSequence() {
		return sequence;
	}
	
	/**
	 * Transaction's approval number.
	 */
	public void setApprovalNumber(String value) {
		this.approvalNumber = value;
	}
	
	/**
	 * Transaction's approval number.
	 */
	public String getApprovalNumber() {
		return approvalNumber;
	}
	
	/**
	 * Transaction's approval code.
	 */
	public void setApprovalCode(String value) {
		this.approvalCode = value;
	}
	
	/**
	 * Transaction's approval code.
	 */
	public String getApprovalCode() {
		return approvalCode;
	}
	
	/**
	 * Last four digit from the Primary Account Number.
	 */
	public void setAccountSuffix(String value) {
		this.accountSuffix = value;
	}
	
	/**
	 * Last four digit from the Primary Account Number.
	 */
	public String getAccountSuffix() {
		return accountSuffix;
	}
	
	/**
	 * Flag to determine whether the transaction was deleted or not.
	 */
        @JsonIgnore
	public void setVoided(boolean value) {
		setVoided(new Boolean(value));
	}
	
	/**
	 * Flag to determine whether the transaction was deleted or not.
	 */
	public void setVoided(Boolean value) {
		this.voided = value;
	}
	
	/**
	 * Flag to determine whether the transaction was deleted or not.
	 */
	public Boolean getVoided() {
		return voided;
	}
	
	public void setPoNumber(String value) {
		this.poNumber = value;
	}
	
	public String getPoNumber() {
		return poNumber;
	}
	
	/**
	 * The invoice number associated to the transaction.
	 */
	public void setInvoiceNumber(String value) {
		this.invoiceNumber = value;
	}
	
	/**
	 * The invoice number associated to the transaction.
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	/**
	 * Transaction last update date.
	 */
	public void setUpdatedAt(java.util.Date value) {
		this.updatedAt = value;
	}
	
	/**
	 * Transaction last update date.
	 */
	public java.util.Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setGeoLocation(String value) {
		this.geoLocation = value;
	}
	
	public String getGeoLocation() {
		return geoLocation;
	}
	
        @JsonIgnore
	public void setFinalized(boolean value) {
		setFinalized(new Boolean(value));
	}
	
	public void setFinalized(Boolean value) {
		this.finalized = value;
	}
	
	public Boolean getFinalized() {
		return finalized;
	}
	
        @JsonIgnore
	public void setHostCapture(boolean value) {
		setHostCapture(new Boolean(value));
	}
	
	public void setHostCapture(Boolean value) {
		this.hostCapture = value;
	}
	
	public Boolean getHostCapture() {
		return hostCapture;
	}
	
	public void setRetrievalData(String value) {
		this.retrievalData = value;
	}
	
	public String getRetrievalData() {
		return retrievalData;
	}
	
        @JsonIgnore
	public void setBatchNumber(int value) {
		setBatchNumber(new Integer(value));
	}
	
	public void setBatchNumber(Integer value) {
		this.batchNumber = value;
	}
	
	public Integer getBatchNumber() {
		return batchNumber;
	}
	
	public void setCvvResult(String value) {
		this.cvvResult = value;
	}
	
	public String getCvvResult() {
		return cvvResult;
	}
	
        @JsonIgnore
	public void setEntryMethod(int value) {
		setEntryMethod(new Integer(value));
	}
	
	public void setEntryMethod(Integer value) {
		this.entryMethod = value;
	}
	
	public Integer getEntryMethod() {
		return entryMethod;
	}
	
	public void setCardHolderEmail(String value) {
		this.cardHolderEmail = value;
	}
	
	public String getCardHolderEmail() {
		return cardHolderEmail;
	}
	
        @JsonIgnore
	public void setIdOriginalTransaction(int value) {
		setIdOriginalTransaction(new Integer(value));
	}
	
	public void setIdOriginalTransaction(Integer value) {
		this.idOriginalTransaction = value;
	}
	
	public Integer getIdOriginalTransaction() {
		return idOriginalTransaction;
	}
	
	public void setCreatedAt(java.util.Date value) {
		this.createdAt = value;
	}
	
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	
	public void setTerminal(com.smartbt.vtsuite.servercommon.model.Terminal value) {
		this.terminal = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Terminal getTerminal() {
		return terminal;
	}
	
	public void setClient(com.smartbt.vtsuite.servercommon.model.Client value) {
		this.client = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Client getClient() {
		return client;
	}
	
	public void setClerk(com.smartbt.vtsuite.servercommon.model.Clerk value) {
		this.clerk = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Clerk getClerk() {
		return clerk;
	}
	
	public void setHost(com.smartbt.vtsuite.servercommon.model.Host value) {
		this.host = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Host getHost() {
		return host;
	}
	
	public void setMode(com.smartbt.vtsuite.servercommon.model.Mode value) {
		this.mode = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Mode getMode() {
		return mode;
	}
	
	public void setOperation(com.smartbt.vtsuite.servercommon.model.Operation value) {
		this.operation = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Operation getOperation() {
		return operation;
	}
	
	public void setCardBrand(com.smartbt.vtsuite.servercommon.model.CardBrand value) {
		this.cardBrand = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.CardBrand getCardBrand() {
		return cardBrand;
	}
	
	public void setSignature(com.smartbt.vtsuite.servercommon.model.Signature value) {
		this.signature = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Signature getSignature() {
		return signature;
	}
	
	public void setAccount(com.smartbt.vtsuite.servercommon.model.Account value) {
		this.account = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Account getAccount() {
		return account;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
