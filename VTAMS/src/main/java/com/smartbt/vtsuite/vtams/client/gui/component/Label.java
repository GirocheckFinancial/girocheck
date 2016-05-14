
package com.smartbt.vtsuite.vtams.client.gui.component;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class Label extends Widget implements HasText {

public Label() {
  setElement(DOM.createLabel());
}

public Label(String text) {
  setElement(DOM.createLabel());
  getElement().setInnerText((text == null) ? "" : text);
}

//@Override
//public void add(Widget w) {
//  super.add(w, getElement());
//}

@Override
public String getText() {
  return getElement().getInnerText();
}

@Override
public void setText(String text) {
  getElement().setInnerText((text == null) ? "" : text);
}

public void setFor(String forWho) {
  getElement().setAttribute("for", forWho);
}
}