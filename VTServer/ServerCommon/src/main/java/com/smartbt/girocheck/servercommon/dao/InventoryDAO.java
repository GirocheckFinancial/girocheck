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
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.InventoryDisplay;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>, Alejo
 */
public class InventoryDAO extends BaseDAO<Merchant> {
 
    protected static InventoryDAO dao;

    public InventoryDAO() {
    }

    public static InventoryDAO get() {
        if (dao == null) {
            dao = new InventoryDAO();
        }
        return dao;
    }

    public ResponseDataList searchInventory(int firstResult, int maxResult) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(Merchant.class);

        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult * maxResult);
            criteria.setMaxResults(maxResult);
        }

        criteria.setMaxResults(maxResult);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("legalName").as("merchant"))
                .add(Projections.property("inventory").as("inventory"))
                .add(Projections.property("threshold").as("threshold"));
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(InventoryDisplay.class));

        List<InventoryDisplay> list = (List<InventoryDisplay>) criteria.list();
        
        Criteria counterCriteria = HibernateUtil.getSession().createCriteria(Merchant.class);
        Long total = (Long)counterCriteria.setProjection(Projections.rowCount()).uniqueResult();
        ResponseDataList response = new ResponseDataList();
        response.setData(list);
        Integer totalPages = (int) Math.ceil((float) total / (float) maxResult);;
         response.setTotalPages(totalPages);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
    
    public static void main(String[] args){
        Long total = 7L;
         int maxResult = 3;
         
        int a = (int) Math.ceil((float) total / (float) maxResult);
         
         System.out.println("a = " +a); 
    }

    public void saveOrUpdate(InventoryDisplay inventory) {
        Merchant merchant = findById(inventory.getId());
        
        if(inventory.getInventory() != null && inventory.getInventory() != 0){
            merchant.setInventory(inventory.getInventory());
        }
         
        if(inventory.getThreshold() != null && inventory.getThreshold() != 0){
            merchant.setThreshold(inventory.getThreshold());
        }
        
        saveOrUpdate(merchant);
    }

}
