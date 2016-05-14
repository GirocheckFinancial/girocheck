/*
 *  File ParameterValueValidator
 * 
 *  Date Created: January 2014
 * 
 *  Copyright @ @ 2004-2014 Smart Business Technology, Inc.
 *
 *  All rights reserved. No part of this software may be 
 *  reproduced, transmitted, transcribed, stored in a retrieval 
 *  system, or translated into any language or computer language,
 *  in any form or by any means, electronic, mechanical, magnetic, 
 *  optical, chemical, manual or otherwise, without the prior 
 *  written permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.servercommon.validators;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.ParameterValueDAO;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue;
import com.smartbt.vtsuite.servercommon.model.DataType;
import com.smartbt.vtsuite.servercommon.model.MerchantParameter;
import com.smartbt.vtsuite.servercommon.model.MerchantParameterValue;
import com.smartbt.vtsuite.servercommon.model.TerminalParameterValue;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomDataType;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.servercommon.validators.utils.Validator;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class ParameterValueValidator {

    private static final Logger log = Logger.getLogger(ParameterValueValidator.class);

    public static void addParameterValue(int idEntity, EntityType entityType, int idParameter, String value) throws ValidationException {
        Object parameter = ParameterValueDAO.get().getParameter(entityType, idParameter);
        if (parameter == null) {
            throw new ValidationException(VTSuiteMessages.PARAMETER_DOES_NOT_EXIST);
        }

        if (ParameterValueDAO.get().isParameterValueInserted(idEntity, entityType, idParameter)) {
            throw new ValidationException(VTSuiteMessages.ASSOCIATION_ALREADY_EXIST);
        }

        DataType dataType = null;
        if (parameter instanceof MerchantParameter) {
            dataType = ((MerchantParameter) parameter).getDataType();
        }
        if (parameter instanceof ApplicationParameter) {
            dataType = ((ApplicationParameter) parameter).getDataType();
        }

        validateDataType(dataType, value);
    }

    public static void updateParameterValue(EntityType entityType, int idParameterValue, String value) throws ValidationException {
        Object parameterValue = ParameterValueDAO.get().getParameterValue(entityType, idParameterValue);
        if (parameterValue == null) {
            throw new ValidationException(VTSuiteMessages.PARAMETER_VALUE_DOES_NOT_EXIST);
        }
        DataType dataType = null;
        if (parameterValue instanceof TerminalParameterValue) {
            dataType = ((TerminalParameterValue) parameterValue).getApplicationParameter().getDataType();
        }
        if (parameterValue instanceof MerchantParameterValue) {
            dataType = ((MerchantParameterValue) parameterValue).getMerchantParameter().getDataType();
        }
        if (parameterValue instanceof ApplicationParameterValue) {
            dataType = ((ApplicationParameterValue) parameterValue).getApplicationParameter().getDataType();
        }

        validateDataType(dataType, value);
    }

    public static void validateDataType(DataType dataType, String value) throws ValidationException {
        if (dataType != null) {
            if (value == null || value.isEmpty()) {
                throw new ValidationException(VTSuiteMessages.VALUE_IS_EMPTY);
            }

            if (dataType.getId() == NomDataType.BOOLEAN.getId() && !Validator.isBooleanValid(value)) {
                throw new ValidationException(VTSuiteMessages.VALUE_DATA_TYPE_IS_INCORRECT);
            }
            if (dataType.getId() == NomDataType.DOUBLE.getId()
                    && (!Validator.isFloatValid(value) || value.length() > Constants.STANDARD_TEXT_MAX_LENGTH)) {
                throw new ValidationException(VTSuiteMessages.VALUE_DATA_TYPE_IS_INCORRECT);
            }
            if (dataType.getId() == NomDataType.EMAIL.getId()
                    && (!Validator.isEmailValid(value) || value.length() > Constants.MEDIUM_TEXT_MAX_LENGTH)) {
                throw new ValidationException(VTSuiteMessages.VALUE_DATA_TYPE_IS_INCORRECT);
            }
            if (dataType.getId() == NomDataType.INTEGER.getId()
                    && (!Validator.isIntegerValid(value) || value.length() > Constants.STANDARD_TEXT_MAX_LENGTH)) {
                throw new ValidationException(VTSuiteMessages.VALUE_DATA_TYPE_IS_INCORRECT);
            }
            if (dataType.getId() == NomDataType.STRING.getId()
                    && (!Validator.isTextVIValid(value) || value.length() > Constants.MEDIUM_TEXT_MAX_LENGTH)) {
                throw new ValidationException(VTSuiteMessages.VALUE_DATA_TYPE_IS_INCORRECT);
            }
//            if (dataType.getId() == NomDataType.DATE.getId() && !ValidatorsPatterns.isDate(value)) {
//            }
        }
    }

    public static void deleteParameterValue(EntityType entityType, int idParameterValue) throws ValidationException {
        Object parameterValue = ParameterValueDAO.get().getParameterValue(entityType, idParameterValue);
        if (parameterValue == null) {
            throw new ValidationException(VTSuiteMessages.PARAMETER_VALUE_DOES_NOT_EXIST);
        }

        if ((parameterValue instanceof ApplicationParameterValue && ((ApplicationParameterValue) parameterValue).getApplicationParameter().isReadOnly())
                || (parameterValue instanceof MerchantParameterValue)
                || (parameterValue instanceof TerminalParameterValue && ((TerminalParameterValue) parameterValue).getApplicationParameter().isReadOnly()))  {
            throw new ValidationException(VTSuiteMessages.PARAMETER_READ_ONLY);
        }
    }
}
