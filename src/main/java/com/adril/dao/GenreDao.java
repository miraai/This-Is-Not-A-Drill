/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.dao;

import com.adril.entity.Genre;
import java.util.List;

/**
 *
 * @author Mirai
 */
public interface GenreDao {
    public List<Genre> getGenreList();
    public Genre addGenre(Genre genre);
    public void editGenre(Genre genre);
    public Genre getGenreById(Integer id);
    public boolean deleteGenre(Genre genre);
}
