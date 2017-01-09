
package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseWindow;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author Sreekanth
 */

public class FeeManagementWindow extends BaseWindow {
    
    protected VLayout mainLayout = new VLayout();

    protected SectionStack feeMgmtStack = new SectionStack();
    
    protected SectionStackSection feeMgmtStackSection = new SectionStackSection("Fee Management");
    
    protected FeeManagementGridPanel feeMgmtGridPanel;
    
    /**
     * Constructor
     *
     */
    public FeeManagementWindow() {
        super();
        
        setHeight100();
        setWidth100();
        setShowHeader(false);
        setShowEdges(false);
        setStyleName("base-entity-window");
        setBodyStyle("base-entity-window"); 
        
        feeMgmtStackSection.setExpanded(true);
        feeMgmtStackSection.setCanCollapse(false);
        
        feeMgmtGridPanel = new FeeManagementGridPanel(EntityType.AMS, null);
        feeMgmtGridPanel.setHeight100();
       
        feeMgmtStackSection.addItem(feeMgmtGridPanel);
  
        feeMgmtStack.addSection(feeMgmtStackSection);
     
        feeMgmtStack.setVisibilityMode(VisibilityMode.MULTIPLE);
 
        mainLayout.setMargin(1); 
        mainLayout.addMember(feeMgmtStack);        
        
        addItem(mainLayout); 
        feeMgmtGridPanel.Filter();        
        
    }
    
}
