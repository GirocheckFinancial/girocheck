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

import com.smartbt.girocheck.servercommon.display.AddressDisplay;
import com.smartbt.girocheck.servercommon.display.CardProgramDisplay;
import com.smartbt.girocheck.servercommon.display.CountryDisplay;
import com.smartbt.girocheck.servercommon.display.MerchantDisplay;
import com.smartbt.girocheck.servercommon.display.StateDisplay;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.CardProgram;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.display.TransactionDisplay;
import java.sql.Date;
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
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>, Alejo
 */
public class MerchantDAO extends BaseDAO<Merchant> {
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MerchantDAO.class);

    protected static MerchantDAO dao;

    public MerchantDAO() {
    }

    public static MerchantDAO get() {
        if ( dao == null ) {
            dao = new MerchantDAO();
        }
        return dao;
    }

    public List<MerchantDisplay> getMerchantsByAgrupation( int idAgrupation ) {

        Criteria criteria = HibernateUtil.getSession().createCriteria( Merchant.class )
                .createAlias( "agrupation", "agrupation" )
                .add( Restrictions.eq( "agrupation.id", idAgrupation ) );

        //List<Merchant> list = criteria.list();
        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "legalName" ).as( "legalName" ) )
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "description" ).as( "description" ) );
        criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( MerchantDisplay.class ) );
        List<MerchantDisplay> merchantDisplayList = (List<MerchantDisplay>) criteria.list();

        return merchantDisplayList;
    }

    public MerchantDisplay getMerchantsById( int id ) {

        Criteria criteria = HibernateUtil.getSession().createCriteria( Merchant.class )
                .add( Restrictions.eq( "id", id ) );

        Merchant merchant = (Merchant) criteria.uniqueResult();

        MerchantDisplay display = new MerchantDisplay();

        if ( merchant != null ) {
            display.setId( merchant.getId() );
            display.setLegalName( merchant.getLegalName() );
            display.setAgentName( merchant.getAgentName() );
            display.setPhone( merchant.getPhone() );
            display.setAccount( merchant.getAccount() );
            display.setAuthFeeP(merchant.getAuthFeeP() );

            Address address = merchant.getAddress();
            AddressDisplay addressDisplay = new AddressDisplay();

            if ( address != null ) {
                addressDisplay.setId( address.getId() );
                addressDisplay.setAddress( address.getAddress() );
                addressDisplay.setCity( address.getCity() );
                addressDisplay.setZip( address.getZipcode() );
                display.setAddress( addressDisplay );

                Country country = address.getCountry();
                if ( country != null ) {
                    CountryDisplay countryDisplay = new CountryDisplay();
                    countryDisplay.setId( country.getId() );
//                    countryDisplay.setAbbreviation( country.getAbbreviation() );
//                    countryDisplay.setName( country.getName() );
                    addressDisplay.setCountry( countryDisplay );
                }

                State state = address.getState();
                if ( state != null ) {
                    StateDisplay stateDisplay = new StateDisplay();
                    stateDisplay.setId( state.getId() );
//                    stateDisplay.setAbbreviation( state.getAbbreviation() );
//                    stateDisplay.setName( state.getName() );
                    addressDisplay.setState( stateDisplay );
                }
            }

            CardProgram cardProgram = merchant.getCard_program();
            if ( cardProgram != null ) {
                CardProgramDisplay cardProgramDisplay = new CardProgramDisplay();
                cardProgramDisplay.setId( cardProgram.getId());
                display.setCardProgram( cardProgramDisplay );
            }

            display.setIdTecnicardCheck( merchant.getIdTecnicardCheck());
            display.setIdTecnicardCash( merchant.getIdTecnicardCash());
            display.setiStreamUser( merchant.getIstreamUser());
            display.setBankName(merchant.getBankName());
            display.setRoutingBankNumber(merchant.getRoutingBankNumber());
            display.setiStreamPassword( merchant.getIstreamPassword());
            display.setIdTellerOrderExp(merchant.getIdTellerOrderExp());
            display.setIdTellerPagoOrderExp(merchant.getIdTellerPagoOrderExp());
            display.setIdPosOrderExp(merchant.getIdPosOrderExp());
            display.setoEAgentNumber(merchant.getoEAgentNumber());
            
//            display.setIdIStream( merchant.getIdIstream());
            display.setIdIstreamFuzeCash(merchant.getIdIstreamFuzeCash());
            display.setIdIstreamFuzeCheck(merchant.getIdIstreamFuzeCheck());
            display.setIdIstreamTecnicardCash(merchant.getIdIstreamTecnicardCash());
            display.setIdIstreamTecnicardCheck(merchant.getIdIstreamTecnicardCheck());
            
            display.setSic( merchant.getSic() );
            display.setCardInventory( merchant.getInventory() + "" );
            display.setMerchantType( merchant.getMerchantType() + "" ); 
            display.setDistributionChanel( merchant.getDistributionChanel() + "" );
            display.setRisk( merchant.getRisk() + "" );

            display.setIndependentOwner( merchant.getIndependentOwner() );
            display.setMoneyTransmission( merchant.isMoneyTransmission() );
            display.setBillPayment( merchant.isBillPayment() );
            display.setCheckCashing( merchant.isCheckCashing() );
            display.setDocumentApproved( merchant.isDocumentApproved() );
            display.setAtm( merchant.isAtm() );
            display.setTraining( merchant.isTraining() );
            display.setActive( merchant.isActive() );
            display.setActivationDate(merchant.getActivationDate());
            display.setOtherFinancialProvider( merchant.isOtherFinancialProvider() );
            display.setAuthFeeP(merchant.getAuthFeeP());

            display.setDocumentNotes( merchant.getDocumentNotes() );
            display.setDescription( merchant.getDescription() );
            display.setCommissionType(merchant.getCommissionType() + "");

            display.setHasTransaction( hasTransaction( id ) );
        } else {
//            log.debug( "[MerchantDAO] Merchant es NULL" );
        }

        return display;
    }

    public boolean hasTransaction( int idMerchant ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Transaction.class )
                .createAlias( "terminal", "terminal" )
                .createAlias( "terminal.merchant", "merchant" )
                .add( Restrictions.eq( "merchant.id", idMerchant ) ).setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setMaxResults( 1 );
        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "id" ).as( "id" ));

       criteria.setProjection( projectionList );
        criteria.setResultTransformer( Transformers.aliasToBean( TransactionDisplay.class ) );
        return criteria.list() != null && criteria.list().size() != 0;
    }

    public void deleteMerchant( int id ) {
        String sql = "delete from merchant where id = :id";
        Query query = HibernateUtil.getSession().createSQLQuery( sql );
        query.setParameter( "id", id );
        query.executeUpdate();
    }
    
       public List<MerchantDisplay> searchMerchants( String search ) {
        Criteria criteria = HibernateUtil.getSession().createCriteria( Merchant.class )
                .createAlias( "terminal", "terminal", JoinType.LEFT_OUTER_JOIN )
                .setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY);

        if ( search != null && search.length() != 0 ) {
            System.out.println( "criteria.add( Restrictions" );
            criteria.add( Restrictions.or( 
                            Restrictions.like( "legalName", search, MatchMode.ANYWHERE ).ignoreCase(),
                            Restrictions.like( "agentName", search, MatchMode.ANYWHERE ).ignoreCase(),
                            Restrictions.like( "terminal.serialNumber", search, MatchMode.ANYWHERE ).ignoreCase()));
        }

        ProjectionList projectionList = Projections.projectionList()
                .add( Projections.property( "legalName" ).as( "legalName" ) )
                .add( Projections.property( "id" ).as( "id" ) )
                .add( Projections.property( "description" ).as( "description" ) );

        criteria.setProjection( Projections.distinct( projectionList ) );
        criteria.setResultTransformer( Transformers.aliasToBean( MerchantDisplay.class ) );

        List<MerchantDisplay> list = criteria.list();
        return list;
    }
       
       public Merchant getMerchantByTerminalID(String serialNumber){
           
        Criteria criteria = HibernateUtil.getSession().createCriteria( Merchant.class)
                .createAlias("terminal", "terminal")
                .add(Restrictions.eq( "terminal.serialNumber", serialNumber ));
        
        return (Merchant)criteria.uniqueResult();
           
       }
}
