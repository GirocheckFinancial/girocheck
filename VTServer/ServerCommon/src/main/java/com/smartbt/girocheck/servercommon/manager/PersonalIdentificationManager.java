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

import com.smartbt.girocheck.servercommon.dao.PersonalIdentificationDAO;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import java.sql.SQLException;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class PersonalIdentificationManager {

    private PersonalIdentificationDAO dao = PersonalIdentificationDAO.get();

    protected static PersonalIdentificationManager _this;

    public static PersonalIdentificationManager get() {
        if (_this == null) {
            _this = new PersonalIdentificationManager();
        }
        return _this;
    }

    public void removeByClientAndType(int idClient, int idType, Integer currentIdentificationId) {
        dao.removeByClientAndType(idClient, idType, currentIdentificationId);
    }
    
    public PersonalIdentification getByClientId( int idClient) throws SQLException {
        return dao.getByClientId(idClient);
    }
    
    public void saveOrUpdate(PersonalIdentification personalIdentification){
        dao.saveOrUpdate(personalIdentification);
    }
}
