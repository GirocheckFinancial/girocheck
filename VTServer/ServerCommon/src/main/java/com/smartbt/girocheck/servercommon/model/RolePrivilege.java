/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
public class RolePrivilege implements Serializable {
	public RolePrivilege() {
	}
	
	private int id;
	
	private Role role;
	
	private com.smartbt.girocheck.servercommon.model.Privilege privilege;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}

        /**
         * @return the role
         */
        public Role getRole() {
            return role;
        }

        /**
         * @param role the role to set
         */
        public void setRole(Role role) {
            this.role = role;
        }

	public void setPrivilege(com.smartbt.girocheck.servercommon.model.Privilege value) {
		this.privilege = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Privilege getPrivilege() {
		return privilege;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
