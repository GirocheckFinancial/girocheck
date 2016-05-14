/*
 ** File: ApplicationDAO.java
 **
 ** Date Created: October 2013
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
import com.smartbt.vtsuite.servercommon.model.Account;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class AccountDAO extends BaseDAO<Account> {
    
    protected static AccountDAO dao;
    
    public AccountDAO() {
        //super(Account.class);
    }
    
    public static AccountDAO get() {
        if (dao == null) {
            dao = new AccountDAO();
        }
        return dao;
    }
    
    public List<Account> findByPAN(String pan) {
        Criteria cri = HibernateUtil.getSession().createCriteria(Account.class).add(Restrictions.eq("encryptedData", pan).ignoreCase());
        return cri.list();
    }
    
    public void removeAccountFromClient(int accountId) {
        Account account = findById(accountId);
        account.setClient(null);
        saveOrUpdate(account);
    }
}
