/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.converter;

import com.adril.dao.MerchandiseDao;
import com.adril.entity.Merchandise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mirai
 */
@Component
public class IntegerToMerchandise implements Converter<String , Merchandise>{
    
    @Autowired
    MerchandiseDao merchandiseDao;

    @Override
    public Merchandise convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Merchandise merch = merchandiseDao.getMerchandiseById(valeu);
        return merch;
    }
    
    
}
