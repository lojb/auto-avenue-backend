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

    @GetMapping
    public List<Advert> getAllAdverts(@RequestParam(required = false, value = "manufacturer") String manufacturer,
                                      @RequestParam(required = false, value = "model") String model,
                                      @RequestParam(required = false, value = "transmission") String transmission,
                                      @RequestParam(required = false, value = "minPrice") Double minPrice,
                                      @RequestParam(required = false, value = "maxPrice") Double maxPrice,
                                      @RequestParam(required = false, value = "minYear") Integer minYear,
                                      @RequestParam(required = false, value = "maxYear") Integer maxYear) {
        return advertService.getAllAdverts().stream()
                .filter(a -> manufacturer == null || a.getManufacturer().toLowerCase().contains(manufacturer.toLowerCase()))
                .filter(a -> model == null || a.getModel().toLowerCase().contains(model.toLowerCase()))
                .filter(a -> transmission == null || a.getTransmission().toLowerCase().contains(transmission.toLowerCase()))
                .filter(a -> minPrice == null || a.getPrice() >= minPrice)
                .filter(a -> maxPrice == null || a.getPrice() <= maxPrice)
                .filter(a -> minYear == null || a.getYear() >= minYear)
                .filter(a -> maxYear == null || a.getYear() <= maxYear)
                .toList();
    }

    @GetMapping("/{id}")
    public Advert getAdvertById(@PathVariable("id") Long id) {
        return advertService.getAdvertById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Advert> getAdvertsByUserId(@PathVariable("userId") Long userId) {
        return advertService.getAdvertsByUserId(userId);
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
