package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import com.smartbt.vtsuite.util.MapUtil;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.jboss.jandex.Main;

/**
 * <p>
 * Java class for PersonalInfoRes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CheckAuthRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "OrderExpressRes", propOrder = {
    "authoNumber",
    "opCode",
    "opCode2",
    "idConsignor",
    "idBeneficiary",
    "bankAutho"
    
} )
public class OrderExpressRes implements IBuilder {
    private String authoNumber;
    private String opCode;
    private String opCode2;
    private String idConsignor;
    private String idBeneficiary;
    private String bankAutho;

    @Override
    public OrderExpressRes build(Map map) throws Exception {
        setAuthoNumber( MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false) );
        setOpCode( MapUtil.getStringValueFromMap(map, ParameterName.OP_CODE, false) );
        setOpCode2( MapUtil.getStringValueFromMap(map, ParameterName.OP_CODE2, false) );
        setIdConsignor( MapUtil.getStringValueFromMap(map, ParameterName.IDCONSIGNOR, false) );
        setIdBeneficiary( MapUtil.getStringValueFromMap(map, ParameterName.ID_BENEFICIARY, false) );
        setBankAutho( MapUtil.getStringValueFromMap(map, ParameterName.BANK_AUTHO, false) );
        
        return this;
    }

    /**
     * @return the authoNumber
     */
    public String getAuthoNumber() {
        return authoNumber;
    }

    /**
     * @param authoNumber the authoNumber to set
     */
    public void setAuthoNumber( String authoNumber ) {
        this.authoNumber = authoNumber;
    }

    /**
     * @return the opCode
     */
    public String getOpCode() {
        return opCode;
    }

    /**
     * @param opCode the opCode to set
     */
    public void setOpCode( String opCode ) {
        this.opCode = opCode;
    }

    /**
     * @return the opCode2
     */
    public String getOpCode2() {
        return opCode2;
    }

    /**
     * @param opCode2 the opCode2 to set
     */
    public void setOpCode2( String opCode2 ) {
        this.opCode2 = opCode2;
    }

    /**
     * @return the idConsignor
     */
    public String getIdConsignor() {
        return idConsignor;
    }

    /**
     * @param idConsignor the idConsignor to set
     */
    public void setIdConsignor( String idConsignor ) {
        this.idConsignor = idConsignor;
    }

    /**
     * @return the idBeneficiary
     */
    public String getIdBeneficiary() {
        return idBeneficiary;
    }

    /**
     * @param idBeneficiary the idBeneficiary to set
     */
    public void setIdBeneficiary( String idBeneficiary ) {
        this.idBeneficiary = idBeneficiary;
    }

    /**
     * @return the bankAutho
     */
    public String getBankAutho() {
        return bankAutho;
    }

    /**
     * @param bankAutho the bankAutho to set
     */
    public void setBankAutho( String bankAutho ) {
        this.bankAutho = bankAutho;
    }

  
}
