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
public class ProductType implements Serializable {
	public ProductType() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Application application;
	
	private String name;
	
	private String description;
	
	private Integer productCode;
	
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
	public void setProductCode(int value) {
		setProductCode(new Integer(value));
	}
	
	public void setProductCode(Integer value) {
		this.productCode = value;
	}
	
	public Integer getProductCode() {
		return productCode;
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
