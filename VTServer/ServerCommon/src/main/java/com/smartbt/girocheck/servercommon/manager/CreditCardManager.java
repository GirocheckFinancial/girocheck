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
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.CreditCardDAO;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.utils.DirexException;
import java.sql.SQLException;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CreditCardManager {

    private CreditCardDAO dao = CreditCardDAO.get();

    public CreditCard getByNumber( String number ) throws DirexException {
        return dao.getByCardNumber( number );
    }

    public CreditCard getCardWaitingOfficialNumber( String ssn ) throws DirexException {
        return dao.getCardWaitingOfficialNumber( ssn );
    }
    
    public CreditCard createOrGet( String cardNumber, Client client, Merchant merchant ) throws Exception {
        return dao.createOrGet( cardNumber, client, merchant );
    }
    public CreditCard get( String cardNumber ) throws SQLException {
        return dao.get( cardNumber );
    }
    public Client getClient( String cardNumber ) throws SQLException {
        return dao.getClient( cardNumber );
    }

//    public boolean validateNewCard(String cardNumber){
//       return dao.validateNewCard( cardNumber );
//    }
//    
//    public boolean validateExistentCard(String cardNumber, String ssn){
//        return dao.validateExistentCard( cardNumber, ssn );
//    }
//    
//    public CreditCard getByCardNumber(String cardNumber){
//        return dao.getByCardNumber( cardNumber );
//    }
}
