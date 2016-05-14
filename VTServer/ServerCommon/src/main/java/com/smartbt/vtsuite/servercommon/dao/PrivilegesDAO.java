/*
 ** File: PrivilegesDAO.java
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

import com.smartbt.vtsuite.servercommon.display.common.model.ApplicationPrivilegeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.PrivilegeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.RolePrivilegeDisplay;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.ClerkPrivilege;
import com.smartbt.vtsuite.servercommon.model.ClerkRole;
import com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege;
import com.smartbt.vtsuite.servercommon.model.UserPrivilege;
import com.smartbt.vtsuite.servercommon.model.UserRole;
import com.smartbt.vtsuite.servercommon.model.UserRolePrivilege;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CLERK;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ariel Saavedra
 */
public class PrivilegesDAO {

    protected static PrivilegesDAO dao;

    public static PrivilegesDAO get() {
        if (dao == null) {
            dao = new PrivilegesDAO();
        }
        return dao;
    }

    public List<RolePrivilegeDisplay> searchRolePrivileges(EntityType entityType, boolean entityNotContain, int idRole, int firstResult, int maxResult) {
        List<RolePrivilegeDisplay> rolePrivilegeDisplays = null;

        // List<Object> rolePrivilegeDisplays = null;
        ProjectionList projectionContainList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("role.id").as("role.id"))
                .add(Projections.property("privilege.id").as("privilege.id"))
                .add(Projections.property("privilege.name").as("privilege.name"))
                .add(Projections.property("privilege.description").as("privilege.description"));

        ProjectionList projectionNotContainList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("description").as("description"));

        Criteria criteria = null;
        switch (entityType) {
            case CLERK: {
                if (entityNotContain) {
                    Criteria criteriaPrivilegesIds = HibernateUtil.getSession().createCriteria(ClerkRolePrivilege.class)
                            .createAlias("clerkPrivilege", "privilege")
                            .createAlias("clerkRole", "role")
                            .add(Restrictions.eq("role.id", idRole));

                    criteriaPrivilegesIds.setProjection(Projections.projectionList().add(Projections.property("privilege.id")));
                    List<Integer> privilegesIds = criteriaPrivilegesIds.list();

                    criteria = HibernateUtil.getSession().createCriteria(ClerkPrivilege.class).addOrder(Order.asc("name"));
                    if (!privilegesIds.isEmpty()) {
                        criteria.add(Restrictions.not(Restrictions.in("id", privilegesIds)));
                    }

                    criteria.setProjection(projectionNotContainList);
                    criteria.setResultTransformer(Transformers.aliasToBean(PrivilegeDisplay.class));
                } else {
                    criteria = HibernateUtil.getSession().createCriteria(ClerkRolePrivilege.class)
                            .createAlias("clerkPrivilege", "privilege")
                            .createAlias("clerkRole", "role")
                            .add(Restrictions.eq("role.id", idRole))
                            .addOrder(Order.asc("privilege.name"));

                    criteria.setProjection(projectionContainList);
                    criteria.setResultTransformer(new TransformerComplexBeans(RolePrivilegeDisplay.class));
                }
                break;
            }
            case AMS: {
                if (entityNotContain) {
                    Criteria criteriaPrivilegesIds = HibernateUtil.getSession().createCriteria(UserRolePrivilege.class)
                            .createAlias("userPrivilege", "privilege")
                            .createAlias("userRole", "role")
                            .add(Restrictions.eq("role.id", idRole));

                    criteriaPrivilegesIds.setProjection(Projections.projectionList().add(Projections.property("privilege.id")));
                    List<Integer> privilegesIds = criteriaPrivilegesIds.list();

                    criteria = HibernateUtil.getSession().createCriteria(UserPrivilege.class).addOrder(Order.asc("name"));
                    if (!privilegesIds.isEmpty()) {
                        criteria.add(Restrictions.not(Restrictions.in("id", privilegesIds)));
                    }

                    criteria.setProjection(projectionNotContainList);
                    criteria.setResultTransformer(Transformers.aliasToBean(PrivilegeDisplay.class));
                } else {
                    criteria = HibernateUtil.getSession().createCriteria(UserRolePrivilege.class)
                            .createAlias("userPrivilege", "privilege")
                            .createAlias("userRole", "role")
                            .add(Restrictions.eq("role.id", idRole))
                            .addOrder(Order.asc("privilege.name"));

                    criteria.setProjection(projectionContainList);
                    criteria.setResultTransformer(new TransformerComplexBeans(RolePrivilegeDisplay.class));
                }
                break;
            }
        }
        if (criteria != null) {
            if (firstResult >= 0) {
                criteria.setFirstResult(firstResult);
                criteria.setMaxResults(maxResult);
            }

            rolePrivilegeDisplays = criteria.list();
        }

        // ****  This is temporary  ****  Need to be improved with Hibernate Criteria        
        // Adding applications to privileges
        if (rolePrivilegeDisplays != null && entityType == EntityType.CLERK) {
            int idPrivilege = 0;
            RolePrivilegeDisplay rolePrivilegeDisplay = null;
            PrivilegeDisplay privilegeDisplay = null;

            for (int i = 0; i < rolePrivilegeDisplays.size(); i++) {
                Object object = rolePrivilegeDisplays.get(i);
                if (object instanceof RolePrivilegeDisplay) {
                    rolePrivilegeDisplay = (RolePrivilegeDisplay) object;
                    idPrivilege = rolePrivilegeDisplay.getPrivilege().getId();
                } else if (object instanceof PrivilegeDisplay) {
                    privilegeDisplay = (PrivilegeDisplay) object;
                    idPrivilege = privilegeDisplay.getId();
                }

                //Find Privilege's Applications
                Criteria critAppl = HibernateUtil.getSession().createCriteria(ApplicationClerkPrivilege.class)
                        .createAlias("clerkPrivilege", "clerkPrivilege")
                        .add(Restrictions.eq("clerkPrivilege.id", idPrivilege));
                List<ApplicationClerkPrivilege> applicationClerkPrivilegeList = critAppl.list();

                //Fill ApplicationPrivilegeDisplay object to be attach to RolePrivilegeDisplay's privilege object
                List<ApplicationPrivilegeDisplay> applicationPrivilegeDisplayList = new ArrayList<ApplicationPrivilegeDisplay>();
                for (ApplicationClerkPrivilege applicationClerkPrivilege : applicationClerkPrivilegeList) {
                    ApplicationPrivilegeDisplay applicationPrivilegeDisplay = new ApplicationPrivilegeDisplay();
                    Application application = new Application();
                    application.setId(applicationClerkPrivilege.getApplication().getId());
                    application.setName(applicationClerkPrivilege.getApplication().getName());
                    applicationPrivilegeDisplay.setApplication(application);
                    applicationPrivilegeDisplayList.add(applicationPrivilegeDisplay);
                }
                if (rolePrivilegeDisplay != null) {
                    rolePrivilegeDisplay.getPrivilege().setApplicationClerkPrivilege(applicationPrivilegeDisplayList);
                } else {
                    privilegeDisplay.setApplicationClerkPrivilege(applicationPrivilegeDisplayList);
                }
            }
        }
        return rolePrivilegeDisplays;
    }

    public void addRolePrivilege(EntityType entityType, int idRole, int idPrivilege) {
        switch (entityType) {
            case CLERK: {
                ClerkRolePrivilege clerkRolePrivilege = new ClerkRolePrivilege();
                ClerkRole clerkRole = (ClerkRole) HibernateUtil.getSession().get(ClerkRole.class, idRole);
                ClerkPrivilege clerkPrivilege = (ClerkPrivilege) HibernateUtil.getSession().get(ClerkPrivilege.class, idPrivilege);
                clerkRolePrivilege.setClerkPrivilege(clerkPrivilege);
                clerkRolePrivilege.setClerkRole(clerkRole);
                HibernateUtil.getSession().saveOrUpdate(clerkRolePrivilege);
                break;
            }
            case AMS: {
                UserRolePrivilege userRolePrivilege = new UserRolePrivilege();
                UserRole clerkRole = (UserRole) HibernateUtil.getSession().get(UserRole.class, idRole);
                UserPrivilege clerkPrivilege = (UserPrivilege) HibernateUtil.getSession().get(UserPrivilege.class, idPrivilege);
                userRolePrivilege.setUserPrivilege(clerkPrivilege);
                userRolePrivilege.setUserRole(clerkRole);
                HibernateUtil.getSession().saveOrUpdate(userRolePrivilege);
                break;
            }
        }
    }

    public void deleteRolePrivilege(EntityType entityType, int idRolePrivilege) {
        String sql = "";
        switch (entityType) {
            case CLERK: {
                sql = "delete from clerk_role_privilege where id = :id ";
                break;
            }
            case AMS: {
                sql = "delete from user_role_privilege where id = :id ";
                break;
            }
        }
        if (!sql.isEmpty()) {
            Query query = HibernateUtil.getSession().createSQLQuery(sql);
            query.setParameter("id", idRolePrivilege);
            query.executeUpdate();
        }
    }

    public boolean existRolePrivilege(EntityType entityType, int idRole, int idPrivilege) {
        boolean ret = false;
        switch (entityType) {
            case CLERK: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(ClerkRolePrivilege.class)
                        .createAlias("clerkRole", "clerkRole")
                        .createAlias("clerkPrivilege", "clerkPrivilege")
                        .add(Restrictions.eq("clerkPrivilege.id", idPrivilege))
                        .add(Restrictions.eq("clerkRole.id", idRole));
                ret = !criteria.list().isEmpty();
                break;
            }
            case AMS: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(UserRolePrivilege.class)
                        .createAlias("userRole", "userRole")
                        .createAlias("userPrivilege", "userPrivilege")
                        .add(Restrictions.eq("userPrivilege.id", idPrivilege))
                        .add(Restrictions.eq("userRole.id", idRole));
                ret = !criteria.list().isEmpty();
                break;
            }
        }
        return ret;
    }
}
