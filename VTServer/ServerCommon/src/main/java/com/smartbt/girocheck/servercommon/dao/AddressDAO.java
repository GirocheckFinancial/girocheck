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

import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class AddressDAO extends BaseDAO<Address> {
    
    protected static AddressDAO dao;
    
    public AddressDAO() {
    }
    
    public static AddressDAO get() {
        if ( dao == null ) {
            dao = new AddressDAO();
        }
        return dao;
    }
    
    public Address getByClient(int idClient){
        Criteria criteria = HibernateUtil.getSession().createCriteria( Address.class).
                createAlias( "client", "client").add( Restrictions.eq( "client.id", idClient));
        return (Address)criteria.uniqueResult();
    }
    
}
