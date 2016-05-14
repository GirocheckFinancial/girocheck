/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.util;

import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.DateDeserializer;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class CustomDateDeserializer extends  DateDeserializer{
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CustomDateDeserializer.class);

   public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, ParseException{
       log.info( "[CustomDateDeserializer] deserialize..." );
       
       DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        
        Date date = formatter.parse( jp.getText());
//        System.out.println( "date :: " + date );
        log.debug( "[CustomDateDeserializer] date :: " + date );
        
        return date;
   }
}
