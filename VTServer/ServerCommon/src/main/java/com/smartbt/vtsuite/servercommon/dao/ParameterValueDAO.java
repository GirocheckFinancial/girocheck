/*
 ** File: ParameterDAO.java
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

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue;
import com.smartbt.vtsuite.servercommon.model.Customer;
import com.smartbt.vtsuite.servercommon.model.CustomerParameter;
import com.smartbt.vtsuite.servercommon.model.CustomerParameterValue;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.MerchantParameter;
import com.smartbt.vtsuite.servercommon.model.MerchantParameterValue;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.model.TerminalParameterValue;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.AMS;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.MERCHANT;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.TERMINAL;
import java.util.List;
import javax.xml.bind.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class ParameterValueDAO {

    protected static ParameterValueDAO dao;

    public static ParameterValueDAO get() {
        if (dao == null) {
            dao = new ParameterValueDAO();
        }
        return dao;
    }

    public List getNotContainedParameters(int idEntity, EntityType entityType) {
        List parameterDisplay = null;
        Criteria criteria = null;

        switch (entityType) {
            case CUSTOMER: {
                Criteria criteriaParamIds = HibernateUtil.getSession().createCriteria(Customer.class)
                        .createAlias("customerParameterValue", "customerParameterValue")
                        .createAlias("customerParameterValue.customerParameter", "parameter")
                        .add(Restrictions.idEq(idEntity));

                criteriaParamIds.setProjection(Projections.projectionList().add(Projections.property("parameter.id")));
                List<Integer> paramIds = criteriaParamIds.list();

                criteria = HibernateUtil.getSession().createCriteria(CustomerParameter.class).addOrder(Order.asc("parameter"));
                if (!paramIds.isEmpty()) {
                    criteria.add(Restrictions.not(Restrictions.in("id", paramIds)));
                }
            }
            break;
            case MERCHANT: {
                Criteria criteriaParamIds = HibernateUtil.getSession().createCriteria(Merchant.class)
                        .createAlias("merchantParameterValue", "merchantParameterValue")
                        .createAlias("merchantParameterValue.merchantParameter", "parameter")
                        .add(Restrictions.idEq(idEntity));

                criteriaParamIds.setProjection(Projections.projectionList().add(Projections.property("parameter.id")));
                List<Integer> paramIds = criteriaParamIds.list();

                criteria = HibernateUtil.getSession().createCriteria(MerchantParameter.class).addOrder(Order.asc("parameter"));
                if (!paramIds.isEmpty()) {
                    criteria.add(Restrictions.not(Restrictions.in("id", paramIds)));
                }
            }
            break;
            case TERMINAL: {
                Criteria criteriaParamIds = HibernateUtil.getSession().createCriteria(Terminal.class)
                        .createAlias("terminalParameterValues", "terminalParameterValues")
                        .createAlias("terminalParameterValues.applicationParameter", "parameter")
                        .add(Restrictions.idEq(idEntity));

                criteriaParamIds.setProjection(Projections.projectionList().add(Projections.property("parameter.id")));
                List<Integer> paramIds = criteriaParamIds.list();

                criteria = HibernateUtil.getSession().createCriteria(ApplicationParameter.class).addOrder(Order.asc("parameter"));
                if (!paramIds.isEmpty()) {
                    criteria.add(Restrictions.not(Restrictions.in("id", paramIds)));
                }
            }
            break;
            case APPLICATION_PARAMETER_VALUE: {
                Criteria criteriaParamIds = HibernateUtil.getSession().createCriteria(Application.class)
                        .createAlias("applicationParameterValues", "applicationParameterValues")
                        .createAlias("applicationParameterValues.applicationParameter", "parameter")
                        .add(Restrictions.idEq(idEntity));

                criteriaParamIds.setProjection(Projections.projectionList().add(Projections.property("parameter.id")));
                List<Integer> paramIds = criteriaParamIds.list();

                criteria = HibernateUtil.getSession().createCriteria(ApplicationParameter.class).addOrder(Order.asc("parameter"));
                if (!paramIds.isEmpty()) {
                    criteria.add(Restrictions.not(Restrictions.in("id", paramIds)));
                }
            }
            break;
        }

        if (criteria != null) {
            parameterDisplay = criteria.list();
        }
        return parameterDisplay;
    }

    public List searchParametersValue(int idEntity, EntityType entityType, String searchFilter, int firstResult, int maxResult) {
        List parameterValueDisplays = null;
        Criteria criteria = null;

        switch (entityType) {
            case CUSTOMER: {
                criteria = HibernateUtil.getSession().createCriteria(CustomerParameterValue.class)
                        .createAlias("customer", "customer")
                        .createAlias("customerParameter", "parameter")
                        .add(Restrictions.eq("customer.id", idEntity))
                        .addOrder(Order.asc("parameter.parameter"));
                break;
            }
            case MERCHANT: {
                criteria = HibernateUtil.getSession().createCriteria(MerchantParameterValue.class)
                        .createAlias("merchant", "merchant")
                        .createAlias("merchantParameter", "parameter")
                        .add(Restrictions.eq("merchant.id", idEntity))
                        .addOrder(Order.asc("parameter.parameter"));
                break;
            }
            case TERMINAL: {
                criteria = HibernateUtil.getSession().createCriteria(TerminalParameterValue.class)
                        .createAlias("terminal", "terminal")
                        .createAlias("applicationParameter", "parameter")
                        .add(Restrictions.eq("terminal.id", idEntity))
                        .addOrder(Order.asc("parameter.parameter"));

                break;
            }
            case APPLICATION_PARAMETER_VALUE:
            case AMS: {
                criteria = HibernateUtil.getSession().createCriteria(ApplicationParameterValue.class)
                        .createAlias("application", "application")
                        .createAlias("applicationParameter", "parameter")
                        .add(Restrictions.eq("application.id", idEntity))
                        .addOrder(Order.asc("parameter.parameter"));
                break;

            }
        }

        if (criteria != null) {
            //criteria.createAlias("parameter.dataType", "dataType");
            if (firstResult >= 0) {
                criteria.setFirstResult(firstResult);
                criteria.setMaxResults(maxResult);
            }
            if (searchFilter != null && !searchFilter.isEmpty()) {
                Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                        .add(Restrictions.like("parameter.parameter", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                        .add(Restrictions.like("parameter.description", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                        .add(Restrictions.like("value", searchFilter, MatchMode.ANYWHERE).ignoreCase());
                criteria.add(disjunction);
            }
            // criteria.setProjection(projectionListContain);
            // criteria.setResultTransformer(new TransformerComplexBeans(ParameterValueDisplay.class));
            parameterValueDisplays = criteria.list();
        }
        return parameterValueDisplays;
    }

    public boolean isParameterValueInserted(int idEntity, EntityType entityType, int idParameter) {
        String entity = "";
        String parameter = "applicationParameter";
        Class classEntity = null;
        switch (entityType) {
            case MERCHANT:
                entity = "merchant";
                parameter = "merchantParameter";
                classEntity = MerchantParameterValue.class;
                break;
            case TERMINAL:
                entity = "terminal";
                classEntity = TerminalParameterValue.class;
                break;
            case AMS:
            case APPLICATION_PARAMETER_VALUE:
                entity = "application";
                classEntity = ApplicationParameterValue.class;
                break;
        }
        Criteria criteria = HibernateUtil.getSession().createCriteria(classEntity)
                .createAlias(entity, "entity")
                .createAlias(parameter, "parameter");
        criteria.add(Restrictions.eq("entity.id", idEntity));
        criteria.add(Restrictions.eq("parameter.id", idParameter));

        return !criteria.list().isEmpty();
    }

    public Object getParameter(EntityType entityType, int idParameter) {
        switch (entityType) {
            case MERCHANT:
                return HibernateUtil.getSession().get(MerchantParameter.class, idParameter);
            case TERMINAL:
                return HibernateUtil.getSession().get(ApplicationParameter.class, idParameter);
            case AMS:
            case APPLICATION_PARAMETER_VALUE:
                return HibernateUtil.getSession().get(ApplicationParameter.class, idParameter);
        }
        return null;
    }

    public Object getParameterValue(EntityType entityType, int idParameterValue) {
        switch (entityType) {
            case MERCHANT:
                return HibernateUtil.getSession().get(MerchantParameterValue.class, idParameterValue);
            case TERMINAL:
                return HibernateUtil.getSession().get(TerminalParameterValue.class, idParameterValue);
            case AMS:
            case APPLICATION_PARAMETER_VALUE:
                return HibernateUtil.getSession().get(ApplicationParameterValue.class, idParameterValue);
        }
        return null;
    }

    /**
     * Add ParameterValue
     *
     * @param idEntity
     * @param entityType
     * @param idParameter
     * @param value
     * @throws java.lang.Exception
     *
     */
    public void addParameterValue(int idEntity, EntityType entityType, int idParameter, String value) throws Exception {
        switch (entityType) {
            case CUSTOMER: {
                break;
            }
            case MERCHANT: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(MerchantParameterValue.class)
                        .createAlias("merchant", "merchant")
                        .createAlias("merchantParameter", "merchantParameter");
                criteria.add(Restrictions.eq("merchant.id", idEntity));
                criteria.add(Restrictions.eq("merchantParameter.id", idParameter));

                if (!criteria.list().isEmpty()) {
                    throw new Exception("Error, parameter value already added");
                }

                MerchantParameterValue merchantParameterValue = new MerchantParameterValue();
                Merchant merchant = (Merchant) HibernateUtil.getSession().get(Merchant.class, idEntity);
                MerchantParameter merchantParameter = (MerchantParameter) HibernateUtil.getSession().get(MerchantParameter.class, idParameter);

                merchantParameterValue.setValue(value);
                merchantParameterValue.setMerchantParameter(merchantParameter);
                merchantParameterValue.setMerchant(merchant);

                HibernateUtil.getSession().saveOrUpdate(merchantParameterValue);
                break;
            }
            case TERMINAL: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(TerminalParameterValue.class)
                        .createAlias("terminal", "terminal")
                        .createAlias("applicationParameter", "applicationParameter");
                criteria.add(Restrictions.eq("terminal.id", idEntity));
                criteria.add(Restrictions.eq("applicationParameter.id", idParameter));

                if (!criteria.list().isEmpty()) {
                    throw new ValidationException("Error, parameter value already added");
                }

                Terminal terminal = (Terminal) HibernateUtil.getSession().get(Terminal.class, idEntity);
                ApplicationParameter applicationParameter = (ApplicationParameter) HibernateUtil.getSession().get(ApplicationParameter.class, idParameter);
                TerminalParameterValue terminalParameterValue = new TerminalParameterValue();

                terminalParameterValue.setValue(value);
                terminalParameterValue.setApplicationParameter(applicationParameter);
                terminalParameterValue.setTerminal(terminal);
                HibernateUtil.getSession().saveOrUpdate(terminalParameterValue);
                break;
            }
            case AMS:
            case APPLICATION_PARAMETER_VALUE: {
                Criteria criteria = HibernateUtil.getSession().createCriteria(ApplicationParameterValue.class)
                        .createAlias("application", "application")
                        .createAlias("applicationParameter", "applicationParameter");
                criteria.add(Restrictions.eq("application.id", idEntity));
                criteria.add(Restrictions.eq("applicationParameter.id", idParameter));

                if (!criteria.list().isEmpty()) {
                    throw new Exception("Error, parameter value already added");
                }

                Application application = (Application) HibernateUtil.getSession().get(Application.class, idEntity);
                ApplicationParameter applicationParameter = (ApplicationParameter) HibernateUtil.getSession().get(ApplicationParameter.class, idParameter);
                ApplicationParameterValue applicationParameterValue = new ApplicationParameterValue();

                applicationParameterValue.setValue(value);
                applicationParameterValue.setApplication(application);
                applicationParameterValue.setApplicationParameter(applicationParameter);
                HibernateUtil.getSession().saveOrUpdate(applicationParameterValue);
                break;
            }
        }
    }

    public void updateParameterValue(EntityType entityType, int idParameterValue, String value) {
        switch (entityType) {
            case CUSTOMER: {
                break;
            }
            case MERCHANT: {
                Criteria cri = HibernateUtil.getSession().createCriteria(MerchantParameterValue.class).add(Restrictions.idEq(idParameterValue));
                MerchantParameterValue merchantParameterValue = (MerchantParameterValue) cri.uniqueResult();

                merchantParameterValue.setValue(value);
                HibernateUtil.getSession().saveOrUpdate(merchantParameterValue);
                break;
            }
            case TERMINAL: {
                Criteria cri = HibernateUtil.getSession().createCriteria(TerminalParameterValue.class).add(Restrictions.idEq(idParameterValue));
                TerminalParameterValue terminalParameterValue = (TerminalParameterValue) cri.uniqueResult();

                terminalParameterValue.setValue(value);
                HibernateUtil.getSession().saveOrUpdate(terminalParameterValue);
                break;
            }
            case AMS:
            case APPLICATION_PARAMETER_VALUE: {
                Criteria cri = HibernateUtil.getSession().createCriteria(ApplicationParameterValue.class).add(Restrictions.idEq(idParameterValue));
                ApplicationParameterValue applicationParameterValue = (ApplicationParameterValue) cri.uniqueResult();

                applicationParameterValue.setValue(value);
                HibernateUtil.getSession().saveOrUpdate(applicationParameterValue);

                break;
            }
        }
    }

    public void deleteParameterValue(EntityType entityType, int idParameter) {
        Class classEntity = null;
        switch (entityType) {
            case MERCHANT: {
                classEntity = MerchantParameterValue.class;
                break;
            }
            case TERMINAL: {
                classEntity = TerminalParameterValue.class;
                break;
            }
            case AMS:
            case APPLICATION_PARAMETER_VALUE: {
                classEntity = ApplicationParameterValue.class;
                break;
            }
        }
        if (classEntity!=null) {
            Object entity = HibernateUtil.getSession().get(classEntity, idParameter);
            HibernateUtil.getSession().delete(entity);
        }
    }
}
