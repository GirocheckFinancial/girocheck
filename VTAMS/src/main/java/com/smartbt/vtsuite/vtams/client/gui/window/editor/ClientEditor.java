/*
 ** File: ClientEditor.java
 **
 ** Date Created: October 2013
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


import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseNumberItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ClientDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TelephoneTypeDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.StateDS;
import com.smartbt.vtsuite.vtams.client.helpers.ValidatorHelper;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomAddressType;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.RequiredIfFunction;

/**
 * The Search Customer Form Class
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class ClientEditor extends BaseEditorWindow {

    private HiddenItem idClient;
    private BaseTextItem firstNameText;
    private BaseTextItem lastNameText;
    private BaseTextItem companyText;
    private BaseTextItem emailText;
    private BaseNumberItem phone1Text;
    private BaseNumberItem phone2Text;
    private BaseNumberItem phone3Text;
    private BaseSelectItem phoneType1Combo;
    private BaseSelectItem phoneType2Combo;
    private BaseSelectItem phoneType3Combo;
    private BaseTextItem personalAddress1Text;
    private BaseTextItem personalAddress2Text;
    private BaseTextItem personalCityText;
    private BaseSelectItem personalStateCombo;
    private BaseTextItem personalZipText;
    private BaseTextItem billingAddress1Text;
    private BaseTextItem billingAddress2Text;
    private BaseTextItem billingCityText;
    private BaseSelectItem billingStateCombo;
    private BaseTextItem billingZipText;
    private CheckboxItem usePersonalAddressCheckbox;
    //private Record record;

    public ClientEditor() {
        super("Customer Editor");
        dataForm.setNumCols(8);
        idClient = new HiddenItem();
        //Row 1 ------------------------------------------------           
        firstNameText = new BaseTextItem(1, "firstName", I18N.GET.LABEL_FIRST_NAME_TITLE(), I18N.GET.TOOLTIP_FIRST_NAME_MSG(), true);
        lastNameText = new BaseTextItem(2, "lastName", I18N.GET.LABEL_LAST_NAME_TITLE(), I18N.GET.TOOLTIP_LAST_NAME_MSG(), true);
        phone1Text = new BaseNumberItem(3, "phone1", I18N.GET.LABEL_CONTACT_NUMBER_TITLE(), I18N.GET.TOOLTIP_CONTACT_NUMBER_MSG(), false);
        phoneType1Combo = new BaseSelectItem(4, "phoneType1", "", I18N.GET.TOOLTIP_CONTACT_NUMBER_MSG(), new TelephoneTypeDS(), false);
        phoneType1Combo.setAutoFetchData(true);

        firstNameText.setColSpan(2);
        lastNameText.setColSpan(2);
        phone1Text.setColSpan(2);
        phoneType1Combo.setWidth(100);

        //Row 2 ------------------------------------------------------------------------------------------------
        companyText = new BaseTextItem(5, "company", I18N.GET.LABEL_COMPANY_TITLE(), I18N.GET.TOOLTIP_COMPANY_MSG(), false);
        phone2Text = new BaseNumberItem(6, "phone2", I18N.GET.LABEL_CONTACT_NUMBER_TITLE(), I18N.GET.TOOLTIP_CONTACT_NUMBER_MSG(), false);
        phoneType2Combo = new BaseSelectItem(7, "phoneType2", "", I18N.GET.TOOLTIP_CONTACT_NUMBER_MSG(), new TelephoneTypeDS(), false);
        phoneType2Combo.setAutoFetchData(true);

        companyText.setColSpan(4);
        phone2Text.setColSpan(2);
        phoneType2Combo.setWidth(100);

        //Row 3 ------------------------------------------------------------------------------------------------
        emailText = new BaseTextItem(8, "email", I18N.GET.LABEL_EMAIL_TITLE(), I18N.GET.TOOLTIP_EMAIL_MSG(), false);
        phone3Text = new BaseNumberItem(9, "phone3", I18N.GET.LABEL_CONTACT_NUMBER_TITLE(), I18N.GET.TOOLTIP_CONTACT_NUMBER_MSG(), false);
        phoneType3Combo = new BaseSelectItem(10, "phoneType3", "", I18N.GET.TOOLTIP_CONTACT_NUMBER_MSG(), new TelephoneTypeDS(), false);
        phoneType3Combo.setAutoFetchData(true);

        emailText.setColSpan(4);
        phone3Text.setColSpan(2);
        phoneType3Combo.setWidth(100);

        //Row 4 ------------------------------------------------------------------------------------------------          
        // Customer address
        HeaderItem personalAddressHeader = new HeaderItem();
        personalAddressHeader.setDefaultValue(I18N.GET.LABEL_CLIENT_PERSONAL_ADDRESS());
        personalAddressHeader.setColSpan(4);
        personalAddressHeader.setStartRow(false);
        personalAddressHeader.setEndRow(false);

        // Billing address
        HeaderItem billingAddressHeader = new HeaderItem();
        billingAddressHeader.setDefaultValue(I18N.GET.LABEL_CLIENT_BILLING_ADDRESS());
        billingAddressHeader.setColSpan(4);
        billingAddressHeader.setStartRow(false);
        billingAddressHeader.setEndRow(false);

        //Row 5 ------------------------------------------------------------------------------------------------
        usePersonalAddressCheckbox = new CheckboxItem("usePersonalAddress", I18N.GET.LABEL_USE_CLIENT_ADDRESS_TITLE());
        usePersonalAddressCheckbox.setColSpan(8);
        usePersonalAddressCheckbox.setTextAlign(Alignment.RIGHT);
        usePersonalAddressCheckbox.setEndRow(true);

        //Row 6 ------------------------------------------------------------------------------------------------        
        personalAddress1Text = new BaseTextItem(11, "paddress1", I18N.GET.LABEL_ADDRESS1_TITLE(), I18N.GET.TOOLTIP_ADDRESS_MSG(), true);
        billingAddress1Text = new BaseTextItem(16, "baddress1", I18N.GET.LABEL_ADDRESS1_TITLE(), I18N.GET.TOOLTIP_ADDRESS_MSG(), true);
        personalAddress1Text.setColSpan(4);
        billingAddress1Text.setColSpan(4);

        //Row 7 ------------------------------------------------------------------------------------------------         
        personalAddress2Text = new BaseTextItem(12, "paddress2", I18N.GET.LABEL_ADDRESS2_TITLE(), I18N.GET.TOOLTIP_ADDRESS_MSG(), false);
        billingAddress2Text = new BaseTextItem(17, "baddress2", I18N.GET.LABEL_ADDRESS2_TITLE(), I18N.GET.TOOLTIP_ADDRESS_MSG(), false);
        personalAddress2Text.setColSpan(4);
        billingAddress2Text.setColSpan(4);

        //Row 8 ------------------------------------------------------------------------------------------------
        personalCityText = new BaseTextItem(13, "pcity", I18N.GET.LABEL_CITY_TITLE(), I18N.GET.TOOLTIP_CITY_MSG(), true);
        personalStateCombo = new BaseSelectItem(14, "pstate", I18N.GET.LABEL_STATE_TITLE(), I18N.GET.TOOLTIP_STATE_MSG(), new StateDS(), true);
        personalStateCombo.setDisplayField("abbreviation");
        personalStateCombo.setAutoFetchData(true);

        billingCityText = new BaseTextItem(18, "bcity", I18N.GET.LABEL_CITY_TITLE(), I18N.GET.TOOLTIP_CITY_MSG(), true);
        billingStateCombo = new BaseSelectItem(19, "bstate", I18N.GET.LABEL_STATE_TITLE(), I18N.GET.TOOLTIP_STATE_MSG(), new StateDS(), true);
        billingStateCombo.setDisplayField("abbreviation");
        billingStateCombo.setAutoFetchData(true);

        personalCityText.setColSpan(2);
        personalStateCombo.setColSpan(2);
        personalStateCombo.setWidth(80);
        billingCityText.setColSpan(2);
        billingStateCombo.setColSpan(2);
        billingStateCombo.setWidth(80);

        //Row 9 ------------------------------------------------------------------------------------------------
        personalZipText = new BaseTextItem(15, "pzip", I18N.GET.LABEL_ZIP_TITLE(), I18N.GET.TOOLTIP_ZIP_MSG(), true);
        billingZipText = new BaseTextItem(20, "bzip", I18N.GET.LABEL_ZIP_TITLE(), I18N.GET.TOOLTIP_ZIP_MSG(), true);

        SpacerItem spacerItem = new SpacerItem();
        spacerItem.setColSpan(2);
        personalZipText.setColSpan(2);
        billingZipText.setColSpan(2);

        usePersonalAddressCheckbox.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                updateBillingAddressForm((Boolean) event.getValue());
            }
        });

        getResetButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                disableBillingAddresForm(false);
            }
        });

        // Set fields Validators --------------------------------------------------------------------------------
        firstNameText.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
        lastNameText.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
        companyText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        emailText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        personalAddress1Text.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        personalAddress2Text.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        personalCityText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        personalZipText.setLength(Constants.ZIP_MAX_LENGTH);
        billingAddress1Text.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        billingAddress2Text.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        billingCityText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        billingZipText.setLength(Constants.ZIP_MAX_LENGTH);
        phone1Text.setLength(Constants.PHONE_MAX_LENGTH);
        phone2Text.setLength(Constants.PHONE_MAX_LENGTH);
        phone3Text.setLength(Constants.PHONE_MAX_LENGTH);

        firstNameText.setKeyPressFilter(RegExp.VALID_TEXT_III_REG_EXP);
        lastNameText.setKeyPressFilter(RegExp.VALID_TEXT_III_REG_EXP);
        companyText.setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);
        personalAddress1Text.setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);
        personalAddress2Text.setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);
        personalCityText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);
        personalZipText.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        billingAddress1Text.setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);
        billingAddress2Text.setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);
        billingCityText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);
        billingZipText.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);

        firstNameText.setValidators(ValidatorHelper.getTextIIIValidator());
        lastNameText.setValidators(ValidatorHelper.getTextIIIValidator());
        companyText.setValidators(ValidatorHelper.getTextVIValidator());
        personalAddress1Text.setValidators(ValidatorHelper.getTextVIValidator());
        personalAddress2Text.setValidators(ValidatorHelper.getTextVIValidator());
        personalCityText.setValidators(ValidatorHelper.getTextVValidator());
        personalZipText.setValidators(ValidatorHelper.getTextValidator(Constants.ZIP_MIN_LENGTH, Constants.ZIP_MAX_LENGTH));
        billingAddress1Text.setValidators(ValidatorHelper.getTextVIValidator());
        billingAddress2Text.setValidators(ValidatorHelper.getTextVIValidator());
        billingCityText.setValidators(ValidatorHelper.getTextVValidator());
        billingZipText.setValidators(ValidatorHelper.getTextValidator(Constants.ZIP_MIN_LENGTH, Constants.ZIP_MAX_LENGTH));
        emailText.setValidators(ValidatorHelper.getEmailValidator());
        phone1Text.setValidators(ValidatorHelper.getPhoneValidator());
        phone2Text.setValidators(ValidatorHelper.getPhoneValidator());
        phone3Text.setValidators(ValidatorHelper.getPhoneValidator());

        phoneType1Combo.setValidators(ValidatorHelper.getRequiredIfValidator(new RequiredIfFunction() {

            public boolean execute(FormItem formItem, Object value) {
                return phone1Text.getValue() != null && !phone1Text.getValueAsString().isEmpty();
            }
        }));

        phoneType2Combo.setValidators(ValidatorHelper.getRequiredIfValidator(new RequiredIfFunction() {

            public boolean execute(FormItem formItem, Object value) {
                return phone2Text.getValue() != null && !phone2Text.getValueAsString().isEmpty();
            }
        }));

        phoneType3Combo.setValidators(ValidatorHelper.getRequiredIfValidator(new RequiredIfFunction() {

            public boolean execute(FormItem formItem, Object value) {
                return phone3Text.getValue() != null && !phone3Text.getValueAsString().isEmpty();
            }
        }));
        //--------------------------------------------------------------------------------------------------------------------
        dataForm.setTitleOrientation(TitleOrientation.TOP);
        dataForm.setDataSource(new ClientDS());
        dataForm.setFields(idClient,
                firstNameText, lastNameText, phone1Text, phoneType1Combo,
                companyText, phone2Text, phoneType2Combo,
                emailText, phone3Text, phoneType3Combo,
                new RowSpacerItem(),
                personalAddressHeader, billingAddressHeader,
                usePersonalAddressCheckbox,
                personalAddress1Text, billingAddress1Text,
                personalAddress2Text, billingAddress2Text,
                personalCityText, personalStateCombo, billingCityText, billingStateCombo,
                personalZipText, spacerItem, billingZipText, spacerItem);
    }

    private void updateBillingAddressForm(boolean useClientAddress) {
        billingAddress1Text.setValue("");
        billingAddress2Text.setValue("");
        billingCityText.setValue("");
        billingStateCombo.setValue("");
        billingZipText.setValue("");

        disableBillingAddresForm(useClientAddress);
        requiredBillingAddresForm(!useClientAddress);
        dataForm.validate();
        usePersonalAddressCheckbox.setValue(useClientAddress);
    }

    private void disableBillingAddresForm(boolean disable) {
        billingAddress1Text.setDisabled(disable);
        billingAddress2Text.setDisabled(disable);
        billingCityText.setDisabled(disable);
        billingStateCombo.setDisabled(disable);
        billingZipText.setDisabled(disable);
    }

    private void requiredBillingAddresForm(boolean required) {
        billingAddress1Text.setRequired(required);
        billingCityText.setRequired(required);
        billingStateCombo.setRequired(required);
        billingZipText.setRequired(required);
    }

    @Override
    public void updateRecord(Record record) {
        if (record == null) {
            updateRecordParent(new Record());
        } else {
            Criteria criteria = new Criteria();
            criteria.setAttribute("clientId", record.getAttributeAsInt("id"));

            ClientDS clientDS = new ClientDS();
            clientDS.setFetchDataURL(Properties.GET_CLIENT_WS);
            clientDS.fetchData(criteria, new DSCallback() {
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    updateRecordParent(response.getData()[0]);
                }
            }, null);
        }
    }

    private void updateRecordParent(Record record) {
        super.updateRecord(record);
        loadRecord(record);
        disableBillingAddresForm(false);
        requiredBillingAddresForm(true);
        if (record.getAttributes().length > 1) {
            dataForm.validate();
        }
    }

    private void loadRecord(Record record) {
        idClient.setAttribute("id", record.getAttribute("id"));
        Record[] arrayRecords = record.getAttributeAsRecordArray("clientTelephoneList");
        for (int i = 0; i < arrayRecords.length; i++) {
            switch (i) {
                case 0: {
                    phone1Text.setAttribute("id", arrayRecords[i].getAttribute("id"));
                    phone1Text.setValue(arrayRecords[i].getAttribute("number"));
                    phoneType1Combo.setValue(arrayRecords[i].getAttributeAsRecord("telephoneType").getAttributeAsInt("id"));
                    break;
                }
                case 1: {
                    phone2Text.setAttribute("id", arrayRecords[i].getAttribute("id"));
                    phone2Text.setValue(arrayRecords[i].getAttribute("number"));
                    phoneType2Combo.setValue(arrayRecords[i].getAttributeAsRecord("telephoneType").getAttributeAsInt("id"));
                    break;
                }
                case 2: {
                    phone3Text.setAttribute("id", arrayRecords[i].getAttribute("id"));
                    phone3Text.setValue(arrayRecords[i].getAttribute("number"));
                    phoneType3Combo.setValue(arrayRecords[i].getAttributeAsRecord("telephoneType").getAttributeAsInt("id"));
                    break;
                }
            }
        }
        arrayRecords = record.getAttributeAsRecordArray("clientAddressList");
        for (Record r : arrayRecords) {
            if (r.getAttributeAsRecord("addressType").getAttributeAsInt("id") == NomAddressType.PERSONAL.getId()) {
                personalAddress1Text.setAttribute("id", r.getAttribute("id"));
                personalAddress1Text.setValue(r.getAttribute("address1"));
                personalAddress2Text.setValue(r.getAttribute("address2"));
                personalCityText.setValue(r.getAttribute("city"));
                personalZipText.setValue(r.getAttribute("zip"));
                personalStateCombo.setValue(r.getAttributeAsRecord("state").getAttributeAsInt("id"));
            } else if (r.getAttributeAsRecord("addressType").getAttributeAsInt("id") == NomAddressType.BILLING.getId()) {
                billingAddress1Text.setAttribute("id", r.getAttribute("id"));
                billingAddress1Text.setValue(r.getAttribute("address1"));
                billingAddress2Text.setValue(r.getAttribute("address2"));
                billingCityText.setValue(r.getAttribute("city"));
                billingZipText.setValue(r.getAttribute("zip"));
                billingStateCombo.setValue(r.getAttributeAsRecord("state").getAttributeAsInt("id"));
            }
        }
        dataForm.rememberValues();
    }

    public Record getRecord(int merchantId) {
        //loading telephones
        Record phoneType1 = new Record();
        phoneType1.setAttribute("id", phoneType1Combo.getValue());

        Record phoneType2 = new Record();
        phoneType2.setAttribute("id", phoneType2Combo.getValue());

        Record phoneType3 = new Record();
        phoneType3.setAttribute("id", phoneType3Combo.getValue());

        Record phone1 = new Record();
        phone1.setAttribute("id", phone1Text.getAttribute("id"));
        phone1.setAttribute("number", phone1Text.getValueAsString());
        phone1.setAttribute("telephoneType", phoneType1);

        Record phone2 = new Record();
        phone2.setAttribute("id", phone2Text.getAttribute("id"));
        phone2.setAttribute("number", phone2Text.getValueAsString());
        phone2.setAttribute("telephoneType", phoneType2);

        Record phone3 = new Record();
        phone3.setAttribute("id", phone3Text.getAttribute("id"));
        phone3.setAttribute("number", phone3Text.getValueAsString());
        phone3.setAttribute("telephoneType", phoneType3);

        RecordList phones = new RecordList();
        if (phone1Text.getValue() != null) {
            phones.add(phone1);
        }
        if (phone2Text.getValue() != null) {
            phones.add(phone2);
        }
        if (phone3Text.getValue() != null) {
            phones.add(phone3);
        }
        //loading addresses       
        Record personalState = new Record();
        personalState.setAttribute("id", personalStateCombo.getValue());

        Record personalAddressType = new Record();
        personalAddressType.setAttribute("id", NomAddressType.PERSONAL.getId());

        Record personalAddress = new Record();
        personalAddress.setAttribute("id", personalAddress1Text.getAttribute("id"));
        personalAddress.setAttribute("address1", personalAddress1Text.getValueAsString());
        personalAddress.setAttribute("address2", personalAddress2Text.getValueAsString());
        personalAddress.setAttribute("city", personalCityText.getValueAsString());
        personalAddress.setAttribute("zip", personalZipText.getValueAsString());
        personalAddress.setAttribute("state", personalState);
        personalAddress.setAttribute("addressType", personalAddressType);

        Record billingState = new Record();
        billingState.setAttribute("id", billingStateCombo.getValue());

        Record billingAddressType = new Record();
        billingAddressType.setAttribute("id", NomAddressType.BILLING.getId());

        Record billingAddress = new Record();
        billingAddress.setAttribute("id", billingAddress1Text.getAttribute("id"));
        billingAddress.setAttribute("addressType", billingAddressType);
        if (usePersonalAddressCheckbox.getValueAsBoolean()) {
            billingAddress.setAttribute("address1", personalAddress1Text.getValueAsString());
            billingAddress.setAttribute("address2", personalAddress2Text.getValueAsString());
            billingAddress.setAttribute("city", personalCityText.getValueAsString());
            billingAddress.setAttribute("zip", personalZipText.getValueAsString());
            billingAddress.setAttribute("state", personalState);
        } else {
            billingAddress.setAttribute("address1", billingAddress1Text.getValueAsString());
            billingAddress.setAttribute("address2", billingAddress2Text.getValueAsString());
            billingAddress.setAttribute("city", billingCityText.getValueAsString());
            billingAddress.setAttribute("zip", billingZipText.getValueAsString());
            billingAddress.setAttribute("state", billingState);
        }
        RecordList addresses = new RecordList();
        addresses.add(personalAddress);
        addresses.add(billingAddress);

        //loading data
        Record clientRecord = new Record();
        clientRecord.setAttribute("id", idClient.getAttribute("id"));
        clientRecord.setAttribute("firstName", firstNameText.getValueAsString());
        clientRecord.setAttribute("lastName", lastNameText.getValueAsString());
        clientRecord.setAttribute("company", companyText.getValueAsString());
        clientRecord.setAttribute("email", emailText.getValueAsString());
        clientRecord.setAttribute("active", Boolean.TRUE);
        clientRecord.setAttribute("clientTelephoneList", phones);
        clientRecord.setAttribute("clientAddressList", addresses);

        //This merchant object is just used in the server on new clients insertions
        if (idClient.getAttribute("id") == null) {
            Record merchant = new Record();
            merchant.setAttribute("id", merchantId);
            clientRecord.setAttribute("merchant", merchant);
        }
        return clientRecord;
    }
}
