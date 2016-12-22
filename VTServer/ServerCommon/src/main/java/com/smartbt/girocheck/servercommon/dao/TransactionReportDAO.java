/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.TransactionDisplay;
import com.smartbt.girocheck.servercommon.display.TransactionReportDisplay;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.display.MerchantDisplay;
import com.smartbt.girocheck.servercommon.display.TerminalDisplay;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.ReportFilters;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.ClientTransactionType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.hibernate.Session;

/**
 *
 * @author Alejo
 */
public class TransactionReportDAO extends BaseDAO<Transaction>{
        protected static TransactionReportDAO dao;
        ObjectMapper objectMapper = new ObjectMapper();

    public TransactionReportDAO() {
    }

    public static TransactionReportDAO get() {
        if ( dao == null ) {
            dao = new TransactionReportDAO();
        }
        return dao;
    }
    
    public int saveReportFilters(ReportFilters filters){
        System.out.println("saveReportFilters()");
        String token = Utils.generateToken();
        filters.setCreateAt(new Date());
        Session session = HibernateUtil.getSession();
        session.saveOrUpdate( filters );
        session.flush();

        return filters.getId();
    }
    
    public ReportFilters getReportFilters(int id){
//        System.out.println("getReportFilters()");
        Criteria cri = HibernateUtil.getSession().createCriteria( ReportFilters.class )
                .add( Restrictions.eq( "id", id ) );
        
        ReportFilters filters = (ReportFilters)cri.uniqueResult();
        
        Date createdAt = filters.getCreateAt();
        
        if(createdAt == null) {
           createdAt = new Date();
        }
        
        long created = createdAt.getTime() + 180000;//3 min
        long actualTime = new Date().getTime();
        
        System.out.println(" [TransactionReportDAO] ACTUAL TIME: "+ actualTime +" CREATED TIME: " + created);
        
        if(actualTime > created){
            return null;
        } 
        
        Session session = HibernateUtil.getSession();
            session.delete(filters);
            session.flush();
        
        return filters;
        
    }
    
    public List<TransactionReportDisplay> searchTransactionReport( Date startRangeDate, Date endRangeDate, String amount ) {
        Criteria cri = HibernateUtil.getSession().createCriteria( Transaction.class )
                .createAlias( "terminal", "terminal", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "terminal.merchant", "merchant", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "merchant.agrupation", "agrupation", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "data_sc1", "creaditCard", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "client", "client", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "check", "check", JoinType.LEFT_OUTER_JOIN )
                .add( Restrictions.eq( "transactionFinished", true ) )
                .addOrder( Order.desc( "dateTime" ) );


        if ( startRangeDate != null ) {
            cri.add( Restrictions.ge( "dateTime", startRangeDate ) );
        }
        if ( endRangeDate != null ) {
            endRangeDate.setHours( 24 );
            cri.add( Restrictions.le( "dateTime", endRangeDate ) );
        }

        if (amount != null) {
            cri.add(Restrictions.eq("ammount", amount));
        }
        
        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "transactionType" ).as( "transactionType" ) )
                .add( Projections.property( "dateTime" ).as( "createdAt" ) )
                .add( Projections.property( "operation" ).as( "operation" ) )
                .add( Projections.property( "creditCard.maskCardNumber" ).as( "maskCardNumber" ) )
                .add( Projections.property( "ammount" ).as( "amount" ) )
                .add( Projections.property( "feeAmmount" ).as( "feeAmmount" ) )
                .add( Projections.property( "payoutAmmount" ).as( "payoutAmmount" ) )
                .add( Projections.property( "single" ).as( "single" ) )
                .add( Projections.property( "resultCode" ).as( "resultCode" ) )
                .add( Projections.property( "resultMessage" ).as( "resultMessage" ) )
                .add( Projections.property( "merchant.legalName" ).as( "merchant" ) )
                .add( Projections.property( "terminal.serialNumber" ).as( "terminal" ) )
                .add( Projections.property( "check.paymentCheck" ).as( "checkNumber" ) )
                .add( Projections.property( "check.makerName" ).as( "makerName" ) )
                .add( Projections.property( "client.firstName" ).as( "clientFirstName" ) )
                .add( Projections.property( "client.lastName" ).as( "clientLastName" ) );
        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( TransactionReportDisplay.class ) );
    
        return cri.list();
    }
    
    /*****************************************************************************************/
    /*
    /*
    */
    
//    public ReportDisplay generateReport( String username, Long idEntity, EntityType entityType, Long idClerk, Long idTransactionMode,
//            Date startRangeDate, Date endRangeDate, boolean justApproved, ReportType reportType, long applicationType ) throws Exception {
//        String token = Utils.generateToken();
//        ReportDisplay report = new ReportDisplay();
//        Report reportDb = new Report();
//        List<TransactionDisplay> transactionsReport = null;
//        String url;
//
//        switch ( reportType ) {
//            case DETAILS:
//                transactionsReport = detailsReport( idEntity, entityType, idClerk, idTransactionMode, startRangeDate, endRangeDate, -1, -1, justApproved, applicationType );
//                break;
//            case TOTALS:
//                //transactionsReport = terminalStats(idEntity, entityType, -1, -1, justApproved, applicationType);
//                transactionsReport = totalsReport( idEntity, entityType, idClerk, idTransactionMode, startRangeDate, endRangeDate, -1, -1, justApproved, applicationType );
//                break;
////            case CLERK:
////                //transactionsReport = customerReport(idEntity, startRangeDate, endRangeDate, -1, -1, justApproved, applicationType);
////                //transactionsReport = cardBrandReport( idEntity, entityType, idClerk, idTransactionMode, "TEST", startRangeDate, endRangeDate, -1, -1, justApproved, applicationType );
////                transactionsReport = clerkReport( idEntity, entityType, idClerk, idTransactionMode, startRangeDate, endRangeDate, -1, -1, justApproved, applicationType );
////                break;
////            case PRE_AUTH_TRANSACTIONS:
////                transactionsReport = preAuthTransactionsReport( idEntity, entityType, idClerk, idTransactionMode, startRangeDate, endRangeDate, -1, -1, justApproved, applicationType );
////                break;
//
//        }
//        String reportData = objectMapper.writeValueAsString( transactionsReport );
//        reportDb.setReport( reportData );
////        reportDb.setKey( username + token );
//
//        if ( !reportData.equalsIgnoreCase( "[]" ) ) {
//            Session session = HibernateUtil.getSession();
//            session.saveOrUpdate( reportDb );
//            session.flush();
//            url = "/Reporting/index.jsp?reportId=" + reportDb.getId() + "&token=" + token;
//
//        } else {
//            url = "/Reporting/index.jsp?reportId=" + 0 + "&token=" + token;
//        }
//        report.setUrl( url );
//        return report;
//    }

//    public ReportDisplay getReport( String username, String token, Long reportId ) throws Exception {
//        Report reportDb = (Report) HibernateUtil.getSession().get( Report.class, reportId );
//        ReportDisplay report = new ReportDisplay();
//        report.setReport( reportDb.getReport() );
//
//        if ( reportDb.getKey().equals( username + token ) ) {
//            return report;
//        } else {
//            throw new Exception( "User not authorized to querry this report with corresponding token" );
//        }
//    }
    
    public String getReport( String startRangeDate, String endRangeDate, String amount, String reportType, int firstResult, int maxResult, int idEntity,EntityType entityType ) throws Exception {
//        System.out.println("[ServerCommon] TransactionReportDAO.getReport()");
        List<TransactionReportDisplay> transactionsReport = null;
//        if (reportType.equalsIgnoreCase("Details")) {

            transactionsReport = detailsReport(DateUtils.getDateByString(startRangeDate), DateUtils.getDateByString(endRangeDate), amount,firstResult, maxResult, idEntity, entityType);

//        } else if (reportType.equalsIgnoreCase("Totals")) {
//            
//            transactionsReport = totalsReport(DateUtils.getDateByString(startRangeDate), DateUtils.getDateByString(endRangeDate), amount);
//
//        }else{
//            System.out.println("[TransactionReportDAO] Report Type is not correct, value: "+reportType);
//        }


        
//       List<TransactionReportDisplay> transactionsReport = searchTransactionReport(DateUtils.getDateByString(startRangeDate),DateUtils.getDateByString(endRangeDate),amount);
       return objectMapper.writeValueAsString( transactionsReport );
       
    }

//    public List<TransactionDisplay> detailsReport( Long idEntity, EntityType entityType, Long idClerk, Long idTransactionMode, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, boolean justApproved, long applicationType ) {
    public List<TransactionReportDisplay> detailsReport( Date startRangeDate, Date endRangeDate, String amount, int firstResult, int maxResult, int idEntity,EntityType entityType ) {

        Criteria cri = HibernateUtil.getSession().createCriteria( Transaction.class )
                .createAlias( "client", "client", JoinType.LEFT_OUTER_JOIN )
//                .createAlias( "check", "check", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "terminal", "terminal", JoinType.LEFT_OUTER_JOIN)
                .createAlias( "terminal.merchant", "merchant", JoinType.LEFT_OUTER_JOIN)
//                .createAlias( "merchant.agrupation", "customer", JoinType.LEFT_OUTER_JOIN)
//                .createAlias("merchant.address", "merchantAddress", JoinType.LEFT_OUTER_JOIN)
//                .createAlias("merchantAddress.state", "merchantState", JoinType.LEFT_OUTER_JOIN)
//                .createAlias("client.address", "clientAddress", JoinType.LEFT_OUTER_JOIN)
//                .createAlias("clientAddress.state", "clientState", JoinType.LEFT_OUTER_JOIN)
                .createAlias( "data_sc1", "creditCard" )
//                .add( Restrictions.eq( "transactionFinished", true ) )
                .addOrder( Order.asc("dateTime" ) );


//        if ( firstResult >= 0 ) {
//            cri.setFirstResult( firstResult );
//            cri.setMaxResults( maxResult );
//        }

        if ( startRangeDate != null ) {
            cri.add( Restrictions.ge( "dateTime", startRangeDate ) );
        }
        if ( endRangeDate != null ) {
            endRangeDate.setHours( 24 );
            cri.add( Restrictions.le( "dateTime", endRangeDate ) );
        }
        if ( amount != null ) {
            cri.add( Restrictions.eq( "ammount", Double.parseDouble( amount ) ) );
        }


        ProjectionList projectionList = Projections.projectionList()
                //transaction data
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "client.id" ).as( "client.id" ) )
                .add( Projections.property( "terminal.id" ).as( "terminal.id" ) )
                .add( Projections.property( "merchant.id" ).as( "merchant.id" ) )
                .add( Projections.property( "creditCard.id" ).as( "card.id" ) )
                .add( Projections.property( "operation" ).as( "operation" ) )
                .add( Projections.property( "requestId" ).as( "requestId" ) )
                .add( Projections.property( "creditCard.maskCardNumber" ).as( "maskCardNumber" ) )
                .add( Projections.property( "payoutAmmount" ).as( "payoutAmount" ) )
                .add( Projections.property( "ammount" ).as( "amount" ) )
                .add( Projections.property( "feeAmmount" ).as( "feeAmount" ) )
                .add( Projections.property( "dateTime" ).as( "dateTime" ) )
                .add( Projections.property( "resultCode" ).as( "resultCode" ) )
                .add( Projections.property( "transactionType" ).as( "transactionType" ) ) 
                .add( Projections.property( "resultMessage" ).as( "resultMessage" ) )
                //merchant data
                .add( Projections.property( "merchant.legalName" ).as( "merchant.legalName" ) )
                .add( Projections.property( "merchant.phone" ).as( "merchant.phone" ) )
                 .add( Projections.property( "merchant.sic" ).as( "merchant.sic" ) )
                .add( Projections.property( "merchant.description" ).as( "merchant.description" ) )
                .add( Projections.property( "merchant.idPosOrderExp" ).as( "merchant.idPosOrderExp" ) )
                .add( Projections.property( "merchant.idTellerOrderExp" ).as( "merchant.idTellerOrderExp" ) )
                .add( Projections.property( "merchant.oEAgentNumber" ).as( "merchant.oEAgentNumber" ) )
//                .add( Projections.property( "customer.name" ).as( "merchant.customerName" ) )
                //terminal data
                .add( Projections.property( "merchant.legalName" ).as( "terminal.merchantName" ) )
                .add( Projections.property( "terminal.serialNumber" ).as( "terminal.serialNumber" ) )
                .add( Projections.property( "terminal.description" ).as( "terminal.description" ) )
                //client data
                 .add( Projections.property( "client.firstName" ).as( "client.firstName" ) )
                .add( Projections.property( "client.lastName" ).as( "client.lastName" ) )
                .add( Projections.property( "client.telephone" ).as( "client.telephone" ) );
        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( TransactionReportDisplay.class ) );
        
        List<TransactionReportDisplay> transactionReportDisplays = cri.list();

        for (Iterator<TransactionReportDisplay> iterator = transactionReportDisplays.iterator(); iterator.hasNext();) {
            TransactionReportDisplay next = iterator.next();
            
            next.setType(ClientTransactionType.getTransactionName( next.getTransactionType()));
        }
        
        return transactionReportDisplays;
    }

//    public List<TransactionDisplay> totalsReport( Long idEntity, EntityType entityType, Long idClerk, Long idTransactionMode, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, boolean justApproved, Long applicationType ) {
    public List<TransactionReportDisplay> totalsReport( Date startRangeDate, Date endRangeDate, String amount ) {
//        System.out.println("[ServerCommon] TransactionReportDAO.totalsReport() Start...");
        List<TransactionReportDisplay> displays = new LinkedList<TransactionReportDisplay>();
        try{
        Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("terminal", "terminal", JoinType.LEFT_OUTER_JOIN)
                .createAlias("terminal.merchant", "merchant", JoinType.LEFT_OUTER_JOIN)  
                 .createAlias("data_sc1", "creditCard", JoinType.LEFT_OUTER_JOIN)
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN) 
                .add(Restrictions.eq("transactionFinished", true))
                .addOrder(Order.desc("dateTime"));

//        if ( firstResult >= 0 ) {
//            cri.setFirstResult( firstResult );
//            cri.setMaxResults( maxResult );
//        }

        if ( startRangeDate != null ) {
            cri.add( Restrictions.ge( "createdAt", startRangeDate ) );
        }
        if ( endRangeDate != null ) {
            endRangeDate.setHours( 24 );
            cri.add( Restrictions.le( "createdAt", endRangeDate ) );
        }
        if ( amount != null ) {
            cri.add( Restrictions.eq( "ammount", amount ) );
        }

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "operation" ).as( "operation" ) )
                .add( Projections.property( "creditCard.maskCardNumber" ).as( "maskCardNumber" ) )
                .add( Projections.property( "payoutAmmount" ).as( "payoutAmount" ) )
                .add( Projections.property( "transactionType" ).as( "transactionType" ) )
                .add( Projections.property( "ammount" ).as( "amount" ) )
                .add( Projections.property( "feeAmmount" ).as( "feeAmount" ) )
                .add( Projections.property( "client.firstName" ).as( "clientFristName" ) )
                .add( Projections.property( "client.lastName" ).as( "clientLastName" ) )
                .add( Projections.property( "terminal.serialNumber" ).as( "terminalSerialNumber" ) )
                .add( Projections.property( "dateTime" ).as( "dateTime" ) )
                .add( Projections.property( "merchant.legalName" ).as( "merchantLegalName" ) );
        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( TransactionReportDisplay.class ) );

        displays = cri.list();
        }catch(Exception e){
            e.printStackTrace();
        }
//        System.out.println("[ServerCommon] TransactionReportDAO.totalsReport() end. Transaction list size: "+displays.size());
        return displays;
    }

    
    public List<TransactionDisplay> customerReport( Long idMerchant, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, boolean justApproved, long applicationType ) {

        Criteria cri = HibernateUtil.getSession().createCriteria( Transaction.class )
                .createAlias( "client", "client", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "client.merchant", "merchant" )
                /*.createAlias("client.client_address", "client_address")
                 .createAlias("client_address.address", "address")
                 .createAlias("client.client_telephone", "client_telephone")*/
                // .add(Restrictions.eq("client.merchant_id", "merchant.id"))
                .addOrder( Order.desc( "ggLastUpdateByDt" ) );

        if ( idMerchant != 0 ) {
            cri.add( Restrictions.eq( "merchant.id", idMerchant ) );
        }

        if ( firstResult >= 0 ) {
            cri.setFirstResult( firstResult );
            cri.setMaxResults( maxResult );
        }

        if ( startRangeDate != null ) {
            cri.add( Restrictions.ge( "client.createdAt", startRangeDate ) );
        }
        if ( justApproved ) {
            cri.add( Restrictions.ilike( "disposition", "%APPROVED%" ) );
        }
        if ( endRangeDate != null ) {
            endRangeDate.setHours( 24 );
            cri.add( Restrictions.le( "client.createdAt", endRangeDate ) );
        }

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "client.firstName" ).as( "firstName" ) )
                .add( Projections.property( "client.middleInital" ).as( "middleInital" ) )
                .add( Projections.property( "client.lastName" ).as( "lastName" ) )
                .add( Projections.property( "client.company" ).as( "company" ) )
                .add( Projections.property( "client.email" ).as( "email" ) )
                .add( Projections.property( "client.emailReceipt" ).as( "emailReceipt" ) )
                .add( Projections.property( "client.active" ).as( "active" ) )
                .add( Projections.property( "client.createdAt" ).as( "createdAt" ) )
                .add( Projections.property( "merchant.name" ).as( "merchant.name" ) )
                .add( Projections.property( "merchant.number" ).as( "merchant.number" ) )
                .add( Projections.property( "merchant.id" ).as( "merchant.id" ) );
        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( ClientDisplay.class ) );

        return cri.list();
    }

    public List<TransactionDisplay> terminalStats( Long idEntity, EntityType entityType, int firstResult, int maxResult, boolean justApproved, long applicationType ) {

        Criteria cri = HibernateUtil.getSession().createCriteria( Terminal.class )
                .createAlias( "merchant", "merchant", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "merchant.customer", "customer" )
                .createAlias( "application", "application" )
                .createAlias( "application.productType", "productType" );

        if ( firstResult >= 0 ) {
            cri.setFirstResult( firstResult );
            cri.setMaxResults( maxResult );
        }

        switch ( entityType ) {
            case CUSTOMER:
                cri.add( Restrictions.eq( "customer.id", idEntity ) );
                break;
        }

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "terminalId" ).as( "terminalId" ) )
                .add( Projections.property( "merchant.name" ).as( "merchantName" ) )
                .add( Projections.property( "application.name" ).as( "application" ) )
                .add( Projections.property( "productType.name" ).as( "productType" ) )
                .add( Projections.property( "serialNumber" ).as( "serialNumber" ) )
                .add( Projections.property( "description" ).as( "description" ) )
                .add( Projections.property( "active" ).as( "active" ) );

        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( TerminalDisplay.class ) );

        return cri.list();
    }

    public List<TransactionDisplay> getEmptyReport( int idEntity, EntityType entityType, int idClerk ) {

        List<TransactionDisplay> transactionDisplayList = new ArrayList<TransactionDisplay>();

        Merchant merchant = null;
        Criteria criteria = HibernateUtil.getSession().createCriteria( Merchant.class );

        switch ( entityType ) {
//            case CUSTOMER:
//                criteria.createAlias("customer", "customer").
//                        add(Restrictions.eq("customer.id", idEntity));
//                criteria.add(Restrictions.eq("customer.id", idEntity));
//                break;
            case MERCHANT:
                merchant = MerchantDAO.get().findById( idEntity );
                break;
            case TERMINAL:
                merchant = (Merchant) criteria.uniqueResult();
                criteria.createAlias( "terminal", "terminal" ).
                        add( Restrictions.eq( "terminal.id", idEntity ) );
                break;
        }

        Collection<Terminal> terminalList = merchant.getTerminal();

        // For each Terminal we need to build a TransactionDisplay object
        Long transactionCounter = (long) 0;

        for ( Iterator<Terminal> it = terminalList.iterator(); it.hasNext(); ) {
            Terminal terminal = it.next();
            transactionCounter++;

            TerminalDisplay terminalDisplay = new TerminalDisplay();
            terminalDisplay.setId( terminal.getId() );
            terminalDisplay.setId( terminal.getId());

            MerchantDisplay merchantDisplay = new MerchantDisplay();
            merchantDisplay.setId( merchant.getId() );
            merchantDisplay.setLegalName( merchant.getLegalName());

            TransactionDisplay transactionDisplay = new TransactionDisplay();
            transactionDisplay.setId( 0 );
            transactionDisplay.setMerchant( null );
            transactionDisplay.setTerminal( null );
            transactionDisplay.setPayoutAmmount(0.0);
            transactionDisplay.setFeeAmmount(0.0);
            transactionDisplay.setAmmount(0.0);
            transactionDisplay.setCreatedAt( new Date() );
            transactionDisplay.setOperation( "CASH" );
            transactionDisplay.setTransactionFinished( Boolean.FALSE );

            transactionDisplayList.add( transactionDisplay );
        }
        return transactionDisplayList;
    }
    
    public List<TransactionDisplay> cardBrandReport( Long idEntity, EntityType entityType, Long idClerk, Long idTransactionMode, String cardBrand, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, boolean justApproved, long applicationType ) {

        Criteria cri = HibernateUtil.getSession().createCriteria( Transaction.class )
                .createAlias( "client", "client", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "clerk", "clerk" )
                .createAlias( "terminal", "terminal" )
                .createAlias( "terminal.merchant", "merchant" )
                .createAlias( "merchant.customer", "customer" )
                .createAlias( "mode", "mode" )
                .createAlias( "operation", "operation" )
                .createAlias( "cardBrand", "cardBrand" )
                .add( Restrictions.eq( "voided", false ) )
                .addOrder( Order.desc( "ggLastUpdateByDt" ) );

        if ( idClerk != 0 ) {
            cri.add( Restrictions.eq( "clerk.id", idClerk ) );
        }
        if ( idTransactionMode != 0 ) {
            cri.add( Restrictions.eq( "mode.id", idTransactionMode ) );
        }

        if ( justApproved ) {
            cri.add( Restrictions.ilike( "disposition", "%APPROVED%" ) );
        }

        if ( firstResult >= 0 ) {
            cri.setFirstResult( firstResult );
            cri.setMaxResults( maxResult );
        }
        
        if ( cardBrand != null ) {
            cri.add( Restrictions.eq( "cardBrand.name", cardBrand ) );
        }
             
        if ( startRangeDate != null ) {
            cri.add( Restrictions.ge( "createdAt", startRangeDate ) );
        }
        if ( endRangeDate != null ) {
            endRangeDate.setHours( 24 );
            cri.add( Restrictions.le( "createdAt", endRangeDate ) );
        }
        switch ( entityType ) {
            case CUSTOMER:
                cri.add( Restrictions.eq( "customer.id", idEntity ) );
                break;
            case MERCHANT:
                cri.add( Restrictions.eq( "merchant.id", idEntity ) );
                break;
            case TERMINAL:
                cri.add( Restrictions.eq( "terminal.id", idEntity ) );
                break;
        }

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "mode.name" ).as( "mode" ) )
                .add( Projections.property( "operation.name" ).as( "operation" ) )
                .add( Projections.property( "cardBrand.name" ).as( "cardBrand" ) )
                .add( Projections.property( "subTotalAmount" ).as( "subTotalAmount" ) )
                .add( Projections.property( "totalAmount" ).as( "totalAmount" ) )
                .add( Projections.property( "tipAmount" ).as( "tipAmount" ) )
                .add( Projections.property( "taxAmount" ).as( "taxAmount" ) )
                .add( Projections.property( "client.id" ).as( "client.id" ) )
                .add( Projections.property( "clerk.id" ).as( "clerk.id" ) )
                .add( Projections.property( "clerk.username" ).as( "clerk.username" ) )
                .add( Projections.property( "terminal.id" ).as( "terminal.id" ) )
                .add( Projections.property( "terminal.terminalId" ).as( "terminal.terminalId" ) )
                .add( Projections.property( "cardHolderEmail" ).as( "cardHolderEmail" ) )
                .add( Projections.property( "geoLocation" ).as( "geoLocation" ) )
                .add( Projections.property( "disposition" ).as( "disposition" ) )
                .add( Projections.property( "sequence" ).as( "sequence" ) )
                .add( Projections.property( "approvalNumber" ).as( "approvalNumber" ) )
                .add( Projections.property( "approvalCode" ).as( "approvalCode" ) )
                .add( Projections.property( "accountSuffix" ).as( "accountSuffix" ) )
                .add( Projections.property( "voided" ).as( "voided" ) )
                .add( Projections.property( "poNumber" ).as( "poNumber" ) )
                .add( Projections.property( "invoiceNumber" ).as( "invoiceNumber" ) )
                .add( Projections.property( "createdAt" ).as( "createdAt" ) )
                .add( Projections.property( "finalized" ).as( "finalized" ) )
                .add( Projections.property( "hostCapture" ).as( "hostCapture" ) )
                .add( Projections.property( "retrievalData" ).as( "retrievalData" ) )
                .add( Projections.property( "batchNumber" ).as( "batchNumber" ) )
                .add( Projections.property( "cvvResult" ).as( "cvvResult" ) )
                .add( Projections.property( "entryMethod" ).as( "entryMethod" ) )
                .add( Projections.property( "idOriginalTransaction" ).as( "idOriginalTransaction" ) )
                .add( Projections.property( "merchant.name" ).as( "merchant.name" ) )
                .add( Projections.property( "merchant.number" ).as( "merchant.number" ) )
                .add( Projections.property( "merchant.id" ).as( "merchant.id" ) );
        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( TransactionDisplay.class ) );

        return cri.list();
    }

    public List<TransactionDisplay> preAuthTransactionsReport( Long idEntity, EntityType entityType, Long idClerk, Long idTransactionMode, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, boolean justApproved, Long applicationType ) {

        Criteria cri = HibernateUtil.getSession().createCriteria( Transaction.class )
                .createAlias( "client", "client", JoinType.LEFT_OUTER_JOIN )
                .createAlias( "clerk", "clerk" )
                .createAlias( "terminal", "terminal" )
                .createAlias( "terminal.merchant", "merchant" )
                .createAlias( "merchant.customer", "customer" )
                .createAlias( "mode", "mode" )
                .createAlias( "operation", "operation" )
                .createAlias( "cardBrand", "cardBrand" )
                .add( Restrictions.eq( "voided", false ) )
                .addOrder( Order.desc( "ggLastUpdateByDt" ) );

        if ( idClerk != 0 ) {
            cri.add( Restrictions.eq( "clerk.id", idClerk ) );
        }
        if ( idTransactionMode != 0 ) {
            cri.add( Restrictions.eq( "mode.id", idTransactionMode ) );
        }

        cri.add( Restrictions.like( "disposition", "%APPROVED%", MatchMode.ANYWHERE ) )
                .add( Restrictions.eq( "operation.id", NomOperation.GUARANTEE.getId() ) );

        if ( firstResult >= 0 ) {
            cri.setFirstResult( firstResult );
            cri.setMaxResults( maxResult );
        }

        if ( startRangeDate != null ) {
            cri.add( Restrictions.ge( "createdAt", startRangeDate ) );
        }
        if ( endRangeDate != null ) {
            endRangeDate.setHours( 24 );
            cri.add( Restrictions.le( "createdAt", endRangeDate ) );
        }
        switch ( entityType ) {
            case CUSTOMER:
                cri.add( Restrictions.eq( "customer.id", idEntity ) );
                break;
            case MERCHANT:
                cri.add( Restrictions.eq( "merchant.id", idEntity ) );
                break;
            case TERMINAL:
                cri.add( Restrictions.eq( "terminal.id", idEntity ) );
                break;
        }

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "mode.name" ).as( "mode" ) )
                .add( Projections.property( "operation.name" ).as( "operation" ) )
                .add( Projections.property( "cardBrand.name" ).as( "cardBrand" ) )
                .add( Projections.property( "subTotalAmount" ).as( "subTotalAmount" ) )
                .add( Projections.property( "totalAmount" ).as( "totalAmount" ) )
                .add( Projections.property( "tipAmount" ).as( "tipAmount" ) )
                .add( Projections.property( "taxAmount" ).as( "taxAmount" ) )
                .add( Projections.property( "client.id" ).as( "client.id" ) )
                .add( Projections.property( "clerk.id" ).as( "clerk.id" ) )
                .add( Projections.property( "clerk.username" ).as( "clerk.username" ) )
                .add( Projections.property( "terminal.id" ).as( "terminal.id" ) )
                .add( Projections.property( "terminal.terminalId" ).as( "terminal.terminalId" ) )
                .add( Projections.property( "cardHolderEmail" ).as( "cardHolderEmail" ) )
                .add( Projections.property( "geoLocation" ).as( "geoLocation" ) )
                .add( Projections.property( "disposition" ).as( "disposition" ) )
                .add( Projections.property( "sequence" ).as( "sequence" ) )
                .add( Projections.property( "approvalNumber" ).as( "approvalNumber" ) )
                .add( Projections.property( "approvalCode" ).as( "approvalCode" ) )
                .add( Projections.property( "accountSuffix" ).as( "accountSuffix" ) )
                .add( Projections.property( "voided" ).as( "voided" ) )
                .add( Projections.property( "poNumber" ).as( "poNumber" ) )
                .add( Projections.property( "invoiceNumber" ).as( "invoiceNumber" ) )
                .add( Projections.property( "createdAt" ).as( "createdAt" ) )
                .add( Projections.property( "finalized" ).as( "finalized" ) )
                .add( Projections.property( "hostCapture" ).as( "hostCapture" ) )
                .add( Projections.property( "retrievalData" ).as( "retrievalData" ) )
                .add( Projections.property( "batchNumber" ).as( "batchNumber" ) )
                .add( Projections.property( "cvvResult" ).as( "cvvResult" ) )
                .add( Projections.property( "entryMethod" ).as( "entryMethod" ) )
                .add( Projections.property( "idOriginalTransaction" ).as( "idOriginalTransaction" ) )
                .add( Projections.property( "merchant.name" ).as( "merchant.name" ) )
                .add( Projections.property( "merchant.number" ).as( "merchant.number" ) )
                .add( Projections.property( "merchant.id" ).as( "merchant.id" ) );
        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( TransactionDisplay.class ) );

        return cri.list();
    }

//    public List<ParameterDisplay> terminalParameterReport( String terminalId ) {
//        Criteria cri = HibernateUtil.getSession().createCriteria( TerminalParameterValue.class )
//                .createAlias( "terminal", "terminal" )
//                .createAlias( "applicationParameter", "applicationParameter" )
//                .add( Restrictions.eq( "terminal.terminalId", terminalId ) );
//
//        ProjectionList projectionList = Projections.projectionList()
//                .add( Projections.property( "applicationParameter.parameter" ).as( "parameter" ) )
//                .add( Projections.property( "applicationParameter.description" ).as( "description" ) )
//                .add( Projections.property( "value" ).as( "value" ) );
//
//        cri.setProjection( projectionList );
//        cri.setResultTransformer( new TransformerComplexBeans( ParameterDisplay.class ) );
//
//        return cri.list();
//    }
    
}
