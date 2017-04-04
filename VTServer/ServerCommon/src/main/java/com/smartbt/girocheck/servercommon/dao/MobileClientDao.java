/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.display.mobile.MobileClientDisplay;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

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

    public MobileClientDisplay getMobileClientDisplayByUserAndPassword(String userName, String password) {
        
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .createAlias("client", "client")
                .add(Restrictions.eq("userName", userName))
                .add(Restrictions.eq("password", password))
                .setMaxResults(1);
        
         ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("clientId"))
                .add(Projections.property("card.cardNumber").as("card"))
                .add(Projections.property("client.firstName").as("clientName"))
                .add(Projections.property("client.email").as("clientEmail"))
                .add(Projections.property("client.telephone").as("clientPhone"))
                .add(Projections.property("userName").as("mobileClientUserName"));
                
        
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(MobileClientDisplay.class));
         
        return (MobileClientDisplay)criteria.uniqueResult();
    }
    
    public String getCardNumberByMobileClientId(Integer mobileClientId){
        return (String)HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .add(Restrictions.eq("id", mobileClientId))
                .setProjection(Projections.property("card.cardNumber"))
                .uniqueResult(); 
    }

    public MobileClient getMobileClientById(int clientId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .add(Restrictions.eq("id", clientId));
        return (MobileClient) criteria.uniqueResult();
    }

    public MobileClient saveOrUpdateMobileClient(MobileClient client) {
        HibernateUtil.getSession().saveOrUpdate(client);
        return client;
    }
    
    public Boolean existMobileClientBySSN(String ssn){
        return (Long)HibernateUtil.getSession().createCriteria(MobileClient.class).
                createAlias("client", "client")
                .add(Restrictions.eq("client.ssn", ssn))
                .setProjection(Projections.rowCount())
                .uniqueResult() > 0;
    }
    
    public Boolean existMobileClientByUsername(String userName){
        return (Long)HibernateUtil.getSession().createCriteria(MobileClient.class) 
                .add(Restrictions.eq("userName", userName))
                .setProjection(Projections.rowCount())
                .uniqueResult() > 0;
    }
    
    public Boolean existMobileAssociatedToCard(String cardNumber){
        return (Long)HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .add(Restrictions.eq("card.cardNumber", cardNumber))
                .setProjection(Projections.rowCount())
                .uniqueResult() > 0;
    }
    
    public MobileClient getMobileClientByCardNumberAndMaskSSN(String maskSSN,String cardNumber) {
        
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .createAlias("client", "client")
                .add(Restrictions.eq("card.cardNumber", cardNumber))
                .add(Restrictions.eq("client.maskSSN", maskSSN))
                .setMaxResults(1);        
         
        return (MobileClient)criteria.uniqueResult();
    }
    
    public MobileClient getMobileClientByTelphone(String recipentNumber) {
        
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("client", "client")
                .add(Restrictions.eq("client.telephone", recipentNumber))               
                .setMaxResults(1);        
         
        return (MobileClient)criteria.uniqueResult();
    }
}
