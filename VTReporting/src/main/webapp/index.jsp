<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1); 
%>

<%@page import="com.stimulsoft.report.enums.StiReportCacheMode"%>
<%@page import="com.smartbt.vtsuite.vtreporting.ReportManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.stimulsoft.lib.utils.StiValidationUtil"%>
<%@page import="com.stimulsoft.webviewer.enums.StiContentAlignment"%>
<%@page import="com.stimulsoft.base.drawing.StiColor"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="com.stimulsoft.base.utils.StiXmlMarshalUtil"%>
<%@page import="com.stimulsoft.base.localization.StiLocalizationInfo"%>
<%@page import="com.stimulsoft.lib.io.StiFileUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.stimulsoft.webviewer.enums.StiShowMenuMode"%>
<%@page import="com.stimulsoft.webviewer.enums.StiPagesViewMode"%>
<%@page import="com.stimulsoft.webviewer.enums.StiPrintDestination"%>
<%@page import="com.stimulsoft.webviewer.enums.StiWebViewerTheme"%>
<%@page import="com.stimulsoft.webviewer.StiWebViewerOptions"%>
<%@page import="com.stimulsoft.report.dictionary.databases.StiJDBCDatabase"%>
<%@page import="com.stimulsoft.report.dictionary.databases.StiXmlDatabase"%>
<%@page import="java.io.File"%>
<%@page import="com.stimulsoft.report.StiSerializeManager"%>
<%@page import="com.stimulsoft.report.StiReport"%>
<%@page import="com.google.common.io.CharStreams"%>
<%@page import="java.io.InputStreamReader"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stimulsoft.com/webviewer" prefix="stiwebviewer"%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <stiwebviewer:resources/>

        <title>VTReporting</title>
        <style type="text/css">
            .t1 td {
                padding-right: 30px
            }
        </style>
    </head>

    <body>
        <%
            try {
                System.out.println("Printing from index.JSP...");
                
                InputStream xmlData, xmlSchema;

                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream inputMRT = null;
                String reportType = request.getParameter("reportType");
                String reportXMLDataBase = "";
                
                System.out.println("reportType = " + reportType);
                
                if (reportType.equalsIgnoreCase("Details")) {
                    inputMRT = classLoader.getResourceAsStream("DetailsReport.mrt");
                    xmlSchema = classLoader.getResourceAsStream("TransactionFK.xsd");
                    reportXMLDataBase = "TransactionFK";
                } else if (reportType.equalsIgnoreCase("DetailsListing")) {
                    inputMRT = classLoader.getResourceAsStream("DetailsListingReport.mrt");
                    xmlSchema = classLoader.getResourceAsStream("TransactionFK.xsd");
                    reportXMLDataBase = "TransactionFK";
                } else if (reportType.equalsIgnoreCase("Merchant")) {
                    inputMRT = classLoader.getResourceAsStream("DetailsListingTerminalReport.mrt");
                    xmlSchema = classLoader.getResourceAsStream("TerminalFK.xsd");
                    reportXMLDataBase = "TerminalFK";
                } else if (reportType.equalsIgnoreCase("Grouping")) {
                    inputMRT = classLoader.getResourceAsStream("DetailsListingMerchantReport.mrt");
                    xmlSchema = classLoader.getResourceAsStream("MechantFK.xsd");
                    reportXMLDataBase = "MerchantFK";
                } else {
                    inputMRT = classLoader.getResourceAsStream("DetailsListingReport.mrt");
                    xmlSchema = classLoader.getResourceAsStream("TransactionFK.xsd");
                    reportXMLDataBase = "TransactionFK";
                }

                StiReport report = StiSerializeManager.deserializeReport(inputMRT);

                report.getDictionary().getDatabases().clear();
                
                if (request.getParameter("id") == null) {
                    xmlData = classLoader.getResourceAsStream("TransactionFK.xml");
                } else {
                    xmlData = ReportManager.processRequest(request.getParameter("id"),request.getParameter("reportType"));
                }

                StiXmlDatabase xmlDatabase = new StiXmlDatabase(reportXMLDataBase, xmlSchema, xmlData);

                report.getDictionary().getDatabases().add(xmlDatabase);

                report.Render();

                StiWebViewerOptions options = new StiWebViewerOptions();
                options.setRefreshTimeout(0);
                pageContext.setAttribute("report", report);
                pageContext.setAttribute("options", options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>

        <stiwebviewer:webviewer report="${report}" options="${options}"/>
        

    </body>
</html>
