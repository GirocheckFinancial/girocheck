package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.FeeBucketsDAO;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.model.FeeBuckets;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alejo
 */
public class FeeBucketsManager {

    protected static FeeBucketsManager feeBuckets;

    public static FeeBucketsManager get() {
        if (feeBuckets == null) {
            feeBuckets = new FeeBucketsManager();
        }
        return feeBuckets;
    }

    FeeBucketsDAO feeBucketsDAO = FeeBucketsDAO.get();
    
    public Map getFees(String merchantId, String operation, String amountString){
        float amount = 0;
        Map responseMap = new HashMap();
        try{
            amount = Float.parseFloat(amountString);
        }catch(Exception e){
            System.out.println("[FeeBucketsManager] FeeBucketsManager() converting amount from string to double fail.");
            e.printStackTrace();
        }
        FeeBuckets bucket = feeBucketsDAO.getFees(merchantId, operation, amount);
        if(bucket != null){
            Float fee = bucket.getFixed()+amount*(bucket.getPercentage()/100);
            System.out.println("[FeeBucketsManager] FeeBucketsManager() final fee result: " + fee);
            responseMap = bucket.toMap();
            responseMap.put(ParameterName.CRDLDF, fee);
        }
        return responseMap;
        
    }

}
