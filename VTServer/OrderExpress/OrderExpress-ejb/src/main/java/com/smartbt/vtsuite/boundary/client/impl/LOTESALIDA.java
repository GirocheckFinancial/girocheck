//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.08 at 02:12:15 PM EDT 
//
package com.smartbt.vtsuite.boundary.client.impl;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TRANSACCION_O">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CONTRATACION_OUTPUT">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AUTHO_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="OP_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="OP_CODE2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ID_CONSIGNOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ID_BENEFICIARY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="BANK_AUTHO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", propOrder = {
    "transacciono"
} )
@XmlRootElement( name = "LOTE_SALIDA" )
public class LOTESALIDA implements IMap {

    @XmlElement( name = "TRANSACCION_O", required = true )
    protected LOTESALIDA.TRANSACCIONO transacciono;

    /**
     * Gets the value of the transacciono property.
     *
     * @return possible object is {@link LOTESALIDA.TRANSACCIONO }
     *
     */
    public LOTESALIDA.TRANSACCIONO getTRANSACCIONO() {
        return transacciono;
    }

    /**
     * Sets the value of the transacciono property.
     *
     * @param value allowed object is {@link LOTESALIDA.TRANSACCIONO }
     *
     */
    public void setTRANSACCIONO( LOTESALIDA.TRANSACCIONO value ) {
        this.transacciono = value;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString( this );
    }

    @Override
    public Map toMap() {

        Map map = new HashMap();
        
        map.put( ParameterName.TRANSACTION_TYPE, TransactionType.ORDER_EXPRESS_CONTRATACIONES );
        map.put( ParameterName.AUTHO_NUMBER, getTRANSACCIONO().getCONTRATACIONOUTPUT().getAUTHONUMBER() );
        map.put( ParameterName.BANK_AUTHO, getTRANSACCIONO().getCONTRATACIONOUTPUT().getBANKAUTHO() );
        map.put( ParameterName.IDBENEFICIARY, getTRANSACCIONO().getCONTRATACIONOUTPUT().getIDBENEFICIARY() );
        map.put( ParameterName.IDCONSIGNOR, getTRANSACCIONO().getCONTRATACIONOUTPUT().getIDCONSIGNOR() );
        map.put( ParameterName.OP_CODE, getTRANSACCIONO().getCONTRATACIONOUTPUT().getOPCODE() );
        map.put( ParameterName.OP_CODE2, getTRANSACCIONO().getCONTRATACIONOUTPUT().getOPCODE2() );

        return map;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CONTRATACION_OUTPUT">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AUTHO_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="OP_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="OP_CODE2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ID_CONSIGNOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ID_BENEFICIARY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="BANK_AUTHO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType( XmlAccessType.FIELD )
    @XmlType( name = "", propOrder = {
        "contratacionoutput"
    } )
    public static class TRANSACCIONO {

        @XmlElement( name = "CONTRATACION_OUTPUT", required = true )
        protected LOTESALIDA.TRANSACCIONO.CONTRATACIONOUTPUT contratacionoutput;

        /**
         * Gets the value of the contratacionoutput property.
         *
         * @return possible object is
         *     {@link LOTESALIDA.TRANSACCIONO.CONTRATACIONOUTPUT }
         *
         */
        public LOTESALIDA.TRANSACCIONO.CONTRATACIONOUTPUT getCONTRATACIONOUTPUT() {
            return contratacionoutput;
        }

        /**
         * Sets the value of the contratacionoutput property.
         *
         * @param value allowed object is
         *     {@link LOTESALIDA.TRANSACCIONO.CONTRATACIONOUTPUT }
         *
         */
        public void setCONTRATACIONOUTPUT( LOTESALIDA.TRANSACCIONO.CONTRATACIONOUTPUT value ) {
            this.contratacionoutput = value;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         *
         * <p>
         * The following schema fragment specifies the expected content
         * contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="AUTHO_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="OP_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="OP_CODE2" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ID_CONSIGNOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ID_BENEFICIARY" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="BANK_AUTHO" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType( XmlAccessType.FIELD )
        @XmlType( name = "", propOrder = {
            "authonumber",
            "opcode",
            "opcode2",
            "idconsignor",
            "idbeneficiary",
            "bankautho"
        } )
        public static class CONTRATACIONOUTPUT {

            @XmlElement( name = "AUTHO_NUMBER", required = true )
            protected String authonumber;
            @XmlElement( name = "OP_CODE", required = true )
            protected String opcode;
            @XmlElement( name = "OP_CODE2", required = true )
            protected String opcode2;
            @XmlElement( name = "ID_CONSIGNOR", required = true )
            protected String idconsignor;
            @XmlElement( name = "ID_BENEFICIARY", required = true )
            protected String idbeneficiary;
            @XmlElement( name = "BANK_AUTHO", required = true )
            protected String bankautho;

            /**
             * Gets the value of the authonumber property.
             *
             * @return possible object is {@link String }
             *
             */
            public String getAUTHONUMBER() {
                return authonumber;
            }

            /**
             * Sets the value of the authonumber property.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setAUTHONUMBER( String value ) {
                this.authonumber = value;
            }

            /**
             * Gets the value of the opcode property.
             *
             * @return possible object is {@link String }
             *
             */
            public String getOPCODE() {
                return opcode;
            }

            /**
             * Sets the value of the opcode property.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setOPCODE( String value ) {
                this.opcode = value;
            }

            /**
             * Gets the value of the opcode2 property.
             *
             * @return possible object is {@link String }
             *
             */
            public String getOPCODE2() {
                return opcode2;
            }

            /**
             * Sets the value of the opcode2 property.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setOPCODE2( String value ) {
                this.opcode2 = value;
            }

            /**
             * Gets the value of the idconsignor property.
             *
             * @return possible object is {@link String }
             *
             */
            public String getIDCONSIGNOR() {
                return idconsignor;
            }

            /**
             * Sets the value of the idconsignor property.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setIDCONSIGNOR( String value ) {
                this.idconsignor = value;
            }

            /**
             * Gets the value of the idbeneficiary property.
             *
             * @return possible object is {@link String }
             *
             */
            public String getIDBENEFICIARY() {
                return idbeneficiary;
            }

            /**
             * Sets the value of the idbeneficiary property.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setIDBENEFICIARY( String value ) {
                this.idbeneficiary = value;
            }

            /**
             * Gets the value of the bankautho property.
             *
             * @return possible object is {@link String }
             *
             */
            public String getBANKAUTHO() {
                return bankautho;
            }

            /**
             * Sets the value of the bankautho property.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setBANKAUTHO( String value ) {
                this.bankautho = value;
            }

        }

    }

}
