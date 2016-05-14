/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: SMART BUSINESS TECHNOLOGY
 * License Type: Purchased
 */
package com.smartbt.vtsuite.servercommon.model;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
@XmlRootElement
public class ApplicationParameterValue implements Serializable {
	public ApplicationParameterValue() {
	}
	
	private int id;
	@JsonIgnore
	private com.smartbt.vtsuite.servercommon.model.Application application;
	@JsonProperty("parameter")
	private com.smartbt.vtsuite.servercommon.model.ApplicationParameter applicationParameter;
	
	private String value;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setApplication(com.smartbt.vtsuite.servercommon.model.Application value) {
		this.application = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Application getApplication() {
		return application;
	}
	
	public void setApplicationParameter(com.smartbt.vtsuite.servercommon.model.ApplicationParameter value) {
		this.applicationParameter = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.ApplicationParameter getApplicationParameter() {
		return applicationParameter;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
