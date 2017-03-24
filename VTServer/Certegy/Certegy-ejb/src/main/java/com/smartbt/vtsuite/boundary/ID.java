package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ID complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ID", propOrder = {
    "type",
    "value"
})
public class ID {

    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Value", required = true)
    protected String value;

    public static ID build(Map map) {
        ID id = new ID();
 
        //TODO put MX when is Matricula Consular
        String type = (String) map.get(ParameterName.STATE_ABBREVIATION);
        id.setType(type);

        String pId = (String) map.get(ParameterName.ID);
        id.setValue(pId);

        return id;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("            <ID>" ).append('\n');
        s.append("                <Type>").append(type).append("</Type>" ).append('\n');
        s.append("                <Value>").append(value).append("</Value>" ).append('\n');
        s.append("            <ID>" ).append('\n');
        return s.toString();
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the value property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setValue(String value) {
        this.value = value;
    }

}
