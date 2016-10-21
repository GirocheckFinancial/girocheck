/*
 ** File: MainWindow.java
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
package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseLinkItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseStaticTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.MenuButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.ProfileEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.transaction.TransactionWindow;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import static com.smartbt.vtsuite.vtams.client.utils.Utils.debug;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.HeaderControls;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

/**
 * The Main Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class MainWindow extends BaseWindow {

    private BaseWindow currentWindow;
    private VLayout vLayout;
    private ProfileEditor profileEditor;

    /**
     * Constructor
     *
     */
    public MainWindow() {
        //setting size to make the window use the scroll bar when the size is less than the one set
        setMinHeight(900);
        setMinWidth(1900);

        DynamicForm userForm = new DynamicForm();
        userForm.setWidth(150);
        userForm.setNumCols(3);
        userForm.setColWidths(50,50,50);
//        userForm.setLayoutAlign(Alignment.CENTER);
        userForm.setLayoutAlign(Alignment.RIGHT);

        BaseLinkItem logoutLink = new BaseLinkItem("logoutLink", I18N.GET.LABEL_LOGOUT_TITLE());
        logoutLink.setWidth(50);
        logoutLink.setAlign(Alignment.RIGHT);
        logoutLink.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                Utils.doLogout();
            }
        });

        BaseStaticTextItem welcomeText = new BaseStaticTextItem("welcomeText");
        welcomeText.setShowTitle(false);
        welcomeText.setAlign(Alignment.RIGHT);
        welcomeText.setWidth(50);
        welcomeText.setValue(I18N.GET.LABEL_WELCOME_TITLE());
        welcomeText.setTextBoxStyle("header-text");

        BaseLinkItem profileLink = new BaseLinkItem("profileLink", Utils.getUsername());
        profileLink.setAlign(Alignment.LEFT);
        profileLink.setWidth(50);
        profileLink.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                debug("--Profile link click");
                Criteria criteria = new Criteria();
                Record record = new Record();
                criteria.setAttribute("userId", Utils.getUserId());

                UserDS userDS = new UserDS(EntityType.AMS);
                userDS.setFetchDataURL(Properties.GET_USER_WS);
                debug("--before request");
                userDS.fetchData(criteria, new DSCallback() {
                    public void execute(DSResponse response, Object rawData, DSRequest request) {
                        debug("--Call back");
                        profileEditor = new ProfileEditor(EntityType.AMS, response.getData()[0]);
                        debug("--After creating userEditor");

                        profileEditor.updateRecord(response.getData()[0]);
                        debug("--After updateRecord");

                        profileEditor.addListener(new EditorListener() { 
                            public void SaveActionExecuted() {
                                debug("--SaveProfile");
                                SaveProfile();
                            }
 
                            public void CloseActionExecuted() {
                                debug("--CloseActionExecuted");
                                profileEditor.hide();
                            }
                        });

                        profileEditor.show();
                        debug("--userEditor.show();");
                    }
                }, null);

            }
        });

        userForm.setItems(welcomeText, profileLink, logoutLink);

        setTitle(I18N.GET.WINDOW_MAIN_TITLE(I18N.GET.GIROCHECK_NAME(), I18N.GET.GIROCHECK_AMS_FULL_NAME(), I18N.GET.GIROCHECK_AMS_VERSION()));
        setSize("700", "500");
        setMargin(10);
        setHeaderControls(HeaderControls.HEADER_LABEL, userForm);

        final ToolStrip mainMenu = new ToolStrip();
        mainMenu.setWidth100();
        mainMenu.setStyleName("main-menu");

        // Transactions Menu Button ---------------------------------------------------------------------------------------------------------------
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_TRANSACTION)) {
            Utils.debug("transactionMenuButton >> Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_TRANSACTION)" + Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_TRANSACTION));
            MenuButtonItem transactionMenuButton = new MenuButtonItem("Transactions Platform");
            mainMenu.addButton(transactionMenuButton);
            transactionMenuButton.addClickHandler(new MainMenuClickHandler(this) {
                @Override
                public BaseWindow createWindow() {
                    return new TransactionWindow();
                }
            });
        }
        // Boarding Menu Button ---------------------------------------------------------------------------------------------------------------
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING)) {
            MenuButtonItem boardingButton = new MenuButtonItem(I18N.GET.BUTTON_BOARDING_TITLE());
            mainMenu.addButton(boardingButton);
            boardingButton.addClickHandler(new MainMenuClickHandler(this) {
                @Override
                public BaseWindow createWindow() {
                    return new BoardingPlatformWindow();
                }
            });
        }
        // Boarding Address Image Menu Button ---------------------------------------------------------------------------------------------------------------
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADDRESS)) {
            MenuButtonItem addressMenuButton = new MenuButtonItem(I18N.GET.BUTTON_ADDRESS_TITLE());
            mainMenu.addButton(addressMenuButton);
            addressMenuButton.addClickHandler(new MainMenuClickHandler(this) {
                @Override
                public BaseWindow createWindow() {
//                return new AddressPlatformWindow();
                    return new ImageAddressViewerWindow();
                }
            });
        }

        // Boarding Administration Menu Button ---------------------------------------------------------------------------------------------------------------
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION)) {
            MenuButtonItem administrationButton = new MenuButtonItem(I18N.GET.BUTTON_ADMINISTRATION_TITLE());
            mainMenu.addButton(administrationButton);
            administrationButton.addClickHandler(new MainMenuClickHandler(this) {
                @Override
                public BaseWindow createWindow() {
                    return new SettingsWindow();
                }
            });
        }

        mainMenu.addFill();

        vLayout = new VLayout();
        vLayout.addMember(mainMenu);

        // Set default opened Window
        MenuButtonItem firstMenuButtonItem = (MenuButtonItem) mainMenu.getMember(0);
        if (firstMenuButtonItem != null) {
            firstMenuButtonItem.fireEvent(new ClickEvent(null));
            firstMenuButtonItem.setSelected(true);
        }

        addItem(vLayout);
    }

    public VLayout getMainLayout() {
        return vLayout;
    }

    public BaseWindow getCurrentWindow() {
        return currentWindow;
    }

    public void setCurrentWindow(BaseWindow currentWindow) {
        this.currentWindow = currentWindow;
    }
    
      public void SaveProfile(){
        Record recordToSave = profileEditor.getRecord();
          
         profileEditor.getDataForm().getDataSource().updateData(recordToSave, new DSCallback() {
               
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    profileEditor.hide();
                }
            });
    }
}

abstract class MainMenuClickHandler implements ClickHandler {

    private BaseWindow window;
    private MainWindow mainWindow;

    public MainMenuClickHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void onClick(ClickEvent event) {
        if (mainWindow.getCurrentWindow() != getWindow()) {
            if (mainWindow.getCurrentWindow() != null) {
                mainWindow.getMainLayout().removeMember(mainWindow.getCurrentWindow());
            }
            mainWindow.setCurrentWindow(getWindow());
            mainWindow.getMainLayout().addMember(mainWindow.getCurrentWindow());
        }
    }

    private BaseWindow getWindow() {
        if (window == null) {
            window = createWindow();
        }
        return window;
    }

    public abstract BaseWindow createWindow();
    
  
}
