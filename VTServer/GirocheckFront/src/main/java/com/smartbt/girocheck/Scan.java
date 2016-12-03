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
        System.out.println("activityReport... >>> Receiving from terminal");
        System.out.println("arg0.getStartDate() = " + arg0.getStartDate());
        System.out.println("arg0.getEndDate() = " + arg0.getEndDate());
        Map map = FrontManager.activityReport(arg0);
        
         
        
      //  System.out.println("map.size() = " + map.keySet().size());
     //   System.out.println(Arrays.toString(map.entrySet().toArray()));
        return new ActivityReportRes().build(map );
    }


//    public ActivityReportRes activityReport( final ActivityReportRequest arg0 ) throws Exception { 
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[GCHFront Scan] ActivityReport",null );
//        System.out.println("TerminalId = " + arg0.getTerminalId());
//        System.out.println("StartDate = " + arg0.getStartDate());
//        System.out.println("EndDate = " + arg0.getEndDate());
//        ActivityReportRes res = new ActivityReportRes();
//        
//        Transaction checkTransaction1 = new Transaction("check2card", new Date(), 12.56345D);
//        Transaction checkTransaction2 = new Transaction("check2card", new Date(), 23.5663);
//        List<Transaction> checkTransactionList = new ArrayList<>();
//        checkTransactionList.add(checkTransaction1);
//        checkTransactionList.add(checkTransaction2);
//        
//        Transaction cashTransaction1 = new Transaction("cash2card", new Date(), 15.27457D);
//        Transaction cashTransaction2 = new Transaction("cash2card", new Date(), 25.85873);
//        List<Transaction> cashTransactionList = new ArrayList<>();
//        cashTransactionList.add(cashTransaction1);
//        cashTransactionList.add(cashTransaction2);
//        
//        Transaction cardTransaction1 = new Transaction("card2merchant", new Date(), 10.8335D);
//        Transaction cardTransaction2 = new Transaction("card2merchant", new Date(), 27.1575);
//        List<Transaction> cardTransactionList = new ArrayList<>();
//        cardTransactionList.add(cardTransaction1);
//        cardTransactionList.add(cardTransaction2);
//        
//        Transactions checkToCardTransactions = new Transactions( checkTransactionList );
//        Transactions cashToCardTransactions = new Transactions( cashTransactionList );
//        Transactions cardToMerchantTransactions = new Transactions( cardTransactionList );
//        
//        res.setCheck2cardTransactions(checkToCardTransactions);
//        res.setCash2cardTransactions(cashToCardTransactions);
//        res.setCard2merchantTransactions(cardToMerchantTransactions);
//        
//        res.setCheck2cardCount(checkToCardTransactions.getTransaction().size());
//        res.setCash2cardCount(cashToCardTransactions.getTransaction().size());
//        res.setCard2merchantCount(cardToMerchantTransactions.getTransaction().size());
//        
//        res.setCheck2cardTotal(sum(checkToCardTransactions.getTransaction()));
//        res.setCash2cardTotal(sum(cashToCardTransactions.getTransaction()));
//        res.setCard2merchantTotal(sum(cardToMerchantTransactions.getTransaction()));
//        
//        res.setCashIn(res.getCheck2cardTotal() + res.getCash2cardTotal());
//        res.setCashOut(res.getCard2merchantTotal());
//        res.setNetCash(res.getCashIn()- res.getCashOut());
//        
//        res.setSuccess(true);
//        res.setTotalRows(res.getCheck2cardCount() + res.getCash2cardCount() + res.getCard2merchantCount());
//        return res;
//
//    }
    
    private Double sum(List<Transaction> list){
        Double sum = 0D;
        
        for (Transaction t : list) {
            sum += t.getAmount();
        }
        return sum;
    }
    
    public static void main(String[] args){
     Date date = new Date();
        System.out.println(dateToISOFormat(new Date()));
    }
    
     public static String dateToISOFormat(Date date) {
        if (date == null) {
            return "";
        }
        try {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
            df.setTimeZone(tz);
            return df.format(new Date());
        } catch (Exception e) {
            return date.toString();
        }

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
         
        Map requestMap = arg0.toMap();
        
         Iterator itReq = requestMap.keySet().iterator();
        
        while (itReq.hasNext()) {
            Object key = itReq.next();
             CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[GCHFront CheckAuthLocationConfig] " + key + " -> " + requestMap.get(key),null );
        }
        
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
