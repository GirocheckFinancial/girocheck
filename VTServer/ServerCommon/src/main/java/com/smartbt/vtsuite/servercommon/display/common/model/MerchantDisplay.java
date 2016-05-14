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
package com.smartbt.vtsuite.servercommon.display.common.model;

import java.io.Serializable;
import java.util.List;
import javassist.SerialVersionUID;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Merchant Display Class - containing all sets/gets
 */
@XmlRootElement
public class MerchantDisplay implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String number;
    private String description;
    private Boolean active;
    private Boolean monitor;
    private String name;
    private List<ParameterDisplay> parameter;
    private String sic;
    private String activationDate;
    private String deactivationDate;
    private List<AddressDisplay> address;
    private List<TelephoneDisplay> telephones;
    private String logoImage;
    private String customerName;

    public MerchantDisplay(Integer id, String number) {
        this.id = id;
        this.number = number;
        
    }

    
    
    /**
     * THe default constructor
     */
    public MerchantDisplay() {
    }

    /**
     *
     * @return
     */
    public String getSic() {
        return sic;
    }

    /**
     *
     * @param sic
     */
    public void setSic( String sic ) {
        this.sic = sic;
    }

    /**
     *
     * @return
     */
    public List<ParameterDisplay> getParameter() {
        return parameter;
    }

    /**
     *
     * @param parameter
     */
    public void setParameter( List<ParameterDisplay> parameter ) {
        this.parameter = parameter;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId( Integer id ) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber( String number ) {
        this.number = number;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive( Boolean active ) {
        this.active = active;
    }

    /**
     *
     * @return
     */
    public Boolean getMonitor() {
        return monitor;
    }

    /**
     *
     * @param monitor
     */
    public void setMonitor( Boolean monitor ) {
        this.monitor = monitor;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getActivationDate() {
        return activationDate;
    }

    /**
     *
     * @param activationDate
     */
    public void setActivationDate( String activationDate ) {
        this.activationDate = activationDate;
    }

    /**
     *
     * @return
     */
    public String getDeactivationDate() {
        return deactivationDate;
    }

    /**
     *
     * @param deactivationDate
     */
    public void setDeactivationDate( String deactivationDate ) {
        this.deactivationDate = deactivationDate;
    }

    /**
     *
     * @return
     */
    public List<AddressDisplay> getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress( List<AddressDisplay> address ) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public List<TelephoneDisplay> getTelephones() {
        return telephones;
    }

    /**
     *
     * @param telephones
     */
    public void setTelephones( List<TelephoneDisplay> telephones ) {
        this.telephones = telephones;
    }

    /**
     *
     * @return
     */
    public String getLogoImage() {
        return logoImage;
    }

    /**
     *
     * @param logoImage
     */
    public void setLogoImage( String logoImage ) {
        this.logoImage = logoImage;
    }

    /**
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName( String customerName ) {
        this.customerName = customerName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + ( this.address != null ? this.address.hashCode() : 0 );
        hash = 97 * hash + ( this.telephones != null ? this.telephones.hashCode() : 0 );

        return hash;
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
        if ( this.parameter != other.parameter && ( this.parameter == null || !this.parameter.equals( other.parameter ) ) ) {
            return false;
        }
        if ( this.address != other.address && ( this.address == null || !this.address.equals( other.address ) ) ) {
            return false;
        }
        if ( this.telephones != other.telephones && ( this.telephones == null || !this.telephones.equals( other.telephones ) ) ) {
            return false;
        }
      
        return true;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString( this );
    }
    
}
