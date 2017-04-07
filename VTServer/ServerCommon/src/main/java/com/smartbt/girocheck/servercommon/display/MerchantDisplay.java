/*
 ** File: MerchantDisplay.java
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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Merchant Display Class - containing all sets/gets
 */
@XmlRootElement
public class MerchantDisplay implements Serializable {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MerchantDisplay.class);

    private static long serialVersionUID = 1L;

    private Integer idAgrupation;

    private Integer id;
    private String legalName;
    private String agentName;
    private String phone;
    private String account;

    private AddressDisplay address;
    private String idTellerOrderExp;
    private String idTellerPagoOrderExp;
    private String idPosOrderExp;

    private String idTecnicardCheck;
    private String idTecnicardCash;
    private String iStreamUser;
    private String iStreamPassword;
    
//    private String idIStream;
    private String idIstreamTecnicardCash;
    
    private String idIstreamFuzeCheck;
    
    private String idIstreamFuzeCash;
    
    private String idIstreamTecnicardCheck;
    
    private String sic;
    private String cardInventory;
    private CardProgramDisplay cardProgram;
    private String routingBankNumber;
    private String bankName;
    private String merchantType;
    private String distributor;
    private String distributionChanel;
    private String risk;

    private Boolean independentOwner;
    private Boolean moneyTransmission;
    private Boolean billPayment;
    private Boolean checkCashing;
    private Boolean documentApproved;
    private Boolean atm;
    private Boolean active;
    private Boolean training;
    private Boolean validate;
    private Boolean otherFinancialProvider;

    private String documentNotes;
    private String description;
    
    private String oEAgentNumber;
    
    private double authFeeP;

    private boolean hasTransaction;
    
    private java.util.Date activationDate;
    private String commissionType;
    
    public MerchantDisplay() {

    }

    public MerchantDisplay( Integer id, String legalName ) {
        this.id = id;
        this.legalName = legalName;
    }

    public void print() {
//        log.debug( "Printing merchant..." );
//        log.debug( ReflectionToStringBuilder.reflectionToString( this ) );
    }

    public void setHasTransaction( boolean hasTransaction ) {
        this.hasTransaction = hasTransaction;
    }

    public boolean isHasTransaction() {
        return hasTransaction;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID( long aSerialVersionUID ) {
        serialVersionUID = aSerialVersionUID;
    }

    public void setIdAgrupation( Integer idAgrupation ) {
        this.idAgrupation = idAgrupation;
    }

    public Integer getIdAgrupation() {
        return idAgrupation;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final MerchantDisplay other = (MerchantDisplay) obj;

        return this.getId() == other.getId() && this.getLegalName().equals( other.getLegalName() );
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString( this );
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Integer id ) {
        this.id = id;
    }

    /**
     * @return the legalName
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * @param legalName the legalName to set
     */
    public void setLegalName( String legalName ) {
        this.legalName = legalName;
    }

    /**
     * @return the agentName
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * @param agentName the agentName to set
     */
    public void setAgentName( String agentName ) {
        this.agentName = agentName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone( String phone ) {
        this.phone = phone;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount( String account ) {
        this.account = account;
    }

    /**
     * @return the address
     */
    public AddressDisplay getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress( AddressDisplay address ) {
        this.address = address;
    }

    /**
     * @return the sic
     */
    public String getSic() {
        return sic;
    }

    /**
     * @param sic the sic to set
     */
    public void setSic( String sic ) {
        this.sic = sic;
    }

    /**
     * @return the cardInventory
     */
    public String getCardInventory() {
        return cardInventory;
    }

    /**
     * @param cardInventory the cardInventory to set
     */
    public void setCardInventory( String cardInventory ) {
        this.cardInventory = cardInventory;
    }

    /**
     * @return the cardProgram
     */
    public CardProgramDisplay getCardProgram() {
        return cardProgram;
    }

    /**
     * @param cardProgram the cardProgram to set
     */
    public void setCardProgram( CardProgramDisplay cardProgram ) {
        this.cardProgram = cardProgram;
    }

    /**
     * @return the merchantType
     */
    public String getMerchantType() {
        return merchantType;
    }

    /**
     * @param merchantType the merchantType to set
     */
    public void setMerchantType( String merchantType ) {
        this.merchantType = merchantType;
    }

    /**
     * @return the distributor
     */
    public String getDistributor() {
        return distributor;
    }

    /**
     * @param distributor the distributor to set
     */
    public void setDistributor( String distributor ) {
        this.distributor = distributor;
    }

    /**
     * @return the distributionChanel
     */
    public String getDistributionChanel() {
        return distributionChanel;
    }

    /**
     * @param distributionChanel the distributionChanel to set
     */
    public void setDistributionChanel( String distributionChanel ) {
        this.distributionChanel = distributionChanel;
    }

    /**
     * @return the risk
     */
    public String getRisk() {
        return risk;
    }

    /**
     * @param risk the risk to set
     */
    public void setRisk( String risk ) {
        this.risk = risk;
    }

    /**
     * @return the independentOwner
     */
    public Boolean getIndependentOwner() {
        return independentOwner;
    }

    /**
     * @param independentOwner the independentOwner to set
     */
    public void setIndependentOwner( Boolean independentOwner ) {
        this.independentOwner = independentOwner;
    }

    /**
     * @return the moneyTransmission
     */
    public Boolean getMoneyTransmission() {
        return moneyTransmission;
    }

    /**
     * @param moneyTransmission the moneyTransmission to set
     */
    public void setMoneyTransmission( Boolean moneyTransmission ) {
        this.moneyTransmission = moneyTransmission;
    }

    /**
     * @return the billPayment
     */
    public Boolean getBillPayment() {
        return billPayment;
    }

    /**
     * @param billPayment the billPayment to set
     */
    public void setBillPayment( Boolean billPayment ) {
        this.billPayment = billPayment;
    }

    /**
     * @return the checkCashing
     */
    public Boolean getCheckCashing() {
        return checkCashing;
    }

    /**
     * @param checkCashing the checkCashing to set
     */
    public void setCheckCashing( Boolean checkCashing ) {
        this.checkCashing = checkCashing;
    }

    /**
     * @return the documentApproved
     */
    public Boolean getDocumentApproved() {
        return documentApproved;
    }

    /**
     * @param documentApproved the documentApproved to set
     */
    public void setDocumentApproved( Boolean documentApproved ) {
        this.documentApproved = documentApproved;
    }

    /**
     * @return the atm
     */
    public Boolean getAtm() {
        return atm;
    }

    /**
     * @param atm the atm to set
     */
    public void setAtm( Boolean atm ) {
        this.atm = atm;
    }
    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive( Boolean active ) {
        this.active = active;
    }

    /**
     * @return the training
     */
    public Boolean getTraining() {
        return training;
    }

    /**
     * @param training the training to set
     */
    public void setTraining( Boolean training ) {
        this.training = training;
    }

    /**
     * @return the otherFinancialProvider
     */
    public Boolean getOtherFinancialProvider() {
        return otherFinancialProvider;
    }

    /**
     * @param otherFinancialProvider the otherFinancialProvider to set
     */
    public void setOtherFinancialProvider( Boolean otherFinancialProvider ) {
        this.otherFinancialProvider = otherFinancialProvider;
    }

    /**
     * @return the documentNotes
     */
    public String getDocumentNotes() {
        return documentNotes;
    }

    /**
     * @param documentNotes the documentNotes to set
     */
    public void setDocumentNotes( String documentNotes ) {
        this.documentNotes = documentNotes;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     * @return the idTecnicardCheck
     */
    public String getIdTecnicardCheck() {
        return idTecnicardCheck;
    }

    /**
     * @param idTecnicardCheck the idTecnicardCheck to set
     */
    public void setIdTecnicardCheck( String idTecnicardCheck ) {
        this.idTecnicardCheck = idTecnicardCheck;
    }

    /**
     * @return the idTecnicardCash
     */
    public String getIdTecnicardCash() {
        return idTecnicardCash;
    }

    /**
     * @param idTecnicardCash the idTecnicardCash to set
     */
    public void setIdTecnicardCash( String idTecnicardCash ) {
        this.idTecnicardCash = idTecnicardCash;
    }

    /**
     * @return the iStreamUser
     */
    public String getiStreamUser() {
        return iStreamUser;
    }

    /**
     * @param iStreamUser the iStreamUser to set
     */
    public void setiStreamUser( String iStreamUser ) {
        this.iStreamUser = iStreamUser;
    }

    /**
     * @return the iStreamPassword
     */
    public String getiStreamPassword() {
        return iStreamPassword;
    }

    /**
     * @param iStreamPassword the iStreamPassword to set
     */
    public void setiStreamPassword( String iStreamPassword ) {
        this.iStreamPassword = iStreamPassword;
    }

//    /**
//     * @return the idIStream
//     */
//    public String getIdIStream() {
//        return idIStream;
//    }
//
//    /**
//     * @param idIStream the idIStream to set
//     */
//    public void setIdIStream( String idIStream ) {
//        this.idIStream = idIStream;
//    }

    /**
     * @return the idTellerOrderExp
     */
    public String getIdTellerOrderExp() {
        return idTellerOrderExp;
    }

    /**
     * @param idTellerOrderExp the idTellerOrderExp to set
     */
    public void setIdTellerOrderExp(String idTellerOrderExp) {
        this.idTellerOrderExp = idTellerOrderExp;
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
     * @return the idIstreamTecnicardCash
     */
    public String getIdIstreamTecnicardCash() {
        return idIstreamTecnicardCash;
    }

    /**
     * @param idIstreamTecnicardCash the idIstreamTecnicardCash to set
     */
    public void setIdIstreamTecnicardCash(String idIstreamTecnicardCash) {
        this.idIstreamTecnicardCash = idIstreamTecnicardCash;
    }

    /**
     * @return the idIstreamFuzeCheck
     */
    public String getIdIstreamFuzeCheck() {
        return idIstreamFuzeCheck;
    }

    /**
     * @param idIstreamFuzeCheck the idIstreamFuzeCheck to set
     */
    public void setIdIstreamFuzeCheck(String idIstreamFuzeCheck) {
        this.idIstreamFuzeCheck = idIstreamFuzeCheck;
    }

    /**
     * @return the idIstreamFuzeCash
     */
    public String getIdIstreamFuzeCash() {
        return idIstreamFuzeCash;
    }

    /**
     * @param idIstreamFuzeCash the idIstreamFuzeCash to set
     */
    public void setIdIstreamFuzeCash(String idIstreamFuzeCash) {
        this.idIstreamFuzeCash = idIstreamFuzeCash;
    }

    /**
     * @return the idIstreamTecnicardCheck
     */
    public String getIdIstreamTecnicardCheck() {
        return idIstreamTecnicardCheck;
    }

    /**
     * @param idIstreamTecnicardCheck the idIstreamTecnicardCheck to set
     */
    public void setIdIstreamTecnicardCheck(String idIstreamTecnicardCheck) {
        this.idIstreamTecnicardCheck = idIstreamTecnicardCheck;
    }

    /**
     * @return the authFeeP
     */
    public double getAuthFeeP() {
        return authFeeP;
    }

    /**
     * @param authFeeP the authFeeP to set
     */
    public void setAuthFeeP(double authFeeP) {
        this.authFeeP = authFeeP;
    }

    /**
     * @return the idPosOrderExp
     */
    public String getIdPosOrderExp() {
        return idPosOrderExp;
    }

    /**
     * @param idPosOrderExp the idPosOrderExp to set
     */
    public void setIdPosOrderExp(String idPosOrderExp) {
        this.idPosOrderExp = idPosOrderExp;
    }

    /**
     * @return the oEAgentNumber
     */
    public String getoEAgentNumber() {
        return oEAgentNumber;
    }

    /**
     * @param oEAgentNumber the oEAgentNumber to set
     */
    public void setoEAgentNumber(String oEAgentNumber) {
        this.oEAgentNumber = oEAgentNumber;
    }

    /**
     * @return the idTellerPagoOrderExp
     */
    public String getIdTellerPagoOrderExp() {
        return idTellerPagoOrderExp;
    }

    /**
     * @param idTellerPagoOrderExp the idTellerPagoOrderExp to set
     */
    public void setIdTellerPagoOrderExp(String idTellerPagoOrderExp) {
        this.idTellerPagoOrderExp = idTellerPagoOrderExp;
    }

    /**
     * @return the validate
     */
    public Boolean getValidate() {
        return validate;
    }

    /**
     * @param validate the validate to set
     */
    public void setValidate(Boolean validate) {
        this.validate = validate;
    }
    
    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }
 
    /**
     * @return the commissionType
     */
    public String getCommissionType() {
        return commissionType;
    }

    /**
     * @param commissionType the commissionType to set
     */
    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }
 

   

}
