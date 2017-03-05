/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alodiga.web.mobile;

import com.alodiga.persistence.manager.OperationManager;
import com.alodiga.util.response.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Robe
 */
@RestController
@RequestMapping(value = "/operations", method = RequestMethod.GET)
public class OperationsController {
    @Autowired
    private OperationManager operationManager;

    @RequestMapping(value = "/migrateStateList", method = RequestMethod.GET)
    public WebResponse migrateStateList() {
        System.out.println("migrateStateList()");
        operationManager.migrateStateList();
        return new WebResponse();
    }

   

    @RequestMapping(value = "/validateStateCities", method = RequestMethod.GET)
    public WebResponse validateStateCities() {
        System.out.println("OperationsController -> validateStateCities()");
        operationManager.validateStateCities();
        return new WebResponse();
    }

   
     

}
