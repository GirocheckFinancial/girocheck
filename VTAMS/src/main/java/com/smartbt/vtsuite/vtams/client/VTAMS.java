/*
 ** File: VTAMS.java
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
package com.smartbt.vtsuite.vtams.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.SettingsDS;
import com.smartbt.vtsuite.vtams.client.gui.window.MainWindow;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartgwt.client.core.KeyIdentifier;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.rpc.HandleErrorCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.KeyCallback;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Dialog;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author Ariamnet Lopez
 */
public class VTAMS implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
//        MainWindow mainWindow = new MainWindow();
//        mainWindow.draw();
//        mainWindow.maximize();

        Utils.debug("onModuleLoad()");
        RPCManager.setUseHttpProxy(Boolean.FALSE);
        
//        Settings.TOKEN = Window.Location.getParameter("token");
        Settings.TOKEN = Utils.getToken();
        // Do a HeartBeat to check if the Server is up and the user has access to the app
        SettingsDS doHeartBeat = new SettingsDS();
        doHeartBeat.fetchData(null, new DSCallback() {
            /**
             * Callback to invoke on completion
             *
             * @param response Response sent by the server in response to a
             * DataSource request.
             * @param rawData data
             * @param request Request sent to the server to initiate a
             * DataSource operation.
             */
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Utils.debug("execute()");
                Utils.debug("request.getActionURL() = " + request.getActionURL());
                
                if (Settings.TOKEN != null) {
                    if (response.getStatus() == Constants.CODE_SUCCESS) {
                        Settings.INSTANCE.setSetting(response.getData()[0]);
                        MainWindow mainWindow = new MainWindow();
                        mainWindow.draw();
                        mainWindow.maximize();

                    }
                    RootPanel.getBodyElement().removeChild(RootPanel.get("loadingWrapper").getElement());
                } else {
                    SC.warn(I18N.GET.WINDOW_INFORMATION_TITLE(), I18N.GET.MESSAGE_SESSION_TIMEOUT_ERROR(), new BooleanCallback() {
                        public void execute(Boolean value) {
                            if (value != null) {
                                Utils.redirectToLoginPage();
                            }
                        }
                    }, new Dialog());
                }

            }
        });

        RPCManager.setHandleErrorCallback(new HandleErrorCallback() {
            /**
             * RPCManager error handler callback.
             *
             * @param response the response
             * @param request the request
             */
            public void handleError(DSResponse response, DSRequest request) {
                switch (response.getStatus()) {
                    case DSResponse.STATUS_SUCCESS: {
                        break;
                    }
                    case DSResponse.STATUS_TRANSPORT_ERROR: {
                        switch (response.getHttpResponseCode()) {
                            case 0:
                                SC.warn(I18N.GET.WINDOW_INFORMATION_TITLE(), I18N.GET.MESSAGE_SESSION_TIMEOUT_ERROR(), new BooleanCallback() {
                                    public void execute(Boolean value) {
                                        if (value != null) {
                                            Utils.redirectToLoginPage();
                                        }
                                    }
                                }, new Dialog());
                                break;
                            case 401:
                                SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_WRONG_USER_ERROR(), new BooleanCallback() {
                                    public void execute(Boolean value) {
                                        if (value != null) {
                                            Utils.doLogout();
                                        }
                                    }
                                }, new Dialog());
                                break;
                        }
                        break;
                    }
                    case DSResponse.STATUS_SERVER_TIMEOUT: {
                        SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_SERVER_TIMEOUT_ERROR());
                        break;
                    }
                    default: {
                        SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_GLOBAL_ERROR());
                        break;
                    }
                }
            }
        });

        if (!GWT.isScript() && Properties.DEV_MODE) {
            KeyIdentifier debugKey = new KeyIdentifier();
            debugKey.setCtrlKey(true);
            debugKey.setKeyName("D");
            Page.registerKey(debugKey, new KeyCallback() {
                public void execute(String keyName) {
                    SC.showConsole();
                }
            });
        }
    }
}
