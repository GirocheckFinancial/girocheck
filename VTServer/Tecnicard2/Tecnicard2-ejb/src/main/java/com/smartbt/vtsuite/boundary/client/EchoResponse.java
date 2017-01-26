package com.smartbt.vtsuite.boundary.client;

import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for EchoResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="EchoResponse">
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
@XmlType(name = "EchoResponse")
public class EchoResponse
        extends MainResponseContainer implements IMap {

    private static String EXPECTED_RESULT_CODE = "0";
    
    @Override
    public Map toMap() {
        Map map = super.getMap(EXPECTED_RESULT_CODE);
        return map;
    }
}
