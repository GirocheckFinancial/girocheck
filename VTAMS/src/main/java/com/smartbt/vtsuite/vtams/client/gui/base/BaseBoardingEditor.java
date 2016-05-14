
package com.smartbt.vtsuite.vtams.client.gui.base;

import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.listener.FormBoardingListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.ArrayList;

/**
 *
 * @author Ariel Saavedra
 */
public abstract class BaseBoardingEditor extends VLayout {

    protected HiddenItem idEntity;
    protected SpacerItem spacerItem;
    protected BaseButtonItem acceptButton;
    protected BaseButtonItem addChildButton;
    protected BaseButtonItem reportButton;

    protected int idParent;
    protected DynamicForm dataForm;
    protected DynamicForm actionForm;
    protected boolean dataFormHasBeenModified;
    protected ChangedHandler itemsChangeHandler = new ChangedHandler() {

        public void onChanged(ChangedEvent event) {
            dataFormHasBeenModified = true;
        }
    };

    private ArrayList<FormBoardingListener> listeners = new ArrayList<FormBoardingListener>();

    /**
     * Constructor
     *
     * @param treeNode
     */
    public BaseBoardingEditor(final int id, final int idParent, final EntityType entityType) {
        setOverflow(Overflow.AUTO);
         Utils.debug( ":: BaseBoardingEditor 1 " ); 
        this.idParent = idParent;
        
        idEntity = new HiddenItem();
        idEntity.setAttribute( "id" , id);
        
          Utils.debug( ":: BaseBoardingEditor 2" ); 
             
         
        
        spacerItem = new SpacerItem();
        spacerItem.setColSpan(5);

        acceptButton = new BaseButtonItem("acceptButton", "Accept");
        acceptButton.setAlign(Alignment.RIGHT);
        acceptButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Utils.debug( "[BaseBoardingEditor] onClick() AcceptAction"); 
                acceptButton.disable();
                acceptActionExecuted(getRecord(), idParent, entityType);
                acceptButton.enable();
            }
        });
        acceptButton.setWidth(80);
        acceptButton.setHeight(30);
        
        reportButton = new BaseButtonItem("reportButton", "Report");
        reportButton.setAlign(Alignment.RIGHT);
        reportButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Utils.debug( "[BaseBoardingEditor] onClick() ReportAction"); 
                reportButton.disable();
                reportActionExecuted(getRecord(), idParent, entityType);
                reportButton.enable();
            }
        });
        reportButton.setWidth(80);
        reportButton.setHeight(30);
        
         Utils.debug( ":: BaseBoardingEditor 3" ); 
        createAddChildButton();
        
         Utils.debug( ":: BaseBoardingEditor 4" ); 
        dataForm = new DynamicForm();
        dataForm.setWidth(500);
        dataForm.setMargin(10);
        //dataForm.setNumCols(20);
        dataForm.setTitleOrientation(TitleOrientation.TOP);

        actionForm = new DynamicForm();
        actionForm.setMargin(5);
        actionForm.setNumCols(8);
        actionForm.setWidth(dataForm.getWidth());
        actionForm.setAlign(Alignment.RIGHT);
        
       
         Utils.debug( ":: BaseBoardingEditor 5" ); 
        
//        actionForm.setFields( acceptButton,addChildButton);
        

        addMembers(dataForm, actionForm);
         Utils.debug( ":: BaseBoardingEditor 6" ); 
    }
    

    /**
     * Add FilterForm Listener
     *
     * @param listener the FilterForm listener
     */
    public void addListener(FormBoardingListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes FilterForm Listener
     *
     * @param listener the FilterForm listener
     */
    public void removeListener(FormBoardingListener listener) {
        listeners.remove(listener);
    }


    /**
     * Add Merchant action to execute
     *
     * @param parentNode
     */
    public void addMerchantActionExecuted(int id, int idParent) {
        for (FormBoardingListener listener : listeners) {
            if(idParent != 0)
            listener.addMerchantActionExecuted(id, idParent);
        }
    }

   
    public void addTerminalActionExecuted(int id, int idParent) {
        for (FormBoardingListener listener : listeners) {
            if(idParent != 0)
            listener.addTerminalActionExecuted(id, idParent);
        }
    }
    
    public void reportActionExecuted(Record record, int idParent, EntityType entityType) {
        for (FormBoardingListener listener : listeners) {
            Utils.debug("[BaseBoardingEditor]:: reportActionExecuted with idparent = " + idParent + " and entityType: " + entityType);
            if (!entityType.equals(EntityType.AGRUPATION)) {
                if (idParent != 0) {
                    Utils.debug("[BaseBoardingEditor]:: reportActionExecuted the idParent is not 0");
                    listener.reportActionExecuted(record, idParent, entityType);
                }
            } else {
                listener.reportActionExecuted(record, idParent, entityType);
            }
        }
        Utils.debug("[BaseBoardingEditor]:: reportActionExecuted done");
    }
   
    public void acceptActionExecuted(Record record, int idParent, EntityType entityType) {
        Utils.debug( ":: [BaseBoardingEditor] acceptActionExecuted()  :: listeners.size() = " + listeners.size()); 
        for (FormBoardingListener listener : listeners) {
            listener.acceptActionExecuted(record, idParent, entityType);
        }
    }

   
    public void dataFormSetFields(FormItem... fields) {
        for (FormItem item : fields) {
            item.addChangedHandler(itemsChangeHandler);
        }
        dataForm.setFields(fields);
    }

    public boolean isNewEntity() {
        Utils.debug( ">>> isNewEntity()");
        boolean result;
            result = idEntity.getAttribute( "id" ) != null && !idEntity.getAttribute( "id" ).endsWith( "0");
        Utils.debug( ">>> [BaseBoardingEditor] isNewEntity() :: " + result);     
        Utils.debug( ">>> [BaseBoardingEditor] isNewEntity() :: idEntity.getAttribute(id)" + idEntity.getAttribute( "id" ));    
        return result;
    }

    public void setIdParent( int idParent ) {
        this.idParent = idParent;
    }
    
    public int getId(){
         Utils.debug( "getId()");
       return idEntity.getAttributeAsInt("id");
    }

    

    public boolean getDataFormHasBeenModified() {
        return dataFormHasBeenModified;
    }

    public abstract Record getRecord();

    public abstract void loadRecord(Record record);

    public abstract EntityType getEntityTypeEditor();
    
    public abstract void createAddChildButton();
}
