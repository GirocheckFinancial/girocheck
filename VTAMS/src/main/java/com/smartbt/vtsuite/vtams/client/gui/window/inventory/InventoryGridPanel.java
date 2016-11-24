package com.smartbt.vtsuite.vtams.client.gui.window.inventory;

import com.smartbt.vtsuite.vtams.client.gui.component.PaginationForm;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class InventoryGridPanel extends VLayout {

    protected HLayout filterLayout;
    //protected VLayout listLayout;

    protected PaginationForm paginationForm;
    protected InventoryListGrid listGrid;
    protected InventoryEditor inventoryEditor;

    public InventoryGridPanel() {
        Utils.debug("** InventoryGridPanel() 1");
        paginationForm = new PaginationForm();
        Utils.debug("** InventoryGridPanel() 2");
        paginationForm.setMargin(10);

        filterLayout = new HLayout();
        filterLayout.setAutoHeight();
        //  listLayout = new VLayout();

        listGrid = new InventoryListGrid();
        filterLayout.addMember(paginationForm);
        //   listLayout.addMember(listGrid);
        Utils.debug("** InventoryGridPanel() 3");
        inventoryEditor = new InventoryEditor();
        inventoryEditor.hide();
        Utils.debug("** InventoryGridPanel() 4");
        addMember(listGrid);
        addMember(filterLayout);
        addMember(inventoryEditor);
        Utils.debug("** InventoryGridPanel() 5");
        listGrid.addListener(new ListListener() {
            public void SelectActionExecuted(Record record) {
                Utils.debug("SelectActionExecuted");
//                SelectActionExcecuted(record);
            }

            public void SelectionChangeActionExecuted(Record record) {
Utils.debug("SelectionChangeActionExecuted");
                 SelectActionExcecuted(record);
            }

            public void DataArrivedHandlerExecuted() {
                // Do Nothing
            }
        });
        Utils.debug("** InventoryGridPanel() 6");
        inventoryEditor.setEditorListener(new EditorListener() {

            @Override
            public void SaveActionExecuted() {
                Utils.debug("SaveActionExecuted");
                InventoryDS inventoryDS = new InventoryDS();
                Record record = inventoryEditor.getRecord();
         
                Utils.debug("inventoryEditor.getDataForm().getValuesAsRecord()");
                inventoryDS.addData(record, new DSCallback() {

                    public void execute(DSResponse response, Object rawData, DSRequest request) {
                        if (response.getStatus() == Constants.CODE_SUCCESS) {
                            Utils.debug("SaveActionExecuted -> response = Success");
                            Filter();
                            Utils.debug("SaveActionExecuted -> after filter");
                        }
                    }
                });
            }

            @Override
            public void CloseActionExecuted() {
                inventoryEditor.hide();
            }
        });
        Utils.debug("** InventoryGridPanel() 7");
//        Utils.debug( "TransactionSection() 4");

        paginationForm.addListener(new PaginationListener() {
            @Override
            public void PreviousActionExecuted() {
                Filter();
            }

            @Override
            public void NextActionExecuted() {
                Filter();
            }
        });
    }

    public PaginationForm getPaginationForm() {
        return paginationForm;
    }

    public InventoryListGrid getListGrid() {
        return listGrid;
    }

    public void SelectActionExcecuted(final Record record) {
        inventoryEditor.show();

        int id = record.getAttributeAsInt("id");
        String merchant = record.getAttribute("merchant");
        String inventory = record.getAttribute("inventory");
        String threshold = record.getAttribute("threshold");

        Utils.debug("id = " + id);
        Utils.debug("merchant = " + merchant);
        Utils.debug("inventory = " + inventory);
        Utils.debug("threshold = " + threshold);

        inventoryEditor.setTitle(merchant);
        inventoryEditor.updateRecord(record);

    }

    public void Filter() {
        Criteria formCriteria = new Criteria();

        formCriteria.addCriteria(paginationForm.getCriteria());

        listGrid.invalidateCache();
        listGrid.setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        listGrid.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                paginationForm.updatePage(response.getAttributeAsInt("totalPages"));
            }
        }, null);
    }

}
