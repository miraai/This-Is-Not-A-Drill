/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller.rest;

import com.adril.dao.GenreDao;
import com.adril.entity.Genre;
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
public class GenreRestController {
    
    @Autowired
    GenreDao genreDao;
    
    @RequestMapping(value = "/genres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Genre>> getGenres() {
        System.out.println("Fetching genres");
        List<Genre> genres = genreDao.getGenreList();
        if (genres.size() == 0) {
            System.out.println("Genres list is empty");
            return new ResponseEntity<List<Genre>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/genre/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") int id) {
        System.out.println("Fetching genres with id:" + id);
        Genre genre = genreDao.getGenreById(id);
        if (genre == null) {
            System.out.println("Genre with id " + id + " not found");
            return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Genre>(genre, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/genre/", method = RequestMethod.POST)
    public ResponseEntity<Void> addGenre(@RequestBody Genre genre) {
        System.out.println("Adding genre " + genre.toString());
        genreDao.addGenre(genre);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/genre/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Genre> editCategory(@PathVariable("id") int id, @RequestBody Genre genre){
        System.out.println("Updating genre " + id);
        
        genre.setId(id);
        genreDao.editGenre(genre);
        return new ResponseEntity<Genre>(genre, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/genre/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Genre> deleteCategory(@PathVariable("id") int id){
        System.out.println("Fetching & Deleting genre with id " + id);
        
        Genre genre = genreDao.getGenreById(id);
        if(genre == null){
            System.out.println("Unable to delete. Genre with id:" + id + "not found");
            return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Genre>(HttpStatus.OK);
    }
}
