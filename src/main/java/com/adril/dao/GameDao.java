/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao;

import com.adril.entity.Game;
import java.util.List;

/**
 *
 * @author Mirai
 */
public interface GameDao {
    public List<Game> getGameList();
    public Game addGame(Game game);
    public Game getGameById(Integer id);
    public void editGame(Game game);
    public boolean deleteGame(Game game);
    public List<Game> gamesByGenreId(Integer id);
}
