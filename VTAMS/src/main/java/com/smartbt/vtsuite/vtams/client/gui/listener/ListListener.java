/*
 ** File: ListListener.java
 **
 ** Date Created: April 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.gui.listener;

import com.smartgwt.client.data.Record;
import java.util.EventListener;

/**
 * The List Event Listener Interface
 *
 * @author Ariamnet Lopez
 */
public interface ListListener extends EventListener {

    /**
     * Select event
     * 
     * @param record the selected record
     */
    public void SelectActionExecuted(Record record);

    /**
     * Selection Change event
     *
     * @param record the selected record
     */
    public void SelectionChangeActionExecuted(Record record);

    /**
     * Data Arrive event
     *
     */
    public void DataArrivedHandlerExecuted();
}
