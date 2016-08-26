package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.model.Host;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alejo
 */
public class HostDAO extends BaseDAO<Host> {

    protected static HostDAO dao;

    public static HostDAO get() {
        if (dao == null) {
            dao = new HostDAO();
        }
        return dao;
    }

    public Host getHostByBinNumber(String binNumber) {
        Host host = null;

        try {
            Criteria criteria = HibernateUtil.getSession().createCriteria(Host.class).
                    add(Restrictions.eq("binNumber", binNumber));

            host = (Host) criteria.uniqueResult();

            if (host == null) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[HostDAO] First attempt (eq) return null with binNumber = " + binNumber, null);

                Criteria criteria4 = HibernateUtil.getSession().createCriteria(Host.class).
                        add(Restrictions.like("binNumber", binNumber));

                host = (Host) criteria4.uniqueResult();

                if (host == null) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[HostDAO] Second attempt (like) return null with binNumber = " + binNumber, null);
                }
            }

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[HostDAO] Exception #1 in getHostByBinNumber and the exception was: ", e.getMessage());
            e.printStackTrace();

            try {
                Criteria criteria2 = HibernateUtil.getSession().createCriteria(Host.class).
                        add(Restrictions.like("binNumber", binNumber));

                host = (Host) criteria2.uniqueResult();

            } catch (Exception e2) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[HostDAO] Exception #2 in getHostByBinNumber and the exception was: ", e2.getMessage());
                e.printStackTrace();
            }
        }

        if (host == null) {
            return getDefaultHost();
        }

        return host;
    }
    
     public Host getDefaultHost() {
         Host host = null;
         try {
                Criteria criteria1 = HibernateUtil.getSession().createCriteria(Host.class).
                        add(Restrictions.eq("defaultHost", true));

                host = (Host) criteria1.uniqueResult();

                if (host == null) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[HostDAO] Never found host", null);
                } else {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[HostDAO] Found default host :: " + host.getHostName(), null);
                }
            } catch (Exception e2) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[HostDAO] Exception #3 in getHostByBinNumber and the exception was: ", e2.getMessage());
                e2.printStackTrace();
            }
         return host;
     }

}
