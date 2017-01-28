/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao.impl;

import com.adril.dao.HardwareDao;
import com.adril.entity.Hardware;
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
@Repository(value = "hardwareDao")
@Service
public class HardwareDaoImpl implements HardwareDao{
    
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
    public List<Hardware> getHardwareList() {
        return getSession().createCriteria(Hardware.class).list();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Hardware addHardware(Hardware hardware) {
        return (Hardware) getSession().merge(hardware);
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Hardware getHardwareById(Integer id) {
        return (Hardware) getSession().createCriteria(Hardware.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteHardware(Hardware hardware) {
        try {
            getSession().delete(hardware);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void editHardware(Hardware hardware) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
