/*
 ** File: TransactionFilterForm.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.filter;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseCheckBoxItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.DataSourceBuilder;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListener;
import com.smartbt.vtsuite.vtams.client.helpers.DateHelper;
import com.smartbt.vtsuite.vtams.client.helpers.ValidatorHelper;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;
import java.util.Date;

/**
 * The Transaction Filter Form
 *
 * @author Ariamnet Lopez
 */
public class DateTextFilterForm extends BaseFilterForm {

    /**
     * The Start Date field
     *
     */
    public DateItem startRangeDate;
    /**
     * The End Date field
     *
     */
    public DateItem endRangeDate;

    private BaseSelectItem transactionTypeSelect;
    private BaseSelectItem operationSelect;

    private BaseSelectItem ammountTypeSelect;
    private BaseSelectItem opTypeSelect;
    private TextItem ammountText;
    private BaseCheckBoxItem pending;
    private BaseCheckBoxItem reportType;
    
    /**
     * Constructor
     *
     */
    public DateTextFilterForm() {
        super();
        super.getFilterButton().setWidth( 70);
        super.getImportButton().setWidth( 70);
        super.getSearchText().setWidth( 100);
        
        transactionTypeSelect = new BaseSelectItem( "transactionType", "Transaction Type", DataSourceBuilder.getTransactionTypeDS(), false );
        transactionTypeSelect.setTextAlign( Alignment.LEFT );
        transactionTypeSelect.setWidth( 115 );
        transactionTypeSelect.setValue( 0 );

        operationSelect = new BaseSelectItem( "operation", "Operation", DataSourceBuilder.getOperationDS(), false );
        operationSelect.setTextAlign( Alignment.LEFT );
        operationSelect.setWidth( 80 );
        operationSelect.setValue( "" );

        ammountTypeSelect = new BaseSelectItem( "ammountType", "Amount Type", DataSourceBuilder.getAmmountTypeDS(), false );
        ammountTypeSelect.setTextAlign( Alignment.LEFT );
        ammountTypeSelect.setWidth( 100 );
        ammountTypeSelect.setValue( "" );
        
        pending = new BaseCheckBoxItem("pending", "Pending", false );
        pending.setTextAlign( Alignment.LEFT );
        
        reportType = new BaseCheckBoxItem("reportType", "Details Listing Report", false );
        reportType.setTextAlign( Alignment.LEFT );

        opTypeSelect = new BaseSelectItem( "opType", "", DataSourceBuilder.getOpTypeDS(), false );
        opTypeSelect.setTextAlign( Alignment.LEFT );
        opTypeSelect.setWidth( 60 );
        opTypeSelect.setValue( "" );

        ammountText = new TextItem();
        ammountText.setName( "ammount" );
        ammountText.setTitle("Amount" );
        ammountText.setTextAlign( Alignment.LEFT );
      //  ammountText.setValidators( ValidatorHelper.getFloatRangeValidator( 1, 9, 2 ) );
        ammountText.setKeyPressFilter( RegExp.EXP_REG_KEY_FLOAT );
        ammountText.setWidth( 60 );

        startRangeDate = new DateItem( "startRangeDate", I18N.GET.LABEL_START_RANGE_TITLE() );
        startRangeDate.setWidth(90 );
        endRangeDate = new DateItem( "endRangeDate", I18N.GET.LABEL_END_RANGE_TITLE() );
        endRangeDate.setWidth(90 );

        startRangeDate.setDateFormatter( DateDisplayFormat.TOUSSHORTDATETIME );
        endRangeDate.setDateFormatter( DateDisplayFormat.TOUSSHORTDATE );

        startRangeDate.setUseMask( true );
        endRangeDate.setUseMask( true );

        startRangeDate.setUseTextField( true );
        endRangeDate.setUseTextField( true );

        startRangeDate.setValidateOnChange( true );
        endRangeDate.setValidateOnChange( true );

        final DateRangeValidator maxValidator = new DateRangeValidator();
        Date maxDate = new Date();
        maxDate.setHours( 24 );
        maxValidator.setMax( maxDate );

        startRangeDate.setValidators( maxValidator );
        endRangeDate.setValidators( maxValidator );

        startRangeDate.setEndDate( new Date() );
        endRangeDate.setEndDate( new Date() );

        startRangeDate.addChangedHandler( new ChangedHandler() {
            public void onChanged( ChangedEvent event ) {
                if ( startRangeDate.validate() ) {
                    DateRangeValidator minValidator = new DateRangeValidator();
                    minValidator.setMin( startRangeDate.getValueAsDate() );
                    endRangeDate.setValidators( maxValidator, minValidator );
                    endRangeDate.validate();
                }
            }
        } );
        
        KeyPressHandler onEnter = new KeyPressHandler() {
            @Override
            public void onKeyPress( KeyPressEvent event ) {
                if ( ( event.getKeyName().equals( "Enter" ) ) ) {
                    event.getItem().blurItem();//ISSUE: needed to fire blur in order to take the values successfully
                    FilterActionExecuted();
                    event.getItem().focusInItem();
                }
            }
        };
        startRangeDate.addKeyPressHandler( onEnter );
        endRangeDate.addKeyPressHandler( onEnter );

        SpacerItem space = new SpacerItem();
        space.setWidth( 10);
        SpacerItem space1 = new SpacerItem();
        space1.setWidth( 10);
        importButton.setTitle("Report");

        setFields( new FormItem[]{startRangeDate, endRangeDate, transactionTypeSelect, operationSelect, space, ammountTypeSelect, opTypeSelect, ammountText, space, searchText, filterButton, pending, importButton, reportType} );
    }

    public Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.addCriteria( "startRangeDate", startRangeDate.getValueAsDate() );
        criteria.addCriteria( "endRangeDate", endRangeDate.getValueAsDate() );
        criteria.addCriteria( "transactionType", (Integer) transactionTypeSelect.getValue() );
        criteria.addCriteria( "operation", operationSelect.getValueAsString() );
        criteria.addCriteria( "searchFilter", getSearchText().getValueAsString() );
        
        if(pending != null && pending.getValue() != null){
            if( pending.getValue().equals(true)){
                    criteria.addCriteria( "pending", true);  
            }else{
                criteria.addCriteria( "pending", false);
            }
        }
        if(reportType != null && reportType.getValue() != null){
            if( reportType.getValue().equals(true)){
                    criteria.addCriteria( "reportType", true);  
            }else{
                criteria.addCriteria( "reportType", false);
            }
        }

        try {
            if ( ammountTypeSelect.getValue() != null &&  ammountText.getValue()!= null  && opTypeSelect.getValue() != null) {
                Integer ammountType =  ammountTypeSelect.getSelectedRecord() != null ? ammountTypeSelect.getSelectedRecord().getAttributeAsInt( "id" ) :  null;
                Integer opType = opTypeSelect.getSelectedRecord() != null ? opTypeSelect.getSelectedRecord().getAttributeAsInt( "id" ) : null;
             String ammountString = (String)ammountText.getValue();
                boolean ammountTextBool = ammountString.length()!= 0 ;
                
                if( ammountType != null &&  ammountType != 0 && opType != null && opType != 0 && ammountTextBool && !ammountString.equals( "0")){
                  
                Double ammount = Double.parseDouble( (String) ammountText.getValue() );

                criteria.addCriteria( "filterAmmount", true );
                criteria.addCriteria( "ammountType", (Integer) ammountTypeSelect.getValue() );
                criteria.addCriteria( "opType", (Integer) opTypeSelect.getValue() );
                criteria.addCriteria( "ammount", (String) ammountText.getValue() );
                }
            } else {
                criteria.addCriteria( "ammount", (String) ammountText.getValue() );
                criteria.addCriteria( "filterAmmount", false );
            }
        } catch ( NumberFormatException mfe ) {
//            Utils.debug( "Exception" );
            criteria.addCriteria( "filterAmmount", false );
        }

        return criteria;
    }

}
