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

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class ClientDAO extends BaseDAO<Client> {

    protected static ClientDAO dao;

    public ClientDAO() {
    }

    public static ClientDAO get() {
        if (dao == null) {
            dao = new ClientDAO();
        }
        return dao;
    }

    public Client createOrGet(String ssn, byte[] addressForm) throws SQLException {

        String maskSSN = "";
        if (ssn != null && ssn.length() >= 9) {
            maskSSN = ssn.substring(5, 9);
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[ClientDAO] createOrGet getting client with ssn = " + ssn, null);

        Client client = (Client) HibernateUtil.getSession().createCriteria(Client.class)
                .add(Restrictions.eq("ssn", ssn))
                .setMaxResults(1)
                .uniqueResult();

        boolean isNewClient = (client == null);

        if (isNewClient) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[ClientDAO] Creating newClient with SSN = " + ssn, null);
            client = new Client();
            client.setSsn(ssn);
            client.setMaskSSN(maskSSN);
            client.setActive(true);
            client.setCreatedAt(new Date());
            client.setSuccessfulLoads(0);
        } else {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[ClientDAO] Client already exist.", null);
        }

        if (addressForm != null) {
            java.sql.Blob addressFormBlob = new SerialBlob(addressForm);
            client.setAddressForm(addressFormBlob);
        }

        if (isNewClient || addressForm != null) {
            saveOrUpdate(client);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[ClientDAO] createOrGet -> after saveOrUpdate client.id = " + client.getId(), null);
        }
        return client;
    }

    public ResponseDataList searchClients(String searchFilter, int firstResult, int maxResult, Boolean blackList, Boolean optOut) {

        List<ClientDisplay> clients;

        Criteria criteria = getSearchCriteria(searchFilter, blackList, optOut);


        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("blacklistCard2bank").as("blackList"))
                .add(Projections.property("excludeSms").as("optOut"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("telephone").as("telephone"))
                .add(Projections.property("email").as("email"))
                .add(Projections.property("maskSSN").as("maskSS"))
                .add(Projections.property("address.address").as("address"))
                .add(Projections.property("address.city").as("city"))
                .add(Projections.property("address.zipcode").as("zipcode"))
                .add(Projections.property("state.name").as("state"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new TransformerComplexBeans(ClientDisplay.class));

        clients = criteria.list();

        Criteria countCriteria = getSearchCriteria(searchFilter, blackList, optOut);
        countCriteria.setProjection(Projections.rowCount());
        Long totalTrans = (Long) countCriteria.uniqueResult();

        ResponseDataList response = new ResponseDataList();

        response.setData(clients);
        response.setTotalPages((int) Math.ceil((float) totalTrans / (float) maxResult));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    private Criteria getSearchCriteria(String searchFilter, Boolean blackList, Boolean optOut) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Client.class);


        criteria.createAlias("address", "address", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("address.state", "state", JoinType.LEFT_OUTER_JOIN);

        if (searchFilter != null && !searchFilter.isEmpty()) {

            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("firstName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("lastName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("email", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("telephone", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("state.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("address.zipcode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("address.city", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("address.address", searchFilter, MatchMode.ANYWHERE).ignoreCase());


            criteria.add(disjunction);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }

        if (blackList != null) {
            if (blackList) {
                criteria.add(Restrictions.eq("blacklistCard2bank", blackList));
            } else {//if is false, include also nulls

                criteria.add(Restrictions.disjunction()
                        .add(Restrictions.eq("blacklistCard2bank", blackList))
                        .add(Restrictions.isNull("blacklistCard2bank")));
            }

        }

        if (optOut != null) {
            if (optOut) {
                criteria.add(Restrictions.eq("excludeSms", optOut));
            } else {
                criteria.add(Restrictions.disjunction()
                        .add(Restrictions.eq("excludeSms", optOut))
                        .add(Restrictions.isNull("excludeSms")));
            }
        }

        return criteria;

    }

    public ResponseData updateClientBlackList(ClientDisplay clientDisplay) {
        System.out.println("ClientDAO -> updateClientBlackList ");
        System.out.println("ClientDAO -> updateClientBlackList :: clientDisplay.getId()  = " + clientDisplay.getId());
        System.out.println("ClientDAO -> updateClientBlackList :: clientDisplay.getBlackList()   = " + clientDisplay.getBlackList());

        if (clientDisplay != null && clientDisplay.getId() != null && clientDisplay.getBlackList() != null) {
            Client client = findById(clientDisplay.getId());
            client.setBlacklistCard2bank(clientDisplay.getBlackList());
            saveOrUpdate(client);
        }
        ResponseData response = new ResponseData(clientDisplay);

        response.setStatus(Constants.CODE_SUCCESS);
        return response;
    }

    public ResponseData updateClientOptOut(Integer clientId) {
        System.out.println("ClientDAO -> updateClientoptOut ");
        System.out.println("ClientDAO -> updateClientoptOut :: clientId  = " + clientId);

        if (clientId != null) {
            Client client = findById(clientId);
            client.setExcludeSms(Boolean.FALSE);
            saveOrUpdate(client);
        }
        ResponseData response = new ResponseData();

        response.setStatus(Constants.CODE_SUCCESS);
        return response;
    }

    public Client getClientBySSN(String ssn) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(Client.class)
                .add(Restrictions.eq("ssn", ssn));
        return (Client) criteria.uniqueResult();
    }
}
