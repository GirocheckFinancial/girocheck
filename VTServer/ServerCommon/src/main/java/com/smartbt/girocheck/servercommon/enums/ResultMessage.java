
package com.smartbt.girocheck.servercommon.enums;

/**
 *
 * @author Roberto
 */


public enum ResultMessage {
    SUCCESS("Success","Success."),
    FAILED("Failed","Transaction Failed. Please Contact Customer Support."),
    
    HOST_RECEIVED_NULL("Host received null message from Core.","Transaction Failed. Please Contact Customer Support."),
    
    CORE_RECEIVED_NULL("Core received null message from Front.","Transaction Failed. Please Contact Customer Support."),
    
    CARDTOBANK_CORE_CLIENT_MISSING("Core cardToBank client is missing.","Transaction Failed: Card is not associated to a Cardholder."),
    
    CARDTOBANK_CORE_MERCHANT_MISSING("Core cardToBank merchant is missing.","Transaction Failed: Merchant is not associated to the terminal."),
   
    CARDTOBANK_CORE_ACH_MISSING("Core cardToBank ACH came null from the terminal.","Transaction Failed: No ACH Form Image Received. Please Try the Transaction Again."),
    
    FRONT_RECEIVED_NULL("Front received null message from Core.","Transaction Failed. Please Contact Customer Support."),
    
    CORE_RECEIVED_NULL_FROM_HOST("Core received null message from Host.","Transaction Failed. Please Contact Customer Support."),
    
    ISTREAM_RETURN_CHECK_ID_NULL("IStream CheckAuth transaction return checkId null.","Transaction Failed. Please Contact Customer Support."),
    
    CORE_RECEIVED_NULL_FROM_IStreamFront("Core received null from IStream Front.","Transaction Failed. Please Contact Customer Support."),
    
//    PERSONAL_INFO_NOT_RECEIVED("Personal info not received from IStream within stablished time.","System error"),
    
    TECNICARD_WRONG_ID_OPERATION("Codes 01 or 02 expected in the parameter OPERATION.","Transaction Failed. Please Contact Customer Support."), 
    
    REQUEST_ID_REQUIRED("Field requestId is required.","Transaction Info Missing. Please Try Again."), 
    
    TERMINAL_ID_REQUIRED("Field terminalId is required.","Transaction Info Missing. Please Try Again."), 
    
    USER_REQUIRED("Field user is required.","Username Missing. Please Contact Customer Support."), 
    
    PASSWORD_REQUIRED("Field password is required.","Password Missing. Please Contact Customer Support."), 
    
    SSN_REQUIRED("Field SSN is required.","SSN Info Missing. Please Try Again."), 
    
    TECNICARD_FAILED("Tecnicard Host failed.","Transaction Cancelled. Please contact Customer Support."), 
    TECNICARD_PERSONALIZATION_FAILED("Tecnicard Personalization failed code: 1360320.","Customer Not Elegible to Activate a VoltCash Card. For Further Info please contact Customer Support."), 
    TECNICARD_PERSONALIZATION_INFO_FAILED("Tecnicard Personalization failed code: 136053.","Customer Not Elegible to Activate a VoltCash Card. For Further Info please contact Customer Support."), 
    
    RESPONSE_TIME_EXCEEDED("Response time exceeded.","Transaction Timed Out. Please Contact Customer Support."), 
    
//    CERTEGY_INFO_NOT_RECEIVED("Certegy Info not received.","System error"),
    
    CERTEGY_DENY("Certegy denied.","Transaction Declined. Please provide decline card to customer."),
    
    ORDER_EXPRESS_FAILED("OrderExpress failed.","Transaction declined. OFAC Validation Failed."),
    
    TERMINAL_CONFIRMATION_TIME_EXCEED("Terminal has exceed the time for TecnicardConfirmation.","Check Truncation Failed. Please Contact Customer Support."),
    
    TERMINAL_WRONG_AMMOUNT_FORMAT("Wrong format for amount. N(2,2) expected.","Transaction Failed. Please Contact Customer Support."),
    TERMINAL_WRONG_AMMOUNT("Terminal's amount doesn't match check's amount.","Terminal's amount doesn't match check's amount."),
    
    TERMINAL_CANCELATED_TRANSACTION("Terminal cancelled transaction.","Transaction Failed. Please Contact Customer Support."),
      
    ISTREAM_FAILED("Istream failed.","Transaction Failed. Please Contact Customer Support."),
    
    LOGIN_FAILED("Login failed.","Login Failed. Please Contact Customer Support."),
    
    //TRANSACTION_NOT_FOUND("Transaction not found.","System error"),
    
    ISTREAM_FRONT_PERSONAL_INFO_RECEIVED_AS_NULL("Personal Info not received from IStream.","Transaction Failed. Please Contact Customer Support."),
    
    ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED("Certegy Info not received.","Transaction Failed. Please Contact Customer Support."),
    
    CARD_UNAUTHORIZED_BY_MIDDLEWARE("Credit card unauthorized by middleware. Please use a VoltCash Card","Please use a VoltCash Card"),
    
    FUZE_STATUS_NULL("Fuze host return status NULL.","Card Manager Comm Failed."),
    
    FUZE_HOST_FAILED("Fuze host failed.","Transaction Failed. Please Contact Customer Support."), 
    
    ISTREAM_CANCELLED_TRANSACTION("Transaction cancelled by Istream. Ineligible","Transaction Declined. Please Contact Customer Support."),
    
    CREDIT_CARD_NOT_EXIST("CreditCard doesn't exist.","Transaction Data Incomplete. Please Try Again."),
    
    TERMINAL_ID_NOT_EXIST("Terminal id doesn't exist.","Terminal Not Active: Please contact Customer Support."),
    
    GENERIC_RESULT_MESSAGE("",""),
    
    OE_LOG_TIME_OUT("OE Log Time Out","Transaction Declined by OE Time Out. Please try again in 5 minutes."),
    
    CARD_RELOAD_DATA_CANCELED("No client data to execute the reload data. Full Data Card Reload needed.","Transaction Declined. Common Card Reload Needed."),
    CLIENT_IN_CARD2BANK_BLACKLIST("Client in Card2Bank Black List.","Client in Card2Bank Black List."),
    
     /*****************************Tecnicard Codes START**********************************/
    
    TC_000000("000000 Everything Successfully.","Success."),//success
    TC_000031("000031 Card does not exist.","Invalid Card: Please Call Customer Support."),//fail
    TC_000122("Embossed Card, not ready to activate.",""),//
    TC_000199("Max allowed balance exceeded.","Balance Limit reached: Please Contact Customer Support."),//
    TC_100011("Non personalized card is ready to be activated.","New Card Load available."),//
    TC_100012("Personalized card is ready to be activated.","Card Reload available."),//
    TC_100013("The current card belongs to the specified id.","Success."),//
    TC_100014("The current card belongs to a different id.","Invalid ID: Please check ID Number and Try again."),//
    TC_100015("Temporary block.","Success."),//
    TC_100016("Card blocked.","Card Blocked: Please contact Customer Support."),//
    TC_100017("Card was already replaced.","Transaction Cancelled: Please contact customer support."),//
    
     /*****************************Tecnicard Codes END**********************************/
    
    
    /*****************************OECodes START**********************************/
    
    OE_LOG_201("OE Log 201 Transaction Declined by O.E Compliance","Transaction Declined: Please Contact Customer Support."),
    OE_LOG_202("OE Log 202 Transaction Declined by O.E Compliance","Transaction Declined: Please Contact Customer Support."),
    OE_LOG_205("OE Log 205 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_206("OE Log 206 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_207("OE Log 207 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_208("OE Log 208 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_210("OE Log 210 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_211("OE Log 211 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_212("OE Log 212 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_213("OE Log 213 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_214("OE Log 214 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_215("OE Log 215 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_216("OE Log 216 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_217("OE Log 217 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_218("OE Log 218 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_219("OE Log 219 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_220("OE Log 220 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_221("OE Log 221 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_222("OE Log 222 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_223("OE Log 223 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_225("OE Log 225 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_226("OE Log 226 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_227("OE Log 227 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_228("OE Log 228 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_230("OE Log 230 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_231("OE Log 231 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_232("OE Log 232 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_233("OE Log 233 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_235("OE Log 235 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_236("OE Log 236 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_237("OE Log 237 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_238("OE Log 238 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_240("OE Log 240 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_241("OE Log 241 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_242("OE Log 242 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_243("OE Log 243 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_244("OE Log 244 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_245("OE Log 245 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_246("OE Log 246 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_247("OE Log 247 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_248("OE Log 248 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_249("OE Log 249 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_250("OE Log 250 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_251("OE Log 251 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_252("OE Log 252 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_253("OE Log 253 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_254("OE Log 254 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_255("OE Log 255 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_256("OE Log 256 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_257("OE Log 257 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_258("OE Log 258 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_259("OE Log 259 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_260("OE Log 260 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_261("OE Log 261 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_262("OE Log 262 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_263("OE Log 263 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_264("OE Log 264 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_265("OE Log 265 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_266("OE Log 266 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_267("OE Log 267 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_268("OE Log 268 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_269("OE Log 269 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_270("OE Log 270 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_271("OE Log 271 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_272("OE Log 272 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_273("OE Log 273 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_274("OE Log 274 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_275("OE Log 275 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_276("OE Log 276 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_277("OE Log 277 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_280("OE Log 280 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_281("OE Log 281 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support."),
    OE_LOG_282("OE Log 282 Transaction Declined by O.E Compliance","Transaction Declined. Please Contact Customer Support.");
    
    /*********************************END***************************************/
    
    
    private String message;
    
    private String terminalMessage;

//    private ResultMessage(String message) {
//        this.message = message;
//    }
    
    private ResultMessage(String message,String terminalMessage) {
        this.message = message;
        this.terminalMessage = terminalMessage;
    }

    public String getMessage() {
        return message;
    }
    
    public String getTerminalMessage() {
        return terminalMessage;
    }    

    /**
     * @param message the message to set
     * @param terminalMessage
     */
    public void setMessage(String message, String terminalMessage) {
        this.message = message;
        this.terminalMessage = terminalMessage;
    }
  
}
