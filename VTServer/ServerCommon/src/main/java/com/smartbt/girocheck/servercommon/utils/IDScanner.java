/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.utils;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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

    public static void main(String[] args) throws Exception {
//       Map map = parseID("48fa49a3-8ca4-4fc5-9a60-93271739969d", "QAoeDUFOU0kgNjM2MDEwMDEwMkRMMDAzOTAxNzBaRjAyMDkwMDY1RExEQUFKQVJBTUlMTE8sSkFJTUUsIEEKREFHMjkzNSBTVyAzMFRIIENUCkRBSUNPQ09OVVQgR1JPVkUKREFKRkwKREFLMzMxMzMtMzYxNSAKREFRSjY1NDQyMTc2MTc3MApEQVJFICAgCkRBU05PTkUKREFUTk9ORQpEQkEyMDIyMDUxNwpEQkIxOTc2MDUxNwpEQkMxCkRCRDIwMTQwNTEzCkRBVTUxMA1aRlpGQVJFUExBQ0VEOiAwMDAwMDAwMApaRkIKWkZDWDYzMTQwNTEzMTI2NgpaRkQKWkZFMDktMDEtMTIKWkZGDQ==");
        Map map = parseID("48fa49a3-8ca4-4fc5-9a60-93271739969d", "QAoeDUFOU0kgNjM2MDEwMDEwMkRMMDAzOTAxNzBaRjAyMDkwMDY1RExEQUFKQVJBTUlMTE8sSkFJTUUsIEEKREFHMjkzNSBTVyAzMFRIIENUCkRBSUNPQ09OVVQgR1JPVkUKREFKRkwKREFLMzMxMzMtMzYxNSAKREFRSjY1NDQyMTc2MTc3MApEQVJFICAgCkRBU05PTkUKREFUTk9ORQpEQkEyMDIyMDUxNwpEQkIxOTc2MDUxNwpEQkMxCkRCRDIwMTQwNTEzCkRBVTUxMA1aRlpGQVJFUExBQ0VEOiAwMDAwMDAwMApaRkIKWkZDWDYzMTQwNTEzMTI2NgpaRkQKWkZFMDktMDEtMTIKWkZGDQ==", 5);

        System.out.println(map.get(ParameterName.BORNDATE)); 

    }

    public static Map<ParameterName, String> parseID(String authKey, String text, Integer attempts) throws Exception {
        if (attempts == 0) {
            return new HashMap();
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: Scanning id...", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: authKey = " + authKey, null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IDScanner]:: text = " + text, null);
        HttpClient client = new DefaultHttpClient();
        //TODO put in a System Property
        HttpPost post = new HttpPost("https://app1.idware.net/DriverLicenseParserRest.svc/Parse");

        ScannerInput scannerInput = new ScannerInput(authKey, text);
        StringEntity input = new StringEntity(scannerInput.toString());
        input.setContentType("application/json");

        post.setEntity(input);
        HttpResponse response;
        try {
            response = client.execute(post);
        } catch (Exception e) {
            return parseID(authKey, text, attempts - 1);
        }

        if (response.getEntity() != null) {
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
                    map.put(ParameterName.STATE, getString(dl, "IssuedBy"));
                    map.put(ParameterName.EXPIRATION_DATE, getString(dl, "ExpirationDate"));
                    map.put(ParameterName.LAST_NAME, getString(dl, "LastName"));
                    map.put(ParameterName.ZIPCODE, getString(dl, "PostalCode"));
                    map.put(ParameterName.FIRST_NAME, getString(dl, "FirstName"));
                    map.put(ParameterName.MIDDLE_NAME, getString(dl, "MiddleName"));
                    map.put(ParameterName.BORNDATE, formatDate( getString(dl, "Birthdate")));
                    map.put(ParameterName.IDSTATE, getString(dl, "IssuedBy"));

                    map.put(ParameterName.COUNTRY, "US");
                    map.put(ParameterName.IDCOUNTRY, "US");

                    return map;
                }
            }
        }
        throw new Exception("Can not parse the ID.");
    }

    public static String getString(JSONObject json, String key) {
        if (json.has(key)) {
            return json.getString(key);
        }
        return "";
    }

    public static String formatDate(String date) {
        try {
            Date dobIn = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            SimpleDateFormat dobOut = new SimpleDateFormat("MM-dd-yyyy");
            return dobOut.format(dobIn);
        } catch (Exception e) {
            e.printStackTrace();
            return date;
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
