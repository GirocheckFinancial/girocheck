/*
 ** File: ResponseFilter.java
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
package com.smartbt.vtsuite.conf.filter;

import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ariel Saavedra
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter {

    @Context
    private UriInfo uriInfo;

    /**
     *
     * @param requestContext
     * @param responseContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        try {
            if (!responseContext.getHeaders().containsKey("rollback")) {
                HibernateUtil.commitTransaction();
            } else {
                responseContext.getHeaders().remove("rollback");
                HibernateUtil.rollbackTransaction();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AMS] ResponseFilter " + uriInfo.getPath() + " was sent ",null);
    }
}
