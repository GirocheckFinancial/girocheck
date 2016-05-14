/*
 ** File: BaseNumberItem.java
 **
 ** Date Created: February 2013
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
package com.smartbt.vtsuite.vtams.client.gui.component;

import com.smartgwt.client.types.Alignment;

/**
 * The Base Number Item Class
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 *
 */
public class BaseNumberItem extends BaseTextItem {
    
    /**
     * Constructor
     *
     * @param tabIndex
     * @param name the name
     * @param title the title
     * @param tooltip
     * @param isRequired
     */
    public BaseNumberItem(int tabIndex, String name, String title, String tooltip, Boolean isRequired) {
        super(tabIndex, name, title, tooltip, isRequired);

        setValidateOnExit(true);
        setShowHintInField(false);
        setMaskPromptChar("");
        setKeyPressFilter("[0-9]");
        setTextAlign(Alignment.RIGHT);
    }
}
