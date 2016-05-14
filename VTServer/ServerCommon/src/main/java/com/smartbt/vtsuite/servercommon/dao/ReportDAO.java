/*
 ** File: TransactionDAO.java
 **
 ** Date Created: March 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Transaction;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.smartbt.vtsuite.servercommon.display.common.model.ReportDisplay;
import com.smartbt.vtsuite.servercommon.model.Report;
import com.smartbt.girocheck.servercommon.utils.Utils;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.MERCHANT;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.TERMINAL;
import com.smartbt.vtsuite.vtcommon.enums.ReportType;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Maite Gonzalez
 */
public class ReportDAO {

    protected static ReportDAO dao;
   ObjectMapper objectMapper = new ObjectMapper();

    public ReportDAO() {
        // super(Transaction.class);
    }

    public static ReportDAO get() {
        if (dao == null) {
            dao = new ReportDAO();
        }
        return dao;
    }

    public ReportDisplay generateAndSaveReport(String username, int idEntity, EntityType entityType, int idClerk, int idTransactionMode,
            Date startRangeDate, Date endRangeDate, boolean justApproved, ReportType reportType) throws Exception {
        String token = Utils.generateToken();
        ReportDisplay report = new ReportDisplay();
        Report reportDb = new Report();
        List<TransactionDisplay> reportData = null;

        switch (reportType) {
            case DETAILS:
                reportData = detailsReport(idEntity, entityType, idClerk, idTransactionMode, startRangeDate, endRangeDate, -1, -1, justApproved);
                break;
            case TOTALS:
                reportData = totalsReport(idEntity, entityType, idClerk, idTransactionMode, startRangeDate, endRangeDate, -1, -1, justApproved);
                break;

        }
        String reportDataString = objectMapper.writeValueAsString(reportData);
        reportDb.setReport(objectMapper.writeValueAsString(reportData));
        reportDb.setKey(username + token);

        Session session = HibernateUtil.getSession();
        session.saveOrUpdate(reportDb);
        session.flush();
        String url = "/VTReporting3/index.jsp?reportId=" + reportDb.getId() + "&token=" + token;
        report.setUrl(url);
        return report;
    }

    public ReportDisplay getReport(String username, String token, int reportId) throws Exception {
        Report reportDb = (Report) HibernateUtil.getSession().get(Report.class, reportId);
        ReportDisplay report = new ReportDisplay();
        report.setReport(reportDb.getReport());

        if (reportDb.getKey().equals(username + token)) {
            return report;
        } else {
            throw new Exception("User not authorized to querry this report with corresponding token");
        }
    }

    public List<TransactionDisplay> detailsReport(int idEntity, EntityType entityType, int idClerk, int idTransactionMode, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, boolean justApproved) {

        Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .createAlias("clerk", "clerk")
                .createAlias("terminal", "terminal")
                .createAlias("terminal.merchant", "merchant")
                .createAlias("merchant.customer", "customer")
                .createAlias("mode", "mode")
                .createAlias("operation", "operation")
                .createAlias("cardBrand", "cardBrand")
                .add(Restrictions.eq("voided", false))
                .addOrder(Order.desc("createdAt"));

        if (idClerk != 0) {
            cri.add(Restrictions.eq("clerk.id", idClerk));
        }
        if (idTransactionMode != 0) {
            cri.add(Restrictions.eq("mode.id", idTransactionMode));
        }

        if (justApproved) {
            cri.add(Restrictions.ilike("disposition", "%APPROVED%"));
        }

        if (firstResult >= 0) {
            cri.setFirstResult(firstResult);
            cri.setMaxResults(maxResult);
        }

        if (startRangeDate != null) {
            cri.add(Restrictions.ge("createdAt", startRangeDate));
        }
        if (endRangeDate != null) {
            endRangeDate.setHours(24);
            cri.add(Restrictions.le("createdAt", endRangeDate));
        }
        switch (entityType) {
            case CUSTOMER:
                cri.add(Restrictions.eq("customer.id", idEntity));
                break;
            case MERCHANT:
                cri.add(Restrictions.eq("merchant.id", idEntity));
                break;
            case TERMINAL:
                cri.add(Restrictions.eq("terminal.id", idEntity));
                break;
        }

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("mode.name").as("mode"))
                .add(Projections.property("operation.name").as("operation"))
                .add(Projections.property("cardBrand.name").as("cardBrand"))
                .add(Projections.property("subTotalAmount").as("subTotalAmount"))
                .add(Projections.property("totalAmount").as("totalAmount"))
                .add(Projections.property("tipAmount").as("tipAmount"))
                .add(Projections.property("taxAmount").as("taxAmount"))
                .add(Projections.property("client.id").as("client.id"))
                .add(Projections.property("clerk.id").as("clerk.id"))
                .add(Projections.property("clerk.username").as("clerk.username"))
                .add(Projections.property("terminal.id").as("terminal.id"))
                .add(Projections.property("terminal.terminalId").as("terminal.terminalId"))
                .add(Projections.property("cardHolderEmail").as("cardHolderEmail"))
                .add(Projections.property("geoLocation").as("geoLocation"))
                .add(Projections.property("disposition").as("disposition"))
                .add(Projections.property("sequence").as("sequence"))
                .add(Projections.property("approvalNumber").as("approvalNumber"))
                .add(Projections.property("approvalCode").as("approvalCode"))
                .add(Projections.property("accountSuffix").as("accountSuffix"))
                .add(Projections.property("voided").as("voided"))
                .add(Projections.property("poNumber").as("poNumber"))
                .add(Projections.property("invoiceNumber").as("invoiceNumber"))
                .add(Projections.property("createdAt").as("createdAt"))
                .add(Projections.property("finalized").as("finalized"))
                .add(Projections.property("hostCapture").as("hostCapture"))
                .add(Projections.property("retrievalData").as("retrievalData"))
                .add(Projections.property("batchNumber").as("batchNumber"))
                .add(Projections.property("cvvResult").as("cvvResult"))
                .add(Projections.property("entryMethod").as("entryMethod"))
                .add(Projections.property("idOriginalTransaction").as("idOriginalTransaction"))
                .add(Projections.property("merchant.name").as("merchant.name"))
                .add(Projections.property("merchant.number").as("merchant.number"))
                .add(Projections.property("merchant.id").as("merchant.id"));
        cri.setProjection(projectionList);
        cri.setResultTransformer(new TransformerComplexBeans(TransactionDisplay.class));

        return cri.list();
    }

    public List<TransactionDisplay> totalsReport(int idEntity, EntityType entityType, int idClerk, int idTransactionMode, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, boolean justApproved) {

         Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .createAlias("clerk", "clerk")
                .createAlias("terminal", "terminal")
                .createAlias("terminal.merchant", "merchant")
                .createAlias("merchant.customer", "customer")
                .createAlias("mode", "mode")
                .createAlias("operation", "operation")
                .createAlias("cardBrand", "cardBrand")
                .add(Restrictions.eq("voided", false))
                .addOrder(Order.desc("createdAt"));

        if (idClerk != 0) {
            cri.add(Restrictions.eq("clerk.id", idClerk));
        }
        if (idTransactionMode != 0) {
            cri.add(Restrictions.eq("mode.id", idTransactionMode));
        }

        if (justApproved) {
            cri.add(Restrictions.ilike("disposition", "%APPROVED%"));
        }

        if (firstResult >= 0) {
            cri.setFirstResult(firstResult);
            cri.setMaxResults(maxResult);
        }

        if (startRangeDate != null) {
            cri.add(Restrictions.ge("createdAt", startRangeDate));
        }
        if (endRangeDate != null) {
            endRangeDate.setHours(24);
            cri.add(Restrictions.le("createdAt", endRangeDate));
        }
        switch (entityType) {
            case CUSTOMER:
                cri.add(Restrictions.eq("customer.id", idEntity));
                break;
            case MERCHANT:
                cri.add(Restrictions.eq("merchant.id", idEntity));
                break;
            case TERMINAL:
                cri.add(Restrictions.eq("terminal.id", idEntity));
                break;
        }

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("mode.name").as("mode"))
                .add(Projections.property("operation.name").as("operation"))
                .add(Projections.property("cardBrand.name").as("cardBrand"))
                .add(Projections.property("subTotalAmount").as("subTotalAmount"))
                .add(Projections.property("totalAmount").as("totalAmount"))
                .add(Projections.property("tipAmount").as("tipAmount"))
                .add(Projections.property("taxAmount").as("taxAmount"))
                .add(Projections.property("client.id").as("client.id"))
                .add(Projections.property("clerk.id").as("clerk.id"))
                .add(Projections.property("clerk.username").as("clerk.username"))
                .add(Projections.property("terminal.id").as("terminal.id"))
                .add(Projections.property("terminal.terminalId").as("terminal.terminalId"))
                .add(Projections.property("cardHolderEmail").as("cardHolderEmail"))
                .add(Projections.property("geoLocation").as("geoLocation"))
                .add(Projections.property("disposition").as("disposition"))
                .add(Projections.property("sequence").as("sequence"))
                .add(Projections.property("approvalNumber").as("approvalNumber"))
                .add(Projections.property("approvalCode").as("approvalCode"))
                .add(Projections.property("accountSuffix").as("accountSuffix"))
                .add(Projections.property("voided").as("voided"))
                .add(Projections.property("poNumber").as("poNumber"))
                .add(Projections.property("invoiceNumber").as("invoiceNumber"))
                .add(Projections.property("createdAt").as("createdAt"))
                .add(Projections.property("finalized").as("finalized"))
                .add(Projections.property("hostCapture").as("hostCapture"))
                .add(Projections.property("retrievalData").as("retrievalData"))
                .add(Projections.property("batchNumber").as("batchNumber"))
                .add(Projections.property("cvvResult").as("cvvResult"))
                .add(Projections.property("entryMethod").as("entryMethod"))
                .add(Projections.property("idOriginalTransaction").as("idOriginalTransaction"))
                .add(Projections.property("merchant.name").as("merchant.name"))
                .add(Projections.property("merchant.number").as("merchant.number"))
                .add(Projections.property("merchant.id").as("merchant.id"));
        cri.setProjection(projectionList);
        cri.setResultTransformer(new TransformerComplexBeans(TransactionDisplay.class));

        return cri.list();
    }
}
