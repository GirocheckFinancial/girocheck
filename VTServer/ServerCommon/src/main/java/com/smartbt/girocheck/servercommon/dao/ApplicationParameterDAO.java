

package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.ApplicationParameterDisplay;
import com.smartbt.girocheck.servercommon.model.ApplicationParameter;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.enums.ApplicationType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;


/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */public class ApplicationParameterDAO extends BaseDAO<ApplicationParameter> {
    
    protected static ApplicationParameterDAO dao;
    
    public ApplicationParameterDAO() {
    }
    
    public static ApplicationParameterDAO get() {
        if (dao == null) {
            dao = new ApplicationParameterDAO();
        }
        return dao;
    }
    
    public List<ApplicationParameter> listParameters(){
        Criteria criteria = HibernateUtil.getSession().createCriteria(ApplicationParameter.class);
        List<ApplicationParameter>  list = criteria.list();
//        System.out.println("size = " + list.size());
        return list;
    }
    
    public List<ApplicationParameter> listParameterValue(){
        Criteria criteria = HibernateUtil.getSession().createCriteria(ApplicationParameter.class);
        
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("name").as("name")).add( Projections.property( "value").as( "value"));
        
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(ApplicationParameter.class));
        
        List<ApplicationParameter> list = criteria.list();
        
        return list;
    }
    
    public List<ApplicationParameter> listParameterByApplication(ApplicationType applicationType){
        
        Criteria criteria = HibernateUtil.getSession().createCriteria(ApplicationParameter.class);
        criteria.add( Restrictions.eq( "application", applicationType.getId()));
        
        List<ApplicationParameter> list = criteria.list();
        return list;
    }

    
    
      @Override
    public void saveOrUpdate(ApplicationParameter applicationParameter) {
  
        
        super.saveOrUpdate(applicationParameter);
    }

    @Override
    public ApplicationParameter findById(int id) {
        Object obj = HibernateUtil.getSession().get(ApplicationParameter.class, id);
        if(obj != null)
            return (ApplicationParameter)obj;
        return null;
    }
    
      public List<ApplicationParameterDisplay> search(String searchFilter, int firstResult, int maxResult) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(ApplicationParameter.class)
                .addOrder(Order.asc("name"));

        if (maxResult > 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }
        
        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "name" ).as( "name" ) )
                .add( Projections.property( "value" ).as( "value" ) )
                .add( Projections.property( "application" ).as( "application" ) )
                .add( Projections.property( "description" ).as( "description" ) )
                .add( Projections.property( "readOnly" ).as( "readOnly" ) );
        
        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("description", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("value", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            criteria.add(disjunction);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }
        
        criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( ApplicationParameterDisplay.class ) );

        //Loading for convert to JSON
        List<ApplicationParameterDisplay> appParameters = criteria.list();
//        for (ApplicationParameter appParameter : appParameters) {
//            HibernateUtil.initialize(appParameter.getDataType());
//        }
        return appParameters;
    }

    public void delete( int applicationParameterId ) {
        super.delete( findById( applicationParameterId));
    }
    
    public ApplicationParameter getAplicationParameterByName(String name){
        Criteria criteria = HibernateUtil.getSession().createCriteria(ApplicationParameter.class)
                .add( Restrictions.eq( "name", name ));
        
        return (ApplicationParameter)criteria.uniqueResult();
    }
    
}
