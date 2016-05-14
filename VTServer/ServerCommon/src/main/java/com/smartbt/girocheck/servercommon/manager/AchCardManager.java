package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.AchCardDAO;
import com.smartbt.girocheck.servercommon.model.AchCard;
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
    
    public AchCard findById(int id){
        return achCardDAO.findById( id );
    }

    public void save( AchCard achCard ) {
        AchCard achCardEncrypted = new AchCard();
        achCardEncrypted.setAchform(achCard.getAchform());
        try {
            achCardEncrypted.setCardNumber(Utils.encryptCredictCardNumber(achCard.getCardNumber()));
        } catch (NoSuchAlgorithmException ex) {
//            log.debug("[AchCardDAO]", ex);
            ex.printStackTrace();
        }
        achCardEncrypted.setMerchant(achCard.getMerchant());
        achCardDAO.save( achCardEncrypted );
    }
    
    public Boolean existAchCard(String terminalId, String cardNumber){
        String cardN = "";
        try {
            cardN = Utils.encryptCredictCardNumber(cardNumber);
        } catch (NoSuchAlgorithmException ex) {
//            log.debug("[AchCardDAO]", ex);
            ex.printStackTrace();
        }
        return achCardDAO.existAchCard(terminalId, cardN);
        
    }
    
    public Merchant getMerchantByTerminalId(String terminalId){
        
        return achCardDAO.getMerchantByTerminalId(terminalId);
        
    }
    
}
