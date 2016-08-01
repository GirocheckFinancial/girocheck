/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.idscanner.main;

import com.girocheck.idscanner.boundary.DriverLicenseParser;
import com.girocheck.idscanner.boundary.IDriverLicenseParser;
import com.girocheck.idscanner.boundary.Response;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.axis.encoding.Base64;

/**
 *
 * @author rrodriguez
 */
public class Main {
     public static void main(String[] args) {
        String authKey = "48fa49a3-8ca4-4fc5-9a60-93271739969d";
        String text = readLicenseText();
        System.out.println("text = ");
        System.out.println(text);
        DriverLicenseParser driverLicenseParser = new DriverLicenseParser();
        IDriverLicenseParser client = driverLicenseParser.getRelease();
        try {
//            String encodedText = Base64.encode(text.getBytes());
            Response response = client.parseBase64AsciiString(authKey,
                    text, (short) -1);
            if (response.isSuccess()) {
                System.out.println("Success..............");
                System.out.println(response.getDriverLicense().getValue().getAddress1().getValue());
            } else {
                System.out.println("failed..............");
                System.out.println(response.getErrorMessage().getValue() );
            }
        } catch (Exception e) {
            System.out.println("Exceptions");
            e.printStackTrace();
        }
    }

    public static String readLicenseText() {
        String text = new String();
        FileReader f;
        try {
            f = new FileReader("C:\\Users\\rrodriguez\\Documents\\Girocheck\\ID\\myid.txt");
            BufferedReader fis = new BufferedReader(f);
            String s = new String();
            while ((s = fis.readLine()) != null) {
                text += s + "\n";
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return text;
    }
}
