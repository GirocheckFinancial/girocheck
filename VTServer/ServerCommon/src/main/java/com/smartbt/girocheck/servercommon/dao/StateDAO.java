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

import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.display.common.model.StateDisplay;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class StateDAO extends BaseDAO<State> {

    protected static StateDAO dao;

    public StateDAO() {
    }

    public static StateDAO get() {
        if ( dao == null ) {
            dao = new StateDAO();
        }
        return dao;
    }

    public State getByAbbreviation( String abbreviation ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( State.class ).add( Restrictions.eq( "abbreviation", abbreviation ) );
        return (State) criteria.uniqueResult();
    }

    public List<StateDisplay> listStates() {
        Criteria criteria = HibernateUtil.getSession().createCriteria( State.class );
        
        criteria.setProjection( Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "name" ).as( "name" ) )
                .add( Projections.property( "abbreviation" ).as( "abbreviation" ) ) );

        criteria.setResultTransformer( Transformers.aliasToBean( StateDisplay.class ) );

        return criteria.list();
    }
    
}
