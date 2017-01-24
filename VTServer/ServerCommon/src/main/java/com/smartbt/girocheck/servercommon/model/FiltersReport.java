package com.smartbt.girocheck.servercommon.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejo
 */
@XmlRootElement
public class FiltersReport {
    private Integer id;
    private String searchFilter; 
    private Date startRangeDate;
    private Date endRangeDate; 
    private Integer transactionType;
    private String operation;
    private Boolean filterAmmount;
    private Integer ammountType;
    private Integer opType;
    private String ammountString;
    private Boolean pending;
 

    public FiltersReport() {
    }

    /**
     * @return the searchFilter
     */
    public String getSearchFilter() {
        return searchFilter;
    }

    /**
     * @param searchFilter the searchFilter to set
     */
    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
    }

    /**
     * @return the startRangeDate
     */
    public Date getStartRangeDate() {
        return startRangeDate;
    }

    /**
     * @param startRangeDate the startRangeDate to set
     */
    public void setStartRangeDate(Date startRangeDate) {
        this.startRangeDate = startRangeDate;
    }

    /**
     * @return the endRangeDate
     */
    public Date getEndRangeDate() {
        return endRangeDate;
    }

    /**
     * @param endRangeDate the endRangeDate to set
     */
    public void setEndRangeDate(Date endRangeDate) {
        this.endRangeDate = endRangeDate;
    }
 
    /**
     * @return the transactionType
     */
    public Integer getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return the filterAmmount
     */
    public Boolean getFilterAmmount() {
        return filterAmmount;
    }

    /**
     * @param filterAmmount the filterAmmount to set
     */
    public void setFilterAmmount(Boolean filterAmmount) {
        this.filterAmmount = filterAmmount;
    }

    /**
     * @return the ammountType
     */
    public Integer getAmmountType() {
        return ammountType;
    }

    /**
     * @param ammountType the ammountType to set
     */
    public void setAmmountType(Integer ammountType) {
        this.ammountType = ammountType;
    }

    /**
     * @return the opType
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     * @param opType the opType to set
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     * @return the ammountString
     */
    public String getAmmountString() {
        return ammountString;
    }

    /**
     * @param ammountString the ammountString to set
     */
    public void setAmmountString(String ammountString) {
        this.ammountString = ammountString;
    }

    /**
     * @return the pending
     */
    public Boolean getPending() {
        return pending;
    }

    /**
     * @param pending the pending to set
     */
    public void setPending(Boolean pending) {
        this.pending = pending;
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
 

}
