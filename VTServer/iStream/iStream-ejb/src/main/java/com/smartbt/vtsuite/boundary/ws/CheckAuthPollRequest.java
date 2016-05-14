
package com.smartbt.vtsuite.boundary.ws;

import com.smartbt.vtsuite.boundary.util.MapUtil;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for checkAuthPollRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkAuthPollRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkId" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "checkAuthPollRequest", propOrder = {
    "checkId",
    "entityId",
    "password",
    "user"
})
public class CheckAuthPollRequest implements IBuilder{

    @XmlElement(nillable = true)
    protected List<Object> checkId;
    protected String entityId;
    protected String password;
    protected String user;

       @Override
   public CheckAuthPollRequest build(Map map) throws Exception {
      if(map.containsKey(ParameterName.CHECK_ID)){
          Object obj = map.get(ParameterName.CHECK_ID);
          
          if(obj instanceof List){
              checkId = (List)obj;
          }else{
              getCheckId().add(obj.toString());
          }
          
      }else{
          throw new Exception("CheckId required in the CheckAuthPoll transaction.");
      }
      
      entityId = MapUtil.getStringValueFromMap(map,ParameterName.ENTITY_ID, true);
      password = MapUtil.getStringValueFromMap(map,ParameterName.PASSWORD, true);
      user =MapUtil.getStringValueFromMap(map,ParameterName.USER, true);
    return this;
    }
   
   public Object getFirstCheckId(){
       if(!checkId.isEmpty())
           return checkId.get(0);
       return null;
   }
   
//       @Override
//   public CheckAuthPollRequest build(Map map) throws Exception {
//      if(map.containsKey(ParameterName.CHECK_ID)){
//          Object obj = map.get(ParameterName.CHECK_ID);
//          
//          if(obj instanceof List){
//              checkId = (List)obj;
//          }else{
//              getCheckId().add(obj.toString());
//          }
//          
//      }else{
//          throw new Exception("CheckId required in the CheckAuthPoll transaction.");
//      }
//      
//      
//      
//      entityId = MapUtil.getStringValueFromMap(map,ParameterName.ENTITY_ID, false);
//      password = MapUtil.getStringValueFromMap(map,ParameterName.PASSWORD, false);
//      user =MapUtil.getStringValueFromMap(map,ParameterName.USER, false);
//    return this;
//    }
    
    /**
     * Gets the value of the checkId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the checkId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCheckId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getCheckId() {
        if (checkId == null) {
            checkId = new ArrayList<Object>();
        }
        return this.checkId;
    }

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
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
