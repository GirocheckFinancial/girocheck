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
import com.smartbt.girocheck.servercommon.utils.PasswordUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
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

        aux.setUsername(user.getUsername());
        aux.setFirstName(user.getFirstName());
        aux.setLastName(user.getLastName());

        aux.setEmail(user.getEmail());

        if (!aux.getActive() && user.getActive()) {
            aux.setFailedAttempts(0);
        }

        aux.setActive(user.getActive());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            aux.setPassword(user.getPassword());
        }

        if (user.getRole() != null) {
            Role role = RoleDAO.get().findById(user.getRole().getId());
            aux.setRole(role);
        }

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

        criteria.addOrder(Order.asc("username"));

        return criteria.list();
    }

    public User findByEmail(String email) {
        Criteria cri = HibernateUtil.getSession().createCriteria(User.class).add(Restrictions.eq("email", email));
        return (User) cri.uniqueResult();
    }

    public void deleteUser(int idUser) {
        super.delete(findById(idUser));
    }

    public UserDisplay addUser(String userName, String password, String firstName, String lastName, Boolean active, String email, int roleid) throws ValidationException, NoSuchAlgorithmException {
        Role role = RoleDAO.get().findById(roleid);
        String encyptedPassword = PasswordUtil.encryptPassword(password);

        User user = new User();
        user.setUsername(userName);
        user.setPassword(encyptedPassword);
        user.setLast5passwords(encyptedPassword + " ");
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(active);
        user.setEmail(email);
        user.setRole(role);
        HibernateUtil.getSession().saveOrUpdate(user);

        UserDisplay display = new UserDisplay();
        display.setPassword(password);
        return display;
    }

    public void changePassword(int userId, String password) throws ValidationException {
        try {
            String encryptedPassword = PasswordUtil.encryptPassword(password);

            User us = findById(userId);

            String last5Passwords = us.getLast5passwords();

            if (last5Passwords == null || last5Passwords.isEmpty()) {
                last5Passwords = encryptedPassword + " ";
            } else {
                String[] passwordsArray = last5Passwords.split(" ");

                for (String passw : passwordsArray) {
                    if (encryptedPassword.equals(passw)) {
                        throw new ValidationException(VTSuiteMessages.LAST_5_PASSW);
                    }
                }

                if (passwordsArray.length == 5) {
                    last5Passwords = last5Passwords.substring(last5Passwords.indexOf(" "));
                }

                last5Passwords += (encryptedPassword + " ");
            }

            us.setPassword(encryptedPassword);
            us.setLast5passwords(last5Passwords);
            HibernateUtil.getSession().saveOrUpdate(us);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

    }

    public UserDisplay getUserById(Integer id) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(User.class)
                .add(Restrictions.eq("id", id)).setMaxResults(1);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("username").as("username"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("email").as("email"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new TransformerComplexBeans(UserDisplay.class));

        return (UserDisplay) criteria.uniqueResult();
    }

}
