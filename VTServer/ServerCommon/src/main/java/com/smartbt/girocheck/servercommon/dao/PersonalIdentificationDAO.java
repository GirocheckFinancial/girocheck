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

import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.sql.SQLException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class PersonalIdentificationDAO extends BaseDAO<PersonalIdentification> {

    protected static PersonalIdentificationDAO dao;

    public PersonalIdentificationDAO() {
    }

    public static PersonalIdentificationDAO get() {
        if (dao == null) {
            dao = new PersonalIdentificationDAO();
        }
        return dao;
    }

    public PersonalIdentification getByClientAndType(int idClient, int idType) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(PersonalIdentification.class).
                createAlias("client", "client").add(Restrictions.eq("client.id", idClient)).add(Restrictions.eq("idType", idType));
        return (PersonalIdentification) criteria.uniqueResult();
    }

    public PersonalIdentification getByClientId(int idClient) throws SQLException {
        Criteria criteria = HibernateUtil.getSession().createCriteria(PersonalIdentification.class).
                createAlias("client", "client").add(Restrictions.eq("client.id", idClient))
                .addOrder(Order.desc("id"));
        criteria.setMaxResults(1);

        PersonalIdentification identification = (PersonalIdentification) criteria.uniqueResult();
 
//        identification.getClient();
//        identification.getState();

        return identification;
    }

    public void removeByClientAndType(int idClient, int idType, Integer currentIdentificationId) {
        String sql = "delete from identification where id_type = " + idType + " and client = " + idClient;
        
        if(currentIdentificationId != null){
            sql += " AND id != " + currentIdentificationId;
        }
        
        Query query = HibernateUtil.getSession().createSQLQuery(sql);
        query.executeUpdate();
    }

}
