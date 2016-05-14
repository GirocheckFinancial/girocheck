/*
 *  File AMSEntity
 * 
 *  Date Created: December 2013
 * 
 *  Copyright @ @ 2004-2013 Smart Business Technology, Inc.
 *
 *  All rights reserved. No part of this software may be 
 *  reproduced, transmitted, transcribed, stored in a retrieval 
 *  system, or translated into any language or computer language,
 *  in any form or by any means, electronic, mechanical, magnetic, 
 *  optical, chemical, manual or otherwise, without the prior 
 *  written permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.servercommon.display.common.model;

import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.io.Serializable;

/**
 *
 * @author Ariel
 */
public class EntityObject implements Serializable{

    private EntityType entityType;
    private int id;

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
