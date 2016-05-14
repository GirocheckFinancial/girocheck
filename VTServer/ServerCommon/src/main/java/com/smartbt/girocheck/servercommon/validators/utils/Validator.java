/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.validators.utils;

import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos
 */
public class Validator {

    public Validator() {
    }

    /**
     * Examples: Following email addresses will pass validation abc@xyz.net
     *
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        return matchPattern(email, RegExp.EMAIL_REG_EXP);
    }

    /**
     * Examples: Matches following phone numbers: (123)456-7890, 123-456-7890,
     * 1234567890, (123)-456-7890
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneNumberValid(String phone) {
        return matchPattern(phone, RegExp.PHONE_REG_EXP);
    }

    public static boolean isFloatValid(String number) {
        return matchPattern(number, RegExp.EXP_REG_FLOAT);
    }

    /**
     * Examples: 879-89-8989; 869878789 etc.
     *
     * @param ssn
     * @return
     */
    public static boolean isSsnValid(String ssn) {
        return matchPattern(ssn, RegExp.EXP_REG_SSN);
    }

    public static boolean isTerminalValid(String terminal) {
        return matchPattern(terminal, RegExp.EXP_REG_TERMINAL);
    }

    public static boolean isMerchantValid(String merchant) {
        return matchPattern(merchant, RegExp.EXP_REG_MERCHANT);
    }

    public static boolean isPanValid(String pan) {
        return matchPattern(pan, RegExp.EXP_REG_PAN);
    }

    public static boolean isExpDateValidMMYY(String date) {
        return true;
    }

    public static boolean isExpDateValidYYMM(String date) {
        return true;
    }

    public static boolean isTk2Valid(String tk2) {
        return true;
    }

    public static boolean isCvvValid(String cvv) {
        return matchPattern(cvv, RegExp.CVV_REG_EXP);
    }

    public static boolean isCvvFlagValid(String cvvflag) {
        return true;
    }

    public static boolean isIntegerValid(String inte) {
        return matchPattern(inte, RegExp.EXP_REG_INTEGER);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isEmpty(String var) {
        return var.trim().equals("");
    }

    public static boolean isFullDate(String date) {
        return matchPattern(date, RegExp.EXP_REG_FULL_DATE);
    }

    public static boolean isDate(String var) {
        return matchPattern(var, RegExp.EXP_REG_DATE);
    }

    public static boolean isTime(String date) {
        return matchPattern(date, RegExp.EXP_REG_TIME);
    }

    public static boolean isModeValid(String var) {
        return true;
    }

    public static boolean isOperationValid(String var) {
        return matchPattern(var, RegExp.EXP_REG_OPERATION);
    }

    public static boolean isBooleanValid(String var) {
        return matchPattern(var, RegExp.EXP_REG_BOOLEAN);
    }

    public static boolean isEncryptFormatValid(String var) {
        return matchPattern(var, RegExp.EXP_REG_VALID_ENCRYPTION_FORMATID);
    }

    public static boolean isHexadecimalValid(String var) {
        return matchPattern(var, RegExp.EXP_REG_VALID_HEXADECIMAL);
    }

    public static boolean isSearchAllowed(String var) {
        return matchPattern(var, RegExp.VALID_SEARCH_II_REG_EXP);
    }

    public static boolean isTextVIValid(String var) {
        return matchPattern(var, RegExp.VALID_TEXT_VI_REG_EXP);
    }

    /**
     * Common Pattern
     *
     * @param value
     * @param regexpr
     * @return
     */
    public static boolean matchPattern(String value, String regexpr) {
        CharSequence inputStr = value;
        Pattern pattern = Pattern.compile(regexpr);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }
}
