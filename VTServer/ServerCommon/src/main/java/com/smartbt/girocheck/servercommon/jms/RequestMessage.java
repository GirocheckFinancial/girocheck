/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.girocheck.servercommon.jms;

import java.io.Serializable;
import java.util.Enumeration;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
public class RequestMessage  implements Serializable, ObjectMessage{
    private Serializable data;
    private String correlationID;
    

    public RequestMessage( String correlationId, Object data ) throws JMSException {
        Serializable s = (Serializable)data;
        setObject( s );
        setJMSCorrelationID( correlationId );
    }

   

    public void setObject( Serializable object ) throws JMSException {
       this.data = object;
    }

    public Serializable getObject() throws JMSException {
        return data;
    }

    public String getJMSMessageID() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSMessageID( String id ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public long getJMSTimestamp() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSTimestamp( long timestamp ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSCorrelationIDAsBytes( byte[] correlationID ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSCorrelationID( String correlationID ) throws JMSException {
       this.correlationID = correlationID;
    }

    public String getJMSCorrelationID() throws JMSException {
       return this.correlationID;
    }

    public Destination getJMSReplyTo() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSReplyTo( Destination replyTo ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public Destination getJMSDestination() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSDestination( Destination destination ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public int getJMSDeliveryMode() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSDeliveryMode( int deliveryMode ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getJMSRedelivered() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSRedelivered( boolean redelivered ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public String getJMSType() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSType( String type ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public long getJMSExpiration() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSExpiration( long expiration ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public long getJMSDeliveryTime() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSDeliveryTime( long deliveryTime ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public int getJMSPriority() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setJMSPriority( int priority ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void clearProperties() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean propertyExists( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getBooleanProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public byte getByteProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public short getShortProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIntProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public long getLongProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public float getFloatProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public double getDoubleProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public String getStringProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getObjectProperty( String name ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public Enumeration getPropertyNames() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBooleanProperty( String name, boolean value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setByteProperty( String name, byte value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setShortProperty( String name, short value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setIntProperty( String name, int value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLongProperty( String name, long value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setFloatProperty( String name, float value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDoubleProperty( String name, double value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setStringProperty( String name, String value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void setObjectProperty( String name, Object value ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void acknowledge() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void clearBody() throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public <T> T getBody( Class<T> c ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isBodyAssignableTo( Class c ) throws JMSException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

   
}
