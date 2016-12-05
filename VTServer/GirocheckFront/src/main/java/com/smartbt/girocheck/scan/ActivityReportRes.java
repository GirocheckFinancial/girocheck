package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import java.math.BigDecimal;
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
    private String check2cardTotal; 

    @XmlElement(name = "cash2cardTotal")
    private String cash2cardTotal; 

    @XmlElement(name = "card2merchantTotal")
    private String card2merchantTotal;
    
     @XmlElement(name = "cashIn")
    private String cashIn;

    @XmlElement(name = "cashOut")
    private String cashOut;

    @XmlElement(name = "netCash")
    private String netCash;

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
 
     public static void main(String[] args){
         Double d = 45.760000000000005;
         System.out.println(String.format("%,.2f", d));
     }
    
    @Override
    public ActivityReportRes build(Map map) throws Exception {
        this.check2cardCount = (Integer)map.get(ParameterName.CHECK2CARD_COUNT);
        this.cash2cardCount = (Integer)map.get(ParameterName.CASH2CARD_COUNT);
        this.card2merchantCount = (Integer)map.get(ParameterName.CARD2MERCHANT_COUNT);
        
        Double check2cardTotalDouble = (Double)map.get(ParameterName.CHECK2CARD_TOTAL);
        this.setCheck2cardTotal(String.format("%,.2f", check2cardTotalDouble));
        
        Double cash2cardTotalDouble = (Double)map.get(ParameterName.CASH2CARD_TOTAL);
        this.setCash2cardTotal(String.format("%,.2f", cash2cardTotalDouble));
        
        Double card2merchantTotalDouble =  (Double)map.get(ParameterName.CARD2MERCHANT_TOTAL);
        this.setCard2merchantTotal(String.format("%,.2f", card2merchantTotalDouble));
        
        Double cashInDouble = (Double)map.get(ParameterName.CASH_IN);
        this.setCashIn(String.format("%,.2f", cashInDouble));
        
        Double cashOutDouble = (Double)map.get(ParameterName.CASH_OUT);
        this.setCashOut(String.format("%,.2f", cashOutDouble));
        
        Double netCash = (Double)map.get(ParameterName.NET_CASH);
        this.setNetCash(String.format("%,.2f", netCash));
        
//        
//        this.check2cardTotal = roundDouble((Double)map.get(ParameterName.CHECK2CARD_TOTAL));
//        this.cash2cardTotal = roundDouble((Double)map.get(ParameterName.CASH2CARD_TOTAL));
//        this.card2merchantTotal = roundDouble((Double)map.get(ParameterName.CARD2MERCHANT_TOTAL));
//        
//        this.cashIn = roundDouble((Double)map.get(ParameterName.CASH_IN));
//        this.cashOut = roundDouble((Double)map.get(ParameterName.CASH_OUT));
//        this.netCash = roundDouble((Double)map.get(ParameterName.NET_CASH));
//        
        this.totalRows = (Integer)map.get(ParameterName.TOTAL_ROWS);
        this.success = (Boolean)map.get(ParameterName.SUCCESS);
        
        this.check2cardTransactions = (Transactions)map.get(ParameterName.CHECK2CARD_TRANSACTIONS);
        this.cash2cardTransactions = (Transactions)map.get(ParameterName.CASH2CARD_TRANSACTIONS);
        this.card2merchantTransactions = (Transactions)map.get(ParameterName.CARD2MERCHANT_TRANSACTIONS);
        
        return this;
    }

    public ActivityReportRes mock() {
        return this;
    }
    
    private Double roundDouble(Double amount){
        if(amount == null)return 0D;
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
     * @param check2cardTotal the check2cardTotal to set
     */
   
      
    
    
 

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

    /**
     * @return the check2cardTotal
     */
    public String getCheck2cardTotal() {
        return check2cardTotal;
    }

    /**
     * @param check2cardTotal the check2cardTotal to set
     */
    public void setCheck2cardTotal(String check2cardTotal) {
        this.check2cardTotal = check2cardTotal;
    }

   

    /**
     * @return the cash2cardTotal
     */
    public String getCash2cardTotal() {
        return cash2cardTotal;
    }

    /**
     * @param cash2cardTotal the cash2cardTotal to set
     */
    public void setCash2cardTotal(String cash2cardTotal) {
        this.cash2cardTotal = cash2cardTotal;
    }

    /**
     * @return the card2merchantTotal
     */
    public String getCard2merchantTotal() {
        return card2merchantTotal;
    }

    /**
     * @param card2merchantTotal the card2merchantTotal to set
     */
    public void setCard2merchantTotal(String card2merchantTotal) {
        this.card2merchantTotal = card2merchantTotal;
    }

    /**
     * @return the cashIn
     */
    public String getCashIn() {
        return cashIn;
    }

    /**
     * @param cashIn the cashIn to set
     */
    public void setCashIn(String cashIn) {
        this.cashIn = cashIn;
    }

    /**
     * @return the cashOut
     */
    public String getCashOut() {
        return cashOut;
    }

    /**
     * @param cashOut the cashOut to set
     */
    public void setCashOut(String cashOut) {
        this.cashOut = cashOut;
    }

    /**
     * @return the netCash
     */
    public String getNetCash() {
        return netCash;
    }

    /**
     * @param netCash the netCash to set
     */
    public void setNetCash(String netCash) {
        this.netCash = netCash;
    }
 
 

}
