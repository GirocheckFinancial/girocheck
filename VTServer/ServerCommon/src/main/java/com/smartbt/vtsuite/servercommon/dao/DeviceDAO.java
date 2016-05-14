/*
 ** File: DeviceDAO.java
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
import com.smartbt.vtsuite.servercommon.model.Device;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.display.common.model.DeviceDisplay;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ariel Saavedra
 */
public class DeviceDAO extends BaseDAO<Device> {

    protected static DeviceDAO dao;

    public DeviceDAO() {
        // super(Device.class);
    }

    public static DeviceDAO get() {
        if (dao == null) {
            dao = new DeviceDAO();
        }
        return dao;
    }

    /**
     * Get a Device by a given id
     *
     * @param id Device id
     * @return Device
     *
     */
    public DeviceDisplay getDevice(int id) {
        DeviceDisplay deviceDisplay;

        Criteria criteriaD = HibernateUtil.getSession().createCriteria(Device.class).add(Restrictions.idEq(id));
        ProjectionList projectionListD = Projections.projectionList().add(Projections.property("serialNumber").as("serialNumber")).add(Projections.property("description").as("description")).add(Projections.property("active").as("active"));
        criteriaD.setProjection(projectionListD);
        criteriaD.setResultTransformer(Transformers.aliasToBean(DeviceDisplay.class));
        deviceDisplay = (DeviceDisplay) criteriaD.uniqueResult();
        return deviceDisplay;
    }

    /**
     * Get the Devices by a given Terminal's id
     *
     * @param idTerminal Terminal id
     * @return List Device by Terminal
     */
    public List<DeviceDisplay> getDevicesByTerminal(int idTerminal) {
        List<DeviceDisplay> devices = null;
        Criteria criteriaD = HibernateUtil.getSession().createCriteria(Device.class).createAlias("productType", "productType").createAlias("terminal", "terminal").add(Restrictions.eq("terminal.id", idTerminal));
        ProjectionList projectionListD = Projections.projectionList().add(Projections.property("serialNumber").as("serialNumber")).add(Projections.property("id").as("id")).add(Projections.property("description").as("description")).add(Projections.property("active").as("active")).add(Projections.property("description").as("description")).add(Projections.property("productType.name").as("name")).add(Projections.property("productType.productCode").as("productCode"));
        criteriaD.setProjection(projectionListD);
        criteriaD.setResultTransformer(Transformers.aliasToBean(DeviceDisplay.class));
        devices = criteriaD.list();
        return devices;
    }

    public Device getDeviceByTerminalId(String terminalId, int productCode) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Device.class).createAlias("terminal", "terminal").createAlias("productType", "productType").add(Restrictions.eq("terminal.terminalId", terminalId)).add(Restrictions.eq("productType.productCode", productCode));
        return (Device) criteria.uniqueResult();
    }
}
