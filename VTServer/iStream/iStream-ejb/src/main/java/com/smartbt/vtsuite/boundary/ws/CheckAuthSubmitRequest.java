
package com.smartbt.vtsuite.boundary.ws;

import com.smartbt.vtsuite.boundary.util.MapUtil;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for checkAuthSubmitRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkAuthSubmitRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkAuthSubmitRequest", propOrder = {
    "action",
    "checkId",
    "password",
    "user"
})
public class CheckAuthSubmitRequest implements IBuilder{
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CheckAuthSubmitRequest.class);

    protected String action;
    protected String checkId;
    protected String password;
    protected String user;

    @Override 
    public CheckAuthSubmitRequest build(Map map) throws Exception{ 
      action=  MapUtil.getStringValueFromMap(map,ParameterName.ACTION, true);
      checkId=  MapUtil.getStringValueFromMap(map,ParameterName.REQUEST_ID, true);
      password=  MapUtil.getStringValueFromMap(map,ParameterName.PASSWORD, true);
      user=  MapUtil.getStringValueFromMap(map,ParameterName.USER, true);
      
//        log.debug("CheckAuthSubmitRequest.build:: action = " + action + ", checkId = " + checkId + ", user = " + user + ", passw = " + password );
      CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IStreamMDB] CheckAuthSubmitRequest.build:: action = " + action + ", checkId = " + checkId + ", user = " + user + ", passw = " + password,null);
        return this; 
  } 
    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the checkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckId() {
        return checkId;
    }

    /**
     * Sets the value of the checkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckId(String value) {
        this.checkId = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

}
