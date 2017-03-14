
package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author rrodriguez
 */
 
@XmlRootElement(name = "DATA")
public class CheckProcessResult { 
  private String status; 
  private String transactionId;

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
 

   
}
