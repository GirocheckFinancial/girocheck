package com.smartbt.girocheck.servercommon.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.display.TerminalReportDisplay;
import com.smartbt.girocheck.servercommon.model.ReportFilters;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
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
public class TerminalReportDAO extends BaseDAO<Terminal> {

    protected static TerminalReportDAO dao;
    ObjectMapper objectMapper = new ObjectMapper();

    public TerminalReportDAO() {
    }

    public static TerminalReportDAO get() {
        if (dao == null) {
            dao = new TerminalReportDAO();
        }
        return dao;
    }
    
    public int saveReportFilters(ReportFilters filters){
        System.out.println("[TerminalReportDAO] saveReportFilters()");
        filters.setCreateAt(new Date());
        Session session = HibernateUtil.getSession();
        session.saveOrUpdate( filters );
        session.flush();

        return filters.getId();
    }
    
    public ReportFilters getReportFilters(int id){
        System.out.println("[TerminalReportDAO] getReportFilters()");
        Criteria cri = HibernateUtil.getSession().createCriteria( ReportFilters.class )
                .add( Restrictions.eq( "id", id ) );
        
        ReportFilters filters = (ReportFilters)cri.uniqueResult();

        if(filters != null){
        
        long created = filters.getCreateAt().getTime()+180000;//3 min
        long actualTime = new Date().getTime();

        System.out.println(" [TerminalReportDAO] ACTUAL TIME: "+ actualTime +" CREATED TIME: " + created);
        
        if(actualTime > created){
            return null;
        } 
        
        Session session = HibernateUtil.getSession();
            session.delete(filters);
            session.flush();
        }
        
        return filters;
        
    }
    
      
    public String getReport(int entityId) throws Exception {
        
        List<TerminalReportDisplay> terminalReport;

            terminalReport = detailsReport(entityId);

       return objectMapper.writeValueAsString( terminalReport );
       
    }
    
    public List<TerminalReportDisplay> detailsReport( int entityId ) {

        Criteria cri = HibernateUtil.getSession().createCriteria( Terminal.class )
                .createAlias( "merchant", "merchant", JoinType.LEFT_OUTER_JOIN )
                .add(Restrictions.eq("merchant.id", entityId));

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "merchant.legalName" ).as( "merchantName" ) )
                .add( Projections.property( "serialNumber" ).as( "serialNumber" ) )
                .add( Projections.property( "description" ).as( "description" ) );

        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( TerminalReportDisplay.class ) );

        return cri.list();
    }
    
}

