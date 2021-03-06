
package com.smartbt.vtsuite.boundary.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
//@WebServiceClient(name = "iStreamSrvHostWS", targetNamespace = "https://SistemasGalileo.com/Services/", wsdlLocation = "http://74.255.192.104/Galileo.TCMS.ExtSRV.Web/iStreamSrvHost.asmx?WSDL")
@WebServiceClient(name = "iStreamSrvHostWS", targetNamespace = "https://SistemasGalileo.com/Services/", wsdlLocation = "https://bizsrv.tcmsystem.net/IStreamWS/iStreamSrvHost.asmx?WSDL")
public class IStreamSrvHostWS
    extends Service
{

    private final static URL ISTREAMSRVHOSTWS_WSDL_LOCATION;
    private final static WebServiceException ISTREAMSRVHOSTWS_EXCEPTION;
    private final static QName ISTREAMSRVHOSTWS_QNAME = new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
//            url = new URL("http://74.255.192.104/Galileo.TCMS.ExtSRV.Web/iStreamSrvHost.asmx?WSDL");
            url = new URL("https://bizsrv.tcmsystem.net/IStreamWS/iStreamSrvHost.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ISTREAMSRVHOSTWS_WSDL_LOCATION = url;
        ISTREAMSRVHOSTWS_EXCEPTION = e;
    }

    public IStreamSrvHostWS() {
        super(__getWsdlLocation(), ISTREAMSRVHOSTWS_QNAME);
    }

    public IStreamSrvHostWS(WebServiceFeature... features) {
        super(__getWsdlLocation(), ISTREAMSRVHOSTWS_QNAME, features);
    }

    public IStreamSrvHostWS(URL wsdlLocation) {
        super(wsdlLocation, ISTREAMSRVHOSTWS_QNAME);
    }

    public IStreamSrvHostWS(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ISTREAMSRVHOSTWS_QNAME, features);
    }

    public IStreamSrvHostWS(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IStreamSrvHostWS(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IStreamSrvHostWSSoap
     */
    @WebEndpoint(name = "iStreamSrvHostWSSoap")
    public IStreamSrvHostWSSoap getIStreamSrvHostWSSoap() {
        return super.getPort(new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWSSoap"), IStreamSrvHostWSSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IStreamSrvHostWSSoap
     */
    @WebEndpoint(name = "iStreamSrvHostWSSoap")
    public IStreamSrvHostWSSoap getIStreamSrvHostWSSoap(WebServiceFeature... features) {
        return super.getPort(new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWSSoap"), IStreamSrvHostWSSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns IStreamSrvHostWSSoap
     */
    @WebEndpoint(name = "iStreamSrvHostWSSoap12")
    public IStreamSrvHostWSSoap getIStreamSrvHostWSSoap12() {
        return super.getPort(new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWSSoap12"), IStreamSrvHostWSSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IStreamSrvHostWSSoap
     */
    @WebEndpoint(name = "iStreamSrvHostWSSoap12")
    public IStreamSrvHostWSSoap getIStreamSrvHostWSSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWSSoap12"), IStreamSrvHostWSSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns IStreamSrvHostWSHttpGet
     */
    @WebEndpoint(name = "iStreamSrvHostWSHttpGet")
    public IStreamSrvHostWSHttpGet getIStreamSrvHostWSHttpGet() {
        return super.getPort(new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWSHttpGet"), IStreamSrvHostWSHttpGet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IStreamSrvHostWSHttpGet
     */
    @WebEndpoint(name = "iStreamSrvHostWSHttpGet")
    public IStreamSrvHostWSHttpGet getIStreamSrvHostWSHttpGet(WebServiceFeature... features) {
        return super.getPort(new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWSHttpGet"), IStreamSrvHostWSHttpGet.class, features);
    }

    /**
     * 
     * @return
     *     returns IStreamSrvHostWSHttpPost
     */
    @WebEndpoint(name = "iStreamSrvHostWSHttpPost")
    public IStreamSrvHostWSHttpPost getIStreamSrvHostWSHttpPost() {
        return super.getPort(new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWSHttpPost"), IStreamSrvHostWSHttpPost.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IStreamSrvHostWSHttpPost
     */
    @WebEndpoint(name = "iStreamSrvHostWSHttpPost")
    public IStreamSrvHostWSHttpPost getIStreamSrvHostWSHttpPost(WebServiceFeature... features) {
        return super.getPort(new QName("https://SistemasGalileo.com/Services/", "iStreamSrvHostWSHttpPost"), IStreamSrvHostWSHttpPost.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ISTREAMSRVHOSTWS_EXCEPTION!= null) {
            throw ISTREAMSRVHOSTWS_EXCEPTION;
        }
        return ISTREAMSRVHOSTWS_WSDL_LOCATION;
    }

}
