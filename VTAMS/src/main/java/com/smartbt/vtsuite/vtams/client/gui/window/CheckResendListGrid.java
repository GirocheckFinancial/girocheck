package com.smartbt.vtsuite.vtams.client.gui.window;

import com.google.gwt.i18n.client.NumberFormat;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.CurrencyListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CheckResendDS;
import com.smartbt.vtsuite.vtams.client.helpers.DateHelper;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SortNormalizer;

/**
 *
 * @author suresh
 */
public class CheckResendListGrid extends BaseListGrid {

    private TextListGridField checkID;
    private TextListGridField dateInserted;
    private TextListGridField dateProcessed;
    private TextListGridField status;
    private CurrencyListGridField amount = new CurrencyListGridField("amount", "Amount", false);

    /**
     * Constructor
     *
     * @param entityType
     */
    public CheckResendListGrid() {
        super();

        checkID = new TextListGridField("id", "Check ID", false);
        dateInserted = new TextListGridField("creationDate", "Date Inserted", false);
        dateProcessed = new TextListGridField("processingDate", "Date Processed", false);
        status = new TextListGridField("status", "Status", false);

        checkID.setSortNormalizer(new SortNormalizer() {
            @Override
            public Object normalize(ListGridRecord record, String fieldName) {
                return NumberFormat.getFormat("0000").format(record.getAttributeAsInt("id"));
            }
        });

        status.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                String checkStatus = record.getAttribute("status");
                if (checkStatus.equalsIgnoreCase("H")) {
                    return "Hold";
                }
                if (checkStatus.equalsIgnoreCase("C")) {
                    return "Completed";
                }
                if (checkStatus.equalsIgnoreCase("D")) {
                    return "Denied";
                }
                if (checkStatus.equalsIgnoreCase("P")) {
                    return "Processing";
                }
                return " - ";
            }
        });

        dateInserted.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                return DateHelper.toUSShortDate(record.getAttribute("creationDate"));
            }
        });

        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_TRANSACTION_LIST());
        setInitialSort(new SortSpecifier[]{
                    new SortSpecifier("creationDate", SortDirection.DESCENDING)
                });

        dateProcessed.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                return DateHelper.toUSShortDate(record.getAttribute("processingDate"));
            }
        });

        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_TRANSACTION_LIST());
        setInitialSort(new SortSpecifier[]{
                    new SortSpecifier("processingDate", SortDirection.DESCENDING)
                });

        setDataSource(new CheckResendDS());
        setFields(checkID,
                amount,
                dateInserted,
                dateProcessed,
                status);
    }
}
