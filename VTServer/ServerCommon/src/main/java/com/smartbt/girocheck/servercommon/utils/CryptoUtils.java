package com.smartbt.girocheck.servercommon.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class CryptoUtils {

    private final static byte[] linebreak = {}; // Remove Base64 encoder default linebreak
    private static final String DEFAULT_KEY = "rpMp=87l06o11veS"; // secret key length must be 16
    private static Cipher cipher;
    private static Base64 coder;

    static {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
            coder = new Base64(32, linebreak, true);
        } catch (NoSuchAlgorithmException t) {
            System.out.println("[CryptoUtils] NoSuchAlgorithmException: " + t.getMessage());
        } catch (NoSuchProviderException t) {
            System.out.println("[CryptoUtils] NoSuchProviderException: " + t.getMessage());
        } catch (NoSuchPaddingException t) {
            System.out.println("[CryptoUtils] NoSuchPaddingException: " + t.getMessage());
        }
    }
    
    public static void main(String[] args){
        System.out.println( encrypt("501929181"));
    }

    public static String encrypt(String plainText, String key) {
        try {
            key = DigestUtils.md5Hex(key).substring(0, 16);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            byte[] cipherText = cipher.doFinal(plainText.getBytes());

            return new String(coder.encode(cipherText));
        } catch (InvalidKeyException ex) {
            System.out.println("[CryptoUtils] encrypt() InvalidKeyException: " + ex.getMessage());
        } catch (IllegalBlockSizeException ex) {
            System.out.println("[CryptoUtils] encrypt() IllegalBlockSizeException: " + ex.getMessage());
        } catch (BadPaddingException ex) {
            System.out.println("[CryptoUtils] encrypt() BadPaddingException: " + ex.getMessage());
        }
        return null;
    }

    public static String encrypt(String plainText) {
        return encrypt(plainText, DEFAULT_KEY);
    }

    public static String decrypt(String codedText) {
        return decrypt(codedText, DEFAULT_KEY);
    }

    public static String decrypt(String codedText, String key) {
        try {
            key = DigestUtils.md5Hex(key).substring(0, 16);
            byte[] encypted = coder.decode(codedText.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            byte[] decrypted = cipher.doFinal(encypted);
            return new String(decrypted);
        } catch (InvalidKeyException ex) {
            System.out.println("[CryptoUtils] decrypt() InvalidKeyException: " + ex.getMessage());
        } catch (IllegalBlockSizeException ex) {
            System.out.println("[CryptoUtils] decrypt() IllegalBlockSizeException: " + ex.getMessage());
        } catch (BadPaddingException ex) {
            System.out.println("[CryptoUtils] decrypt() BadPaddingException: " + ex.getMessage());
        }
        return null;
    }
}
