package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.CheckDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Check;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

/**
 *
 * @author suresh
 */
public class CheckResendDAO extends BaseDAO<Check> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CheckResendDAO.class);
    protected static CheckResendDAO dao;

    public CheckResendDAO() {
    }

    public static CheckResendDAO get() {
        if (dao == null) {
            dao = new CheckResendDAO();
        }
        return dao;
    }

    public ResponseDataList searchCheckDetails(int firstResult, int maxResult, String status, Date startRangeDateStr, Date endRangeDateStr) {

        System.out.println("[CheckResendDAO] searchCheckDetails()");
        Criteria criteria = HibernateUtil.getSession().createCriteria(Check.class)
                .createAlias("transaction", "transaction", JoinType.LEFT_OUTER_JOIN);

        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult * maxResult);
            criteria.setMaxResults(maxResult);
        }

        criteria.setMaxResults(maxResult);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("transaction.ammount").as("amount"))
                .add(Projections.property("micr").as("micr"))
                .add(Projections.property("crc").as("crc"))
                .add(Projections.property("key").as("key"))
                .add(Projections.property("makerName").as("makerName"))
                .add(Projections.property("makerAddress").as("makerAddress"))
                .add(Projections.property("makerCity").as("makerCity"))
                .add(Projections.property("makerState").as("makerState"))
                .add(Projections.property("makerZip").as("makerZip"))
                .add(Projections.property("makerPhone").as("makerPhone"))
                .add(Projections.property("locationId").as("locationId"))
                .add(Projections.property("paymentCheck").as("paymentCheck"))
                .add(Projections.property("status").as("status"))
                .add(Projections.property("creationDate").as("creationDate"))
                .add(Projections.property("processingDate").as("processingDate"));

        System.out.println("startRangeDate + " + startRangeDateStr);
        System.out.println("endRangeDate + " + startRangeDateStr);
        System.out.println("status + " + status);

        if (startRangeDateStr != null) {
            startRangeDateStr.setHours(0);
            startRangeDateStr.setMinutes(0);
            startRangeDateStr.setSeconds(0);
        }

        if (endRangeDateStr != null) {
            endRangeDateStr.setHours(23);
            endRangeDateStr.setMinutes(59);
            endRangeDateStr.setSeconds(59);
        }

        if (startRangeDateStr != null && endRangeDateStr != null && status != null) {
            criteria.add(Restrictions.ge("processingDate", startRangeDateStr));
            endRangeDateStr.setHours(24);
            criteria.add(Restrictions.le("processingDate", endRangeDateStr));
            criteria.add(Restrictions.eq("status", status));
        } else if (startRangeDateStr != null && endRangeDateStr != null) {
            criteria.add(Restrictions.ge("processingDate", startRangeDateStr));
            endRangeDateStr.setHours(24);
            criteria.add(Restrictions.le("processingDate", endRangeDateStr));
        } else if (status != null && !status.isEmpty()) {
            criteria.add(Restrictions.eq("status", status));
        } else {
            criteria.add(Restrictions.eq("status", "H").ignoreCase());
        }

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(CheckDisplay.class));

        List<CheckDisplay> list = (List<CheckDisplay>) criteria.list();

        Criteria counterCriteria = HibernateUtil.getSession().createCriteria(Check.class);
        Long total = (Long) counterCriteria.setProjection(Projections.rowCount()).uniqueResult();
        ResponseDataList response = new ResponseDataList();
        response.setData(list);
        Integer totalPages = (int) Math.ceil((float) total / (float) maxResult);
        response.setTotalPages(totalPages);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;

    }

    public CheckDisplay resendCheck(int id) {
        Check check = dao.findById(id);
        check.setStatus("C");
        System.out.println("[CheckResendDAO] resendCheck()");
        HibernateUtil.getSession().saveOrUpdate(check);
        CheckDisplay display = new CheckDisplay();
        return display;
    }
    
    public Check getCheckDetails(int id){
        Check check = dao.findById(id);
        return check;
    }
}
