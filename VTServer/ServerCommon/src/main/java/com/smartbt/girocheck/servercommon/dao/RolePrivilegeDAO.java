

package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.RolePrivilege;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class RolePrivilegeDAO extends BaseDAO<RolePrivilege> {
    
    protected static RolePrivilegeDAO dao;
    
    public RolePrivilegeDAO() {
    }
    
    public static RolePrivilegeDAO get() {
        if (dao == null) {
            dao = new RolePrivilegeDAO();
        }
        return dao;
    }
    
}
