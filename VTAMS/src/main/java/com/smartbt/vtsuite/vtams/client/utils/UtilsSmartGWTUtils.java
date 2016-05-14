/*
 ** File: Utils.java
 **
 ** Date Created: June 2013
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
package com.smartbt.vtsuite.vtams.client.utils;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author Ariel Saavedra
 */
public class UtilsSmartGWTUtils {

    public static VLayout getLineSpace() {
        VLayout layout = new VLayout();

        Label space = new Label();
        space.setStyleName("detail-space");
        space.setHeight(5);
        layout.setHeight(30);
        layout.setAlign(VerticalAlignment.BOTTOM);
        layout.addMember(space);
        return layout;
    }
}
