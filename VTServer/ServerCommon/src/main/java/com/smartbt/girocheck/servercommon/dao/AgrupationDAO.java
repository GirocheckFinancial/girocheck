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

import com.smartbt.girocheck.servercommon.display.AgrupationDisplay;
import com.smartbt.girocheck.servercommon.model.Agrupation;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class AgrupationDAO extends BaseDAO<Agrupation> {

    protected static AgrupationDAO dao;

    public AgrupationDAO() {
    }

    public static AgrupationDAO get() {
        if ( dao == null ) {
            dao = new AgrupationDAO();
        }
        return dao;
    }

    public List<Agrupation> listAgrupations() {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Agrupation.class );

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "name" ).as( "name" ) )
                .add( Projections.property( "description" ).as( "description" ) );

        criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( AgrupationDisplay.class ) );
        return criteria.list();
    }

    public boolean hasTransaction( int idAgrupation ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Transaction.class )
               // .createAlias( "client", "client" )
                .createAlias( "terminal", "terminal" )
                .createAlias( "terminal.merchant", "merchant" )
                .createAlias( "merchant.agrupation", "agrupation" )
                .add( Restrictions.eq( "agrupation.id", idAgrupation )).setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setMaxResults( 1 );
       ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ));

       criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( TransactionDisplay.class ) );
        return criteria.list() != null && criteria.list().size() != 0;
    }

    public void deleteAgrupation( int idAgrupation ) {
        String sql = "delete from agrupation where id = :id";
        Query query = HibernateUtil.getSession().createSQLQuery( sql );
        query.setParameter( "id", idAgrupation );
        query.executeUpdate();
    }

    public List<AgrupationDisplay> searchAgrupations( String search ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Agrupation.class )
                .createAlias( "merchant", "merchant" , JoinType.LEFT_OUTER_JOIN)
                .createAlias( "merchant.terminal", "terminal", JoinType.LEFT_OUTER_JOIN )
                .setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY);

        if ( search != null && search.length() != 0 ) {
            criteria.add( Restrictions.or( 
                            Restrictions.like( "name", search, MatchMode.ANYWHERE ).ignoreCase(),
                            Restrictions.like( "merchant.legalName", search, MatchMode.ANYWHERE ).ignoreCase() ,
                            Restrictions.like( "merchant.agentName", search, MatchMode.ANYWHERE ).ignoreCase()));
        }

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "name" ).as( "name" ) )
                .add( Projections.property( "description" ).as( "description" ) );

        criteria.setProjection( Projections.distinct( projectionList ) );
        criteria.setResultTransformer( Transformers.aliasToBean( AgrupationDisplay.class ) );

        List<AgrupationDisplay> list = criteria.list();
        return list;
    }
}
