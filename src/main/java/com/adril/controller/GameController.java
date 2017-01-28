package com.adril.controller;

import com.adril.dao.GameDao;
import com.adril.dao.GenreDao;
import com.adril.entity.Game;
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
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mirai
 */
@Controller
public class GameController {
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    GenreDao genreDao;
    
    @RequestMapping(value = "/addGame", method = RequestMethod.GET)
    public String addGame(Model model){
        model.addAttribute("game", new Game());
        List games = gameDao.getGameList();
        model.addAttribute("games", games);
        model.addAttribute("genres", genreDao.getGenreList());
        return "addGame";
    }
    
    @RequestMapping(value = "/addGame", method = RequestMethod.POST)
    public ModelAndView addGame(@ModelAttribute("game") Game game, ModelAndView model){
        game = gameDao.addGame(game);
        model.addObject("successMsg", "Game Added!");
        model.addObject("game", new Game());
        List games = gameDao.getGameList();
        model.addObject("games", games);
        model.addObject("genres", genreDao.getGenreList());
        return model;
    }
    
    @RequestMapping(value = "/editGame/{id}", method = RequestMethod.GET)
    public String editGame(@PathVariable("id") int id, Model model){
        Game game = gameDao.getGameById(id);
        model.addAttribute("game", game);
        List games = gameDao.getGameList();
        model.addAttribute("games", games);
        model.addAttribute("genres", genreDao.getGenreList());
        return "addGame";
    }
    
    @RequestMapping(value = "/deleteGame/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") int id, HttpServletRequest request){
        Game game = gameDao.getGameById(id);
        if (game == null){
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        gameDao.deleteGame(game);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
