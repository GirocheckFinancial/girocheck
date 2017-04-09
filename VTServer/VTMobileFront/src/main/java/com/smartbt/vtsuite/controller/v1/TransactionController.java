/*
 ** File: TransactionController.java
 **
 ** Date Created: October 2014
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
package com.smartbt.vtsuite.controller.v1;

import static com.smartbt.vtsuite.controller.v1.GeneralController.TOKEN;
import com.smartbt.vtsuite.manager.TransactionManager;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping("/v1/tx")
public class TransactionController {

    @Autowired
    TransactionManager transactionManager;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Map listExpenses(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "limit", defaultValue = "0") Integer limit,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate,
            @RequestParam(value = "clientId") Integer clientId,
            HttpSession session) throws Exception {

        System.out.println("page = " + page + " \n start = " + start + "\n limit = " + limit + "\n startDate = " + startDate + "\n endDate = " + endDate + " \n clientId = " + clientId);
        String token = (String) session.getAttribute(TOKEN); 
        return transactionManager.transactionHistory(clientId, page, start, limit, startDate, endDate, token);
    }
}
