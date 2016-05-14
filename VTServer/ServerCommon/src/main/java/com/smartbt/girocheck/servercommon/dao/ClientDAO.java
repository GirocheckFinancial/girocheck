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

import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.utils.CryptoUtils;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.security.NoSuchAlgorithmException;
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
        if ( dao == null ) {
            dao = new ClientDAO();
        }
        return dao;
    }

    public Client createOrGet( String ssn, byte[] addressForm ) throws SQLException {
        Client client;
        String encryptedSSN = "";
        String maskSSN = "";
        if(ssn!= null && !ssn.equals("")){
            maskSSN = ssn.substring(5, 9);
        }
        
        try {
            //encrypting cardNumber
            encryptedSSN = CryptoUtils.encrypt(ssn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Criteria criteria = HibernateUtil.getSession().createCriteria( Client.class ).add( Restrictions.eq( "hashSSN", encryptedSSN ) );
//        Criteria criteria = HibernateUtil.getSession().createCriteria( Client.class ).add( Restrictions.eq( "ssn", encryptedSSN ) );
        client = (Client) criteria.uniqueResult();

        boolean newClient = client == null;

        if ( newClient ) {
            client = new Client();
            client.setHashSSN(encryptedSSN );
            client.setSsn( ssn );
            client.setMaskSSN(maskSSN );
            client.setActive( true);
            client.setHashSSN(ssn);
            client.setCreatedAt( new Date());
        }

        if ( addressForm != null ) {
            java.sql.Blob addressFormBlob = new SerialBlob( addressForm );
            client.setAddressForm( addressFormBlob );
        }

        if ( newClient || addressForm != null ) {
            saveOrUpdate( client );
        }       
        return client;
    }
    
    public List<ClientDisplay> searchClients(String searchFilter, int firstResult, int maxResult, NomApplication application) {
        
        List<ClientDisplay> clients;
        
        Criteria criteria = HibernateUtil.getSession().createCriteria(Client.class);

        criteria.createAlias("address", "address", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("address.state", "state", JoinType.LEFT_OUTER_JOIN);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("telephone").as("telephone"))     
                .add(Projections.property("email").as("email"))
//                .add(Projections.property("ssn").as("maskSS"))
                .add(Projections.property("maskSSN").as("maskSS"))

                .add(Projections.property("address.address").as("address"))
                .add(Projections.property("address.city").as("city"))
                .add(Projections.property("address.zipcode").as("zipcode"))
                .add(Projections.property("state.name").as("state"));
        
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
        
        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }
        
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new TransformerComplexBeans(ClientDisplay.class));
        
        clients = criteria.list();
        
        for (ClientDisplay client : clients) {
            client.setMaskSS("*****"+client.getMaskSS());
        }
        
        return clients;
        }
        
    }
