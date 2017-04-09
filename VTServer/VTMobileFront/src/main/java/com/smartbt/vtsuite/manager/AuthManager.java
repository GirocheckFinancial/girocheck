/*
 ** File: AuthManager.java
 **
 ** Date Created: August 2014
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
package com.smartbt.vtsuite.manager;
 
import com.smartbt.girocheck.servercommon.dao.MobileClientDao;
import com.smartbt.girocheck.servercommon.display.mobile.MobileClientDisplay;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.PasswordUtil;
import com.smartbt.vtsuite.util.MobileMessage;
import com.smartbt.vtsuite.util.MobileValidationException;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roberto Rodriguez
 */
@Service
@Transactional
public class AuthManager {

    public MobileClientDisplay getMobileClientDisplayByUserAndPassword(String username, String password) {
        return MobileClientDao.get().getMobileClientDisplayByUserAndPassword(username, password);
    }

    public void resetPassword(String clientId, String password, String lang) throws ValidationException, NoSuchAlgorithmException, MobileValidationException {
        int id = Integer.parseInt(clientId);
        MobileClient mobileClient = MobileClientDao.get().getMobileClientById(id);

        if (mobileClient == null) {
            throw new MobileValidationException(Constants.CLIENT_DOES_NOT_EXIST, MobileMessage.CLIENT_DOES_NOT_EXIST.get(lang));
        }

        String encyptedPassword = PasswordUtil.encryptPassword(password);
        mobileClient.setPassword(encyptedPassword);
        MobileClientDao.get().saveOrUpdate(mobileClient);
    }
}
