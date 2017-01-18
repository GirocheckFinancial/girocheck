 package com.smartbt.girocheck.servercommen.sms;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.email.EmailUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

 
/**
 * Go4Clients SMS utils
 *
 */
public class SMSUtils {
    
    static String url = "https://go4clients.com:8443/TelintelSms/api/sms/sendmessage";
    
    static String API_KEY="aec51d9fc4a64bd19639b5b2c2a88e5d";
    
    static String API_SECRET="0086572281";
 
    public static void sendSMS(String phone, String message) {
       
        HttpPost postRequest = new HttpPost(url);
        ObjectMapper mapper = new ObjectMapper();
 
        try {
            System.out.println("came here");
 
            String request = "{\"toList\" :[\""
                    + phone
                    + "\"], \"message\":\""
                    + message
                    +"\"}";
            HttpClient httpClient = HttpClientBuilder.create().build();
 
            postRequest.addHeader("Content-type", "application/json");
            postRequest.addHeader("Apikey", API_KEY);
            postRequest.addHeader("Apisecret",API_SECRET);
 
            StringEntity input = new StringEntity(request);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            System.out.println("came here before execute");
 
            HttpResponse response = httpClient.execute(postRequest);
            HttpEntity responseEntity = response.getEntity();
            
            if(responseEntity!=null) {
               String resp = EntityUtils.toString(responseEntity);
               System.out.println("came here after execute :- "+resp);// mapper.readValue(response.getEntity().getContent(), Response.class);
            } 
            
 
           // System.out.println(entityResponse.toString());
       } catch (Exception e) { 
           String str = "There is a problem while sending SMS.Please see the error message: "+e.getMessage();
           StringBuffer buffer= new StringBuffer();
           buffer.append(str);
           generateNotificationEmail(buffer) ;      
        
       } finally {
       if (postRequest != null) {
          postRequest.releaseConnection();
       }
    }  
    }
    
    private  static void generateNotificationEmail(StringBuffer buffer) {
        
        String server_address = "smtp.cbeyond.com";
        String server_port = "587";
        String server_username = "direx@smartbt.com";
        String server_password = "MiamiRocks12";
        String server_from_address = "direx@smartbt.com";        
       
        
        String[] recipients = new String[1];
        recipients[0]="it@girocheck.com";
        
        EmailUtils email = new EmailUtils(server_address, server_port, server_username, server_password);
        
        email.setMessage(buffer.toString(), "text/html");
        
        email.sendEmail(recipients, server_from_address, "General Error", false);
    }
    
    
    public static void main(String s[]){
        sendSMS("917204881557","Thank you for using VoltCash");
    }
}
    
 

