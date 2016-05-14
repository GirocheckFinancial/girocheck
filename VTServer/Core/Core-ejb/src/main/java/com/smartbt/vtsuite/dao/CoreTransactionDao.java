/*
 ** File: CoreTransactionDao.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.dao;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.HostDAO;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.*;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomMode;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomPrivileges;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * The Core Data Access Object Class
 */
public class CoreTransactionDao {

    /**
     * Constructor
     */
    public CoreTransactionDao() {
    }

    /**
     * Verify if user can perform the current transaction.
     *
     * @param idClerk The clerk id
     * @param mode The transaction mode
     * @param operation The transaction operation
     * @param swipe The swipe indicator
     * @return User access result.
     * @throws Exception
     */
    public boolean verifyUserAccess(Integer idClerk, String mode, String operation, boolean swipe) throws Exception {
        if (mode.equalsIgnoreCase(NomMode.MOCK.toString())) {
            return true;
        }

        List<Integer> privileges = new ArrayList<Integer>();
       
        if ((operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.SALE.toString())
           ||operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.FORCE.toString())
           ||operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.INQUIRY.toString())) && swipe) {
            privileges.add(NomPrivileges.ALLOW_CARD_PAYMENT.getId());
        }
        
        if ((operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.SALE.toString())
           ||operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.FORCE.toString())
           ||operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.INQUIRY.toString())) && !swipe) {
            privileges.add(NomPrivileges.ALLOW_CARD_PAYMENT_MANUAL.getId());
        }

        if (operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.REFUND.toString()) && swipe) {
            privileges.add(NomPrivileges.ALLOW_REFUND.getId());
        }
       
        if (operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.REFUND.toString()) && !swipe) {
           privileges.add(NomPrivileges.ALLOW_REFUND_MANUAL.getId());
        }

        if (operation.equalsIgnoreCase(com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation.REVERSAL.toString())) {
            privileges.add(NomPrivileges.ALLOW_VOID.getId());
        }

        Criteria criteria = HibernateUtil.getSession().createCriteria(Clerk.class, "Clerk").
                createAlias("Clerk.clerkRole", "ClerkRole").
                createAlias("ClerkRole.clerkRolePrivilege", "ClerkRolePrivilege").
                createAlias("ClerkRolePrivilege.clerkPrivilege", "ClerkPrivilege").
                add(Restrictions.eq("Clerk.id", idClerk)).
                add(Restrictions.in("ClerkPrivilege.id", privileges));

        Clerk clerk = (Clerk) criteria.uniqueResult();
        return clerk != null;

    }

    /**
     * Verify if terminal can perform the current transaction.
     *
     * @param direxTransactionRequest
     * @return Terminal access result.
     * @throws Exception
     */
    public String verifyTerminalAccess(DirexTransactionRequest direxTransactionRequest) throws Exception {

        Host host = null;//HostDAO.get().getHostByMerchantMode(direxTransactionRequest.getTerminalId(), direxTransactionRequest.getMode());

        if (host != null) {
            return host.getName();
        } else {
            throw new Exception(VTSuiteMessages.NO_HOST_FOUND);
        }
    }
}
