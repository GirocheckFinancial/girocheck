/*
 ** File: BaseFilterForm.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.vtams.client.gui.base;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import java.util.ArrayList;

import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;

/**
 * The Base Filter Form
 *
 * @author Ariamnet Lopez
 */
public class BaseFilterForm extends DynamicForm {

    protected BaseButtonItem addButton;
    protected BaseButtonItem updateButton;
    protected BaseButtonItem deleteButton;
    protected BaseButtonItem filterButton;
    protected BaseButtonItem deleteAllButton;
    protected BaseButtonItem importButton;
    protected BaseTextItem searchText;
    protected ArrayList<FilterListener> listeners = new ArrayList<FilterListener>();

    /**
     * Add FilterForm Listener
     *
     * @param listener the FilterForm listener
     */
    public void addListener(FilterListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes FilterForm Listener
     *
     * @param listener the FilterForm listener
     */
    public void removeListener(FilterListener listener) {
        listeners.remove(listener);
    }

    /**
     * Filter action to execute
     *
     */
    public void FilterActionExecuted() {
        if (validate()) {
            for (FilterListener listener : listeners) {
                listener.FilterActionExecuted();
            }
        }
    }

    /**
     * Add action to execute
     *
     */
    public void AddActionExecuted() {
        for (FilterListener listener : listeners) {
            listener.AddActionExecuted();
        }
    }

    /**
     * Update action to execute
     *
     */
    public void UpdateActionExecuted() {
        for (FilterListener listener : listeners) {
            listener.UpdateActionExecuted();
        }
    }

    /**
     * Delete action to execute
     *
     */
    public void DeleteActionExecuted() {
        for (FilterListener listener : listeners) {
            listener.DeleteActionExecuted();
        }
    }

    /**
     * Delete All action to execute
     *
     */
    public void DeleteAllActionExecuted() {
        for (FilterListener listener : listeners) {
            listener.DeleteAllActionExecuted();
        }
    }

    /**
     * Import action to execute
     *
     */
    public void ImportActionExecuted() {
        for (FilterListener listener : listeners) {
            listener.ImportActionExecuted();
        }
    }

    /**
     * Constructor
     *
     */
    public BaseFilterForm() {
        super();

        setWidth100();
        setMargin(10);
        setNumCols(20);
        setTitleOrientation(TitleOrientation.TOP);

        searchText = new BaseTextItem("searchFilter", I18N.GET.LABEL_SEARCH_TITLE(), false);
        searchText.setKeyPressFilter(RegExp.VALID_SEARCH_II_REG_EXP);

        addButton = new BaseButtonItem("addButton", I18N.GET.BUTTON_ADD_TITLE());        
        addButton.setAlign(Alignment.RIGHT);
        addButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                AddActionExecuted();
            }
        });

        updateButton = new BaseButtonItem("updateButton", I18N.GET.BUTTON_UPDATE_TITLE());
        updateButton.setDisabled(true);
        updateButton.setAlign(Alignment.RIGHT);
        updateButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                UpdateActionExecuted();
            }
        });

        deleteButton = new BaseButtonItem("deleteButton", I18N.GET.BUTTON_DELETE_TITLE());
//        deleteButton.setDisabled(true);
        deleteButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                SC.confirm(I18N.GET.MESSAGE_CONFIRM_DELETE(), new BooleanCallback() {
                    public void execute(Boolean value) {
                        if (value == Boolean.TRUE) {
                            DeleteActionExecuted();
                        }
                    }
                });
            }
        });

        filterButton = new BaseButtonItem("filter", I18N.GET.BUTTON_SEARCH_TITLE());

        searchText.addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if ((event.getKeyName().equals("Enter"))) {
                    FilterActionExecuted();
                }
            }
        });

        filterButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                FilterActionExecuted();
            }
        });

        deleteAllButton = new BaseButtonItem("deleteAllButton", I18N.GET.BUTTON_DELETE_ALL_TITLE());
        deleteAllButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                SC.confirm(I18N.GET.MESSAGE_CONFIRM_DELETE(), new BooleanCallback() {
                    public void execute(Boolean value) {
                        if (value == Boolean.TRUE) {
                            DeleteAllActionExecuted();
                        }
                    }
                });
            }
        });

        importButton = new BaseButtonItem("importButton", I18N.GET.BUTTON_IMPORT_TITLE());
        importButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                ImportActionExecuted();
            }
        });
    }

    public BaseButtonItem getAddButton() {
        return addButton;
    }

    public void setAddButton(BaseButtonItem addButton) {
        this.addButton = addButton;
    }

    public BaseButtonItem getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(BaseButtonItem updateButton) {
        this.updateButton = updateButton;
    }

    public BaseButtonItem getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(BaseButtonItem deleteButton) {
        this.deleteButton = deleteButton;
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

    /**
     * @return the importButton
     */
    public BaseButtonItem getImportButton() {
        return importButton;
    }

    /**
     * @param importButton the importButton to set
     */
    public void setImportButton(BaseButtonItem importButton) {
        this.importButton = importButton;
    }
}
