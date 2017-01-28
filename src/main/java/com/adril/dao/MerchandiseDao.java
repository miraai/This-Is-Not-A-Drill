/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao;

import com.adril.entity.Merchandise;
import java.util.List;

/**
 *
 * @author Mirai
 */
public interface MerchandiseDao {
    public List<Merchandise> getMerchandiseList();
    public Merchandise addMerchandise(Merchandise merchandise);
    public Merchandise getMerchandiseById(Integer id);
    public void editMerchandise(Merchandise merchandise);
    public boolean deleteMerchandise(Merchandise merchandise);
    public List<Merchandise> merchandiseByShopId(Integer id);
    public List<Merchandise> merchandiseByGameId(Integer id);
    public List<Merchandise> merchandiseByHardwareId(Integer id);
}
