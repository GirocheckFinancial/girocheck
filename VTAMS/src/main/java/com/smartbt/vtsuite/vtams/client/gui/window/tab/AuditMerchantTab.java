/*
 ** File: AuditMerchantTab.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab;


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseSearchWindow;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.window.search.SearchAuditLogWindow;

/**
 * The AuditMerchant Tab
 *
 * @author Ariel Saavedra
 */
public class AuditMerchantTab extends BaseTab {

    /**
     * Constructor
     *
     */
    public AuditMerchantTab() {
        super(I18N.GET.TAB_AUDIT_MERCHANT_TITLE());

        mainVLayout.removeMember(filterLayout);
        mainVLayout.removeMember(listLayout);

        mainVLayout.addMember(new SearchAuditLogWindow());
    }
}
