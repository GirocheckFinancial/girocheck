/*
 ** File: I18N.java
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
package com.smartbt.vtsuite.vtams.client.classes.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;

/**
 *
 * @author Ariel Saavedra
 */
public class I18N {

    /**
     * Get Messages Instance
     */
    public static Messages GET = (Messages) GWT.create(Messages.class);

    /**
     * Change locale to ES
     */
    public static void changeLocaleToES() {        
        UrlBuilder newUrl = Window.Location.createUrlBuilder();
        newUrl.setParameter("locale", "es");
        Window.Location.assign(newUrl.buildString());
        
    }
    
    /**
     * Change locale to EN
     */
    public static void changeLocaleToEN() {
        UrlBuilder newUrl = Window.Location.createUrlBuilder();
        newUrl.setParameter("locale", "en");
        Window.Location.assign(newUrl.buildString());
    }
}
