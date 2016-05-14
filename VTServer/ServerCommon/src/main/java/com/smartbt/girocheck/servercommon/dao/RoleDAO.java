

package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.Role;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.display.common.model.RoleDisplay;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @Alejo
 */
public class RoleDAO extends BaseDAO<Role> {
    
    protected static RoleDAO dao;
    
    public RoleDAO() {
        super();
    }
    
    public static RoleDAO get() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }
    
    public List<Role> findRolesByNameAndEntity(EntityType entityType, String name) {
            return HibernateUtil.getSession().createCriteria(Role.class).add(Restrictions.eq("name", name).ignoreCase()).list();
    }

    public List<RoleDisplay> getRoles(EntityType entityType) {
        
        List<RoleDisplay> roleDisplays = new LinkedList<RoleDisplay>();
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
//                .add(Projections.property("active").as("active"))
                .add(Projections.property("description").as("description"));
//        switch (entityType) {
//            case CLERK: {
//                Criteria criteria = HibernateUtil.getSession().createCriteria(ClerkRole.class);
//                criteria.setProjection(projectionList);
//                criteria.setResultTransformer(Transformers.aliasToBean(RoleDisplay.class));
//                roleDisplays = criteria.list();
//                break;
//            }
//            case AMS: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(Role.class);
                criteria.setProjection(projectionList);
                criteria.setResultTransformer(Transformers.aliasToBean(RoleDisplay.class));
                roleDisplays = criteria.list();
//                break;
//            }
//        }
        return roleDisplays;
    }

    public void deleteRole(int idRole) {
        String sql = "";
//        switch (entityType) {
//            case CLERK: {
//                sql = "delete from clerk_role where id = :id";
//                break;
//            }
//            case AMS: {
                sql = "delete from role_privilege where role = :id";
//                break;
//            }
//        }

        if (!sql.isEmpty()) {
            Query query = HibernateUtil.getSession().createSQLQuery(sql);
            query.setParameter("id", idRole);
            query.executeUpdate();
        }
        
        super.delete(findById(idRole));
    }

    public void updateRole(EntityType entityType, int idRole, String name, String description) {
//        switch (entityType) {
//            case CLERK: {
//                ClerkRole role = (ClerkRole) HibernateUtil.getSession().get(ClerkRole.class, idRole);
//                role.setDescription(description);
//                role.setName(name);
//                // role.setActive(roleDisplay.getActive());
//                HibernateUtil.getSession().saveOrUpdate(role);
//                break;
//            }
//            case AMS: {
                Role role = (Role) HibernateUtil.getSession().get(Role.class, idRole);
                role.setDescription(description);
                role.setName(name);
                HibernateUtil.getSession().saveOrUpdate(role);
//                break;
//            }
//        }
    }

    public void addRole(EntityType entityType, String name, String description) {
//        switch (entityType) {
//            case CLERK: {
//                ClerkRole role = new ClerkRole();
//                role.setDescription(description);
//                role.setName(name);
//                //role.setActive(roleDisplay.getActive());
//                HibernateUtil.getSession().saveOrUpdate(role);
//                break;
//            }
//            case AMS: {
                Role role = new Role();
                role.setDescription(description);
                role.setName(name);
                // role.setActive(roleDisplay.getActive());
                HibernateUtil.getSession().saveOrUpdate(role);
//                break;
//            }
//        }
    }
    
}
