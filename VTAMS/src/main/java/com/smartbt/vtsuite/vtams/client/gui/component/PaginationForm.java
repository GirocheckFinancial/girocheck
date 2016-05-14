/*
 ** File: PaginationForm.java
 **
 ** Date Created: July 2013
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
package com.smartbt.vtsuite.vtams.client.gui.component;


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ariamnet Lopez
 */
public class PaginationForm extends DynamicForm {

    private int pageNumber = 0;
    private int totalPagesNumber = 0;
    private int rowsPerPage = 40;
    private BaseStaticTextItem paginationText;
    private BaseLinkItem prevPageLink;
    private BaseLinkItem nextPageLink;
    private ArrayList<PaginationListener> listeners = new ArrayList<PaginationListener>();
    private Criteria criteria;
    private LINK_PRESSED lastLinkPressed;

    private static enum LINK_PRESSED {

        NEXT, PREV
    };

    /**
     * Add Pagination Listener
     *
     * @param listener the Pagination listener
     */
    public void addListener(PaginationListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes Pagination Listener
     *
     * @param listener the Pagination listener
     */
    public void removeListener(PaginationListener listener) {
        listeners.remove(listener);
    }

    /**
     * Previous Page action to execute
     *
     */
    public void PreviousActionExecuted() {
        lastLinkPressed = LINK_PRESSED.PREV;
        prevPageLink.setDisabled(true);
        prevPageLink.redraw();
        for (PaginationListener listener : listeners) {
            listener.PreviousActionExecuted();
        }
    }

    /**
     * Previous Page action to execute
     *
     */
    public void NextActionExecuted() {
        lastLinkPressed = LINK_PRESSED.NEXT;
        nextPageLink.setDisabled(true);
        nextPageLink.redraw();
        for (PaginationListener listener : listeners) {
            listener.NextActionExecuted();
        }
    }

    public PaginationForm() {
        //setWidth(250);
        setAutoHeight();
        setNumCols(3);
        setLayoutAlign(Alignment.RIGHT);
        setLayoutAlign(VerticalAlignment.CENTER);

        paginationText = new BaseStaticTextItem("welcomeText");
        paginationText.setWidth(100);
        paginationText.setShowTitle(false);
        paginationText.setAlign(Alignment.RIGHT);
        paginationText.setTextBoxStyle("header-text");
        paginationText.setDefaultValue(I18N.GET.LABEL_DEFAULT_PAGINATION());

        prevPageLink = new BaseLinkItem("prevPageLink", I18N.GET.LABEL_PREVPAGE_TITLE());
        prevPageLink.setWidth(100);
        prevPageLink.setDisabled(true);
        prevPageLink.setAlign(Alignment.RIGHT);
        prevPageLink.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                PreviousActionExecuted();
            }
        });

        nextPageLink = new BaseLinkItem("nextPageLink", I18N.GET.LABEL_NEXTPAGE_TITLE());
        nextPageLink.setWidth(100);
        nextPageLink.setAlign(Alignment.RIGHT);
        nextPageLink.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                NextActionExecuted();
            }
        });

        setFields(new FormItem[]{paginationText, prevPageLink, nextPageLink});
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getRequestPageNumber() {
        if (lastLinkPressed != null) {
            if (lastLinkPressed.equals(LINK_PRESSED.NEXT)) {
                return this.pageNumber + 1;
            } else if (lastLinkPressed.equals(LINK_PRESSED.PREV)) {
                return this.pageNumber - 1;
            }
        }
        return 0;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPagesNumber() {
        return totalPagesNumber;
    }

    public void setTotalPagesNumber(int totalPagesNumber) {
        this.totalPagesNumber = totalPagesNumber;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }


    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public LINK_PRESSED getLastLinkPressed() {
        return lastLinkPressed;
    }

    public void setLastLinkPressed(LINK_PRESSED lastLinkPressed) {
        this.lastLinkPressed = lastLinkPressed;
    }

    public void updatePage(int totalPagesNumber) {
        if (lastLinkPressed != null) {
            if (lastLinkPressed.equals(LINK_PRESSED.NEXT)) {
                this.pageNumber += 1;
            } else if (lastLinkPressed.equals(LINK_PRESSED.PREV)) {
                this.pageNumber -= 1;
            }
            lastLinkPressed = null;
        } else {
            this.pageNumber = 0;
        }
        this.totalPagesNumber = totalPagesNumber == 0 ? 1 : totalPagesNumber;

        if ((pageNumber + 1) == totalPagesNumber || totalPagesNumber == 0) {
            nextPageLink.setDisabled(true);
        } else {
            nextPageLink.setDisabled(false);
        }
        if (pageNumber == 0) {
            prevPageLink.setDisabled(true);
        } else {
            prevPageLink.setDisabled(false);
        }
        paginationText.setValue("Page " + (this.pageNumber + 1) + " of " + this.totalPagesNumber);
    }
    
     public Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.addCriteria( "pageNumber",getRequestPageNumber());
        criteria.addCriteria( "rowsPerPage",getRowsPerPage());
        return criteria;
    }
}
