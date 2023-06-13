package com.codecool.autoavenue;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.service.DAO.AdvertDAO;
import com.codecool.autoavenue.service.AdvertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdvertServiceTest {

    @Mock
    private AdvertDAO advertDAO;

    private AdvertService advertService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        advertService = new AdvertService();
        advertService.setAdvertDAO(advertDAO);
    }

    @Test
    void testGetAllAdverts() {
        // Arrange
        Advert advert1 = new Advert();
        Advert advert2 = new Advert();
        List<Advert> expectedAdverts = Arrays.asList(advert1, advert2);

        when(advertDAO.findAll()).thenReturn(expectedAdverts);

        // Act
        List<Advert> actualAdverts = advertService.getAllAdverts();

        // Assert
        assertEquals(expectedAdverts, actualAdverts);
        verify(advertDAO, times(1)).findAll();
    }

    @Test
    void testGetAdvertById() {
        // Arrange
        Long id = 1L;
        Advert expectedAdvert = new Advert();

        when(advertDAO.findById(id)).thenReturn(Optional.of(expectedAdvert));

        // Act
        Advert actualAdvert = advertService.getAdvertById(id);

        // Assert
        assertEquals(expectedAdvert, actualAdvert);
        verify(advertDAO, times(1)).findById(id);
    }

    @Test
    void testGetAdvertsByUserId() {
        // Arrange
        Long userId = 1L;
        Advert advert1 = new Advert();
        Advert advert2 = new Advert();
        List<Advert> expectedAdverts = Arrays.asList(advert1, advert2);

        when(advertDAO.findBySellerId(userId)).thenReturn(expectedAdverts);

        // Act
        List<Advert> actualAdverts = advertService.getAdvertsByUserId(userId);

        // Assert
        assertEquals(expectedAdverts, actualAdverts);
        verify(advertDAO, times(1)).findBySellerId(userId);
    }

    @Test
    void testAddAdvert() {
        // Arrange
        Advert advert = new Advert();
        when(advertDAO.save(advert)).thenReturn(advert);

        // Act
        Advert savedAdvert = advertService.addAdvert(advert);

        // Assert
        assertEquals(advert, savedAdvert);
        verify(advertDAO, times(1)).save(advert);
    }

    @Test
    void testUpdateAdvertById() {
        // Arrange
        Long id = 1L;
        Advert existingAdvert = new Advert();
        existingAdvert.setId(id);

        Advert updatedAdvert = new Advert();
        updatedAdvert.setId(id);
        updatedAdvert.setManufacturer("Updated Manufacturer");

        when(advertDAO.findById(id)).thenReturn(Optional.of(existingAdvert));
        when(advertDAO.save(existingAdvert)).thenReturn(updatedAdvert);

        // Act
        advertService.updateAdvertById(id, updatedAdvert);

        // Assert
        assertEquals(updatedAdvert.getManufacturer(), existingAdvert.getManufacturer());
        verify(advertDAO, times(1)).findById(id);
        verify(advertDAO, times(1)).save(existingAdvert);
    }

    @Test
    void testDeleteAdvertById() {
        // Arrange
        Long id = 1L;

        // Act
        advertService.deleteAdvertById(id);

        // Assert
        verify(advertDAO, times(1)).deleteById(id);
    }



}

