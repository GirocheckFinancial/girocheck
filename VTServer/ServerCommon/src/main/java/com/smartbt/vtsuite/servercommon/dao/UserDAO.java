/*
 ** File: UserDAO.java
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
import com.smartbt.vtsuite.servercommon.display.common.model.UserDisplay;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.User;
import com.smartbt.vtsuite.servercommon.model.UserRole;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class UserDAO extends BaseDAO<User> {

    protected static UserDAO dao;

    public UserDAO() {
        //super(User.class);
    }

    public static UserDAO get() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    /**
     * Update a User
     *
     * @param user
     *
     */
    public void updateUser(UserDisplay user) {
        User u = findById(user.getId());
        u.setUserRole((UserRole) HibernateUtil.getSession().get(UserRole.class, user.getRole().getId()));
        HibernateUtil.getSession().saveOrUpdate(u);
    }

    /**
     * Search all the Users by a given filter
     *
     * @param search
     * @param firstResult
     * @param maxResult
     * @return
     *
     */
    public List<UserDisplay> searchUsers(String search, int firstResult, int maxResult) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(User.class).createAlias("userRole", "userRole");

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("username").as("username"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("active").as("active"))
                .add(Projections.property("userRole.id").as("role.id"))
                .add(Projections.property("userRole.name").as("role.name"));

        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }

        if (search != null && !search.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("username", search, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("lastName", search, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("firstName", search, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("userRole.name", search, MatchMode.ANYWHERE).ignoreCase());
            criteria.add(disjunction);
        }

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new TransformerComplexBeans(UserDisplay.class));
        return criteria.list();
    }

    public User findByEmail(String email) {
        Criteria cri = HibernateUtil.getSession().createCriteria(User.class).add(Restrictions.eq("email", email));
        return (User) cri.uniqueResult();
    }
}
