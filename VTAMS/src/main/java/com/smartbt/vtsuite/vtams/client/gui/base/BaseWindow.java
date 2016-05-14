/*
 ** File: BaseWindow.java
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

import com.smartgwt.client.widgets.Window;

/**
 * The Base Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class BaseWindow extends Window {

    /**
     * Constructor
     *
     */
    public BaseWindow() {
        super();
        setCanDragReposition(false);
        setCanDragResize(false);

        setSize("700", "500");
        setShowMaximizeButton(false);
        setShowStatusBar(false);
        setShowMinimizeButton(false);
        setShowResizer(false);
        setShowCloseButton(false);
    }
}