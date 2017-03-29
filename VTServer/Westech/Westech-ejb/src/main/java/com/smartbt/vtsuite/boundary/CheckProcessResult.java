
package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
 
@XmlRootElement(name = "DATA")
public class CheckProcessResult { 
  private String status; 
  private String transactionId;
  private String message;

    public CheckProcessResult() {
    }
 
    
    public static void main(String args[]) throws JAXBException{
        String xml = "<DATA><Status>0</Status><TransactionId>50082</TransactionId></DATA>";
        
    }

    @Override
    public String toString() {
        return "[Status = " + getStatus() +  ", TransactionId = " + transactionId + "]";
    }
    
    public Map toMap(){
        Map map = new HashMap();
        map.put(ParameterName.STATUS, status);
        map.put(ParameterName.CHECK_ID, transactionId);
        map.put(ParameterName.MESSAGE, message); 
        return map;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param Status the Status to set
     */
    @XmlElement(name = "Status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the TransactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    @XmlElement(name = "TransactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    @XmlElement(name = "Message")
    public void setMessage(String message) {
        this.message = message;
    }
 

   
}
