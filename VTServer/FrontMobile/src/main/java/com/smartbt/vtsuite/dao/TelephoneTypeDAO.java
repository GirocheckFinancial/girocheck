/*
 ** File: ApplicationParameterDAO.java
 **
 ** Date Created: January 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
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
import com.smartbt.vtsuite.servercommon.model.TelephoneType;





/**
 *
 * @author Roberto Rodriguez
 */
public class TelephoneTypeDAO extends BaseDAO<TelephoneType> {

    protected static TelephoneTypeDAO dao;

    public static TelephoneTypeDAO get() {
        if (dao == null) {
            dao = new TelephoneTypeDAO();
        }
        return dao;
    }

     
    

}
