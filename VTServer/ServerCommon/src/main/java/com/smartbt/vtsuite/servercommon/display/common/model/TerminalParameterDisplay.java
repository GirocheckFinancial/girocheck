/*
 ** File: TerminalParameterMobile.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.servercommon.display.common.model;

import java.io.Serializable;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Terminal Parameter Mobile class - containing all sets/gets
 */
@XmlRootElement
public class TerminalParameterDisplay implements Serializable{

    private int sessionTimeOut;
    private boolean tip;
    private boolean tax;
    private String receiptMessage;
    private boolean confirmLeavingCardPayments;
    private boolean displayCardPayConfirmScreen;
    private boolean skipCustomerCardPayment;
    private boolean showPINpadCardPayment;
    private boolean showReceiptCardPayment;
    private boolean requiredFieldsCardPayments;
    private boolean emailReceiptCardPayment;
    private boolean subTotalCardPayments;
    private boolean poNumberCardPayments;
    private boolean invoiceCardPayments;
    private boolean serverCardPayments;
    private boolean last4CardPayments;
    private boolean manualEntryCardPayments;
    private boolean saveCardCardPayments;
    private boolean mcPCARDIIICardPayments;
    private boolean visaPCARDIIICardPayment;
    private boolean newTransCardPayment;
    private boolean repeatCardPayments;
    private boolean emailCardPayments;
    private boolean skipSignatureCardPayments;
    private boolean startOnCardPayment;
    private boolean returnToCardPayment;
    private float taxPercentage;
    private String merchantCopy;

    /**
     * The default constructor
     */
    public TerminalParameterDisplay() {
    }

    /**
     *
     * @return
     */
    public String getMerchantCopy() {
        return merchantCopy;
    }

    /**
     *
     * @return
     */
    public float getTaxPercentage() {
        return taxPercentage;
    }

    /**
     *
     * @return
     */
    public int getSessionTimeOut() {
        return sessionTimeOut;
    }

    /**
     *
     * @param sessionTimeOut
     */
    public void setSessionTimeOut(int sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    /**
     *
     * @return
     */
    public boolean isTip() {
        return tip;
    }

    /**
     *
     * @param tip
     */
    public void setTip(boolean tip) {
        this.tip = tip;
    }

    /**
     *
     * @return
     */
    public boolean isTax() {
        return tax;
    }

    /**
     *
     * @param tax
     */
    public void setTax(boolean tax) {
        this.tax = tax;
    }

    /**
     *
     * @return
     */
    public String getReceiptMessage() {
        return receiptMessage;
    }

    /**
     *
     * @param receiptMessage
     */
    public void setReceiptMessage(String receiptMessage) {
        this.receiptMessage = receiptMessage;
    }

    /**
     *
     * @return
     */
    public boolean isReturnToCardPayment() {
        return returnToCardPayment;
    }

    /**
     *
     * @return
     */
    public boolean isStartOnCardPayment() {
        return startOnCardPayment;
    }

    /**
     *
     * @param merchantCopy
     */
    public void setMerchantCopy(String merchantCopy) {
        this.merchantCopy = merchantCopy;
    }

    /**
     *
     * @param returnToCardPayment
     */
    public void setReturnToCardPayment(boolean returnToCardPayment) {
        this.returnToCardPayment = returnToCardPayment;
    }

    /**
     *
     * @param startOnCardPayment
     */
    public void setStartOnCardPayment(boolean startOnCardPayment) {
        this.startOnCardPayment = startOnCardPayment;
    }

    /**
     *
     * @param taxPercentage
     */
    public void setTaxPercentage(float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    /**
     *
     * @return
     */
    public boolean isConfirmLeavingCardPayments() {
        return confirmLeavingCardPayments;
    }

    /**
     *
     * @param confirmLeavingCardPayments
     */
    public void setConfirmLeavingCardPayments(boolean confirmLeavingCardPayments) {
        this.confirmLeavingCardPayments = confirmLeavingCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isDisplayCardPayConfirmScreen() {
        return displayCardPayConfirmScreen;
    }

    /**
     *
     * @param displayCardPayConfirmScreen
     */
    public void setDisplayCardPayConfirmScreen(boolean displayCardPayConfirmScreen) {
        this.displayCardPayConfirmScreen = displayCardPayConfirmScreen;
    }

    /**
     *
     * @return
     */
    public boolean isSkipCustomerCardPayment() {
        return skipCustomerCardPayment;
    }

    /**
     *
     * @param skipCustomerCardPayment
     */
    public void setSkipCustomerCardPayment(boolean skipCustomerCardPayment) {
        this.skipCustomerCardPayment = skipCustomerCardPayment;
    }

    /**
     *
     * @return
     */
    public boolean isShowPINpadCardPayment() {
        return showPINpadCardPayment;
    }

    /**
     *
     * @param showPINpadCardPayment
     */
    public void setShowPINpadCardPayment(boolean showPINpadCardPayment) {
        this.showPINpadCardPayment = showPINpadCardPayment;
    }

    /**
     *
     * @return
     */
    public boolean isShowReceiptCardPayment() {
        return showReceiptCardPayment;
    }

    /**
     *
     * @param showReceiptCardPayment
     */
    public void setShowReceiptCardPayment(boolean showReceiptCardPayment) {
        this.showReceiptCardPayment = showReceiptCardPayment;
    }

    /**
     *
     * @return
     */
    public boolean isRequiredFieldsCardPayments() {
        return requiredFieldsCardPayments;
    }

    /**
     *
     * @param requiredFieldsCardPayments
     */
    public void setRequiredFieldsCardPayments(boolean requiredFieldsCardPayments) {
        this.requiredFieldsCardPayments = requiredFieldsCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isEmailReceiptCardPayment() {
        return emailReceiptCardPayment;
    }

    /**
     *
     * @param emailReceiptCardPayment
     */
    public void setEmailReceiptCardPayment(boolean emailReceiptCardPayment) {
        this.emailReceiptCardPayment = emailReceiptCardPayment;
    }

    /**
     *
     * @return
     */
    public boolean isSubTotalCardPayments() {
        return subTotalCardPayments;
    }

    /**
     *
     * @param subTotalCardPayments
     */
    public void setSubTotalCardPayments(boolean subTotalCardPayments) {
        this.subTotalCardPayments = subTotalCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isPoNumberCardPayments() {
        return poNumberCardPayments;
    }

    /**
     *
     * @param poNumberCardPayments
     */
    public void setPoNumberCardPayments(boolean poNumberCardPayments) {
        this.poNumberCardPayments = poNumberCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isInvoiceCardPayments() {
        return invoiceCardPayments;
    }

    /**
     *
     * @param invoiceCardPayments
     */
    public void setInvoiceCardPayments(boolean invoiceCardPayments) {
        this.invoiceCardPayments = invoiceCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isServerCardPayments() {
        return serverCardPayments;
    }

    /**
     *
     * @param serverCardPayments
     */
    public void setServerCardPayments(boolean serverCardPayments) {
        this.serverCardPayments = serverCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isLast4CardPayments() {
        return last4CardPayments;
    }

    /**
     *
     * @param last4CardPayments
     */
    public void setLast4CardPayments(boolean last4CardPayments) {
        this.last4CardPayments = last4CardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isManualEntryCardPayments() {
        return manualEntryCardPayments;
    }

    /**
     *
     * @param manualEntryCardPayments
     */
    public void setManualEntryCardPayments(boolean manualEntryCardPayments) {
        this.manualEntryCardPayments = manualEntryCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isSaveCardCardPayments() {
        return saveCardCardPayments;
    }

    /**
     *
     * @param saveCardCardPayments
     */
    public void setSaveCardCardPayments(boolean saveCardCardPayments) {
        this.saveCardCardPayments = saveCardCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isMcPCARDIIICardPayments() {
        return mcPCARDIIICardPayments;
    }

    /**
     *
     * @param mcPCARDIIICardPayments
     */
    public void setMcPCARDIIICardPayments(boolean mcPCARDIIICardPayments) {
        this.mcPCARDIIICardPayments = mcPCARDIIICardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isVisaPCARDIIICardPayment() {
        return visaPCARDIIICardPayment;
    }

    /**
     *
     * @param visaPCARDIIICardPayment
     */
    public void setVisaPCARDIIICardPayment(boolean visaPCARDIIICardPayment) {
        this.visaPCARDIIICardPayment = visaPCARDIIICardPayment;
    }

    /**
     *
     * @return
     */
    public boolean isNewTransCardPayment() {
        return newTransCardPayment;
    }

    /**
     *
     * @param newTransCardPayment
     */
    public void setNewTransCardPayment(boolean newTransCardPayment) {
        this.newTransCardPayment = newTransCardPayment;
    }

    /**
     *
     * @return
     */
    public boolean isRepeatCardPayments() {
        return repeatCardPayments;
    }

    /**
     *
     * @param repeatCardPayments
     */
    public void setRepeatCardPayments(boolean repeatCardPayments) {
        this.repeatCardPayments = repeatCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isEmailCardPayments() {
        return emailCardPayments;
    }

    /**
     *
     * @param emailCardPayments
     */
    public void setEmailCardPayments(boolean emailCardPayments) {
        this.emailCardPayments = emailCardPayments;
    }

    /**
     *
     * @return
     */
    public boolean isSkipSignatureCardPayments() {
        return skipSignatureCardPayments;
    }

    /**
     *
     * @param skipSignatureCardPayments
     */
    public void setSkipSignatureCardPayments(boolean skipSignatureCardPayments) {
        this.skipSignatureCardPayments = skipSignatureCardPayments;
    }

    public void initFromMap(Map<String, Object> map) {
        if (map != null) {
            setSessionTimeOut(Integer.valueOf((String) map.get("sessionTimeOut")));

            setReceiptMessage((String) map.get("receiptMessage"));

            setTax(Boolean.parseBoolean((String) map.get("tax")));

            setTip(Boolean.parseBoolean((String) map.get("tip")));

            setConfirmLeavingCardPayments(Boolean.parseBoolean((String) map.get("confirmLeavingCardPayments")));
            setDisplayCardPayConfirmScreen(Boolean.parseBoolean((String) map.get("displayCardPayConfirmScreen")));
            setSkipCustomerCardPayment(Boolean.parseBoolean((String) map.get("skipCustomerCardPayment")));
            setShowPINpadCardPayment(Boolean.parseBoolean((String) map.get("showPINpadCardPayment")));
            setShowReceiptCardPayment(Boolean.parseBoolean((String) map.get("showReceiptCardPayment")));
            setRequiredFieldsCardPayments(Boolean.parseBoolean((String) map.get("requiredFieldsCardPayments")));
            setEmailReceiptCardPayment(Boolean.parseBoolean((String) map.get("emailReceiptCardPayment")));
            setSubTotalCardPayments(Boolean.parseBoolean((String) map.get("subTotalCardPayments")));
            setPoNumberCardPayments(Boolean.parseBoolean((String) map.get("poNumberCardPayments")));
            setInvoiceCardPayments(Boolean.parseBoolean((String) map.get("invoiceCardPayments")));
            setServerCardPayments(Boolean.parseBoolean((String) map.get("serverCardPayments")));
            setLast4CardPayments(Boolean.parseBoolean((String) map.get("last4CardPayments")));
            setManualEntryCardPayments(Boolean.parseBoolean((String) map.get("manualEntryCardPayments")));
            setSaveCardCardPayments(Boolean.parseBoolean((String) map.get("saveCardCardPayments")));
            setMcPCARDIIICardPayments(Boolean.parseBoolean((String) map.get("mcPCARDIIICardPayments")));
            setVisaPCARDIIICardPayment(Boolean.parseBoolean((String) map.get("visaPCARDIIICardPayment")));
            setNewTransCardPayment(Boolean.parseBoolean((String) map.get("newTransCardPayment")));
            setRepeatCardPayments(Boolean.parseBoolean((String) map.get("repeatCardPayments")));
            setEmailCardPayments(Boolean.parseBoolean((String) map.get("emailCardPayments")));
            setSkipSignatureCardPayments(Boolean.parseBoolean((String) map.get("skipSignatureCardPayments")));
        }
    }
}
