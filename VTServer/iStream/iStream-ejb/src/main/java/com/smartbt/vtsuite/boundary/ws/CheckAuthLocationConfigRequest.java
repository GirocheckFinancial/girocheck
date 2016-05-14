
package com.smartbt.vtsuite.boundary.ws;

import com.smartbt.vtsuite.boundary.util.MapUtil;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for checkAuthLocationConfigRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkAuthLocationConfigRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "checkAuthLocationConfigRequest", propOrder = {
    "entityId",
    "password",
    "user"
})
public class CheckAuthLocationConfigRequest implements IBuilder{

    protected String entityId;
    protected String password;
    protected String user;

  @Override
    public CheckAuthLocationConfigRequest build(Map map) throws Exception{
        
        
//         entityId = MapUtil.getIntegerValueFromMap(map, ParameterName.TERMINAL_ID, true);
        System.out.println("CheckAuthLocationConfigRequest.build() with values: entityId["+map.get(ParameterName.TERMINAL_ID_ISTREAM)+"], password["+map.get(ParameterName.TERMINAL_PASSWORD_ISTREAM)+"], user["+map.get(ParameterName.TERMINAL_USER_ISTREAM)+"]");
//         entityId = MapUtil.getLongValueFromMap(map, ParameterName.TERMINAL_ID_ISTREAM, true);
         entityId = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_ID_ISTREAM, true);
         password =  MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_PASSWORD_ISTREAM, true);
         user = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_USER_ISTREAM, true); 
         return this;
     }
    /**
     * Gets the value of the entityId property.
     * 
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     */
    public void setEntityId(String value) {
        this.entityId = value;
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
