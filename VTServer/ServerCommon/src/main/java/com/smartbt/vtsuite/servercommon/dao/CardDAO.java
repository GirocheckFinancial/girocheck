/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.Card;

/**
 *
 * @author Carlos
 */
public class CardDAO extends BaseDAO<Card>{
       protected static CardDAO dao;

    public CardDAO() {
        // super(Transaction.class);
    }

    public static CardDAO get() {
        if (dao == null) {
            dao = new CardDAO();
        }
        return dao;
    }

}
