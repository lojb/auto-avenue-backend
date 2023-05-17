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
@CrossOrigin(origins = "http://localhost:3000")
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    //csúnya nagyon de megy
    @GetMapping
    public List<Advert> getAllAdverts(@RequestParam(required = false, value = "manufacturer") String manufacturer,
                                      @RequestParam(required = false, value = "model") String model,
                                      @RequestParam(required = false, value = "minPrice") Double minPrice,
                                      @RequestParam(required = false, value = "maxPrice") Double maxPrice,
                                      @RequestParam(required = false, value = "minYear") Integer minYear,
                                      @RequestParam(required = false, value = "maxYear") Integer maxYear) {
        var adverts = advertService.getAllAdverts().stream();
        if (manufacturer != null) {
            adverts = adverts.filter(a -> a.getManufacturer().toLowerCase().equals(manufacturer.toLowerCase()));
        }
        if (model != null) {
            adverts = adverts.filter(a -> a.getModel().toLowerCase().contains(model.toLowerCase()));
        }
        if (minPrice != null) {
            adverts = adverts.filter(a -> a.getPrice() >= minPrice);
        }
        if (maxPrice != null) {
            adverts = adverts.filter(a -> a.getPrice() <= maxPrice);
        }
        if (minYear != null) {
            adverts = adverts.filter(a -> a.getYear() >= minYear);
        }
        if (maxYear != null) {
            adverts = adverts.filter(a -> a.getYear() <= maxYear);
        }
        return adverts.toList();
    }

    @GetMapping("/{id}")
    public Advert getAdvertById(@PathVariable("id") Long id) {
        return advertService.getAdvertById(id);
    }

    @PostMapping
    public ResponseEntity<Advert> addAdvert(@RequestBody Advert advert){
       Advert createdAdvert = advertService.addAdvert(advert);
        return new ResponseEntity<>(createdAdvert, HttpStatus.OK);
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
