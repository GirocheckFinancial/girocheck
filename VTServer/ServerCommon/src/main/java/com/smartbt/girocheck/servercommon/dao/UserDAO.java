/*
 ** File: UserDAO.java
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
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.UserDisplay;
import com.smartbt.girocheck.servercommon.model.Role;
import com.smartbt.girocheck.servercommon.model.User;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.girocheck.common.VTSuiteMessages;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ariel Saavedra
 */
public class UserDAO extends BaseDAO<User> {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserDAO.class);

    protected static UserDAO dao;

    public UserDAO() {
        super(User.class);
    }

    public static UserDAO get() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    /**
     * Update a User
     *
     * @param user
     *
     */
    public void updateUser(UserDisplay user) throws ValidationException, NoSuchAlgorithmException {
        User aux = findById(user.getId());
        Role role = RoleDAO.get().findById(user.getRole().getId());
        
        aux.setUsername(user.getUsername());
        aux.setPassword(user.getPassword());
        aux.setFirstName(user.getFirstName());
        aux.setLastName(user.getLastName());
        aux.setActive(user.getActive());
        aux.setEmail(user.getEmail());
        aux.setRole(role);
        
        HibernateUtil.getSession().saveOrUpdate(aux);
    }

    /**
     * Search all the Users by a given filter
     *
     * @param search
     * @param firstResult
     * @param maxResult
     * @return
     *
     */
    public List<UserDisplay> searchUsers(String search, int firstResult, int maxResult) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(User.class).createAlias("role", "role");

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("username").as("username"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("email").as("email"))
                .add(Projections.property("password").as("password"))
                .add(Projections.property("active").as("active"))
                .add(Projections.property("role.id").as("role.id"))
                .add(Projections.property("role.name").as("role.name"));

        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }

        if (search != null && !search.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("username", search, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("lastName", search, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("firstName", search, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("email", search, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("role.name", search, MatchMode.ANYWHERE).ignoreCase());
            criteria.add(disjunction);
        }

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new TransformerComplexBeans(UserDisplay.class));
        /**************/
        
        List<UserDisplay> UserList = criteria.list();
        
        return UserList;
//        return criteria.list();
    }

    public User findByEmail(String email) {
        Criteria cri = HibernateUtil.getSession().createCriteria(User.class).add(Restrictions.eq("email", email));
        return (User) cri.uniqueResult();
    }
    
    public void deleteUser(int idUser) {        
        super.delete(findById(idUser));
    }
        
    public void addUser(String userName, String password, String firstName, String lastName, Boolean active, String email, int roleid) throws ValidationException, NoSuchAlgorithmException {
//
        Role role = RoleDAO.get().findById(roleid);
        
                User user = new User();
                user.setUsername(userName);
                user.setPassword(encryptPassword(password));
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setActive(active);
                user.setEmail(email);
                user.setRole(role);
                HibernateUtil.getSession().saveOrUpdate(user);
    }
    
    public void changePassword(int userId, String password){
        
        try {
            User us = findById(userId);
            us.setPassword(encryptPassword(password));
            HibernateUtil.getSession().saveOrUpdate(us);
        } catch (ValidationException ex) {
//            log.debug("[UserDAO] ", ex);
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
//            log.debug("[UserDAO] ", ex);
            ex.printStackTrace();
        }
        
    }
    
    public String encryptPassword(String password) throws ValidationException, NoSuchAlgorithmException {
        
         if (  password == null ) {
            throw new ValidationException( VTSuiteMessages.CANNOT_ENCRYPT_NULL_PASSWORD );
        }
        
        MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
        byte[] result = mDigest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
    
}
