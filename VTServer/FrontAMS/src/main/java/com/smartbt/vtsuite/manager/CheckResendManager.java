package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.dao.CheckResendDAO;
import com.smartbt.girocheck.servercommon.display.CheckDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.Check;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author suresh
 */
public class CheckResendManager {

    private CheckResendDAO checkResendDAO = CheckResendDAO.get();    
    protected static CheckResendManager _this;

    public static CheckResendManager get() {
        if (_this == null) {
            _this = new CheckResendManager();
        }
        return _this;
    }

    public ResponseDataList searchCheckDetails(int firstResult, int maxResult, String status, Date startRangeDateStr, Date endRangeDateStr) {
        return checkResendDAO.searchCheckDetails(firstResult, maxResult, status, startRangeDateStr, endRangeDateStr);
    }

    public BaseResponse resendCheck(int id) {
        System.out.println("[CheckResendManager] resendCheck()");
        ResponseData response = new ResponseData();
        String checkId = String.valueOf(id);
        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckResendManager] processTransaction " + checkId, null);

        try {
            Check check = checkResendDAO.getCheckDetails(id);
            Map map = new HashMap();
            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.ISTREAM2_SEND_SINGLE_ICL);
            String prodProperty = System.getProperty("PROD");
            Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");
            if (isProd) {
                String userName = System.getProperty("ISTREAM2_USER_NAME");
                String password = System.getProperty("ISTREAM2_PWD");
                map.put(ParameterName.USER, userName);
                map.put(ParameterName.PASSWORD, password);

            } else {
                map.put(ParameterName.USER, "GCTLS");
                map.put(ParameterName.PASSWORD, "sts283");
                map.put(ParameterName.DEPOSIT, "4pm Deposit");
            }
            map.put(ParameterName.LOCATION_ID, check.getLocationId());
            
            DecimalFormat twoPlaces = new DecimalFormat("0.00");
            String amountString = twoPlaces.format(check.getTransaction().getAmmount());            
            
            String originalMICR = check.getMicr();
            String translatedMICR = translateMICR(originalMICR);           
            
            map.put(ParameterName.AMMOUNT, amountString);
            map.put(ParameterName.MICR, translatedMICR);
            map.put(ParameterName.CHECK_ID, check.getId());
            map.put(ParameterName.CHECK_FRONT, check.getCheckFront());
            map.put(ParameterName.CHECK_BACK, check.getCheckBack());

            direxTransactionRequest.setTransactionData(map);
            direxTransactionRequest.setCorrelation(checkId);
            direxTransactionRequest.setTransactionType(TransactionType.ISTREAM2_SEND_SINGLE_ICL);            

            DirexTransactionResponse iStream2Response = IStream2HostManager.get().processTransaction(direxTransactionRequest);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "iStream2Response " + iStream2Response, null);

            if (iStream2Response != null && iStream2Response.wasApproved()) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "iStream2Response.wasApproved()" + iStream2Response.wasApproved(), null);
                CheckDisplay checkDisplay = checkResendDAO.resendCheck(id);
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "iStream2Response.wasApproved() = true" + checkDisplay, null);
                if (checkDisplay != null) {
                    response.setStatus(Constants.CODE_SUCCESS);
                    response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckResendManager] processTransaction finished" + checkId, null);
                } else {
                    response.setStatus(Constants.CODE_ERROR_GENERAL);
                    response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.ERROR_GENERAL);
                }
            } else {
                response.setStatus(Constants.CODE_ERROR_GENERAL);
                response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.ERROR_GENERAL);
            }
        } catch (Exception e) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.ERROR_GENERAL);
            e.printStackTrace();
        }

        return response;
    }

    private String translateMICR(String originalMICR) {
        if (originalMICR != null) {
            return originalMICR.replace("C", "<").replace("A", ":").replace("D", "=");
        }
        return "";
    }
}
