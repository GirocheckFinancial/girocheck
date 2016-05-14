
package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tecnicardConfirmationRequest", propOrder = {
   "user",
    "password",
    "requestId",
    "terminalId",
    
    "truncatedCheck"
//    "achForm",
})
public class TecnicardConfirmationRequest implements IMap{

    private String user;
    private String password;
    private String requestId;
    private String terminalId; 
    
    private byte[] truncatedCheck; 
//    private byte[] achForm; 
   
    
     @Override
   public Map toMap() {
      Map map = new HashMap();
      
        map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_CONFIRMATION);
      
        map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.REQUEST_ID, getRequestId());
        map.put(ParameterName.TERMINAL_ID, getTerminalId());
        
        map.put(ParameterName.TRUNCATED_CHECK, getTruncatedCheck());
//        map.put(ParameterName.ACH_FORM, getAchForm());

       
    return map;
    }

     public Map mock() {
      Map map = new HashMap();
      
        map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_CONFIRMATION);
      
        map.put(ParameterName.USER, getUser());
        map.put(ParameterName.PASSWORD, getPassword());
        map.put(ParameterName.REQUEST_ID, getRequestId());
        map.put(ParameterName.TERMINAL_ID, getTerminalId());
        
        map.put(ParameterName.TRUNCATED_CHECK, ParameterName.IMAGE);
//        map.put(ParameterName.ACH_FORM, ParameterName.IMAGE);

       
    return map;
    }
   
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the terminalId
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * @param terminalId the terminalId to set
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * @return the truncatedCheck
     */
    public byte[] getTruncatedCheck() {
        return truncatedCheck;
    }

    /**
     * @param truncatedCheck the truncatedCheck to set
     */
    public void setTruncatedCheck(byte[] truncatedCheck) {
        this.truncatedCheck = truncatedCheck;
    }

//    /**
//     * @return the achForm
//     */
//    public byte[] getAchForm() {
//        return achForm;
//    }
//
//    /**
//     * @param achForm the achForm to set
//     */
//    public void setAchForm(byte[] achForm) {
//        this.achForm = achForm;
//    }

    
}
