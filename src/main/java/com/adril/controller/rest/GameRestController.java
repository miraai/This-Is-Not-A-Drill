/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller.rest;

import com.adril.dao.GameDao;
import com.adril.entity.Game;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mirai
 */
@RestController
public class GameRestController {
    @Autowired
    private GameDao gameDao;

    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Game>> getGames() {
        System.out.println("Fetching games");

        List<Game> games = gameDao.getGameList();
        if (games.size() == 0) {
            System.out.println("Game list is empty");
            return new ResponseEntity<List<Game>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> getGameById(@PathVariable("id") int id) {
        System.out.println("Fetching game with id " + id);

        Game game = gameDao.getGameById(id);
        if (game == null) {
            System.out.println("Game with id " + id + " not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    @RequestMapping(value = "/game/", method = RequestMethod.POST)
    public ResponseEntity<Void> addGame(@RequestBody Game game) {
        System.out.println("Adding game " + game.toString());
        gameDao.addGame(game);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Game> editGame(@PathVariable("id") int id, @RequestBody Game game) {
        System.out.println("Updating game " + id);

        game.setId(id);
        gameDao.editGame(game);
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Game> deleteGame(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting game with id " + id);

        Game game = gameDao.getGameById(id);
        if (game == null) {
            System.out.println("Unable to delete. Game with id:" + id + "not found");
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Game>(HttpStatus.OK);
    }
}
