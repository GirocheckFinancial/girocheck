/*
 ** File: MerchantDAO.java
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
import com.smartbt.vtsuite.servercommon.display.common.model.AddressDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.MerchantDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.PhoneTypeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.StateDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TelephoneDisplay;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.MerchantAddress;
import com.smartbt.vtsuite.servercommon.model.Telephone;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.utils.receipt.ImageHelper;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class MerchantDAO extends BaseDAO<Merchant> {
    
    protected static MerchantDAO dao;
    
    public MerchantDAO() {
        //super(Merchant.class);
    }
    
    public static MerchantDAO get() {
        if (dao == null) {
            dao = new MerchantDAO();
        }
        return dao;
    }

    /**
     * Get a Merchant by a given id
     *
     * @param id Merchant id
     * @return Merchant
     *
     */
    public MerchantDisplay getMerchant(int id) throws SQLException {
        MerchantDisplay merchantDisplay = new MerchantDisplay();
        
        Criteria criteriaM = HibernateUtil.getSession().createCriteria(Merchant.class).add(Restrictions.idEq(id));
        Merchant merchant = (Merchant) criteriaM.uniqueResult();
        merchantDisplay.setId(merchant.getId());
        merchantDisplay.setActive(merchant.getActive());
        merchantDisplay.setDescription(merchant.getDescription());
        merchantDisplay.setName(merchant.getName());
        merchantDisplay.setNumber(merchant.getNumber());
        merchantDisplay.setSic(merchant.getSic());
        if (merchant.getLogo() != null) {
            byte[] bdata = merchant.getLogo().getBytes(1, (int) merchant.getLogo().length());
            merchantDisplay.setLogoImage(new String(bdata));
        }
        SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
        merchantDisplay.setActivationDate(merchant.getActivationDate() == null ? null : dateF.format(merchant.getActivationDate()));
        merchantDisplay.setDeactivationDate(merchant.getDeactivationDate() == null ? null : dateF.format(merchant.getDeactivationDate()));
        
        List<AddressDisplay> addressDisplays = new LinkedList<AddressDisplay>();
        for (MerchantAddress address : merchant.getMerchantAddress()) {
            AddressDisplay addressDisplay = new AddressDisplay();
            addressDisplay.setAddress1(address.getAddress1());
            addressDisplay.setAddress2(address.getAddress2());
            addressDisplay.setCity(address.getCity());
            addressDisplay.setCountry(address.getCountry() == null ? null : address.getCountry().getName());
            //addressDisplay.setState(address.getState() == null ? null : address.getState().getName());
            addressDisplay.setZip(address.getZip());
            addressDisplays.add(addressDisplay);
        }
        merchantDisplay.setAddress(addressDisplays);
        
        List<TelephoneDisplay> telephoneDisplays = new LinkedList<TelephoneDisplay>();
        for (Telephone telephone : merchant.getMerchantTelephone()) {
            TelephoneDisplay telephoneDisplay = new TelephoneDisplay();
            //telephoneDisplay.setTelephoneType(telephone.getTelephoneType().getName());
            telephoneDisplay.setNumber(telephone.getNumber());
            telephoneDisplays.add(telephoneDisplay);
        }
        merchantDisplay.setTelephones(telephoneDisplays);
        merchantDisplay.setCustomerName(merchant.getCustomer().getName());
        return merchantDisplay;
    }

    /**
     * Get the Merchants by a given Customer's id
     *
     * @param idCustomer Customer id
     * @return List Merchant by Customer
     *
     */
    public List<MerchantDisplay> getMerchantsByCustomer(int idCustomer) {
//        Criteria criteriaM = HibernateUtil.getSession().createCriteria(Merchant.class)
//                .createAlias("customer", "customer")
//                .add(Restrictions.eq("customer.id", idCustomer));
//        
        List<MerchantDisplay> merchantDisplayList = new LinkedList<MerchantDisplay>();
       // for (Merchant merchant : (List<Merchant>) criteriaM.list()) {
            MerchantDisplay merchantDisplay = new MerchantDisplay();
            
            merchantDisplay.setId(1);
            merchantDisplay.setActive(true);
            merchantDisplay.setDescription("Description");
            merchantDisplay.setName("Merchant's Name");
            merchantDisplay.setNumber("Merchant's  Number");
            merchantDisplay.setSic("Sic");
            
            SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
            merchantDisplay.setActivationDate( dateF.format(new Date()));
            merchantDisplay.setDeactivationDate( null );
            
            List<AddressDisplay> addressDisplays = new LinkedList<AddressDisplay>();
           
                StateDisplay stateDisplay = new StateDisplay();
                stateDisplay.setId(1);
                
                AddressDisplay addressDisplay = new AddressDisplay();
                addressDisplay.setId(1);
                addressDisplay.setAddress1("Address1");
                addressDisplay.setAddress2("Address2");
                addressDisplay.setCity("Miami");
                addressDisplay.setCountry("United States");
                //addressDisplay.setState(address.getState() == null ? null : address.getState().getName());
                addressDisplay.setZip("33245");
                addressDisplay.setState(stateDisplay);
                
                addressDisplays.add(addressDisplay);
            
            merchantDisplay.setAddress(addressDisplays);
            
            List<TelephoneDisplay> telephoneDisplays = new LinkedList<TelephoneDisplay>();
                PhoneTypeDisplay phoneTypeDisplay = new PhoneTypeDisplay();
                phoneTypeDisplay.setId(1);
                
                TelephoneDisplay telephoneDisplay = new TelephoneDisplay();
                telephoneDisplay.setId(1);
                telephoneDisplay.setNumber("786-567-9898");
                telephoneDisplay.setTelephoneType(phoneTypeDisplay);
                
                telephoneDisplays.add(telephoneDisplay);
            
            merchantDisplay.setTelephones(telephoneDisplays);
            //merchantDisplay.setCustomerName(merchant.getCustomer().getName());
            
            merchantDisplayList.add(merchantDisplay);
      //  }

//        ProjectionList projectionListM = Projections.projectionList()
//                .add(Projections.property("name").as("name"))
//                .add(Projections.property("id").as("id"))
//                .add(Projections.property("active").as("active"))
//                .add(Projections.property("monitor").as("monitor"))
//                .add(Projections.property("number").as("number"))
//                .add(Projections.property("description").as("description"))
//                .add(Projections.property("sic").as("sic"));
//        criteriaM.setProjection(projectionListM);
//        criteriaM.setResultTransformer(Transformers.aliasToBean(MerchantDisplay.class));
//        List<MerchantDisplay> merchantDisplayList = (List<MerchantDisplay>) criteriaM.list();
        return merchantDisplayList;
    }
    
    public MerchantDisplay setMerchantLogo(int idMerchant, InputStream logoImageInputStream) throws Exception {
        Merchant m = findById(idMerchant);
        BufferedImage img = ImageIO.read(logoImageInputStream);
        
        String base64Img = ImageHelper.resizeImagePNG(img, "png", 400, 200, true);
        
        java.sql.Blob signatureBlob = new SerialBlob(base64Img.getBytes());
        m.setLogo(signatureBlob);
        saveOrUpdate(m);
        return getMerchant(idMerchant);
    }

//    public static String encodeToString(BufferedImage image, String type) {
//        String imageString = null;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        try {
//            ImageIO.write(image, type, bos);
//            byte[] imageBytes = bos.toByteArray();
//
//            BASE64Encoder encoder = new BASE64Encoder();
//            imageString = encoder.encode(imageBytes);
//
//            bos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return imageString;
//    }
    public Merchant getMerchantByNumber(String number) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Merchant.class).add(Restrictions.eq("number", number));
        return (Merchant) criteria.uniqueResult();
    }

    public Merchant validateNoRepeatMerchant(String number, int idMerchant) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Merchant.class).add(Restrictions.eq("number", number)).add(Restrictions.ne("id", idMerchant));
        return (Merchant) criteria.uniqueResult();
    }
    
    public Merchant getMerchantByTerminal(int idTerminal) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Merchant.class).
                createAlias("terminal", "terminal").
                add(Restrictions.eq("terminal.id", idTerminal));
        return (Merchant) criteria.uniqueResult();
    }
      
      
     
    
}
