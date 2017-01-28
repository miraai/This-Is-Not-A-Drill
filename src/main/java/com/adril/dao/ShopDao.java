/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao;

import com.adril.entity.Shop;
import java.util.List;

/**
 *
 * @author Mirai
 */
public interface ShopDao {
    public List<Shop> getShopList();
    public Shop addShop(Shop shop);
    public void editShop(Shop shop);
    public Shop getShopById(Integer id);
    public boolean deleteShop(Shop shop);
}
