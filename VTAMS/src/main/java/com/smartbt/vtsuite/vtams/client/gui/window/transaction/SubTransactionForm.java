/*
 ** File: BaseFilterForm.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.transaction;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextAreaItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.SubTransactionImageDS;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;

/**
 * The Base Filter Form
 *
 * @author Roberto Rodriguez
 */
public class SubTransactionForm extends DynamicForm {

    protected BaseTextItem merchantText;
    protected BaseTextItem terminalText;
    protected BaseTextItem customerText;
    protected BaseTextAreaItem resultMessageTextArea;
    protected CanvasItem imageContent;
    private Img logo;

    public SubTransactionForm() {
        super();

        setWidth100();
        setAutoHeight();
        setMargin( 10 );
//        setTop( 150 );
        setNumCols(1);
        setTitleOrientation( TitleOrientation.TOP );

        merchantText = new BaseTextItem( "merchant", "Merchant", false );
        merchantText.setTextAlign( Alignment.LEFT );
        merchantText.setColSpan(4);
        //merchantText.setKeyPressFilter( scClassName );
        
        
        terminalText = new BaseTextItem( "terminal", "Terminal", false );
        terminalText.setTextAlign( Alignment.LEFT );
        terminalText.setWidth("90%");

        customerText = new BaseTextItem( "customer", "Customer Name", false );
        customerText.setTextAlign( Alignment.LEFT );
       // customerText.setWidth("90%");

        resultMessageTextArea = new BaseTextAreaItem(1, "resultMessage", "Result Message", "", false );
        resultMessageTextArea.setHeight( 50 );
        resultMessageTextArea.setColSpan(4);
        //resultMessageTextArea.setWidth( "90%");
//        label = new Label( "SubTransactions" );
//        label.setHeight( 20 );
                
        logo = new Img();
        logo.setAutoFit(true);
        imageContent = new CanvasItem();
        imageContent.setTitle("Image");
//        logo.setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
        imageContent.setCanvas(logo);

        setFields(
//                new RowSpacerItem(),
//                new RowSpacerItem(),
//                new RowSpacerItem(),
//                merchantText,
//                terminalText,
//                customerText,
                resultMessageTextArea
//                imageContent
        );
    }

    public void loadRecord( Record record ) {
        Utils.debug( "loadRecord..." );
        editRecord( record );
        Utils.debug( "loadRecord finish" );
    }

    public BaseTextAreaItem getResultMessageTextArea() {
        return resultMessageTextArea;
    }

    public void setImageContente(Criteria criteria){
        Utils.debug( "setImageContente()... criteria.getAttribute(idTransaction): " + criteria.getAttribute("idTransaction"));
        SubTransactionImageDS ds = new SubTransactionImageDS();
        ds.fetchData(criteria, new DSCallback() {
            
            /**
             * Callback to invoke on completion
             *
             * @param response Response sent by the server in response to a
             * DataSource request.
             * @param rawData data
             * @param request Request sent to the server to initiate a
             * DataSource operation.
             */
            public void execute(DSResponse response, Object rawData, DSRequest request) {
//                Filter();
                Utils.debug("setImageContente().execute()>>>>>>>>>>> ...");
//                Utils.debug("response.getAttributeAsString(image):: " + response.getData()[0].getAttributeAsString("image"));
                if (response.getData()[0].getAttributeAsString("image") == null) {
                    logo.setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
                    logo.redraw();
                } else {
                    logo.setSrc(response.getData()[0].getAttributeAsString("image"));
                    logo.redraw();
                }
            }
        });
    
    }
}
