/*
 ** File: WatchdogDAO.java
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

import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.Customer;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.model.User;
import com.smartbt.vtsuite.servercommon.model.WatchdogAlert;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntity;
import com.smartbt.vtsuite.servercommon.model.Watchdog;
import com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Ariel Saavedra
 */
public class WatchdogDAO {

    /**
     *
     */
    protected static WatchdogDAO dao;

    /**
     *
     */
    public WatchdogDAO() {
    }

    /**
     *
     * @return
     */
    public static WatchdogDAO get() {
        if (dao == null) {
            dao = new WatchdogDAO();
        }
        return dao;
    }

    /**
     *
     * @param id
     * @return
     */
    public Watchdog findWatchdogById(int id) {
        return (Watchdog) HibernateUtil.getSession().get(Watchdog.class, id);
    }

    /**
     *
     * @param id
     * @return
     */
    public WatchdogAlert findWatchdogAlertById(int id) {
        return (WatchdogAlert) HibernateUtil.getSession().get(WatchdogAlert.class, id);
    }

    /**
     *
     * @param id
     * @return
     */
    public WatchdogEntity findWatchdogEntityById(int id) {
        return (WatchdogEntity) HibernateUtil.getSession().get(WatchdogEntity.class, id);
    }

    /**
     * Save or Update a WatchdogEntity
     *
     * @param watchdogEntity
     */
    public void saveOrUpdateWatchdogEntity(WatchdogEntity watchdogEntity) {
        WatchdogEntity watchdogEntitySave;
        if (watchdogEntity.getId() != 0) {
            watchdogEntitySave = findWatchdogEntityById(watchdogEntity.getId());
        } else {
            watchdogEntitySave = new WatchdogEntity();
            watchdogEntitySave.setCreatedAt(new Date());
        }

        Watchdog watchdog = findWatchdogById(watchdogEntity.getWatchdog().getId());
        Customer customer = null;
        Merchant merchant = null;
        Terminal terminal = null;
        User userCreator = null;
        Clerk clerkCreator = null;

        if (watchdogEntity.getCustomer() != null) {
            customer = CustomerDAO.get().findById(watchdogEntity.getCustomer().getId());
        }
        if (watchdogEntity.getMerchant() != null) {
            merchant = MerchantDAO.get().findById(watchdogEntity.getMerchant().getId());
        }
        if (watchdogEntity.getTerminal() != null) {
            terminal = TerminalDAO.get().findById(watchdogEntity.getTerminal().getId());
        }
        if (watchdogEntity.getUserCreator() != null) {
            userCreator = UserDAO.get().findById(watchdogEntity.getUserCreator().getId());
        }
        if (watchdogEntity.getClerkCreator() != null) {
            clerkCreator = ClerkDAO.get().findById(watchdogEntity.getClerkCreator().getId());
        }

        Collection<WatchdogEntityClerk> watchdogEntityClerks = new LinkedList<WatchdogEntityClerk>();
        if (watchdogEntity.getWatchdogEntityClerk() != null) {
            for (WatchdogEntityClerk watchdogEntityClerk : watchdogEntity.getWatchdogEntityClerk()) {
                WatchdogEntityClerk watchdogEntityClerkSave = new WatchdogEntityClerk();
                watchdogEntityClerkSave.setClerk(ClerkDAO.get().findById(watchdogEntityClerk.getClerk().getId()));
                watchdogEntityClerkSave.setWatchdogEntity(watchdogEntitySave);
                watchdogEntityClerks.add(watchdogEntityClerkSave);
            }
        }
        if (watchdogEntitySave.getWatchdogEntityClerk() != null) {
            for (WatchdogEntityClerk watchdogEntityClerk : watchdogEntitySave.getWatchdogEntityClerk()) {
                HibernateUtil.getSession().delete(watchdogEntityClerk);
            }
        }

        watchdogEntitySave.setWatchdog(watchdog);
        watchdogEntitySave.setCustomer(customer);
        watchdogEntitySave.setMerchant(merchant);
        watchdogEntitySave.setTerminal(terminal);
        watchdogEntitySave.setUserCreator(userCreator);
        watchdogEntitySave.setClerkCreator(clerkCreator);
        watchdogEntitySave.setValue(watchdogEntity.getValue());
        watchdogEntitySave.setWatchdogEntityClerk(watchdogEntityClerks);
        HibernateUtil.getSession().saveOrUpdate(watchdogEntitySave);
    }

    /**
     * Add a WatchdogAlert
     *
     * @param watchdogAlert
     */
    public void addWatchdogAlert(WatchdogAlert watchdogAlert) {
        HibernateUtil.getSession().saveOrUpdate(watchdogAlert);
    }

    /**
     *
     * @param watchdogEntityId
     */
    public void deleteWatchdogEntity(int watchdogEntityId) {
        HibernateUtil.getSession().delete(findWatchdogEntityById(watchdogEntityId));
    }

    /**
     *
     * @param watchdogAlertId
     */
    public void deleteWatchdogAlert(int watchdogAlertId) {
        HibernateUtil.getSession().delete(findWatchdogAlertById(watchdogAlertId));
    }

    /**
     *
     * @param clerk
     * @param watchdogAlertId
     */
    public void deleteWatchdogAlertByClerkDestination(Clerk clerk, int watchdogAlertId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(WatchdogAlertClerk.class)
                .createAlias("watchdogAlert", "watchdogAlert")
                .createAlias("clerk", "clerk")
                .add(Restrictions.eq("watchdogAlert.id", watchdogAlertId))
                .add(Restrictions.eq("clerk.id", clerk.getId()));
        WatchdogAlertClerk watchdogAlertClerk = (WatchdogAlertClerk) criteria.uniqueResult();

        if (watchdogAlertClerk != null) {
            HibernateUtil.getSession().delete(watchdogAlertClerk);
            clerk.getWatchdogAlertClerk().remove(watchdogAlertClerk);
            HibernateUtil.getSession().save(clerk);
        }
    }

    /**
     *
     * @param clerk
     */
    public void deleteAllWatchdogAlertsByClerkDestination(Clerk clerk) {
        for (WatchdogAlertClerk watchdogAlertClerk : clerk.getWatchdogAlertClerk()) {
            HibernateUtil.getSession().delete(watchdogAlertClerk);
        }
        clerk.getWatchdogAlertClerk().clear();
        HibernateUtil.getSession().save(clerk);
    }

    /**
     *
     * @param entityType
     * @param idEntity
     */
    public void deleteAllWatchdogAlertsByEntity(EntityType entityType, int idEntity) {
        List<WatchdogAlert> watchdogAlerts = getWatchdogAlertsByEntity(entityType, idEntity, null, null, null, -1, -1);
        for (WatchdogAlert watchdogAlert : watchdogAlerts) {
            Query query = HibernateUtil.getSession().createQuery("delete WatchdogAlertClerk where watchdogAlert.id = :id");
            query.setParameter("id", watchdogAlert.getId());
            query.executeUpdate();

            HibernateUtil.getSession().delete(watchdogAlert);
        }
    }

    /**
     *
     * @param watchdogEntityId
     * @return
     */
    public WatchdogEntity getWatchdogEntity(int watchdogEntityId) {
        WatchdogEntity watchdogEntity = findWatchdogEntityById(watchdogEntityId);
        for (WatchdogEntityClerk watchdogEntityClerk : watchdogEntity.getWatchdogEntityClerk()) {
            HibernateUtil.initialize(watchdogEntityClerk.getClerk());
        }
        watchdogEntity.getWatchdog();
        HibernateUtil.getSession().clear();//Avoiding issue (Hibernate update the object)
        watchdogEntity.setCustomer(null);
        watchdogEntity.setMerchant(null);
        watchdogEntity.setTerminal(null);
        return watchdogEntity;
    }

    /**
     *
     * @return
     */
    public List<Watchdog> getWatchdogs() {
        List<Watchdog> watchdogs = (List<Watchdog>) HibernateUtil.getSession().createCriteria(Watchdog.class)
                .add(Restrictions.eq("active", true))
                .addOrder(Order.asc("name"))
                .list();
        for (Watchdog watchdog : watchdogs) {
            watchdog.getDataType();
        }
        return watchdogs;
    }

    /**
     *
     * @param entityType
     * @param idEntity
     * @return
     */
    public List<WatchdogEntity> getWatchdogEntitiesByEntity(EntityType entityType, int idEntity) {
        List<WatchdogEntity> watchdogEntities = new LinkedList<WatchdogEntity>();

        switch (entityType) {
            case CUSTOMER:
                Customer customer = (Customer) HibernateUtil.getSession().get(Customer.class, idEntity);
                watchdogEntities.addAll(customer.getWatchdogEntity());
                break;
            case MERCHANT:
                Merchant merchant = (Merchant) HibernateUtil.getSession().get(Merchant.class, idEntity);
                watchdogEntities.addAll(merchant.getWatchdogEntity());
                watchdogEntities.addAll(merchant.getCustomer().getWatchdogEntity());
                break;
            case TERMINAL:
                Terminal terminal = (Terminal) HibernateUtil.getSession().get(Terminal.class, idEntity);
                watchdogEntities.addAll(terminal.getWatchdogEntity());
                watchdogEntities.addAll(terminal.getMerchant().getWatchdogEntity());
                watchdogEntities.addAll(terminal.getMerchant().getCustomer().getWatchdogEntity());
                break;
        }

        //Loading for convert to JSON
        for (WatchdogEntity watchdogEntity : watchdogEntities) {
            watchdogEntity.getWatchdog().getDataType();
            watchdogEntity.getCustomer();
            watchdogEntity.getMerchant();
            watchdogEntity.getTerminal();
            HibernateUtil.initialize(watchdogEntity.getUserCreator());
            HibernateUtil.initialize(watchdogEntity.getClerkCreator());
            for (WatchdogEntityClerk watchdogEntityClerk : watchdogEntity.getWatchdogEntityClerk()) {
                HibernateUtil.initialize(watchdogEntityClerk.getClerk());
            }
        }
        return watchdogEntities;
    }

    /**
     *
     * @param entityType
     * @param idEntity
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<WatchdogAlert> getWatchdogAlertsByEntity(EntityType entityType, int idEntity, String searchFilter,
            Date startRangeDate, Date endRangeDate, int firstResult, int maxResult) {

        Criteria watchdogAlertClerkCriteriaIDs = HibernateUtil.getSession().createCriteria(WatchdogAlertClerk.class)
                .createAlias("watchdogAlert", "watchdogAlert")
                .createAlias("clerk", "clerk");
        watchdogAlertClerkCriteriaIDs.setProjection(Projections.projectionList().add(Projections.property("watchdogAlert.id")));

        Disjunction or = Restrictions.disjunction();
        switch (entityType) {
            case CUSTOMER:
                watchdogAlertClerkCriteriaIDs.createAlias("clerk.customer", "customer", JoinType.LEFT_OUTER_JOIN);
                watchdogAlertClerkCriteriaIDs.createAlias("clerk.merchant", "merchant", JoinType.LEFT_OUTER_JOIN);
                watchdogAlertClerkCriteriaIDs.createAlias("clerk.terminal", "terminal", JoinType.LEFT_OUTER_JOIN);
                watchdogAlertClerkCriteriaIDs.createAlias("merchant.customer", "merchantCustomer", JoinType.LEFT_OUTER_JOIN);
                watchdogAlertClerkCriteriaIDs.createAlias("terminal.merchant", "terminalMerchant", JoinType.LEFT_OUTER_JOIN);
                watchdogAlertClerkCriteriaIDs.createAlias("terminalMerchant.customer", "terminalMerchantCustomer", JoinType.LEFT_OUTER_JOIN);
                or.add(Restrictions.eq("customer.id", idEntity));
                or.add(Restrictions.eq("merchantCustomer.id", idEntity));
                or.add(Restrictions.eq("terminalMerchantCustomer.id", idEntity));
                watchdogAlertClerkCriteriaIDs.add(or);
                break;
            case MERCHANT:
                watchdogAlertClerkCriteriaIDs.createAlias("clerk.merchant", "merchant", JoinType.LEFT_OUTER_JOIN);
                watchdogAlertClerkCriteriaIDs.createAlias("clerk.terminal", "terminal", JoinType.LEFT_OUTER_JOIN);
                watchdogAlertClerkCriteriaIDs.createAlias("terminal.merchant", "terminalMerchant", JoinType.LEFT_OUTER_JOIN);
                or.add(Restrictions.eq("merchant.id", idEntity));
                or.add(Restrictions.eq("terminalMerchant.id", idEntity));
                watchdogAlertClerkCriteriaIDs.add(or);
                break;
            case TERMINAL:
                watchdogAlertClerkCriteriaIDs.createAlias("clerk.terminal", "terminal");
                watchdogAlertClerkCriteriaIDs.add(Restrictions.eq("terminal.id", idEntity));
                break;
        }

        List<Integer> watchdogAlertIds = watchdogAlertClerkCriteriaIDs.list();
        List<WatchdogAlert> watchdogAlerts = new LinkedList<WatchdogAlert>();

        if (!watchdogAlertIds.isEmpty()) {
            Criteria criteria = HibernateUtil.getSession().createCriteria(WatchdogAlert.class)
                    .add(Restrictions.ge("createdAt", DateUtils.lessDays(new GregorianCalendar(), 30)))
                    .addOrder(Order.desc("createdAt"));

            criteria.add(Restrictions.in("id", watchdogAlertIds));

            if (firstResult >= 0) {
                criteria.setFirstResult(firstResult);
                criteria.setMaxResults(maxResult);
            }

            if (startRangeDate != null) {
                criteria.add(Restrictions.ge("createdAt", startRangeDate));
            }
            if (endRangeDate != null) {
                endRangeDate.setHours(24);
                criteria.add(Restrictions.le("createdAt", endRangeDate));
            }

            if (searchFilter != null && !searchFilter.isEmpty()) {
                criteria.createAlias("watchdog", "watchdog")
                        .createAlias("userOrigination", "userOrigination", JoinType.LEFT_OUTER_JOIN)
                        .createAlias("clerkOrigination", "clerkOrigination", JoinType.LEFT_OUTER_JOIN);

                Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                        .add(Restrictions.like("value", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                        .add(Restrictions.like("clerkOrigination.username", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                        .add(Restrictions.like("userOrigination.username", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                        .add(Restrictions.like("watchdog.name", searchFilter, MatchMode.ANYWHERE).ignoreCase());
                Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "createdAt");
                if (dateRestriction != null) {
                    disjunction.add(dateRestriction);
                }
                criteria.add(disjunction);
            }

            watchdogAlerts = criteria.list();
        }

        //Loading for convert to JSON
        for (WatchdogAlert watchdogAlert : watchdogAlerts) {
            watchdogAlert.setWatchdogAlertClerk(null);

            HibernateUtil.initialize(watchdogAlert.getClerkOrigination());
            HibernateUtil.initialize(watchdogAlert.getUserOrigination());

            watchdogAlert.getWatchdog().getDataType();
        }
        return watchdogAlerts;
    }

    /**
     *
     * @param clerk
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<WatchdogAlert> getWatchdogAlertsByClerkDestination(Clerk clerk, String searchFilter,
            Date startRangeDate, Date endRangeDate, int firstResult, int maxResult) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(WatchdogAlert.class)
                .createAlias("watchdogAlertClerk", "watchdogAlertClerk")
                .createAlias("watchdogAlertClerk.clerk", "clerk")
                .add(Restrictions.eq("clerk.id", clerk.getId()))
                .add(Restrictions.ge("createdAt", DateUtils.lessDays(new GregorianCalendar(), 30)))
                .addOrder(Order.desc("createdAt"));

        if (maxResult > 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }

        if (startRangeDate != null) {
            criteria.add(Restrictions.ge("createdAt", startRangeDate));
        }
        if (endRangeDate != null) {
            endRangeDate.setHours(24);
            criteria.add(Restrictions.le("createdAt", endRangeDate));
        }

        if (searchFilter != null && !searchFilter.isEmpty()) {
            criteria.createAlias("watchdog", "watchdog")
                    .createAlias("userOrigination", "userOrigination", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("clerkOrigination", "clerkOrigination", JoinType.LEFT_OUTER_JOIN);

            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("value", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("clerkOrigination.username", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("userOrigination.username", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("watchdog.name", searchFilter, MatchMode.ANYWHERE).ignoreCase());
            Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "createdAt");
            if (dateRestriction != null) {
                disjunction.add(dateRestriction);
            }
            criteria.add(disjunction);
        }

        //Loading for convert to JSON
        List<WatchdogAlert> watchdogAlerts = criteria.list();
        for (WatchdogAlert watchdogAlert : watchdogAlerts) {
            watchdogAlert.setWatchdogAlertClerk(null);
            HibernateUtil.initialize(watchdogAlert.getClerkOrigination());
            HibernateUtil.initialize(watchdogAlert.getUserOrigination());
            watchdogAlert.getWatchdog().getDataType();
        }
        return watchdogAlerts;
    }

//    public List<WatchdogAlert> getWatchdogAlertsByClerkOrigination(int idClerk) {
//        Criteria criteria = HibernateUtil.getSession().createCriteria(WatchdogAlert.class)
//                .createAlias("clerkOrigination", "clerkOrigination")
//                .add(Restrictions.eq("clerkOrigination.id", idClerk))
//                .add(Restrictions.ge("createdAt", DateUtils.lessDays(new GregorianCalendar(), 30)))
//                .addOrder(Order.desc("createdAt"));
//
//        //Loading for convert to JSON
//        List<WatchdogAlert> watchdogAlerts = criteria.list();
//        for (WatchdogAlert watchdogAlert : watchdogAlerts) {
//            watchdogAlert.setWatchdogAlertClerk(null);
//            watchdogAlert.getClerkOrigination();
//            watchdogAlert.getUserOrigination();
//            watchdogAlert.getWatchdog().getDataType();
//        }
//        return watchdogAlerts;
//    }
}
