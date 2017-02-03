/*
 ** File: ClientListGrid.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.list;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ClientDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.types.GroupStartOpen;

/**
 * The Transaction ListGrid
 *
 * @author Ariel Saavedra
 */
public class SearchClientListGrid extends BaseListGrid {

    private TextListGridField firstNameField = new TextListGridField("firstName", I18N.GET.LIST_FIELD_FIRST_NAME_TITLE(), false);
    private TextListGridField lastField = new TextListGridField("lastName", I18N.GET.LIST_FIELD_LAST_NAME_TITLE(), false);
    private TextListGridField telephoneField = new TextListGridField("telephone", I18N.GET.LIST_FIELD_TELEPHONE_TITLE(), false);
    private TextListGridField maskSSField = new TextListGridField("maskSS", I18N.GET.LIST_FIELD_MSSN_TITLE(), false);
 
    public SearchClientListGrid( ) {
        super();
 
        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_CLIENT_LIST());
 
        setGroupStartOpen(GroupStartOpen.ALL);
      

        setDataSource(new ClientDS());
        setFields(firstNameField,
                lastField,
                telephoneField, 
                maskSSField); 
    }
}
