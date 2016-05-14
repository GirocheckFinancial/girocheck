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
package com.smartbt.girocheck.servercommon.dao;

//import com.smartbt.vtsuite.servercommon.display.common.model.ApplicationPrivilegeDisplay;
import com.smartbt.girocheck.servercommon.display.PrivilegeDisplay;
import com.smartbt.girocheck.servercommon.display.RolePrivilegeDisplay;
import com.smartbt.girocheck.servercommon.model.Privilege;
import com.smartbt.girocheck.servercommon.model.Role;
import com.smartbt.girocheck.servercommon.model.RolePrivilege;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
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
 * @author Alejo
 */
public class PrivilegesDAO extends BaseDAO<Privilege>{

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
//        switch (entityType) {
//            case CLERK: {
//                if (entityNotContain) {
//                    Criteria criteriaPrivilegesIds = HibernateUtil.getSession().createCriteria(ClerkRolePrivilege.class)
//                            .createAlias("clerkPrivilege", "privilege")
//                            .createAlias("clerkRole", "role")
//                            .add(Restrictions.eq("role.id", idRole));
//
//                    criteriaPrivilegesIds.setProjection(Projections.projectionList().add(Projections.property("privilege.id")));
//                    List<Integer> privilegesIds = criteriaPrivilegesIds.list();
//
//                    criteria = HibernateUtil.getSession().createCriteria(ClerkPrivilege.class).addOrder(Order.asc("name"));
//                    if (!privilegesIds.isEmpty()) {
//                        criteria.add(Restrictions.not(Restrictions.in("id", privilegesIds)));
//                    }
//
//                    criteria.setProjection(projectionNotContainList);
            //       criteria.setResultTransformer(Transformers.aliasToBean(PrivilegeDisplay.class));
//                } else {
//                    criteria = HibernateUtil.getSession().createCriteria(ClerkRolePrivilege.class)
//                            .createAlias("clerkPrivilege", "privilege")
//                            .createAlias("clerkRole", "role")
//                            .add(Restrictions.eq("role.id", idRole))
//                            .addOrder(Order.asc("privilege.name"));
//
//                    criteria.setProjection(projectionContainList);
//                    criteria.setResultTransformer(new TransformerComplexBeans(RolePrivilegeDisplay.class));
//                }
//                break;
//            }
//            case AMS: {
                if (entityNotContain) {
                    Criteria criteriaPrivilegesIds = HibernateUtil.getSession().createCriteria(RolePrivilege.class)
                            .createAlias("privilege", "privilege")
                            .createAlias("role", "role")
                            .add(Restrictions.eq("role.id", idRole));

                    criteriaPrivilegesIds.setProjection(Projections.projectionList().add(Projections.property("privilege.id")));
                    List<Integer> privilegesIds = criteriaPrivilegesIds.list(); //role privileges contain ids
                  
                    criteria = HibernateUtil.getSession().createCriteria(Privilege.class).addOrder(Order.asc("name"));
                    if (!privilegesIds.isEmpty()) {
                        criteria.add(Restrictions.not(Restrictions.in("id", privilegesIds)));
                    }

                    criteria.setProjection(projectionNotContainList);
                    criteria.setResultTransformer(Transformers.aliasToBean(PrivilegeDisplay.class));
                } else {
                    criteria = HibernateUtil.getSession().createCriteria(RolePrivilege.class)
                            .createAlias("privilege", "privilege")
                            .createAlias("role", "role")
                            .add(Restrictions.eq("role.id", idRole))
                            .addOrder(Order.asc("privilege.name"));

                    criteria.setProjection(projectionContainList);
                    criteria.setResultTransformer(new TransformerComplexBeans(RolePrivilegeDisplay.class));
                }
//                break;
//            }
//        }
        if (criteria != null) {
            if (firstResult >= 0) {
                criteria.setFirstResult(firstResult);
                criteria.setMaxResults(maxResult);
            }

            rolePrivilegeDisplays = criteria.list();
        }

        // ****  This is temporary  ****  Need to be improved with Hibernate Criteria        
        // Adding applications to privileges
//        if (rolePrivilegeDisplays != null && entityType == EntityType.CLERK) {
//            int idPrivilege = 0;
//            RolePrivilegeDisplay rolePrivilegeDisplay = null;
//            PrivilegeDisplay privilegeDisplay = null;
//
//            for (int i = 0; i < rolePrivilegeDisplays.size(); i++) {
//                Object object = rolePrivilegeDisplays.get(i);
//                if (object instanceof RolePrivilegeDisplay) {
//                    rolePrivilegeDisplay = (RolePrivilegeDisplay) object;
//                    idPrivilege = rolePrivilegeDisplay.getPrivilege().getId();
//                } else if (object instanceof PrivilegeDisplay) {
//                    privilegeDisplay = (PrivilegeDisplay) object;
//                    idPrivilege = privilegeDisplay.getId();
//                }
//
//                //Find Privilege's Applications
//                Criteria critAppl = HibernateUtil.getSession().createCriteria(ApplicationClerkPrivilege.class)
//                        .createAlias("clerkPrivilege", "clerkPrivilege")
//                        .add(Restrictions.eq("clerkPrivilege.id", idPrivilege));
//                List<ApplicationClerkPrivilege> applicationClerkPrivilegeList = critAppl.list();
//
//                //Fill ApplicationPrivilegeDisplay object to be attach to RolePrivilegeDisplay's privilege object
//                List<ApplicationPrivilegeDisplay> applicationPrivilegeDisplayList = new ArrayList<ApplicationPrivilegeDisplay>();
//                for (ApplicationClerkPrivilege applicationClerkPrivilege : applicationClerkPrivilegeList) {
//                    ApplicationPrivilegeDisplay applicationPrivilegeDisplay = new ApplicationPrivilegeDisplay();
//                    Application application = new Application();
//                    application.setId(applicationClerkPrivilege.getApplication().getId());
//                    application.setName(applicationClerkPrivilege.getApplication().getName());
//                    applicationPrivilegeDisplay.setApplication(application);
//                    applicationPrivilegeDisplayList.add(applicationPrivilegeDisplay);
//                }
//                if (rolePrivilegeDisplay != null) {
//                    rolePrivilegeDisplay.getPrivilege().setApplicationClerkPrivilege(applicationPrivilegeDisplayList);
//                } else {
//                    privilegeDisplay.setApplicationClerkPrivilege(applicationPrivilegeDisplayList);
//                }
//            }
//        }
        return rolePrivilegeDisplays;
    }

    public void addRolePrivilege(EntityType entityType, int idRole, int idPrivilege) {
//        switch (entityType) {
//            case CLERK: {
//                ClerkRolePrivilege clerkRolePrivilege = new ClerkRolePrivilege();
//                ClerkRole clerkRole = (ClerkRole) HibernateUtil.getSession().get(ClerkRole.class, idRole);
//                ClerkPrivilege clerkPrivilege = (ClerkPrivilege) HibernateUtil.getSession().get(ClerkPrivilege.class, idPrivilege);
//                clerkRolePrivilege.setClerkPrivilege(clerkPrivilege);
//                clerkRolePrivilege.setClerkRole(clerkRole);
//                HibernateUtil.getSession().saveOrUpdate(clerkRolePrivilege);
//                break;
//            }
//            case AMS: {
                RolePrivilege rolePrivilege = new RolePrivilege();
                Role role = (Role) HibernateUtil.getSession().get(Role.class, idRole);
                Privilege privilege = (Privilege) HibernateUtil.getSession().get(Privilege.class, idPrivilege);
                rolePrivilege.setPrivilege(privilege);
                rolePrivilege.setRole(role);
                HibernateUtil.getSession().saveOrUpdate(rolePrivilege);
//                break;
//            }
//        }
    }

    public void deleteRolePrivilege(int rolePrivilegeId) {
        String sql;

        sql = "delete from role_privilege where id = :id ";

        if (!sql.isEmpty()) {
            Query query = HibernateUtil.getSession().createSQLQuery(sql);
            query.setParameter("id", rolePrivilegeId);
            query.executeUpdate();
        }
    }

    public boolean existRolePrivilege(EntityType entityType, int idRole, int idPrivilege) {
        boolean ret;
//        switch (entityType) {
//            case CLERK: {
//                Criteria criteria = HibernateUtil.getSession().createCriteria(ClerkRolePrivilege.class)
//                        .createAlias("clerkRole", "clerkRole")
//                        .createAlias("clerkPrivilege", "clerkPrivilege")
//                        .add(Restrictions.eq("clerkPrivilege.id", idPrivilege))
//                        .add(Restrictions.eq("clerkRole.id", idRole));
//                ret = !criteria.list().isEmpty();
//                break;
//            }
//            case AMS: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(RolePrivilege.class)
                        .createAlias("role", "role")
                        .createAlias("privilege", "privilege")
                        .add(Restrictions.eq("privilege.id", idPrivilege))
                        .add(Restrictions.eq("role.id", idRole));
                ret = !criteria.list().isEmpty();
//                break;
//            }
//        }
        return ret;
    }
}
