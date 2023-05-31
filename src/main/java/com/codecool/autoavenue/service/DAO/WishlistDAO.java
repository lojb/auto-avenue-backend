package com.codecool.autoavenue.service.DAO;

import com.codecool.autoavenue.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistDAO extends JpaRepository<Wishlist, Long> {
    Wishlist findWishlistByUser(Long id);
}
