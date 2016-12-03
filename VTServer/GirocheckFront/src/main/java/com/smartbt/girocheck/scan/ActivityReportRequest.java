package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for checkAuthRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="checkAuthRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entrada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *        &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "activityReportRequest", propOrder = {
    "terminalId",
    "startDate",
    "endDate"
})
public class ActivityReportRequest implements IMap {

    private String terminalId;
    private String startDate;
    private String endDate;

    private Date getDateFromString(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }

        try {
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            return df.parse(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Date Format");
        }
    }

    @Override
    public Map toMap() {
        Map map = new HashMap(); 
        map.put(ParameterName.TERMINAL_ID, terminalId); 
        map.put(ParameterName.START_DATE, getDateFromString(getStartDate()));
        map.put(ParameterName.END_DATE, getDateFromString(getEndDate()));
         
        System.out.println("toMap()");
        System.out.println("terminalId = " + terminalId);
        System.out.println("startDate = " + getStartDate());
        System.out.println("endDate = " + getEndDate());
        return map;
    }

    public Map mock() {
        Map map = new HashMap();

        return map;
    }

    /**
     * @return the terminalId
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * @param terminalId the terminalId to set
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
 
  
}
