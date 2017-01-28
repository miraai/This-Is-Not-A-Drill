/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.converter;

import com.adril.dao.HardwareDao;
import com.adril.entity.Hardware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mirai
 */
@Component
public class IntegerToHardware implements Converter<String , Hardware>{
    
    @Autowired
    HardwareDao hardwareDao;
    
    @Override
    public Hardware convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Hardware hard = hardwareDao.getHardwareById(valeu);
        return hard;
    }
    
}
