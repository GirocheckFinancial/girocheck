

package com.smartbt.vtsuite.vtams.client.gui.window.tab.parameter;


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.window.management.DefaultParametersManagementWindow;

/*
 *  @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class ParametersTab extends BaseTab {

    /**
     * Constructor
     *
     */
    public ParametersTab() {
        super(I18N.GET.TAB_APPLICATION_PARAMETERS_TITLE());

        DefaultParametersManagementWindow defaultParametersManagementWindow = new DefaultParametersManagementWindow();

        mainVLayout.removeMember(filterLayout);
        mainVLayout.removeMember(listLayout);

        mainVLayout.addMember(defaultParametersManagementWindow);
    }
}
