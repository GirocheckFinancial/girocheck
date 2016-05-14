/*
 ** File: BaseInterface.java
 **
 ** Date Created: May 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.gui.base;

import com.smartgwt.client.data.Record;

/**
 * The Base interface
 *
 * @author Ariamnet Lopez
 */
public interface BaseInterface {

    /**
     * Method to execute on Filter.
     *
     */
    public void Filter();

    /**
     * Method to execute on Add.
     *
     */
    public void Add();

    /**
     * Method to execute on Update.
     *
     * @param record the record to update
     */
    public void Update(Record record);

    /**
     * Method to execute on Delete
     *
     */
    public void Delete();

    /**
     * Method to execute on Save.
     *
     */
    public void Save();
}
