/*
 ** File: Diagnosable.java
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
package com.smartbt.girocheck.servercommon.diagnostics;

import java.util.Map;

/**
 * Diagnosable Class
 */
public interface Diagnosable {

    /**
     * Enables diagnostics
     */
    public void enableDiagnostics();

    /**
     * Determines enabled status
     *
     * @return Determination
     */
    public boolean areDiagnosticsEnabled();

    /**
     * Records diagnostic event data
     *
     * @param evt The event
     * @param value The event value
     */
    public void recordDiagnosticEvent(DiagnosticEvent evt, Long value);

    /**
     * Records diagnostic event data
     *
     * @param evt The event
     */
    public void recordDiagnosticEvent(DiagnosticEvent evt);

    /**
     * Adds diagnostic event record
     *
     * @param diagEvts The events
     */
    public void addDiagnosticEvents(Map<DiagnosticEvent, Long> diagEvts);

    /**
     * Gets diagnostic event records
     *
     * @return Map
     */
    public Map<DiagnosticEvent, Long> getDiagnosticEvents();

    /**
     * Copies diagnostic event record
     *
     * @param other
     */
    public void copyDiagnosticEvents(Diagnosable other);

    /**
     * Retrieves diagnostic event record
     *
     * @param evt The event
     * @return Event Record
     */
    public Long retrieveEventRecord(DiagnosticEvent evt);

    /**
     * Determines elapsed time between events
     *
     * @param evtT1 First event.
     * @param evtT2 Last event.
     * @return Elapsed time
     */
    public Long getElapsedTime(DiagnosticEvent evtT1, DiagnosticEvent evtT2);
}
