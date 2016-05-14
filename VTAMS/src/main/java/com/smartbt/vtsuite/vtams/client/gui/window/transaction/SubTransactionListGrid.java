/*
 ** File: TransactionListGrid.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.transaction;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.SubTransactionsDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TransactionsDS;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.ClientTransactionType;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * The Transaction ListGrid
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class SubTransactionListGrid extends BaseListGrid {

    private TextListGridField typeField = new TextListGridField( "subTransactionType", "Type", false );
    private TextListGridField resultCodeField = new TextListGridField( "resultCode", "Result Code", false );
    private TextListGridField hostCodeField = new TextListGridField( "hostCode", "Host Code", false );

    /**
     * Constructor
     *
     * @param entityType
     */
    public SubTransactionListGrid() {
        super();
        setEmptyMessage( "No SubTransactions to show" );
        
        setWidth("98%");
        setMargin(10);
        setHeight( 250);
        
        typeField.setCellFormatter( new CellFormatter() {
            @Override
            public String format( Object value, ListGridRecord record, int rowNum, int colNum ) {
                Integer type = record.getAttributeAsInt( "subTransactionType" );

                if ( type == null ) {
                    return "Unknown";
                }
                return ClientTransactionType.getTransactionName( type );
            }
        } );

        setDataSource( new SubTransactionsDS() );

        setFields( typeField,
                resultCodeField,
                hostCodeField );
        
        
         Utils.debug( "Sub table width ::::" );
           resizeField( 0, 80 );
        Utils.debug( "Sub table width ::::  resizeField( 1, 80 );" );
//        resizeField( 2, 80 );
//        resizeField( 3, 80 );
    }
}
