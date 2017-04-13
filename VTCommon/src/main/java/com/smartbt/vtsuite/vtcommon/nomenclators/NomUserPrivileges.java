/*
 ** File: UserPrivilegesValidator.java
 **
 ** Date Created: December 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtcommon.nomenclators;

/**
 *
 * UserPrivilegesValidator class
 */
public enum NomUserPrivileges {

    
      ALLOW_BOARDING(1),
      ALLOW_BOARDING_MANAGEMENT(12),
      ALLOW_BOARDING_MANAGEMENT_REMOVE(17),
      ALLOW_BOARDING_MANAGEMENT_AGRUPATION(13),
      ALLOW_BOARDING_MANAGEMENT_AGRUPATION_ADD(18),
      ALLOW_BOARDING_MANAGEMENT_AGRUPATION_ADD_MERCHANT(21),
      ALLOW_BOARDING_MANAGEMENT_MERCHANT_ADD(19),
      ALLOW_BOARDING_MANAGEMENT_MERCHANT_ADD_TERMINAL(22),
      ALLOW_BOARDING_MANAGEMENT_MERCHANT(14),
      ALLOW_BOARDING_MANAGEMENT_TERMINAL(15),
      ALLOW_BOARDING_MANAGEMENT_TERMINAL_ADD(20),
      ALLOW_BOARDING_APLICATION(16),
      ALLOW_TRANSACTION(2),
      ALLOW_ADDRESS(3),
      ALLOW_ADMINISTRATION(4),
      
      ALLOW_ADMINISTRATION_USERS(6),
      ALLOW_ADMINISTRATION_USERS_UPDATE(9),
      ALLOW_ADMINISTRATION_ROLES(5),
      ALLOW_ADMINISTRATION_ROLES_ADD_PRIVILEGE(7),
      ALLOW_ADMINISTRATION_ROLES_NEW(11),
      ALLOW_ADMINISTRATION_ROLES_DELETE(8),
      ALLOW_ADMINISTRATION_ROLES_DELETE_PRIVILEGE(10),
      ALLOW_ADMINISTRATION_USERS_CHANGE_PASSWORD(23),
      ALLOW_MANAGE_CARD_INVENTORY(24),
      ALLOW_FEEMANAGEMENT(25),
      ALLOW_CHECKRESEND(26),
      ALLOW_CLIENT_BLACK_LIST(27),
      ALLOW_SMS_MESSAGES(28)
    ;
    
    
    
    private int id;

    private NomUserPrivileges(int id) {
        this.id = id;
    }

    /**
     * Get id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
   
}
