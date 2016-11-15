/*
 ** File: DirexTransactionRequest.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software mays be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.servercommon.messageFormat;

import com.smartbt.girocheck.servercommon.diagnostics.Diagnosable;
import com.smartbt.girocheck.servercommon.diagnostics.DiagnosticEvent;
import java.util.HashSet;
import java.util.Map;

/**
 * Direx Transaction Request Class
 */
public class DirexTransactionRequest extends DirexTransaction implements Diagnosable, Cloneable {

    private static final long serialVersionUID = 1L;

    private String correlation;

    private String requestId;

    @Override
    public DirexTransactionRequest clone() throws CloneNotSupportedException { 
        DirexTransactionRequest clon =  (DirexTransactionRequest) super.clone();
        clon.getTransaction().setSub_Transaction(new HashSet());
        return clon;
    }

    public void setCorrelation(String correlation) {
        this.correlation = correlation;
    }

    public String getCorrelation() {
        return correlation;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void enableDiagnostics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean areDiagnosticsEnabled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void recordDiagnosticEvent(DiagnosticEvent evt, Long value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void recordDiagnosticEvent(DiagnosticEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addDiagnosticEvents(Map<DiagnosticEvent, Long> diagEvts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Map<DiagnosticEvent, Long> getDiagnosticEvents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void copyDiagnosticEvents(Diagnosable other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long retrieveEventRecord(DiagnosticEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getElapsedTime(DiagnosticEvent evtT1, DiagnosticEvent evtT2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
