 
package com.smartbt.girocheck.servercommon.display;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.model.TransactionMethod;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author suresh
 */


public class FeeScheduleDisplay implements Serializable{
    
    @NotNull
    private int id;
    private TransactionMethod method;
    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (merchant)")
    private Integer  merchant;
    private Boolean isdefault;    
    private String merchantName;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /**
     *
     * @return
     */
    public Integer  getMerchant() {
        return merchant;
    }

    /**
     *
     * @param merchant
     */
    public void setMerchant(Integer  merchant) {
        this.merchant = merchant;
    }

    /**
     *
     * @return
     */
    public TransactionMethod getMethod() {
        return method;
    }

    /**
     *
     * @param method
     */
    public void setMethod(TransactionMethod method) {
        this.method = method;
    }
    
    /**
     *
     * @return
     */
     public Boolean getIsdefault() {
        return isdefault;
    }

    /**
     *
     * @param isdefault
     */
    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }
    
    /**
     * @return the id
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param id the id to set
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
