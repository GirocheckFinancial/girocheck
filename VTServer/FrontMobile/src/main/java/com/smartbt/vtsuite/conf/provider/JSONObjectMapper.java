/*
 ** File: JSONObjectMapper.java
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
package com.smartbt.vtsuite.conf.provider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ws.rs.ext.ContextResolver;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.Pair;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

/**
 *
 * @author Ariel Saavedra
 */
public class JSONObjectMapper implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;
    final ObjectMapper combinedObjectMapper;

    public JSONObjectMapper() {
        defaultObjectMapper = createDefaultMapper();
        combinedObjectMapper = createCombinedObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {

        return defaultObjectMapper;

    }

    private static ObjectMapper createCombinedObjectMapper() {

        Pair combinedIntrospector = createJaxbJacksonAnnotationIntrospector();
        ObjectMapper result = new ObjectMapper();
        result.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
        result.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
        result.setDeserializationConfig(result.getDeserializationConfig().withAnnotationIntrospector(combinedIntrospector));
        result.setSerializationConfig(result.getSerializationConfig().withAnnotationIntrospector(combinedIntrospector));

        
        return result;
    }

    private static ObjectMapper createDefaultMapper() {

        ObjectMapper result = new ObjectMapper();
        result.configure(Feature.INDENT_OUTPUT, true);

//         Hibernate4Module hbm = new Hibernate4Module();
//        hbm.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
//        // hbm.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, false);
//        registerModule(hbm);

        //The format for the date should be 'yyyy-MM-dd'T'HH:mm:ssX' but the 'X' is just for java 1.7 or greater
        String timeZone = (new SimpleDateFormat("Z")).format(Calendar.getInstance().getTime());
        timeZone = timeZone.substring(0, timeZone.length() - 2) + ":" + timeZone.substring(timeZone.length() - 2);
        result.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'" + timeZone + "'"));

        result.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, false);
        result.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        result.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        result.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);

        //result.getDeserializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        //result.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        
        return result;
    }

    private static Pair createJaxbJacksonAnnotationIntrospector() {

        AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
        AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

        return new AnnotationIntrospector.Pair(jacksonIntrospector, jaxbIntrospector);
    }
}
