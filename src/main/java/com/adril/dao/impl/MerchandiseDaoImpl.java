/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao.impl;

import com.adril.dao.MerchandiseDao;
import com.adril.entity.Game;
import com.adril.entity.Hardware;
import com.adril.entity.Merchandise;
import com.adril.entity.Shop;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mirai
 */
@Repository(value = "merchandiseDao")
@Service
public class MerchandiseDaoImpl implements MerchandiseDao{
    
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
    public List<Merchandise> getMerchandiseList() {
        return getSession().createCriteria(Merchandise.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Merchandise addMerchandise(Merchandise merchandise) {
        return (Merchandise) getSession().merge(merchandise);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Merchandise getMerchandiseById(Integer id) {
        return (Merchandise) getSession().createCriteria(Merchandise.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteMerchandise(Merchandise merchandise) {
        try {
            getSession().delete(merchandise);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Merchandise> merchandiseByShopId(Integer id) {
        try{
            Shop sho = (Shop) getSession().createCriteria(Shop.class).add(Restrictions.eq("id", id)).uniqueResult();
            List merchandises = getSession().createCriteria(Shop.class).add(Restrictions.eq("shopId", sho)).list();
            return merchandises;
        }catch (Exception ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Merchandise> merchandiseByGameId(Integer id) {
        try{
            Game gem = (Game) getSession().createCriteria(Game.class).add(Restrictions.eq("id", id)).uniqueResult();
            List games = getSession().createCriteria(Game.class).add(Restrictions.eq("gameId", gem)).list();
            return games;
        }catch (Exception ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Merchandise> merchandiseByHardwareId(Integer id) {
        try{
            Hardware hard = (Hardware) getSession().createCriteria(Hardware.class).add(Restrictions.eq("id", id)).uniqueResult();
            List hardwares = getSession().createCriteria(Hardware.class).add(Restrictions.eq("gameId", hard)).list();
            return hardwares;
        }catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void editMerchandise(Merchandise merchandise) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
