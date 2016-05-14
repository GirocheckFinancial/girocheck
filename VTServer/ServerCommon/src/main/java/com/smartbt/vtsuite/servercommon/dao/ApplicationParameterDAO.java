/*
 ** File: ApplicationParameterDAO.java
 **
 ** Date Created: January 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
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
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.servercommon.model.DataType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class ApplicationParameterDAO extends BaseDAO<ApplicationParameter> {

    protected static ApplicationParameterDAO dao;

    public static ApplicationParameterDAO get() {
        if (dao == null) {
            dao = new ApplicationParameterDAO();
        }
        return dao;
    }

    /**
     * Save or update application parameters
     *
     * @param applicationParameter
     */
    @Override
    public void saveOrUpdate(ApplicationParameter applicationParameter) {
       
        if (applicationParameter.getId() > 0) {
            ApplicationParameter appParamValidation = findById(applicationParameter.getId());
            if (appParamValidation == null || appParamValidation.isReadOnly()) {
                return;
            }
            appParamValidation.setDefaultValue(applicationParameter.getDefaultValue());
            appParamValidation.setDescription(applicationParameter.getDescription());
            appParamValidation.setParameter(applicationParameter.getParameter());
            appParamValidation.setDataType((DataType) HibernateUtil.getSession().get(DataType.class, applicationParameter.getDataType().getId()));
            
            applicationParameter = appParamValidation;
        }
        super.saveOrUpdate(applicationParameter);
    }

    /**
     * delete an application parameter
     *
     * @param applicationParameterId
     */
    public void delete(int applicationParameterId) {
        super.delete(findById(applicationParameterId));
    }

    /**
     * Search all application parameters
     *
     * @param searchFilter
     * @param firstResult
     * @param maxResult
     * @return List<ApplicationParameter>
     */
    public List<ApplicationParameter> search(String searchFilter, int firstResult, int maxResult) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(ApplicationParameter.class)
                .addOrder(Order.asc("name"));

        if (maxResult > 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }
        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("description", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("value", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            criteria.add(disjunction);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }

        //Loading for convert to JSON
        List<ApplicationParameter> appParameters = criteria.list();
//        for (ApplicationParameter appParameter : appParameters) {
//            HibernateUtil.initialize(appParameter.getDataType());
//        }
        return appParameters;
    }

}
