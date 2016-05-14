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
@XmlRootElement
public class JMSLog implements Serializable {
	public JMSLog() {
	}
	
	private int id;
	
	private String correlationId;
	
	private String cause;
	
	private String messageId;
	
	private String description;
	
	private java.util.Date arrivedAt;
	
	private String destination;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}		
	
	public void setCorrelationId(String value) {
		this.correlationId = value;
	}
	
	public String getCorrelationId() {
		return correlationId;
	}
	
	public void setCause(String value) {
		this.cause = value;
	}
	
	public String getCause() {
		return cause;
	}
	
	public void setMessageId(String value) {
		this.messageId = value;
	}
	
	public String getMessageId() {
		return messageId;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setArrivedAt(java.util.Date value) {
		this.arrivedAt = value;
	}
	
	public java.util.Date getArrivedAt() {
		return arrivedAt;
	}
	
	public void setDestination(String value) {
		this.destination = value;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
