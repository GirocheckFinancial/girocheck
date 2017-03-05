/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alodiga.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rober
 * @param <T>
 * @param <I>
 */
public class BaseDAO <T , I extends Serializable> {

    protected final Class<T> type;

    @Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
        return sessionFactory;
    }

    public Session getSession(){
        return getSessionFactory().getCurrentSession();
    }
    
    public Class<T> getType() {
        return type;
    }

    public BaseDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional(readOnly = true)
    public T findById(I id) {
        return (T) getSession().get(getType(), id);
    }

    @Transactional
    public void delete(T obj) {
        getSession().delete(obj);
    }
    
    @Transactional
    public void delete(I id) {
        getSession().delete(findById(id));
    }

    @Transactional
    public T saveOrUpdate(T obj) {
        getSession().saveOrUpdate(obj);
        return obj;
    }       
    
    @Transactional
    public List<T> list() {
        return getSession().createCriteria(type).list();
    }
    
    public Criteria getCriteria(){
        return getSession().createCriteria(type);
    }
    
    public Long total(){
        Long total = (Long)getCriteria().setProjection(Projections.rowCount()).uniqueResult();
        return total == null ? 0 : total;
    }
}