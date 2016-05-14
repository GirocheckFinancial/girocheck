
package com.smartbt.vtsuite.vtams.client.gui.base;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.AddressImageFormDS;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FocusEvent;
import com.smartgwt.client.widgets.form.fields.events.FocusHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;

/**
 *
 * @author Alejo
 */


public class BaseFilterAddressImageForm extends DynamicForm{
    
    protected CanvasItem imageContent;
    protected BaseButtonItem filterButton;
    protected BaseTextItem searchText;
    
    private Img logo;
    private Record record;

    /**
     * Constructor
     *
     */
    public BaseFilterAddressImageForm() {
        super();

        setWidth100();
        setMargin(10);
        setNumCols(2);
        setTitleOrientation(TitleOrientation.TOP);
        
        imageContent = new CanvasItem();
        imageContent.setColSpan(3);
        imageContent.setTitle("Address Form Image");
        imageContent.setHeight(330);
        imageContent.setWidth(755);

        logo = new Img();
        logo.setAutoFit(true);
        logo.setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
        imageContent.setCanvas(logo);

        searchText = new BaseTextItem("searchFilter", "Terminal S/N", false);
        searchText.setKeyPressFilter(RegExp.VALID_SEARCH_II_REG_EXP);
        
        filterButton = new BaseButtonItem("filter", I18N.GET.BUTTON_SEARCH_TITLE());
        filterButton.setDisabled(true);

        searchText.addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {

                if ((event.getKeyName().equals("Enter"))) {
                    //FilterActionExecuted();
                    Filter();
                }
            }
        });
        
        searchText.addChangeHandler(new ChangeHandler() {

            @Override
            public void onChange(ChangeEvent event) {
                if (event.getValue() != null){
                    filterButton.setDisabled(false);
                }else
                    filterButton.setDisabled(true);

            }
        });

        filterButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Filter();
            }
        });
    }

    public BaseButtonItem getFilterButton() {
        return filterButton;
    }

    public void setFilterButton(BaseButtonItem filterButton) {
        this.filterButton = filterButton;
    }

    public BaseTextItem getSearchText() {
        return searchText;
    }

    public void setSearchText(BaseTextItem searchText) {
        this.searchText = searchText;
    }
    
    public void Filter() { 
            
        Criteria formCriteria = new Criteria();

        formCriteria.addCriteria("serialNumber", searchText.getValue().toString());

        AddressImageFormDS imageDS = new AddressImageFormDS();
        imageDS.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {

                record = response.getData()[0];
                if (record.getAttributeAsString("addressImage") == null) {
                    logo.setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
                } else {
                    logo.setSrc(record.getAttributeAsString("addressImage"));
                }
                logo.redraw();

            }
        }, null);
    }
    
}