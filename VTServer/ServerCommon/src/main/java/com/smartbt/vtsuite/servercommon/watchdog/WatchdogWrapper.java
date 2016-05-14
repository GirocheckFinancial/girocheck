/*
 ** File: WatchdogWrapper.java
 **
 ** Date Created: December 2013
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
package com.smartbt.vtsuite.servercommon.watchdog;

import com.smartbt.vtsuite.common.SessionClerk;
import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.vtsuite.servercommon.dao.VTSessionDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.model.User;
import com.smartbt.vtsuite.servercommon.watchdog.messages.WatchdogMessage;
import com.smartbt.vtsuite.servercommon.watchdog.processor.WatchdogINFOProcessor;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomWatchdog;
import java.util.LinkedList;
import java.util.List;
import javax.jms.JMSException;

/**
 *
 * @author Ariel Saavedra
 */
public class WatchdogWrapper {

    public static void wrapINFOMessage(Object data) throws JMSException {
        WatchdogMessage message = new WatchdogMessage();
        message.setData(data);
        message.setWatchdogType(NomWatchdog.INFO);
        setLoguedUser(message);
        if (message.getClerk() != null) {
            JMSManager.get().send(message, JMSManager.get().getWatchdogQueue());
        } else {
            WatchdogINFOProcessor.proccess(message);
        }
    }

    public static void wrapAccountDeletedMessage() throws JMSException {
        if (SessionClerk.get() != null) {
            Terminal terminal = new Terminal();
            terminal.setId(SessionClerk.get().getTerminal().getId());

            WatchdogMessage message = new WatchdogMessage();
            message.setWatchdogType(NomWatchdog.CARD_DELETED);
            message.setTerminal(terminal);
            message.setClerk(SessionClerk.get().getClerk());

            JMSManager.get().send(message, JMSManager.get().getWatchdogQueue());
        }
    }

    public static void wrapTransactionMessage(TransactionDisplay tranReq, TransactionDisplay tranResp) throws JMSException {
        Terminal terminal = new Terminal();
        terminal.setId(tranResp.getTerminal().getId());

        List<Object> listTrans = new LinkedList<Object>();
        listTrans.add(tranReq);
        listTrans.add(tranResp);

        WatchdogMessage message = new WatchdogMessage();
        message.setDataList(listTrans);
        message.setTerminal(terminal);
        setLoguedUser(message);

        JMSManager.get().send(message, JMSManager.get().getWatchdogQueue());
    }

    private static void setLoguedUser(WatchdogMessage message) {
        if (SessionClerk.get() != null) {
            Clerk clerk = new Clerk();
            clerk.setId(SessionClerk.get().getClerk().getId());
            message.setClerk(clerk);

            Terminal terminal = new Terminal();
            terminal.setId(SessionClerk.get().getTerminal().getId());

            message.setTerminal(terminal);
        } else if (SessionAMSUser.get() != null) {
            User user = new User();
            user.setId(SessionAMSUser.get().getId());
            message.setUser(user);
        }
    }
}
