package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.HostDAO;
import com.smartbt.girocheck.servercommon.model.Host;

/**
 *
 * @author Alejo
 */


public class HostManager {
    
    private HostDAO dao = HostDAO.get();
    
    public Host getHostByBinNumber( String binNumber){
        return dao.getHostByBinNumber(binNumber);
    }
    
    public Host getDefaultHost(){
        return dao.getDefaultHost();
    }
    
}
