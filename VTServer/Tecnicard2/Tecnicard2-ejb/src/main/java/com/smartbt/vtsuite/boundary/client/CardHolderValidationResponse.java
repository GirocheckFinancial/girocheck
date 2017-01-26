package com.smartbt.vtsuite.boundary.client;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CardHolderValidationResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CardHolderValidationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://SistemasGalileo.com/Services/}MainResponseContainer">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardHolderValidationResponse")
public class CardHolderValidationResponse
        extends MainResponseContainer implements IMap {

    private static final String EXPECTED_RESULT_CODE = "100013";
    
    @Override
    public Map toMap() {
        return super.getMap(EXPECTED_RESULT_CODE);
    }
}
