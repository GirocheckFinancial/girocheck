package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.FeeScheduleDisplay;
import com.smartbt.girocheck.servercommon.display.TerminalDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.FeeBuckets;
import com.smartbt.girocheck.servercommon.model.FeeBucketsDisplay;
import com.smartbt.girocheck.servercommon.model.FeeSchedules;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.model.TransactionMethod;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.xml.bind.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

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
     
    public ResponseDataList searchFeeBuckets( int idFeeSchedule,int firstResult, int maxResult ) {

        Criteria criteria = HibernateUtil.getSession().createCriteria( FeeBuckets.class )
                .createAlias( "feeSchedule", "feeSchedule" )
                .add( Restrictions.eq( "feeSchedule.id", idFeeSchedule ) );
        
        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult * maxResult);
            criteria.setMaxResults(maxResult);
        }

        criteria.setMaxResults(maxResult);

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "minimum" ).as( "minimum" ) )
                .add( Projections.property( "fixed" ).as( "fixed" ) )
                .add( Projections.property( "percentage" ).as( "percentage" ) )
                .add( Projections.property( "id" ).as( "id" ) );
        criteria.setProjection( projectionList );
        criteria.setResultTransformer(Transformers.aliasToBean(FeeBucketsDisplay.class));       
        List<FeeBucketsDisplay> bucketsDisplayList = (List<FeeBucketsDisplay>) criteria.list();

        Criteria counterCriteria = HibernateUtil.getSession().createCriteria(FeeBuckets.class);
        Long total = (Long)counterCriteria.setProjection(Projections.rowCount()).uniqueResult();
        ResponseDataList response = new ResponseDataList();
        response.setData(bucketsDisplayList);
        Integer totalPages = (int) Math.ceil((float) total / (float) maxResult);;
        response.setTotalPages(totalPages);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    } 
    
    public FeeBucketsDisplay addFeeBucket(float minimum,float percentage, float fixed,FeeSchedules feeSchedule) throws ValidationException, NoSuchAlgorithmException {

        if(feeSchedule==null){
            String sql = "INSERT INTO fee_buckets(minimum,fixed,percentage) VALUES("+minimum+","+fixed+","+percentage+")"; 
            int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
        }else{
            String sql = "INSERT INTO fee_buckets(minimum,fixed,percentage,fee_schedule_id) VALUES("+minimum+","+fixed+","+percentage+","+feeSchedule.getId()+")"; 
            int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
        }
        
        FeeBucketsDisplay display = new FeeBucketsDisplay();
        return display;
    }
    
     public FeeBucketsDisplay updateFeeBucket(float minimum, float percentage,float fixed,FeeSchedules feeSchedule,int id) throws ValidationException, NoSuchAlgorithmException {
        
        String sql = "UPDATE fee_buckets SET minimum="+minimum+",fixed="+fixed+",percentage="+percentage+" WHERE id="+id; 
        int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
        
        FeeBucketsDisplay display = new FeeBucketsDisplay();
        return display;
    }
   
       public void deleteFeeBucket(int id) {         
         
         
         String sql = "delete from fee_buckets WHERE id = " + id;
 
        int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
        
  }
     
    
}
