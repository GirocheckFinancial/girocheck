package com.smartbt.vtsuite.vtreporting;

import com.eclipsesource.json.*;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.vtsuite.vtreporting.totals.MerchantTotals;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class JSON2XMLParser {

    /**
     * The report header
     */
    protected static String HEADER = "", FOOTER = "";

    protected static String HEADERMerchant = "", FOOTERMerchant = "";

    protected static String HEADERTerminal = "", FOOTERTerminal = "";

    static BufferedReader readJson;

    /**
     * The xml that describes the transaction
     */
    protected static String transactionXml = "",
            strMerchantXml = "", strTerminalXml = "", entityId = "";

    /**
     * The xml that describes the merchant
     */
    protected static Set<String> merchantXml,
            /**
             * The xml that describes the terminal
             */
            terminalXml,
            //            /**
            //             * The xml that describes the clerk
            //             */
            //            clerkXml,
            /**
             * The xml that describes the client
             */
            clientXml,
            /**
             * The xml that describes the general attributes
             */
            generalXml;

    private static final Double totalMerchantAmount = 0.0;
    private static ArrayList<MerchantTotals> merchantTotalsList;

    /**
     * Converts from json to xml.
     *
     * @param jsonData
     * @return xmlData
     * @throws Exception
     */
    public static String fromJsonToXml(String jsonData, String header, String footer, String fromDate, String toDate, String reportType) throws Exception {
 
        JsonArray jsonArray = JsonArray.readFrom(jsonData);
        String response = "";

        merchantTotalsList = new ArrayList<>();

        switch (reportType) {
            case "DetailsListing":
            case "Details":
                HEADER = "<?xml version=\"1.0\"?><Report xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"TransactionFK.xsd\">";
                FOOTER = "</Report>";
                response = HEADER + parseJSONTransactionArray(jsonArray, header, footer, fromDate, toDate) + FOOTER;
                break;
            case "Grouping":
                HEADERMerchant = "<?xml version=\"1.0\"?><Report xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"MerchantFK.xsd\">";
                FOOTERMerchant = "</Report>";
                response = HEADERMerchant + parseJSONMerchantArray(jsonArray, header, footer, fromDate, toDate) + FOOTERMerchant;
                break;
            case "Merchant":
                HEADERTerminal = "<?xml version=\"1.0\"?><Report xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"TerminalFK.xsd\">";
                FOOTERTerminal = "</Report>";
                response = HEADERTerminal + parseJSONTerminalArray(jsonArray, header, footer, fromDate, toDate) + FOOTERTerminal;
                break;

        }

        return response;

    }

    /**
     * Builds the xml for entities
     */
    protected static void initEntities() {
        merchantXml = new HashSet<>();
        terminalXml = new HashSet<>();
//        clerkXml = new HashSet<>();
        clientXml = new HashSet<>();
        transactionXml = "";

        merchantXml.add(tag("Merchant", tag("id", "0") /*+ nullTag("number")*/
                + nullTag("legalName") + nullTag("description") /*+ nullTag("acitve") + nullTag("monitor")
                 + nullTag("parameter")*/ + nullTag("sic") /*+ nullTag("activationDate") + nullTag("deactivationDate")*/
                + nullTag("address") + nullTag("phone") + nullTag("zipcode") + nullTag("state") + nullTag("city")
                + nullTag("idPosOrderExp") + nullTag("idTellerOrderExp") + nullTag("oEAgentNumber") + nullTag("customerName")));

        terminalXml.add(tag("Terminal", tag("id", "0") + /*nullTag("terminalId")
                 + */ nullTag("serialNumber") + nullTag("description")
                /*+ nullTag("active") + nullTag("parameter") + nullTag("activationDate") + nullTag("deactivationDate")
                 + nullTag("customerName") */ + nullTag("merchantName")));

//        clerkXml.add(tag("Clerk", tag("id", "0") + nullTag("role")
//                + nullTag("username") + nullTag("lastName") + nullTag("firstName")
//                + nullTag("active") + nullTag("password")));
        clientXml.add(tag("Client", tag("id", "0") + nullTag("first") + nullTag("last") + nullTag("phone")));
    }

    /**
     * Generates a string will all entities information.
     *
     * @param merchantTotalsList
     * @return entities string
     */
    protected static String concatEntities(ArrayList<MerchantTotals> merchantTotalsList) {
        String entityString = "";

        for (String enstr : merchantXml) {
//            enstr = getValueTag(enstr, "Merchant");
//            String idMerchant = getValueTag(enstr, "id");
//            for (MerchantTotals merchantTotals : merchantTotalsList) {
//                if (idMerchant.equals(merchantTotals.getId().toString())) {
//                    enstr = enstr.concat(tag("totalMerchant", merchantTotals.getAmount().toString()));
//                } else {
//                    enstr += nullTag("totalMerchant");
//                }
//            }
//            enstr = tag("Merchant", enstr);
            entityString += enstr;
        }
        for (String enstr : terminalXml) {
            entityString += enstr;
        }
//        for (String enstr : clerkXml) {
//            entityString += enstr;
//        }
        for (String enstr : clientXml) {
            entityString += enstr;
        }

        return entityString;
    }

    /**
     *
     * @param jsonArray
     * @param header
     * @param footer
     * @param fromDate
     * @param toDate
     * @return
     */
    protected static String parseJSONTransactionArray(JsonArray jsonArray, String header, String footer, String fromDate, String toDate) throws java.text.ParseException {
        try {
            generalXml = new HashSet<>();
            initEntities();

            for (JsonValue jsonValue : jsonArray) {
                if (jsonValue.isObject()) {
                    //  transactionXml += tag("Transaction", parseJSONTransaction(jsonValue.asObject(), secundaryTxInfoList));
                    transactionXml += tag("Transaction", parseJSONTransaction(jsonValue.asObject()));
                }
            }
            transactionXml = transactionXml + concatEntities(merchantTotalsList);
            generalXml.add(tag("General", tag("header", header) + tag("footer", footer) + tag("fromDate", fromDate) + tag("toDate", toDate)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return generalXml + transactionXml;
    }

    protected static String parseJSONMerchantArray(JsonArray jsonArray, String header, String footer, String fromDate, String toDate) throws java.text.ParseException {
        try {
            generalXml = new HashSet<>();
            strMerchantXml = "";

            for (JsonValue jsonValue : jsonArray) {
                if (jsonValue.isObject()) {
                    strMerchantXml += tag("Merchant", parseJSONMerchantEntity(jsonValue.asObject()));
                }
            }
//        strMerchantXml = strMerchantXml + concatEntities(merchantTotalsList);
            generalXml.add(tag("General", tag("header", header) + tag("footer", footer) + tag("fromDate", fromDate) + tag("toDate", toDate)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return generalXml + strMerchantXml;
    }

    protected static String parseJSONTerminalArray(JsonArray jsonArray, String header, String footer, String fromDate, String toDate) throws java.text.ParseException {
        try {
            generalXml = new HashSet<>();
            strTerminalXml = "";

            for (JsonValue jsonValue : jsonArray) {
                if (jsonValue.isObject()) {
                    strTerminalXml += tag("Terminal", parseJSONTerminalEntity(jsonValue.asObject()));
                }
            }
            generalXml.add(tag("General", tag("header", header) + tag("footer", footer) + tag("fromDate", fromDate) + tag("toDate", toDate)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return generalXml + strTerminalXml;
    }

    /**
     * Gets xmlTransaction from JSONTransaction
     *
     * @param jsonObject
     * @return transactionData
     */
    // protected static String parseJSONTransaction(JsonObject jsonObject, ArrayList<Object[]> originalTxInfoList) {
    protected static String parseJSONTransaction(JsonObject jsonObject) {
 
        String transactionData = "";

        transactionData += tagNum("id", jsonObject.get("id"));
 
        transactionData += tag("clientFirstName", jsonObject.get("clientFirstName"));
        transactionData += tag("clientLastName", jsonObject.get("clientLastName"));
        transactionData += tag("operation", jsonObject.get("operation"));
    
        transactionData += tag("requestId", jsonObject.get("requestId"));
        transactionData += tag("maskCardNumber", jsonObject.get("maskCardNumber"));
        transactionData += tagNum("payoutAmount", jsonObject.get("payoutAmount"));
        transactionData += tagNum("feeAmount", jsonObject.get("feeAmount"));
        transactionData += tagNum("amount", jsonObject.get("amount"));
        transactionData += parseJSONType(jsonObject);
//        transactionData += tagNum("type", jsonObject.get("type"));
        transactionData += tag("checkNumber", jsonObject.get("checkNumber"));
        transactionData += tag("makerName", jsonObject.get("makerName"));
        transactionData += tagNum("resultCode", jsonObject.get("resultCode"));
        transactionData += tag("resultMessage", jsonObject.get("resultMessage"));
        transactionData += tagDateTime("dateTime", jsonObject.get("dateTime"));
         
         transactionData += tag("idPosOrderExp", jsonObject.get("idPosOrderExp"));
         transactionData += tag("terminalSerial",  jsonObject.get("terminalSerial"));
         transactionData += tag("merchantName", jsonObject.get("merchantName"));
         transactionData += tag("idTellerOrderExp", jsonObject.get("idTellerOrderExp"));
         transactionData += tag("clientPhone", jsonObject.get("clientPhone"));
         transactionData += tag("oEAgentNumber", jsonObject.get("oEAgentNumber"));
        
        return transactionData;
    }

    public static String parseJSONType(JsonObject jsonObject) {
        JsonValue typeJSONValue = jsonObject.get("transactionType");

        String type = typeJSONValue == null ? "" : typeJSONValue.toString();

        if (!type.isEmpty()) {
            Integer typeInt = 0;
            try {
                typeInt = Integer.parseInt(type);
                TransactionType transactionType = TransactionType.get(typeInt);

                if (transactionType != null && transactionType != TransactionType.TRANSACTION_TYPE) {
                    type = transactionType.toString().replaceAll("TECNICARD_", "").replaceAll("_", " ");
                }
            } catch (NumberFormatException e) {
            }
        }

        return tag("type", type.trim());
    }

//    public static String parseJSONOperation(JsonObject jsonObject) {
//        JsonValue op = jsonObject.get("operation");
//
//        String operation = op == null ? "" : jsonObject.get("operation").toString();
//
//        String operationResult = "";
//      
//        if (operation != null) {
//            if (operation.trim().contains("01")) {
//                operationResult = "Check";
//            } else {
//                if (operation.trim().contains("02")) {
//                    operationResult = "Cash";
//                }
//            }
//        }
// 
//        return tag("operation", operationResult);
//    }

    /**
     * Gets XMLMerchant from JSONMerchant
     *
     * @param jsonMerchant
     * @param jsonTransaction
     * @param originalTxInfoList
     * @param jsonValue
     * @return merchantData
     */
    //protected static String parseJSONMerchant(JsonValue jsonMerchant, JsonValue jsonTransaction, ArrayList<Object[]> secundaryTxInfoList) {
    protected static String parseJSONMerchant(JsonValue jsonMerchant) {
        String id = "0", xmlData = "";

        if (jsonMerchant == null || jsonMerchant.isNull() || jsonMerchant.asObject().get("id") == null || jsonMerchant.asObject().get("id").isNull()) {
            return id;
        }

        JsonObject jsonObject = jsonMerchant.asObject();
        id = parseLiteral(jsonObject.get("id"));

//        Object[] merchantInfo = existCustomerList(merchantTotalsList, Long.valueOf(id));
//        MerchantTotals merchantTotal = new MerchantTotals();
//        Long idOriginalTransaction = jsonTransaction.asObject().get("id").asLong();
//        String operationOriginalTransaction = jsonTransaction.asObject().get("operation").asString();
//        String operationSecundaryTx = findModeSecundaryTransaction(idOriginalTransaction, secundaryTxInfoList);
//
//              
//        //This is first time we get merchant
//        if (merchantInfo[0] == Boolean.FALSE) {
//
//            merchantTotal.setId(Long.valueOf(id));
//            merchantTotal.setAmount(new Double (0.0));
//
//            if (operationOriginalTransaction.equalsIgnoreCase("Sale")) {
//                //sale does not have refund nor void -- we add amount
//                if (operationSecundaryTx.equalsIgnoreCase("")) {
//                    merchantTotal.setAmount(merchantTotal.getAmount() + Double.valueOf(jsonTransaction.asObject().get("totalAmount").asString()));
//                }
//            } else if (operationOriginalTransaction.equalsIgnoreCase("Refund")) {
//                // refund does not have secundary transaction -- we rest amount
//                if (operationSecundaryTx.equalsIgnoreCase("")) {
//                    merchantTotal.setAmount(merchantTotal.getAmount() - Double.valueOf(jsonTransaction.asObject().get("totalAmount").asString()));
//                } else // refund has been void  -- we sum amount
//                if (operationSecundaryTx.equalsIgnoreCase("Void")) {
//                    merchantTotal.setAmount(merchantTotal.getAmount() + Double.valueOf(jsonTransaction.asObject().get("totalAmount").asString()));
//                }
//            }
//            merchantTotalsList.add((Integer) merchantInfo[2], merchantTotal);
//
//        } // MerchantTotal already exists in the list, we update the total
//        else {
//            merchantTotal = (MerchantTotals) merchantInfo[1];
//
//            if (operationOriginalTransaction.equalsIgnoreCase("Sale")) {
//                //sale does not have refund nor void -- we add amount
//                if (operationSecundaryTx.equalsIgnoreCase("")) {
//                    merchantTotal.setAmount(merchantTotal.getAmount() + Double.valueOf(jsonTransaction.asObject().get("totalAmount").asString()));
//                }
//            } else if (operationOriginalTransaction.equalsIgnoreCase("Refund")) {
//                // refund does not have secundary transaction -- we rest amount
//                if (operationSecundaryTx.equalsIgnoreCase("")) {
//                    merchantTotal.setAmount(merchantTotal.getAmount() - Double.valueOf(jsonTransaction.asObject().get("totalAmount").asString()));
//                } else // refund has been void  -- we sum amount
//                if (operationSecundaryTx.equalsIgnoreCase("Void")) {
//                    merchantTotal.setAmount(merchantTotal.getAmount() + Double.valueOf(jsonTransaction.asObject().get("totalAmount").asString()));
//                }
//            }
//            merchantTotalsList.set((Integer) merchantInfo[2], merchantTotal);
//        }
        xmlData += tag("id", id);
//        xmlData += tag("number", jsonObject.get("number"));
        xmlData += tag("legalName", jsonObject.get("legalName"));
        xmlData += tag("description", jsonObject.get("description"));
//        xmlData += tag("active", jsonObject.get("active"));
//        xmlData += tag("monitor", jsonObject.get("monitor"));
//        xmlData += tag("parameter", jsonObject.get("parameter"));
        xmlData += tag("sic", jsonObject.get("sic"));
//        xmlData += tag("activationDate", jsonObject.get("activationDate"));
//        xmlData += tag("deactivationDate", jsonObject.get("deactivationDate"));
        xmlData += tag("address", jsonObject.get("address"));
        xmlData += tag("zipcode", jsonObject.get("zipcode"));
        xmlData += tag("state", jsonObject.get("state"));
        xmlData += tag("city", jsonObject.get("city"));
        xmlData += tag("phone", jsonObject.get("phone"));
        xmlData += tag("idPosOrderExp", jsonObject.get("idPosOrderExp"));
        xmlData += tag("idTellerOrderExp", jsonObject.get("idTellerOrderExp"));
        xmlData += tag("oEAgentNumber", jsonObject.get("oEAgentNumber"));
//        xmlData += tag("logoImage", jsonObject.get("logoImage"));
        xmlData += tag("customerName", jsonObject.get("customerName"));

        merchantXml.add(tag("Merchant", xmlData));

        return id;
    }

    protected static String parseJSONMerchantEntity(JsonValue jsonMerchant) {
        String id = "0", xmlData = "";

        if (jsonMerchant == null || jsonMerchant.isNull() || jsonMerchant.asObject().get("id") == null || jsonMerchant.asObject().get("id").isNull()) {
            return id;
        }

        JsonObject jsonObject = jsonMerchant.asObject();
        id = parseLiteral(jsonObject.get("id"));

        xmlData += tag("id", id);
        xmlData += tag("legalName", jsonObject.get("legalName"));
        xmlData += tag("description", jsonObject.get("description"));
        xmlData += tag("sic", jsonObject.get("sic"));
        xmlData += tag("address", jsonObject.get("address"));
        xmlData += tag("zipcode", jsonObject.get("zipcode"));
        xmlData += tag("state", jsonObject.get("state"));
        xmlData += tag("city", jsonObject.get("city"));
        xmlData += tag("phone", jsonObject.get("phone"));
        xmlData += tag("idPosOrderExp", jsonObject.get("idPosOrderExp"));
        xmlData += tag("idTellerOrderExp", jsonObject.get("idTellerOrderExp"));
        xmlData += tag("oEAgentNumber", jsonObject.get("oEAgentNumber"));
        xmlData += tag("customerName", jsonObject.get("customerName"));

//        merchantXml.add(tag("Merchant", xmlData));
        return xmlData;
    }

    /**
     * Gets XMLTerminal from JSONTerminal
     *
     * @param jsonValue
     * @return terminalData
     */
    protected static String parseJSONTerminal(JsonValue jsonValue) {
        String id = "0", xmlData = "";
        if (jsonValue == null || jsonValue.isNull() || jsonValue.asObject().get("id") == null || jsonValue.asObject().get("id").isNull()) {
            return id;
        }

        JsonObject jsonObject = jsonValue.asObject();

        id = parseLiteral(jsonObject.get("id"));

        xmlData += tag("id", id);
//        xmlData += tag("terminalId", jsonObject.get("terminalId"));
        xmlData += tag("serialNumber", jsonObject.get("serialNumber"));
        xmlData += tag("description", jsonObject.get("description"));
//        xmlData += tag("active", jsonObject.get("active"));
//        xmlData += tag("parameter", jsonObject.get("parameter"));
//        xmlData += tag("activationDate", jsonObject.get("activationDate"));
//        xmlData += tag("deactivationDate", jsonObject.get("deactivationDate"));
//        xmlData += tag("customerName", jsonObject.get("customerName"));
        xmlData += tag("merchantName", jsonObject.get("merchantName"));
//        xmlData += tag("productType", jsonObject.get("productType"));
//        xmlData += tag("application", jsonObject.get("application"));

        terminalXml.add(tag("Terminal", xmlData));

        return id;
    }

    protected static String parseJSONTerminalEntity(JsonValue jsonValue) {
        String id = "0", xmlData = "";
        if (jsonValue == null || jsonValue.isNull() || jsonValue.asObject().get("id") == null || jsonValue.asObject().get("id").isNull()) {
            return id;
        }

        JsonObject jsonObject = jsonValue.asObject();

        id = parseLiteral(jsonObject.get("id"));

        xmlData += tag("id", id);
        xmlData += tag("serialNumber", jsonObject.get("serialNumber"));
        xmlData += tag("description", jsonObject.get("description"));
        xmlData += tag("merchantName", jsonObject.get("merchantName"));

        return xmlData;
    }

    /**
     * Gets XMLClerk from JSONClerk
     *
     * @param jsonValue
     * @return clerkData
     */
//    protected static String parseJSONClerk(JsonValue jsonValue) {
//        String id = "0", xmlData = "";
//        if (jsonValue == null || jsonValue.isNull() || jsonValue.asObject().get("id") == null || jsonValue.asObject().get("id").isNull()) {
//            return id;
//        }
//
//        JsonObject jsonObject = jsonValue.asObject();
//
//        id = parseLiteral(jsonObject.get("id"));
//
//        xmlData += tag("id", id);
//        xmlData += tag("username", jsonObject.get("username"));
//        xmlData += tag("role", jsonObject.get("role"));
//        xmlData += tag("lastName", jsonObject.get("lastName"));
//        xmlData += tag("firstName", jsonObject.get("firstName"));
//        xmlData += tag("active", jsonObject.get("active"));
//        xmlData += tag("password", jsonObject.get("password"));
//
//        clerkXml.add(tag("Clerk", xmlData));
//
//        return id;
//    }
    /**
     * Gets XMLClient from JSONClient
     *
     * @param jsonValue
     * @return clientData
     */
    protected static String parseJSONClient(JsonValue jsonValue) {
        String id = "0", xmlData = "";
 
        JsonObject jsonObject = jsonValue.asObject();
 
        xmlData += tag("first", jsonObject.get("firstName"));
 
        xmlData += tag("last", jsonObject.get("lastName"));
 
        String clientXML = tag("Client", xmlData);
 
        clientXml.add(clientXML);

        return clientXML;
    }

    /**
     * Gets datatypes as String.
     *
     * @param value
     * @return value as String.
     */
    protected static String parseLiteral(JsonValue value) {
        String result = "";

        if (value.isString()) {
            result = value.asString();
        } else if (value.isBoolean() || value.isNumber()) {
            result = value.toString();
        }

        return result;
    }

    /**
     * Remove the tag specified with elementName
     *
     * @param elementName
     * @param xml
     * @return XML String
     */
    protected static String deleteHeaderTag(String xml, String elementName) {
        if (xml == null || elementName == null) {
            return "";
        } else {
            return xml.substring(elementName.length() + 3, xml.length() - (elementName.length() + 3));
        }
    }

    /**
     * Remove the tag specified with elementName
     *
     * @param elementName
     * @param xml
     * @return XML String
     */
    protected static String getValueTag(String xml, String elementName) {
        if (xml == null || elementName == null) {
            return "";
        } else {
            int start = xml.indexOf("<" + elementName + ">") + elementName.length() + 2;
            int end = xml.indexOf("</" + elementName + ">");
            return xml.substring(start, end);
        }
    }

    /**
     * Return XML format for a tag element
     *
     * @param elementName
     * @param elementValue
     * @return XML String
     */
    protected static String tag(String elementName, String elementValue) {
        if (elementValue == null || elementValue.isEmpty()) {
            return "\n<" + elementName + "/>";
        } else {
            return "\n<" + elementName + ">" + elementValue + "</" + elementName + ">";
        }
    }

    /**
     * Return XML format for a tag element
     *
     * @param elementName
     * @param elementValue
     * @return XML String
     */
    protected static String tag(String elementName, JsonValue elementValue) {
        String val = "";
        if (!(elementValue == null || elementValue.isNull())) {
            val = parseLiteral(elementValue);
        }

        return "\n<" + elementName + ">" + val + "</" + elementName + ">";

    }

//    protected static String tag(String elementName, JsonValue elementValue) {
//        if (elementValue == null || elementValue.isNull()) {
//            return "\n<" + elementName + "/>";
//        } else {
//            return "\n<" + elementName + ">" + parseLiteral(elementValue) + "</" + elementName + ">";
//        }
//    }
    /**
     * Return XML format for a tag element
     *
     * @param elementName
     * @param elementValue
     * @return XML String
     */
    protected static String tagNum(String elementName, JsonValue elementValue) {
        if (elementValue == null || elementValue.isNull()) {
            return "<" + elementName + ">" + "0" + "</" + elementName + ">";
        } else {
            return "<" + elementName + ">" + parseLiteral(elementValue) + "</" + elementName + ">";
        }
    }

    /**
     * Return XML format for a tag element
     *
     * @param elementName
     * @param elementValue
     * @return XML String
     */
    protected static String tagDateTime(String elementName, JsonValue elementValue) {
        if (elementValue == null || elementValue.isNull()) {
            return "<" + elementName + ">" + "0" + "</" + elementName + ">";
        } else {
            return "<" + elementName + ">" + formatDate(parseLiteral(elementValue)) + "</" + elementName + ">";
        }
    }

    /**
     * Return Date in format MM-DD-YYYY HH:mm:SS
     *
     * @param longDateTime String
     * @return DateFormatted String
     */
    protected static String formatDate(String longDateTime) {
        Date date = new Date(Long.valueOf(longDateTime));
        SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dateText = df2.format(date);
        return dateText;
    }

    /**
     * Return XML format for a tag element
     *
     * @param elementName
     * @return XML String
     */
    protected static String nullTag(String elementName) {
        return "\n<" + elementName + "/>";
    }

    /**
     * Verifies if the merchant already exists and also returns the amount
     *
     * @param elementName
     * @return boolean if exists, the amount and the position in the list
     */
    private static Object[] existCustomerList(ArrayList<MerchantTotals> merchantTotalsList, Long idMerchant) {

        Object[] result = new Object[3];
        result[0] = Boolean.FALSE;

        //The list is empty, customer does not exist
        if (merchantTotalsList == null || merchantTotalsList.isEmpty()) {
            result[2] = 0;
            return result;
        }

        for (int i = 0; i < merchantTotalsList.size(); i++) {

            MerchantTotals merchantTotals = merchantTotalsList.get(i);

            if (merchantTotals.getId() == idMerchant) {
                result[0] = Boolean.TRUE;
                result[1] = merchantTotals;
                result[2] = i;

                return result;
            }
        }
        result[2] = merchantTotalsList.size();
        return result;
    }

    private static String findModeSecundaryTransaction(Long idOriginalTransaction, ArrayList<Object[]> secundaryTxInfoList) {
        if (secundaryTxInfoList.isEmpty()) {
            return "";
        } else {
            int j = 0;
            while (secundaryTxInfoList.size() > j) {
                Object[] secundaryTxInfo = secundaryTxInfoList.get(j);
                if (secundaryTxInfo[0] == (idOriginalTransaction)) {
                    return secundaryTxInfo[1].toString();

                }
                j++;
            }
            return "";
        }

    }

}
