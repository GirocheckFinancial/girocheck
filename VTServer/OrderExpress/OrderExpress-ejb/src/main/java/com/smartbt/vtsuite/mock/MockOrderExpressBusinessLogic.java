/*
 ** File: IStreamBusinessLogic.java
 **
 ** Date Created: February 2013
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
package com.smartbt.vtsuite.mock;

import com.smartbt.vtsuite.manager.*;
import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADA;
import com.smartbt.vtsuite.boundary.client.impl.ObjectFactory;
import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.enums.EnumApplicationParameter;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.OEWS.ContratacionesResponse;
import com.smartbt.vtsuite.utils.MapUtil;
import com.smartbt.vtsuite.utils.OEUtils;
import static com.smartbt.vtsuite.utils.OEUtils.processRequest;
import com.smartbt.vtsuite.utils.OrderExpressConstantValues;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;

/**
 * OrderExpress Business Logic Class
 */
public class MockOrderExpressBusinessLogic extends AbstractBusinessLogicModule {

    //   private OrderExpressService service = new OrderExpressService();
    //   private OrderExpressSoap port = service.OrderExpressSoap();
//    private static MpowaCommunicator mpowaComms = new MpowaCommunicator();
//    private HostTransactionDAO hostDAO = new HostTransactionDAO();
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MockOrderExpressBusinessLogic.class);

    /**
     * Constructor
     */
    public MockOrderExpressBusinessLogic() {
    }

    @Override
    public void preprocess(DirexTransactionRequest tr) throws Exception {
    }

    @Override
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MOCK_OrderExpressBusinessLogic] processing ...",null);

        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();
        Map map = request.getTransactionData();
        Map response = map;
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MOCK_OrderExpressBusinessLogic] processing transaction type: " +transactionType.toString(),null);
        switch(transactionType){
            case ORDER_EXPRESS_CONTRATACIONES:
                response = contrataciones();
                break;
            case ORDER_EXPRESS_REPORTAPAGO:
                response = reportaPago();
                break;
            case ORDER_EXPRESS_DEVOLUCION:
                response = devolucion();
                break;
            case ORDER_EXPRESS_LOGS:
                response = log();
                break;
        } 

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MOCK_OrderExpressBusinessLogic] Proccess finish",null);
        direxTransactionResponse.setTransactionType(transactionType);
        direxTransactionResponse.setTransactionData( response );

        return direxTransactionResponse;
    }

    /**
     * Performs postprocess operations
     *
     * transactionRequest The transaction request transactionResponse The
     * transaction response
     *
     * @param transactionRequest
     * @param transactionResponse
     * @throws Exception
     */
    @Override
    public void postprocess(DirexTransactionRequest transactionRequest, DirexTransactionResponse transactionResponse) throws Exception {
    }

    /**
     * This method takes the DirexTransactionRequest Map and converts the
     * parameters to to entry Object, passes the raw XML object into a
     * Base64String and returns the response as a Map object.
     *
     * @param map
     * @return
     * @throws JAXBException
     */
    private Map contrataciones() throws JAXBException, Exception {

        Map responseMap = new HashMap();

        responseMap.put(ParameterName.AUTHO_NUMBER, "OE: AUTHO_NUMBER");
        responseMap.put(ParameterName.OP_CODE, "001");
        responseMap.put(ParameterName.OP_CODE2, "OE OP_CODE2");
        responseMap.put(ParameterName.IDCONSIGNOR, "OE IDCONSIGNOR");
        responseMap.put(ParameterName.IDBENEFICIARY, "IDBENEFICIARY");
        responseMap.put(ParameterName.BANK_AUTHO, "BANK_AUTHO");

        return responseMap;

    }
    private Map reportaPago() throws JAXBException, Exception {

        Map responseMap = new HashMap();

        responseMap.put( ParameterName.AUTHO_NUMBER,  "OE: AUTHO_NUMBER" );
        responseMap.put( ParameterName.OP_CODE, "001");
        responseMap.put( ParameterName.OP_CODE2, "OE OP_CODE2" );

        return responseMap;

    }
    
    private Map devolucion() throws JAXBException, Exception {

        Map responseMap = new HashMap();

        responseMap.put( ParameterName.AUTHO_NUMBER,  "OE: AUTHO_NUMBER" );
        responseMap.put( ParameterName.OP_CODE, "001");
        responseMap.put( ParameterName.OP_CODE2, "OE OP_CODE2" );

        return responseMap;

    }
    
    private Map log() throws JAXBException, Exception {

        Map responseMap = new HashMap();

        responseMap.put(ParameterName.OESTATUS, "3");
        
        return responseMap;

    }

}
