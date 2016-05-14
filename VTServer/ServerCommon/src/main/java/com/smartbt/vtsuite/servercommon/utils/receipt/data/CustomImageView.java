/*
 ** File: CustomImageView.java
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

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.text.Element;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.ImageView;

/**
 *
 * @author Ariamnet Lopez
 */
public class CustomImageView extends ImageView {

    public CustomImageView(Element elem) {
        super(elem);
        setLoadsSynchronously(true);
    }

    @Override
    /**
     * Return a URL for the image source, or null if it could not be determined.
     */
    public URL getImageURL() {
        String src = (String) getElement().getAttributes().
                getAttribute(HTML.Attribute.SRC);

        if (src == null) {
            return null;
        }

        URL reference = ((HTMLDocument) getDocument()).getBase();
        try {
            URL u = new URL(reference, src, new Handler());
            return u;
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
