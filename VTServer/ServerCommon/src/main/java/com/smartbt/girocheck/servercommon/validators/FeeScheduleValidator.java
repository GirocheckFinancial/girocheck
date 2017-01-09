 
package com.smartbt.girocheck.servercommon.validators;

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.FeeScheduleDAO;
import com.smartbt.girocheck.servercommon.display.FeeScheduleDisplay;
import com.smartbt.girocheck.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author suresh
 */


public class FeeScheduleValidator {
    
  private static final Logger log = Logger.getLogger(UserValidator.class);

    /**
     * Search all the Users by a given filter Validator
     *
     * @param search
     * @param pageNumber
     * @param rowsPerPage
     * @throws java.lang.Exception
     */
    public static void searchFeeSchedule(String search, int pageNumber, int rowsPerPage) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_FEEMANAGEMENT.getId());
        }
        UtilValidator.validateSearchFilter(search);
    }
    
    public static void addFeeSchedule(FeeScheduleDisplay fee) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_FEEMANAGEMENT.getId());
        }
        if (FeeScheduleDAO.get().findById(fee.getId()) == null) {
            log.info("----->  updateUser: This FeeSchedule does not exist <-----");
            throw new ValidationException(VTSuiteMessages.FEESCHEDULE_DOES_NOT_EXIST);
        }
    }
//
//    /**
//     * Update a User Validator
//     *
//     * @param user
//     * @throws java.lang.Exception
//     */
//    public static void updateFeeSchedule(FeeScheduleDisplay fee) throws Exception {
//        if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId()
//                    , NomUserPrivileges.ALLOW_ADMINISTRATION_USERS.getId()
//                    , NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE.getId());
//        }
//        if (UserDAO.get().findById(user.getId()) == null) {
//            log.info("----->  updateUser: This User does not exist <-----");
//            throw new ValidationException(VTSuiteMessages.USER_DOES_NOT_EXIST);
//        }
//    }
//    
    
//        
//    public static void deleteUser(int user) throws Exception {
//        if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_USERS.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE.getId());
//        }
//        if (UserDAO.get().findById(user) == null) {
//            log.info("----->  updateUser: This User does not exist <-----");
//            throw new ValidationException(VTSuiteMessages.USER_DOES_NOT_EXIST);
//        }
//    }
//      
//    
}
