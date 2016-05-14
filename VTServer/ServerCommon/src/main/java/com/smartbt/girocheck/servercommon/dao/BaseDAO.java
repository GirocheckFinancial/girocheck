/*
 ** File: SbtAMSFrontDao.java
 **
 ** Date Created: October 2013
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

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

/**
 *
 * @author Ariel Saavedra
 * @param <T>
 */
public class BaseDAO<T> {

    private Class<T> typeObject;

    @SuppressWarnings("unchecked")
    protected BaseDAO() {
        try {
            this.typeObject = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass())
                    .getActualTypeArguments()[0];
        } catch (Exception e) {
        }

    }

    public void setTypeObject(Class<T> typeObject) {
        this.typeObject = typeObject;
    }

    protected BaseDAO(Class<T> typeObject) {
        this.typeObject = typeObject;
    }

    public T findById(int id) {
        return (T) HibernateUtil.getSession().get(typeObject, id);
    }

    public Integer save(T obj) {
       return (Integer)HibernateUtil.getSession().save(obj);
    }

    public void saveOrUpdate(T obj) {
        HibernateUtil.getSession().saveOrUpdate(obj);
    }

    public void update(T obj) {
        HibernateUtil.getSession().update(obj);
    }

    public void merge(T obj) {
        HibernateUtil.getSession().merge(obj);
    }

    public List<T> list() {
        return HibernateUtil.getSession().createCriteria(typeObject).addOrder(Order.asc("id")).list();
    }

    public List<T> listByExample(T obj) {
        List<T> lista;
        Example example
                = Example.create(obj).excludeZeroes() //exclude zero valued properties            
                .ignoreCase() //perform case insensitive string comparisons
                .enableLike(MatchMode.ANYWHERE); //use like for string comparisons
        
        lista = HibernateUtil.getSession().createCriteria(typeObject).add(example).addOrder(Order.asc("id")).list();

        return lista;
    }

    public List<T> listQuery(String query) {
        List<T> lista;
        
        String queryToExecute = "from " + typeObject.getName() + " where " + query;
        Query q = HibernateUtil.getSession().createQuery(queryToExecute);
        lista = q.list();
        return lista;
    }

    public void delete(T obj) {
        HibernateUtil.getSession().delete(obj);
    }

    public void deleteGivenID(int id, String table) {
        HibernateUtil.getSession().createSQLQuery("delete from " + table + " where id =" + id).executeUpdate();
    }

    public boolean existById(int id) {
        return HibernateUtil.getSession().get(typeObject, id) != null;
    }
}
