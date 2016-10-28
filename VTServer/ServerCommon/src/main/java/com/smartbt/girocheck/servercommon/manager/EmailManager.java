/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.EmailDAO;
import com.smartbt.girocheck.servercommon.enums.EmailName;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.model.Email;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class EmailManager {
    public static EmailManager INSTANCE;
    EmailDAO dao = EmailDAO.get();
    
       public static EmailManager get() {
        if ( INSTANCE == null ) {
            INSTANCE = new EmailManager();
        }
        return INSTANCE;
    }

    public Email getByName( EmailName name ) {
        return dao.getByName( name );
    }
 
}
