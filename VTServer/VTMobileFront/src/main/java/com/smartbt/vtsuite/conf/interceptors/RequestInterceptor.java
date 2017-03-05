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
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Roberto Rodriguez
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    VTSessionManager vTSessionManager;
//    private static final Logger log = Logger.getLogger(RequestInterceptor.class);
    private final List<String> uriNotNeedToken = new LinkedList<String>() {
        {
            add("/FrontMobile");
            add("/FrontMobile/login/login");
        }
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("RequestInterceptor -> preHandle :: HibernateUtil.beginTransaction();");
        HibernateUtil.beginTransaction();
//        log.info("----->  Request " + request.getRequestURI() + " has been received <-----");
//
//        if (!validateToken(request.getRequestURI())) {
//            return true;
//        }
//
//        String token = request.getHeader("TOKEN");
//        if (token == null) {
//            throw new CustomException(Constants.HEADER_TOKEN_MISSING, "Token missing");
//        }
//
//        int result = vTSessionManager.validateSession(token);
//        if (result != Constants.CODE_SESSION_VALID) {
//            throw new CustomException(result, "Session problem");
//        }
        return true;
//        SessionUser.set(sessionServiceManager.getSessionByToken(token));
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
        }
    }

    private boolean validateToken(String uri) {
        if (uri.charAt(uri.length() - 1) == '/') {
            uri = uri.substring(0, uri.length() - 2);
        }
        return !uriNotNeedToken.contains(uri);
    }
}
