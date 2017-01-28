/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao.impl;

import com.adril.dao.ShopDao;
import com.adril.entity.Shop;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mirai
 */
@Repository(value = "shopDao")
@Service
public class ShopDaoImpl implements ShopDao{
    
    @SuppressWarnings("unused")
    private final Log logger = LogFactory.getLog(getClass());

    //Instanciramo sesiju
    @Autowired
    private SessionFactory sessionFactory;

    //kreiramo seter za sesiju
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //kreiramo geter za sesiju
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Shop> getShopList() {
        return getSession().createCriteria(Shop.class).list();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Shop addShop(Shop shop) {
        return (Shop) getSession().merge(shop);
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Shop getShopById(Integer id) {
        return (Shop) getSession().createCriteria(Shop.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteShop(Shop shop) {
        try {
            getSession().delete(shop);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
