/*
 ** File: TransactionDetailWindow.java
 **
 ** Date Created: Janauary 2014
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
package com.smartbt.vtsuite.vtams.client.gui.window.detail;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Label;

/**
 * The MonitoringDetailWindow Detail Window
 *
 * @author Arierl Saavedra
 */
public class MonitoringDetailWindow extends BaseEditorWindow {

    protected Label contentLabel;

    /**
     * Constructor
     *
     * @param record
     */
    public MonitoringDetailWindow(Record record) {
        super(I18N.GET.WINDOW_MONITORING_TITLE());

         mainVLayout.setWidth(500);
         mainVLayout.setHeight(400);  
        
        contentLabel = new Label();
        contentLabel.setContents(record.getAttributeAsString("information"));
        
        mainVLayout.removeMember(dataForm);
        mainVLayout.removeMember(actionLayout);
        mainVLayout.addMember(contentLabel);

        addListener(new EditorListener() {
            public void SaveActionExecuted() {
                // TODO: Do Nothing
            }

            public void CloseActionExecuted() {
                destroy();
            }
        });
    }
}
