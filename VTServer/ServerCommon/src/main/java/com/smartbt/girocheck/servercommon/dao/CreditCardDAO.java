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

import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CreditCardDAO extends BaseDAO<CreditCard> {

    protected static CreditCardDAO dao;

    public CreditCardDAO() {
    }

    public static CreditCardDAO get() {
        if (dao == null) {
            dao = new CreditCardDAO();
        }
        return dao;
    }


    public Client getClient(String cardNumber) throws Exception { 
        CreditCard card = getCard(cardNumber);

        if (card != null) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CreditCardDAO] **getClient -> Card exist ", null);
            return card.getClient();
        }
        
        return null;
    }

    public CreditCard createOrGet(String cardNumber, Client client, Merchant merchant) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CreditCardDAO] createOrGet(...)"
                + "is client != null: [" + (client != null) + "] and Merchant != null: " + (merchant != null), null);

        CreditCard creditCard = getCard(cardNumber);
        
        if (creditCard == null) {
            String maskCardNumber = "";
            if(cardNumber.length() >= 16){
                maskCardNumber = cardNumber.substring(0, 4) + "********" + cardNumber.substring(12);
            }else{
                 if(cardNumber.length() >= 4){
                     maskCardNumber = cardNumber.substring(0, 4) + "********" ;
                 }
            }
            

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CreditCardDAO] createOrGet Creating Card:: " + maskCardNumber, null);
            creditCard = new CreditCard();
            creditCard.setCardNumber(cardNumber);
            creditCard.setMaskCardNumber(maskCardNumber);
            creditCard.setClient(client);
            creditCard.setMerchant(merchant); 
            saveOrUpdate(creditCard);
        } else {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CreditCardDAO] createOrGet creditCard already exist.", null);
            
            if(creditCard.getClient() == null){
                creditCard.setClient(client);
                saveOrUpdate(creditCard);
            }
        }
        return creditCard;

    }

    public CreditCard getCard(String cardNumber) throws Exception {
        return (CreditCard) HibernateUtil.getSession().createCriteria(CreditCard.class)
                .add(Restrictions.eq("cardNumber", cardNumber))
                .setMaxResults(1).uniqueResult();
    }
    
    //Get one card
    public CreditCard getCardByClientId(int clientId) {
        return (CreditCard)HibernateUtil.getSession().createCriteria(CreditCard.class).
                 createAlias( "client", "client" ) 
                 .add(Restrictions.eq("client.id", clientId))
                .setMaxResults(1).uniqueResult();
    }   

}
