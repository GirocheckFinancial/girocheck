package com.smartbt.girocheck.servercommon.enums;

import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 *
 * @author Roberto
 */
public enum TransactionType {

    TRANSACTION_TYPE( null, 0 ),
    NEW_CARD_LOAD( NomHost.TECNICARD, 1 ),
    CARD_RELOAD( NomHost.TECNICARD, 2 ),
    CARD_RELOAD_WITH_DATA( NomHost.TECNICARD, 35 ),
    PERSONAL_INFO( null, 3 ),
    CERTEGY_INFO( null, 4 ),
    ISTREAM_CHECK_AUTH_LOCATION_CONFIG( NomHost.ISTREAM, 5 ),
    ISTREAM_CHECK_AUTH( NomHost.ISTREAM, 6 ),
    ISTREAM_ENHANCED_CASH_AUTH_POLL( NomHost.ISTREAM, 7 ),
    ISTREAM_CHECK_AUTH_SUBMIT( NomHost.ISTREAM, 8 ),
    ISTREAM_CHECK_AUTH_POLL( NomHost.ISTREAM, 9 ),
    ISTREAM_ENHANCED_CHECK_AUTH_POLL( NomHost.ISTREAM, 10 ),
    ISTREAM_CASH_AUTH_SUBMIT( NomHost.ISTREAM, 11 ),
    ORDER_EXPRESS_CONTRATACIONES( NomHost.ORDER_EXPRESS, 12 ),
    TECNICARD_CARD_LOAD_OR_CASH_TO_CARD( NomHost.TECNICARD, 13 ),
    TECNICARD_CARD_ACTIVATION( NomHost.TECNICARD, 14 ),
    TECNICARD_CARD_PERSONALIZATION( NomHost.TECNICARD, 15 ),
    TECNICARD_CARD_VALIDATION( NomHost.TECNICARD, 16 ),
    TECNICARD_CARD_LOAD( NomHost.TECNICARD, 17 ),
    TECNICARD_BALANCE_INQUIRY( NomHost.TECNICARD, 18 ),
    TECNICARD_CARD_TO_BANK( NomHost.TECNICARD, 19 ),
    TECNICARD_CARD_TO_BANK_CONFIRMATION( NomHost.TECNICARD, 25 ),
    TECNICARD_CARD_HOLDER_VALIDATION( NomHost.TECNICARD, 20 ),
    TECNICARD_ECHO( NomHost.TECNICARD, 21 ),
    TECNICARD_LAST_TRANSACTIONS( NomHost.TECNICARD, 22 ),
    TECNICARD_CASH_TO_CARD( NomHost.TECNICARD, 23 ),
    TECNICARD_CONFIRMATION( null, 24 ),
    
    GENERIC_HOST_VALIDATION( null, 26 ),
    GENERIC_HOST_CARD_LOAD( null, 27 ),
    
    FUZE_BILLER_SEARCH(NomHost.FUZE, 28),
    FUZE_PROCESS_PAYMENT(NomHost.FUZE, 29), 
    FUZE_LOOKUP_TRANSACTION(NomHost.FUZE, 30),
//    FUZE_HOST_LOOKUP_TRANSACTION(null, 31), 
    CHECK_AUTH_LOCATION_CONFIG(NomHost.ISTREAM, 31),
    ORDER_EXPRESS_DEVOLUCION( NomHost.ORDER_EXPRESS, 32 ),
    ORDER_EXPRESS_REPORTAPAGO( NomHost.ORDER_EXPRESS, 33 ),
    ORDER_EXPRESS_LOGS( NomHost.ORDER_EXPRESS, 34 ),
    
    ACTIVITY_REPORT(null, 35 ), //no transactional
    
    CHOICE_INSERT_TRANSACTION(NomHost.CHOICE, 36 ),
    CHOICE_CANCELATION_REQUEST(NomHost.CHOICE, 37 ),  
    CHOICE_NOTIFY_PAYMENT(NomHost.CHOICE, 38 ), 
    
    WESTECH_CHECK_PROCESS(NomHost.WESTECH, 39 ),
    
    CERTEGY_AUTHENTICATION(NomHost.CERTEGY, 40),
    CERTEGY_REVERSE_REQUEST(NomHost.CERTEGY, 41),
    
    ISTREAM2_SEND_SINGLE_ICL(NomHost.ISTREAM2, 42),
    CHECK_INFO(null, 43);

    private NomHost host;

    private int code;

    private TransactionType( NomHost host, int code ) {
        this.host = host;
        this.code = code;
    }

    public NomHost getHost() {
        return host;
    }

    public int getCode() {
        return code;
    }

    public static TransactionType get( int code ) {
        switch ( code ) {
            case 0:
                return TransactionType.TRANSACTION_TYPE;
            case 1:
                return TransactionType.NEW_CARD_LOAD;
            case 2:
                return TransactionType.CARD_RELOAD;
            case 3:
                return TransactionType.PERSONAL_INFO;
            case 4:
                return TransactionType.CERTEGY_INFO;
            case 5:
                return TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG;
            case 6:
                return TransactionType.ISTREAM_CHECK_AUTH;
            case 7:
                return TransactionType.ISTREAM_ENHANCED_CASH_AUTH_POLL;
            case 8:
                return TransactionType.ISTREAM_CHECK_AUTH_SUBMIT;
            case 9:
                return TransactionType.ISTREAM_CHECK_AUTH_POLL;
            case 10:
                return TransactionType.ISTREAM_ENHANCED_CHECK_AUTH_POLL;
            case 11:
                return TransactionType.ISTREAM_CASH_AUTH_SUBMIT;
            case 12:
                return TransactionType.ORDER_EXPRESS_CONTRATACIONES;
            case 13:
                return TransactionType.TECNICARD_CARD_LOAD_OR_CASH_TO_CARD;
            case 14:
                return TransactionType.TECNICARD_CARD_ACTIVATION;
            case 15:
                return TransactionType.TECNICARD_CARD_PERSONALIZATION;
            case 16:
                return TransactionType.TECNICARD_CARD_VALIDATION;
            case 17:
                return TransactionType.TECNICARD_CARD_LOAD;
            case 18:
                return TransactionType.TECNICARD_BALANCE_INQUIRY;
            case 19:
                return TransactionType.TECNICARD_CARD_TO_BANK;            
            case 20:
                return TransactionType.TECNICARD_CARD_HOLDER_VALIDATION;
            case 21:
                return TransactionType.TECNICARD_ECHO;
            case 22:
                return TransactionType.TECNICARD_LAST_TRANSACTIONS;
            case 23:
                return TransactionType.TECNICARD_CASH_TO_CARD;
            case 24:
                return TransactionType.TECNICARD_CONFIRMATION;
            case 25:
                return TransactionType.TECNICARD_CARD_TO_BANK_CONFIRMATION;
            case 26: 
                return TransactionType.GENERIC_HOST_VALIDATION;
            case 27: 
                return TransactionType.GENERIC_HOST_CARD_LOAD;
            case 28: 
                return TransactionType.FUZE_BILLER_SEARCH;
            case 29: 
                return TransactionType.FUZE_PROCESS_PAYMENT;
            case 30: 
                return TransactionType.FUZE_LOOKUP_TRANSACTION;
//            case 31: 
//                return TransactionType.FUZE_HOST_LOOKUP_TRANSACTION;
            case 31: 
                return TransactionType.CHECK_AUTH_LOCATION_CONFIG;
            case 32: 
                return TransactionType.ORDER_EXPRESS_DEVOLUCION;
            case 33: 
                return TransactionType.ORDER_EXPRESS_REPORTAPAGO;
            case 34: 
                return TransactionType.ORDER_EXPRESS_LOGS;
            case 35: 
                return TransactionType.CARD_RELOAD_WITH_DATA;
            case 36: 
                return TransactionType.CHOICE_INSERT_TRANSACTION;
            case 37: 
                return TransactionType.CHOICE_CANCELATION_REQUEST;
            case 38: 
                return TransactionType.CHOICE_NOTIFY_PAYMENT;
            case 39: 
                return TransactionType.WESTECH_CHECK_PROCESS;
            case 40: 
                return TransactionType.CERTEGY_AUTHENTICATION;
            case 41: 
                return TransactionType.CERTEGY_REVERSE_REQUEST;
            case 42: 
                return TransactionType.ISTREAM2_SEND_SINGLE_ICL;
            default:
                  return TransactionType.TRANSACTION_TYPE;
        }
    }

    } 