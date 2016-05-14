/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.validators.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Carlos
 */
public class CustomsAnnotations {

    /**
     * VALIDATE - EMAIL_ADDRESS
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface EmailAdressValid {

        String message();
    }

    /**
     * VALIDATE - PHONE_NUMBER
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PhoneNumberValid {

        String message();
    }

    /**
     * VALIDATE - NUMBER
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NumericValid {

        String message();
    }

    /**
     * VALIDATE - SSN
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SsnValid {

        String message();
    }

    /**
     * VALIDATE - DATE
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DateValid {

        String message();
    }

    /**
     * VALIDATE - TIME
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TimeValid {

        String message();
    }

    /**
     * VALIDATE - TERMINAL
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TerminalValid {

        String message();
    }

    /**
     * VALIDATE - MERCHANT
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MerchantValid {

        String message();
    }

    /**
     * VALIDATE - PAN
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PanValid {

        String message();
    }

    /**
     * VALIDATE - EXP_DATE_MMYY
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExpDateValidMMYY {

        String message();
    }

    /**
     * VALIDATE - EXP_DATE_YYMM
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExpDateValidYYMM {

        String message();
    }

    /**
     * VALIDATE - TK2
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Tk2Valid {

        String message();
    }

    /**
     * VALIDATE - CVV
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CvvValid {

        String message();
    }

    /**
     * VALIDATE - CVV_FLAG
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CvvFlagValid {

        String message();
    }

    /**
     * VALIDATE - INTEGERS
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IntegerValid {

        String message();
    }
    
    /**
     * VALIDATE - MODE
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ModeValid {

        String message();
    }

    /**
     * VALIDATE - OPERATION
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface OperationValid {

        String message();
    }

    /**
     * VALIDATE - BOOLEAN
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface BooleanValid {

        String message();
    }

    /**
     * VALIDATE - ENCRYPT_FORMAT
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface EncryptFormatValid {

        String message();
    }

    /**
     * VALIDATE - HEXADECIMAL
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HexadecimalValid {

        /**
         *
         * @return
         */
        String message();
    }
}
