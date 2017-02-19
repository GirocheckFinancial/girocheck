
package com.smartbt.vtsuite.boundary;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.smartbt.vtsuite.boundary package. 
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

    private final static QName _EchoRequest_QNAME = new QName("http://fis.certegy.pca.com/", "EchoRequest");
    private final static QName _ReverseResponse_QNAME = new QName("http://fis.certegy.pca.com/", "ReverseResponse");
    private final static QName _ReverseRequest_QNAME = new QName("http://fis.certegy.pca.com/", "ReverseRequest");
    private final static QName _AuthorizeRequest_QNAME = new QName("http://fis.certegy.pca.com/", "AuthorizeRequest");
    private final static QName _EchoResponse_QNAME = new QName("http://fis.certegy.pca.com/", "EchoResponse");
    private final static QName _AuthorizeResponse_QNAME = new QName("http://fis.certegy.pca.com/", "AuthorizeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.smartbt.vtsuite.boundary
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PCAEchoResponse }
     * 
     */
    public PCAEchoResponse createPCAEchoResponse() {
        return new PCAEchoResponse();
    }

    /**
     * Create an instance of {@link PCAResponse }
     * 
     */
    public PCAResponse createPCAResponse() {
        return new PCAResponse();
    }

    /**
     * Create an instance of {@link PCAReverseRequest }
     * 
     */
    public PCAReverseRequest createPCAReverseRequest() {
        return new PCAReverseRequest();
    }

    /**
     * Create an instance of {@link PCARequest }
     * 
     */
    public PCARequest createPCARequest() {
        return new PCARequest();
    }

    /**
     * Create an instance of {@link PCAEchoRequest }
     * 
     */
    public PCAEchoRequest createPCAEchoRequest() {
        return new PCAEchoRequest();
    }

    /**
     * Create an instance of {@link PCAReverseResponse }
     * 
     */
    public PCAReverseResponse createPCAReverseResponse() {
        return new PCAReverseResponse();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link Device }
     * 
     */
    public Device createDevice() {
        return new Device();
    }

    /**
     * Create an instance of {@link Check }
     * 
     */
    public Check createCheck() {
        return new Check();
    }

    /**
     * Create an instance of {@link Custom }
     * 
     */
    public Custom createCustom() {
        return new Custom();
    }

    /**
     * Create an instance of {@link MICR }
     * 
     */
    public MICR createMICR() {
        return new MICR();
    }

    /**
     * Create an instance of {@link DOB }
     * 
     */
    public DOB createDOB() {
        return new DOB();
    }

    /**
     * Create an instance of {@link Enrollment }
     * 
     */
    public Enrollment createEnrollment() {
        return new Enrollment();
    }

    /**
     * Create an instance of {@link ID }
     * 
     */
    public ID createID() {
        return new ID();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PCAEchoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fis.certegy.pca.com/", name = "EchoRequest")
    public JAXBElement<PCAEchoRequest> createEchoRequest(PCAEchoRequest value) {
        return new JAXBElement<PCAEchoRequest>(_EchoRequest_QNAME, PCAEchoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PCAReverseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fis.certegy.pca.com/", name = "ReverseResponse")
    public JAXBElement<PCAReverseResponse> createReverseResponse(PCAReverseResponse value) {
        return new JAXBElement<PCAReverseResponse>(_ReverseResponse_QNAME, PCAReverseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PCAReverseRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fis.certegy.pca.com/", name = "ReverseRequest")
    public JAXBElement<PCAReverseRequest> createReverseRequest(PCAReverseRequest value) {
        return new JAXBElement<PCAReverseRequest>(_ReverseRequest_QNAME, PCAReverseRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PCARequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fis.certegy.pca.com/", name = "AuthorizeRequest")
    public JAXBElement<PCARequest> createAuthorizeRequest(PCARequest value) {
        return new JAXBElement<PCARequest>(_AuthorizeRequest_QNAME, PCARequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PCAEchoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fis.certegy.pca.com/", name = "EchoResponse")
    public JAXBElement<PCAEchoResponse> createEchoResponse(PCAEchoResponse value) {
        return new JAXBElement<PCAEchoResponse>(_EchoResponse_QNAME, PCAEchoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PCAResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fis.certegy.pca.com/", name = "AuthorizeResponse")
    public JAXBElement<PCAResponse> createAuthorizeResponse(PCAResponse value) {
        return new JAXBElement<PCAResponse>(_AuthorizeResponse_QNAME, PCAResponse.class, null, value);
    }

}
