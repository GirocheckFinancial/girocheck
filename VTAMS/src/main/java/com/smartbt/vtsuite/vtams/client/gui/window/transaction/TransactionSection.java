
package com.smartbt.vtsuite.vtams.client.gui.window.transaction;

import com.smartbt.vtsuite.vtams.client.gui.component.PaginationForm;
import com.smartbt.vtsuite.vtams.client.gui.window.detail.TransactionDetailWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.DateTextFilterForm;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class TransactionSection extends VLayout{
    protected HLayout filterLayout;
    protected VLayout listLayout;
    
    protected DateTextFilterForm filterForm;
    protected PaginationForm paginationForm;
    protected TransactionListGrid listGrid;
   // private TransactionDetailWindow detailWindow;
  

    public TransactionSection() {
//        Utils.debug( "TransactionSection() 1");
        paginationForm = new PaginationForm();

        filterLayout = new HLayout();
        filterLayout.setAutoHeight();
        listLayout = new VLayout();
        
        filterForm = new DateTextFilterForm();
//        Utils.debug( "TransactionSection() 2");
        listGrid = new TransactionListGrid();
//        Utils.debug( "TransactionSection() 3");
        filterLayout.addMember(filterForm);
        filterLayout.addMember(paginationForm);
        listLayout.addMember(listGrid);
        
        addMember(filterLayout);
        addMember(listLayout);
        
//        Utils.debug( "TransactionSection() 4");
    }

    public DateTextFilterForm getFilterForm() {
        return filterForm;
    }

    public PaginationForm getPaginationForm() {
        return paginationForm;
    }

    public TransactionListGrid getListGrid() {
        return listGrid;
    }
    
    
}
