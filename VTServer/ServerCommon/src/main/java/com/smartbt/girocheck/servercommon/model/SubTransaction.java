/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 *
 * This is an automatic generated file. It will be regenerated every time you
 * generate persistence class.
 *
 * Modifying its content may cause the program not work, or your work may lost.
 */
/**
 * Licensee: License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;

public class SubTransaction implements Serializable, Comparable<SubTransaction> {

    public SubTransaction() {
    }

    private int id;

    private com.smartbt.girocheck.servercommon.model.Transaction transaction;

    private Integer type;

    private Integer resultCode;

    private String resultMessage;

    private String errorCode;

    private Integer host;

    private Integer order;

    private void setId( int value ) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setType( int value ) {
        setType( new Integer( value ) );
    }

    public void setType( Integer value ) {
        this.type = value;
    }

    public Integer getType() {
        return type;
    }

    public void setResultCode( int value ) {
        setResultCode( new Integer( value ) );
    }

    public void setResultCode( Integer value ) {
        this.resultCode = value;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultMessage( String value ) {
        if(value != null && value.length() > 254)value = value.substring( 0, 254 );
        this.resultMessage = value;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setErrorCode( String value ) {
        this.errorCode = value;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setHost( int value ) {
        setHost( new Integer( value ) );
    }

    public void setHost( Integer value ) {
        this.host = value;
    }

    public Integer getHost() {
        return host;
    }

    public void setOrder( int value ) {
        setOrder( new Integer( value ) );
    }

    public void setOrder( Integer value ) {
        this.order = value;
    }

    public Integer getOrder() {
        return order;
    }

    public void setTransaction( com.smartbt.girocheck.servercommon.model.Transaction value ) {
        this.transaction = value;
    }

    public com.smartbt.girocheck.servercommon.model.Transaction getTransaction() {
        return transaction;
    }

    public String toString() {
        return String.valueOf( getId() );
    }

    public int compareTo(SubTransaction o) {
        return order.compareTo(o.getOrder());
    }

}
