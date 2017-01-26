
package com.smartbt.vtsuite.boundary.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "iStreamSrvHostWSSoap", targetNamespace = "https://SistemasGalileo.com/Services/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IStreamSrvHostWSSoap {


    /**
     * This method allows to activate a given card whose activation is pending.
     * 
     * @param pCardNumber
     * @param pTerminalCode
     * @param pRequestID
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.CardActivationResponse
     */
    @WebMethod(operationName = "WMCardActivation", action = "https://SistemasGalileo.com/Services/WMCardActivation")
    @WebResult(name = "WMCardActivationResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMCardActivation", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardActivation")
    @ResponseWrapper(localName = "WMCardActivationResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardActivationResponse")
    public CardActivationResponse wmCardActivation(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pTerminalCode", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pTerminalCode,
        @WebParam(name = "pCardNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCardNumber);

    /**
     * This method allows customized a card.
     * 
     * @param pIdType
     * @param pState
     * @param pDateOfBirth
     * @param pAddress
     * @param pCity
     * @param pIdState
     * @param pTelephoneAreaCode
     * @param pCellphone
     * @param pZipCode
     * @param pMiddleName
     * @param pTelephone
     * @param pFaxAreaCode
     * @param pLastName
     * @param pRequestID
     * @param pWorkphoneAreaCode
     * @param pCountry
     * @param pCellphoneAreaCode
     * @param pPersonTitle
     * @param pEmail
     * @param pCurrentAddress
     * @param pRBService
     * @param pFirstName
     * @param pId
     * @param pMaidenName
     * @param pWorkphone
     * @param pIdCountry
     * @param pFaxphone
     * @param pCard
     * @param pIdExpiration
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.CardCreationResponse
     */
    @WebMethod(operationName = "WMCardPersonalization", action = "https://SistemasGalileo.com/Services/WMCardPersonalization")
    @WebResult(name = "WMCardPersonalizationResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMCardPersonalization", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardPersonalization")
    @ResponseWrapper(localName = "WMCardPersonalizationResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardPersonalizationResponse")
    public CardCreationResponse wmCardPersonalization(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pCard", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCard,
        @WebParam(name = "pId", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pId,
        @WebParam(name = "pIdType", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pIdType,
        @WebParam(name = "pIdExpiration", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pIdExpiration,
        @WebParam(name = "pIdCountry", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pIdCountry,
        @WebParam(name = "pIdState", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pIdState,
        @WebParam(name = "pPersonTitle", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pPersonTitle,
        @WebParam(name = "pFirstName", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pFirstName,
        @WebParam(name = "pMiddleName", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pMiddleName,
        @WebParam(name = "pLastName", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pLastName,
        @WebParam(name = "pMaidenName", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pMaidenName,
        @WebParam(name = "pDateOfBirth", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pDateOfBirth,
        @WebParam(name = "pCountry", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCountry,
        @WebParam(name = "pState", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pState,
        @WebParam(name = "pCity", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCity,
        @WebParam(name = "pAddress", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pAddress,
        @WebParam(name = "pZipCode", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pZipCode,
        @WebParam(name = "pEmail", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pEmail,
        @WebParam(name = "pTelephoneAreaCode", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pTelephoneAreaCode,
        @WebParam(name = "pTelephone", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pTelephone,
        @WebParam(name = "pCellphoneAreaCode", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCellphoneAreaCode,
        @WebParam(name = "pCellphone", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCellphone,
        @WebParam(name = "pWorkphoneAreaCode", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pWorkphoneAreaCode,
        @WebParam(name = "pWorkphone", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pWorkphone,
        @WebParam(name = "pFaxAreaCode", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pFaxAreaCode,
        @WebParam(name = "pFaxphone", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pFaxphone,
        @WebParam(name = "pRBService", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRBService,
        @WebParam(name = "pCurrentAddress", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCurrentAddress);

    /**
     * This method allows to load funds into a given card from a virtual terminal.
     * 
     * @param pTransAmount
     * @param pCheckFee
     * @param pCardNumber
     * @param pTerminalCode
     * @param pRequestID
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.CardLoadResponse
     */
    @WebMethod(operationName = "WMCardLoad", action = "https://SistemasGalileo.com/Services/WMCardLoad")
    @WebResult(name = "WMCardLoadResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMCardLoad", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardLoad")
    @ResponseWrapper(localName = "WMCardLoadResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardLoadResponse")
    public CardLoadResponse wmCardLoad(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pTerminalCode", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pTerminalCode,
        @WebParam(name = "pCardNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCardNumber,
        @WebParam(name = "pTransAmount", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pTransAmount,
        @WebParam(name = "pCheckFee", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCheckFee);

    /**
     * This method allows to obtain the current balance for a given card.
     * 
     * @param pCardNumber
     * @param pRequestID
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.BalanceInquiryResponse
     */
    @WebMethod(operationName = "WMBalanceInquiry", action = "https://SistemasGalileo.com/Services/WMBalanceInquiry")
    @WebResult(name = "WMBalanceInquiryResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMBalanceInquiry", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMBalanceInquiry")
    @ResponseWrapper(localName = "WMBalanceInquiryResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMBalanceInquiryResponse")
    public BalanceInquiryResponse wmBalanceInquiry(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pCardNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCardNumber);

    /**
     * This method allows to transfer funds from a card to a bank account.
     * 
     * @param pAmount
     * @param pRoutingBankNumber
     * @param pCardNumber
     * @param pAccountNumber
     * @param pRequestID
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.CardToBankResponse
     */
    @WebMethod(operationName = "WMCardToBank", action = "https://SistemasGalileo.com/Services/WMCardToBank")
    @WebResult(name = "WMCardToBankResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMCardToBank", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardToBank")
    @ResponseWrapper(localName = "WMCardToBankResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardToBankResponse")
    public CardToBankResponse wmCardToBank(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pCardNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCardNumber,
        @WebParam(name = "pAccountNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pAccountNumber,
        @WebParam(name = "pRoutingBankNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRoutingBankNumber,
        @WebParam(name = "pAmount", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pAmount);

    /**
     * This method allows to validate the status for a given card.
     * 
     * @param pAmount
     * @param pCardNumber
     * @param pRequestID
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.CardValidationResponse
     */
    @WebMethod(operationName = "WMCardValidation", action = "https://SistemasGalileo.com/Services/WMCardValidation")
    @WebResult(name = "WMCardValidationResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMCardValidation", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardValidation")
    @ResponseWrapper(localName = "WMCardValidationResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardValidationResponse")
    public CardValidationResponse wmCardValidation(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pCardNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCardNumber,
        @WebParam(name = "pAmount", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pAmount);

    /**
     * This method allows to validate if the id given is correct for the card holder.
     * 
     
     * * @param pRequestID
     * @param pCardNumber
     * * @param pId
     * @param pIdType
     
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.CardHolderValidationResponse
     */
    @WebMethod(operationName = "WMCardHolderValidation", action = "https://SistemasGalileo.com/Services/WMCardHolderValidation")
    @WebResult(name = "WMCardHolderValidationResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMCardHolderValidation", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardHolderValidation")
    @ResponseWrapper(localName = "WMCardHolderValidationResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCardHolderValidationResponse")
    public CardHolderValidationResponse wmCardHolderValidation(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pCardNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCardNumber,
        @WebParam(name = "pId", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pId,
        @WebParam(name = "pIdType", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pIdType);

    /**
     * This method allows to test the conectivity.
     * 
     * @param pRequestID
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.EchoResponse
     */
    @WebMethod(operationName = "WMEcho", action = "https://SistemasGalileo.com/Services/WMEcho")
    @WebResult(name = "WMEchoResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMEcho", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMEcho")
    @ResponseWrapper(localName = "WMEchoResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMEchoResponse")
    public EchoResponse wmEcho(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID);

    /**
     * This method allows to obtain the last transactions for a given card. The number of transactions can be specified. Additionally, it provides the starting and final balance (before and after the listed transactions).
     * 
     * @param pTransactionQuantity
     * @param pStartDate
     * @param pCardNumber
     * @param pEndDate
     * @param pRequestID
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.LastTransactionsResponse
     */
    @WebMethod(operationName = "WMLastTransactions", action = "https://SistemasGalileo.com/Services/WMLastTransactions")
    @WebResult(name = "WMLastTransactionsResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMLastTransactions", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMLastTransactions")
    @ResponseWrapper(localName = "WMLastTransactionsResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMLastTransactionsResponse")
    public LastTransactionsResponse wmLastTransactions(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pCardNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCardNumber,
        @WebParam(name = "pStartDate", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pStartDate,
        @WebParam(name = "pEndDate", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pEndDate,
        @WebParam(name = "pTransactionQuantity", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pTransactionQuantity);

    /**
     * This method allows to load funds into a given card from a virtual terminal.
     * 
     * @param pTransAmount
     * @param pCardNumber
     * @param pTerminalCode
     * @param pRequestID
     * @return
     *     returns com.smartbt.vtsuite.boundary.client.CashToCardResponse
     */
    @WebMethod(operationName = "WMCashToCard", action = "https://SistemasGalileo.com/Services/WMCashToCard")
    @WebResult(name = "WMCashToCardResult", targetNamespace = "https://SistemasGalileo.com/Services/")
    @RequestWrapper(localName = "WMCashToCard", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCashToCard")
    @ResponseWrapper(localName = "WMCashToCardResponse", targetNamespace = "https://SistemasGalileo.com/Services/", className = "com.smartbt.vtsuite.boundary.client.WMCashToCardResponse")
    public CashToCardResponse wmCashToCard(
        @WebParam(name = "pRequestID", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pRequestID,
        @WebParam(name = "pTerminalCode", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pTerminalCode,
        @WebParam(name = "pCardNumber", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pCardNumber,
        @WebParam(name = "pTransAmount", targetNamespace = "https://SistemasGalileo.com/Services/")
        String pTransAmount);

}