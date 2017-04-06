/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.utils;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.manager.StateManager;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.idscanner.DriverLicense;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author rrodriguez
 */
public class IDScanner {

    private static HttpPost post = new HttpPost("https://app1.idware.net/DriverLicenseParserRest.svc/Parse");
    private static HttpClient client = new DefaultHttpClient();

//    public static void main(String[] args) throws Exception {
//         DriverLicense dl = new DriverLicense(
//"ANSI 6360100102DL00390171ZF02100047DLDAADE LA NUEZ,PEDRO,LUIS\n" +
//"DAG7019 POMELO DR\n" +
//"DAIORLANDO\n" +
//"DAJFL\n" +
//"DAK32819-0000 \n" +
//"DAQD452672771350\n" +
//"DARE \n" +
//"DASNONE\n" +
//"DATNONE\n" +
//"DBA20250415\n" +
//"DBB19770415\n" +
//"DBC1\n" +
//"DBD20160621\n" +
//"DBHN\n" +
//"DAU510\n" +
//"ZFZFA\n" +
//"ZFB\n" +
//"ZFCG811606210093\n" +
//"ZFD\n" +
//"ZFE06-01-14\n" +
//"ZFF"
//);
//
//         Map map = dl.toMap();
//         
//        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
//            Object next = iterator.next();
//            System.out.println(next + " -> " + map.get(next));
//        } 
//        
//        validateOutput(map);
//    }
    public static void main(String[] args) throws Exception {
//        testParseID();
        Map map = parseID("48fa49a3-8ca4-4fc5-9a60-93271739969d", "QU5TSSA2MzYwMTAwMTAyREwwMDM5MDE3MVpGMDIxMDAwNDdETERBQURFIExBIE5VRVosUEVEUk8sTFVJUw0KREFHNzAxOSBQT01FTE8gRFINCkRBSU9STEFORE8NCkRBSkZMDQpEQUszMjgxOS0wMDAwIA0KREFRRDQ1MjY3Mjc3MTM1MA0KREFSRSANCkRBU05PTkUNCkRBVE5PTkUNCkRCQTIwMjUwNDE1DQpEQkIxOTc3MDQxNQ0KREJDMQ0KREJEMjAxNjA2MjENCkRCSE4NCkRBVTUxMA0KWkZaRkENClpGQg0KWkZDRzgxMTYwNjIxMDA5Mw0KWkZEDQpaRkUwNi0wMS0xNA0KWkZG");
//       Map map = parseID("48fa49a3-8ca4-4fc5-9a60-93271739969d", "QAoeDUFOU0kgNjM2MDEwMDEwMkRMMDAzOTAxNzBaRjAyMDkwMDY1RExEQUFKQVJBTUlMTE8sSkFJTUUsIEEKREFHMjkzNSBTVyAzMFRIIENUCkRBSUNPQ09OVVQgR1JPVkUKREFKRkwKREFLMzMxMzMtMzYxNSAKREFRSjY1NDQyMTc2MTc3MApEQVJFICAgCkRBU05PTkUKREFUTk9ORQpEQkEyMDIyMDUxNwpEQkIxOTc2MDUxNwpEQkMxCkRCRDIwMTQwNTEzCkRBVTUxMA1aRlpGQVJFUExBQ0VEOiAwMDAwMDAwMApaRkIKWkZDWDYzMTQwNTEzMTI2NgpaRkQKWkZFMDktMDEtMTIKWkZGDQ==");
//        Map map = parseID("48fa49a3-8ca4-4fc5-9a60-93271739969d", "QAoeDUFOU0kgNjM2MDEwMDEwMkRMMDAzOTAxNzBaRjAyMDkwMDY1RExEQUFKQVJBTUlMTE8sSkFJTUUsIEEKREFHMjkzNSBTVyAzMFRIIENUCkRBSUNPQ09OVVQgR1JPVkUKREFKRkwKREFLMzMxMzMtMzYxNSAKREFRSjY1NDQyMTc2MTc3MApEQVJFICAgCkRBU05PTkUKREFUTk9ORQpEQkEyMDIyMDUxNwpEQkIxOTc2MDUxNwpEQkMxCkRCRDIwMTQwNTEzCkRBVTUxMA1aRlpGQVJFUExBQ0VEOiAwMDAwMDAwMApaRkIKWkZDWDYzMTQwNTEzMTI2NgpaRkQKWkZFMDktMDEtMTIKWkZGDQ==");
//        Map map = parseID("48fa49a3-8ca4-4fc5-9a60-93271739969d", "QAoeDUFOU0kgNjM2MDEwMDEwMkRMMDAzOTAxOTJaRjAyMzEwMDYzRExEQUFST0xEQU4gQU5EUklOTyxTQUlSQSxMCkRBRzI5MjYwIFNXIDE0MiBBVkUKREFJSE9NRVNURUFECkRBSkZMCkRBSzMzMDMzLTMwMTcgCkRBUVI0MzU3OTI2MTY4ODYKREFSRSAgIApEQVMgICAgICAgICAgCkRBVCAgICAgCkRCQTIwMTgwNTI4CkRCQjE5NjEwNTI4CkRCQzIKREJEMjAxMDA2MjMKREJITiAgICAgICAgIApEQVU1MDMNWkZaRkFSRVBMQUNFRDogMDAwMDAwMDAKWkZCIApaRkNUMDYxMDA2MjMwMTgzClpGRCAKWkZFMDgtMzEtMDk=");
//        Map map = parseID("48fa49a3-8ca4-4fc5-9a60-93271739969d", "QAoeDUFOU0kgNjM2MDEwMDEwMkRMMDAzOTAxOTJaRjAyMzEwMDYzRExEQUFST0xEQU4gQU5EUklOTyxTQUlSQSxMCkRBRzI5MjYwIFNXIDE0MiBBVkUKREFJSE9NRVNURUFECkRBSkZMCkRBSzMzMDMzLTMwMTcgCkRBUVI0MzU3OTI2MTY4ODYKREFSRSAgIApEQVMgICAgICAgICAgCkRBVCAgICAgCkRCQTIwMTgwNTI4CkRCQjE5NjEwNTI4CkRCQzIKREJEMjAxMDA2MjMKREJITiAgICAgICAgIApEQVU1MDMNWkZaRkFSRVBMQUNFRDogMDAwMDAwMDAKWkZCIApaRkNUMDYxMDA2MjMwMTgzClpGRCAKWkZFMDgtMzEtMDk=");
//
//        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
//            Object next = iterator.next();
//            System.out.println(next + " -> " + map.get(next));
//        }

    }

    public static boolean testParseID() {
        try {
            Map map = parseIDRemotely("48fa49a3-8ca4-4fc5-9a60-93271739969d", "QAoeDUFOU0kgNjM2MDEwMDEwMkRMMDAzOTAxNzBaRjAyMDkwMDY1RExEQUFKQVJBTUlMTE8sSkFJTUUsIEEKREFHMjkzNSBTVyAzMFRIIENUCkRBSUNPQ09OVVQgR1JPVkUKREFKRkwKREFLMzMxMzMtMzYxNSAKREFRSjY1NDQyMTc2MTc3MApEQVJFICAgCkRBU05PTkUKREFUTk9ORQpEQkEyMDIyMDUxNwpEQkIxOTc2MDUxNwpEQkMxCkRCRDIwMTQwNTEzCkRBVTUxMA1aRlpGQVJFUExBQ0VEOiAwMDAwMDAwMApaRkIKWkZDWDYzMTQwNTEzMTI2NgpaRkQKWkZFMDktMDEtMTIKWkZGDQ==", 5);

            return map.containsKey(ParameterName.BORNDATE)
                    && map.get(ParameterName.BORNDATE) != null
                    && map.get(ParameterName.BORNDATE).equals("05-17-1976");
        } catch (Exception ex) {
            return false;
        }
    }

    public static Map<ParameterName, Object> parseID(String authKey, String text) throws Exception {
        Map<ParameterName, Object> map = null;
        try {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]::: Parsing ID", null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]::: text = " + text, null);
            map = parseIdLocally(text);
        } catch (Exception e) {
        }

        if (!validateOutput(map)) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]::: Parsing ID locally FAILED", null);
            try {
                map = parseIDRemotely(authKey, text, 5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: Parsing ID locally... SUCCESS", null);
        }

        String stateAbbreviation = (String)map.get(ParameterName.STATE_ABBREVIATION);
        HibernateUtil.beginTransaction();
        State state = StateManager.get().getByAbbreviation(stateAbbreviation);
        map.put(ParameterName.STATE, state.getCode() + "");
        System.out.println("IDScanner -> STATE = " + state.getCode());
        HibernateUtil.commitTransaction();
        return map;
    }

    public static Map<ParameterName, Object> parseIdLocally(String text) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: Parsing ID locally...", null);

        String decoded = new String(Base64.decodeBase64(text));
        return new DriverLicense(decoded).toMap();
    }

    public static Map<ParameterName, Object> parseIDRemotely(String authKey, String text, Integer attempts) throws Exception {
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: Parsing ID Remotely...", null);if (attempts == 0) {
            return null;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: Parsing ID remotely...", null);

        //TODO put in a System Property
        ScannerInput scannerInput = new ScannerInput(authKey, text);
        StringEntity input = new StringEntity(scannerInput.toString());
        input.setContentType("application/json");

        post.setEntity(input);
        HttpResponse response;
        try {
            response = client.execute(post);
        } catch (Exception e) {
            Thread.sleep(2000);
            return parseIDRemotely(authKey, text, attempts - 1);
        }

        if (response != null && response.getEntity() != null) {
            String resp = EntityUtils.toString(response.getEntity());

            System.out.println(resp);

            System.out.println("-json-");
            JSONObject json = new JSONObject(resp);

            if (json.has("ParseResult")) {
                JSONObject parseresult = json.getJSONObject("ParseResult");

                if (parseresult.getBoolean("Success")) {
                    JSONObject dl = parseresult.getJSONObject("DriverLicense");

                    System.out.println(dl.toString());

                    Map map = new HashMap<ParameterName, String>();
                    map.put(ParameterName.ID, getString(dl, "LicenseNumber"));
                    map.put(ParameterName.ADDRESS, getString(dl, "Address1"));
                    map.put(ParameterName.GENDER, getString(dl, "Gender"));
                    map.put(ParameterName.CITY, getString(dl, "City"));
                    map.put(ParameterName.LAST_NAME, getString(dl, "LastName"));
                    map.put(ParameterName.ZIPCODE, getString(dl, "PostalCode"));
                    map.put(ParameterName.FIRST_NAME, getString(dl, "FirstName"));
                    map.put(ParameterName.MIDDLE_NAME, getString(dl, "MiddleName"));

                    map.put(ParameterName.IDSTATE, getString(dl, "IssuedBy")); 
                    map.put(ParameterName.STATE_ABBREVIATION, getString(dl, "IssuedBy"));

                    map.put(ParameterName.COUNTRY, "US");
                    map.put(ParameterName.IDCOUNTRY, "US");

                    formatDate(ParameterName.BORNDATE, ParameterName.BORNDATE_AS_DATE, getString(dl, "Birthdate"), map);
                    formatDate(ParameterName.EXPIRATION_DATE, ParameterName.EXPIRATION_DATE_AS_DATE, getString(dl, "ExpirationDate"), map);

                    if(map.get(ParameterName.EXPIRATION_DATE_AS_DATE) == null){
                        System.out.println("IDScan -> ID is Matricula Consular, seting IDTYPE = 1");
                        map.put(ParameterName.IDTYPE, IdType.SSN);
                    }
                    
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: Scan was Success", null);
                    return map;
                }
            }
        }

        return parseIDRemotely(authKey, text, attempts - 1);
    }

    public static boolean validateOutput(Map<ParameterName, Object> map) {
        if (map == null) {
            return false;
        }

        ParameterName key = null;

        try {
            for (Iterator<ParameterName> iterator = map.keySet().iterator(); iterator.hasNext();) {
                key = iterator.next();

                if (key != ParameterName.MIDDLE_NAME
                        && (!map.containsKey(key) || map.get(key) == null || map.get(key).toString().isEmpty())) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: Rejecting map for property = " + key, null);
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception validating Driver License :: key = " + key);

            e.printStackTrace();
        }

        return true;
    }

    public static String getString(JSONObject json, String key) {
        if (json.has(key) && !json.isNull(key)) {
            try{
                return (String) json.get(key);    
            }catch(Exception e){
                System.out.println("IDScanner failed to get value for " + key);
            } 
        }
        return "";
    }

    public static void formatDate(ParameterName parameter, ParameterName parameterAsDate, String date, Map map) {
        try {
            if (!date.isEmpty()) {
                Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                map.put(parameterAsDate, d);
                SimpleDateFormat dobOut = new SimpleDateFormat("MM-dd-yyyy");
                map.put(parameter, dobOut.format(d));
            } else {
                map.put(parameterAsDate, null);
                map.put(parameter, "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ScannerInput {

    private String authKey;
    private String text;

    public ScannerInput() {
    }

    public ScannerInput(String authKey, String data) {
        this.authKey = authKey;
        this.text = data;
    }

    @Override
    public String toString() {
        return "{\"authKey\":\"" + authKey + "\", \"text\":\"" + text + "\"}";
    }

    /**
     * @return the authKey
     */
    public String getAuthKey() {
        return authKey;
    }

    /**
     * @param authKey the authKey to set
     */
    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
