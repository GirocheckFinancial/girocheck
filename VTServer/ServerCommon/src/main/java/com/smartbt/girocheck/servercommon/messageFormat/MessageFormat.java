/*
 ** File: MessageFormat.java
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
package com.smartbt.girocheck.servercommon.messageFormat;

import java.util.List;
import java.util.Map;

/**
 * Message Format Class
 */
public interface MessageFormat {

    /**
     * Pack message from list
     *
     * @param req List containing the transaction request.
     * @return request in byte array.
     * @throws Exception
     */
    public byte[] pack(List req) throws Exception;

    /**
     * Unpack message to list
     *
     * @param rspBytes the response in byte array.
     * @return Map for the response.
     * @throws Exception
     */
    public Map unpack(byte[] rspBytes) throws Exception;
}
