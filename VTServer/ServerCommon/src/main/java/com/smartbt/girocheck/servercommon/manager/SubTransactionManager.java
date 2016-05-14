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

import com.smartbt.girocheck.servercommon.dao.SubTransactionDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.Date;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class SubTransactionManager {
    SubTransactionDAO subTransactionDAO = SubTransactionDAO.get();
    
    public void save(SubTransaction subTransaction){
        subTransactionDAO.save( subTransaction );
    }
    
       public ResponseDataList listSubTransactions(int idTransaction) throws Exception {
        ResponseDataList response = new ResponseDataList();

        response.setData(subTransactionDAO.listByTransaction(idTransaction));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
}
