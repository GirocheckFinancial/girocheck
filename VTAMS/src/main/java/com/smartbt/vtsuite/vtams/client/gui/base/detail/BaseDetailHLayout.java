/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.vtams.client.gui.base.detail;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 *
 * @author Ariel Saavedra
 */
public class BaseDetailHLayout extends HLayout {

    public BaseDetailHLayout(Canvas... canvas) {
        setMembersMargin(30);
        setHeight(20);
        if(canvas!=null){
            for(Canvas c:canvas){
                addMember(c);
            }
        }
    }
}
