package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.FeeBucketsDAO;
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
    
    public Map getFees(String merchantId, String operation, String amount){
        float am = 0;
        Map responseMap = new HashMap();
        try{
            am = Float.parseFloat(amount);
        }catch(Exception e){
            System.out.println("[FeeBucketsManager] FeeBucketsManager() converting amount from string to double fail.");
            e.printStackTrace();
        }
        FeeBuckets bucket = feeBucketsDAO.getFees(merchantId, operation, am);
        if(bucket != null){
            float fee = bucket.getFixed()+am*(bucket.getPercentage()/100);
            System.out.println("[FeeBucketsManager] FeeBucketsManager() final fee result: " + fee);
            responseMap.put("FINALFEE", fee);
            responseMap.put("BUCKET", bucket);
        }
        return responseMap;
        
    }

}
