/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao.impl;

import com.adril.dao.GenreDao;
import com.adril.entity.Genre;
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
@Repository(value = "genreDao")
@Service
public class GenreDaoImpl implements GenreDao{
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
    public List<Genre> getGenreList() {
        return getSession().createCriteria(Genre.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Genre addGenre(Genre genre) {
        return (Genre) getSession().merge(genre);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Genre getGenreById(Integer id) {
        return (Genre) getSession().createCriteria(Genre.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteGenre(Genre genre) {
        try {
            getSession().delete(genre);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
