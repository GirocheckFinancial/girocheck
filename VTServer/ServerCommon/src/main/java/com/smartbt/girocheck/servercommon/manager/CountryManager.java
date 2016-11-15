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

import com.smartbt.girocheck.servercommon.dao.CountryDAO;
import com.smartbt.girocheck.servercommon.display.CountryDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.List;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CountryManager {
    private static CountryManager INSTANCE;
    
    public static synchronized CountryManager get(){
        if(INSTANCE == null) {
            INSTANCE = new CountryManager();
        }
        return INSTANCE;
    }

    CountryDAO dao = CountryDAO.get();

    public Country getByAbbreviation( String abbreviation ) {
        return dao.getByAbbreviation( abbreviation );
    }

    public ResponseDataList listCountries() {
        ResponseDataList response = new ResponseDataList();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );

        List<CountryDisplay> list = dao.listCountries();

        response.setData( list );
        return response;
    }
}
