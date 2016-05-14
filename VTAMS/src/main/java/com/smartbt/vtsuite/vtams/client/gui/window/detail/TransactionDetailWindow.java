/*
 ** File: TransactionDetailWindow.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.detail;

import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartgwt.client.data.Record;

/**
 * The Transaction Detail Form
 *
 * @author Ariamnet Lopez
 */
public class TransactionDetailWindow extends BaseEditorWindow {


    /**
     * Constructor
     *
     * @param record the Transaction record
     */
    public TransactionDetailWindow(Record record) {
        super(I18N.GET.WINDOW_RECEIPT_TITLE());

        final Image tempImage = new Image();
        tempImage.setUrl(record.getAttributeAsString("data"));
        
        mainVLayout.addMember(tempImage, 0);
        
        tempImage.addLoadHandler(new LoadHandler() {
            public void onLoad(LoadEvent event) {           
                mainVLayout.setWidth(tempImage.getWidth());
                mainVLayout.setHeight(tempImage.getHeight());                
            }
        });         

        mainVLayout.removeMember(dataForm);
        mainVLayout.removeMember(actionLayout);

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
