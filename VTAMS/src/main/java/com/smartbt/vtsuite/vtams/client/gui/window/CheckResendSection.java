 
package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.gui.component.PaginationForm;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.StatusCheckFilterForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author suresh
 */


public class CheckResendSection extends VLayout{
    
    protected HLayout filterLayout;
    protected VLayout listLayout;
    
    protected StatusCheckFilterForm filterForm;
    protected PaginationForm paginationForm;
    protected CheckResendListGrid listGrid;
   
    public CheckResendSection() {
        paginationForm = new PaginationForm();
        paginationForm.setMargin(10);
        filterLayout = new HLayout();
        filterLayout.setWidth(950);
        filterLayout.setAutoHeight();
        listLayout = new VLayout();
        
        filterForm = new StatusCheckFilterForm();
        listGrid = new CheckResendListGrid();
        listGrid.setWidth(1000);
        filterLayout.addMember(filterForm);
        filterLayout.addMember(paginationForm);
        listLayout.addMember(listGrid);
        
        addMember(filterLayout);
        addMember(listLayout);
        
     }

    public StatusCheckFilterForm getFilterForm() {
        return filterForm;
    }

    public PaginationForm getPaginationForm() {
        return paginationForm;
    }

    public CheckResendListGrid getListGrid() {
        return listGrid;
    }
    
    
}
