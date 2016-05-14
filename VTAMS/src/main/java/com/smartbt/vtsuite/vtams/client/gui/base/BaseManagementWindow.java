/*
 ** File: BaseManagementWindow.java
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
package com.smartbt.vtsuite.vtams.client.gui.base;

import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * The Base Management Window
 *
 * @author Ariamnet Lopez
 */
public class BaseManagementWindow extends TabSet {

    /**
     * Constructor
     *
     */
    public BaseManagementWindow() {
        super();

        setTabBarPosition(Side.TOP);
    }
}