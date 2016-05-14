package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;

/**
 *
 * @author Alejo
 */


public class FeeBuckets implements Serializable{
    
    private int id;
    /*
    * Amount
    */
    private float minimum;
    /*
    * Fee
    */
    private float fixed;
    private float percentage;
    private FeeSchedules feeSchedule;

    public FeeBuckets() {
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
