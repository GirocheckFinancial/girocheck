/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */
package com.smartbt.girocheck.servercommon.log;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class LogUtil {

    // private static List<String> logs = new ArrayList<String>();

    private static final Logger logger = Logger.getLogger(LogUtil.class);
    private static boolean loggerActived = true;
    private static File logFile;
    private static StringBuffer buffer;
    

    static {
        File folder = new File("c:/Girocheck_Output/");

        if (!folder.exists()) {
            folder.mkdirs();
        }
        logFile = new File(folder, "log.txt");

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(LogUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        buffer = new StringBuffer();
    }

    public static void log(String className, String log) {
        //  System.out.println( "[" + className + "] :: " + log );
        print("[" + className + "] :: " + log);
    }

    public static void log(String log) {
        // System.out.println( log );
        print(log);
    }

    public static void logAndStore(String className, String log)  {
   
            String newLog = "[" + className + "] :: " + log;
            logAndStore(newLog);
       
    }

    public static void logAndStore(String log){
        try {
            logger.info(log);
            write(log);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LogUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void print(String log) {
//        if(loggerActived || log.contains( "CoreComplexBL")){
        logger.info(log);
//        }else{
//            System.out.println( log );
//        }
    }

    private void createFolders()  {
        File folder = new File("c:/Girocheck_Output/");

        if (!folder.exists()) {
            folder.mkdirs();
        }
        File logFile = new File(folder, "log.txt");

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(LogUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void write(String text) throws IOException {
        buffer.append(text).append('\n');
        
        PrintStream fop = new PrintStream(logFile);

        fop.println(buffer.toString());

        fop.flush();
        fop.close();
    }
    
    public static void clearBuffer(){
        buffer = new StringBuffer();
    }
}
