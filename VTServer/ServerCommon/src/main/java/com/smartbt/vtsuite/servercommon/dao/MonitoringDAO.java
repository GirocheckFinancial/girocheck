/*
 ** File: MonitoringDAO.java
 **
 ** Date Created: Janauary 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
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
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Monitoring;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class MonitoringDAO extends BaseDAO<Monitoring> {

    protected static MonitoringDAO dao;
    private static final Logger log = Logger.getLogger(MonitoringDAO.class);

    public MonitoringDAO() {
        //super(Terminal.class);
    }

    public static MonitoringDAO get() {
        if (dao == null) {
            dao = new MonitoringDAO();
        }
        return dao;
    }

    /**
     * Get a monitoring by a given id
     *
     * @param id
     * @return Monitoring
     */
    public Monitoring getMonitoring(int id) {
        Monitoring monitoring = findById(id);
        HibernateUtil.initialize(monitoring.getTerminal());
        HibernateUtil.initialize(monitoring.getClerk());
        return monitoring;
    }

    /**
     * Search all the Monitoring
     *
     * @param idEntity
     * @param searchFilter
     * @param entityType
     * @param firstResult
     * @param maxResult
     * @return List<Monitoring>
     */
    public List<Monitoring> searchMonitoring(EntityType entityType, int idEntity, String searchFilter, int firstResult, int maxResult) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Monitoring.class)
                .createAlias("terminal", "terminal")
                .addOrder(Order.desc("createdAt"));

        switch (entityType) {
            case CUSTOMER:
                criteria.createAlias("terminal.merchant", "terminalMerchant");
                criteria.createAlias("terminalMerchant.customer", "terminalMerchantCustomer");
                criteria.add(Restrictions.eq("terminalMerchantCustomer.id", idEntity));
                break;
            case MERCHANT:
                criteria.createAlias("terminal.merchant", "terminalMerchant");
                criteria.add(Restrictions.eq("terminalMerchant.id", idEntity));
                break;
            case TERMINAL:               
                criteria.add(Restrictions.eq("terminal.id", idEntity));
                break;
        }

        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }
        if (searchFilter != null && !searchFilter.isEmpty()) {
            criteria.createAlias("clerk", "clerk");

            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("clerk.username", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("terminal.terminalId", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("information", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("host", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("function", searchFilter, MatchMode.ANYWHERE).ignoreCase());
            Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "createdAt");
            if (dateRestriction != null) {
                disjunction.add(dateRestriction);
            }
            criteria.add(disjunction);
        }

        List<Monitoring> monitorings = criteria.list();

        //Loading for convert to JSON
        for (Monitoring monitoring : monitorings) {
            HibernateUtil.initialize(monitoring.getTerminal());
            HibernateUtil.initialize(monitoring.getClerk());
        }

        return monitorings;
    }

    /**
     * Add new monitoring
     *
     * @param monitoring
     */
    public void addMonitoring(Monitoring monitoring) {
        monitoring.setCreatedAt(new Date());
        saveOrUpdate(monitoring);
    }

    /**
     * Start monitoring terminal
     *
     * @param idTerminal
     */
    public void startMonitoringTerminal(int idTerminal) {
        Terminal terminal = TerminalDAO.get().findById(idTerminal);
        terminal.setStartedMonitorAt(new Date());
        terminal.setMonitored(Boolean.TRUE);

        HibernateUtil.getSession().saveOrUpdate(terminal);
    }

    /**
     * Stop monitoring terminal
     *
     * @param idTerminal
     */
    public void stopMonitoringTerminal(int idTerminal) {
        Terminal terminal = TerminalDAO.get().findById(idTerminal);
        terminal.setMonitored(Boolean.FALSE);

        HibernateUtil.getSession().saveOrUpdate(terminal);
    }

    /**
     * Whether or not the terminal should stop of being monitored, taking into
     * account that just can be monitored for one day.
     *
     * @param idTerminal
     * @return
     */
    public boolean shouldBeStopedMonitoring(int idTerminal) {
        Terminal terminal = TerminalDAO.get().findById(idTerminal);
        if (terminal != null) {
            Date dayLessFromNow = DateUtils.lessDays(new GregorianCalendar(), 1);

            if (terminal.getStartedMonitorAt() != null && terminal.getStartedMonitorAt().before(dayLessFromNow)) {
                stopMonitoringTerminal(terminal.getId());

                log.info("Stopping monitoring on terminal " + terminal.getTerminalId());
                return true;
            }
        }
        return false;
    }
}
