/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author suresh
 */
public class CardDao extends BaseDAO<CreditCard> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CardDao.class);
    protected static CardDao dao;

    public CardDao() {
    }

    public static CardDao get() {
        if (dao == null) {
            dao = new CardDao();
        }
        return dao;
    }
    
    public CreditCard getCardNumberByClientId(int clientId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(CreditCard.class).
                 createAlias( "client", "client" ) 
                 .add(Restrictions.eq("client.id", clientId));
        return (CreditCard) criteria.uniqueResult();
    }
   

}

    
    

