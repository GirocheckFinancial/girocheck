/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.criterion.Restrictions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.display.TransactionDisplay;
import com.smartbt.girocheck.servercommon.model.FiltersReport;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.girocheck.servercommon.display.report.TransactionDetailReportDisplay;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;

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
    
    
    public String detailTransactionReport(FiltersReport filters) throws JsonProcessingException {
        Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("terminal", "terminal", JoinType.LEFT_OUTER_JOIN)
                .createAlias("terminal.merchant", "merchant", JoinType.LEFT_OUTER_JOIN)
                .createAlias("merchant.agrupation", "agrupation", JoinType.LEFT_OUTER_JOIN)
                // .createAlias( "data_sc1", "data_sc1", JoinType.LEFT_OUTER_JOIN )
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .addOrder(Order.desc("dateTime"));
        
        Date startRangeDate = filters.getStartRangeDate();
        Date endRangeDate = filters.getEndRangeDate();
        String operation = filters.getOperation();
        Integer transactionType = filters.getTransactionType();
        Boolean pending = filters.getPending();
        Boolean filterAmmount = filters.getFilterAmmount();
        String ammountString = filters.getAmmountString();
        Integer ammountType = filters.getAmmountType();
        Integer opType = filters.getOpType();
        String searchFilter = filters.getSearchFilter();
        
        System.out.println("startRangeDate + " + startRangeDate);
        System.out.println("endRangeDate + " + endRangeDate);
        
        if(startRangeDate != null){
            startRangeDate.setHours(0);
            startRangeDate.setMinutes(0);
            startRangeDate.setSeconds(0);
        }
        
        if(endRangeDate != null){
            endRangeDate.setHours(23);
            endRangeDate.setMinutes(59);
            endRangeDate.setSeconds(59);
        }
         

        if (startRangeDate != null) {
            cri.add(Restrictions.ge("dateTime", startRangeDate));
        }
        if (endRangeDate != null) {
            endRangeDate.setHours(24);
            cri.add(Restrictions.le("dateTime", endRangeDate));
        }

        if (operation != null && (operation.contains("01") || operation.contains("02"))) {
            cri.add(Restrictions.like("operation", operation, MatchMode.ANYWHERE).ignoreCase());
        }

        if (transactionType != 0) {
            cri.add(Restrictions.eq("transactionType", transactionType));
        }else{
            cri.add(Restrictions.ne("transactionType", 5));
        }

        if (pending) {
            //if pending is checked gonna bring unfinished transactions
            cri.add(Restrictions.eq("transactionFinished", false));
        }

        try {
            if (filterAmmount) {
                Double ammount = Double.parseDouble(ammountString);

                String field;

                switch (ammountType) {
                    case 1:
                        field = "ammount";
                        break;
                    case 2:
                        field = "feeAmmount";
                        break;
                    case 3:
                        field = "payoutAmmount";
                        break;
                    default:
                        field = "ammount";
                }

                switch (opType) {
                    case 1:
                        cri.add(Restrictions.gt(field, ammount));
                        break;
                    case 2:
                        cri.add(Restrictions.eq(field, ammount));
                        break;
                    case 3:
                        cri.add(Restrictions.lt(field, ammount));
                        break;
                    default:
                        field = "ammount";
                }
            }
        } catch (NumberFormatException e) {
//            log.debug( "[TransactionDAO] NumberFormatException" );
            e.printStackTrace();
        }

        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    //  .add( Restrictions.like( "resultCode", searchFilter, MatchMode.ANYWHERE ).ignoreCase() )
                    .add(Restrictions.like("resultMessage", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("account", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("cardNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("errorCode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("errorCode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.legalName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idTecnicardCheck", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idTecnicardCash", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idIstreamTecnicardCash", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idIstreamTecnicardCheck", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idIstreamFuzeCash", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idIstreamFuzeCheck", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("terminal.serialNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.firstName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.ssn", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.lastName", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "dateTime");
            if (dateRestriction != null) {
//                log.debug( "[TransactionDAO] ( dateRestriction != null )" );
                disjunction.add(dateRestriction);
            }
            cri.add(disjunction);

        }

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("transactionType").as("transactionType"))
                .add(Projections.property("dateTime").as("dateTime"))
                .add(Projections.property("operation").as("operation"))
                .add(Projections.property("cardNumber").as("maskCardNumber"))
                .add(Projections.property("ammount").as("amount"))
                .add(Projections.property("feeAmmount").as("feeAmount"))
                .add(Projections.property("payoutAmmount").as("payoutAmount"))
                .add(Projections.property("requestId").as("requestId"))
                .add(Projections.property("resultCode").as("resultCode"))
                .add(Projections.property("resultMessage").as("resultMessage"))
//                .add(Projections.property("merchant.legalName").as("merchant"))
//                .add(Projections.property("terminal.serialNumber").as("terminal"))
//                .add(Projections.property("client.firstName").as("clientName"))
//                .add(Projections.property("client.lastName").as("clientLastName"));
                .add(Projections.property("client.firstName").as("client.firstName"))
                .add(Projections.property("client.lastName").as("client.lastName"));
        cri.setProjection(projectionList);
        cri.setResultTransformer(new TransformerComplexBeans(TransactionDetailReportDisplay.class));

        List<TransactionDetailReportDisplay> list = cri.list();
        
        return objectMapper.writeValueAsString( list );
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
