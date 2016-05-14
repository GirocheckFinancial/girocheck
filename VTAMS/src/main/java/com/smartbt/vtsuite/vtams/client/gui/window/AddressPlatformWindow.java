
package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEntityTabSetWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.DefaultImageAddressTab;

/**
 *
 * @author Alejo
 */


public class AddressPlatformWindow extends BaseEntityTabSetWindow{
    
    public AddressPlatformWindow(){
        super();
        
        managementWindow.addTab(new DefaultImageAddressTab(I18N.GET.TAB_APPLICATION_IMAGE_ADDRESS_TITLE()));
        
    }
    
}
