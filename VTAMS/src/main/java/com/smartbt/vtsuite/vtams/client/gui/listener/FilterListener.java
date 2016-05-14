/*
 ** File: FilterListener.java
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
 * The Filter Form Event Listener Interface
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public interface FilterListener extends EventListener {

    /**
     * Filter event
     *
     */
    public void FilterActionExecuted();

    /**
     * Add event
     *
     */
    public void AddActionExecuted();

    /**
     * Update event
     *
     */
    public void UpdateActionExecuted();

    /**
     * Delete event
     *
     */
    public void DeleteActionExecuted();
    
    /**
     * Delete event
     *
     */
    public void DeleteAllActionExecuted();
    
    /**
     * Delete event
     *
     */
    public void ImportActionExecuted();
    
    /**
     * Rotate event
     *
     */
    public void RotateActionExecuted();
    
    /**
     * ChangePassword event
     *
     */
    public void ChangePasswordActionExecuted();
}
