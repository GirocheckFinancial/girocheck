/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.utils;

import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADA;
import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADAD;
import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADAL;
import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADARP;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDA;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDAD;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDAL;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDARP;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
public class OEUtils {

    /**
     * Simple Util method to enclose a string in single quotes.
     * 
     * @param string
     * @return 
     */
    public static String singleQuote( String string ) {

        return "'" + string + "'";

    }

    
    /**
     * Takes the entry object as a parameter and returns a Base64Encoded String
     * 
     * @param entry
     * @return
     * @throws JAXBException 
     */
    public static String processRequest( LOTEENTRADA entry ) throws JAXBException {
        JAXBContext xmlEntry = JAXBContext.newInstance( entry.getClass() );
      
        String encoded = marshallToBase64( xmlEntry, entry );
        return encoded;
    }
    public static String processRequestD( LOTEENTRADAD entry ) throws JAXBException {
        JAXBContext xmlEntry = JAXBContext.newInstance( entry.getClass() );
      
        String encoded = marshallToBase64( xmlEntry, entry );
        return encoded;
    }
    public static String processRequestRP( LOTEENTRADARP entry ) throws JAXBException {
        JAXBContext xmlEntry = JAXBContext.newInstance( entry.getClass() );
      
        String encoded = marshallToBase64( xmlEntry, entry );
        return encoded;
    }
    public static String processRequestL( LOTEENTRADAL entry ) throws JAXBException {
        JAXBContext xmlEntry = JAXBContext.newInstance( entry.getClass() );
      
        String encoded = marshallToBase64( xmlEntry, entry );
        return encoded;
    }
    
    /**
     * Utility method to convert an object into it's XML representation.
     * 
     * @param ctx
     * @param object
     * @return
     * @throws JAXBException 
     */
    public static String genericMarshaller( JAXBContext ctx, Object object ) throws JAXBException {
        StringWriter writer = new StringWriter();
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_ENCODING, "us-ascii" );
        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        marshaller.marshal( object, writer );
        return writer.toString();
    }

    /**
     * Converts an XMLObject into a base64Encoded string.
     * 
     * @param ctx
     * @param object
     * @return
     * @throws JAXBException 
     */
    public static String marshallToBase64( JAXBContext ctx, Object object ) throws JAXBException {
        StringWriter writer = new StringWriter();
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_ENCODING, "us-ascii" );
        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        marshaller.marshal( object, writer );
        return Base64.encodeBase64String( writer.toString().getBytes() );
    }

    /**
     * Decodes a base64String into a string and removes all 'Control' Characters
     * 
     * @param string
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String decodeBase64String( String string ) throws UnsupportedEncodingException {

        byte[] decoded = Base64.decodeBase64( string );
        String decodedString = new String( decoded );
        return decodedString.replaceAll( "\\p{Cntrl}", "" );
    }
    
    /**
     * Returns an instance of the LOTESALIDA object.
     * 
     * @param ctx
     * @param string
     * @return
     * @throws JAXBException 
     */
    public static LOTESALIDA unMarshallResponse( JAXBContext ctx, String string ) throws JAXBException {
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        LOTESALIDA lotesalida = (LOTESALIDA) unmarshaller.unmarshal( new StreamSource( new StringReader( string ) ) );
        return lotesalida;
    }
    public static LOTESALIDAD unMarshallResponseD( JAXBContext ctx, String string ) throws JAXBException {
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        LOTESALIDAD lotesalida = (LOTESALIDAD) unmarshaller.unmarshal( new StreamSource( new StringReader( string ) ) );
        return lotesalida;
    }
    public static LOTESALIDARP unMarshallResponseRP( JAXBContext ctx, String string ) throws JAXBException {
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        LOTESALIDARP lotesalida = (LOTESALIDARP) unmarshaller.unmarshal( new StreamSource( new StringReader( string ) ) );
        return lotesalida;
    }
    public static LOTESALIDAL unMarshallResponseL( JAXBContext ctx, String string ) throws JAXBException {
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        LOTESALIDAL lotesalida = (LOTESALIDAL) unmarshaller.unmarshal( new StreamSource( new StringReader( string ) ) );
        return lotesalida;
    }
    
}
