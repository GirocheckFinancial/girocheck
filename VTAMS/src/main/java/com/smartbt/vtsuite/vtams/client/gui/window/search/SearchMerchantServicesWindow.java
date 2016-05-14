/*
 ** File: MerchantServicesWindow.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.search;

import com.smartbt.vtsuite.vtams.client.gui.window.detail.MerchantDetailLayout;

import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseSearchWindow;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.window.detail.CustomerDetailLayout;
import com.smartbt.vtsuite.vtams.client.gui.window.detail.DeviceDetailLayout;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.MerchantDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CustomerDS;
import com.smartbt.vtsuite.vtams.client.gui.window.detail.TerminalDetailLayout;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TerminalDS;
import com.smartbt.vtsuite.vtams.client.gui.window.management.CustomerManagementWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.management.MerchantManagementWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.management.TerminalManagementWindow;

/**
 * The Merchant Services Window
 *
 * @author Ariel Saavedra
 */
public class SearchMerchantServicesWindow extends BaseSearchWindow {
    
    @Override
    protected void selectActionExecuted(BaseTreeNode node) {
         node.setAttribute("merchantServices", true);
                if (detailWindow != null) {
                    vDetailLayout.removeChild(detailWindow);
                }
                switch ((EntityType) node.getAttributeAsObject("entityType")) {                 
                    case CUSTOMER: {
                        // Create and add Customer Detail View
                        detailSection.setTitle(I18N.GET.WINDOW_CUSTOMER_INFO_TITLE());
                        detailWindow = new CustomerDetailLayout(node, true, new CustomerDS(Properties.GET_CUSTOMER_WS));
                        vDetailLayout.addMember(detailWindow);

                        vManagementLayout.removeMembers(vManagementLayout.getMembers());
                        managementWindow = new CustomerManagementWindow(node);
                        vManagementLayout.addMember(new CustomerDetailLayout(node));
                        vManagementLayout.addMember(managementWindow);
                        break;
                    }
                    case MERCHANT: {
                        // Create and add Merchant Detail Window 
                        detailSection.setTitle(I18N.GET.WINDOW_MERCHANT_INFO_TITLE());
                        detailWindow = new MerchantDetailLayout(node, true, new MerchantDS(Properties.GET_MERCHANT_WS));
                        vDetailLayout.addMember(detailWindow);

                        vManagementLayout.removeMembers(vManagementLayout.getMembers());
                        managementWindow = new MerchantManagementWindow(node);
                        vManagementLayout.addMember(new MerchantDetailLayout(node));
                        vManagementLayout.addMember(managementWindow);
                        break;
                    }
                    case TERMINAL: {
                        // Create and add Terminal Detail View 
                        detailSection.setTitle(I18N.GET.WINDOW_TERMINAL_INFO_TITLE());
                        detailWindow = new TerminalDetailLayout(node, true, new TerminalDS(Properties.GET_TERMINAL_WS));
                        vDetailLayout.addMember(detailWindow);

                        vManagementLayout.removeMembers(vManagementLayout.getMembers());
                        managementWindow = new TerminalManagementWindow(node);
                        vManagementLayout.addMember(new TerminalDetailLayout(node));
                        vManagementLayout.addMember(managementWindow);
                        break;
                    }
                    case DEVICE: {
                        // Create and add Device Detail View 
                        detailSection.setTitle(I18N.GET.WINDOW_DEVICE_INFO_TITLE());
                        detailWindow = new DeviceDetailLayout(node);
                        vDetailLayout.addMember(detailWindow);

                        vManagementLayout.removeMembers(vManagementLayout.getMembers());
                        vManagementLayout.addMember(new DeviceDetailLayout(node));
                        break;
                    }
                }
    }

    @Override
    public BaseTreeNode getSelectedNode() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
}
