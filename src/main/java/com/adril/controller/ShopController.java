/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller;

import com.adril.dao.ShopDao;
import com.adril.entity.Shop;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mirai
 */
@Controller
public class ShopController {
    
    @Autowired
    ShopDao shopDao;
    
    @RequestMapping(value = "/addShop", method = RequestMethod.GET)
    public String addShop(Model model){
        model.addAttribute("shop", new Shop());
        List shops = shopDao.getShopList();
        model.addAttribute("shops", shops);
        return "addShop";
    }
    
    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    public ModelAndView addShop(@ModelAttribute("shop") Shop shop, ModelAndView model){
        shop = shopDao.addShop(shop);
        model.addObject("successMsg", "Shop Added!");
        model.addObject("shop", new Shop());
        List shops = shopDao.getShopList();
        model.addObject("shops", shops);
        return model;
    }
    
    @RequestMapping(value = "/editShop/{id}", method = RequestMethod.GET)
    public String editShop(@PathVariable("id") int id, Model model){
        Shop shop = shopDao.getShopById(id);
        model.addAttribute("shop", shop);
        List shops = shopDao.getShopList();
        model.addAttribute("shops", shops);
        return "addShop";
    }
    
    @RequestMapping(value = "/deleteShop/{id}", method = RequestMethod.GET)
    public String deleteShop(@PathVariable("id") int id, HttpServletRequest request){
        Shop shop = shopDao.getShopById(id);
        if (shop == null){
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        shopDao.deleteShop(shop);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    
}
