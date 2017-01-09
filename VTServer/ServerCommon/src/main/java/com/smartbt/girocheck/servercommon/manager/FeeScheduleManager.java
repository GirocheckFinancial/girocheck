 package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.FeeScheduleDAO;
import com.smartbt.girocheck.servercommon.display.FeeScheduleDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.ValidationException;

/**
 *
 * @author suresh
 */

public class FeeScheduleManager {
    
   private FeeScheduleDAO feeScheduleDAO = FeeScheduleDAO.get();
    
     protected static FeeScheduleManager _this;
 
    public static FeeScheduleManager get() {
        if (_this == null) {
            _this = new FeeScheduleManager();
        }
        return _this;
    }
    
    public ResponseDataList searchFeeSchedule(int firstResult, int maxResult) {
        return feeScheduleDAO.searchFeeSchedule(firstResult, maxResult);
    }
    
   public ResponseData addFeeSchedule(FeeScheduleDisplay display) throws ValidationException, NoSuchAlgorithmException, Exception {
       // FeeScheduleValidator.addFeeSchedule(display);
        ResponseData response = new ResponseData();
        Merchant merchant = null;
        
        if(display.getMerchant()!=null){
            merchant= new Merchant();
            merchant.setId(display.getMerchant());                   
        }
        boolean isExists= feeScheduleDAO.checkFeeScheduleMethodExists(merchant, display.getMethod().getId());
        if(isExists){
                response.setStatus(Constants.FEE_RECORD_EXISTS);
                response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.ERROR_FEE_SCHEDULE_METHOD);
                response.setData(display);
                return response;
        }           
        FeeScheduleDisplay feeScheduleDisplay = feeScheduleDAO.addFeeSchedule(merchant, display.getMethod(), display.getIsdefault());
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
        response.setData(feeScheduleDisplay);
        return response;
    }
    
/**
     * Update a FeeSchedule
     *
     * @param FeeSchedule
     * @return
     * @throws java.lang.Exception
     */
   public ResponseData updateFeeSchedule(FeeScheduleDisplay display) throws ValidationException, NoSuchAlgorithmException, Exception {
       // FeeScheduleValidator.addFeeSchedule(display);
        ResponseData response = new ResponseData();
        
        Merchant merchant = null;
        if(display.getMerchant()!=null){
            merchant= new Merchant();
            merchant.setId(display.getMerchant());
        }
        boolean isExists= feeScheduleDAO.checkFeeScheduleMethodExists(merchant, display.getMethod().getId(),display.getId());
        if(isExists){
                response.setStatus(Constants.FEE_RECORD_EXISTS);
                response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.ERROR_FEE_SCHEDULE_METHOD);
                response.setData(display);
                return response;
        }  
        FeeScheduleDisplay feeScheduleDisplay = feeScheduleDAO.updateFeeSchedule(merchant, display.getMethod(), display.getIsdefault(),display.getId());
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
        response.setData(feeScheduleDisplay);
        return response;
    }
    
    public BaseResponse deleteFeeSchedule(int id) throws Exception { 
        //UserValidator.deleteFeeSchedule(id);
        BaseResponse response = new BaseResponse();
       //VTSessionDAO.get().deleteSessionByFeeschedule(id); 
        feeScheduleDAO.deleteFeeSchedule(id);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
        return response;
    }
   
    
     public ResponseDataList searchTransactionMethod() {
        return feeScheduleDAO.searchTransactionMethod();
    }

    
}
