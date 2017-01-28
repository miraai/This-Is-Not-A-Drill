/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller;

import com.adril.dao.GameDao;
import com.adril.dao.HardwareDao;
import com.adril.dao.MerchandiseDao;
import com.adril.dao.ShopDao;
import com.adril.entity.Merchandise;
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
public class MerchandiseController {
    
    @Autowired
    MerchandiseDao merchandiseDao;
    
    @Autowired
    ShopDao shopDao;
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    HardwareDao hardwareDao;
    
    @RequestMapping(value = "/addMerchandise", method = RequestMethod.GET)
    public String addMerchandise(Model model){
        model.addAttribute("merchandise", new Merchandise());
        List merchandises = merchandiseDao.getMerchandiseList();
        model.addAttribute("merchandises", merchandises);
        model.addAttribute("shops", shopDao.getShopList());
        model.addAttribute("games", gameDao.getGameList());
        model.addAttribute("hardwares", hardwareDao.getHardwareList());
        return "addMerchandise";
    }
    
    @RequestMapping(value = "/addMerchandise", method = RequestMethod.POST)
    public ModelAndView addMerchandise(@ModelAttribute("game") Merchandise merchandise, ModelAndView model){
        merchandise = merchandiseDao.addMerchandise(merchandise);
        model.addObject("successMsg", "Merchandis Added!");
        model.addObject("merchandise", new Merchandise());
        List merchandises = merchandiseDao.getMerchandiseList();
        model.addObject("merchandises", merchandises);
        model.addObject("shops", shopDao.getShopList());
        model.addObject("games", gameDao.getGameList());
        model.addObject("hardwares", hardwareDao.getHardwareList());
        return model;
    }
    
    @RequestMapping(value = "/editMerchandise/{id}", method = RequestMethod.GET)
    public String editMerchandise(@PathVariable("id") int id, Model model){
        Merchandise merchandise = merchandiseDao.getMerchandiseById(id);
        model.addAttribute("merchandise", merchandise);
        List merchandises = merchandiseDao.getMerchandiseList();
        model.addAttribute("merchandises", merchandises);
        model.addAttribute("shops", shopDao.getShopList());
        model.addAttribute("games", gameDao.getGameList());
        model.addAttribute("hardwares", hardwareDao.getHardwareList());
        return "addMerchandise";
    }
    
    @RequestMapping(value = "/deleteMerchandise/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") int id, HttpServletRequest request){
        Merchandise merchandise = merchandiseDao.getMerchandiseById(id);
        if (merchandise == null){
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        merchandiseDao.deleteMerchandise(merchandise);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
