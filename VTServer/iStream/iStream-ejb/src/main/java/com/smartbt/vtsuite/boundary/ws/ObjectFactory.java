
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

    private final static QName _CheckAuthSubmit_QNAME = new QName("http://web.service.scanner.tc.com/", "checkAuthSubmit");
    private final static QName _ForgotPasswordResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "forgotPasswordResponse");
    private final static QName _CloseBatchResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "closeBatchResponse");
    private final static QName _Authenticate_QNAME = new QName("http://web.service.scanner.tc.com/", "authenticate");
    private final static QName _SendImage_QNAME = new QName("http://web.service.scanner.tc.com/", "sendImage");
    private final static QName _GetMessagesResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "getMessagesResponse");
    private final static QName _CheckAuthSubmitResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "checkAuthSubmitResponse");
    private final static QName _MakeDepositSlip_QNAME = new QName("http://web.service.scanner.tc.com/", "makeDepositSlip");
    private final static QName _SendStats_QNAME = new QName("http://web.service.scanner.tc.com/", "sendStats");
    private final static QName _LookupEntityResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "lookupEntityResponse");
    private final static QName _SendImageResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "sendImageResponse");
    private final static QName _GetMessages_QNAME = new QName("http://web.service.scanner.tc.com/", "getMessages");
    private final static QName _GetClientData_QNAME = new QName("http://web.service.scanner.tc.com/", "getClientData");
    private final static QName _ChangePasswordResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "changePasswordResponse");
    private final static QName _EnhancedCheckAuthPoll_QNAME = new QName("http://web.service.scanner.tc.com/", "enhancedCheckAuthPoll");
    private final static QName _CloseBatch_QNAME = new QName("http://web.service.scanner.tc.com/", "closeBatch");
    private final static QName _AuthenticateResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "authenticateResponse");
    private final static QName _ChangePassword_QNAME = new QName("http://web.service.scanner.tc.com/", "changePassword");
    private final static QName _CheckAuthPoll_QNAME = new QName("http://web.service.scanner.tc.com/", "checkAuthPoll");
    private final static QName _CheckAuthLocationConfigResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "checkAuthLocationConfigResponse");
    private final static QName _CheckAuthResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "checkAuthResponse");
    private final static QName _GetClientAuxDataConfigResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "getClientAuxDataConfigResponse");
    private final static QName _LookupEntity_QNAME = new QName("http://web.service.scanner.tc.com/", "lookupEntity");
    private final static QName _GetRecentDepositsResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "getRecentDepositsResponse");
    private final static QName _CheckAuthLocationConfig_QNAME = new QName("http://web.service.scanner.tc.com/", "checkAuthLocationConfig");
    private final static QName _GetRecentDeposits_QNAME = new QName("http://web.service.scanner.tc.com/", "getRecentDeposits");
    private final static QName _MakeDepositSlipResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "makeDepositSlipResponse");
    private final static QName _ForgotPassword_QNAME = new QName("http://web.service.scanner.tc.com/", "forgotPassword");
    private final static QName _EnhancedCheckAuthPollResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "enhancedCheckAuthPollResponse");
    private final static QName _GetClientDataResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "getClientDataResponse");
    private final static QName _SendStatsResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "sendStatsResponse");
    private final static QName _GetClientAuxDataConfig_QNAME = new QName("http://web.service.scanner.tc.com/", "getClientAuxDataConfig");
    private final static QName _CheckAuth_QNAME = new QName("http://web.service.scanner.tc.com/", "checkAuth");
    private final static QName _CheckAuthPollResponse_QNAME = new QName("http://web.service.scanner.tc.com/", "checkAuthPollResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.smartbt.vtsuite.boundary.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendStats }
     * 
     */
    public SendStats createSendStats() {
        return new SendStats();
    }

    /**
     * Create an instance of {@link LookupEntityResponse }
     * 
     */
    public LookupEntityResponse createLookupEntityResponse() {
        return new LookupEntityResponse();
    }

    /**
     * Create an instance of {@link SendImageResponse }
     * 
     */
    public SendImageResponse createSendImageResponse() {
        return new SendImageResponse();
    }

    /**
     * Create an instance of {@link GetMessages }
     * 
     */
    public GetMessages createGetMessages() {
        return new GetMessages();
    }

    /**
     * Create an instance of {@link SendImage }
     * 
     */
    public SendImage createSendImage() {
        return new SendImage();
    }

    /**
     * Create an instance of {@link Authenticate }
     * 
     */
    public Authenticate createAuthenticate() {
        return new Authenticate();
    }

    /**
     * Create an instance of {@link CloseBatchResponse }
     * 
     */
    public CloseBatchResponse createCloseBatchResponse() {
        return new CloseBatchResponse();
    }

    /**
     * Create an instance of {@link ForgotPasswordResponse }
     * 
     */
    public ForgotPasswordResponse createForgotPasswordResponse() {
        return new ForgotPasswordResponse();
    }

    /**
     * Create an instance of {@link CheckAuthSubmit }
     * 
     */
    public CheckAuthSubmit createCheckAuthSubmit() {
        return new CheckAuthSubmit();
    }

    /**
     * Create an instance of {@link GetMessagesResponse }
     * 
     */
    public GetMessagesResponse createGetMessagesResponse() {
        return new GetMessagesResponse();
    }

    /**
     * Create an instance of {@link CheckAuthSubmitResponse }
     * 
     */
    public CheckAuthSubmitResponse createCheckAuthSubmitResponse() {
        return new CheckAuthSubmitResponse();
    }

    /**
     * Create an instance of {@link MakeDepositSlip }
     * 
     */
    public MakeDepositSlip createMakeDepositSlip() {
        return new MakeDepositSlip();
    }

    /**
     * Create an instance of {@link CheckAuthResponse }
     * 
     */
    public CheckAuthResponse createCheckAuthResponse() {
        return new CheckAuthResponse();
    }

    /**
     * Create an instance of {@link GetClientAuxDataConfigResponse }
     * 
     */
    public GetClientAuxDataConfigResponse createGetClientAuxDataConfigResponse() {
        return new GetClientAuxDataConfigResponse();
    }

    /**
     * Create an instance of {@link LookupEntity }
     * 
     */
    public LookupEntity createLookupEntity() {
        return new LookupEntity();
    }

    /**
     * Create an instance of {@link GetRecentDepositsResponse }
     * 
     */
    public GetRecentDepositsResponse createGetRecentDepositsResponse() {
        return new GetRecentDepositsResponse();
    }

    /**
     * Create an instance of {@link CheckAuthLocationConfig }
     * 
     */
    public CheckAuthLocationConfig createCheckAuthLocationConfig() {
        return new CheckAuthLocationConfig();
    }

    /**
     * Create an instance of {@link GetRecentDeposits }
     * 
     */
    public GetRecentDeposits createGetRecentDeposits() {
        return new GetRecentDeposits();
    }

    /**
     * Create an instance of {@link ForgotPassword }
     * 
     */
    public ForgotPassword createForgotPassword() {
        return new ForgotPassword();
    }

    /**
     * Create an instance of {@link MakeDepositSlipResponse }
     * 
     */
    public MakeDepositSlipResponse createMakeDepositSlipResponse() {
        return new MakeDepositSlipResponse();
    }

    /**
     * Create an instance of {@link EnhancedCheckAuthPollResponse }
     * 
     */
    public EnhancedCheckAuthPollResponse createEnhancedCheckAuthPollResponse() {
        return new EnhancedCheckAuthPollResponse();
    }

    /**
     * Create an instance of {@link SendStatsResponse }
     * 
     */
    public SendStatsResponse createSendStatsResponse() {
        return new SendStatsResponse();
    }

    /**
     * Create an instance of {@link GetClientAuxDataConfig }
     * 
     */
    public GetClientAuxDataConfig createGetClientAuxDataConfig() {
        return new GetClientAuxDataConfig();
    }

    /**
     * Create an instance of {@link GetClientDataResponse }
     * 
     */
    public GetClientDataResponse createGetClientDataResponse() {
        return new GetClientDataResponse();
    }

    /**
     * Create an instance of {@link CheckAuthPollResponse }
     * 
     */
    public CheckAuthPollResponse createCheckAuthPollResponse() {
        return new CheckAuthPollResponse();
    }

    /**
     * Create an instance of {@link CheckAuth }
     * 
     */
    public CheckAuth createCheckAuth() {
        return new CheckAuth();
    }

    /**
     * Create an instance of {@link ChangePasswordResponse }
     * 
     */
    public ChangePasswordResponse createChangePasswordResponse() {
        return new ChangePasswordResponse();
    }

    /**
     * Create an instance of {@link GetClientData }
     * 
     */
    public GetClientData createGetClientData() {
        return new GetClientData();
    }

    /**
     * Create an instance of {@link CheckAuthPoll }
     * 
     */
    public CheckAuthPoll createCheckAuthPoll() {
        return new CheckAuthPoll();
    }

    /**
     * Create an instance of {@link ChangePassword }
     * 
     */
    public ChangePassword createChangePassword() {
        return new ChangePassword();
    }

    /**
     * Create an instance of {@link AuthenticateResponse }
     * 
     */
    public AuthenticateResponse createAuthenticateResponse() {
        return new AuthenticateResponse();
    }

    /**
     * Create an instance of {@link CloseBatch }
     * 
     */
    public CloseBatch createCloseBatch() {
        return new CloseBatch();
    }

    /**
     * Create an instance of {@link EnhancedCheckAuthPoll }
     * 
     */
    public EnhancedCheckAuthPoll createEnhancedCheckAuthPoll() {
        return new EnhancedCheckAuthPoll();
    }

    /**
     * Create an instance of {@link CheckAuthLocationConfigResponse }
     * 
     */
    public CheckAuthLocationConfigResponse createCheckAuthLocationConfigResponse() {
        return new CheckAuthLocationConfigResponse();
    }

    /**
     * Create an instance of {@link MessagesRequest }
     * 
     */
    public MessagesRequest createMessagesRequest() {
        return new MessagesRequest();
    }

    /**
     * Create an instance of {@link Deposit }
     * 
     */
    public Deposit createDeposit() {
        return new Deposit();
    }

    /**
     * Create an instance of {@link DepositSlipRequest }
     * 
     */
    public DepositSlipRequest createDepositSlipRequest() {
        return new DepositSlipRequest();
    }

    /**
     * Create an instance of {@link CheckAuthSubmitRequest }
     * 
     */
    public CheckAuthSubmitRequest createCheckAuthSubmitRequest() {
        return new CheckAuthSubmitRequest();
    }

    /**
     * Create an instance of {@link MessagesRes }
     * 
     */
    public MessagesRes createMessagesRes() {
        return new MessagesRes();
    }

    /**
     * Create an instance of {@link SendStatsRequest }
     * 
     */
    public SendStatsRequest createSendStatsRequest() {
        return new SendStatsRequest();
    }

    /**
     * Create an instance of {@link RecentDepositRes }
     * 
     */
    public RecentDepositRes createRecentDepositRes() {
        return new RecentDepositRes();
    }

    /**
     * Create an instance of {@link Code }
     * 
     */
    public Code createCode() {
        return new Code();
    }

    /**
     * Create an instance of {@link CloseBatchRes }
     * 
     */
    public CloseBatchRes createCloseBatchRes() {
        return new CloseBatchRes();
    }

    /**
     * Create an instance of {@link DepositSlipRes }
     * 
     */
    public DepositSlipRes createDepositSlipRes() {
        return new DepositSlipRes();
    }

    /**
     * Create an instance of {@link ImageRef }
     * 
     */
    public ImageRef createImageRef() {
        return new ImageRef();
    }

    /**
     * Create an instance of {@link EnhancedCheckAuthPollRes }
     * 
     */
    public EnhancedCheckAuthPollRes createEnhancedCheckAuthPollRes() {
        return new EnhancedCheckAuthPollRes();
    }

    /**
     * Create an instance of {@link DraftAuxData }
     * 
     */
    public DraftAuxData createDraftAuxData() {
        return new DraftAuxData();
    }

    /**
     * Create an instance of {@link AuthRequest }
     * 
     */
    public AuthRequest createAuthRequest() {
        return new AuthRequest();
    }

    /**
     * Create an instance of {@link CloseBatchRequest }
     * 
     */
    public CloseBatchRequest createCloseBatchRequest() {
        return new CloseBatchRequest();
    }

    /**
     * Create an instance of {@link ServiceError }
     * 
     */
    public ServiceError createServiceError() {
        return new ServiceError();
    }

    /**
     * Create an instance of {@link ClientRequest }
     * 
     */
    public ClientRequest createClientRequest() {
        return new ClientRequest();
    }

    /**
     * Create an instance of {@link EnhancedCheckAuthPollRequest }
     * 
     */
    public EnhancedCheckAuthPollRequest createEnhancedCheckAuthPollRequest() {
        return new EnhancedCheckAuthPollRequest();
    }

    /**
     * Create an instance of {@link DraftAuxDataConfig }
     * 
     */
    public DraftAuxDataConfig createDraftAuxDataConfig() {
        return new DraftAuxDataConfig();
    }

    /**
     * Create an instance of {@link CheckAuthPollRes }
     * 
     */
    public CheckAuthPollRes createCheckAuthPollRes() {
        return new CheckAuthPollRes();
    }

    /**
     * Create an instance of {@link DraftAuxDataDetail }
     * 
     */
    public DraftAuxDataDetail createDraftAuxDataDetail() {
        return new DraftAuxDataDetail();
    }

    /**
     * Create an instance of {@link EnhancedCheckAuthPollItem }
     * 
     */
    public EnhancedCheckAuthPollItem createEnhancedCheckAuthPollItem() {
        return new EnhancedCheckAuthPollItem();
    }

    /**
     * Create an instance of {@link CheckAuthRes }
     * 
     */
    public CheckAuthRes createCheckAuthRes() {
        return new CheckAuthRes();
    }

    /**
     * Create an instance of {@link RecentDepositRequest }
     * 
     */
    public RecentDepositRequest createRecentDepositRequest() {
        return new RecentDepositRequest();
    }

    /**
     * Create an instance of {@link DraftAuxDataColumnOption }
     * 
     */
    public DraftAuxDataColumnOption createDraftAuxDataColumnOption() {
        return new DraftAuxDataColumnOption();
    }

    /**
     * Create an instance of {@link CheckAuthLocationConfigRes }
     * 
     */
    public CheckAuthLocationConfigRes createCheckAuthLocationConfigRes() {
        return new CheckAuthLocationConfigRes();
    }

    /**
     * Create an instance of {@link ChangePasswordRequest }
     * 
     */
    public ChangePasswordRequest createChangePasswordRequest() {
        return new ChangePasswordRequest();
    }

    /**
     * Create an instance of {@link EntityData }
     * 
     */
    public EntityData createEntityData() {
        return new EntityData();
    }

    /**
     * Create an instance of {@link ForgotPasswordRequest }
     * 
     */
    public ForgotPasswordRequest createForgotPasswordRequest() {
        return new ForgotPasswordRequest();
    }

    /**
     * Create an instance of {@link VersionInfo }
     * 
     */
    public VersionInfo createVersionInfo() {
        return new VersionInfo();
    }

    /**
     * Create an instance of {@link CheckAuthPollItem }
     * 
     */
    public CheckAuthPollItem createCheckAuthPollItem() {
        return new CheckAuthPollItem();
    }

    /**
     * Create an instance of {@link AuthRes }
     * 
     */
    public AuthRes createAuthRes() {
        return new AuthRes();
    }

    /**
     * Create an instance of {@link Stat }
     * 
     */
    public Stat createStat() {
        return new Stat();
    }

    /**
     * Create an instance of {@link LookupEntityRequest }
     * 
     */
    public LookupEntityRequest createLookupEntityRequest() {
        return new LookupEntityRequest();
    }

    /**
     * Create an instance of {@link ClientData }
     * 
     */
    public ClientData createClientData() {
        return new ClientData();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link CheckAuthRequest }
     * 
     */
    public CheckAuthRequest createCheckAuthRequest() {
        return new CheckAuthRequest();
    }

    /**
     * Create an instance of {@link SendImageRequest }
     * 
     */
    public SendImageRequest createSendImageRequest() {
        return new SendImageRequest();
    }

    /**
     * Create an instance of {@link CheckAuthPollRequest }
     * 
     */
    public CheckAuthPollRequest createCheckAuthPollRequest() {
        return new CheckAuthPollRequest();
    }

    /**
     * Create an instance of {@link CheckAuthLocationConfigRequest }
     * 
     */
    public CheckAuthLocationConfigRequest createCheckAuthLocationConfigRequest() {
        return new CheckAuthLocationConfigRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthSubmit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "checkAuthSubmit")
    public JAXBElement<CheckAuthSubmit> createCheckAuthSubmit(CheckAuthSubmit value) {
        return new JAXBElement<CheckAuthSubmit>(_CheckAuthSubmit_QNAME, CheckAuthSubmit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ForgotPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "forgotPasswordResponse")
    public JAXBElement<ForgotPasswordResponse> createForgotPasswordResponse(ForgotPasswordResponse value) {
        return new JAXBElement<ForgotPasswordResponse>(_ForgotPasswordResponse_QNAME, ForgotPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CloseBatchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "closeBatchResponse")
    public JAXBElement<CloseBatchResponse> createCloseBatchResponse(CloseBatchResponse value) {
        return new JAXBElement<CloseBatchResponse>(_CloseBatchResponse_QNAME, CloseBatchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authenticate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "authenticate")
    public JAXBElement<Authenticate> createAuthenticate(Authenticate value) {
        return new JAXBElement<Authenticate>(_Authenticate_QNAME, Authenticate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "sendImage")
    public JAXBElement<SendImage> createSendImage(SendImage value) {
        return new JAXBElement<SendImage>(_SendImage_QNAME, SendImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMessagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "getMessagesResponse")
    public JAXBElement<GetMessagesResponse> createGetMessagesResponse(GetMessagesResponse value) {
        return new JAXBElement<GetMessagesResponse>(_GetMessagesResponse_QNAME, GetMessagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthSubmitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "checkAuthSubmitResponse")
    public JAXBElement<CheckAuthSubmitResponse> createCheckAuthSubmitResponse(CheckAuthSubmitResponse value) {
        return new JAXBElement<CheckAuthSubmitResponse>(_CheckAuthSubmitResponse_QNAME, CheckAuthSubmitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeDepositSlip }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "makeDepositSlip")
    public JAXBElement<MakeDepositSlip> createMakeDepositSlip(MakeDepositSlip value) {
        return new JAXBElement<MakeDepositSlip>(_MakeDepositSlip_QNAME, MakeDepositSlip.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "sendStats")
    public JAXBElement<SendStats> createSendStats(SendStats value) {
        return new JAXBElement<SendStats>(_SendStats_QNAME, SendStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookupEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "lookupEntityResponse")
    public JAXBElement<LookupEntityResponse> createLookupEntityResponse(LookupEntityResponse value) {
        return new JAXBElement<LookupEntityResponse>(_LookupEntityResponse_QNAME, LookupEntityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "sendImageResponse")
    public JAXBElement<SendImageResponse> createSendImageResponse(SendImageResponse value) {
        return new JAXBElement<SendImageResponse>(_SendImageResponse_QNAME, SendImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMessages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "getMessages")
    public JAXBElement<GetMessages> createGetMessages(GetMessages value) {
        return new JAXBElement<GetMessages>(_GetMessages_QNAME, GetMessages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "getClientData")
    public JAXBElement<GetClientData> createGetClientData(GetClientData value) {
        return new JAXBElement<GetClientData>(_GetClientData_QNAME, GetClientData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "changePasswordResponse")
    public JAXBElement<ChangePasswordResponse> createChangePasswordResponse(ChangePasswordResponse value) {
        return new JAXBElement<ChangePasswordResponse>(_ChangePasswordResponse_QNAME, ChangePasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnhancedCheckAuthPoll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "enhancedCheckAuthPoll")
    public JAXBElement<EnhancedCheckAuthPoll> createEnhancedCheckAuthPoll(EnhancedCheckAuthPoll value) {
        return new JAXBElement<EnhancedCheckAuthPoll>(_EnhancedCheckAuthPoll_QNAME, EnhancedCheckAuthPoll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CloseBatch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "closeBatch")
    public JAXBElement<CloseBatch> createCloseBatch(CloseBatch value) {
        return new JAXBElement<CloseBatch>(_CloseBatch_QNAME, CloseBatch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "authenticateResponse")
    public JAXBElement<AuthenticateResponse> createAuthenticateResponse(AuthenticateResponse value) {
        return new JAXBElement<AuthenticateResponse>(_AuthenticateResponse_QNAME, AuthenticateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "changePassword")
    public JAXBElement<ChangePassword> createChangePassword(ChangePassword value) {
        return new JAXBElement<ChangePassword>(_ChangePassword_QNAME, ChangePassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPoll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "checkAuthPoll")
    public JAXBElement<CheckAuthPoll> createCheckAuthPoll(CheckAuthPoll value) {
        return new JAXBElement<CheckAuthPoll>(_CheckAuthPoll_QNAME, CheckAuthPoll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthLocationConfigResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "checkAuthLocationConfigResponse")
    public JAXBElement<CheckAuthLocationConfigResponse> createCheckAuthLocationConfigResponse(CheckAuthLocationConfigResponse value) {
        return new JAXBElement<CheckAuthLocationConfigResponse>(_CheckAuthLocationConfigResponse_QNAME, CheckAuthLocationConfigResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "checkAuthResponse")
    public JAXBElement<CheckAuthResponse> createCheckAuthResponse(CheckAuthResponse value) {
        return new JAXBElement<CheckAuthResponse>(_CheckAuthResponse_QNAME, CheckAuthResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientAuxDataConfigResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "getClientAuxDataConfigResponse")
    public JAXBElement<GetClientAuxDataConfigResponse> createGetClientAuxDataConfigResponse(GetClientAuxDataConfigResponse value) {
        return new JAXBElement<GetClientAuxDataConfigResponse>(_GetClientAuxDataConfigResponse_QNAME, GetClientAuxDataConfigResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookupEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "lookupEntity")
    public JAXBElement<LookupEntity> createLookupEntity(LookupEntity value) {
        return new JAXBElement<LookupEntity>(_LookupEntity_QNAME, LookupEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecentDepositsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "getRecentDepositsResponse")
    public JAXBElement<GetRecentDepositsResponse> createGetRecentDepositsResponse(GetRecentDepositsResponse value) {
        return new JAXBElement<GetRecentDepositsResponse>(_GetRecentDepositsResponse_QNAME, GetRecentDepositsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthLocationConfig }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "checkAuthLocationConfig")
    public JAXBElement<CheckAuthLocationConfig> createCheckAuthLocationConfig(CheckAuthLocationConfig value) {
        return new JAXBElement<CheckAuthLocationConfig>(_CheckAuthLocationConfig_QNAME, CheckAuthLocationConfig.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecentDeposits }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "getRecentDeposits")
    public JAXBElement<GetRecentDeposits> createGetRecentDeposits(GetRecentDeposits value) {
        return new JAXBElement<GetRecentDeposits>(_GetRecentDeposits_QNAME, GetRecentDeposits.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeDepositSlipResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "makeDepositSlipResponse")
    public JAXBElement<MakeDepositSlipResponse> createMakeDepositSlipResponse(MakeDepositSlipResponse value) {
        return new JAXBElement<MakeDepositSlipResponse>(_MakeDepositSlipResponse_QNAME, MakeDepositSlipResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ForgotPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "forgotPassword")
    public JAXBElement<ForgotPassword> createForgotPassword(ForgotPassword value) {
        return new JAXBElement<ForgotPassword>(_ForgotPassword_QNAME, ForgotPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnhancedCheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "enhancedCheckAuthPollResponse")
    public JAXBElement<EnhancedCheckAuthPollResponse> createEnhancedCheckAuthPollResponse(EnhancedCheckAuthPollResponse value) {
        return new JAXBElement<EnhancedCheckAuthPollResponse>(_EnhancedCheckAuthPollResponse_QNAME, EnhancedCheckAuthPollResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "getClientDataResponse")
    public JAXBElement<GetClientDataResponse> createGetClientDataResponse(GetClientDataResponse value) {
        return new JAXBElement<GetClientDataResponse>(_GetClientDataResponse_QNAME, GetClientDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendStatsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "sendStatsResponse")
    public JAXBElement<SendStatsResponse> createSendStatsResponse(SendStatsResponse value) {
        return new JAXBElement<SendStatsResponse>(_SendStatsResponse_QNAME, SendStatsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientAuxDataConfig }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "getClientAuxDataConfig")
    public JAXBElement<GetClientAuxDataConfig> createGetClientAuxDataConfig(GetClientAuxDataConfig value) {
        return new JAXBElement<GetClientAuxDataConfig>(_GetClientAuxDataConfig_QNAME, GetClientAuxDataConfig.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuth }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "checkAuth")
    public JAXBElement<CheckAuth> createCheckAuth(CheckAuth value) {
        return new JAXBElement<CheckAuth>(_CheckAuth_QNAME, CheckAuth.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAuthPollResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.service.scanner.tc.com/", name = "checkAuthPollResponse")
    public JAXBElement<CheckAuthPollResponse> createCheckAuthPollResponse(CheckAuthPollResponse value) {
        return new JAXBElement<CheckAuthPollResponse>(_CheckAuthPollResponse_QNAME, CheckAuthPollResponse.class, null, value);
    }

}
