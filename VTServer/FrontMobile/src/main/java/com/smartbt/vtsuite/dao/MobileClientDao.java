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
    
    public MobileClient getMobileClientId(int clientId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class).
                 createAlias( "client", "client" ) 
                 .add(Restrictions.eq("client.id", clientId));
               return (MobileClient) criteria.uniqueResult();
    }
    
    public MobileClient getMobileClientByUserNameAndPwd(String userName , String password) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                 .add(Restrictions.eq("userName", userName))
                 .add(Restrictions.eq("password", password));
               return (MobileClient) criteria.uniqueResult();
    }
    
    public MobileClient getMobileClientByPINAndDeviceId(String pin , String deviceId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class) 
                 .add(Restrictions.eq("pin", pin))
                 .add(Restrictions.eq("deviceId", deviceId));
               return (MobileClient) criteria.uniqueResult();
    }
    
    public MobileClient saveOrUpdateMobileClient(MobileClient client){
        HibernateUtil.getSession().saveOrUpdate(client);
        return client;
    }
}    

