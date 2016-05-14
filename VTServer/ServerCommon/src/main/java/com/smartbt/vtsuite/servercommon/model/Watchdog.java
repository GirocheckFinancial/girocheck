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
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement
public class Watchdog implements Serializable {

    public Watchdog() {
    }

    private int id;

    private com.smartbt.vtsuite.servercommon.model.DataType dataType;

    private String name;

    private String description;

    private Boolean hasValue;

    private Boolean active;
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> watchdogEntity = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogEntity>();

    public void setId(int value) {
        this.id = value;
    }

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

    @JsonIgnore
    public void setHasValue(boolean value) {
        setHasValue(new Boolean(value));
    }

    public void setHasValue(Boolean value) {
        this.hasValue = value;
    }

    public Boolean getHasValue() {
        return hasValue;
    }

    public void setDataType(com.smartbt.vtsuite.servercommon.model.DataType value) {
        this.dataType = value;
    }

    public com.smartbt.vtsuite.servercommon.model.DataType getDataType() {
        return dataType;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setWatchdogEntity(java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> value) {
        this.watchdogEntity = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> getWatchdogEntity() {
        return watchdogEntity;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
