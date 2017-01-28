/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao.impl;

import com.adril.dao.GameDao;
import com.adril.entity.Game;
import com.adril.entity.Genre;
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
@Repository("gameDao")
@Service
public class GameDaoImpl implements GameDao{
    
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
    public List<Game> getGameList() {
        return getSession().createCriteria(Game.class).list();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Game addGame(Game game) {
        return (Game) getSession().merge(game);
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Game getGameById(Integer id) {
        return (Game) getSession().createCriteria(Game.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteGame(Game game) {
        try {
            getSession().delete(game);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Game> gamesByGenreId(Integer id) {
        try{
            Genre gen = (Genre) getSession().createCriteria(Genre.class).add(Restrictions.eq("id", id)).uniqueResult();
            List games = getSession().createCriteria(Game.class).add(Restrictions.eq("genreId", gen)).list();
            return games;
        }catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void editGame(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
