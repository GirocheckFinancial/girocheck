package com.smartbt.vtsuite.boundary.prod;

import com.smartbt.vtsuite.boundary.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.8 Generated source
 * version: 2.0
 *
 */
@WebServiceClient(name = "PCAService", targetNamespace = "http://fis.certegy.pca.com/", wsdlLocation = "file:/C:/REPO/Dev/girocheck/VTServer/Certegy/Certegy-ejb/src/main/java/com/smartbt/vtsuite/boundary/prod/pcaservice.wsdl")
public class PCAService
        extends Service {

    private final static URL PCASERVICE_WSDL_LOCATION;
    private final static WebServiceException PCASERVICE_EXCEPTION;
    private final static QName PCASERVICE_QNAME = new QName("http://fis.certegy.pca.com/", "PCAService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/REPO/Dev/girocheck/VTServer/Certegy/Certegy-ejb/src/main/java/com/smartbt/vtsuite/boundary/prod/pcaservice.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PCASERVICE_WSDL_LOCATION = url;
        PCASERVICE_EXCEPTION = e;
    }

    public PCAService() {
        super(__getWsdlLocation(), PCASERVICE_QNAME);
    }

    public PCAService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     *
     * @return returns PCA
     */
    @WebEndpoint(name = "PCAPort")
    public PCA getPCAPort() {
        return super.getPort(new QName("http://fis.certegy.pca.com/", "PCAPort"), PCA.class);
    }

    private static URL __getWsdlLocation() {
        if (PCASERVICE_EXCEPTION != null) {
            throw PCASERVICE_EXCEPTION;
        }
        return PCASERVICE_WSDL_LOCATION;
    }
}
