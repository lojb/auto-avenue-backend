package com.codecool.autoavenue.service;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.model.User;
import com.codecool.autoavenue.model.Wishlist;
import com.codecool.autoavenue.service.DAO.WishlistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistDAO wishlistDAO;

    public void setWishlistDAO(WishlistDAO wishlistDAO) {
        this.wishlistDAO = wishlistDAO;
    }

    public List<Wishlist> getAllWishlists() {
        return wishlistDAO.findAll();
    }

    public Wishlist getWishlistById(Long id) {
        return wishlistDAO.findWishlistById(id);
    }

    public void addWishlistForUser(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlistDAO.save(wishlist);
    }

    public void addAdvertToWishlist(Long id, Advert advert) {
        Wishlist wishlistToUpdate = wishlistDAO.findWishlistById(id);

        wishlistToUpdate.getWishlistItems().add(advert);
        wishlistDAO.save(wishlistToUpdate);
    }

    public void deleteWishlistItem(Long itemId, Long wishlistId) {
        Wishlist wishlist = getWishlistById(wishlistId);

        Advert item = wishlist.getWishlistItems().stream().filter(i -> i.getId() == itemId).findFirst().get();

        wishlist.getWishlistItems().remove(item);

        wishlistDAO.save(wishlist);
    }

    public void deleteWishlist(Long id) {
        wishlistDAO.deleteById(id);
    }
}
