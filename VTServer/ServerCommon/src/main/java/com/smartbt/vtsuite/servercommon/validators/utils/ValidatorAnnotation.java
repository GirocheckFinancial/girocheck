/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.validators.utils;

import com.smartbt.vtsuite.servercommon.validators.utils.CustomsAnnotations.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 * @author Carlos
 */
public class ValidatorAnnotation {

    /**
     * Process all annotations for validations
     *
     * @param obj
     * @throws Exception
     */
    public void processAnnotations(Object obj) throws Exception {

        Class cl = obj.getClass();

        for (Field f : cl.getDeclaredFields()) {

            for (Annotation a : f.getAnnotations()) {
                //Checking for a NullValueValidate annotation
                if (a.annotationType() == EmailAdressValid.class) {
                    EmailAdressValid var = (EmailAdressValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isEmailValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == PhoneNumberValid.class) {
                    PhoneNumberValid var = (PhoneNumberValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isPhoneNumberValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == NumericValid.class) {
                    NumericValid var = (NumericValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isFloatValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == SsnValid.class) {
                    SsnValid var = (SsnValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isSsnValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == DateValid.class) {
                    DateValid var = (DateValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isDate(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == TimeValid.class) {
                    TimeValid var = (TimeValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isTime(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == TerminalValid.class) {
                    TerminalValid var = (TerminalValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isTerminalValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == MerchantValid.class) {
                    MerchantValid var = (MerchantValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isMerchantValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == PanValid.class) {
                    PanValid var = (PanValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isPanValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == ExpDateValidMMYY.class) {
                    ExpDateValidMMYY var = (ExpDateValidMMYY) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isExpDateValidMMYY(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == ExpDateValidYYMM.class) {
                    ExpDateValidYYMM var = (ExpDateValidYYMM) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isExpDateValidYYMM(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == Tk2Valid.class) {
                    Tk2Valid var = (Tk2Valid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isTk2Valid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == CvvValid.class) {
                    CvvValid var = (CvvValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isCvvValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == CvvFlagValid.class) {
                    CvvFlagValid var = (CvvFlagValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isCvvFlagValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == IntegerValid.class) {
                    IntegerValid var = (IntegerValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isIntegerValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == ModeValid.class) {
                    ModeValid var = (ModeValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isModeValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == OperationValid.class) {
                    OperationValid var = (OperationValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isOperationValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == BooleanValid.class) {
                    BooleanValid var = (BooleanValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isBooleanValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == EncryptFormatValid.class) {
                    EncryptFormatValid var = (EncryptFormatValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isEncryptFormatValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                } else if (a.annotationType() == HexadecimalValid.class) {
                    HexadecimalValid var = (HexadecimalValid) a;
                    f.setAccessible(true);
                    if (f.get(obj) != null) {
                        if (!f.get(obj).equals("")) {
                            if (!Validator.isHexadecimalValid(f.get(obj).toString())) {
                                throw new Exception(var.message());
                            }
                        }
                    }
                }
            }
        }

    }
}
