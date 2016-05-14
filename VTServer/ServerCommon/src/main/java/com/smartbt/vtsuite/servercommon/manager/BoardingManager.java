/*
 ** File: BoardingManager.java
 **
 ** Date Created: March 2013
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
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.dao.BoardingDAO;
import com.smartbt.vtsuite.servercommon.validators.BoardingValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class BoardingManager {

    private static final Logger log = Logger.getLogger( BoardingManager.class );


    private BoardingDAO boardingDAO = BoardingDAO.get();
//    private BoardingAMSDAO boardingAMSDAO = BoardingAMSDAO.get();

    /**
     * Search all the BoardingStatus by a given filter
     *
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param pageNumber
     * @param rowsPerPage
     * @return
     */
    public ResponseDataList searchBoardingStatus( String searchFilter, Date startRangeDate, Date endRangeDate, int pageNumber, int rowsPerPage ) throws Exception {
        BoardingValidator.searchBoardingStatus( searchFilter, startRangeDate, endRangeDate, pageNumber, rowsPerPage );
        ResponseDataList response = new ResponseDataList();

        response.setData( boardingDAO.searchBoardingStatus( searchFilter, startRangeDate, endRangeDate, pageNumber * rowsPerPage, rowsPerPage ) );
        int total = boardingDAO.searchBoardingStatus( searchFilter, startRangeDate, endRangeDate, -1, -1 ).size();
        response.setTotalPages( (int) Math.ceil( (float) total / (float) rowsPerPage ) );

        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );

        return response;

    }

}
