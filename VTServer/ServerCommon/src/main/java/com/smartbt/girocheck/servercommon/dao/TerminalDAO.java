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

import com.smartbt.girocheck.servercommon.display.TerminalDisplay;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class TerminalDAO extends BaseDAO<Terminal> {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TerminalDAO.class);

    protected static TerminalDAO dao;

    public TerminalDAO() {
    }

    public static TerminalDAO get() {
        if ( dao == null ) {
            dao = new TerminalDAO();
        }
        return dao;
    }

    public Terminal findBySerialNumber( String serialNumber ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Terminal.class ).add( Restrictions.eq( "serialNumber", serialNumber ) );
        return (Terminal) criteria.uniqueResult();
    }

    public String getAccountFromMerchantByTerminalSerialNumber( String serialNumber ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Terminal.class ).
                createAlias( "merchant", "merchant" ).add( Restrictions.eq( "serialNumber", serialNumber ) );

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "merchant.account" ).as( "account" ) );

        criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( Merchant.class ) );
        Merchant merchant = (Merchant) criteria.uniqueResult();
        return merchant.getAccount();
    }

    public boolean loginTerminal( String serialNumber, String user, String password ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Terminal.class )
                .add( Restrictions.eq( "serialNumber", serialNumber ))
                .add( Restrictions.eq( "girocheckUser", user ))
                .add( Restrictions.eq( "girocheckPassword", password ));

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) );

        criteria.setProjection( projectionList );
        criteria.setMaxResults( 1);
        //criteria.setResultTransformer( Transformers.aliasToBean( Merchant.class ) );
        return criteria.uniqueResult() != null;
    }

    public Merchant getParametersFromMerchant( String serialNumber ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Terminal.class ).
                createAlias( "merchant", "merchant" ).add( Restrictions.eq( "serialNumber", serialNumber ) );

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "merchant.idTecnicardCheck" ).as( "idTecnicardCheck" ) )
                .add( Projections.property( "merchant.idTecnicardCash" ).as( "idTecnicardCash" ) )
                .add( Projections.property( "merchant.idIstreamCash" ).as( "idIstreamCash" ) )
                .add( Projections.property( "merchant.idIstreamCheck" ).as( "idIstreamCheck" ) )
                .add( Projections.property( "merchant.idIstreamCash" ).as( "istreamUser" ) )
                .add( Projections.property( "merchant.idIstreamCheck" ).as( "istreamUser" ) );

        criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( Merchant.class ) );
        Merchant merchant = (Merchant) criteria.uniqueResult();

        return merchant;
    }

    public List<TerminalDisplay> getTerminalByMerchant( int idMerchant ) {

        Criteria criteria = HibernateUtil.getSession().createCriteria( Terminal.class )
                .createAlias( "merchant", "merchant" )
                .add( Restrictions.eq( "merchant.id", idMerchant ) );

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "serialNumber" ).as( "serialNumber" ) )
                .add( Projections.property( "id" ).as( "id" ) );
        criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( TerminalDisplay.class ) );
        List<TerminalDisplay> terminalDisplayList = (List<TerminalDisplay>) criteria.list();

        return terminalDisplayList;
    }

    public TerminalDisplay getTerminalById( int id ) {

        Criteria criteria = HibernateUtil.getSession().createCriteria( Terminal.class )
                .add( Restrictions.eq( "id", id ) );

        Terminal terminal = (Terminal) criteria.uniqueResult();

        TerminalDisplay display = new TerminalDisplay();

        if ( terminal != null ) {
            display.setId( terminal.getId() );
            display.setSerialNumber( terminal.getSerialNumber() );
            display.setTerminalUser( terminal.getGirocheckUser() );
            display.setTerminalPassword( terminal.getGirocheckPassword() );
            display.setDescription( terminal.getDescription() );
            display.setHasTransaction( hasTransaction( id ) );
            display.setIdPOSOrderExp(terminal.getIdPOSOrderExp());
        } else {
//            log.debug( "[TerminalDAO] Terminal es NULL" );
        }

        return display;
    }

    public boolean hasTransaction( int idTerminal ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Transaction.class )
                .createAlias( "terminal", "terminal" )
                .add( Restrictions.eq( "terminal.id", idTerminal ) ).setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setMaxResults( 1 );

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ));

       criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( TransactionDisplay.class ) );
        return criteria.list() != null && criteria.list().size() != 0;
    }

    public void deleteTerminal( int id ) {
        String sql = "delete from terminal where id = :id";
        Query query = HibernateUtil.getSession().createSQLQuery( sql );
        query.setParameter( "id", id );
        query.executeUpdate();
    }
}
