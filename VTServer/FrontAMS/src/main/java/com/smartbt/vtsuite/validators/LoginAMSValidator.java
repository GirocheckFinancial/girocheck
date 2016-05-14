/**
 *
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be reproduced, transmitted,
 * transcribed, stored in a retrieval system, or translated into any language or
 * computer language, in any form or by any means, electronic, mechanical,
 * magnetic, optical, chemical, manual or otherwise, without the prior written
 * permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.validators;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
public class LoginAMSValidator {

    private static final Logger log = Logger.getLogger( LoginAMSValidator.class );

    public static void authenticateUser( String username, String password ) throws ValidationException {
        if ( username == null ) {
            throw new ValidationException( VTSuiteMessages.USERNAME_IS_NULL );
        }
        if( password == null ) {
            throw new ValidationException( VTSuiteMessages.PASSWORD_IS_NULL);
        }
    }
    
    public static void encryptPassword( String password ) throws ValidationException {
         if (  password == null ) {
            throw new ValidationException( VTSuiteMessages.CANNOT_ENCRYPT_NULL_PASSWORD );
        }
    }

}
