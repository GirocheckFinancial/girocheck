/*
 ** File: TecnicardHostManager.java
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
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.mock.MockOrderExpressBusinessLogic;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 * The Host Manager class
 */
public class OrderExpressHostManager {

    AbstractBusinessLogicModule bizLogic;

    public DirexTransactionResponse processTransaction(DirexTransactionRequest direxTransactionRequest, Integer numberOfAttempts) throws Exception {

//        MockOrderExpressBusinessLogic bizLogic = new MockOrderExpressBusinessLogic();
//       OrderExpressBusinessLogic bizLogic = new OrderExpressBusinessLogic();
        DirexTransactionResponse response;

        fixZipCode(direxTransactionRequest);

        String prodProperty = System.getProperty("PROD");
        Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");
        System.out.println("OrderExpressHostManager() -> isProd = " + isProd);

        if (isProd) {
            System.out.println("bizLogic = new OrderExpressBusinessLogic();");
            bizLogic = new OrderExpressBusinessLogic();
        } else {
            System.out.println(" bizLogic = new MockOrderExpressBusinessLogic();");
            bizLogic = new MockOrderExpressBusinessLogic();
        }

        try {
            response = (DirexTransactionResponse) bizLogic.handle(direxTransactionRequest);
        } catch (Exception e) {
            if (numberOfAttempts > 1) {
                Thread.sleep(30_000);
                return processTransaction(direxTransactionRequest, numberOfAttempts - 1);
            } else {
                e.printStackTrace();
                return DirexTransactionResponse.forException(ResultCode.ORDER_EXPRESS_FAILED, ResultMessage.ORDER_EXPRESS_FAILED, " Error description: " + e.getMessage(), "");
            }

        }

        if (!response.getTransactionType().equals(TransactionType.ORDER_EXPRESS_LOGS)) {

            if (!response.getTransactionData().containsKey(ParameterName.OP_CODE) || response.getTransactionData().get(ParameterName.OP_CODE) == null || ((String) response.getTransactionData().get(ParameterName.OP_CODE)).isEmpty()) {
                return DirexTransactionResponse.forException(response.getTransactionType(), ResultCode.ORDER_EXPRESS_FAILED, ResultMessage.ORDER_EXPRESS_FAILED, " OrderExpress did not return op_code1.");
            }

            String opCode = (String) response.getTransactionData().get(ParameterName.OP_CODE);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] opCode: " + opCode, null);

            if (!opCode.equals("001")) {
                String opCode2 = (String) response.getTransactionData().get(ParameterName.OP_CODE2);

                if (opCode2 != null && needToRepeatTransaction(opCode2) && numberOfAttempts > 1) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] OP_CODE2 = " + opCode2 + ", Re-Submitting request... ", null);
                    Thread.sleep(30_000);
                    return processTransaction(direxTransactionRequest, numberOfAttempts - 1);
                } else {
                    response = DirexTransactionResponse.forException(response.getTransactionType(), ResultCode.ORDER_EXPRESS_FAILED, ResultMessage.ORDER_EXPRESS_FAILED, " Order Express return OP_CODE : " + opCode + "and OP_CODE2: " + (String) response.getTransactionData().get(ParameterName.OP_CODE2));
                    response.setErrorCode(opCode2);
                }

            }
        } else {

            if (response.getTransactionData().containsKey(ParameterName.OP_CODE) && (response.getTransactionData().get(ParameterName.OP_CODE) == null || ((String) response.getTransactionData().get(ParameterName.OP_CODE)).isEmpty())) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] OrderExpress LogMethod did not return an op_code value.", null);
                return DirexTransactionResponse.forException(response.getTransactionType(), ResultCode.ORDER_EXPRESS_FAILED, ResultMessage.ORDER_EXPRESS_FAILED, " OrderExpress LogMethod did not return an op_code value.");
            } else if (response.getTransactionData().containsKey(ParameterName.OP_CODE)) {
                String opCode = (String) response.getTransactionData().get(ParameterName.OP_CODE);
                String opCode2 = (String) response.getTransactionData().get(ParameterName.OP_CODE2);

                if (opCode2 != null && needToRepeatTransaction(opCode2) && numberOfAttempts > 1) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] OP_CODE2 == opCode2, Re-Submitting request... ", null);
                    Thread.sleep(30_000);
                    return processTransaction(direxTransactionRequest, numberOfAttempts - 1);
                } else {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] LogMethod opCode: " + opCode, null);
                    response = DirexTransactionResponse.forException(response.getTransactionType(), ResultCode.ORDER_EXPRESS_FAILED, ResultMessage.ORDER_EXPRESS_FAILED, " Order Express LogMethod return OP_CODE : " + opCode + "and OP_CODE2: " + (String) response.getTransactionData().get(ParameterName.OP_CODE2));
                    response.setErrorCode(opCode2);
                }
            } else {
                //LOG Method behavior
                String status = (String) response.getTransactionData().get(ParameterName.OESTATUS);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] Status: " + status, null);

                if (!status.equals("3")) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] Condition Status != 3 and OELOGTIMEOUT value: " + response.getTransactionData().get(ParameterName.OELOGTIMEOUT), null);

                    if (numberOfAttempts > 1) {
                        Thread.sleep(30_000);
                        return processTransaction(direxTransactionRequest, numberOfAttempts - 1);
                    }

                    if ((boolean) response.getTransactionData().get(ParameterName.OELOGTIMEOUT)) {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] OELOGTIMEOUT true", null);
                        response = DirexTransactionResponse.forException(response.getTransactionType(), ResultCode.ORDER_EXPRESS_FAILED, ResultMessage.OE_LOG_TIME_OUT, " Order Express return OESTATUS : " + status);
                        response.setErrorCode((String) response.getTransactionData().get(ParameterName.OESTATUS));
                    } else {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressHostManager] OELOGTIMEOUT false", null);
                        response = DirexTransactionResponse.forException(response.getTransactionType(), ResultCode.ORDER_EXPRESS_FAILED, selectResultMessage(status), " Order Express return OESTATUS : " + status);
                        response.setErrorCode((String) response.getTransactionData().get(ParameterName.OESTATUS));
                    }
                }
            }
        }

        if (response.wasApproved()) {
            SubTransaction subTransaction = new SubTransaction();
            subTransaction.setType(response.getTransactionType().getCode());
            subTransaction.setResultCode(ResultCode.SUCCESS.getCode());
            subTransaction.setErrorCode(response.getErrorCode());
            subTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
            subTransaction.setHost(NomHost.ORDER_EXPRESS.getId());
            response.getTransaction().addSubTransaction(subTransaction);
        }

        return response;
    }

//    public static void main(String[] args) throws Exception {
//       DirexTransactionRequest req = new DirexTransactionRequest();
//       Map map = new HashMap();
//       map.put(ParameterName.ZIPCODE, "33157-4567");
//       req.setTransactionData(map);
//       fixZipCode(req);
//        System.out.println(req.getTransactionData().get(ParameterName.ZIPCODE));
//    }
    private static void fixZipCode(DirexTransactionRequest direxTransactionRequest) {
        if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.ZIPCODE)
                && ((String) direxTransactionRequest.getTransactionData().get(ParameterName.ZIPCODE)).length() > 5) {
            String newZip = ((String) direxTransactionRequest.getTransactionData().get(ParameterName.ZIPCODE)).substring(0, 5);
            direxTransactionRequest.getTransactionData().put(ParameterName.ZIPCODE, newZip);
        }
    }

    private static boolean needToRepeatTransaction(String resultCode) {
        try {
            int code = Integer.parseInt(resultCode);

            return ((code >= 201 && code <= 282)
                    || (code >= 411 && code <= 424)
                    || (code >= 430 && code < 490 && (code % 10 == 0 || code % 10 == 1))
                    || (code >= 482 && code <= 485)
                    || (code == 25)
                    || (code == 14)
                    || (code == 506));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultMessage selectResultMessage(String code) {

        switch (code) {

            case "201":
                return ResultMessage.OE_LOG_201;
            case "202":
                return ResultMessage.OE_LOG_202;
            case "205":
                return ResultMessage.OE_LOG_205;
            case "206":
                return ResultMessage.OE_LOG_206;
            case "207":
                return ResultMessage.OE_LOG_207;
            case "208":
                return ResultMessage.OE_LOG_208;
            case "210":
                return ResultMessage.OE_LOG_210;
            case "211":
                return ResultMessage.OE_LOG_211;
            case "212":
                return ResultMessage.OE_LOG_212;
            case "213":
                return ResultMessage.OE_LOG_213;
            case "214":
                return ResultMessage.OE_LOG_214;
            case "215":
                return ResultMessage.OE_LOG_215;
            case "216":
                return ResultMessage.OE_LOG_216;
            case "217":
                return ResultMessage.OE_LOG_217;
            case "218":
                return ResultMessage.OE_LOG_218;
            case "219":
                return ResultMessage.OE_LOG_219;
            case "220":
                return ResultMessage.OE_LOG_220;
            case "221":
                return ResultMessage.OE_LOG_221;
            case "222":
                return ResultMessage.OE_LOG_222;
            case "223":
                return ResultMessage.OE_LOG_223;
            case "225":
                return ResultMessage.OE_LOG_225;
            case "226":
                return ResultMessage.OE_LOG_226;
            case "227":
                return ResultMessage.OE_LOG_227;
            case "228":
                return ResultMessage.OE_LOG_228;
            case "230":
                return ResultMessage.OE_LOG_230;
            case "231":
                return ResultMessage.OE_LOG_231;
            case "232":
                return ResultMessage.OE_LOG_232;
            case "233":
                return ResultMessage.OE_LOG_233;
            case "235":
                return ResultMessage.OE_LOG_235;
            case "236":
                return ResultMessage.OE_LOG_236;
            case "237":
                return ResultMessage.OE_LOG_237;
            case "238":
                return ResultMessage.OE_LOG_238;
            case "240":
                return ResultMessage.OE_LOG_240;
            case "241":
                return ResultMessage.OE_LOG_241;
            case "242":
                return ResultMessage.OE_LOG_242;
            case "243":
                return ResultMessage.OE_LOG_243;
            case "244":
                return ResultMessage.OE_LOG_244;
            case "245":
                return ResultMessage.OE_LOG_245;
            case "246":
                return ResultMessage.OE_LOG_246;
            case "247":
                return ResultMessage.OE_LOG_247;
            case "248":
                return ResultMessage.OE_LOG_248;
            case "249":
                return ResultMessage.OE_LOG_249;
            case "250":
                return ResultMessage.OE_LOG_250;
            case "251":
                return ResultMessage.OE_LOG_251;
            case "252":
                return ResultMessage.OE_LOG_252;
            case "253":
                return ResultMessage.OE_LOG_253;
            case "254":
                return ResultMessage.OE_LOG_254;
            case "255":
                return ResultMessage.OE_LOG_255;
            case "256":
                return ResultMessage.OE_LOG_256;
            case "257":
                return ResultMessage.OE_LOG_257;
            case "258":
                return ResultMessage.OE_LOG_258;
            case "259":
                return ResultMessage.OE_LOG_259;
            case "260":
                return ResultMessage.OE_LOG_260;
            case "261":
                return ResultMessage.OE_LOG_261;
            case "262":
                return ResultMessage.OE_LOG_262;
            case "263":
                return ResultMessage.OE_LOG_263;
            case "264":
                return ResultMessage.OE_LOG_264;
            case "265":
                return ResultMessage.OE_LOG_265;
            case "266":
                return ResultMessage.OE_LOG_266;
            case "267":
                return ResultMessage.OE_LOG_267;
            case "268":
                return ResultMessage.OE_LOG_268;
            case "269":
                return ResultMessage.OE_LOG_269;
            case "270":
                return ResultMessage.OE_LOG_270;
            case "271":
                return ResultMessage.OE_LOG_271;
            case "272":
                return ResultMessage.OE_LOG_272;
            case "273":
                return ResultMessage.OE_LOG_273;
            case "274":
                return ResultMessage.OE_LOG_274;
            case "275":
                return ResultMessage.OE_LOG_275;
            case "276":
                return ResultMessage.OE_LOG_276;
            case "277":
                return ResultMessage.OE_LOG_277;
            case "280":
                return ResultMessage.OE_LOG_280;
            case "281":
                return ResultMessage.OE_LOG_281;
            case "282":
                return ResultMessage.OE_LOG_282;
            default:
                return ResultMessage.ORDER_EXPRESS_FAILED;
        }

    }

}
