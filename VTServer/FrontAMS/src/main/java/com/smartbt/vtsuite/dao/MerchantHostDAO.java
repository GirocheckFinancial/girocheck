
package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.MerchantHost;


/**
 *
 * @author Roberto Rodriguez
 */
public class MerchantHostDAO extends BaseDAO<MerchantHost> {

    protected static MerchantHostDAO dao;


    public static MerchantHostDAO get() {
        if (dao == null) {
            dao = new MerchantHostDAO();
        }
        return dao;
    }

    
}
