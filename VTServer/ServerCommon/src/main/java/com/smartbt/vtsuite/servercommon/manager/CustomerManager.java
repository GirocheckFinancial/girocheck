/*
 ** File: CustomerManager.java
 **
 ** Date Created: March 2013
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
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.CustomerDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.display.common.model.AddressDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.CustomerDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.PhoneTypeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TelephoneDisplay;
import com.smartbt.vtsuite.servercommon.model.Customer;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class CustomerManager {

    private static final Logger log = Logger.getLogger(CustomerManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private CustomerDAO customerDAO = CustomerDAO.get();

    /**
     * Search all the Customer's by a filter
     *
     * @param search
     * @return
     */
    public ResponseDataList<CustomerDisplay> searchCustomer(String search) {
        ResponseDataList<CustomerDisplay> response = new ResponseDataList<CustomerDisplay>();
        if (search != null && !validators.isSearchAllowed(search)) {
            log.info("----->  searchCustomer: This filter is not allowed <-----");
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.FILTER_IS_NOT_ALLOWED);
        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(customerDAO.search(search));
        }
        return response;
    }

    /**
     * Search all Customers
     *
     * @return
     */
    public ResponseDataList<Customer> getCustomers() {
        ResponseDataList<Customer> response = new ResponseDataList<Customer>();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(customerDAO.getCustomers());
        return response;
    }

    /**
     * Get a Customer by a given id
     *
     * @param id
     * @return
     */
    public ResponseDataList<CustomerDisplay> getCustomer(int id) {
        ResponseDataList<CustomerDisplay> response = new ResponseDataList<CustomerDisplay>();
//        if (customerDAO.findById(id) == null) {
//            log.info("-----> getCustomer: This Customer does not exist <-----");
//            response.setStatus(Constants.CODE_ERROR_GENERAL);
//            response.setStatusMessage(VTSuiteMessages.CUSTOMER_DOES_NOT_EXIST);
//        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(getCustomerList(id));
     //   }
        return response;
    }
    
      public List<CustomerDisplay> getCustomerList(int id) {
        List<CustomerDisplay> customers = new LinkedList<CustomerDisplay>();
       
        CustomerDisplay customerDisplay = new CustomerDisplay();
        customerDisplay.setId(1);
        customerDisplay.setActive(true);
        customerDisplay.setDescription("Customer description.");
        customerDisplay.setName("Girocheck");
        customerDisplay.setNumber("1234");

        SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
        customerDisplay.setActivationDate( dateF.format(new Date()));
        customerDisplay.setDeactivationDate(null);

        List<AddressDisplay> addressDisplays = new LinkedList<AddressDisplay>();
            AddressDisplay addressDisplay = new AddressDisplay();
            addressDisplay.setAddress1("Address 1");
            addressDisplay.setAddress2("Address 2");
            addressDisplay.setCity("Miami");
            addressDisplay.setCountry("United States");
            //addressDisplay.setState(address.getState() == null ? null : address.getState().getName());
            addressDisplay.setZip("22345");
            addressDisplays.add(addressDisplay);
        
        customerDisplay.setAddress(addressDisplays);

        List<TelephoneDisplay> telephoneDisplays = new LinkedList<TelephoneDisplay>();
            PhoneTypeDisplay phoneTypeDisplay = new PhoneTypeDisplay();
            phoneTypeDisplay.setId(1);
            phoneTypeDisplay.setName("Movil");
            
            TelephoneDisplay telephoneDisplay = new TelephoneDisplay();
            telephoneDisplay.setTelephoneType(phoneTypeDisplay);
            telephoneDisplay.setNumber("786-454-3232");
            telephoneDisplays.add(telephoneDisplay);
        
        customerDisplay.setTelephones(telephoneDisplays);
        customers.add(customerDisplay);

        return customers;
    }
}
