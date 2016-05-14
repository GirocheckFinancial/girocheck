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

import com.smartbt.girocheck.servercommon.display.SubTransactionDisplay;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class SubTransactionDAO extends BaseDAO<SubTransaction> {
    
    protected static SubTransactionDAO dao;
    
    public SubTransactionDAO() {
    }
    
    public static SubTransactionDAO get() {
        if ( dao == null ) {
            dao = new SubTransactionDAO();
        }
        return dao;
    }
    
       public List<SubTransactionDisplay> listByTransaction( int idTransaction ) {
        Criteria cri = HibernateUtil.getSession().createCriteria( SubTransaction.class )
                .createAlias( "transaction", "transaction" ).add( Restrictions.eq( "transaction.id", idTransaction))
                .addOrder( Order.asc( "order" ) );

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "type" ).as( "subTransactionType" ) )
                .add( Projections.property( "resultCode" ).as( "resultCode" ) )
                .add( Projections.property( "resultMessage" ).as( "resultMessage" ) )
                .add( Projections.property( "errorCode" ).as( "hostCode" ) );
        cri.setProjection( projectionList );
        cri.setResultTransformer( new TransformerComplexBeans( SubTransactionDisplay.class ) );

        return cri.list();
    }
    
}
