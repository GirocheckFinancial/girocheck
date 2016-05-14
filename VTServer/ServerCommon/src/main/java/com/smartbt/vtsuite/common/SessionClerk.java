/*
 ** File: LoguedClerk.java
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

package com.smartbt.vtsuite.common;

import com.smartbt.vtsuite.servercommon.model.VTSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class SessionClerk {
    
    private static final Logger log = Logger.getLogger(SessionClerk.class);
    
    private static final ThreadLocal SESSION_THREAD_LOCAL = new ThreadLocal();
    
    public static VTSession get() {
        return (VTSession) SESSION_THREAD_LOCAL.get();
    }
    
    public static void set(VTSession session) {
        SESSION_THREAD_LOCAL.set(session);
    }        
}
