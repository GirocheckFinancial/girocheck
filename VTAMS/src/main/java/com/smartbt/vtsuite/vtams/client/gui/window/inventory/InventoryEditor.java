/*
 ** File: ClientEditor.java
 **
 ** Date Created: October 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.gui.window.inventory;

import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseStaticTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * The Search Customer Form Class
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class InventoryEditor extends Window {

    protected VLayout mainVLayout;
    private DynamicForm dataForm;
    protected DynamicForm actionForm;

    protected BaseButtonItem acceptButton; 

    BaseStaticTextItem inventoryText;
    BaseStaticTextItem thresholdText;

    private HiddenItem id;
    private HiddenItem inventory;

    private BaseTextItem inventoryUpdater;
    private BaseTextItem threshold;
    protected BaseButtonItem plusButton;
    protected BaseButtonItem minusButton;

    private EditorListener editorListener;

    public InventoryEditor() {
        setTitle("");
      
        setHeight(200);
        setWidth("500");

        dataForm = new DynamicForm();
        dataForm.setNumCols(4);
        dataForm.setColWidths("30%", "10%", "10%", "10%");
        id = new HiddenItem();
        inventory = new HiddenItem();
 
        //----------------------

        inventoryText = new BaseStaticTextItem("inventoryText");
        inventoryText.setShowTitle(false);
        inventoryText.setAlign(Alignment.LEFT);
        inventoryText.setTextAlign(Alignment.LEFT);
        inventoryText.setWidth(80);
        inventoryText.setValue(""); 
        //Row 1 ------------------------------------------------           
        inventoryUpdater = new BaseTextItem(1, "inventoryUpdater", "", "Inventory", false);
       
        threshold = new BaseTextItem(2, "threshold", "Threshold", "Threshold", false);
   
        inventoryUpdater.setWidth(50);
        inventoryUpdater.setShowTitle(false);

        threshold.setWidth(120); 
        //--------------------------------------------------------------------------------------------------------------------
        plusButton = new BaseButtonItem("plusButton", "+");
        plusButton.setAlign(Alignment.LEFT);
        plusButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) { 
                String invString = inventoryUpdater.getValueAsString(); 
                Integer newInventory = Integer.parseInt(invString);
 
                String currentInventoryStr = inventory.getAttributeAsString("inventory");
                Integer currentInventory = Integer.parseInt(currentInventoryStr);
              
                Integer resultingInventory = currentInventory + newInventory;
 
                inventory.setAttribute("inventory", resultingInventory);
                inventoryText.setValue("Inventory: " + resultingInventory);
            }
        });
        plusButton.setWidth(30);
        plusButton.setHeight(30); 
        //--------------------------------------------------------------------------------------------------------------------
        minusButton = new BaseButtonItem("minusButton", "-");
        minusButton.setAlign(Alignment.LEFT);
        minusButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
               
                String invString = inventoryUpdater.getValueAsString();
                Integer newInventory = Integer.parseInt(invString);
 
                Integer currentInventory = inventory.getAttributeAsInt("inventory");
               
                if (newInventory <= currentInventory) {
                    Integer resultingInventory = currentInventory - newInventory;
 
                    resultingInventory = Integer.parseInt(resultingInventory + "");  //avoiding 024 kind of values
 
                    inventory.setAttribute("inventory", resultingInventory);

                    inventoryText.setValue("Inventory: " + resultingInventory);
                } else {
                    SC.say("Invalid Input", "The inventory to substract can not be bigger than the current inventory.");
                }

            }
        });
        minusButton.setWidth(30);
        minusButton.setHeight(30);
      
        //--------------------------------------------------------------------------------------------------------------------
        dataForm.setTitleOrientation(TitleOrientation.TOP);
        dataForm.setMargin(10);
        dataForm.setDataSource(new InventoryDS());
        dataForm.setFields(id,
                inventory, inventoryText, plusButton, inventoryUpdater, minusButton, threshold,
                new RowSpacerItem());
      
        acceptButton = new BaseButtonItem("acceptButton", "Accept");
        acceptButton.setWidth(60);
        acceptButton.setAlign(Alignment.RIGHT);
        acceptButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                editorListener.SaveActionExecuted();
            }
        });
       
        actionForm = new DynamicForm();
        actionForm.setNumCols(3);
        actionForm.setWidth(400);
        actionForm.setAlign(Alignment.RIGHT);
        dataForm.setColWidths("80%", "10%", "10%");
        actionForm.setFields(new RowSpacerItem(), acceptButton);
     
        mainVLayout = new VLayout();
       
        mainVLayout.addMember(dataForm);
      
        mainVLayout.addMember(actionForm);
        
        addItem(mainVLayout);
    }

    //  @Override
    public void updateRecord(Record record) {
        if (record != null) { 
            inventoryText.setValue("Inventory: " + record.getAttribute("inventory"));
            threshold.setValue(record.getAttribute("threshold"));

            inventory.setAttribute("inventory", record.getAttribute("inventory"));
            id.setAttribute("id", record.getAttribute("id"));

        }
    }

    public Record getRecord() {
         String thresholdValue = threshold.getValueAsString();
         String inventoryValue = inventory.getAttribute("inventory");
        String idValue = id.getAttribute("id");
        
        Record record = new Record();
        record.setAttribute("id", idValue);
        record.setAttribute("threshold", thresholdValue);
        record.setAttribute("inventory", inventoryValue);
        return record;
    }

    public HiddenItem getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(HiddenItem id) {
        this.id = id;
    }

    /**
     * @return the threshold
     */
    public BaseTextItem getThreshold() {
        return threshold;
    }

    /**
     * @param threshold the threshold to set
     */
    public void setThreshold(BaseTextItem threshold) {
        this.threshold = threshold;
    }

    /**
     * @return the editorListener
     */
    public EditorListener getEditorListener() {
        return editorListener;
    }

    /**
     * @param editorListener the editorListener to set
     */
    public void setEditorListener(EditorListener editorListener) {
        this.editorListener = editorListener;
    }

    /**
     * @return the inventory
     */
    public HiddenItem getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(HiddenItem inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the inventoryUpdater
     */
    public BaseTextItem getInventoryUpdater() {
        return inventoryUpdater;
    }

    /**
     * @param inventoryUpdater the inventoryUpdater to set
     */
    public void setInventoryUpdater(BaseTextItem inventoryUpdater) {
        this.inventoryUpdater = inventoryUpdater;
    }

    /**
     * @return the dataForm
     */
    public DynamicForm getDataForm() {
        return dataForm;
    }

    /**
     * @param dataForm the dataForm to set
     */
    public void setDataForm(DynamicForm dataForm) {
        this.dataForm = dataForm;
    }
}
