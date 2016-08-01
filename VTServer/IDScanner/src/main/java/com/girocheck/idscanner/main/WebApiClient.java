package com.girocheck.idscanner.main;

import com.girocheck.idscanner.boundary.DriverLicenseParser;
import com.girocheck.idscanner.boundary.IDriverLicenseParser;
import com.girocheck.idscanner.boundary.Response;

//import net.idware.dlpws.*;
import org.apache.axis.encoding.Base64;

public class WebApiClient {

    public static String scanId(String authKey, String imageBase64){
//           String authKey = "48fa49a3-8ca4-4fc5-9a60-93271739969d";
   
        DriverLicenseParser driverLicenseParser = new DriverLicenseParser();
        IDriverLicenseParser client = driverLicenseParser.getRelease();
        try {
//            String encodedText = Base64.encode(imageBase64.getBytes());
            Response response = client.parseBase64AsciiString(authKey,
                    imageBase64, (short) -1);
            if (response.isSuccess()) {
                System.out.println("Success..............");
                return "Address: " + response.getDriverLicense().getValue().getAddress1().getValue();
            } else {
                System.out.println("failed..............");
                return response.getErrorMessage().getValue();
            }
        } catch (Exception e) {
            System.out.println("Exceptions");
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
