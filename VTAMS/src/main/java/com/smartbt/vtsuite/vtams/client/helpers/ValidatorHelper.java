/*
 ** File: ValidatorHelper.java
 **
 ** Date Created: October 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.helpers;


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.form.validator.MaskValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.form.validator.RequiredIfFunction;
import com.smartgwt.client.widgets.form.validator.RequiredIfValidator;
import com.smartgwt.client.widgets.form.validator.Validator;

/**
 * The Validator Helpers Class
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 *
 */
public class ValidatorHelper {

    /**
     * The Text Validator
     *
     * @param minLength
     * @param maxLengt
     * @return The validator
     */
    public static RegExpValidator getTextValidator(int minLength, int maxLengt) {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_TEXT_REG_EXP(minLength, maxLengt));
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());
        return validator;
    }
    
    /**
     * The Text I Validator
     *
     * @return The validator
     */
    public static RegExpValidator getTextIValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_TEXT_I_REG_EXP);
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());

        return validator;
    }

    /**
     * The Text II Validator
     *
     * @return The validator
     */
    public static RegExpValidator getTextIIValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_TEXT_II_REG_EXP);
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());

        return validator;
    }

    /**
     * The Text III Validator
     *
     * @return The validator
     */
    public static RegExpValidator getTextIIIValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_TEXT_III_REG_EXP);
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());
        return validator;
    }

    /**
     * The Text III Validator
     *
     * @param minLength
     * @param maxLengt
     * @return The validator
     */
    public static RegExpValidator getTextIIIValidator(int minLength, int maxLengt) {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_TEXT_III_REG_EXP(minLength, maxLengt));
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());
        return validator;
    }

    /**
     * The Text V Validator
     *
     * @return The validator
     */
    public static RegExpValidator getTextVValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_TEXT_V_REG_EXP);
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());

        return validator;
    }

    /**
     * The Text VI Validator
     *
     * @return The validator
     */
    public static RegExpValidator getTextVIValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_TEXT_VI_REG_EXP);
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());
        return validator;
    }

    /**
     * The Text VI Validator
     *
     * @param minLength
     * @param maxLengt
     * @return The validator
     */
    public static RegExpValidator getTextVIValidator(int minLength, int maxLengt) {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_TEXT_VI_REG_EXP(minLength, maxLengt));
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());
        return validator;
    }

    /**
     * The Search I Validator
     *
     * @return The validator
     */
    public static RegExpValidator getSearchIValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.VALID_SEARCH_I_REG_EXP);
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());

        return validator;
    }

    /**
     * The Float Precision Validator
     *
     * @return The validator
     */
    public static FloatPrecisionValidator getPrecisionValidator() {
        FloatPrecisionValidator validator = new FloatPrecisionValidator();
        validator.setPrecision(2);
        validator.setRoundToPrecision(2);

        return validator;
    }

    /**
     * The Float Range Validator
     *
     * @return The validator
     */
    public static IntegerRangeValidator getAmountFloatRangeValidator() {
        IntegerRangeValidator validator = new IntegerRangeValidator();
        validator.setMin(Constants.MIN_AMOUNT_ALLOWED);
        validator.setMax(Constants.MAX_AMOUNT_ALLOWED);
        validator.setErrorMessage(I18N.GET.VALIDATION_AMOUNT_ERROR_MSG());

        return validator;
    }

    /**
     * The Email Validator
     *
     * @return The validator
     */
    public static RegExpValidator getEmailValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.EMAIL_REG_EXP);
        validator.setErrorMessage(I18N.GET.VALIDATION_EMAIL_ERROR_MSG());

        return validator;
    }
    /**
     * The Zip Validator
     *
     * @return The validator
     */
    public static RegExpValidator getZipValidator() {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.EXP_REG_ZIP_CODE);
        validator.setErrorMessage("Zip code entry is incorrect");

        return validator;
    }

    /**
     * The Phone Validator
     *
     * @return The validator
     */
    public static MaskValidator getPhoneValidator() {
        MaskValidator validator = new MaskValidator();
        validator.setMask(RegExp.PHONE_REG_EXP);
        validator.setTransformTo(RegExp.PHONE_TRANSFORM_TO);
        validator.setErrorMessage(I18N.GET.VALIDATION_PHONE_ERROR_MSG());

        return validator;
    }

    /**
     * The Integer Validator
     *
     * @param minLength
     * @param maxLength
     * @return The validator
     */
    public static Validator getIntegerRangeValidator(int minLength, int maxLength) {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.EXP_REG_INTEGER(minLength, maxLength));
        return validator;
    }

    public static Validator getTextRangeValidator(int minLength, int maxLength) {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.EXP_REG_TEXT(minLength, maxLength));
        return validator;
    }

    /**
     * The Password Validator
     *
     * @return The validator
     */
    public static RegExpValidator getPasswordValidator() {
        RegExpValidator validator = new RegExpValidator();
      //  validator.setExpression(RegExp.EXP_REG_PASSWORD);
        validator.setErrorMessage(I18N.GET.VALIDATION_CHARACTERS_ERROR_MSG());
        return validator;
    }
    
    /**
     * The Float Validator
     *
     * @param minLength
     * @param maxLength
     * @param precision
     * @return The validator
     */
    public static Validator getFloatRangeValidator(int minLength, int maxLength, int precision) {
        RegExpValidator validator = new RegExpValidator();
        validator.setExpression(RegExp.EXP_REG_FLOAT(minLength, maxLength, precision));
        return validator;
    }

//    /**
//     * The Float Validator
//     *
//     * @param precision
//     * @return The validator
//     */
//    public static Validator getFloatPrecisionValidator(int precision) {
//        FloatPrecisionValidator validator = new FloatPrecisionValidator();
//        validator.setPrecision(precision);
//        return validator;
//    }
//    public static Validator getIsStringValidator() {
//        IsStringValidator validator = new IsStringValidator();
//        return validator;
//    }
//    public static Validator getIsBooleanValidator() {
//        IsBooleanValidator validator = new IsBooleanValidator();
//        return validator;
//    }
//    public static Validator getLenghtRangeValidator(int min, int max) {
//        LengthRangeValidator validator = new LengthRangeValidator();
//        validator.setMin(min);
//        validator.setMax(max);
//        return validator;
//    }
    public static Validator getRequiredIfValidator(RequiredIfFunction req) {
        RequiredIfValidator validator = new RequiredIfValidator();

//        RequiredIfFunction req = new RequiredIfFunction() {
//
//            public boolean execute(FormItem formItem, Object value) {
//                return true;
//            }
//        };
        validator.setExpression(req);
        return validator;
    }
}
