/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CoreLogger {

    private static List<String> logs;
    private static final Logger logger = Logger.getLogger( CoreLogger.class );

    private File file = new File( "c:/Girocheck_Output/log.txt" );

    public CoreLogger() {
        logs = new ArrayList<>();

        if ( !file.exists() ) {
            try {
                file.createNewFile();
            } catch ( IOException ex ) {
                java.util.logging.Logger.getLogger( CoreLogger.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }

    public void logAndStore( String log ) {
        
        logs.add(log );
         log( "", log );
    }

    public void logAndStore( String className, String log ) {
        String newLog = "[" + className + "] :: " + log;
         log( className, newLog );
    }

    public void log( String className, String newLog ) {
        try {
            logger.info( newLog );

            writeInputData();
        } catch ( IOException ex ) {
            java.util.logging.Logger.getLogger( CoreLogger.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    public void sendLogEmail() throws Exception {
        // writeInputData();
//        String receiptTitle = ( logs == null || logs.isEmpty() ) ? "Logs List empty" : logs.get( 0 );
//
//        System.out.println( receiptTitle );
//
//        List<String> emailList = new ArrayList<String>();
//        // emailList.add(" stephen@techtrex.com");
//        emailList.add( "girochecktest@yahoo.com" );
//
//        //    TransactionValidator.sendTheEmail(imageAsBase64, emailList);
//        String server_address = "smtp.cbeyond.com";
//        String server_port = "587";
//        String server_username = "direx@smartbt.com";
//        String server_password = "MiamiRocks12";
//        String server_from_address = "direx@smartbt.com";
//
//        boolean email_debug = false;
//
//        String[] recipients = new String[emailList.size()];
//        emailList.toArray( recipients );
//
//        EmailUtils email;
//
//        if ( server_username != null && !server_username.isEmpty() ) {
//            email = new EmailUtils( server_address, server_port, server_username, server_password );
//        } else {
//            email = new EmailUtils( server_address, server_port );
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        buffer.append( "<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body> Received Data:" );
//
//        for ( String log : logs ) {
//            buffer.append( "<br/>" + log );
//        }
//
//        buffer.append( "</body></html>" );
//
//        email.setMessage( buffer.toString(), "text/html" );
//        email.sendEmail( recipients, server_from_address, receiptTitle, email_debug );
    }

    private void writeInputData() throws IOException {
     
        PrintStream ps = new PrintStream( file );

        for ( String log : logs ) {
            ps.println( log );
        }
        
        ps.flush();
        ps.close();
    }

//    private void writeInputData() throws IOException{
//        StringBuffer buffer = new StringBuffer();
//        
//        File file = new File( "c:/Girocheck_Output/log.txt");
//        if(!file.exists()){
//            file.createNewFile();
//        }
//        
//        PrintStream ps = new PrintStream(file);
//        for ( String log :logs ) {
//            ps.println( log );
//        }
//        
//        ps.flush();
//        ps.close();
//    }
}
