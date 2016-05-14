/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 *
 * This is an automatic generated file. It will be regenerated every time you
 * generate persistence class.
 *
 * Modifying its content may cause the program not work, or your work may lost.
 */
/**
 * Licensee: SMART BUSINESS TECHNOLOGY License Type: Purchased
 */
package com.smartbt.vtsuite.servercommon.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Establish the relationship (m:m) between merchants and parameters.
 */
@XmlRootElement
public class MerchantParameterValue implements Serializable {

    public MerchantParameterValue() {
    }

    private int id;
    @JsonIgnore
    private com.smartbt.vtsuite.servercommon.model.Merchant merchant;
    @JsonProperty("parameter")
    private com.smartbt.vtsuite.servercommon.model.MerchantParameter merchantParameter;

    private String value;

    /**
     * Table record identification.
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Table record identification.
     */
    public int getId() {
        return id;
    }

    /**
     * The value the parameter has for the specific merchant.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * The value the parameter has for the specific merchant.
     */
    public String getValue() {
        return value;
    }

    public void setMerchantParameter(com.smartbt.vtsuite.servercommon.model.MerchantParameter value) {
        this.merchantParameter = value;
    }

    public com.smartbt.vtsuite.servercommon.model.MerchantParameter getMerchantParameter() {
        return merchantParameter;
    }

    public void setMerchant(com.smartbt.vtsuite.servercommon.model.Merchant value) {
        this.merchant = value;
    }

    public com.smartbt.vtsuite.servercommon.model.Merchant getMerchant() {
        return merchant;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
