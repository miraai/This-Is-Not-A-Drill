/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller;

import com.adril.dao.HardwareDao;
import com.adril.entity.Hardware;
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
public class HardwareController {
    
    @Autowired
    private HardwareDao hardwareDao;
    
    @RequestMapping(value = "/addHardware", method = RequestMethod.GET)
    public String addHardware(Model model){
        model.addAttribute("hardware", new Hardware());
        List hardwares = hardwareDao.getHardwareList();
        model.addAttribute("hardwares", hardwares);
        return "addHardware";
    }
    
    @RequestMapping(value = "/addHardware", method = RequestMethod.POST)
    public ModelAndView addHardware(@ModelAttribute("hardware") Hardware hardware, ModelAndView model){
        hardware = hardwareDao.addHardware(hardware);
        model.addObject("successMsg", "Hardware Added!");
        model.addObject("hardware", new Hardware());
        List hardwares = hardwareDao.getHardwareList();
        model.addObject("hardwares", hardwares);
        return model;
    }
    
    @RequestMapping(value = "/editHardware/{id}", method = RequestMethod.GET)
    public String editHardware(@PathVariable("id") int id, Model model){
        Hardware hardware = hardwareDao.getHardwareById(id);
        model.addAttribute("hardware", hardware);
        List hardwares = hardwareDao.getHardwareList();
        model.addAttribute("hardwares", hardwares);
        return "addHardware";
    }
    
    @RequestMapping(value = "/deleteHardware/{id}", method = RequestMethod.GET)
    public String deleteHardware(@PathVariable("id") int id, HttpServletRequest request){
        Hardware hardware = hardwareDao.getHardwareById(id);
        if (hardware == null){
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        hardwareDao.deleteHardware(hardware);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
