package com.codecool.autoavenue.controller;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adverts")
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    @GetMapping
    public List<Advert> getAllAdverts() {
        return advertService.getAllAdverts();
    }

    @GetMapping("/{id}")
    public Advert getAdvertById(@PathVariable("id") Long id) {
        return advertService.getAdvertById(id);
    }

    @PostMapping
    public ResponseEntity<Void> addAdvert(@RequestBody Advert advert){
        advertService.addAdvert(advert);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdvertById(@PathVariable("id") Long id, @RequestBody Advert updatedAdvert){
        Advert advertToUpdate = advertService.getAdvertById(id);

        if (advertToUpdate != null) {
            advertService.updateAdvertById(id, updatedAdvert);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertById(@PathVariable("id") Long id){
        Advert advertToDelete = advertService.getAdvertById(id);

        if (advertToDelete != null) {
            advertService.deleteAdvertById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
