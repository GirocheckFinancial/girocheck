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
import java.util.Collection;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Stores all application parameter.
 */
@XmlRootElement
public class ApplicationParameter implements Serializable {

    public ApplicationParameter() {
    }

    private int id;

    private String parameter;

    private String description;
    @JsonProperty("value")
    private String defaultValue;

    private com.smartbt.vtsuite.servercommon.model.DataType dataType;

    private boolean readOnly;

    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue> applicationParameterValues = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue>();

    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalParameterValue> terminalParameterValues = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.TerminalParameterValue>();

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

    public void setParameter(String value) {
        this.parameter = value;
    }

    public String getParameter() {
        return parameter;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Collection<TerminalParameterValue> getTerminalParameterValues() {
        return terminalParameterValues;
    }

    public void setTerminalParameterValues(Collection<TerminalParameterValue> terminalParameterValues) {
        this.terminalParameterValues = terminalParameterValues;
    }

    public Collection<ApplicationParameterValue> getApplicationParameterValues() {
        return applicationParameterValues;
    }

    public void setApplicationParameterValues(Collection<ApplicationParameterValue> applicationParameterValues) {
        this.applicationParameterValues = applicationParameterValues;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
