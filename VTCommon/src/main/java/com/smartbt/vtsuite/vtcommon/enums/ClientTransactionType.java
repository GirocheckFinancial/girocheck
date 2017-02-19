package com.smartbt.vtsuite.vtcommon.enums;

import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 *
 * @author Roberto
 */
public class ClientTransactionType {

    public static String getTransactionName( int code ) {
        switch ( code ) {
            case 1:
                return "New Card Load";
            case 2:
                return "Card Reload";
            case 3:
                return "Personal Info";
            case 4:
                return "Certegy Info";
            case 5:
                return "Location Config";
            case 6:
                return "Check Auth";
            case 7:
                return "Check Auth Poll";
            case 8:
                return "Check Auth Submit";
            case 9:
                return "Check Auth Poll";
            case 10:
                return "Enhanced Check Auth Poll";
            case 11:
                return "Check Auth Submit";
            case 12:
                return "Contrataciones";
            case 14:
                return "Card Activation";
            case 15:
                return "Card Personalization";
            case 16:
                return "Card Validation";
            case 17:
                return "Card Load";
            case 18:
                return "Balance Inquiry";
            case 19:
                return "Card to Bank";
            case 20:
                return "Card Holder Validation";
            case 21:
                return "Echo";
            case 22:
                return "Last Transactions";
            case 23:
                return "Cash to Card";
            case 24:
                return "Tecnicard Confirmation";
            case 25:
                return "Tecnicard to Bank Confirmation";
            case 26: 
                return "Generic Host Validation";
            case 27: 
                return "Generic Host Card Load";
            case 28: 
                return "Fuze Biller Search";
            case 29: 
                return "Fuze Process Payment";                
            case 30: 
                return "Fuze Lookup Transaction"; 
            case 31:
                return "Check Auth Location Config";
            case 32: 
                return "OE Devolucion";                
            case 33: 
                return "OE Reporta Pago";
            case 34: 
                return "OE Logs";
             case 35: 
                return "Card Reload Data";     
            case 36: 
                return "Choice Insert Transaction";
            case 37: 
                return "Choice Cancelation Request";
            case 38: 
                return "Choice Notify Payment";
            case 39: 
                return "Westech CheckAuth";
            case 40: 
                return "Certegy Auth";
            case 41: 
                return "Certegy Reverse";
            case 42: 
                return "iStream Send Single ICL";
           
            default:
                return "Unknown";
        }
    }
}
