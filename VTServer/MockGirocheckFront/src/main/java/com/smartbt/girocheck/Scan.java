/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck;

import com.smartbt.girocheck.scan.BalanceInquiryRequest;
import com.smartbt.girocheck.scan.BalanceInquiryRes;
import com.smartbt.girocheck.scan.CardReloadRequest;
import com.smartbt.girocheck.scan.CardReloadRes;
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
import com.smartbt.vtsuite.manager.MockManager;
import com.smartbt.girocheck.servercommon.email.ImagePart;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

/*
 *  @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
@WebService( serviceName = "Scan", portName = "ScanPort", endpointInterface = "com.smartbt.girocheck.scan.Scan", targetNamespace = "http://scan.girocheck.smartbt.com/", wsdlLocation = "WEB-INF/wsdl/Scan.wsdl" )
public class Scan {

    public CheckAuthRes checkAuth( final CheckAuthRequest arg0 ) throws Exception {

        long time = System.currentTimeMillis();

        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                    sendcheckAuth( arg0 );
                } catch ( Exception ex ) {
                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        thread.run();
        System.out.println( "time :: " + ( System.currentTimeMillis() - time ) );
        return new CheckAuthRes().mock();
    }

    private void sendcheckAuth( CheckAuthRequest arg0 ) throws Exception {
        long time = System.currentTimeMillis();

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

        if ( arg0.getAddressForm() != null && arg0.getAddressForm().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getAddressForm() );

            ImagePart addressForm = new ImagePart( imageAsBase64, ParameterName.ADDRESS_FORM.toString(), "addressFormComp" );
            images.put( ParameterName.ADDRESS_FORM, addressForm );
        }

        System.out.println( "time :: " + ( System.currentTimeMillis() - time ) );

        MockManager.sendTheEmail( images, "CheckAuth", arg0.toMap() );
    }

    public TecnicardConfirmationRes tecnicardConfirmation( final TecnicardConfirmationRequest arg0 ) throws Exception {
        long time = System.currentTimeMillis();

        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                    sendConfirmation( arg0 );
                } catch ( Exception ex ) {
                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        thread.run();
        System.out.println( "time :: " + ( System.currentTimeMillis() - time ) );
        return new TecnicardConfirmationRes().mock();
    }
    
    private void sendConfirmation( TecnicardConfirmationRequest arg0 ) throws Exception{
         String imageAsBase64 = "";
        Map<ParameterName, ImagePart> images = new HashMap<ParameterName, ImagePart>();

        if ( arg0.getAchForm() != null && arg0.getAchForm().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getAchForm() );

            ImagePart achForm = new ImagePart( imageAsBase64, ParameterName.ACH_FORM.toString(), "achForm" );
            images.put( ParameterName.ACH_FORM, achForm );
        }

        if ( arg0.getTruncatedCheck() != null && arg0.getTruncatedCheck().length != 0 ) {
            imageAsBase64 = Base64.encode( arg0.getTruncatedCheck() );

            ImagePart truncatedCheck = new ImagePart( imageAsBase64, ParameterName.TRUNCATED_CHECK.toString(), "truncatedCheck" );
            images.put( ParameterName.TRUNCATED_CHECK, truncatedCheck );
        }

        MockManager.sendTheEmail( images, "tecnicardConfirmation", arg0.toMap() );
    }
    

    public CardReloadRes cardReload(final CardReloadRequest arg0 ) throws Exception {

        long time = System.currentTimeMillis();

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
        System.out.println( "time :: " + ( System.currentTimeMillis() - time ) );
        return new CardReloadRes().mock();
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

        MockManager.sendTheEmail( images, "CardReload", arg0.toMap() );

    }

    public CheckAuthSubmitRes checkAuthSubmit( final CheckAuthSubmitRequest arg0 ) throws Exception {
        
        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                  MockManager.sendTheEmail( "CheckAuthSubmit", arg0.toMap() );
                } catch ( Exception ex ) {
                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        thread.run();
        
        return new CheckAuthSubmitRes().mock();
    }

    public CheckAuthLocationConfigRes checkAuthLocationConfig(final CheckAuthLocationConfigRequest arg0 ) throws Exception {
         Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                  MockManager.sendTheEmail( "CheckAuthLocationConfig", arg0.toMap() );
                } catch ( Exception ex ) {
                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        thread.run();
        
        return new CheckAuthLocationConfigRes().mock();
    }

    public BalanceInquiryRes balanceInquiry(final BalanceInquiryRequest arg0 ) throws Exception {

        Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                          MockManager.sendTheEmail( "BalanceInquiry", arg0.toMap() );
                } catch ( Exception ex ) {
                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        thread.run();
        return new BalanceInquiryRes().mock();
    }

    public CardToBankRes cardToBank(final CardToBankRequest arg0 ) throws Exception {
    
         Thread thread = new Thread( new Runnable() {

            @Override
            public void run() {
                try {
                            MockManager.sendTheEmail( "CardToBank", arg0.toMap() );
                } catch ( Exception ex ) {
                    Logger.getLogger( Scan.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        thread.run();
        return new CardToBankRes().mock();
    }

}
