/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao;

import com.adril.entity.Hardware;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mirai
 */
public interface HardwareDao{
    public List<Hardware> getHardwareList();
    public Hardware addHardware(Hardware hardware);
    public void editHardware(Hardware hardware);
    public Hardware getHardwareById(Integer id);
    public boolean deleteHardware(Hardware hardware);
}
