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

import com.smartbt.girocheck.servercommon.enums.EmailName;
import com.smartbt.girocheck.servercommon.model.Email;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class EmailDAO extends BaseDAO<Email> {
    
    protected static EmailDAO dao;
    
    public EmailDAO() {
    }
    
    public static EmailDAO get() {
        if ( dao == null ) {
            dao = new EmailDAO();
        }
        return dao;
    }
    
    public Email getByName(EmailName name){
        Criteria criteria = HibernateUtil.getSession().createCriteria( Email.class)
                .add( Restrictions.eq( "name", name.getName()));
        return (Email)criteria.uniqueResult();
    }
    
}
