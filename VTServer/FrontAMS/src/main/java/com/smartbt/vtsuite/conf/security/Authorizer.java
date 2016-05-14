/*
 ** File: Authorizer.java
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
import com.smartbt.girocheck.servercommon.model.RolePrivilege;
import java.security.Principal;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Ariel Saavedra
 */
public class Authorizer implements SecurityContext {

    @Context
    private UriInfo uriInfo;
    private User user;
    private UserContext principal;
    private boolean accessAllowed;

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public Authorizer(final User user) {
        this.user = user;
        this.accessAllowed = true;
        this.principal = new UserContext(user);
    }

    /**
     *
     * @return
     */
    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    /**
     *
     * @param idPrivilege
     * @return
     */
    @Override
    public boolean isUserInRole(String idPrivilege) {
        boolean allowed = false;
        for (RolePrivilege rolePrivilege : user.getRole().getRolPrivilege()) {
            if (rolePrivilege.getPrivilege().getId() == Integer.valueOf(idPrivilege)) {
                allowed = true;
            }
        }
        this.accessAllowed = accessAllowed && allowed;
        return this.accessAllowed;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isSecure() {
        return "https".equals(uriInfo.getRequestUri().getScheme());
    }

    /**
     *
     * @return
     */
    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
