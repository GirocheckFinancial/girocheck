package com.smartbt.vtsuite.vtams.client.gui.window.transaction;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class SubTransactionSection extends VLayout {

    private SubTransactionForm subForm;
    private SubTransactionListGrid listGrid;
   

    public SubTransactionSection() {
        setWidth100();
        setHeight100();
        
        Utils.debug( ">>>>> SubTransactionSection() 1" );

        this.setAutoHeight();

        subForm = new SubTransactionForm();
        Utils.debug( "SubTransactionSection() 2" );

        listGrid = new SubTransactionListGrid();
        Utils.debug( "SubTransactionSection() 3" );
        
        listGrid.setIsGroup(true);
        listGrid.setGroupTitle("Sub Transactions");
        
        addMember( subForm );
        addMember( listGrid );
        
         listGrid.addListener( new ListListener() {
            /**
             * Method to execute when a Select event is fired.
             *
             */
            public void SelectActionExecuted( Record record ) {
                SelectSubTransactionActionExcecuted( record );
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted( Record record ) {
                SelectSubTransactionActionExcecuted( record );
            }

            /**
             * Method to execute when a Data Arrived event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                // Do Nothing
            }
        } );
    }

    /**
     * @return the subForm
     */
    public SubTransactionForm getSubForm() {
        return subForm;
    }

    /**
     * @param subForm the subForm to set
     */
    public void setSubForm( SubTransactionForm subForm ) {
        this.subForm = subForm;
    }

    /**
     * @return the listGrid
     */
    public SubTransactionListGrid getListGrid() {
        return listGrid;
    }

    /**
     * @param listGrid the listGrid to set
     */
    public void setListGrid( SubTransactionListGrid listGrid ) {
        this.listGrid = listGrid;
    }

  
     public void SelectSubTransactionActionExcecuted(final Record record ) {
       
        subForm.getResultMessageTextArea().setValue( record.getAttribute( "resultMessage" ));

    }
}


class Label extends Widget implements HasText {

    public Label() {
        setElement( DOM.createLabel() );
    }

    public Label(String text) {
        setElement( DOM.createLabel() );
        getElement().setInnerText( ( text == null ) ? "" : text );
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText( String text ) {
        getElement().setInnerText( ( text == null ) ? "" : text );
    }

    public void setFor( String forWho ) {
        getElement().setAttribute( "for", forWho );
    }
}