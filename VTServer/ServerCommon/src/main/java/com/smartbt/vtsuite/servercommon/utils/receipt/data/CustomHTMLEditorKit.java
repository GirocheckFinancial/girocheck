/*
 ** File: CustomHTMLEditorKit.java
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

import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 * The Custom HTML Editor Kit
 *
 * @author Ariamnet Lopez
 */
public class CustomHTMLEditorKit extends HTMLEditorKit {

    //private static final CustomHTMLFactory customFactory = ;

    public ViewFactory getViewFactory() {
        return new CustomHTMLFactory();
    }

    static class CustomHTMLFactory extends HTMLFactory {

        public View create(Element elem) {

            Object type = elem.getAttributes().getAttribute(StyleConstants.NameAttribute);
            if (type == HTML.Tag.IMG) {
                return new CustomImageView(elem);
            } else {
                return super.create(elem);
            }
        }
    }  
    
    @Override
    public Document createDefaultDocument ()
    {
        Document doc = super.createDefaultDocument ();
        ((HTMLDocument) doc).setAsynchronousLoadPriority (-1);
        return doc;
    }
}