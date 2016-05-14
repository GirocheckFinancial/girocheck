/*
 ** File: UserContext.java
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
package com.smartbt.vtsuite.conf.security;

import com.smartbt.girocheck.servercommon.model.User;
import java.security.Principal;

/**
 *
 * @author Ariel Saavedra
 */
public class UserContext implements Principal {

    private User user;

    /**
     *
     * @param user
     */
    public UserContext(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }
}