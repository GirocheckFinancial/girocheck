/*
 ** File: DashboardAMSDAO.java
 **
 ** Date Created: December 2013
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

import com.smartbt.vtsuite.servercommon.display.ams.AMSDashboardEnvironmental;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ariel Saavedra
 */
public class DashboardAMSDAO {

    /**
     *
     */
    protected static DashboardAMSDAO dao;

    /**
     *
     */
    public DashboardAMSDAO() {
    }

    /**
     *
     * @return
     */
    public static DashboardAMSDAO get() {
        if (dao == null) {
            dao = new DashboardAMSDAO();
        }
        return dao;
    }

    /**
     * Get list of environmental dashboard
     *
     * @return
     */
    public List<AMSDashboardEnvironmental> getDashboardEnvironmental() {
        List<AMSDashboardEnvironmental> dashboards = new LinkedList<AMSDashboardEnvironmental>();

        AMSDashboardEnvironmental d1 = new AMSDashboardEnvironmental("Collected Transactions", "32453", new Date());
        AMSDashboardEnvironmental d2 = new AMSDashboardEnvironmental("Approved Transactions", "32400", new Date());
        AMSDashboardEnvironmental d3 = new AMSDashboardEnvironmental("Declined Transactions", "53", new Date());
        AMSDashboardEnvironmental d4 = new AMSDashboardEnvironmental("TCMP Transactions", "458", new Date());
        AMSDashboardEnvironmental d5 = new AMSDashboardEnvironmental("TCMP TPS", "78", new Date());
        AMSDashboardEnvironmental d6 = new AMSDashboardEnvironmental("Direct Debit Transactions", "7076", new Date());
        AMSDashboardEnvironmental d7 = new AMSDashboardEnvironmental("Direct Debit TPS", "489", new Date());
        AMSDashboardEnvironmental d8 = new AMSDashboardEnvironmental("VT Server Instances", "12", new Date());
        AMSDashboardEnvironmental d9 = new AMSDashboardEnvironmental("Boarded Merchants", "323", new Date());
        AMSDashboardEnvironmental d10 = new AMSDashboardEnvironmental("Recurring Transactions", "2453", new Date());
        dashboards.add(d1);
        dashboards.add(d2);
        dashboards.add(d3);
        dashboards.add(d4);
        dashboards.add(d5);
        dashboards.add(d6);
        dashboards.add(d7);
        dashboards.add(d8);
        dashboards.add(d9);
        dashboards.add(d10);
        return dashboards;
    }
}
