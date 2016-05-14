/*
 ** File: LoguedUser.java
 **
 ** Date Created: December 2013
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

package com.smartbt.girocheck.common;

import com.smartbt.girocheck.servercommon.model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class SessionAMSUser {
    
    private static final Logger log = Logger.getLogger(SessionAMSUser.class);
    
    private static final ThreadLocal USER_THREAD_LOCAL = new ThreadLocal();
    
    public static User get() {
        return (User) USER_THREAD_LOCAL.get();
    }
    
    public static void set(User user) {
        USER_THREAD_LOCAL.set(user);
    }        
}
