/*
 ** File: RequestInterceptor.java
 **
 ** Date Created: November 2014
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
package com.smartbt.vtsuite.conf.interceptors;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import static com.smartbt.vtsuite.controller.v1.GeneralController.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Roberto Rodriguez
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static Map excludedURLs = new HashMap();

    static {
        excludedURLs.put("ping", "");
        excludedURLs.put("login", "");
        excludedURLs.put("register", "");
        excludedURLs.put("forgotPassword", "");
        excludedURLs.put("optOut", "");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("RequestInterceptor -> preHandle :: HibernateUtil.beginTransaction();");

        try {
            if (HibernateUtil.getSession().getTransaction() != null) {
                HibernateUtil.beginTransaction();
            } else {
                System.out.println("TRANSACTION STILL OPEN!!!");
            }
        } catch (Exception e) {
            System.out.println("It was going to throw a NestedTransactionException...   Thread.currentThread().sleep(1000);");
            HibernateUtil.commitTransaction();

            Thread.currentThread().sleep(1000);
            HibernateUtil.beginTransaction();
        }

        String uri = request.getRequestURI();
        System.out.println("URL = " + uri);

        String lang = request.getHeader("LANG");
        System.out.println("LANG = " + lang);

        if (lang == null || lang.equalsIgnoreCase("defaultLocale")) {
            lang = "es";
        } else {
            lang = lang.toLowerCase();
        }
        request.getSession().setAttribute(LANG, lang);

        if (isExcludedURL(uri)) {
            System.out.println("Excluded URL, (not need to check for token)");
            return true;
        }

        String tokenInSession = (String) request.getSession().getAttribute("TOKEN");
        String token = request.getHeader("TOKEN");

        System.out.println("JSESSIONID = " + request.getSession().getId());

        System.out.println("tokenInSession = " + tokenInSession);
        System.out.println("tokenInHeader = " + token);

        if (tokenInSession == null) {
            request.getSession().setAttribute(TOKEN, token);
        }

        boolean isValid = (tokenInSession != null && token != null && token.equals(tokenInSession));

        System.out.println("is Token Valid = " + isValid);

        return isValid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        response.addHeader("Access-Control-Allow-Origin", "*");

        try {
            System.out.println("RequestInterceptor -> postHandle :: HibernateUtil.commitTransaction();");
            HibernateUtil.commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.getSession().close();
        }
    }

    private static boolean isExcludedURL(String uri) {
        return uri != null && excludedURLs.containsKey(uri.substring(uri.lastIndexOf("/") + 1));
    }
}
