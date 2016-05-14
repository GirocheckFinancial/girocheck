package com.smartbt.vtsuite.vtams.client.gui.window.list;

import com.smartbt.vtsuite.vtams.client.classes.enums.ParameterType;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter.ApplicationParameterDS;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.ApplicationType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;

/**
 * The Parameter Value ListGrid
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class TransactionGrid extends BaseListGrid {

    private TextListGridField applicationField = new TextListGridField( "application", "Application", false );
    private TextListGridField nameField = new TextListGridField( "name", I18N.GET.LIST_FIELD_PARAMETER_TITLE(), false );
    private TextListGridField valueField = new TextListGridField( "value", I18N.GET.LIST_FIELD_VALUE_TITLE(), false );
    private TextListGridField descriptionField = new TextListGridField( "description", I18N.GET.LIST_FIELD_DESCRIPTION_TITLE(), false );
    private TextListGridField readOnlyField = new TextListGridField( "readOnly", I18N.GET.LIST_FIELD_READ_ONLY_TITLE(), false );

    /**
     * Constructor
     *
     * @param parameterType
     */
    public TransactionGrid() {
        super();
        setEmptyMessage( I18N.GET.MESSAGE_EMPTY_PARAMETER_LIST() );

        Utils.debug( "ApplicationParameterListGrid 1" );
        readOnlyField.setCellFormatter( new CellFormatter() {
            public String format( Object value, ListGridRecord record, int rowNum, int colNum ) {
                Boolean result = record.getAttributeAsBoolean( "readOnly" );
                return result == null ? " - " : result.toString();
            }
        } );

        Utils.debug( "ApplicationParameterListGrid 2" );
        applicationField.setCellFormatter( new CellFormatter() {
            public String format( Object value, ListGridRecord record, int rowNum, int colNum ) {
                return record.getAttribute( "applicationLabel" );
            }
        } );
        Utils.debug( "ApplicationParameterListGrid 3" );

        nameField.setDataPath( "name" );
        valueField.setDataPath( "value" );
        descriptionField.setDataPath( "description" );

        addDataArrivedHandler( new DataArrivedHandler() {
            public void onDataArrived( DataArrivedEvent event ) {
                        groupBy( "applicationLabel" );
            }
        } );

        setDataSource( new ApplicationParameterDS() );
        setFields( applicationField, nameField, valueField, descriptionField, readOnlyField );

    }

}
