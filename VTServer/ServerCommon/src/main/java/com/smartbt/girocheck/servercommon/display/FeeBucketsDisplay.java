package com.smartbt.girocheck.servercommon.model;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @author Alejo
 */


public class FeeBucketsDisplay implements Serializable{
    
    private int id;
    /*
    * Amount
    */
    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (minimum)")
    private float minimum;
    /*
    * Fee
    */
    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (fixed)")
    private float fixed;
    
    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (percentage)")  
    private float percentage;
    
    private FeeSchedules feeSchedule;

    public FeeBucketsDisplay() {
    }
    
    public Map toMap(){
        Map map = new HashMap();
        map.put(ParameterName.AUTH_FEEM, minimum);  
        map.put(ParameterName.AUTH_FEEP, percentage);     
        return map;
    }

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
     * @return the minimum
     */
    public float getMinimum() {
        return minimum;
    }

    /**
     * @param minimum the minimum to set
     */
    public void setMinimum(float minimum) {
        this.minimum = minimum;
    }

    /**
     * @return the fixed
     */
    public float getFixed() {
        return fixed;
    }

    /**
     * @param fixed the fixed to set
     */
    public void setFixed(float fixed) {
        this.fixed = fixed;
    }

    /**
     * @return the percentage
     */
    public float getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    /**
     * @return the feeSchedule
     */
    public FeeSchedules getFeeSchedule() {
        return feeSchedule;
    }

    /**
     * @param feeSchedule the feeSchedule to set
     */
    public void setFeeSchedule(FeeSchedules feeSchedule) {
        this.feeSchedule = feeSchedule;
    }
    
}
