/*
 ** File: CustomerDAO.java
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
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Customer;
import com.smartbt.vtsuite.servercommon.model.CustomerAddress;
import com.smartbt.vtsuite.servercommon.model.Device;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.Telephone;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.display.common.model.AddressDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.CustomerDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.PhoneTypeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TelephoneDisplay;
import com.smartbt.vtsuite.servercommon.model.TelephoneType;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ariel Saavedra
 */
public class CustomerDAO extends BaseDAO<Customer> {

    protected static CustomerDAO dao;

    public CustomerDAO() {
        // super(Customer.class);
    }

    public static CustomerDAO get() {
        if (dao == null) {
            dao = new CustomerDAO();
        }
        return dao;
    }

    public Collection<Customer> getCustomers() {
        return HibernateUtil.getSession().createCriteria(Customer.class).list();
    }

    /**
     * Get a Customer by a given id
     *
     * @param id Customer id
     * @return Customer
     *
     */
    public List<CustomerDisplay> getCustomer(int id) {
        List<CustomerDisplay> customers = new LinkedList<CustomerDisplay>();
        Criteria criteriaC = HibernateUtil.getSession().createCriteria(Customer.class).add(Restrictions.idEq(id));
        Customer customer = (Customer) criteriaC.uniqueResult();
        CustomerDisplay customerDisplay = new CustomerDisplay();
        customerDisplay.setId(customer.getId());
        customerDisplay.setActive(customer.getActive());
        customerDisplay.setDescription(customer.getDescription());
        customerDisplay.setName(customer.getName());
        customerDisplay.setNumber(customer.getNumber());

        SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
        customerDisplay.setActivationDate(customer.getActivationDate() == null ? null : dateF.format(customer.getActivationDate()));
        customerDisplay.setDeactivationDate(customer.getDeactivationDate() == null ? null : dateF.format(customer.getDeactivationDate()));

        List<AddressDisplay> addressDisplays = new LinkedList<AddressDisplay>();
        for (CustomerAddress address : customer.getCustomerAddress()) {
            AddressDisplay addressDisplay = new AddressDisplay();
            addressDisplay.setAddress1(address.getAddress1());
            addressDisplay.setAddress2(address.getAddress2());
            addressDisplay.setCity(address.getCity());
            addressDisplay.setCountry(address.getCountry() == null ? null : address.getCountry().getName());
            //addressDisplay.setState(address.getState() == null ? null : address.getState().getName());
            addressDisplay.setZip(address.getZip());
            addressDisplays.add(addressDisplay);
        }
        customerDisplay.setAddress(addressDisplays);

        List<TelephoneDisplay> telephoneDisplays = new LinkedList<TelephoneDisplay>();
        for (Telephone telephone : customer.getCustomerTelephone()) {
            PhoneTypeDisplay phoneTypeDisplay = new PhoneTypeDisplay();
            phoneTypeDisplay.setId(telephone.getTelephoneType().getId());
            phoneTypeDisplay.setName(telephone.getTelephoneType().getName());
            
            TelephoneDisplay telephoneDisplay = new TelephoneDisplay();
            telephoneDisplay.setTelephoneType(phoneTypeDisplay);
            telephoneDisplay.setNumber(telephone.getNumber());
            telephoneDisplays.add(telephoneDisplay);
        }
        customerDisplay.setTelephones(telephoneDisplays);
        customers.add(customerDisplay);

        return customers;
    }

    /**
     * Search all the Customers by a given filter
     *
     * @param search
     * @return
     */
    public List<CustomerDisplay> search(String search) {
        List<CustomerDisplay> customers;
        List<CustomerDisplay> result = null;
// Looking for on Customer table
        Criteria customer = HibernateUtil.getSession().createCriteria(Customer.class);
        Disjunction disjunctionC = (Disjunction) Restrictions.disjunction().add(Restrictions.like("name", search, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("description", search, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("number", search, MatchMode.ANYWHERE).ignoreCase());
        ProjectionList projectionListC = Projections.projectionList().add(Projections.property("id").as("id"));
        customer.add(disjunctionC);
        customer.setProjection(projectionListC);
        customer.setResultTransformer(Transformers.aliasToBean(CustomerDisplay.class));
        customers = customer.list();

//Looking for on merchant table
        Criteria merchant = HibernateUtil.getSession().createCriteria(Merchant.class).createAlias("customer", "customer");
        for (int i = 0; i < customers.size(); i++) {
            merchant.add(Restrictions.ne("customer.id", customers.get(i).getId()));
        }
        Disjunction disjunctionM = (Disjunction) Restrictions.disjunction().add(Restrictions.like("name", search, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("description", search, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("number", search, MatchMode.ANYWHERE).ignoreCase());
        ProjectionList projectionListM = Projections.projectionList().add(Projections.property("customer.id").as("id")).add(Projections.groupProperty("customer.id"));
        merchant.add(disjunctionM);
        merchant.setProjection(projectionListM);
        merchant.setResultTransformer(Transformers.aliasToBean(CustomerDisplay.class));
        customers.addAll(merchant.list());

//Looking for on terminal table
        Criteria terminal = HibernateUtil.getSession().createCriteria(Terminal.class).createAlias("merchant", "merchant").createAlias("merchant.customer", "customer");
        for (int i = 0; i < customers.size(); i++) {
            terminal.add(Restrictions.ne("customer.id", customers.get(i).getId()));
        }
        Disjunction disjunctionT = (Disjunction) Restrictions.disjunction().add(Restrictions.like("terminalId", search, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("serialNumber", search, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("description", search, MatchMode.ANYWHERE).ignoreCase());
        ProjectionList projectionListT = Projections.projectionList().add(Projections.property("customer.id").as("id")).add(Projections.groupProperty("customer.id"));
        terminal.add(disjunctionT);
        terminal.setProjection(projectionListT);
        terminal.setResultTransformer(Transformers.aliasToBean(CustomerDisplay.class));
        customers.addAll(terminal.list());
//Looking for on device table
        Criteria device = HibernateUtil.getSession().createCriteria(Device.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("merchant.customer", "customer");
        for (int i = 0; i < customers.size(); i++) {
            device.add(Restrictions.ne("customer.id", customers.get(i).getId()));
        }
        Disjunction disjunctionD = (Disjunction) Restrictions.disjunction().add(Restrictions.like("serialNumber", search, MatchMode.ANYWHERE).ignoreCase()).add(Restrictions.like("description", search, MatchMode.ANYWHERE).ignoreCase());
        ProjectionList projectionListD = Projections.projectionList().add(Projections.property("customer.id").as("id")).add(Projections.groupProperty("customer.id"));
        device.add(disjunctionD);
        device.setProjection(projectionListD);
        device.setResultTransformer(Transformers.aliasToBean(CustomerDisplay.class));
        customers.addAll(device.list());

        List<Integer> collection = new LinkedList<Integer>();
        for (int i = 0; i < customers.size(); i++) {
            collection.add(customers.get(i).getId());
        }
        collection.add(0);
        Criteria c = HibernateUtil.getSession().createCriteria(Customer.class).add(Restrictions.in("id", collection));
        ProjectionList pL = Projections.projectionList().add(Projections.property("id").as("id")).add(Projections.property("name").as("name")).add(Projections.property("description").as("description")).add(Projections.property("active").as("active")).add(Projections.property("number").as("number"));
        c.setProjection(pL);
        c.setResultTransformer(Transformers.aliasToBean(CustomerDisplay.class));
        result = c.list();

        return result;
    }

    public Customer getCustomerByNumber(String number) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Customer.class).add(Restrictions.eq("number", number));
        return (Customer) criteria.uniqueResult();
    }
}
