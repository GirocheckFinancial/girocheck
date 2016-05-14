/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.email.EmailUtils;
import com.smartbt.girocheck.servercommon.email.ImagePart;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roberto
 */
public class MockManager {

    public static void sendTheEmail(String methodName, Map values) throws Exception {

        System.out.println( "Sending the email.... for method :: "+ methodName );
        
        DateFormat df = DateFormat.getDateTimeInstance();

        String receiptTitle = "Test to " + methodName + " at " + df.format(new Date());

        System.out.println(receiptTitle);

        List<String> emailList = new ArrayList<String>();
        emailList.add("titorobe@yahoo.com");

        //    TransactionValidator.sendTheEmail(imageAsBase64, emailList);
        String server_address = "smtp.cbeyond.com";
        String server_port = "587";
        String server_username = "direx@smartbt.com";
        String server_password = "MiamiRocks12";
        String server_from_address = "direx@smartbt.com";

        boolean email_debug = false;

        String[] recipients = new String[emailList.size()];
        emailList.toArray(recipients);

        EmailUtils email;

        if (server_username != null && !server_username.isEmpty()) {
            email = new EmailUtils(server_address, server_port, server_username, server_password);
        } else {
            email = new EmailUtils(server_address, server_port);
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body> Received Data:");

        for (Object key : values.keySet()) {
            if (!(key instanceof ParameterName)) {
                continue;
            }
            ParameterName parameter = (ParameterName) key;
            buffer.append("<br/>" + parameter.toString() + " :: " + values.get(parameter));
        }

        buffer.append("</body></html>");

        email.setMessage(buffer.toString(), "text/html");
        //    email.setImage(imageAsBase64, "logo.JPEG", "image/JPEG", "image");
        email.sendEmail(recipients, server_from_address, receiptTitle, email_debug);
    }

    public static void sendTheEmail(Map<ParameterName, ImagePart> images, String methodName, Map values) throws Exception {

        DateFormat df = DateFormat.getDateTimeInstance();

        String receiptTitle = "Test to " + methodName + " at " + df.format(new Date());

        System.out.println(receiptTitle);

        List<String> emailList = new ArrayList<String>();
        emailList.add("girochecktest@gmail.com");

        //    TransactionValidator.sendTheEmail(imageAsBase64, emailList);
        String server_address = "smtp.cbeyond.com";
        String server_port = "587";
        String server_username = "direx@smartbt.com";
        String server_password = "MiamiRocks12";
        String server_from_address = "direx@smartbt.com";

        boolean email_debug = false;

        String[] recipients = new String[emailList.size()];
        emailList.toArray(recipients);

        EmailUtils email;

        if (server_username != null && !server_username.isEmpty()) {
            email = new EmailUtils(server_address, server_port, server_username, server_password);
        } else {
            email = new EmailUtils(server_address, server_port);
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body> Received Data:");

        for (Object key : values.keySet()) {
            if (!(key instanceof ParameterName)) {
                continue;
            }

            ParameterName parameter = (ParameterName) key;

            Object obj = values.get(parameter);

            if (obj instanceof ParameterName && ((ParameterName) obj).equals(ParameterName.IMAGE)) {
                if (images.containsKey(parameter)) {
                    email.addImage(images.get(parameter));
                    buffer.append("<br/>" + parameter.toString() + " ::  (See image attached with name :: " + parameter + ".JPEG )");
                } else {
                    buffer.append("<br/>" + parameter.toString() + " ::  EMPTY");
                }
            } else {
                buffer.append("<br/>" + parameter.toString() + " :: " + values.get(parameter));
            }
        }

        buffer.append("</body></html>");

        email.setMessage(buffer.toString(), "text/html");
        //email.setImage(imageAsBase64, "backTiff.JPEG", "image/JPEG", "image");
        email.sendEmail(recipients, server_from_address, receiptTitle, email_debug);
    }

}
