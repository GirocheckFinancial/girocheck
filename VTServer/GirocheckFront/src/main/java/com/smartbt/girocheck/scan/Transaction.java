package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.display.ActivityReportTransactionDisplay;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.DateUtils;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import com.smartbt.vtsuite.util.OperationType;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@XmlType(name = "Transaction", propOrder = {
    "transactionType",
    "dateTime",
    "amount"
})
public class Transaction extends MainResponseContainer {

    private static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    @XmlElement(name = "transactionType")
    private String transactionType;

    @XmlElement(name = "dateTime")
    private String dateTime;

    @XmlElement(name = "amount")
    private String amount;

    public Transaction() {
    }

    public Transaction(String transactionType, Date dateTime, Double amount) {
        this.transactionType = transactionType;
        this.dateTime = DateUtils.dateToISOFormat(dateTime);
        this.amount = String.format("%,.2f", amount);
    }

    public Transaction(ActivityReportTransactionDisplay display, OperationType operationType) {
        this.transactionType = operationType.getOperation();
        this.dateTime = DateUtils.dateToISOFormat(display.getDateTime());
        this.amount = String.format("%,.2f", display.getAmount());
    }

    private Double roundDouble(Double amount) {
        BigDecimal bd = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    /**
     * @return the transactionType
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

}
