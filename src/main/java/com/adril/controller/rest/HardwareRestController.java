/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.controller.rest;


import com.adril.dao.HardwareDao;
import com.adril.entity.Hardware;
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
public class HardwareRestController {
    @Autowired
    HardwareDao hardwareDao;
    
    @RequestMapping(value = "/hardwares", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Hardware>> getHardwares() {
        System.out.println("Fetching hardwares");
        List<Hardware> hardwares = hardwareDao.getHardwareList();
        if (hardwares.size() == 0) {
            System.out.println("Hardwares list is empty");
            return new ResponseEntity<List<Hardware>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Hardware>>(hardwares, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hardware/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hardware> getHardwareById(@PathVariable("id") int id) {
        System.out.println("Fetching hardwares with id:" + id);
        Hardware hardware = hardwareDao.getHardwareById(id);
        if (hardware == null) {
            System.out.println("Hardware with id " + id + " not found");
            return new ResponseEntity<Hardware>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Hardware>(hardware, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hardware/", method = RequestMethod.POST)
    public ResponseEntity<Void> addHardware(@RequestBody Hardware hardware) {
        System.out.println("Adding hardware " + hardware.toString());
        hardwareDao.addHardware(hardware);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/hardware/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Hardware> editHardware(@PathVariable("id") int id, @RequestBody Hardware hardware){
        System.out.println("Updating hardware " + id);
        
        hardware.setId(id);
        hardwareDao.editHardware(hardware);
        return new ResponseEntity<Hardware>(hardware, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hardware/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Hardware> deleteHardware(@PathVariable("id") int id){
        System.out.println("Fetching & Deleting hardware with id " + id);
        
        Hardware hardware = hardwareDao.getHardwareById(id);
        if(hardware == null){
            System.out.println("Unable to delete. Hardware with id:" + id + "not found");
            return new ResponseEntity<Hardware>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Hardware>(HttpStatus.OK);
    }
}
