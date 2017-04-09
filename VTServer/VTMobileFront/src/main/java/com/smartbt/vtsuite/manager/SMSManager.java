/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.MobileClientDao;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.vtsuite.util.MobileMessage;
import com.smartbt.vtsuite.vtcommon.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author suresh
 */
@Service
@Transactional
public class SMSManager {

    protected static SMSManager _this;

    public static SMSManager get() {
        if (_this == null) {
            _this = new SMSManager();
        }
        return _this;
    }

    public ResponseData optOut(String recipentNumber, String messageKeyword) {

        ResponseData response = ResponseData.OK();
        try {
            if (recipentNumber != null && !recipentNumber.isEmpty() && messageKeyword != null && !messageKeyword.isEmpty()) {
                if (messageKeyword.contains("STOP") || messageKeyword.contains("stop")) {
                    MobileClient mobileClient = MobileClientDao.get().getMobileClientByTelphone(recipentNumber);
                    if (mobileClient != null) {
                        mobileClient.getClient().setExcludeSms(Boolean.TRUE);
                        MobileClientDao.get().saveOrUpdate(mobileClient);
                    }
                }
            }
        } catch (Exception e) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.ERROR_GENERAL);
            e.printStackTrace();
        }

        return response;
    }
}
