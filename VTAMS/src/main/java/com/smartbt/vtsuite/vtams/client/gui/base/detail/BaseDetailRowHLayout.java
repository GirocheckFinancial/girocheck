/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.vtams.client.gui.base.detail;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 *
 * @author Ariel Saavedra
 */
public class BaseDetailRowHLayout extends HLayout {

    protected BaseDetailLabel tilteLabel;
    protected BaseDetailLabel contentLabel;

    public static BaseDetailRowHLayout getRow(String title, String content) {
        if (content == null || content.equals("null")) {
            content = "-";
        }
        return new BaseDetailRowHLayout(title, content);
    }

    public BaseDetailRowHLayout(String title, String content) {
        setMembersMargin(20);
        setHeight(20);
        tilteLabel = new BaseDetailLabel(title, true);
        contentLabel = new BaseDetailLabel(content, false);

        addMember(tilteLabel);
        addMember(contentLabel);
    }

    public Label getTilteLabel() {
        return tilteLabel;
    }

    public Label getContentLabel() {
        return contentLabel;
    }

}

class BaseDetailLabel extends Label {

    public BaseDetailLabel(String contents, boolean bold) {
        if (bold) {
            setContents("<b>" + contents + "</b>");
            setWidth(130);
        } else {
            setContents(contents);
            setWidth(250);
        }
        setHeight(20);
    }
}
