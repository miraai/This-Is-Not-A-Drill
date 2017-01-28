/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller.rest;

import com.adril.dao.MerchandiseDao;
import com.adril.entity.Merchandise;
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
public class MerchandiseRestController {
    @Autowired
    MerchandiseDao merchandiseDao;
    
    @RequestMapping(value = "/merchandises", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Merchandise>> getMerchandises() {
        System.out.println("Fetching merchandise");
        List<Merchandise> merchandises = merchandiseDao.getMerchandiseList();
        if (merchandises.size() == 0) {
            System.out.println("Merchandise list is empty");
            return new ResponseEntity<List<Merchandise>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Merchandise>>(merchandises, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/merchandise/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchandise> getMerchandiseById(@PathVariable("id") int id) {
        System.out.println("Fetching merchandise with id:" + id);
        Merchandise merchandise = merchandiseDao.getMerchandiseById(id);
        if (merchandise == null) {
            System.out.println("Merchandise with id " + id + " not found");
            return new ResponseEntity<Merchandise>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Merchandise>(merchandise, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/merchandise/", method = RequestMethod.POST)
    public ResponseEntity<Void> addMerchandise(@RequestBody Merchandise merchandise) {
        System.out.println("Adding merchandise " + merchandise.toString());
        merchandiseDao.addMerchandise(merchandise);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/merchandise/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Merchandise> editMerchandise(@PathVariable("id") int id, @RequestBody Merchandise merchandise){
        System.out.println("Updating merchandise " + id);
        
        merchandise.setId(id);
        merchandiseDao.editMerchandise(merchandise);
        return new ResponseEntity<Merchandise>(merchandise, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/merchandise/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Merchandise> deleteMerchandise(@PathVariable("id") int id){
        System.out.println("Fetching & Deleting merchandise with id " + id);
        
        Merchandise merchandise = merchandiseDao.getMerchandiseById(id);
        if(merchandise == null){
            System.out.println("Unable to delete. Merchandise with id:" + id + "not found");
            return new ResponseEntity<Merchandise>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Merchandise>(HttpStatus.OK);
    }
}
