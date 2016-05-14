package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseImageContent;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.AddressImageFormDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.AddressImageFilterForm;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author Alejo
 */


public class ImageAddressViewerWindow extends BaseWindow{
    
    protected VLayout mainLayout = new VLayout();
    protected VLayout imageContentLayout = new VLayout();
    protected BaseImageContent imageContent = new BaseImageContent("Address Form Image", 330, 755);
    protected AddressImageFilterForm addressImageFilterForm = new AddressImageFilterForm();
    
    private Record record;
    
    public ImageAddressViewerWindow(){
        super();
        setHeight100();
        setWidth100();
        setShowHeader( false );
        setShowEdges( false );
        setTitle("");

        mainLayout.setHeight100();
        mainLayout.setWidth100();
        mainLayout.addMember(addressImageFilterForm);
        mainLayout.addMember(imageContent);
        addItem( mainLayout );
        
        addressImageFilterForm.addListener( new FilterListenerImp() {
            @Override
            public void FilterActionExecuted() {
                Filter();
            }
        } );
        
        addressImageFilterForm.addListener( new FilterListenerImp() {
            @Override
            public void RotateActionExecuted() {
                Rotate();
            }
        } );
    }
    
    public void Filter() { 
            
        Criteria formCriteria = new Criteria();

        formCriteria.addCriteria("serialNumber", addressImageFilterForm.getSearchText().getValue().toString());
        formCriteria.addCriteria("rotate", false);

        AddressImageFormDS imageDS = new AddressImageFormDS();
        imageDS.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {

                record = response.getData()[0];
                if (record.getAttributeAsString("addressImage") == null) {
                    imageContent.getLogo().setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
                } else {
                    Utils.debug( "********Exist Image********" );
                    imageContent.getLogo().setSrc(record.getAttributeAsString("addressImage"));
                }
                imageContent.redraw();
                addressImageFilterForm.getRotateButton().setDisabled(false);
            }
        }, null);
    }
    
    public void Rotate(){
        Criteria formCriteria = new Criteria();

        formCriteria.addCriteria("serialNumber", addressImageFilterForm.getSearchText().getValue().toString());
        formCriteria.addCriteria("rotate", true);

        AddressImageFormDS imageDS = new AddressImageFormDS();
        imageDS.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {

                record = response.getData()[0];
               if (record.getAttributeAsString("addressImage") == null) {
                    imageContent.getLogo().setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
                } else {
                    imageContent.getLogo().setSrc(record.getAttributeAsString("addressImage"));
                }
                imageContent.redraw();
                addressImageFilterForm.getRotateButton().setDisabled(true);
            }
        }, null);
    }
   
}
