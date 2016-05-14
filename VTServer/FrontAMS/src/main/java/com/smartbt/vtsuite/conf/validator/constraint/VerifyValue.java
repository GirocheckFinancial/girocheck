/*
 ** File: VerifyValue.java
 **
 ** Date Created: December 2013
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
package com.smartbt.vtsuite.conf.validator.constraint;

import com.smartbt.vtsuite.conf.validator.constraint.VerifyValue.VerifyValueValidator;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = VerifyValue.Validator.class)
//public @interface VerifyValue {
//
//    String message() default "Value specified is not valid";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//
//    public class Validator implements ConstraintValidator<VerifyValue, String> {
//
//        @Override
//        public void initialize(final VerifyValue hasId) {
//        }
//
//        @Override
//        public boolean isValid(final String contact, final ConstraintValidatorContext constraintValidatorContext) {
//            return true;
//        }
//    }
//}
@Retention(RUNTIME)
//@Target({FIELD, METHOD})
//@Documented
@Constraint(validatedBy = VerifyValueValidator.class)
public @interface VerifyValue {

    String message() default "Value specified is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class value();

    class VerifyValueValidator implements ConstraintValidator<VerifyValue, String> {

        Class<Enum> enumClass;

        @Override
        public void initialize(final VerifyValue enumObject) {
            enumClass = enumObject.value();

        }

        @Override
        public boolean isValid(final String myval, final ConstraintValidatorContext constraintValidatorContext) {

            if ((myval != null) && (enumClass != null)) {
                Enum[] enumValues = enumClass.getEnumConstants();
                Object enumValue = null;

                for (Enum enumerable : enumValues) {
                    if (myval.equals(enumerable.toString())) {
                        return true;
                    }
                    enumValue = getEnumValue(enumerable);
                    if ((enumValue != null)
                            && (myval.toString().equals(enumValue.toString()))) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Invokes the getValue() method for enum if present
         *
         * @param enumerable The Enum object
         * @return returns the value of enum from getValue() or enum constant
         */
        private Object getEnumValue(Enum enumerable) {
            try {
                for (Method method : enumerable.getClass().getDeclaredMethods()) {
                    if (method.getName().equals("getValue")) {
                        return method.invoke(enumerable);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
