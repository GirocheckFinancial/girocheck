/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.vtams.client.gui.base.detail;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author Ariel Saavedra
 */
public class BaseDetailVLayout extends VLayout {

    public BaseDetailVLayout(Canvas... canvas) {       
        setWidth(250);
        if(canvas!=null){
            for(Canvas c:canvas){
                addMember(c);
            }
        }
    }
}
