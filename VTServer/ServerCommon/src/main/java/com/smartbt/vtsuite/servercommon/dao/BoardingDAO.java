/*
 ** File: BoardingDAO.java
 **
 ** Date Created: March 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.BoardingDisplay;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Boarding;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.vtsuite.servercommon.validators.utils.Validator;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ariel Saavedra
 */
public class BoardingDAO extends BaseDAO<Boarding> {

    protected static BoardingDAO dao;

    public BoardingDAO() {
        // super(Boarding.class);
    }

    public static BoardingDAO get() {
        if (dao == null) {
            dao = new BoardingDAO();
        }
        return dao;
    }

    /**
     * Search all BoardingStatus by a given filter
     *
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<BoardingDisplay> searchBoardingStatus(String searchFilter, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult) {
        List<BoardingDisplay> boardings;

        Criteria criteria = HibernateUtil.getSession().createCriteria(Boarding.class);
        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }

        if (startRangeDate != null) {
            criteria.add(Restrictions.ge("activationDate", startRangeDate));
        }
        if (endRangeDate != null) {
            endRangeDate.setHours(24);
            criteria.add(Restrictions.le("activationDate", endRangeDate));
        }

        ProjectionList projectionList = Projections.projectionList().add(Projections.property("id").as("id"))
                .add(Projections.property("activationDate").as("activationDate"))
                .add(Projections.property("active").as("active"))
                .add(Projections.property("boarded").as("boarded"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("merchantNumber").as("merchantNumber"))
                .add(Projections.property("customerNumber").as("customerNumber"))
                .add(Projections.property("terminalID").as("terminalID"))
                .add(Projections.property("description").as("description"))
                .add(Projections.property("sicCode").as("sicCode"));
        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction().add(Restrictions.like("boarded", searchFilter, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("merchantNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("customerNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("terminalID", searchFilter, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("description", searchFilter, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("name", searchFilter, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("sicCode", searchFilter, MatchMode.ANYWHERE).ignoreCase());
           
            Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter,"activationDate");
            if (dateRestriction != null) {
                disjunction.add(dateRestriction);
            }           
            criteria.add(disjunction);
        }
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(BoardingDisplay.class));

        boardings = criteria.list();
        return boardings;
    }
}
