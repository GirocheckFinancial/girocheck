/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.criterion.Restrictions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.model.FiltersReport;
import org.hibernate.Session;

/**
 *
 * @author Roberto Rodriguez
 */
public class ReportDAO extends BaseDAO<Transaction>{
        protected static ReportDAO dao;
        ObjectMapper objectMapper = new ObjectMapper();

    public ReportDAO() {
    }

    public static ReportDAO get() {
        if ( dao == null ) {
            dao = new ReportDAO();
        }
        return dao;
    }
    
    public int saveFiltersReport(FiltersReport filters){
        System.out.println("ReportDAO -> saveFiltersReport()");  
        Session session = HibernateUtil.getSession();
        session.saveOrUpdate( filters );
        session.flush();

        return filters.getId();
    }
    
    public FiltersReport getFiltersReport(int id){
        System.out.println("ReportDAO -> getFiltersReport()");
        
        return (FiltersReport)HibernateUtil.getSession().createCriteria( FiltersReport.class )
                .add( Restrictions.eq( "id", id )).uniqueResult();
          
    }
     
}
