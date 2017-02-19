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
package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.MerchantParameterValue;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto Rodriguez
 */
public class MerchantParameterValueDAO extends BaseDAO<MerchantParameterValue> {

    protected static MerchantParameterValueDAO dao;

    public static MerchantParameterValueDAO get() {
        if (dao == null) {
            dao = new MerchantParameterValueDAO();
        }
        return dao;
    }

    public void removeMerchantParameterValueByIdMerchant_IdMerchantParameter(int idMerchant, int idMerchantParameter) {
//         String query = "delete from merchant_parameter_value  where id_merchant = " + idMerchant + " and id_merchant_parameter = " + idMerchantParameter;
//        HibernateUtil.getSession().createSQLQuery(query).executeUpdate();
        String hql = "delete from " + MerchantParameterValue.class.getName() + " where merchant.id = :idMerchant AND merchantParameter.id= :idMerchantParameter";
        HibernateUtil.getSession().createQuery(hql)
                .setInteger("idMerchant", idMerchant)
                .setInteger("idMerchantParameter", idMerchantParameter)
                .executeUpdate();
    }

}
