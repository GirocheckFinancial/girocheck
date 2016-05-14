
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.model.Host;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alejo
 */


public class HostDAO extends BaseDAO<Host>{
    
    protected static HostDAO dao;
    
    public static HostDAO get() {
        if ( dao == null ) {
            dao = new HostDAO();
        }
        return dao;
    }
    
    public Host getHostByBinNumber( String binNumber) {
        
        Criteria criteria = HibernateUtil.getSession().createCriteria(Host.class).
                add(Restrictions.eq("binNumber", binNumber));
        
        Host host = (Host) criteria.uniqueResult();

        Criteria criteria1 = HibernateUtil.getSession().createCriteria(Host.class).
                add(Restrictions.eq("defaultHost", true));
        
        Host defaultValue = (Host) criteria1.uniqueResult();

        if(host != null){
            return host;
        }else
            return defaultValue;       
    }
    
}
