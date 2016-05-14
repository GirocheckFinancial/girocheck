/*
 ** File: ParameterValueEditor.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.editor;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.UploadItem;

/**
 * The Parameter Value Editor Window
 *
 * @author Ariel Saavedra
 */
public class UploadFileEditor extends BaseEditorWindow {

    private UploadItem uploadLogo;

    /**
     * Constructor
     *
     * @param idMerchant
     * @param formAction
     * @param uploadFileTitle
     * @param boxTitle
     */
    public UploadFileEditor(int idMerchant, String formAction, String boxTitle, String uploadFileTitle) {
        super(boxTitle);        
       
        dataForm.setAction(formAction);
        dataForm.setEncoding(Encoding.MULTIPART);
        dataForm.setTarget("frame");
        dataForm.setTitleOrientation(TitleOrientation.TOP);

        final HiddenItem idMerchantHiddenField = new HiddenItem("id");
        idMerchantHiddenField.setValue(idMerchant);

        uploadLogo = new UploadItem("file", uploadFileTitle);
        uploadLogo.setRequired(true);
        uploadLogo.setWidth(400);
        uploadLogo.setErrorOrientation(FormErrorOrientation.RIGHT);

        dataForm.setFields(uploadLogo, idMerchantHiddenField);
    }
}
