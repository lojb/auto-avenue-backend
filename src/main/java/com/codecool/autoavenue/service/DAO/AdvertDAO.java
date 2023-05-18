package com.codecool.autoavenue.service.DAO;

import com.codecool.autoavenue.model.Advert;
import com.codecool.autoavenue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertDAO extends JpaRepository<Advert, Long> {
    List<Advert> findBySellerId(Long id);
}
