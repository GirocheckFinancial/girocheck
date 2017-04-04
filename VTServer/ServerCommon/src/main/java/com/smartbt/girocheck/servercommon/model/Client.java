/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
public class Client implements Serializable {
	public Client() {
	}
	
	private int id;
	
	private com.smartbt.girocheck.servercommon.model.Address address;
	
	private String firstName;
	
	private String lastName;
	
	private String telephone;
	
	private String email;
	
	private Boolean active;
        
	private Boolean blacklistCard2bank;
	
	private java.util.Date createdAt;
	
	private java.sql.Blob addressForm;
	
	private Integer successfulLoads;
	
	private java.util.Date bornDate;
	
	private String ssn;
        
	private String maskSSN; 
	
	private String idBeneficiary;
        
        private Boolean excludeSms;
                
        
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> data_SC = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.CreditCard>();
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> data_SD = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.PersonalIdentification>();
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> transaction = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Transaction>();
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.Check> check = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Check>();
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setFirstName(String value) {
		this.firstName = value;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String value) {
		this.lastName = value;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setTelephone(String value) {
		this.telephone = value;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setActive(boolean value) {
		this.active = value;
	}
	
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setCreatedAt(java.util.Date value) {
		this.createdAt = value;
	}
	
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	
	public void setAddressForm(java.sql.Blob value) {
		this.addressForm = value;
	}
	
	public java.sql.Blob getAddressForm() {
		return addressForm;
	}
	
	
	
	public void setBornDate(java.util.Date value) {
		this.bornDate = value;
	}
	
	public java.util.Date getBornDate() {
		return bornDate;
	}
	
	public void setSsn(String value) {
		this.ssn = value;
	}
	
	public String getSsn() {
		return ssn;
	}
	
	public void setIdBeneficiary(String value) {
		this.idBeneficiary = value;
	}
	
	public String getIdBeneficiary() {
		return idBeneficiary;
	}
	
	public void setAddress(com.smartbt.girocheck.servercommon.model.Address value) {
		this.address = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Address getAddress() {
		return address;
	}
	
	public void setData_SC(java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> value) {
		this.data_SC = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> getData_SC() {
		return data_SC;
	}
	
	
	public void setData_SD(java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> value) {
		this.data_SD = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> getData_SD() {
		return data_SD;
	}
	
	
	public void setTransaction(java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> value) {
		this.transaction = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> getTransaction() {
		return transaction;
	}
	
	
	public void setCheck(java.util.Set<com.smartbt.girocheck.servercommon.model.Check> value) {
		this.check = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.Check> getCheck() {
		return check;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}

    /**
     * @return the maskSSN
     */
    public String getMaskSSN() {
        return maskSSN;
    }

    /**
     * @param maskSSN the maskSSN to set
     */
    public void setMaskSSN(String maskSSN) {
        this.maskSSN = maskSSN;
    }
    
    public static void main(String[] args){
        Client c = new Client();
        c.ssn = "922916668";
        
        System.out.println("has ITIN = " + c.hasITIN());
    }
 
    public  boolean hasITIN(){
        if(this.ssn == null) return false;
        long auxSsn = Long.parseLong(this.ssn);
        int aux = Integer.parseInt(this.ssn.substring(3, 5));
        
        if(this.ssn.startsWith("9") && 70<=aux && aux<=88){
            return true;
        }
        if(900700000<=auxSsn && auxSsn<=999889999){
            return true;
        }
        if(900900000<=auxSsn && auxSsn<=999929999){
            return true;
        }
        return 900940000<=auxSsn && auxSsn<=999999999;
    }

    /**
     * @return the successfulLoads
     */
    public Integer getSuccessfulLoads() {
        return successfulLoads;
    }

    /**
     * @param successfulLoads the successfulLoads to set
     */
    public void setSuccessfulLoads(Integer successfulLoads) {
        this.successfulLoads = successfulLoads;
    }

    /**
     * @return the blacklistCard2bank
     */
    public Boolean getBlacklistCard2bank() {
        return blacklistCard2bank;
    }

    /**
     * @param blacklistCard2bank the blacklistCard2bank to set
     */
    public void setBlacklistCard2bank(Boolean blacklistCard2bank) {
        this.blacklistCard2bank = blacklistCard2bank;
    }

    /**
     * @return the excludeSms
     */
    public Boolean getExcludeSms() {
        return excludeSms;
    }

    /**
     * @param excludeSms the excludeSms to set
     */
    public void setExcludeSms(Boolean excludeSms) {
        this.excludeSms = excludeSms;
    }
        	
}
