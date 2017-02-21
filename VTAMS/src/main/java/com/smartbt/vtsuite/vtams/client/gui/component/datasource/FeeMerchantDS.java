 package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;

/**
 *
 * @author suresh
 */

public class FeeMerchantDS extends BaseDatasource {
    
     public FeeMerchantDS() {
         
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
       
        DataSourceBooleanField hasTransactionField = new DataSourceBooleanField("hasTransaction");
        
         
         setFetchDataURL(Properties.GET_SEARCH_MERCHANTS_WS);
         
          getAddOperation().setDataProtocol(DSProtocol.POSTPARAMS);
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
                //idIStream,
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
                activeField,
                trainingField,
                otherFinancialProviderField,
                
                documentNotesField,
                descriptionField,
                oEAgentNumberField,
        
                hasTransactionField,
                authFeePField);
     }
     
     public FeeMerchantDS(String fetchDataUrl) {
        this();
        setFetchDataURL(fetchDataUrl);
    }
    
}
