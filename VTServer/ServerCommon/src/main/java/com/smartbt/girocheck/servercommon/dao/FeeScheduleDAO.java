
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.FeeScheduleDisplay;
import com.smartbt.girocheck.servercommon.display.TransactionMethodDisplay;
import com.smartbt.girocheck.servercommon.display.UserDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.FeeSchedules;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.TransactionMethod;
import com.smartbt.girocheck.servercommon.model.User;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

/**
 *
 * @author suresh
 */


public class FeeScheduleDAO extends BaseDAO<FeeSchedules> {
    
   private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FeeScheduleDAO.class);
    protected static FeeScheduleDAO dao;

    public FeeScheduleDAO() {
    }

    public static FeeScheduleDAO get() {
        if (dao == null) {
            dao = new FeeScheduleDAO();
        }
        return dao;
    }

    
    public ResponseDataList searchFeeSchedule(int firstResult, int maxResult) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(FeeSchedules.class);

        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult * maxResult);
            criteria.setMaxResults(maxResult);
        }

        criteria.setMaxResults(maxResult);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("merchant").as("merchant"))
                .add(Projections.property("method").as("method"))
                .add(Projections.property("isdefault").as("isdefault"));
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(FeeScheduleDisplay.class));

        List<FeeScheduleDisplay> list = (List<FeeScheduleDisplay>) criteria.list();
        List<FeeScheduleDisplay> dispalyList = new ArrayList<FeeScheduleDisplay>();
        for(int i=0;i<list.size();i++){
            FeeScheduleDisplay feeSchedule =(FeeScheduleDisplay)list.get(i);
            if(feeSchedule.getMerchant()==null){
                feeSchedule.setMerchantName("Default");
            }else{
                Merchant  merchant = (Merchant)HibernateUtil.getSession().get(Merchant.class, feeSchedule.getMerchant());
                feeSchedule.setMerchantName(String.valueOf(merchant.getLegalName()));
            }
            dispalyList.add(feeSchedule);
        }
        
        Criteria counterCriteria = HibernateUtil.getSession().createCriteria(FeeSchedules.class);
       Long total = (Long)counterCriteria.setProjection(Projections.rowCount()).uniqueResult();
        ResponseDataList response = new ResponseDataList();
        response.setData(dispalyList);
        Integer totalPages = (int) Math.ceil((float) total / (float) maxResult);;
       response.setTotalPages(totalPages);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
    
    public static void main(String[] args){
        Long total = 7L;
         int maxResult = 3;
         
        int a = (int) Math.ceil((float) total / (float) maxResult);
         
         System.out.println("a = " +a); 
    }

     public FeeScheduleDisplay addFeeSchedule(Merchant merchant,TransactionMethod method, Boolean isdefault) throws ValidationException, NoSuchAlgorithmException {
        FeeSchedules fee = new FeeSchedules();
        if(merchant!=null){
            fee.setMerchant(merchant.getId());
            fee.setMethod(method);
            fee.setIsdefault(isdefault);
            HibernateUtil.getSession().saveOrUpdate(fee);
           
        }else{
            String deafalutStr ="";
            if(isdefault==true){
                deafalutStr="b'1'";
            }else{
                deafalutStr="b'0'";
            }
            
            String sql = "INSERT INTO fee_schedules(method_id,isdefault) VALUES("+method.getId()+","+deafalutStr+")"; 
            int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();        
            //log.info("****************done- ");
        }        
       
        FeeScheduleDisplay display = new FeeScheduleDisplay();
        return display;
    }

      public FeeScheduleDisplay updateFeeSchedule(Merchant merchant,TransactionMethod method, Boolean isdefault,int id) throws ValidationException, NoSuchAlgorithmException {
        
       
         if(merchant!=null){
             String deafalutStr ="";
            FeeSchedules fee = dao.findById(id);            
            if(isdefault==true){      
                
                deafalutStr="b'1'";                 
                String sql = "UPDATE fee_schedules SET merchant_id="+merchant.getId()+",method_id="+method.getId()+",isdefault="+deafalutStr+" WHERE id="+id; 
                int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
            }else{
                deafalutStr="b'0'";
                String sql = "UPDATE fee_schedules SET merchant_id="+merchant.getId()+",method_id="+method.getId()+",isdefault="+deafalutStr+" WHERE id="+id;
                int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
            }  
           
        }else{
            String deafalutStr ="";
            if(isdefault==true){
                deafalutStr="b'0'";
                String sql = "UPDATE fee_schedules SET isdefault="+deafalutStr+"WHERE method_id="+method.getId(); 
                int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
                
                deafalutStr="b'1'";
                 
                sql = "UPDATE fee_schedules SET method_id="+method.getId()+",isdefault="+deafalutStr+" WHERE id="+id;; 
                updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
            }else{
                deafalutStr="b'0'";
                String sql = "UPDATE fee_schedules SET method_id="+method.getId()+",isdefault="+deafalutStr+" WHERE id="+id;; 
                int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
            }   
            
            //log.info("****************done- ");
        }  
        FeeScheduleDisplay display = new FeeScheduleDisplay();
        return display;
    }
   
       public void deleteFeeSchedule(int id) { 
         //  log.info("****************deleting record:- "+id);
         //dao.delete(findById(id)); 
           
         String sql = "delete from fee_buckets WHERE fee_schedule_id = " + id;
 
         int updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
         
         sql = "delete from fee_schedules WHERE id = " + id;
 
         updatedRows = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
        
       // log.info("****************deleted record:- "+id +" response ="+updatedRows);
        
    }

    
    
    public ResponseDataList searchTransactionMethod() {

        Criteria criteria = HibernateUtil.getSession().createCriteria(TransactionMethod.class);

       

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("operation").as("operation"))
                .add(Projections.property("description").as("description"));
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(TransactionMethodDisplay.class));

        List<TransactionMethodDisplay> list = (List<TransactionMethodDisplay>) criteria.list();    
        
       
        ResponseDataList response = new ResponseDataList();
        response.setData(list);       
       response.setTotalPages(null);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
    
    
    public boolean checkFeeScheduleMethodExists(Merchant merchant,int method_id){
        boolean isExists = false;
        if(merchant!=null){
            
            String hql = "FROM FeeSchedules F WHERE F.method.id="+method_id+" and F.merchant="+merchant.getId();
            Query query =  HibernateUtil.getSession().createQuery(hql);
            List results = query.list();
                if(results.size()>0){
                    isExists = true;
                    return isExists;
            }                     
                 
        }else{            
            String sql = "select * from fee_schedules where merchant_id is null and method_id="+method_id;
            SQLQuery query = HibernateUtil.getSession().createSQLQuery(sql);           
            List results = query.list();
            if(results.size()>0){
                isExists = true;
                return isExists;
            }        
        }
       
        
        return isExists;  
        
    }
    
    
    public boolean checkFeeScheduleMethodExists(Merchant merchant,int method_id,int id){
        boolean isExists = false;
        if(merchant!=null){
             FeeSchedules fee = dao.findById(id);
             if(fee.getMethod().getId()!=method_id){             
                String hql = "FROM FeeSchedules F WHERE F.method.id="+method_id+" and F.merchant="+merchant.getId();
                Query query =  HibernateUtil.getSession().createQuery(hql);
                List results = query.list();
                if(results.size()>0){
                    isExists = true;
                    return isExists;
                }  
             }            
        }else{            
            String sql = "select * from fee_schedules where merchant_id is null and id="+id;
            SQLQuery query = HibernateUtil.getSession().createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List results = query.list();
            if(results.size()>0){             

               for(Object object : results)
               {
                      Map row = (Map)object;
                      Integer dbmethod =(Integer)row.get("method_id");
                      if(dbmethod !=method_id){
                        sql = "select * from fee_schedules where merchant_id is null and method_id="+method_id;
                        query = HibernateUtil.getSession().createSQLQuery(sql);           
                        List record = query.list();
                        if(record.size()>0){
                             isExists = true;
                             return isExists;
                        }             
                
                 }              
                       
             }
                            
           }        
        }      
        
        return isExists;  
        
    }
   
}
