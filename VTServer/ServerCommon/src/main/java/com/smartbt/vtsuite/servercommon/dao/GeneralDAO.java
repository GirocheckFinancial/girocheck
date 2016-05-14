/*
 ** File: GeneralDAO.java
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

import com.smartbt.vtsuite.servercommon.display.common.model.PhoneTypeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.RolePrivilegeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.SettingDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.StateDisplay;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.DataType;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.MerchantParameterValue;
import com.smartbt.vtsuite.servercommon.model.State;
import com.smartbt.vtsuite.servercommon.model.TelephoneType;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.model.TerminalParameterValue;
import com.smartbt.vtsuite.servercommon.model.VTSession;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ariel Saavedra
 */
public class GeneralDAO {

    protected static GeneralDAO dao;

    public static GeneralDAO get() {
        if (dao == null) {
            dao = new GeneralDAO();
        }
        return dao;
    }

    /**
     * FInd Data Type by ID
     *
     * @param id
     * @return
     *
     */
    public DataType findDataTypeById(int id) {
        return (DataType) HibernateUtil.getSession().get(DataType.class, id);
    }

    /**
     * List all data types
     *
     * @return List<DataType>
     *
     */
    public List<DataType> listDataTypes() {
        return HibernateUtil.getSession().createCriteria(DataType.class).list();
    }

    /**
     * List all available states
     *
     * @return List<StateDisplay>
     *
     */
    public List<StateDisplay> listStates() {
        List<StateDisplay> stateList;
        Criteria criteria = HibernateUtil.getSession().createCriteria(State.class, "State");

        criteria.setProjection(Projections.projectionList().add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("abbreviation").as("abbreviation")));

        criteria.setResultTransformer(Transformers.aliasToBean(StateDisplay.class));

        stateList = criteria.list();

        return stateList;
    }

    /**
     * List all available states
     *
     * @param state
     * @return List<StateDisplay>
     */
    public StateDisplay stateByName(String state) {
        StateDisplay stateDisplay;
        Criteria criteria = HibernateUtil.getSession().createCriteria(State.class, "State").add(Restrictions.eq("name", state));

        criteria.setProjection(Projections.projectionList().add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("abbreviation").as("abbreviation")));

        criteria.setResultTransformer(Transformers.aliasToBean(StateDisplay.class));

        stateDisplay = (StateDisplay) criteria.uniqueResult();

        return stateDisplay;
    }

    /**
     * List all available phone types
     *
     * @return List<TelephoneType>
     *
     */
    public List<TelephoneType> listPhoneTypes() {
        List<TelephoneType> typeList;
        Criteria criteria = HibernateUtil.getSession().createCriteria(TelephoneType.class, "TelephoneType");

        criteria.setProjection(Projections.projectionList().add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("description").as("description")));

        criteria.setResultTransformer(Transformers.aliasToBean(PhoneTypeDisplay.class));

        typeList = criteria.list();

        return typeList;
    }

    /**
     * Verify objects exists.
     *
     * @param objectClass The object class
     * @param id The object id
     * @return true if exists, false otherwise.
     *
     */
    public boolean existObject(Class objectClass, int id) {
        return !HibernateUtil.getSession().createCriteria(objectClass).add(Restrictions.idEq(id)).list().isEmpty();
    }
    
    /**
     * Login
     *
     * @param clerk
     * @param merchantId
     * @param application
     * @return Login response
     * @throws java.sql.SQLException
     */
    public SettingDisplay login(Clerk clerk, int merchantId, NomApplication application) throws SQLException {
        SettingDisplay settingDisplay = new SettingDisplay();

        if (clerk != null) {            
            if (clerk.getCustomer() != null) {
                merchantId = ((Merchant) clerk.getCustomer().getMerchant().toArray()[0]).getId();
            } else if (clerk.getMerchant() != null) {
                merchantId = clerk.getMerchant().getId();
            } else {
                merchantId = clerk.getTerminal().getMerchant().getId();
            }           

            Criteria criteria = HibernateUtil.getSession().createCriteria(Terminal.class)
                    .createAlias("application", "application")
                    .createAlias("merchant", "merchant")
                    .add(Restrictions.eq("merchant.id", merchantId))
                    .add(Restrictions.eq("application.id", application.getId()));
            List terminals = criteria.list();
            
            if (terminals.isEmpty()) {
                return null;
            }
            
            Terminal terminal = (Terminal) terminals.get(0);

            VTSession clerkSession = VTSessionDAO.get().saveOrUpdateSession(terminal, clerk);
            settingDisplay.setToken(clerkSession.getToken());
        }
        return settingDisplay;
    }

    /**
     * Get Settings
     *
     * @param clerk
     * @param merchantId
     * @param application
     * @return Login response
     */
    public SettingDisplay getSettings(Clerk clerk, int merchantId, NomApplication application) throws SQLException {
        SettingDisplay settingDisplay = new SettingDisplay();

        if (clerk != null) {
            if (merchantId == 0) {
                if (clerk.getCustomer() != null) {
                    merchantId = ((Merchant) clerk.getCustomer().getMerchant().toArray()[0]).getId();
                    settingDisplay.setEntityType("CUSTOMER");
                } else if (clerk.getMerchant() != null) {
                    merchantId = clerk.getMerchant().getId();
                    settingDisplay.setEntityType("MERCHANT");
                } else {
                    merchantId = clerk.getTerminal().getMerchant().getId();
                    settingDisplay.setEntityType("TERMINAL");
                }
            } else {
                if (clerk.getCustomer() != null) {
                    settingDisplay.setEntityType("CUSTOMER");
                } else if (clerk.getMerchant() != null) {
                    settingDisplay.setEntityType("MERCHANT");
                } else {
                    settingDisplay.setEntityType("TERMINAL");
                }
            }

            List<MerchantParameterValue> merchantParametersValueDisplay = ParameterValueDAO.get().searchParametersValue(merchantId, EntityType.MERCHANT, null, 0, 1000);
            List<RolePrivilegeDisplay> rolePrivilegesDisplay = PrivilegesDAO.get().searchRolePrivileges(EntityType.CLERK, false, clerk.getClerkRole().getId(), 0, 1000);

            Criteria criteria = HibernateUtil.getSession().createCriteria(Terminal.class)
                    .createAlias("application", "application")
                    .createAlias("merchant", "merchant")
                    .add(Restrictions.eq("merchant.id", merchantId))
                    .add(Restrictions.eq("application.id", application.getId()));
            List terminals = criteria.list();
            if (terminals.isEmpty()) {
                return null;
            }
            Terminal terminal = (Terminal) terminals.get(0);

            List<TerminalParameterValue> terminalParametersValueDisplay = ParameterValueDAO.get().searchParametersValue(terminal.getId(), EntityType.TERMINAL, null, 0, 1000);

            switch (application) {
                case VT_APPLICATION:
                    settingDisplay.setMerchantParameters(getMerchantParameters(merchantParametersValueDisplay));
                    settingDisplay.setTerminalParameters(getTerminalParameters(terminalParametersValueDisplay));
                    settingDisplay.setClerkPrivileges(getRolePrivileges(rolePrivilegesDisplay));

                    if (terminal.getMerchant().getLogo() != null) {
                        byte[] bdata = terminal.getMerchant().getLogo().getBytes(1, (int) terminal.getMerchant().getLogo().length());
                        settingDisplay.setMerchantLogo(new String(bdata));
                    }
                    break;
                case VT_MOBILE:
                    settingDisplay.setMerchantParameters(getMerchantParameters(merchantParametersValueDisplay));
                    settingDisplay.setTerminalParameters(getTerminalParameters(terminalParametersValueDisplay));
                    settingDisplay.setClerkPrivileges(getRolePrivileges(rolePrivilegesDisplay));
                    break;
            }

            settingDisplay.setTerminal(terminal.getTerminalId());
            settingDisplay.setTerminalId(terminal.getId());

            settingDisplay.setMerchant(terminal.getMerchant().getNumber());
            settingDisplay.setMerchantId(terminal.getMerchant().getId());
            settingDisplay.setMerchantName(terminal.getMerchant().getName());

            settingDisplay.setCustomer(terminal.getMerchant().getCustomer().getNumber());
            settingDisplay.setCustomerId(terminal.getMerchant().getCustomer().getId());
            settingDisplay.setCustomerName(terminal.getMerchant().getCustomer().getName());

            settingDisplay.setFirstTime(clerk.getFirstTime());

            VTSession clerkSession = VTSessionDAO.get().saveOrUpdateSession(terminal, clerk);
            settingDisplay.setToken(clerkSession.getToken());
        }
        return settingDisplay;
    }

    /**
     * Gets merchant parameters
     *
     * @param parametersValueDisplay
     * @return
     */
    public static Map getMerchantParameters(List<MerchantParameterValue> parametersValueDisplay) {
        Map parametersMap = new LinkedHashMap();

        for (MerchantParameterValue parameterValue : parametersValueDisplay) {
            parametersMap.put(parameterValue.getMerchantParameter().getId(), parameterValue.getValue());
        }
        return parametersMap;
    }

    /**
     * Gets terminal parameters
     *
     * @param parametersValueDisplay
     * @return
     */
    public static Map getTerminalParameters(List<TerminalParameterValue> parametersValueDisplay) {
        Map parametersMap = new LinkedHashMap();

        for (TerminalParameterValue parameterValue : parametersValueDisplay) {
            parametersMap.put(parameterValue.getApplicationParameter().getId(), parameterValue.getValue());
        }
        return parametersMap;
    }

    /**
     * Gets clerk privileges
     *
     * @param rolePrivilegesDisplay
     * @return
     */
    public static Map getRolePrivileges(List<RolePrivilegeDisplay> rolePrivilegesDisplay) {
        Map rolePrivilegeMap = new LinkedHashMap();

        for (RolePrivilegeDisplay rolePrivilege : rolePrivilegesDisplay) {
            rolePrivilegeMap.put(rolePrivilege.getPrivilege().getId(), rolePrivilege.getPrivilege().getName());
        }
        return rolePrivilegeMap;
    }
}
