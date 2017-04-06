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

import com.smartbt.girocheck.servercommon.dao.StateDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.enums.EnumState;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.display.common.model.StateDisplay;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.List;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class StateManager {

    private StateDAO dao = StateDAO.get();

    private static StateManager INSTANCE;

    public static synchronized StateManager get() {
        if (INSTANCE == null) {
            INSTANCE = new StateManager();
        }
        return INSTANCE;
    }

    public State getByAbbreviation(String abbreviation) {
        State state = dao.getByAbbreviation(abbreviation);

        if (state == null) {
            return getByAbbreviation(EnumState.FL.toString());
        }
        return state;
    }

    public ResponseDataList<StateDisplay> listStates() {
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        List<StateDisplay> list = dao.listStates();
        response.setData(list);
        return response;
    }
}
