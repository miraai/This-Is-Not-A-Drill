/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller.rest;

import com.adril.dao.ShopDao;
import com.adril.entity.Shop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mirai
 */
public class ShopRestController {
    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "/shops", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Shop>> getShops() {
        System.out.println("Fetching shops");

        List<Shop> shops = shopDao.getShopList();
        if (shops.size() == 0) {
            System.out.println("Shop list is empty");
            return new ResponseEntity<List<Shop>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
    }

    @RequestMapping(value = "/shop/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shop> getShopById(@PathVariable("id") int id) {
        System.out.println("Fetching shop with id " + id);

        Shop shop = shopDao.getShopById(id);
        if (shop == null) {
            System.out.println("Shop with id " + id + " not found");
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }

    @RequestMapping(value = "/shop/", method = RequestMethod.POST)
    public ResponseEntity<Void> addShop(@RequestBody Shop shop) {
        System.out.println("Adding shop " + shop.toString());
        shopDao.addShop(shop);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/shop/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Shop> editShop(@PathVariable("id") int id, @RequestBody Shop shop) {
        System.out.println("Updating shop " + id);

        shop.setId(id);
        shopDao.editShop(shop);
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }

    @RequestMapping(value = "/shop/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Shop> deleteShop(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting shop with id " + id);

        Shop shop = shopDao.getShopById(id);
        if (shop == null) {
            System.out.println("Unable to delete. Shop with id:" + id + "not found");
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Shop>(HttpStatus.OK);
    }
}
