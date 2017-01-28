/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller;

import com.adril.dao.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mirai
 */
@Controller
public class GenreController {
    @Autowired
    private GenreDao genreDao;
    
    @RequestMapping(value = "/addGenre", method = RequestMethod.GET)
    public String addGenre(Model model){
        return "addGenre";
    }
}
