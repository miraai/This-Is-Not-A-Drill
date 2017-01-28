/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.converter;

import com.adril.dao.GameDao;
import com.adril.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Mirai
 */
public class IntegerToGame implements Converter<String , Game>{
    
    @Autowired
    GameDao gameDao;
    
    @Override
    public Game convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Game gem = gameDao.getGameById(valeu);
        return gem;
    }
    
}
