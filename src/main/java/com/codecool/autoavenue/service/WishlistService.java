package com.codecool.autoavenue.service;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.model.User;
import com.codecool.autoavenue.model.Wishlist;
import com.codecool.autoavenue.service.DAO.WishlistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistDAO wishlistDAO;

    public List<Wishlist> getAllWishlists() {
        return wishlistDAO.findAll();
    }

    public Wishlist getWishlistById(Long id) {
        return wishlistDAO.findById(id).get();
    }

    public Wishlist getWishlistByUserId(Long id) {
        return wishlistDAO.findWishlistByUser(id);
    }

    public void addWishlistForUser(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlistDAO.save(wishlist);
    }

    public void addAdvertToWishlist(Long id, Advert advert) {
        Wishlist wishlistToUpdate = wishlistDAO.findById(id).get();

        List<Advert> list = wishlistToUpdate.getWishlistItems();
        list.add(advert);

        wishlistToUpdate.setWishlistItems(list);

        wishlistDAO.save(wishlistToUpdate);
    }

    public void deleteWishlist(Long id) {
        wishlistDAO.deleteById(id);
    }
}
