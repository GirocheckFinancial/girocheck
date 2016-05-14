/*
 ** File: ApplicationParametersDisplay.java
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

import com.smartbt.vtsuite.servercommon.display.common.model.*;
import com.smartbt.vtsuite.vtcommon.enums.ApplicationType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * The Application Parameters Display Class - containing all sets/gets
 */
@XmlRootElement
public class ApplicationParameterDisplay implements Serializable {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApplicationParameterDisplay.class);

    private Integer id;
    private String name;
    private String description;
    private String value;
    private String applicationLabel;
    private Integer application;
    private Boolean readOnly;

    /**
     * The default constructor
     */
    public ApplicationParameterDisplay() {
    }

   public void print() {
//        log.debug( "Printing ApplicationParameterDisplay..." );
//        log.debug( ReflectionToStringBuilder.reflectionToString( this ) );
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

    public void setApplicationLabel( String applicationLabel ) {
        this.applicationLabel = applicationLabel;
    }

    public String getApplicationLabel() {
        return applicationLabel;
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

    public void setApplication( Integer application ) {
        this.application = application;
        
            this.applicationLabel = ApplicationType.get( application);
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setReadOnly( Boolean readOnly ) {
        this.readOnly = readOnly;
    }

    public Integer getApplication() {
        return application;
    }

    public String getName() {
        return name;
    }

    public Boolean isReadOnly() {
        return readOnly;
    }
    
    
}
