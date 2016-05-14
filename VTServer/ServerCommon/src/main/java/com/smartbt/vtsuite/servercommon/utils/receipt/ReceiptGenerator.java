/*
 ** File: ReceiptGenerator.java
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
package com.smartbt.vtsuite.servercommon.utils.receipt;

import com.smartbt.vtsuite.servercommon.display.common.model.MerchantDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.MerchantParameterDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TerminalParameterDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.utils.receipt.data.CustomHTMLEditorKit;
import com.smartbt.vtsuite.vtcommon.Messages;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomEntryMethod;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Maite Gonzalez, Ariamnet Lopez
 */
public class ReceiptGenerator {

    private TransactionDisplay transactionDisplay;
    private MerchantParameterDisplay merchantParameters;
    private TerminalParameterDisplay terminalParameters;
    private MerchantDisplay merchantDisplay;
    private static final String htmlStartTag = "<html> ";
    private static final String htmlHeadTag = "    <head> "
            + "         <style> "
            + "             body { "
            + "                 font: normal 11px/ 15px Arial, Verdana, sans-serif; "
            + "                 font-smoothing: antialiased !important; "
            + "                 -webkit-font-smoothing: antialiased !important; "
            + "                 -moz-font-smoothing: antialiased !important; "
            + "                 width: 200px; "
            + "             } "
            + "             .headerMain, .operationMode { "
            + "                 color: rgb(87, 93, 120); "
            + "                 font-size: 10px; "
            + "                 text-transform: capitalize; "
            + "                 font-weight: bold; "
            + "                 text-align: center; "
            + "                 line-height: 9px; "
            + "             } "
            + "             .operationMode { "
            + "                 font-size: 9px; "
            + "             } "
            + "             .headerLine { "
            + "                 color: rgb(191, 188, 188); "
            + "                 font-size: 8.5px; "
            + "                 text-transform: capitalize; "
            + "                 text-align: center; "
            + "             } "
            + "             .labelLine, .promissoryLine { "
            + "                 float: left; "
            + "                 color: rgb(87, 93, 120); "
            + "                 font-size: 8.5px; "
            + "                 text-align: left; "
            + "                 width: 200px; "
            + "                 line-height: 5px; "
            + "                 white-space: nowrap; "
            + "             } "
            + "             .promissoryLine { "
            + "                 font-size: 8px; "
            + "                 text-align: center; "
            + "             } "
            + "             .infoLine { "
            + "                 float: left; "
            + "                 color: rgb(87, 93, 120); "
            + "                 font-size: 8.5px; "
            + "                 text-align: right; "
            + "                 width: 200px; "
            + "             } "
            + "             .separator { "
            + "                 height: 3px; "
            + "                 line-height: 3px; "
            + "                 display: block; "
            + "             } "
            + "             .shortSeparator { "
            + "                 height: 1px; "
            + "                 line-height: 1px; "
            + "                 display: block; "
            + "             } "
            + "             .signature { "
            + "                 display: block;"
            + "                 margin-left: auto; "
            + "                 margin-right: auto; "
            + "             } "
            + "         </style> "
            + "    </head> ";
    private static final String htmlBodyStartTag = "    <body> "
            + "         <table cellspacing='0' cellpadding='0'>";
    private static final String htmlHeaderMain = "          <tr>"
            + "                 <td class='headerMain' colspan=2><b>%s</b></td>"
            + "             </tr>";
    private static final String htmlHeaderLine = "             <tr>"
            + "                 <td class='headerLine' colspan=2>%s</td>"
            + "             </tr>";
    private static final String htmlRowInfo = "             <tr>"
            + "                 <td class='labelLine'>%s</td>"
            + "                 <td class='infoLine'><div>%s</div></td>"
            + "             </tr>";
    private static final String htmlRowImage = "             <tr>"
            + "                 <td class='headerLine' colspan=2>"
            + "                    <img class='signature' height=100 width=200 src=\"%s\">"
            + "                 </td>"
            + "             </tr>";
       private static final String htmlRowImageLogo = "             <tr>"
            + "                 <td class='headerLine' colspan=2>"
            + "                    <img class='signature' height=59 width=95 src=\"%s\">"
            + "                 </td>"
            + "             </tr>";
    private static final String htmlRowPromissory = "       <tr>"
            + "                 <td class='promissoryLine' colspan=2>%s</td>"
            + "             </tr>";
    private static final String htmlSpaceTag = "            <tr class='separator'></tr>";
    private static final String htmlSeparatorTag = "        <tr class='separator'> "
            + "                 <td colspan=2> "
            + "                     <div style='height: 1px; font-size:0; background-color:rgb(191, 188, 188);'>&nbsp;</div> "
            + "                 </td> "
            + "             </tr>";
    private static final String htmlBodyEndTag = "        </table>"
            + "    </body>";
    private static final String htmlEndTag = "</html>";
    
     private static final String htmlRowCardHolder = "       <tr>"
            + "                 <td class='promissoryLine' colspan=2>%s</td>"
            + "             </tr>";

    public ReceiptGenerator(TransactionDisplay transactionDisplay,
            MerchantParameterDisplay merchantParameters, TerminalParameterDisplay terminalParameters, MerchantDisplay merchantDisplay) {
        this.transactionDisplay = transactionDisplay;
        this.merchantParameters = merchantParameters;
        this.terminalParameters = terminalParameters;
        this.merchantDisplay = merchantDisplay;
    }

    public String getReceiptInfo() throws Exception {

        String rowInfo = "";

      
        if (merchantDisplay != null && merchantDisplay.getLogoImage()!=null && !merchantDisplay.getLogoImage().isEmpty()) {
            rowInfo = rowInfo.concat(String.format(htmlRowImageLogo, ImageHelper.resizeImagePNG(merchantDisplay.getLogoImage(), "png", 95, 59, true)));
        }
        
        
        if (merchantParameters != null) {
            rowInfo = rowInfo.concat(String.format(htmlHeaderMain, merchantParameters.getHeaderLine1() != null ? merchantParameters.getHeaderLine1() : ""));
            rowInfo = rowInfo.concat(String.format(htmlHeaderLine, merchantParameters.getHeaderLine2() != null ? merchantParameters.getHeaderLine2() : ""));
            rowInfo = rowInfo.concat(String.format(htmlHeaderLine, merchantParameters.getHeaderLine3() != null ? merchantParameters.getHeaderLine3() : ""));
            rowInfo = rowInfo.concat(String.format(htmlHeaderLine, merchantParameters.getHeaderLine4() != null ? merchantParameters.getHeaderLine4() : ""));
            rowInfo = rowInfo.concat(String.format(htmlHeaderLine, merchantParameters.getHeaderLine5() != null ? merchantParameters.getHeaderLine5() : ""));
        }

        rowInfo = rowInfo.concat(htmlSpaceTag);
        rowInfo = rowInfo.concat(htmlSeparatorTag);
        rowInfo = rowInfo.concat(htmlSpaceTag);
        rowInfo = rowInfo.concat(String.format(htmlHeaderMain, transactionDisplay.getMode() + " " + transactionDisplay.getOperation()));

        rowInfo = rowInfo.concat(htmlSpaceTag);
        
//        TODO: [DELETE] NOT FOR THIS PHASE
//        rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.MID_TITLE, String.valueOf(transactionDisplay.getMerchant().getNumber())));
        rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.TID_TITLE, transactionDisplay.getTerminal().getTerminalId()));

        rowInfo = rowInfo.concat(htmlSpaceTag);
        rowInfo = rowInfo.concat(String.format(htmlRowInfo, new SimpleDateFormat("MM/dd/yy").format(transactionDisplay.getCreatedAt()), new SimpleDateFormat("hh:mm:ssa").format(transactionDisplay.getCreatedAt())));

        rowInfo = rowInfo.concat(htmlSpaceTag);
        rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.ENTRY_METHOD_TITLE,
                transactionDisplay.getEntryMethod() == NomEntryMethod.SWIPE.getId()
                ? Messages.SWIPED_ENTRY_MSG : Messages.MANUAL_ENTRY_MSG));
        
//        TODO: [DELETE] NOT FOR THIS PHASE
//        rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.CVV_RESPONSE_CODE_TITLE, transactionDisplay.getCvvResult()));

        rowInfo = rowInfo.concat(htmlSpaceTag);
        rowInfo = rowInfo.concat(String.format(htmlRowInfo, transactionDisplay.getCardBrand().toUpperCase() + ":",
                Messages.CC_PREFIX + transactionDisplay.getAccountSuffix()));
        rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.SEQUENCE_TITLE, transactionDisplay.getSequence()));
        rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.BATCH_TITLE, transactionDisplay.getBatchNumber()));
        rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.APPROVAL_CODE_TITLE, transactionDisplay.getApprovalCode()));

//        TODO: [DELETE] NOT FOR THIS PHASE
//        rowInfo = rowInfo.concat(htmlSpaceTag);
//        rowInfo = rowInfo.concat(String.format(htmlHeaderMain, transactionDisplay.getDisposition()));

        rowInfo = rowInfo.concat(htmlSpaceTag);
        if (transactionDisplay.getOperation().equalsIgnoreCase(NomOperation.SALE.toString()) && (terminalParameters != null)) {
            if (terminalParameters.isTip() || terminalParameters.isTax()) {
                String subtotal = FormatHelper.priceToString(transactionDisplay.getSubTotalAmount());
                rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.SUB_TOTAL_TITLE, Messages.DOLLAR_SIGN + subtotal));
            }
            if (terminalParameters.isTax()) {
                String tax = FormatHelper.priceToString(transactionDisplay.getTaxAmount());
                rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.TAX_TITLE, Messages.DOLLAR_SIGN + tax));
            }
            if (terminalParameters.isTip()) {
                String tip = FormatHelper.priceToString(transactionDisplay.getTipAmount());
                rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.TIP_TITLE, Messages.DOLLAR_SIGN + tip));
            }
        }

        String total = FormatHelper.priceToString(transactionDisplay.getTotalAmount());
        rowInfo = rowInfo.concat(String.format(htmlRowInfo, Messages.TOTAL_TITLE, Messages.DOLLAR_SIGN + total));

        if (merchantParameters != null) {
            rowInfo = rowInfo.concat(htmlSpaceTag);
            rowInfo = rowInfo.concat(String.format(htmlRowPromissory, merchantParameters.getPromissoryVerbiage1() != null ? merchantParameters.getPromissoryVerbiage1() : ""));
            rowInfo = rowInfo.concat(String.format(htmlRowPromissory, merchantParameters.getPromissoryVerbiage2() != null ? merchantParameters.getPromissoryVerbiage2() : ""));
            rowInfo = rowInfo.concat(String.format(htmlRowPromissory, merchantParameters.getPromissoryVerbiage3() != null ? merchantParameters.getPromissoryVerbiage3() : ""));
        }

//        TODO: [DELETE] NOT FOR THIS PHASE
//        if (transactionDisplay.getSignature() != null && !transactionDisplay.getSignature().isEmpty()) {
//            rowInfo = rowInfo.concat(String.format(htmlRowImage, ImageHelper.resizeImagePNG(transactionDisplay.getSignature(), "png", 200, 100, true)));
//        }

//        if (transactionDisplay.getAccount().getCardHolderName()!= null && !transactionDisplay.getAccount().getCardHolderName().trim().equalsIgnoreCase("")) {
//            rowInfo = rowInfo.concat(htmlSpaceTag);
//            rowInfo = rowInfo.concat(String.format(htmlRowCardHolder, transactionDisplay.getAccount().getCardHolderName() != null ? transactionDisplay.getAccount().getCardHolderName() : ""));
//        }

        if (terminalParameters != null) {
            rowInfo = rowInfo.concat(htmlSpaceTag);
            rowInfo = rowInfo.concat(String.format(htmlHeaderMain, terminalParameters.getReceiptMessage()));
        }

        String html = htmlStartTag + htmlHeadTag + htmlBodyStartTag + rowInfo + htmlBodyEndTag + htmlEndTag;

        CustomHTMLEditorKit eKit = new CustomHTMLEditorKit();

        JEditorPane pane = new JEditorPane();
        pane.setEditable(false);
        pane.setEditorKit(eKit);
        pane.setContentType("text/html");
        pane.setText(html);
        pane.setSize(pane.getPreferredSize());

        BufferedImage image = new BufferedImage(pane.getWidth(), pane.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = image.getGraphics();
        pane.paint(graphics);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", out);

        String base64bytes = DatatypeConverter.printBase64Binary(out.toByteArray());
        String src = "data:image/png;base64," + base64bytes;

        return src;
    }
}