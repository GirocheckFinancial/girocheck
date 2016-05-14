/*
 ** File: VTSessionManager.java
 **
 ** Date Created: October 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.servercommon.dao.VTSessionDAO;
import com.smartbt.vtsuite.servercommon.model.VTSession;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class VTSessionManager {

    private static final Logger log = Logger.getLogger(VTSessionManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private VTSessionDAO vtSessionDAO = VTSessionDAO.get();

//    public VTSession saveOrUpdateSession(Terminal terminal, Clerk clerk) {
//        return vtSessionDAO.saveOrUpdateSession(terminal, clerk);
//    }

    public VTSession getSessionByToken(String token) {
        return vtSessionDAO.getSessionByToken(token);
    }

    public int validateSession(String token) {
        return vtSessionDAO.validateSession(token);
    }
}
