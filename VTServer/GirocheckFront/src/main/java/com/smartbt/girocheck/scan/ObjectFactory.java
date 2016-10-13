
package com.smartbt.girocheck.scan;

import com.mchange.v2.log.NullMLogger;
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

   

    private final static QName _CheckAuth_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkAuth");
    private final static QName _ActivityReport_QNAME = new QName("http://scan.girocheck.smartbt.com/", "activityReport");
    private final static QName _CardReload_QNAME = new QName("http://scan.girocheck.smartbt.com/", "cardReload");
    private final static QName _CardReloadData_QNAME = new QName("http://scan.girocheck.smartbt.com/", "cardReloadData");

    private final static QName _CheckAuthLocationConfigResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkAuthLocationConfigResponse");

    private final static QName _CheckAuthSubmitResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkAuthSubmitResponse");
    private final static QName _CheckAuthLocationConfig_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkAuthLocationConfig");
    private final static QName _CheckAuthSubmit_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkAuthSubmit");
    private final static QName _CheckAuthResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "checkAuthResponse");
    private final static QName _ActivityReportResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "activityReportResponse");
    private final static QName _CardReloadResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "cardReloadResponse");
    private final static QName _CardReloadDataResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "cardReloadDataResponse");
    
    /**
     * Adding Tecnicard methods...
     */
    private final static QName _BalanceInquiryResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "BalanceInquiryResponse");
    private final static QName _CardToBankResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "CardToBankResponse");
    private final static QName _CardToBankConfirmationResponse_QNAME = new QName("http://scan.girocheck.smartbt.com/", "CardToBankConfirmationResponse");
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
    
    

    
    public CheckAuthSubmit createCheckAuthSubmit() {
        return new CheckAuthSubmit();
    }

    /**
     * Create an instance of {@link CheckAuthResponse }
     * 
     */
    public CheckAuthResponse createCheckAuthResponse() {
        return new CheckAuthResponse();
    }

    /**
     * Create an instance of {@link ActivityReportResponse }
     * 
     */
    public ActivityReportResponse createActivityReportResponse() {
        return new ActivityReportResponse();
    }
    /**
     * Create an instance of {@link CheckAuthResponse }
     * 
     */
    public CardReloadResponse createCardReloadResponse() {
        return new CardReloadResponse();
    }
    /**
     * Create an instance of {@link CheckAuthResponse }
     * 
     */
    public CardReloadDataResponse createCardReloadDataResponse() {
        return new CardReloadDataResponse();
    }

    /**
     * Create an instance of {@link CheckAuthLocationConfig }
     * 
     */
    public CheckAuthLocationConfig createCheckAuthLocationConfig() {
        return new CheckAuthLocationConfig();
    }

    /**
     * Create an instance of {@link CheckAuthSubmitResponse }
     * 
     */
    public CheckAuthSubmitResponse createCheckAuthSubmitResponse() {
        return new CheckAuthSubmitResponse();
    }

   
    /**
     * Create an instance of {@link CheckAuthLocationConfigResponse }
     * 
     */
    public CheckAuthLocationConfigResponse createCheckAuthLocationConfigResponse() {
        return new CheckAuthLocationConfigResponse();
    }

    
    /**
     * Create an instance of {@link CheckAuth }
     * 
     */
    public CheckAuth createCheckAuth() {
        return new CheckAuth();
    }
    
    /**
     * Create an instance of {@link CheckAuth }
     * 
     */
    public ActivityReport createActivityReport() {
        return new ActivityReport();
    }
    /**
     * Create an instance of {@link CheckAuth }
     * 
     */
    public CardReload createCardReload() {
        return new CardReload();
    }
    /**
     * Create an instance of {@link CardReloadData }
     * 
     */
    public CardReloadData createCardReloadData() {
        return new CardReloadData();
    }

    
   

   
    /**
     * Create an instance of {@link CheckAuthRequest }
     * 
     */
    public CheckAuthRequest createCheckAuthRequest() {
        return new CheckAuthRequest();
    }
    /**
     * Create an instance of {@link CheckAuthRequest }
     * 
     */
    public ActivityReportRequest createActivityReportRequest() {
        return new ActivityReportRequest();
    }
    /**
     * Create an instance of {@link CheckAuthRequest }
     * 
     */
    public CardReloadRequest createCardReloadRequest() {
        return new CardReloadRequest();
    }
    /**
     * Create an instance of {@link CheckAuthRequest }
     * 
     */
    public CardReloadDataRequest createCardReloadDataRequest() {
        return new CardReloadDataRequest();
    }


    /**
     * Create an instance of {@link CheckAuthSubmitRequest }
     * 
     */
    public CheckAuthSubmitRequest createCheckAuthSubmitRequest() {
        return new CheckAuthSubmitRequest();
    }

    /**
     * Create an instance of {@link CheckAuthRes }
     * 
     */
    public CheckAuthRes createCheckAuthRes() {
        return new CheckAuthRes();
    }
    /**
     * Create an instance of {@link CheckAuthRes }
     * 
     */
    public ActivityReportRes createActivityReportRes() {
        return new ActivityReportRes();
    }
    /**
     * Create an instance of {@link CheckAuthRes }
     * 
     */
    public CardReloadRes createCardReloadRes() {
        return new CardReloadRes();
    }
    /**
     * Create an instance of {@link CheckAuthRes }
     * 
     */
    public CardReloadDataRes createCardReloadDataRes() {
        return new CardReloadDataRes();
    }

    /**
     * Create an instance of {@link CheckAuthLocationConfigRes }
     * 
     */
    public CheckAuthLocationConfigRes createCheckAuthLocationConfigRes() {
        return new CheckAuthLocationConfigRes();
    }

  

    /**
     * Create an instance of {@link CheckAuthLocationConfigRequest }
     * 
     */
    public CheckAuthLocationConfigRequest createCheckAuthLocationConfigRequest() {
        return new CheckAuthLocationConfigRequest();
    }
    
    /**
     * Create an instance of {@link BalanceInquiryRes }
     * 
     */
    public BalanceInquiryRes createBalanceInquiryResponse(){
        return new BalanceInquiryRes();
    }
    
    /**
     * Create an instance of {@link CardToBankRes}
     * 
     */
    public CardToBankRes createCardToBankResponse(){
        return new CardToBankRes();
    }
    
        public CardToBankConfirmationRes createCardToBankConfirmationResponse(){
        return new CardToBankConfirmationRes();
    }

    
   
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuth }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkAuth")
    public JAXBElement<CheckAuth> createCheckAuth(CheckAuth value) {
        return new JAXBElement<CheckAuth>(_CheckAuth_QNAME, CheckAuth.class, null, value);
    }
   
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link activityReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "activityReport")
    public JAXBElement<ActivityReport> createActivityReport(ActivityReport value) {
        return new JAXBElement<ActivityReport>(_ActivityReport_QNAME, ActivityReport.class, null, value);
    }
   
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CardReload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "cardReload")
    public JAXBElement<CardReload> createCardReload(CardReload value) {
        return new JAXBElement<CardReload>(_CardReload_QNAME, CardReload.class, null, value);
    }
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CardReloadData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "cardReloadData")
    public JAXBElement<CardReloadData> createCardReloadData(CardReloadData value) {
        return new JAXBElement<CardReloadData>(_CardReloadData_QNAME, CardReloadData.class, null, value);
    }

    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthLocationConfigResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkAuthLocationConfigResponse")
    public JAXBElement<CheckAuthLocationConfigResponse> createCheckAuthLocationConfigResponse(CheckAuthLocationConfigResponse value) {
        return new JAXBElement<CheckAuthLocationConfigResponse>(_CheckAuthLocationConfigResponse_QNAME, CheckAuthLocationConfigResponse.class, null, value);
    }

    

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthSubmitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkAuthSubmitResponse")
    public JAXBElement<CheckAuthSubmitResponse> createCheckAuthSubmitResponse(CheckAuthSubmitResponse value) {
        return new JAXBElement<CheckAuthSubmitResponse>(_CheckAuthSubmitResponse_QNAME, CheckAuthSubmitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthLocationConfig }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkAuthLocationConfig")
    public JAXBElement<CheckAuthLocationConfig> createCheckAuthLocationConfig(CheckAuthLocationConfig value) {
        return new JAXBElement<CheckAuthLocationConfig>(_CheckAuthLocationConfig_QNAME, CheckAuthLocationConfig.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthSubmit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkAuthSubmit")
    public JAXBElement<CheckAuthSubmit> createCheckAuthSubmit(CheckAuthSubmit value) {
        return new JAXBElement<CheckAuthSubmit>(_CheckAuthSubmit_QNAME, CheckAuthSubmit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "checkAuthResponse")
    public JAXBElement<CheckAuthResponse> createCheckAuthResponse(CheckAuthResponse value) {
        return new JAXBElement<CheckAuthResponse>(_CheckAuthResponse_QNAME, CheckAuthResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "activityReportResponse")
    public JAXBElement<ActivityReportResponse> createActivityReportResponse(ActivityReportResponse value) {
        return new JAXBElement<ActivityReportResponse>(_ActivityReportResponse_QNAME, ActivityReportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CardReloadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "cardReloadResponse")
    public JAXBElement<CardReloadResponse> createCardReloadResponse(CardReloadResponse value) {
        return new JAXBElement<CardReloadResponse>(_CardReloadResponse_QNAME, CardReloadResponse.class, null, value);
    }
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CardReloadDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "cardReloadDataResponse")
    public JAXBElement<CardReloadDataResponse> createCardReloadDataResponse(CardReloadDataResponse value) {
        return new JAXBElement<CardReloadDataResponse>(_CardReloadDataResponse_QNAME, CardReloadDataResponse.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "BalanceInquiryResponse")
    public JAXBElement<BalanceInquiryRes> createBalanceInquiryResponse(BalanceInquiryRes value) {
        return new JAXBElement<BalanceInquiryRes>(_BalanceInquiryResponse_QNAME, BalanceInquiryRes.class,null,value);
    }
    
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "CardToBankResponse")
    public JAXBElement<CardToBankRes> createCardToBankResponse(CardToBankRes value) {
        return new JAXBElement<CardToBankRes>(_CardToBankResponse_QNAME, CardToBankRes.class,null,value);
    }
    
    @XmlElementDecl(namespace = "http://scan.girocheck.smartbt.com/", name = "CardToBankConfirmationResponse")
    public JAXBElement<CardToBankConfirmationRes> createCardToBankConfirmationResponse(CardToBankConfirmationRes value) {
        return new JAXBElement<CardToBankConfirmationRes>(_CardToBankConfirmationResponse_QNAME, CardToBankConfirmationRes.class,null,value);
    }
}
