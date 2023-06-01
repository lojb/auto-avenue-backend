package com.codecool.autoavenue.controller;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.model.User;
import com.codecool.autoavenue.model.Wishlist;
import com.codecool.autoavenue.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    public List<Wishlist> getAllWishlist() {
        return wishlistService.getAllWishlists();
    }

    @GetMapping("/{id}")
    public Wishlist getWishlistByUserId(@PathVariable("id") Long id) {
        return wishlistService.getWishlistById(id);
    }

    @PostMapping
    public ResponseEntity<Void> addWishlist(@RequestBody User user) {
        wishlistService.addWishlistForUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> addToWishlist(@PathVariable("id") Long id, @RequestBody Advert advert) {
        wishlistService.addAdvertToWishlist(id, advert);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-item")
    public ResponseEntity<Void> deleteItemFromWishlist(@RequestParam(value = "item") Long itemId,
                                                       @RequestParam(value = "wishlist") Long wishlistId) {
        wishlistService.deleteWishlistItem(itemId, wishlistId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlistById(@PathVariable("id") Long id) {
        Wishlist wishlistToDelete = wishlistService.getWishlistById(id);

        if (wishlistToDelete != null) {
            wishlistService.deleteWishlist(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
