/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.converter;

import com.adril.dao.GenreDao;
import com.adril.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mirai
 */
@Component
public class IntegerToGenre implements Converter<String , Genre>{

    @Autowired
    GenreDao genreDao;
    
    @Override
    public Genre convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Genre gen = genreDao.getGenreById(valeu);
        return gen;
    }
    
}
