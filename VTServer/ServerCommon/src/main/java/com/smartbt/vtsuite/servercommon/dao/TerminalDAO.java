/*
 ** File: TerminalDAO.java
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
import com.smartbt.vtsuite.servercommon.display.common.model.ModeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TerminalDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ariel Saavedra
 */
public class TerminalDAO extends BaseDAO<Terminal> {

    protected static TerminalDAO dao;

    public TerminalDAO() {
        //super(Terminal.class);
    }

    public static TerminalDAO get() {
        if (dao == null) {
            dao = new TerminalDAO();
        }
        return dao;
    }

    /**
     * Get a terminal by a given id
     *
     * @param id Terminal id
     * @return Terminal
     *
     */
    public TerminalDisplay getTerminal(int id) {

        Terminal terminal = super.findById(id);
        if (terminal.isMonitored()) {
            MonitoringDAO.get().shouldBeStopedMonitoring(id);
        }

        TerminalDisplay terminalDisplay = new TerminalDisplay();
        terminalDisplay.setId(terminal.getId());
        terminalDisplay.setTerminalId(terminal.getTerminalId());
        terminalDisplay.setActive(terminal.getActive());
        terminalDisplay.setDescription(terminal.getDescription());
        terminalDisplay.setMonitored(terminal.isMonitored());

        terminalDisplay.setCustomerName(terminal.getMerchant().getCustomer().getName());
        terminalDisplay.setMerchantName(terminal.getMerchant().getName());
        terminalDisplay.setApplication(terminal.getApplication().getName());
        terminalDisplay.setProductType(terminal.getProductType().getName());

        SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
        terminalDisplay.setActivationDate(terminal.getActivationDate() == null ? null : dateF.format(terminal.getActivationDate()));
        terminalDisplay.setDeactivationDate(terminal.getDeactivationDate() == null ? null : dateF.format(terminal.getDeactivationDate()));
        return terminalDisplay;
    }

    /**
     * Get the Terminals by a given Merchant's id
     *
     * @param id Merchant id
     * @return List Terminal by Merchant
     *
     */
    public List<TransactionDisplay> getTerminalsByMerchant(int id) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Terminal.class)
                .createAlias("merchant", "merchant")
                .createAlias("application", "application")
                .add(Restrictions.eq("merchant.id", id));

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("terminalId").as("terminalId"))
                .add(Projections.property("active").as("active"))
                .add(Projections.property("description").as("description"))
                .add(Projections.property("application.id").as("idApplication"));
        
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(TerminalDisplay.class));
        return criteria.list();
    }

    public Terminal getTerminalByTerminalId(String terminalId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Terminal.class).add(Restrictions.eq("terminalId", terminalId));
        return (Terminal) criteria.uniqueResult();
    }
}
