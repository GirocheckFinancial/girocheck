/*
 ** File: AuditLogDAO.java
 **
 ** Date Created: October 2013
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

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.AuditLogDisplay;
import com.smartbt.vtsuite.servercommon.model.AuditLog;
import com.smartbt.vtsuite.servercommon.model.User;
import com.smartbt.vtsuite.servercommon.model.VTSession;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class AuditLogDAO extends BaseDAO<AuditLog> {

    private static final Logger log = Logger.getLogger(AuditLogDAO.class);
    protected static AuditLogDAO dao;

    public AuditLogDAO() {
        //super(AuditLog.class);
    }

    public static AuditLogDAO get() {
        if (dao == null) {
            dao = new AuditLogDAO();
        }
        return dao;
    }

    /**
     * Inserts in Audit Log.
     *
     * @param vTSession
     * @param user
     * @param details The audit details
     */
    public void addAuditLog(VTSession vTSession, User user, String details) {
        AuditLog auditLog = new AuditLog();
        if (vTSession != null) {
            auditLog.setClerk(vTSession.getClerk());
            auditLog.setTerminal(vTSession.getTerminal());
        } else {
            auditLog.setUser(user);
        }
        auditLog.setDetails(details);
        auditLog.setCategory(1);
        auditLog.setCreatedAt(new Timestamp(new Date().getTime()));
        HibernateUtil.getSession().merge(auditLog);
    }

    public List<AuditLogDisplay> searchAuditLogs(int idEntity, EntityType entityType, String searchFilter, Date startRangeDate, Date endRangeDate, int firstResult, int maxResults) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(AuditLog.class).addOrder(Order.desc("createdAt"));

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("details").as("information"))
                .add(Projections.property("createdAt").as("createdAt"));

        if (entityType == EntityType.AMS) {
            criteria.createAlias("user", "user");

            projectionList.add(Projections.property("user.username").as("user.username"));
        } else {
            criteria.createAlias("terminal", "terminal")
                    .createAlias("terminal.merchant", "merchant")
                    .createAlias("merchant.customer", "customer")
                    .createAlias("clerk", "user");

            projectionList.add(Projections.property("terminal.id").as("terminal.id"))
                    .add(Projections.property("terminal.terminalId").as("terminal.serialNumber"))
                    .add(Projections.property("merchant.name").as("merchant.name"))
                    .add(Projections.property("merchant.number").as("merchant.number"))
                    .add(Projections.property("merchant.id").as("merchant.id"))
                    .add(Projections.property("user.username").as("user.username"));
        }

        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResults);
        }

        if (startRangeDate != null) {
            criteria.add(Restrictions.ge("createdAt", startRangeDate));
        }
        if (endRangeDate != null) {
            endRangeDate.setHours(24);
            criteria.add(Restrictions.le("createdAt", endRangeDate));
        }
        switch (entityType) {
            case CUSTOMER:
                criteria.add(Restrictions.eq("customer.id", idEntity));
                break;
            case MERCHANT:
                criteria.add(Restrictions.eq("merchant.id", idEntity));
                break;
            case TERMINAL:
                criteria.add(Restrictions.eq("terminal.id", idEntity));
                break;
        }

        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("details", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("user.username", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "createdAt");
            if (dateRestriction != null) {
                disjunction.add(dateRestriction);
            }
            criteria.add(disjunction);
        }

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new TransformerComplexBeans(AuditLogDisplay.class));

        return criteria.list();
    }
}
