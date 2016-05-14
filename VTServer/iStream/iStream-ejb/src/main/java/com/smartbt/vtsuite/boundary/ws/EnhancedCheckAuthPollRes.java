
package com.smartbt.vtsuite.boundary.ws;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnhancedCheckAuthPollRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnhancedCheckAuthPollRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="items" type="{http://web.service.scanner.tc.com/}enhancedCheckAuthPollItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnhancedCheckAuthPollRes", propOrder = {
    "items"
})
public class EnhancedCheckAuthPollRes implements IMap{

    @XmlElement(nillable = true)
    protected List<EnhancedCheckAuthPollItem> items;

 public Map toMap(){
     
        Map map = new HashMap();
        
        List<Map> list = new ArrayList<>();
                
           for (EnhancedCheckAuthPollItem item : getItems()) {
              list.add(item.toMap());
           }
        
        map.put(ParameterName.ENHANCED_CHECK_AUTH_POLL_ITEMS,list );
        return map;
    }
    
    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnhancedCheckAuthPollItem }
     * 
     * 
     */
    public List<EnhancedCheckAuthPollItem> getItems() {
        if (items == null) {
            items = new ArrayList<EnhancedCheckAuthPollItem>();
        }
        return this.items;
    }

}
