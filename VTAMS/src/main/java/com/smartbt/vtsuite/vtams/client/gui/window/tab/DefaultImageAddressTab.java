
package com.smartbt.vtsuite.vtams.client.gui.window.tab;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterAddressImageForm;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.EntityAddressImageSetField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;


/**
 *
 * @author Alejo
 */


public class DefaultImageAddressTab extends Tab{
    
    protected VLayout mainVLayout;
    protected HLayout filterLayout;
  //  protected VLayout imageLayout;
    
    private BaseFilterAddressImageForm filterForm;

    public DefaultImageAddressTab(String title) {
        super(title);
        
        mainVLayout = new VLayout();

        filterLayout = new HLayout();
        filterLayout.setAutoHeight();
  //      imageLayout = new VLayout();
        
        filterForm = new EntityAddressImageSetField();
        
        //filterForm.editRecord(null);
        
        filterLayout.addMember(filterForm);

        mainVLayout.addMember(filterLayout);
   //     mainVLayout.addMember(imageLayout);

        setPane(mainVLayout);
        
    }

    public VLayout getMainVLayout() {
        return mainVLayout;
    }
    
}
