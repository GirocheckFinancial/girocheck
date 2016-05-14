/*
 ** File: ClerkDAO.java
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

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.ClerkRole;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.MERCHANT;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.TERMINAL;
import com.smartbt.vtsuite.servercommon.display.common.model.UserDisplay;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Ariel Saavedra
 */
public class ClerkDAO extends BaseDAO<Clerk> {

    protected static ClerkDAO dao;

    public ClerkDAO() {
        //super(Clerk.class);
    }

    public static ClerkDAO get() {
        if (dao == null) {
            dao = new ClerkDAO();
        }
        return dao;
    }

    /**
     * Search all the Clerks
     *
     * @param idEntity
     * @param searchFilter
     * @param entityType
     * @param firstResult
     * @param maxResult
     * @return List<UserDisplay>
     */
    public List<UserDisplay> searchClerks(int idEntity, String searchFilter, EntityType entityType, int firstResult, int maxResult) {
        Criteria cri = searchClerksCriteria(idEntity, searchFilter, entityType, firstResult, maxResult);
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("username").as("username"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("clerkRole.id").as("role.id"))
                .add(Projections.property("clerkRole.name").as("role.name"))
                .add(Projections.property("active").as("active"))
                .add(Projections.property("customer.id").as("customer.id"))
                .add(Projections.property("merchant.id").as("merchant.id"))
                .add(Projections.property("terminal.id").as("terminal.id"))
                .add(Projections.property("customer.name").as("customer.name"))
                .add(Projections.property("merchant.name").as("merchant.name"))
                .add(Projections.property("terminal.terminalId").as("terminal.terminalId"));

        cri.setProjection(projectionList);
        cri.setResultTransformer(new TransformerComplexBeans(UserDisplay.class));
        return cri.list();
    }

    /**
     * Search all the Clerks
     *
     * @param idEntity
     * @param searchFilter
     * @param entityType
     * @param firstResult
     * @param maxResult
     * @return List<Clerk>
     */
    public List<Clerk> searchClerksDB(int idEntity, String searchFilter, EntityType entityType, int firstResult, int maxResult) {
        return searchClerksCriteria(idEntity, searchFilter, entityType, firstResult, maxResult).list();
    }

    private Criteria searchClerksCriteria(int idEntity, String searchFilter, EntityType entityType, int firstResult, int maxResult) {
        Criteria cri = HibernateUtil.getSession().createCriteria(Clerk.class).createAlias("clerkRole", "clerkRole");
        cri.createAlias("customer", "customer", JoinType.LEFT_OUTER_JOIN);
        cri.createAlias("merchant", "merchant", JoinType.LEFT_OUTER_JOIN);
        cri.createAlias("terminal", "terminal", JoinType.LEFT_OUTER_JOIN);
        Disjunction or = Restrictions.disjunction();
        switch (entityType) {
            case CUSTOMER:

                cri.createAlias("merchant.customer", "merchantCustomer", JoinType.LEFT_OUTER_JOIN);
                cri.createAlias("terminal.merchant", "terminalMerchant", JoinType.LEFT_OUTER_JOIN);
                cri.createAlias("terminalMerchant.customer", "terminalMerchantCustomer", JoinType.LEFT_OUTER_JOIN);
                or.add(Restrictions.eq("customer.id", idEntity));
                or.add(Restrictions.eq("merchantCustomer.id", idEntity));
                or.add(Restrictions.eq("terminalMerchantCustomer.id", idEntity));
                cri.add(or);
                break;
            case MERCHANT:
                // cri.createAlias("merchant", "merchant", JoinType.LEFT_OUTER_JOIN);
                //cri.createAlias("terminal", "terminal", JoinType.LEFT_OUTER_JOIN);
                cri.createAlias("terminal.merchant", "terminalMerchant", JoinType.LEFT_OUTER_JOIN);
                or.add(Restrictions.eq("merchant.id", idEntity));
                or.add(Restrictions.eq("terminalMerchant.id", idEntity));
                cri.add(or);
                break;
            case TERMINAL:
                //cri.createAlias("terminal", "terminal");
                cri.add(Restrictions.eq("terminal.id", idEntity));
                break;
        }
        if (firstResult >= 0) {
            cri.setFirstResult(firstResult);
            cri.setMaxResults(maxResult);
        }
        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("username", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("firstName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("lastName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("clerkRole.name", searchFilter, MatchMode.ANYWHERE).ignoreCase());
            cri.add(disjunction);
        }
        return cri;
    }

    /**
     * Get clerks by Entity
     *
     * @param idEntity
     * @param entityType
     * @param firstResult
     * @param maxResult
     * @return Collection<Clerk>
     */
    public Collection<Clerk> getClerksByEntityType(int idEntity, EntityType entityType, int firstResult, int maxResult) {
        List<Clerk> clerks = new LinkedList<Clerk>();
        switch (entityType) {
            case CUSTOMER:
                clerks = (List<Clerk>) CustomerDAO.get().findById(idEntity).getClerk();
                break;
            case MERCHANT:
                clerks = (List<Clerk>) MerchantDAO.get().findById(idEntity).getClerk();
                break;
            case TERMINAL:
                clerks = (List<Clerk>) TerminalDAO.get().findById(idEntity).getClerk();
                break;
        }
        for (Clerk clerk : clerks) {
            clerk.getId();
        }
        return clerks;
    }

    /**
     * Update a Clerk
     *
     * @param clerkDisplay
     * @param application
     */
    public void updateClerk(UserDisplay clerkDisplay, NomApplication application) {
        Clerk clerk = findById(clerkDisplay.getId());
        clerk.setCustomer(null);
        clerk.setMerchant(null);
        clerk.setTerminal(null);
        if (clerkDisplay.getCustomer() != null) {
            clerk.setCustomer(CustomerDAO.get().findById(clerkDisplay.getCustomer().getId()));
        }
        if (clerkDisplay.getMerchant() != null) {
            clerk.setMerchant(MerchantDAO.get().findById(clerkDisplay.getMerchant().getId()));
        }
        if (clerkDisplay.getTerminal() != null) {
            clerk.setTerminal(TerminalDAO.get().findById(clerkDisplay.getTerminal().getId()));
        }
        switch (application) {
            case VT_APPLICATION: {
                clerk.setFirstName(clerkDisplay.getFirstName());
                clerk.setLastName(clerkDisplay.getLastName());
                break;
            }                
        }
        
        clerk.setClerkRole((ClerkRole) HibernateUtil.getSession().get(ClerkRole.class, clerkDisplay.getRole().getId()));
        HibernateUtil.getSession().saveOrUpdate(clerk);
    }

    /**
     * Complete clerk's First Time Installation
     *
     * @param clerk
     */
    public void completeFirstTimeInstall(UserDisplay clerk) {
        Clerk c = findById(clerk.getId());
        c.setFirstTime(false);
        HibernateUtil.getSession().saveOrUpdate(c);
    }

    public Clerk findByUsername(String username) {
        Criteria cri = HibernateUtil.getSession().createCriteria(Clerk.class).add(Restrictions.eq("username", username).ignoreCase());
        return (Clerk) cri.uniqueResult();
    }

    public List<Clerk> listActive() {
        Criteria cri = HibernateUtil.getSession().createCriteria(Clerk.class)
                .add(Restrictions.eq("active", true));
        return cri.list();
    }
}
