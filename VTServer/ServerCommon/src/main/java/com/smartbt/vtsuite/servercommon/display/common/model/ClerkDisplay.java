/**
 *
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be reproduced, transmitted,
 * transcribed, stored in a retrieval system, or translated into any language or
 * computer language, in any form or by any means, electronic, mechanical,
 * magnetic, optical, chemical, manual or otherwise, without the prior written
 * permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.servercommon.display.common.model;

import com.smartbt.vtsuite.servercommon.model.AuditLog;
import com.smartbt.vtsuite.servercommon.model.ClerkParameterValue;
import com.smartbt.vtsuite.servercommon.model.ClerkRole;
import com.smartbt.vtsuite.servercommon.model.Customer;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.Monitoring;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.model.Transaction;
import com.smartbt.vtsuite.servercommon.model.VTSession;
import com.smartbt.vtsuite.servercommon.model.WatchdogAlert;
import com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
@XmlRootElement
public class ClerkDisplay implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean active;

    private String middleInitial;

    private java.util.Date deactivationDate;

    private boolean firstTime;

    private com.smartbt.vtsuite.servercommon.model.Customer customer;

    private com.smartbt.vtsuite.servercommon.model.Merchant merchant;

    private com.smartbt.vtsuite.servercommon.model.ClerkRole clerkRole;

    private com.smartbt.vtsuite.servercommon.model.Terminal terminal;

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Monitoring> monitorings = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Monitoring>();

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> transaction = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Transaction>();

    private com.smartbt.vtsuite.servercommon.model.VTSession vTSession;

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> auditLog = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.AuditLog>();

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue> clerkParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue>();

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk> watchdogEntityClerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk>();

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlert> watchdogAlertOrigination = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogAlert>();

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk> watchdogAlertClerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk>();

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive( Boolean active ) {
        this.active = active;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial( String middleInitial ) {
        this.middleInitial = middleInitial;
    }

    public Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate( Date deactivationDate ) {
        this.deactivationDate = deactivationDate;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime( boolean firstTime ) {
        this.firstTime = firstTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer( Customer customer ) {
        this.customer = customer;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant( Merchant merchant ) {
        this.merchant = merchant;
    }

    public ClerkRole getClerkRole() {
        return clerkRole;
    }

    public void setClerkRole( ClerkRole clerkRole ) {
        this.clerkRole = clerkRole;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal( Terminal terminal ) {
        this.terminal = terminal;
    }

    public Collection<Monitoring> getMonitorings() {
        return monitorings;
    }

    public void setMonitorings( Collection<Monitoring> monitorings ) {
        this.monitorings = monitorings;
    }

    public Collection<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction( Collection<Transaction> transaction ) {
        this.transaction = transaction;
    }

    public VTSession getvTSession() {
        return vTSession;
    }

    public void setvTSession( VTSession vTSession ) {
        this.vTSession = vTSession;
    }

    public Collection<AuditLog> getAuditLog() {
        return auditLog;
    }

    public void setAuditLog( Collection<AuditLog> auditLog ) {
        this.auditLog = auditLog;
    }

    public Collection<ClerkParameterValue> getClerkParameterValue() {
        return clerkParameterValue;
    }

    public void setClerkParameterValue( Collection<ClerkParameterValue> clerkParameterValue ) {
        this.clerkParameterValue = clerkParameterValue;
    }

    public Collection<WatchdogEntityClerk> getWatchdogEntityClerk() {
        return watchdogEntityClerk;
    }

    public void setWatchdogEntityClerk( Collection<WatchdogEntityClerk> watchdogEntityClerk ) {
        this.watchdogEntityClerk = watchdogEntityClerk;
    }

    public Collection<WatchdogAlert> getWatchdogAlertOrigination() {
        return watchdogAlertOrigination;
    }

    public void setWatchdogAlertOrigination( Collection<WatchdogAlert> watchdogAlertOrigination ) {
        this.watchdogAlertOrigination = watchdogAlertOrigination;
    }

    public Collection<WatchdogAlertClerk> getWatchdogAlertClerk() {
        return watchdogAlertClerk;
    }

    public void setWatchdogAlertClerk( Collection<WatchdogAlertClerk> watchdogAlertClerk ) {
        this.watchdogAlertClerk = watchdogAlertClerk;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + ( this.terminal != null ? this.terminal.hashCode() : 0 );
        hash = 97 * hash + ( this.monitorings != null ? this.monitorings.hashCode() : 0 );
        hash = 97 * hash + ( this.transaction != null ? this.transaction.hashCode() : 0 );
        hash = 97 * hash + ( this.vTSession != null ? this.vTSession.hashCode() : 0 );
        hash = 97 * hash + ( this.auditLog != null ? this.auditLog.hashCode() : 0 );
        hash = 97 * hash + ( this.clerkParameterValue != null ? this.clerkParameterValue.hashCode() : 0 );
        hash = 97 * hash + ( this.watchdogEntityClerk != null ? this.watchdogEntityClerk.hashCode() : 0 );
        hash = 97 * hash + ( this.watchdogAlertOrigination != null ? this.watchdogAlertOrigination.hashCode() : 0 );
        hash = 97 * hash + ( this.watchdogAlertClerk != null ? this.watchdogAlertClerk.hashCode() : 0 );
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
        final ClerkDisplay other = (ClerkDisplay) obj;
        if ( this.terminal != other.terminal && ( this.terminal == null || !this.terminal.equals( other.terminal ) ) ) {
            return false;
        }
        if ( this.monitorings != other.monitorings && ( this.monitorings == null || !this.monitorings.equals( other.monitorings ) ) ) {
            return false;
        }
        if ( this.transaction != other.transaction && ( this.transaction == null || !this.transaction.equals( other.transaction ) ) ) {
            return false;
        }
        if ( this.vTSession != other.vTSession && ( this.vTSession == null || !this.vTSession.equals( other.vTSession ) ) ) {
            return false;
        }
        if ( this.auditLog != other.auditLog && ( this.auditLog == null || !this.auditLog.equals( other.auditLog ) ) ) {
            return false;
        }
        if ( this.clerkParameterValue != other.clerkParameterValue && ( this.clerkParameterValue == null || !this.clerkParameterValue.equals( other.clerkParameterValue ) ) ) {
            return false;
        }
        if ( this.watchdogEntityClerk != other.watchdogEntityClerk && ( this.watchdogEntityClerk == null || !this.watchdogEntityClerk.equals( other.watchdogEntityClerk ) ) ) {
            return false;
        }
        if ( this.watchdogAlertOrigination != other.watchdogAlertOrigination && ( this.watchdogAlertOrigination == null || !this.watchdogAlertOrigination.equals( other.watchdogAlertOrigination ) ) ) {
            return false;
        }
        if ( this.watchdogAlertClerk != other.watchdogAlertClerk && ( this.watchdogAlertClerk == null || !this.watchdogAlertClerk.equals( other.watchdogAlertClerk ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString( this );
    }

}
