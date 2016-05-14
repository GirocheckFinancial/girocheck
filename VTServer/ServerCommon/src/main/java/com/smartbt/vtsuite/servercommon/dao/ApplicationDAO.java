/*
 ** File: ApplicationDAO.java
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
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class ApplicationDAO extends BaseDAO<Application> {

    protected static ApplicationDAO dao;

    public ApplicationDAO() {
        //super(Application.class);
    }

    public static ApplicationDAO get() {
        if (dao == null) {
            dao = new ApplicationDAO();
        }
        return dao;
    }

    /**
     * Add a Application
     *
     * @param application
     */
    public void addApplication(Application application) {
        saveOrUpdate(application);
    }

    /**
     * Find Application by name
     *
     * @param name
     */
    public List<Application> findApplicationByName(String name) {
        return HibernateUtil.getSession().createCriteria(Application.class).add(Restrictions.eq("name", name).ignoreCase()).list();
    }
    
    
    /**
     * Get all the Applications
     *
     * @return List Applications
     */
    public List<Application> getApplications() {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Application.class)
                .add(Restrictions.ne("id", NomApplication.VT_AMS.getId()));
        return criteria.list();
    }
}
