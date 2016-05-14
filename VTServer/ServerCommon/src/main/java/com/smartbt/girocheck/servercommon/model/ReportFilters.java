
package com.smartbt.girocheck.servercommon.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejo
 */

@XmlRootElement
public class ReportFilters {
    
    
    //Transactions
        private Integer id;
        private String startRangeDate;
        private String endRangeDate;
        private String amount;
        private String token;
        private Date createAt;
        
    //Entity(Grouping,Merchant)
        
        private Integer entityId;
        

    public ReportFilters() {
    }

    /**
     * @return the startRangeDate
     */
    public String getStartRangeDate() {
        return startRangeDate;
    }

    /**
     * @param startRangeDate the startRangeDate to set
     */
    public void setStartRangeDate(String startRangeDate) {
        this.startRangeDate = startRangeDate;
    }

    /**
     * @return the endRangeDate
     */
    public String getEndRangeDate() {
        return endRangeDate;
    }

    /**
     * @param endRangeDate the endRangeDate to set
     */
    public void setEndRangeDate(String endRangeDate) {
        this.endRangeDate = endRangeDate;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the key
     */
    public String getToken() {
        return token;
    }

    /**
     * @param key the key to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the createAt
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt the createAt to set
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return the groupingId
     */
    public Integer getEntityId() {
        return entityId;
    }

    /**
     * @param groupingId the groupingId to set
     */
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
        
        
        
}
