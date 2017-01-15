package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.FeeBucketsDAO;
import com.smartbt.girocheck.servercommon.dao.FeeScheduleDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.model.FeeBuckets;
import com.smartbt.girocheck.servercommon.model.FeeBucketsDisplay;
import com.smartbt.girocheck.servercommon.model.FeeSchedules;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.ValidationException;

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
    FeeScheduleDAO feeScheduleDAO = FeeScheduleDAO.get(); 

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
    
     public ResponseDataList searchFeeBucket(int idFeeSchedule,int firstResult, int maxResult) {
        return feeBucketsDAO.searchFeeBuckets(idFeeSchedule,firstResult, maxResult);
    }
     
     public ResponseData addFeeBucket(FeeBucketsDisplay display) throws ValidationException, NoSuchAlgorithmException, Exception {
       
        ResponseData response = new ResponseData();       
        FeeBucketsDisplay feeBucketsDisplay = feeBucketsDAO.addFeeBucket(display.getMinimum(),display.getPercentage(),display.getFixed(),display.getFeeSchedule());
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
        response.setData(feeBucketsDisplay);
        return response;
    }
     
     
    public ResponseData updateFeeBucket(FeeBucketsDisplay display) throws ValidationException, NoSuchAlgorithmException, Exception {
       
        ResponseData response = new ResponseData();
        //FeeSchedules schedule = feeScheduleDAO.findById(display.getFeeSchedule().getId());  
        //display.setFeeSchedule(schedule);
        FeeBucketsDisplay feeBucketsDisplay = feeBucketsDAO.updateFeeBucket(display.getMinimum(),display.getPercentage(),display.getFixed(),display.getFeeSchedule(),display.getId());
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
        response.setData(feeBucketsDisplay);
        return response;
    }
  
    public BaseResponse deleteFeeBucket(int id) throws Exception { 
        //UserValidator.deleteFeeSchedule(id);
        BaseResponse response = new BaseResponse();
       //VTSessionDAO.get().deleteSessionByFeeschedule(id); 
        feeBucketsDAO.deleteFeeBucket(id);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
        return response;
    }
   

}
