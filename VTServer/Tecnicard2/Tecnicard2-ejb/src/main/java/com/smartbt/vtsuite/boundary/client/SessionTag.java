
package com.smartbt.vtsuite.boundary.client;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SessionTag complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SessionTag">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SucessfullProcessing" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SystemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GMTTimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionTag", propOrder = {
    "sucessfullProcessing",
    "requestID",
    "systemName",
    "operationName",
    "time",
    "gmtTimeZone",
    "operationID",
    "resultCode",
    "resultMessage"
})
public class SessionTag {

    @XmlElement(name = "SucessfullProcessing")
    protected boolean sucessfullProcessing;
    @XmlElement(name = "RequestID")
    protected String requestID;
    @XmlElement(name = "SystemName")
    protected String systemName;
    @XmlElement(name = "OperationName")
    protected String operationName;
    @XmlElement(name = "Time")
    protected String time;
    @XmlElement(name = "GMTTimeZone")
    protected String gmtTimeZone;
    @XmlElement(name = "OperationID")
    protected String operationID;
    @XmlElement(name = "ResultCode")
    protected String resultCode;
    @XmlElement(name = "ResultMessage")
    protected String resultMessage;

    /**
     * Gets the value of the sucessfullProcessing property.
     * 
     * @param expectedResultCode
     * @return 
     */
    public Map getMap(String expectedResultCode){
        Map sessionTagMap = new HashMap();
          sessionTagMap.put(ParameterName.SUCESSFULL_PROCESSING , sucessfullProcessing);
          sessionTagMap.put(ParameterName.REQUEST_ID , requestID);
          sessionTagMap.put(ParameterName.SYSTEM_NAME , systemName);
          sessionTagMap.put(ParameterName.OPERATION_NAME , operationName);
          sessionTagMap.put(ParameterName.TIME , time);
          sessionTagMap.put(ParameterName.GMT_TIME_ZONE , gmtTimeZone);
          sessionTagMap.put(ParameterName.OPERATION_ID , operationID);
          sessionTagMap.put(ParameterName.RESULT_CODE , resultCode);
          sessionTagMap.put(ParameterName.RESULT_MESSAGE , resultMessage);
          
          if(expectedResultCode.equals( "0")){
              sessionTagMap.put( ParameterName.SESSION_TAG_IS_EXPECTED_ANSWER, true);
          }else{
              sessionTagMap.put( ParameterName.SESSION_TAG_IS_EXPECTED_ANSWER, resultCode.equals( expectedResultCode));
          }
          return sessionTagMap;
    }
    
    
    public boolean isSucessfullProcessing() {
        return sucessfullProcessing;
    }

    /**
     * Sets the value of the sucessfullProcessing property.
     * 
     */
    public void setSucessfullProcessing(boolean value) {
        this.sucessfullProcessing = value;
    }

    /**
     * Gets the value of the requestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Sets the value of the requestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestID(String value) {
        this.requestID = value;
    }

    /**
     * Gets the value of the systemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * Sets the value of the systemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemName(String value) {
        this.systemName = value;
    }

    /**
     * Gets the value of the operationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * Sets the value of the operationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationName(String value) {
        this.operationName = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the gmtTimeZone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMTTimeZone() {
        return gmtTimeZone;
    }

    /**
     * Sets the value of the gmtTimeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMTTimeZone(String value) {
        this.gmtTimeZone = value;
    }

    /**
     * Gets the value of the operationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationID() {
        return operationID;
    }

    /**
     * Sets the value of the operationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationID(String value) {
        this.operationID = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultCode(String value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the resultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Sets the value of the resultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMessage(String value) {
        this.resultMessage = value;
    }

}
