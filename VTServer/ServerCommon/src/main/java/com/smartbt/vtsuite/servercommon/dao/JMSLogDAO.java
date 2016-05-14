/*
 ** File: JMSLogDAO.java
 **
 ** Date Created: October 2013
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
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.JMSLog;

/**
 *
 * @author Eduardo Juarez
 */
public class JMSLogDAO extends BaseDAO<JMSLog> {
    
    protected static JMSLogDAO dao;
    
    public JMSLogDAO() {
        //super(Account.class);
    }
    
    public static JMSLogDAO get() {
        if (dao == null) {
            dao = new JMSLogDAO();
        }
        return dao;
    }
    
}
