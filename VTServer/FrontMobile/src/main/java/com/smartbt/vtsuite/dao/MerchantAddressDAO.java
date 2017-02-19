
package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.MerchantAddress;



/**
 *
 * @author Roberto Rodriguez
 */
public class MerchantAddressDAO extends BaseDAO<MerchantAddress> {

    protected static MerchantAddressDAO dao;

    public static MerchantAddressDAO get() {
        if (dao == null) {
            dao = new MerchantAddressDAO();
        }
        return dao;
    }

     

}
