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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roberto Rodriguez
 */
@Service
@Transactional
public class AuthManager {
  
    public MobileClientDisplay getMobileClientDisplayByUserAndPassword(String username, String password){
         return MobileClientDao.get().getMobileClientDisplayByUserAndPassword(username, password);
    }
}
