/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author suresh
 */
public class MobileClientDao extends BaseDAO<MobileClient> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MobileClientDao.class);
    protected static MobileClientDao dao;

    public MobileClientDao() {
    }

    public static MobileClientDao get() {
        if (dao == null) {
            dao = new MobileClientDao();
        }
        return dao;
    }
    
    public CreditCard getMobileClientId(int clientId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class).
                 createAlias( "MobileClient", "MobileClient" ) 
                 .add(Restrictions.eq("MobileClient.id", clientId));
               return (CreditCard) criteria.uniqueResult();
    }
}    

