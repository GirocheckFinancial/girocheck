/*
 ** File: PerformanceTimer.java
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
package com.smartbt.vtsuite.servercommon.utils;

import java.io.Serializable;

/**
 * PerformanceTimer Class - track component processing times.
 */
public class PerformanceTimer implements Serializable {

    private long CLIENT_RECV_REQ;   //   ("recevied request from client"),
    private long FE_BEGIN;   //   ("frontend began processing"),
    private long FE_SEND;   //   ("frontend send message to core"),
    private long CORE_BEGIN;   //   ("core began processing"),
    private long CORE_SEND;   //   ("core sent message to host module"),
    private long HOST_BEGIN;   //   ("host module began processing"),
    private long COMMS_BEGIN;   //   ("host module began communications with remote host"),
    private long COMMS_END;   //   ("host module completed communications with remote host"),
    private long HOST_END;   //   ("host module completed processing"),
    private long CORE_RECV;   //   ("core received message from host module"),
    private long CORE_END;   //   ("core completed processing"),
    private long FE_RECV;   //   ("frontend received message from core"),
    private long FE_END;   //   ("frontend completed processing"),
    private long CLIENT_SEND_RSP;   //   ("sent reply to client");

    public PerformanceTimer() {
       // this.CLIENT_RECV_REQ = System.currentTimeMillis();
    }

    /**
     */
    public void setCLIENT_RECV_REQ() {
        this.CLIENT_RECV_REQ = System.currentTimeMillis();
    }

    /**
     */
    public void setFE_BEGIN() {
        this.FE_BEGIN = System.currentTimeMillis();
    }

    /**
     */
    public void setFE_SEND() {
        this.FE_SEND = System.currentTimeMillis();
    }

    /**
     */
    public void setCORE_BEGIN() {
        this.CORE_BEGIN = System.currentTimeMillis();
    }

    /**
     */
    public void setCORE_SEND() {
        this.CORE_SEND = System.currentTimeMillis();
    }

    /**
     */
    public void setHOST_BEGIN() {
        this.HOST_BEGIN = System.currentTimeMillis();
    }

    /**
     */
    public void setCOMMS_BEGIN() {
        this.COMMS_BEGIN = System.currentTimeMillis();
    }

    /**
     */
    public void setCOMMS_END() {
        this.COMMS_END = System.currentTimeMillis();
    }

    /**
     */
    public void setHOST_END() {
        this.HOST_END = System.currentTimeMillis();
    }

    /**
     */
    public void setCORE_RECV() {
        this.CORE_RECV = System.currentTimeMillis();
    }

    /**
     */
    public void setCORE_END() {
        this.CORE_END = System.currentTimeMillis();
    }

    /**
     */
    public void setFE_RECV() {
        this.FE_RECV = System.currentTimeMillis();
    }

    /**
     */
    public void setFE_END() {
        this.FE_END = System.currentTimeMillis();
    }

    /**
     */
    public void setCLIENT_SEND_RSP() {
        this.CLIENT_SEND_RSP = System.currentTimeMillis();
    }

    /**
     */
    public void setCLIENT_RECV_REQ(long millis) {
        this.CLIENT_RECV_REQ = millis;
    }

    /**
     */
    public void setFE_BEGIN(long millis) {
        this.FE_BEGIN = millis;
    }

    /**
     */
    public void setFE_SEND(long millis) {
        this.FE_SEND = millis;
    }

    /**
     */
    public void setCORE_BEGIN(long millis) {
        this.CORE_BEGIN = millis;
    }

    /**
     */
    public void setCORE_SEND(long millis) {
        this.CORE_SEND = millis;
    }

    /**
     */
    public void setHOST_BEGIN(long millis) {
        this.HOST_BEGIN = millis;
    }

    /**
     */
    public void setCOMMS_BEGIN(long millis) {
        this.COMMS_BEGIN = millis;
    }

    /**
     */
    public void setCOMMS_END(long millis) {
        this.COMMS_END = millis;
    }

    /**
     */
    public void setHOST_END(long millis) {
        this.HOST_END = millis;
    }

    /**
     */
    public void setCORE_RECV(long millis) {
        this.CORE_RECV = millis;
    }

    /**
     */
    public void setCORE_END(long millis) {
        this.CORE_END = millis;
    }

    /**
     */
    public void setFE_RECV(long millis) {
        this.FE_RECV = millis;
    }

    /**
     */
    public void setFE_END(long millis) {
        this.FE_END = millis;
    }

    /**
     */
    public void setCLIENT_SEND_RSP(long millis) {
        this.CLIENT_SEND_RSP = millis;
    }

    @Override
    public String toString() {

        long clientStart = FE_BEGIN - CLIENT_RECV_REQ;
        long frontStart = FE_SEND - FE_BEGIN;
        long jmsFrontCore = CORE_BEGIN - FE_SEND;
        long coreStart = CORE_SEND - CORE_BEGIN;
        long jmsCoreHost = HOST_BEGIN - CORE_SEND;
        long hostStart = COMMS_BEGIN - HOST_BEGIN;
        long comms = COMMS_END - COMMS_BEGIN;
        long hostFinish = HOST_END - COMMS_END;
        long jmsHostCore = CORE_RECV - HOST_END;
        long coreFinish = CORE_END - CORE_RECV;
        long jmsCoreFront = FE_RECV - CORE_END;
        long frontFinish = FE_END - FE_RECV;
        long clientFinish = CLIENT_SEND_RSP - FE_END;
        long totalTransactionTime = CLIENT_SEND_RSP - CLIENT_RECV_REQ;

        return String.format("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d", clientStart, frontStart, jmsFrontCore, coreStart, jmsCoreHost, hostStart, comms, hostFinish, jmsHostCore, coreFinish, jmsCoreFront, frontFinish, clientFinish, totalTransactionTime);
    }
}
