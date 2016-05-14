/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */

package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.CountryDisplay;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class CountryDAO extends BaseDAO<Country> {
    
    protected static CountryDAO dao;
    
    public CountryDAO() {
    }
    
    public static CountryDAO get() {
        if ( dao == null ) {
            dao = new CountryDAO();
        }
        return dao;
    }
    
    public Country getByAbbreviation(String abbreviation){
        Criteria criteria = HibernateUtil.getSession().createCriteria( Country.class).add( Restrictions.eq( "abbreviation", abbreviation));
        return (Country)criteria.uniqueResult();
    }
    
        public List<CountryDisplay> listCountries() {
        List<CountryDisplay> countryList;
        Criteria criteria = HibernateUtil.getSession().createCriteria(Country.class);

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("abbreviation").as("abbreviation")));

        criteria.setResultTransformer(Transformers.aliasToBean(CountryDisplay.class));

        countryList = criteria.list();

        return countryList;
    }
    
}
