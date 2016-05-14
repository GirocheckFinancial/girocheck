/*
 ** File: MerchantDAO.java
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
package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.TerminalHost;


/**
 *
 * @author Roberto Rodriguez
 */
public class TerminalHostDAO extends BaseDAO<TerminalHost> {

    protected static TerminalHostDAO dao;

    public TerminalHostDAO() {
        //super(Merchant.class);
    }

    public static TerminalHostDAO get() {
        if (dao == null) {
            dao = new TerminalHostDAO();
        }
        return dao;
    }

    
}
