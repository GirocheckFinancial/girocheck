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
import com.smartbt.vtsuite.vtams.client.gui.window.inventory.InventoryWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.transaction.TransactionWindow;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
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
        userForm.setWidth(160);
        userForm.setNumCols(3);
        userForm.setColWidths(60, 50, 50);
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
        welcomeText.setAlign(Alignment.LEFT);
        welcomeText.setTextAlign(Alignment.LEFT);
        welcomeText.setWidth(60);
        welcomeText.setValue(I18N.GET.LABEL_WELCOME_TITLE());
        welcomeText.setTextBoxStyle("header-text");

        BaseLinkItem profileLink = new BaseLinkItem("profileLink", Utils.getUsername());
        profileLink.setAlign(Alignment.LEFT);
        profileLink.setTextAlign(Alignment.LEFT);
        profileLink.setWidth(50);
        profileLink.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {

                Criteria criteria = new Criteria();
                Record record = new Record();
                criteria.setAttribute("userId", Utils.getUserId());

                UserDS userDS = new UserDS(EntityType.AMS);
                userDS.setFetchDataURL(Properties.GET_USER_WS);

                userDS.fetchData(criteria, new DSCallback() {
                    public void execute(DSResponse response, Object rawData, DSRequest request) {

                        profileEditor = new ProfileEditor(EntityType.AMS, response.getData()[0]);

                        profileEditor.updateRecord(response.getData()[0]);

                        profileEditor.addListener(new EditorListener() {
                            public void SaveActionExecuted() {
                                SaveProfile();
                            }

                            public void CloseActionExecuted() {
                                profileEditor.hide();
                            }
                        });

                        profileEditor.show();
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
        // Card Inventory Menu---------------------------------------------------------------------------------------------------------
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MANAGE_CARD_INVENTORY)) {
            MenuButtonItem inventoryButton = new MenuButtonItem("Card Inventory");
            inventoryButton.addClickHandler(new MainMenuClickHandler(this) {
                @Override
                public BaseWindow createWindow() {
                    return new InventoryWindow();
                }
            });
            mainMenu.addButton(inventoryButton);
        }
        //Fee Management Menu Button----------------------------------------------------------------------------------------------
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_FEEMANAGEMENT)) {
            MenuButtonItem feeManagementButton = new MenuButtonItem("Fee Management");
            feeManagementButton.addClickHandler(new MainMenuClickHandler(this) {
                @Override
                public BaseWindow createWindow() {                   
                    return new FeeManagementWindow();
                }
            });
            mainMenu.addButton(feeManagementButton);
        }
        //CLIENT_BLACK_LIST Menu Button----------------------------------------------------------------------------------------------
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_CLIENT_BLACK_LIST)) {
            MenuButtonItem clientBlacklistButton = new MenuButtonItem("Black List");
            clientBlacklistButton.addClickHandler(new MainMenuClickHandler(this) {
                @Override
                public BaseWindow createWindow() {                   
                    return new ClientBlackListWindow();
                }
            });
            mainMenu.addButton(clientBlacklistButton);
        }
            
         //Check Resend Button------------------------------------------------------------------------------------------------------
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_CHECKRESEND)) {
            MenuButtonItem checkResendButton = new MenuButtonItem("Check Resend");
            checkResendButton.addClickHandler(new MainMenuClickHandler(this) {
                @Override
                public BaseWindow createWindow() {                   
                    return new CheckResendWindow();
                }
            });
            mainMenu.addButton(checkResendButton);
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

    public void SaveProfile() {
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
