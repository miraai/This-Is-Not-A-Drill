/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.converter;

import com.adril.dao.ShopDao;
import com.adril.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mirai
 */
@Component
public class IntegerToShop implements Converter<String , Shop>{
    
    @Autowired
    ShopDao shopDao;
    
    @Override
    public Shop convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Shop sho = shopDao.getShopById(valeu);
        return sho;
    }
    
}
