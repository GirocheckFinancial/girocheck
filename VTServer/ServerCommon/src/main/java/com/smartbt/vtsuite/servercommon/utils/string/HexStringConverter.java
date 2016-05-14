/*
 ** File: HexStringConverter.java
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
package com.smartbt.vtsuite.servercommon.utils.string;

import java.io.UnsupportedEncodingException;

/**
 * Hex String Helper Class
 *
 * @author Carlos
 */
public class HexStringConverter {

    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    private static HexStringConverter hexStringConverter = null;

    /**
     * The default constructor
     */
    public HexStringConverter() {
    }

    /**
     * Gets class instance
     *
     * @return Hex string converter
     */
    public static HexStringConverter getHexStringConverterInstance() {
        if (hexStringConverter == null) {
            hexStringConverter = new HexStringConverter();
        }
        return hexStringConverter;
    }

    /**
     * Convert ASCII string to hex format
     *
     * @param input String in ASCII format
     * @return String in hex format
     * @throws UnsupportedEncodingException
     */
    public String stringToHex(String input) throws UnsupportedEncodingException {
        if (input == null) {
            throw new NullPointerException();
        }
        return asHex(input.getBytes()).toUpperCase();
    }

    /**
     * Convert hex string to ASCII string
     *
     * @param input String in hex format
     * @return ASCII string
     */
    public String hexToString(String input) {
        byte[] txtInByte = new byte[input.length() / 2];
        int j = 0;
        for (int i = 0; i < input.length(); i += 2) {
            txtInByte[j++] = Byte.parseByte(input.substring(i, i + 2), 16);
        }
        return new String(txtInByte);
    }

    private String asHex(byte[] buf) {
        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
    }
}
