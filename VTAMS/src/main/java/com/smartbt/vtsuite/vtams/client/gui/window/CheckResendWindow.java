package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseWindow;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

/**
 *
 * @author suresh
 */
public class CheckResendWindow extends BaseWindow {

    protected HLayout hMainLayout = new HLayout();
    protected DynamicForm leftHeaderForm = new DynamicForm();
    protected CheckResendSection checkResendPanel;
    protected SectionStack rightSectionStack = new SectionStack();
    protected SectionStackSection rightSectionStackSection = new SectionStackSection(" Check Resend");

    public CheckResendWindow() {
        super();

        setHeight100();
        setWidth100();
        setShowHeader(false);
        setShowEdges(false);
        setStyleName("base-entity-window");
        setBodyStyle("base-entity-window");

        checkResendPanel = new CheckResendSection();
        checkResendPanel.setHeight100();

        rightSectionStackSection.addItem(checkResendPanel);

        rightSectionStack.addSection(rightSectionStackSection);
        rightSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);

        hMainLayout.setMargin(1);
        hMainLayout.addMember(rightSectionStack);

        addItem(hMainLayout);
        checkResendPanel.getFilterForm().addListener(new FilterListenerImp() {
            @Override
            public void FilterActionExecuted() {
                Filter(2);
            }
        });

        checkResendPanel.getPaginationForm().addListener(new PaginationListener() {
            @Override
            public void PreviousActionExecuted() {
                Filter(3);
            }

            @Override
            public void NextActionExecuted() {
                Filter(3);
            }
        });

        checkResendPanel.getListGrid().addListener(new ListListener() {
            /**
             * Method to execute when a Select event is fired.
             *
             */
            public void SelectActionExecuted(Record record) {

                SelectActionExcecuted(record);
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {

                SelectActionExcecuted(record);

            }

            /**
             * Method to execute when a Data Arrived event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                // Do Nothing
            }
        });

        checkResendPanel.filterForm.getResendButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                ResendButtonActionExecuted();
            }
        });
        Filter(1);

    }

    public CheckResendSection getCheckResendSection() {
        return checkResendPanel;
    }

    /*
     OPTION:
     1 - onLoad
     2 - onSearch
     3 - onPageChange
     */
    public void Filter(int option) {
        Criteria formCriteria = new Criteria();

        if (option != 1) {
            formCriteria = checkResendPanel.getFilterForm().getCriteria();
        }
        formCriteria.addCriteria(checkResendPanel.getPaginationForm().getCriteria());

        checkResendPanel.getFilterForm().setDisabled(true);
//      checkResendPanel.getListGrid().invalidateCache();
        checkResendPanel.getListGrid().setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        checkResendPanel.getListGrid().fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                checkResendPanel.getPaginationForm().updatePage(response.getAttributeAsInt("totalPages"));
                checkResendPanel.getFilterForm().setDisabled(false);
            }
        }, null);
    }

    public void SelectActionExcecuted(final Record record) {
        checkResendPanel.getFilterForm().getResendButton().setDisabled(record == null ? true : !record.getAttribute("status").equalsIgnoreCase("H"));
    }

    public void ResendButtonActionExecuted() {
        Record recordToDelete = checkResendPanel.getListGrid().getSelectedRecord();
        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria("id", recordToDelete.getAttributeAsInt("id"));

        ds.setFetchDataURL(Properties.RESEND_CHECK_WS);
        ds.fetchData(criteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Filter(1);

            }
        });
    }
}
