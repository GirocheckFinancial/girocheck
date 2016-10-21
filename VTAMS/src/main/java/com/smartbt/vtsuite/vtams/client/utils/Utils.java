/*
 ** File: Utils.java
 **
 ** Date Created: June 2013
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
package com.smartbt.vtsuite.vtams.client.utils;

import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;

import com.smartbt.vtsuite.vtams.client.gui.component.datasource.SystemPropertyDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.VTSessionDS;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomSystemProperties;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.XMLTools;
import com.smartgwt.client.util.SC;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * The Utils Class
 *
 * @author Ariamnet Lopez
 */
public class Utils {

    public static void redirectToLoginPage() {
        
        Cookies.removeCookie("USER_NAME");
        Cookies.removeCookieNative("USER_NAME", "/VTAMS");
        Cookies.removeCookie("TOKEN");
        Cookies.removeCookieNative("TOKEN", "/VTAMS");
        
        UrlBuilder url = Window.Location.createUrlBuilder();
        url.setProtocol(Window.Location.getProtocol());
        url.setHost(Window.Location.getHost());
        url.setPath("VTAMS/login.html");

        String hostUrl = url.buildString();
        Window.Location.replace(hostUrl);
    }

    /**
     * Method used to do logout from the application
     *
     */
    public static void doTokenLogout() {
        UrlBuilder url = Window.Location.createUrlBuilder();
        url.setProtocol(Window.Location.getProtocol());
        url.setHost(Window.Location.getHost());
        url.setPath("VTAMS/login.html");
        url.removeParameter("token");

        String hostUrl = url.buildString();
        Window.Location.replace(hostUrl);
    }

//    public static void doLogout() {
//        Cookies.removeCookie("USER_NAME");
//        Cookies.removeCookieNative("USER_NAME", "/VTAMS");
//
//        Criteria criteria = new Criteria("name", NomSystemProperties.VTAMS_LOGOUT_URL.getViewValue());
//
//        SystemPropertyDS systemPropertyDS = new SystemPropertyDS();
//        systemPropertyDS.fetchData(criteria, new DSCallback() {
//            /**
//             * Callback to invoke on completion
//             *
//             * @param response Response sent by the server in response to a
//             * DataSource request.
//             * @param rawData data
//             * @param request Request sent to the server to initiate a
//             * DataSource operation.
//             */
//            public void execute(DSResponse response, Object rawData, DSRequest request) {
//
//                if (response.getStatus() == Constants.CODE_SUCCESS) {
//                    JSONArray valueStatus = XMLTools.selectObjects(rawData, "/propertyValue");
//                    String value = ((JSONString) valueStatus.get(0)).stringValue();
//
//                    Window.Location.replace(value);
//                } else {
//                    SC.warn(I18N.GET.WINDOW_WARNING_TITLE(), I18N.GET.MESSAGE_WARNING_NO_LOGOUT_SYSTEM_PROPERTY());
//                }
//            }
//        });
//    }
    public static void doLogout() {
        
        if(getToken()!=null){
        Cookies.removeCookie("USER_NAME");
        Cookies.removeCookieNative("USER_NAME", "/VTAMS");

        Criteria criteria = new Criteria("token", getToken());
        
        Cookies.removeCookie("TOKEN");
        Cookies.removeCookieNative("TOKEN", "/VTAMS");
        Cookies.setCookie("TOKEN", null);

        VTSessionDS vTSessionDS = new VTSessionDS();
        
        vTSessionDS.fetchData(criteria, new DSCallback() {
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

                if (response.getStatus() == Constants.CODE_SUCCESS) {

                    redirectToLoginPage();
                    
                } else {
                    SC.warn(I18N.GET.WINDOW_WARNING_TITLE(), I18N.GET.MESSAGE_WARNING_NO_LOGOUT_SYSTEM_PROPERTY());
                }
            }
        });
        }else
            redirectToLoginPage();
    }

    public static String getUsername() {
        String usernameCookie = Cookies.getCookie("USER_NAME");
//        Cookies.removeCookie("USER_NAME");
//        Cookies.removeCookieNative("USER_NAME", "/VTAMS");
//        String username = "smartbt@worldpay.us";
        String username = "";

        if ((usernameCookie != null) && (!usernameCookie.isEmpty())) {
            username = usernameCookie.replace("\"", "");
        }
//        return username.contains("@") ? username.substring(0, username.indexOf("@")).toUpperCase() : username;
        return username;
    }
    
    public static String getToken() {

        String tokenCookieTest = Cookies.getCookie("TOKEN");
        
        if (!tokenCookieTest.equals("undefined")) {
            if (!Cookies.getCookie("TOKEN").isEmpty()) {
                String tokenCookie = Cookies.getCookie("TOKEN");
//        Cookies.removeCookie("TOKEN");
//        Cookies.removeCookieNative("TOKEN", "/VTAMS");

          debug("Utils.getToken() received -> "+tokenCookie);

                if ((tokenCookie != null) && (!tokenCookie.isEmpty())) {
                    tokenCookie = tokenCookie.replace("\"", "");
                    tokenCookie = tokenCookie.split("_")[1];
                    debug("Utils.getToken() after process -> "+tokenCookie);
                }
             //   debug("Utils.getToken() output -> " + tokenCookie);
                return tokenCookie;
            } else {
                return null;
            }
        } else {
            redirectToLoginPage();
        }
        return null;
    }
    
    public static String getUserId() {

        String tokenCookieTest = Cookies.getCookie("TOKEN");
        
        if (!tokenCookieTest.equals("undefined")) {
            if (!Cookies.getCookie("TOKEN").isEmpty()) {
                String idCookie = Cookies.getCookie("TOKEN");
 
          debug("Utils.getUserId() received -> "+idCookie);

                if ((idCookie != null) && (!idCookie.isEmpty())) {
                    idCookie = idCookie.replace("\"", "");
                    idCookie = idCookie.split("_")[0];
                    debug("Utils.getToken() after process -> "+idCookie);
                }
             //   debug("Utils.getToken() output -> " + tokenCookie);
                return idCookie;
            } else {
                return null;
            }
        } else {
            redirectToLoginPage();
        }
        return null;
    }

    public static native void debug(String debug)/*-{     
     $wnd.console.debug(debug);
     }-*/;

    public static String MD5(String unencrypted) {

		try {

			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(unencrypted.getBytes(), 0, unencrypted.length());
			String MD5 = new BigInteger(1, m.digest()).toString(16);

			return MD5;

		} catch (Exception e) {
                        debug("[VTAMS Utils] Error creating MD5 value: "+ e.getMessage());
			return null;
		}

	}
}
