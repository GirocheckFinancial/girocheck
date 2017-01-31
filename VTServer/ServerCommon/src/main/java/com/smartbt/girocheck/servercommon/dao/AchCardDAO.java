package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.AchCard;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.model.Terminal;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Alejo
 */


public class AchCardDAO extends BaseDAO<AchCard>{
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AchCardDAO.class);
    
    protected static AchCardDAO dao;

    public AchCardDAO() {
    }

    public static AchCardDAO get() {
        if ( dao == null ) {
            dao = new AchCardDAO();
        }
        return dao;
    }
    
    public Boolean existAchCard(String cardNumber){ 
            Criteria criteria = HibernateUtil.getSession().createCriteria( AchCard.class )
                    .createAlias("data_sc1", "card")
                    .add( Restrictions.eq( "card.cardNumber", cardNumber ) );

            criteria.setMaxResults( 1 );
 
            return criteria.uniqueResult() != null;
    }
    
//    public Boolean existAchCard(String terminalId, String cardNumber){ 
//            Criteria criteria = HibernateUtil.getSession().createCriteria( AchCard.class )
//                    .add( Restrictions.eq( "cardNumber", cardNumber ) );
//
//            criteria.setMaxResults( 1 );
// 
//            return criteria.uniqueResult() != null;
//    }
    
    public Merchant getMerchantByTerminalId(String serialNumber){
        
        Criteria criteria = HibernateUtil.getSession().createCriteria( Terminal.class)
                .add(Restrictions.eq( "serialNumber", serialNumber ));
        
        Terminal terminal = (Terminal)criteria.uniqueResult();
        if(terminal != null){
            return terminal.getMerchant();
        }else
            return null;
        
    }
    
}
