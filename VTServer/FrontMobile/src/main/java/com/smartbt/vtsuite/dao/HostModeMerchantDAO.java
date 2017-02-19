
package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.HostModeMerchant;





/**
 *
 * @author Roberto Rodriguez
 */
public class HostModeMerchantDAO extends BaseDAO<HostModeMerchant> {

    protected static HostModeMerchantDAO dao;

    public static HostModeMerchantDAO get() {
        if (dao == null) {
            dao = new HostModeMerchantDAO();
        }
        return dao;
    }

     
    

}
