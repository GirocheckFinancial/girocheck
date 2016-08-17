/*
 ** File: ServerJNDI.java
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
package com.smartbt.girocheck.common;

/**
 * Constants for webserver resources.
 */
public class ServerJNDI {
    public final static String CONNECTION_FACTORY_JNDI = "jms/DxQueueConnectionFactory";
    public final static String CORE_IN_QUEUE_JNDI = "jms/DirexQueueCoreIn";
    public final static String CORE_OUT_QUEUE_JNDI = "jms/DirexQueueCoreOut";
    public final static String CORE2_IN_QUEUE_JNDI = "jms/DirexQueueCore2In";
    public final static String CORE2_OUT_QUEUE_JNDI = "jms/DirexQueueCore2Out";
    public final static String HOST_IN_QUEUE_JNDI = "jms/DirexQueueHostIn";
    public final static String HOST_OUT_QUEUE_JNDI = "jms/DirexQueueHostOut";
    public final static String FRONT_ISTREAM_IN_QUEUE_JNDI = "jms/DirexQueueFrontIStreamIn";
    public final static String FRONT_ISTREAM_OUT_QUEUE_JNDI = "jms/DirexQueueFrontIStreamOut";
    public final static String WATCHDOG_QUEUE_JNDI = "jms/DirexQueueWatchdog";
    
    public final static String DMQ_QUEUE_JNDI = "jms/DirexQueueDMQ";
    public final static String LDAP_CREDENTIAL = "jdbc/LdapCredential";
    public final static String LDAP_KEY = "jdbc/LdapKey";    
}

