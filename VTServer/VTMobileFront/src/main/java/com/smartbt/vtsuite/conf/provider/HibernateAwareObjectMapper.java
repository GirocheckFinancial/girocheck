/*
 ** File: JSONObjectMapper.java
 **
 ** Date Created: October 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
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


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import java.text.SimpleDateFormat;
/**
 *
 * @author Roberto Rodriguez
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
 
    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        hm.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, false);
        hm.configure(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, false);
        registerModule(hm);
                
        //The format for the date should be 'yyyy-MM-dd'T'HH:mm:ssX' but the 'X' is just for java 1.7 or greater
        //String timeZone = (new SimpleDateFormat("Z")).format(Calendar.getInstance().getTime());
        //timeZone = timeZone.substring(0, timeZone.length() - 2) + ":" + timeZone.substring(timeZone.length() - 2);
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));//'" + timeZone + "'"));

        configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        setSerializationInclusion(JsonInclude.Include.NON_EMPTY); 
    }
}
