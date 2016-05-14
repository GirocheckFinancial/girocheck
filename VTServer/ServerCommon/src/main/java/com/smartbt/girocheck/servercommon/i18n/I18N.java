/*
 ** File: I18N.java
 **
 ** Date Created: February 2014
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
package com.smartbt.girocheck.servercommon.i18n;

import com.smartbt.girocheck.servercommon.dao.SystemPropertyDAO;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Ariel Saavedra
 */
public class I18N {

    /**
     * Get Messages Instance
     */
    private static final ResourceBundle GET = createResourceBundle();
    private static Locale currentLocale;

    /**
     * @return ResourceBundle
     */
    private static ResourceBundle createResourceBundle() {
        String dbLocale = SystemPropertyDAO.get().searchSysProperty("LOCALE");
        currentLocale = (dbLocale == null || dbLocale.isEmpty()) ? Locale.getDefault() : Locale.forLanguageTag(dbLocale);
        return ResourceBundle.getBundle("com.smartbt.girocheck.servercommon.i18n.Messages", currentLocale);
    }

    public static String get(String key) {
        return GET.getString(key);
    }

    public static String get(String key, Object... parms) {
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);

        formatter.applyPattern(GET.getString(key));

        return formatter.format(parms);
    }

    public static String get(Messages key) {
        return get(key.toString());
    }

    public static String get(Messages key, Object... parms) {
        return get(key.toString(), parms);
    }
}
