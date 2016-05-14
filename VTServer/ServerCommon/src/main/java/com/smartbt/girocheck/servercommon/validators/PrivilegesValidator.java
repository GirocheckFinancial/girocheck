package com.smartbt.girocheck.servercommon.validators;

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.PrivilegesDAO;
import com.smartbt.girocheck.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Alejo
 */


public class PrivilegesValidator {
     private static final Logger log = Logger.getLogger(UserValidator.class);
     
    public static void searchRolePrivileges(String search, int pageNumber, int rowsPerPage) throws Exception {
        
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId()
                    , NomUserPrivileges.ALLOW_ADMINISTRATION_USERS.getId());
        }
        UtilValidator.validateSearchFilter(search);
    }
    
    public static void addRolePrivilege(int idRole, int idPrivilege) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_ADD_PRIVILEGE.getId());
        }
//        if (UserDAO.get().findById(user.getId()) == null) {
//            log.info("----->  updateUser: This User does not exist <-----");
//            throw new ValidationException(VTSuiteMessages.USER_DOES_NOT_EXIST);
//        }
    }
        
    public static void deleteRolePrivilege(int rolePrivilegeId) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_DELETE_PRIVILEGE.getId());
        }
//        if (PrivilegesDAO.get().findById(idPrivilege) == null) {
//            log.info("----->  updateUser: This Privilege does not exist <-----");
//            throw new ValidationException(VTSuiteMessages.PRIVILEGE_DOES_NOT_EXIST);
//        }
    }
     
}
