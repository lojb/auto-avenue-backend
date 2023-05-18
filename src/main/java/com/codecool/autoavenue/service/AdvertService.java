package com.codecool.autoavenue.service;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.service.DAO.AdvertDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {
    @Autowired
    private AdvertDAO advertDAO;

    public List<Advert> getAllAdverts() {
        return advertDAO.findAll();
    }

    public Advert getAdvertById(Long id) {
        return advertDAO.findById(id).get();
    }

    public List<Advert> getAdvertsByUserId(Long id) {
        return advertDAO.findBySellerId(id);
    }

    public Advert addAdvert(Advert advert) {
       return advertDAO.save(advert);
    }

    public void updateAdvertById(Long id, Advert advert) {
        Advert advertToUpdate = getAdvertById(id);

        advertToUpdate.setManufacturer(advert.getManufacturer());
        advertToUpdate.setModel(advert.getModel());
        advertToUpdate.setYear(advert.getYear());
        advertToUpdate.setManufacturer(advert.getManufacturer());
        advertToUpdate.setTitle(advert.getTitle());
        advertToUpdate.setDescription(advert.getDescription());
        advertToUpdate.setPrice(advert.getPrice());

        advertDAO.save(advertToUpdate);
    }

    public void deleteAdvertById(Long id) {
        advertDAO.deleteById(id);
    }
}
