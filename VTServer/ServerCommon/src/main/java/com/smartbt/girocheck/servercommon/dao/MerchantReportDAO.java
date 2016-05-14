
package com.smartbt.girocheck.servercommon.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.display.MerchantReportDisplay;
import com.smartbt.girocheck.servercommon.model.ReportFilters;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.girocheck.servercommon.model.Merchant;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Alejo
 */


public class MerchantReportDAO {
    
    protected static MerchantReportDAO dao;
    ObjectMapper objectMapper = new ObjectMapper();
    
        public static MerchantReportDAO get() {
        if ( dao == null ) {
            dao = new MerchantReportDAO();
        }
        return dao;
    }
        
    public int saveReportFilters(ReportFilters filters){
        System.out.println("[MerchantReportDAO] saveReportFilters()");
        filters.setCreateAt(new Date());
        Session session = HibernateUtil.getSession();
        session.saveOrUpdate( filters );
        session.flush();

        return filters.getId();
    }
    
    public ReportFilters getReportFilters(int id){
        System.out.println("[MerchantReportDAO] getReportFilters()");
        Criteria cri = HibernateUtil.getSession().createCriteria( ReportFilters.class )
                .add( Restrictions.eq( "id", id ) );
        
        ReportFilters filters = (ReportFilters)cri.uniqueResult();
        
        long created = filters.getCreateAt().getTime()+180000;//3 min
        long actualTime = new Date().getTime();
        
        System.out.println(" [MerchantReportDAO] ACTUAL TIME: "+ actualTime +" CREATED TIME: " + created);
        
        if(actualTime > created){
            return null;
        } 
        
        Session session = HibernateUtil.getSession();
            session.delete(filters);
            session.flush();
        
        return filters;
        
    }
    
      
    public String getReport(int entityId) throws Exception {
        
        List<MerchantReportDisplay> merchantReport;

            merchantReport = detailsReport(entityId);

       return objectMapper.writeValueAsString( merchantReport );
       
    }

    public List<MerchantReportDisplay> detailsReport(int entityId) {

        System.out.println(" [MerchantReportDAO] detailsReport()");
        
        Criteria cri = HibernateUtil.getSession().createCriteria( Merchant.class )
                .createAlias( "agrupation", "customer", JoinType.LEFT_OUTER_JOIN)
                .createAlias("address", "merchantAddress", JoinType.LEFT_OUTER_JOIN)
                .createAlias("merchantAddress.state", "merchantState", JoinType.LEFT_OUTER_JOIN)
                .add( Restrictions.eq( "customer.id", entityId ) );


        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "legalName" ).as( "legalName" ) )
                .add( Projections.property( "phone" ).as( "phone" ) )
                .add( Projections.property( "merchantAddress.address" ).as( "address" ) )
                .add( Projections.property( "merchantAddress.city" ).as( "city" ) )
                .add( Projections.property( "merchantState.name" ).as( "state" ) )
                .add( Projections.property( "merchantAddress.zipcode" ).as( "zipcode" ) )
                .add( Projections.property( "sic" ).as( "sic" ) )
                .add( Projections.property( "description" ).as( "description" ) )
                .add( Projections.property( "idPosOrderExp" ).as( "idPosOrderExp" ) )
                .add( Projections.property( "idTellerOrderExp" ).as( "idTellerOrderExp" ) )
                .add( Projections.property( "oEAgentNumber" ).as( "oEAgentNumber" ) )
                .add( Projections.property( "customer.name" ).as( "customerName" ) );


        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( MerchantReportDisplay.class ) );
        
        List<MerchantReportDisplay> merchantReportDisplays = cri.list();
        
        return merchantReportDisplays;
    }
    
}
