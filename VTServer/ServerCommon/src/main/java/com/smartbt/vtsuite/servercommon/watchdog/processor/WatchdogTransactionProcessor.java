/*
 ** File: WatchdogTransactionProcessor.java
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
package com.smartbt.vtsuite.servercommon.watchdog.processor;

import com.smartbt.vtsuite.servercommon.dao.AccountDAO;
import com.smartbt.vtsuite.servercommon.watchdog.manager.WatchdogManager;
import com.smartbt.vtsuite.servercommon.dao.TransactionDAO;
import com.smartbt.vtsuite.servercommon.dao.WatchdogDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.vtsuite.servercommon.model.Transaction;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntity;
import com.smartbt.vtsuite.servercommon.watchdog.messages.WatchdogMessage;
import com.smartbt.vtsuite.servercommon.watchdog.utils.MessageDescription;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomWatchdog;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class WatchdogTransactionProcessor {

    private static final Logger log = Logger.getLogger(WatchdogTransactionProcessor.class);

    public static void proccess(WatchdogMessage message) {
        TransactionDisplay transReq = (TransactionDisplay) message.getDataList().get(0);
        TransactionDisplay transResp = (TransactionDisplay) message.getDataList().get(1);

        if (!transResp.isApproved()) {
            return;
        }

        List<WatchdogEntity> watchdogEntities
                = WatchdogDAO.get().getWatchdogEntitiesByEntity(EntityType.TERMINAL, message.getTerminal().getId());

        for (WatchdogEntity watchdogEntity : watchdogEntities) {
            //Check REFUNDS_AMOUNT -------------------------------------------------------------------------------------------------------------
            if (watchdogEntity.getWatchdog().getId() == NomWatchdog.REFUNDS_AMOUNT.getId()) {
                if (transResp.getOperation().equalsIgnoreCase(NomOperation.REFUND.toString())
                        && transResp.getTotalAmount() > Double.valueOf(watchdogEntity.getValue())) {
                    WatchdogManager.sendWatchdogAlert(
                            WatchdogManager.createWatchdogAlert(watchdogEntity,
                                    MessageDescription.refundAmountMsg(Double.valueOf(watchdogEntity.getValue()), transResp.getTotalAmount())),
                            message.getClerk());
                }
            } //Check VOIDS ---------------------------------------------------------------------------------------------------------------------
            else if (watchdogEntity.getWatchdog().getId() == NomWatchdog.VOIDS.getId()) {
                if (transResp.getOperation().toLowerCase().contains(NomOperation.VOID.toString().toLowerCase())) {

                    long quantityOfVoidsByClerkLast24H = TransactionDAO.get().getQuantityOfOperationsByClerkLast24H(message.getClerk(), NomOperation.VOID);
                    if (quantityOfVoidsByClerkLast24H >= Integer.valueOf(watchdogEntity.getValue())) {
                        WatchdogManager.sendWatchdogAlert(
                                WatchdogManager.createWatchdogAlert(watchdogEntity, MessageDescription.voidMsg(watchdogEntity.getValue(), quantityOfVoidsByClerkLast24H)),
                                message.getClerk());
                    }
                }
            } //Check CVV Skippped ---------------------------------------------------------------------------------------------------------------------
            else if (watchdogEntity.getWatchdog().getId() == NomWatchdog.CVV.getId()) {
                if ((!transResp.getOperation().toLowerCase().contains(NomOperation.VOID.toString().toLowerCase()))
                        && (transReq.getCvv() == null || transReq.getCvv().isEmpty())) {
                    WatchdogManager.sendWatchdogAlert(
                            WatchdogManager.createWatchdogAlert(watchdogEntity, MessageDescription.cvvSkipppedMsg(transResp.getSequence())),
                            message.getClerk());
                }
            } //Check REFUNDS QUANTITY ---------------------------------------------------------------------------------------------------------------------
            else if (watchdogEntity.getWatchdog().getId() == NomWatchdog.REFUNDS_QUANTITY.getId()) {
                if (transResp.getOperation().equalsIgnoreCase(NomOperation.REFUND.toString())) {
                    long quantityOfRefundsByClerkLast24H = TransactionDAO.get().getQuantityOfOperationsByClerkLast24H(message.getClerk(), NomOperation.REFUND);
                    if (quantityOfRefundsByClerkLast24H >= Integer.valueOf(watchdogEntity.getValue())) {
                        WatchdogManager.sendWatchdogAlert(
                                WatchdogManager.createWatchdogAlert(watchdogEntity, MessageDescription.refundQuantityMsg(watchdogEntity.getValue(), quantityOfRefundsByClerkLast24H)),
                                message.getClerk());
                    }
                }
            } //Check SWIPE ---------------------------------------------------------------------------------------------------------------------
            else if (watchdogEntity.getWatchdog().getId() == NomWatchdog.SWIPE.getId()) {
                long quantityOfSwipesByClerkLast24H = TransactionDAO.get().getQuantityOfSwipesByClerkLast24H(message.getClerk());
                if (transReq.getSwipe() && quantityOfSwipesByClerkLast24H >= Integer.valueOf(watchdogEntity.getValue())) {
                    WatchdogManager.sendWatchdogAlert(
                            WatchdogManager.createWatchdogAlert(watchdogEntity, MessageDescription.swipesQuantityMsg(watchdogEntity.getValue(), quantityOfSwipesByClerkLast24H)),
                            message.getClerk());
                }
            } //Check SIGNATURE ---------------------------------------------------------------------------------------------------------------------
            else if (watchdogEntity.getWatchdog().getId() == NomWatchdog.SIGNATURE.getId()) {

            } //Check for REPEAT TRANSACTION ---------------------------------------------------------------------------------------------------------------------
            else if (watchdogEntity.getWatchdog().getId() == NomWatchdog.REPEAT.getId()) {
                Transaction lastTransaction = TransactionDAO.get().getLastTransactionByClerkDiffToCurrent(message.getClerk(), transResp.getId());
                if (lastTransaction != null && lastTransaction.getAccount() != null) {
                    String currentTransactionPan = (transReq.getAccount() != null && transReq.getAccount().getId() != 0)
                            ? AccountDAO.get().findById(transReq.getAccount().getId()).getEncryptedData()
                            : transReq.getAccount().getPan();

                    if (lastTransaction.getMode().getName().equalsIgnoreCase(transResp.getMode())
                            && lastTransaction.getOperation().getName().equalsIgnoreCase(transResp.getOperation())
                            && lastTransaction.getAccount().getEncryptedData().equals(currentTransactionPan)
                            && lastTransaction.getTotalAmount() == transReq.getTotalAmount()) {
                        WatchdogManager.sendWatchdogAlert(
                                WatchdogManager.createWatchdogAlert(watchdogEntity, MessageDescription.repeatTransMsg(transReq.getTotalAmount())),
                                message.getClerk());
                    }
                }
            }

        }
    }

}
