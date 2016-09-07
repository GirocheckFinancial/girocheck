package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.FeeBuckets;
import com.smartbt.girocheck.servercommon.model.FeeSchedules;
import com.smartbt.girocheck.servercommon.model.TransactionMethod;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alejo
 */


public class FeeBucketsDAO extends BaseDAO<FeeBuckets>{
    
    protected static FeeBucketsDAO dao;
    
    public static FeeBucketsDAO get() {
        if ( dao == null ) {
            dao = new FeeBucketsDAO();
        }
        return dao;
    }
     
    public FeeBuckets getFees(String merchantId, String operation, float amount){

        System.out.println("[FeeBucketsDAO] getFees() params: merchant id: " + merchantId+" operation: " + operation+" amount: " +amount);
        FeeBuckets fee;
        
           int idMerchant = Integer.parseInt(merchantId);
     
            Criteria criteria = HibernateUtil.getSession().createCriteria( FeeBuckets.class ).
                    createAlias( "feeSchedule", "feeSchedule" ).
                    createAlias( "feeSchedule.method", "method" ).
                    createAlias( "feeSchedule.merchant", "merchant" ).
                    add( Restrictions.eq( "merchant.id", idMerchant ) ).
                    add( Restrictions.eq( "method.operation", operation ) ).
                    add( Restrictions.le( "minimum", amount ) ).
                    addOrder(Order.desc("minimum") );

            criteria.setMaxResults( 1 );

            fee = (FeeBuckets)criteria.uniqueResult();
            if(fee == null){
                System.out.println("[FeeBucketsDAO] getFees() No Fee Match for the entered data. Then looking in the default FeeSchedule");
                return getDefaultFeesValues(operation, amount); 
            }
            else{
                System.out.println("[FeeBucketsDAO] getFees() fee result: "+fee.getFixed());
                 return fee;
            } 
    }
    
     public FeeBuckets getDefaultFeesValues(String operation, float amount){
        Criteria criteria = HibernateUtil.getSession().createCriteria( FeeBuckets.class ).
                    createAlias( "feeSchedule", "feeSchedule" ).
                    createAlias( "feeSchedule.method", "method" ). 
                    add( Restrictions.eq( "feeSchedule.isdefault", true ) ).
                    add( Restrictions.eq( "method.operation", operation ) ).
                    add( Restrictions.le( "minimum", amount ) ).
                    addOrder(Order.desc("minimum") );

            criteria.setMaxResults( 1 );

            return (FeeBuckets)criteria.uniqueResult();
    }
    
}
