/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller;

import com.adril.dao.GenreDao;
import com.adril.entity.Genre;
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
public class GenreController {
    @Autowired
    private GenreDao genreDao;
    
    @RequestMapping(value = "/addGenre", method = RequestMethod.GET)
    public String addGenre(Model model){
        model.addAttribute("genre", new Genre());
        List genres = genreDao.getGenreList();
        model.addAttribute("genres", genres);
        return "addGenre";
    }
    
    @RequestMapping(value = "addGenre", method = RequestMethod.POST)
    public ModelAndView addGenre(@ModelAttribute("genre") Genre genre, ModelAndView model){
        genre = genreDao.addGenre(genre);
        model.addObject("successMsg", "Genre Added!");
        model.addObject("genre", new Genre());
        List genres = genreDao.getGenreList();
        model.addObject("genres", genres);
        return model;
    }
    
    @RequestMapping(value = "editGenre/{id}", method = RequestMethod.GET)
    public String editGenre(@PathVariable("id") int id, Model model){
        Genre genre = genreDao.getGenreById(id);
        model.addAttribute("genre", genre);
        List genres = genreDao.getGenreList();
        model.addAttribute("genres", genres);
        return "addGenres";
    }
    
    @RequestMapping(value = "deleteGenre/{id}", method = RequestMethod.GET)
    public String deleteGenre(@PathVariable("id") int id, HttpServletRequest request){
        Genre genre = genreDao.getGenreById(id);
        if (genre == null){
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        genreDao.deleteGenre(genre);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
