

package com.smartbt.vtsuite.validators;

import com.smartbt.vtsuite.servercommon.dao.ClerkDAO;
import com.smartbt.vtsuite.servercommon.dao.MerchantDAO;
import com.smartbt.vtsuite.servercommon.dao.TerminalDAO;
import com.smartbt.girocheck.servercommon.i18n.I18N;
import com.smartbt.girocheck.servercommon.i18n.Messages;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import java.util.List;
import javax.xml.bind.ValidationException;

/**
 * @author      Roberto Rodriguez ::  < roberto.rodriguez@smartbt.com >
 */
public class BoardingAMSValidator {

    
    public void validateUpdateMerchant(Merchant merchant) throws ValidationException{
        if(merchant.getNumber() != null)
        if(MerchantDAO.get().validateNoRepeatMerchant(merchant.getNumber(), merchant.getId()) != null)
                 throw new ValidationException(I18N.get(Messages.ERROR_MERCHANT_REPEATED, merchant.getNumber()));
    }
    
    public void validateNewMerchant(Merchant merchant) throws ValidationException{
        validateNameAndNumberMerchant( merchant);
        
        if(MerchantDAO.get().getMerchantByNumber(merchant.getNumber()) != null)
                 throw new ValidationException(I18N.get(Messages.ERROR_MERCHANT_REPEATED, merchant.getNumber()));
    }
    
    private void validateNameAndNumberMerchant(Merchant merchant)throws ValidationException{
        if(merchant.getNumber() == null)
            throw new ValidationException(I18N.get(Messages.ERROR_MERCHANT_NUMBER_NOT_NULL));
        
        if(merchant.getName() == null)
            throw new ValidationException(I18N.get(Messages.ERROR_MERCHANT_NAME_NOT_NULL));
     
    }
    
    public void validateNewTerminal(Terminal terminal) throws ValidationException{
        if(terminal.getTerminalId() == null)
            throw new ValidationException(I18N.get(Messages.ERROR_TERMINAL_ID_NOT_NULL));
        
        if(TerminalDAO.get().getTerminalByTerminalId(terminal.getTerminalId()) != null)
            throw new ValidationException(I18N.get(Messages.ERROR_TERMINAL_NUMBER_REPEATED, terminal.getTerminalId()));
    }
    
    public void validateUpdateTerminal(Terminal terminal) throws ValidationException{
//        if(terminal.getTerminalId() != null)
//        if(TerminalDAO.get().validateNoRepeatTerminalId(terminal.getTerminalId(), terminal.getId()) != null)
//                 throw new ValidationException(I18N.get(Messages.ERROR_TERMINAL_NUMBER_REPEATED, terminal.getTerminalId()));
    }
    
    
    public void validateClerk(Clerk clerk) throws ValidationException{
      
        if(clerk.getUsername() == null)
            throw new ValidationException(I18N.get(Messages.ERROR_CLERK_USERNAME_NOT_NULL));
        
        if(clerk.getPassword() == null)
            throw new ValidationException(I18N.get(Messages.ERROR_CLERK_REPEATED));
        
        if(ClerkDAO.get().findByUsername(clerk.getUsername()) != null)
            throw new ValidationException(I18N.get(Messages.ERROR_CLERK_REPEATED, clerk.getUsername()));
        
         }
    
    
    
    public void validateDontInsertRepeteadMerchant(List<String> merchantNumberList, Merchant merchant) throws ValidationException{
        if(merchant.getNumber() == null)return;
        for (String number : merchantNumberList) {
            if(merchant.getNumber().equalsIgnoreCase(number))
                throw new ValidationException(I18N.get(Messages.ERROR_MERCHANT_REPEATED,number));
        }
        merchantNumberList.add(merchant.getNumber());
         }
    
    public void validateDontInsertRepeteadClerkUserName(List<String> clerkUserNameList, Clerk clerk) throws ValidationException{
        if(clerk.getUsername() == null)return;
        for (String userName : clerkUserNameList) {
            if(clerk.getUsername().equals(userName))
                throw new ValidationException(I18N.get(Messages.ERROR_CLERK_REPEATED, userName));
        }
        clerkUserNameList.add(clerk.getUsername());
         }

    public void validateDontInsertRepeteadTerminalId(List<String> terminalIdList, Terminal terminal) throws ValidationException {
        if(terminal.getTerminalId() == null)return;
        for (String terminalId : terminalIdList) {
            if(terminal.getTerminalId().equals(terminalId))
                throw new ValidationException(I18N.get(Messages.ERROR_TERMINAL_NUMBER_REPEATED, terminalId));
        }
        terminalIdList.add(terminal.getTerminalId());
         }
        
    
}
