/*
 ** File: Utils.java
 **
 ** Date Created: March 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.servercommon.utils;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
//import org.im4java.core.ConvertCmd;
//import org.im4java.core.IMOperation;
//import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class Utils {

    private static String token_alphabet = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static List collectionToList(Collection coll) {
        List list;
        if (coll instanceof List) {
            list = (List) coll;
        } else {
            list = new ArrayList(coll);
        }
        return list;
    }

    public static String generateToken() {
        return rndstr(48);
    }

    private static String rndstr(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < len) {
            sb.append(token_alphabet.charAt(r.nextInt(token_alphabet.length() - 1)));
        }

        return sb.toString();
    }

    /**
     * Basic SHA-1 Password Encryption
     *
     * @param password
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
        byte[] result = mDigest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }

    public static String encryptCredictCardNumber(String cCardNumber) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
        byte[] result = mDigest.digest(cCardNumber.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }

    public static String encryptSSN(String ssn) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
        byte[] result = mDigest.digest(ssn.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }

    public static String MD5(String unencrypted) {

        try {

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(unencrypted.getBytes(), 0, unencrypted.length());
            String MD5 = new BigInteger(1, m.digest()).toString(16);

            return MD5;

        } catch (Exception e) {

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[ServerCommon.utils.Utils] MD5(...) Error ", e.getMessage());
            return null;
        }

    }

//    public static void convertTifToPng(File inputImage, File outputImage) {
//        IMOperation op = new IMOperation();
//        op.addImage(); //place holder for input file
//        op.addImage(); //place holder for output file
//
//        ConvertCmd convert = new ConvertCmd();
//        convert.run(op, new Object[]{inputImage.getAbsolutePath(), outputImage.getAbsolutePath()});
//    }
    public static String generateRandomNumber(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int n = 0; n < length; n++) {
            int j = (Math.abs(random.nextInt()) % 10);
            // If First digit is "0", skip that and get next random
            if (n == 0 && j == 0) {
                n--;
                continue;
            }
            sb.append(Integer.toString(j));
        }
        return sb.toString();
    }
}
