

package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.PrivilegeDAO;
import com.smartbt.girocheck.servercommon.dao.RoleDAO;
import com.smartbt.girocheck.servercommon.dao.RolePrivilegeDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Privilege;
import com.smartbt.girocheck.servercommon.model.Role;
import com.smartbt.girocheck.servercommon.model.RolePrivilege;
import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.validators.RoleValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.List;
import javax.xml.bind.ValidationException;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class RoleManager {
     private RoleDAO roleDAO = RoleDAO.get();
     PrivilegeDAO privilegeDAO = PrivilegeDAO.get();
             private RolePrivilegeDAO rolePrivilegeDAO = RolePrivilegeDAO.get();
     
     public List<Role> list() throws Exception{
         RoleValidator.getRoles(EntityType.AMS);
         
//         System.out.println("before save Rol");
//         Role rol = new Role();
//         rol.setName("Cool Role");
//         dao.saveOrUpdate(rol);
//         
//         Privilege privilege = new Privilege();
//         privilege.setName("Cool Privilege");
//         
//         privilegeDAO.saveOrUpdate(privilege);
//         
//         
//         RolePrivilege rolPrivilege = new RolePrivilege();
//         rolPrivilege.setRol(rol);
//         rolPrivilege.setPrivilege(privilege);
//         rolePrivilegeDAO.saveOrUpdate(rolPrivilege);
//         
//         
//         System.out.println("after save Rol");
//         
         return roleDAO.list();
     }
     
    public ResponseDataList getRoles(EntityType entityType) throws Exception {
        
        RoleValidator.getRoles(entityType);
        
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(roleDAO.getRoles(entityType));
        return response;
    }

    public BaseResponse deleteRole(int idRole) throws Exception {
        RoleValidator.deleteRole(idRole);
        BaseResponse response = new BaseResponse();
//            if (!frontFacade.existObject(ClerkRole.class, role.getId())) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.CLERK_ROLE_DOES_NOT_EXIST);
//                log.info("----->  updateClerkRole: This ClerkRole does not exist <-----");
//            } else {
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        roleDAO.deleteRole(idRole);
//            }
        return response;
    }

    public BaseResponse updateRole(EntityType entityType, int idRole, String name, String description) {
        BaseResponse response = new BaseResponse();
//            if (!frontFacade.existObject(ClerkRole.class, role.getId())) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.CLERK_ROLE_DOES_NOT_EXIST);
//                log.info("----->  updateClerkRole: This ClerkRole does not exist <-----");
//            } else {
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        roleDAO.updateRole(entityType, idRole, name, description);
//            }
        return response;
    }

    public BaseResponse addRole(EntityType entityType, String name, String description) throws ValidationException, Exception {
        RoleValidator.addRole(entityType, name, description);
        BaseResponse response = new BaseResponse();
        roleDAO.addRole(entityType, name, description);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
     
     
    
}
