
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
 * <p>Java class for CheckAuthLocationConfigRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckAuthLocationConfigRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configList" type="{http://web.service.scanner.tc.com/}code" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAuthLocationConfigRes", propOrder = {
    "configList"
})
public class CheckAuthLocationConfigRes implements IMap{

    @XmlElement(nillable = true)
    protected List<Code> configList;

    
 public Map toMap(){
        Map map = new HashMap();
        
        List<Map> list = new ArrayList<Map>();
        
           for (Code code  : getConfigList()) {
               list.add(code.toMap());
           }
         
        map.put(ParameterName.CONFIG_LIST, list);
        return map;
    }
    
    /**
     * Gets the value of the configList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the configList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfigList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Code }
     * 
     * 
     */
    public List<Code> getConfigList() {
        if (configList == null) {
            configList = new ArrayList<Code>();
        }
        return this.configList;
    }

    public void setConfigList(List<Code> configList) {
        this.configList = configList;
    }

    
    
}
