package com.smartbt.vtsuite.vtreporting;

import com.smartbt.girocheck.servercommon.dao.MerchantReportDAO;
import com.smartbt.girocheck.servercommon.dao.TerminalReportDAO;
import com.smartbt.girocheck.servercommon.dao.TransactionReportDAO;
import com.smartbt.girocheck.servercommon.model.FiltersReport;
import com.smartbt.girocheck.servercommon.model.ReportFilters;
import com.smartbt.girocheck.servercommon.dao.ReportDAO;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil; 
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/*
 * @author Christopher Perez, Maite Gonzalez
 *
 */
public class ReportManager {

    /**
     * Report Data Access Object
     */
//    protected static ReportDAO reportDAO = ReportDAO.get();
    protected static TransactionReportDAO transactionReportDAO = TransactionReportDAO.get();
    protected static MerchantReportDAO merchantReportDAO = MerchantReportDAO.get();
    protected static TerminalReportDAO terminalReportDAO = TerminalReportDAO.get(); 
    /**
     * Username
     */
    private static final String USER_NAME = "USER_NAME";


    
    public static InputStream processRequest(String id, String reportType){
        
        InputStream stream = null;
        
        switch(reportType){
            case "DetailsListing":
            case "Details":
                stream = getDataTransaction(id, reportType );
                break;
            case "Merchant":
                stream = getDataTerminal(id, reportType );
                break;
            case "Grouping":
                stream = getDataMerchant(id, reportType );
                break;

        } 
        
        
        return stream;
    }
    //Working!!
       public static InputStream getDataTransaction(String id, String reportType) {

        System.out.println("[VTREPORTING] ReportManager.getDataTransaction() with id: " + id);
        InputStream stream = null;
//        String userEmail = "";
        
        String header = "Transaction Report";
        String footer = "Report";
        String userName="";
 
        FiltersReport filters = new FiltersReport();
        
        Integer idFilter = Integer.parseInt(id);
        
        try {
            HibernateUtil.beginTransaction();
            
            filters = ReportDAO.get().getFiltersReport(idFilter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                HibernateUtil.commitTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                HibernateUtil.rollbackTransaction();
            }
        }
        
        if(filters == null){
            System.out.println("VTReporting ReportManager reportFirlters is NULL");
            return stream;
        }
        
        try {
            HibernateUtil.beginTransaction();
            String jsonData = "";
            
            if(reportType.equals("Details")){
                jsonData = ReportDAO.get().detailTransactionReport(filters);
            }else{
                jsonData = ReportDAO.get().detailListingTransactionReport(filters);
            }
            
            String xmlData = JSON2XMLParser.fromJsonToXml(jsonData, header, footer, filters.getStartRangeDate() + "", filters.getEndRangeDate() + "", reportType);
            
            stream = new ByteArrayInputStream(xmlData.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                HibernateUtil.commitTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                HibernateUtil.rollbackTransaction();
            }
        }
       
        return stream;
    }
    
//    public static InputStream getDataTransaction(String id, String reportType) {
//
//        System.out.println("[VTREPORTING] ReportManager.getDataTransaction() with id: " + id);
//        InputStream stream = null;
////        String userEmail = "";
//        
//        String header = "Transaction Report";
//        String footer = "Report";
//        String userName="";
////         Get user name
////        try {
////            cookies = cookies.replace(";", "\n");
////            Properties propAttrs = new Properties();
////            propAttrs.load(new StringReader(cookies));
////            userName = propAttrs.getProperty(USER_NAME).replace("\"", "");
////            System.out.println("[VTREPORTING] ReportManager.getData() with userName from cookies: " + userName);
////        } catch (Exception e) {
////        e.printStackTrace();
////        }
//
//        ReportFilters reportFilters = new ReportFilters();
//        try {
//            HibernateUtil.beginTransaction();
//            reportFilters = transactionReportDAO.getReportFilters(Integer.parseInt(id));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                HibernateUtil.commitTransaction();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                HibernateUtil.rollbackTransaction();
//            }
//        }
//        
//        if(reportFilters == null){
//            System.out.println("VTReporting ReportManager reportFirlters is NULL");
//            return stream;
//        }
//        
//        try {
//            HibernateUtil.beginTransaction();
//            String jsonData = transactionReportDAO.getReport(reportFilters.getStartRangeDate(), reportFilters.getEndRangeDate(), reportFilters.getAmount(), reportType , 0,300,0,null);
//            System.out.println("jsonData = " + jsonData);
//            String xmlData = JSON2XMLParser.fromJsonToXml(jsonData, header, footer, reportFilters.getStartRangeDate(), reportFilters.getEndRangeDate(), reportType);
//           System.out.println("xmlData = " + xmlData);
//            stream = new ByteArrayInputStream(xmlData.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                HibernateUtil.commitTransaction();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                HibernateUtil.rollbackTransaction();
//            }
//        }
//        System.out.println("[VTREPORTING] ReportManager.getDataTransaction() END");
//        return stream;
//    }

    
    public static InputStream getDataMerchant(String idReport, String reportType) {

//        System.out.println("[VTREPORTING] ReportManager.getDataMerchant() with idReport: " + idReport);
        InputStream stream = null;
//        String userEmail = "";
        
        String header = "Merchant Report";
        String footer = "Report";

        ReportFilters reportFilters = new ReportFilters();
        try {
            HibernateUtil.beginTransaction();
            reportFilters = merchantReportDAO.getReportFilters(Integer.parseInt(idReport));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                HibernateUtil.commitTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                HibernateUtil.rollbackTransaction();
            }
        }
        
        if(reportFilters == null){
            System.out.println("VTReporting ReportManager reportFilters is NULL");
            return stream;
        }
        
        try {
            HibernateUtil.beginTransaction();
            String jsonData = merchantReportDAO.getReport(reportFilters.getEntityId());
            String xmlData = JSON2XMLParser.fromJsonToXml(jsonData, header, footer, reportFilters.getStartRangeDate(), reportFilters.getEndRangeDate(), reportType);
            stream = new ByteArrayInputStream(xmlData.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                HibernateUtil.commitTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                HibernateUtil.rollbackTransaction();
            }
        }
        System.out.println("[VTREPORTING] ReportManager.getData() END");
        return stream;
    }
    
    public static InputStream getDataTerminal(String idReport, String reportType) {

        System.out.println("[VTREPORTING] ReportManager.getDataTerminal() with idReport: " + idReport);
        InputStream stream = null;
//        String userEmail = "";
        
        String header = "Terminal Report";
        String footer = "Report";

        ReportFilters reportFilters = new ReportFilters();
        try {
            HibernateUtil.beginTransaction();
            reportFilters = terminalReportDAO.getReportFilters(Integer.parseInt(idReport));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                HibernateUtil.commitTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                HibernateUtil.rollbackTransaction();
            }
        }
        
        if(reportFilters == null){
            System.out.println("VTReporting ReportManager reportFilters is NULL");
            return stream;
        }
        
        if(reportFilters.getEntityId() == null){
            System.out.println("VTReporting ReportManager entity ID from reportFilters is NULL");
            return stream;
        }

        try {
            HibernateUtil.beginTransaction();
            String jsonData = terminalReportDAO.getReport(reportFilters.getEntityId());
            String xmlData = JSON2XMLParser.fromJsonToXml(jsonData, header, footer, reportFilters.getStartRangeDate(), reportFilters.getEndRangeDate(), reportType);
            stream = new ByteArrayInputStream(xmlData.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                HibernateUtil.commitTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                HibernateUtil.rollbackTransaction();
            }
        }
        System.out.println("[VTREPORTING] ReportManager.getData() END");
        return stream;
    }
    
    /**
     * Loads schema from file.
     *
     * @param path
     * @return Schema as InputStream
     */
    public static InputStream getDefaultSchema(String path) {
        FileInputStream stream = null;

        try {
            stream = new FileInputStream(path);
        } catch (Exception e) {
        }

        return stream;
    }

    /**
     * Loads data from file.
     *
     * @param path
     * @return Data as InputStream
     */
    public static InputStream getDefaultData(String path) {
        FileInputStream stream = null;

        try {
            stream = new FileInputStream(path);
        } catch (Exception e) {
        }

        return stream;
    }
}
