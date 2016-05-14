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

import com.smartbt.girocheck.servercommon.dao.AgrupationDAO;
import com.smartbt.girocheck.servercommon.display.AgrupationDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Agrupation;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.List;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class AgrupationManager {

    private AgrupationDAO dao = AgrupationDAO.get();

    public ResponseDataList<Agrupation> listAgrupations() {
        ResponseDataList<Agrupation> response = new ResponseDataList<Agrupation>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        List list = dao.listAgrupations();
        response.setData( list );
        return response;
    }

    public ResponseData<AgrupationDisplay> saveOrUpdateAgrupation( AgrupationDisplay display ) {
        ResponseData<AgrupationDisplay> response = new ResponseData<AgrupationDisplay>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );

        Agrupation agrupation = new Agrupation();
        if ( display.getId() != null ) {
            agrupation.setId( display.getId() );
        }

        agrupation.setName( display.getName() );
        agrupation.setDescription( display.getDescription() );

        dao.saveOrUpdate( agrupation );

        display.setId( agrupation.getId() );
        response.setData( display );
        return response;
    }

    public ResponseData getAgrupation( int idAgrupation ) {
        ResponseData<AgrupationDisplay> response = new ResponseData<AgrupationDisplay>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );

        Agrupation agrupation = dao.findById( idAgrupation );

        AgrupationDisplay display = new AgrupationDisplay();
        display.setId( agrupation.getId() );
        display.setName( agrupation.getName() );
        display.setDescription( agrupation.getDescription() );
        display.setHasTransaction( dao.hasTransaction( idAgrupation ) );

        response.setData( display );

        return response;
    }

    public BaseResponse deleteAgrupation( int idAgrupation ) {
        BaseResponse response = new BaseResponse();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        dao.deleteAgrupation( idAgrupation );
        return response;
    }

    public ResponseDataList<Agrupation> searchAgrupations( String search ) {
        ResponseDataList<Agrupation> response = new ResponseDataList<Agrupation>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        List list = dao.searchAgrupations( search );
        response.setData( list );
        return response;
    }
}
