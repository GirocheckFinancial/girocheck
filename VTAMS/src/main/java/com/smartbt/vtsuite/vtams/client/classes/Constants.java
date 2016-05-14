/*
 ** File: Constants.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.vtams.client.classes;

/**
 * The Constants class
 *
 * @author Ariamnet Lopez
 */
public class Constants {

    /**
     * Default value for portal columns
     */
    public static final int DEFAULT_PORTAL_COLUMNS = 2;
    
    /**
     * Constant that represents the maximum number of years that will be shown in the Expiration Year select
     */
    public static final int CC_EXPYEARS = 25;
    /**
     * Constant that represents the amount precision
     */
    public static final int AMOUNT_PRECISION = 2;
    /**
     * Constant that represents the max length allowed in the CVV field
     */
    public static final int CVV_MAX_LENGTH = 4;
    /**
     * Constant that represents the max length allowed in the Expiration Year field
     */
    public static final int YEAR_MAX_LENGTH = 4;
    /**
     * Constant that represents the max length allowed in the Expiration Month field
     */
    public static final int MONTH_MAX_LENGTH = 2;
    /**
     * Constant that represents the max length allowed in the Credit Card Number field
     */
    public static final int CC_MAX_LENGTH = 19;
    /**
     * Constant that represents the max length allowed in the Amount field
     */
    public static final int AMOUNT_MAX_LENGTH = 14;
    /**
     * Constant that represents the min value allowed in the Amount field
     */
    public static final int MIN_AMOUNT_ALLOWED = 001;
    /**
     * Constant that represents the max value allowed in the Amount field
     */
    public static final int MAX_AMOUNT_ALLOWED = 99999999;
    /**
     * Constant that represents the receipt's image width
     */
    public static final int RECEIPT_IMAGE_WIDTH = 95;
    /**
     * Constant that represents the receipt's image height
     */
    public static final int RECEIPT_IMAGE_HEIGHT = 59;
    
     /**
     * Constant value true
     */
    public static final String CONSTANT_TRUE = "True";
    /**
     * Constant value false
     */
    public static final String CONSTANT_FALSE = "False";
    /**
     * Constant value enabled
     */
    public static final String CONSTANT_ENABLED = "Enabled";
    /**
     * Constant value disabled
     */
    public static final String CONSTANT_DISABLED = "Disabled";
}
