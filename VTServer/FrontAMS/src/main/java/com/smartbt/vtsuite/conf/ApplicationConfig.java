/*
 ** File: ApplicationConfig.java
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
package com.smartbt.vtsuite.conf;

import com.smartbt.vtsuite.conf.filter.RequestFilter;
import com.smartbt.vtsuite.conf.filter.ResponseFilter;
import com.smartbt.vtsuite.conf.filter.SecurityFilter;
import com.smartbt.vtsuite.conf.provider.ExceptionMapperProvider;
import com.smartbt.vtsuite.conf.provider.JSONObjectMapper;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 *
 * @author Ariel Saavedra
 */
@ApplicationPath("webresources")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        /* cambio_to_girocheck*/
     //   packages("com.smartbt.girocheck.controller");
        packages("com.smartbt.vtsuite.controller");
        
        register(JacksonFeature.class);
        register(JSONObjectMapper.class);
       
        register(MultiPartFeature.class);

        // Add filters.
        register(SecurityFilter.class);
        register(RequestFilter.class);
        register(ResponseFilter.class);
        //Roles Security
        register(RolesAllowedDynamicFeature.class);

        //Exception Handler
        register(ExceptionMapperProvider.class);
        //register(ValidationExceptionMapperProvider.class);
        
        //register(ValidationConfigurationContextResolver.class);
        // Now you can expect validation errors to be sent to the client.
       // property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        // @ValidateOnExecution annotations on subclasses won't cause errors.
        //property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }
}
