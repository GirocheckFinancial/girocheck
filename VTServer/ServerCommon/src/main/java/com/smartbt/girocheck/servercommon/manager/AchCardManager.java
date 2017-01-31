package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.AchCardDAO;
import com.smartbt.girocheck.servercommon.dao.CreditCardDAO;
import com.smartbt.girocheck.servercommon.model.AchCard;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.utils.Utils;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejo
 */
public class AchCardManager {

    private AchCardDAO achCardDAO = AchCardDAO.get();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AchCardManager.class);

    public AchCard findById(int id) {
        return achCardDAO.findById(id);
    }

    public void save(AchCard achCard) {
        AchCard ach = new AchCard();
        ach.setAchform(achCard.getAchform());

        CreditCard card = null;
        try {
            System.out.println("Looking card by number: " + achCard.getCardNumber());
            
            card = CreditCardDAO.get().getCard(achCard.getCardNumber());
            
            System.out.println("card = " + (card != null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ach.setData_sc1(card);
        ach.setCardNumber("");
 
        ach.setMerchant(achCard.getMerchant());
        achCardDAO.save(ach);
    }

    public Boolean existAchCard(String cardNumber) {
        return achCardDAO.existAchCard(cardNumber);

    }

    public Merchant getMerchantByTerminalId(String terminalId) {

        return achCardDAO.getMerchantByTerminalId(terminalId);

    }

}
