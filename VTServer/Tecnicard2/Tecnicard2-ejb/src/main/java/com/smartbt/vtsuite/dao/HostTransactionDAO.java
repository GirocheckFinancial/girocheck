/*
 ** File: HostTransactionDAO.java
 **
 ** Date Created: February 2013
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

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.*;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomParameterGroup;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomTerminalHostParameter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * The Host Data Access Object class
 */
public class HostTransactionDAO {

    /**
     * Constructor
     */
    public HostTransactionDAO() {
    }

    /**
     * Get host parameters for respective merchant/terminal
     *
     * @param terminalId The terminal id
     * @return terminal parameters.
     * @throws Exception
     */
    public Map getTerminalHostParametersX(Integer terminalId) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Criteria criteria = HibernateUtil.getSession().createCriteria(TerminalHostParameterValue.class, "TerminalHostParameterValue").
                createAlias("TerminalHostParameterValue.terminalHost", "TerminalHost").
                createAlias("TerminalHost.terminal", "Terminal").
                createAlias("TerminalHost.host", "Host").
                add(Restrictions.eq("Host.name", NomHost.ISTREAM).ignoreCase()).
                add(Restrictions.eq("Terminal.id", terminalId));

        List terminalHostParametersValue = criteria.list();

        for (Iterator it = terminalHostParametersValue.iterator(); it.hasNext();) {
            TerminalHostParameterValue terminalHostParameterValue = (TerminalHostParameterValue) it.next();
            TerminalHostParameter terminalHostParameter = (TerminalHostParameter) HibernateUtil.getSession().load(TerminalHostParameter.class, terminalHostParameterValue.getTerminalHostParameter().getId());
            params.put(terminalHostParameter.getParameter(), terminalHostParameterValue.getValue());
        }

        return params;
    }

    /**
     * Increment terminal sequence number
     *
     * @param terminalId The terminal id
     * @return Sequence number.
     * @throws Exception
     */
    public int getIncrementSeqNumX(Integer terminalId) throws Exception {
        int seqNumInc = 0000;
            Criteria criteria = HibernateUtil.getSession().createCriteria(TerminalHostParameterValue.class, "TerminalHostParameterValue").
                    createAlias("TerminalHostParameterValue.terminalHost", "TerminalHost").
                    createAlias("TerminalHostParameterValue.terminalHostParameter", "TerminalHostParameter").
                    createAlias("TerminalHost.terminal", "Terminal").
                    createAlias("TerminalHost.host", "Host").
                    add(Restrictions.eq("Host.name", NomHost.ISTREAM).ignoreCase()).
                    add(Restrictions.eq("Terminal.id", terminalId)).
                    add(Restrictions.eq("TerminalHostParameter.parameter", NomTerminalHostParameter.WORLDPAY_SEQNUM).ignoreCase());

            TerminalHostParameterValue terminalHostParamValue = (TerminalHostParameterValue) criteria.uniqueResult();
            seqNumInc = Integer.valueOf(terminalHostParamValue.getValue()) + 1;

            if (seqNumInc > 9999) {
                seqNumInc = 1;
            }
            terminalHostParamValue.setValue(String.valueOf(seqNumInc));
            HibernateUtil.getSession().saveOrUpdate(terminalHostParamValue);      
        return seqNumInc;
    }

    /**
     * Get host parameters for respective merchant
     *
     * @param terminalId The terminal id
     * @return Merchant parameters.
     * @throws Exception
     */
    public Map getMerchantHostParametersX(Integer terminalId) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
            Criteria criteria = HibernateUtil.getSession().createCriteria(MerchantHostParameterValue.class, "MerchantHostParameterValue").
                    createAlias("MerchantHostParameterValue.merchantHost", "MerchantHost").
                    createAlias("MerchantHost.merchant", "Merchant").
                    createAlias("MerchantHost.host", "Host").
                    createAlias("Merchant.terminal", "Terminal").
                    add(Restrictions.eq("Terminal.id", terminalId)).
                    add(Restrictions.eq("Host.name", NomHost.ISTREAM).ignoreCase());

            List merchantHostParametersValue = criteria.list();

            for (Iterator it = merchantHostParametersValue.iterator(); it.hasNext();) {
                MerchantHostParameterValue merchantHostParameterValue = (MerchantHostParameterValue) it.next();
                MerchantHostParameter merchantHostParameter = (MerchantHostParameter) HibernateUtil.getSession().get(MerchantHostParameter.class, merchantHostParameterValue.getMerchantHostParameters().getId());
                params.put(merchantHostParameter.getParameter(), merchantHostParameterValue.getValue());
            }
      
        return params;
    }

    /**
     * Get host parameters.
     *
     * @return Host parameters.
     * @throws Exception
     */
    public Map getHostParametersX() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
            Criteria criteria = HibernateUtil.getSession().createCriteria(HostParameterValue.class, "HostParameterValue").
                    createAlias("HostParameterValue.host", "Host").
                    createAlias("HostParameterValue.hostParameter", "HostParameter").
                    createAlias("HostParameter.parameterGroup", "ParameterGroup").
                    add(Restrictions.eq("Host.name", NomHost.ISTREAM).ignoreCase()).
                    add(Restrictions.eq("ParameterGroup.name", NomParameterGroup.WORLDPAY).ignoreCase());

            List hostParametersValue = criteria.list();

            for (Iterator it = hostParametersValue.iterator(); it.hasNext();) {
                HostParameterValue hostParameterValue = (HostParameterValue) it.next();
                HostParameter hostParameter = (HostParameter) HibernateUtil.getSession().get(HostParameter.class, hostParameterValue.getHostParameter().getId());
                params.put(hostParameter.getParameter(), hostParameterValue.getValue());
            }
        return params;
    }
}
