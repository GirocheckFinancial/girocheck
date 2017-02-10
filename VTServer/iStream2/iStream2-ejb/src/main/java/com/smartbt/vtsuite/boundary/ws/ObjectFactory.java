
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.smartbt.vtsuite.boundary.ws package. 
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

    private final static QName _WSServiceException_QNAME = new QName("http://web.service.fileloader.tc.com/", "WSServiceException");
    private final static QName _SendSingleICLResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendSingleICLResponse");
    private final static QName _SendACH_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendACH");
    private final static QName _GetCheckImageResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "getCheckImageResponse");
    private final static QName _GetCheckImage_QNAME = new QName("http://web.service.fileloader.tc.com/", "getCheckImage");
    private final static QName _SendSingleICL_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendSingleICL");
    private final static QName _IneligibleICLResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "ineligibleICLResponse");
    private final static QName _SendDetailedACHResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendDetailedACHResponse");
    private final static QName _SendACHResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendACHResponse");
    private final static QName _SendDetailedACHCustomerResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendDetailedACHCustomerResponse");
    private final static QName _SendICL_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendICL");
    private final static QName _SendDetailedACHCustomer_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendDetailedACHCustomer");
    private final static QName _SendACHCustomer_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendACHCustomer");
    private final static QName _CancelACHResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "cancelACHResponse");
    private final static QName _Resubmit_QNAME = new QName("http://web.service.fileloader.tc.com/", "resubmit");
    private final static QName _RegisterCustomer_QNAME = new QName("http://web.service.fileloader.tc.com/", "registerCustomer");
    private final static QName _LookupBankResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "lookupBankResponse");
    private final static QName _LookupBank_QNAME = new QName("http://web.service.fileloader.tc.com/", "lookupBank");
    private final static QName _RefundResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "refundResponse");
    private final static QName _CancelACH_QNAME = new QName("http://web.service.fileloader.tc.com/", "cancelACH");
    private final static QName _IneligibleICL_QNAME = new QName("http://web.service.fileloader.tc.com/", "ineligibleICL");
    private final static QName _ServiceException_QNAME = new QName("http://web.service.fileloader.tc.com/", "ServiceException");
    private final static QName _ResubmitResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "resubmitResponse");
    private final static QName _SendICLResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendICLResponse");
    private final static QName _RegisterCustomerResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "registerCustomerResponse");
    private final static QName _SendACHCustomerResponse_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendACHCustomerResponse");
    private final static QName _Refund_QNAME = new QName("http://web.service.fileloader.tc.com/", "refund");
    private final static QName _SendDetailedACH_QNAME = new QName("http://web.service.fileloader.tc.com/", "sendDetailedACH");
    private final static QName _SendDetailedACHCustomerCompanyDescription_QNAME = new QName("", "companyDescription");
    private final static QName _SendDetailedACHCustomerCheckSerialNumber_QNAME = new QName("", "checkSerialNumber");
    private final static QName _SendDetailedACHCustomerDiscretionaryData_QNAME = new QName("", "discretionaryData");
    private final static QName _SendDetailedACHCustomerTraceNumber_QNAME = new QName("", "traceNumber");
    private final static QName _SendDetailedACHCustomerDepositName_QNAME = new QName("", "depositName");
    private final static QName _SendDetailedACHCustomerTerminalCity_QNAME = new QName("", "terminalCity");
    private final static QName _SendDetailedACHCustomerAddenda_QNAME = new QName("", "addenda");
    private final static QName _SendDetailedACHCustomerEffectiveDate_QNAME = new QName("", "effectiveDate");
    private final static QName _SendDetailedACHCustomerTerminalState_QNAME = new QName("", "terminalState");
    private final static QName _IneligibleICLNote_QNAME = new QName("", "note");
    private final static QName _SendDetailedACHEntryClassCode_QNAME = new QName("", "entryClassCode");
    private final static QName _SendSingleICLHighQualityImage_QNAME = new QName("", "highQualityImage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.smartbt.vtsuite.boundary.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LookupBank }
     * 
     */
    public LookupBank createLookupBank() {
        return new LookupBank();
    }

    /**
     * Create an instance of {@link RefundResponse }
     * 
     */
    public RefundResponse createRefundResponse() {
        return new RefundResponse();
    }

    /**
     * Create an instance of {@link RegisterCustomer }
     * 
     */
    public RegisterCustomer createRegisterCustomer() {
        return new RegisterCustomer();
    }

    /**
     * Create an instance of {@link Resubmit }
     * 
     */
    public Resubmit createResubmit() {
        return new Resubmit();
    }

    /**
     * Create an instance of {@link LookupBankResponse }
     * 
     */
    public LookupBankResponse createLookupBankResponse() {
        return new LookupBankResponse();
    }

    /**
     * Create an instance of {@link SendICLResponse }
     * 
     */
    public SendICLResponse createSendICLResponse() {
        return new SendICLResponse();
    }

    /**
     * Create an instance of {@link RegisterCustomerResponse }
     * 
     */
    public RegisterCustomerResponse createRegisterCustomerResponse() {
        return new RegisterCustomerResponse();
    }

    /**
     * Create an instance of {@link SendDetailedACH }
     * 
     */
    public SendDetailedACH createSendDetailedACH() {
        return new SendDetailedACH();
    }

    /**
     * Create an instance of {@link Refund }
     * 
     */
    public Refund createRefund() {
        return new Refund();
    }

    /**
     * Create an instance of {@link SendACHCustomerResponse }
     * 
     */
    public SendACHCustomerResponse createSendACHCustomerResponse() {
        return new SendACHCustomerResponse();
    }

    /**
     * Create an instance of {@link IneligibleICL }
     * 
     */
    public IneligibleICL createIneligibleICL() {
        return new IneligibleICL();
    }

    /**
     * Create an instance of {@link CancelACH }
     * 
     */
    public CancelACH createCancelACH() {
        return new CancelACH();
    }

    /**
     * Create an instance of {@link ResubmitResponse }
     * 
     */
    public ResubmitResponse createResubmitResponse() {
        return new ResubmitResponse();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link SendSingleICL }
     * 
     */
    public SendSingleICL createSendSingleICL() {
        return new SendSingleICL();
    }

    /**
     * Create an instance of {@link SendDetailedACHResponse }
     * 
     */
    public SendDetailedACHResponse createSendDetailedACHResponse() {
        return new SendDetailedACHResponse();
    }

    /**
     * Create an instance of {@link IneligibleICLResponse }
     * 
     */
    public IneligibleICLResponse createIneligibleICLResponse() {
        return new IneligibleICLResponse();
    }

    /**
     * Create an instance of {@link SendSingleICLResponse }
     * 
     */
    public SendSingleICLResponse createSendSingleICLResponse() {
        return new SendSingleICLResponse();
    }

    /**
     * Create an instance of {@link FaultDetail }
     * 
     */
    public FaultDetail createFaultDetail() {
        return new FaultDetail();
    }

    /**
     * Create an instance of {@link GetCheckImageResponse }
     * 
     */
    public GetCheckImageResponse createGetCheckImageResponse() {
        return new GetCheckImageResponse();
    }

    /**
     * Create an instance of {@link GetCheckImage }
     * 
     */
    public GetCheckImage createGetCheckImage() {
        return new GetCheckImage();
    }

    /**
     * Create an instance of {@link SendACH }
     * 
     */
    public SendACH createSendACH() {
        return new SendACH();
    }

    /**
     * Create an instance of {@link SendACHResponse }
     * 
     */
    public SendACHResponse createSendACHResponse() {
        return new SendACHResponse();
    }

    /**
     * Create an instance of {@link CancelACHResponse }
     * 
     */
    public CancelACHResponse createCancelACHResponse() {
        return new CancelACHResponse();
    }

    /**
     * Create an instance of {@link SendICL }
     * 
     */
    public SendICL createSendICL() {
        return new SendICL();
    }

    /**
     * Create an instance of {@link SendDetailedACHCustomerResponse }
     * 
     */
    public SendDetailedACHCustomerResponse createSendDetailedACHCustomerResponse() {
        return new SendDetailedACHCustomerResponse();
    }

    /**
     * Create an instance of {@link SendACHCustomer }
     * 
     */
    public SendACHCustomer createSendACHCustomer() {
        return new SendACHCustomer();
    }

    /**
     * Create an instance of {@link SendDetailedACHCustomer }
     * 
     */
    public SendDetailedACHCustomer createSendDetailedACHCustomer() {
        return new SendDetailedACHCustomer();
    }

    /**
     * Create an instance of {@link Deposit }
     * 
     */
    public Deposit createDeposit() {
        return new Deposit();
    }

    /**
     * Create an instance of {@link DepositItem }
     * 
     */
    public DepositItem createDepositItem() {
        return new DepositItem();
    }

    /**
     * Create an instance of {@link AuxField }
     * 
     */
    public AuxField createAuxField() {
        return new AuxField();
    }

    /**
     * Create an instance of {@link LineItem }
     * 
     */
    public LineItem createLineItem() {
        return new LineItem();
    }

    /**
     * Create an instance of {@link ActionMessages }
     * 
     */
    public ActionMessages createActionMessages() {
        return new ActionMessages();
    }

    /**
     * Create an instance of {@link TlsiclResponse }
     * 
     */
    public TlsiclResponse createTlsiclResponse() {
        return new TlsiclResponse();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link ResponseItem }
     * 
     */
    public ResponseItem createResponseItem() {
        return new ResponseItem();
    }

    /**
     * Create an instance of {@link TransactionLoaderReply }
     * 
     */
    public TransactionLoaderReply createTransactionLoaderReply() {
        return new TransactionLoaderReply();
    }

    /**
     * Create an instance of {@link LookupBankRes }
     * 
     */
    public LookupBankRes createLookupBankRes() {
        return new LookupBankRes();
    }

    /**
     * Create an instance of {@link GetCheckImageRes }
     * 
     */
    public GetCheckImageRes createGetCheckImageRes() {
        return new GetCheckImageRes();
    }

    /**
     * Create an instance of {@link GetCheckImageRequest }
     * 
     */
    public GetCheckImageRequest createGetCheckImageRequest() {
        return new GetCheckImageRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "WSServiceException")
    public JAXBElement<FaultDetail> createWSServiceException(FaultDetail value) {
        return new JAXBElement<FaultDetail>(_WSServiceException_QNAME, FaultDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSingleICLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendSingleICLResponse")
    public JAXBElement<SendSingleICLResponse> createSendSingleICLResponse(SendSingleICLResponse value) {
        return new JAXBElement<SendSingleICLResponse>(_SendSingleICLResponse_QNAME, SendSingleICLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendACH }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendACH")
    public JAXBElement<SendACH> createSendACH(SendACH value) {
        return new JAXBElement<SendACH>(_SendACH_QNAME, SendACH.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCheckImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "getCheckImageResponse")
    public JAXBElement<GetCheckImageResponse> createGetCheckImageResponse(GetCheckImageResponse value) {
        return new JAXBElement<GetCheckImageResponse>(_GetCheckImageResponse_QNAME, GetCheckImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCheckImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "getCheckImage")
    public JAXBElement<GetCheckImage> createGetCheckImage(GetCheckImage value) {
        return new JAXBElement<GetCheckImage>(_GetCheckImage_QNAME, GetCheckImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSingleICL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendSingleICL")
    public JAXBElement<SendSingleICL> createSendSingleICL(SendSingleICL value) {
        return new JAXBElement<SendSingleICL>(_SendSingleICL_QNAME, SendSingleICL.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IneligibleICLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "ineligibleICLResponse")
    public JAXBElement<IneligibleICLResponse> createIneligibleICLResponse(IneligibleICLResponse value) {
        return new JAXBElement<IneligibleICLResponse>(_IneligibleICLResponse_QNAME, IneligibleICLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendDetailedACHResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendDetailedACHResponse")
    public JAXBElement<SendDetailedACHResponse> createSendDetailedACHResponse(SendDetailedACHResponse value) {
        return new JAXBElement<SendDetailedACHResponse>(_SendDetailedACHResponse_QNAME, SendDetailedACHResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendACHResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendACHResponse")
    public JAXBElement<SendACHResponse> createSendACHResponse(SendACHResponse value) {
        return new JAXBElement<SendACHResponse>(_SendACHResponse_QNAME, SendACHResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendDetailedACHCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendDetailedACHCustomerResponse")
    public JAXBElement<SendDetailedACHCustomerResponse> createSendDetailedACHCustomerResponse(SendDetailedACHCustomerResponse value) {
        return new JAXBElement<SendDetailedACHCustomerResponse>(_SendDetailedACHCustomerResponse_QNAME, SendDetailedACHCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendICL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendICL")
    public JAXBElement<SendICL> createSendICL(SendICL value) {
        return new JAXBElement<SendICL>(_SendICL_QNAME, SendICL.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendDetailedACHCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendDetailedACHCustomer")
    public JAXBElement<SendDetailedACHCustomer> createSendDetailedACHCustomer(SendDetailedACHCustomer value) {
        return new JAXBElement<SendDetailedACHCustomer>(_SendDetailedACHCustomer_QNAME, SendDetailedACHCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendACHCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendACHCustomer")
    public JAXBElement<SendACHCustomer> createSendACHCustomer(SendACHCustomer value) {
        return new JAXBElement<SendACHCustomer>(_SendACHCustomer_QNAME, SendACHCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelACHResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "cancelACHResponse")
    public JAXBElement<CancelACHResponse> createCancelACHResponse(CancelACHResponse value) {
        return new JAXBElement<CancelACHResponse>(_CancelACHResponse_QNAME, CancelACHResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resubmit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "resubmit")
    public JAXBElement<Resubmit> createResubmit(Resubmit value) {
        return new JAXBElement<Resubmit>(_Resubmit_QNAME, Resubmit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "registerCustomer")
    public JAXBElement<RegisterCustomer> createRegisterCustomer(RegisterCustomer value) {
        return new JAXBElement<RegisterCustomer>(_RegisterCustomer_QNAME, RegisterCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookupBankResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "lookupBankResponse")
    public JAXBElement<LookupBankResponse> createLookupBankResponse(LookupBankResponse value) {
        return new JAXBElement<LookupBankResponse>(_LookupBankResponse_QNAME, LookupBankResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookupBank }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "lookupBank")
    public JAXBElement<LookupBank> createLookupBank(LookupBank value) {
        return new JAXBElement<LookupBank>(_LookupBank_QNAME, LookupBank.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefundResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "refundResponse")
    public JAXBElement<RefundResponse> createRefundResponse(RefundResponse value) {
        return new JAXBElement<RefundResponse>(_RefundResponse_QNAME, RefundResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelACH }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "cancelACH")
    public JAXBElement<CancelACH> createCancelACH(CancelACH value) {
        return new JAXBElement<CancelACH>(_CancelACH_QNAME, CancelACH.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IneligibleICL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "ineligibleICL")
    public JAXBElement<IneligibleICL> createIneligibleICL(IneligibleICL value) {
        return new JAXBElement<IneligibleICL>(_IneligibleICL_QNAME, IneligibleICL.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "ServiceException")
    public JAXBElement<ServiceException> createServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_ServiceException_QNAME, ServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResubmitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "resubmitResponse")
    public JAXBElement<ResubmitResponse> createResubmitResponse(ResubmitResponse value) {
        return new JAXBElement<ResubmitResponse>(_ResubmitResponse_QNAME, ResubmitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendICLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendICLResponse")
    public JAXBElement<SendICLResponse> createSendICLResponse(SendICLResponse value) {
        return new JAXBElement<SendICLResponse>(_SendICLResponse_QNAME, SendICLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "registerCustomerResponse")
    public JAXBElement<RegisterCustomerResponse> createRegisterCustomerResponse(RegisterCustomerResponse value) {
        return new JAXBElement<RegisterCustomerResponse>(_RegisterCustomerResponse_QNAME, RegisterCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendACHCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendACHCustomerResponse")
    public JAXBElement<SendACHCustomerResponse> createSendACHCustomerResponse(SendACHCustomerResponse value) {
        return new JAXBElement<SendACHCustomerResponse>(_SendACHCustomerResponse_QNAME, SendACHCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Refund }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "refund")
    public JAXBElement<Refund> createRefund(Refund value) {
        return new JAXBElement<Refund>(_Refund_QNAME, Refund.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendDetailedACH }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.fileloader.tc.com/", name = "sendDetailedACH")
    public JAXBElement<SendDetailedACH> createSendDetailedACH(SendDetailedACH value) {
        return new JAXBElement<SendDetailedACH>(_SendDetailedACH_QNAME, SendDetailedACH.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "companyDescription", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerCompanyDescription(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerCompanyDescription_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "checkSerialNumber", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerCheckSerialNumber(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerCheckSerialNumber_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "discretionaryData", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerDiscretionaryData(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerDiscretionaryData_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "traceNumber", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerTraceNumber(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerTraceNumber_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "depositName", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerDepositName(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerDepositName_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "terminalCity", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerTerminalCity(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerTerminalCity_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "addenda", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerAddenda(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerAddenda_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "effectiveDate", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerEffectiveDate(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerEffectiveDate_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "terminalState", scope = SendDetailedACHCustomer.class)
    public JAXBElement<String> createSendDetailedACHCustomerTerminalState(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerTerminalState_QNAME, String.class, SendDetailedACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "note", scope = IneligibleICL.class)
    public JAXBElement<String> createIneligibleICLNote(String value) {
        return new JAXBElement<String>(_IneligibleICLNote_QNAME, String.class, IneligibleICL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "depositName", scope = SendACH.class)
    public JAXBElement<String> createSendACHDepositName(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerDepositName_QNAME, String.class, SendACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "depositName", scope = SendACHCustomer.class)
    public JAXBElement<String> createSendACHCustomerDepositName(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerDepositName_QNAME, String.class, SendACHCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "companyDescription", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHCompanyDescription(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerCompanyDescription_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "checkSerialNumber", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHCheckSerialNumber(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerCheckSerialNumber_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "discretionaryData", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHDiscretionaryData(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerDiscretionaryData_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "traceNumber", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHTraceNumber(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerTraceNumber_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "depositName", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHDepositName(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerDepositName_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "entryClassCode", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHEntryClassCode(String value) {
        return new JAXBElement<String>(_SendDetailedACHEntryClassCode_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "terminalCity", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHTerminalCity(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerTerminalCity_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "addenda", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHAddenda(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerAddenda_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "effectiveDate", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHEffectiveDate(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerEffectiveDate_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "terminalState", scope = SendDetailedACH.class)
    public JAXBElement<String> createSendDetailedACHTerminalState(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerTerminalState_QNAME, String.class, SendDetailedACH.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "depositName", scope = SendSingleICL.class)
    public JAXBElement<String> createSendSingleICLDepositName(String value) {
        return new JAXBElement<String>(_SendDetailedACHCustomerDepositName_QNAME, String.class, SendSingleICL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Image }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "highQualityImage", scope = SendSingleICL.class)
    public JAXBElement<Image> createSendSingleICLHighQualityImage(Image value) {
        return new JAXBElement<Image>(_SendSingleICLHighQualityImage_QNAME, Image.class, SendSingleICL.class, value);
    }

}
