package com.smartbt.girocheck.servercommon.utils;

import com.smartbt.girocheck.common.VTSuiteMessages;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.xml.bind.ValidationException;

/**
 *
 * @author rrodriguez
 */
public class PasswordUtil {

    private static String passwordValidationRegExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^])(?=\\S+$).{8,}$";

    public static String generatePassword(int length) {
        final String allowedChars[] = new String[]{"0123456789", "abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "!@#$%^*"};
        int[] variety = new int[]{0, 0, 0, 0};

        SecureRandom random = new SecureRandom();
        StringBuilder pass = new StringBuilder();

        while (pass.length() < length || !hasVariety(variety)) {
            int charType = random.nextInt(4);

            variety[charType] = 1;

            pass.append(allowedChars[charType].charAt(random.nextInt(allowedChars[charType].length())));
        }

        return pass.toString();
    }

    public static boolean validatePasswordFormat(String password) {
        return password.matches(passwordValidationRegExp);
    }

    public static boolean hasVariety(int[] variety) {
        int var = 0;

        for (int w : variety) {
            var += w;
        }
        return var == 3;
    }

    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 1000; i++) {
//            String pass = generatePassword(8);
//            if(!pass.matches(passwordValidationRegExp)){
//                System.out.println(pass);
//            }
//        }
        System.out.println("40bd001563085fc35165329ea1ff5c5ecbdbbeef".length());
    }

    public static String encryptPassword(String password) throws ValidationException, NoSuchAlgorithmException {

        if (password == null) {
            throw new ValidationException(VTSuiteMessages.CANNOT_ENCRYPT_NULL_PASSWORD);
        }

        MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
        byte[] result = mDigest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }

}
