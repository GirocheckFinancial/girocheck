/**
 *
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be reproduced, transmitted,
 * transcribed, stored in a retrieval system, or translated into any language or
 * computer language, in any form or by any means, electronic, mechanical,
 * magnetic, optical, chemical, manual or otherwise, without the prior written
 * permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.model.User;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>, Alejo
 */
public class LoginAMSDAO extends BaseDAO<User> {

    /**
     *
     */
    protected static LoginAMSDAO dao;

    /**
     *
     */
    public LoginAMSDAO() {
    }

    /**
     * Singleton Contract
     *
     * @return
     */
    public static LoginAMSDAO get() {
        if ( dao == null ) {
            dao = new LoginAMSDAO();
        }
        return dao;
    }

    /**
     * Find a user by username and password
     *
     * @param username
     * @param password
     * @return User
     */
    public User findByUserNameAndPassword(String username, String password) {
        
        Criteria cri = HibernateUtil.getSession().createCriteria(User.class).add(Restrictions.eq("username", username).ignoreCase()).add(Restrictions.eq("password", password));
        
        return (User) cri.uniqueResult();
    }

    /**
     * Find a user by username  
     *
     * @param username 
     * @return User
     */
    public User findByUserName(String username) {
        
        Criteria cri = HibernateUtil.getSession().createCriteria(User.class)
                .add(Restrictions.eq("username", username).ignoreCase());
        
        return (User) cri.uniqueResult();
    }

}
