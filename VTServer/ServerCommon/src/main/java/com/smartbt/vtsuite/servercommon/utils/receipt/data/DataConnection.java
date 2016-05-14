/*
 ** File: DataConnection.java
 **
 ** Date Created: June 2013
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
package com.smartbt.vtsuite.servercommon.utils.receipt.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.bind.DatatypeConverter;

/**
 * The Data Connection Class
 *
 * @author Ariamnet Lopez
 */
public class DataConnection extends URLConnection {

    public DataConnection(URL u) {
        super(u);
    }

    @Override
    public void connect() throws IOException {
        connected = true;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        String data = url.toString();
        data = data.replaceFirst("^.*;base64,", "");
        byte[] bytes = DatatypeConverter.parseBase64Binary(data);
        return new ByteArrayInputStream(bytes);
    }
}
