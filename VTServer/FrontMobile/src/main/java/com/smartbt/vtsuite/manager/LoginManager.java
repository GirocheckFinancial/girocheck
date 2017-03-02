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

import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.vtsuite.dao.MobileClientDao;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>, Alejo
 */
public class LoginManager{
    
    private MobileClientDao mobileClientDao = MobileClientDao.get();
    

    public MobileClient getMobileClientByUserNameAndPwd(String userName, String password) {
        return mobileClientDao.getMobileClientByUserNameAndPwd(userName, password);
    } 
    
    public MobileClient getMobileClientByPINAndDeviceId(String pin, String deviceId) {
         return mobileClientDao.getMobileClientByPINAndDeviceId(pin, deviceId);
    }
    
    public MobileClient saveorUpdate(MobileClient mobileClient) {
        return mobileClientDao.saveOrUpdateMobileClient(mobileClient);
    } 
}
