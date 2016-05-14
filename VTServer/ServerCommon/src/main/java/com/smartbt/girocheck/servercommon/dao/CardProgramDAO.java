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

import com.smartbt.girocheck.servercommon.display.CardProgramDisplay;
import com.smartbt.girocheck.servercommon.model.CardProgram;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CardProgramDAO extends BaseDAO<CardProgram> {

    protected static CardProgramDAO dao;

    public CardProgramDAO() {
    }

    public static CardProgramDAO get() {
        if ( dao == null ) {
            dao = new CardProgramDAO();
        }
        return dao;
    }

    public List<CardProgramDisplay> listCardProgram() {
        Criteria criteria = HibernateUtil.getSession().createCriteria( CardProgram.class);
        
        criteria.setProjection( Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "name" ).as( "name" ) ));

        criteria.setResultTransformer( Transformers.aliasToBean( CardProgramDisplay.class ) );

        return criteria.list();
    }
}
