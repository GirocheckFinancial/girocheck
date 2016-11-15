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

import com.google.gwt.i18n.client.NumberFormat;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.CurrencyListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TimeListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TransactionsDS;
import com.smartbt.vtsuite.vtams.client.helpers.DateHelper;
import com.smartbt.vtsuite.vtcommon.enums.ClientTransactionType;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SortNormalizer;

/**
 * The Transaction ListGrid
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class TransactionListGrid extends BaseListGrid {

    private TextListGridField typeField = new TextListGridField("transactionType", "Transaction Type", false);
    private TextListGridField datexField = new TextListGridField("createdAt", I18N.GET.LIST_FIELD_DATE_TITLE(), false);
    private TimeListGridField timeField = new TimeListGridField("time", I18N.GET.LIST_FIELD_TIME_TITLE(), false);
    
    private TextListGridField operationField = new TextListGridField("operation", "Operation", false);
    private TextListGridField accountSuffixField = new TextListGridField("accountSuffix", "Masked Account", false);
    private TextListGridField merchantNameField = new TextListGridField("merchant", "Merchant", false);
    private TextListGridField terminalSerialNumberField = new TextListGridField("terminal", "Terminal", false);
    private TextListGridField cusntomerNameField = new TextListGridField("clientFirstName", "Customer name", false);
    
    private CurrencyListGridField ammountField = new CurrencyListGridField("ammount", "Amount", false);
    private CurrencyListGridField feeAmmountField = new CurrencyListGridField("feeAmmount", "Fee Amount", false);
    private CurrencyListGridField payoutAmmountField = new CurrencyListGridField("payoutAmmount", "Payout Amount", false);
    
    private TextListGridField resultCode = new TextListGridField("resultCode", "Result Code", false);
    private TextListGridField transactionFinished = new TextListGridField("transactionFinished", "Transaction Status", false);
  

    /**
     * Constructor
     *
     * @param entityType
     */
    public TransactionListGrid() {
        super();

//        clerkField.setDataPath("clerk/username");
//        merchantField.setDataPath("merchant/name");
//        terminalField.setDataPath("terminal/terminalId");
//        modeField.setDataPath("mode/name");
//        cardBrandField.setDataPath("cardBrand/name");

        typeField.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                Integer type = record.getAttributeAsInt("transactionType");
                
                if(type == null)return "Unknown..";
                return ClientTransactionType.getTransactionName( type);
            }
        });

        operationField.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                String operation = record.getAttribute("operation");
                if(operation.contains( "01")) return "Check";
                if(operation.contains( "02")) return "Cash";
                return " - ";
            }
        });
        
        transactionFinished.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                String finished = record.getAttribute("transactionFinished");
                if(finished.contains("true")) return "Finished";
                if(finished.contains( "false")) return "Pending";
                return " - ";
            }
        });

        accountSuffixField.setSortNormalizer(new SortNormalizer() {
            @Override
            public Object normalize(ListGridRecord record, String fieldName) {
                return NumberFormat.getFormat("0000").format(record.getAttributeAsInt("accountSuffix"));
            }
        });

        accountSuffixField.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                String formatted = NumberFormat.getFormat("0000").format(record.getAttributeAsInt("accountSuffix"));
               return "***********" + formatted;
            }
        });

//        dateField.setSortNormalizer(new SortNormalizer() {
//            @Override
//            public Object normalize(ListGridRecord record, String fieldName) {
//                return record.getAttributeAsDate("createdAt");
//            }
//        });

        datexField.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                return DateHelper.toUSShortDate(record.getAttribute("createdAt"));
            }
        });

        timeField.setSortNormalizer(new SortNormalizer() {
            @Override
            public Object normalize(ListGridRecord record, String fieldName) {
                return DateHelper.toUSShortTime(record.getAttributeAsDate("createdAt"));
            }
        });

        timeField.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                return DateHelper.toUSShortTime(record.getAttribute("createdAt"));
            }
        });

        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_TRANSACTION_LIST());
        setInitialSort(new SortSpecifier[]{
            new SortSpecifier("createdAt", SortDirection.DESCENDING)
        });

        setDataSource(new TransactionsDS());
        
        
        setFields(typeField,
                datexField,
                timeField,
                merchantNameField,
                terminalSerialNumberField,
                cusntomerNameField,
                operationField,
                accountSuffixField,
                ammountField,
                feeAmmountField,
                payoutAmmountField,
                resultCode,
                transactionFinished);
   //     resizeField(2, 110);
    }
}
