
package com.smartbt.vtsuite.boundary.ws;

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
//@WebServiceClient(name = "Scan", targetNamespace = "http://web.service.scanner.tc.com/", wsdlLocation = "https://stable-1.istreamdeposit.com/Scan?wsdl")
//@WebServiceClient(name = "Scan", targetNamespace = "http://web.service.scanner.tc.com/", wsdlLocation = "https://giro-1.istreamdeposit.com/Scan?wsdl")
@WebServiceClient(name = "Scan", targetNamespace = "http://web.service.scanner.tc.com/", wsdlLocation = "https://istreamdeposit.com/Scan?wsdl")
public class Scan_Service
    extends Service
{

    private final static URL SCAN_WSDL_LOCATION;
    private final static WebServiceException SCAN_EXCEPTION;
    private final static QName SCAN_QNAME = new QName("http://web.service.scanner.tc.com/", "Scan");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
//            url = new URL("https://stable-1.istreamdeposit.com/Scan?wsdl");
//            url = new URL("https://giro-1.istreamdeposit.com/Scan?wsdl");
//            url = new URL("https://istreamdeposit.com/Scan?wsdl");
            url = new URL("https://giro-1.istreamdeposit.com/Scan?WSDL");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            e = new WebServiceException(ex);
        }
        SCAN_WSDL_LOCATION = url;
        SCAN_EXCEPTION = e;
    }

    public Scan_Service() {
        super(__getWsdlLocation(), SCAN_QNAME);
    }

    public Scan_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns Scan
     */
    @WebEndpoint(name = "ScanPort")
    public Scan getScanPort() {
        return super.getPort(new QName("http://web.service.scanner.tc.com/", "ScanPort"), Scan.class);
    }

    private static URL __getWsdlLocation() {
        if (SCAN_EXCEPTION!= null) {
            throw SCAN_EXCEPTION;
        }
        return SCAN_WSDL_LOCATION;
    }

}
