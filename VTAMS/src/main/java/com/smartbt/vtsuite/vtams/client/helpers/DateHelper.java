/*
 ** File: DateHelper.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.vtams.client.helpers;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import java.util.Date;

/**
 * The Date Helper
 *
 * @author Ariamnet Lopez
 */
public class DateHelper {

    private static DateDisplayFormatter japaneseDateFormater = new DateDisplayFormatter() {

        @Override
        public String format(Date date) {
            String year = DateTimeFormat.getFormat("yyyy").format(date);
            String month = DateTimeFormat.getFormat("MM").format(date);
            String day = DateTimeFormat.getFormat("dd").format(date);
            return year + "年" + month + "月" + day + "日";
        }
    };

    private static DateDisplayFormatter japaneseTimeFormater = new DateDisplayFormatter() {

        @Override
        public String format(Date date) {
            String hour = DateTimeFormat.getFormat("HH").format(date);
            String minutes = DateTimeFormat.getFormat("mm").format(date);
            String seconds = DateTimeFormat.getFormat("ss").format(date);
            return hour + "時" + minutes + "分" + seconds + "秒";
        }
    };
    private static DateDisplayFormatter usDateFormater = new DateDisplayFormatter() {

        @Override
        public String format(Date date) {
            String year = DateTimeFormat.getFormat("yyyy").format(date);
            String month = DateTimeFormat.getFormat("MM").format(date);
            String day = DateTimeFormat.getFormat("dd").format(date);
            return year + "/" + month + "/" + day;
        }
    };

    private static DateDisplayFormatter usTimeFormater = new DateDisplayFormatter() {

        @Override
        public String format(Date date) {
            String hour = DateTimeFormat.getFormat("HH").format(date);
            String minutes = DateTimeFormat.getFormat("mm").format(date);
            String seconds = DateTimeFormat.getFormat("ss").format(date);
            return hour + ":" + minutes + ":" + seconds;
        }
    };

    /**
     * Converts date to US Short Date
     *
     * @param date the date
     * @return String
     */
    public static String toUSShortDate(Date date) {
       return usDateFormater.format(date);
    }
    
     public static String toUSShortDate(String dateStr) {
         if(dateStr != null){
             try{
                 Long dateLong = Long.parseLong(dateStr); 
                 Date date = new Date(dateLong);
                 return usDateFormater.format(date);
             }catch(Exception e){
                 Utils.debug( "Exception parsing " + dateStr);
             }
          
         }
       return "";
    }
    
    public static String toUSShortTime(Date date) {
        if(date == null)return "";
        return usTimeFormater.format(date);
    }
    
    public static String toUSShortTime(String dateStr) {
        if(dateStr != null){
            Long dateLong = Long.parseLong(dateStr);
            Date date = new Date(dateLong);
            return usTimeFormater.format(date);
        }
        
        return "";
    }

    /**
     * Converts date to 24 Hour Time
     *
     * @param date the date
     * @return String
     */
    public static String to24HourTime(Date date) {
        String time = DateUtil.TO24HOURTIME.format(date);
        return time;
    }

    public static String toJapaneseDate(Date date) {
        return japaneseDateFormater.format(date);
    }

    public static String toJapaneseTime(Date date) {
        return japaneseTimeFormater.format(date);
    }
}
