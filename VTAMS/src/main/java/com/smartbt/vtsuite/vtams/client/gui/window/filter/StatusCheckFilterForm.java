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
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;
import java.util.Date;

/**
 * The Transaction Filter Form
 *
 * @author Ariamnet Lopez
 */
public class StatusCheckFilterForm extends BaseFilterForm {

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

    
    private BaseButtonItem resendButton;
    private TextItem statusText;
    
    /**
     * Constructor
     *
     */
    public StatusCheckFilterForm() {
        super();
        super.getFilterButton().setWidth( 70);
        super.getSearchText().setWidth( 100);
        
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
        
        statusText = new TextItem();
        statusText.setName( "status" );
        statusText.setTitle("Status" );
        statusText.setTextAlign( Alignment.LEFT );
        statusText.setWidth( 60 );
        
        resendButton = new BaseButtonItem("resendButton", "Resend");
        resendButton.setDisabled(true);      
        
        SpacerItem space = new SpacerItem();
        space.setWidth( 10);
        SpacerItem space1 = new SpacerItem();
        space1.setWidth( 10);
        
        setFields( new FormItem[]{startRangeDate, endRangeDate, statusText, filterButton, resendButton} );
    }    
    

    public Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.addCriteria( "startRangeDate", startRangeDate.getValueAsDate() );
        criteria.addCriteria( "endRangeDate", endRangeDate.getValueAsDate() );
        criteria.addCriteria( "status", statusText.getValueAsString() );
        criteria.addCriteria( "searchFilter", getSearchText().getValueAsString() );
        return criteria;
    }

    /**
     * @return the resendButton
     */
    public BaseButtonItem getResendButton() {
        return resendButton;
    }

    /**
     * @param resendButton the resendButton to set
     */
    public void setResendButton(BaseButtonItem resendButton) {
        this.resendButton = resendButton;
    }

}
