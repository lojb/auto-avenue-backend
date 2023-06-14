package com.codecool.autoavenue;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.service.DAO.AdvertDAO;
import com.codecool.autoavenue.service.AdvertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class AdvertServiceTest {

    @Mock
    private AdvertDAO advertDAO;

    @InjectMocks
    private AdvertService advertService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        advertDAO = Mockito.mock(AdvertDAO.class);
        advertService = new AdvertService();
        advertService.setAdvertDAO(advertDAO);
    }

    @Test
    void testGetAllAdverts() {
        Advert advert1 = new Advert();
        Advert advert2 = new Advert();
        List<Advert> expectedAdverts = Arrays.asList(advert1, advert2);

        when(advertDAO.findAll()).thenReturn(expectedAdverts);

        List<Advert> actualAdverts = advertService.getAllAdverts();

        assertEquals(expectedAdverts, actualAdverts);
        verify(advertDAO, times(1)).findAll();
    }

    @Test
    void testGetAdvertById() {
        Long id = 1L;
        Advert expectedAdvert = new Advert();

        when(advertDAO.getAdvertById(id)).thenReturn(expectedAdvert);

        Advert actualAdvert = advertService.getAdvertById(id);
        assertEquals(expectedAdvert, actualAdvert);
        verify(advertDAO, times(1)).getAdvertById(id);
    }

    @Test
    void testGetAdvertsByUserId() {
        Long userId = 1L;
        Advert advert1 = new Advert();
        Advert advert2 = new Advert();
        List<Advert> expectedAdverts = Arrays.asList(advert1, advert2);

        when(advertDAO.findBySellerId(userId)).thenReturn(expectedAdverts);

        List<Advert> actualAdverts = advertService.getAdvertsByUserId(userId);

        assertEquals(expectedAdverts, actualAdverts);
        verify(advertDAO, times(1)).findBySellerId(userId);
    }

    @Test
    void testAddAdvert() {
        Advert advert = new Advert();
        when(advertDAO.save(advert)).thenReturn(advert);

        Advert savedAdvert = advertService.addAdvert(advert);

        assertEquals(advert, savedAdvert);
        verify(advertDAO, times(1)).save(advert);
    }

    @Test
    void testUpdateAdvertById() {
        Long id = 1L;
        Advert existingAdvert = new Advert();
        existingAdvert.setId(id);

        Advert updatedAdvert = new Advert();
        updatedAdvert.setId(id);
        updatedAdvert.setManufacturer("Updated Manufacturer");

        when(advertDAO.getAdvertById(id)).thenReturn(existingAdvert);
        when(advertDAO.save(existingAdvert)).thenReturn(updatedAdvert);

        advertService.updateAdvertById(id, updatedAdvert);

        assertEquals(updatedAdvert.getManufacturer(), existingAdvert.getManufacturer());
        verify(advertDAO, times(1)).getAdvertById(id);
        verify(advertDAO, times(1)).save(existingAdvert);
    }

    @Test
    void testDeleteAdvertById() {
        Long id = 1L;
        Advert advert = new Advert();
        advert.setId(id);
        advert.setActive(true);

        Mockito.when(advertService.getAdvertById(id)).thenReturn(advert);

        advertService.deleteAdvertById(id);

        assertFalse(advert.isActive());
    }
}

