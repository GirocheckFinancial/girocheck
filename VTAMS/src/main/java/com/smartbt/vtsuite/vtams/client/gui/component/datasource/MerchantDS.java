/*
 ** File: MerchantDS.java
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
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The Merchant DataSource
 *
 * @author Ariamnet Lopez
 */
public class MerchantDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public MerchantDS() {

        DataSourceTextField idAgrupationField = new DataSourceTextField("idAgrupation");
        
        DataSourceTextField nameField = new DataSourceTextField("legalName");
        DataSourceTextField agentNameField = new DataSourceTextField("agentName");
        DataSourceTextField phoneField = new DataSourceTextField("phone");
        DataSourceTextField accountField = new DataSourceTextField("account");
     
        DataSourceField addressField = new DataSourceField("address", FieldType.ANY);
        addressField.setMultiple(Boolean.TRUE);
        addressField.setTypeAsDataSource(new AddressDS());
        
        DataSourceTextField idTecnicardCheck = new DataSourceTextField("idTecnicardCheck"); 
        DataSourceTextField idTecnicardCash = new DataSourceTextField("idTecnicardCash"); 
        DataSourceTextField iStreamUser = new DataSourceTextField("iStreamUser"); 
        DataSourceTextField iStreamPassword = new DataSourceTextField("iStreamPassword"); 
        
//        DataSourceTextField idIStream = new DataSourceTextField("idIStream");        
        DataSourceTextField idIStreamFuzeCash = new DataSourceTextField("idIstreamFuzeCash");        
        DataSourceTextField idIStreamFuzeCheck = new DataSourceTextField("idIstreamFuzeCheck");  
        DataSourceTextField idIStreamTecnicardCash = new DataSourceTextField("idIstreamTecnicardCash");        
        DataSourceTextField idIStreamTecnicardCheck = new DataSourceTextField("idIstreamTecnicardCheck");  
        DataSourceTextField idTellerOrderExp = new DataSourceTextField("idTellerOrderExp");
        DataSourceTextField idTellerPagoOrderExp = new DataSourceTextField("idTellerPagoOrderExp");
        DataSourceTextField idPosOrderExp = new DataSourceTextField("idPosOrderExp");  
        
        DataSourceTextField sicField = new DataSourceTextField("sic");        
        DataSourceTextField cardInventoryField = new DataSourceTextField("cardInventory");
        DataSourceTextField bankNameField = new DataSourceTextField("bankName");
        DataSourceTextField routingBankNumberField = new DataSourceTextField("routingBankNumber");
        
        DataSourceField cardProgramField = new DataSourceField("cardProgram", FieldType.ANY);
        cardProgramField.setMultiple(Boolean.TRUE);
        cardProgramField.setTypeAsDataSource(new CardProgramDS());
           
        DataSourceTextField merchantTypeField = new DataSourceTextField("merchantType");
        DataSourceTextField activationdateField = new DataSourceTextField("activationdate");
        DataSourceTextField distributorField = new DataSourceTextField("distributor");        
        DataSourceTextField distributionChanelField = new DataSourceTextField("distributionChanel");        
        DataSourceTextField riskField = new DataSourceTextField("risk");        

        DataSourceBooleanField independentOwnerField = new DataSourceBooleanField("independentOwner");
        DataSourceBooleanField moneyTransmissionField = new DataSourceBooleanField("moneyTransmission");
        DataSourceBooleanField billPaymentField = new DataSourceBooleanField("billPayment");
        DataSourceBooleanField checkCashingField = new DataSourceBooleanField("checkCashing");
        DataSourceBooleanField documentApprovedField = new DataSourceBooleanField("documentApproved");
        DataSourceBooleanField atmField = new DataSourceBooleanField("atm");
        DataSourceBooleanField activeField = new DataSourceBooleanField("active");
        DataSourceBooleanField trainingField = new DataSourceBooleanField("training");
        DataSourceBooleanField otherFinancialProviderField = new DataSourceBooleanField("otherFinancialProvider");
        
        DataSourceTextField documentNotesField = new DataSourceTextField("documentNotes");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        DataSourceTextField oEAgentNumberField = new DataSourceTextField("oEAgentNumber");
        DataSourceTextField authFeePField = new DataSourceTextField("authFeeP");
        DataSourceTextField commissionTypeField = new DataSourceTextField("commissionType");
       
        DataSourceBooleanField hasTransactionField = new DataSourceBooleanField("hasTransaction");
        
        //setJsonRecordXPath("merchant");
        setFetchDataURL(Properties.GET_MERCHANTS_BY_AGRUPATION_WS);
        setAddDataURL( Properties.SAVE_OR_UPDATE_MERCHANT);
        setRemoveDataURL( Properties.DELETE_MERCHANT_WS);

        setFields(idAgrupationField,
                
                nameField,
                agentNameField,
                phoneField,
                accountField,
                
                addressField,
                
                idTecnicardCheck,
                idTecnicardCash,
                iStreamUser,
                iStreamPassword,
                
//                idIStream,
                idIStreamFuzeCash,
                idIStreamFuzeCheck,
                idIStreamTecnicardCash,
                idIStreamTecnicardCheck,
                idTellerOrderExp,
                idTellerPagoOrderExp,
                idPosOrderExp,
                sicField,
                cardInventoryField,
                bankNameField,
                routingBankNumberField,
                cardProgramField,
                
                distributorField,
                distributionChanelField,
                riskField,
                merchantTypeField,
                activationdateField,
                
                independentOwnerField,
                moneyTransmissionField,
                billPaymentField,
                checkCashingField,
                documentApprovedField,
                atmField,
                trainingField,
                activeField,
                otherFinancialProviderField,
                
                documentNotesField,
                descriptionField,
                oEAgentNumberField,
        
                hasTransactionField,
                authFeePField,
                commissionTypeField);
    }

    public MerchantDS(String fetchDataUrl) {
        this();
        setFetchDataURL(fetchDataUrl);
    }
}
