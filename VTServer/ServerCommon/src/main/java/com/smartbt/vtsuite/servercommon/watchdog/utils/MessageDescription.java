/*
 ** File: WatchdogMessage.java
 **
 ** Date Created: October 2013
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
package com.smartbt.vtsuite.servercommon.watchdog.utils;

import java.text.NumberFormat;

/**
 *
 * @author Ariel Saavedra
 */
public class MessageDescription {

     public static String cardDeletedMsg() {      
        return "This user has deleted a customer's account";
    }
    
    public static String repeatTransMsg(double totalAmount) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return "This user has performed a transaction with the same card and with the amount of " + format.format(totalAmount);
    }

    public static String refundAmountMsg(double totalAmountWatchDog, double totalAmountTransaction) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return "Ran refund with " + format.format(totalAmountTransaction)
                + " exceeding the " + format.format(totalAmountWatchDog) + " specified";
    }

    public static String voidMsg(String valueWatchDog, long quantityVoids) {
        return "This user has performed " + quantityVoids + " voids exceeding the quantity specified of " + valueWatchDog;
    }

    public static String refundQuantityMsg(String valueWatchDog, long quantityRefunds) {
        return "This user has performed " + quantityRefunds + " refunds exceeding the quantity specified of " + valueWatchDog;
    }

    public static String swipesQuantityMsg(String valueWatchDog, long quantitySwipes) {
        return "This user has performed " + quantitySwipes + " swipes exceeding the quantity specified of " + valueWatchDog;
    }

    public static String cvvSkipppedMsg(int transSequence) {
        return "This user has skipped the CVV in a transaction with reference " + transSequence;
    }
}
