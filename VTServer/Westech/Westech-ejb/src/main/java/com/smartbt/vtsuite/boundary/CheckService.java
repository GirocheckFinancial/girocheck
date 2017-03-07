
package com.smartbt.vtsuite.boundary;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "CheckService", targetNamespace = "http://10.10.11.152/eFraudAPI", wsdlLocation = "http://10.10.11.152/eFraudAPI/CheckService.asmx?WSDL")
public class CheckService
    extends Service
{

    private final static URL CHECKSERVICE_WSDL_LOCATION;
    private final static WebServiceException CHECKSERVICE_EXCEPTION;
    private final static QName CHECKSERVICE_QNAME = new QName("http://10.10.11.152/eFraudAPI", "CheckService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.10.11.152/eFraudAPI/CheckService.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CHECKSERVICE_WSDL_LOCATION = url;
        CHECKSERVICE_EXCEPTION = e;
    }

    public CheckService() {
        super(__getWsdlLocation(), CHECKSERVICE_QNAME);
    }

    public CheckService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns CheckServiceSoap
     */
    @WebEndpoint(name = "CheckServiceSoap")
    public CheckServiceSoap getCheckServiceSoap() {
        return super.getPort(new QName("http://10.10.11.152/eFraudAPI", "CheckServiceSoap"), CheckServiceSoap.class);
    }

    /**
     * 
     * @return
     *     returns CheckServiceSoap
     */
    @WebEndpoint(name = "CheckServiceSoap12")
    public CheckServiceSoap getCheckServiceSoap12() {
        return super.getPort(new QName("http://10.10.11.152/eFraudAPI", "CheckServiceSoap12"), CheckServiceSoap.class);
    }

    private static URL __getWsdlLocation() {
        if (CHECKSERVICE_EXCEPTION!= null) {
            throw CHECKSERVICE_EXCEPTION;
        }
        return CHECKSERVICE_WSDL_LOCATION;
    }

}
