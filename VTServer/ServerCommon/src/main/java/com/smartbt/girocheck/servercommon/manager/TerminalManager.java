/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.TerminalDAO;
import com.smartbt.girocheck.servercommon.display.TerminalDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.MerchantDAO;
import com.smartbt.vtsuite.vtcommon.Constants;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class TerminalManager {
    
    protected static TerminalManager INSTANCE;
    
    public static TerminalManager get() {
        if ( INSTANCE == null ) {
            INSTANCE = new TerminalManager();
        }
        return INSTANCE;
    }

    private final TerminalDAO terminalDAO = TerminalDAO.get();
    private final MerchantDAO merchantDAO = MerchantDAO.get();

    public Terminal findBySerialNumber( String serialNumber ) {
        return terminalDAO.findBySerialNumber( serialNumber );
    }

    public Terminal findById( int id ) {
        return terminalDAO.findById( id );
    }
    
    public boolean loginTerminal( String serialNumber, String user, String password ) {
        return terminalDAO.loginTerminal( serialNumber, user, password );
    }

    public String getAccountFromMerchantByTerminalSerialNumber( String serialNumber ) {
        return terminalDAO.getAccountFromMerchantByTerminalSerialNumber( serialNumber );
    }

    public ResponseDataList getTerminalByMerchant( int idMerchant ) {
        ResponseDataList response = new ResponseDataList();

        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        response.setData( terminalDAO.getTerminalByMerchant( idMerchant ) );

        return response;
    }

    public Merchant getParametersFromMerchant( String serialNumber ) {
        return terminalDAO.getParametersFromMerchant( serialNumber );
    }

    public ResponseData<TerminalDisplay> getTerminalById( int id ) {
        ResponseData<TerminalDisplay> response = new ResponseData<TerminalDisplay>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        response.setData( terminalDAO.getTerminalById( id ) );
        return response;
    }

    public ResponseData<TerminalDisplay> saveOrUpdateTerminal( TerminalDisplay display ) {
        ResponseData<TerminalDisplay> response = new ResponseData<TerminalDisplay>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        
        Terminal terminal = new Terminal();
        if ( display.getId() != null && display.getId() != 0 ) {
//            terminal.setId( display.getId() );
            terminal = terminalDAO.findById(display.getId());
        } else {
            if (terminalDAO.findBySerialNumber(display.getSerialNumber()) != null) {
                response.setStatus(Constants.CODE_INVALID_ENTRY_DATA);
                response.setStatusMessage(VTSuiteMessages.ERROR_TRANSACTION_TERMINAL + " Terminal Serial Number already exist.");
                return response;
            }
        }

//        Merchant merchant = new Merchant( display.getIdMerchant() );
        Merchant merchant = merchantDAO.findById(display.getIdMerchant());

        terminal.setMerchant( merchant );
        terminal.setSerialNumber( display.getSerialNumber() );
        terminal.setGirocheckUser( display.getTerminalUser() );
        terminal.setGirocheckPassword( display.getTerminalPassword() );
        terminal.setDescription( display.getDescription() );
        terminal.setIdPOSOrderExp(display.getIdPOSOrderExp());

        terminalDAO.saveOrUpdate( terminal );

        display.setId( terminal.getId() );
        response.setData( display );

        return response;
    }

    public BaseResponse deleteTerminal( int idTerminal ) {
        BaseResponse response = new BaseResponse();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        terminalDAO.deleteTerminal( idTerminal );
        return response;
    }
}
