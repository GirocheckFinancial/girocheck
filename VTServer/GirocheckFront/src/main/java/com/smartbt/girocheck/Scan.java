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
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.manager.FrontManager;
import javax.jws.WebService;

/**
 *
 * @author Roberto Rodriguez :: <Roberto.Rodriguez@smartbt.com>
 */
@WebService( serviceName = "Scan", portName = "ScanPort", endpointInterface = "com.smartbt.girocheck.scan.Scan", targetNamespace = "http://scan.girocheck.smartbt.com/", wsdlLocation = "WEB-INF/wsdl/Scan.wsdl" )
public class Scan {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Scan.class);

    public CheckAuthRes checkAuth( final CheckAuthRequest arg0 ) throws Exception { 
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] After sendcheckAuth",null );
        return new CheckAuthRes().build( FrontManager.processTransaction( arg0 ) );
 
    }

    public TecnicardConfirmationRes tecnicardConfirmation( final TecnicardConfirmationRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT TECNICARD CONFIRMATION ",null );
       
        return new TecnicardConfirmationRes().build( FrontManager.processTransaction( arg0 ) );
 
    }

    public CardReloadRes cardReload( final CardReloadRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CARD RELOAD ",null );
       
        return new CardReloadRes().build( FrontManager.processTransaction( arg0 ) );
 
    }
    public CardReloadDataRes cardReloadData( final CardReloadDataRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CARD RELOAD DATA",null );
        
        return new CardReloadDataRes().build( FrontManager.processTransaction( arg0 ) );
 
    }

    public CheckAuthSubmitRes checkAuthSubmit( CheckAuthSubmitRequest arg0 ) throws Exception {
//        return new CheckAuthSubmitRes().build( FrontManager.processTransaction( arg0 ) );  made for the cancel functionality that is not used.
        return new CheckAuthSubmitRes().mock();
    }

    public CheckAuthLocationConfigRes checkAuthLocationConfig( final CheckAuthLocationConfigRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CHECK AUTH LOCATION CONFIG",null );
         
        return new CheckAuthLocationConfigRes().build( FrontManager.processTransaction( arg0 ) );
 
    }

    public BalanceInquiryRes balanceInquiry( final BalanceInquiryRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT BALANCE INQUIRY ",null );
 
        return new BalanceInquiryRes().build( FrontManager.processTransaction( arg0 ) );
 
    }

    public CardToBankRes cardToBank( final CardToBankRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CARD TO BANK ",null );
 
       return new CardToBankRes().build( FrontManager.processTransaction( arg0 ) );
 
    }
    
//    NEW WS
    
    public CardToBankConfirmationRes cardToBankConfirmation( final CardToBankConfirmationRequest arg0 ) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] FRONT CARD TO BANK CONFIRMATION ",null );
 
        return new CardToBankConfirmationRes().build( FrontManager.processTransaction( arg0 ) );
 
    }
// END
    
 
//    private void writeBase64( String name, byte[] image ) throws IOException {
//        String imageAsBase64 = Base64.encode( image );
//
//        File fileJPG = new File( "c:/Girocheck_Output/base64/" + name + ".txt" );
//        if ( !fileJPG.exists() ) {
//            fileJPG.createNewFile();
//        }
//        FileOutputStream fop = new FileOutputStream( fileJPG );
//        fop.write( imageAsBase64.getBytes() );
//        fop.flush();
//        fop.close();
//    }
 
}
