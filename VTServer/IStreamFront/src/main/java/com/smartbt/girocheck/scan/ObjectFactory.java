
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

    private final static QName _PersonalInfo_QNAME = new QName("http://scan.girocheck.smartbt.com/", "personalInfo");
    private final static QName _PersonalInfoResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "personalInfoResponse");
    private final static QName _PersonalInfoRequest_QNAME = new QName("http://scan.girocheck.smartbt.com/", "personalInfoRequest");

    private final static QName _CheckInfo_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkInfo");
    private final static QName _CheckInfoResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkInfoResponse");
    private final static QName _CheckInfoRequest_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkInfoRequest");

    private final static QName _CertegyInfo_QNAME = new QName("http://scan.girocheck.smartbt.com/", "certegyInfo");
    private final static QName _CertegyInfoResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "certegyInfoResponse");
    private final static QName _CertegyInfoRequest_QNAME = new QName("http://scan.girocheck.smartbt.com/", "certegyInfoRequest");


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.smartbt.girocheck.scan
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckAuthSubmit }
     * 
     */
    
   //PersonalInfo
    public PersonalInfo createPersonalInfo() {
        return new PersonalInfo();
    }
    
    public PersonalInfoRequest createPersonalInfoRequest() {
        return new PersonalInfoRequest();
    }
    
    public PersonalInfoResponse createPersonalInfoResponse() {
        return new PersonalInfoResponse();
    }
    
     public PersonalInfoRes createPersonalInfoRes() {
        return new PersonalInfoRes();
    }
     
     
     
    //Check Info 
    public CheckInfo createCheckInfo() {
        return new CheckInfo();
    }
    
    public CheckInfoRequest createCheckInfoRequest() {
        return new CheckInfoRequest();
    }
    
    public CheckInfoResponse createCheckInfoResponse() {
        return new CheckInfoResponse();
    }
    
     public CheckInfoRes createCheckInfoRes() {
        return new CheckInfoRes();
    }
    
     
    
    //--Certegy
    public CertegyInfo createCertegyInfo() {
        return new CertegyInfo();
    }
    
    public CertegyInfoRequest createCertegyInfoRequest() {
        return new CertegyInfoRequest();
    }
    
    public CertegyInfoResponse createCertegyInfoResponse() {
        return new CertegyInfoResponse();
    }
    
     public CertegyInfoRes createCertegyInfoRes() {
        return new CertegyInfoRes();
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonalInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "personalInfo")
    public JAXBElement<PersonalInfo> createPersonalInfo(PersonalInfo value) {
        return new JAXBElement<PersonalInfo>(_PersonalInfo_QNAME, PersonalInfo.class, null, value);
    }

    
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "personalInfoResponse")
    public JAXBElement<PersonalInfoResponse> createPersonalInfoResponse(PersonalInfoResponse value) {
        return new JAXBElement<PersonalInfoResponse>(_PersonalInfoResponse_QNAME, PersonalInfoResponse.class, null, value);
    }
    
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "personalInfoRequest")
    public JAXBElement<PersonalInfoRequest> createPersonalInfoRequest(PersonalInfoRequest value) {
        return new JAXBElement<PersonalInfoRequest>(_PersonalInfoRequest_QNAME, PersonalInfoRequest.class, null, value);
    }
    
    
    
    
    
     /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonalInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkInfo")
    public JAXBElement<CheckInfo> createCheckInfo(CheckInfo value) {
        return new JAXBElement<CheckInfo>(_CheckInfo_QNAME, CheckInfo.class, null, value);
    } 
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkInfoResponse")
    public JAXBElement<CheckInfoResponse> createCheckInfoResponse(CheckInfoResponse value) {
        return new JAXBElement<CheckInfoResponse>(_CheckInfoResponse_QNAME, CheckInfoResponse.class, null, value);
    } 
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkInfoRequest")
    public JAXBElement<CheckInfoRequest> createCheckInfoRequest(CheckInfoRequest value) {
        return new JAXBElement<CheckInfoRequest>(_CheckInfoRequest_QNAME, CheckInfoRequest.class, null, value);
    }
    
    
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonalInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "certegyInfo")
    public JAXBElement<CertegyInfo> createCertegyInfo(CertegyInfo value) {
        return new JAXBElement<CertegyInfo>(_PersonalInfo_QNAME, CertegyInfo.class, null, value);
    }

    
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "certegyInfoResponse")
    public JAXBElement<CertegyInfoResponse> createCertegyInfoResponse(CertegyInfoResponse value) {
        return new JAXBElement<CertegyInfoResponse>(_CertegyInfoResponse_QNAME, CertegyInfoResponse.class, null, value);
    }
    
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "certegyInfoRequest")
    public JAXBElement<CertegyInfoRequest> createCertegyInfoRequest(CertegyInfoRequest value) {
        return new JAXBElement<CertegyInfoRequest>(_CertegyInfoRequest_QNAME, CertegyInfoRequest.class, null, value);
    }
    
  
}
