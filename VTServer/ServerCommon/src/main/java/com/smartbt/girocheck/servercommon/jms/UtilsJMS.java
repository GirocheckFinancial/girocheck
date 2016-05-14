/*
 ** File: UtilsJMS.java
 **
 ** Date Created: October 2013
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

package com.smartbt.girocheck.servercommon.jms;

import java.util.UUID;

/**
 *
 * @author Ariel Saavedra
 */
public class UtilsJMS {
    
    /**
     * Generates a random GUID for message correlationId
     *
     * @return GUID
     */
    public static String generateGUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
