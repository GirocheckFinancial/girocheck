package com.smartbt.vtsuite.boundary;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Enrollment complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="Enrollment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DOB" type="{http://fis.certegy.pca.com/}DOB"/>
 *         &lt;element name="Phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://fis.certegy.pca.com/}Address" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://fis.certegy.pca.com/}ID" minOccurs="0"/>
 *         &lt;element name="SwipedID" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Enrollment", propOrder = {
    "lastName",
    "firstName",
    "dob",
    "phone",
    "address",
    "id",
    "swipedID"
})
public class Enrollment {

    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "DOB", required = true)
    protected DOB dob;
    @XmlElement(name = "Phone")
    protected String phone;
    @XmlElement(name = "Address")
    protected Address address;
    @XmlElement(name = "ID")
    protected ID id;
    @XmlElement(name = "SwipedID")
    protected byte[] swipedID;

    public static Enrollment build(Map map) {
        Enrollment _this = new Enrollment();

        _this.setLastName((String) map.get(ParameterName.LAST_NAME));
        _this.setFirstName((String) map.get(ParameterName.FIRST_NAME));
        
        DOB dob = DOB.build(map);
        _this.setDOB(dob);

        _this.setPhone((String) map.get(ParameterName.PHONE));

        Address address = Address.build(map);

        _this.setAddress(address);

        ID id = ID.build(map);
        _this.setID(id);

        //TODO ask if this is idFront or idBack
       // byte[] idFront = (byte[]) map.get(ParameterName.IDFRONT);
      //  _this.setSwipedID(idFront);

        return _this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("        <Enrollment>" ).append('\n');
        s.append("            <LastName>").append(lastName).append("</LastName>" ).append('\n');
        s.append("            <FirstName>").append(firstName).append("</FirstName>" ).append('\n');
        s.append(dob.toString() ).append('\n');
        s.append("            <Phone>").append(phone).append("</Phone>" ).append('\n');
        s.append(address.toString() ).append('\n');
        s.append(id.toString() ).append('\n');

        if (swipedID != null && swipedID.length > 0) {
            s.append("            <SwipedID> HAS VALUE </SwipedID>" ).append('\n');
        }
        s.append("        <Enrollment>" ).append('\n');
        return s.toString();
    }

    /**
     * Gets the value of the lastName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the firstName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the dob property.
     *
     * @return possible object is {@link DOB }
     *
     */
    public DOB getDOB() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     *
     * @param value allowed object is {@link DOB }
     *
     */
    public void setDOB(DOB value) {
        this.dob = value;
    }

    /**
     * Gets the value of the phone property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the address property.
     *
     * @return possible object is {@link Address }
     *
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     *
     * @param value allowed object is {@link Address }
     *
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link ID }
     *
     */
    public ID getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link ID }
     *
     */
    public void setID(ID value) {
        this.id = value;
    }

    /**
     * Gets the value of the swipedID property.
     *
     * @return possible object is byte[]
     */
    public byte[] getSwipedID() {
        return swipedID;
    }

    /**
     * Sets the value of the swipedID property.
     *
     * @param value allowed object is byte[]
     */
    public void setSwipedID(byte[] value) {
        this.swipedID = value;
    }

}
