/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 *
 * This is an automatic generated file. It will be regenerated every time you
 * generate persistence class.
 *
 * Modifying its content may cause the program not work, or your work may lost.
 */
/**
 * Licensee: License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable; 

public class Merchant implements Serializable {

    public Merchant() {
    }

    private int id;

    private com.smartbt.girocheck.servercommon.model.Address address;

    private com.smartbt.girocheck.servercommon.model.Agrupation agrupation;

    private com.smartbt.girocheck.servercommon.model.CardProgram card_program;
    
    private String routingBankNumber;
    
    private String bankName;

    private String legalName;
    
    private String idTellerOrderExp;
    
    private String idTellerPagoOrderExp;

    private String description;

    private Boolean active;

    private Boolean monitor;

    private String idTecnicardCheck;

    private String idTecnicardCash;

//    private String idIstream;
    private String idIstreamTecnicardCash;
    
    private String idIstreamFuzeCheck;
    
    private String idIstreamFuzeCash;
    
    private String idIstreamTecnicardCheck;

    private String istreamUser;

    private String istreamPassword;

    private java.util.Date activationDate;

    private java.util.Date deactivationDate;

    private String sic;

    private Integer aliveSessionTime;

    private java.sql.Blob logo;

    private String phone;

    private String agentName;

    private Integer distributor;
 

    private Boolean training;

    private Boolean documentApproved;

    private String documentNotes;

    private Integer distributionChanel;

    private Integer merchantType;

    private Integer risk;

    private Boolean independentOwner;

    private Boolean moneyTransmission;

    private Boolean billPayment;

    private Boolean checkCashing;

    private Boolean atm;

    private Boolean otherFinancialProvider;

    private String account;

    private String key;
    
    private String idPosOrderExp;
    
    private String oEAgentNumber;
    
    private double authFeeP;
    
    private Integer inventory;
    private Integer threshold;
    private Character commissionType;

    private java.util.Set<com.smartbt.girocheck.servercommon.model.MerchantParameterValue> merchant_Parameter_Value = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.MerchantParameterValue>();

    private java.util.Set<com.smartbt.girocheck.servercommon.model.Terminal> terminal = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Terminal>();

    private java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> data_SC = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.CreditCard>();

    public Merchant( int id) {
        this.id = id;
    }

    
    
    public void setId( int value ) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setIdTecnicardCheck( String value ) {
        this.idTecnicardCheck = value;
    }

    public String getIdTecnicardCheck() {
        return idTecnicardCheck;
    }

    public void setIdTecnicardCash( String value ) {
        this.idTecnicardCash = value;
    }

    public String getIdTecnicardCash() {
        return idTecnicardCash;
    }

//    public void setIdIstream( String value ) {
//        this.idIstream = value;
//    }
//
//    public String getIdIstream() {
//        return idIstream;
//    }
    public void setIstreamUser( String value ) {
        this.istreamUser = value;
    }

    public String getIstreamUser() {
        return istreamUser;
    }

    public void setIstreamPassword( String value ) {
        this.istreamPassword = value;
    }

    public String getIstreamPassword() {
        return istreamPassword;
    }

    public void setLegalName( String value ) {
        this.legalName = value;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setDescription( String value ) {
        this.description = value;
    }

    public String getDescription() {
        return description;
    }

    public void setActive( boolean value ) {
        setActive( new Boolean( value ) );
    }

    public void setActive( Boolean value ) {
        this.active = value;
    }

    public Boolean getActive() {
        return active;
    }

    public void setMonitor( boolean value ) {
        setMonitor( new Boolean( value ) );
    }

    public void setMonitor( Boolean value ) {
        this.monitor = value;
    }

    public Boolean getMonitor() {
        return monitor;
    }

    public void setActivationDate( java.util.Date value ) {
        this.activationDate = value;
    }

    public java.util.Date getActivationDate() {
        return activationDate;
    }

    public void setDeactivationDate( java.util.Date value ) {
        this.deactivationDate = value;
    }

    public java.util.Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setSic( String value ) {
        this.sic = value;
    }

    public String getSic() {
        return sic;
    }

    public void setAliveSessionTime( int value ) {
        setAliveSessionTime( new Integer( value ) );
    }

    public void setAliveSessionTime( Integer value ) {
        this.aliveSessionTime = value;
    }

    public Integer getAliveSessionTime() {
        return aliveSessionTime;
    }

    public void setLogo( java.sql.Blob value ) {
        this.logo = value;
    }

    public java.sql.Blob getLogo() {
        return logo;
    }

    public void setPhone( String value ) {
        this.phone = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setAgentName( int value ) {
        setAgentName( new Integer( value ) );
    }

    public void setAgentName( String value ) {
        this.agentName = value;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setDistributor( int value ) {
        setDistributor( new Integer( value ) );
    }

    public void setDistributor( Integer value ) {
        this.distributor = value;
    }

    public Integer getDistributor() {
        return distributor;
    }
 
 
    public void setTraining( boolean value ) {
        setTraining( new Boolean( value ) );
    }

    public void setTraining( Boolean value ) {
        this.training = value;
    }

    public Boolean getTraining() {
        return training;
    }

    public void setDocumentStatus( boolean value ) {
        setDocumentStatus( new Boolean( value ) );
    }

    public void setDocumentNotes( String value ) {
        this.documentNotes = value;
    }

    public String getDocumentNotes() {
        return documentNotes;
    }

    public void setDistributionChanel( int value ) {
        setDistributionChanel( new Integer( value ) );
    }

    public void setDistributionChanel( Integer value ) {
        this.distributionChanel = value;
    }

    public Integer getDistributionChanel() {
        return distributionChanel;
    }

    public void setMerchantType( int value ) {
        setMerchantType( new Integer( value ) );
    }

    public void setMerchantType( Integer value ) {
        this.merchantType = value;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setRisk( int value ) {
        setRisk( new Integer( value ) );
    }

    public void setRisk( Integer value ) {
        this.risk = value;
    }

    public Integer getRisk() {
        return risk;
    }

    public void setAtm( Boolean atm ) {
        this.atm = atm;
    }

    public void setBillPayment( Boolean billPayment ) {
        this.billPayment = billPayment;
    }

    public void setCheckCashing( Boolean checkCashing ) {
        this.checkCashing = checkCashing;
    }

    public void setDocumentApproved( Boolean documentApproved ) {
        this.documentApproved = documentApproved;
    }

    public void setIndependentOwner( Boolean independentOwner ) {
        this.independentOwner = independentOwner;
    }

    public void setMoneyTransmission( Boolean moneyTransmission ) {
        this.moneyTransmission = moneyTransmission;
    }

    public void setOtherFinancialProvider( Boolean otherFinancialProvider ) {
        this.otherFinancialProvider = otherFinancialProvider;
    }

    public void setProgram( Integer program ) {
        this.program = program;
    }

    public Boolean getIndependentOwner() {
        return independentOwner;
    }

    public Integer getProgram() {
        return program;
    }

    public Boolean isActive() {
        return active;
    }

    public Boolean isAtm() {
        return atm;
    }

    public Boolean isBillPayment() {
        return billPayment;
    }

    public Boolean isCheckCashing() {
        return checkCashing;
    }

    public Boolean isDocumentApproved() {
        return documentApproved;
    }

    public Boolean isMoneyTransmission() {
        return moneyTransmission;
    }

    public Boolean isMonitor() {
        return monitor;
    }

    public Boolean isOtherFinancialProvider() {
        return otherFinancialProvider;
    }

    public Boolean isTraining() {
        return training;
    }

    public void setAccount( String value ) {
        this.account = value;
    }

    public String getAccount() {
        return account;
    }

    public void setKey( String value ) {
        this.key = value;
    }

    public String getKey() {
        return key;
    }

    public void setAgrupation( com.smartbt.girocheck.servercommon.model.Agrupation value ) {
        this.agrupation = value;
    }

    public com.smartbt.girocheck.servercommon.model.Agrupation getAgrupation() {
        return agrupation;
    }

    public void setCard_program( com.smartbt.girocheck.servercommon.model.CardProgram value ) {
        this.card_program = value;
    }

    public com.smartbt.girocheck.servercommon.model.CardProgram getCard_program() {
        return card_program;
    }

    public void setAddress( com.smartbt.girocheck.servercommon.model.Address value ) {
        this.address = value;
    }

    public com.smartbt.girocheck.servercommon.model.Address getAddress() {
        return address;
    }

    public void setMerchant_Parameter_Value( java.util.Set<com.smartbt.girocheck.servercommon.model.MerchantParameterValue> value ) {
        this.merchant_Parameter_Value = value;
    }

    public java.util.Set<com.smartbt.girocheck.servercommon.model.MerchantParameterValue> getMerchant_Parameter_Value() {
        return merchant_Parameter_Value;
    }

    public void setTerminal( java.util.Set<com.smartbt.girocheck.servercommon.model.Terminal> value ) {
        this.terminal = value;
    }

    public java.util.Set<com.smartbt.girocheck.servercommon.model.Terminal> getTerminal() {
        return terminal;
    }

    public void setData_SC( java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> value ) {
        this.data_SC = value;
    }

    public java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> getData_SC() {
        return data_SC;
    }

    private Integer program;

    public String toString() {
        return String.valueOf( getId() );
    }

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
     * @return the inventory
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the threshold
     */
    public Integer getThreshold() {
        return threshold;
    }

    /**
     * @param threshold the threshold to set
     */
    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
 

    /**
     * @return the commissionType
     */
    public Character getCommissionType() {
        return commissionType;
    }

    /**
     * @param commissionType the commissionType to set
     */
    public void setCommissionType(Character commissionType) {
        this.commissionType = commissionType;
    }
 


}
