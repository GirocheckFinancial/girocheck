package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ActivityReportRes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ActivityReportRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityReportRes", propOrder = {
    "check2cardCount",
    "cash2cardCount",
    "card2merchantCount",
    
    "check2cardTotal",
    "cash2cardTotal",
    "card2merchantTotal",
    
    "cashIn",
    "cashOut",
    "netCash",
    
    "totalRows",
    "success",
    
    "check2cardTransactions",
    "cash2cardTransactions",
    "card2merchantTransactions",
})
public class ActivityReportRes extends MainResponseContainer implements IBuilder {

    @XmlElement(name = "check2cardCount")
    private Integer check2cardCount;

    @XmlElement(name = "cash2cardCount")
    private Integer cash2cardCount;

    @XmlElement(name = "card2merchantCount")
    private Integer card2merchantCount;

    @XmlElement(name = "check2cardTotal")
    private Double check2cardTotal;

    @XmlElement(name = "cash2cardTotal")
    private Double cash2cardTotal;

    @XmlElement(name = "card2merchantTotal")
    private Double card2merchantTotal;
    
     @XmlElement(name = "cashIn")
    private Double cashIn;

    @XmlElement(name = "cashOut")
    private Double cashOut;

    @XmlElement(name = "netCash")
    private Double netCash;

    @XmlElement(name = "totalRows")
    private Integer totalRows;

    @XmlElement(name = "success")
    private Boolean success;
    
    

    @XmlElement(name = "check2cardTransactions")
    private Transactions check2cardTransactions;

    @XmlElement(name = "cash2cardTransactions")
    private Transactions cash2cardTransactions;

    @XmlElement(name = "card2merchantTransactions")
    private Transactions card2merchantTransactions;

     
    
    @Override
    public ActivityReportRes build(Map map) throws Exception {
        return this;
    }

    public ActivityReportRes mock() {
        return this;
    }
    
    private Double roundDouble(Double amount){
        BigDecimal bd = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
     

    /**
     * @return the check2cardCount
     */
    public Integer getCheck2cardCount() {
        return check2cardCount;
    }

    /**
     * @param check2cardCount the check2cardCount to set
     */
    public void setCheck2cardCount(Integer check2cardCount) {
        this.check2cardCount = check2cardCount;
    }

    /**
     * @return the cash2cardCount
     */
    public Integer getCash2cardCount() {
        return cash2cardCount;
    }

    /**
     * @param cash2cardCount the cash2cardCount to set
     */
    public void setCash2cardCount(Integer cash2cardCount) {
        this.cash2cardCount = cash2cardCount;
    }

    /**
     * @return the card2merchantCount
     */
    public Integer getCard2merchantCount() {
        return card2merchantCount;
    }

    /**
     * @param card2merchantCount the card2merchantCount to set
     */
    public void setCard2merchantCount(Integer card2merchantCount) {
        this.card2merchantCount = card2merchantCount;
    }

    /**
     * @return the check2cardTotal
     */
    public Double getCheck2cardTotal() {
        return check2cardTotal;
    }
     

    /**
     * @param check2cardTotal the check2cardTotal to set
     */
    public void setCheck2cardTotal(Double check2cardTotal) {
        this.check2cardTotal =  roundDouble(check2cardTotal);
    }

    /**
     * @return the cash2cardTotal
     */
    public Double getCash2cardTotal() {
        return cash2cardTotal;
    }
     
    /**
     * @param cash2cardTotal the cash2cardTotal to set
     */
    public void setCash2cardTotal(Double cash2cardTotal) {
        this.cash2cardTotal = roundDouble(cash2cardTotal);
    }

    /**
     * @return the card2merchantTotal
     */
    public Double getCard2merchantTotal() {
        return card2merchantTotal;
    }
     
    /**
     * @param card2merchantTotal the card2merchantTotal to set
     */
    public void setCard2merchantTotal(Double card2merchantTotal) {
        this.card2merchantTotal = roundDouble(card2merchantTotal);
    }

    /**
     * @return the cashIn
     */
    public Double getCashIn() {
        return cashIn;
    }
     

    /**
     * @param cashIn the cashIn to set
     */
    public void setCashIn(Double cashIn) {
        this.cashIn = roundDouble(cashIn);
    }

    /**
     * @return the cashOut
     */
    public Double getCashOut() {
        return cashOut;
    } 
    /**
     * @param cashOut the cashOut to set
     */
    public void setCashOut(Double cashOut) {
        this.cashOut = roundDouble(cashOut);
    }

    /**
     * @return the netCash
     */
    public Double getNetCash() { 
        return roundDouble(netCash);
    }
     
    /**
     * @param netCash the netCash to set
     */
    public void setNetCash(Double netCash) { 
        this.netCash = roundDouble(netCash);
    }

    /**
     * @return the totalRows
     */
    public Integer getTotalRows() {
        return totalRows;
    }

    /**
     * @param totalRows the totalRows to set
     */
    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return the check2cardTransactions
     */
    public Transactions getCheck2cardTransactions() {
        return check2cardTransactions;
    }

    /**
     * @param check2cardTransactions the check2cardTransactions to set
     */
    public void setCheck2cardTransactions(Transactions check2cardTransactions) {
        this.check2cardTransactions = check2cardTransactions;
    }

    /**
     * @return the cash2cardTransactions
     */
    public Transactions getCash2cardTransactions() {
        return cash2cardTransactions;
    }

    /**
     * @param cash2cardTransactions the cash2cardTransactions to set
     */
    public void setCash2cardTransactions(Transactions cash2cardTransactions) {
        this.cash2cardTransactions = cash2cardTransactions;
    }

    /**
     * @return the card2merchantTransactions
     */
    public Transactions getCard2merchantTransactions() {
        return card2merchantTransactions;
    }

    /**
     * @param card2merchantTransactions the card2merchantTransactions to set
     */
    public void setCard2merchantTransactions(Transactions card2merchantTransactions) {
        this.card2merchantTransactions = card2merchantTransactions;
    }
 

}
