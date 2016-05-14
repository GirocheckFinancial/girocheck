/*
 ** File: SbtAMSFrontDao.java
 **
 ** Date Created: March 2013
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
package com.smartbt.vtsuite.vtams.client.helpers;

/**
 *
 * @author Ariel Saavedra
 */
public class JavaScriptMethodHelper {

    private static int requestCounter = 0;

    public static String addUploadFileCallback(JavaScriptMethodCallback callback) {
        String callbackName = "callback" + (requestCounter++);
        createCallbackFunction(callback, callbackName);
        return callbackName;
    }

    private native static void createCallbackFunction(JavaScriptMethodCallback obj, String callbackName)/*-{
     $wnd.utils.tmpcallback = function( j ){		   
     obj.@com.smartbt.vtsuite.vtams.client.helpers.JavaScriptMethodCallback::execute(Ljava/lang/String;)( j );     
     //$wnd.utils.tmpcallback=null;
      };    
     }-*/;
}