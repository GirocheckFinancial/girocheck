/*
 ** File: MerchantDetailLayout.java
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


import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailLayout;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailRowHLayout;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.UploadFileEditor;
import com.smartbt.vtsuite.vtams.client.helpers.JavaScriptMethodCallback;
import com.smartbt.vtsuite.vtams.client.helpers.JavaScriptMethodHelper;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.JSONEncoder;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;

/**
 * The Merchant Detail Layout
 *
 * @author Ariel Saavedra
 */
public class MerchantDetailLayout extends BaseDetailLayout {

    public MerchantDetailLayout(Record record) {
        super(record);
    }

    public MerchantDetailLayout(Record record, boolean allDetails, BaseDatasource datasource) {
        super(record, allDetails, datasource);
    }

    @Override
    protected void addLeftTopFields() {
        vLayoutLeftTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_LEGAL_NAME(), record.getAttributeAsString("name")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_SIC(), record.getAttributeAsString("sic")));
    }

    @Override
    protected void addRightTopFields() {
        vLayoutRightTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_NUMBER(), record.getAttributeAsString("number")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_STATUS(), (record.getAttributeAsBoolean("active") ? "Active" : "Inactive")));
    }

    @Override
    protected void addLeftBottomFields() {
        vLayoutLeftBottom.addMember(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_MERCHANT_DESCRIPTION(), record.getAttributeAsString("description")));
        vLayoutLeftBottom.addMember(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_CUSTOMER_NAME(), record.getAttributeAsString("customerName")));

        Record[] addressRecords = record.getAttributeAsRecordArray("address");
        for (Record addressRecord : addressRecords) {
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ZIP(), addressRecord.getAttributeAsString("zip")));
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_STATE(), addressRecord.getAttributeAsRecord("state").getAttributeAsString("name")));            
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ADDRESS_LINE1(), addressRecord.getAttributeAsString("address1")));
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ADDRESS_LINE2(), addressRecord.getAttributeAsString("address2")));
            //vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_CITY(), addressRecord.getAttributeAsString("city")));
            
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_COUNTRY(), addressRecord.getAttributeAsString("country")));
        }

        Record[] telephonesRecords = record.getAttributeAsRecordArray("telephones");
        for (Record telephonesRecord : telephonesRecords) {
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(
                    telephonesRecord.getAttributeAsRecord("telephoneType").getAttributeAsString("name") + ":", 
                    telephonesRecord.getAttributeAsString("number")));
        }
    }

    @Override
    protected void addRightBottomFields() {
//        final DynamicForm formParameter = new DynamicForm();
//        formParameter.setNumCols(2);
//        formParameter.setColWidths("80%", "20%");
//        formParameter.setTitleOrientation(TitleOrientation.TOP);
//
//        BaseTextItem parameterItem = new BaseTextItem("parameter", "Default AMS Parameter Base", true);
//        parameterItem.setWidth("100%");
//        ButtonItem resetParamButton = new ButtonItem();
//        resetParamButton.setVAlign(VerticalAlignment.BOTTOM);
//        resetParamButton.setTitle("Reset");
//        resetParamButton.setStartRow(false);
//        resetParamButton.setEndRow(false);
//        resetParamButton.setWidth(50);
//        resetParamButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
//            public void onClick(ClickEvent event) {
//                if (formParameter.validate()) {
//                    SC.ask("Resting parameter", "You are about to reset the parameter entered, do you really want to do this action?", new BooleanCallback() {
//                        public void execute(Boolean value) {
//                            if (value.equals(Boolean.TRUE)) {
//                                BaseDatasource ds = new BaseDatasource();
//                                ds.setFetchDataURL(Properties.RESET_MERCHANT_PARAMETER_WS);
//                                Criteria c = formParameter.getValuesAsCriteria();
//                                c.addCriteria("id", record.getAttributeAsString("id"));
//                                ds.fetchData(c, null);
//                            }
//                        }
//                    });
//                }
//            }
//        });
//        formParameter.setFields(new RowSpacerItem(), parameterItem, resetParamButton, new RowSpacerItem());

        final DynamicForm formLogo = new DynamicForm();
        formLogo.setTitleOrientation(TitleOrientation.TOP);

        final CanvasItem logoContent = new CanvasItem("merchantImg", "Merchant Image");
        logoContent.setHeight(130);
        logoContent.setWidth(255);
        final Img logo = new Img();
        logo.setBorder("1px solid black");
        logoContent.setCanvas(logo);
        logo.setSrc(record.getAttributeAsString("logoImage"));

        final ButtonItem uploadImage = new ButtonItem();
        uploadImage.setAlign(Alignment.RIGHT);
        uploadImage.setTitle(I18N.GET.BUTTON_UPLOAD_IMAGE());
        uploadImage.setWidth(100);

        uploadImage.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(ClickEvent event) {
                final UploadFileEditor uploadFileEditor = new UploadFileEditor(record.getAttributeAsInt("id"),
                        Properties.SET_MERCHANT_LOGO_WS,
                        I18N.GET.WINDOW_UPLOAD_MERCHANTLOGO_BOX_TITLE(), "Select an image");
                uploadFileEditor.addListener(new EditorListener() {
                    public void SaveActionExecuted() {
                         JavaScriptMethodHelper.addUploadFileCallback(new JavaScriptMethodCallback() {
                            public void execute(String obj) {
                                Record response = new Record(JSONEncoder.decode(obj));
                                if (response.getAttributeAsInt("status") == Constants.CODE_SUCCESS) {
                                    logo.setSrc(response.getAttributeAsRecord("data").getAttributeAsString("logoImage"));
                                } else {
                                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), response.getAttribute("statusMessage"));
                                }
                            }
                        });
                        uploadFileEditor.submitForm();
                        uploadFileEditor.hide();
                    }

                    public void CloseActionExecuted() {
                        uploadFileEditor.hide();
                    }
                });

                uploadFileEditor.show();
            }
        });

        formLogo.setFields(new RowSpacerItem(), logoContent, uploadImage);


        vLayoutRightBottom.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ACTIVATION_DATE(), record.getAttributeAsString("activationDate")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_DEACTIVATION_DATE(), record.getAttributeAsString("deactivationDate")),
                /*formParameter,*/ formLogo);
    }
}
