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

import com.smartbt.girocheck.servercommon.dao.AgrupationDAO;
import com.smartbt.girocheck.servercommon.dao.CardProgramDAO;
import com.smartbt.girocheck.servercommon.dao.MerchantDAO;
import com.smartbt.girocheck.servercommon.display.MerchantDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Agrupation;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.AddressDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.CardProgram;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class MerchantManager {
    private AgrupationDAO agrupationDAO = AgrupationDAO.get();
    private MerchantDAO merchantDAO = MerchantDAO.get(); 
    private CardProgramDAO cardProgramDAO = CardProgramDAO.get();

    public ResponseDataList getMerchantsByAgrupation( int idAgrupation ) {
        ResponseDataList response = new ResponseDataList();

        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        response.setData( merchantDAO.getMerchantsByAgrupation( idAgrupation ) );

        return response;
    }

    public ResponseData<MerchantDisplay> getMerchantsById(int id){
        ResponseData<MerchantDisplay> response = new ResponseData<MerchantDisplay>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        response.setData( merchantDAO.getMerchantsById( id));
        return response;
    }
    
    public ResponseData<MerchantDisplay> saveOrUpdateMerchant( MerchantDisplay display ) throws UnsupportedEncodingException {
        ResponseData<MerchantDisplay> response = new ResponseData<MerchantDisplay>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );

        Merchant merchant = new Merchant();
        if ( display.getId() != null && display.getId() != 0) {
            merchant.setId( display.getId() ); 
        } 
        
        Agrupation agrupation = agrupationDAO.findById( display.getIdAgrupation());
        merchant.setAgrupation( agrupation );

        merchant.setLegalName(display.getLegalName());
        merchant.setAgentName( display.getAgentName() );
        merchant.setBankName(display.getBankName());
        merchant.setRoutingBankNumber(display.getRoutingBankNumber());
        merchant.setPhone( display.getPhone() );
        merchant.setAccount( display.getAccount() );
        merchant.setInventory(0);
        
        //ADDRESS
        AddressDisplay addressDisplay = display.getAddress();
        Address address = new Address();
        if ( addressDisplay.getId() != null ) {
            address.setId( addressDisplay.getId() );
        }
        address.setAddress( addressDisplay.getAddress() );
        address.setCity( addressDisplay.getCity() );
        address.setZipcode( addressDisplay.getZip() );

        int idCountry = addressDisplay.getCountry().getId();
        Country country = new Country(idCountry );
        
        address.setCountry( country );

        int idState = addressDisplay.getState().getId();
        State state = new State(idState);
        address.setState( state );

        address.setCity( addressDisplay.getCity() );
        merchant.setAddress( address );
        merchant.setIdTellerOrderExp(display.getIdTellerOrderExp());
        merchant.setIdTellerPagoOrderExp(display.getIdTellerPagoOrderExp());
        merchant.setIdPosOrderExp(display.getIdPosOrderExp());
        merchant.setoEAgentNumber(display.getoEAgentNumber());
        
        merchant.setIdTecnicardCheck( display.getIdTecnicardCheck());
        merchant.setIdTecnicardCash(display.getIdTecnicardCash());
        merchant.setIstreamUser( display.getiStreamUser());
        merchant.setIstreamPassword( display.getiStreamPassword());
         
        merchant.setIdIstreamFuzeCash(display.getIdIstreamFuzeCash());
        merchant.setIdIstreamFuzeCheck(display.getIdIstreamFuzeCheck());
        merchant.setIdIstreamTecnicardCash(display.getIdIstreamTecnicardCash());
        merchant.setIdIstreamTecnicardCheck(display.getIdIstreamTecnicardCheck());
        
        //String values
        merchant.setSic(display.getSic() );
      
        
        if(display.getCardProgram() != null){
            CardProgram cardProgram = cardProgramDAO.findById( display.getCardProgram().getId());
            merchant.setCard_program( cardProgram );
        }
        
        merchant.setMerchantType(Integer.parseInt(display.getMerchantType()) ); 
       
        //Boolean values
        merchant.setIndependentOwner( display.getIndependentOwner());
        merchant.setMoneyTransmission( display.getMoneyTransmission());
        merchant.setBillPayment(display.getBillPayment());
        merchant.setCheckCashing( display.getCheckCashing());
        merchant.setDocumentApproved( display.getDocumentApproved());
        merchant.setAtm( display.getAtm());
        merchant.setActive( display.getActive());
        merchant.setActivationDate(new Date());
        merchant.setTraining(display.getTraining());
        merchant.setOtherFinancialProvider(display.getOtherFinancialProvider());
        
        merchant.setDocumentNotes( display.getDocumentNotes());
        merchant.setDescription(display.getDescription());
        merchant.setAuthFeeP(display.getAuthFeeP());
        
        if(display.getCommissionType() != null && display.getCommissionType().length() > 0){
            merchant.setCommissionType(display.getCommissionType().charAt(0));
        }
        

        merchantDAO.saveOrUpdate( merchant );

        display.setId( merchant.getId() );
        response.setData( display );
        return response;
    }
    
        public BaseResponse deleteMerchant( int idMerchant) {
        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        merchantDAO.deleteMerchant( idMerchant );
        return response;
    }

         public ResponseDataList<Agrupation> searchMerchants( String search ) {

        ResponseDataList<Agrupation> response = new ResponseDataList<Agrupation>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        List list = merchantDAO.searchMerchants( search );

        response.setData( list );
        return response;
    }
         
//     public Merchant getMerchantByCreditCard(String CreditCard){
//         return creditCardDAO.getMerchantByCreditCardNumber(CreditCard);
//     }
     
     public Merchant getMerchantByTerminalID(String terminalId){         
         return merchantDAO.getMerchantByTerminalID(terminalId);
     }
}
