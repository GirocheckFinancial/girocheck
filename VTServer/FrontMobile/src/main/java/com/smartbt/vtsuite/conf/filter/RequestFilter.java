/*
 ** File: RequestFilter.java
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
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ariel Saavedra
 */
@Provider
public class RequestFilter implements ContainerRequestFilter {

    @Context
    private UriInfo uriInfo;

    /**
     *
     * @param requestContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AMS] RequestFilter filter() Request in " + uriInfo.getPath() + " has been received " ,null);

        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        List<String> contentTypes = headers.remove(HttpHeaders.CONTENT_TYPE);
        if (contentTypes != null && !contentTypes.isEmpty()) {
            String contentType = contentTypes.get(0);
            String sanitizedContentType = contentType.replaceFirst("; charset=UTF-8", "");
            headers.add(HttpHeaders.CONTENT_TYPE, sanitizedContentType);
        }

    }
}
