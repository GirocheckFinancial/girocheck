/*
 ** File: ExceptionInterceptor.java
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

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.Constants;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Roberto Rodriguez
 */
@ControllerAdvice
public class ExceptionInterceptor {

    static Logger log = Logger.getLogger(ExceptionInterceptor.class);

    public ExceptionInterceptor() {
        System.out.println("Creating ExceptionInterceptor bean...");
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse handleGeneralError(Exception ex) {

        System.out.println("ExceptionInterceptor -> handleGeneralError");

        if (HibernateUtil.getSession().getTransaction() != null) {
            System.out.println("There is a liven transaction");
            try {
                System.out.println("RequestInterceptor -> postHandle :: HibernateUtil.commitTransaction();");
                HibernateUtil.commitTransaction();
            } catch (Exception e) {
                HibernateUtil.rollbackTransaction();
            } finally {
                HibernateUtil.getSession().close();
            }
        } else {
            System.out.println("No transactions are open.");
        }

        ex.printStackTrace();

        return new BaseResponse(Constants.CODE_ERROR_GENERAL, VTSuiteMessages.ERROR_GENERAL);
    }

//    @ResponseBody
//    @ExceptionHandler(javax.ws.rs.ForbiddenException.class)
//    public BaseResponse handleForbiddenException(javax.ws.rs.ForbiddenException ex) {
//        exceptionCommon(ex);
//        return new BaseResponse(Constants.CODE_NOT_PRIVILEGE, ex.getMessage());
//    }
//
//    @ResponseBody
//    @ExceptionHandler(javax.xml.bind.ValidationException.class)
//    public BaseResponse handleMethodArgumentNotValidException(javax.xml.bind.ValidationException ex) {
//        //exceptionCommon(ex);
//        return new BaseResponse(Constants.CODE_INVALID_ENTRY_DATA, ex.getMessage());
//    }
//    
//    @ResponseBody
//    @ExceptionHandler(CustomException.class)
//    public BaseResponse handleGeneralError(CustomException ex) {
//        //exceptionCommon(ex);
//        return new BaseResponse(ex.getCode() , ex.getMessage());
//    }
//    
    private void exceptionCommon(Exception ex) {
        log.error(ex, ex);
    }
}
