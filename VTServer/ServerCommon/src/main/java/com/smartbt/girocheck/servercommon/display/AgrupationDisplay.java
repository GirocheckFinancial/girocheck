/*
 ** File: AgrupationDisplay.java
 **
 ** Date Created: February 2013
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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Customer Display Class - containing all sets/gets
 */
@XmlRootElement
public class AgrupationDisplay implements Serializable {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AgrupationDisplay.class);

    private Integer id;
    private String name;
    private String description;
    private boolean hasTransaction;

    public AgrupationDisplay(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void print(){
//        log.debug( "-- Printing AgrupationDisplay --" );
//        log.debug( "id :: " + id );
//        log.debug( "name :: " + name );
//        log.debug( "desc :: " + description );
    }
    
    /**
     * The default constructor
     */
    public AgrupationDisplay() {
    }

   
    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setHasTransaction( boolean hasTransaction ) {
        this.hasTransaction = hasTransaction;
    }

    public boolean isHasTransaction() {
        return hasTransaction;
    }
    
    

}
