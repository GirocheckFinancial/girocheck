/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileJPG, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck;

import com.smartbt.girocheck.scan.ActivityReportRequest;
import com.smartbt.girocheck.scan.ActivityReportRes;
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
import com.smartbt.girocheck.scan.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.manager.FrontManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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

    public ActivityReportRes activityReport( final ActivityReportRequest arg0 ) throws Exception { 
        System.out.println("-----activityReport... >>> Receiving from terminal");
        System.out.println("arg0.getStartDate() = " + arg0.getStartDate());
        System.out.println("arg0.getEndDate() = " + arg0.getEndDate());
        Map map = FrontManager.activityReport(arg0);
        
        return new ActivityReportRes().build(map );
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
          
        Map map = FrontManager.processTransaction( arg0 );
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] Response got to Scan",null );
        
        Iterator it = map.keySet().iterator();
        
        while (it.hasNext()) {
            Object key = it.next();
             CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront Scan] " + key + " -> " + map.get(key),null );
        }
        
        return new CheckAuthLocationConfigRes().build( map );
 
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
