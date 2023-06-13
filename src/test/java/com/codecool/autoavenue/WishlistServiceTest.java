package com.codecool.autoavenue;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.model.User;
import com.codecool.autoavenue.model.Wishlist;
import com.codecool.autoavenue.service.DAO.WishlistDAO;
import com.codecool.autoavenue.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WishlistServiceTest {

    private WishlistService wishlistService;

    @Mock
    private WishlistDAO wishlistDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        wishlistService = new WishlistService();
        wishlistService.setWishlistDAO(wishlistDAO);
    }

    @Test
    void testGetAllWishlists() {
        // Arrange
        List<Wishlist> expectedWishlists = new ArrayList<>();
        expectedWishlists.add(new Wishlist());
        expectedWishlists.add(new Wishlist());
        when(wishlistDAO.findAll()).thenReturn(expectedWishlists);

        // Act
        List<Wishlist> actualWishlists = wishlistService.getAllWishlists();

        // Assert
        assertEquals(expectedWishlists, actualWishlists);
        verify(wishlistDAO, times(1)).findAll();
    }

    @Test
    void testGetWishlistById() {
        // Arrange
        Long wishlistId = 1L;
        Wishlist expectedWishlist = new Wishlist();
        when(wishlistDAO.findWishlistById(wishlistId)).thenReturn(expectedWishlist);

        // Act
        Wishlist actualWishlist = wishlistService.getWishlistById(wishlistId);

        // Assert
        assertEquals(expectedWishlist, actualWishlist);
        verify(wishlistDAO, times(1)).findWishlistById(wishlistId);
    }

    @Test
    void testAddWishlistForUser() {
        // Arrange
        User user = new User();

        // Act
        wishlistService.addWishlistForUser(user);

        // Assert
        verify(wishlistDAO, times(1)).save(any(Wishlist.class));
    }

    @Test
    void testAddAdvertToWishlist() {
        // Arrange
        Long wishlistId = 1L;
        Wishlist wishlist = new Wishlist();
        wishlist.setId(wishlistId);
        Advert advert = new Advert();
        when(wishlistDAO.findWishlistById(wishlistId)).thenReturn(wishlist);

        // Act
        wishlistService.addAdvertToWishlist(wishlistId, advert);

        // Assert
        assertEquals(1, wishlist.getWishlistItems().size());
        assertEquals(advert, wishlist.getWishlistItems().get(0));
        verify(wishlistDAO, times(1)).save(wishlist);
    }

    @Test
    void testDeleteWishlistItem() {
        // Arrange
        Long itemId = 1L;
        Long wishlistId = 1L;
        Wishlist wishlist = new Wishlist();
        wishlist.setId(wishlistId);
        Advert advert = new Advert();
        advert.setId(itemId);
        wishlist.getWishlistItems().add(advert);
        when(wishlistDAO.findWishlistById(wishlistId)).thenReturn(wishlist);

        // Act
        wishlistService.deleteWishlistItem(itemId, wishlistId);

        // Assert
        assertEquals(0, wishlist.getWishlistItems().size());
        verify(wishlistDAO, times(1)).save(wishlist);
    }

    @Test
    void testDeleteWishlist() {
        // Arrange
        Long wishlistId = 1L;

        // Act
        wishlistService.deleteWishlist(wishlistId);

        // Assert
        verify(wishlistDAO, times(1)).deleteById(wishlistId);
    }
}