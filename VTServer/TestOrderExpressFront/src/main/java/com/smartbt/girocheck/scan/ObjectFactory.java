
package com.smartbt.girocheck.scan;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.smartbt.girocheck.scan package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _OrderExpress_QNAME = new QName("http://scan.girocheck.smartbt.com/", "orderExpress");
    private final static QName _OrderExpressResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "orderExpressResponse");

    private final static QName _CheckAuth_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkAuth");
    private final static QName _CheckAuthResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkAuthResponse");
   
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckAuthSubmit }
     * 
     */
    
    
    public OrderExpress createOrderExpress() {
        return new OrderExpress();
    }
    
    public OrderExpressRequest createOrderExpressRequest() {
        return new OrderExpressRequest();
    }
    
    public OrderExpressResponse createOrderExpressResponse() {
        return new OrderExpressResponse();
    }
    
     public OrderExpressRes createOrderExpressRes() {
        return new OrderExpressRes();
    }
     
     
     
     /**
     * Create an instance of {@link CheckAuthResponse }
     * 
     */
    public CheckAuthResponse createCheckAuthResponse() {
        return new CheckAuthResponse();
    }
    
     public CheckAuth createCheckAuth() {
        return new CheckAuth();
    }
     
     public CheckAuthRequest createCheckAuthRequest() {
        return new CheckAuthRequest();
    }
     
      public CheckAuthRes createCheckAuthRes() {
        return new CheckAuthRes();
    }

    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderExpress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "orderExpress")
    public JAXBElement<OrderExpress> createOrderExpress(OrderExpress value) {
        return new JAXBElement<OrderExpress>(_OrderExpress_QNAME, OrderExpress.class, null, value);
    }

    
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "orderExpressResponse")
    public JAXBElement<OrderExpressResponse> createOrderExpressResponse(OrderExpressResponse value) {
        return new JAXBElement<OrderExpressResponse>(_OrderExpressResponse_QNAME, OrderExpressResponse.class, null, value);
    }
    
    
    
    
     @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkAuth")
    public JAXBElement<CheckAuth> createCheckAuth(CheckAuth value) {
        return new JAXBElement<CheckAuth>(_CheckAuth_QNAME, CheckAuth.class, null, value);
    }
   
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkAuthResponse")
    public JAXBElement<CheckAuthResponse> createCheckAuthResponse(CheckAuthResponse value) {
        return new JAXBElement<CheckAuthResponse>(_CheckAuthResponse_QNAME, CheckAuthResponse.class, null, value);
    }

  
}
