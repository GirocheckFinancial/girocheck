/*
 ** File: CurrentTime.java
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
package com.smartbt.girocheck.servercommon.diagnostics;

/**
 * Current Time Class
 */
public class CurrentTime {

    /**
     * Gets current milliseconds since zero time
     *
     * @return Milliseconds
     */
    public static long inMilliseconds() {
        return System.currentTimeMillis();
    }

    /**
     * Gets current nanoseconds since zero time
     *
     * @return Nanoseconds
     */
    public static long inNanoseconds() {
        return System.nanoTime();
    }

    /**
     * Gets current milliseconds since zero time
     *
     * @return Milliseconds
     */
    public static long get() {
        return inMilliseconds();
    }
}
