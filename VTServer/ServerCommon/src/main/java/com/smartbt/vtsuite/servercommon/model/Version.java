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
@XmlRootElement
public class Version implements Serializable {
	public Version() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Application application;
	
	private String url1;
	
	private String url2;
	
	private Integer version;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUrl1(String value) {
		this.url1 = value;
	}
	
	public String getUrl1() {
		return url1;
	}
	
	public void setUrl2(String value) {
		this.url2 = value;
	}
	
	public String getUrl2() {
		return url2;
	}
	@JsonIgnore
	public void setVersion(int value) {
		setVersion(new Integer(value));
	}
	
	public void setVersion(Integer value) {
		this.version = value;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setApplication(com.smartbt.vtsuite.servercommon.model.Application value) {
		this.application = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Application getApplication() {
		return application;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
