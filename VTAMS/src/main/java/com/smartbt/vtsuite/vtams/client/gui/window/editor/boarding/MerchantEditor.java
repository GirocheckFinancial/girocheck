/*
 *  File MerchantEditor
 * 
 *  Date Created: February 2014
 * 
 *  Copyright @ @ 2004-2014 Smart Business Technology, Inc.
 *
 *  All rights reserved. No part of this software may be 
 *  reproduced, transmitted, transcribed, stored in a retrieval 
 *  system, or translated into any language or computer language,
 *  in any form or by any means, electronic, mechanical, magnetic, 
 *  optical, chemical, manual or otherwise, without the prior 
 *  written permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.vtams.client.gui.window.editor.boarding;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseBoardingEditor;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseNumberItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextAreaItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CardProgramDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CountryDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.DataSourceBuilder;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.StateDS;
import com.smartbt.vtsuite.vtams.client.helpers.ValidatorHelper;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import java.util.Date;

/**
 *
 * @author Ariel Saavedra
 */
public class MerchantEditor extends BaseBoardingEditor {
    /*  Row 1 */

    private BaseTextItem legalNameText;
    private BaseTextItem agentNameText;
    /*  Row 2 */
    private BaseNumberItem phoneText;
    private BaseNumberItem accountText;
    /*  Row 3 */
    private BaseTextItem zipText;
    private BaseSelectItem countryCombo;
    private BaseSelectItem stateCombo;
    private BaseTextItem cityText;
    /*  Row 4*/
    private BaseTextItem addressText;

    /*  Row 5*/
    private BaseTextItem idTecnicardCheck;
    private BaseTextItem idTecnicardCash;
    private BaseTextItem iStreamUser;
    private BaseTextItem iStreamPassword;
    private BaseTextItem idTellerOrderExp;
    private BaseTextItem idTellerPagoOrderExp;
    private BaseTextItem idPosOrderExp;

    /*  Row 6*/
//    private BaseTextItem idIStream;
    private BaseTextItem idIstreamFuzeCash;
    private BaseTextItem idIstreamFuzeCheck;
    private BaseTextItem idIstreamTecnicardCash;
    private BaseTextItem idIstreamTecnicardCheck;
    private BaseTextItem sicText;
    private BaseNumberItem cardInventoryText;
    private BaseTextItem bankNameText;
    private BaseTextItem routingBankNumberText;
    private BaseSelectItem cardProgramCombo;

    /*  Row 7*/
    private BaseSelectItem commissionTypeCombo;
    private BaseSelectItem distributionChanelCombo;
    private BaseSelectItem riskCombo;
    private BaseSelectItem merchantTypeCombo;
    public DateItem activationdate;

    /*  Row 8*/
    private CheckboxItem independentOwnerCheckbox;
    private CheckboxItem moneyTransmissionCheckbox;
    private CheckboxItem billPaymentCheckbox;
    private CheckboxItem checkCashingCheckbox;
    /*  Row 9*/
    private CheckboxItem documentApprovedCheckbox;
    private CheckboxItem atmCheckbox;
    private CheckboxItem otherFinancialProviderCheckbox;
    private CheckboxItem trainingCheckbox;
    private CheckboxItem activeCheckbox;

    /*  Row 10*/
    private BaseTextAreaItem documentNotesTextArea;
    /*  Row 11*/
    private BaseTextAreaItem descriptionTextArea;
    private BaseTextItem authFeePText;
    private BaseTextItem oEAgentNumberText;

    public MerchantEditor(int id, int idParent) {
        super(id, idParent, EntityType.MERCHANT);

        acceptButton.setDisabled(true);
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_MERCHANT_ADD)) {
            acceptButton.setDisabled(false);

        }

        actionForm.setFields(spacerItem, reportButton, acceptButton, addChildButton);

        dataForm.setNumCols(4);
        dataForm.setColWidths("50%", "50%", "50%", "50%");
        /*  Row 1 */
        legalNameText = new BaseTextItem(1, "legalName", "Legal Name:", "Enter the legal name for the merchant.", true);
        legalNameText.setTextAlign(Alignment.LEFT);
        legalNameText.setColSpan(2);

        agentNameText = new BaseTextItem(2, "agentName", "Agent Name:", "Enter the agent name for the merchant.", true);
        agentNameText.setTextAlign(Alignment.LEFT);
        agentNameText.setColSpan(2);

        /*  Row 2 */
        phoneText = new BaseNumberItem(3, "phone", I18N.GET.LABEL_CONTACT_NUMBER_TITLE(), I18N.GET.TOOLTIP_CONTACT_NUMBER_MERCHANT_MSG(), true);
        phoneText.setTextAlign(Alignment.LEFT);
        phoneText.setColSpan(2);

        accountText = new BaseNumberItem(4, "account", "Account Number:", "Enter the account number fot the merchant.", true);
        accountText.setTextAlign(Alignment.LEFT);
        accountText.setColSpan(2);
        accountText.setEndRow(true);

        /*  Row 3 */
        zipText = new BaseTextItem(5, "zip", "Zip Code", "Enter the zipcode for the merchant.", true);
        zipText.setTextAlign(Alignment.LEFT);

        countryCombo = new BaseSelectItem(6, "country", "Country", "Enter the country for the merchant.", new CountryDS(), true);
        countryCombo.setDisplayField("abbreviation");
        // countryCombo.setAutoFetchData( true);
        countryCombo.setWidth(115);

        stateCombo = new BaseSelectItem(7, "state", "State", "Enter the state for the merchant.", new StateDS(), true);
        countryCombo.setDisplayField("abbreviation");
        stateCombo.setWidth(115);

        cityText = new BaseTextItem(8, "city", "City", "Enter the city for the merchant.", true);
        cityText.setTextAlign(Alignment.LEFT);
        cityText.setEndRow(true);

        /*  Row 4*/
        addressText = new BaseTextItem(9, "address", "Address", "Enter the address for the merchant.", true);
        // addressText.setTextAlign( Alignment.RIGHT );
        // addressText.setWidth( 90 ); //
        addressText.setColSpan(4);
        /*  Row 5*/
        idTecnicardCheck = new BaseTextItem(10, "idTecnicardCheck", "ID Tecnicard check", "Enter the Tecnicard ID for check operations.", true);
        idTecnicardCheck.setTextAlign(Alignment.LEFT);

        idTecnicardCash = new BaseTextItem(11, "idTecnicardCash", "ID Tecnicard cash", "Enter the Tecnicard ID for cash operations.", true);
        idTecnicardCash.setTextAlign(Alignment.LEFT);

        iStreamUser = new BaseTextItem(12, "iStreamUser", "IStream user", "Enter the user for iStream.", false);
        iStreamUser.setTextAlign(Alignment.LEFT);

        idTellerOrderExp = new BaseTextItem(13, "idTellerOrderExp", "Teller OrderExpress", "Enter the ID Teller for OrderExpress.", false);
        idTellerOrderExp.setTextAlign(Alignment.LEFT);

        idTellerPagoOrderExp = new BaseTextItem(14, "idTellerPagoOrderExp", "Teller Pago OE", "Enter the ID Teller Pago for OrderExpress.", false);
        idTellerPagoOrderExp.setTextAlign(Alignment.LEFT);

        idPosOrderExp = new BaseTextItem(15, "idPosOrderExp", "POS OrderExpress", "Enter the ID POS for OrderExpress.", false);
        idPosOrderExp.setTextAlign(Alignment.LEFT);

        iStreamPassword = new BaseTextItem(16, "iStreamPassword", "IStream Password", "Enter the password for iStream.", false);
        iStreamPassword.setTextAlign(Alignment.LEFT);
        iStreamPassword.setEndRow(true);

        /*  Row 6*/
//        idIStream = new BaseTextItem( 14, "idIStream", "ID IStream", "Enter the IStream ID.", false );
//        idIStream.setTextAlign( Alignment.LEFT );
        idIstreamFuzeCash = new BaseTextItem(17, "idIstreamFuzeCash", "Certegy's Location Id", "Enter the certegy's Location ID.", false);
        idIstreamFuzeCash.setTextAlign(Alignment.LEFT);
        idIstreamFuzeCheck = new BaseTextItem(18, "idIstreamFuzeCheck", "ID IStream Fuze Check", "Enter the Istream ID Fuze Check.", false);
        idIstreamFuzeCheck.setTextAlign(Alignment.LEFT);
        idIstreamTecnicardCash = new BaseTextItem(19, "idIstreamTecnicardCash", "ID IStream Tec Cash", "Enter the Istream ID Tecnicard Cash.", false);
        idIstreamTecnicardCash.setTextAlign(Alignment.LEFT);
        idIstreamTecnicardCheck = new BaseTextItem(20, "idIstreamTecnicardCheck", "ID IStream Tec Check", "Enter the Istream ID Tecnicard Check.", false);
        idIstreamTecnicardCheck.setTextAlign(Alignment.LEFT);

        sicText = new BaseTextItem(21, "sic", "Sic", "Enter the sic for the merchant.", false);
        sicText.setTextAlign(Alignment.LEFT);

        cardInventoryText = new BaseNumberItem(22, "cardInventory", "Card Inventory", "Card Inventory for this merchant.", false);
        cardInventoryText.setTextAlign(Alignment.LEFT);
        cardInventoryText.disable();

        bankNameText = new BaseTextItem(23, "bankName", "Bank Name", "Enter the Bank Name.", false);
        bankNameText.setTextAlign(Alignment.LEFT);

        routingBankNumberText = new BaseTextItem(24, "routingBankNumber", "Routing Bank Number", "Enter the Routing Bank Number.", false);
        routingBankNumberText.setTextAlign(Alignment.LEFT);

        oEAgentNumberText = new BaseTextItem(25, "oEAgentNumber", "OE Agent Number", "Enter the OE Agent Number.", false);
        oEAgentNumberText.setTextAlign(Alignment.LEFT);

        cardProgramCombo = new BaseSelectItem(26, "cardProgram", "Card Program", "Select card program for this merchant.", new CardProgramDS(), false);
        cardProgramCombo.setWidth(115);
        cardProgramCombo.setEndRow(true);

        /* Row 7*/
        commissionTypeCombo = new BaseSelectItem(27, "commissionType", "Commission Type", "Select the Commission Type.", DataSourceBuilder.getCommissionTypeDS(), false);
        commissionTypeCombo.setTextAlign(Alignment.LEFT);
        commissionTypeCombo.setWidth(115);

        distributionChanelCombo = new BaseSelectItem(28, "distributionChanel", "Distribution Chanel", "Select the distribution chanel for this merchant.", DataSourceBuilder.getDistributionChanelDS(), false);
        distributionChanelCombo.setTextAlign(Alignment.LEFT);
        distributionChanelCombo.setWidth(115);

        riskCombo = new BaseSelectItem(29, "risk", "Risk", "Select the risk for this merchant.", DataSourceBuilder.getRiskDS(), false);
        riskCombo.setTextAlign(Alignment.LEFT);
        riskCombo.setWidth(115);

        merchantTypeCombo = new BaseSelectItem(30, "merchantType", "Merchant Type", "Select the merchant type.", DataSourceBuilder.getMerchantTypeDS(), false);
        merchantTypeCombo.setWidth(115);

        activationdate = new DateItem("activationdate", I18N.GET.LIST_FIELD_ACTIVATION_DATE_TITLE());
        activationdate.setWidth(90);
        activationdate.setDateFormatter(DateDisplayFormat.TOUSSHORTDATETIME);
        activationdate.setUseMask(true);
        activationdate.setUseTextField(true);
        activationdate.setValidateOnChange(true);
        final DateRangeValidator maxValidator = new DateRangeValidator();
        Date maxDate = new Date();
        maxDate.setHours(24);
        maxValidator.setMax(maxDate);

        activationdate.setEndRow(true);

        authFeePText = new BaseTextItem(31, "authFeeP", "Auth FeeP", "Enter the Auth FeeP.", false);
        authFeePText.setTextAlign(Alignment.LEFT);

        /* Row 8*/
        independentOwnerCheckbox = new CheckboxItem("independentOwner", "Independent Owner");

        independentOwnerCheckbox.setTextAlign(Alignment.LEFT);

        moneyTransmissionCheckbox = new CheckboxItem("moneyTransmission", "Money Transmission");

        moneyTransmissionCheckbox.setTextAlign(Alignment.LEFT);

        billPaymentCheckbox = new CheckboxItem("billPayment", "Bill Payment");

        billPaymentCheckbox.setTextAlign(Alignment.LEFT);

        checkCashingCheckbox = new CheckboxItem("checkCashing", "Check Cashing");

        checkCashingCheckbox.setTextAlign(Alignment.LEFT);

        checkCashingCheckbox.setEndRow(true);

        /* Row 9*/
        documentApprovedCheckbox = new CheckboxItem("documentApproved", "Document Approved");

        documentApprovedCheckbox.setTextAlign(Alignment.LEFT);

        atmCheckbox = new CheckboxItem("atm", "ATM");

        atmCheckbox.setTextAlign(Alignment.LEFT);

        trainingCheckbox = new CheckboxItem("training", "Training");

        trainingCheckbox.setTextAlign(Alignment.LEFT);

        otherFinancialProviderCheckbox = new CheckboxItem("otherFinancialProvider", "Financial Provider");

        otherFinancialProviderCheckbox.setTextAlign(Alignment.LEFT);

        activeCheckbox = new CheckboxItem("active", "Active");
        activeCheckbox.setTextAlign(Alignment.LEFT);

        activeCheckbox.setEndRow(true);

        /* Row 10*/
        documentNotesTextArea = new BaseTextAreaItem(32, "documentNotes", "Document Notes", "Enter the document notes.", false);

        documentNotesTextArea.setHeight(50);
        documentNotesTextArea.setColSpan(4);

        /*Row 11*/
        descriptionTextArea = new BaseTextAreaItem(33, "description", "Description", "Enter the description of the merchant.", false);

        descriptionTextArea.setHeight(50);
        descriptionTextArea.setColSpan(4);

        //----------- validators lenght---------------------------------------------------------------------------------------------------------
        legalNameText.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
        agentNameText.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
        oEAgentNumberText.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
        phoneText.setLength(Constants.PHONE_MAX_LENGTH);
        accountText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        zipText.setLength(10);
        cityText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        addressText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);

        idTecnicardCheck.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        idTecnicardCash.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        iStreamUser.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        iStreamPassword.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        idTellerOrderExp.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        idTellerPagoOrderExp.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        idPosOrderExp.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
//        idIStream.setLength( Constants.MEDIUM_TEXT_MAX_LENGTH );
        idIstreamFuzeCash.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        idIstreamFuzeCheck.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        idIstreamTecnicardCash.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        idIstreamTecnicardCheck.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        sicText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);

        //----------- validator reg exp ---------------------------------------------------------------------------------------------------------
        legalNameText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);
        bankNameText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);
        routingBankNumberText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);
        oEAgentNumberText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);
        agentNameText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);
        phoneText.setValidators(ValidatorHelper.getPhoneValidator());
        accountText.setValidators(ValidatorHelper.getTextIValidator());
        zipText.setValidators(getZipValidator());
        cityText.setValidators(ValidatorHelper.getTextVValidator());
        addressText.setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);

        idTecnicardCheck.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        idTecnicardCash.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        idTellerOrderExp.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        idTellerPagoOrderExp.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        idPosOrderExp.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        iStreamUser.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        iStreamPassword.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
//        idIStream.setKeyPressFilter( RegExp.VALID_TEXT_REG_EXP );
        idIstreamFuzeCash.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        idIstreamFuzeCheck.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        idIstreamTecnicardCash.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        idIstreamTecnicardCheck.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        sicText.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        authFeePText.setKeyPressFilter(RegExp.EXP_REG_KEY_FLOAT);

        //--------------------------------------------------------------------------------------------------------------------
        dataFormSetFields(idEntity,
                legalNameText, agentNameText,
                phoneText, accountText,
                new RowSpacerItem(),
                zipText, countryCombo, stateCombo, cityText,
                addressText,
                new RowSpacerItem(),
                idTecnicardCheck, idTecnicardCash, iStreamUser, iStreamPassword, idIstreamFuzeCash, idIstreamFuzeCheck, idIstreamTecnicardCash, idIstreamTecnicardCheck,
                idTellerOrderExp, idTellerPagoOrderExp, idPosOrderExp, sicText, cardInventoryText, bankNameText,
                routingBankNumberText, oEAgentNumberText, cardProgramCombo, authFeePText,
                commissionTypeCombo, distributionChanelCombo, riskCombo, merchantTypeCombo, activationdate,
                independentOwnerCheckbox, moneyTransmissionCheckbox, billPaymentCheckbox, checkCashingCheckbox,
                documentApprovedCheckbox, atmCheckbox, trainingCheckbox, otherFinancialProviderCheckbox, activeCheckbox,
                new RowSpacerItem(),
                documentNotesTextArea,
                new RowSpacerItem(),
                descriptionTextArea
        );

    }

    @Override
    public Record getRecord() {
        Utils.debug("MerchantEditor -> getRecord() :: id = " + idEntity.getAttribute("id"));
        Utils.debug("idParent = " + idParent);

        Record record = new Record();
        boolean validate = dataForm.validate();
        record.setAttribute("validate", validate);
        Utils.debug("MerchantEditor -> getRecord() :: validate = " + validate);

        record.setAttribute("idAgrupation", idParent);
        record.setAttribute("id", idEntity.getAttribute("id"));

        record.setAttribute("legalName", legalNameText.getValueAsString());
        record.setAttribute("agentName", agentNameText.getValueAsString());
        record.setAttribute("phone", phoneText.getValueAsString());
        record.setAttribute("account", accountText.getValueAsString());

        //Loading Address
        Record address = new Record();
        address.setAttribute("id", addressText.getAttribute("id"));
        address.setAttribute("city", cityText.getValueAsString());
        address.setAttribute("address", addressText.getValueAsString());
        address.setAttribute("state", stateCombo.getValueAsString());
        address.setAttribute("zip", zipText.getValueAsString());

        Record state = new Record();
        state.setAttribute("id", stateCombo.getValue());
        address.setAttribute("state", state);

        Record country = new Record();
        country.setAttribute("id", countryCombo.getValue());
        address.setAttribute("country", country);

        record.setAttribute("address", address);

        record.setAttribute("idTecnicardCheck", idTecnicardCheck.getValueAsString());
        record.setAttribute("idTecnicardCash", idTecnicardCash.getValueAsString());
        record.setAttribute("idTellerOrderExp", idTellerOrderExp.getValueAsString());
        record.setAttribute("idTellerPagoOrderExp", idTellerPagoOrderExp.getValueAsString());
        record.setAttribute("idPosOrderExp", idPosOrderExp.getValueAsString());
        record.setAttribute("iStreamUser", iStreamUser.getValueAsString());
        record.setAttribute("iStreamPassword", iStreamPassword.getValueAsString());

//        record.setAttribute( "idIStream", idIStream.getValueAsString() );
        record.setAttribute("idIstreamFuzeCash", idIstreamFuzeCash.getValueAsString());
        record.setAttribute("idIstreamFuzeCheck", idIstreamFuzeCheck.getValueAsString());
        record.setAttribute("idIstreamTecnicardCash", idIstreamTecnicardCash.getValueAsString());
        record.setAttribute("idIstreamTecnicardCheck", idIstreamTecnicardCheck.getValueAsString());
        record.setAttribute("sic", sicText.getValueAsString());
        record.setAttribute("cardInventory", cardInventoryText.getValueAsString());
        record.setAttribute("bankName", bankNameText.getValueAsString());
        record.setAttribute("routingBankNumber", routingBankNumberText.getValueAsString());
        record.setAttribute("oEAgentNumber", oEAgentNumberText.getValueAsString());

        Record cardProgram = new Record();
        cardProgram.setAttribute("id", cardProgramCombo.getValue());
        record.setAttribute("cardProgram", cardProgram);

        Integer comType = commissionTypeCombo.getSelectedRecord() != null ? commissionTypeCombo.getSelectedRecord().getAttributeAsInt("id") : (Integer) commissionTypeCombo.getValue();
        Utils.debug("MerchantEditor -> getRecord() :: comType = " + comType);
        if (comType != null) {
            String commisionType = comType == 1 ? "D" : "I";
            Utils.debug("MerchantEditor -> getRecord() :: commisionType = " + commisionType);
            record.setAttribute("commissionType", commisionType);
        }

        record.setAttribute("distributionChanel", distributionChanelCombo.getSelectedRecord() != null ? distributionChanelCombo.getSelectedRecord().getAttributeAsInt("id") : distributionChanelCombo.getValue());
        record.setAttribute("risk", riskCombo.getSelectedRecord() != null ? riskCombo.getSelectedRecord().getAttributeAsInt("id") : riskCombo.getValue());
        record.setAttribute("merchantType", merchantTypeCombo.getSelectedRecord() != null ? merchantTypeCombo.getSelectedRecord().getAttributeAsInt("id") : merchantTypeCombo.getValue());

        record.setAttribute("independentOwner", independentOwnerCheckbox.getValueAsBoolean());
        record.setAttribute("moneyTransmission", moneyTransmissionCheckbox.getValueAsBoolean());
        record.setAttribute("billPayment", billPaymentCheckbox.getValueAsBoolean());
        record.setAttribute("checkCashing", checkCashingCheckbox.getValueAsBoolean());
        record.setAttribute("documentApproved", documentApprovedCheckbox.getValueAsBoolean());
        record.setAttribute("atm", atmCheckbox.getValueAsBoolean());
        record.setAttribute("training", trainingCheckbox.getValueAsBoolean());
        record.setAttribute("otherFinancialProvider", otherFinancialProviderCheckbox.getValueAsBoolean());
        record.setAttribute("authFeeP", authFeePText.getValueAsString());
        record.setAttribute("active", activeCheckbox.getValueAsBoolean());

        record.setAttribute("documentNotes", documentNotesTextArea.getValueAsString());
        record.setAttribute("description", descriptionTextArea.getValueAsString());

        return record;
    }

    @Override
    public void loadRecord(Record record) {
        Utils.debug("loadRecord...");
        dataForm.editRecord(record);
        idEntity.setAttribute("id", record.getAttribute("id"));

        Record addressReccord = record.getAttributeAsRecord("address");
        Utils.debug("addressReccord = " + (addressReccord == null ? "NULL" : "NOT NULL"));
        if (addressReccord != null) {
            addressText.setAttribute("id", addressReccord.getAttribute("id"));
            addressText.setValue(addressReccord.getAttribute("address"));
            cityText.setValue(addressReccord.getAttribute("city"));
            countryCombo.setValue(addressReccord.getAttributeAsRecord("country").getAttributeAsInt("id"));
            zipText.setValue(addressReccord.getAttribute("zip"));
            stateCombo.setValue(addressReccord.getAttributeAsRecord("state").getAttribute("id"));
        }

        Record cardProgram = record.getAttributeAsRecord("cardProgram");
        if (cardProgram != null) {
            cardProgramCombo.setValue(cardProgram.getAttributeAsInt("id"));
        }

        if (record.getAttributeAsInt("cardInventory") == null) {
            cardInventoryText.setValue(0);
        }
        merchantTypeCombo.setValue(record.getAttributeAsInt("merchantType"));

        String comType = record.getAttributeAsString("commissionType");
        Utils.debug("MerchantEditor -> loadRecord() :: comType = " + comType);
        if (comType != null) {
            Integer commissionType = comType.equalsIgnoreCase("D") ? 1 : 2;
            Utils.debug("MerchantEditor -> loadRecord() :: commissionType = " + commissionType);
            commissionTypeCombo.setValue(commissionType);
        }

        distributionChanelCombo.setValue(record.getAttributeAsInt("distributionChanel"));
        riskCombo.setValue(record.getAttributeAsInt("risk"));
        dataForm.rememberValues();
        Utils.debug("termino el loadRecord...");
    }

    @Override
    public EntityType getEntityTypeEditor() {
        return EntityType.MERCHANT;
    }

    public void createAddChildButton() {
        addChildButton = new BaseButtonItem("addChildButton", "Add Terminal");
        addChildButton.setAlign(Alignment.RIGHT);
        addChildButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                addTerminalActionExecuted(0, getId());
            }
        });
        addChildButton.setWidth(80);
        addChildButton.setHeight(30);
        addChildButton.setDisabled(super.getId() == 0);
        addChildButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_MERCHANT_ADD_TERMINAL));
    }

    public static RegExpValidator getZipValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression("^\\d{5}(-\\d{4})?$");
        validator.setErrorMessage("Zip code entry is incorrect");
        return validator;
    }

}
