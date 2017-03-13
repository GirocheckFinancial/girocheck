
package com.smartbt.vtsuite.boundary;

import java.io.StringReader;
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "DATA"
})
@XmlRootElement(name = "DATA")
public class CheckProcessResult {
  @XmlElement( name = "Status")
  private String Status;
  @XmlElement( name = "TransactionId")
  private String TransactionId;

    public CheckProcessResult() {
    }

    public CheckProcessResult(String xml) throws JAXBException {
      JAXBContext ctx = JAXBContext.newInstance(CheckProcessResult.class);
      Unmarshaller unmarshaller = ctx.createUnmarshaller();
      CheckProcessResult result = (CheckProcessResult)unmarshaller.unmarshal( new StreamSource( new StringReader( xml ) ) );
    
        System.out.println(result);
    }
    
    public static void main(String args[]) throws JAXBException{
        String xml = "<DATA><Status>0</Status><TransactionId>50082</TransactionId></DATA>";
        
        new CheckProcessResult(xml);
    }

    @Override
    public String toString() {
        return "[Status = " + getStatus() +  ", TransactionId = " + TransactionId + "]";
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the TransactionId
     */
    public String getTransactionId() {
        return TransactionId;
    }

    /**
     * @param TransactionId the TransactionId to set
     */
    public void setTransactionId(String TransactionId) {
        this.TransactionId = TransactionId;
    }
 

   
}
