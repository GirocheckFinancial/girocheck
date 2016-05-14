/*
 ** File: AMSDashboardEnvironmental.java
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
package com.smartbt.vtsuite.servercommon.display.ams;

import java.util.Date;

/**
 *
 * @author Ariel Saavedra
 */
public class AMSDashboardEnvironmental {

    private String taskId;
    private String value;
    private Date createdAt;

    /**
     *
     */
    public AMSDashboardEnvironmental() {
    }

    /**
     *
     * @param taskId
     * @param value
     * @param createdAt
     */
    public AMSDashboardEnvironmental(String taskId, String value, Date createdAt) {
        this.taskId = taskId;
        this.value = value;
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     *
     * @param taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
