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

import com.smartbt.girocheck.servercommon.enums.CardStatus;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.utils.CryptoUtils;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class CreditCardDAO extends BaseDAO<CreditCard> {
    
    protected static CreditCardDAO dao;
    
    public CreditCardDAO() {
    }
    
    public static CreditCardDAO get() {
        if ( dao == null ) {
            dao = new CreditCardDAO();
        }
        return dao;
    }
    
    public CreditCard getByNumber(String cardNumber){
        
//        String encryptedCCardNumber = "";
//        try {
//            //encrypting cardNumber
//            encryptedCCardNumber = CryptoUtils.encrypt(cardNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        
        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));
        Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class)
                .add(Property.forName("id").eq(maxId))
//                .add(Restrictions.eq( "pan", encryptedCCardNumber ));
                .add(Restrictions.eq( "pan", cardNumber ));
        
        criteria.setMaxResults(1);
        return (CreditCard)criteria.uniqueResult();
    }
    
    public CreditCard getCardWaitingOfficialNumber(String ssn){
        
        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));
        
          Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class)
                  .createAlias( "client", "client")
                  .add(Property.forName("id").eq(maxId))
                  .add(Restrictions.eq( "client.ssn", ssn))
                  .add( Restrictions.eq( "cardStatus", CardStatus.WAITING_OFFICIAL_NUMBER.getId()))
                  .setMaxResults(1);
          
        return (CreditCard)criteria.uniqueResult();
    }
    
    public boolean validateNewCard(String cardNumber){
        
//        String encryptedCCardNumber = "";
//        try {
//            //encrypting cardNumber
//            encryptedCCardNumber = CryptoUtils.encrypt(cardNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        
        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));
        
        Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class)
                .add(Property.forName("id").eq(maxId))
//                .add(Restrictions.eq( "pan", encryptedCCardNumber )).add( Restrictions.eq( "cardStatus", CardStatus.UNACTIVE.getId()) );
                .add(Restrictions.eq( "pan", cardNumber )).add( Restrictions.eq( "cardStatus", CardStatus.UNACTIVE.getId()) );
        
        CreditCard card = null;
        
        try{
            card = (CreditCard)criteria.list().get(0);
        }catch(Exception e){}
        
        return card != null;
    }
    
    public boolean validateExistentCard(String cardNumber, String ssn){
        
//        String encryptedCCardNumber = "";
//        try {
//            //encrypting cardNumber
//            encryptedCCardNumber = CryptoUtils.encrypt(cardNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
 
        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));

        Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class).createAlias( "client", "client")
                .add(Property.forName("id").eq(maxId))
//                .add(Restrictions.eq( "pan", encryptedCCardNumber )).add(Restrictions.eq( "client.ssn", ssn)).add( Restrictions.eq( "cardStatus", CardStatus.ACTIVE.getId()) );
                .add(Restrictions.eq( "pan", cardNumber )).add(Restrictions.eq( "client.ssn", ssn)).add( Restrictions.eq( "cardStatus", CardStatus.ACTIVE.getId()) );
        CreditCard card = null;
        try{
            card = (CreditCard)criteria.list().get(0);
        }catch(Exception e){}
        
        return card != null;
    }
    
    public CreditCard getByCardNumber(String cardNumber){
        
//        String encryptedCCardNumber = "";
//        try {
//            //encrypting cardNumber
//            encryptedCCardNumber = CryptoUtils.encrypt(cardNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        
        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));
        
        Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class)
                .add(Property.forName("id").eq(maxId))
//                .add(Restrictions.eq( "pan", encryptedCCardNumber ));
                .add(Restrictions.eq( "pan", cardNumber ));
        
        CreditCard card;
        
        try{
        
            card = (CreditCard) criteria.list().get(0);
            
        }catch(Exception e){
        
            return null;
        }
        
        return card;
    }
    
    public Client getClient(String creditCardNumber){

        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));

        Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class )
                .add(Property.forName("id").eq(maxId))
                .add( Restrictions.eq( "pan", creditCardNumber ) );
        
        List<CreditCard> list = criteria.list();
        
        if(list != null){
            return list.get(0).getClient();
        }else
            return null;
        
    }
    
    public Merchant getMerchantByCreditCardNumber(String creditCard){
//        String encryptedCCardNumber = "";
//        try {
//            //encrypting cardNumber
//            encryptedCCardNumber = CryptoUtils.encrypt(creditCard);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        
        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));
        
        Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class )
                .add(Property.forName("id").eq(maxId))
//                .add( Restrictions.eq( "pan", encryptedCCardNumber ) );
                .add( Restrictions.eq( "pan", creditCard ) );

        CreditCard card = null;
                
            try{
                card = (CreditCard) criteria.list().get(0);
            }catch(Exception e){
                return null;
            }    
            if(card != null){
                return card.getMerchant();
            }else{
                return null;
            }
            
    }
    
        public CreditCard createOrGet( String cardNumber, Client client, Merchant merchant) throws Exception {
        CreditCard creditCard = null;
        String maskCardNumber;
//        
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CreditCardDAO] createOrGet(...) with cardnumber value: ["+cardNumber+"] "
//                + "is client != null: ["+(client!=null)+"] and Merchant != null: "+(merchant!=null),null);
        
        if(!cardNumber.equals("") && client != null && merchant != null && cardNumber.length() == 16){
//        try {
//            //encrypting cardNumber
//            encryptedCCardNumber = CryptoUtils.encrypt(cardNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        
        maskCardNumber = cardNumber.substring(0,4)+"********"+cardNumber.substring(12,16);
        
        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));
        
        Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class )
                .add(Property.forName("id").eq(maxId))
//                .add( Restrictions.eq( "pan", encryptedCCardNumber ) );
                .add( Restrictions.eq( "pan", cardNumber ) );
//        Criteria criteria = HibernateUtil.getSession().createCriteria( CreditCard.class ).add( Restrictions.eq( "cardNumber", cardNumber ) );
        try{
            creditCard = (CreditCard) criteria.list().get(0);
        }catch(Exception e){}

                if (creditCard == null) {
                    if (client.getFirstName() != null && client.getFirstName().equals("BIQorCTB")) {
                        creditCard = new CreditCard();
//                        creditCard.setPan(encryptedCCardNumber);
                        creditCard.setPan(cardNumber);
                        creditCard.setCardNumber(cardNumber);
                        creditCard.setMaskCardNumber(maskCardNumber);
                        creditCard.setMerchant(merchant);
                        saveOrUpdate(creditCard);
                    } else {
                        creditCard = new CreditCard();
//                        creditCard.setPan(encryptedCCardNumber);
                        creditCard.setPan(cardNumber);
                        creditCard.setCardNumber(cardNumber);
                        creditCard.setMaskCardNumber(maskCardNumber);
                        creditCard.setClient(client);
                        creditCard.setMerchant(merchant);
                        saveOrUpdate(creditCard);
                    }
                }
                return creditCard;
            } else
                return null;
    }
    public CreditCard get(String cardNumber) {
//        String encryptedCCardNumber = "";
//        try {
//            //encrypting cardNumber
//            encryptedCCardNumber = Utils.encryptCredictCardNumber(cardNumber);
//        } catch (NoSuchAlgorithmException ex) {
//            ex.printStackTrace();
//        }
        
        DetachedCriteria maxId = DetachedCriteria.forClass(CreditCard.class)
                .setProjection(Projections.max("id"));

        Criteria criteria = HibernateUtil.getSession().createCriteria(CreditCard.class)
                .add(Property.forName("id").eq(maxId))
//                .add(Restrictions.eq("pan", encryptedCCardNumber));
                .add(Restrictions.eq("pan", cardNumber));
        CreditCard card = null;
        try{
            card = (CreditCard)criteria.list().get(0);
        }catch(Exception e){}
        
        return card;
    }
    
}
