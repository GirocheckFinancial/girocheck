/*
 ** File: SystemPropertyDAO.java
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
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.display.PropertyDisplay;
import com.smartbt.girocheck.servercommon.model.VTInstanceProperty;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.VTSystemProperty;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ariel Saavedra
 */
public class SystemPropertyDAO extends BaseDAO<VTSystemProperty> {

    protected static SystemPropertyDAO dao;

    public SystemPropertyDAO() {
        //super(VTSystemProperty.class);
    }

    public static SystemPropertyDAO get() {
        if (dao == null) {
            dao = new SystemPropertyDAO();
        }
        return dao;
    }

    /**
     * Verify that the System Property exist
     *
     * @param name
     * @return true in case the System Property exist
     *
     */
    public boolean existSystemProperty(String name) {
        Criteria cri = HibernateUtil.getSession().createCriteria(VTSystemProperty.class)
                .add(Restrictions.eq("propertyName", name));
        return (cri.uniqueResult() != null);
    }

    /**
     * Get System Property by given name
     *
     * @param name
     * @return List<PropertyDisplay>
     *
     */
    public PropertyDisplay getSystemProperty(String name) {
        Criteria criteriaC = HibernateUtil.getSession().createCriteria(VTSystemProperty.class)
                .add(Restrictions.eq("propertyName", name));

        ProjectionList projectionListC = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("propertyName").as("propertyName"))
                .add(Projections.property("propertyValue").as("propertyValue"));
        criteriaC.setProjection(projectionListC);
        criteriaC.setResultTransformer(Transformers.aliasToBean(PropertyDisplay.class));
        return (PropertyDisplay) criteriaC.uniqueResult();
    }

    /**
     * Search the value from the property given
     *
     * @param property
     * @return system property value, blank in case not found
     *
     */
    public String searchSysProperty(String property) {
        String result = "";
        Criteria criteriaIns = HibernateUtil.getSession().createCriteria(VTInstanceProperty.class).
                add(Restrictions.eq("propertyName", property));

        VTInstanceProperty instanceProperty = (VTInstanceProperty) criteriaIns.uniqueResult();

        if (instanceProperty != null) {
            result = instanceProperty.getPropertyValue();
        } else {
            Criteria criteriaSys = HibernateUtil.getSession().createCriteria(VTSystemProperty.class).
                    add(Restrictions.eq("propertyName", property));

            VTSystemProperty systemProperty = (VTSystemProperty) criteriaSys.uniqueResult();
            if (systemProperty
                    != null) {
                result = systemProperty.getPropertyValue();
            }
        }
        return result;
    }
}
