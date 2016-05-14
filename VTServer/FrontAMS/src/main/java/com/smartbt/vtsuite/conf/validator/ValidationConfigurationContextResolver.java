/*
 ** File: ValidationConfigurationContextResolver.java
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
package com.smartbt.vtsuite.conf.validator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import javax.validation.ParameterNameProvider;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import org.glassfish.jersey.server.validation.ValidationConfig;

/**
 *
 * @author Ariel
 */
public class ValidationConfigurationContextResolver implements ContextResolver<ValidationConfig> {

    @Context
    private ResourceContext resourceContext;

    @Override
    public ValidationConfig getContext(final Class<?> type) {
         return new ValidationConfig();
                    //.constraintValidatorFactory(resourceContext.getResource(InjectingConstraintValidatorFactory.class))
                    //.parameterNameProvider(new CustomParameterNameProvider());
    }

    /**
     * See ContactCardTest#testAddInvalidContact.
     */
    private class CustomParameterNameProvider implements ParameterNameProvider {

        private final ParameterNameProvider nameProvider;

        public CustomParameterNameProvider() {
            nameProvider = null;//Validation.byDefaultProvider().configure().getDefaultParameterNameProvider();
        }

        @Override
        public List<String> getParameterNames(final Constructor<?> constructor) {
            return nameProvider.getParameterNames(constructor);
        }

        @Override
        public List<String> getParameterNames(final Method method) {

            return nameProvider.getParameterNames(method);
        }
    }
}
