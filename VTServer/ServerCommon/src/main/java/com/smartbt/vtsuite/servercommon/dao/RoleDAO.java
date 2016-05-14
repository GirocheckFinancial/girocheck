/*
 ** File: RoleDAO.java
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

import com.smartbt.vtsuite.servercommon.display.common.model.RoleDisplay;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.ClerkRole;
import com.smartbt.vtsuite.servercommon.model.UserRole;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CLERK;
import java.util.LinkedList;
import java.util.List;
import javax.management.relation.Role;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ariel Saavedra
 */
public class RoleDAO {

    protected static RoleDAO dao;

    public static RoleDAO get() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> findRolesByNameAndEntity(EntityType entityType, String name) {
        if (entityType == EntityType.CLERK) {
            return HibernateUtil.getSession().createCriteria(ClerkRole.class).add(Restrictions.eq("name", name).ignoreCase()).list();
        }
        if (entityType == EntityType.AMS) {
            return HibernateUtil.getSession().createCriteria(UserRole.class).add(Restrictions.eq("name", name).ignoreCase()).list();
        }
        return null;
    }

    public List<RoleDisplay> getRoles(EntityType entityType) {
        List<RoleDisplay> roleDisplays = new LinkedList<RoleDisplay>();
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("active").as("active"))
                .add(Projections.property("description").as("description"));
        switch (entityType) {
            case CLERK: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(ClerkRole.class);
                criteria.setProjection(projectionList);
                criteria.setResultTransformer(Transformers.aliasToBean(RoleDisplay.class));
                roleDisplays = criteria.list();
                break;
            }
            case AMS: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(UserRole.class);
                criteria.setProjection(projectionList);
                criteria.setResultTransformer(Transformers.aliasToBean(RoleDisplay.class));
                roleDisplays = criteria.list();
                break;
            }
        }
        return roleDisplays;
    }

    public void deleteRole(EntityType entityType, int idRole) {
        String sql = "";
        switch (entityType) {
            case CLERK: {
                sql = "delete from clerk_role where id = :id";
                break;
            }
            case AMS: {
                sql = "delete from user_role where id = :id";
                break;
            }
        }

        if (!sql.isEmpty()) {
            Query query = HibernateUtil.getSession().createSQLQuery(sql);
            query.setParameter("id", idRole);
            query.executeUpdate();
        }
    }

    public void updateRole(EntityType entityType, int idRole, String name, String description) {
        switch (entityType) {
            case CLERK: {
                ClerkRole role = (ClerkRole) HibernateUtil.getSession().get(ClerkRole.class, idRole);
                role.setDescription(description);
                role.setName(name);
                // role.setActive(roleDisplay.getActive());
                HibernateUtil.getSession().saveOrUpdate(role);
                break;
            }
            case AMS: {
                UserRole role = (UserRole) HibernateUtil.getSession().get(UserRole.class, idRole);
                role.setDescription(description);
                role.setName(name);
                //role.setActive(roleDisplay.getActive());
                HibernateUtil.getSession().saveOrUpdate(role);
                break;
            }
        }
    }

    public void addRole(EntityType entityType, String name, String description) {
        switch (entityType) {
            case CLERK: {
                ClerkRole role = new ClerkRole();
                role.setDescription(description);
                role.setName(name);
                //role.setActive(roleDisplay.getActive());
                HibernateUtil.getSession().saveOrUpdate(role);
                break;
            }
            case AMS: {
                UserRole role = new UserRole();
                role.setDescription(description);
                role.setName(name);
                // role.setActive(roleDisplay.getActive());
                HibernateUtil.getSession().saveOrUpdate(role);
                break;
            }
        }
    }
}
