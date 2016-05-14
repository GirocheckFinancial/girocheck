/**
 *
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be reproduced, transmitted,
 * transcribed, stored in a retrieval system, or translated into any language or
 * computer language, in any form or by any means, electronic, mechanical,
 * magnetic, optical, chemical, manual or otherwise, without the prior written
 * permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.VTSessionDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.model.User;
import com.smartbt.girocheck.servercommon.model.VTSession;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.dao.LoginAMSDAO;
import com.smartbt.vtsuite.validators.LoginAMSValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.ValidationException;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>, Alejo
 */
public class LoginAMSManager extends GeneralAMSManager {

    private final LoginAMSDAO loginAMSDAO = LoginAMSDAO.get();

    /**
     * Authenticates a user
     *
     * @param username
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws javax.xml.bind.ValidationException
     */
    public BaseResponse authenticateUser(String username, String password) throws NoSuchAlgorithmException, ValidationException {
        
//        System.out.println(" beginTransaction");
        HibernateUtil.beginTransaction();
//        System.out.println("authenticateUser");
//        System.out.println("authenticateUser() with username: " + username);
        LoginAMSValidator.authenticateUser(username, password);
//        System.out.println("1");
        ResponseData response = new ResponseData();
        String encrypted = encryptPassword(password);
//        System.out.println("Password encrypted in authenticateUser() "+encrypted);
        User user = loginAMSDAO.findByUserNameAndPassword(username, encrypted);
//        System.out.println("2 user = null :: "+ (user == null));
        
        if (user == null) {
//            System.out.println("authenticateUser(): The user doesn't exist");

            response.setStatus(user != null ? Constants.CODE_SUCCESS : Constants.CODE_INVALID_USER);
            response.setStatusMessage(user != null ? VTSuiteMessages.SUCCESS : VTSuiteMessages.INVALID_USER);
        } else {
            VTSession userSession = VTSessionDAO.get().saveOrUpdateSession(user);
//            System.out.println("3");
            response.setData(userSession.getToken());

            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
        }
        return response;
    }

    /**
     * Basic SHA-1 Password Encryption
     *
     * @param password
     * @return String
     * @throws NoSuchAlgorithmException
     * @throws javax.xml.bind.ValidationException
     */
    public static String encryptPassword(String password) throws NoSuchAlgorithmException, ValidationException {
        LoginAMSValidator.encryptPassword(password);
        MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
        byte[] result = mDigest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }

    /**
     * Utility Method to Check Password
     *
     * @param password
     * @param encryptedPassword
     * @return boolean
     * @throws NoSuchAlgorithmException
     * @throws javax.xml.bind.ValidationException
     */
    public static boolean checkPassword(String password, String encryptedPassword) throws NoSuchAlgorithmException, ValidationException {
        return encryptPassword(password).equals(encryptedPassword);
    }
    
    public BaseResponse deleteSession(String token) {
        BaseResponse response = new BaseResponse();
//            if (!frontFacade.existObject(ClerkRole.class, role.getId())) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.CLERK_ROLE_DOES_NOT_EXIST);
//                log.info("----->  updateClerkRole: This ClerkRole does not exist <-----");
//            } else {

        VTSessionDAO vtsession = VTSessionDAO.get();
        vtsession.deleteSession(token);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
//            }
        return response;
    }
}
