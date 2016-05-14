/*
 ** File: PaginationListener.java
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

import java.util.EventListener;

/**
 * The Pagination Event Listener Interface
 *
 * @author Ariamnet Lopez
 */
public interface PaginationListener extends EventListener {

    /**
     * Previous Page event
     *
     */
    public void PreviousActionExecuted();

    /**
     * Next Page event
     *
     */
    public void NextActionExecuted();
}
