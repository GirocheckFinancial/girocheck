

package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.Privilege;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class PrivilegeDAO extends BaseDAO<Privilege> {
    
    protected static PrivilegeDAO dao;
    
    public PrivilegeDAO() {
    }
    
    public static PrivilegeDAO get() {
        if (dao == null) {
            dao = new PrivilegeDAO();
        }
        return dao;
    }
    
}
