/**/

package com.smartbt.vtsuite.vtams.client.gui.window.filter;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListener;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import java.util.ArrayList;
/**
 *
 * @author Alejo
 */


public class AddressImageFilterForm extends DynamicForm{
    
    protected BaseTextItem searchText;
    protected BaseButtonItem filterButton;
    protected BaseButtonItem rotateButton;
    
    private ArrayList<FilterListener> listeners = new ArrayList<FilterListener>();
    
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
     * Rotate action to execute
     *
     */
    public void RotateActionExecuted() {
        for (FilterListener listener : listeners) {
            listener.RotateActionExecuted();
        }
    }
    
   /**
     * Constructor
     *
     */
    public AddressImageFilterForm() {
        super();

        setWidth100();
        setMargin(10);
        setNumCols(20);
        setTitleOrientation(TitleOrientation.TOP);

        searchText = new BaseTextItem("searchFilter", "Terminal S/N", false);
        searchText.setKeyPressFilter(RegExp.VALID_SEARCH_II_REG_EXP);


        filterButton = new BaseButtonItem("filter", I18N.GET.BUTTON_SEARCH_TITLE());
        filterButton.setDisabled(true);

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

        rotateButton = new BaseButtonItem("rotateButton", I18N.GET.BUTTON_ROTATE_TITLE());
        rotateButton.setDisabled(true);
        rotateButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RotateActionExecuted();
            }
        });
        
        searchText.addChangeHandler(new ChangeHandler() {

            @Override
            public void onChange(ChangeEvent event) {
                if (event.getValue() != null){
                    filterButton.setDisabled(false);
                    rotateButton.setDisabled(false);
                }else
                {
                    filterButton.setDisabled(true);
                    rotateButton.setDisabled(true);
                }

            }
        });
        
        setFields(searchText,filterButton,rotateButton);

    }

    /**
     * @return the searchText
     */
    public BaseTextItem getSearchText() {
        return searchText;
    }

    /**
     * @param searchText the searchText to set
     */
    public void setSearchText(BaseTextItem searchText) {
        this.searchText = searchText;
    }

    /**
     * @return the filterButton
     */
    public BaseButtonItem getFilterButton() {
        return filterButton;
    }

    /**
     * @param filterButton the filterButton to set
     */
    public void setFilterButton(BaseButtonItem filterButton) {
        this.filterButton = filterButton;
    }

    /**
     * @return the rotateButton
     */
    public BaseButtonItem getRotateButton() {
        return rotateButton;
    }

    /**
     * @param rotateButton the rotateButton to set
     */
    public void setRotateButton(BaseButtonItem rotateButton) {
        this.rotateButton = rotateButton;
    }

    /**
     * @return the listeners
     */
    public ArrayList<FilterListener> getListeners() {
        return listeners;
    }

    /**
     * @param listeners the listeners to set
     */
    public void setListeners(ArrayList<FilterListener> listeners) {
        this.listeners = listeners;
    }
    
}
