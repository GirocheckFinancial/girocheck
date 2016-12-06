package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.FeeBucketsDAO;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.model.FeeBuckets;
import java.math.BigDecimal;
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

    public Map getFees(String merchantId, String operation, String amountString) {
        System.out.println("FeeBucketsManager:: getFees() -> amountString = " + amountString);
        float amount = 0;
        Float calculatedFee = 0F;
        Map responseMap = new HashMap();
        try {
            amount = Float.parseFloat(amountString);
        } catch (Exception e) {
            System.out.println("[FeeBucketsManager] FeeBucketsManager() converting amount from string to double fail.");
            e.printStackTrace();
        }

        FeeBuckets bucket = feeBucketsDAO.getFees(merchantId, operation, amount);
        if (bucket != null) {
            System.out.println("FeeBucketsManager:: found bucket ");
            responseMap = bucket.toMap();
            Float fee = 0F;

            fee = bucket.getFixed() + amount * (bucket.getPercentage() / 100);

            BigDecimal bd = new BigDecimal(fee).setScale(2, BigDecimal.ROUND_HALF_UP);
            calculatedFee = Float.parseFloat(bd.toString());

            System.out.println("***[FeeBucketsManager] FeeBucketsManager() final fee result: " + responseMap.get(ParameterName.CRDLDF));
        } else {
            System.out.println("FeeBucketsManager:: bucket is NULL");
        }
        System.out.println("FeeBucketsManager::calculatedFee = " + calculatedFee);
        responseMap.put(ParameterName.CRDLDF, calculatedFee);

        return responseMap;
    }

    //provitional method for calculate checks
    private Float getFeeForChecks(float amount) {
        if (amount <= 295) {
            return 2.95F;
        } else {
            return amount * 0.01F;
        }
    }

    public static void main(String args[]) {
        float f = 3.1823232F;
        BigDecimal bd = new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(Float.parseFloat(bd.toString()));
    }

}
