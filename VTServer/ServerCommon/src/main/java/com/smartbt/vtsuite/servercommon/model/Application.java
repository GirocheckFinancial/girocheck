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

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Stores all applications.
 */
@javax.xml.bind.annotation.XmlRootElement
public class Application implements Serializable {

    public Application() {
    }

    private int id;

    private String name;

    private String description;
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue> applicationParameterValues = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Version> version = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Version>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ProductType> productType = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ProductType>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege> applicationClerkPrivilege = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege>();

    /**
     * Table record identification.
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Table record identification.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return description;
    }

    public void setApplicationParameterValues(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue> value) {
        this.applicationParameterValues = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue> getApplicationParameterValues() {
        return applicationParameterValues;
    }

    public void setVersion(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Version> value) {
        this.version = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Version> getVersion() {
        return version;
    }

    public void setProductType(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ProductType> value) {
        this.productType = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ProductType> getProductType() {
        return productType;
    }

    public void setApplicationClerkPrivilege(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege> value) {
        this.applicationClerkPrivilege = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege> getApplicationClerkPrivilege() {
        return applicationClerkPrivilege;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
