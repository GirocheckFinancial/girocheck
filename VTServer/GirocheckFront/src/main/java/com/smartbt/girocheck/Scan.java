/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileJPG, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck;

import com.smartbt.girocheck.scan.BalanceInquiryRequest;
import com.smartbt.girocheck.scan.BalanceInquiryRes;
import com.smartbt.girocheck.scan.CardReloadDataRequest;
import com.smartbt.girocheck.scan.CardReloadDataRes;
import com.smartbt.girocheck.scan.CardReloadRequest;
import com.smartbt.girocheck.scan.CardReloadRes;
import com.smartbt.girocheck.scan.CardToBankConfirmationRequest;
import com.smartbt.girocheck.scan.CardToBankConfirmationRes;
import com.smartbt.girocheck.scan.CardToBankRequest;
import com.smartbt.girocheck.scan.CardToBankRes;
import com.smartbt.girocheck.scan.CheckAuthLocationConfigRequest;
import com.smartbt.girocheck.scan.CheckAuthLocationConfigRes;
import com.smartbt.girocheck.scan.CheckAuthRequest;
import com.smartbt.girocheck.scan.CheckAuthRes;
import com.smartbt.girocheck.scan.CheckAuthSubmitRequest;
import com.smartbt.girocheck.scan.CheckAuthSubmitRes;
import com.smartbt.girocheck.scan.TecnicardConfirmationRequest;
import com.smartbt.girocheck.scan.TecnicardConfirmationRes;
import com.smartbt.girocheck.servercommon.email.ImagePart;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.manager.EmailManager;
import com.smartbt.vtsuite.manager.FrontManager;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
@WebService( serviceName = "Scan", portName = "ScanPort", endpointInterface = "com.smartbt.girocheck.scan.Scan", targetNamespace = "http://scan.girocheck.smartbt.com/", wsdlLocation = "WEB-INF/wsdl/Scan.wsdl" )
public class Scan {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Scan.class);

    public CheckAuthRes checkAuth( final CheckAuthRequest arg0 ) throws Exception {
//        System.out.println( ">>***********  FRONT CHECK AUTH *******************" );
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CHECK AUTH ",null );
        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {

                    createFolders();
                    sendcheckAuth( arg0 );
                    writeInputData( "CheckAuth", arg0.mock() );
                } catch ( Exception ex ) {
//                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] Error ", ex.getMessage() );
                }
            }
        } );
        thread.run();

//        System.out.println( ">> after sendcheckAuth." );
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] After sendcheckAuth",null );
        return new CheckAuthRes().build( FrontManager.processTransaction( arg0 ) );
//        return new CheckAuthRes().mock();
    }

    public TecnicardConfirmationRes tecnicardConfirmation( final TecnicardConfirmationRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT TECNICARD CONFIRMATION ",null );
        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                    sendConfirmation( arg0 );
                    writeInputData( "TecnicardConfirmation", arg0.toMap() );
                } catch ( Exception ex ) {
//                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] Error", ex.getMessage() );
                }
            }
        } );
        thread.run();
        return new TecnicardConfirmationRes().build( FrontManager.processTransaction( arg0 ) );
//        return new TecnicardConfirmationRes().mock();
    }

    public CardReloadRes cardReload( final CardReloadRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CARD RELOAD ",null );
        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    sendCardReload( arg0 );
                } catch ( Exception ex ) {
                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        thread.run();
        return new CardReloadRes().build( FrontManager.processTransaction( arg0 ) );
//        return new CardReloadRes().mock();
    }
    public CardReloadDataRes cardReloadData( final CardReloadDataRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CARD RELOAD DATA",null );
        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    sendCardReloadData( arg0 );
                } catch ( Exception ex ) {
                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        thread.run();
        return new CardReloadDataRes().build( FrontManager.processTransaction( arg0 ) );
//        return new CardReloadRes().mock();
    }

    public CheckAuthSubmitRes checkAuthSubmit( CheckAuthSubmitRequest arg0 ) throws Exception {
//        return new CheckAuthSubmitRes().build( FrontManager.processTransaction( arg0 ) );  made for the cancel functionality that is not used.
        return new CheckAuthSubmitRes().mock();
    }

    public CheckAuthLocationConfigRes checkAuthLocationConfig( final CheckAuthLocationConfigRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CHECK AUTH LOCATION CONFIG",null );
        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                    EmailManager.sendTheEmail( "CheckAuthLocationConfig", arg0.toMap() );
                } catch ( Exception ex ) {
//                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] Error ", ex.getMessage() );
                }
            }
        } );
        thread.run();
        return new CheckAuthLocationConfigRes().build( FrontManager.processTransaction( arg0 ) );
//        return new CheckAuthLocationConfigRes().mock();
    }

    public BalanceInquiryRes balanceInquiry( final BalanceInquiryRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT BALANCE INQUIRY ",null );

        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                    EmailManager.sendTheEmail( "BalanceInquiry", arg0.toMap() );
                } catch ( Exception ex ) {
//                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] Error ", ex.getMessage() );
                }
            }
        } );
        thread.run();
        return new BalanceInquiryRes().build( FrontManager.processTransaction( arg0 ) );
//        return new BalanceInquiryRes().mock();
    }

    public CardToBankRes cardToBank( final CardToBankRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CARD TO BANK ",null );

        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                    EmailManager.sendTheEmail( "CardToBank", arg0.toMap() );
                } catch ( Exception ex ) {
//                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] Error ", ex.getMessage() );
                }
            }
        } );
        thread.run();
       return new CardToBankRes().build( FrontManager.processTransaction( arg0 ) );
//        return new CardToBankRes().mock();
    }
    
//    NEW WS
    
    public CardToBankConfirmationRes cardToBankConfirmation( final CardToBankConfirmationRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CARD TO BANK CONFIRMATION ",null );

        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                    EmailManager.sendTheEmail( "CardToBankConfirmation", arg0.toMap() );
                } catch ( Exception ex ) {
//                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] Error ", ex.getMessage() );
                }
            }
        } );
        thread.run();
        return new CardToBankConfirmationRes().build( FrontManager.processTransaction( arg0 ) );
//        return new CardToBankConfirmationRes().mock();
    }
// END
    private void sendcheckAuth( CheckAuthRequest arg0 ) throws Exception {
        long time = System.currentTimeMillis();

        String imageAsBase64 = "";
        Map<ParameterName, ImagePart> images = new HashMap<ParameterName, ImagePart>();
        if ( arg0.getBackTiff() != null && arg0.getBackTiff().length != 0 ) {
            writeImage( "BACK_TIFF", arg0.getBackTiff() );

            imageAsBase64 = Base64.encode( arg0.getBackTiff() );

            ImagePart backTiff = new ImagePart( imageAsBase64, ParameterName.BACK_TIFF.toString(), "compBackTiff" );
            images.put( ParameterName.BACK_TIFF, backTiff );
        }

        if ( arg0.getFrontTiff() != null && arg0.getFrontTiff().length != 0 ) {
            writeImage( "front_TIFF", arg0.getFrontTiff() );
            imageAsBase64 = Base64.encode( arg0.getFrontTiff() );

            ImagePart frontTiff = new ImagePart( imageAsBase64, ParameterName.FRONT_TIFF.toString(), "compfrontTiff" );
            images.put( ParameterName.FRONT_TIFF, frontTiff );
        }

        if ( arg0.getCheckBack() != null && arg0.getCheckBack().length != 0 ) {
            writeImage( "CHECK_BACK", arg0.getCheckBack() );
            imageAsBase64 = Base64.encode( arg0.getCheckBack() );

            ImagePart checkBack = new ImagePart( imageAsBase64, ParameterName.CHECK_BACK.toString(), "compCheckBack" );
            images.put( ParameterName.CHECK_BACK, checkBack );
        }

        if ( arg0.getCheckFront() != null && arg0.getCheckFront().length != 0 ) {
            writeImage( "CHECK_FRONT", arg0.getCheckFront() );
            imageAsBase64 = Base64.encode( arg0.getCheckFront() );

            ImagePart checkFront = new ImagePart( imageAsBase64, ParameterName.CHECK_FRONT.toString(), "checkFrontComp" );
            images.put( ParameterName.CHECK_FRONT, checkFront );
        }

        if ( arg0.getIdBack() != null && arg0.getIdBack().length != 0 ) {
            writeImage( "ID_BACK", arg0.getIdBack() );
            imageAsBase64 = Base64.encode( arg0.getIdBack() );

            ImagePart idBack = new ImagePart( imageAsBase64, ParameterName.IDBACK.toString(), "compIdBack" );
            images.put( ParameterName.IDBACK, idBack );
        }

        if ( arg0.getIdFront() != null && arg0.getIdFront().length != 0 ) {
            writeImage( "ID_FRONT", arg0.getIdFront() );
            imageAsBase64 = Base64.encode( arg0.getIdFront() );

            ImagePart idFront = new ImagePart( imageAsBase64, ParameterName.IDFRONT.toString(), "checkIdComp" );
            images.put( ParameterName.IDFRONT, idFront );
        }

        if ( arg0.getAddressForm() != null && arg0.getAddressForm().length != 0 ) {
            writeImage( "ADDRESS_FORM", arg0.getAddressForm() );
             imageAsBase64 = Base64.encode( arg0.getAddressForm() );

            ImagePart addressForm = new ImagePart( imageAsBase64, ParameterName.ADDRESS_FORM.toString(), "addressForm" );
            images.put( ParameterName.ADDRESS_FORM, addressForm );
        }

//        System.out.println( "time :: " + ( System.currentTimeMillis() - time ) );
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] time :: " + ( System.currentTimeMillis() - time ), null );

        EmailManager.sendTheEmail( images, "CheckAuth", arg0.toMap() );
    }

    private void createFolders() {
        File folder = new File( "c:/Girocheck_Output/" );

        if ( !folder.exists() ) {
            folder.mkdirs();
        }
        File jpg = new File( folder, "jpg" );
        if ( !jpg.exists() ) {
            jpg.mkdir();
        }
        File b64 = new File( folder, "base64" );
        if ( !b64.exists() ) {
            b64.mkdir();
        }

    }

    private void writeInputData( String method, Map map ) throws IOException {
        File file = new File( "c:/Girocheck_Output/" + method + "_InputData.txt" );
        PrintStream fop = new PrintStream( file );

        if ( !file.exists() ) {
            file.createNewFile();
        }

        for ( Object key : map.keySet() ) {
            if ( key instanceof ParameterName ) {
                String a = key.toString() + " :: " + map.get( key );
                fop.println( a );
            }
        }
        fop.flush();
        fop.close();
    }

    private void writeImage( String name, byte[] image ) throws IOException {
        write( name, "tiff", image );
        writeBase64( name, image );
//        write(  name, "tiff",image );
    }

    private void write( String name, String subFolder, byte[] image ) throws IOException {
         File a = new File( "c:/Girocheck_Output" );
          a.mkdir();
        
//        File fileJPG = new File( "c:/Girocheck_Output/" + subFolder + "/" + name + "." + subFolder );
        File fileJPG = new File( a,"ale.jpg" );
        if ( !fileJPG.exists() ) {
            fileJPG.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream( fileJPG );
        fop.write( image );
        fop.flush();
        fop.close();
    }

    private void writeBase64( String name, byte[] image ) throws IOException {
        String imageAsBase64 = Base64.encode( image );

        File fileJPG = new File( "c:/Girocheck_Output/base64/" + name + ".txt" );
        if ( !fileJPG.exists() ) {
            fileJPG.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream( fileJPG );
        fop.write( imageAsBase64.getBytes() );
        fop.flush();
        fop.close();
    }

    private void sendConfirmation( TecnicardConfirmationRequest arg0 ) throws Exception {
        String imageAsBase64 = "";
        Map<ParameterName, ImagePart> images = new HashMap<ParameterName, ImagePart>();

//        if ( arg0.getAchForm() != null && arg0.getAchForm().length != 0 ) {
//            imageAsBase64 = Base64.encode( arg0.getAchForm() );
//
//            ImagePart achForm = new ImagePart( imageAsBase64, ParameterName.ACH_FORM.toString(), "achForm" );
//            images.put( ParameterName.ACH_FORM, achForm );
//        }

        if ( arg0.getTruncatedCheck() != null && arg0.getTruncatedCheck().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getTruncatedCheck() );

            ImagePart truncatedCheck = new ImagePart( imageAsBase64, ParameterName.TRUNCATED_CHECK.toString(), "truncatedCheck" );
            images.put( ParameterName.TRUNCATED_CHECK, truncatedCheck );
        }

        EmailManager.sendTheEmail( images, "tecnicardConfirmation", arg0.toMap() );
    }

    private void sendCardReload( CardReloadRequest arg0 ) throws Exception {
        String imageAsBase64 = "";
        Map<ParameterName, ImagePart> images = new HashMap<ParameterName, ImagePart>();

        if ( arg0.getBackTiff() != null && arg0.getBackTiff().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getBackTiff() );

            ImagePart backTiff = new ImagePart( imageAsBase64, ParameterName.BACK_TIFF.toString(), "compBackTiff" );
            images.put( ParameterName.BACK_TIFF, backTiff );
        }

        if ( arg0.getFrontTiff() != null && arg0.getFrontTiff().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getFrontTiff() );

            ImagePart frontTiff = new ImagePart( imageAsBase64, ParameterName.FRONT_TIFF.toString(), "compfrontTiff" );
            images.put( ParameterName.FRONT_TIFF, frontTiff );
        }

        if ( arg0.getCheckBack() != null && arg0.getCheckBack().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getCheckBack() );

            ImagePart checkBack = new ImagePart( imageAsBase64, ParameterName.CHECK_BACK.toString(), "compCheckBack" );
            images.put( ParameterName.CHECK_BACK, checkBack );
        }

        if ( arg0.getCheckFront() != null && arg0.getCheckFront().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getCheckFront() );

            ImagePart checkFront = new ImagePart( imageAsBase64, ParameterName.CHECK_FRONT.toString(), "checkFrontComp" );
            images.put( ParameterName.CHECK_FRONT, checkFront );
        }

        if ( arg0.getIdBack() != null && arg0.getIdBack().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getIdBack() );

            ImagePart idBack = new ImagePart( imageAsBase64, ParameterName.IDBACK.toString(), "compIdBack" );
            images.put( ParameterName.IDBACK, idBack );
        }

        if ( arg0.getIdFront() != null && arg0.getIdFront().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getIdFront() );

            ImagePart idFront = new ImagePart( imageAsBase64, ParameterName.IDFRONT.toString(), "checkIdComp" );
            images.put( ParameterName.IDFRONT, idFront );
        }

        EmailManager.sendTheEmail( images, "CardReload", arg0.toMap() );

    }
    private void sendCardReloadData( CardReloadDataRequest arg0 ) throws Exception {
        String imageAsBase64 = "";
        Map<ParameterName, ImagePart> images = new HashMap<ParameterName, ImagePart>();

        if ( arg0.getBackTiff() != null && arg0.getBackTiff().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getBackTiff() );
//            imageAsBase64 = encoder.encodeToString(arg0.getBackTiff() );

            ImagePart backTiff = new ImagePart( imageAsBase64, ParameterName.BACK_TIFF.toString(), "compBackTiff" );
            images.put( ParameterName.BACK_TIFF, backTiff );
        }

        if ( arg0.getFrontTiff() != null && arg0.getFrontTiff().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getFrontTiff() );
//            imageAsBase64 = encoder.encodeToString(arg0.getFrontTiff() );

            ImagePart frontTiff = new ImagePart( imageAsBase64, ParameterName.FRONT_TIFF.toString(), "compfrontTiff" );
            images.put( ParameterName.FRONT_TIFF, frontTiff );
        }

        if ( arg0.getCheckBack() != null && arg0.getCheckBack().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getCheckBack() );
//            imageAsBase64 = encoder.encodeToString(arg0.getCheckBack() );

            ImagePart checkBack = new ImagePart( imageAsBase64, ParameterName.CHECK_BACK.toString(), "compCheckBack" );
            images.put( ParameterName.CHECK_BACK, checkBack );
        }

        if ( arg0.getCheckFront() != null && arg0.getCheckFront().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getCheckFront() );
//            imageAsBase64 = encoder.encodeToString(arg0.getCheckFront() );

            ImagePart checkFront = new ImagePart( imageAsBase64, ParameterName.CHECK_FRONT.toString(), "checkFrontComp" );
            images.put( ParameterName.CHECK_FRONT, checkFront );
        }

        EmailManager.sendTheEmail( images, "CardReloadData", arg0.toMap() );

    }
}
