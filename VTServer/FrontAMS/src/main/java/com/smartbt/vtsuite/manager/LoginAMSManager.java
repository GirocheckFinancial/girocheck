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
import com.smartbt.girocheck.servercommon.dao.UserDAO;
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
import java.util.Date;
import javax.xml.bind.ValidationException;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>, Alejo
 */
public class LoginAMSManager extends GeneralAMSManager {

    private static final Long ONE_MIN = 60_000L;
    private static final Long ONE_HOUR = 60 * 60_000L;
    private static final Long THREE_MONTHS = 90 * 24 * ONE_HOUR;

    private final LoginAMSDAO loginAMSDAO = LoginAMSDAO.get();

    private UserDAO userDao = UserDAO.get();

    /**
     * Authenticates a user
     *
     * @param username
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws javax.xml.bind.ValidationException
     */
    public BaseResponse authenticateUser(String username, String password) throws NoSuchAlgorithmException, ValidationException, Exception {
            HibernateUtil.beginTransaction();
            LoginAMSValidator.authenticateUser(username, password);

            ResponseData response = new ResponseData();
            String encryptedPassword = encryptPassword(password);

            System.out.println("****encryptedPassword = " + encryptedPassword);

            User user = loginAMSDAO.findByUserName(username);

            if (user == null) {
                System.out.println("user == null");
                failLogin(response);
            } else {
//            if(!user.getActive()){
//                failLogin(response, VTSuiteMessages.INACTIVE_USER);
//                return response;
//            }

                Integer failedAttempts = user.getFailedAttempts();
                System.out.println("failedAttempts = " + failedAttempts);
                if (failedAttempts == null) {
                    failedAttempts = 0;
                }
                Date now = new Date();
                Date lastTimeUpdatedPassword = user.getLastTimeUpdatePassword();
                System.out.println("lastTimeUpdatedPassword = " + lastTimeUpdatedPassword);

                if (lastTimeUpdatedPassword == null) {
                    user.setLastTimeUpdatePassword(now);
                } else {
                    if (now.getTime() - lastTimeUpdatedPassword.getTime() >= THREE_MONTHS) {
                        System.out.println("PASSWORD_EXPIRED");
                        response.setStatus(Constants.CODE_INVALID_USER);
                        response.setStatusMessage(VTSuiteMessages.PASSWORD_EXPIRED);
                        user.setActive(false);
                        return response;
                    }
                }

                if (failedAttempts >= 3) {
                    if (user.getLastTimeFailedAttempt() != null
                            && (now.getTime() - user.getLastTimeFailedAttempt().getTime() < ONE_HOUR)) {
                        System.out.println("USER_BLOCKED");
                        response.setStatus(Constants.CODE_INVALID_USER);
                        response.setStatusMessage(VTSuiteMessages.USER_BLOCKED);
                        user.setActive(false);
                        return response;
                    } else {
                        user.setFailedAttempts(failedAttempts = 0);
                        user.setActive(true);
                    }
                }

                if (user.getLastTimeFailedAttempt() != null
                        && (now.getTime() - user.getLastTimeFailedAttempt().getTime() >= ONE_MIN)) {
                    user.setFailedAttempts(failedAttempts = 0);
                }

                if (user.getPassword() == null || !user.getPassword().equals(encryptedPassword)) {
                    user.setFailedAttempts(failedAttempts + 1);
                    user.setLastTimeFailedAttempt(now);
                    System.out.println("INCREMENTING failedAttempts to " + user.getFailedAttempts());
                    failLogin(response);
                } else {
                    successLogin(response, user);
                    user.setFailedAttempts(failedAttempts = 0);
                }

                userDao.save(user);
            }

            System.out.println("Before return getStatusMessage = " + response.getStatusMessage());
            return response;

        
    }

    private void failLogin(ResponseData response) {
        failLogin(response, VTSuiteMessages.INVALID_LOGIN_CREDENTIALS);
    }

    private void failLogin(ResponseData response, String message) {
        response.setStatus(Constants.CODE_INVALID_USER);
        response.setStatusMessage(message);
    }

    private void successLogin(ResponseData response, User user) {
        VTSession userSession = VTSessionDAO.get().saveOrUpdateSession(user);

        response.setData(user.getId() + "_" + userSession.getToken());

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
    }

    /**
     * Basic SHA-1 Password Encryption
     *
     * @param password
     * @return String
     * @throws NoSuchAlgorithmException
     * @throws javax.xml.bind.ValidationException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, ValidationException {
        System.out.println(encryptPassword("sad"));
    }

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
