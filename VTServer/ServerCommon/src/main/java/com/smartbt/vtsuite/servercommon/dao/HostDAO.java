/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.Host; 
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Carlos
 */
public class HostDAO extends BaseDAO<Host> {

    protected static HostDAO dao;

    public HostDAO() {
        //super(Application.class);
    }

    public static HostDAO get() {
        if (dao == null) {
            dao = new HostDAO();
        }
        return dao;
    }

    /**
     * Get host from terminal and mode.
     *
     * @param terminalId The terminal id
     * @param modeName The transaction mode
     * @return Host object
     *
     */
    public Host getHostByMerchantMode(Integer terminalId, String modeName) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Host.class, "Host").
                createAlias("Host.hostMode", "HostMode").
                createAlias("HostMode.mode", "Mode").
                createAlias("HostMode.hostModeMerchant", "HostModeMerchant").
                createAlias("HostModeMerchant.merchant", "Merchant").
                createAlias("Merchant.terminal", "Terminal").
                add(Restrictions.eq("Terminal.id", terminalId)).
                add(Restrictions.eq("Mode.name", modeName).ignoreCase());

        return (Host) criteria.uniqueResult();
    }
}
